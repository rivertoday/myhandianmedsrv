package com.hrsb.cg.controller.manage;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.hrsb.cg.controller.util.BaseLogsUtil;
import com.hrsb.cg.model.BaseLog;
import com.hrsb.cg.model.Manager;
import com.hrsb.cg.model.ManagerRole;
import com.hrsb.cg.model.Module;
import com.hrsb.cg.model.Role;
import com.hrsb.cg.service.BaseLogService;
import com.hrsb.cg.service.ManagerRoleService;
import com.hrsb.cg.service.ManagerService;
import com.hrsb.cg.service.ModuleService;
import com.hrsb.cg.service.RoleService;
import com.hrsb.cg.util.AjaxUtil;
import com.hrsb.cg.util.CodeUtil;
import com.hrsb.cg.util.Const;
import com.hrsb.cg.util.DateUtil;
import com.hrsb.cg.util.IDUtil;
import com.hrsb.cg.util.MapUtil;
import com.hrsb.cg.util.Page;
import com.hrsb.cg.util.Tools;

@Controller
@RequestMapping("m2")
public class ManagerController {
	@Autowired
	ManagerService managerService;
	@Autowired
	ModuleService moduleService;
	@Autowired
	RoleService roleService;
	@Autowired
	BaseLogService baseLogService;
	@Autowired
	ManagerRoleService managerRoleService;
	/**
	 * 修改密码
	 */
	@RequestMapping(value="/cp",method=RequestMethod.POST)
	public void changePassword(String oldpwd,String newpwd,HttpSession session,HttpServletRequest request,HttpServletResponse response){
		Manager manager = (Manager) session.getAttribute(Const.SESSION_MANAGER);
		try{
			if(!CodeUtil.check(manager.getPassword(), oldpwd)){
				new AjaxUtil(request, response).JsonType("300", "原密码校验失败", "", "", "", "", "修改密码失败");
			}else{
				manager.setPassword(CodeUtil.jiami(newpwd));
				managerService.updateByPrimaryKeySelective(manager);
				session.setAttribute(Const.SESSION_MANAGER, manager);
				new AjaxUtil(request, response).JsonType("200", "修改成功！", "", "pagination", "", "closeCurrent", "密码修改成功！");
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	@RequestMapping("/cp2")
	public void changePwd2(String newpwd,Integer mid,HttpServletRequest request,HttpServletResponse response) throws IOException{
		Manager manager = managerService.selectByPrimaryKey(mid);
		try{
				manager.setPassword(CodeUtil.jiami(newpwd));
				managerService.updateByPrimaryKeySelective(manager);
				new AjaxUtil(request, response).JsonType("200", "修改成功！", "", "pagination", "", "closeCurrent", "密码修改成功！",true);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			new AjaxUtil(request, response).JsonType("300", "修改失败", "", "", "", "closeCurrent", "",true);
		}
	}
	//批量获取管理员
	@RequestMapping(value="/g1")
	public ModelAndView selectmanage(Map<String,Object> params,Integer pageSize,Integer pageCurrent,HttpServletRequest request,HttpServletResponse response){
		String username = request.getParameter("username");
		Page<Manager> page=new Page<Manager>().setPageNo(pageCurrent==null?1:pageCurrent).setPageSize(pageSize==null?15:pageSize).setParams(params);
		page.getParams().put("username", username);
		List<Manager> managers=managerService.selectPage(page);
		page.setResults(managers);
		ModelAndView mv = new ModelAndView("manage/manager");
		mv.addObject("roles", roleService.getAll(new Role().setStatus(0)));
		mv.addObject("page", page);
		return mv;
	}
	//获取单个管理员
	@RequestMapping(value="g2")
	public ModelAndView getManagerById(Integer mid,HttpSession session,HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new ModelAndView("manage/editmanager");
		if(mid!=null){
			Manager m = managerService.selectByPrimaryKey(mid);
			mv.addObject("manager", m);
		}
		return mv;
	}
	//编辑管理员
	@RequestMapping(value="e1")
	public void editManager(@RequestBody@ModelAttribute Manager manager,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws IOException{
		int row = 0;
		Manager m2 = (Manager) session.getAttribute(Const.SESSION_MANAGER);
		manager.setOperatime(new Date());
		manager.setOperator(m2.getId());
		if(manager.getId()==null){
			manager.setCreatetime(new Date());
			manager.setPassword(CodeUtil.jiami(manager.getPassword()));
			manager.setIdkey(IDUtil.manegerID());
			BaseLog bs = new BaseLog(m2.getIdkey(),new Date(),"操作员管理",manager.getIdkey(),"添加操作员信息",0,0);
			if(managerService.findManager(new Manager().setUsername(manager.getUsername()))!=null){
				bs.setStatus(1);
				baseLogService.insertSelective(bs);
				new AjaxUtil(request, response).JsonType("300", "操作失败", "", "", "", "", "操作失败");
				return;
			}
			row = managerService.insertSelective(manager);
			baseLogService.insertSelective(bs);
		}else{
			if(manager.getPassword().length()!=32){
				manager.setPassword(CodeUtil.jiami(manager.getPassword()));
			}
			BaseLog bs = new BaseLog(m2.getIdkey(),new Date(),"操作员管理",manager.getIdkey(),"修改操作员信息",0,0);
			row = managerService.updateByPrimaryKeySelective(manager);
			baseLogService.insertSelective(bs);
		}
		if(row>0){
			new AjaxUtil(request, response).JsonType("200", "操作完成", "", "manager", "", "", "操作完成",true);
		}else{
			new AjaxUtil(request, response).JsonType("300", "操作失败", "", "manager", "", "", "操作失败",true);
		}
	}
	//批量删除
	@RequestMapping(value="d1")
	public void deleteManager(String ids,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<String> list = Arrays.asList(ids.split(","));
		int row = managerService.deleteManager(list);
		if(row>0){
			new AjaxUtil(request, response).JsonType("200", "操作完成", "", "manager", "", "", "");
		}else{
			new AjaxUtil(request, response).JsonType("300", "操作失败", "", "manager", "", "", "操作失败");
		}
	}
	//冻结
	@RequestMapping(value="d2")
	public void dingjieManage(Integer mid,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws IOException{
		Manager m2 = (Manager) session.getAttribute(Const.SESSION_MANAGER);
		Manager manager = managerService.selectByPrimaryKey(mid);
		BaseLog b = null;
		if(manager.getStatus()==null||manager.getStatus().equals("1")){
			manager.setStatus("0");
			b = new BaseLog(m2.getIdkey(), new Date(), "操作员管理", manager.getIdkey(), "启用用户", 0, 0);
		}else{
			manager.setStatus("1");
			b = new BaseLog(m2.getIdkey(), new Date(), "操作员管理", manager.getIdkey(), "冻结用户", 0, 0);
		}
		int row = managerService.updateByPrimaryKeySelective(manager);
		if(row>0){
			BaseLogsUtil.insertBaseLog(b.setStatus(0));
			new AjaxUtil(request, response).JsonType("200", "操作完成", "", "manager", "", "", "");
		}else{
			BaseLogsUtil.insertBaseLog(b.setStatus(1));
			new AjaxUtil(request, response).JsonType("300", "操作失败", "", "manager", "", "", "操作失败");
		}
	}
	@RequestMapping(value="sr")
	public void setRoleOfManager(Integer mid,String rids,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		try{
			int row = 0;
			Manager m2 = (Manager) session.getAttribute(Const.SESSION_MANAGER);
			managerRoleService.deleteByManager(mid);
			if(rids!=null){
				String [] ids = rids.split(",");
				for (int i = 0; i < ids.length; i++) {
					if(!StringUtils.isBlank(ids[i])){
						ManagerRole mr = new ManagerRole();
						mr.setOperatime(new Date());
						mr.setOperator(m2.getId());
						mr.setRoleid(Integer.parseInt(ids[i]));
						mr.setManageid(mid);
						managerRoleService.insert(mr);
					}
				}
			}
			new AjaxUtil(request, response).JsonType("200", "操作完成", "", "manager", "", "", "");
		}catch (Exception e) {
			new AjaxUtil(request, response).JsonType("300", "操作失败", "", "manager", "", "", "操作失败");
		}
	}
	@RequestMapping("/ep")
	public void ExportSystemLog(String expids,HttpServletResponse response){
		 	response.setContentType("application/binary;charset=ISO8859_1");  
	        try  
	        {
	            ServletOutputStream outputStream = response.getOutputStream();  
	            String fileName = new String(("操作人员信息记录"+DateUtil.formatDate(new Date(), "yyyy-MM-dd")).getBytes(), "ISO8859_1");  
	            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式  
	            List<Manager> sl = null;
	            if(StringUtils.isBlank(expids)){
	            	sl = managerService.getAll();
	            }else{
	            	sl = managerService.getAll(Arrays.asList(expids.split(",")));
	            }
	            String[] titles = { "操作员编号", "用户名", "姓名","联系电话","创建时间","修改时间","状态"};  
	            managerService.exportExcel(sl, titles, outputStream);
	        }  
	        catch (IOException e)  
	        {  
	            e.printStackTrace();  
	        }  
	}
}
