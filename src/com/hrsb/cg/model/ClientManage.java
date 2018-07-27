package com.hrsb.cg.model;

import java.util.Date;

public class ClientManage {
    private Integer id;

    private String title;

    private String image;

    private String linkurl;

    private Integer sort;

    private Integer types;

    private Integer operator;

    private Date operatdate;
    
    //extend
    private String operatorName;
    //extend

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getLinkurl() {
        return linkurl;
    }

    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl == null ? null : linkurl.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getTypes() {
        return types;
    }

    public void setTypes(Integer types) {
        this.types = types;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    public Date getOperatdate() {
        return operatdate;
    }

    public void setOperatdate(Date operatdate) {
        this.operatdate = operatdate;
    }

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String toString() {
		return "ClientManage [id=" + id + ", title=" + title + ", image="
				+ image + ", linkurl=" + linkurl + ", sort=" + sort
				+ ", types=" + types + ", operator=" + operator
				+ ", operatdate=" + operatdate + "]";
	}
    
}