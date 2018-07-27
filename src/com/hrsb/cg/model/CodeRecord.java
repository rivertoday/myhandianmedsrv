package com.hrsb.cg.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 验证码记录表
 * @author app001
 *
 */
public class CodeRecord implements Serializable {
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String phone;// 手机号

    private String code;// 验证码

    private Date effectiveTime;// 有效时间

    private String spare1;// 备用字段1

    private String spare2;// 备用字段2

    private String spare3;// 备用字段3

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
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
}