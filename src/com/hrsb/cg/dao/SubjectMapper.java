package com.hrsb.cg.dao;

import java.util.List;

import com.hrsb.cg.model.Subject;
import com.hrsb.cg.util.Page;

public interface SubjectMapper {
	
    int deleteByPrimaryKey(Long id);

    int insertSelective(Subject record);

    Subject selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Subject record);

	List<Subject> selectByPage(Page<Subject> page);
	
}