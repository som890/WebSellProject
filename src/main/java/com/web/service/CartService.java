package com.web.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.web.entity.Cart;
import com.web.entity.ColorWatch;

public class CartService {

	public void addCart(ColorWatch colorWatch, Integer quantity, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Cart cart = new Cart();
		cart.setColorWatch(colorWatch);
		cart.setQuantity(quantity);
		if(session.getAttribute("cart") == null) {
			List<Cart> list = new ArrayList<>();
			list.add(cart);
			session.setAttribute("cart", list);
			return;
		}
		List<Cart> list = (List<Cart>) session.getAttribute("cart");
		for(Cart c : list) {
			if(c.getColorWatch().getId() == colorWatch.getId()) {
				c.setQuantity(quantity);
				session.setAttribute("cart", list);
				return;
			}
		}
		list.add(cart);
		session.setAttribute("cart", list);
	}
	
	public void updateQuantity(Integer quantity, Integer idcolor, HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<Cart> list = (List<Cart>) session.getAttribute("cart");
		for(Cart c: list) {
			if(c.getColorWatch().getId() == idcolor) {
				c.setQuantity(c.getQuantity() + quantity);
				if(c.getQuantity() == 0) {
					list.remove(c);
				}
				session.setAttribute("cart", list);
				break;
			}
		}
	}
}
