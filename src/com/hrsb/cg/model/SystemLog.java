package com.hrsb.cg.model;

import java.util.Date;

public class SystemLog {
    private Integer id;

    private String content;

    private String title;

    private Integer userid;

    private Integer operator;

    private Date operatime;

    private Integer status;
    private Manager operatorM;
    public Manager getOperatorM() {
		return operatorM;
	}

	public void setOperatorM(Manager operatorM) {
		this.operatorM = operatorM;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public SystemLog(String content, String title, Integer operator,
			Date operatime, Integer status) {
		super();
		this.content = content;
		this.title = title;
		this.operator = operator;
		this.operatime = operatime;
		this.status = status;
	}

	public SystemLog() {
		super();
	}
}