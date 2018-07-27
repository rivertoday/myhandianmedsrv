package com.hrsb.cg.service;

import java.util.List;

import com.hrsb.cg.model.Group;

public interface GroupService {
	int deleteByPrimaryKey(Integer id);

    int insert(Group record);

    int insertSelective(Group record);

    Group selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKey(Group record);
    
    List<Group> getAll(Group record);
}
