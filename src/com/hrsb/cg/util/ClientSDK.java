package com.hrsb.cg.util;

import java.net.URLEncoder;

public class ClientSDK {
	private WebClient webclient = new WebClient();
	private String baseurl1 = "http://q.hl95.com:8061/";
	private String baseurl = "http://api.itrigo.net/";
	/**
	 * 
	 * @param username 用户名
	 * @param password 密码
	 * @param phone 发送手机号，如果发送多个手机号可用逗号隔开，每一批号码最多20个
	 * @param content 发送内容
	 * @return 如果返回值为0，则说明发送成功，如果返回其他值，则说明发送失败
	 * @throws Exception
	 */
	public String sendSms(String username, String password, String phone,
			String content) throws Exception {
		return webclient.doGet(baseurl + "mt.jsp?cpName=" + username
				+ "&cpPwd=" + password + "&phones=" + phone + "&msg="
				+ URLEncoder.encode(content, "gbk"),"gbk");
	}
	
	//鸿联
	public String sendSms2(String username, String password,String epid, String phone,
			String message) throws Exception {
		return webclient.doGet(baseurl1 + "?username=" + username
				+ "&password=" + password + "&epid=" +epid+ "&phone=" + phone + "&message="
				+ URLEncoder.encode(message, "gb2312"),"gb2312");
	}
	
	public String sendYY(String username, String password, String phone,
			String content) throws Exception {
		return webclient.doGet(baseurl + "yysms.jsp?cpName=" + username
				+ "&cpPwd=" + password + "&phones=" + phone + "&msg="
				+ URLEncoder.encode(content, "gbk"),"gbk");
	}


}
