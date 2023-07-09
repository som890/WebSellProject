package com.web.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.entity.Blog;
import com.web.entity.StatusInvoice;

@Repository
@Transactional
public class StatusInvoiceDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<StatusInvoice> findAll(){
		List<StatusInvoice> list = new ArrayList<StatusInvoice>();
		Session session = sessionFactory.getCurrentSession();
		String sql = "select c from StatusInvoice c";
		Query query = session.createQuery(sql);
		list = query.list();
		return list;
	}
	
	public StatusInvoice findById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		StatusInvoice statusInvoice = (StatusInvoice) session.get(StatusInvoice.class, id);
		return statusInvoice;
	}
}
