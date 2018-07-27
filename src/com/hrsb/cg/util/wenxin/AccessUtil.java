package com.hrsb.cg.util.wenxin;
import java.text.SimpleDateFormat;
import java.util.Date;
public class AccessUtil {
	private static String instance = null;//tocken
	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static java.util.Date begin;
	public static java.util.Date now;
	static String appId = "";
	// 第三方用户唯一凭证密钥
	static String appSecret = "";

	public static void emptyaccess(){
		instance=null;
		getaccess();
	}
	public static String geteemptyaccess(){
		instance=null;
		return getaccess();
	}
	public static String getaccess() {

		if (instance == null) { // 第一次使用
			// 在此处获取accesstoket，并保存到instance常量里面
			//获取初始时间
			begin=new Date();
			AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
			instance=at.getToken();

			//返回token
		} else {
			//先获取当前时间，然后跟初始时间相比，看是否超过了7200秒
			now=new Date();
			long l = now.getTime() - begin.getTime();
			if (l/1000>500) {
				AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
				instance=at.getToken();
				begin=new Date();
				//返回token
			}
		}
		return instance;
	}
	public static void main(String[] args) {
		System.out.println(getaccess());
	}
}