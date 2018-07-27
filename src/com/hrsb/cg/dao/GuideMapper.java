package com.hrsb.cg.dao;

import java.util.List;

import com.hrsb.cg.model.Guide;
import com.hrsb.cg.util.Page;

public interface GuideMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Guide record);

    Guide selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Guide record);

	List<Guide> selectByPage(Page<Guide> page);
	
}