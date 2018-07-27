package com.hrsb.cg.dao;

import java.util.List;

import com.hrsb.cg.model.ManagerRole;

public interface ManagerRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ManagerRole record);

    int insertSelective(ManagerRole record);

    ManagerRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ManagerRole record);

    int updateByPrimaryKey(ManagerRole record);
    List<ManagerRole> getRoleByManager(Integer manageId);
    
    int deleteMore(List<String> ids);
    
    int deleteByManager(Integer mid);
}