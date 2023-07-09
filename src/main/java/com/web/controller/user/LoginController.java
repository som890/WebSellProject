package com.web.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.dao.CategoryDao;

@Controller
@Transactional
public class LoginController {

	@Autowired
	private CategoryDao categoryDao;
	
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String welcomePage(Model model) {
		model.addAttribute("category", categoryDao.findAll());
		return "/views/user/login";
	}
}
