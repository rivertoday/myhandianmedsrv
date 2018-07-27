package com.hrsb.cg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hrsb.cg.dao.SubjectMapper;
import com.hrsb.cg.dao.SubjectQuestionMapper;
import com.hrsb.cg.dao.SubjectQuestionOptionMapper;
import com.hrsb.cg.model.Subject;
import com.hrsb.cg.model.SubjectQuestion;
import com.hrsb.cg.model.SubjectQuestionOption;
import com.hrsb.cg.service.SubjectService;
import com.hrsb.cg.util.Page;

@Service(value = "subjectService")
public class SubjectServiceImpl implements SubjectService {
	
	@Autowired
	SubjectMapper subjectMapper;
	
	@Autowired
	SubjectQuestionMapper subjectQuestionMapper;
	
	@Autowired
	SubjectQuestionOptionMapper subjectQuestionOptionMapper;

	@Override
	public Subject getById(long id) {
		return subjectMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SubjectQuestion> getQuestionBySubject(Subject subject) {
		List<SubjectQuestion> subjectQuestions = subjectQuestionMapper.selectQuestionBySubjectId(subject.getId());
		
		if (!CollectionUtils.isEmpty(subjectQuestions) && (subject.getTypes() == 1 || subject.getTypes() == 2)) {
			for (SubjectQuestion subjectQuestion : subjectQuestions) {
				List<SubjectQuestionOption> subjectQuestionOptions = subjectQuestionOptionMapper.selectByQuestionId(subjectQuestion.getId());
				subjectQuestion.setSubjectQuestionOptions(subjectQuestionOptions);
			}
		}
		
		return subjectQuestions;
	}

	@Override
	public List<Subject> getByPage(Page<Subject> page) {
		return subjectMapper.selectByPage(page);
	}

}
