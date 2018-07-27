package com.hrsb.cg.service;

import java.util.List;

import com.hrsb.cg.model.Module;
import com.hrsb.cg.util.Page;

public interface ModuleService {
	int deleteByPrimaryKey(Integer id);

    int insert(Module record);

    int insertSelective(Module record);

    Module selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Module record);

    int updateByPrimaryKey(Module record);
    //根据组织节点获取部门
    List<Module> findModuleByGroupId(Integer groupId);
    //根据子级查询所有（需要特殊权限）
    List<Module> findAllModuleByChirld(Integer id);
    //根据父级查询所有
    List<Module> findAllModuleByParent(Integer id);
    //获取所有
    List<Module> getAll();
    //获取所有部门
    List<Module> getModuleByPage(Page<Module> module);
    //批量删除
    int deleteSome(List<String> ids);
}
