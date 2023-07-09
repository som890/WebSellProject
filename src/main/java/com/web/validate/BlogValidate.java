package com.web.validate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.web.entity.Blog;
import com.web.entity.Category;

@Component
public class BlogValidate implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Blog.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Blog blog = (Blog) target;
		ValidationUtils.rejectIfEmpty(errors, "title", "error.title", "Tiêu đề không được trống");
		ValidationUtils.rejectIfEmpty(errors, "description", "error.description", "Mô tả không được trống");
		ValidationUtils.rejectIfEmpty(errors, "content", "error.description", "Nội dung không được trống");
		if(blog.getId() == null) {
//			ValidationUtils.rejectIfEmpty(errors, "imageBanner", "error.imageBanner", "Ảnh không được trống");
		}
	}
}
