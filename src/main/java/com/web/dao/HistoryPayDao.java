package com.web.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.entity.HistoryPay;
import com.web.entity.ImageWatch;

@Repository
@Transactional
public class HistoryPayDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(HistoryPay historyPay) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(historyPay);
	}
	
	public HistoryPay findByOrderAndresques(String order, String request) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "select c from HistoryPay c where c.orderId = :order and c.requestId = :requests";
		Query query = session.createQuery(sql);
		query.setParameter("order", order);
		query.setParameter("requests", request);
		HistoryPay historyPay = (HistoryPay) query.uniqueResult();
		return historyPay;
	}
	
}
