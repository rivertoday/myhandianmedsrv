package com.hrsb.cg.controller.manage;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hrsb.cg.model.Permission;
import com.hrsb.cg.service.PermissionService;
import com.hrsb.cg.util.AjaxUtil;

@Controller
@RequestMapping("/permission")
public class PermissionController {
	@Autowired
	PermissionService permissionService;

	@RequestMapping("/list")
	public ModelAndView findPermission(Integer mid) {
		ModelAndView mv = new ModelAndView("manage/permission");
		List<Permission> ps = permissionService.getMenus();
		mv.addObject("mid", mid);
		mv.addObject("permissions", ps);
		return mv;
	}
	
	@RequestMapping("/fp")
	public ModelAndView findMenusByParentId(Integer parentid){
		List<Permission> ms = permissionService.getMenus();
		List<Permission> ms2 = permissionService.getPermissionByParentId(parentid);
		return new ModelAndView("/manage/permission").addObject("permissions", ms).addObject("menubodys", ms2).addObject("parentid", parentid);
	}
	
	/**
	 * 进入修改页面
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("mod")
	public ModelAndView modPermissions(Integer id,HttpServletRequest request,HttpServletResponse response){
		ModelAndView model = new ModelAndView("manage/permission-mod");
		Permission permission = permissionService.selectByPrimaryKey(id);
		model.addObject("permission", permission);
		return model;
	}
	
	/**
	 * 进入添加页面
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("add")
	public ModelAndView addPermissions(Integer parentid,HttpServletRequest request,HttpServletResponse response){
		ModelAndView model = new ModelAndView("manage/permission-add");
		model.addObject("parentid", parentid);
		return model;
	}
	
	/**
	 * 修改保存
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@SuppressWarnings("static-access")
	@RequestMapping("/update")
	public void updatePermissions(Integer id,HttpServletRequest request,HttpServletResponse response) throws IOException{
		String title = request.getParameter("title");
		String sort = request.getParameter("sort");
		
		Permission permission = new Permission();
		permission.setId(id);
		permission.setTitle(title);
		permission.setSort(Integer.parseInt(sort));
		
		int row = permissionService.updateByPrimaryKeySelective(permission);
		
		if(row > 0){
			new AjaxUtil(request, response).JsonType("200", "操作完成", "", "permission", "", "", "",true);
		}else{
			new AjaxUtil(request, response).JsonType("200", "操作失败", "", "permission", "", "", "",true);
		}
	}
	
	/**
	 * 添加保存
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@SuppressWarnings("static-access")
	@RequestMapping("/save")
	public void savePermissions(Integer id,HttpServletRequest request,HttpServletResponse response) throws IOException{
		String title = request.getParameter("title");
		String parentid = request.getParameter("parentid");
		String sort = request.getParameter("sort");
		Permission permission = new Permission();
		permission.setTitle(title);
		permission.setOperatime(new Date());
		permission.setOperator(1);
		permission.setStatus(0);
		permission.setParentid(Integer.parseInt(parentid));
		permission.setSort(Integer.parseInt(sort));
		
		int row = permissionService.insertSelective(permission);
		
		if(row > 0){
			new AjaxUtil(request, response).JsonType("200", "操作完成", "", "permission", "", "", "",true);
		}else{
			new AjaxUtil(request, response).JsonType("200", "操作失败", "", "permission", "", "", "",true);
		}
	}
	
	/**
	 * 删除
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@SuppressWarnings("static-access")
	@RequestMapping("/del")
	public void delPermissions(Integer id,HttpServletRequest request,HttpServletResponse response) throws IOException{
		Permission permission = new Permission();
		permission.setId(id);
		int row = permissionService.deleteByPrimaryKey(id);
		
		if(row > 0){
			new AjaxUtil(request, response).JsonType("200", "操作完成", "", "permission", "", "", "");
		}else{
			new AjaxUtil(request, response).JsonType("200", "操作失败", "", "permission", "", "", "");
		}
	}
	
}
