package com.hrsb.cg.dao;

import java.util.List;

import com.hrsb.cg.model.Module;
import com.hrsb.cg.util.Page;

public interface ModuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Module record);

    int insertSelective(Module record);

    Module selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Module record);

    int updateByPrimaryKey(Module record);
    //根据组织节点获取部门(作废)
    List<Module> findModuleByGroupId(Integer groupId);
    //根据子级查询所有（需要特殊权限）
    List<Module> findAllModuleByChirld(Integer id);
    //根据父级查询所有
    List<Module> findAllModuleByParent(Integer id);
    //获取所有
    List<Module> getAll();
    List<Module> getModuleByPage(Page<Module> page);
    //批量删除
    int deleteSome(List<String> ids);
}