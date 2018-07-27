package com.hrsb.cg.service;

import java.util.List;

import com.hrsb.cg.model.Role;
/**
 * 角色
 * @author risenb-java001
 *
 */
public interface RoleService {
	int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    List<Role> getAll(Role role);
    /**
     * 批量删除
     * @param ids
     * @return
     */
    int deleteRoleByIds(List<String> ids);
}
