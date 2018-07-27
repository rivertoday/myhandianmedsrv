package com.hrsb.cg.util;
//返回AJAX
public class ReturnAjax {
	private String statusCode;
	private String message;
	private String tabid;
	private boolean closeCurrent;
	private String forward;
	private String forwardConfirm;
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public ReturnAjax(String statusCode, String message, String tabid,
			boolean closeCurrent, String forward, String forwardConfirm) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.tabid = tabid;
		this.closeCurrent = closeCurrent;
		this.forward = forward;
		this.forwardConfirm = forwardConfirm;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTabid() {
		return tabid;
	}
	public void setTabid(String tabid) {
		this.tabid = tabid;
	}
	public boolean getCloseCurrent() {
		return closeCurrent;
	}
	public void setCloseCurrent(boolean closeCurrent) {
		this.closeCurrent = closeCurrent;
	}
	public String getForward() {
		return forward;
	}
	public void setForward(String forward) {
		this.forward = forward;
	}
	public String getForwardConfirm() {
		return forwardConfirm;
	}
	public void setForwardConfirm(String forwardConfirm) {
		this.forwardConfirm = forwardConfirm;
	}
}
