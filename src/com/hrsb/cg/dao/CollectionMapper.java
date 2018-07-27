package com.hrsb.cg.dao;

import java.util.List;

import com.hrsb.cg.model.Collection;
import com.hrsb.cg.util.Page;

public interface CollectionMapper {
	
    int insertSelective(Collection record);

    Collection selectByPrimaryKey(Long id);

	List<Collection> selectByPage(Page<Collection> page);

	int deleteByIdAndUserId(long id, long userId);

	Collection selectByLiteratureIdAndUserId(Long literatureId, long userId,
			byte types);
	
}