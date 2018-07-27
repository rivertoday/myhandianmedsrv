package com.hrsb.cg.model;

import java.util.Date;

public class Manager {
    private Integer id;

    private String username;

    private String password;

    private String name;

    private String headimg;

    private String status;

    private Date createtime;
    
    private Integer moduleId;
    private Module moduleM;
    private Integer operator;
    private Date operatime;
    private String phone;
    private String idkey;
    public String getIdkey() {
		return idkey;
	}

	public void setIdkey(String idkey) {
		this.idkey = idkey;
	}

	private Manager operatorM;
	public Manager getOperatorM() {
		return operatorM;
	}

	public void setOperatorM(Manager operatorM) {
		this.operatorM = operatorM;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

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

    public String getUsername() {
        return username;
    }

    public Manager setUsername(String username) {
        this.username = username == null ? null : username.trim();
        return this;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg == null ? null : headimg.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}


}