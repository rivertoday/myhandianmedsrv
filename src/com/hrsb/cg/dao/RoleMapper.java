package com.hrsb.cg.dao;

import java.util.List;

import com.hrsb.cg.model.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    /**
     * 查询所有
     * @param role
     * @return
     */
    List<Role> getAll(Role role);
    /**
     * 删除多个
     * @param ids
     * @return
     */
    int deleteRoleByIds(List<String> ids);
}