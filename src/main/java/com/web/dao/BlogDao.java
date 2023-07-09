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
import com.web.entity.Category;
import com.web.model.Pageable;

@Repository
@Transactional
public class BlogDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Blog> findAll(){
		List<Blog> list = new ArrayList<Blog>();
		Session session = sessionFactory.getCurrentSession();
		String sql = "select c from Blog c";
		Query query = session.createQuery(sql);
		list = query.list();
		return list;
	}
	
	public List<Blog> getPage(Pageable pageable){
		List<Blog> list = new ArrayList<Blog>();
		Session session = sessionFactory.getCurrentSession();
		String sql = "select c from Blog c";
		Query query = session.createQuery(sql);
		query.setMaxResults(pageable.size);
		query.setFirstResult(pageable.size * pageable.page);
		list = query.list();
		return list;
	}
	
	public Integer getTotalPage(Pageable pageable){
		Integer total = 0;
		Session session = sessionFactory.getCurrentSession();
		String sql = "select count(c.id) from Blog c";
		Query query = session.createQuery(sql);
		Long f = (Long) query.uniqueResult();
		System.out.println("========= "+f % pageable.size);
		if(f % pageable.size != 0) {
			total = (int) (f / pageable.size)+1;
		}
		else {
			total = (int) (f / pageable.size);
		}
		return total;
	}
	
	public Blog findById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Blog blog = (Blog) session.get(Blog.class, id);
		return blog;
	}
	
	public void save(Blog blog) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(blog);
	}
	
	public void delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		Blog blog = (Blog) session.get(Blog.class, id);
		session.delete(blog);
	}
}
