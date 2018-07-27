package com.hrsb.cg.dao;

import com.hrsb.cg.model.UserLogin;

public interface UserLoginMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserLogin record);

    int insertSelective(UserLogin record);

    UserLogin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserLogin record);
    
    int updateByPhoneSelective(UserLogin record);//added by JIANG

    int updateByPrimaryKey(UserLogin record);

	UserLogin isExistLoginName(String loginName);

	UserLogin selectByPhoneAndPassword(String phone, String password);

	UserLogin selectByPhone(String phone);

	void updateByPhone(String password, String phone);
	
}