package com.hrsb.cg.controller.phoneweb;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hrsb.cg.model.About;
import com.hrsb.cg.model.CodeRecord;
import com.hrsb.cg.model.Comment;
import com.hrsb.cg.model.Department;
import com.hrsb.cg.model.Dirty;
import com.hrsb.cg.model.UserDetail;
import com.hrsb.cg.model.UserLogin;
import com.hrsb.cg.util.AjaxUtil;
import com.hrsb.cg.util.Const;
import com.hrsb.cg.util.MD5;
import com.hrsb.cg.util.Tools;

/**
 * 首页控制类
 * @author app001
 *
 */
@Controller
@RequestMapping(value = "/phoneweb")
public class PhoneWebIndexController extends PhoneWebController {
	
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
		// 来源为：手机站
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
				detail.setSource("手机站");
				detail.setDownloadTypes((byte) 1);
				detail.setDownloadCount(Const.DOWNLOADCOUNT);
				userDetailMapper.insertSelective(detail);
				session.setAttribute(Const.SESSION_PHONE_USER, login);
				flag = true;
			} else {
				message = "验证码错误";
			}
		} else {
			message = "用户已存在";
		}
		
		if (flag) {
			return "redirect:/phoneweb/literature/homepage.html";
		} else {
			session.setAttribute("message", message);
			return "redirect:/redirect.html?path=phoneweb/register";
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
			HttpSession session) {
		UserLogin userLogin = userLoginMapper.selectByPhoneAndPassword(phone, MD5.convertToMD5(password));
		
		if (null != userLogin && userLogin.getFrozen() == 0) {
			session.setAttribute(Const.SESSION_PHONE_USER, userLogin);
		} else {
			session.setAttribute("message", "用户不存在或者被冻结");
		}
		
		return "redirect:/phoneweb/literature/homepage.html";
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
	@RequestMapping(value = "/about.json")
	public String about(ModelMap modelMap) {
		About about = aboutMapper.selectByPrimaryKey(1);
		modelMap.put("about", about);		
		return "phoneweb/about";
	}
	
	/**
	 * 用户协议
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/agreement")
	public String agreement(ModelMap modelMap) {
		About about = aboutMapper.selectByPrimaryKey(2);
		modelMap.put("about", about);
		return "phoneweb/agreement";
	}
	
	/**
	 * 用户协议2
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/agreement2")
	public String agreement2(ModelMap modelMap) {
		About about = aboutMapper.selectByPrimaryKey(2);
		modelMap.put("about", about);
		return "phoneweb/agreement2";
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
	public String comment(@RequestParam String articleId, @RequestParam String typ, 
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
		return "redirect:/phoneweb/literature/detail.html?articleId=" + articleId + "&types=" + typ;
	}
	
	/**
	 * 退出
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) throws Exception {
		session.setAttribute(Const.SESSION_PHONE_USER, null);
		return "redirect:/redirect.html?path=phoneweb/login";
	}
	
	/**
	 * 收藏
	 * @param id
	 * @param c
	 * @param modelMap
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/collection")
	public void collect(@RequestParam long id, @RequestParam byte types,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		try {
			UserLogin userLogin = (UserLogin) session.getAttribute(Const.SESSION_PHONE_USER);
			String msg = productService.collect(id, userLogin.getId(), types);
			new AjaxUtil(request, response).print(msg);
		} catch (Exception e) {
			new AjaxUtil(request, response).print("error");
		}
	}
	
	/**
	 * 下载
	 * @param id
	 * @param c
	 * @param modelMap
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/download")
	public void download(@RequestParam long id, byte types, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		try {
			UserLogin userLogin = (UserLogin) session.getAttribute(Const.SESSION_PHONE_USER);
			String msg = productService.download(id, userLogin.getId(), types);
			new AjaxUtil(request, response).print(msg);
		} catch (Exception e) {
			new AjaxUtil(request, response).print("error");
		}
	}
	
	/**
	 * H5评论
	 * @param c
	 * @param comment
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/comment")
	public String comments(@RequestParam String articleId, @RequestParam String type, 
			Comment comment, HttpSession session) throws Exception {
		String content=comment.getContent();
		if (!StringUtils.isEmpty(content)) {
			comment.setContent(Tools.filterEmojiString(content));
		
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
			baseService.saveComment(comment, session);
		}
		return "redirect:/phoneweb/literature/detail.html?articleId=" + articleId + "&types=" + type;
	}
	
}
