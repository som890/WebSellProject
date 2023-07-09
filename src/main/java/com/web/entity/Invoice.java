package com.web.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Invoice")
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Double totalAmount;
	
	private Date createdDate;
	
	private String note;
	
	private Integer type;
	
	@ManyToOne
	@JoinColumn(name = "statusInvoice_id")
	private StatusInvoice statusInvoice;
	
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;
	
	@ManyToOne
	@JoinColumn(name = "addressAccount_id")
	private AddressAccount addressAccount;
	
	@OneToMany(mappedBy = "invoice", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<DetailInvoice> detailInvoices = new ArrayList<>();
	
	@OneToMany(mappedBy = "invoice", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<HistoryPay> historyPays = new ArrayList<>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public StatusInvoice getStatusInvoice() {
		return statusInvoice;
	}

	public void setStatusInvoice(StatusInvoice statusInvoice) {
		this.statusInvoice = statusInvoice;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public AddressAccount getAddressAccount() {
		return addressAccount;
	}

	public void setAddressAccount(AddressAccount addressAccount) {
		this.addressAccount = addressAccount;
	}

	public List<DetailInvoice> getDetailInvoices() {
		return detailInvoices;
	}

	public void setDetailInvoices(List<DetailInvoice> detailInvoices) {
		this.detailInvoices = detailInvoices;
	}

	public List<HistoryPay> getHistoryPays() {
		return historyPays;
	}

	public void setHistoryPays(List<HistoryPay> historyPays) {
		this.historyPays = historyPays;
	}
	
	
}
