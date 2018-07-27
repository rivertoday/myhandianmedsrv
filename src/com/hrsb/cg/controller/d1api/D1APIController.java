package com.hrsb.cg.controller.d1api;

import org.springframework.beans.factory.annotation.Autowired;

import com.hrsb.cg.dao.AboutMapper;
import com.hrsb.cg.dao.AreaCityMapper;
import com.hrsb.cg.dao.AreaCountyMapper;
import com.hrsb.cg.dao.AreaProvMapper;
import com.hrsb.cg.dao.BannerMapper;
import com.hrsb.cg.dao.CodeRecordMapper;
import com.hrsb.cg.dao.CollectionMapper;
import com.hrsb.cg.dao.CommentMapper;
import com.hrsb.cg.dao.DepartmentMapper;
import com.hrsb.cg.dao.DirtyMapper;
import com.hrsb.cg.dao.DownloadRecordMapper;
import com.hrsb.cg.dao.HerbalMedicineMapper;
import com.hrsb.cg.dao.HospitalGradeMapper;
import com.hrsb.cg.dao.LiteratureMapper;
import com.hrsb.cg.dao.ProductLiteratureMapper;
import com.hrsb.cg.dao.SubjectQuestionMapper;
import com.hrsb.cg.dao.SubjectQuestionOptionMapper;
import com.hrsb.cg.dao.SubjectResultMapper;
import com.hrsb.cg.dao.UserAuthMapper;
import com.hrsb.cg.dao.UserDetailMapper;
import com.hrsb.cg.dao.UserKeywordMapper;
import com.hrsb.cg.dao.UserLoginMapper;
import com.hrsb.cg.dao.UserMessageMapper;
import com.hrsb.cg.dao.VersionMapper;
import com.hrsb.cg.service.BaseService;
import com.hrsb.cg.service.HerbalMedicineService;
import com.hrsb.cg.service.MingCiService;
import com.hrsb.cg.service.LiteratureService;
import com.hrsb.cg.service.ProductLiteratureService;
import com.hrsb.cg.service.ProductService;
import com.hrsb.cg.service.SubjectService;
import com.hrsb.cg.service.UserService;
import com.hrsb.cg.util.ClientSDK;

public class D1APIController {
	
	@Autowired
	BaseService baseService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductLiteratureService productLiteratureService;//Added by JIANG at 20170814
	
	@Autowired
	HerbalMedicineService herbalMedicineService; //Added by JIANG at 20171012
	
	@Autowired
	MingCiService mingciService; //Added by JIANG at 20171012
	
	@Autowired
	SubjectService subjectService;
	
	@Autowired
	LiteratureService literatureService;
	
	@Autowired
	LiteratureMapper literatureMapper;
	
	@Autowired
	DownloadRecordMapper downloadRecordMapper;
	
	@Autowired
	CollectionMapper collectionMapper;
	
	@Autowired
	SubjectQuestionOptionMapper subjectQuestionOptionMapper;
	
	@Autowired
	SubjectResultMapper subjectResultMapper;
	
	@Autowired
	SubjectQuestionMapper subjectQuestionMapper;
	
	@Autowired
	UserDetailMapper userDetailMapper;
	
	@Autowired
	UserLoginMapper userLoginMapper;
	
	//added by JIANG
	@Autowired
	UserAuthMapper userAuthMapper;
	
	@Autowired
	CodeRecordMapper codeRecordMapper;
	
	@Autowired
	AboutMapper aboutMapper;
	
	@Autowired
	DepartmentMapper departmentMapper;
	
	@Autowired
	DirtyMapper dirtyMapper;
	
	@Autowired
	CommentMapper commentMapper;
	
	@Autowired
	UserMessageMapper userMessageMapper;
	
	@Autowired
	AreaCityMapper areaCityMapper;
	
	@Autowired
	AreaCountyMapper areaCountyMapper;
	
	@Autowired
	HospitalGradeMapper hospitalGradeMapper;
	
	@Autowired
	AreaProvMapper areaProvMapper;
	
	@Autowired
	ProductLiteratureMapper productLiteratureMapper;
	
	@Autowired
	BannerMapper bannerMapper;
	
	@Autowired
	UserKeywordMapper userKeywordMapper;
	
	//added by JIANG
	@Autowired
	VersionMapper versionMapper;
	
	@Autowired
	HerbalMedicineMapper herbalMedicineMapper;
	
	ClientSDK sdk = new ClientSDK();
	
}
