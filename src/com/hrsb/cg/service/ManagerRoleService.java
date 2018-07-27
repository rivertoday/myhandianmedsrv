package com.hrsb.cg.service;

import java.util.List;

import com.hrsb.cg.model.ManagerRole;

public interface ManagerRoleService {
	 int deleteByPrimaryKey(Integer id);

	    int insert(ManagerRole record);

	    int insertSelective(ManagerRole record);

	    ManagerRole selectByPrimaryKey(Integer id);

	    int updateByPrimaryKeySelective(ManagerRole record);

	    int updateByPrimaryKey(ManagerRole record);
	    List<ManagerRole> getRoleByManager(Integer manageId);
	    int deleteMore(String ids);
	    int deleteByManager(Integer mid);
}
