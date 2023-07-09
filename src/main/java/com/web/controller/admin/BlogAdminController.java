package com.web.controller.admin;

import java.io.File;
import java.io.IOException;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
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
import com.web.dao.BlogDao;
import com.web.entity.Blog;
import com.web.entity.Category;
import com.web.validate.BlogValidate;

@Controller
public class BlogAdminController {

	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private BlogValidate blogValidate;
	
	private UploadConfig uploadConfig = new UploadConfig();
	
	@RequestMapping(value = { "/admin/blog" }, method = RequestMethod.GET)
	public String blog(Model model) {
		model.addAttribute("blogs", blogDao.findAll());
		return "/views/admin/blog";
	}
	
	@RequestMapping(value = { "/admin/addblog" }, method = RequestMethod.GET)
	public String addblog(Model model, @RequestParam(value = "id", required = false) Integer id) {
		Blog blog = null;
		if (id == null) {
			blog = new Blog();
		} else {
			blog = blogDao.findById(id);
		}
		model.addAttribute("blog", blog);
		return "/views/admin/addblog";
	}
	
	@RequestMapping(value = { "/admin/addblogPost" }, method = RequestMethod.POST)
	public String addblogPost(Model model,@RequestParam("file") MultipartFile file, 
			HttpServletRequest request, 
			@Valid @ModelAttribute("blog") Blog blog, BindingResult bindingResult) throws IOException {
		String image = null;
		Date d = new Date(System.currentTimeMillis());
		if(blog.getId() != null) {
			Blog b =  blogDao.findById(blog.getId());
			image = b.getImageBanner();
			d = b.getCreatedDate();
		}
		blogValidate.validate(blog, bindingResult);
		if (bindingResult.hasErrors()) {
			return "/views/admin/addblog";
		}
		if (!file.isEmpty()) {
			image = uploadConfig.uploadFile(file);
		}
		blog.setImageBanner(image);
		blog.setCreatedDate(d);
		blogDao.save(blog);
		return "redirect:blog";
	}
	
	@RequestMapping(value = "/admin/deleteblog", method = RequestMethod.GET)
	public String deleteEmployeeHandel(@RequestParam("id") String id, RedirectAttributes redirectAttributes) {
		try {
			blogDao.delete(Integer.valueOf(id));
			redirectAttributes.addFlashAttribute("success", "đã xóa thành công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "không thể xóa");
		}
		return "redirect:blog";
	}
}
