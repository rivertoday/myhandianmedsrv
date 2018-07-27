package com.hrsb.cg.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrsb.cg.dao.ProductLiteratureMapper;
import com.hrsb.cg.model.ProductLiterature;
import com.hrsb.cg.service.ProductLiteratureService;

@Service
public class ProductLiteratureServiceImpl implements ProductLiteratureService {

	@Autowired
	ProductLiteratureMapper productLiteratureMapper;
	
	@Override
	public ProductLiterature getById(long id) {
		ProductLiterature productl = productLiteratureMapper.selectByPrimaryKey(id);
		return productl;
	}
	
	@Override
	public List<ProductLiterature> getAll() {
		return productLiteratureMapper.selectAll();
	}
	
	@Override
	public void modifyProductLiteratureReadCount(long id) throws Exception {
		productLiteratureMapper.updateReadCount(id);
		
	}
	
}
