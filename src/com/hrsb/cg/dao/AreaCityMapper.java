package com.hrsb.cg.dao;

import java.util.List;

import com.hrsb.cg.model.AreaCity;

public interface AreaCityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AreaCity record);

    int insertSelective(AreaCity record);

    AreaCity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AreaCity record);

    int updateByPrimaryKey(AreaCity record);

    /**
     * 根据省查询市
     * @param provCode
     * @return
     */
	List<AreaCity> selectAllCity(String provCode);

	
	AreaCity getCityByCityCode(String cityCode);
	
	AreaCity getCityByCityName(String myCityName, String myProvCode);
}