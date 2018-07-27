package com.hrsb.cg.dao;

import java.util.List;

import com.hrsb.cg.model.SubjectResult;
import com.hrsb.cg.util.Page;

public interface SubjectResultMapper {
	
    int deleteByPrimaryKey(Long id);

    int insertSelective(SubjectResult record);

    SubjectResult selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SubjectResult record);

	List<SubjectResult> selectByPage(Page<SubjectResult> page);

	SubjectResult selectBySubjectIdBetweenScore(long subjectId, int score);

	SubjectResult selectBySubjectIdAndIsCorrect(long subjectId, int i);

	SubjectResult selectBySubjectIdAndQuestionId(long subjectId, long l);
	
}