package com.hrsb.cg.dao;

import java.util.List;

import com.hrsb.cg.model.HerbalMedicine;

import com.hrsb.cg.util.Page;

public interface HerbalMedicineMapper {
	
	/*List<String> selectAllPinYin();*/
	
	List<HerbalMedicine> selectByCategory(String cate);
	
	HerbalMedicine selectHerbalMedicine(Long herbmedid);
	
	List<HerbalMedicine> selectByName(String hmkey);
	
	List<HerbalMedicine> selectByPage(Page<HerbalMedicine> page);
	
	int insertSelective(HerbalMedicine record);

    int updateByPrimaryKeySelective(HerbalMedicine record);
    
    int delLogic(Integer herbalId);
}
