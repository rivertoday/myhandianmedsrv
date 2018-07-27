package com.hrsb.cg.dto;

import com.hrsb.cg.model.Manager;

public class PremissionItem extends Manager{
	
	
	private String rolename;
	
	private String premissionTitle;
	
	private Integer premissionId;


	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getPremissionTitle() {
		return premissionTitle;
	}

	public void setPremissionTitle(String premissionTitle) {
		this.premissionTitle = premissionTitle;
	}

	public Integer getPremissionId() {
		return premissionId;
	}

	public void setPremissionId(Integer premissionId) {
		this.premissionId = premissionId;
	}

	
}
