package com.hrsb.cg.service;

import java.util.List;

import javax.servlet.ServletOutputStream;

import com.hrsb.cg.model.SystemLog;
import com.hrsb.cg.util.Page;

public interface SystemLogService {
	int deleteByPrimaryKey(Integer id);

    int insert(SystemLog record);

    int insertSelective(SystemLog record);

    SystemLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemLog record);

    int updateByPrimaryKey(SystemLog record);
  //分页查询记录
    List<SystemLog> getAllByPage(Page<SystemLog> page);
    //导出
    public void exportExcel(List<SystemLog> systemlog,String [] titles,ServletOutputStream outputStream);
    //获取全部
    public List<SystemLog> getAll();
    //根据id获取全部
    public List<SystemLog> getAll(List<String> expids);
}
