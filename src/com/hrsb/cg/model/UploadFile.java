package com.hrsb.cg.model;

import java.util.Date;

public class UploadFile {

	private Long id;

	private String title;
    private String submitter;
    private String phone;
    private String mail;
    private String downloadName;
    private String downloadUrl;
    private Date operate_time;
    
    private String spec1;
    private String spec2;
    private String spec3;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getSpec1() {
		return spec1;
	}
	public void setSpec1(String spec1) {
		this.spec1 = spec1;
	}
	public String getSpec2() {
		return spec2;
	}
	public void setSpec2(String spec2) {
		this.spec2 = spec2;
	}
	public String getSpec3() {
		return spec3;
	}
	public void setSpec3(String spec3) {
		this.spec3 = spec3;
	}
	public String getSubmitter() {
		return submitter;
	}
	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getDownloadName() {
		return downloadName;
	}
	public void setDownloadName(String downloadName) {
		this.downloadName = downloadName;
	}
	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getOperate_time() {
		return operate_time;
	}
	public void setOperate_time(Date operate_time) {
		this.operate_time = operate_time;
	}
    
    
    
}
