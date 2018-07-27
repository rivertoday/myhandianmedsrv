package com.hrsb.cg.controller.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.hrsb.cg.service.ProductService;
import com.hrsb.cg.util.Const;
import com.hrsb.cg.util.Page;

/**
 * 汉典产品控制类
 * @author app001
 *
 */
@Controller
@RequestMapping(value = "/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	/**
	 * 产品列表页
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String getProductList(@RequestParam(defaultValue = "1") int pageNo, 
			@RequestParam(defaultValue = Const.PAGESIZE) int pageSize, 
			ModelMap modelMap) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", 1);
		Page<Product> page = new Page<Product>(pageNo, pageSize).setParams(params);
		page.setOrderField("sorts");
		page.setOrderDirection("asc");
		List<Product> products = productService.selectByPage(page);
		page.setResults(products);
		modelMap.put("page", page);
		modelMap.put("type", 2);
		return "client/product/list";
	}
	
	/**
	 * 详情
	 * @param id
	 * @param modelMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/detail")
	public String getDetai(@RequestParam long id,@RequestParam(defaultValue="1",required=false) Integer diff, ModelMap modelMap, HttpSession session) throws Exception {
		UserLogin userLogin = (UserLogin) session.getAttribute(Const.SESSION_CLIENT_USER);
		Long userId=0L;
		// 产品详情
		Product product = productService.getById(id);
		// 产品多图
		List<ProductImage> productImages = productService.getImagesByProductId(id);
		// 常见问题
		List<ProductQuestion> productQuestions = productService.getQuestionByProductId(product.getId());
		// 专家点评
		List<ProductComment> productComments = productService.getCommentByProductId(product.getId());
		// 产品文献,是否下载，是否收藏
		if(userLogin!=null){
			userId=userLogin.getId();
		}
		List<ProductLiterature> productLiteratures = productService.getLiteratureByProductId(product.getId(), userId);
		modelMap.put("product", product);
		modelMap.put("productImages", productImages);
		modelMap.put("productQuestions", productQuestions);
		modelMap.put("productComments", productComments);
		modelMap.put("productLiteratures", productLiteratures);
		modelMap.put("type", 2);
		modelMap.put("diff", diff);
		return "client/product/detail";
	}
	
}
