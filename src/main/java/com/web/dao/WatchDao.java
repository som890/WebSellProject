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
import com.web.model.Pageable;

@Repository
@Transactional
public class WatchDao {

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
	
	public List<Watch> findByCategory(Integer cateId){
		List<Watch> list = new ArrayList<Watch>();
		Session session = sessionFactory.getCurrentSession();
		String sql = "select c from Watch c where c.category.id = :id";
		Query query = session.createQuery(sql);
		query.setParameter("id", cateId);
		query.setMaxResults(4);
		list = query.list();
		return list;
	}
	
	public List<Watch> getSanPhamLienQuan(Integer cateId, Integer proId){
		List<Watch> list = new ArrayList<Watch>();
		Session session = sessionFactory.getCurrentSession();
		String sql = "select c from Watch c where c.category.id = :id and c.id != :idpro";
		Query query = session.createQuery(sql);
		query.setParameter("id", cateId);
		query.setParameter("idpro", proId);
		query.setMaxResults(4);
		list = query.list();
		return list;
	}
	
	public List<Watch> listWatchPage(Pageable pageable){
		List<Watch> list = new ArrayList<Watch>();
		Session session = sessionFactory.getCurrentSession();
		String sql = "select c from Watch c order by c.id desc";
		Query query = session.createQuery(sql);
		query.setMaxResults(pageable.size);
		query.setFirstResult(pageable.size * pageable.page);
		list = query.list();
		return list;
	}
	
	public List<Watch> listSpBanChay(Pageable pageable){
		List<Watch> list = new ArrayList<Watch>();
		Session session = sessionFactory.getCurrentSession();
		String sql = "select d.colorWatch.watch from DetailInvoice d order by (select sum(ds.quantity) from DetailInvoice ds where ds.id = d.id) desc";
		Query query = session.createQuery(sql);
		query.setMaxResults(pageable.size);
		query.setFirstResult(pageable.size * pageable.page);
		list = query.list();
		return list;
	}
	
	public List<Watch> listWatchPageByCate(Pageable pageable, Integer cateId){
		List<Watch> list = new ArrayList<Watch>();
		Session session = sessionFactory.getCurrentSession();
		String sql = "select c from Watch c where c.category.id = :idcate order by c.id desc";
		Query query = session.createQuery(sql);
		query.setParameter("idcate", cateId);
		query.setMaxResults(pageable.size);
		query.setFirstResult(pageable.size * pageable.page);
		list = query.list();
		return list;
	}
	
	public List<Watch> listWatchPageByParam(Pageable pageable, String search){
		List<Watch> list = new ArrayList<Watch>();
		Session session = sessionFactory.getCurrentSession();
		String sql = "select c from Watch c where c.name like :param order by c.id desc";
		Query query = session.createQuery(sql);
		query.setParameter("param", "%"+search+"%");
		query.setMaxResults(pageable.size);
		query.setFirstResult(pageable.size * pageable.page);
		list = query.list();
		return list;
	}
	
	public Integer getTotalPageByParam(Pageable pageable, String search){
		Integer total = 0;
		Session session = sessionFactory.getCurrentSession();
		String sql = "select count(c.id) from Watch c where c.name like :param";
		Query query = session.createQuery(sql);
		query.setParameter("param", "%"+search+"%");
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
	
	public Integer getTotalPageByCate(Pageable pageable, Integer cateId){
		Integer total = 0;
		Session session = sessionFactory.getCurrentSession();
		String sql = "select count(c.id) from Watch c where c.category.id = :idcate";
		Query query = session.createQuery(sql);
		query.setParameter("idcate", cateId);
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
	
	
	
	public Integer getTotalPage(Pageable pageable){
		Integer total = 0;
		Session session = sessionFactory.getCurrentSession();
		String sql = "select count(c.id) from Watch c";
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
	
	public List<Watch> listWatchPageSearch(Pageable pageable, String hql, Double small, Double lager, Integer cateid, Integer trademarkid){
		List<Watch> list = new ArrayList<Watch>();
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("small", small);
		query.setParameter("lager", lager);
		query.setParameter("idcate", cateid);
		query.setParameter("idtrademark", trademarkid);
		query.setMaxResults(pageable.size);
		query.setFirstResult(pageable.size * pageable.page);
		list = query.list();
		return list;
	}
	
	public Integer getTotalPageSearch(Pageable pageable, String hql, Double small, Double lager, Integer cateid, Integer trademarkid){
		Integer total = 0;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("small", small);
		query.setParameter("lager", lager);
		query.setParameter("idcate", cateid);
		query.setParameter("idtrademark", trademarkid);
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
	
	public Watch findById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Watch watch = (Watch) session.get(Watch.class, id);
		return watch;
	}
	
	public Watch save(Watch watch) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(watch);
		return watch;
	}
	
	public void delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		Watch watch = (Watch) session.get(Watch.class, id);
		session.delete(watch);
	}
}
