package com.web.controller.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.dao.CategoryDao;
import com.web.dao.CommentDao;
import com.web.dao.TradeMarkDao;
import com.web.dao.WatchDao;
import com.web.entity.Watch;
import com.web.model.Pageable;
import com.web.service.Contains;

@Controller
public class ProductUserController {

	@Autowired
	private WatchDao watchDao;
	
	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private TradeMarkDao tradeMarkDao;
	
	
	
	@RequestMapping(value = { "/product" }, method = RequestMethod.GET)
	public String product(Model model, @RequestParam(value = "khoangia", required = false) String khoanggia,
			@RequestParam(value = "danhmuc", required = false) Integer iddanhmuc,
			@RequestParam(value = "thuonghieu", required = false) Integer idthuonghieu,
			@RequestParam(value = "cate", required = false) Integer cate,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "page", required = false) Integer page) {
		Integer p = 0;
		if(page != null) {
			p = page - 1;
		}
		List<Integer> listpage = new ArrayList<>();
		Double small = 0D;
		Double lager = 100000000000D;
		System.out.println("============ "+cate);
		if(search != null) {
			model.addAttribute("listproduct", watchDao.listWatchPageByParam(new Pageable(Contains.sizeProduct, p), search));
			for(int i=1; i<=watchDao.getTotalPageByParam(new Pageable(Contains.sizeProduct, 0),search); i++) {
				listpage.add(i);
			}
			model.addAttribute("totalpage", listpage);
		}
		else if(khoanggia == null && iddanhmuc == null && idthuonghieu == null & cate == null) {
			model.addAttribute("listproduct", watchDao.listWatchPage(new Pageable(Contains.sizeProduct, p)));
			for(int i=1; i<=watchDao.getTotalPage(new Pageable(Contains.sizeProduct, 0)); i++) {
				listpage.add(i);
			}
			model.addAttribute("totalpage", listpage);
		}
		else if(cate != null) {
			System.out.println("+++++++++++++++++");
			model.addAttribute("listproduct", watchDao.listWatchPageByCate(new Pageable(Contains.sizeProduct, p), cate ));
			for(int i=1; i<=watchDao.getTotalPageByCate(new Pageable(Contains.sizeProduct, 0), cate); i++) {
				listpage.add(i);
			}
			model.addAttribute("totalpage", listpage);
		}
		else {
			if(khoanggia != null) {
				small = Double.valueOf(khoanggia.split("-")[0]);
				lager = Double.valueOf(khoanggia.split("-")[1]);
			}
			System.out.println(small);
			System.out.println(lager);
			System.out.println(iddanhmuc);
			System.out.println(idthuonghieu);
			String hql = "select c from Watch c where c.price >= :small and c.price <= :lager ";
			String hqlcount = "select count(c.id) from Watch c where c.price >= :small and c.price <= :lager ";
			if(iddanhmuc == -1) {
				hql += "and c.category.id != :idcate ";
				hqlcount += "and c.category.id != :idcate ";
			}
			else {
				hql += "and c.category.id = :idcate ";
				hqlcount += "and c.category.id = :idcate ";
			}
			if(idthuonghieu == -1) {
				hql += "and c.tradeMark.id != :idtrademark ";
				hqlcount += "and c.tradeMark.id != :idtrademark ";
			}
			else {
				hql += "and c.tradeMark.id != :idtrademark ";
				hqlcount += "and c.tradeMark.id != :idtrademark ";
			}
			hql += " order by c.id desc";
			System.out.println(hql);
			model.addAttribute("listproduct", watchDao.listWatchPageSearch(
					new Pageable(Contains.sizeProduct, p), hql,small, lager, iddanhmuc, idthuonghieu));
			
			for(int i=1; i<=watchDao.getTotalPageSearch(new Pageable(Contains.sizeProduct, 0), hqlcount, small, lager,iddanhmuc, idthuonghieu); i++) {
				listpage.add(i);
			}
			model.addAttribute("totalpage", listpage);
			
		}
		
		model.addAttribute("listcategory", categoryDao.findAll());
		model.addAttribute("listtrademark", tradeMarkDao.findAll());
		model.addAttribute("category", categoryDao.findAll());
		return "/views/user/listproduct";
	}
	
	@RequestMapping(value = { "/detail" }, method = RequestMethod.GET)
	public String detailProduct(Model model, @RequestParam("id") Integer id) {
		Watch w =  watchDao.findById(id);
		model.addAttribute("detail", w);
		model.addAttribute("listComment", commentDao.findByProductId(id));
		model.addAttribute("listlienquan", watchDao.getSanPhamLienQuan(w.getCategory().getId(), id));
		model.addAttribute("category", categoryDao.findAll());
		return "/views/user/detail";
	}
}
