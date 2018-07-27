package com.hrsb.cg.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 自测题库
 * @author app001
 *
 */
public class SubjectQuestion implements Serializable {
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long subjectId;// 自测id

    private String question;// 问题

    private Integer score;// 分数

    private Byte types;// 类型(1原始8分2原始7分3累积分4是否5一题一答)

    private Integer managerId;// 操作人

    private Date operateTime;// 操作时间

    private String spare1;// 备用字段1

    private String spare2;// 备用字段2

    private String spare3;// 备用字段3
    
    private List<SubjectQuestionOption> subjectQuestionOptions;// 选项

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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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

	public List<SubjectQuestionOption> getSubjectQuestionOptions() {
		return subjectQuestionOptions;
	}

	public void setSubjectQuestionOptions(
			List<SubjectQuestionOption> subjectQuestionOptions) {
		this.subjectQuestionOptions = subjectQuestionOptions;
	}
    
}