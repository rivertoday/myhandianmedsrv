package com.hrsb.cg.test;

import java.text.ParseException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	static ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{ "spring.xml","spring-mybatis.xml" });
	
	public static void main(String[] args) throws InterruptedException, ParseException {
		  
	}
	
}
