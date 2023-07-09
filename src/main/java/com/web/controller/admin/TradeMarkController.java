package com.web.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.dao.CategoryDao;
import com.web.dao.TradeMarkDao;
import com.web.entity.Category;
import com.web.entity.TradeMark;
import com.web.validate.CategoryValidate;
import com.web.validate.TradeMarkValidate;

@Controller
public class TradeMarkController {


	@Autowired
	private TradeMarkDao tradeMarkDao;

	@Autowired
	private TradeMarkValidate tradeMarkValidate;

	
	@RequestMapping(value = { "/admin/trademark" }, method = RequestMethod.GET)
	public String trademark(Model model) {
		model.addAttribute("trademarks", tradeMarkDao.findAll());
		return "/views/admin/trademark";
	}
	
	@RequestMapping(value = { "/admin/addtrademark" }, method = RequestMethod.GET)
	public String addtrademark(Model model,@RequestParam(value = "id", required = false) Integer id) {
		TradeMark tradeMark = null;
		if (id == null) {
			tradeMark = new TradeMark();
		} else {
			tradeMark = tradeMarkDao.findById(id);
		}
		model.addAttribute("trademark", tradeMark);
		return "/views/admin/addtrademark";
	}
	
	@RequestMapping(value = { "/admin/addtrademarkPost" }, method = RequestMethod.POST)
	public String addtrademarkPost(@Valid @ModelAttribute("trademark") TradeMark tradeMark, BindingResult bindingResult) {
		tradeMarkValidate.validate(tradeMark, bindingResult);
		if (bindingResult.hasErrors()) {
			return "/views/admin/addtrademark";
		}
		tradeMarkDao.save(tradeMark);
		return "redirect:trademark";
	}

	@RequestMapping(value = "/admin/deletetrademark", method = RequestMethod.GET)
	public String deleteEmployeeHandel(@RequestParam("id") String id, RedirectAttributes redirectAttributes) {
		try {
			tradeMarkDao.delete(Integer.valueOf(id));
			redirectAttributes.addFlashAttribute("success", "đã xóa thành công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "không thể xóa thương hiệu này, hãy xóa các sản phẩm trước");
		}
		return "redirect:trademark";
	}
}
