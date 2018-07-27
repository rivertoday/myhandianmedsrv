package com.hrsb.cg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import com.hrsb.cg.model.LoginLog;

public interface LoginLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LoginLog record);

    int insertSelective(LoginLog record);

    LoginLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoginLog record);

    int updateByPrimaryKey(LoginLog record);
    @Select("select * from loginlog where userid = #{uid} order by createtime desc limit 10")
    @ResultType(LoginLog.class)
    List<LoginLog> findTen(@Param("uid")Integer uid);
}