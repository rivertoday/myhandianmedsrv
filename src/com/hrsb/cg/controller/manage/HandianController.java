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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hrsb.cg.dao.HospitalGradeMapper;
import com.hrsb.cg.model.HospitalGrade;
import com.hrsb.cg.model.Manager;
import com.hrsb.cg.model.UserKeyword;
import com.hrsb.cg.util.AjaxUtil;
import com.hrsb.cg.util.Const;
import com.hrsb.cg.util.Page;

@Controller
@RequestMapping(value="hd")
public class HandianController {
	
	@Autowired
	HospitalGradeMapper hospitalGradeMapper;
	
	@RequestMapping(value="hospitalGradeList")
	public ModelAndView hospitalGradeList(@RequestParam(defaultValue="1",required=false)Integer pageCurrent,
			@RequestParam(defaultValue="15",required=false)Integer pageSize,HttpServletRequest request){
		String name=request.getParameter("name");
		String types=request.getParameter("types");
		ModelAndView mv=new ModelAndView("/manage/baseinfor/hospitalGrade-list");
		Page<HospitalGrade> page=new Page<HospitalGrade>(pageCurrent,pageSize);
		page.setOrderDirection("asc");
		page.setOrderField("sorts");
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("name", name);
		params.put("types", types);
		page.setParams(params);
		List<HospitalGrade> hospitalGradeList=hospitalGradeMapper.selectByPage(page);
		page.setResults(hospitalGradeList);
		mv.addObject("page", page);
		return mv;
	}

	@RequestMapping(value="hospitalGradeEditUI")
	public ModelAndView hospitalGradeEditUI(@RequestParam(required=false)Integer hospitalGradeId,HttpServletRequest request){
		ModelAndView mv=new ModelAndView("/manage/baseinfor/hospitalGrade-edit");
		HospitalGrade hospitalGrade=hospitalGradeMapper.selectByPrimaryKey(hospitalGradeId);
		mv.addObject("hospitalGrade", hospitalGrade);
		return mv;
	}
	
	
	@RequestMapping(value="/hospitalGradeEdit")
	public void hospitalGradeEdit(@ModelAttribute HospitalGrade hospitalGrade,HttpServletRequest request,HttpServletResponse response) throws IOException{
		Manager m=(Manager)request.getSession().getAttribute(Const.SESSION_MANAGER);
		hospitalGrade.setManagerId(m.getId());
		hospitalGrade.setOperateTime(new Date());
		hospitalGrade.setStatus((byte) 1);
			int i=0;
			if(hospitalGrade.getId()==null){
			 i=hospitalGradeMapper.insertSelective(hospitalGrade);
			}else{
			 i=hospitalGradeMapper.updateByPrimaryKeySelective(hospitalGrade);
			}
				if(i==1){
					new AjaxUtil(request, response).JsonType("200", "保存成功！", "", "grade", "", "", "保存成功！",true);
				}else{
					new AjaxUtil(request, response).JsonType("300", "保存失败！", "", "grade", "", "", "保存失败",true);	
				}
	}
	
	@RequestMapping(value="/status")
	public void status(HospitalGrade hospitalGrade, HttpServletRequest request, HttpServletResponse response) throws IOException{
		Manager m=(Manager)request.getSession().getAttribute(Const.SESSION_MANAGER);
		hospitalGrade.setManagerId(m.getId());
		hospitalGrade.setOperateTime(new Date());
		int i = hospitalGradeMapper.updateByPrimaryKeySelective(hospitalGrade);
		
		if (i == 1) {
			new AjaxUtil(request, response).JsonType("200", "删除成功！", "", "grade", "", "", "删除成功！",false);
		} else {
			new AjaxUtil(request, response).JsonType("300", "删除失败！", "", "grade", "", "", "删除失败",false);	
		}
	}

	
}
