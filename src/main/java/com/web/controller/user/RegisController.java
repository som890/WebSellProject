package com.web.controller.user;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.config.UploadConfig;
import com.web.dao.AccountDao;
import com.web.dao.AuthorityDao;
import com.web.dao.CategoryDao;
import com.web.entity.Account;
import com.web.entity.Authority;
import com.web.entity.Blog;
import com.web.entity.UserRole;
import com.web.service.MailService;
import com.web.validate.RegisValidate;

@Controller
public class RegisController {

	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private RegisValidate regisValidate;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthorityDao authorityDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	
	private UploadConfig uploadConfig = new UploadConfig();
	
	@RequestMapping(value = { "/regis" }, method = RequestMethod.GET)
	public String welcomePage(Model model) {
		model.addAttribute("account", new Account());
		model.addAttribute("category", categoryDao.findAll());
		return "/views/user/regis";
	}
	
	@RequestMapping(value = { "/actived" }, method = RequestMethod.GET)
	public String actived(@RequestParam("key") String key) {
		Account a = accountDao.findByActivationkey(key);
		a.setEnable(1);
		accountDao.save(a);
		return "redirect:login";
	}
	
	@RequestMapping(value = { "/regispost" }, method = RequestMethod.POST)
	public String addblogPost(HttpServletRequest request, 
			@Valid @ModelAttribute("account") Account account, BindingResult bindingResult) throws IOException {
		regisValidate.validate(account, bindingResult);
		if (bindingResult.hasErrors()) {
			return "/views/user/regis";
		}
		String image = uploadConfig.uploadFile(account.getFile());
		account.setAvatar(image);
		String random = mailService.randomKey();
		account.setEnable(0);
		account.setActivationkey(random);
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		Account result = accountDao.save(account);
		Authority authority = authorityDao.findByRoleName("USER");
		UserRole userRole = new UserRole();
		userRole.setAccount(result);
		userRole.setAuthority(authority);
		accountDao.addRole(userRole);
		mailService.sendMail(account.getEmail(),"xác nhận tài khoản", "http://localhost:8080/shopdh/actived?key="+random);
		return "redirect:login";
	}
	
	@RequestMapping(value = { "/remember" }, method = RequestMethod.GET)
	public String remember(Model model) {
		model.addAttribute("category", categoryDao.findAll());
		return "/views/user/remember";
	}
	
	@RequestMapping(value = { "/rememberpost" }, method = RequestMethod.GET)
	public String rememberpost(@RequestParam("email") String email,RedirectAttributes redirectAttributes) {
		Account a = accountDao.findByEmail(email);
		if(a == null) {
			redirectAttributes.addFlashAttribute("mailnull", "mail khong ton tai");
			return "redirect:remember";
		}
		else {
			if(a.getEnable() == 0) {
				redirectAttributes.addFlashAttribute("khoa", "tai khoan da bi khoa");
				return "redirect:remember";
			}
		}
		String random = mailService.randomKey();
		a.setPassword(passwordEncoder.encode(random));
		accountDao.save(a);
		mailService.sendMail(email,"khôi phục mật khẩu", "mật khẩu mới của bạn là: "+random);
		redirectAttributes.addFlashAttribute("remembersuccess", "check email de lay pass moi");
		return "redirect:login";
	}
	
	
}
