package com.hrsb.cg.model;

import java.util.Date;

//Added by JIANG
public class UserAuth {
	private Long id;

    private String phone;

    private String token;
    
    private Date createtime;// token创建时间
    
    private Date updatetime;// token更新时间

    private Date effectivetime;// token有效时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }
    
    public Date getCreateTime() {
        return createtime;
    }

    public void setCreateTime(Date createT) {
        this.createtime = createT;
    }
    
    public Date getUpdateTime() {
        return updatetime;
    }

    public void setUpdateTime(Date updateT) {
        this.updatetime = updateT;
    }

    public Date getEffectiveTime() {
        return effectivetime;
    }

    public void setEffectiveTime(Date effectiveT) {
        this.effectivetime = effectiveT;
    }
}
