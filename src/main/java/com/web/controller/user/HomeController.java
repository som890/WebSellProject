package com.web.controller.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.dao.AccountDao;
import com.web.dao.CategoryDao;
import com.web.dao.WatchDao;
import com.web.entity.Account;
import com.web.model.Pageable;
import com.web.service.Contains;

@Controller
@Transactional
public class HomeController {

	
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private WatchDao watchDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String welcomePage(Model model,
			@RequestParam(value = "page", required = false) Integer page) {
		Integer p = 0;
		if(page == null) {
			p = 0;
		}
		else {
			p = page - 1;
		}
		List<Integer> listpage = new ArrayList<>();
		for(int i=1; i<=watchDao.getTotalPage(new Pageable(Contains.sizeProduct, 0)); i++) {
			listpage.add(i);
		}
		model.addAttribute("newwatch", watchDao.listWatchPage(new Pageable(Contains.sizeProduct, p)));
		model.addAttribute("totalpage", listpage);
		model.addAttribute("category", categoryDao.findAll());
		model.addAttribute("pre", p);
		if(p == 0) {
			model.addAttribute("pre", 1);
		}
		model.addAttribute("next", p+2);
		if(p == watchDao.getTotalPage(new Pageable(Contains.sizeProduct, 0)) - 1) {
			model.addAttribute("next", p+1);
		}
		return "/views/user/index";
	}
}
