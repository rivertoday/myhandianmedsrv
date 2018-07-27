package com.hrsb.cg.controller.client;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hrsb.cg.dao.AreaCityMapper;
import com.hrsb.cg.dao.AreaCountyMapper;
import com.hrsb.cg.dao.AreaProvMapper;
import com.hrsb.cg.dao.CodeRecordMapper;
import com.hrsb.cg.dao.CollectionMapper;
import com.hrsb.cg.dao.CommentMapper;
import com.hrsb.cg.dao.DepartmentMapper;
import com.hrsb.cg.dao.DownloadRecordMapper;
import com.hrsb.cg.dao.HospitalGradeMapper;
import com.hrsb.cg.dao.LiteratureMapper;
import com.hrsb.cg.dao.ProductLiteratureMapper;
import com.hrsb.cg.dao.UserLoginMapper;
import com.hrsb.cg.dao.UserMessageMapper;
import com.hrsb.cg.model.AreaCity;
import com.hrsb.cg.model.AreaCounty;
import com.hrsb.cg.model.AreaProv;
import com.hrsb.cg.model.CodeRecord;
import com.hrsb.cg.model.Collection;
import com.hrsb.cg.model.Comment;
import com.hrsb.cg.model.Department;
import com.hrsb.cg.model.DownloadRecord;
import com.hrsb.cg.model.HospitalGrade;
import com.hrsb.cg.model.Literature;
import com.hrsb.cg.model.ProductLiterature;
import com.hrsb.cg.model.UserDetail;
import com.hrsb.cg.model.UserLogin;
import com.hrsb.cg.model.UserMessage;
import com.hrsb.cg.model.UserMessageWithBLOBs;
import com.hrsb.cg.service.UserService;
import com.hrsb.cg.util.AjaxUtil;
import com.hrsb.cg.util.Const;
import com.hrsb.cg.util.MD5;
import com.hrsb.cg.util.Page;

/**
 * 用户控制类
 * @author app001
 *
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	HospitalGradeMapper hospitalGradeMapper;
	
	@Autowired
	DepartmentMapper departmentMapper;
	
	@Autowired
	AreaProvMapper areaProvMapper;
	
	@Autowired
	AreaCityMapper areaCityMapper;
	
	@Autowired
	AreaCountyMapper areaCountyMapper;
	
	@Autowired
	DownloadRecordMapper downloadRecordMapper;
	
	@Autowired
	LiteratureMapper literatureMapper;
	
	@Autowired
	ProductLiteratureMapper productLiteratureMapper;
	
	@Autowired
	CollectionMapper collectionMapper;
	
	@Autowired
	CommentMapper commentMapper;
	
	@Autowired
	UserMessageMapper userMessageMapper;
	
	@Autowired
	CodeRecordMapper codeRecordMapper;
	
	@Autowired
	UserLoginMapper userLoginMapper;
	
	/**
	 * 基本信息
	 * @param c
	 * @return
	 */
	@RequestMapping(value = "/detail")
	public String getUserInfo(ModelMap modelMap, HttpSession session) {
		UserLogin userLogin = (UserLogin) session.getAttribute(Const.SESSION_CLIENT_USER);
		
		if (null != userLogin) {
			UserDetail userDetail = userService.getUserDetailByUserId(userLogin.getId());
			userService.getHeadImg(userDetail);
			modelMap.put("userDetail", userDetail);
			// 用户对应的科室
			if (null != userDetail.getDepartmentOne() && userDetail.getDepartmentOne() != 0) {
				List<Department> departments2 = departmentMapper.selectByParentId(userDetail.getDepartmentOne());
				modelMap.put("departments2", departments2);
			} 
			
			if (!StringUtils.isEmpty(userDetail.getProvince())) {
				List<AreaCity> areaCities = areaCityMapper.selectAllCity(userDetail.getProvince());
				modelMap.put("areaCities", areaCities);
			}
			
			if (!StringUtils.isEmpty(userDetail.getCity())) {
				List<AreaCounty> areaCounties = areaCountyMapper.selectAllCounty(userDetail.getCity());
				modelMap.put("areaCounties", areaCounties);
			}
		}
		
		// 医院等级
		List<HospitalGrade> hospitalGrades = hospitalGradeMapper.selectByType(1);
		modelMap.put("hospitalGrades", hospitalGrades);
		// 职称
		List<HospitalGrade> grades = hospitalGradeMapper.selectByType(2);
		modelMap.put("grades", grades);
		// 科室
		List<Department> departments = departmentMapper.selectByParentId(0);
		modelMap.put("departments", departments);
		// 省
		List<AreaProv> areaProvs = areaProvMapper.findAllProv();
		modelMap.put("areaProvs", areaProvs);
		modelMap.put("type", 1);
		return "client/user/detail";
	}
	
	/**
	 * 保存基本资料
	 * @param userDetail
	 * @param modelMap
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/perfection")
	public String perfect(UserDetail userDetail, ModelMap modelMap, HttpSession session) {
		UserLogin userLogin = (UserLogin) session.getAttribute(Const.SESSION_CLIENT_USER);
		String message = "操作不合法";
			if (null != userLogin) {
				UserDetail userDetail2= userService.IsExistPhone(userDetail.getPhone());
				if(StringUtils.isNotEmpty(userLogin.getToken())){
					if(userDetail2==null || userDetail2.getPhone().equals(userLogin.getPhone())){
						UserDetail user = userService.getUserDetailByUserId(userLogin.getId());
						if (user.getStatus() == 1) {
							userDetail.setId(user.getId());
							userDetail.setStatus((byte) 2);
							userService.modifyUserDetailById(userDetail);
							message = "已提交到审核";
						} 
					}else{
						message = "手机号已存在";
						session.setAttribute("message", message);
					}
				}else{
					if(userDetail2==null || userDetail2.getPhone().equals(userLogin.getPhone())){
						UserDetail user = userService.getUserDetailByUserId(userLogin.getId());
						if (user.getStatus() == 1) {
							userDetail.setId(user.getId());
							userDetail.setStatus((byte) 2);
							userService.modifyUserDetailById(userDetail);
							message = "已提交到审核";
						} 
					}else{
						message = "手机号已存在";
						session.setAttribute("message", message);
					}
				}
			}
		session.setAttribute("message", message);
		return "redirect:detail.html";
	}
	
	/**
	 * 我的下载
	 * @param types
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/downloadrecord")
	public String getDownloadRecord(@RequestParam(defaultValue = "1") byte types, 
			@RequestParam(defaultValue = "1") int pageNo, 
			@RequestParam(defaultValue = Const.PAGESIZE) int pageSize, 
			ModelMap modelMap, HttpSession session) {
		UserLogin userLogin = (UserLogin) session.getAttribute(Const.SESSION_CLIENT_USER);
		UserDetail userDetail = userService.getUserDetailByUserId(userLogin.getId());
		userService.getHeadImg(userDetail);
		modelMap.put("userDetail", userDetail);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userLogin.getId());
		params.put("types", types);
		Page<DownloadRecord> page = new Page<DownloadRecord>(pageNo, pageSize).setParams(params);
		page.setOrderField("create_time");
		page.setOrderDirection("desc");
		List<DownloadRecord> downloadRecords = downloadRecordMapper.selectByPage(page);
		
		if (!CollectionUtils.isEmpty(downloadRecords)) {
			for (DownloadRecord downloadRecord : downloadRecords) {
				if (types == 1) {
					Literature literature = literatureMapper.selectByPrimaryKey(downloadRecord.getLiteratureId());
					downloadRecord.setLiterature(literature);
				}
				
				if (types == 2) {
					ProductLiterature productLiterature = productLiteratureMapper.selectByPrimaryKey(downloadRecord.getLiteratureId());
					downloadRecord.setProductLiterature(productLiterature);
				}
			}
		}
		
		page.setResults(downloadRecords);
		modelMap.put("page", page);
		modelMap.put("type", 2);
		return "client/user/downloadrecord";
	}
	
	/**
	 * 删除下载记录
	 * @param id
	 * @param types
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/download_cancel")
	public String cancelDownload(@RequestParam long id, @RequestParam byte types, HttpSession session) {
		UserLogin userLogin = (UserLogin) session.getAttribute(Const.SESSION_CLIENT_USER);
		//downloadRecordMapper.deleteByIdAndUserId(id, userLogin.getId());
		downloadRecordMapper.updateByIdAndUserId(id, userLogin.getId(), (byte) 2);
		return "redirect:downloadrecord.html?types=" + types;
	}
	
	/**
	 * 已下载
	 * @param id
	 * @param types
	 * @param session
	 * @throws Exception 
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/download_status")
	public void download_status(@RequestParam long id, HttpSession session, 
			HttpServletRequest request,HttpServletResponse response) throws Exception {
		UserLogin userLogin = (UserLogin) session.getAttribute(Const.SESSION_CLIENT_USER);
		DownloadRecord downloadRecord = downloadRecordMapper.selectByPrimaryKey(id);
		
		if (null != downloadRecord && userLogin!=null) {
			DownloadRecord d = new DownloadRecord();
			d.setId(id);
			d.setIsDownload((byte) 1);
			downloadRecordMapper.updateByPrimaryKeySelective(d);
		}
		
		new AjaxUtil(request, response).print("ok");
	}
	
	/**
	 * 我的收藏
	 * @param types
	 * @param pageNo
	 * @param pageSize
	 * @param modelMap
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/collection")
	public String getCollection(@RequestParam(defaultValue = "1") byte types, 
			@RequestParam(defaultValue = "1") int pageNo, 
			@RequestParam(defaultValue = Const.PAGESIZE) int pageSize, 
			ModelMap modelMap, HttpSession session) {
		UserLogin userLogin = (UserLogin) session.getAttribute(Const.SESSION_CLIENT_USER);
		UserDetail userDetail = userService.getUserDetailByUserId(userLogin.getId());
		userService.getHeadImg(userDetail);
		modelMap.put("userDetail", userDetail);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userLogin.getId());
		params.put("types", types);
		Page<Collection> page = new Page<Collection>(pageNo, pageSize).setParams(params);
		page.setOrderField("create_time");
		page.setOrderDirection("desc");
		List<Collection> collections = collectionMapper.selectByPage(page);
		
		if (!CollectionUtils.isEmpty(collections)) {
			for (Collection collection : collections) {
				if (types == 1) {
					Literature literature = literatureMapper.selectByPrimaryKey(collection.getLiteratureId());
					collection.setLiterature(literature);
				}
				
				if (types == 2) {
					ProductLiterature productLiterature = productLiteratureMapper.selectByPrimaryKey(collection.getLiteratureId());
					collection.setProductLiterature(productLiterature);
				}
			}
		}
		
		page.setResults(collections);
		modelMap.put("page", page);
		modelMap.put("type", 3);
		return "client/user/collection";
	}
	
	/**
	 * 删除收藏记录
	 * @param id
	 * @param types
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/collection_cancel")
	public String cancelCollection(@RequestParam long id, @RequestParam byte types, HttpSession session) {
		UserLogin userLogin = (UserLogin) session.getAttribute(Const.SESSION_CLIENT_USER);
		collectionMapper.deleteByIdAndUserId(id, userLogin.getId());
		return "redirect:collection.html?types=" + types;
	}
	
	/**
	 * 我的评论
	 * @param types
	 * @param pageNo
	 * @param pageSize
	 * @param modelMap
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/comment")
	public String getComment(@RequestParam(defaultValue = "1") byte types, 
			@RequestParam(defaultValue = "1") int pageNo, 
			@RequestParam(defaultValue = Const.PAGESIZE) int pageSize, 
			ModelMap modelMap, HttpSession session) {
		UserLogin userLogin = (UserLogin) session.getAttribute(Const.SESSION_CLIENT_USER);
		UserDetail userDetail = userService.getUserDetailByUserId(userLogin.getId());
		userService.getHeadImg(userDetail);
		modelMap.put("userDetail", userDetail);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userLogin.getId());
		params.put("types", types);
		Page<Comment> page = new Page<Comment>(pageNo, pageSize).setParams(params);
		page.setOrderField("create_time");
		page.setOrderDirection("desc");
		List<Comment> comments = commentMapper.getByPage(page);
		
		if (!CollectionUtils.isEmpty(comments)) {
			for (Comment comment : comments) {
				if (types == 1) {
					Literature literature = literatureMapper.selectByPrimaryKey(comment.getLiteratureId());
					comment.setLiterature(literature);
				}
				
				if (types == 2) {
					ProductLiterature productLiterature = productLiteratureMapper.selectByPrimaryKey(comment.getLiteratureId());
					comment.setProductLiterature(productLiterature);
				}
			}
		}
		
		page.setResults(comments);
		modelMap.put("page", page);
		modelMap.put("type", 4);
		return "client/user/comment";
	}
	
	/**
	 * 删除收藏记录
	 * @param id
	 * @param types
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/comment_cancel")
	public String cancelComment(@RequestParam long id, HttpSession session) {
		UserLogin userLogin = (UserLogin) session.getAttribute(Const.SESSION_CLIENT_USER);
		commentMapper.deleteByIdAndUserId(id, userLogin.getId());
		return "redirect:comment.html";
	}
	
	/**
	 * 我的消息
	 * @param pageNo
	 * @param pageSize
	 * @param modelMap
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/message")
	public String message(@RequestParam(defaultValue = "1") int pageNo, 
			@RequestParam(defaultValue = Const.PAGESIZE) int pageSize, 
			ModelMap modelMap, HttpSession session) {
		UserLogin userLogin = (UserLogin) session.getAttribute(Const.SESSION_CLIENT_USER);
		UserDetail userDetail = userService.getUserDetailByUserId(userLogin.getId());
		userService.getHeadImg(userDetail);
		modelMap.put("userDetail", userDetail);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userLogin.getId());
		Page<UserMessageWithBLOBs> page = new Page<UserMessageWithBLOBs>(pageNo, pageSize).setParams(params);
		page.setOrderField("operate_time");
		page.setOrderDirection("desc");
		List<UserMessageWithBLOBs> userMessages = userMessageMapper.getByPage(page);
		page.setResults(userMessages);
		modelMap.put("page", page);
		modelMap.put("type", 5);
		return "client/user/message";
	}
	
	/**
	 * 删除我的消息
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/message_cancel")
	public String cancelMessage(@RequestParam long id, HttpSession session) {
		UserLogin userLogin = (UserLogin) session.getAttribute(Const.SESSION_CLIENT_USER);
		userMessageMapper.deleteByIdAndUserId(id, userLogin.getId());
		return "redirect:message.html";
	}
	
	/**
	 * 我的消息详情
	 * @param modelMap
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/message_detail")
	public String getMessageDetail(ModelMap modelMap, @RequestParam long id, HttpSession session) {
		UserLogin userLogin = (UserLogin) session.getAttribute(Const.SESSION_CLIENT_USER);
		userMessageMapper.updateIsNew(id);
		UserMessage userMessage = userMessageMapper.selectByPrimaryKey(id);
		modelMap.put("userMessage", userMessage);
		// 上一篇
		UserMessage preUserMessage = userMessageMapper.selectPre(userMessage.getId(), userLogin.getId());
		modelMap.put("preUserMessage", preUserMessage);
		// 下一篇
		UserMessage nextUserMessage = userMessageMapper.selectNext(userMessage.getId(), userLogin.getId());
		modelMap.put("nextUserMessage", nextUserMessage);
		modelMap.put("type", 5);
		return "client/user/message_detail";
	}
	
	/**
	 * 设置
	 * @param modelMap
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/settingUI")
	public String settingUI(ModelMap modelMap, HttpSession session) {
		UserLogin userLogin = (UserLogin) session.getAttribute(Const.SESSION_CLIENT_USER);
		UserDetail userDetail = userService.getUserDetailByUserId(userLogin.getId());
		userService.getHeadImg(userDetail);
		modelMap.put("userDetail", userDetail);
		modelMap.put("type", 6);
		modelMap.put("userLogin", userLogin);
		return "client/user/setting";
	}
	
	/**
	 * 密码修改
	 * @param phone
	 * @param code
	 * @param oldpwd
	 * @param password
	 * @param modelMap
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/setting")
	public String setting(@RequestParam String phone, @RequestParam String code, 
			@RequestParam String oldpwd, @RequestParam String password, 
			ModelMap modelMap, HttpSession session) {
		//fixed by JIANG
		if (userService.isExistLoginName(phone) && checkCode(phone, code)) {
			oldpwd = MD5.convertToMD5(oldpwd);
			UserLogin userLogin = userLoginMapper.selectByPhoneAndPassword(phone, oldpwd);
			
			if (null != userLogin) {
				password = MD5.convertToMD5(password);
				userLoginMapper.updateByPhone(password, phone);
				session.setAttribute("ms", "修改成功");
			}
		}
		
		return "redirect:settingUI.html";
	}
	
	/**
	 * 验证验证码是否有效
	 * @param phone
	 * @param code
	 * @return
	 */
	public boolean checkCode(String phone, String code) {
		CodeRecord codeRecord = codeRecordMapper.selectByPhone(phone);
		
		if (null != codeRecord && !StringUtils.isEmpty(code) &&
				code.equals(codeRecord.getCode()) && codeRecord.getEffectiveTime().after(new Date())) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 修改昵称
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="updNickName")
	public void updNickName(HttpServletRequest request,HttpServletResponse response) throws Exception{
		UserLogin userLogin=(UserLogin)request.getSession().getAttribute(Const.SESSION_CLIENT_USER);
		String nickName=request.getParameter("nickName");
		if(StringUtils.isNotEmpty(nickName)){
			UserDetail userDetail =userService.getUserDetailByUserId(userLogin.getId());
			if(userDetail!=null){
				UserDetail userDetail2 = new UserDetail();
				userDetail2.setId(userDetail.getId());
				userDetail2.setNickName(nickName);
				userService.modifyUserDetailById(userDetail2);
				new AjaxUtil(request, response).print("ok");
			}
		}else{
			new AjaxUtil(request, response).print("nickNamenull");
		}
	}
	
	/**
	 * 上传头像
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="updheadImg")
	public void updheadImg(HttpServletRequest request,HttpServletResponse response) throws Exception{
		UserLogin userLogin=(UserLogin)request.getSession().getAttribute(Const.SESSION_CLIENT_USER);
		String headImg=request.getParameter("headImg");
		
		if(StringUtils.isNotEmpty(headImg) && userLogin!=null){
			UserDetail userDetail =userService.getUserDetailByUserId(userLogin.getId());
			if(userDetail!=null){
				UserDetail userDetail2 = new UserDetail();
				userDetail2.setId(userDetail.getId());
				userDetail2.setHeadImg(headImg);
				userService.modifyUserDetailById(userDetail2);
				new AjaxUtil(request, response).print("ok");
			}else{
				new AjaxUtil(request, response).print("fail");
			}
		}else{
			new AjaxUtil(request, response).print("headImgnull");
		}
	}
	
	
}
