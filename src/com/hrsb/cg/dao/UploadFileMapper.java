package com.hrsb.cg.dao;

import java.util.List;

import com.hrsb.cg.model.UploadFile;
import com.hrsb.cg.util.Page;

public interface UploadFileMapper {
	
	UploadFile selectUploadFile(Long upfileid);
	
	List<UploadFile> selectByName(String ufkey);//title
	
	List<UploadFile> selectByPage(Page<UploadFile> page);
	
	int insertSelective(UploadFile record);
    
    int delLogic(Integer ufId);
}
