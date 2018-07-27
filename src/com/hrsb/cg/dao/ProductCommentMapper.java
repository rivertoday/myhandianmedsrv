package com.hrsb.cg.dao;

import java.util.List;

import com.hrsb.cg.model.ProductComment;
import com.hrsb.cg.util.Page;

public interface ProductCommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductComment record);

    int insertSelective(ProductComment record);

    ProductComment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductComment record);

    int updateByPrimaryKeyWithBLOBs(ProductComment record);

    int updateByPrimaryKey(ProductComment record);

	List<ProductComment> selectProductCommentByPage(Page<ProductComment> page);

	List<ProductComment> selectByProductId(Long productId);
	
}