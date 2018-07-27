package com.hrsb.cg.dao;

import java.util.List;

import com.hrsb.cg.model.Version;
import com.hrsb.cg.util.Page;

public interface VersionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Version record);

    int insertSelective(Version record);

    Version selectByPrimaryKey(Integer id);
    
    Version selectLatest();//added by JIANG

    int updateByPrimaryKeySelective(Version record);

    int updateByPrimaryKey(Version record);

	List<Version> findAllByPage(Page<Version> page);
}