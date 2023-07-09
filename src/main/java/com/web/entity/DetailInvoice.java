package com.web.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DetailInvoice")
public class DetailInvoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Double price;
	
	private Integer quantity;
	
	@ManyToOne
	@JoinColumn(name = "colorWatch_id")
	private ColorWatch colorWatch;
	
	@ManyToOne
	@JoinColumn(name = "invoice_id")
	private Invoice invoice;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public ColorWatch getColorWatch() {
		return colorWatch;
	}

	public void setColorWatch(ColorWatch colorWatch) {
		this.colorWatch = colorWatch;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	
	
}
