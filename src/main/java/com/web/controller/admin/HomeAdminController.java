package com.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.dao.AccountDao;
import com.web.dao.InvoiceDao;
import com.web.dao.ProductDao;

@Controller
public class HomeAdminController {

	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private InvoiceDao invoiceDao;
	
	@Autowired
	private ProductDao productDao;
	
	@RequestMapping(value = {"/admin/", "/admin/home" }, method = RequestMethod.GET)
	public String getHomePage(Model model) {
		model.addAttribute("totaluser", accountDao.countUser());
		model.addAttribute("totalProduct", productDao.countProduct());
		model.addAttribute("totalInvoice", invoiceDao.countInvoice());
		model.addAttribute("users", accountDao.getUserThisMonth());
		return "/views/admin/home";
	}
}
