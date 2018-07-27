package com.hrsb.cg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrsb.cg.dao.LoginLogMapper;
import com.hrsb.cg.model.LoginLog;
import com.hrsb.cg.service.LoginLogService;
@Service
public class LoginLogServiceImpl implements LoginLogService {
	@Autowired
	LoginLogMapper loginLogMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return loginLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(LoginLog record) {
		// TODO Auto-generated method stub
		return loginLogMapper.insert(record);
	}

	@Override
	public int insertSelective(LoginLog record) {
		// TODO Auto-generated method stub
		return loginLogMapper.insertSelective(record);
	}

	@Override
	public LoginLog selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return loginLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(LoginLog record) {
		// TODO Auto-generated method stub
		return loginLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(LoginLog record) {
		// TODO Auto-generated method stub
		return loginLogMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<LoginLog> findTen(Integer uid) {
		// TODO Auto-generated method stub
		return loginLogMapper.findTen(uid);
	}

}
