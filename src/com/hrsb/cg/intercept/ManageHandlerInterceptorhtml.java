package com.hrsb.cg.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hrsb.cg.dao.UserLoginMapper;
import com.hrsb.cg.model.UserLogin;
import com.hrsb.cg.util.Const;
public class ManageHandlerInterceptorhtml extends HandlerInterceptorAdapter {
	
	@Autowired
	UserLoginMapper userLoginMapper;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		UserLogin userLogin = (UserLogin) session.getAttribute(Const.SESSION_CLIENT_USER);		
		if (null == userLogin) {
			userLogin = (UserLogin) session.getAttribute(Const.SESSION_PHONE_USER);
		}
		
		String path = request.getServletPath();		
		System.out.println(path);
		System.out.println(path.matches(Const.NO_INTERCEPTOR_PATH_client));
		if(path.matches(Const.NO_INTERCEPTOR_PATH_client) || path.matches(Const.NO_INTERCEPTOR_PAGE)){
			return true;
		} else {
			if (userLogin == null) {
				if (path.startsWith("/phoneweb")) {
					response.sendRedirect("/redirect.html?path=phoneweb/login");
				} else {
					response.sendRedirect("/index.html");
				}
				
				return false;
			}
		}
		
		return true;
	}
}
