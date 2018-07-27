package com.hrsb.cg.service;

import java.util.List;

import com.hrsb.cg.model.HerbalMedicine;
import com.hrsb.cg.util.Page;

public interface HerbalMedicineService {
	/*List<String> getList();*/
	
	List<HerbalMedicine> getCategory(String cate);
	
	HerbalMedicine getDetails(Long id);
	
	List<HerbalMedicine> searchHerbal(String hmkey);
	
	List<HerbalMedicine> selectByPage(Page<HerbalMedicine> page);
	
	int saveHerbal(HerbalMedicine herbal);
	
	int delLogic(Integer herbalId);
}
