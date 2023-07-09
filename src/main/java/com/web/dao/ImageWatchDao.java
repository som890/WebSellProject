package com.web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.entity.Blog;
import com.web.entity.ImageWatch;
import com.web.entity.Watch;

@Repository
@Transactional
public class ImageWatchDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(ImageWatch imageWatch) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(imageWatch);
	}
	
	public void delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		ImageWatch imageWatch = (ImageWatch) session.get(ImageWatch.class, id);
		session.delete(imageWatch);
	}
}
