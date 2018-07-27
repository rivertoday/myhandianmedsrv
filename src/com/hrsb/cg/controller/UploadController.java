package com.hrsb.cg.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.coobird.thumbnailator.geometry.Positions;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.sanselan.ImageInfo;
import org.apache.sanselan.Sanselan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hrsb.cg.util.AjaxUtil;
import com.hrsb.cg.util.Const;
import com.hrsb.cg.util.DownloadUtils;
import com.hrsb.cg.util.ImageUtils;
import com.hrsb.cg.util.MapUtil;
import com.hrsb.cg.util.Tools;
@Controller
@RequestMapping("upload")
public class UploadController{
	@RequestMapping(value="file",method=RequestMethod.POST)
	public void uploadfile(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ServletContext application = request.getSession().getServletContext();
		String savePath = application.getRealPath("/") + "upload/";

		// 文件保存目录URL
		String saveUrl = request.getContextPath() + "/upload/";

		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,xml,sql,txt,zip,rar,gz,bz2,pdf");

		// 最大文件大小
		long maxSize = 10000000;

		response.setContentType("text/html; charset=UTF-8");

		if (!ServletFileUpload.isMultipartContent(request)) {
			AjaxUtil.JsonType("500", "请选择文件", "", "", "", "", "");
			return;
		}
		// 检查目录
		File uploadDir = new File(savePath);
		if (!uploadDir.isDirectory()) {
			uploadDir.mkdirs();
		}
		if (!uploadDir.isDirectory()) {
			AjaxUtil.JsonType("300", "上传目录不存在。", "", "", "", "", "上传失败");
			return;
		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			AjaxUtil.JsonType("300", "上传目录没有写权限。", "", "", "", "", "");
			return;
		}

		

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		List items = upload.parseRequest(request);
		Iterator itr = items.iterator();
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			String fileName = item.getName();
			if (!item.isFormField()) {
				// 检查文件大小
				if (item.getSize() > maxSize) {
					AjaxUtil.JsonType("300", "上传文件大小超过限制。", "", "", "", "", "");
					return;
				}
				// 检查扩展名
				String fileExt = fileName.substring(
						fileName.lastIndexOf(".") + 1).toLowerCase();
				String dirName = request.getParameter("dir");
				if (dirName == null) {
					dirName = MapUtil.getKey(extMap, fileExt, true);
				}
				if (!extMap.containsKey(dirName)) {
					AjaxUtil.JsonType("300", "目录名不正确。", "", "", "", "", "");
					return;
				}
				// 创建文件夹
				savePath += dirName + "/";
				saveUrl += dirName + "/";
				File saveDirFile = new File(savePath);
				if (!saveDirFile.exists()) {
					saveDirFile.mkdirs();
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String ymd = sdf.format(new Date());
				savePath += ymd + "/";
				saveUrl += ymd + "/";
				File dirFile = new File(savePath);
				if (!dirFile.exists()) {
					dirFile.mkdirs();
				}
				if (!Arrays.<String> asList(extMap.get(dirName).split(","))
						.contains(fileExt)) {
					AjaxUtil.JsonType("300", "上传文件扩展名是不允许的扩展名。\n只允许"
							+ extMap.get(dirName) + "格式。", "", "", "", "", "");
					return;
				}

				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_"
						+ new Random().nextInt(1000) + "." + fileExt;
				try {
					File uploadedFile = new File(savePath, newFileName);
					item.write(uploadedFile);
				} catch (Exception e) {
					AjaxUtil.JsonType("300", "上传失败", "", "", "", "", "上传文件失败。");
					return;
				}

				Map<String, Object> msg = new HashMap<String, Object>();
				msg.put("error", 0);
				msg.put("url", saveUrl + newFileName);
				//WebUtil.writerJson(response, msg);
				new AjaxUtil(request,response).JsonType("200", "上传完成", "", Tools.getBasePath(request) + saveUrl + newFileName, "", "", "");
			}
		}
	}
	
	
	@RequestMapping(value="file6",method=RequestMethod.POST)
	public void uploadfile6(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ServletContext application = request.getSession().getServletContext();
		String savePath = application.getRealPath("/") + "upload/";

		// 文件保存目录URL
		String saveUrl = request.getContextPath() + "/upload/";

		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,xml,sql,txt,zip,rar,gz,bz2,pdf");

		// 最大文件大小
		long maxSize = 10000000;

		response.setContentType("text/html; charset=UTF-8");

		if (!ServletFileUpload.isMultipartContent(request)) {
			AjaxUtil.JsonType("500", "请选择文件", "", "", "", "", "");
			return;
		}
		// 检查目录
		File uploadDir = new File(savePath);
		if (!uploadDir.isDirectory()) {
			uploadDir.mkdirs();
		}
		if (!uploadDir.isDirectory()) {
			AjaxUtil.JsonType("300", "上传目录不存在。", "", "", "", "", "上传失败");
			return;
		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			AjaxUtil.JsonType("300", "上传目录没有写权限。", "", "", "", "", "");
			return;
		}

		

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		List items = upload.parseRequest(request);		
		Iterator itr = items.iterator();
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			String fileName = item.getName();
			if (!item.isFormField()) {
				// 检查文件大小
				if (item.getSize() > maxSize) {
					AjaxUtil.JsonType("300", "上传文件大小超过限制。", "", "", "", "", "");
					return;
				}
				// 检查扩展名
				String fileExt = fileName.substring(
						fileName.lastIndexOf(".") + 1).toLowerCase();
				String dirName = request.getParameter("dir");
				if (dirName == null) {
					dirName = MapUtil.getKey(extMap, fileExt, true);
				}
				if (!extMap.containsKey(dirName)) {
					AjaxUtil.JsonType("300", "目录名不正确。", "", "", "", "", "");
					return;
				}
				// 创建文件夹
				savePath += dirName + "/";
				saveUrl += dirName + "/";
				File saveDirFile = new File(savePath);
				if (!saveDirFile.exists()) {
					saveDirFile.mkdirs();
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String ymd = sdf.format(new Date());
				savePath += ymd + "/";
				saveUrl += ymd + "/";
				File dirFile = new File(savePath);
				if (!dirFile.exists()) {
					dirFile.mkdirs();
				}
				if (!Arrays.<String> asList(extMap.get(dirName).split(","))
						.contains(fileExt)) {
					AjaxUtil.JsonType("300", "上传文件扩展名是不允许的扩展名。\n只允许"
							+ extMap.get(dirName) + "格式。", "", "", "", "", "");
					return;
				}

				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_"
						+ new Random().nextInt(1000) + "." + fileExt;
				String name=null;
				try {
					File uploadedFile = new File(savePath, newFileName);
					item.write(uploadedFile);
					name=item.getName();
				} catch (Exception e) {
					AjaxUtil.JsonType("300", "上传失败", "", "", "", "", "上传文件失败。");
					return;
				}

				Map<String, Object> msg = new HashMap<String, Object>();
				msg.put("error", 0);
				msg.put("url", saveUrl + newFileName);
				//WebUtil.writerJson(response, msg);
				new AjaxUtil(request,response).JsonType("200", "上传完成", "", Tools.getBasePath(request) + saveUrl + newFileName+","+name, "", "", "");
			}
		}
	}
	
	/**
	 * 上传头像用此方法
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="cfile",method=RequestMethod.POST)
	public void cuploadfile(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ServletContext application = request.getSession().getServletContext();
		String savePath = application.getRealPath("/") + "upload/";

		// 文件保存目录URL
		String saveUrl = request.getContextPath() + "/upload/";

		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,xml,sql,txt,zip,rar,gz,bz2,pdf");

		// 最大文件大小
		long maxSize = 167772160;

		response.setContentType("text/html; charset=UTF-8");

		if (!ServletFileUpload.isMultipartContent(request)) {
			AjaxUtil.JsonType("500", "请选择文件", "", "", "", "", "");
			return;
		}
		// 检查目录
		File uploadDir = new File(savePath);
		if (!uploadDir.isDirectory()) {
			AjaxUtil.JsonType("300", "上传目录不存在。", "", "", "", "", "上传失败");
			return;
		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			AjaxUtil.JsonType("300", "上传目录没有写权限。", "", "", "", "", "");
			return;
		}

		

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		List items = upload.parseRequest(request);
		Iterator itr = items.iterator();
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			String fileName = item.getName();
			if (!item.isFormField()) {
				// 检查文件大小
				if (item.getSize() > maxSize) {
					AjaxUtil.JsonType("300", "上传文件大小超过限制。", "", "", "", "", "");
					return;
				}
				// 检查扩展名
				String fileExt = fileName.substring(
						fileName.lastIndexOf(".") + 1).toLowerCase();
				String dirName = request.getParameter("dir");
				if (dirName == null) {
					dirName = MapUtil.getKey(extMap, fileExt, true);
				}
				if (!extMap.containsKey(dirName)) {
					AjaxUtil.JsonType("300", "目录名不正确。", "", "", "", "", "");
					return;
				}
				// 创建文件夹
				savePath += dirName + "/";
				saveUrl += dirName + "/";
				File saveDirFile = new File(savePath);
				if (!saveDirFile.exists()) {
					saveDirFile.mkdirs();
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String ymd = sdf.format(new Date());
				savePath += ymd + "/";
				saveUrl += ymd + "/";
				File dirFile = new File(savePath);
				if (!dirFile.exists()) {
					dirFile.mkdirs();
				}
				if (!Arrays.<String> asList(extMap.get(dirName).split(","))
						.contains(fileExt)) {
					AjaxUtil.JsonType("300", "上传文件扩展名是不允许的扩展名。\n只允许"
							+ extMap.get(dirName) + "格式。", "", "", "", "", "");
					return;
				}

				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_"
						+ new Random().nextInt(1000) + "." + fileExt;
				try {
					File uploadedFile = new File(savePath, newFileName);
					item.write(uploadedFile);
					//String str = ImageUtils.watermarkImage(newFileName, Const.SHUIYIN, ".png", 200, 200, Positions.CENTER, 0.5f, 1.0);
					//System.out.println(str);
				} catch (Exception e) {
					AjaxUtil.JsonType("300", "上传失败", "", "", "", "", "上传文件失败。");
					return;
				}

				Map<String, Object> msg = new HashMap<String, Object>();
				msg.put("error", 0);
				msg.put("url", saveUrl + newFileName);
				//WebUtil.writerJson(response, msg);
				new AjaxUtil(request,response).print(Tools.getBasePath(request) + saveUrl + newFileName);
			}
		}
	}
	/**
	 * 作品集上传视频用次方法
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="cfile5",method=RequestMethod.POST)
	public void cuploadfile5(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ServletContext application = request.getSession().getServletContext();
		String savePath = application.getRealPath("/") + "upload/";
		
		// 文件保存目录URL
		String saveUrl = request.getContextPath() + "/upload/";
		
		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp4,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,xml,sql,txt,zip,rar,gz,bz2,pdf");
		
		// 最大文件大小
		long maxSize = 167772160;
		
		response.setContentType("text/html; charset=UTF-8");
		
		if (!ServletFileUpload.isMultipartContent(request)) {
			AjaxUtil.JsonType("500", "请选择文件", "", "", "", "", "");
			return;
		}
		// 检查目录
		File uploadDir = new File(savePath);
		if (!uploadDir.isDirectory()) {
			AjaxUtil.JsonType("300", "上传目录不存在。", "", "", "", "", "上传失败");
			return;
		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			AjaxUtil.JsonType("300", "上传目录没有写权限。", "", "", "", "", "");
			return;
		}
		
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		List items = upload.parseRequest(request);
		Iterator itr = items.iterator();
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			String fileName = item.getName();
			if (!item.isFormField()) {
				// 检查文件大小
				if (item.getSize() > maxSize) {
					AjaxUtil.JsonType("300", "上传文件大小超过限制。", "", "", "", "", "");
					return;
				}
				// 检查扩展名
				String fileExt = fileName.substring(
						fileName.lastIndexOf(".") + 1).toLowerCase();
				String dirName = request.getParameter("dir");
				if (dirName == null) {
					dirName = MapUtil.getKey(extMap, fileExt, true);
				}
				if (!extMap.containsKey(dirName)) {
					AjaxUtil.JsonType("300", "目录名不正确。", "", "", "", "", "");
					return;
				}
				// 创建文件夹
				savePath += dirName + "/";
				saveUrl += dirName + "/";
				File saveDirFile = new File(savePath);
				if (!saveDirFile.exists()) {
					saveDirFile.mkdirs();
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String ymd = sdf.format(new Date());
				savePath += ymd + "/";
				saveUrl += ymd + "/";
				File dirFile = new File(savePath);
				if (!dirFile.exists()) {
					dirFile.mkdirs();
				}
				if (!Arrays.<String> asList(extMap.get(dirName).split(","))
						.contains(fileExt)) {
					AjaxUtil.JsonType("300", "上传文件扩展名是不允许的扩展名。\n只允许"
							+ extMap.get(dirName) + "格式。", "", "", "", "", "");
					return;
				}
				
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_"
						+ new Random().nextInt(1000) + "." + fileExt;
				long size = 0l;
				try {
					File uploadedFile = new File(savePath, newFileName);
					item.write(uploadedFile);
					size = item.getSize();
					//String str = ImageUtils.watermarkImage(newFileName, Const.SHUIYIN, ".png", 200, 200, Positions.CENTER, 0.5f, 1.0);
					//System.out.println(str);
				} catch (Exception e) {
					AjaxUtil.JsonType("300", "上传失败", "", "", "", "", "上传文件失败。");
					return;
				}
				
				Map<String, Object> msg = new HashMap<String, Object>();
				msg.put("error", 0);
				msg.put("url", saveUrl + newFileName);
				//WebUtil.writerJson(response, msg);
				String json = "{fileurl:\""+(saveUrl+newFileName)+"\",size:\""+Tools.getFileSize(size)+"\"}";
				new AjaxUtil(request,response).print(json);
			}
		}
	}
	/**
	 * 发布项目上传附件用此方法
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="cfile4",method=RequestMethod.POST)
	public void cuploadfile4(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ServletContext application = request.getSession().getServletContext();
		String savePath = application.getRealPath("/") + "upload/";
		
		// 文件保存目录URL
		String saveUrl = request.getContextPath() + "/upload/";
		
		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,xml,sql,txt,zip,rar,gz,bz2,pdf");
		
		// 最大文件大小
		long maxSize = 167772160;
		
		response.setContentType("text/html; charset=UTF-8");
		
		if (!ServletFileUpload.isMultipartContent(request)) {
			AjaxUtil.JsonType("500", "请选择文件", "", "", "", "", "");
			return;
		}
		// 检查目录
		File uploadDir = new File(savePath);
		if (!uploadDir.isDirectory()) {
			AjaxUtil.JsonType("300", "上传目录不存在。", "", "", "", "", "上传失败");
			return;
		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			AjaxUtil.JsonType("300", "上传目录没有写权限。", "", "", "", "", "");
			return;
		}
		
		
		
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		List items = upload.parseRequest(request);
		Iterator itr = items.iterator();
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			String fileName = item.getName();
			if (!item.isFormField()) {
				// 检查文件大小
				if (item.getSize() > maxSize) {
					AjaxUtil.JsonType("300", "上传文件大小超过限制。", "", "", "", "", "");
					return;
				}
				// 检查扩展名
				String fileExt = fileName.substring(
						fileName.lastIndexOf(".") + 1).toLowerCase();
				String dirName = request.getParameter("dir");
				if (dirName == null) {
					dirName = MapUtil.getKey(extMap, fileExt, true);
				}
				if (!extMap.containsKey(dirName)) {
					AjaxUtil.JsonType("300", "目录名不正确。", "", "", "", "", "");
					return;
				}
				// 创建文件夹
				savePath += dirName + "/";
				saveUrl += dirName + "/";
				File saveDirFile = new File(savePath);
				if (!saveDirFile.exists()) {
					saveDirFile.mkdirs();
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String ymd = sdf.format(new Date());
				savePath += ymd + "/";
				saveUrl += ymd + "/";
				File dirFile = new File(savePath);
				if (!dirFile.exists()) {
					dirFile.mkdirs();
				}
				if (!Arrays.<String> asList(extMap.get(dirName).split(","))
						.contains(fileExt)) {
					AjaxUtil.JsonType("300", "上传文件扩展名是不允许的扩展名。\n只允许"
							+ extMap.get(dirName) + "格式。", "", "", "", "", "");
					return;
				}
				
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_"
						+ new Random().nextInt(1000) + "." + fileExt;
				String name = "";
				long size = 0l;
				try {
					File uploadedFile = new File(savePath, newFileName);
					item.write(uploadedFile);
					name = item.getName();
					size = item.getSize();
					//String str = ImageUtils.watermarkImage(newFileName, Const.SHUIYIN, ".png", 200, 200, Positions.CENTER, 0.5f, 1.0);
					//System.out.println(str);
				} catch (Exception e) {
					AjaxUtil.JsonType("300", "上传失败", "", "", "", "", "上传文件失败。");
					return;
				}
				
				Map<String, Object> msg = new HashMap<String, Object>();
				msg.put("error", 0);
				msg.put("url", saveUrl + newFileName);
				//WebUtil.writerJson(response, msg);
				String json = "{fileurl:\""+(saveUrl+newFileName)+"\","+ "name:\""+name+"\",size:\""+Tools.getFileSize(size)+"\",sizenum:\""+size+"\"}";
				new AjaxUtil(request,response).print(json);
			}
		}
	}
	
	/**
	 * 作品集上传图片用此方法
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="cfile3",method=RequestMethod.POST)
	public void cuploadfile3(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ServletContext application = request.getSession().getServletContext();
		String savePath = application.getRealPath("/") + "upload/";
		
		// 文件保存目录URL
		String saveUrl = request.getContextPath() + "/upload/";
		
		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,xml,sql,txt,zip,rar,gz,bz2,pdf");
		
		// 最大文件大小
		long maxSize = 167772160;
		
		response.setContentType("text/html; charset=UTF-8");
		
		if (!ServletFileUpload.isMultipartContent(request)) {
			AjaxUtil.JsonType("500", "请选择文件", "", "", "", "", "");
			return;
		}
		// 检查目录
		File uploadDir = new File(savePath);
		if (!uploadDir.isDirectory()) {
			AjaxUtil.JsonType("300", "上传目录不存在。", "", "", "", "", "上传失败");
			return;
		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			AjaxUtil.JsonType("300", "上传目录没有写权限。", "", "", "", "", "");
			return;
		}
		
		
		
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		List items = upload.parseRequest(request);
		Iterator itr = items.iterator();
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			String fileName = item.getName();
			if (!item.isFormField()) {
				// 检查文件大小
				if (item.getSize() > maxSize) {
					AjaxUtil.JsonType("300", "上传文件大小超过限制。", "", "", "", "", "");
					return;
				}
				// 检查扩展名
				String fileExt = fileName.substring(
						fileName.lastIndexOf(".") + 1).toLowerCase();
				String dirName = request.getParameter("dir");
				if (dirName == null) {
					dirName = MapUtil.getKey(extMap, fileExt, true);
				}
				if (!extMap.containsKey(dirName)) {
					AjaxUtil.JsonType("300", "目录名不正确。", "", "", "", "", "");
					return;
				}
				// 创建文件夹
				savePath += dirName + "/";
				saveUrl += dirName + "/";
				File saveDirFile = new File(savePath);
				if (!saveDirFile.exists()) {
					saveDirFile.mkdirs();
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String ymd = sdf.format(new Date());
				savePath += ymd + "/";
				saveUrl += ymd + "/";
				File dirFile = new File(savePath);
				if (!dirFile.exists()) {
					dirFile.mkdirs();
				}
				if (!Arrays.<String> asList(extMap.get(dirName).split(","))
						.contains(fileExt)) {
					AjaxUtil.JsonType("300", "上传文件扩展名是不允许的扩展名。\n只允许"
							+ extMap.get(dirName) + "格式。", "", "", "", "", "");
					return;
				}
				
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_"
						+ new Random().nextInt(1000) + "." + fileExt;
				long size = 0l;
				int dpi = 0;
				int heigth = 0;
				int width = 0;
				String imgName = "";
				try {
					File uploadedFile = new File(savePath, newFileName);
					item.write(uploadedFile);
					imgName = item.getName();
					ImageInfo imageInfo = Sanselan.getImageInfo(uploadedFile);
					size = item.getSize();//图片大小
					dpi = imageInfo.getPhysicalHeightDpi();//分辨率
					heigth = imageInfo.getHeight();//高
					width = imageInfo.getWidth();//宽
					//String str = ImageUtils.watermarkImage(newFileName, Const.SHUIYIN, ".png", 200, 200, Positions.CENTER, 0.5f, 1.0);
					//System.out.println(str);
				} catch (Exception e) {
					AjaxUtil.JsonType("300", "上传失败", "", "", "", "", "上传文件失败。");
					return;
				}

				Map<String, Object> msg = new HashMap<String, Object>();
				msg.put("error", 0);
				msg.put("url", saveUrl + newFileName);
				/*msg.put("size", size);
				msg.put("dpi", dpi);
				msg.put("heigth", heigth);
				msg.put("width", width);*/
				//WebUtil.writerJson(response, msg);
				String json = "{fileurl:\""+(saveUrl+newFileName)+"\","+ "imgName:\""+imgName+"\",size:\""+Tools.getFileSize(size)+"\",dpi:"+dpi+",measure:\""+(width + "*" + heigth)+"\"}";
				new AjaxUtil(request,response).print(json);
			}
		}
	}
	
	/**
	 * 附件下载 支持一次性下载多个文件
	 * @param request
	 * @param response
	 * @param filename 单个文件名称
	 * @param displayName 
	 * @param fileNameArryList 文件集合 用逗号分隔的字符串
	 * @param marked 唯一标示 可传ID 也可不传，防止并发时 下载错误数据
	 * @throws IOException
	 */
	@RequestMapping(value="download")
	public void download(HttpServletRequest request,
			HttpServletResponse response, String filename, String displayName,String fileNameArryList,String marked)
			throws IOException {
		if(fileNameArryList != null && !fileNameArryList.equals("")){
			String []list = null;
			list = fileNameArryList.split(",");
			DownloadUtils.execute(request, response, list,marked);
		}
		if(filename != null && !filename.equals("")){
			String filePath = request.getSession().getServletContext().getRealPath("/") + filename;
			DownloadUtils.download(request, response, filePath);
		}
	}
	
	
	@RequestMapping(value="dowV")
	public void dowV(HttpServletRequest request,HttpServletResponse response)throws IOException {
		String type = request.getParameter("type");
		String filePath = "";
		if(type.equals("0")){
			filePath = request.getSession().getServletContext().getRealPath("/") + "gerengonghan.docx";
		}else {
			filePath = request.getSession().getServletContext().getRealPath("/") + "qiyegonghan.docx";
		}
		DownloadUtils.download(request, response, filePath);
	}
	
}
