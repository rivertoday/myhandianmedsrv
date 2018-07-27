package com.hrsb.cg.controller;

import java.util.HashMap;
import java.util.Map;

import com.hrsb.cg.util.GsonUtil;
import com.hrsb.cg.util.MD5;
import com.hrsb.cg.util.push.UmengPushUtil;

public class Test {

	public static void main(String[] args) {
		pushNews("9FC3D7152BA9336A670E36D0ED79BC43", "3", "拒绝3", "拒绝3");
		//pushNews("1", "driver1", "9", "派遣端新工单", "派遣端新工单");
		//System.out.println(MD5.convertToMD5("111111"));
	}
	
	public static void pushNews(String uid, String type, String title, String content) {
		
		try {
			//uid = MD5.convertToMD5(uid);
			Map<String,Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("type", type);
			String custom = GsonUtil.mapToJsonString(jsonMap);
			//通过Uid值给指定的用户发送消息
			UmengPushUtil umengPushUtil = new UmengPushUtil();
			//给安卓设备推送
			//umengPushUtil.sendAndroidGroupcast(custom, uid, title, content);
			//给IOS设备推送
			umengPushUtil.sendIOSGroupcast(custom, uid, title, content);
			
			//umengPushUtil.sendIOSBroadcast(custom, title, content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
