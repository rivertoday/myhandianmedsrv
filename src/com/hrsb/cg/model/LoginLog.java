package com.hrsb.cg.model;

import java.util.Date;

public class LoginLog {
    private Integer id;

    private Integer userid;

    private Date createtime;

    private String ip;

    private Integer status;

    private Integer operator;

    private Date operatime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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