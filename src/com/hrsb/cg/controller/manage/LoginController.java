package com.hrsb.cg.controller.manage;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hrsb.cg.model.LoginLog;
import com.hrsb.cg.model.Manager;
import com.hrsb.cg.model.ManagerRole;
import com.hrsb.cg.model.Permission;
import com.hrsb.cg.model.PermissionRole;
import com.hrsb.cg.model.Role;
import com.hrsb.cg.service.LoginLogService;
import com.hrsb.cg.service.ManagerRoleService;
import com.hrsb.cg.service.ManagerService;
import com.hrsb.cg.service.RoleService;
import com.hrsb.cg.util.AjaxUtil;
import com.hrsb.cg.util.CodeUtil;
import com.hrsb.cg.util.Const;

@Controller
@RequestMapping(value="/manage")
public class LoginController {
	
	@Autowired
	ManagerService managerService;
	@Autowired
	ManagerRoleService managerRoleService;
	@Autowired
	RoleService roleService;
	@Autowired
	LoginLogService loginLogService;
	
	/**
	 * 跳轉登錄
	 */
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String tologin(){
		return "manage/login";
	}
	/**
	 * 登出
	 * @param session
	 * @return
	 */
	@RequestMapping(value="logout")
	public String logout(HttpSession session){
		session.removeAttribute(Const.SESSION_MANAGER);
		return "manage/login";
	}
	/**
	 * 登錄驗證，獲取用戶信息
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/login")
	public ModelAndView login(HttpSession session,@RequestBody@ModelAttribute Manager manager,@Param(value="checkcode")String checkcode,HttpServletRequest request) throws IOException{
		ModelAndView model = new ModelAndView();
		Manager m = managerService.findManager(manager); 
		String code = session.getAttribute(Const.CHECK_CODE).toString();
		if(!checkcode.equalsIgnoreCase(code)){
			model.addObject("error", "验证码不正确");
		}else if(m==null){
			model.addObject("error", "用户名不存在");
		}else if(!CodeUtil.check(m.getPassword(), manager.getPassword())){
			model.addObject("error", "用户名或密码错误！");
		}else if("1".equalsIgnoreCase(m.getStatus())){
			model.addObject("error", "用户名被冻结,请联系管理员");
		}else{
			LoginLog ll = new LoginLog();
			ll.setCreatetime(new Date());
			ll.setIp(AjaxUtil.getIpAddress(request));
			ll.setOperatime(new Date());
			ll.setOperator(m.getId());
			ll.setStatus(0);
			ll.setUserid(m.getId());
			loginLogService.insertSelective(ll);
			session.setAttribute(Const.SESSION_MANAGER, m);
			model.setViewName("redirect:/manage/index.im");
			return model;
		}
		model.setViewName("manage/login");
		return model;
	}
	/**
	 * 用戶登錄成功，獲取權限
	 * @param session
	 * @return
	 */
	@RequestMapping(value="index")
	public ModelAndView index(HttpSession session){
		ModelAndView view = new ModelAndView("manage/index");
		Manager manager = (Manager) session.getAttribute(Const.SESSION_MANAGER);
		if(manager==null){
			view.setViewName("manage/login");
		return view;}
		Map<Long,Permission> ps = new HashMap<Long,Permission>();
		List<ManagerRole> mr = managerRoleService.getRoleByManager(manager.getId());
		for (int i = 0; i < mr.size(); i++) {
			Role role = roleService.selectByPrimaryKey(mr.get(i).getRoleid());
			if(role.getStatus()==0){
				List<PermissionRole> pr = role.getPr();
				for (int j = 0; j < pr.size(); j++) {
					if(pr.get(j).getPermission() != null){
						ps.put(Long.parseLong(pr.get(j).getPermission().getId().toString()), pr.get(j).getPermission());
					}
				}
			}
		}
		view.addObject("loginlogs",loginLogService.findTen(manager.getId()));
		session.setAttribute(Const.SESSION_MANAGER_ROLE, ps);
		//获取权限
		return view;
	}
}
