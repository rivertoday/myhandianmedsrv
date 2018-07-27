package com.hrsb.cg.util;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * GSON工具类
 * @author 
 *
 */
public class GsonUtil {
	/**
    *
    * 函数名称: parseData
    * 函数描述: 将json字符串转换为map
    * @param data
    * @return
    */
   public static Map<String, String> parseData(String data){
       GsonBuilder gb = new GsonBuilder();
       Gson g = gb.create();
       Map<String, String> map = g.fromJson(data, new TypeToken<Map<String, String>>() {}.getType());
       return map;
   }
   /**
    * 函数描述：将OBject转为json字符
    * @param obj
    * @return
    */
   public static String parseJson(Object obj){
	   Gson g = new Gson();
	   return g.toJson(obj);
   }
   
   /**
    * Map 转换json 字符串
    * @param map
    * @return
    */
   public static String mapToJsonString(Map<String, Object> map){
	   String result = "";
	   Gson gson = new Gson();
	   result = gson.toJson(map);
	   return result;
   }
}
