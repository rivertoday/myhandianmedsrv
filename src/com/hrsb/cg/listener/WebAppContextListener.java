package com.hrsb.cg.listener;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hrsb.cg.util.Const;
import com.hrsb.cg.util.DateUtil;

/**
 * 服务监听
 * 可用来初始化一些全局静态资源
 * 
 * @author 王启靖
 *
 */
public class WebAppContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
	}

	public void contextInitialized(ServletContextEvent event) {
		Const.WEB_APP_CONTEXT = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		Const.SERVLET_CONTEXT = event.getServletContext();
		System.out.println("Application启动:"+new Date());
		System.out.println("applicationContext初始化："+Const.WEB_APP_CONTEXT==null?"失败":"成功");
		Timer t = new Timer();
		Calendar  cad = Calendar.getInstance();
		cad.set(cad.get(Calendar.YEAR), cad.get(Calendar.MONTH), cad.get(Calendar.DATE)+1, 0, 0, 0);
		Date ad = cad.getTime();
		TimerTask tt = new TimerTask() {
			@Override
			public void run() {
				Const.SERVLET_CONTEXT.setAttribute("checkCodes", null);
			}
		};
		t.schedule(tt, ad, 24*60*60*1000);
		//System.out.println("========获取Spring WebApplicationContext");
	}
	public static void main(String[] args) {
		
	}
}
