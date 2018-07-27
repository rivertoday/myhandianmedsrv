package com.hrsb.cg.dao;

import java.util.List;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import com.hrsb.cg.model.SystemLog;
import com.hrsb.cg.util.Page;

public interface SystemLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemLog record);

    int insertSelective(SystemLog record);

    SystemLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemLog record);

    int updateByPrimaryKey(SystemLog record);
    //分页查询记录
    List<SystemLog> getAllByPage(Page<SystemLog> page);
    @Select("select * from systemlog order by operatime desc")
    @ResultType(SystemLog.class)
    List<SystemLog> getAlls();
    List<SystemLog> getAll(List<String> expids);
}