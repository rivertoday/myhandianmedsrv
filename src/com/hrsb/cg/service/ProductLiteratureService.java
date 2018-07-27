package com.hrsb.cg.service;

import java.util.List;

import com.hrsb.cg.model.ProductLiterature;

public interface ProductLiteratureService {

	ProductLiterature getById(long id);
	
	List<ProductLiterature> getAll();
	
	void modifyProductLiteratureReadCount(long id) throws Exception;//added by JIANG at 20170814

}
