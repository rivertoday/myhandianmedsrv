package com.hrsb.cg.model;

import java.util.Date;

public class ManagerRole {
    private Integer id;

    private Integer manageid;

    private Integer roleid;

    private Integer operator;

    private Date operatime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getManageid() {
        return manageid;
    }

    public void setManageid(Integer manageid) {
        this.manageid = manageid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
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