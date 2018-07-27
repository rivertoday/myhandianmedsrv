package com.hrsb.cg.controller.manage;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.ehcache.util.ProductInfo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hrsb.cg.controller.client.BaseController;
import com.hrsb.cg.dao.ProductCommentMapper;
import com.hrsb.cg.dao.ProductImageMapper;
import com.hrsb.cg.dao.ProductQuestionMapper;
import com.hrsb.cg.model.AreaProv;
import com.hrsb.cg.model.Department;
import com.hrsb.cg.model.HospitalGrade;
import com.hrsb.cg.model.Manager;
import com.hrsb.cg.model.Product;
import com.hrsb.cg.model.ProductComment;
import com.hrsb.cg.model.ProductImage;
import com.hrsb.cg.model.ProductLiterature;
import com.hrsb.cg.model.ProductQuestion;
import com.hrsb.cg.model.UserDetail;
import com.hrsb.cg.service.ProductService;
import com.hrsb.cg.util.AjaxUtil;
import com.hrsb.cg.util.Const;
import com.hrsb.cg.util.Page;
import com.hrsb.cg.util.Tools;

@Controller
@RequestMapping(value="/manageProduct")
public class ManageProductController extends BaseController{
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductQuestionMapper productQuestionMapper;
	
	@Autowired
	ProductCommentMapper productCommentMapper;
	
	@Autowired
	ProductImageMapper productImageMapper;
	
	/**
	 * 产品列表页
	 * @param pageCurrent
	 * @param pageSize
	 * @param title
	 * @param request
	 * @return
	 */
	@RequestMapping(value="productList")
	public ModelAndView productList(@RequestParam(defaultValue="1",required=false)Integer pageCurrent, 
			@RequestParam(defaultValue="15",required=false)Integer pageSize,@RequestParam(required=false) String title,HttpServletRequest request){
		ModelAndView mv=new ModelAndView("/manage/product/product-list");
		Page<Product> page=new Page<Product>(pageCurrent,pageSize);
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("title", title);
		page.setParams(params);
		page.setOrderDirection("asc");
		page.setOrderField("sorts");
		List<Product> proList=productService.selectByPage(page);
		page.setResults(proList);
		mv.addObject("page", page);
		return mv;
	}

	/**
	 * 产品编辑
	 * @param productId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="productSaveUI")
	public ModelAndView productSaveUI(@RequestParam(required=false)Long productId
			,HttpServletRequest request){
		ModelAndView mv=new ModelAndView("/manage/product/product-edit");
		Product product= productService.selectById(productId);
		mv.addObject("product", product);
		return mv;
	}
	
	/**
	 * 产品保存
	 * @param product
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/productSave")
	public void productSave(@ModelAttribute Product product,HttpServletRequest request,HttpServletResponse response) throws IOException{
		Manager m=(Manager)request.getSession().getAttribute(Const.SESSION_MANAGER);
		product.setManagerId(m.getId());
		product.setOperateTime(new Date());
			int i=productService.saveProduct(product);
				if(i==1){
					new AjaxUtil(request, response).JsonType("200", "保存成功！", "", "product", "", "", "保存成功！",true);
				}else{
					new AjaxUtil(request, response).JsonType("300", "保存失败！", "", "product", "", "", "保存失败",true);	
				}
	}
	
	/**
	 * 产品修改上下架
	 * @param productId
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/updStatus")
	public void updStatus(Long productId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		Product product= productService.selectById(productId);
		if(product!=null){
			if(product.getStatus()==1){
				product.setStatus((byte)2);
			}else{
				product.setStatus((byte)1);
			}
		}
		int i=productService.saveProduct(product);
				if(i==1){
					new AjaxUtil(request, response).JsonType("200", "保存成功！", "", "product", "", "", "保存成功！",true);
				}else{
					new AjaxUtil(request, response).JsonType("300", "保存失败！", "", "product", "", "", "保存失败",true);	
				}
	}
	
	/**
	 * 每个产品的问题列表页 productId (必传)
	 * @param pageCurrent
	 * @param pageSize
	 * @param productId
	 * @param question
	 * @param answer
	 * @param request
	 * @return
	 */
	@RequestMapping(value="questionList")
	public ModelAndView questionList(@RequestParam(defaultValue="1",required=false)Integer pageCurrent, 
			@RequestParam(defaultValue="15",required=false)Integer pageSize,
			@RequestParam(required=true) Long productId,
			@RequestParam(required=false) String question,
			@RequestParam(required=false) String answer,HttpServletRequest request){
		ModelAndView mv=new ModelAndView("/manage/product/productQuestion-list");
		Page<ProductQuestion> page=new Page<ProductQuestion>(pageCurrent,pageSize);
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("productId", productId);
		params.put("question", question);
		params.put("answer", answer);
		page.setParams(params);
		page.setOrderDirection("desc");
		page.setOrderField("operate_time");
		List<ProductQuestion> proQuestionList=productService.selectProductQuestionByPage(page);
		page.setResults(proQuestionList);
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 问题编辑页
	 * @param questionId
	 * @param productId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="questionSaveUI")
	public ModelAndView questionSaveUI(@RequestParam(required=false)Long questionId,
			@RequestParam(required=false)Long productId,HttpServletRequest request){
		ModelAndView mv=new ModelAndView("/manage/product/question-edit");
		ProductQuestion productQuestion= productService.selectByQuestionId(questionId);
		mv.addObject("productQuestion", productQuestion);
		mv.addObject("productId", productId);
		return mv;
	}
	
	/**
	 * 问题保存
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/questionSave")
	public void questionSave(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Manager m=(Manager)request.getSession().getAttribute(Const.SESSION_MANAGER);
		String question=request.getParameter("question");
		String answer=request.getParameter("answer");
		String questionId=request.getParameter("questionId");
		String state=request.getParameter("state");
		String productId=request.getParameter("productId");
		ProductQuestion productQuestion=new ProductQuestion();
		productQuestion.setManagerId(m.getId());
		productQuestion.setOperateTime(new Date());
		productQuestion.setAnswer(answer);
		productQuestion.setQuestion(question);
		if(StringUtils.isNotEmpty(questionId)){
			productQuestion.setId(Long.parseLong(questionId));
		}
		if(StringUtils.isNotEmpty(state)){
			productQuestion.setState(Byte.parseByte(state));
		}
		if(StringUtils.isNotEmpty(productId)){
			productQuestion.setProductId(Long.parseLong(productId));
		}
		
			int i=productService.saveProductQuestion(productQuestion);
				if(i==1){
					new AjaxUtil(request, response).JsonType("200", "保存成功！", "", "productQuestion", "", "", "保存成功！",true);
				}else{
					new AjaxUtil(request, response).JsonType("300", "保存失败！", "", "productQuestion", "", "", "保存失败",true);	
				}
	}
	
	
	/**
	 * 问题上下架
	 * @param questionId
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/updQuestionState")
	public void updQuestionState(Long questionId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		ProductQuestion productQuestion= productService.selectByQuestionId(questionId);
		if(productQuestion!=null){
			if(productQuestion.getState()==1){
				productQuestion.setState((byte)2);
			}else{
				productQuestion.setState((byte)1);
			}
		}
		int i=productService.saveProductQuestion(productQuestion);
				if(i==1){
					new AjaxUtil(request, response).JsonType("200", "保存成功！", "", "productQuestion", "", "", "保存成功！",false);
				}else{
					new AjaxUtil(request, response).JsonType("300", "保存失败！", "", "productQuestion", "", "", "保存失败",false);	
				}
	}
	
	@RequestMapping(value="/questionDel")
	public void questionDel(Long id,HttpServletRequest request,HttpServletResponse response) throws IOException{
		int i=productQuestionMapper.deleteByPrimaryKey(id);
				if(i==1){
					new AjaxUtil(request, response).JsonType("200", "删除成功！", "", "productQuestion", "", "", "删除成功！",false);
				}else{
					new AjaxUtil(request, response).JsonType("300", "删除失败！", "", "productQuestion", "", "", "删除失败",false);	
				}
	}
	
	
	@RequestMapping(value="/productComment")
	public ModelAndView productComment(@RequestParam(value="pageCurrent",defaultValue="1") Integer pageCurrent,@RequestParam(value="pageSize",defaultValue="15") Integer pageSize,
			@RequestParam(required=true) Long productId,@RequestParam(required=false) String userName ){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("productId", productId);
		params.put("userName", userName);
		Page<ProductComment> page=new Page<ProductComment>(pageCurrent, pageSize);
		page.setParams(params);
		List<ProductComment> productComments=productService.selectProductCommentByPage(page);
		page.setResults(productComments);
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/manage/product/productComment-list");
		modelAndView.addObject("page", page);
		List<AreaProv> areaProvs=getAllProvince();
		List<HospitalGrade> hospitalProfessionalList=hospitalGradeList(2);
		modelAndView.addObject("areaProvs", areaProvs);
		modelAndView.addObject("hospitalProfessionalList", hospitalProfessionalList);
		return modelAndView;
	}

	
	/**
	 * 专家点评页
	 * @param questionId
	 * @param productId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="productCommentSaveUI")
	public ModelAndView productCommentSaveUI(@RequestParam(required=false)Long productCommentId,
			@RequestParam(required=false)Long productId,HttpServletRequest request){
		ModelAndView mv=new ModelAndView("/manage/product/productComment-edit");
		ProductComment productComment= productService.selectByProductCommentId(productCommentId);
		mv.addObject("productComment", productComment);
		mv.addObject("productId", productId);
		List<HospitalGrade> hospitalGradeList=hospitalGradeList(1);
		List<HospitalGrade> hospitalProfessionalList=hospitalGradeList(2);
		List<Department> departments=departmentList();
		mv.addObject("hospitalGradeList", hospitalGradeList);
		mv.addObject("hospitalProfessionalList", hospitalProfessionalList);
		mv.addObject("departments", departments);
		return mv;
	}
	
	/**
	 * 专家点评保存
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/productCommentSave")
	public void productCommentSave(@RequestParam(required=false) Long productId,@ModelAttribute ProductComment productComment,HttpServletRequest request,HttpServletResponse response) throws IOException{
		Manager m=(Manager)request.getSession().getAttribute(Const.SESSION_MANAGER);
		productComment.setManagerId(m.getId());
		productComment.setOperateTime(new Date());
		if(productId!=null){
			productComment.setProductId(productId);
		}
		
			int i=productService.saveProductComment(productComment);
				if(i==1){
					new AjaxUtil(request, response).JsonType("200", "保存成功！", "", "productComment", "", "", "保存成功！",true);
				}else{
					new AjaxUtil(request, response).JsonType("300", "保存失败！", "", "productComment", "", "", "保存失败",true);	
				}
	}
	
	/**
	 * 专家点评改变状态
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/updProductCommentState")
	public void updProductCommentState(Long productCommentId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		ProductComment productComment= productService.selectByProductCommentId(productCommentId);
			if(productComment!=null){
				if(productComment.getState()==1){
					productComment.setState((byte)2);
				}else{
					productComment.setState((byte)1);	
				}
			}
			int i=productService.saveProductComment(productComment);
				if(i==1){
					new AjaxUtil(request, response).JsonType("200", "保存成功！", "", "productComment", "", "", "保存成功！",false);
				}else{
					new AjaxUtil(request, response).JsonType("300", "保存失败！", "", "productComment", "", "", "保存失败",false);	
				}
	}
	
	@RequestMapping(value="/commentDel")
	public void commentDel(Long id,HttpServletRequest request,HttpServletResponse response) throws IOException{
		int i=productCommentMapper.deleteByPrimaryKey(id);
		if(i==1){
			new AjaxUtil(request, response).JsonType("200", "删除成功！", "", "productComment", "", "", "删除成功！",false);
		}else{
			new AjaxUtil(request, response).JsonType("300", "删除失败！", "", "productComment", "", "", "删除失败",false);	
		}
	}
	
	@RequestMapping(value="/productLiteratureList")
	public ModelAndView productLiteratureList(@RequestParam(value="pageCurrent",defaultValue="1") Integer pageCurrent,@RequestParam(value="pageSize",defaultValue="15") Integer pageSize,
			@RequestParam(required=true) Long productId,@RequestParam(required=false) String title){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("productId", productId);
		params.put("title", title);
		Page<ProductLiterature> page=new Page<ProductLiterature>(pageCurrent, pageSize);
		page.setParams(params);
		page.setOrderDirection("desc");
		page.setOrderField("operate_time");
		List<ProductLiterature> productLiteratures=productService.selectProductLiteratureByPage(page);
		page.setResults(productLiteratures);
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/manage/product/productLiterature-list");
		modelAndView.addObject("page", page);
		return modelAndView;
	}
	
	/**
	 * 产品文献页
	 * @param questionId
	 * @param productId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="productLiteratureSaveUI")
	public ModelAndView productLiteratureSaveUI(@RequestParam(required=false)Long productLiteratureId,
			@RequestParam(required=false)Long productId,HttpServletRequest request){
		ModelAndView mv=new ModelAndView("/manage/product/productLiterature-edit");
		ProductLiterature productLiterature= productService.selectByProductLiteratureId(productLiteratureId);
		mv.addObject("productLiterature", productLiterature);
		mv.addObject("productId", productId);
		return mv;
	}
	
	/**
	 * 专家点评保存
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/productLiteratureSave")
	public void productLiteratureSave(@RequestParam(required=false) Long productId,@ModelAttribute ProductLiterature productLiterature,HttpServletRequest request,HttpServletResponse response) throws IOException{
		Manager m=(Manager)request.getSession().getAttribute(Const.SESSION_MANAGER);
		productLiterature.setManagerId(m.getId());
		productLiterature.setOperateTime(new Date());
		if(productId!=null){
			productLiterature.setProductId(productId);
		}
		
			int i=productService.saveProductLiterature(productLiterature);
				if(i==1){
					new AjaxUtil(request, response).JsonType("200", "保存成功！", "", "productLiteratureList", "", "", "保存成功！",true);
				}else{
					new AjaxUtil(request, response).JsonType("300", "保存失败！", "", "productLiteratureList", "", "", "保存失败",true);	
				}
	}
	
	/**
	 * 产品文献改变状态
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/updproductLiteratureState")
	public void updproductLiteratureState(Long productLiteratureId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		ProductLiterature productLiterature= productService.selectByProductLiteratureId(productLiteratureId);
			if(productLiterature!=null){
				if(productLiterature.getState()==1){
					productLiterature.setState((byte)2);
				}else{
					productLiterature.setState((byte)1);	
				}
			}
			int i=productService.saveProductLiterature(productLiterature);
				if(i==1){
					new AjaxUtil(request, response).JsonType("200", "保存成功！", "", "productComment", "", "", "保存成功！",false);
				}else{
					new AjaxUtil(request, response).JsonType("300", "保存失败！", "", "productComment", "", "", "保存失败",false);	
				}
	}
	
	/**
	 * 图片添加或者编辑
	 * @param id
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "productImageUI")
	public ModelAndView productImageUI(Integer productId, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv=new ModelAndView("/manage/product/productImage-edit");
		List<ProductImage> productImages = productImageMapper.selectByProductId(productId);
		mv.addObject("productImages", productImages);
		mv.addObject("productId", productId);
		return mv;
	}
	
	/**
	 * 图片保存
	 * @param session
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "productImageSave")
	public void productImageSave(HttpSession session,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Manager manager = (Manager) session.getAttribute(Const.SESSION_MANAGER);
		String productId = request.getParameter("productId");
		String[] id = request.getParameterValues("id");
		String[] image = request.getParameterValues("image");
		
		try {
			for (int i=0; i<id.length; i++) {
				ProductImage productImage = new ProductImage();
				productImage.setProductId(Long.valueOf(productId));
				productImage.setManagerId(manager.getId());
				productImage.setOperateTime(new Date());
				productImage.setImage(image[i]);
				//id=0是添加：有图片的时候保存，没有图片但是又介绍的时候保存
				if ("0".equals(id[i]) && !StringUtils.isEmpty(image[i])) {
					productImageMapper.insertSelective(productImage);
				} else {
					if (!StringUtils.isEmpty(image[i])) {
						productImage.setId(Long.valueOf(id[i]));
						productImageMapper.updateByPrimaryKeySelective(productImage);
					} else {
						productImageMapper.deleteByPrimaryKey(Long.valueOf(id[i]));
					}
				}
			}
			
			new AjaxUtil(request, response).JsonType("200", "操作完成", "", "product", "", "", "操作完成", true);
		} catch (NumberFormatException e) {
			new AjaxUtil(request, response).JsonType("300", "操作失败", "", "product", "", "", "操作失败", true);
		}
	}
	
	/**
	 * 删除产品 逻辑删除
	 * @param id
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "del")
	public void productDel(Integer productId, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int i=0;
		i=productService.del(productId);
		if(i==1){
			new AjaxUtil(request, response).JsonType("200", "删除成功！", "", "product", "", "", "删除成功！",false);
		}else{
			new AjaxUtil(request, response).JsonType("300", "删除失败！", "", "product", "", "", "删除失败",false);	
		}
	}
	
	
}
