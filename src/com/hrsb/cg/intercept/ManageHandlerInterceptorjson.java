package com.hrsb.cg.intercept;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.gson.JsonObject;
import com.hrsb.cg.dao.UserAuthMapper;
import com.hrsb.cg.model.UserAuth;

public class ManageHandlerInterceptorjson extends HandlerInterceptorAdapter {
	
	@Autowired
	UserAuthMapper userAuthMapper;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//cancel the authentication processing
		return true;		
		
		/*request.setCharacterEncoding("utf-8");
		String errStr = null;
		
		if(handler.getClass().isAssignableFrom(HandlerMethod.class)){
            D1apiAuth authPassport = ((HandlerMethod) handler).getMethodAnnotation(D1apiAuth.class);
            
            //没有声明需要权限,或者声明不验证权限
            if(authPassport==null){
                return true;
            }else{                
                //在这里实现自己的权限验证逻辑            	
            	String token = request.getHeader("token");
            	System.out.println("Http request header token: "+token);
            	
            	UserAuth myAuth = null;
            	if (null != token && !token.isEmpty()) {
            		myAuth = userAuthMapper.selectByToken(token);
            	}
            	
                if(myAuth==null){//如果token不存在，说明该用户尚未登录                	
                	errStr = "鉴权校验未通过！"; 
                	JsonObject retObj = new JsonObject();
        			retObj.addProperty("success", "0");
        			retObj.addProperty("errorMsg", errStr);
        			response.getWriter().print(retObj);
        			//App前端代码拿到这样的返回数据，需要切换页面到登录页面上去
        			
                    System.out.println(errStr);
                    return false;
                }else{
                	//否则还是需要检查token是否已经过期
                	String phone = myAuth.getPhone();
                	Date effectTime = myAuth.getEffectiveTime();
                	
                	if (effectTime.after(new Date())) {
                		//token仍然有效
                		return true;
                	}else {
                		errStr = phone + "的token已经过期！";
            			JsonObject retObj = new JsonObject();
            			retObj.addProperty("success", "0");
            			retObj.addProperty("errorMsg", errStr);
            			response.getWriter().print(retObj);
            			
                        System.out.println(errStr);
                        return false;
                	}
                }       
            }
        }else{
            return true;
        }*/
	}
}
