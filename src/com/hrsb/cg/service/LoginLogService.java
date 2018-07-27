package com.hrsb.cg.service;

import java.util.List;

import com.hrsb.cg.model.LoginLog;

public interface LoginLogService {
	int deleteByPrimaryKey(Integer id);

    int insert(LoginLog record);

    int insertSelective(LoginLog record);

    LoginLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoginLog record);

    int updateByPrimaryKey(LoginLog record);
    
    List<LoginLog> findTen(Integer uid);
}
