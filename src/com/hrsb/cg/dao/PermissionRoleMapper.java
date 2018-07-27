package com.hrsb.cg.dao;

import java.util.List;

import com.hrsb.cg.model.PermissionRole;

public interface PermissionRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PermissionRole record);

    int insertSelective(PermissionRole record);

    PermissionRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PermissionRole record);

    int updateByPrimaryKey(PermissionRole record);
    
    public List<PermissionRole> getPermissionRoleByRoleById(Integer roleid);
    public int deleteByRole(Integer roleid);
}