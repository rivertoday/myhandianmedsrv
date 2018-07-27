package com.hrsb.cg.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Service;

import com.hrsb.cg.model.AutoId;

@Service
public interface AutoIdMapper {

	@Insert("INSERT INTO auto_id() VALUES (null)")
	@SelectKey(before = false, keyProperty = "id", resultType = Integer.class, statementType = StatementType.STATEMENT, statement = "SELECT LAST_INSERT_ID() AS id")
	void addAutoUserId(AutoId autoId);
}
