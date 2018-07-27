package com.hrsb.cg.service;

import java.util.List;

import com.hrsb.cg.model.Subject;
import com.hrsb.cg.model.SubjectQuestion;
import com.hrsb.cg.util.Page;

public interface SubjectService {

	Subject getById(long id);

	List<SubjectQuestion> getQuestionBySubject(Subject subject);

	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	List<Subject> getByPage(Page<Subject> page);

}
