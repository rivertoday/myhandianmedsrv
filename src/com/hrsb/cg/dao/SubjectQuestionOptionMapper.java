package com.hrsb.cg.dao;

import java.util.List;

import com.hrsb.cg.model.SubjectQuestionOption;
import com.hrsb.cg.util.Page;

public interface SubjectQuestionOptionMapper {
	
    int deleteByPrimaryKey(Long id);

    int insertSelective(SubjectQuestionOption record);

    SubjectQuestionOption selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SubjectQuestionOption record);

	List<SubjectQuestionOption> selectByPage(Page<SubjectQuestionOption> page);

	List<SubjectQuestionOption> selectByQuestionId(Long questionId);
	
}