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
import com.web.entity.UserRole;
import com.web.model.UserInfo;

@Repository
@Transactional
public class UserInfoDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public UserInfoDAO() {

	}

	public UserInfo findUserInfo(String userName) {
		String sql = "select a from Account a where a.username = :username and a.enable = :enable";

		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery(sql);
		query.setParameter("username", userName);
		query.setParameter("enable", 1);
		Account a = (Account) query.uniqueResult();
		System.out.println("account-----: " + a);
		UserInfo u = new UserInfo(a.getUsername(), a.getPassword());
		return u;
	}

	public List<String> getUserRoles(String userName) {
		String sql = "select u from UserRole u inner join u.account where u.account.username = :username ";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		query.setParameter("username", userName);
		List<UserRole> roles = query.list();
		List<String> list = new ArrayList<>();
		for (UserRole u : roles) {
			list.add(u.getAuthority().getROLE_NAME());
		}
		System.out.println(list);

		return list;
	}
}
