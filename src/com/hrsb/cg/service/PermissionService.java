package com.hrsb.cg.service;

import java.util.List;

import com.hrsb.cg.model.Permission;
/**
 * 权限
 * @author risenb-java001
 *
 */
public interface PermissionService {
	List<Permission> getAll();
	int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
    List<Permission> getMenus();
    List<Permission> getPermissionByParentId(Integer parentid);
}
