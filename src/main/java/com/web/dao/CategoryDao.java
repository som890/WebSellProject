package com.web.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.entity.Account;
import com.web.entity.Category;

@Repository
@Transactional
public class CategoryDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Category> findAll(){
		List<Category> list = new ArrayList<Category>();
		Session session = sessionFactory.getCurrentSession();
		String sql = "select c from Category c";
		Query query = session.createQuery(sql);
		list = query.list();
		return list;
	}
	
	public Category findById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Category category = (Category) session.get(Category.class, id);
		return category;
	}
	
	public Category findByName(String name) {
		String sql = "select c from Category c where c.name = :name";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		query.setParameter("name", name);
		Category c = (Category) query.uniqueResult();
		return c;
	}
	
	public void save(Category category) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(category);
	}
	
	public void delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		Category category = (Category) session.get(Category.class, id);
		session.delete(category);
	}
}
