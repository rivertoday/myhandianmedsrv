package com.hrsb.cg.model;

import java.util.Date;
import java.util.List;

public class Role {
    private Integer id;

    private String rolename;

    private Integer parentid;

    private Integer status;

    private Integer moduleid;

    private Integer operator;

    private Date operatime;

    private Module moduleM;
    private Manager operatorM;
    public Manager getOperatorM() {
		return operatorM;
	}

	public void setOperatorM(Manager operatorM) {
		this.operatorM = operatorM;
	}

	public List<PermissionRole> getPr() {
		return pr;
	}

	public void setPr(List<PermissionRole> pr) {
		this.pr = pr;
	}

	private List<PermissionRole> pr;
    public Module getModuleM() {
		return moduleM;
	}

	public void setModuleM(Module moduleM) {
		this.moduleM = moduleM;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getStatus() {
        return status;
    }

    public Role setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getModuleid() {
        return moduleid;
    }

    public void setModuleid(Integer moduleid) {
        this.moduleid = moduleid;
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