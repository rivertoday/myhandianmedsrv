package com.hrsb.cg.util;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class MapUtil {
	public static <T> T MapToObject(HashMap<String,Object> map,Class<T> class1) throws InstantiationException, IllegalAccessException, IllegalArgumentException, Exception {
		Field[] fields=class1.getDeclaredFields();
		T t = null;
		if(fields.length>0)
		{
			t=class1.newInstance();
		}
		boolean flag;
		for (Field field : fields) {
			if(map.containsKey(field.getName())&&map.get(field.getName())!=null)
			{
				flag=false;
				if(!field.isAccessible())
				{
					field.setAccessible(true);
					flag=true;
				}
				if((field.getType() == java.util.Date.class || field.getType() == java.sql.Date.class) && map.get(field.getName()).getClass()!=field.getType())
				{//时间类型的转换
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					field.set(t,format.parse((String)map.get(field.getName())));
				}else if(field.getType() == java.sql.Timestamp.class && map.get(field.getName()).getClass()!=field.getType())
				{//Timestamp转换
					field.set(t,Timestamp.valueOf((String)map.get(field.getName())));
				}else if(field.getType() == java.lang.Long.class &&map.get(field.getName()).getClass()!=field.getType())
				{//Long
					field.set(t,Long.valueOf((String)map.get(field.getName())));
				}else if((field.getType() == int.class || field.getType() == java.lang.Integer.class)&& map.get(field.getName()).getClass()!=field.getType())
				{//int
					field.set(t,Integer.parseInt((String)map.get(field.getName())));
				}else
				{
					field.set(t,map.get((String)field.getName()));
				}
				if(flag)
				{
					field.setAccessible(false);
				}
			}
		}
		return t;
	}
	//類轉Map<String,Object>
	public static Map<String, Object> beanToMap(Object entity){  
        Map<String, Object> parameter = new HashMap<String, Object>();  
         Field[]   fields   =   entity.getClass().getDeclaredFields();  
        for(int i = 0; i < fields.length; i++){  
            String fieldName =  fields[i].getName();  
            Object o = null;  
            String firstLetter = fieldName.substring(0, 1).toUpperCase();  
               String getMethodName = "get" + firstLetter + fieldName.substring(1);  
               Method getMethod;  
            try {  
                getMethod = entity.getClass().getMethod(getMethodName, new Class[] {});  
                 o = getMethod.invoke(entity, new Object[] {});  
            } catch (Exception e) {  
                e.printStackTrace();  
            }    
            if(o != null){  
                parameter.put(fieldName, o);  
            }  
        }  
        return parameter;  
    }
	/** 
	 * 将map转换成url 
	 * @param map 
	 * @return 
	 */  
	public static String getUrlParamsByMap(Map<String, String> map) {  
	    if (map == null) {  
	        return "";  
	    }  
	    StringBuffer sb = new StringBuffer();  
	    for (Map.Entry<String, String> entry : map.entrySet()) {  
	    	if(!StringUtils.isBlank(entry.getValue())){
	        sb.append(entry.getKey() + "=" + entry.getValue());  
	        sb.append("&");
	    	}
	    }  
	    String s = sb.toString();  
	    if (s.endsWith("&")) {  
	        s = StringUtils.substringBeforeLast(s, "&");  
	    }  
	    return s;  
	}
	
	public static String getKey(Map map,String value,boolean islike){
		 Set set=map.entrySet();
		 Iterator it = set.iterator();
		 while(it.hasNext()){
			 Map.Entry entry=(Map.Entry)it.next();
			 if(islike){
				 if(StringUtils.contains(entry.getValue().toString(),value)){
					return entry.getKey().toString(); 
				 }
			 }else{
				 if(entry.getValue().equals(value)) return entry.getKey().toString();
			 }
		 }
		 return "";
	}
	public static Integer getIntegerByDoubleStr(Object doublestr){
		Double b = Double.parseDouble(doublestr.toString());
		return Integer.valueOf(b.intValue());
	}
}
