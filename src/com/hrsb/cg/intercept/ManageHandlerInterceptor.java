package com.hrsb.cg.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hrsb.cg.model.Manager;
import com.hrsb.cg.util.Const;

public class ManageHandlerInterceptor extends HandlerInterceptorAdapter{
	public String[] allowUrls;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		String path = request.getServletPath();
		String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");    
        System.out.println(requestUrl);  
        if(null != allowUrls && allowUrls.length>=1)  
            for(String url : allowUrls) {    
                if(requestUrl.contains(url)) {    
                    return true;    
                }    
            }
		if(path.matches(Const.NO_INTERCEPTOR_PATH_MANAGE)){
			return true;
		}else{
			HttpSession session = request.getSession();
			Manager manager = (Manager)session.getAttribute(Const.SESSION_MANAGER);
			if(manager!=null){
				return true;
			}else{
				response.sendRedirect(request.getContextPath()+"/manage/login.im");
				return false;
			}
		}
	}
	public String[] getAllowUrls() {
		return allowUrls;
	}
	public void setAllowUrls(String[] allowUrls) {
		this.allowUrls = allowUrls;
	}
	
}
