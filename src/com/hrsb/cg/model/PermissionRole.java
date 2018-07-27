package com.hrsb.cg.model;

import java.util.Date;

public class PermissionRole {
    private Integer id;

    private Integer roleid;

    private Integer permissionid;

    private Integer operator;

    private Date operatime;
    
    private Permission permission;
    public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getPermissionid() {
        return permissionid;
    }

    public void setPermissionid(Integer permissionid) {
        this.permissionid = permissionid;
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