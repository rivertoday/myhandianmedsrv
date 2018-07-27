package com.hrsb.cg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrsb.cg.dao.UploadFileMapper;
import com.hrsb.cg.model.MingCi;
import com.hrsb.cg.model.UploadFile;
import com.hrsb.cg.service.UploadService;
import com.hrsb.cg.util.Page;

@Service
public class UploadServiceImpl implements UploadService {

	@Autowired
	UploadFileMapper uploadMapper;

	@Override
	public UploadFile getDetails(Long id) {
		// TODO Auto-generated method stub
		UploadFile herbmed = uploadMapper.selectUploadFile(id);
		return herbmed;
	}

	@Override
	public List<UploadFile> searchUploadFile(String ufkey) {
		// TODO Auto-generated method stub
		List<UploadFile> listuf = uploadMapper
				.selectByName(ufkey);
		return listuf;
	}
	
	@Override
	public List<UploadFile> selectByPage(Page<UploadFile> page) {
		// TODO Auto-generated method stub
		List<UploadFile> listupfile = uploadMapper.selectByPage(page);
		return listupfile;
	}

	@Override
	public int saveUploadFile(UploadFile upfile) {
		int row = 0;
		row = uploadMapper.insertSelective(upfile);

		return row;
	}

	@Override
	public int delLogic(Integer upfileId) {
		int i = uploadMapper.delLogic(upfileId);
		return i;
	}

}
