package com.hrsb.cg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.TriggersRemove;
import com.googlecode.ehcache.annotations.When;
import com.hrsb.cg.dao.PermissionMapper;
import com.hrsb.cg.dao.PermissionRoleMapper;
import com.hrsb.cg.model.Permission;
import com.hrsb.cg.model.PermissionRole;
import com.hrsb.cg.service.PermissionRoleService;
@Service(value = "permissionRoleService")
public class PermissionRoleServiceImpl implements PermissionRoleService {
	@Autowired
	PermissionMapper permissionMapper;
	@Autowired
	PermissionRoleMapper permissionRoleMapper;
	@Override
	public List<PermissionRole> getPermissionRoleByRoleById(Integer roleId) {
		List<PermissionRole> prs = permissionRoleMapper.getPermissionRoleByRoleById(roleId);
		for (int i = 0; i < prs.size(); i++) {
			Permission p = permissionMapper.selectByPrimaryKey(prs.get(i).getPermissionid());
			prs.get(i).setPermission(p);
		}
		return prs;
	}
	@Override
	@TriggersRemove(cacheName="baseCache",when=When.AFTER_METHOD_INVOCATION,removeAll=true)
	public int deleteByRole(Integer roleid) {
		// TODO Auto-generated method stub
		return permissionRoleMapper.deleteByRole(roleid);
	}
	@Override
	@TriggersRemove(cacheName="baseCache",when=When.AFTER_METHOD_INVOCATION,removeAll=true)
	public int insert(PermissionRole record) {
		// TODO Auto-generated method stub
		return permissionRoleMapper.insert(record);
	}

}
