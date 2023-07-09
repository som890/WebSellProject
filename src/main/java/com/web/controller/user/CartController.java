package com.web.controller.user;

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

import com.web.dao.CategoryDao;
import com.web.dao.ColorWatchDao;
import com.web.entity.Cart;
import com.web.entity.ColorWatch;
import com.web.service.CartService;

@Controller
public class CartController {

	@Autowired
	private ColorWatchDao colorWatchDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	
	@RequestMapping(value = { "/cart" }, method = RequestMethod.GET)
	public String welcomePage(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("cart") == null) {
			model.addAttribute("carts", new ArrayList<Cart>());
			model.addAttribute("tongtien", 0);
			model.addAttribute("soluongsp", 0);
			return "/views/user/cart";
		}
		List<Cart> list = (List<Cart>) session.getAttribute("cart");
		model.addAttribute("carts", list);
		Integer tongsl = 0;
		Double tongtien = 0D;
		for(Cart c : list) {
			tongsl += c.getQuantity();
			tongtien += c.getColorWatch().getWatch().getPrice() * c.getQuantity();
		}
		model.addAttribute("soluongsp", tongsl);
		model.addAttribute("tongtien", tongtien);
		model.addAttribute("category", categoryDao.findAll());
		return "/views/user/cart";
	}
	
	@RequestMapping(value = { "/addcart" }, method = RequestMethod.POST)
	public String addcart(@RequestParam("quantity") Integer quantity,HttpServletRequest request,
			@RequestParam("mausac") Integer mausac,RedirectAttributes redirectAttributes) {
		ColorWatch colorWatch = colorWatchDao.findById(mausac);
		if(quantity > colorWatch.getWatch().getQuantity()) {
			redirectAttributes.addFlashAttribute("khongdusoluong", colorWatch.getWatch().getQuantity());
		}
		CartService cartService = new CartService();
		cartService.addCart(colorWatch, quantity, request);
		
		redirectAttributes.addFlashAttribute("addcartsuccess", "themgiohangthanhcong");
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
	
	@RequestMapping(value = { "/updatequantity" }, method = RequestMethod.POST)
	public String updatequantity(@RequestParam("quantity") Integer quantity,HttpServletRequest request,
			@RequestParam("mausac") Integer mausac) {
		CartService cartService = new CartService();
		cartService.updateQuantity(quantity, mausac, request);
		return "redirect:cart";
	}
	
	@RequestMapping(value = { "/deletecart" }, method = RequestMethod.GET)
	public String deletecart(@RequestParam("id") Integer idcolorw,HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<Cart> list = (List<Cart>) session.getAttribute("cart");
		for(Cart c : list) {
			if(c.getColorWatch().getId() == idcolorw) {
				list.remove(c);
				break;
			}
		}
		session.setAttribute("cart", list);
		return "redirect:cart";
	}
	
}
