package com.web.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.dao.CategoryDao;
import com.web.entity.Category;

@RestController
public class AccessDenied {

	@Autowired
	private CategoryDao categoryDao;
	
	@RequestMapping(value = "/category", method = RequestMethod.GET
			,produces = { MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public List<Category> testapi() {
		return categoryDao.findAll();
	}
	
//	@RequestMapping(value = "/category", method = RequestMethod.GET)
//	public List<Category> testapi() {
//		
//		return categoryDao.findAll();
//	}
}
