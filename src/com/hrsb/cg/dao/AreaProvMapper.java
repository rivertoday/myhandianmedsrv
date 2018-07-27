package com.hrsb.cg.dao;

import java.util.List;

import com.hrsb.cg.model.AreaProv;

public interface AreaProvMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AreaProv record);

    int insertSelective(AreaProv record);

    AreaProv selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AreaProv record);

    int updateByPrimaryKey(AreaProv record);

    /**
     * 省
     * @return
     */
	List<AreaProv> findAllProv();

	/**
	 * 按省code查找
	 * @param province
	 * @return
	 */
	AreaProv getByProvCode(String provCode);
	
	AreaProv getByProvName(String provName);
}