package com.hrsb.cg.controller.client;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hrsb.cg.dao.SubjectQuestionMapper;
import com.hrsb.cg.dao.SubjectQuestionOptionMapper;
import com.hrsb.cg.dao.SubjectResultMapper;
import com.hrsb.cg.model.Subject;
import com.hrsb.cg.model.SubjectQuestion;
import com.hrsb.cg.model.SubjectQuestionOption;
import com.hrsb.cg.model.SubjectResult;
import com.hrsb.cg.service.SubjectService;
import com.hrsb.cg.util.Const;
import com.hrsb.cg.util.Page;

/**
 * 健康自测控制类
 * @author app001
 *
 */
@Controller
@RequestMapping(value = "/subject")
public class SubjectController {
	
	@Autowired
	SubjectService subjectService;
	
	@Autowired
	SubjectResultMapper subjectResultMapper;
	
	@Autowired
	SubjectQuestionMapper subjectQuestionMapper;
	
	@Autowired
	SubjectQuestionOptionMapper subjectQuestionOptionMapper;
	
	/**
	 * 健康自测列表页
	 * @param c
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String getSubject(@RequestParam(defaultValue = "1") int pageNo, 
			@RequestParam(defaultValue = Const.PAGESIZE) int pageSize, 
			ModelMap modelMap) {
		Page<Subject> page = new Page<Subject>(pageNo, pageSize);
		page.setOrderField("types");
		page.setOrderDirection("asc");
		List<Subject> subjects = subjectService.getByPage(page);
		page.setResults(subjects);
		modelMap.put("page", page);
		modelMap.put("type", 4);
		return "client/subject/list";
	}
	
	/**
	 * 测试题
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/detail")
	public String getDetai(@RequestParam long id, ModelMap modelMap) {
		Subject subject = subjectService.getById(id);
		// types==1、2的时候需要测试题有选项
		if (null != subject) {
			List<SubjectQuestion> subjectQuestions = subjectService.getQuestionBySubject(subject);
			modelMap.put("subjectQuestions", subjectQuestions);
		}
		
		modelMap.put("subject", subject);
		modelMap.put("type", 4);
		return "client/subject/detail";
	}
	
	/**
	 * 测试结果
	 * @param subjectId
	 * @param optionId
	 * @param choice
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/result")
	public String result(@RequestParam long subjectId, @RequestParam long[] optionId, 
			ModelMap modelMap, HttpServletRequest request, HttpSession session) {
		Subject subject = subjectService.getById(subjectId);
		
		if (null != subject) {
			// 计算总分
			int total = 0;
			// 平均分
			int score = 0;
			SubjectResult subjectResult = null;
			// 1原始8分2原始7分3累积分4是否5一题一答
			if (subject.getTypes() == 1 || subject.getTypes() == 2) {
				for (int i=0; i<optionId.length; i++) {
					SubjectQuestionOption subjectQuestionOption = subjectQuestionOptionMapper.selectByPrimaryKey(optionId[i]);
					total += subjectQuestionOption.getOptionScore();
				}
				// 1原始8分2原始7分3累积分4是否5一题一答
				if (subject.getTypes() == 1) {
					Double scores = (total -8.0) / (8 * 4) * 100;
					score = scores.intValue();
				}
				
				if (subject.getTypes() == 2) {
					Double scores = (total -7.0) / (7 * 4) * 100;
					score = scores.intValue();
				}
				System.out.println(score + "-----------------------------------");
				subjectResult = subjectResultMapper.selectBySubjectIdBetweenScore(subjectId, score);
			}
			
			if (subject.getTypes() == 3) {
				for (int i=0; i<optionId.length; i++) {
					if (optionId[i] != 0) {
						SubjectQuestion subjectQuestion = subjectQuestionMapper.selectByPrimaryKey(optionId[i]);
						total += subjectQuestion.getScore();
					}
				}
				
				subjectResult = subjectResultMapper.selectBySubjectIdBetweenScore(subjectId, total);
			}
			
			if (subject.getTypes() == 4) {
				for (int i=0; i<optionId.length; i++) {
					if (optionId[i] == 1) {
						score = 1;
						break;
					}
				}
				
				if (score == 0) {
					subjectResult = subjectResultMapper.selectBySubjectIdAndIsCorrect(subjectId, 0);
				} else {
					subjectResult = subjectResultMapper.selectBySubjectIdAndIsCorrect(subjectId, 1);
				}
			}
			
			if (subject.getTypes() == 5) {
				subjectResult = subjectResultMapper.selectBySubjectIdAndQuestionId(subjectId, optionId[0]);
			}
			
			modelMap.put("subjectResult", subjectResult);
			modelMap.put("subjectId", subjectId);
		}
		
		modelMap.put("subject", subject);
		modelMap.put("type", 4);
		//request.setAttribute("idS", subject.getId());
		//request.setAttribute("titleS", subject.getTitle());
		//request.setAttribute("imageS", subject.getImage());
		return "client/subject/result";
	}
	
}
