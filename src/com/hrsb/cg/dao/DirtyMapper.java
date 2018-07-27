package com.hrsb.cg.dao;

import com.hrsb.cg.model.Dirty;

public interface DirtyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dirty record);

    int insertSelective(Dirty record);

    Dirty selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dirty record);

    int updateByPrimaryKeyWithBLOBs(Dirty record);

    int updateByPrimaryKey(Dirty record);
}