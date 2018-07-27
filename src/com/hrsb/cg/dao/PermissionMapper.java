package com.hrsb.cg.dao;

import java.util.List;

import com.hrsb.cg.model.Permission;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
    List<Permission> getAll();
    List<Permission> getMenus();
    List<Permission> getPermissionByParentId(Integer parentid);
}