package com.hrsb.cg.dao;

import com.hrsb.cg.model.ManagerModule;

public interface ManagerModuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ManagerModule record);

    int insertSelective(ManagerModule record);

    ManagerModule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ManagerModule record);

    int updateByPrimaryKey(ManagerModule record);
}