package com.hrsb.cg.dao;

import com.hrsb.cg.model.CodeRecord;

public interface CodeRecordMapper {
	
    int deleteByPrimaryKey(Long id);

    int insertSelective(CodeRecord record);

    CodeRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CodeRecord record);

	CodeRecord selectByPhone(String phone);
	
}