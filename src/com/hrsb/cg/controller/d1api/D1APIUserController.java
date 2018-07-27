package com.hrsb.cg.controller.d1api;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrsb.cg.dao.UserLoginMapper;
import com.hrsb.cg.model.UserDetail;
import com.hrsb.cg.model.UserLogin;
import com.hrsb.cg.service.UserService;
import com.hrsb.cg.util.JsonUtil;

/**
 * 用户控制类
 * @author app001
 *
 */
@Controller
@RequestMapping(value = "/d1api/user")
public class D1APIUserController {
	private static final Logger JJLogger = Logger.getLogger("RIVER_LOGGER");
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserLoginMapper userLoginMapper;
	/**
	 * 基本信息
	 * @param c
	 * @return
	 */
	@RequestMapping(value = "/detail.json")
	@ResponseBody
	public Map<String, Object> getUserInfo(@RequestParam String phone) {
		Map<String, Object> mymap = new HashMap<String, Object>();
		ModelMap modelMap = new ModelMap();
		
		try {			
			UserDetail userDetail = userService.getUserDetailByUserPhone(phone);
			modelMap.put("userDetail", userDetail);
			mymap.put("success", "1");
			mymap.put("data", modelMap);
		}catch(Exception ex) {
			mymap.put("success", "0");
			mymap.put("errorMsg", ex.toString());
		}
		JJLogger.info(JsonUtil.maptojson(mymap));
		return mymap;
	}
	
	
	
}
