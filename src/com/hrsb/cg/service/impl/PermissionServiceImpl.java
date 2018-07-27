package com.hrsb.cg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrsb.cg.dao.PermissionMapper;
import com.hrsb.cg.model.Permission;
import com.hrsb.cg.service.PermissionService;
@Service
public class PermissionServiceImpl implements PermissionService{
	@Autowired
	PermissionMapper permissionMapper;
	@Override
	public List<Permission> getAll() {
		// TODO Auto-generated method stub
		return permissionMapper.getAll();
	}
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return permissionMapper.deleteByPrimaryKey(id);
	}
	@Override
	public int insert(Permission record) {
		// TODO Auto-generated method stub
		return permissionMapper.insert(record);
	}
	@Override
	public int insertSelective(Permission record) {
		// TODO Auto-generated method stub
		return permissionMapper.insertSelective(record);
	}
	@Override
	public Permission selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return permissionMapper.selectByPrimaryKey(id);
	}
	@Override
	public int updateByPrimaryKeySelective(Permission record) {
		// TODO Auto-generated method stub
		return permissionMapper.updateByPrimaryKeySelective(record);
	}
	@Override
	public int updateByPrimaryKey(Permission record) {
		// TODO Auto-generated method stub
		return permissionMapper.updateByPrimaryKey(record);
	}
	@Override
	public List<Permission> getMenus() {
		// TODO Auto-generated method stub
		List<Permission> ps = permissionMapper.getMenus();
		for (int i = 0; i < ps.size(); i++) {
			ps.get(i).setChirlds(permissionMapper.getPermissionByParentId(ps.get(i).getId()));
		}
		return ps;
	}
	@Override
	public List<Permission> getPermissionByParentId(Integer parentid) {
		// TODO Auto-generated method stub
		return permissionMapper.getPermissionByParentId(parentid);
	} 

}
