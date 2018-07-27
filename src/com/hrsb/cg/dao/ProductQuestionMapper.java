package com.hrsb.cg.dao;

import java.util.List;

import com.hrsb.cg.model.ProductQuestion;
import com.hrsb.cg.util.Page;

public interface ProductQuestionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductQuestion record);

    int insertSelective(ProductQuestion record);

    ProductQuestion selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductQuestion record);

    int updateByPrimaryKeyWithBLOBs(ProductQuestion record);

    int updateByPrimaryKey(ProductQuestion record);

    /**
     * 后台分页
     * @param page
     * @return
     */
	List<ProductQuestion> selectProductQuestionByPage(Page<ProductQuestion> page);

	List<ProductQuestion> selectByProductId(Long productId);
	
}