package com.web.service;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.DetailInvoiceDao;
import com.web.dao.HistoryPayDao;
import com.web.dao.InvoiceDao;
import com.web.dao.StatusInvoiceDao;
import com.web.entity.Account;
import com.web.entity.Cart;
import com.web.entity.DetailInvoice;
import com.web.entity.HistoryPay;
import com.web.entity.Invoice;
import com.web.entity.StatusInvoice;

@Service
public class PaymentService {

	@Autowired
	private InvoiceDao invoiceDao;
	
	@Autowired
	private StatusInvoiceDao statusInvoiceDao;
	
	@Autowired
	private DetailInvoiceDao detailInvoiceDao;
	
	@Autowired
	private HistoryPayDao historyPayDao;
	
	public void createPayment(Account account, HttpServletRequest request, String note) {
		HttpSession session = request.getSession();
		List<Cart> list = (List<Cart>) session.getAttribute("cart");
		
		Double tongtien = 0D;
		for(Cart c : list) {
			tongtien += c.getColorWatch().getWatch().getPrice() * c.getQuantity();
		}
		
		StatusInvoice statusInvoice = statusInvoiceDao.findById(1);
		
		Invoice invoice = new Invoice();
		invoice.setAccount(account);
		invoice.setCreatedDate(new Date(System.currentTimeMillis()));
		invoice.setNote(note);
		invoice.setType(0);
		invoice.setTotalAmount(tongtien);
		invoice.setStatusInvoice(statusInvoice);
		Invoice result = invoiceDao.save(invoice);
		
		for(Cart c : list) {
			DetailInvoice detail = new DetailInvoice();
			detail.setPrice(c.getColorWatch().getWatch().getPrice());
			detail.setInvoice(result);
			detail.setColorWatch(c.getColorWatch());
			detail.setQuantity(c.getQuantity());
			if(c.getQuantity() > c.getColorWatch().getWatch().getQuantity()) {
				continue;
			}
			detailInvoiceDao.save(detail);
		}
	}
	
	
	public void createPaymentMoMo(Account account, HttpServletRequest request, String note, String orderId, String requestId) {
		HttpSession session = request.getSession();
		List<Cart> list = (List<Cart>) session.getAttribute("cart");
		
		Double tongtien = 0D;
		for(Cart c : list) {
			tongtien += c.getColorWatch().getWatch().getPrice() * c.getQuantity();
		}
		
		StatusInvoice statusInvoice = statusInvoiceDao.findById(1);
		
		Invoice invoice = new Invoice();
		invoice.setAccount(account);
		invoice.setCreatedDate(new Date(System.currentTimeMillis()));
		invoice.setNote(note);
		invoice.setType(1);
		invoice.setTotalAmount(tongtien);
		invoice.setStatusInvoice(statusInvoice);
		Invoice result = invoiceDao.save(invoice);
		
		for(Cart c : list) {
			DetailInvoice detail = new DetailInvoice();
			detail.setPrice(c.getColorWatch().getWatch().getPrice());
			detail.setInvoice(result);
			detail.setColorWatch(c.getColorWatch());
			detail.setQuantity(c.getQuantity());
			if(c.getQuantity() > c.getColorWatch().getWatch().getQuantity()) {
				continue;
			}
			detailInvoiceDao.save(detail);
		}
		
		HistoryPay historyPay = new HistoryPay();
		historyPay.setInvoice(invoice);
		historyPay.setOrderId(orderId);
		historyPay.setRequestId(requestId);
		historyPayDao.save(historyPay);
	}
}
