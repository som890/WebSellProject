package com.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.dao.AccountDao;
import com.web.entity.Account;

@Controller
public class UserAdminController {

	@Autowired
	private AccountDao accountDao;
	
	@RequestMapping(value = { "/admin/user" }, method = RequestMethod.GET)
	public String blog(Model model) {
		model.addAttribute("accounts", accountDao.findByRoleUser());
		return "/views/admin/user";
	}
	
	@RequestMapping(value = { "/admin/active" }, method = RequestMethod.GET)
	public String activeOrUn(RedirectAttributes redirectAttributes,@RequestParam(value = "id", required = false) Integer id) {
		Account account= accountDao.findById(id);
		if (account.getEnable() == 1) {
			account.setEnable(0);
			redirectAttributes.addFlashAttribute("khoa", "đã khóa");
		}
		else {
			account.setEnable(1);
			redirectAttributes.addFlashAttribute("mokhoa", "đã khóa");
		}
		accountDao.save(account);
		return "redirect:user";
	}
}
