package com.hrsb.cg.controller.util;

import java.util.Date;


import com.hrsb.cg.model.BaseLog;
import com.hrsb.cg.service.BaseLogService;
import com.hrsb.cg.util.Const;
/**
 * 操作记录添加
 * @author 王启靖
 *
 */
public class BaseLogsUtil {
	static BaseLogService baseLogService;
	
	public BaseLogsUtil() {
		super();
		if(baseLogService==null){
			baseLogService =(BaseLogService) Const.WEB_APP_CONTEXT.getBean("baseLogService");
		}
	}
	/**
	 * 添加操作记录
	 * @param operator 操作人编号
	 * @param operatime 操作时间
	 * @param operatype 操作类型
	 * @param target 操作目标编号
	 * @param status 操作状态
	 * @param type 类型（0后台，1前台）
	 * @param content 操作内容
	 */
	public static void insertBaseLog(String operator,String operatype,String target,String content,Integer status,Integer type){
		new BaseLogsUtil().baseLogService.insertSelective(new BaseLog(operator, new Date(), operatype, target, content, status, type));
	}
	/**
	 * 添加操作记录
	 * @param baselog
	 */
	public static void insertBaseLog(BaseLog baselog){
		new BaseLogsUtil().baseLogService.insertSelective(baselog);
	}
}
