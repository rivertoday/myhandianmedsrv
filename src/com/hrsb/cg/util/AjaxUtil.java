package com.hrsb.cg.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

public class AjaxUtil{
	private static HttpServletRequest request;
	private static HttpServletResponse response;
	@SuppressWarnings("static-access")
	public AjaxUtil(HttpServletRequest request,HttpServletResponse response) {
		this.request = request;
		this.response = response;
		// TODO Auto-generated constructor stub
	}
	public AjaxUtil() {
		super();
		// TODO Auto-generated constructor stub
	}
	private static Logger logger = Logger.getLogger(AjaxUtil.class);  
	//printstring
	public static void print(String content) throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out =response.getWriter();
		out.print(content);
		out.close();
		out.flush();
	}
	//maptojson
	public static void print(Map<String, Object> content) throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out =response.getWriter();
		out.print(JsonUtil.maptojson(content).toString());
		out.close();
		out.flush();
	}
	//listtojson
	public static <T> void print(List<T> content) throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out =response.getWriter();
		out.print(JsonUtil.listtojson(content));
		out.close();
		out.flush();
	}
	public static void print(Object o) throws IOException{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out =response.getWriter();
		out.print(JsonUtil.objecttojson(o));
		out.close();
		out.flush();
	}
	/**
	 * 判断是否是ajax提交
	 * @param request
	 * @return
	 */
	public static boolean isAjax() {
		if (request != null	&& "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With")))
			return true;
		return false;
	}
	//返回json格式
	/**
	 * 返回json for b-jui
	 * @param statusCode 操作状态
	 * @param message 提示信息
	 * @param forward 重定向url
	 * @param navTabId 刷新navtabid（唯一）
	 * @param tabid 窗体id
	 * @param callbackType 自定义回调函数
	 * @param confirmMsg 刷新标签触发confirm消息
	 * @throws IOException
	 */
	public static void JsonType(String statusCode,String message, String forward,
			String navTabId, String tabid, String callbackType,String confirmMsg) throws IOException{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out =response.getWriter();
		BaseUtil bast=new BaseUtil(statusCode, message, forward, navTabId, tabid, callbackType,confirmMsg);
		Gson gson=new Gson();
		String json=gson.toJson(bast);
		System.out.println(json);
		out.print(json);
		out.close();
		out.flush();
	}
	//返回json2格式
	/**
	 * 
	 * 返回json for b-jui
	 * @param statusCode 操作状态
	 * @param message 提示信息
	 * @param forward 重定向url
	 * @param navTabId 刷新navtabid（唯一）
	 * @param tabid 窗体id
	 * @param callbackType 自定义回调函数
	 * @param confirmMsg 刷新标签触发confirm消息
	 * @param closeCurrent 是否关闭当前dialog或者navtab
	 * @throws IOException
	 */
		public static void JsonType(String statusCode,String message, String forwardUrl,
				String navTabId, String rel, String callbackType,String confirmMsg,boolean closeCurrent ) throws IOException{
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out =response.getWriter();
			BaseUtil bast=new BaseUtil(statusCode, message, forwardUrl, navTabId, rel, callbackType,confirmMsg,closeCurrent);
			Gson gson=new Gson();
			String json=gson.toJson(bast);
			System.out.println(json);
			out.print(json);
			out.close();
			out.flush();
		}
		/**
		 * 
		 * @param statusCode 状态代码 200成功 300失败
		 * @param message 提示信息
		 * @param tabid 窗体ID
		 * @param closeCurrent 是否关闭当前窗口
		 * @param forward 跳转
		 * @param forwardConfirm 跳转确认
		 * @throws IOException
		 */
	public static void returnAjax(String statusCode,String message,String tabid,boolean closeCurrent,String forward,String forwardConfirm) throws IOException{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out =response.getWriter();
		ReturnAjax ajax = new ReturnAjax(statusCode, message, tabid, closeCurrent, forward, forwardConfirm);
		Gson gson=new Gson();
		String json=gson.toJson(ajax);
		System.out.println(json);
		out.print(json);
		out.close();
		out.flush();
	}

	public static void addCookie(String key,String obj,HttpServletResponse response,HttpServletRequest request){
		response.setContentType("text/html;charset=utf-8");
		Cookie c = new Cookie(key,obj) ;
		//设定有效时间  以s为单位
		c.setMaxAge(60*60*60*12*30);
		//设置Cookie路径和域名
		c.setPath("/");
		//域名要以“.”开头
		c.setDomain("localhost") ;
		//发送Cookie文件
		response.addCookie(c) ;
	}
	public static String getCookie(String key,HttpServletResponse response,HttpServletRequest request){
		Cookie cookies[] = request.getCookies() ;
		Cookie c1 = null ;
		if(cookies != null){
			
			for(int i=0;i<cookies.length;i++){
				c1 = cookies[i] ;
				if(key.equals(c1.getName())){
					return c1.getValue();
				}
			}
		}
		return null;
	}
	public static void delCookie(String key,HttpServletResponse response,HttpServletRequest request){
		//删除Cookie,(将Cookie的有效时间设为0)
	       Cookie cookies[] = request.getCookies() ;
	       Cookie c = null ;
	       for(int i=0;i<cookies.length;i++){
	           c = cookies[i] ;
	           if(c.getName().equals(key)){
	              c.setMaxAge(0);
	              response.addCookie(c) ;
	           }
	       }
	}

	/*	public static void main(String[] args) {
		Gson gson=new Gson();
		String json=gson.toJson(new BaseUtil(200,null,"","","",""));
		System.err.println(json);
	}*/
	/** 
     * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址; 
     *  
     * @param request 
     * @return 
     * @throws IOException 
     */  
    public final static String getIpAddress(HttpServletRequest request) throws IOException {  
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址  
  
        String ip = request.getHeader("X-Forwarded-For");  
        if (logger.isInfoEnabled()) {  
            logger.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);  
        }  
  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("Proxy-Client-IP");  
                if (logger.isInfoEnabled()) {  
                    logger.info("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip=" + ip);  
                }  
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("WL-Proxy-Client-IP");  
                if (logger.isInfoEnabled()) {  
                    logger.info("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip=" + ip);  
                }  
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("HTTP_CLIENT_IP");  
                if (logger.isInfoEnabled()) {  
                    logger.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip);  
                }  
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
                if (logger.isInfoEnabled()) {  
                    logger.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip=" + ip);  
                }  
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getRemoteAddr();  
                if (logger.isInfoEnabled()) {  
                    logger.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip);  
                }  
            }  
        } else if (ip.length() > 15) {  
            String[] ips = ip.split(",");  
            for (int index = 0; index < ips.length; index++) {  
                String strIp = (String) ips[index];  
                if (!("unknown".equalsIgnoreCase(strIp))) {  
                    ip = strIp;  
                    break;  
                }  
            }  
        }  
        return ip;  
    }  
}