package com.hrsb.cg.dao;

import java.util.List;

import com.hrsb.cg.model.Product;
import com.hrsb.cg.model.ProductLiterature;
import com.hrsb.cg.util.Page;

public interface ProductLiteratureMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductLiterature record);

    int insertSelective(ProductLiterature record);

    ProductLiterature selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductLiterature record);

    int updateByPrimaryKeyWithBLOBs(ProductLiterature record);

    int updateByPrimaryKey(ProductLiterature record);

	List<ProductLiterature> selectProductLiteratureByPage(
			Page<ProductLiterature> page);
	
	List<ProductLiterature> selectAPIProductLiteratureByPage(
			Page<ProductLiterature> page);//Added by JIANG He at 20190327

	List<ProductLiterature> selectByProductId(Long productId);
	
	List<ProductLiterature> selectAll();//Added by JIANG at 20170815
	
	List<ProductLiterature> selectByTitleAndSummary(String gskeyword);//Added by JIANG at 20170810
	
	void updateReadCount(Long id);//Added by JIANG at 20170814
	
}