package com.hrsb.cg.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hrsb.cg.dao.CommentMapper;
import com.hrsb.cg.dao.LiteratureMapper;
import com.hrsb.cg.dao.UserDetailMapper;
import com.hrsb.cg.model.Literature;
import com.hrsb.cg.service.LiteratureService;
import com.hrsb.cg.util.Const;
import com.hrsb.cg.util.Page;
import com.hrsb.cg.util.Tools;
import com.hrsb.cg.model.Comment;
import com.hrsb.cg.model.UserDetail;

@Service
public class LiteratureServiceImpl implements LiteratureService{
	
	@Autowired
	LiteratureMapper literatureMapper;
	
	@Autowired
	CommentMapper commentMapper;
	
	@Autowired
	UserDetailMapper userDetailMapper;

	@Override
	public List<Literature> selectByPage(Page<Literature> page) {
		
		return literatureMapper.selectByPage(page);
	}

	@Override
	public Literature getByArticleId(String articleId) {
		return literatureMapper.selectByArticleId(articleId);
	}

	/**
	 * 保存万方返回的文件到本地
	 * @param resultMap
	 * @param literature
	 * @param request 
	 * @throws Exception 
	 */
	@Override
	public void saveWanFangDetail(Map<String, Object> resultMap, Literature literature, HttpServletRequest request) throws Exception {
		Integer hasOriginalDoc = (Integer) resultMap.get("HasOriginalDoc");
		literature.setHasOriginalDoc(hasOriginalDoc.byteValue());
		
		if (hasOriginalDoc == 1) {
			// 请求万方下载全文接口
			String url = Const.WF_DOWNLOAD + "&articleId=" + literature.getArticleId() + "&type=" + literature.getTypes() + "&sign=" + Tools.getSign();
			System.out.println(url);
			String fileUrl = Tools.downloadFile(url, request);
			//String fileUrl = Tools.getBasePath(request) + "/upload/file/20160316115658196256.pdf";
			//literature.setDownloadUrl(fileUrl);
			literature.setSpare1(fileUrl);//updated by JIANG at 20180109
		}		
	}

	@Override
	public Map<String, Object> getCommentList(long id, int pageNo,
			int pageSize, HttpServletRequest request) {
		Map<String, Object> json = new HashMap<String, Object>();
		List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("types", 1);
		Page<Comment> page = new Page<Comment>(pageNo, pageSize).setParams(params);
		page.setOrderField("create_time");
		page.setOrderDirection("desc");
		List<Comment> comments = commentMapper.selectByPage(page);
		
		if (!CollectionUtils.isEmpty(comments)) {
			for (Comment comment : comments) {
				Map<String, Object> map = new HashMap<String, Object>();
				UserDetail userDetail = userDetailMapper.selectByUserId(comment.getUserId());
				String headImg = "";
				
				if (!StringUtils.isEmpty(userDetail.getHeadImg())) {
					headImg = userDetail.getHeadImg();
				} else {
					if (!StringUtils.isEmpty(userDetail.getSex())) {
						if ("男".equals(userDetail.getSex())) {
							headImg = Tools.getBasePath(request) + Const.DEFAULT_M;
						} else if ("女".equals(userDetail.getSex())) {
							headImg = Tools.getBasePath(request) + Const.DEFAULT_W;
						} else {
							headImg = Tools.getBasePath(request) + Const.DEFAULT_N;
						}
					} else {
						headImg = Tools.getBasePath(request) + Const.DEFAULT_N;
					}
				}
				
				map.put("headImg", headImg);
				map.put("userName", userDetail.getUserName());
				map.put("content", comment.getContent());
				map.put("levels", comment.getLevels());
				map.put("createTime", comment.getCreateTime().getTime());
				map.put("createTimeStr", new SimpleDateFormat("yyyy-MM-dd").format(comment.getCreateTime()));
				data.add(map);
			}
		}
		
		json.put("data", data);
		json.put("success", page.getTotalRecord());
		return json;
	}

}
