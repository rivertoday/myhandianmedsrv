package com.hrsb.cg.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;

/**
 * @author  jing
 */
public class Const {
	// 短信平台账号
	public static final String server_username = "handian";
	// 短信平台密码
	public static final String server_password = "123456";
	// 加密串
	public final static String ENCODE_KEY = "1234567890123456";
	// 系统错误
	public final static String SYSERROR = "系统错误";
	// 验证码有效时间10分
	public final static int MINUTE = 60;
	// 会话有效时间60分, added by JIANG
	public final static int SESSMINUTE = 60;
	// 每页显示条数
	public static final String PAGESIZE = "10";
	// 可以下载次数
	public static final int DOWNLOADCOUNT = 5;
	// 万方用户名
	public static final String WF_USERNAME = "handian001";
	// 万方密码
	public static final String WF_PASSWORD = "handianyigu";
	// 万方Token
	public static final String WF_TOKEN = "Handian";
	// 万方Key
	public static final String WF_KEY = "handian020171122";
	// 万方URL
	public static final String WF_URL = "http://api.med.wanfangdata.com.cn";
	// 万方检索
	public static final String WF_SEARCH = WF_URL + "/Article/Search?token=" + WF_TOKEN;
	// 万方详情
	public static final String WF_DETAIL = WF_URL + "/Article/Detail?token=" + WF_TOKEN;
	// 万方下载
	public static final String WF_DOWNLOAD = WF_URL + "/Article/Download?token=" + WF_TOKEN;
	//友盟安卓推送Appkey
	public static final String uMengAndroidappkey = "56f24c4267e58eab0c001149";
	//友盟安卓推送appMasterSecret
	public static final String uMengAndroidappMasterSecret = "srxocp4bp0fnrheevtmvh1hzmcffnnbx";
	//友盟IOS推送Appkey
	public static final String uMengIOSappkey = "5717403ee0f55a74df000fd0";
	//友盟IOS推送appMasterSecret
	public static final String uMengIOSappMasterSecret = "5zvaugizshtn3qjfcof40bt43y4evecu";
	// 默认男头像
	public static final String DEFAULT_M = "/client/images/sex_m.png";
	// 默认女头像
	public static final String DEFAULT_W = "/client/images/sex_w.png";
	// 默认保密头像
	public static final String DEFAULT_N = "/client/images/sex_n.png";
	
	public static final String COOKIE_USER_NAME="useremail";
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";
	public static final String SESSION_USER = "sessionUser";
	// PC用户端session
	public static final String SESSION_CLIENT_USER = "clientuser";
	// 手机站用户端session
	public static final String SESSION_PHONE_USER = "phoneuser";
	public static final String SESSION_MANAGER = "sessionManager";
	public static final String SESSION_MANAGER_ROLE = "sessionManagerRole";
	public static final String NO_INTERCEPTOR_PATH_MANAGE = ".*/((manage/login)|(manage/logout)|(manage/index)|(file_upload)|(upload/file6)).*";	//不对匹配该值的访问路径拦截（正则）後台, updated by JIANG for upload file6
	public static final String NO_INTERCEPTOR_PATH_client = ".*/((about)|(secret)|(index)|(registerUI)|(existence)|" +
			"(register)|(code_check)|(code)|(login)|(password)|(password)|(upload/cfile)|(redirect)|(city)|(county)|(distill)|" +//updated by JIANG for city and county and file upload
			"(subject/detail)|(subject/result)|(product/detail)|(literature/detail)|(phoneweb/literature/detail)|(phoneweb/subject/detail)|(phoneweb/subject/result)|" +
			"(phoneweb/product/detail)|(phoneweb/user/forgetPwdUI)|(phoneweb.agreement2)).*";	//不对匹配该值的访问路径拦截（正则）前台
	public static final String NO_INTERCEPTOR_PAGE = ".*/((client/index)|(client/mycollection)|(distill)).*";	//不对匹配该值的访问路径拦截（正则）前台
	public static final String CHECK_CODE = "checkcode";
	public static final String CLIENT_CHECK_CODE = "clientcheckcode";
	public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(logout)|(code)).*";	//不对匹配该值的访问路径拦截（正则）後台
	public static ApplicationContext WEB_APP_CONTEXT = null; //该值会在web容器启动时由WebAppContextListener初始化
	public static ServletContext SERVLET_CONTEXT = null;
	public static final String ManagerName = "系统管理员";//系统管理员名称
	
	public static final int ManagerId = 1;//系统管理员ID
	
	/** 
	 * 用户和Session绑定关系 
	 */  
	public static final Map<String, HttpSession> USER_SESSION=new HashMap<String, HttpSession>();
	
	
	/** 
	 * seeionId和用户的绑定关系 
	 */  
	public static final Map<String, String> SESSIONID_USER=new HashMap<String, String>();  
	
}
