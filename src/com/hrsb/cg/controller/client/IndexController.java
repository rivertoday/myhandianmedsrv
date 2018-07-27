package com.hrsb.cg.controller.client;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hrsb.cg.dao.AboutMapper;
import com.hrsb.cg.dao.CodeRecordMapper;
import com.hrsb.cg.dao.CommentMapper;
import com.hrsb.cg.dao.DepartmentMapper;
import com.hrsb.cg.dao.DirtyMapper;
import com.hrsb.cg.dao.UploadFileMapper;
import com.hrsb.cg.dao.UserDetailMapper;
import com.hrsb.cg.dao.UserLoginMapper;
import com.hrsb.cg.model.About;
import com.hrsb.cg.model.CodeRecord;
import com.hrsb.cg.model.Comment;
import com.hrsb.cg.model.Department;
import com.hrsb.cg.model.Manager;
import com.hrsb.cg.model.ProductLiterature;
import com.hrsb.cg.model.UploadFile;
import com.hrsb.cg.model.UserDetail;
import com.hrsb.cg.model.UserLogin;
import com.hrsb.cg.model.UserMessage;
import com.hrsb.cg.service.ProductService;
import com.hrsb.cg.service.UploadService;
import com.hrsb.cg.service.UserService;
import com.hrsb.cg.util.AjaxUtil;
import com.hrsb.cg.util.ClientSDK;
import com.hrsb.cg.util.Const;
import com.hrsb.cg.util.MD5;
import com.hrsb.cg.util.Tools;
import com.hrsb.cg.model.Dirty;

/**
 * 首页控制类
 * @author app001
 *
 */
@Controller
public class IndexController {
	//Added by JIANG, please refer to the log4j.xml and web.xml for configuration
	private static final Logger JJLogger = Logger.getLogger("RIVER_LOGGER");
	
	@Autowired
	UserService userService;
	
	@Autowired
	CodeRecordMapper codeRecordMapper;
	
	@Autowired
	UserLoginMapper userLoginMapper;
	
	@Autowired
	UserDetailMapper userDetailMapper;
	
	@Autowired
	AboutMapper aboutMapper;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	DepartmentMapper departmentMapper;
	
	@Autowired
	CommentMapper commentMapper;
	
	@Autowired
	DirtyMapper dirtyMapper;
	
	@Autowired
	UploadService uploadService;
	
	ClientSDK sdk = new ClientSDK();
	
	
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping(value = "/top")
	public String headerNickName(ModelMap modelMap, HttpSession session) {
		UserLogin userLogin = (UserLogin) session.getAttribute(Const.SESSION_CLIENT_USER);
		// 用户信息
		if (null != userLogin) {
			UserDetail userDetail = userDetailMapper.selectByUserId(userLogin.getId());
			userService.getHeadImg(userDetail);
			modelMap.put("userDetail", userDetail);
		}
		return "/client/top";
	}
	
	
	
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String index(ModelMap modelMap, HttpSession session) {
		UserLogin userLogin = (UserLogin) session.getAttribute(Const.SESSION_CLIENT_USER);
		// 用户信息
		if (null != userLogin) {
			UserDetail userDetail = userDetailMapper.selectByUserId(userLogin.getId());
			userService.getHeadImg(userDetail);
			modelMap.put("userDetail", userDetail);
		}
		
		return "client/index";
	}
	
	
	
	
	/**
	 * 注册页面
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/registerUI")
	public String registerUI(ModelMap modelMap) {
		// 注册协议
		About about=aboutMapper.selectByPrimaryKey(3);
		modelMap.put("about", about);
		// 左侧图片
		
		return "client/register";
	}
	
	/**
	 * 验证手机号是否已经注册
	 * @param phone
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/existence")
	public void existence(@RequestParam String phone, HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean flag = userService.isExistLoginName(phone);
		
		if (flag) {//fixed by JIANG
			new AjaxUtil(request, response).print("exist");
		}
	}
	
	/**
	 * 注册
	 * @param phone
	 * @param code
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/register")
	public String regist(@RequestParam String phone, @RequestParam String code, 
			@RequestParam String password, HttpSession session) {
		// 来源为：PC站
		boolean flag = false;
		String message = "";
		
		if (!userService.isExistLoginName(phone)) {
			if (checkCode(phone, code)) {
				UserLogin login = new UserLogin();
				login.setPhone(phone);
				login.setPassword(MD5.convertToMD5(password));
				login.setFrozen((byte) 0);
				userLoginMapper.insertSelective(login);
				UserDetail detail = new UserDetail();
				detail.setUserId(login.getId());
				detail.setWay((byte) 1);
				detail.setPhone(phone);
				detail.setStatus((byte) 1);
				detail.setCreateTime(new Date());
				detail.setIsLoginFirst((byte) 1);
				detail.setSource("PC站");
				detail.setDownloadTypes((byte) 1);
				detail.setDownloadCount(Const.DOWNLOADCOUNT);
				userDetailMapper.insertSelective(detail);
				session.setAttribute(Const.SESSION_CLIENT_USER, login);
				flag = true;
			} else {
				message = "验证码错误";
			}
		} else {
			message = "用户已存在";
		}
		
		if (flag) {
			return "redirect:index.html";
		} else {
			session.setAttribute("message", message);
			return "redirect:registerUI.html";
		}
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
	 * 验证码是否有效
	 * @param phone
	 * @param code
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/code_check")
	public void code_check(@RequestParam String phone, @RequestParam String code, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CodeRecord codeRecord = codeRecordMapper.selectByPhone(phone);
		
		if (null != codeRecord && !StringUtils.isEmpty(code) &&
				code.equals(codeRecord.getCode()) && codeRecord.getEffectiveTime().after(new Date())) {
			new AjaxUtil(request, response).print("ok");
		} else {
			new AjaxUtil(request, response).print("fail");
		}
	}
	
	/**
	 * 验证密码是否正确
	 * @param phone
	 * @param oldpwd
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/password_check")
	public void password_check(@RequestParam String phone, @RequestParam String oldpwd, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if (!StringUtils.isEmpty(oldpwd)) {
			oldpwd = MD5.convertToMD5(oldpwd);
			UserLogin userLogin = userLoginMapper.selectByPhoneAndPassword(phone, oldpwd);
			
			if (null != userLogin) {
				new AjaxUtil(request, response).print("ok");
			} else {
				new AjaxUtil(request, response).print("fail");
			}
		} else {
			new AjaxUtil(request, response).print("fail");
		}
	}
	
	/**
	 * 获取验证码
	 * @param phone
	 * @throws Exception 
	 */
	@RequestMapping(value = "/code")
	public void getCode(@RequestParam String phone) throws Exception {
		String code = Tools.getRandom(6);
		String content = "您的手机验证码为：" + code + " 【汉典医学】";
		String result = sdk.sendSms(Const.server_username, Const.server_password, phone, content);
		String[] arr = StringUtils.split(result, ",");
		
		if (!ArrayUtils.isEmpty(arr) && !StringUtils.isEmpty(arr[0]) && "0".equals(arr[0].trim())) {
			CodeRecord codeRecord = codeRecordMapper.selectByPhone(phone);
			CodeRecord record = new CodeRecord();
			
			if (null != codeRecord) {
				record.setId(codeRecord.getId());
				record.setCode(code);
				record.setEffectiveTime(DateUtils.addMinutes(new Date(), Const.MINUTE));
				codeRecordMapper.updateByPrimaryKeySelective(record);
			} else {
				record.setPhone(phone);
				record.setCode(code);
				record.setEffectiveTime(DateUtils.addMinutes(new Date(), Const.MINUTE));
				codeRecordMapper.insertSelective(record);
			}
		}
	}
	
	/**
	 * 登录
	 * @param phone
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login")
	public String login(@RequestParam String phone, @RequestParam String password, 
			HttpSession session, ModelMap modelMap) {
		UserLogin userLogin = userLoginMapper.selectByPhoneAndPassword(phone, MD5.convertToMD5(password));
		
		if (null != userLogin && userLogin.getFrozen() == 0) {
			session.setAttribute(Const.SESSION_CLIENT_USER, userLogin);
			UserDetail userDetail = userService.getUserDetailByUserId(userLogin.getId());
			userService.getHeadImg(userDetail);
			modelMap.put("userDetail", userDetail);
		} else {
			session.setAttribute("message", "用户不存在或者被冻结");
		}
		
		return "redirect:index.html";
	}
	
	/**
	 * 验证用户密码是否正确
	 * @param phone
	 * @param password
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/password")
	public void password(@RequestParam String phone, @RequestParam String password,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String msg = userService.checkPassword(phone, password);
		new AjaxUtil(request, response).print(msg);
	}
	
	/**
	 * 找回密码页面
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/passwordUI")
	public String passwordUI(ModelMap modelMap) {
		
		return "client/password";
	}
	
	/**
	 * 找回密码
	 * @param phone
	 * @param code
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/password_forget")
	public String password(@RequestParam String phone, @RequestParam String code, 
			@RequestParam String password, HttpSession session) {
		UserLogin userLogin = userLoginMapper.selectByPhone(phone);
		
		if (null != userLogin) {
			if (checkCode(phone, code)) {
				UserLogin login = new UserLogin();
				login.setId(userLogin.getId());
				login.setPassword(MD5.convertToMD5(password));
				userLoginMapper.updateByPrimaryKeySelective(login);
				session.setAttribute("message", "密码找回成功");
				return "redirect:index.html";
			} else {
				session.setAttribute("message", "验证码错误");
				return "redirect:passwordUI.html";
			}
		} else {
			session.setAttribute("message", "该手机号未注册");
			return "redirect:passwordUI.html";
		}		
	}
	
	/**
	 * 关于我们
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/about")
	public String about(ModelMap modelMap) {
		About about = aboutMapper.selectByPrimaryKey(1);
		modelMap.put("about", about);
		return "client/about";
	}
	
	/**
	 * 隐私条款
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/secret")
	public String secret(ModelMap modelMap) {
		About about = aboutMapper.selectByPrimaryKey(3);
		modelMap.put("about", about);
		return "client/secret";
	}
	
	/**
	 * 左边
	 * @param types
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/left")
	public String left(String types, ModelMap modelMap, HttpSession session) {
		// types==1只有图2我的消息+图
		/**
		if ("2".equals(types)) {
			UserLogin userLogin = (UserLogin) session.getAttribute(Const.SESSION_CLIENT_USER);
			List<UserMessage> userMessages = userService.getTopMessageByUserId(userLogin.getId());
			modelMap.put("userMessages", userMessages);
		}
		*/
		modelMap.put("types", types);
		return "client/left";
	}
	
	/**
	 * 下载
	 * @param id
	 * @param types
	 * @param session
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/download")
	public void download(@RequestParam long id, byte types, HttpSession session, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			UserLogin userLogin = (UserLogin) session.getAttribute(Const.SESSION_CLIENT_USER);
			String msg = productService.download(id, userLogin.getId(), types);
			new AjaxUtil(request, response).print(msg);
		} catch (Exception e) {
			new AjaxUtil(request, response).print("error");
		}
	}
	
	/**
	 * 上传页面
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/distill")
	public ModelAndView upload(){
		ModelAndView mv=new ModelAndView("/client/mycollection");
		
		UploadFile upfile = new UploadFile();
		upfile.setTitle("请输入上传标题");
		upfile.setPhone("请留下联系电话");
		upfile.setMail("请留下联系邮箱");
		upfile.setSubmitter("请填写联系人");
		mv.addObject("upfile",upfile);
		return mv;
	}
	
	/**
	 * 专家点评保存
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/distillSave")
	public void uploadSave(@RequestBody String data, HttpSession session,
			HttpServletRequest request,HttpServletResponse response) throws IOException{

		try {
			System.out.println("Here it is the general search POST data: " + data);
			JsonParser parser = new JsonParser(); // 创建json解析器
			JsonObject json = (JsonObject) parser.parse(data); // 创建jsonObject对象
			String title = json.get("title").getAsString();
			String submitter = json.get("submitter").getAsString();
			String phone = json.get("phone").getAsString();
			String mail = json.get("mail").getAsString();
			String filename = json.get("downloadName").getAsString();
			String fileurl = json.get("downloadUrl").getAsString();
			String checkcode = json.get("checkcode").getAsString();
			
			String code = session.getAttribute(Const.CHECK_CODE).toString();
			if(!checkcode.equalsIgnoreCase(code)){
				new AjaxUtil(request, response).JsonType("300", "验证码错误", "", "uploadFile", "", "", "验证码错误",true);	
				return;
			}
			
			UploadFile upfile = new UploadFile();
			upfile.setTitle(title);
			upfile.setSubmitter(submitter);
			upfile.setPhone(phone);
			upfile.setMail(mail);
			upfile.setDownloadName(filename);
			upfile.setDownloadUrl(fileurl);
			upfile.setOperate_time(new Date());
			int i=uploadService.saveUploadFile(upfile);
			if(i==1){
				new AjaxUtil(request, response).JsonType("200", "保存成功！", "", "uploadFile", "", "", "保存成功！",true);
			}else{
				new AjaxUtil(request, response).JsonType("300", "保存失败！", "", "uploadFile", "", "", "保存失败",true);	
			}
		}catch (Exception ex) {
			String emsg = "failed: "+ex.toString();
			JJLogger.info(emsg);
		}
	}
	
	/**
	 * 收藏
	 * @param id
	 * @param types
	 * @param session
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/collection")
	public void collect(@RequestParam long id, @RequestParam byte types, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			UserLogin userLogin = (UserLogin) session.getAttribute(Const.SESSION_CLIENT_USER);
				if(userLogin!=null){
				String msg = productService.collect(id, userLogin.getId(), types);
				new AjaxUtil(request, response).print(msg);
			}else{
				new AjaxUtil(request, response).print("usernull");
			}
		} catch (Exception e) {
			new AjaxUtil(request, response).print("error");
		}
	}
	
	/**
	 * 异步获取科室
	 * @param id
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/department")
	public void getDepartment(@RequestParam int id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			List<Department> departments = departmentMapper.selectByParentId(id);
			new AjaxUtil(request, response).print(departments);
		} catch (Exception e) {
			new AjaxUtil(request, response).print("error");
		}
	}
	
	/**
	 * 评论
	 * @param c
	 * @param comment
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/comments")
	public String comments(@RequestParam String articleId, @RequestParam String typ, 
			Comment comment, HttpSession session) throws Exception {
		UserLogin userLogin = (UserLogin) session.getAttribute(Const.SESSION_CLIENT_USER);
		String content = comment.getContent();
		// 判断是否有脏字
		Dirty dirty = dirtyMapper.selectByPrimaryKey(1);
		
		if (!StringUtils.isEmpty(dirty.getContent())) {
			String[] arr = StringUtils.split(dirty.getContent(), ",");
			
			if (!ArrayUtils.isEmpty(arr)) {
				for (String str : arr) {
					content = StringUtils.replace(content, str, "**");
				}
			}
			
			String[] arr1 = StringUtils.split(dirty.getContent(), "，");
			
			if (!ArrayUtils.isEmpty(arr1)) {
				for (String str : arr1) {
					content = StringUtils.replace(content, str, "**");
				}
			}
			
		}
		
		comment.setContent(content);
		comment.setUserId(userLogin.getId());
		comment.setCreateTime(new Date());
		commentMapper.insertSelective(comment);
		return "redirect:/literature/detail.html?articleId=" + articleId + "&typ=" + typ;
	}
	
	/**
	 * 退出
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) throws Exception {
		session.setAttribute(Const.SESSION_CLIENT_USER, null);
		return "redirect:index.html";
	}
	
}