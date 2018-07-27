package com.hrsb.cg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrsb.cg.dao.HerbalMedicineMapper;
import com.hrsb.cg.model.HerbalMedicine;
import com.hrsb.cg.service.HerbalMedicineService;
import com.hrsb.cg.util.Page;

@Service
public class HerbalMedicineServiceImpl implements HerbalMedicineService {

	@Autowired
	HerbalMedicineMapper herbalmedicineMapper;

	/*
	 * @Override public List<String> getList() { // TODO Auto-generated method
	 * stub List<String> herballist = ? return null; }
	 */

	@Override
	public List<HerbalMedicine> getCategory(String cate) {
		// TODO Auto-generated method stub
		List<HerbalMedicine> listherb = herbalmedicineMapper
				.selectByCategory(cate);
		return listherb;
	}

	@Override
	public HerbalMedicine getDetails(Long id) {
		// TODO Auto-generated method stub
		HerbalMedicine herbmed = herbalmedicineMapper.selectHerbalMedicine(id);
		return herbmed;
	}

	@Override
	public List<HerbalMedicine> searchHerbal(String hmkey) {
		// TODO Auto-generated method stub
		List<HerbalMedicine> listherb = herbalmedicineMapper
				.selectByName(hmkey);
		return listherb;
	}

	@Override
	public List<HerbalMedicine> selectByPage(Page<HerbalMedicine> page) {
		// TODO Auto-generated method stub
		List<HerbalMedicine> listherb = herbalmedicineMapper.selectByPage(page);
		return listherb;
	}

	@Override
	public int saveHerbal(HerbalMedicine herbal) {
		int row = 0;
		if (herbal.getId() != null) {
			row = herbalmedicineMapper.updateByPrimaryKeySelective(herbal);
		} else {
			row = herbalmedicineMapper.insertSelective(herbal);
		}
		return row;
	}

	@Override
	public int delLogic(Integer herbalId) {
		int i = herbalmedicineMapper.delLogic(herbalId);
		return i;
	}

}
