package com.hrsb.cg.dao;

import java.util.List;

import org.apache.ibatis.annotations.ResultType;

import com.hrsb.cg.model.BaseLog;
import com.hrsb.cg.model.SystemLog;
import com.hrsb.cg.util.Page;

public interface BaseLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseLog record);

    int insertSelective(BaseLog record);

    BaseLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseLog record);

    int updateByPrimaryKey(BaseLog record);
    /**
     * 根据操作人查询
     * @return
     */
    List<BaseLog> selectLogsByPage(Page<BaseLog> page);
    //@Select("select * from baselogs where type = #{type} order by operatime desc")
    @ResultType(SystemLog.class)
    List<BaseLog> getAlls(Integer type);
    List<BaseLog> getAll(List expids);
}