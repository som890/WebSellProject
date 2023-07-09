package com.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.web.config.UploadConfig;
import com.web.dao.CategoryDao;
import com.web.dao.ColorWatchDao;
import com.web.dao.ImageWatchDao;
import com.web.dao.TradeMarkDao;
import com.web.dao.WatchDao;
import com.web.entity.Category;
import com.web.entity.ColorWatch;
import com.web.entity.ImageWatch;
import com.web.entity.TradeMark;
import com.web.entity.Watch;

@Service
public class WatchService {

	@Autowired
	private WatchDao watchDao;
	
	private UploadConfig uploadConfig = new UploadConfig();
	
	@Autowired
	private ColorWatchDao colorWatchDao;
	
	@Autowired
	private ImageWatchDao imageWatchDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private TradeMarkDao tradeMarkDao;
	
	public void saveWatch(Watch watch, Integer category, Integer trademark, String color[]) {
		Category cate = categoryDao.findById(category);
		TradeMark traMark = tradeMarkDao.findById(trademark);
		watch.setCategory(cate);
		watch.setTradeMark(traMark);
		String banner = "";
		if(watch.getId() != null) {
			banner = watchDao.findById(watch.getId()).getBanner();
		}
		if(!watch.getAnhnen().isEmpty()) {
			banner = uploadConfig.uploadFile(watch.getAnhnen());
		}
		watch.setBanner(banner);
		Watch w = watchDao.save(watch);
		colorWatchDao.deleteByWatchId(w.getId());
		for(String co : color) {
			ColorWatch colorWatch = new ColorWatch();
			colorWatch.setColorname(co);
			colorWatch.setWatch(w);
			colorWatchDao.save(colorWatch);
		}
		for(MultipartFile m : watch.getAnhphu()) {
			if(!m.isEmpty()) {
				String img = uploadConfig.uploadFile(m);
				ImageWatch imageWatch = new ImageWatch();
				imageWatch.setLinkImage(img);
				imageWatch.setWatch(w);
				imageWatchDao.save(imageWatch);
			}
		}
	}
}
