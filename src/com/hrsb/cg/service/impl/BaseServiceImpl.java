package com.hrsb.cg.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hrsb.cg.dao.AreaCityMapper;
import com.hrsb.cg.dao.AreaCountyMapper;
import com.hrsb.cg.dao.AreaProvMapper;
import com.hrsb.cg.dao.CodeRecordMapper;
import com.hrsb.cg.dao.CollectionMapper;
import com.hrsb.cg.dao.CommentMapper;
import com.hrsb.cg.dao.DepartmentMapper;
import com.hrsb.cg.dao.DirtyMapper;
import com.hrsb.cg.dao.DownloadRecordMapper;
import com.hrsb.cg.dao.HospitalGradeMapper;
import com.hrsb.cg.dao.UserDetailMapper;
import com.hrsb.cg.model.AreaCity;
import com.hrsb.cg.model.AreaCounty;
import com.hrsb.cg.model.AreaProv;
import com.hrsb.cg.model.CodeRecord;
import com.hrsb.cg.model.Collection;
import com.hrsb.cg.model.Comment;
import com.hrsb.cg.model.Department;
import com.hrsb.cg.model.Dirty;
import com.hrsb.cg.model.DownloadRecord;
import com.hrsb.cg.model.HospitalGrade;
import com.hrsb.cg.model.UserDetail;
import com.hrsb.cg.model.UserLogin;
import com.hrsb.cg.service.BaseService;
import com.hrsb.cg.util.ClientSDK;
import com.hrsb.cg.util.Const;
import com.hrsb.cg.util.WebClient;

@Service(value = "baseService")
public class BaseServiceImpl implements BaseService {
	
	@Autowired
	CodeRecordMapper codeRecordMapper;
	
	@Autowired
	UserDetailMapper userDetailMapper;
	
	@Autowired
	HospitalGradeMapper hospitalGradeMapper;
	
	@Autowired
	DepartmentMapper departmentMapper;

	@Autowired
	AreaProvMapper provinceMapper;

	@Autowired
	AreaCityMapper cityMapper;
	
	@Autowired
	AreaCountyMapper countryMapper;
	
	@Autowired
	DownloadRecordMapper downloadRecordMapper;
	
	@Autowired
	CollectionMapper collectionMapper;
	
	@Autowired
	CommentMapper commentMapper;
	
	@Autowired
	DirtyMapper dirtyMapper;
	
	ClientSDK sdk = new ClientSDK();
	
	WebClient webClient = new WebClient();
	
	/**
	 * 验证验证码是否有效
	 * @param phone
	 * @param code
	 * @return
	 */
	public boolean checkCode(String phone, String code) {
		CodeRecord codeRecord = codeRecordMapper.selectByPhone(phone);
		
		if (null != codeRecord && !StringUtils.isEmpty(code) &&
				code.equals(codeRecord.getCode()) && codeRecord.getEffectiveTime().after(new Date())) {
			return true;
		}
		
		return false;
	}
	

	/**
	 * 县的名称
	 * @param country
	 * @return
	 */
	public String getCountryName(String country) {
		if (!StringUtils.isEmpty(country)) {
			AreaCounty country2 = countryMapper.getCountyByCountyCode(country);
			
			if (null != country2) {
				return country2.getCountyName();
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	/**
	 * 市名称
	 * @param city
	 * @return
	 */
	public String getCityName(String city) {
		if (!StringUtils.isEmpty(city)) {
			AreaCity city2 = cityMapper.getCityByCityCode(city);
			
			if (null != city2) {
				return city2.getCityName();
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	/**
	 * 省名称
	 * @param province
	 * @return
	 */
	public String getProvinceName(String province) {
		if (!StringUtils.isEmpty(province)) {
			AreaProv province2 = provinceMapper.getByProvCode(province);
			
			if (null != province2) {
				return province2.getProvName();
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	/**
	 * 科室名称
	 * @param department
	 * @return
	 */
	public String getDepartmentName(Integer department) {
		if (null != department) {
			Department department2 = departmentMapper.selectByPrimaryKey(department); 
			
			if (null != department2) {
				return department2.getName();
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	/**
	 * 医院等级/职称 名称
	 * @param hospitalGrade
	 * @return
	 */
	public String getHospitalGradeName(Integer hospitalGrade) {
		if (null != hospitalGrade) {
			HospitalGrade grade = hospitalGradeMapper.selectByPrimaryKey(hospitalGrade); 
			
			if (null != grade) {
				return grade.getName();
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	/**
	 * 评论信息
	 * @param literatureId
	 * @param types
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getCommentInfo(Long literatureId, byte types) {
		List<Comment> comments = commentMapper.selectByLiteratureId(literatureId, types);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		if (!CollectionUtils.isEmpty(comments)) {
			for (Comment comment : comments) {
				Map<String, Object> map = new HashMap<String, Object>();
				UserDetail userDetail = userDetailMapper.selectByUserId(comment.getUserId());
				map.put("headImg", userDetail.getHeadImg());
				map.put("userName", userDetail.getUserName());
				map.put("content", comment.getContent());
				map.put("createTime", comment.getCreateTime().getTime());
				list.add(map);
			}
		}
		
		return list;
	}

	/**
	 * 判断是否收藏
	 * @param literatureId
	 * @param userId
	 * @param types
	 * @return
	 */
	public int checkIsCollection(Long literatureId, long userId, byte types) {
		Collection collection = collectionMapper.selectByLiteratureIdAndUserId(literatureId, userId, types);
		
		if (null != collection) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * 判断是否已经下载过
	 * @param literatureId
	 * @param userId
	 * @param types
	 * @return
	 */
	public int checkIsDownload(Long literatureId, long userId, byte types) {
		DownloadRecord downloadRecord = downloadRecordMapper.selectByLiteratureIdAndUserId(literatureId, userId, types);
		
		if (null != downloadRecord) {
			if (downloadRecord.getStatus() == 1) {
				return 1;
			} else {
				return 2;
			}
		} else {
			return 0;
		}
	}

	public void saveComment(Comment comment, HttpSession session) throws Exception {
		String content = comment.getContent();
		// 判断是否有脏字
		Dirty dirty = dirtyMapper.selectByPrimaryKey(1);
		
		if (!StringUtils.isEmpty(dirty.getContent())) {
			String[] arr = StringUtils.split(dirty.getContent(), ",");
			
			if (!ArrayUtils.isEmpty(arr)) {
				for (String str : arr) {
					content = StringUtils.replace(content, str, "**");
				}
			}
		}
		
		UserLogin userLogin = (UserLogin) session.getAttribute(Const.SESSION_PHONE_USER);
		comment.setUserId(userLogin.getId());
		comment.setCreateTime(new Date());
		commentMapper.insertSelective(comment);
	}
	
	/**
	 * 替换表格无border等
	 * @param content
	 */
	public String replaceContent(String content) {
		//content = StringUtils.replace(content, "<img src=\"", "<img src=\"" + "handian.com01.org:8855");
		content = StringUtils.replace(content, "<table", "<table border=\"1\" cellspacing=\"0\" cellpadding=\"0\"");
		return content;
	}

}
