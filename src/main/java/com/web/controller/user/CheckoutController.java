package com.web.controller.user;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.dao.AccountDao;
import com.web.dao.CategoryDao;
import com.web.entity.Account;
import com.web.entity.Cart;
import com.web.service.PaymentService;

@Controller
public class CheckoutController {

	@Autowired
	private AccountDao accountDao;

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private CategoryDao categoryDao;
	

	@RequestMapping(value = { "/checkout" }, method = RequestMethod.GET)
	public String checkout(Model model, Principal principal, HttpServletRequest request) {
		String username = principal.getName();
		Account account = accountDao.getUserWithAuthority(username);
		model.addAttribute("account", account);
		HttpSession session = request.getSession();
		if (session.getAttribute("cart") == null) {
			model.addAttribute("cartromg", "cart rong");
			model.addAttribute("tongtien", 0);
			model.addAttribute("tongsl", 0);
		} else {
			List<Cart> list = (List<Cart>) session.getAttribute("cart");
			if (list.size() == 0) {
				model.addAttribute("cartromg", "cart rong");
			}
			Integer tongsl = 0;
			Double tongtien = 0D;
			for (Cart c : list) {
				tongsl += c.getQuantity();
				tongtien += c.getColorWatch().getWatch().getPrice() * c.getQuantity();
			}
			model.addAttribute("tongsl", tongsl);
			model.addAttribute("tongtien", tongtien);
		}
		model.addAttribute("category", categoryDao.findAll());
		return "/views/user/checkout";
	}

	@RequestMapping(value = { "/payment" }, method = RequestMethod.POST)
	public String checkout(Principal principal, HttpServletRequest request, @RequestParam("ghichukhinhan") String note,
			RedirectAttributes redirectAttributes) {
		String username = principal.getName();
		Account account = accountDao.getUserWithAuthority(username);
		try {
			paymentService.createPayment(account, request, note);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("taoinvoicetb", "tao hoa don that bai");
			String referer = request.getHeader("Referer");
			return "redirect:" + referer;
		}
		return "redirect:thanhcong";
	}

	

	
}
