package com.hrsb.cg.dao;

import java.util.List;

import com.hrsb.cg.model.MingCi;

import com.hrsb.cg.util.Page;

public interface MingCiMapper {
	
	/*List<String> selectAllPinYin();*/
	
	List<MingCi> selectByCategory(String cate);
	
	MingCi selectMingCi(Long mingciid);
	
	List<MingCi> selectByName(String mckey);
	
	List<MingCi> selectByPage(Page<MingCi> page);
	
	int insertSelective(MingCi record);

    int updateByPrimaryKeySelective(MingCi record);
    
    int delLogic(Integer mingciId);
}
