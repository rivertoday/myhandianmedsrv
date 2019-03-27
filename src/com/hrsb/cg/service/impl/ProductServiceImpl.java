package com.hrsb.cg.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hrsb.cg.dao.CollectionMapper;
import com.hrsb.cg.dao.DepartmentMapper;
import com.hrsb.cg.dao.DownloadRecordMapper;
import com.hrsb.cg.dao.HospitalGradeMapper;
import com.hrsb.cg.dao.LiteratureMapper;
import com.hrsb.cg.dao.ProductCommentMapper;
import com.hrsb.cg.dao.ProductImageMapper;
import com.hrsb.cg.dao.ProductLiteratureMapper;
import com.hrsb.cg.dao.ProductMapper;
import com.hrsb.cg.dao.ProductQuestionMapper;
import com.hrsb.cg.dao.UserDetailMapper;
import com.hrsb.cg.model.Collection;
import com.hrsb.cg.model.Department;
import com.hrsb.cg.model.DownloadRecord;
import com.hrsb.cg.model.HospitalGrade;
import com.hrsb.cg.model.Literature;
import com.hrsb.cg.model.Product;
import com.hrsb.cg.model.ProductComment;
import com.hrsb.cg.model.ProductImage;
import com.hrsb.cg.model.ProductLiterature;
import com.hrsb.cg.model.ProductQuestion;
import com.hrsb.cg.model.UserDetail;
import com.hrsb.cg.service.ProductService;
import com.hrsb.cg.util.Page;
import com.hrsb.cg.util.Tools;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductMapper productMapper;
	@Autowired
	ProductQuestionMapper productQuestionMapper;
	@Autowired
	ProductCommentMapper productCommentMapper;
	@Autowired
	ProductLiteratureMapper productLiteratureMapper;
	@Autowired
	ProductImageMapper productImageMapper;
	@Autowired
	DepartmentMapper departmentMapper;
	@Autowired
	HospitalGradeMapper hospitalGradeMapper;
	@Autowired
	DownloadRecordMapper downloadRecordMapper;
	@Autowired
	CollectionMapper collectionMapper;
	@Autowired
	UserDetailMapper userDetailMapper;
	@Autowired
	LiteratureMapper literatureMapper;

	@Override
	public List<Product> selectByPage(Page<Product> page) {
		return productMapper.selectByPage(page);
	}
	
	//Added by JIANG
	@Override
	public List<Product> getByTitleAndIntroduction(String gskeyword) {
		return productMapper.selectByTitleAndIntroduction(gskeyword);
	}
	
	@Override
	public List<Product> getByContent(String pskeyword) {
		return productMapper.selectByContent(pskeyword);
	}
	
	@Override
	public List<Product> getByClickCount() {
		return productMapper.selectByClickCount();
	}

	@Override
	public Product selectById(Long productId) {
		return productMapper.selectByPrimaryKey(productId);
	}

	@Override
	public int saveProduct(Product product) {
		int row=0;
		if(product.getId()!=null){
			row=productMapper.updateByPrimaryKeySelective(product);
		}else{
			product.setCreateTime(new Date());
			row=productMapper.insertSelective(product);
		}
		return row;
	}

	@Override
	public List<ProductQuestion> selectProductQuestionByPage(
			Page<ProductQuestion> page) {
		return productQuestionMapper.selectProductQuestionByPage(page);
	}

	@Override
	public ProductQuestion selectByQuestionId(Long questionId) {
		return productQuestionMapper.selectByPrimaryKey(questionId);
	}

	@Override
	public int saveProductQuestion(ProductQuestion productQuestion) {
		// TODO Auto-generated method stub
		int row=0;
		if(productQuestion.getId()!=null){
			row=productQuestionMapper.updateByPrimaryKeySelective(productQuestion);
		}else{
			row=productQuestionMapper.insertSelective(productQuestion);
		}
		return row;
	}

	@Override
	public List<ProductComment> selectProductCommentByPage(
			Page<ProductComment> page) {
		return productCommentMapper.selectProductCommentByPage(page);
	}

	@Override
	public int saveProductComment(ProductComment productComment) {
		int row=0;
		if(productComment.getId()==null){
			productComment.setCreateTime(new Date());
			row=productCommentMapper.insertSelective(productComment);
		}else{
			row=productCommentMapper.updateByPrimaryKeySelective(productComment);
		}
		return row;
	}

	@Override
	public ProductComment selectByProductCommentId(Long productCommentId) {
		// TODO Auto-generated method stub
		return productCommentMapper.selectByPrimaryKey(productCommentId);
	}

	@Override
	public List<ProductLiterature> selectProductLiteratureByPage(
			Page<ProductLiterature> page) {
		
		return productLiteratureMapper.selectProductLiteratureByPage(page);
	}
	
	//Added by JIANG He at 20190327
	@Override
	public List<ProductLiterature> selectAPIProductLiteratureByPage(
			Page<ProductLiterature> page) {
		
		return productLiteratureMapper.selectAPIProductLiteratureByPage(page);
	}

	@Override
	public ProductLiterature selectByProductLiteratureId(
			Long productLiteratureId) {
		return productLiteratureMapper.selectByPrimaryKey(productLiteratureId);
	}

	@Override
	public int saveProductLiterature(ProductLiterature productLiterature) {
		int row=0;
		if(productLiterature.getId()==null){
			row=productLiteratureMapper.insertSelective(productLiterature);
		}else{
			row=productLiteratureMapper.updateByPrimaryKeySelective(productLiterature);
		}
		return row;
	}

	@Override
	public List<ProductImage> getImagesByProductId(long id) {
		return productImageMapper.selectByProductId(id);
	}

	@Override
	public List<ProductQuestion> getQuestionByProductId(Long productId) {
		return productQuestionMapper.selectByProductId(productId);
	}

	@Override
	public List<ProductComment> getCommentByProductId(Long productId) {
		List<ProductComment> productComments = productCommentMapper.selectByProductId(productId);
		
	/*	if (!CollectionUtils.isEmpty(productComments)) {
			for (ProductComment productComment : productComments) {
				Department department = departmentMapper.selectByPrimaryKey(Integer.valueOf(productComment.getDepartment()));
				productComment.setDepartment(department.getName());
				HospitalGrade hospitalGrade = hospitalGradeMapper.selectByPrimaryKey(Integer.valueOf(productComment.getProfessional()));
				productComment.setProfessional(hospitalGrade.getName());
			}
		}*/
		
		return productComments;
	}

	@Override
	public List<ProductLiterature> getLiteratureByProductId(Long productId,
			Long userId) {
		List<ProductLiterature> productLiteratures = productLiteratureMapper.selectByProductId(productId);
		
		if (!CollectionUtils.isEmpty(productLiteratures)) {
			for (ProductLiterature productLiterature : productLiteratures) {
				DownloadRecord downloadRecord = downloadRecordMapper.selectByLiteratureIdAndUserId(productLiterature.getId(), userId, (byte) 2);
			
				if (null != downloadRecord) {
					productLiterature.setIsDownload(1);
				} else {
					productLiterature.setIsDownload(0);
				}
				
				Collection collection = collectionMapper.selectByLiteratureIdAndUserId(productLiterature.getId(), userId, (byte) 2);
			
				if (null != collection) {
					productLiterature.setIsCollection(1);
				} else {
					productLiterature.setIsCollection(0);
				}
			}
		}
		
		return productLiteratures;
	}
	
	//Added by JIANG
	@Override
	public List<ProductLiterature> getLiteratureByProductId(Long productId) {
		List<ProductLiterature> productLiteratures = productLiteratureMapper.selectByProductId(productId);
				
		return productLiteratures;
	}
	
	//Added by JIANG
	@Override
	public List<ProductLiterature> getLiteratureByTitleAndSummary(String gskeyword) {
		List<ProductLiterature> productLiteratures = productLiteratureMapper.selectByTitleAndSummary(gskeyword);
					
		return productLiteratures;
	}

	@Override
	public Product getById(long id) {
		Product product = productMapper.selectByPrimaryKey(id);
		product.setContent(Tools.replaceContent(product.getContent()));
		return product;
	}

	@Override
	public String download(long id, Long userId, byte types) {
		String msg = "fail";
		// 如果types=2汉典文献，判断是否下载过，直接添加下载记录；如果types=1万方文献，判断是否下载过，判断下载次数
		if (types == 2) {
			DownloadRecord downloadRecord = new DownloadRecord();
			downloadRecord.setUserId(userId);
			downloadRecord.setLiteratureId(id);
			downloadRecord.setTypes(types);
			downloadRecord.setStatus((byte) 1);
			downloadRecord.setCreateTime(new Date());
			downloadRecordMapper.insertSelective(downloadRecord);
			msg = "ok";
		}
		
		if (types == 1) {
			if (checkIsDownload(id, userId, types) == 0) {
				UserDetail userDetail = userDetailMapper.selectByUserId(userId);
				boolean flag = false;
				// 1默认:判断资料是否完善；如果完善无限下载，否则判断是否还有下载次数2人工:判断是否还有下载次数
				if (userDetail.getDownloadTypes() == 1) {
					if (userDetail.getStatus() == 3) {
						flag = true;
					} else {
						int downloadCount = downloadRecordMapper.selectCountByUserId(userDetail.getUserId());
						
						if (userDetail.getDownloadCount() - downloadCount > 0) {
							flag = true;
						}
					}
				} else {
					int downloadCount = downloadRecordMapper.selectCountByUserId(userDetail.getUserId());
					
					if (userDetail.getDownloadCount() - downloadCount > 0) {
						flag = true;
					}
				}
				
				if (flag) {
					DownloadRecord downloadRecord = new DownloadRecord();
					downloadRecord.setUserId(userId);
					downloadRecord.setLiteratureId(id);
					downloadRecord.setTypes(types);
					downloadRecord.setCreateTime(new Date());
					downloadRecord.setStatus((byte) 1);
					downloadRecordMapper.insertSelective(downloadRecord);
					// 修改下载次数
					if (types == 1) {
						Literature literature = literatureMapper.selectByPrimaryKey(id);
						Literature literature2 = new Literature();
						literature2.setId(id);
						literature2.setDownloadCount(literature.getDownloadCount() + 1);
						literatureMapper.updateDownloadCount(literature2);
					}
					
					msg = "ok";
				}
			} else if (checkIsDownload(id, userId, types) == 2) {
				// 修改状态
				DownloadRecord downloadRecord = downloadRecordMapper.selectByLiteratureIdAndUserId(id, userId, types);
				downloadRecordMapper.updateByIdAndUserId(downloadRecord.getId(), userId, (byte) 1);
				msg = "ok";
			} else {
				msg = "already";
			}
		}
		
		
		return msg;
	}
	
	/**
	 * 判断是否已经下载过
	 * @param literatureId
	 * @param userId
	 * @param types
	 * @return
	 */
	@Override
	public int checkIsDownload(Long literatureId, long userId, byte types) {
		DownloadRecord downloadRecord = downloadRecordMapper.selectByLiteratureIdAndUserId(literatureId, userId, types);
		
		if (null != downloadRecord) {
			if (downloadRecord.getStatus() == 1) {
				return 1;
			} else {
				return 2;
			}
		} else {
			return 0;
		}
	}

	@Override
	public String collect(long id, Long userId, byte types) {
		String msg = "fail";
		
		if (checkIsCollection(id, userId, types) == 0) {
			Collection collection = new Collection();
			collection.setUserId(userId);
			collection.setLiteratureId(id);
			collection.setTypes(types);
			collection.setCreateTime(new Date());
			collectionMapper.insertSelective(collection);
			msg = "ok";
		} 
		
		return msg;
	}
	

	/**
	 * 判断是否收藏
	 * @param literatureId
	 * @param userId
	 * @param types
	 * @return
	 */
	@Override
	public int checkIsCollection(Long literatureId, long userId, byte types) {
		Collection collection = collectionMapper.selectByLiteratureIdAndUserId(literatureId, userId, types);
		
		if (null != collection) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public void modifyProductClickCount(Product product) {
		Product product2 = new Product();
		product2.setId(product.getId());
		product2.setClickCount(product.getClickCount() + 1);
		productMapper.updateClickCount(product2);
	}

	@Override
	public int del(Integer productId) {
		int i=productMapper.delLogic(productId);
		return i;
	}
	
}
