package com.web.model;

public class Pageable {

	public Integer size;
	
	public Integer page;

	public Pageable() {
	}

	public Pageable(Integer size, Integer page) {
		super();
		this.size = size;
		this.page = page;
	}
	
	
}
