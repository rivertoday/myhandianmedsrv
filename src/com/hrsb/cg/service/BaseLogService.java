package com.hrsb.cg.service;

import java.util.List;

import javax.servlet.ServletOutputStream;

import com.hrsb.cg.model.BaseLog;
import com.hrsb.cg.model.SystemLog;
import com.hrsb.cg.util.Page;

public interface BaseLogService {
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
    //获取全部
    public List<BaseLog> getAll(Integer type);
    //根据id获取全部
    public List<BaseLog> getAll(List expids);
    //导出
    public void exportExcel(List<BaseLog> systemlog,String [] titles,ServletOutputStream outputStream);
}
