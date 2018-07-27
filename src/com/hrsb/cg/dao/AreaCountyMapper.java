package com.hrsb.cg.dao;

import java.util.List;

import com.hrsb.cg.model.AreaCounty;

public interface AreaCountyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AreaCounty record);

    int insertSelective(AreaCounty record);

    AreaCounty selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AreaCounty record);

    int updateByPrimaryKey(AreaCounty record);

    /**
     * 根据市获取县
     * @param cityCode
     * @return
     */
	List<AreaCounty> selectAllCounty(String cityCode);

	/**
	 * 获取某个县
	 * @param countyCode
	 * @return
	 */
	AreaCounty getCountyByCountyCode(String countyCode);
	
	AreaCounty getCountyByCountyName(String myCountyName, String myProvCode, String myCityCode);

}