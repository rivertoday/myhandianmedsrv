package com.hrsb.cg.service;

import java.util.List;

import com.hrsb.cg.model.MingCi;
import com.hrsb.cg.util.Page;

public interface MingCiService {
	/*List<String> getList();*/
	
	List<MingCi> getCategory(String cate);
	
	MingCi getDetails(Long id);
	
	List<MingCi> searchMingCi(String hmkey);
	
	List<MingCi> selectByPage(Page<MingCi> page);
	
	int saveMingCi(MingCi mingci);
	
	int delLogic(Integer mingciId);
}
