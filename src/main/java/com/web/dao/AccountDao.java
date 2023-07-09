package com.web.dao;

import java.sql.Date;
import java.sql.Timestamp;
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


@Repository
@Transactional
public class AccountDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addOrUpdate(Account account) {
		account.setEnable(1);
		account.setCreated_date(new Timestamp(System.currentTimeMillis()));
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(account);
	}
	
	public Account save(Account account) {
		account.setCreated_date(new Timestamp(System.currentTimeMillis()));
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(account);
		return account;
	}
	
	@SuppressWarnings("unchecked")
	public List<Account> findByRole(String role){
		List<Account> list = new ArrayList<Account>();
		Session session = sessionFactory.getCurrentSession();
		String sql = "select a from Account a inner join a.userRoles u where u.userRole = :role";
		Query query = session.createQuery(sql);
		query.setParameter("role", role);
		list = query.list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Account> findByRoleUser(){
		List<Account> list = new ArrayList<Account>();
		Session session = sessionFactory.getCurrentSession();
		String sql = "select a from Account a inner join a.userRoles u where u.authority.ROLE_NAME = :role";
		Query query = session.createQuery(sql);
		query.setParameter("role", "USER");
		list = query.list();
		return list;
	}
	
	public Account findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Account acc = (Account) session.get(Account.class, id);
		return acc;
	}
	
	public void addRole(UserRole userRole) {
		Session session = sessionFactory.getCurrentSession();
		session.save(userRole);
	}
	
	public void delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		Account acc = (Account) session.get(Account.class, id);
		session.delete(acc);
	}
	
	public Account getUserWithAuthority(String username) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "select a from Account a where a.username = :username";
		Query query = session.createQuery(sql);
		query.setParameter("username", username);
		Account account = (Account) query.uniqueResult();
		return account;
	}
	
	public Account findByActivationkey(String key) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "select a from Account a where a.activationkey = :key";
		Query query = session.createQuery(sql);
		query.setParameter("key", key);
		Account account = (Account) query.uniqueResult();
		return account;
	}
	
	public Account findByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "select a from Account a where a.email = :email";
		Query query = session.createQuery(sql);
		query.setParameter("email", email);
		Account account = (Account) query.uniqueResult();
		return account;
	}
	
	public Long countUser() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "select count(a.id) from Account a";
		Query query = session.createQuery(sql);
		Long total = (Long) query.uniqueResult();
		return total;
	}
	
	@SuppressWarnings("unchecked")
	public List<Account> getUserThisMonth(){
		List<Account> list = new ArrayList<Account>();
		Session session = sessionFactory.getCurrentSession();
		String sql = "select a from Account a where MONTH(a.created_date) = :month and YEAR(a.created_date) = :year";
		Query query = session.createQuery(sql);
		query.setParameter("month", new Date(System.currentTimeMillis()).getMonth() + 1);
		System.out.println("===== month: "+new Date(System.currentTimeMillis()).getMonth());
		query.setParameter("year", new Date(System.currentTimeMillis()).getYear());
		list = query.list();
		return list;
	}
}
