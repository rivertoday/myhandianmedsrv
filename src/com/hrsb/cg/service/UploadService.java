package com.hrsb.cg.service;

import java.util.List;

import com.hrsb.cg.model.MingCi;
import com.hrsb.cg.model.UploadFile;
import com.hrsb.cg.util.Page;


public interface UploadService {
	
	UploadFile getDetails(Long id);
	
	List<UploadFile> searchUploadFile(String ufkey);//按照标题
	
	List<UploadFile> selectByPage(Page<UploadFile> page);
	
	int saveUploadFile(UploadFile upfile);
	
	int delLogic(Integer upfileId);
}
