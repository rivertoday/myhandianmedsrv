package com.hrsb.cg.dao;

import java.util.List;

import com.hrsb.cg.model.Banner;
import com.hrsb.cg.util.Page;

public interface BannerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Banner record);

    int insertSelective(Banner record);

    Banner selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Banner record);

    int updateByPrimaryKey(Banner record);

	List<Banner> findAllByPage(Page<Banner> page);

	List<Banner> selectByTypes(byte types);
	
}