package com.hrsb.cg.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 健康自测
 * @author app001
 *
 */
public class Subject implements Serializable {
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String title;// 标题
    
    private String instruction;// 说明

    private String image;// 图片

    private Byte types;// 类型(1原始8分2原始7分3累积分4多选一结果5一题一答案)

    private Integer managerId;// 操作人

    private Date operateTime;// 操作时间

    private String spare1;// 备用字段1

    private String spare2;// 备用字段2

    private String spare3;// 备用字段3
    
    private String typesStr;// 类型中文

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Byte getTypes() {
        return types;
    }

    public void setTypes(Byte types) {
        this.types = types;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
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

	public String getTypesStr() {
		this.typesStr = this.types == 1 ? "原始8分" :
						this.types == 2 ? "原始7分" :
						this.types == 3 ? "累积分" :
						this.types == 4 ? "多选一结果" :
						this.types == 5 ? "一题一答案" : "";
		return typesStr;
	}

	public void setTypesStr(String typesStr) {
		this.typesStr = typesStr;
	}
    
}