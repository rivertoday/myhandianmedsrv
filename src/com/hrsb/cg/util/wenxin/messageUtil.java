package com.hrsb.cg.util.wenxin;



import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;



public class messageUtil {
	public void TextinputOutuser(String OPENID,String content){
		Map<String,Object> touser=new HashMap<String, Object>();
		touser.put("touser", OPENID);
		touser.put("msgtype", "text");
		Map<String,String> text=new HashMap<String, String>();
		text.put("content", content);
		touser.put("text", text);
		String json =JSONObject.toJSONString(touser);
		WeixinUtil.httpRequest("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+AccessUtil.getaccess()+"","POST",json);
	}
	public static void main(String[] args) {
		new messageUtil().TextinputOutuser("oTTV4uF1uB8HG4qgPKAqqtqc-SAw", "Hello ");
	}
}
