package com.web.controller.user;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.config.UploadConfig;
import com.web.dao.AccountDao;
import com.web.dao.CategoryDao;
import com.web.entity.Account;
import com.web.model.PasswordDto;
import com.web.validate.PasswordUpdateValidate;
import com.web.validate.UpdateInforValidate;

@Controller
public class AccountController {

	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private UpdateInforValidate updateInforValidate;
	
	@Autowired
	private PasswordUpdateValidate passwordUpdateValidate;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CategoryDao categoryDao;
	
	private UploadConfig uploadConfig = new UploadConfig();
	
	
	@RequestMapping(value = { "/account" }, method = RequestMethod.GET)
	public String welcomePage(Model model,Principal principal) {
		String username = principal.getName();
		Account account = accountDao.getUserWithAuthority(username);
		model.addAttribute("account", account);
		model.addAttribute("accountp", account);
		model.addAttribute("password", new PasswordDto());
		model.addAttribute("category", categoryDao.findAll());
		return "/views/user/account";
	}
	
	@RequestMapping(value = { "/updateinfor" }, method = RequestMethod.POST)
	public String updateinfor(Model model, @Valid @ModelAttribute("account") Account account, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		Account a = accountDao.findById(account.getId());
		updateInforValidate.validate(account, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("accountp", a);
			model.addAttribute("password", new PasswordDto());
			model.addAttribute("category", categoryDao.findAll());
			return "/views/user/account";
		}
		
		
		if(!account.getFile().isEmpty()) {
			String img = uploadConfig.uploadFile(account.getFile());
			a.setAvatar(img);
		}
		a.setFullname(account.getFullname());
		a.setEmail(account.getEmail());
		a.setPhone(account.getPhone());
		a.setAddress(account.getAddress());
		accountDao.save(a);
		redirectAttributes.addFlashAttribute("inforthanhcong", "update thanh cong");
		return "redirect:account";
	}
	
	@RequestMapping(value = { "/updatepass" }, method = RequestMethod.POST)
	public String updatepass(Model model, @Valid @ModelAttribute("password") PasswordDto password, BindingResult bindingResult,
			RedirectAttributes redirectAttributes,Principal principal) {
		passwordUpdateValidate.validate(password, bindingResult);
		String username = principal.getName();
		Account account = accountDao.getUserWithAuthority(username);
		if (bindingResult.hasErrors()) {
			model.addAttribute("account", account);
			model.addAttribute("accountp", account);
			model.addAttribute("category", categoryDao.findAll());
			return "/views/user/account";
		}
		if(passwordEncoder.matches(password.getPass(), account.getPassword())) {
			account.setPassword(passwordEncoder.encode(password.getNewpass()));
			accountDao.save(account);
			redirectAttributes.addFlashAttribute("doimktc", "doimatkhauthanhcong");
		}
		else {
			redirectAttributes.addFlashAttribute("doimkthatbai", "doi mat khau that bai");
		}
		
		return "redirect:account";
	}
}
