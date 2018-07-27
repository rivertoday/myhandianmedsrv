package com.hrsb.cg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrsb.cg.dao.ManagerMapper;
import com.hrsb.cg.dao.ModuleMapper;
import com.hrsb.cg.dao.RoleMapper;
import com.hrsb.cg.model.PermissionRole;
import com.hrsb.cg.model.Role;
import com.hrsb.cg.service.PermissionRoleService;
import com.hrsb.cg.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleMapper roleMapper;
	@Autowired
	ModuleMapper moduleMapper;
	@Autowired
	PermissionRoleService permissionRoleService;
	@Autowired
	ManagerMapper managerMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return roleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Role record) {
		// TODO Auto-generated method stub
		return roleMapper.insert(record);
	}

	@Override
	public int insertSelective(Role record) {
		// TODO Auto-generated method stub
		return roleMapper.insertSelective(record);
	}

	@Override
	public Role selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		Role role = roleMapper.selectByPrimaryKey(id);
		if(role!=null){
		role.setPr(permissionRoleService.getPermissionRoleByRoleById(role.getId()));
		}
		return role;
	}

	@Override
	public int updateByPrimaryKeySelective(Role record) {
		// TODO Auto-generated method stub
		return roleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Role record) {
		// TODO Auto-generated method stub
		return roleMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Role> getAll(Role role) {
		// TODO Auto-generated method stub
		List<Role> roles = roleMapper.getAll(role);
		for (int i = 0; i < roles.size(); i++) {
			roles.get(i).setOperatorM(managerMapper.selectByPrimaryKey(roles.get(i).getOperator()));
			List<PermissionRole> ps = permissionRoleService.getPermissionRoleByRoleById(roles.get(i).getId());
			roles.get(i).setPr(ps);
		}
		return roles;
	}

	@Override
	public int deleteRoleByIds(List<String> ids) {
		// TODO Auto-generated method stub
		return roleMapper.deleteRoleByIds(ids);
	}

}
