package com.web.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class UploadConfig {

	public String upload(MultipartFile file, HttpServletRequest request) throws IOException {
		try {
			String uploadsDir = "/uploads/";
			String realPathtoUploads = request.getServletContext().getRealPath(uploadsDir);
			if (!new File(realPathtoUploads).exists()) {
				new File(realPathtoUploads).mkdir();
			}
			System.out.println("realPathtoUploads"+ realPathtoUploads);
			String orgName = file.getOriginalFilename();
			String filePath = realPathtoUploads + orgName;
			File dest = new File(filePath);
			file.transferTo(dest);
			System.out.println(dest.getPath());
			return dest.getPath();
		} catch (Exception e) {
			
		}
		return "";
	}

	public String uploadFile(MultipartFile file) {
		try {
			File uploadedFile = convertMultiPartToFile(file);
			Map uploadResult = cloudinaryConfigs().uploader().upload(uploadedFile, ObjectUtils.emptyMap());
			uploadedFile.delete();
			return uploadResult.get("url").toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Cloudinary cloudinaryConfigs() {
		Cloudinary cloudinary = null;
		Map config = new HashMap();
		config.put("cloud_name", "dxccmy7an");
		config.put("api_key", "233598743282511");
		config.put("api_secret", "uQJ0N8nPQjm9Zr870BmGWUeViN4");
		cloudinary = new Cloudinary(config);
		return cloudinary;
	}

	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}
}
