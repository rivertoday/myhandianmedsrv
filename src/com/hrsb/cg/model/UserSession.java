package com.hrsb.cg.model;

import java.util.Date;

public class UserSession {
	
	private String addr;

	private String sessid;

	private String username;
	
	private Date createDate;

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getSessid() {
		return sessid;
	}

	public void setSessid(String sessid) {
		this.sessid = sessid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	

}
