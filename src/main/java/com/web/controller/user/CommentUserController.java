package com.web.controller.user;

import java.security.Principal;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.dao.AccountDao;
import com.web.dao.CommentDao;
import com.web.dao.WatchDao;
import com.web.entity.Account;
import com.web.entity.Comment;
import com.web.entity.Watch;

@Controller
public class CommentUserController {

	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private WatchDao watchDao;

	@RequestMapping(value = { "/addcomment" }, method = RequestMethod.POST)
	public String blogdetail(Model model, @RequestParam("content") String content,
			@RequestParam("idpro") Integer idpro, HttpServletRequest request,Principal principal, RedirectAttributes redirectAttributes) {
		String username = principal.getName();
		Account account = accountDao.getUserWithAuthority(username);
		Watch watch = watchDao.findById(idpro);
		Comment comment = new Comment();
		comment.setCreatedDate(new Date(System.currentTimeMillis()));
		comment.setContent(content);
		comment.setAccount(account);
		comment.setWatch(watch);
		commentDao.save(comment);
		String referer = request.getHeader("Referer");
		redirectAttributes.addFlashAttribute("successaddcomment", "da dang binh luan");
		return "redirect:" + referer;
	}
	
	@RequestMapping(value = { "/deletecomment" }, method = RequestMethod.GET)
	public String deleteComment(@RequestParam("id") Integer id, HttpServletRequest request,Principal principal, RedirectAttributes redirectAttributes) {
		String username = principal.getName();
		Account account = accountDao.getUserWithAuthority(username);
		Comment comment = commentDao.findById(id);
		if(comment.getAccount().getId() != account.getId()) {
			redirectAttributes.addFlashAttribute("khongduquyenxoa", "khong du quyen xoa");
			String referer = request.getHeader("Referer");
			return "redirect:" + referer;
		}
		commentDao.delete(id);
		String referer = request.getHeader("Referer");
		redirectAttributes.addFlashAttribute("xoacmtsuccess", "da xoa binh luan");
		return "redirect:" + referer;
	}
}
