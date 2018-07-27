package com.hrsb.cg.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.hrsb.cg.model.Comment;

public interface BaseService {

	List<Map<String, Object>> getCommentInfo(Long id, byte types);

	void saveComment(Comment comment, HttpSession session) throws Exception;

}
