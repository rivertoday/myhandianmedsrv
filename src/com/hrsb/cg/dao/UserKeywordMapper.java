package com.hrsb.cg.dao;

import java.util.List;

import com.hrsb.cg.model.UserKeyword;
import com.hrsb.cg.util.Page;

public interface UserKeywordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserKeyword record);

    int insertSelective(UserKeyword record);

    UserKeyword selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserKeyword record);

    int updateByPrimaryKey(UserKeyword record);

	List<UserKeyword> selectByPage(Page<UserKeyword> page);

	UserKeyword selectByName(String title);
	
}