package com.hrsb.cg.controller.client;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hrsb.cg.dao.AreaCityMapper;
import com.hrsb.cg.dao.AreaCountyMapper;
import com.hrsb.cg.dao.AreaProvMapper;
import com.hrsb.cg.dao.DepartmentMapper;
import com.hrsb.cg.dao.HospitalGradeMapper;
import com.hrsb.cg.model.AreaCity;
import com.hrsb.cg.model.AreaCounty;
import com.hrsb.cg.model.AreaProv;
import com.hrsb.cg.model.Department;
import com.hrsb.cg.model.HospitalGrade;
import com.hrsb.cg.service.UserService;
import com.hrsb.cg.util.AjaxUtil;
@Controller
public class BaseController {
	
	@Autowired
	AreaProvMapper areaProvMapper;
	
	@Autowired
	AreaCityMapper areaCityMapper;
	
	@Autowired
	AreaCountyMapper areaCountyMapper;
	
	@Autowired
	HospitalGradeMapper hospitalGradeMapper;
	
	@Autowired
	DepartmentMapper departmentMapper;
	
	@Autowired
	UserService userService;
	
	
	
	/**
	 * 获取所有的省
	 * @return
	 */
	public List<AreaProv> getAllProvince() {
		return areaProvMapper.findAllProv();
	}
	
	
	/**
	 * 根据省code获取市
	 * @param provCode
	 * @return
	 */
	public List<AreaCity> getCityByProvCode(String provCode) {
		return areaCityMapper.selectAllCity(provCode);
	}
	
	/**
	 * 根据市code获取县
	 * @param provCode
	 * @return
	 */
	public List<AreaCounty> getCountyByCityCode(String cityCode) {
		return areaCountyMapper.selectAllCounty(cityCode);
	}
	

	/**
	 * 获取医院等级职称
	 * @param types
	 * @return
	 */
	public List<HospitalGrade> hospitalGradeList(Integer types){
		List<HospitalGrade> hospitalGrades=hospitalGradeMapper.selectByType(types);
		
		return hospitalGrades;
	}
	
	/**
	 * 获取顶级科室
	 * @param types
	 * @return
	 */
	public List<Department> departmentList(){
		List<Department> departmentList=departmentMapper.TopDepartment();
		
		return departmentList;
	}
	
	
	/**
	 * 异步获取对应省下的市
	 * @param province
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/city")
	public void city(@RequestParam String province, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<AreaCity> areaCities = getCityByProvCode(province);
		new AjaxUtil(request, response).print(areaCities);
	}
	
	/**
	 * 异步获取对应市下的县
	 * @param province
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/county")
	public void county(@RequestParam String city, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<AreaCounty> areaCounties = getCountyByCityCode(city);
		new AjaxUtil(request, response).print(areaCounties);
	}
	
	/**
	 * 验证手机号是否注册
	 * @param province
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/valPhone")
	public void valPhone(@RequestParam String loginName, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean flag=userService.isExistLoginName(loginName);
		if(flag){
			new AjaxUtil(request, response).print("ok");
		}else{
			new AjaxUtil(request, response).print("isExist");
		}
	}
	
	/**
	 * 替换表格无border等
	 * @param content
	 */
	public String replaceContent(String content, HttpServletRequest request) {
		content = StringUtils.replace(content, "<img src=\"", "<img src=\"" + "handian.com01.org:8855");
		content = StringUtils.replace(content, "<table", "<table border=\"1\" cellspacing=\"0\" cellpadding=\"0\"");
		return content;
	}
	
}
