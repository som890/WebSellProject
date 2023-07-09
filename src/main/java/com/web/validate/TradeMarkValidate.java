package com.web.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.web.dao.TradeMarkDao;
import com.web.entity.TradeMark;

@Component
public class TradeMarkValidate implements Validator {

	@Autowired
	private TradeMarkDao tradeMarkDao;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return TradeMark.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TradeMark tradeMark = (TradeMark) target;
		ValidationUtils.rejectIfEmpty(errors, "name", "error.name", "Tên thương hiệu không được trống");
		if (tradeMarkDao.findByName(tradeMark.getName()) != null && tradeMark.getId() == null) {
			errors.rejectValue("name", "error.name", "Tên thương hiệu này đã tồn tại");
		}
	}

	
}
