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
import com.web.entity.Comment;
import com.web.model.Pageable;

@Repository
@Transactional
public class CommentDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Comment> findByProductId(Integer productId){
		List<Comment> list = new ArrayList<Comment>();
		Session session = sessionFactory.getCurrentSession();
		String sql = "select c from Comment c where c.watch.id = :idpro";
		Query query = session.createQuery(sql);
		query.setParameter("idpro", productId);
		list = query.list();
		return list;
	}
	
	
	public void save(Comment comment) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(comment);
	}
	
	public void delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		Comment comment = (Comment) session.get(Comment.class, id);
		session.delete(comment);
	}
	
	public Comment findById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Comment comment = (Comment) session.get(Comment.class, id);
		return comment;
	}
}
