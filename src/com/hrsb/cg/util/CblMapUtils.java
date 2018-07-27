package com.hrsb.cg.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class CblMapUtils {
	
	
	public static Map<String, String>  ShapeCodeMap(){
		Map<String, String> map=new HashMap<String, String>();
		map.put("块", "01");
		map.put("棒", "02");
		map.put("条", "02");
		map.put("板", "03");
		map.put("片", "04");
		map.put("箔", "05");
		map.put("丝", "06");
		map.put("管", "07");
		map.put("粉", "08");
		map.put("粒", "09");
		map.put("蒸发料", "10");
		map.put("靶材", "11");
		map.put("边角", "12");
		map.put("自由项", "13");
		map.put("网", "14");
		map.put("段", "15");
		map.put("海绵", "16");
		map.put("半球", "17");
		return map;
	}
	
	public static String  dictionaryCode_metal(String str){
		if(StringUtils.isNotBlank(str)){
			if("贵金属".equalsIgnoreCase(str) || "贵金属系列".equalsIgnoreCase(str)){
				return "01";
			}else if("大有色金属".equalsIgnoreCase(str) || "大有色金属系列".equalsIgnoreCase(str)){
				return "02";
			}else if("黑色金属".equalsIgnoreCase(str) || "黑色金属系列".equalsIgnoreCase(str)){
				return "03";
			}else if("小金属".equalsIgnoreCase(str) || "小金属系列".equalsIgnoreCase(str)){
				return "04";
			}else if("难熔金属".equalsIgnoreCase(str) || "难熔金属系列".equalsIgnoreCase(str)){
				return "05";
			}else if("类金属".equalsIgnoreCase(str) || "类金属系列".equalsIgnoreCase(str)){
				return "06";
			}else if("非金属".equalsIgnoreCase(str) || "非金属系列".equalsIgnoreCase(str)){
				return "07";
			}else if("稀土金属".equalsIgnoreCase(str) || "稀土金属系列".equalsIgnoreCase(str)){
				return "08";
			}else if("烃".equalsIgnoreCase(str) || "烃系列".equalsIgnoreCase(str)){
				return "09";
			}else if("烃的衍生物".equalsIgnoreCase(str) || "烃的衍生物系列".equalsIgnoreCase(str)){
				return "10";
			}else if("无机非金属".equalsIgnoreCase(str) || "无机非金属系列".equalsIgnoreCase(str)){
				return "11";
			}else if("混合物".equalsIgnoreCase(str) || "混合物系列".equalsIgnoreCase(str)){
				return "12";
			}
			
		}
		return null;
	}
	
	
	
	
	
	public  static String  purityCode(String purity){
		
		if(StringUtils.isNotBlank(purity)){
			if(purity.endsWith("%")){
				purity=purity.substring(0, purity.length()-1);
			}
			Double purityCode=Double.parseDouble(purity);
			if(purityCode<90 && purityCode>=0){
				return "0";
			}else if(purityCode<99 && purityCode>=90){
				return "1";
			}else if(purityCode<99.9 && purityCode>=99){
				return "2";
			}else if(purityCode<99.99 && purityCode>=99.9){
				return "3";
			}else if(purityCode<99.999 && purityCode>=99.99){
				return "4";
			}else if(purityCode<99.9999 && purityCode>=99.999){
				return "5";
			}
			
			
		}
		
		return null;
	}

	
	
	
	
}
