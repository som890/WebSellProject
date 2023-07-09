package com.web.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.config.UploadConfig;
import com.web.dao.CategoryDao;
import com.web.dao.ImageWatchDao;
import com.web.dao.TradeMarkDao;
import com.web.dao.WatchDao;
import com.web.entity.Blog;
import com.web.entity.ColorWatch;
import com.web.entity.Watch;
import com.web.model.ColorDto;
import com.web.service.ColorInit;
import com.web.service.WatchService;
import com.web.validate.ProductValidate;

@Controller
public class ProductAdminController {

	@Autowired
	private WatchDao watchDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private TradeMarkDao tradeMarkDao;
	
	@Autowired
	private ProductValidate productValidate;
	
	@Autowired
	private WatchService watchService;
	
	@Autowired
	private ImageWatchDao imageWatchDao;
	
	private UploadConfig uploadConfig = new UploadConfig();
	
//	private ColorInit colorInit = new ColorInit();
	
	@RequestMapping(value = { "/admin/product" }, method = RequestMethod.GET)
	public String welcomePage(Model model) {
		model.addAttribute("gia", new Double(3000000));
		model.addAttribute("listwatch", watchDao.findAll());
		watchDao.findAll().forEach(p->{
			System.out.println(p);
		});
		return "/views/admin/product";
	}
	
	@RequestMapping(value = { "/admin/addproduct" }, method = RequestMethod.GET)
	public String addproduct(Model model, @RequestParam(value = "id", required = false) Integer id) {
		Watch watch = null;
		if (id == null) {
			
			watch = new Watch();
		} else {
			watch = watchDao.findById(id);
		}
		watch.getColorWatchs().forEach(p->{
			System.out.println(p);
		});
		model.addAttribute("watch", watch);
		model.addAttribute("watchp", watch);
		model.addAttribute("categories", categoryDao.findAll());
		model.addAttribute("trademarks", tradeMarkDao.findAll());
		List<ColorDto> listColor = ColorInit.InitColor();
		if(id != null) {
			for(ColorDto c : listColor) {
				for(ColorWatch cw : watch.getColorWatchs()) {
					if(c.getTenmau().equalsIgnoreCase(cw.getColorname())) {
						c.setSelected("selected");
					}
				}
			}
		}
		model.addAttribute("colors", listColor);
		return "/views/admin/addproduct";
	}

	
	@RequestMapping(value = { "/admin/addproductPost" }, method = RequestMethod.POST)
	public String addproductPost(Model model,@Valid @ModelAttribute("watch") Watch watch, 
			BindingResult bindingResult, @RequestParam("listcolor") String listcolor[],
			 @RequestParam("danhmuc") Integer danhmuc,@RequestParam("thuonghieu") Integer thuonghieu) {
		productValidate.validate(watch, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("categories", categoryDao.findAll());
			model.addAttribute("trademarks", tradeMarkDao.findAll());
			Watch w = null;
			if (watch.getId() == null) {
				model.addAttribute("colors", ColorInit.InitColor());
				w = new Watch();
			} else {
				w = watchDao.findById(watch.getId());
				List<ColorDto> listColor = ColorInit.InitColor();
				for(ColorDto c : listColor) {
					for(ColorWatch cw : w.getColorWatchs()) {
						if(c.getTenmau().equalsIgnoreCase(cw.getColorname())) {
							c.setSelected("selected");
						}
					}
				}
				model.addAttribute("colors", listColor);
			}
			model.addAttribute("watchp", w);
			return "/views/admin/addproduct";
		}
		watchService.saveWatch(watch, danhmuc, thuonghieu, listcolor);
		return "redirect:product";
	}
	
	@RequestMapping(value = { "/admin/deleteimg" }, method = RequestMethod.GET)
	public String deleteimg( @RequestParam("id") Integer idprom, @RequestParam("idimg") Integer idimg) {
		imageWatchDao.delete(idimg);
		return "redirect:addproduct?id="+idprom;
	}
	
	@RequestMapping(value = "/admin/deleteproduct", method = RequestMethod.GET)
	public String deleteEmployeeHandel(@RequestParam("id") String id, RedirectAttributes redirectAttributes) {
		try {
			watchDao.delete(Integer.valueOf(id));
			redirectAttributes.addFlashAttribute("success", "đã xóa thành công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "không thể xóa");
		}
		return "redirect:product";
	}
}
