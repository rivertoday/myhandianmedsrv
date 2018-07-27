package com.hrsb.cg.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrsb.cg.dao.ManagerRoleMapper;
import com.hrsb.cg.model.ManagerRole;
import com.hrsb.cg.service.ManagerRoleService;
@Service
public class ManagerRoleServiceImpl implements ManagerRoleService {
	@Autowired
	ManagerRoleMapper managerRoleMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return managerRoleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ManagerRole record) {
		// TODO Auto-generated method stub
		return managerRoleMapper.insert(record);
	}

	@Override
	public int insertSelective(ManagerRole record) {
		// TODO Auto-generated method stub
		return managerRoleMapper.insertSelective(record);
	}

	@Override
	public ManagerRole selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return managerRoleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ManagerRole record) {
		// TODO Auto-generated method stub
		return managerRoleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ManagerRole record) {
		// TODO Auto-generated method stub
		return managerRoleMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<ManagerRole> getRoleByManager(Integer manageid) {
		// TODO Auto-generated method stub
		return managerRoleMapper.getRoleByManager(manageid);
	}

	@Override
	public int deleteMore(String ids) {
		// TODO Auto-generated method stub
		return managerRoleMapper.deleteMore(Arrays.asList(ids.split(",")));
	}

	@Override
	public int deleteByManager(Integer mid) {
		// TODO Auto-generated method stub
		return managerRoleMapper.deleteByManager(mid);
	}
}
