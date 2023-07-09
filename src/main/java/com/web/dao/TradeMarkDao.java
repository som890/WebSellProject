package com.web.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.entity.Category;
import com.web.entity.TradeMark;

@Repository
@Transactional
public class TradeMarkDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<TradeMark> findAll(){
		List<TradeMark> list = new ArrayList<TradeMark>();
		Session session = sessionFactory.getCurrentSession();
		String sql = "select c from TradeMark c";
		Query query = session.createQuery(sql);
		list = query.list();
		return list;
	}
	
	public TradeMark findById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		TradeMark tradeMark = (TradeMark) session.get(TradeMark.class, id);
		return tradeMark;
	}
	
	public TradeMark findByName(String name) {
		String sql = "select c from TradeMark c where c.name = :name";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		query.setParameter("name", name);
		TradeMark c = (TradeMark) query.uniqueResult();
		return c;
	}
	
	public void save(TradeMark tradeMark) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(tradeMark);
	}
	
	public void delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		TradeMark tradeMark = (TradeMark) session.get(TradeMark.class, id);
		session.delete(tradeMark);
	}
}
