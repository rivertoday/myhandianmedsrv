package com.hrsb.cg.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 自测结果
 * @author app001
 *
 */
public class SubjectResult implements Serializable {
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long subjectId;// 自测id

    private Long questionId;// 问题id

    private Byte isCorrect;// 是否正确结果(1是0否)

    private String name;// 名称

    private Integer scoreSmall;// 小分数

    private Integer scoreBig;// 大分数

    private Integer managerId;// 操作人

    private Date operateTime;// 操作时间

    private String spare1;// 备用字段1

    private String spare2;// 备用字段2

    private String spare3;// 备用字段3
    
    private String instruction;// 说明
    
    private String suggestion;// 建议

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Byte getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Byte isCorrect) {
        this.isCorrect = isCorrect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getScoreSmall() {
        return scoreSmall;
    }

    public void setScoreSmall(Integer scoreSmall) {
        this.scoreSmall = scoreSmall;
    }

    public Integer getScoreBig() {
        return scoreBig;
    }

    public void setScoreBig(Integer scoreBig) {
        this.scoreBig = scoreBig;
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

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
    
}