package com.hrsb.cg.controller.d1api;

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
import org.apache.log4j.Logger;
import org.bouncycastle.asn1.x509.qualified.TypeOfBiometricData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
import com.hrsb.cg.util.JsonUtil;
import com.hrsb.cg.util.Page;
import com.hrsb.cg.util.Tools;
import com.hrsb.cg.util.WebClient;

/**
 * 万方文献控制类
 * 
 * @author app001
 * 
 */
@Controller
@RequestMapping(value = "/d1api/literature")
public class D1APILiteratureController extends D1APIController {
	// Added by JIANG, please refer to the log4j.xml and web.xml for
	// configuration
	private static final Logger JJLogger = Logger.getLogger("RIVER_LOGGER");

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
	 * 高级检索列表
	 * 
	 * @param title
	 * @param creator
	 * @param keywords
	 * @param abstracts
	 * @param yearSmall
	 * @param yearBig
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	// @SuppressWarnings("unchecked")
	@RequestMapping(value = "/search.json")
	@ResponseBody
	public Map<String, Object> list_search(@RequestBody String data) {

		Map<String, Object> mymap = new HashMap<String, Object>();
		ModelMap modelMap = new ModelMap();
		Map<String, Object> params = new HashMap<String, Object>();
		String title = null;
		String creator = null;
		String keywords = null;
		String abstracts = null;
		String yearSmall = null;
		String yearBig = null;
		String monthSmall = null;
		String monthBig = null;
		Integer pageNo = 1;
		String pageSize = Const.PAGESIZE;

		try {
			if (StringUtils.isNotEmpty(data)) {
				System.out.println("Here it is the general search POST data: "
						+ data);
				JsonParser parser = new JsonParser(); // 创建json解析器
				JsonObject json = (JsonObject) parser.parse(data); // 创建jsonObject对象
				if (json.has("title"))
					title = json.get("title").getAsString();
				if (json.has("creator"))
					creator = json.get("creator").getAsString();
				if (json.has("keywords"))
					keywords = json.get("keywords").getAsString();
				if (json.has("abstracts"))
					abstracts = json.get("abstracts").getAsString();
				if (json.has("yearSmall"))
					yearSmall = json.get("yearSmall").getAsString();
				if (json.has("yearBig"))
					yearBig = json.get("yearBig").getAsString();
				if (json.has("monthSmall"))
					monthSmall = json.get("monthSmall").getAsString();
				if (json.has("monthBig"))
					monthBig = json.get("monthBig").getAsString();
				if (json.has("pageNo"))
					pageNo = json.get("pageNo").getAsInt();
				if (json.has("pageSize"))
					pageSize = json.get("pageSize").getAsString();

				if (StringUtils.isNotEmpty(title))
					params.put("title", title);
				if (StringUtils.isNotEmpty(creator))
					params.put("creator", creator);
				if (StringUtils.isNotEmpty(keywords))
					params.put("keywords", keywords);
				if (StringUtils.isNotEmpty(abstracts))
					params.put("abstracts", abstracts);
				if (StringUtils.isNotEmpty(yearSmall)
						&& StringUtils.isNotEmpty(monthSmall)) {
					params.put("yearSmall", yearSmall + "-" + monthSmall);
				} else if (StringUtils.isNotEmpty(yearSmall)) {
					params.put("yearSmall", yearSmall);
				}
				if (StringUtils.isNotEmpty(yearBig)
						&& StringUtils.isNotEmpty(monthBig)) {
					params.put("yearBig", yearBig + "-" + monthBig);
				} else if (StringUtils.isNotEmpty(yearBig)) {
					params.put("yearBig", yearBig);
				}
			}

			// 检索调用万方文献接口查询数据
			String url = Tools.getWanFangSearchUrl(params,
					(pageNo.intValue() - 1) * Integer.parseInt(pageSize) + 1,
					Integer.parseInt(pageSize));
			System.out.println(url);
			String result = webClient.doGet(url);
			params.put("yearSmall1", yearSmall);
			params.put("yearBig1", yearBig);
			params.put("monthSmall", monthSmall);
			params.put("monthBig", monthBig);
			Page<Literature> page = new Page<Literature>(pageNo.intValue(),
					Integer.parseInt(pageSize)).setParams(params);
			List<Literature> literatures = new ArrayList<Literature>();
			// 处理万方返回来的数据
			if (!StringUtils.isEmpty(result)) {
				Map<String, Object> resultMap = JSON.parseObject(result,
						Map.class);
				List<Map<String, Object>> records = (List<Map<String, Object>>) resultMap
						.get("Records");
				page.setTotalRecord((Integer) resultMap.get("Total"));
				String totalPage = "" + (float) page.getTotalRecord()
						/ Integer.parseInt(pageSize) + "";
				if (Tools
						.isRepeat(totalPage.substring(totalPage.indexOf(".") + 1))) {
					page.setTotalPage(page.getTotalRecord()
							/ Integer.parseInt(pageSize));
				} else {
					page.setTotalPage(Integer.parseInt(totalPage.substring(0,
							totalPage.indexOf("."))) + 1);
				}
				if (!CollectionUtils.isEmpty(records)) {
					for (Map<String, Object> record : records) {
						Literature literature = new Literature();
						literature.setArticleId((String) record
								.get("ArticleID"));
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
			List<String> month = Tools.getMonth();
			List<String> year = Tools.getYear();
			List<String> year2 = Tools.getYear();
			modelMap.put("month", month);
			modelMap.put("year", year);
			Collections.sort(year2,
					Collator.getInstance(java.util.Locale.CHINA));// 注意：是根据的汉字的拼音的字母排序的，而不是根据汉字一般的排序方法
			modelMap.put("year2", year2);

			mymap.put("success", "1");
			mymap.put("data", modelMap);
		} catch (Exception ex) {
			mymap.put("success", "0");
			mymap.put("errorMsg", ex.toString());
		}
		JJLogger.info(JsonUtil.maptojson(mymap));
		return mymap;
	}

	/**
	 * 详情
	 * 
	 * @param articleId
	 * @param type
	 * @param request
	 * @return
	 * @throws Exception
	 */
	// @SuppressWarnings("unchecked")
	@RequestMapping(value = "/detail.json")
	@ResponseBody
	public Map<String, Object> getLiteratureDetails(
			@RequestParam String articleId, @RequestParam String typ,			
			HttpServletRequest request) {

		Map<String, Object> mymap = new HashMap<String, Object>();
		ModelMap modelMap = new ModelMap();

		try {
			// 判断本地数据库是否已经保存有，如果没有先去万方获取详情保存到本地；
			Literature literature = literatureService.getByArticleId(articleId);			

			if (null != literature) {
				// 修改浏览量
				Literature literature2 = new Literature();
				literature2.setId(literature.getId());
				literature2.setClickCount(literature.getClickCount() + 1);
				literatureMapper.updateClickCount(literature2);			
				
			} else {
				// 请求万方，保存到本地再返回参数
				// 把从万方获取到的数据保存到本地数据库
				JJLogger.info("Plan to get the details of article "+articleId);
				String url = Const.WF_DETAIL + "&articleId=" + articleId
						+ "&type=" + typ;
				String result = webClient.doGet(url);
				System.out.println(result);
				JJLogger.info("Got article "+articleId+" with result "+result);
				// 处理万方返回来的数据
				if (!StringUtils.isEmpty(result)) {
					Map<String, Object> resultMap = JSON.parseObject(result,
							Map.class);
					literature = new Literature();
					literature.setArticleId(articleId);
					literature.setTitle((String) resultMap.get("Title"));
					literature.setAbstracts((String) resultMap.get("Abstract"));
					literature.setCreator((String) resultMap.get("Creator"));
					literature.setOrganization((String) resultMap
							.get("Organization"));
					literature.setSource((String) resultMap.get("Source"));
					literature.setYear((Integer) resultMap.get("Year"));
					literature.setVolume((String) resultMap.get("Volum"));
					literature.setIssue((String) resultMap.get("Issue"));
					literature.setKeywords((String) resultMap.get("KeyWords"));
					literature.setPage((String) resultMap.get("Page"));
					literature.setConference((String) resultMap
							.get("Conference"));
					literature.setConferenceDate((String) resultMap
							.get("ConferenceDate"));
					literature.setConferenceLocus((String) resultMap
							.get("ConferenceLocus"));
					literature.setConvener((String) resultMap.get("Convener"));
					literature.setAuthorSubject((String) resultMap
							.get("AuthorSubject"));
					literature.setDegree((String) resultMap.get("Degree"));
					literature.setTeacherName((String) resultMap
							.get("TeacherName"));
					literature.setTypes(typ);
					literature.setDownloadCount(0);
					literature.setClickCount(1);
					literature.setCreateTime(new Date());
					literature.setIsCollection(0);
					literature.setIsDownload(0);

					// 判断是否有全文，如果有全文保存到本地服务器
					Integer hasOriginalDoc = (Integer) resultMap.get("HasOriginalDoc");
					literature.setHasOriginalDoc(hasOriginalDoc.byteValue());
					//JJLogger.info("Plan to download article "+articleId);
					//literatureService.saveWanFangDetail(resultMap, literature,request);
					//JJLogger.info("Download article "+articleId+" success.");
					
					literatureMapper.insertSelective(literature);
					JJLogger.info("Article "+articleId + " has been inserted into local database.");
				}
			}

			modelMap.put("literature", literature);
			modelMap.put("type", 3);
			mymap.put("success", "1");
			mymap.put("data", modelMap);
		} catch (Exception ex) {
			mymap.put("success", "0");
			mymap.put("errorMsg", ex.toString());
		}
		JJLogger.info(JsonUtil.maptojson(mymap));
		return mymap;
	}
	
	/**
	 * 下载
	 * 
	 * @param articleId
	 * @param types
	 * @param request
	 * @param modelMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/download.json")
	@ResponseBody
	public Map<String, Object> downloadLiterature(
			@RequestParam String articleId, @RequestParam String typ,
			HttpServletRequest request) throws Exception {

		Map<String, Object> mymap = new HashMap<String, Object>();
		ModelMap modelMap = new ModelMap();

		try {
			Literature literature = literatureService.getByArticleId(articleId);
			//String downloadURL = literature.getDownloadUrl();
			String downloadURL = literature.getSpare1();//updated by JIANG at 20180109
			
			if (literature.getHasOriginalDoc()==1) {
				if ((downloadURL == null) || downloadURL.isEmpty()) {
					// 请求万方下载全文接口
					String url = Const.WF_DOWNLOAD + "&articleId=" + literature.getArticleId() + "&type=" + literature.getTypes() + "&sign=" + Tools.getSign();
					System.out.println(url);
					JJLogger.info("d1api download interface plan to get wanfan literature: "+url);
					String fileUrl = Tools.downloadFile(url, request);
					JJLogger.info("d1api download interface got the wanfan literature: "+fileUrl);
					//literature.setDownloadUrl(fileUrl);
					literature.setSpare1(fileUrl);//updated by JIANG at 20180109
					
					literatureMapper.updateByPrimaryKeySelective(literature);//added by JIANG at 20180522
				}				
				
				modelMap.put("literature", literature);
				modelMap.put("type", 3);
				mymap.put("success", "1");
				mymap.put("data", modelMap);
				
			}else{
				mymap.put("success", "1");
				mymap.put("data", "No pdf");
			}
			
		}catch (Exception ex) {
			mymap.put("success", "0");
			mymap.put("errorMsg", ex.toString());
		}
		JJLogger.info(JsonUtil.maptojson(mymap));
		return mymap;
		
	}
	
}
