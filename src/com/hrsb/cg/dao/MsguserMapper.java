package com.hrsb.cg.dao;

import com.hrsb.cg.model.Msguser;
public interface MsguserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Msguser record);

    int insertSelective(Msguser record);

    Msguser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Msguser record);

    int updateByPrimaryKey(Msguser record);
}