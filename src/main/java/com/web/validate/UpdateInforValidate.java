package com.web.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.web.dao.AccountDao;
import com.web.entity.Account;

@Component
public class UpdateInforValidate implements Validator{

	@Autowired
	private AccountDao accountDao;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Account.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		Account account = (Account) target;
		ValidationUtils.rejectIfEmpty(errors, "fullname", "error.fullname", "Họ tên không được trống");
		ValidationUtils.rejectIfEmpty(errors, "email", "error.email", "email không được trống");
		ValidationUtils.rejectIfEmpty(errors, "address", "error.address", "address không được trống");
		ValidationUtils.rejectIfEmpty(errors, "phone", "error.phone", "Số điện thoại không được trống");
	}
}
