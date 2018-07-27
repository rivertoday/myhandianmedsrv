package com.hrsb.cg.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseUtil{
	private String message = null;
	private String statusCode = "200";  //{"statusCode":"300", "message":"操作失败" statusCode":"200", "message":"操作成功",  statusCode":"301, "message":"会话超时"}
	private String forward = null;// 只有callbackType="forward"时需要forward值
	private String navTabId=null; //服务器转回navTabId可以把那个navTab标记为tabidoadFlag=1,  下次切换到那个navTab时会重新载入内容
	private String tabid=null;
	private String callbackType=null; //callbackType如果是closeCurrent就会关闭当前tab
	private String confirmMsg = "";
	private boolean closeCurrent;
	public boolean isCloseCurrent() {
		return closeCurrent;
	}

	public void setCloseCurrent(boolean closeCurrent) {
		this.closeCurrent = closeCurrent;
	}

	public String getNavTabId() {
		return navTabId;
	}

	public void setNavTabId(String navTabId) {
		this.navTabId = navTabId;
	}

	public String getTabid() {
		return tabid;
	}

	public void setTabid(String tabid) {
		this.tabid = tabid;
	}

	public String getCallbackType() {
		return callbackType;
	}

	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}

	public String getConfirmMsg() {
		return confirmMsg;
	}

	public void setConfirmMsg(String confirmMsg) {
		this.confirmMsg = confirmMsg;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getForward() {
		return forward;
	}

	public void setForward(String forward) {
		this.forward = forward;
	}

	public BaseUtil(String statusCode,String message, String forward,
			String navTabId, String tabid, String callbackType,String confirmMsg) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.forward = forward;
		this.navTabId = navTabId;
		this.tabid = tabid;
		this.callbackType = callbackType;
		this.confirmMsg = confirmMsg;
	}

	public BaseUtil(String statusCode,String message, String forward,
			String navTabId, String tabid, String callbackType,
			String confirmMsg, boolean closeCurrent) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.forward = forward;
		this.navTabId = navTabId;
		this.tabid = tabid;
		this.callbackType = callbackType;
		this.confirmMsg = confirmMsg;
		this.closeCurrent = closeCurrent;
	}
	public static void main(String[] args) {
		System.out.println("he abc Certainly!Hes loves her!".matches("\\b((?!abc)\\w)+\\b"));
	}
}
