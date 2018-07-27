package com.hrsb.cg.controller.d1api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hrsb.cg.intercept.D1apiAuth;
import com.hrsb.cg.model.HerbalMedicine;
import com.hrsb.cg.util.JsonUtil;
/**
 * 汉典产品控制类
 * 
 * @author app001
 * 
 */
@Controller
@RequestMapping(value = "/d1api/yaodian")
public class D1APIYaoDianController extends D1APIController {

	//Added by JIANG, please refer to the log4j.xml and web.xml for configuration
	private static final Logger JJLogger = Logger.getLogger("RIVER_LOGGER");
	
	/**
	 * 获取所有的拼音列表
	 */
	//@D1apiAuth
	@RequestMapping(value = "/list.json")
	@ResponseBody
	public Map<String, Object> list() {
		Map<String, Object> mymap = new HashMap<String, Object>();

		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("status", 1);

			mymap.put("success", "1");
			mymap.put("data", "pending to do");
		} catch (Exception ex) {
			mymap.put("success", "0");
			mymap.put("errorMsg", ex.toString());
		}

		JJLogger.info(JsonUtil.maptojson(mymap));
		return mymap;
	}
	
	/*
	 * 获取一个拼音范畴下的所有草药的名单
	 */
	@RequestMapping(value = "/category.json")
	@ResponseBody
	public Map<String, Object> category(@RequestParam String cate) {
		Map<String, Object> mymap = new HashMap<String, Object>();

		try {
			List<HerbalMedicine> cateList = herbalMedicineService.getCategory(cate);
			mymap.put("success", "1");
			mymap.put("data", cateList);
		} catch (Exception ex) {
			mymap.put("success", "0");
			mymap.put("errorMsg", ex.toString());
		}

		JJLogger.info(JsonUtil.maptojson(mymap));
		return mymap;
	}

	/**
	 * 草药详情
	 * 
	 * @param id
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	//@D1apiAuth
	@RequestMapping(value = "/detail.json")
	@ResponseBody
	public Map<String, Object> getDetai(@RequestParam long id) {
		Map<String, Object> mymap = new HashMap<String, Object>();

		try {
			// 产品详情
			HerbalMedicine herbMed = herbalMedicineService.getDetails(id);

			mymap.put("success", "1");
			mymap.put("data", herbMed);
		} catch (Exception ex) {
			mymap.put("success", "0");
			mymap.put("errorMsg", ex.toString());
		}

		JJLogger.info(JsonUtil.maptojson(mymap));
		return mymap;
	}

	@RequestMapping(value = "/search.json")
	@ResponseBody
	public Map<String, Object> search(@RequestBody String data) {
		Map<String, Object> mymap = new HashMap<String, Object>();

		try {
			System.out.println("Here it is the general search POST data: " + data);
			JsonParser parser = new JsonParser(); // 创建json解析器
			JsonObject json = (JsonObject) parser.parse(data); // 创建jsonObject对象
			String hmkey = json.get("name").getAsString();
			JJLogger.info("name: " + hmkey);
			
			List<HerbalMedicine> nameList = herbalMedicineService.searchHerbal(hmkey);
			mymap.put("success", "1");
			mymap.put("data", nameList);
		} catch (Exception ex) {
			mymap.put("success", "0");
			mymap.put("errorMsg", ex.toString());
		}

		JJLogger.info(JsonUtil.maptojson(mymap));
		return mymap;
	}
}
