package com.web.controller.user;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.dao.AccountDao;
import com.web.dao.CategoryDao;
import com.web.dao.HistoryPayDao;
import com.web.entity.Account;
import com.web.service.PaymentService;

@Controller
public class ThanhCongController {

	@Autowired
	private HistoryPayDao historyPayDao;
	
	@Autowired
	private AccountDao accountDao;

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private CategoryDao categoryDao;
	
	
	@RequestMapping(value = { "/thanhcong" }, method = RequestMethod.GET)
	public String welcomePage(Model model) {
		
		return "/views/user/thanhcong";
	}
	
	@RequestMapping(value = { "/thanhcongmomo" }, method = RequestMethod.GET)
	public String thanhcongmomo(@RequestParam("requestId") String requestId, Model model,
			@RequestParam("orderId") String orderId,Principal principal,HttpServletRequest request) {
		if(historyPayDao.findByOrderAndresques(orderId, requestId) != null) {
			return "/views/user/thanhcong";
		}
		else {
			String username = principal.getName();
			Account account = accountDao.getUserWithAuthority(username);
			paymentService.createPaymentMoMo(account, request, username, orderId, requestId);
		}
		model.addAttribute("category", categoryDao.findAll());
		return "/views/user/thanhcong";
	}
}
