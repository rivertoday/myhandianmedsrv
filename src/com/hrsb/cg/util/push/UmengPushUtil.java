package com.hrsb.cg.util.push;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.hrsb.cg.util.Const;

/**
 * 友盟推送工具类
 * @author risenb-003
 *
 */
public class UmengPushUtil {
	
	private String timestamp = Integer.toString((int)(System.currentTimeMillis() / 1000));
	
	/**
	 * broadcast-广播
	 * 发送全部设备 不需要使用条件
	 * @throws Exception
	 */
	public String sendAndroidBroadcast(String custom,String title,String text) throws Exception {
		AndroidBroadcast broadcast = new AndroidBroadcast();
		broadcast.setAppMasterSecret(Const.uMengAndroidappMasterSecret);
		broadcast.setPredefinedKeyValue("appkey", Const.uMengAndroidappkey);
		broadcast.setPredefinedKeyValue("timestamp", this.timestamp);
		broadcast.setPredefinedKeyValue("ticker", "汉典医学安卓信息推送");
		broadcast.setPredefinedKeyValue("title",  title);
		broadcast.setPredefinedKeyValue("text",   text);
		broadcast.setPredefinedKeyValue("after_open", "go_custom");
		broadcast.setPredefinedKeyValue("display_type", "notification");
		broadcast.setPredefinedKeyValue("custom", custom);
		// TODO Set 'production_mode' to 'false' if it's a test device. 
		// For how to register a test device, please see the developer doc.
		broadcast.setPredefinedKeyValue("production_mode", "true");
		// Set customized fields
		//broadcast.setExtraField("test", "helloworld");
		return "安卓设备"+broadcast.send();
	}
	
	/**
	 * 安卓发送方法
	 * groupcast-组播(按照filter条件筛选特定用户群, 具体请参照filter参数)
	 * @throws Exception
	 */
	public String sendAndroidGroupcast(String custom, List<String> uidList, String title,
			String content) throws Exception {
		AndroidGroupcast groupcast = new AndroidGroupcast();
		groupcast.setAppMasterSecret(Const.uMengAndroidappMasterSecret);
		groupcast.setPredefinedKeyValue("appkey", Const.uMengAndroidappkey);
		groupcast.setPredefinedKeyValue("timestamp", this.timestamp);
		/*  TODO
		 *  Construct the filter condition:
		 *  "where": 
		 *	{
    	 *		"and": 
    	 *		[
      	 *			{"tag":"test"},
      	 *			{"tag":"Test"}
    	 *		]
		 *	}
		 */
		JSONObject filterJson = new JSONObject();
		JSONObject whereJson = new JSONObject();
		JSONArray tagArray = new JSONArray();
		
		for(int i=0;i<uidList.size();i++){
			JSONObject testTag = new JSONObject();
			testTag.put("tag", uidList.get(i));
			tagArray.put(testTag);
		}
		
		whereJson.put("or", tagArray);
		filterJson.put("where", whereJson);
		System.out.println(filterJson.toString());
		
		groupcast.setPredefinedKeyValue("builder_id", 1);
		groupcast.setPredefinedKeyValue("custom", custom);
		groupcast.setPredefinedKeyValue("filter", filterJson);
		groupcast.setPredefinedKeyValue("ticker", "汉典医学安卓信息推送");
		groupcast.setPredefinedKeyValue("title",  title);
		groupcast.setPredefinedKeyValue("text",   content);
		groupcast.setPredefinedKeyValue("after_open", "go_custom");
		groupcast.setPredefinedKeyValue("display_type", "notification");
		// TODO Set 'production_mode' to 'false' if it's a test device. 
		// For how to register a test device, please see the developer doc.
		groupcast.setPredefinedKeyValue("production_mode", "true");
		return "安卓设备"+groupcast.send();
	}
	
	/**
	 * 安卓发送方法  一个用户
	 * groupcast-组播(按照filter条件筛选特定用户群, 具体请参照filter参数)
	 * @throws Exception
	 */
	public String sendAndroidGroupcast(String custom, String uid, String title,
			String content) throws Exception {
		AndroidGroupcast groupcast = new AndroidGroupcast();
		groupcast.setAppMasterSecret(Const.uMengAndroidappMasterSecret);
		groupcast.setPredefinedKeyValue("appkey", Const.uMengAndroidappkey);
		groupcast.setPredefinedKeyValue("timestamp", this.timestamp);
		/*  TODO
		 *  Construct the filter condition:
		 *  "where": 
		 *	{
		 *		"and": 
		 *		[
		 *			{"tag":"test"},
		 *			{"tag":"Test"}
		 *		]
		 *	}
		 */
		JSONObject filterJson = new JSONObject();
		JSONObject whereJson = new JSONObject();
		JSONArray tagArray = new JSONArray();
		
		JSONObject testTag = new JSONObject();
		testTag.put("tag", uid);
		tagArray.put(testTag);
		
		whereJson.put("or", tagArray);
		filterJson.put("where", whereJson);
		System.out.println(filterJson.toString());
		
		groupcast.setPredefinedKeyValue("builder_id", 1);
		groupcast.setPredefinedKeyValue("custom", custom);
		groupcast.setPredefinedKeyValue("filter", filterJson);
		groupcast.setPredefinedKeyValue("ticker", "汉典医学安卓信息推送");
		groupcast.setPredefinedKeyValue("title",  title);
		groupcast.setPredefinedKeyValue("text",   content);
		groupcast.setPredefinedKeyValue("after_open", "go_custom");
		groupcast.setPredefinedKeyValue("display_type", "notification");
		// TODO Set 'production_mode' to 'false' if it's a test device. 
		// For how to register a test device, please see the developer doc.
		groupcast.setPredefinedKeyValue("production_mode", "true");
		return "安卓设备"+groupcast.send();
	}
	
	/**
	 * IOS发送方法
	 * groupcast-组播(按照filter条件筛选特定用户群, 具体请参照filter参数)
	 * @throws Exception
	 */
	public String sendIOSGroupcast(String custom, List<String> uidList, String title,
			String content) throws Exception {
		IOSGroupcast groupcast = new IOSGroupcast();
		groupcast.setAppMasterSecret(Const.uMengIOSappMasterSecret);
		groupcast.setPredefinedKeyValue("appkey", Const.uMengIOSappkey);
		groupcast.setPredefinedKeyValue("timestamp", this.timestamp);
		/*  TODO
		 *  Construct the filter condition:
		 *  "where": 
		 *	{
    	 *		"and": 
    	 *		[
      	 *			{"tag":"iostest"}
    	 *		]
		 *	}
		 */
		JSONObject filterJson = new JSONObject();
		JSONObject whereJson = new JSONObject();
		JSONArray tagArray = new JSONArray();
		
		for(int i=0;i<uidList.size();i++){
			JSONObject testTag = new JSONObject();
			testTag.put("tag", uidList.get(i));
			tagArray.put(testTag);
		}
		
		whereJson.put("or", tagArray);
		filterJson.put("where", whereJson);
		System.out.println(filterJson.toString());
		
		// Set filter condition into rootJson
		groupcast.setPredefinedKeyValue("custom", custom);
		groupcast.setPredefinedKeyValue("filter", filterJson);
		
		groupcast.setPredefinedKeyValue("description",  title);
		groupcast.setPredefinedKeyValue("alert", content);
		groupcast.setPredefinedKeyValue("badge", 0);
		groupcast.setPredefinedKeyValue("sound", "chime");
		// TODO set 'production_mode' to 'true' if your app is under production mode
		groupcast.setPredefinedKeyValue("production_mode", "true");
		return "IOS设备"+groupcast.send();
	}
	
	/**
	 * IOS发送方法单个用户
	 * groupcast-组播(按照filter条件筛选特定用户群, 具体请参照filter参数)
	 * @throws Exception
	 */
	public String sendIOSGroupcast(String custom, String uid, String title,
			String content) throws Exception {
		IOSGroupcast groupcast = new IOSGroupcast();
		groupcast.setAppMasterSecret(Const.uMengIOSappMasterSecret);
		groupcast.setPredefinedKeyValue("appkey", Const.uMengIOSappkey);
		groupcast.setPredefinedKeyValue("timestamp", this.timestamp);
		/*  TODO
		 *  Construct the filter condition:
		 *  "where": 
		 *	{
		 *		"and": 
		 *		[
		 *			{"tag":"iostest"}
		 *		]
		 *	}
		 */
		JSONObject filterJson = new JSONObject();
		JSONObject whereJson = new JSONObject();
		JSONArray tagArray = new JSONArray();
		
		JSONObject testTag = new JSONObject();
		testTag.put("tag", uid);
		tagArray.put(testTag);
		
		whereJson.put("or", tagArray);
		filterJson.put("where", whereJson);
		System.out.println(filterJson.toString());
		
		// Set filter condition into rootJson
		groupcast.setPredefinedKeyValue("custom", custom);
		groupcast.setPredefinedKeyValue("filter", filterJson);
		
		groupcast.setPredefinedKeyValue("description",  title);
		groupcast.setPredefinedKeyValue("alert", content);
		groupcast.setPredefinedKeyValue("badge", 0);
		groupcast.setPredefinedKeyValue("sound", "chime");
		// TODO set 'production_mode' to 'true' if your app is under production mode
		groupcast.setPredefinedKeyValue("production_mode", "true");
		return "IOS设备"+groupcast.send();
	}
	
	/**
	 * 发送全部设备
	 * @throws Exception
	 */
	public String sendIOSBroadcast(String custom,String title,String text) throws Exception {
		IOSBroadcast broadcast = new IOSBroadcast();
		broadcast.setAppMasterSecret(Const.uMengIOSappMasterSecret);
		broadcast.setPredefinedKeyValue("appkey", Const.uMengIOSappkey);
		broadcast.setPredefinedKeyValue("timestamp", this.timestamp);
		broadcast.setPredefinedKeyValue("custom", custom);
		broadcast.setPredefinedKeyValue("alert", text);
		broadcast.setPredefinedKeyValue("badge", 0);
		broadcast.setPredefinedKeyValue("sound", "chime");
		broadcast.setPredefinedKeyValue("description",  title);
		// TODO set 'production_mode' to 'true' if your app is under production mode
		broadcast.setPredefinedKeyValue("production_mode", "true");
		// Set customized fields
		//broadcast.setCustomizedField("test", "helloworld");
		return "IOS设备"+ broadcast.send();
	}

}
