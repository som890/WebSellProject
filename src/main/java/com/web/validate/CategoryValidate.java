package com.web.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.web.dao.CategoryDao;
import com.web.entity.Category;

@Component
public class CategoryValidate implements Validator {

	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Category.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Category category = (Category) target;
		ValidationUtils.rejectIfEmpty(errors, "name", "error.name", "Tên danh mục không được trống");
		if (categoryDao.findByName(category.getName()) != null && category.getId() == null) {
			errors.rejectValue("name", "error.name", "Tên danh mục này đã tồn tại");
		}
	}

	
}
