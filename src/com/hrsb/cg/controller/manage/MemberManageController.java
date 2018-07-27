package com.hrsb.cg.controller.manage;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hrsb.cg.controller.client.BaseController;
import com.hrsb.cg.dao.UserDetailMapper;
import com.hrsb.cg.dao.UserLoginMapper;
import com.hrsb.cg.dao.UserMessageMapper;
import com.hrsb.cg.model.AreaProv;
import com.hrsb.cg.model.Department;
import com.hrsb.cg.model.HospitalGrade;
import com.hrsb.cg.model.Manager;
import com.hrsb.cg.model.UserDetail;
import com.hrsb.cg.model.UserLogin;
import com.hrsb.cg.model.UserMessage;
import com.hrsb.cg.model.UserMessageWithBLOBs;
import com.hrsb.cg.service.UserService;
import com.hrsb.cg.util.AjaxUtil;
import com.hrsb.cg.util.Const;
import com.hrsb.cg.util.DateUtil;
import com.hrsb.cg.util.MD5;
import com.hrsb.cg.util.Page;
import com.hrsb.cg.util.Tools;

@Controller
@RequestMapping(value="/mm")
public class MemberManageController extends BaseController{
	
	@Autowired
	UserService userService;
	@Autowired
	UserLoginMapper userLoginMapper;
	@Autowired
	UserDetailMapper userDetailMapper;
	@Autowired
	UserMessageMapper userMessageMapper;
	
	
	@RequestMapping(value="/memberList")
	public ModelAndView memberList(@RequestParam(value="pageCurrent",defaultValue="1") Integer pageCurrent,@RequestParam(value="pageSize",defaultValue="15") Integer pageSize,
			@RequestParam(required=false) String phone,@RequestParam(required=false) String hospitalName,
			@RequestParam(required=false) String province,@RequestParam(required=false) String professional,
			@RequestParam(required=false) String source,@RequestParam(required=false) String status,HttpServletRequest request){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("phone", phone);
		params.put("hospitalName", hospitalName);
		params.put("province", province);
		params.put("professional", professional);
		params.put("source", source);
		params.put("status", status);
		Page<UserDetail> page=new Page<UserDetail>(pageCurrent, pageSize);
		page.setParams(params);
		List<UserDetail> userDetails=userService.selectUserDetailByPage(page);
		page.setResults(userDetails);
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/manage/member/member");
		modelAndView.addObject("page", page);
		List<AreaProv> areaProvs=getAllProvince();
		List<HospitalGrade> hospitalProfessionalList=hospitalGradeList(2);
		modelAndView.addObject("areaProvs", areaProvs);
		modelAndView.addObject("hospitalProfessionalList", hospitalProfessionalList);
		return modelAndView;
	}

	@RequestMapping(value="/memberDetail")
	public ModelAndView memberDetail(@RequestParam(value="userDetailId",required=false)Long userDetailId,HttpServletRequest request){
		
		UserDetail userDetail= userService.selectById(userDetailId);
		List<AreaProv> areaProvs=getAllProvince();
		List<HospitalGrade> hospitalGradeList=hospitalGradeList(1);
		List<HospitalGrade> hospitalProfessionalList=hospitalGradeList(2);
		List<Department> departments=departmentList();
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/manage/member/editmember");
		modelAndView.addObject("us", userDetail);
		modelAndView.addObject("areaProvs", areaProvs);
		modelAndView.addObject("hospitalGradeList", hospitalGradeList);
		modelAndView.addObject("hospitalProfessionalList", hospitalProfessionalList);
		modelAndView.addObject("departments", departments);
		return modelAndView;
	}
	
	@RequestMapping(value="/memberDelete")
	public void memberDelete(@RequestParam(value="userDetailId",required=true)Long userDetailId,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		int i = userService.delUser(userDetailId);
		if(i==1){
			new AjaxUtil(request, response).JsonType("200", "删除成功！", "", "userlist", "", "", "删除成功！",true);
		}else{
			new AjaxUtil(request, response).JsonType("300", "删除失败！", "", "userlist", "", "", "删除失败",true);	
		}
	}

	@RequestMapping(value="/userDetailSave")//fixed by JIANG
	public void userDetailSave(@RequestParam(value="phone")String phone, @RequestParam(value="password")String password,
			@ModelAttribute UserDetail userDetail,HttpServletRequest request,HttpServletResponse response) throws IOException{
		Manager m=(Manager)request.getSession().getAttribute(Const.SESSION_MANAGER);
		//boolean flag= userService.isExistLoginName(phone);
		//if(!flag){//fixed by JIANG
			userDetail.setManagerId(m.getId());
			userDetail.setOperateTime(new Date());
			int i=userService.userDetailSave(phone, password, userDetail);//updated by JIANG
				if(i==1){
					new AjaxUtil(request, response).JsonType("200", "保存成功！", "", "userlist", "", "", "保存成功！",true);
				}else{
					new AjaxUtil(request, response).JsonType("300", "保存失败！", "", "userlist", "", "", "保存失败",true);	
				}
		//}else{
			 //new AjaxUtil(request, response).JsonType("300", "登录名已存在！", "", "userlist", "", "", "保存失败",true);
		//}
	}

	
	@RequestMapping(value="/audit")
	public void audit(@RequestParam(value="userDetailId")Long userDetailId,@RequestParam(value="status")Byte status,HttpServletRequest request,HttpServletResponse response) throws IOException{
		Manager manager = (Manager) request.getSession().getAttribute(Const.SESSION_MANAGER);
		UserDetail userDetail=userDetailMapper.selectByPrimaryKey(userDetailId);
		userDetail.setStatus(status);
		int i=userDetailMapper.updateByPrimaryKeySelective(userDetail);
				if(i==1){
					UserDetail detail = userService.selectById(userDetailId);
					UserMessageWithBLOBs userMessage = new UserMessageWithBLOBs();
					userMessage.setUserId(detail.getUserId());
					userMessage.setTypes((byte) 1);
					userMessage.setIsNew((byte) 1);
					userMessage.setManagerId(manager.getId());
					userMessage.setOperateTime(new Date());
					// 审核失败
					if (status == 1) {
						userMessage.setTitle("您的会员信息审核未通过");
						userMessage.setContent("您的会员信息审核未通过");
						Tools.pushNewsSingle(String.valueOf(detail.getUserId()), "2", "审核失败", "审核失败");
					}
					// 审核通过
					if (status == 3) {
						userMessage.setTitle("您的会员信息已审核通过，已获得无限下载权限");
						userMessage.setContent("您的会员信息已审核通过，已获得无限下载权限");
						Tools.pushNewsSingle(String.valueOf(detail.getUserId()), "3", "审核通过", "审核通过");
					}
					
					userMessageMapper.insertSelective(userMessage);
					new AjaxUtil(request, response).JsonType("200", "保存成功！", "", "userlist", "", "", "保存成功！",false);
				}else{
					new AjaxUtil(request, response).JsonType("300", "保存失败！", "", "userlist", "", "", "保存失败",false);	
				}
	}

	@RequestMapping(value="/frozen")
	public void frozen(@RequestParam(value="userId")Long userId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		UserLogin userLogin= userService.selectUserLoginById(userId);
		if(userLogin!=null && userLogin.getFrozen()==0){
			userLogin.setFrozen((byte)1);
		}else{
			userLogin.setFrozen((byte)0);
		}
		int i=userLoginMapper.updateByPrimaryKeySelective(userLogin);
		
		if(i==1){
			// 推送客户端强制退出
			Tools.pushNewsSingle(String.valueOf(userLogin.getId()), "1", "用户被冻结", "用户被冻结");
			new AjaxUtil(request, response).JsonType("200", "保存成功！", "", "userlist", "", "", "保存成功！",false);
		}else{
			new AjaxUtil(request, response).JsonType("300", "保存失败！", "", "userlist", "", "", "保存失败",false);	
		}
	}
	
	@RequestMapping(value="/export")
	public void export(String expids,HttpServletRequest request,HttpServletResponse response){
		response.setContentType("application/binary;charset=ISO8859-1");
		try {
			ServletOutputStream servletOutputStream= response.getOutputStream();
			String fileName = new String(("会员列表"+DateUtil.formatDate(new Date(), "yyyy-MM-dd")).getBytes(), "ISO8859_1");  
            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式  
            List<UserDetail> sl = null;
            if(StringUtils.isBlank(expids)){
            	sl = userService.getAll();
            }else{
            	sl = userService.getAll(Arrays.asList(expids.split(",")));
            }
            String[] titles = {"手机号码", "昵称", "姓名","医院","地区","是否认证","职称","注册来源","医院级别","下载量"};  
            userService.exportExcel(sl, titles, servletOutputStream);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Tools.pushNewsSingle("2", "1", "拒绝3", "拒绝3");
	}
	
}
