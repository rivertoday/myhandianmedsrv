package com.hrsb.cg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrsb.cg.dao.CommentMapper;
import com.hrsb.cg.model.Comment;
import com.hrsb.cg.service.CommentService;
import com.hrsb.cg.util.Page;
@Service(value="commentService")
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	CommentMapper commentMapper;

	@Override
	public List<Comment> selectByPage(Page<Comment> page) {
		
		return commentMapper.selectByPage(page);
	}

	@Override
	public int delById(Long commentId) {
		
		return commentMapper.deleteByPrimaryKey(commentId);
	}

}
