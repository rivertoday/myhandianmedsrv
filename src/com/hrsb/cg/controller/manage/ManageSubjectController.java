package com.hrsb.cg.controller.manage;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hrsb.cg.controller.util.BaseLogsUtil;
import com.hrsb.cg.dao.SubjectMapper;
import com.hrsb.cg.dao.SubjectQuestionMapper;
import com.hrsb.cg.dao.SubjectQuestionOptionMapper;
import com.hrsb.cg.dao.SubjectResultMapper;
import com.hrsb.cg.model.BaseLog;
import com.hrsb.cg.model.Manager;
import com.hrsb.cg.model.Subject;
import com.hrsb.cg.model.SubjectQuestion;
import com.hrsb.cg.model.SubjectQuestionOption;
import com.hrsb.cg.model.SubjectResult;
import com.hrsb.cg.util.AjaxUtil;
import com.hrsb.cg.util.Const;
import com.hrsb.cg.util.Page;

/**
 * 诊断自测管理控制类
 * @author app001
 *
 */
@Controller
@RequestMapping("managesubject")
public class ManageSubjectController {
	
	@Autowired
	SubjectMapper subjectMapper;
	
	@Autowired
	SubjectQuestionMapper subjectQuestionMapper;
	
	@Autowired
	SubjectQuestionOptionMapper subjectQuestionOptionMapper;
	
	@Autowired
	SubjectResultMapper subjectResultMapper;
	
	/**
	 * 诊断题库列表
	 * @param title
	 * @param types
	 * @param pageCurrent
	 * @param pageSize
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/subject_list")
	public ModelAndView getSubjectList(@RequestParam(required = false) String title, 
			@RequestParam(defaultValue = "0") byte types, 
			@RequestParam(required = false) Integer pageCurrent, 
			@RequestParam(required = false) Integer pageSize, 
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("manage/subject/subject_list");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", title);
		params.put("types", types);
		Page<Subject> page = new Page<Subject>(pageCurrent, pageSize).setParams(params);
		page.setOrderField("operate_time");
		page.setOrderDirection("desc");
		List<Subject> subjects = subjectMapper.selectByPage(page);
		page.setResults(subjects);
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 编辑/添加 诊断题库 
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/subject_edit")
	public ModelAndView subjectEdit(Long id, HttpServletRequest request, HttpServletResponse response) throws IOException{
		ModelAndView mv = new ModelAndView("manage/subject/subject_edit");
		
		if (null != id) {
			Subject subject = subjectMapper.selectByPrimaryKey(id);
			mv.addObject("subject", subject);
		}
		
		return mv;
	}
	
	/**
	 * 保存 诊断题库
	 * @param subject
	 * @param session
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/subject_save")
	public void subjectSave(Subject subject, HttpSession session, 
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		Manager manager = (Manager) session.getAttribute(Const.SESSION_MANAGER);
		subject.setManagerId(manager.getId());
		subject.setOperateTime(new Date());
		int row = 0;
		
		if (null != subject.getId()) {
			row = subjectMapper.updateByPrimaryKeySelective(subject);
		} else {
			row = subjectMapper.insertSelective(subject);
		}
		
		if(row > 0){
			BaseLog baseLog = new BaseLog(manager.getIdkey(), new Date(), "操作员管理", manager.getIdkey(), "添加/编辑诊断题库", 0, 0);
			BaseLogsUtil.insertBaseLog(baseLog);
			new AjaxUtil(request, response).JsonType("200", "操作成功", "", "subjectlist", "", "", "操作成功", true);
		}else{
			new AjaxUtil(request, response).JsonType("300", "操作失败", "", "subjectlist", "", "", "操作失败", true);
		}
	}
	
	/**
	 * 删除 诊断题库
	 * @param id
	 * @param session
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/subject_del")
	public void subjectDel(@RequestParam long id, HttpSession session, 
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		Manager manager = (Manager) session.getAttribute(Const.SESSION_MANAGER);
		int row = subjectMapper.deleteByPrimaryKey(id);
		
		if(row > 0){
			BaseLog baseLog = new BaseLog(manager.getIdkey(), new Date(), "操作员管理", manager.getIdkey(), "删除诊断题库", 0, 0);
			BaseLogsUtil.insertBaseLog(baseLog);
			new AjaxUtil(request, response).JsonType("200", "操作成功", "", "subjectlist", "", "", "操作成功", false);
		}else{
			new AjaxUtil(request, response).JsonType("300", "操作失败", "", "subjectlist", "", "", "操作失败", false);
		}
	}
	
	/**
	 * 问题列表
	 * @param subjectId
	 * @param title
	 * @param pageCurrent
	 * @param pageSize
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/question_list")
	public ModelAndView getQuestionList(@RequestParam long subjectId, @RequestParam byte types, 
			@RequestParam(required = false) String title, 
			@RequestParam(required = false) Integer pageCurrent, 
			@RequestParam(required = false) Integer pageSize, 
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("manage/subject/question_list");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("subjectId", subjectId);
		params.put("title", title);
		Page<SubjectQuestion> page = new Page<SubjectQuestion>(pageCurrent, pageSize).setParams(params);
		page.setOrderField("operate_time");
		page.setOrderDirection("desc");
		List<SubjectQuestion> subjectQuestions = subjectQuestionMapper.selectByPage(page);
		page.setResults(subjectQuestions);
		mv.addObject("types", types);
		mv.addObject("subjectId", subjectId);
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 编辑/添加 问题
	 * @param subjectQuestion
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/question_edit")
	public ModelAndView questionEdit(SubjectQuestion subjectQuestion, HttpServletRequest request, HttpServletResponse response) throws IOException{
		ModelAndView mv = new ModelAndView("manage/subject/question_edit");
		
		if (null != subjectQuestion.getId()) {
			subjectQuestion = subjectQuestionMapper.selectByPrimaryKey(subjectQuestion.getId());
			mv.addObject("subjectQuestion", subjectQuestion);
		}
		
		return mv;
	}
	
	/**
	 * 保存 问题
	 * @param subject
	 * @param session
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/question_save")
	public void questionSave(SubjectQuestion subjectQuestion, HttpSession session, 
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		Manager manager = (Manager) session.getAttribute(Const.SESSION_MANAGER);
		subjectQuestion.setManagerId(manager.getId());
		subjectQuestion.setOperateTime(new Date());
		int row = 0;
		
		if (null != subjectQuestion.getId()) {
			row = subjectQuestionMapper.updateByPrimaryKeySelective(subjectQuestion);
		} else {
			row = subjectQuestionMapper.insertSelective(subjectQuestion);
		}
		
		if(row > 0){
			BaseLog baseLog = new BaseLog(manager.getIdkey(), new Date(), "操作员管理", manager.getIdkey(), "添加/编辑问题", 0, 0);
			BaseLogsUtil.insertBaseLog(baseLog);
			new AjaxUtil(request, response).JsonType("200", "操作成功", "", "questionlist", "", "", "操作成功", true);
		}else{
			new AjaxUtil(request, response).JsonType("300", "操作失败", "", "questionlist", "", "", "操作失败", true);
		}
	}
	
	/**
	 * 删除 问题
	 * @param id
	 * @param session
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/question_del")
	public void questionDel(@RequestParam long id, HttpSession session, 
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		Manager manager = (Manager) session.getAttribute(Const.SESSION_MANAGER);
		int row = subjectQuestionMapper.deleteByPrimaryKey(id);
		
		if(row > 0){
			BaseLog baseLog = new BaseLog(manager.getIdkey(), new Date(), "操作员管理", manager.getIdkey(), "删除问题", 0, 0);
			BaseLogsUtil.insertBaseLog(baseLog);
			new AjaxUtil(request, response).JsonType("200", "操作成功", "", "questionlist", "", "", "操作成功", false);
		}else{
			new AjaxUtil(request, response).JsonType("300", "操作失败", "", "questionlist", "", "", "操作失败", false);
		}
	}
	
	/**
	 * 选项列表
	 * @param subjectId
	 * @param questionId
	 * @param pageCurrent
	 * @param pageSize
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/option_list")
	public ModelAndView getOptionList(@RequestParam long subjectId, @RequestParam long questionId, 
			@RequestParam(required = false) Integer pageCurrent, 
			@RequestParam(required = false) Integer pageSize, 
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("manage/subject/option_list");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("questionId", questionId);
		Page<SubjectQuestionOption> page = new Page<SubjectQuestionOption>(pageCurrent, pageSize).setParams(params);
		page.setOrderField("operate_time");
		page.setOrderDirection("desc");
		List<SubjectQuestionOption> subjectQuestionOptions = subjectQuestionOptionMapper.selectByPage(page);
		page.setResults(subjectQuestionOptions);
		mv.addObject("subjectId", subjectId);
		mv.addObject("questionId", questionId);
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 编辑/添加 选项
	 * @param subjectQuestion
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/option_edit")
	public ModelAndView optionEdit(SubjectQuestionOption subjectQuestionOption, HttpServletRequest request, HttpServletResponse response) throws IOException{
		ModelAndView mv = new ModelAndView("manage/subject/option_edit");
		
		if (null != subjectQuestionOption.getId()) {
			subjectQuestionOption = subjectQuestionOptionMapper.selectByPrimaryKey(subjectQuestionOption.getId());
			mv.addObject("subjectQuestionOption", subjectQuestionOption);
		}
		
		return mv;
	}
	
	/**
	 * 保存 选项
	 * @param subject
	 * @param session
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/option_save")
	public void optionSave(SubjectQuestionOption subjectQuestionOption, HttpSession session, 
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		Manager manager = (Manager) session.getAttribute(Const.SESSION_MANAGER);
		subjectQuestionOption.setManagerId(manager.getId());
		subjectQuestionOption.setOperateTime(new Date());
		int row = 0;
		
		if (null != subjectQuestionOption.getId()) {
			row = subjectQuestionOptionMapper.updateByPrimaryKeySelective(subjectQuestionOption);
		} else {
			row = subjectQuestionOptionMapper.insertSelective(subjectQuestionOption);
		}
		
		if(row > 0){
			BaseLog baseLog = new BaseLog(manager.getIdkey(), new Date(), "操作员管理", manager.getIdkey(), "添加/编辑选项", 0, 0);
			BaseLogsUtil.insertBaseLog(baseLog);
			new AjaxUtil(request, response).JsonType("200", "操作成功", "", "optionlist", "", "", "操作成功", true);
		}else{
			new AjaxUtil(request, response).JsonType("300", "操作失败", "", "optionlist", "", "", "操作失败", true);
		}
	}
	
	/**
	 * 删除 选项
	 * @param id
	 * @param session
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/option_del")
	public void optionDel(@RequestParam long id, HttpSession session, 
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		Manager manager = (Manager) session.getAttribute(Const.SESSION_MANAGER);
		int row = subjectQuestionOptionMapper.deleteByPrimaryKey(id);
		
		if(row > 0){
			BaseLog baseLog = new BaseLog(manager.getIdkey(), new Date(), "操作员管理", manager.getIdkey(), "删除选项", 0, 0);
			BaseLogsUtil.insertBaseLog(baseLog);
			new AjaxUtil(request, response).JsonType("200", "操作成功", "", "optionlist", "", "", "操作成功", false);
		}else{
			new AjaxUtil(request, response).JsonType("300", "操作失败", "", "optionlist", "", "", "操作失败", false);
		}
	}
	
	/**
	 * 结果列表
	 * @param subjectId
	 * @param questionId
	 * @param pageCurrent
	 * @param pageSize
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/result_list")
	public ModelAndView getResultList(@RequestParam(defaultValue = "0") Long subjectId, 
			@RequestParam(defaultValue = "0") Long questionId, 
			@RequestParam(required = false) Integer pageCurrent, 
			@RequestParam(required = false) Integer pageSize, 
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("manage/subject/result_list");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("subjectId", subjectId);
		params.put("questionId", questionId);
		Page<SubjectResult> page = new Page<SubjectResult>(pageCurrent, pageSize).setParams(params);
		page.setOrderField("operate_time");
		page.setOrderDirection("desc");
		List<SubjectResult> subjectResults = subjectResultMapper.selectByPage(page);
		page.setResults(subjectResults);
		mv.addObject("subjectId", subjectId);
		mv.addObject("questionId", questionId);
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 编辑/添加 结果
	 * @param subjectQuestion
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/result_edit")
	public ModelAndView resultEdit(SubjectResult subjectResult, 
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		ModelAndView mv = new ModelAndView("manage/subject/result_edit");
		
		if (null != subjectResult.getId()) {
			subjectResult = subjectResultMapper.selectByPrimaryKey(subjectResult.getId());
			mv.addObject("subjectResult", subjectResult);
		}
		
		Subject subject = subjectMapper.selectByPrimaryKey(subjectResult.getSubjectId());
		mv.addObject("subject", subject);
		return mv;
	}
	
	/**
	 * 保存 结果
	 * @param subject
	 * @param session
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/result_save")
	public void resultSave(SubjectResult subjectResult, HttpSession session, 
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		Manager manager = (Manager) session.getAttribute(Const.SESSION_MANAGER);
		subjectResult.setManagerId(manager.getId());
		subjectResult.setOperateTime(new Date());
		int row = 0;
		
		if (null != subjectResult.getId()) {
			row = subjectResultMapper.updateByPrimaryKeySelective(subjectResult);
		} else {
			row = subjectResultMapper.insertSelective(subjectResult);
		}
		
		if(row > 0){
			BaseLog baseLog = new BaseLog(manager.getIdkey(), new Date(), "操作员管理", manager.getIdkey(), "添加/编辑结果", 0, 0);
			BaseLogsUtil.insertBaseLog(baseLog);
			new AjaxUtil(request, response).JsonType("200", "操作成功", "", "resultlist", "", "", "操作成功", true);
		}else{
			new AjaxUtil(request, response).JsonType("300", "操作失败", "", "resultlist", "", "", "操作失败", true);
		}
	}
	
	/**
	 * 删除 结果
	 * @param id
	 * @param session
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/result_del")
	public void resultDel(@RequestParam long id, HttpSession session, 
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		Manager manager = (Manager) session.getAttribute(Const.SESSION_MANAGER);
		int row = subjectResultMapper.deleteByPrimaryKey(id);
		
		if(row > 0){
			BaseLog baseLog = new BaseLog(manager.getIdkey(), new Date(), "操作员管理", manager.getIdkey(), "删除结果", 0, 0);
			BaseLogsUtil.insertBaseLog(baseLog);
			new AjaxUtil(request, response).JsonType("200", "操作成功", "", "resultlist", "", "", "操作成功", false);
		}else{
			new AjaxUtil(request, response).JsonType("300", "操作失败", "", "resultlist", "", "", "操作失败", false);
		}
	}
	
}
