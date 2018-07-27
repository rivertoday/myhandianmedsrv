package com.hrsb.cg.dao;

import java.util.List;

import com.hrsb.cg.model.Department;
import com.hrsb.cg.util.Page;

public interface DepartmentMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

	List<Department> TopDepartment();

	List<Department> selectByPage(Page<Department> page);

	List<Department> selectByParentId(int parentId);
	
}