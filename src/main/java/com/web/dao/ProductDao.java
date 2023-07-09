package com.web.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.entity.Blog;
import com.web.entity.Watch;

@Repository
@Transactional
public class ProductDao {

	@Autowired
	private SessionFactory sessionFactory;
	

	public List<Watch> findAll(){
		List<Watch> list = new ArrayList<Watch>();
		Session session = sessionFactory.getCurrentSession();
		String sql = "select c from Watch c";
		Query query = session.createQuery(sql);
		list = query.list();
		return list;
	}
	
	public Long countProduct() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "select sum(a.quantity) from Watch a";
		Query query = session.createQuery(sql);
		Long total = (Long) query.uniqueResult();
		return total;
	}
}
