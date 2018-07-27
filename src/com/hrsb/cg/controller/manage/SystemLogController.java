package com.hrsb.cg.controller.manage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hrsb.cg.model.SystemLog;
import com.hrsb.cg.service.SystemLogService;
import com.hrsb.cg.util.Page;

@RequestMapping("/sc")
@Controller
public class SystemLogController {
	@Autowired
	private SystemLogService systemLogService;
	//分页查询系统日志
	@RequestMapping("/fs")
	public ModelAndView findLogs(Integer pageSize,Integer pageCurrent,String orderField,String orderDirection,HttpSession session,HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new ModelAndView("manage/systemlog");
		Page<SystemLog> page = new Page<SystemLog>().setPageNo(pageCurrent==null?1:pageCurrent).setPageSize(pageSize==null?20:pageSize).setOrderDirection(orderDirection==null?"desc":orderDirection).setOrderField(orderField==null?"operatime":orderField);
		List<SystemLog> ls = systemLogService.getAllByPage(page);
		page.setResults(ls);
		mv.addObject("page", page);
		return mv;
	}
	@RequestMapping("/ep")
	public void ExportSystemLog(String expids,HttpServletResponse response){
		 	response.setContentType("application/binary;charset=ISO8859_1");  
	        try  
	        {
	            ServletOutputStream outputStream = response.getOutputStream();  
	            String fileName = new String(("系统日志").getBytes(), "ISO8859_1");  
	            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式  
	            List<SystemLog> sl = null;
	            if(StringUtils.isBlank(expids)){
	            	sl = systemLogService.getAll();
	            }else{
	            	sl = systemLogService.getAll(Arrays.asList(expids.split(",")));
	            }
	            String[] titles = { "操作", "操作描述", "操作时间","操作状态","操作人" };  
	            systemLogService.exportExcel(sl, titles, outputStream);
	        }  
	        catch (IOException e)  
	        {  
	            e.printStackTrace();  
	        }  
	}
}
