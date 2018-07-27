package com.hrsb.cg.controller.manage;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hrsb.cg.model.BaseLog;
import com.hrsb.cg.model.SystemLog;
import com.hrsb.cg.service.BaseLogService;
import com.hrsb.cg.util.DateUtil;
import com.hrsb.cg.util.Page;

@Controller
@RequestMapping("baselog")
public class BaseLogController {
	@Autowired
	BaseLogService baseLogService;
	@RequestMapping(value="/gs")
	public ModelAndView findBaseLog(@RequestParam Map<String,Object> params,Integer idkey,Integer pageSize,Integer pageCurrent){
		ModelAndView mv = new ModelAndView("manage/baselog");
		params.put("type", 0);
		Page<BaseLog> page=new Page<BaseLog>().setPageNo(pageCurrent==null?1:pageCurrent).setPageSize(pageSize==null?15:pageSize).setParams(params);
		List<BaseLog> bs = baseLogService.selectLogsByPage(page);
		page.setResults(bs);
		mv.addObject("page", page);
		return mv;
	}
	@RequestMapping(value="/gs2")
	public ModelAndView findBaseLog2(@RequestParam Map<String,Object> params,Integer idkey,Integer pageSize,Integer pageCurrent){
		ModelAndView mv = new ModelAndView("manage/clientlog");
		params.put("type", 1);
		Page<BaseLog> page=new Page<BaseLog>().setPageNo(pageCurrent==null?1:pageCurrent).setPageSize(pageSize==null?15:pageSize).setParams(params);
		List<BaseLog> bs = baseLogService.selectLogsByPage(page);
		page.setResults(bs);
		mv.addObject("page", page);
		return mv;
	}
	@RequestMapping("/ep")
	public void ExportSystemLog(String expids,HttpServletResponse response){
		 	response.setContentType("application/binary;charset=ISO8859_1");  
	        try  
	        {
	            ServletOutputStream outputStream = response.getOutputStream();  
	            String fileName = new String(("操作记录"+DateUtil.formatDate(new Date(), "yyyy-MM-dd")).getBytes(), "ISO8859_1");  
	            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式  
	            List<BaseLog> sl = null;
	            if(StringUtils.isBlank(expids)){
	            	sl = baseLogService.getAll(0);
	            }else{
	            	sl = baseLogService.getAll(Arrays.asList(expids.split(",")));
	            }
	            String[] titles = { "操作员编号", "操作时间", "目标编号","操作状态","操作行为","操作内容"};  
	            baseLogService.exportExcel(sl, titles, outputStream);
	        }  
	        catch (IOException e)  
	        {  
	            e.printStackTrace();  
	        }  
	}
	@RequestMapping("/ep2")
	public void ExportSystemLog2(String expids,HttpServletResponse response){
		 	response.setContentType("application/binary;charset=ISO8859_1");  
	        try  
	        {
	            ServletOutputStream outputStream = response.getOutputStream();  
	            String fileName = new String(("操作记录"+DateUtil.formatDate(new Date(), "yyyy-MM-dd")).getBytes(), "ISO8859_1");  
	            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式  
	            List<BaseLog> sl = null;
	            if(StringUtils.isBlank(expids)){
	            	sl = baseLogService.getAll(1);
	            }else{
	            	sl = baseLogService.getAll(Arrays.asList(expids.split(",")));
	            }
	            String[] titles = { "操作人编号", "操作时间", "目标编号","操作状态","操作行为","操作内容"};  
	            baseLogService.exportExcel(sl, titles, outputStream);
	        }  
	        catch (IOException e)  
	        {  
	            e.printStackTrace();  
	        }  
	}
}
