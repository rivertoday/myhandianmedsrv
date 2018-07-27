package com.hrsb.cg.model;

import java.util.Date;
import java.util.List;

public class Permission {
	private Integer id;

	private String title;

	private String detail;

	private Integer status;

	private Integer parentid;

	private Integer operator;

	private Date operatime;

	private Integer ismenu;

	List<Permission> chirlds;

	private Integer sort;// 排序

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public List<Permission> getChirlds() {
		return chirlds;
	}

	public void setChirlds(List<Permission> chirlds) {
		this.chirlds = chirlds;
	}

	public Integer getIsmenu() {
		return ismenu;
	}

	public void setIsmenu(Integer ismenu) {
		this.ismenu = ismenu;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail == null ? null : detail.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public Integer getOperator() {
		return operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}

	public Date getOperatime() {
		return operatime;
	}

	public void setOperatime(Date operatime) {
		this.operatime = operatime;
	}
}