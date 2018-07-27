package com.hrsb.cg.dao;

import java.util.List;

import com.hrsb.cg.model.ProductImage;

public interface ProductImageMapper {
	
    int deleteByPrimaryKey(Long id);

    int insertSelective(ProductImage record);

    ProductImage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductImage record);

	List<ProductImage> selectByProductId(long id);

}