package com.hrsb.cg.service;

import java.util.List;

import com.hrsb.cg.model.Comment;
import com.hrsb.cg.util.Page;

public interface CommentService {

	List<Comment> selectByPage(Page<Comment> page);

	int delById(Long commentId);

}
