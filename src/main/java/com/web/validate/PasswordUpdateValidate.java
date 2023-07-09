package com.web.validate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.web.entity.Account;
import com.web.model.PasswordDto;

@Component
public class PasswordUpdateValidate implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PasswordDto.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		PasswordDto pass = (PasswordDto) target;
		ValidationUtils.rejectIfEmpty(errors, "pass", "error.pass", "password không được trống");
		ValidationUtils.rejectIfEmpty(errors, "newpass", "error.newpass", "mật khẩu mới không được trống");
		ValidationUtils.rejectIfEmpty(errors, "renewpass", "error.renewpass", "không được để trống mật khẩu");
		if(!pass.getNewpass().equals(pass.getRenewpass())) {
			errors.rejectValue("renewpass", "error.renewpass", "mật khẩu mới không trùng khớp");
		}
		if(pass.getNewpass().length() < 4) {
			
		}
	}
}
