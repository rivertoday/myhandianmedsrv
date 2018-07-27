package com.hrsb.cg.controller.util;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hrsb.cg.util.CharsetUtil;

@Controller
@RequestMapping("/")
public class PageController {
	@RequestMapping(value="/redirect")
	public ModelAndView topage(@Param(value="path") String path,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		ModelAndView model = new ModelAndView(path);
		String msg = request.getParameter("msg");
		CharsetUtil test = new CharsetUtil();
		@SuppressWarnings("unchecked")
		Map<String,String[]> map = request.getParameterMap();
		for (String s : map.keySet()) {
			model.addObject(s, request.getParameter(s));
		}
		if(msg != null && !msg.equals("")){
			model.addObject("msg", test.toUTF_8(msg));
		}
		return model;
	}
}
