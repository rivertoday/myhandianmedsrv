package com.hrsb.cg.service;

import java.util.List;

import com.hrsb.cg.model.PermissionRole;

public interface PermissionRoleService {
	List<PermissionRole> getPermissionRoleByRoleById(Integer roleId);
	int deleteByRole(Integer roleid);
	int insert(PermissionRole record);
}
