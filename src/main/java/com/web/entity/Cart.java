package com.web.entity;

public class Cart {

	private Long id;
	
	private ColorWatch colorWatch;
	
	private Integer quantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ColorWatch getColorWatch() {
		return colorWatch;
	}

	public void setColorWatch(ColorWatch colorWatch) {
		this.colorWatch = colorWatch;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
