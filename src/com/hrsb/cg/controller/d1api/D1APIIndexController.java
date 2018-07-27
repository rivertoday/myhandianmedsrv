package com.hrsb.cg.controller.d1api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hrsb.cg.intercept.D1apiAuth;
import com.hrsb.cg.model.About;
import com.hrsb.cg.model.AreaCity;
import com.hrsb.cg.model.AreaCounty;
import com.hrsb.cg.model.AreaProv;
import com.hrsb.cg.model.CodeRecord;
import com.hrsb.cg.model.Comment;
import com.hrsb.cg.model.Department;
import com.hrsb.cg.model.Dirty;
import com.hrsb.cg.model.HospitalGrade;
import com.hrsb.cg.model.UserAuth;
import com.hrsb.cg.model.UserDetail;
import com.hrsb.cg.model.UserLogin;
import com.hrsb.cg.model.Version;
import com.hrsb.cg.util.AjaxUtil;
import com.hrsb.cg.util.Const;
import com.hrsb.cg.util.JsonUtil;
import com.hrsb.cg.util.MD5;
import com.hrsb.cg.util.Tools;

/**
 * 首页控制类
 * 
 * @author app001
 * 
 */
@Controller
@RequestMapping(value = "/d1api")
public class D1APIIndexController extends D1APIController {
	
	//Added by JIANG, please refer to the log4j.xml and web.xml for configuration
	private static final Logger JJLogger = Logger.getLogger("RIVER_LOGGER");

	/**
	 * 验证手机号是否已经注册
	 * 
	 * @param phone
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/existence.json")
	@ResponseBody
	public Map<String, Object> existence(@RequestParam String phone) {
		Map<String, Object> mymap = new HashMap<String, Object>();
		try {
			boolean flag = userService.isExistLoginName(phone);
			if (!flag) {
				mymap.put("success", "0");
				mymap.put("errorMsg", "用户不存在");
			} else {
				mymap.put("success", "1");
				mymap.put("data", phone);
			}
		}catch(Exception ex){
			mymap.put("success", "0");
			mymap.put("errorMsg", ex.toString());			
		}

		JJLogger.info(JsonUtil.maptojson(mymap));
		//System.out.println(JsonUtil.maptojson(mymap));
		return mymap;
	}

	/**
	 * 注册
	 * 
	 * @param phone
	 * @param code
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/register_get.json")
	@ResponseBody
	public Map<String, Object> regist_get(@RequestParam String phone,
			@RequestParam String code, @RequestParam String password) {
		Map<String, Object> mymap = new HashMap<String, Object>();
		
		try {
			String message = "";
			if (userService.isExistLoginName(phone)) {
				if (checkCode(phone, code)) {
					UserLogin login = new UserLogin();
					login.setPhone(phone);
					login.setPassword(MD5.convertToMD5(password));
					login.setFrozen((byte) 0);
					userLoginMapper.insertSelective(login);
					UserDetail detail = new UserDetail();
					detail.setUserId(login.getId());
					detail.setWay((byte) 1);
					detail.setPhone(phone);
					detail.setStatus((byte) 1);
					detail.setCreateTime(new Date());
					detail.setIsLoginFirst((byte) 1);
					detail.setSource("D1API");// modified from 手机站 to D1API
					detail.setDownloadTypes((byte) 1);
					detail.setDownloadCount(Const.DOWNLOADCOUNT);
					userDetailMapper.insertSelective(detail);
	
					// message = "注册成功";
					mymap.put("success", "1");
					mymap.put("data", detail);
				} else {
					message = "验证码错误";
					mymap.put("success", "0");
					mymap.put("errorMsg", message);
				}
			} else {
				message = "用户已存在";
				mymap.put("success", "0");
				mymap.put("errorMsg", message);
			}
		}catch(Exception ex){
			mymap.put("success", "0");
			mymap.put("errorMsg", ex.toString());			
		}

		JJLogger.info(JsonUtil.maptojson(mymap));
		//System.out.println(JsonUtil.maptojson(mymap));
		return mymap;
	}
	
	/**
	 * 注册
	 * 
	 * @param phone
	 * @param code
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/register.json")
	@ResponseBody
	public Map<String, Object> regist(@RequestBody String data) {
		Map<String, Object> mymap = new HashMap<String, Object>();
		
		try {
			JsonParser parser = new JsonParser(); // 创建json解析器
			JsonObject json = (JsonObject) parser.parse(data); // 创建jsonObject对象
			String phone = json.get("phone").getAsString();
			String password = json.get("password").getAsString();
			String code = json.get("code").getAsString();
			JJLogger.info("phone:" + phone+", password:"+password+", code:"+code);
			
			String message = "";
			if (userService.isExistLoginName(phone)) {
				if (checkCode(phone, code)) {
					UserLogin login = new UserLogin();
					login.setPhone(phone);
					login.setPassword(MD5.convertToMD5(password));
					login.setFrozen((byte) 0);
					userLoginMapper.insertSelective(login);
					UserDetail detail = new UserDetail();
					detail.setUserId(login.getId());
					detail.setWay((byte) 1);
					detail.setPhone(phone);
					detail.setStatus((byte) 1);
					detail.setCreateTime(new Date());
					detail.setIsLoginFirst((byte) 1);
					detail.setSource("D1API");// modified from 手机站 to D1API
					detail.setDownloadTypes((byte) 1);
					detail.setDownloadCount(Const.DOWNLOADCOUNT);
					userDetailMapper.insertSelective(detail);
	
					// message = "注册成功";
					mymap.put("success", "1");
					mymap.put("data", detail);
				} else {
					message = "验证码错误";
					mymap.put("success", "0");
					mymap.put("errorMsg", message);
				}
			} else {
				message = "用户已存在";
				mymap.put("success", "0");
				mymap.put("errorMsg", message);
			}
		}catch(Exception ex){
			mymap.put("success", "0");
			mymap.put("errorMsg", ex.toString());			
		}

		JJLogger.info(JsonUtil.maptojson(mymap));
		//System.out.println(JsonUtil.maptojson(mymap));
		return mymap;
	}

	/**
	 * 验证验证码是否有效
	 * 
	 * @param phone
	 * @param code
	 * @return
	 */
	private boolean checkCode(String phone, String code) {
		CodeRecord codeRecord = codeRecordMapper.selectByPhone(phone);

		if (null != codeRecord && !StringUtils.isEmpty(code)
				&& code.equals(codeRecord.getCode())
				&& codeRecord.getEffectiveTime().after(new Date())) {
			return true;
		}

		return false;
	}

	/**
	 * 验证码是否有效
	 * 
	 * @param phone
	 * @param code
	 */
	@RequestMapping(value = "/code_check")
	@ResponseBody
	public Map<String, Object> code_check(@RequestParam String phone,
			@RequestParam String code) {
		Map<String, Object> mymap = new HashMap<String, Object>();

		if (checkCode(phone, code)) {
			mymap.put("code_check", "1");
		} else {
			mymap.put("code_check", "0");
		}

		JJLogger.info(JsonUtil.maptojson(mymap));
		//System.out.println(JsonUtil.maptojson(mymap));
		return mymap;
	}

	/**
	 * 验证密码是否正确
	 * 
	 * @param phone
	 * @param password
	 */
	@RequestMapping(value = "/checkpassword.json")
	@ResponseBody
	public Map<String, Object> checkPassword(@RequestParam String phone,
			@RequestParam String password) {
		Map<String, Object> mymap = new HashMap<String, Object>();
		String msg = userService.checkPassword(phone, password);

		if ("ok" == msg) {
			mymap.put("success", "1");
		} else {
			mymap.put("success", "0");
			mymap.put("errorMsg", msg);// fail：用户名密码不匹配或者其它；frozen：用户冻结
		}

		JJLogger.info(JsonUtil.maptojson(mymap));
		//System.out.println(JsonUtil.maptojson(mymap));
		return mymap;
	}

	/**
	 * 获取验证码
	 * 
	 * @param phone
	 * @throws Exception
	 */
	@RequestMapping(value = "/getcode_get.json")
	@ResponseBody
	public Map<String, Object> getCode_get(@RequestParam String phone) {
		Map<String, Object> mymap = new HashMap<String, Object>();
		try {
			String code = Tools.getRandom(6);//改成6位的了
			String content = "R您的手机验证码为：" + code + " 【汉典医学】";
			String result = sdk.sendSms(Const.server_username,
					Const.server_password, phone, content);
			String[] arr = StringUtils.split(result, ",");

			if (!ArrayUtils.isEmpty(arr) && !StringUtils.isEmpty(arr[0])
					&& "0".equals(arr[0].trim())) {
				CodeRecord codeRecord = codeRecordMapper.selectByPhone(phone);
				CodeRecord record = new CodeRecord();

				if (null != codeRecord) {
					record.setId(codeRecord.getId());
					record.setCode(code);
					record.setEffectiveTime(DateUtils.addMinutes(new Date(),
							Const.MINUTE));
					codeRecordMapper.updateByPrimaryKeySelective(record);
				} else {
					record.setPhone(phone);
					record.setCode(code);
					record.setEffectiveTime(DateUtils.addMinutes(new Date(),
							Const.MINUTE));
					codeRecordMapper.insertSelective(record);
				}

				mymap.put("success", "1");
				mymap.put("data", code);
			} else {
				mymap.put("success", "0");
				mymap.put("errorMsg", result);
			}
		} catch (Exception ex) {
			mymap.put("success", "0");
			mymap.put("errorMsg", ex.toString());
		}

		JJLogger.info(JsonUtil.maptojson(mymap));
		//System.out.println(JsonUtil.maptojson(mymap));
		return mymap;
	}
	
	/**
	 * 获取验证码
	 * 
	 * @param phone
	 * @throws Exception
	 */
	@RequestMapping(value = "/getcode.json")
	@ResponseBody
	public Map<String, Object> getCode(@RequestBody String data) {
		Map<String, Object> mymap = new HashMap<String, Object>();
		try {
			JsonParser parser = new JsonParser(); // 创建json解析器
			JsonObject json = (JsonObject) parser.parse(data); // 创建jsonObject对象
			String phone = json.get("phone").getAsString();
			System.out.println("phone:" + phone);
			
			String code = Tools.getRandom(6);//改成6位的了
			String content = "您的手机验证码为：" + code + " 【汉典医学】";
			String result = sdk.sendSms(Const.server_username,
					Const.server_password, phone, content);
			String[] arr = StringUtils.split(result, ",");

			if (!ArrayUtils.isEmpty(arr) && !StringUtils.isEmpty(arr[0])
					&& "0".equals(arr[0].trim())) {
				CodeRecord codeRecord = codeRecordMapper.selectByPhone(phone);
				CodeRecord record = new CodeRecord();

				if (null != codeRecord) {
					record.setId(codeRecord.getId());
					record.setCode(code);
					record.setEffectiveTime(DateUtils.addMinutes(new Date(),
							Const.MINUTE));
					codeRecordMapper.updateByPrimaryKeySelective(record);
				} else {
					record.setPhone(phone);
					record.setCode(code);
					record.setEffectiveTime(DateUtils.addMinutes(new Date(),
							Const.MINUTE));
					codeRecordMapper.insertSelective(record);
				}

				mymap.put("success", "1");
				mymap.put("data", code);
			} else {
				mymap.put("success", "0");
				mymap.put("errorMsg", result);
			}
		} catch (Exception ex) {
			mymap.put("success", "0");
			mymap.put("errorMsg", ex.toString());
		}

		JJLogger.info(JsonUtil.maptojson(mymap));
		//System.out.println(JsonUtil.maptojson(mymap));
		return mymap;
	}
	
	/**
	 * 获取验证码
	 * 
	 * @param phone
	 * @throws Exception
	 */
	@RequestMapping(value = "/sendsm.json")
	@ResponseBody
	public Map<String, Object> sendSM(@RequestBody String data) {
		Map<String, Object> mymap = new HashMap<String, Object>();
		try {
			JsonParser parser = new JsonParser(); // 创建json解析器
			JsonObject json = (JsonObject) parser.parse(data); // 创建jsonObject对象
			String phone = json.get("phone").getAsString();
			String info = json.get("info").getAsString();
			JJLogger.info("Send phone "+phone+" with info:" + info);
			
			String content = info + " 【汉典医学】";
			String result = sdk.sendSms(Const.server_username,
					Const.server_password, phone, content);
			String[] arr = StringUtils.split(result, ",");

			if (!ArrayUtils.isEmpty(arr) && !StringUtils.isEmpty(arr[0])
					&& "0".equals(arr[0].trim())) {				
				mymap.put("success", "1");
				mymap.put("data", phone+":"+content);
			} else {
				mymap.put("success", "0");
				mymap.put("errorMsg", result);
			}
		} catch (Exception ex) {
			mymap.put("success", "0");
			mymap.put("errorMsg", ex.toString());
		}

		JJLogger.info(JsonUtil.maptojson(mymap));
		//System.out.println(JsonUtil.maptojson(mymap));
		return mymap;
	}


	/**
	 * 登录
	 * 
	 * @param phone
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login_get.json")
	@ResponseBody
	public Map<String, Object> login_get(@RequestParam String phone,
			@RequestParam String password) {
		UserLogin userLogin = userLoginMapper.selectByPhoneAndPassword(phone,MD5.convertToMD5(password));
		Map<String, Object> mymap = new HashMap<String, Object>();

		if (null != userLogin && userLogin.getFrozen() == 0) {
			//先检查该用户的登录是否已经过期
			UserAuth oldAuth = userAuthMapper.selectByPhone(phone);
			UserAuth newAuth = null;
			
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			String time = null;
			
			if (null != oldAuth) {
				//曾经登录过，鉴权有记录
				Date updateTime = new Date();
				Date newEffectTime = DateUtils.addMinutes(new Date(),Const.SESSMINUTE);// 60 min有效时间
        		String newtoken = MD5.convertToMD5(phone+password+updateTime.toString());
        		userAuthMapper.updateEffectByPhone(newEffectTime, updateTime, newtoken, phone);
        		
        		UserDetail detail = userDetailMapper.getPhone(phone);
    			mymap.put("success", "1");
    			mymap.put("token", newtoken);
    			time = format.format(updateTime);
    			mymap.put("updatetime", time);
    			time = format.format(newEffectTime);
    			mymap.put("effectivetime", time);
    			mymap.put("data", detail);
			}
			else {
        		//没有登录过，在鉴权表没有记录，需要在userAuth表里面创建记录
				Date createtime = DateUtils.addMinutes(new Date(),0);
				Date effecttime = DateUtils.addMinutes(new Date(),Const.SESSMINUTE);// 60 min有效时间
				String token = MD5.convertToMD5(phone+password+createtime.toString());
				
				newAuth = new UserAuth();
				newAuth.setPhone(phone);
				newAuth.setToken(token);
				newAuth.setCreateTime(createtime);
				newAuth.setEffectiveTime(effecttime);
				userAuthMapper.insertSelective(newAuth);

				UserDetail detail = userDetailMapper.getPhone(phone);

				mymap.put("success", "1");
				mymap.put("token", token);
				time = format.format(effecttime);
				mymap.put("effectivetime", time);
				mymap.put("data", detail);
        	}			
		} else {
			mymap.put("success", "0");
			mymap.put("errorMsg", "用户不存在或者被冻结");
		}

		JJLogger.info(JsonUtil.maptojson(mymap));
		//System.out.println(JsonUtil.maptojson(mymap));
		return mymap;
	}

	/**
	 * 登录
	 * 
	 * @param phone
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login.json")
	@ResponseBody
	public Map<String, Object> login(@RequestBody String data) {
		Map<String, Object> mymap = new HashMap<String, Object>();
		
		try {
			System.out.println("Here it is the login POST data: " + data);
			JsonParser parser = new JsonParser(); // 创建json解析器
			JsonObject json = (JsonObject) parser.parse(data); // 创建jsonObject对象
			String phone = json.get("phone").getAsString();
			String password = json.get("password").getAsString();
			JJLogger.info("phone: " + phone + ", password: " + password);
	
			String md5tmp = MD5.convertToMD5(password);
			UserLogin userLogin = userLoginMapper.selectByPhoneAndPassword(phone,md5tmp);			
	
			if (null != userLogin && userLogin.getFrozen() == 0) {
				//先检查该用户的登录是否已经过期
				UserAuth oldAuth = userAuthMapper.selectByPhone(phone);
				UserAuth newAuth = null;
				
				DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				String time = null;
				
				if (null != oldAuth) {
					//曾经登录过，鉴权有记录
					Date updateTime = new Date();
					Date newEffectTime = DateUtils.addMinutes(new Date(),Const.SESSMINUTE);// 60 min有效时间
	        		String newtoken = MD5.convertToMD5(phone+password+updateTime.toString());
	        		userAuthMapper.updateEffectByPhone(newEffectTime, updateTime, newtoken, phone);
	        		
	        		UserDetail detail = userDetailMapper.getPhone(phone);
	    			mymap.put("success", "1");
	    			mymap.put("token", newtoken);
	    			time = format.format(updateTime);
	    			mymap.put("updatetime", time);
	    			time = format.format(newEffectTime);
	    			mymap.put("effectivetime", time);
	    			mymap.put("data", detail);
				}
				else {
	        		//没有登录过，在鉴权表没有记录，需要在userAuth表里面创建记录
					Date createtime = DateUtils.addMinutes(new Date(),0);
					Date effecttime = DateUtils.addMinutes(new Date(),Const.SESSMINUTE);// 60 min有效时间
					String token = MD5.convertToMD5(phone+password+createtime.toString());
					
					newAuth = new UserAuth();
					newAuth.setPhone(phone);
					newAuth.setToken(token);
					newAuth.setCreateTime(createtime);
					newAuth.setEffectiveTime(effecttime);
					userAuthMapper.insertSelective(newAuth);

					UserDetail detail = userDetailMapper.getPhone(phone);

					mymap.put("success", "1");
					mymap.put("token", token);
					time = format.format(effecttime);
					mymap.put("effectivetime", time);
					mymap.put("data", detail);
	        	}			
			} else {
				mymap.put("success", "0");
				mymap.put("errorMsg", "用户不存在或者被冻结");
			}
		}catch(Exception ex) {
			mymap.put("success", "0");
			mymap.put("errorMsg", ex.toString());
		}

		JJLogger.info(JsonUtil.maptojson(mymap));
		//System.out.println(JsonUtil.maptojson(mymap));
		return mymap;
	}

	/**
	 * 找回密码
	 * 
	 * @param phone
	 * @param code
	 * @param password
	 * @return
	 */
	// 此接口调用前，用户应该先获取手机验证码，然后上传的信息包括
	// 手机号，验证码，以及新设置的密码（填写两次，由前端检查是否一致）
	@RequestMapping(value = "/resetpassword_get.json")
	@ResponseBody
	public Map<String, Object> resetpassword_get(@RequestParam String phone,
			@RequestParam String code, @RequestParam String password) {
		Map<String, Object> mymap = new HashMap<String, Object>();
		UserLogin userLogin = userLoginMapper.selectByPhone(phone);
		String message = "";

		if (null != userLogin) {
			if (checkCode(phone, code)) {
				UserLogin login = new UserLogin();
				login.setId(userLogin.getId());
				login.setPassword(MD5.convertToMD5(password));
				userLoginMapper.updateByPrimaryKeySelective(login);

				mymap.put("success", "1");
				// message = "密码找回成功";
			} else {
				mymap.put("success", "0");
				message = "验证码错误";
				mymap.put("errorMsg", message);
			}
		} else {
			mymap.put("success", "0");
			message = "该手机号尚未注册";
			mymap.put("errorMsg", message);
		}

		JJLogger.info(JsonUtil.maptojson(mymap));
		//System.out.println(JsonUtil.maptojson(mymap));
		return mymap;
	}
	
	/**
	 * 找回密码
	 * 
	 * @param phone
	 * @param code
	 * @param password
	 * @return
	 */
	// 此接口调用前，用户应该先获取手机验证码，然后上传的信息包括
	// 手机号，验证码，以及新设置的密码（填写两次，由前端检查是否一致）
	//@D1apiAuth
	@RequestMapping(value = "/resetpassword.json")
	@ResponseBody
	public Map<String, Object> resetpassword(@RequestBody String data) {
		Map<String, Object> mymap = new HashMap<String, Object>();
		
		try {
			JJLogger.info("Here it is the login POST data: " + data);
			JsonParser parser = new JsonParser(); // 创建json解析器
			JsonObject json = (JsonObject) parser.parse(data); // 创建jsonObject对象
			String phone = json.get("phone").getAsString();
			String newpassword = json.get("newpassword").getAsString();
			String code = json.get("code").getAsString();
			JJLogger.info("phone: " + phone + ", newpassword: " + newpassword);
			
			UserLogin userLogin = userLoginMapper.selectByPhone(phone);
			String message = "";
	
			if (null != userLogin) {
				if (checkCode(phone, code)) {
					UserLogin login = new UserLogin();
					login.setId(userLogin.getId());
					login.setPassword(MD5.convertToMD5(newpassword));
					userLoginMapper.updateByPrimaryKeySelective(login);
	
					mymap.put("success", "1");
					// message = "密码找回成功";
				} else {
					mymap.put("success", "0");
					message = "验证码错误";
					mymap.put("errorMsg", message);
				}
			} else {
				mymap.put("success", "0");
				message = "该手机号尚未注册";
				mymap.put("errorMsg", message);
			}
		}catch(Exception ex) {
			mymap.put("success", "0");
			mymap.put("errorMsg", ex.toString());
		}

		JJLogger.info(JsonUtil.maptojson(mymap));
		//System.out.println(JsonUtil.maptojson(mymap));
		return mymap;
	}


	/**
	 * 关于我们
	 * 
	 * @param modelMap
	 * @return
	 */
	//@D1apiAuth
	@RequestMapping(value = "/about.json")
	@ResponseBody
	public Map<String, Object> about() {
		Map<String, Object> mymap = new HashMap<String, Object>();

		try {
			About about = aboutMapper.selectByPrimaryKey(1);

			mymap.put("success", "1");
			mymap.put("data", about);
		} catch (Exception ex) {
			mymap.put("success", "0");
			mymap.put("errorMsg", ex.toString());
		}

		JJLogger.info(JsonUtil.maptojson(mymap));
		//System.out.println(JsonUtil.maptojson(mymap));
		return mymap;
	}

	/**
	 * 用户协议
	 * 
	 * @param modelMap
	 * @return
	 */
	//@D1apiAuth
	@RequestMapping(value = "/agreement.json")
	@ResponseBody
	public Map<String, Object> agreement() {
		Map<String, Object> mymap = new HashMap<String, Object>();

		try {
			About about = aboutMapper.selectByPrimaryKey(2);

			mymap.put("success", "1");
			mymap.put("data", about);
		} catch (Exception ex) {
			mymap.put("success", "0");
			mymap.put("errorMsg", ex.toString());
		}

		JJLogger.info(JsonUtil.maptojson(mymap));
		//System.out.println(JsonUtil.maptojson(mymap));
		return mymap;
	}

	/**
	 * 隐私条款
	 * 
	 * @param modelMap
	 * @return Map<String, Object>
	 */
	//@D1apiAuth
	@RequestMapping(value = "/secret.json")
	@ResponseBody
	public Map<String, Object> secret() {
		Map<String, Object> mymap = new HashMap<String, Object>();

		try {
			About about = aboutMapper.selectByPrimaryKey(3);

			mymap.put("success", "1");
			mymap.put("data", about);
		} catch (Exception ex) {
			mymap.put("success", "0");
			mymap.put("errorMsg", ex.toString());
		}

		JJLogger.info(JsonUtil.maptojson(mymap));
		//System.out.println(JsonUtil.maptojson(mymap));
		return mymap;
	}

	/**
	 * 当前版本
	 * 
	 * @param modelMap
	 * @return Map<String, Object>
	 */
	//@D1apiAuth
	@RequestMapping(value = "/version.json")
	@ResponseBody
	public Map<String, Object> getVersion(@RequestParam(value="versionId", required=false) String versionId) {
		Map<String, Object> mymap = new HashMap<String, Object>();

		try {
			Version ver = null;
			if (null != versionId && !versionId.isEmpty()) {
				ver = versionMapper.selectByPrimaryKey(Integer.parseInt(versionId));
			} else {
				ver = versionMapper.selectLatest();
			}
			
			if (null != ver) {
				mymap.put("success", "1");
				mymap.put("data", ver);
			}else{
				mymap.put("success", "0");
				mymap.put("errorMsg", "versionId不正确！");
			}
			
		} catch (Exception ex) {
			mymap.put("success", "0");
			mymap.put("errorMsg", ex.toString());
		}

		JJLogger.info(JsonUtil.maptojson(mymap));
		//System.out.println(JsonUtil.maptojson(mymap));
		return mymap;
	}

	/**
	 * 获取所有省
	 * 
	 * @param
	 * @return Map<String, Object>
	 */
	//@D1apiAuth
	@RequestMapping(value = "/province.json")
	@ResponseBody
	public Map<String, Object> getProvince() {
		Map<String, Object> mymap = new HashMap<String, Object>();

		try {
			List<AreaProv> areaProvs = areaProvMapper.findAllProv();
			mymap.put("success", "1");
			mymap.put("data", areaProvs);

		} catch (Exception ex) {
			mymap.put("success", "0");
			mymap.put("errorMsg", ex.toString());
		}

		JJLogger.info(JsonUtil.maptojson(mymap));
		//System.out.println(JsonUtil.maptojson(mymap));
		return mymap;
	}

	/**
	 * 获取省下的所有市
	 * 
	 * @param prov_code
	 */
	//@D1apiAuth
	@RequestMapping(value = "/city.json")
	@ResponseBody
	public Map<String, Object> getCity(@RequestParam String prov_code) {
		Map<String, Object> mymap = new HashMap<String, Object>();

		try {
			List<AreaCity> areaCities = areaCityMapper.selectAllCity(prov_code);
			mymap.put("success", "1");
			mymap.put("data", areaCities);

		} catch (Exception ex) {
			mymap.put("success", "0");
			mymap.put("errorMsg", ex.toString());
		}

		JJLogger.info(JsonUtil.maptojson(mymap));
		//System.out.println(JsonUtil.maptojson(mymap));
		return mymap;
	}

	/**
	 * 获取市下的所有县
	 * 
	 * @param city_code
	 */
	//@D1apiAuth
	@RequestMapping(value = "/county.json")
	@ResponseBody
	public Map<String, Object> getCounty(@RequestParam String city_code) {
		Map<String, Object> mymap = new HashMap<String, Object>();

		try {
			List<AreaCounty> areaCounties = areaCountyMapper
					.selectAllCounty(city_code);
			mymap.put("success", "1");
			mymap.put("data", areaCounties);

		} catch (Exception ex) {
			mymap.put("success", "0");
			mymap.put("errorMsg", ex.toString());
		}

		JJLogger.info(JsonUtil.maptojson(mymap));
		//System.out.println(JsonUtil.maptojson(mymap));
		return mymap;
	}

	/**
	 * 获取医院的等级或者职称
	 * 
	 * @param type
	 */
	//@D1apiAuth
	@RequestMapping(value = "/grade.json")
	@ResponseBody
	public Map<String, Object> getGrade(@RequestParam int type) {
		Map<String, Object> mymap = new HashMap<String, Object>();

		try {
			List<HospitalGrade> hospitalGrades = hospitalGradeMapper
					.selectByType(type);

			mymap.put("success", "1");
			mymap.put("data", hospitalGrades);

		} catch (Exception ex) {
			mymap.put("success", "0");
			mymap.put("errorMsg", ex.toString());
		}

		JJLogger.info(JsonUtil.maptojson(mymap));
		//System.out.println(JsonUtil.maptojson(mymap));
		return mymap;
	}

	/**
	 * 获取科室
	 * 
	 * @param parent_id
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	//@D1apiAuth
	@RequestMapping(value = "/department.json")
	@ResponseBody
	public Map<String, Object> getDepartment(@RequestParam int parent_id) {
		Map<String, Object> mymap = new HashMap<String, Object>();

		try {
			List<Department> departments = departmentMapper
					.selectByParentId(parent_id);

			mymap.put("success", "1");
			mymap.put("data", departments);

		} catch (Exception ex) {
			mymap.put("success", "0");
			mymap.put("errorMsg", ex.toString());
		}
		
		JJLogger.info(JsonUtil.maptojson(mymap));
		//System.out.println(JsonUtil.maptojson(mymap));
		return mymap;
	}	
}
