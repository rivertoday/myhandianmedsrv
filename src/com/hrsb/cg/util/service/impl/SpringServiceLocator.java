package com.hrsb.cg.util.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.hrsb.cg.util.service.ServiceLocator;

/**
 * 利用Spring实现的系统服务定位
 * 
 */
@Service("serviceLocator")
public class SpringServiceLocator implements ServiceLocator{
	
	@Autowired
	private ApplicationContext ctx;
	
	public ApplicationContext getCtx() {
		return ctx;
	}

	public void setCtx(ApplicationContext ctx) {
		this.ctx = ctx;
	}

	@Override
	public Object getService(String serviceName) {
		return ctx.getBean(serviceName);
	}
}
