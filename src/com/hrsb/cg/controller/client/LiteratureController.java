package com.hrsb.cg.controller.client;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.asn1.x509.qualified.TypeOfBiometricData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.hrsb.cg.dao.CollectionMapper;
import com.hrsb.cg.dao.CommentMapper;
import com.hrsb.cg.dao.DownloadRecordMapper;
import com.hrsb.cg.dao.LiteratureMapper;
import com.hrsb.cg.dao.UserDetailMapper;
import com.hrsb.cg.dao.UserKeywordMapper;
import com.hrsb.cg.model.Collection;
import com.hrsb.cg.model.Comment;
import com.hrsb.cg.model.DownloadRecord;
import com.hrsb.cg.model.Literature;
import com.hrsb.cg.model.UserDetail;
import com.hrsb.cg.model.UserKeyword;
import com.hrsb.cg.model.UserLogin;
import com.hrsb.cg.service.LiteratureService;
import com.hrsb.cg.util.Const;
import com.hrsb.cg.util.Page;
import com.hrsb.cg.util.Tools;
import com.hrsb.cg.util.WebClient;

/**
 * 万方文献控制类
 * @author app001
 *
 */
@Controller
@RequestMapping(value = "/literature")
public class LiteratureController extends BaseController {
	
	@Autowired
	LiteratureMapper literatureMapper;
	
	@Autowired
	UserKeywordMapper userKeywordMapper;
	
	@Autowired
	LiteratureService literatureService;
	
	@Autowired
	DownloadRecordMapper downloadRecordMapper;
	
	@Autowired
	CollectionMapper collectionMapper;
	
	@Autowired
	CommentMapper commentMapper;
	
	@Autowired
	UserDetailMapper userDetailMapper;
	
	WebClient webClient = new WebClient();
	
	/**
	 * 万方文献列表页
	 * @param title
	 * @param pageNo
	 * @param pageSize
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/list")
	public String getLiterature(@RequestParam(required = false) String title, 
			@RequestParam(defaultValue = "1") int pageNo, 
			@RequestParam(defaultValue = Const.PAGESIZE) int pageSize, 
			ModelMap modelMap) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", title);
		Page<Literature> page = new Page<Literature>(pageNo, pageSize).setParams(params);
		page.setOrderField("create_time");
		page.setOrderDirection("desc");
		List<Literature> literatures = null;
		// title为空查本地，不为空查万方
		if (StringUtils.isEmpty(title)) {
			literatures = literatureMapper.selectByPage(page); 
		} else {
			// 检索调用万方文献接口查询数据
			String url = Tools.getWanFangSearchUrl(params, (pageNo - 1) * pageSize + 1, pageSize);
			System.out.println(url);
			String result = webClient.doGet(url);
			
			// 处理万方返回来的数据
			if (!StringUtils.isEmpty(result)) {
				Map<String, Object> resultMap = JSON.parseObject(result, Map.class);
				List<Map<String, Object>> records = (List<Map<String, Object>>) resultMap.get("Records");
				page.setTotalRecord((Integer) resultMap.get("Total"));
				String totalPage=""+(float)page.getTotalRecord() / pageSize+"";
				if(Tools.isRepeat(totalPage.substring(totalPage.indexOf(".")+1))){
					page.setTotalPage(page.getTotalRecord() / pageSize);
				}else{
					page.setTotalPage(Integer.parseInt(totalPage.substring(0, totalPage.indexOf(".")))+1);
				}
				if (!CollectionUtils.isEmpty(records)) {
					literatures = new ArrayList<Literature>();
					
					for (Map<String, Object> record : records) {
						Literature literature = new Literature();
						literature.setArticleId((String) record.get("ArticleID"));
						literature.setTitle((String) record.get("Title"));
						literature.setCreator((String) record.get("Creator"));
						literature.setSource((String) record.get("Source"));
						literature.setKeywords((String) record.get("KeyWords"));
						literature.setYear((Integer) record.get("Year"));
						literature.setTypes((String) record.get("DBID"));
						literatures.add(literature);
					}
				}
			}
			
			UserKeyword userKeyword = userKeywordMapper.selectByName(title);
			UserKeyword keyword = new UserKeyword();
			
			if (null != userKeyword) {
				keyword.setId(userKeyword.getId());
				keyword.setClickCount(userKeyword.getClickCount() + 1);
				userKeywordMapper.updateByPrimaryKeySelective(keyword);
			} else {
				keyword.setName(title);
				keyword.setClickCount(1);
				keyword.setStatus((byte) 1);
				userKeywordMapper.insertSelective(keyword);
			}
		}
		
		page.setResults(literatures);
		modelMap.put("page", page);
		modelMap.put("type", 3);
		return "client/literature/list";
	}
	
	/**
	 * 高级检索页面
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/searchUI")
	public String searchUI(ModelMap modelMap) {
		List<String> month=Tools.getMonth();
		List<String> year=Tools.getYear();
		List<String> year2=Tools.getYear();
		modelMap.put("month", month);
		modelMap.put("year", year);
		Collections.sort(year2,Collator.getInstance(java.util.Locale.CHINA));//注意：是根据的汉字的拼音的字母排序的，而不是根据汉字一般的排序方法
		modelMap.put("year2", year2);
		modelMap.put("type", 3);
		return "client/literature/search";
	}
	
	/**
	 * 高级检索列表页面
	 * @param title
	 * @param creator
	 * @param keywords
	 * @param abstracts
	 * @param yearSmall
	 * @param yearBig
	 * @param pageNo
	 * @param pageSize
	 * @param modelMap
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/list_search")
	public String list_search(@RequestParam(required = false) String title, 
			@RequestParam(required = false) String creator, 
			@RequestParam(required = false) String keywords, @RequestParam(required = false) String abstracts, 
			@RequestParam(required = false) String yearSmall,@RequestParam(required = false) String monthSmall,
			@RequestParam(required = false) String yearBig, @RequestParam(required = false) String monthBig,
			@RequestParam(defaultValue = "1") int pageNo, 
			@RequestParam(defaultValue = Const.PAGESIZE) int pageSize, 
			ModelMap modelMap,HttpServletRequest request) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", title);
		params.put("creator", creator);
		params.put("keywords", keywords);
		params.put("abstracts", abstracts);
		if(StringUtils.isNotEmpty(yearSmall) && StringUtils.isNotEmpty(monthSmall)){
			params.put("yearSmall", yearSmall+"-"+monthSmall);
		}else if(StringUtils.isNotEmpty(yearSmall)){
			params.put("yearSmall", yearSmall);
		}
		
		if(StringUtils.isNotEmpty(yearBig) && StringUtils.isNotEmpty(monthBig)){
			params.put("yearBig", yearBig+"-"+monthBig);
		}else if(StringUtils.isNotEmpty(yearBig)){
			params.put("yearBig", yearBig);
		}
		// 检索调用万方文献接口查询数据
		String url = Tools.getWanFangSearchUrl(params, (pageNo - 1) * pageSize + 1, pageSize);
		System.out.println(url);
		String result = webClient.doGet(url);
		params.put("yearSmall1", yearSmall);
		params.put("yearBig1", yearBig);
		params.put("monthSmall", monthSmall);
		params.put("monthBig", monthBig);
		Page<Literature> page = new Page<Literature>(pageNo, pageSize).setParams(params);
		List<Literature> literatures = new ArrayList<Literature>();
		// 处理万方返回来的数据
		if (!StringUtils.isEmpty(result)) {
			Map<String, Object> resultMap = JSON.parseObject(result, Map.class);
			List<Map<String, Object>> records = (List<Map<String, Object>>) resultMap.get("Records");
			page.setTotalRecord((Integer) resultMap.get("Total"));
			String totalPage=""+(float)page.getTotalRecord() / pageSize+"";
			if(Tools.isRepeat(totalPage.substring(totalPage.indexOf(".")+1))){
				page.setTotalPage(page.getTotalRecord() / pageSize);
			}else{
				page.setTotalPage(Integer.parseInt(totalPage.substring(0, totalPage.indexOf(".")))+1);
			}
			if (!CollectionUtils.isEmpty(records)) {
				for (Map<String, Object> record : records) {
					Literature literature = new Literature();
					literature.setArticleId((String) record.get("ArticleID"));
					literature.setTitle((String) record.get("Title"));
					literature.setCreator((String) record.get("Creator"));
					literature.setSource((String) record.get("Source"));
					literature.setKeywords((String) record.get("KeyWords"));
					literature.setYear((Integer) record.get("Year"));
					literature.setTypes((String) record.get("DBID"));
					literatures.add(literature);
				}
			}
		}
		page.setResults(literatures);
		modelMap.put("page", page);
		modelMap.put("type", 3);
		List<String> month=Tools.getMonth();
		List<String> year=Tools.getYear();
		List<String> year2=Tools.getYear();
		modelMap.put("month", month);
		modelMap.put("year", year);
		Collections.sort(year2,Collator.getInstance(java.util.Locale.CHINA));//注意：是根据的汉字的拼音的字母排序的，而不是根据汉字一般的排序方法
		modelMap.put("year2", year2);
		return "client/literature/search";
	}
	
	/**
	 * 详情页面
	 * @param articleId
	 * @param types
	 * @param request
	 * @param modelMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/detail")
	public String getLiteratureDetails(@RequestParam String articleId, @RequestParam String typ, 
			@RequestParam(defaultValue = "1") int pageNo, 
			@RequestParam(defaultValue = Const.PAGESIZE) int pageSize,
			HttpServletRequest request, ModelMap modelMap, HttpSession session) throws Exception {
		UserLogin userLogin = (UserLogin) session.getAttribute(Const.SESSION_CLIENT_USER);
		// 判断本地数据库是否已经保存有，如果没有先去万方获取详情保存到本地；
		Literature literature = literatureService.getByArticleId(articleId);
		
		if (null != literature) {
			if(userLogin!=null){
				DownloadRecord downloadRecord = downloadRecordMapper.selectByLiteratureIdAndUserId(literature.getId(), userLogin.getId(), (byte) 1);
				
				if (null != downloadRecord && downloadRecord.getStatus() == 1) {
					literature.setIsDownload(1);
				} else {
					literature.setIsDownload(0);
				}
				
				Collection collection = collectionMapper.selectByLiteratureIdAndUserId(literature.getId(), userLogin.getId(), (byte) 1);
			
				if (null != collection) {
					literature.setIsCollection(1);
				} else {
					literature.setIsCollection(0);
				}
			}
			// 修改浏览量
			Literature literature2 = new Literature();
			literature2.setId(literature.getId());
			literature2.setClickCount(literature.getClickCount() + 1);
			literatureMapper.updateClickCount(literature2);
		} else {
			// 请求万方，保存到本地再返回参数
			// 把从万方获取到的数据保存到本地数据库
			String url = Const.WF_DETAIL + "&articleId=" + articleId + "&type=" + typ;
			String result = webClient.doGet(url);
			System.out.println(result);
			// 处理万方返回来的数据
			if (!StringUtils.isEmpty(result)) {
				Map<String, Object> resultMap = JSON.parseObject(result, Map.class);
				literature = new Literature();
				literature.setArticleId(articleId);
				literature.setTitle((String) resultMap.get("Title"));
				literature.setAbstracts((String) resultMap.get("Abstract"));
				literature.setCreator((String) resultMap.get("Creator"));
				literature.setOrganization((String) resultMap.get("Organization"));
				literature.setSource((String) resultMap.get("Source"));
				literature.setYear((Integer) resultMap.get("Year"));
				literature.setVolume((String) resultMap.get("Volume"));
				literature.setIssue((String) resultMap.get("Issue"));
				literature.setKeywords((String) resultMap.get("KeyWords"));
				literature.setPage((String) resultMap.get("KeyWords"));
				literature.setConference((String) resultMap.get("Conference"));
				literature.setConferenceDate((String) resultMap.get("ConferenceDate"));
				literature.setConferenceLocus((String) resultMap.get("ConferenceLocus"));
				literature.setConvener((String) resultMap.get("Convener"));
				literature.setAuthorSubject((String) resultMap.get("AuthorSubject"));
				literature.setDegree((String) resultMap.get("Degree"));
				literature.setTeacherName((String) resultMap.get("TeacherName"));
				literature.setTypes(typ);
				literature.setDownloadCount(0);
				literature.setClickCount(1);
				literature.setCreateTime(new Date());
				literature.setIsCollection(0);
				literature.setIsDownload(0);
				
				// 判断是否有全文，如果有全文保存到本地服务器
				literatureService.saveWanFangDetail(resultMap, literature, request);
							
				literatureMapper.insertSelective(literature);
			} 
		}
		
		// 评论
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", literature.getId());
		params.put("types", 1);
		Page<Comment> page = new Page<Comment>(pageNo, pageSize).setParams(params);
		page.setOrderField("create_time");
		page.setOrderDirection("desc");
		List<Comment> comments = commentMapper.getByPage(page);
		
		if (!CollectionUtils.isEmpty(comments)) {
			for (Comment comment : comments) {
				UserDetail userDetail = userDetailMapper.selectByUserId(comment.getUserId());
				userService.getHeadImg(userDetail);
				comment.setUserDetail(userDetail);
			}
		}
		
		page.setResults(comments);
		modelMap.put("literature", literature);
		modelMap.put("page", page);
		modelMap.put("type", 3);
		return "client/literature/detail";
	}
	
	/**
	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	public @ResponseBody ListJson getComment(@RequestParam long id, 
			@RequestParam(defaultValue = "1") int pageNo, 
			@RequestParam(defaultValue = Const.PAGESIZE) int pageSize, 
			HttpServletRequest request) {
		ListJson json = new ListJson();
		
		try {
			json = literatureService.getCommentList(id, pageNo, pageSize, request);
		} catch (Exception e) {
			getSysError(e, json);
		}
		
		return json;
	}
	*/
}
