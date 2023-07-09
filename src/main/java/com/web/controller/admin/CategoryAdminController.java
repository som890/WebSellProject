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
import com.web.entity.Category;
import com.web.validate.CategoryValidate;

@Controller
public class CategoryAdminController {

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private CategoryValidate categoryValidate;

	@RequestMapping(value = { "/admin/category" }, method = RequestMethod.GET)
	public String category(Model model) {
		model.addAttribute("categories", categoryDao.findAll());
		return "/views/admin/category";
	}

	@RequestMapping(value = { "/admin/addcategory" }, method = RequestMethod.GET)
	public String addcategory(Model model, @RequestParam(value = "id", required = false) Integer id) {
		Category category = null;
		if (id == null) {
			category = new Category();
		} else {
			category = categoryDao.findById(id);
		}
		model.addAttribute("category", category);
		return "/views/admin/addcategory";
	}

	@RequestMapping(value = { "/admin/addcategoryPost" }, method = RequestMethod.POST)
	public String addCategoryPost(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult) {
		categoryValidate.validate(category, bindingResult);
		System.out.println(category);
		if (bindingResult.hasErrors()) {
			return "/views/admin/addcategory";
		}
		categoryDao.save(category);
		return "redirect:category";
	}

	@RequestMapping(value = "/admin/deletecategory", method = RequestMethod.GET)
	public String deleteEmployeeHandel(@RequestParam("id") String id, RedirectAttributes redirectAttributes) {
		try {
			categoryDao.delete(Integer.valueOf(id));
			redirectAttributes.addFlashAttribute("success", "đã xóa danh mục thành công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "không thể xóa danh mục này, hãy xóa các sản phẩm trước");
		}
		return "redirect:category";
	}
}
