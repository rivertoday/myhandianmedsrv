package com.hrsb.cg.dao;

import java.util.List;

import com.hrsb.cg.model.HospitalGrade;
import com.hrsb.cg.util.Page;

public interface HospitalGradeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HospitalGrade record);

    int insertSelective(HospitalGrade record);

    HospitalGrade selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HospitalGrade record);

    int updateByPrimaryKey(HospitalGrade record);

	List<HospitalGrade> selectByType(Integer types);

	List<HospitalGrade> selectByPage(Page<HospitalGrade> page);
}