package com.hrsb.cg.dao;

import java.util.List;

import com.hrsb.cg.model.Product;
import com.hrsb.cg.util.Page;

public interface ProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKeyWithBLOBs(Product record);

    int updateByPrimaryKey(Product record);

	List<Product> selectByPage(Page<Product> page);
	
	List<Product> selectByTitleAndIntroduction(String gskeyword);//Added by JIANG at 20170810
	
	List<Product> selectByContent(String pskeyword); //Added by JIANG at 20170811
	
	List<Product> selectByClickCount(); //Added by JIANG at 20170811

	void updateClickCount(Product product2);

	int delLogic(Integer productId);
	
}