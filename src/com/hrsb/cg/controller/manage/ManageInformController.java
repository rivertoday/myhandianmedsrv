package com.hrsb.cg.controller.manage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hrsb.cg.controller.client.BaseController;
import com.hrsb.cg.dao.UserDetailMapper;
import com.hrsb.cg.model.AreaProv;
import com.hrsb.cg.model.HospitalGrade;
import com.hrsb.cg.model.Manager;
import com.hrsb.cg.model.UserDetail;
import com.hrsb.cg.model.UserMessageWithBLOBs;
import com.hrsb.cg.service.InformService;
import com.hrsb.cg.service.UserService;
import com.hrsb.cg.util.AjaxUtil;
import com.hrsb.cg.util.CharsetUtil;
import com.hrsb.cg.util.Const;
import com.hrsb.cg.util.MD5;
import com.hrsb.cg.util.Page;
import com.hrsb.cg.util.Tools;
import com.sun.org.apache.bcel.internal.generic.NEW;

@Controller
@RequestMapping(value="manageInform")
public class ManageInformController extends BaseController{
	
	@Autowired
	InformService informService;
	@Autowired
	UserService userService;
	@Autowired
	UserDetailMapper userDetailMapper;
	
	@RequestMapping(value="inform")
	public ModelAndView inform(@RequestParam(required=false,defaultValue="1")Integer pageCurrent,
							   @RequestParam(required=false,defaultValue="15")Integer pageSize, HttpServletRequest request){
		ModelAndView mv=new ModelAndView("/manage/inform/inform");
		Page<UserMessageWithBLOBs> page=new Page<UserMessageWithBLOBs>(pageCurrent,pageSize);
		page.setOrderDirection("desc");
		page.setOrderField("operate_time");
		List<UserMessageWithBLOBs> userMessages=informService.selectByPage(page);
		page.setResults(userMessages);
		mv.addObject("page", page);
		return mv;
	}

	@RequestMapping(value="informEditUI")
	public ModelAndView informEditUI(HttpServletRequest request){
		ModelAndView mv=new ModelAndView("/manage/inform/inform-edit");
		return mv;
	}
	
	@RequestMapping(value="/memberList")
	public ModelAndView memberList(@RequestParam(value="pageCurrent",defaultValue="1") Integer pageCurrent,@RequestParam(value="pageSize",defaultValue="15") Integer pageSize,
			@RequestParam(required=false) String phone,@RequestParam(required=false) String hospitalName,
			@RequestParam(required=false) String province,@RequestParam(required=false) String professional,
			@RequestParam(required=false) String source,@RequestParam(required=false) String status,HttpServletRequest request) throws UnsupportedEncodingException{
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("phone", phone);
		params.put("hospitalName", new CharsetUtil().toUTF_8(hospitalName));
		params.put("province", province);
		params.put("professional", professional);
		if("1".equals(source)){
			source="安卓";
		}else if("2".equals(source)){
			source="IOS";
		}else if("3".equals(source)){
			source="手机站";
		}else if("4".equals(source)){
			source="PC站";
		}
		params.put("source", source);
		params.put("status", status);
		Page<UserDetail> page=new Page<UserDetail>(pageCurrent, pageSize);
		page.setParams(params);
		List<UserDetail> userDetails=userService.selectUserDetailByPage(page);
		page.setResults(userDetails);
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/manage/inform/userList");
		modelAndView.addObject("page", page);
		List<AreaProv> areaProvs=getAllProvince();
		List<HospitalGrade> hospitalProfessionalList=hospitalGradeList(2);
		modelAndView.addObject("areaProvs", areaProvs);
		modelAndView.addObject("hospitalProfessionalList", hospitalProfessionalList);
		return modelAndView;
	}
	
	
	
	@RequestMapping(value="/informSave")
	public void userDetailSave(String chooseUser,String userDetailId,String title,Byte types,String image,String content,String detail,HttpServletRequest request,HttpServletResponse response) throws IOException{
		Manager m=(Manager)request.getSession().getAttribute(Const.SESSION_MANAGER);
		int row=0;
		if("1".equals(chooseUser)){
		List<UserDetail> userDetails=userService.getAll();
			if(userDetails!=null && userDetails.size()>0){
				for(UserDetail userDetail: userDetails){
					UserMessageWithBLOBs userMessageWithBLOBs=new UserMessageWithBLOBs();
					userMessageWithBLOBs.setContent(content);
					userMessageWithBLOBs.setDetail(detail);
					userMessageWithBLOBs.setImage(image);
					userMessageWithBLOBs.setTitle(title);
					userMessageWithBLOBs.setTypes(types);
					userMessageWithBLOBs.setIsNew((byte)1);
					userMessageWithBLOBs.setUserId(userDetail.getUserId());
					userMessageWithBLOBs.setManagerId(m.getId());
					userMessageWithBLOBs.setOperateTime(new Date());
					row=informService.Save(userMessageWithBLOBs);
				}
				
				Tools.pushNewsAll("4", title, title);
			}
		}else{
			if(StringUtils.isNotEmpty(userDetailId)){
				String userIdArr[]=userDetailId.split(",");
				List<String> uidList = new ArrayList<String>();
				
				for (int i = 0; i < userIdArr.length; i++) {
					UserMessageWithBLOBs userMessageWithBLOBs=new UserMessageWithBLOBs();
					userMessageWithBLOBs.setContent(content);
					userMessageWithBLOBs.setDetail(detail);
					userMessageWithBLOBs.setImage(image);
					userMessageWithBLOBs.setTitle(title);
					userMessageWithBLOBs.setTypes(types);
					userMessageWithBLOBs.setIsNew((byte)1);
					userMessageWithBLOBs.setUserId(Long.parseLong(userIdArr[i]));
					userMessageWithBLOBs.setManagerId(m.getId());
					userMessageWithBLOBs.setOperateTime(new Date());
					row=informService.Save(userMessageWithBLOBs);
					UserDetail userDetail = userService.selectById(Long.valueOf(userIdArr[i]));
					uidList.add(MD5.convertToMD5(String.valueOf(userDetail.getUserId())));
				}
				
				if (!CollectionUtils.isEmpty(uidList)) {
					Tools.pushNewsGroup(uidList, "4", title, title);
				}
			}
		}
				if(row==1){
					new AjaxUtil(request, response).JsonType("200", "保存成功！", "", "inform", "", "", "保存成功！",true);
				}else{
					new AjaxUtil(request, response).JsonType("300", "保存失败！", "", "inform", "", "", "保存失败",true);	
				}
		
	}
	
	@RequestMapping(value="user_list")
	public ModelAndView user_list(@RequestParam String title,
			@RequestParam(required=false,defaultValue="1")Integer pageCurrent,
			@RequestParam(required=false,defaultValue="15")Integer pageSize, HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView mv=new ModelAndView("/manage/inform/user_list");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", new CharsetUtil().toUTF_8(title));
		Page<UserMessageWithBLOBs> page=new Page<UserMessageWithBLOBs>(pageCurrent,pageSize).setParams(params);
		page.setOrderDirection("desc");
		page.setOrderField("operate_time");
		List<UserMessageWithBLOBs> userMessages=informService.selectByPage(page);
		
		if (!CollectionUtils.isEmpty(userMessages)) {
			for (UserMessageWithBLOBs userMessageWithBLOBs : userMessages) {
				UserDetail userDetail = userDetailMapper.selectByUserId(userMessageWithBLOBs.getUserId());
				userMessageWithBLOBs.setUserDetail(userDetail);
			}
		}
		
		page.setResults(userMessages);
		mv.addObject("page", page);
		return mv;
	}

	
}
