package com.web.controller.user;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.dao.AccountDao;
import com.web.dao.CategoryDao;
import com.web.dao.InvoiceDao;
import com.web.dao.StatusInvoiceDao;
import com.web.dao.UserInfoDAO;
import com.web.entity.Account;
import com.web.entity.Invoice;
import com.web.entity.StatusInvoice;
import com.web.model.Pageable;
import com.web.service.Contains;

@Controller
@Transactional
public class InvoiceUserController {

	@Autowired
	private InvoiceDao invoiceDao;
	
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private UserInfoDAO userInfoDAO;
	
	@Autowired
	private StatusInvoiceDao statusInvoiceDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	
	@RequestMapping(value = { "/history" }, method = RequestMethod.GET)
	public String history(Model model,Principal principal) {
		String username = principal.getName();
		List<Invoice> list = invoiceDao.findByUser(username);
		model.addAttribute("listinvoice", list);
		model.addAttribute("category", categoryDao.findAll());
		return "/views/user/historyorder";
	}
	
	@RequestMapping(value = { "/deleteinvoice" }, method = RequestMethod.GET)
	public String deleteInvoice(Model model,Principal principal, @RequestParam("id") Integer id,RedirectAttributes redirectAttributes) {
		String username = principal.getName();
		Account account = accountDao.getUserWithAuthority(username);
		Invoice invoice = invoiceDao.findById(id);
		if(invoice.getAccount().getId() != account.getId()) {
			redirectAttributes.addFlashAttribute("khongduquyen", "khongduquyen");
			return "redirect:history";
		}
		if(invoice.getStatusInvoice().getId() > 2) {
			redirectAttributes.addFlashAttribute("donhangkhongthehuy", "donhangkhongthehuy");
			return "redirect:history";
		}
		if(invoice.getType() == 1) {
			redirectAttributes.addFlashAttribute("dathanhtoanmomo", "dathanhtoanmomo");
			return "redirect:history";
		}
		StatusInvoice statusInvoice = statusInvoiceDao.findById(Contains.statusHuy);
		invoice.setStatusInvoice(statusInvoice);
		invoiceDao.save(invoice);
		redirectAttributes.addFlashAttribute("huythanhcong", "huythanhcong");
		return "redirect:history";
	}
}
