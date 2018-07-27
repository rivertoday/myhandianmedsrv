package com.hrsb.cg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrsb.cg.dao.MingCiMapper;
import com.hrsb.cg.model.MingCi;
import com.hrsb.cg.service.MingCiService;
import com.hrsb.cg.util.Page;

@Service
public class MingCiServiceImpl implements MingCiService {

	@Autowired
	MingCiMapper mingciMapper;

	/*
	 * @Override public List<String> getList() { // TODO Auto-generated method
	 * stub List<String> herballist = ? return null; }
	 */

	@Override
	public List<MingCi> getCategory(String cate) {
		// TODO Auto-generated method stub
		List<MingCi> listherb = mingciMapper
				.selectByCategory(cate);
		return listherb;
	}

	@Override
	public MingCi getDetails(Long id) {
		// TODO Auto-generated method stub
		MingCi herbmed = mingciMapper.selectMingCi(id);
		return herbmed;
	}

	@Override
	public List<MingCi> searchMingCi(String hmkey) {
		// TODO Auto-generated method stub
		List<MingCi> listherb = mingciMapper
				.selectByName(hmkey);
		return listherb;
	}

	@Override
	public List<MingCi> selectByPage(Page<MingCi> page) {
		// TODO Auto-generated method stub
		List<MingCi> listherb = mingciMapper.selectByPage(page);
		return listherb;
	}

	@Override
	public int saveMingCi(MingCi herbal) {
		int row = 0;
		if (herbal.getId() != null) {
			row = mingciMapper.updateByPrimaryKeySelective(herbal);
		} else {
			row = mingciMapper.insertSelective(herbal);
		}
		return row;
	}

	@Override
	public int delLogic(Integer mingciId) {
		int i = mingciMapper.delLogic(mingciId);
		return i;
	}

}
