package com.hrsb.cg.controller.phoneweb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hrsb.cg.model.Product;
import com.hrsb.cg.model.ProductComment;
import com.hrsb.cg.model.ProductImage;
import com.hrsb.cg.model.ProductLiterature;
import com.hrsb.cg.model.ProductQuestion;
import com.hrsb.cg.model.UserLogin;
import com.hrsb.cg.util.AjaxUtil;
import com.hrsb.cg.util.Const;
import com.hrsb.cg.util.Page;

/**
 * 汉典产品控制类
 * @author app001
 *
 */
@Controller
@RequestMapping(value = "/phoneweb/product")
public class PhoneWebProductController extends PhoneWebController {
	
	private int bottom = 2;
	
	/**
	 * 汉典产品列表
	 * @param modelMap
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list")
	public String list(ModelMap modelMap, @RequestParam(defaultValue = "1") int pageNo, 
			@RequestParam(defaultValue = "6") int pageSize) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", 1);
		Page<Product> page = new Page<Product>(pageNo, pageSize).setParams(params);
		List<Product> products = productService.selectByPage(page);
		page.setResults(products);
		modelMap.put("page", page);
		modelMap.put("bottom", bottom);
		return "phoneweb/product/list";
	}
	
	/**
	 * 汉典产品列表 
	 * @param modelMap
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list_more")
	public void list_more(ModelMap modelMap, @RequestParam(defaultValue = "1") int pageNo, 
			@RequestParam(defaultValue = "6") int pageSize,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", 1);
		Page<Product> page = new Page<Product>(pageNo, pageSize).setParams(params);
		List<Product> products = productService.selectByPage(page);
		page.setResults(products);
		modelMap.put("page", page);
		modelMap.put("bottom", bottom);
		new AjaxUtil(request, response).print(products);
	}
	
	/**
	 * 产品详情
	 * @param id
	 * @param modelMap
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/detail")
	public String getDetai(@RequestParam long id, ModelMap modelMap) throws Exception {
		// 产品详情
		Product product = productService.getById(id);
		productService.modifyProductClickCount(product);
		List<ProductImage> productImages = productService.getImagesByProductId(id);
		modelMap.put("product", product);
		modelMap.put("productImages", productImages);
		modelMap.put("types", 1);
		modelMap.put("bottom", bottom);
		return "phoneweb/product/product_detail";
	}
	
	/**
	 * 常见问题
	 * @param id
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/problem")
	public String problem(@RequestParam long id, ModelMap modelMap) throws Exception {
		// 产品详情
		Product product = productService.getById(id);
		
		if (null != product) {
			// 常见问题
			List<ProductQuestion> productQuestions = productService.getQuestionByProductId(product.getId());
			modelMap.put("productQuestions", productQuestions);
			List<ProductImage> productImages = productService.getImagesByProductId(id);
			modelMap.put("productImages", productImages);
		}
		
		modelMap.put("product", product);
		modelMap.put("types", 2);
		modelMap.put("bottom", bottom);
		return "phoneweb/product/product_problem";
	}
	
	/**
	 * 专家点评
	 * @param id
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/comment")
	public String comment(@RequestParam long id, ModelMap modelMap) throws Exception {
		// 产品详情
		Product product = productService.getById(id);
		
		if (null != product) {
			// 专家点评
			List<ProductComment> productComments = productService.getCommentByProductId(product.getId());
			modelMap.put("productComments", productComments);
			List<ProductImage> productImages = productService.getImagesByProductId(id);
			modelMap.put("productImages", productImages);
		}
		
		modelMap.put("product", product);
		modelMap.put("types", 3);
		modelMap.put("bottom", bottom);
		return "phoneweb/product/product_comment";
	}
	
	/**
	 * 汉典文献
	 * @param id
	 * @param c
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/literature")
	public String literature(@RequestParam long id, ModelMap modelMap, HttpSession session) throws Exception {
		// 产品详情
		Product product = productService.getById(id);
		UserLogin userLogin = (UserLogin) session.getAttribute(Const.SESSION_PHONE_USER);
		
		if (null != product) {
			// 产品文献,是否下载，是否收藏
			List<ProductLiterature> productLiteratures = productService.getLiteratureByProductId(product.getId(), userLogin.getId());
			modelMap.put("productLiteratures", productLiteratures);
			List<ProductImage> productImages = productService.getImagesByProductId(id);
			modelMap.put("productImages", productImages);
		}
		
		modelMap.put("product", product);
		modelMap.put("types", 4);
		modelMap.put("bottom", bottom);
		return "phoneweb/product/product_literature";
	}
	
}
