package com.hrsb.cg.model;

import java.util.Date;

public class Module {
    private Integer id;

    private String modulename;

    private Integer parentid;

    private Integer status;

    private Integer groupid;

    private Integer operator;
    
    private Date operatime;
    private String modulecode;//部门编号
    
    private Manager operatorM;
    private Module parentM;
    public Module getParentM() {
		return parentM;
	}

	public void setParentM(Module parentM) {
		this.parentM = parentM;
	}

	public Manager getOperatorM() {
		return operatorM;
	}

	public void setOperatorM(Manager operatorM) {
		this.operatorM = operatorM;
	}

	public String getModulecode() {
		return modulecode;
	}

	public void setModulecode(String modulecode) {
		this.modulecode = modulecode;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModulename() {
        return modulename;
    }

    public void setModulename(String modulename) {
        this.modulename = modulename == null ? null : modulename.trim();
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

    public void setStatus(Integer status) {
        this.status = status;
    }

	public Integer getGroupid() {
		return groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
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