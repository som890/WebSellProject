package com.web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.entity.DetailInvoice;
import com.web.entity.Invoice;

@Repository
@Transactional
public class DetailInvoiceDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(DetailInvoice detailInvoice) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(detailInvoice);
	}
	
	
	
}
