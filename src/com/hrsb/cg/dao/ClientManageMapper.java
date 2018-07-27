package com.hrsb.cg.dao;

import java.util.List;

import com.hrsb.cg.model.ClientManage;

public interface ClientManageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClientManage record);

    int insertSelective(ClientManage record);

    ClientManage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClientManage record);

    int updateByPrimaryKey(ClientManage record);

	ClientManage selectByType(String type);

	List<ClientManage> selectAll();
}