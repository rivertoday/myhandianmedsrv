package com.hrsb.cg.controller.d1api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hrsb.cg.intercept.D1apiAuth;
import com.hrsb.cg.model.Product;
import com.hrsb.cg.model.ProductComment;
import com.hrsb.cg.model.ProductImage;
import com.hrsb.cg.model.ProductLiterature;
import com.hrsb.cg.model.ProductQuestion;
import com.hrsb.cg.util.JsonUtil;
import com.hrsb.cg.util.Page;

/**
 * 汉典产品控制类
 * 
 * @author app001
 * 
 */
@Controller
@RequestMapping(value = "/d1api/product")
public class D1APIProductController extends D1APIController {

	//Added by JIANG, please refer to the log4j.xml and web.xml for configuration
	private static final Logger JJLogger = Logger.getLogger("RIVER_LOGGER");
	
	/**
	 * 汉典产品列表
	 * 
	 * @param modelMap
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	//@D1apiAuth
	@RequestMapping(value = "/list.json")
	@ResponseBody
	public Map<String, Object> list(ModelMap modelMap,
			@RequestParam(defaultValue = "1") int pageNo,
			@RequestParam(defaultValue = "6") int pageSize) {
		Map<String, Object> mymap = new HashMap<String, Object>();

		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("status", 1);
			Page<Product> page = new Page<Product>(pageNo, pageSize)
					.setParams(params);
			List<Product> products = productService.selectByPage(page);
			page.setResults(products);
			modelMap.put("page", page);

			mymap.put("success", "1");
			mymap.put("data", modelMap);
		} catch (Exception ex) {
			mymap.put("success", "0");
			mymap.put("errorMsg", ex.toString());
		}

		JJLogger.info(JsonUtil.maptojson(mymap));
		return mymap;
	}

	/**
	 * 产品详情
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
		ModelMap modelMap = new ModelMap();

		try {
			// 产品详情
			Product product = productService.getById(id);
			productService.modifyProductClickCount(product);
			List<ProductImage> productImages = productService
					.getImagesByProductId(id);
			modelMap.put("product", product);
			modelMap.put("productImages", productImages);
			modelMap.put("types", 1);

			mymap.put("success", "1");
			mymap.put("data", modelMap);
		} catch (Exception ex) {
			mymap.put("success", "0");
			mymap.put("errorMsg", ex.toString());
		}

		JJLogger.info(JsonUtil.maptojson(mymap));
		return mymap;
	}

	/**
	 * 常见问题
	 * 
	 * @param id
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	//@D1apiAuth
	@RequestMapping(value = "/problem.json")
	@ResponseBody
	public Map<String, Object> problem(@RequestParam long id) {
		Map<String, Object> mymap = new HashMap<String, Object>();
		ModelMap modelMap = new ModelMap();

		try {
			// 产品详情
			Product product = productService.getById(id);

			if (null != product) {
				// 常见问题
				List<ProductQuestion> productQuestions = productService
						.getQuestionByProductId(product.getId());
				modelMap.put("productQuestions", productQuestions);
				List<ProductImage> productImages = productService
						.getImagesByProductId(id);
				modelMap.put("productImages", productImages);
			}

			modelMap.put("product", product);
			modelMap.put("types", 2);

			mymap.put("success", "1");
			mymap.put("data", modelMap);
		} catch (Exception ex) {
			mymap.put("success", "0");
			mymap.put("errorMsg", ex.toString());
		}

		JJLogger.info(JsonUtil.maptojson(mymap));
		return mymap;
	}

	/**
	 * 专家点评
	 * 
	 * @param id
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	//@D1apiAuth
	@RequestMapping(value = "/comment.json")
	@ResponseBody
	public Map<String, Object> comment(@RequestParam long id) {
		Map<String, Object> mymap = new HashMap<String, Object>();
		ModelMap modelMap = new ModelMap();

		try {
			// 产品详情
			Product product = productService.getById(id);

			if (null != product) {
				// 专家点评
				List<ProductComment> productComments = productService
						.getCommentByProductId(product.getId());
				modelMap.put("productComments", productComments);
				List<ProductImage> productImages = productService
						.getImagesByProductId(id);
				modelMap.put("productImages", productImages);
			}

			modelMap.put("product", product);
			modelMap.put("types", 3);

			mymap.put("success", "1");
			mymap.put("data", modelMap);
		} catch (Exception ex) {
			mymap.put("success", "0");
			mymap.put("errorMsg", ex.toString());
		}

		JJLogger.info(JsonUtil.maptojson(mymap));
		return mymap;
	}

	/**
	 * 汉典文献
	 * 
	 * @param id
	 * @param c
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	//@D1apiAuth
	@RequestMapping(value = "/literature.json")
	@ResponseBody
	public Map<String, Object> literature(@RequestParam long id) {
		Map<String, Object> mymap = new HashMap<String, Object>();
		ModelMap modelMap = new ModelMap();

		List<ProductLiterature> productLiteratures = null;
		try {
			if (id>0) {
				productLiteratures = productService.getLiteratureByProductId(id);
			}else {
				productLiteratures = productLiteratureService.getAll();
			}

			modelMap.put("literature", productLiteratures);
			mymap.put("success", "1");
			mymap.put("data", modelMap);
		} catch (Exception ex) {
			mymap.put("success", "0");
			mymap.put("errorMsg", ex.toString());
		}

		JJLogger.info(JsonUtil.maptojson(mymap));
		return mymap;
	}
	
	/**
	 * 汉典文献阅读次数
	 * 
	 * @param id
	 * @param c
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	//@D1apiAuth
	@RequestMapping(value = "/updateliteraturereadcount.json")
	@ResponseBody
	public Map<String, Object> updateLiteratureReadCount(@RequestParam long id) {
		Map<String, Object> mymap = new HashMap<String, Object>();

		try {
			if (id > 0) {
				productLiteratureService.modifyProductLiteratureReadCount(id);
			}
			
			mymap.put("success", "1");
			mymap.put("data", null);
		} catch (Exception ex) {
			mymap.put("success", "0");
			mymap.put("errorMsg", ex.toString());
		}

		JJLogger.info(JsonUtil.maptojson(mymap));
		return mymap;
	}
	
	/**
	 * 汉典App首页查询
	 * 
	 * @param modelMap
	 * @return
	 */
	//@D1apiAuth
	@RequestMapping(value = "/generalsearch.json")
	@ResponseBody
	public Map<String, Object> generalSearch(@RequestBody String data) {
		Map<String, Object> mymap = new HashMap<String, Object>();
		ModelMap modelMap = new ModelMap();
		
		try {
			System.out.println("Here it is the general search POST data: " + data);
			JsonParser parser = new JsonParser(); // 创建json解析器
			JsonObject json = (JsonObject) parser.parse(data); // 创建jsonObject对象
			String keyword = json.get("gskeyword").getAsString();
			JJLogger.info("keyword: " + keyword);
					
			List<Product> products = productService.getByTitleAndIntroduction(keyword);			
			modelMap.put("products", products);
			
			List<ProductLiterature> prodliteratures = productService.getLiteratureByTitleAndSummary(keyword);
			modelMap.put("literature", prodliteratures);

			mymap.put("success", "1");
			mymap.put("data", modelMap);
		} catch (Exception ex) {
			mymap.put("success", "0");
			mymap.put("errorMsg", ex.toString());
		}

		JJLogger.info(JsonUtil.maptojson(mymap));
		return mymap;
	}

	/**
	 * 用药助手查询
	 * 
	 * @param modelMap
	 * @return
	 */
	//@D1apiAuth
	@RequestMapping(value = "/productsearch.json")
	@ResponseBody
	public Map<String, Object> productSearch(@RequestBody String data) {
		Map<String, Object> mymap = new HashMap<String, Object>();
		ModelMap modelMap = new ModelMap();
		
		try {
			System.out.println("Here it is the product search POST data: " + data);
			JsonParser parser = new JsonParser(); // 创建json解析器
			JsonObject json = (JsonObject) parser.parse(data); // 创建jsonObject对象
			Integer stype = json.get("pstype").getAsInt();
			
			String skeyword = null;
			if (stype.intValue() !=3)
				skeyword = json.get("pskeyword").getAsString();
			
			JJLogger.info("type: "+stype + "keyword: " + skeyword);
			
			List<Product> products = null;
			switch (stype.intValue()) {
			case 1:
				products = productService.getByTitleAndIntroduction(skeyword);
				modelMap.put("products", products);
				mymap.put("success", "1");
				break;
			case 2:
				products = productService.getByContent(skeyword);
				modelMap.put("products", products);
				mymap.put("success", "1");
				break;
			case 3:
				products = productService.getByClickCount();
				modelMap.put("products", products);
				mymap.put("success", "1");
				break;
			default:
				mymap.put("success", "0");
				mymap.put("errorMsg", "Unsupported search type!");
				JJLogger.info(JsonUtil.maptojson(mymap));
				return mymap;
			}			
			
			mymap.put("data", modelMap);
		} catch (Exception ex) {
			mymap.put("success", "0");
			mymap.put("errorMsg", ex.toString());
		}

		JJLogger.info(JsonUtil.maptojson(mymap));
		return mymap;
	}

	
	/**
	 * 下载
	 * 
	 * @param id
	 * @param c
	 * @param modelMap
	 * @throws Exception
	 */
	//@D1apiAuth
	@RequestMapping(value = "/download.json")
	@ResponseBody
	public Map<String, Object> download(@RequestParam Long articleid) {
		Map<String, Object> mymap = new HashMap<String, Object>();
		
		try {
			/*JJLogger.info("Here it is the login POST data: " + data);
			JsonParser parser = new JsonParser(); // 创建json解析器
			JsonObject json = (JsonObject) parser.parse(data); // 创建jsonObject对象
			Long articleid = json.get("articleid").getAsLong();*/
			
			ProductLiterature prodl = productLiteratureMapper.selectByPrimaryKey(articleid);

			mymap.put("success", "1");
			mymap.put("data", prodl);

			System.out.println(JsonUtil.maptojson(mymap));
			return mymap;
		} catch (Exception e) {
			mymap.put("success", "0");
			mymap.put("errorMsg", e.toString());
		}
		
		JJLogger.info(JsonUtil.maptojson(mymap));
		return mymap;
	}

}
