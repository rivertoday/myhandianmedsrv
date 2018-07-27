package com.hrsb.cg.controller.manage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hrsb.cg.model.BaseLog;
import com.hrsb.cg.model.ClientManage;
import com.hrsb.cg.model.Manager;
import com.hrsb.cg.service.BaseLogService;
import com.hrsb.cg.service.ClientManageService;
import com.hrsb.cg.util.AjaxUtil;
import com.hrsb.cg.util.Const;
import com.hrsb.cg.util.Page;

@Controller
@RequestMapping("/keywords")
public class KeywordsController {

	@Autowired
	ClientManageService clientManageService;
	
	@Autowired
	BaseLogService baseLogService;
	
	@RequestMapping(value="/g1")
	public ModelAndView getMember(Map<String,Object> params,Integer pageSize,Integer pageCurrent,HttpServletRequest request,HttpServletResponse response){
		
		Page<ClientManage> page=new Page<ClientManage>().setPageNo(pageCurrent==null?1:pageCurrent).setPageSize(pageSize==null?15:pageSize).setParams(params);
		ClientManage clientManage = clientManageService.selectByType(clientManageService.CLIENTMANAGER_TYPE_KEYWORDS);
		List<ClientManage> list = new ArrayList<ClientManage>();
		list.add(clientManage);
		page.setResults(list);
		ModelAndView mv = new ModelAndView("manage/keywords/keywords");
		mv.addObject("clientManage", clientManage);
		mv.addObject("page", page);
		return mv;
	}
	
	@RequestMapping(value="/g2")
	public ModelAndView getManagerById(Integer cmid,HttpSession session,HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new ModelAndView("manage/keywords/editkeywords");
		if(cmid!=null){
			ClientManage clientManage = clientManageService.selectByPrimaryKey(cmid);
			mv.addObject("clientManage", clientManage);
		}
		return mv;
	}
	@RequestMapping(value="/e1")
	public void editManager(@RequestBody@ModelAttribute ClientManage clientManage,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws IOException{
		int row = 0;
		//登录用户
		Manager m = (Manager) session.getAttribute(Const.SESSION_MANAGER);
		if(clientManage.getId()==null){
			new AjaxUtil(request, response).JsonType("300", "操作失败", "", "member", "", "", "操作失败",true);
		}else{
			clientManage.setOperatdate(new Date());
			clientManage.setOperator(m.getId());
			row = clientManageService.updateByPrimaryKeySelective(clientManage);
			BaseLog bs = new BaseLog(m.getIdkey(),new Date(),"搜索关键词",clientManage.getId().toString(),"修改关键词信息",0,0);
			baseLogService.insertSelective(bs);
		}
		if(row>0){
			new AjaxUtil(request, response).JsonType("200", "操作完成", "", "manager", "", "", "操作完成",true);
		}else{
			new AjaxUtil(request, response).JsonType("300", "操作失败", "", "keywords", "", "", "操作失败",true);
		}
	}
}
