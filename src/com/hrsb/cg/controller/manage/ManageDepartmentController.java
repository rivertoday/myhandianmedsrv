package com.hrsb.cg.controller.manage;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hrsb.cg.controller.util.BaseLogsUtil;
import com.hrsb.cg.dao.DepartmentMapper;
import com.hrsb.cg.model.BaseLog;
import com.hrsb.cg.model.Department;
import com.hrsb.cg.model.HospitalGrade;
import com.hrsb.cg.model.Manager;
import com.hrsb.cg.util.AjaxUtil;
import com.hrsb.cg.util.Const;
import com.hrsb.cg.util.Page;

/**
 * 部门管理控制类
 * @author app001
 *
 */
@Controller
@RequestMapping(value="department")
public class ManageDepartmentController {
	
	@Autowired
	DepartmentMapper departmentMapper;
	
	/**
	 * 列表页
	 * @param pageCurrent
	 * @param pageSize
	 * @param name
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/list")
	public ModelAndView menulist(@RequestParam(required = false) Integer pageCurrent, 
			@RequestParam(required = false) Integer pageSize, 
			String name, HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("manage/department/list");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		Page<Department> page = new Page<Department>(pageCurrent, pageSize).setParams(params);
		page.setOrderField("sorts");
		page.setOrderDirection("asc");
		List<Department> departments = departmentMapper.selectByPage(page);
		page.setResults(departments);
		modelAndView.addObject("page", page);
		return modelAndView;
		
	}
	
	/**
	 * 编辑/添加
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(Department department, HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("manage/department/edit");
		
		if (null != department.getId()) {
			department = departmentMapper.selectByPrimaryKey(department.getId());
			modelAndView.addObject("department", department);
		}
		
		return modelAndView;
		
	}
	
	/**
	 * 保存
	 * @param helpCenterMenu
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/save")
	public void save(Department department, HttpServletRequest request, HttpServletResponse response) throws IOException{
		Manager manager = (Manager) request.getSession().getAttribute(Const.SESSION_MANAGER);
		int row = 0;
		department.setManagerId(manager.getId());
		department.setOperateTime(new Date());
		department.setStatus((byte) 1);
		
		if (null != department.getId()) {
			row = departmentMapper.updateByPrimaryKeySelective(department);
		} else {
			row = departmentMapper.insertSelective(department);
		}
		
		if(row > 0){
			//保存操作日志
			BaseLog bs = new BaseLog(manager.getIdkey(), new Date(), "添加/编辑科室", manager.getIdkey(), "添加/编辑科室", 0, 0);
			BaseLogsUtil.insertBaseLog(bs);
			new AjaxUtil(request, response).JsonType("200", "操作成功", "", "departmentlist", "", "", "操作成功", true);
		}else{
			new AjaxUtil(request, response).JsonType("300", "操作失败", "", "departmentlist", "", "", "操作失败", true);
		}
	}
	
	@RequestMapping(value="/status")
	public void status(Department department, HttpServletRequest request, HttpServletResponse response) throws IOException{
		Manager m=(Manager)request.getSession().getAttribute(Const.SESSION_MANAGER);
		department.setManagerId(m.getId());
		department.setOperateTime(new Date());
		int i = departmentMapper.updateByPrimaryKeySelective(department);
		
		if (i == 1) {
			new AjaxUtil(request, response).JsonType("200", "删除成功！", "", "departmentlist", "", "", "删除成功！",false);
		} else {
			new AjaxUtil(request, response).JsonType("300", "删除失败！", "", "departmentlist", "", "", "删除失败",false);	
		}
	}
	
}
