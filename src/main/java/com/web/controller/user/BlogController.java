package com.web.controller.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.dao.BlogDao;
import com.web.dao.CategoryDao;
import com.web.model.Pageable;
import com.web.service.Contains;

@Controller
public class BlogController {
	
	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@RequestMapping(value = { "/blog" }, method = RequestMethod.GET)
	public String welcomePage(Model model, @RequestParam(value = "page", required = false) Integer page) {
		Integer p = 0;
		if(page == null) {
			p = 0;
		}
		else {
			p = page - 1;
		}
		List<Integer> listpage = new ArrayList<>();
		for(int i=1; i<=blogDao.getTotalPage(new Pageable(Contains.sizeBlog, 0)); i++) {
			listpage.add(i);
		}
		model.addAttribute("blogs", blogDao.getPage(new Pageable(Contains.sizeBlog, p)));
		model.addAttribute("totalpage", listpage);
		model.addAttribute("category", categoryDao.findAll());
		return "/views/user/blog";
	}
	
	@RequestMapping(value = { "/blogdetail" }, method = RequestMethod.GET)
	public String blogdetail(Model model, @RequestParam("id") Integer id) {
		model.addAttribute("detail", blogDao.findById(id));
		model.addAttribute("category", categoryDao.findAll());
		return "/views/user/blogdetail";
	}
}
