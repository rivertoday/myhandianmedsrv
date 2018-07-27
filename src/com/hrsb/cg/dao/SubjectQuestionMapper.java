package com.hrsb.cg.dao;

import java.util.List;

import com.hrsb.cg.model.SubjectQuestion;
import com.hrsb.cg.util.Page;

public interface SubjectQuestionMapper {
	
    int deleteByPrimaryKey(Long id);

    int insertSelective(SubjectQuestion record);

    SubjectQuestion selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SubjectQuestion record);

	List<SubjectQuestion> selectByPage(Page<SubjectQuestion> page);

	List<SubjectQuestion> selectQuestionBySubjectId(Long subjectId);
	
}