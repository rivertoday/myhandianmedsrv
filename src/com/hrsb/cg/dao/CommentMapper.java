package com.hrsb.cg.dao;

import java.util.List;

import com.hrsb.cg.model.Comment;
import com.hrsb.cg.util.Page;

public interface CommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);

	List<Comment> selectByPage(Page<Comment> page);

	List<Comment> getByPage(Page<Comment> page);

	void deleteByIdAndUserId(long id, Long id2);

	List<Comment> selectByLiteratureId(Long literatureId, byte types);
	
}