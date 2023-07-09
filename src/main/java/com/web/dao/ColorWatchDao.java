package com.web.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.entity.Blog;
import com.web.entity.ColorWatch;
import com.web.entity.ImageWatch;

@Repository
@Transactional
public class ColorWatchDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(ColorWatch colorWatch) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(colorWatch);
	}
	
	public void deleteByWatchId(Integer idw) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "delete from ColorWatch where watch.id= :wid";
		Query query = session.createQuery(sql);
		query.setParameter("wid", idw);
		query.executeUpdate();
	}
	
	public ColorWatch findById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		ColorWatch colorWatch = (ColorWatch) session.get(ColorWatch.class, id);
		return colorWatch;
	}
}
