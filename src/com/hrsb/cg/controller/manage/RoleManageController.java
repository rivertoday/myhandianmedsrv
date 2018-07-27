package com.hrsb.cg.controller.manage;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hrsb.cg.model.Manager;
import com.hrsb.cg.model.ManagerRole;
import com.hrsb.cg.model.Module;
import com.hrsb.cg.model.PermissionRole;
import com.hrsb.cg.model.Role;
import com.hrsb.cg.service.GroupService;
import com.hrsb.cg.service.ManagerRoleService;
import com.hrsb.cg.service.ModuleService;
import com.hrsb.cg.service.PermissionRoleService;
import com.hrsb.cg.service.PermissionService;
import com.hrsb.cg.service.RoleService;
import com.hrsb.cg.util.AjaxUtil;
import com.hrsb.cg.util.Const;
import com.hrsb.cg.util.JsonUtil;
import com.hrsb.cg.util.Page;

@Controller
@RequestMapping("role")
public class RoleManageController {
	@Autowired
	RoleService roleService;
	@Autowired
	PermissionService permissionService;
	@Autowired
	GroupService groupService;
	@Autowired
	ModuleService moduleService;
	@Autowired
	PermissionRoleService permissionRoleService;
	@Autowired
	ManagerRoleService managerRoleService;
//	//查询所有部门
//	@RequestMapping(value="modules")
//	public ModelAndView getModules(@RequestBody@ModelAttribute Module module,Integer pageSize,Integer pageNo,String orderField,String orderDirection,HttpServletRequest request,HttpServletResponse response){
//		ModelAndView mv = new ModelAndView("manage/module");
//		Page<Module> page = new Page<Module>().setPageNo(pageNo==null?1:pageNo).setPageSize(pageSize==null?15:pageSize).setOrderDirection(orderDirection).setOrderField(orderField);
//		List<Module> ms = moduleService.getModuleByPage(page);
//		page.setResults(ms);
//		mv.addObject("page", page);
//		return mv;
//	}
//	//查询单个部门
//	@RequestMapping(value="gm")
//	public ModelAndView getModuleById(Integer mid,HttpServletRequest request,HttpServletResponse response){
//		ModelAndView mv = new ModelAndView("manage/editmodule");
//		if(mid!=null){
//			mv.addObject("module", moduleService.selectByPrimaryKey(mid));
//		}
//		List<Module> modules = moduleService.getAll();
//		mv.addObject("modules", modules);
//		return mv;
//	}
//	//禁用部门
//	@RequestMapping(value="dj")
//	public void dongjieM(HttpSession session,Integer mid,HttpServletRequest request,HttpServletResponse response) throws IOException{
//		Module module = moduleService.selectByPrimaryKey(mid);
//		if(module.getStatus()==null||module.getStatus()==0){
//			module.setStatus(1);
//		}else{
//			module.setStatus(0);
//		}
//		module.setOperatime(new Date());
//		module.setOperator(((Manager)session.getAttribute(Const.SESSION_MANAGER)).getId());
//		Integer row = moduleService.updateByPrimaryKeySelective(module);
//		if(row>0){
//			new AjaxUtil(request, response).JsonType("200", "操作完成", "", "module", "", "", "");
//		}else{
//			new AjaxUtil(request, response).JsonType("300", "删除失败", "", "module", "", "", "");
//		}
//	}
//	//删除部门
//	@RequestMapping(value="dm")
//	public void delM(Integer mid,String delids,HttpServletRequest request,HttpServletResponse response) throws IOException{
//		String[] idss = delids.split(",");
//		Integer row = 0;
//		if(mid!=null){
//			row = moduleService.deleteByPrimaryKey(mid);
//		}else{
//			row = moduleService.deleteSome(Arrays.asList(idss));
//		}
//		if(row>0){
//			new AjaxUtil(request, response).JsonType("200", "操作完成", "", "module", "", "", "");
//		}else{
//			new AjaxUtil(request, response).JsonType("300", "删除失败", "", "module", "", "", "");
//		}
//	}
//	//编辑部门
//	@RequestMapping(value="em")
//	public void editM(@RequestBody@ModelAttribute Module module,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
//		module.setOperatime(new Date());
//		module.setOperator(((Manager)session.getAttribute(Const.SESSION_MANAGER)).getId());
//		int row = 0;
//		if(module.getId()!=null){
//			row = moduleService.updateByPrimaryKeySelective(module);
//		}else{
//			module.setStatus(0);
//			row = moduleService.insertSelective(module);
//		}
//		if(row>0){
//			new AjaxUtil(request, response).JsonType("200", "操作完成", "", "module", "", "", "",true);
//		}else{
//			new AjaxUtil(request, response).JsonType("300", "删除失败", "", "module", "", "", "",true);
//		}
//	}
	//获取所有角色
	@RequestMapping("fr")
	public ModelAndView findRoles(@RequestBody@ModelAttribute Role role,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		ModelAndView mv = new ModelAndView("manage/rolemanage");
		List<Role> roles = roleService.getAll(role);
		mv.addObject("roles", roles);
		return mv;
	}
	//获取单个角色
	@RequestMapping(value="gr")
	public ModelAndView findRoleById(Integer rid,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		ModelAndView mv = new ModelAndView("manage/editrole");
		if(rid!=null){
			Role role = roleService.selectByPrimaryKey(rid);
			mv.addObject("role", role);
			List<PermissionRole> pr =role.getPr();
			String psid = "";
			String psname="";
			for(int i =0;i<pr.size();i++){
				if(pr.get(i).getPermission() != null){
					psid+=pr.get(i).getPermission().getId()+",";
					psname+=pr.get(i).getPermission().getTitle()+",";
				}
			}
			if(psid.length()>0){
				psid = psid.substring(0, psid.length()-1);
				psname = psname.substring(0, psname.length()-1);
			}
			mv.addObject("psid", psid);
			mv.addObject("psname", psname);
		}
		mv.addObject("ps", permissionService.getAll());
		return mv;
	}
	//编辑角色
	@RequestMapping(value="er")
	public void editRole(@RequestBody@ModelAttribute Role role,String pids,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		Manager manager = (Manager) session.getAttribute(Const.SESSION_MANAGER);
		int row = 0;
		role.setOperator(((Manager)session.getAttribute(Const.SESSION_MANAGER)).getId());
		role.setOperatime(new Date());
		if(role.getId()!=null){
			row = roleService.updateByPrimaryKeySelective(role);
		}else{
			row = roleService.insertSelective(role);
		}
		if(row>0){
			permissionRoleService.deleteByRole(role.getId());
			String [] ps = pids.split(",");
			for (int i = 0; i < ps.length; i++) {
				if(!StringUtils.isBlank(ps[i])){
					PermissionRole pr = new PermissionRole();
					pr.setOperator(manager.getId());
					pr.setOperatime(new Date());
					pr.setPermissionid(Integer.parseInt(ps[i]));
					pr.setRoleid(role.getId());
					permissionRoleService.insert(pr);
				}
			}
			new AjaxUtil(request, response).JsonType("200", "操作完成", "", "role", "", "", "操作完成",true);
		}else{
			new AjaxUtil(request, response).JsonType("300", "操作失败", "", "role", "", "", "操作失败",true);
		}
	}
	//冻结角色
	@RequestMapping(value="dr")
	public void dongjieRole(Integer rid,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		try{
		Manager manager = (Manager) session.getAttribute(Const.SESSION_MANAGER);
		int row = 0;
		Role role = roleService.selectByPrimaryKey(rid);
		role.setOperator(((Manager)session.getAttribute(Const.SESSION_MANAGER)).getId());
		role.setOperatime(new Date());
		if(role.getStatus()==null||role.getStatus()==1){
			role.setStatus(0);
		}else{
			role.setStatus(1);
		}
		row = roleService.updateByPrimaryKey(role);
		if(row>0){
			new AjaxUtil(request, response).JsonType("200", "操作完成", "", "role", "", "", "");
		}else{
			new AjaxUtil(request, response).JsonType("300", "操作失败", "", "role", "", "", "操作失败");
		}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//
	@RequestMapping(value="delr")
	public void deleteRole(String ids,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		List<String> list = Arrays.asList(ids.split(","));
		int row = roleService.deleteRoleByIds(list);
		if(row>0){
			new AjaxUtil(request, response).JsonType("200", "操作完成", "", "role", "", "", "");
		}else{
			new AjaxUtil(request, response).JsonType("300", "操作失败", "", "role", "", "", "操作失败");
		}
	}
	@RequestMapping(value="fmr")
	public void getManagerRole(Integer mid,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception{
		List<ManagerRole> mrs = managerRoleService.getRoleByManager(mid);
		new AjaxUtil(request, response).print(JsonUtil.listtojson(mrs));
	}
}
