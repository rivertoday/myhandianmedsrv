package com.hrsb.cg.model;

import java.util.Date;

public class Menu {
    private Integer id;

    private String title;

    private String logoimg;

    private String logohimg;

    private Date createtime;

    private Integer isshow;

    private Integer sort;

    private String linkurl;

    private String pagekeys;

    private String pagedescription;

    private Integer parentid;

    private Integer isindex;

    private Integer istop;

    private Integer isrecommend;

    private String imgalt;
    private String content;

    private String description;
    private String standbyfield1;
    private String standbyfield2;
    private String standbyfield3;
    private String standbyfield4;
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

    public String getLogoimg() {
        return logoimg;
    }

    public void setLogoimg(String logoimg) {
        this.logoimg = logoimg == null ? null : logoimg.trim();
    }

    public String getLogohimg() {
        return logohimg;
    }

    public void setLogohimg(String logohimg) {
        this.logohimg = logohimg == null ? null : logohimg.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getIsshow() {
        return isshow;
    }

    public void setIsshow(Integer isshow) {
        this.isshow = isshow;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getLinkurl() {
        return linkurl;
    }

    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl == null ? null : linkurl.trim();
    }

    public String getPagekeys() {
        return pagekeys;
    }

    public void setPagekeys(String pagekeys) {
        this.pagekeys = pagekeys == null ? null : pagekeys.trim();
    }

    public String getPagedescription() {
        return pagedescription;
    }

    public void setPagedescription(String pagedescription) {
        this.pagedescription = pagedescription == null ? null : pagedescription.trim();
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getIsindex() {
        return isindex;
    }

    public void setIsindex(Integer isindex) {
        this.isindex = isindex;
    }

    public Integer getIstop() {
        return istop;
    }

    public void setIstop(Integer istop) {
        this.istop = istop;
    }

    public Integer getIsrecommend() {
        return isrecommend;
    }

    public void setIsrecommend(Integer isrecommend) {
        this.isrecommend = isrecommend;
    }

    public String getImgalt() {
        return imgalt;
    }

    public void setImgalt(String imgalt) {
        this.imgalt = imgalt == null ? null : imgalt.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

	public String getStandbyfield1() {
		return standbyfield1;
	}

	public void setStandbyfield1(String standbyfield1) {
		this.standbyfield1 = standbyfield1;
	}

	public String getStandbyfield2() {
		return standbyfield2;
	}

	public void setStandbyfield2(String standbyfield2) {
		this.standbyfield2 = standbyfield2;
	}

	public String getStandbyfield3() {
		return standbyfield3;
	}

	public void setStandbyfield3(String standbyfield3) {
		this.standbyfield3 = standbyfield3;
	}

	public String getStandbyfield4() {
		return standbyfield4;
	}

	public void setStandbyfield4(String standbyfield4) {
		this.standbyfield4 = standbyfield4;
	}
}