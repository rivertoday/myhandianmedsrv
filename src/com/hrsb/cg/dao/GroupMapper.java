package com.hrsb.cg.dao;

import java.util.List;

import com.hrsb.cg.model.Group;

public interface GroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Group record);

    int insertSelective(Group record);

    Group selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKey(Group record);
    //获取全部组织信息
    List<Group> getAll(Group record);
}