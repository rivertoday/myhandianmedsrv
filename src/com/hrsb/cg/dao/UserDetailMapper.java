package com.hrsb.cg.dao;

import java.util.List;

import com.hrsb.cg.model.UserDetail;
import com.hrsb.cg.util.Page;

public interface UserDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserDetail record);

    int insertSelective(UserDetail record);

    UserDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserDetail record);

    int updateByPrimaryKey(UserDetail record);
    
    List<UserDetail> selectUserDetailByPage(Page<UserDetail> page);

	List<UserDetail> selectAll();

	List<UserDetail> selectByChoose(List<String> list);

	UserDetail selectByUserId(Long userId);
	
	UserDetail selectByPhone(String phone);//Added by JIANG

	UserDetail getPhone(String phone);
	
}