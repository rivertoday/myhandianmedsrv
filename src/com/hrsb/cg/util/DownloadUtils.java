package com.hrsb.cg.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 下载
 * @author nalis
 *
 */
public class DownloadUtils {
	
	public static void download(HttpServletRequest request,
			HttpServletResponse response, String filePath) throws IOException {
		download(request, response, filePath, "");
	}

	public static void download(HttpServletRequest request,
			HttpServletResponse response, String filePath, String displayName)
			throws IOException {
		File file = new File(filePath);
		if (StringUtils.isEmpty(displayName)) {
			displayName = file.getName();
		}
		if (!file.exists() || !file.canRead()) {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write("您下载的文件不存在！");
			return;
		}
		String userAgent = request.getHeader("User-Agent");
		boolean isIE = (userAgent != null)
				&& (userAgent.toLowerCase().indexOf("msie") != -1);
		response.reset();
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "must-revalidate, no-transform");
		response.setDateHeader("Expires", 0L);
		response.setContentType("application/x-download");
		response.setContentLength((int) file.length());
		String displayFilename = displayName.substring(displayName
				.lastIndexOf("_") + 1);
		displayFilename = displayFilename.replace(" ", "_");
		if (isIE) {
			displayFilename = URLEncoder.encode(displayFilename, "UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=\""
					+ displayFilename + "\"");
		} else {
			displayFilename = new String(displayFilename.getBytes("UTF-8"),
					"ISO8859-1");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ displayFilename);
		}
		BufferedInputStream is = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			is = new BufferedInputStream(new FileInputStream(file));
			IOUtils.copy(is, os);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(is);
		}
	}

	public static void download(HttpServletRequest request,
			HttpServletResponse response, String displayName, byte[] bytes)
			throws IOException {
		if (ArrayUtils.isEmpty(bytes)) {
			response.setContentType("text/html;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("您下载的文件不存在！");
			return;
		}
		String userAgent = request.getHeader("User-Agent");
		boolean isIE = (userAgent != null)
				&& (userAgent.toLowerCase().indexOf("msie") != -1);
		response.reset();
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "must-revalidate, no-transform");
		response.setDateHeader("Expires", 0L);
		response.setContentType("application/x-download");
		response.setContentLength((int) bytes.length);
		String displayFilename = displayName.substring(displayName
				.lastIndexOf("_") + 1);
		displayFilename = displayFilename.replace(" ", "_");
		if (isIE) {
			displayFilename = URLEncoder.encode(displayFilename, "UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=\""
					+ displayFilename + "\"");
		} else {
			displayFilename = new String(displayFilename.getBytes("UTF-8"),
					"ISO8859-1");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ displayFilename);
		}
		BufferedInputStream is = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			is = new BufferedInputStream(new ByteArrayInputStream(bytes));
			IOUtils.copy(is, os);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(is);
		}
	}
	
	
	public static void execute(HttpServletRequest request,
			HttpServletResponse response,String []fileNameArryList,String marked) {  
        //生成的ZIP文件名为Demo.zip  
        String tmpFileName = DateUtil.getCompactCurrentDateTime()+marked+".zip";  
        String FilePath = request.getSession().getServletContext().getRealPath("/");
        byte[] buffer = new byte[1024*3];//压缩包大小  
        String strZipPath =  FilePath+"/upload/downZipFile/" + tmpFileName;  
        try {  
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(  
                    strZipPath));  
            for (int i = 0; i < fileNameArryList.length; i++) {  
                FileInputStream fis = new FileInputStream(FilePath + fileNameArryList[i]);  
                out.putNextEntry(new ZipEntry(fileNameArryList[i].substring(23)));  
                //设置压缩文件内的字符编码，不然会变成乱码  
                //out.("UTF-8");  
                int len;  
                // 读入需要下载的文件的内容，打包到zip文件  
                while ((len = fis.read(buffer)) > 0) {  
                    out.write(buffer, 0, len);  
                }  
                out.flush();
                out.closeEntry();  
                fis.close();
            }  
            out.close();  
            DownloadUtils.download(request, response, strZipPath);  
        } catch (Exception e) {  
            e.printStackTrace();
        }  
    }  
}
