package com.hrsb.cg.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.tools.zip.ZipEntry;
//import org.apache.tools.zip.ZipOutputStream;

public class FileUtil {
	
	/**
	 * 获取文件保存后名称
	 * @param fileName 原名称
	 * @return
	 * @throws Exception
	 */
	public static String getName(String fileName) throws Exception {
		Random ran = new Random();
		int number = ran.nextInt(1024);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String dateTime = sdf.format(new Date());
		return dateTime + number + getSuffix(fileName);
	}
	
	/**
	 * 取后缀
	 * @param fileName 原始文件名称
	 * @return 后缀
	 */
	private static String getSuffix(String fileName){
		String suffix = "";
		
		if(fileName != null){
			if(fileName.lastIndexOf(".") != -1){
				int index = fileName.lastIndexOf(".");
				suffix = fileName.substring(index);
			}
			
			if(fileName.toLowerCase().lastIndexOf(".pdf") != -1){
				suffix = ".pdf";
			}
		}
		
		return suffix;
	}

	public static String settimes(String time){
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = null;
		try {
			d = df.parse(time);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		Calendar cal=Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.DATE, -1);  
		cal.add(Calendar.YEAR, 1); 
		return df.format(cal.getTime());
	}
	public static String getTimess() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		return sdf.format(date.getTime());
	}

	public static String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return sdf.format(date.getTime());
	}

	public static String getTimedd() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return sdf.format(date.getTime());
	}

	public static String getTimeMM() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date date = new Date();
		return sdf.format(date.getTime());
	}
	public static String getTimeFullYear(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Date date = new Date();
		return sdf.format(date.getTime());
	}
	// 上传文件/复制文件。
	public static void copyFile(File src, File dst) {
		try {
			int BUFFER_SIZE = 16 * 1024;
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src),
						BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				for (int byteRead = 0; (byteRead = in.read(buffer)) > 0;) {
					out.write(buffer, 0, byteRead);
				}

			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String fileName(){
		String fileName = java.util.UUID.randomUUID().toString();
		return fileName;
	}

	//压缩文件
	public static void createZip(String src, String nilename, String path)
			throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ZipOutputStream zipOut = new ZipOutputStream(bos);
		//zipOut.setsetEncoding("gbk");

		File file = new File(path, src);
		byte[] buffer = new byte[4096];
		int bytes_read;
		InputStream fis = new FileInputStream(file);
		zipOut.putNextEntry(new ZipEntry(src));
		while ((bytes_read = fis.read(buffer)) != -1) {
			zipOut.write(buffer, 0, bytes_read);
		}
		zipOut.closeEntry();
		fis.close();

		zipOut.close();
		FileOutputStream fout = new FileOutputStream(new File(path, nilename));
		bos.writeTo(fout);	
		fout.flush();
		fout.close();
	}
	public static void createFile(String url){
		File file = new File(url);
		if(!file.exists())    
		{    
			if  (!file .exists()  && !file .isDirectory())      
			{       
			    System.out.println("//不存在");  
			    file .mkdir();    
			} else   
			{  
			    System.out.println("//目录存在");  
			}  
   
		} 
		
	}
	public static void main(String[] args) throws Exception {
		//createZip("1234.txt","11.rar","E:/");
	}
	public static String fuleurl(HttpServletRequest request,HttpServletResponse response,String url){
		String savePath = request.getSession().getServletContext().getRealPath(url);
		return savePath;
	}
}
