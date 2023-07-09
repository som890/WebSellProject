package com.web.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.entity.Account;
import com.web.entity.Authority;

@Repository
@Transactional
public class AuthorityDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Authority findByRoleName(String roleName) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "select a from Authority a where a.ROLE_NAME = :role";
		Query query = session.createQuery(sql);
		query.setParameter("role", roleName);
		Authority authority = (Authority) query.uniqueResult();
		return authority;
	}
}
