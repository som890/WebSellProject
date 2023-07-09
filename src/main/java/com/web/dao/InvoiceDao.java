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
import com.web.entity.Invoice;

@Repository
@Transactional
public class InvoiceDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Invoice> findAll(){
		List<Invoice> list = new ArrayList<Invoice>();
		Session session = sessionFactory.getCurrentSession();
		String sql = "select c from Invoice c";
		Query query = session.createQuery(sql);
		list = query.list();
		return list;
	}
	
	public List<Invoice> findByDateEndStatus(Date start, Date end, Integer status){
		List<Invoice> list = new ArrayList<Invoice>();
		Session session = sessionFactory.getCurrentSession();
		String sql = "select c from Invoice c where c.createdDate >= :start and c.createdDate <= :end and c.statusInvoice.id = :sta";
		Query query = session.createQuery(sql);
		query.setParameter("start", start);
		query.setParameter("end", end);
		query.setParameter("sta", status);
		list = query.list();
		return list;
	}
	
	public List<Invoice> findByDate(Date start, Date end){
		List<Invoice> list = new ArrayList<Invoice>();
		Session session = sessionFactory.getCurrentSession();
		String sql = "select c from Invoice c where c.createdDate >= :start and c.createdDate <= :end";
		Query query = session.createQuery(sql);
		query.setParameter("start", start);
		query.setParameter("end", end);
		list = query.list();
		return list;
	}
	
	public List<Invoice> findByStatus(Integer status){
		List<Invoice> list = new ArrayList<Invoice>();
		Session session = sessionFactory.getCurrentSession();
		String sql = "select c from Invoice c where c.statusInvoice.id = :sta";
		Query query = session.createQuery(sql);
		query.setParameter("sta", status);
		list = query.list();
		return list;
	}
	
	public List<Invoice> findByUser(String username){
		List<Invoice> list = new ArrayList<Invoice>();
		Session session = sessionFactory.getCurrentSession();
		String sql = "select c from Invoice c where c.account.username = :username";
		Query query = session.createQuery(sql);
		query.setParameter("username", username);
		list = query.list();
		return list;
	}
	
	public Invoice save(Invoice invoice) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(invoice);
		return invoice;
	}
	
	public Invoice findById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Invoice invoice = (Invoice) session.get(Invoice.class, id);
		return invoice;
	}
	
	public void delete(Invoice invoice) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(invoice);
	}
	
	public Long countInvoice() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "select count(a.id) from Invoice a";
		Query query = session.createQuery(sql);
		Long total = (Long) query.uniqueResult();
		return total;
	}
}
