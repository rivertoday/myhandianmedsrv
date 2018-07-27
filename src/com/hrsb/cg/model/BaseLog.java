package com.hrsb.cg.model;

import java.util.Date;

public class BaseLog {
    private Integer id;

    private String operator;

    private Date operatime;

    private String opreatype;

    private String target;

    private String content;

    private Integer status;

    private Integer type;
    
    private Manager operatorM;
    public Manager getOperatorM() {
		return operatorM;
	}

	public void setOperatorM(Manager operatorM) {
		this.operatorM = operatorM;
	}

	public BaseLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @param operator 操作人编号
	 * @param operatime 操作时间
	 * @param opreatype 操作类型
	 * @param target 操作目标编号
	 * @param content 操作内容
	 * @param status 操作状态
	 * @param type 类型（0为后台1为前台）
	 */
	public BaseLog(String operator, Date operatime, String opreatype,
			String target, String content, Integer status, Integer type) {
		super();
		this.operator = operator;
		this.operatime = operatime;
		this.opreatype = opreatype;
		this.target = target;
		this.content = content;
		this.status = status;
		this.type = type;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getOperatime() {
        return operatime;
    }

    public void setOperatime(Date operatime) {
        this.operatime = operatime;
    }

    public String getOpreatype() {
        return opreatype;
    }

    public void setOpreatype(String opreatype) {
        this.opreatype = opreatype == null ? null : opreatype.trim();
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public BaseLog setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}