package com.hrsb.cg.model;

import java.util.Date;

public class Banner {
    private Integer id;

    private String image;

    private String link;

    private Byte sorts;

    private Byte status;
    
    private Byte types;// 类型(1万方首页2新首页)

    private Integer managerId;

    private Date operateTime;

    private String spare1;

    private String spare2;

    private String spare3;
    
    private String typesStr;// 类型中文显示

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    public Byte getSorts() {
        return sorts;
    }

    public void setSorts(Byte sorts) {
        this.sorts = sorts;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getTypes() {
		return types;
	}

	public void setTypes(Byte types) {
		this.types = types;
	}

	public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getSpare1() {
        return spare1;
    }

    public void setSpare1(String spare1) {
        this.spare1 = spare1 == null ? null : spare1.trim();
    }

    public String getSpare2() {
        return spare2;
    }

    public void setSpare2(String spare2) {
        this.spare2 = spare2 == null ? null : spare2.trim();
    }

    public String getSpare3() {
        return spare3;
    }

    public void setSpare3(String spare3) {
        this.spare3 = spare3 == null ? null : spare3.trim();
    }

	public String getTypesStr() {
		this.typesStr = this.types == 1 ? "万方首页" :
						this.types == 2 ? "新首页" : "";
		return typesStr;
	}

	public void setTypesStr(String typesStr) {
		this.typesStr = typesStr;
	}
    
}