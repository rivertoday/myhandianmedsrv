package com.hrsb.cg.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hrsb.cg.model.Literature;
import com.hrsb.cg.util.Page;

public interface LiteratureService {

	List<Literature> selectByPage(Page<Literature> page);

	Literature getByArticleId(String articleId);

	void saveWanFangDetail(Map<String, Object> resultMap,
			Literature literature, HttpServletRequest request) throws Exception;

	Map<String, Object> getCommentList(long id, int pageNo, int pageSize,
			HttpServletRequest request);

}
