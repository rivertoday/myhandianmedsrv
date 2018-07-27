package com.hrsb.cg.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class IDUtil {
	/**
	 * 操作员
	 * @return
	 */
	public static String manegerID(){
		String id = "A"+(System.currentTimeMillis()+"").substring(0,8);
		return id;
	}
	public static void main(String[] args) {
		System.out.println(manegerID());
	}
	/**
	 * 用户
	 * @return
	 */
	public static String userID(){
		String id = "B"+(System.currentTimeMillis()+"").substring(0,8);
		return id;
	}
	
	/**
	 * 用户2
	 * @return
	 */
	public static String userID2(){
		String id = "B"+(System.currentTimeMillis()+"").substring(0,8);
		return id;
	}
	
	/**
	 * 方案
	 * @return
	 */
	public static String fanganID(){
		String id = "C"+(System.currentTimeMillis()+"").substring(0,8);
		return id;
	}
	/**
	 * 项目
	 * @return
	 */
	public static String projectID(){
		String id = "P"+(System.currentTimeMillis()+"").substring(0,8);
		return id;
	}
	/**
	 * 作品
	 * @return
	 */
	public static String zuopinID(){
		String id = "S"+(System.currentTimeMillis()+"").substring(0,8);
		return id;
	}
	/**
	 * 投诉
	 * @return
	 */
	public static String tousuID(){
		String id = "T"+(System.currentTimeMillis()+"").substring(0,8);
		return id;
	}
	/**
	 * 试题
	 * @return
	 */
	public static String shitiID(){
		String id = "J"+(System.currentTimeMillis()+"").substring(0,8);
		return id;
	}
	/**
	 * 试卷
	 * @return
	 */
	public static String shijuanID() {
		String id = "K"+(System.currentTimeMillis()+"").substring(0,8);
		return id;
	}
}
