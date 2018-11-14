package com.hrsb.cg.controller.manage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.hrsb.cg.dao.LiteratureMapper;
import com.hrsb.cg.dao.UserKeywordMapper;
import com.hrsb.cg.model.Literature;
import com.hrsb.cg.model.Manager;
import com.hrsb.cg.model.UserDetail;
import com.hrsb.cg.model.UserKeyword;
import com.hrsb.cg.service.LiteratureService;
import com.hrsb.cg.util.AjaxUtil;
import com.hrsb.cg.util.Const;
import com.hrsb.cg.util.Page;
import com.hrsb.cg.util.Tools;
import com.hrsb.cg.util.WebClient;

@Controller
@RequestMapping(value = "manageLiterature")
public class ManageLiteratureController {

	@Autowired
	LiteratureService literatureService;

	@Autowired
	UserKeywordMapper userKeywordMapper;
	
	@Autowired
	LiteratureMapper literatureMapper;//added by JIANG at 20180522

	WebClient webClient = new WebClient();

	@RequestMapping(value = "literatureList")
	public ModelAndView literatureList(Integer pageCurrent, Integer pageSize,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/manage/literature/literature-list");
		String title = request.getParameter("title");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", title);
		Page<Literature> page = new Page<Literature>(null == pageCurrent ? 1
				: pageCurrent, null == pageSize ? 15 : pageSize)
				.setParams(params);
		page.setOrderDirection("desc");
		page.setOrderField("download_count");
		List<Literature> Literatures = literatureService.selectByPage(page);
		page.setResults(Literatures);
		mv.addObject("page", page);
		return mv;
	}

	/**
	 * 关键字查询
	 * 
	 * @param pageCurrent
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "keywordList")
	public ModelAndView keywordList(Integer pageCurrent, Integer pageSize,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/manage/literature/keyword-list");

		Page<UserKeyword> page = new Page<UserKeyword>(null == pageCurrent ? 1
				: pageCurrent, null == pageSize ? 15 : pageSize);
		page.setOrderField("click_count");
		page.setOrderDirection("desc");
		List<UserKeyword> userKeywords = userKeywordMapper.selectByPage(page);
		page.setResults(userKeywords);
		mv.addObject("page", page);
		return mv;
	}

	/**
	 * 关键字编辑页面
	 * 
	 * @param pageCurrent
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "keywordSaveUI")
	public ModelAndView keywordSaveUI(String keywordId,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/manage/literature/keyword-edit");

		if (StringUtils.isNotEmpty(keywordId)) {
			UserKeyword userKeyword = userKeywordMapper.selectByPrimaryKey(Long
					.parseLong(keywordId));
			mv.addObject("userKeyword", userKeyword);
		}
		return mv;
	}

	@RequestMapping(value = "/keywordSave")
	public void keywordSave(@ModelAttribute UserKeyword userKeyword,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Manager m = (Manager) request.getSession().getAttribute(
				Const.SESSION_MANAGER);
		userKeyword.setManagerId(m.getId());
		userKeyword.setOperateTime(new Date());
		int i = 0;
		if (userKeyword.getId() == null) {
			i = userKeywordMapper.insertSelective(userKeyword);
		} else {
			i = userKeywordMapper.updateByPrimaryKeySelective(userKeyword);
		}
		if (i == 1) {
			new AjaxUtil(request, response).JsonType("200", "保存成功！", "",
					"keyword", "", "", "保存成功！", true);
		} else {
			new AjaxUtil(request, response).JsonType("300", "保存失败！", "",
					"keyword", "", "", "保存失败", true);
		}
	}

	@RequestMapping(value = "/keywordDel")
	public void keywordDel(@RequestParam(value = "keywordId") Long keywordId,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		int i = 0;
		i = userKeywordMapper.deleteByPrimaryKey(keywordId);
		if (i == 1) {
			new AjaxUtil(request, response).JsonType("200", "保存成功！", "",
					"keyword", "", "", "保存成功！", false);
		} else {
			new AjaxUtil(request, response).JsonType("300", "保存失败！", "",
					"keyword", "", "", "保存失败", false);
		}
	}

	@RequestMapping(value = "literatureSearch")
	public ModelAndView literatureSearch(Integer pageCurrent, Integer pageSize,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView(
				"/manage/literature/literature-search");
		String title = request.getParameter("title");
		//Modified by JIANG He at 20181109
		String keywords = request.getParameter("keywords");
		String creator = request.getParameter("creator");
		String yearSmall = request.getParameter("yearSmall");
		String yearBig = request.getParameter("yearBig");
		String sType = request.getParameter("sType");

		if ((title == null) && (keywords == null) && (creator == null) && 
			(yearSmall == null) && (yearBig == null)) {
			Page<Literature> page = new Page<Literature>(
					null == pageCurrent ? 1 : pageCurrent,
					null == pageSize ? 30 : pageSize);
			mv.addObject("page", page);
			return mv;
		}
		//Modified End

		try {
			Map<String, Object> params = new HashMap<String, Object>();
			if (title != null) {
				params.put("title", title);
			}
			//Modified by JIANG He at 20181109
			if (keywords != null) {
				params.put("keywords", keywords);
			}
			if (creator != null) {
				params.put("creator", creator);
			}
			if ((yearSmall != null) && (yearBig != null)) {
				params.put("yearSmall", yearSmall);
				params.put("yearBig", yearBig);
			}
			if (sType != null) {
				params.put("sType", sType);
			}
			//Modified End

			// 检索调用万方文献接口查询数据
			String url = Tools.getWanFangSearchUrl(params,
					(pageCurrent.intValue() - 1) * pageSize.intValue() + 1,
					pageSize.intValue());
			System.out.println(url);
			String result = webClient.doGet(url);

			Page<Literature> page = new Page<Literature>(
					null == pageCurrent ? 1 : pageCurrent,
					null == pageSize ? 15 : pageSize).setParams(params);
			List<Literature> literatures = new ArrayList<Literature>();

			// 处理万方返回来的数据
			if (!StringUtils.isEmpty(result)) {
				Map<String, Object> resultMap = JSON.parseObject(result,
						Map.class);
				List<Map<String, Object>> records = (List<Map<String, Object>>) resultMap
						.get("Records");
				page.setTotalRecord((Integer) resultMap.get("Total"));
				String totalPage = "" + (float) page.getTotalRecord()
						/ pageSize.intValue() + "";
				if (Tools
						.isRepeat(totalPage.substring(totalPage.indexOf(".") + 1))) {
					page.setTotalPage(page.getTotalRecord()
							/ pageSize.intValue());
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
			page.setPageSize(30);
			mv.addObject("page", page);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

		return mv;
	}

	@RequestMapping(value = "literatureDetail")
	public ModelAndView literatureDetail(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/manage/literature/literature-edit");
		String articleId = request.getParameter("articleId");
		String url = Const.WF_DETAIL + "&articleId=" + articleId
				+ "&type=WF_QK";

		try {
			String result = webClient.doGet(url);
			System.out.println(result);
			Literature literature = new Literature();
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
				literature.setConference((String) resultMap.get("Conference"));
				literature.setConferenceDate((String) resultMap
						.get("ConferenceDate"));
				literature.setConferenceLocus((String) resultMap
						.get("ConferenceLocus"));
				literature.setConvener((String) resultMap.get("Convener"));
				literature.setAuthorSubject((String) resultMap
						.get("AuthorSubject"));
				literature.setDegree((String) resultMap.get("Degree"));
				literature
						.setTeacherName((String) resultMap.get("TeacherName"));
				literature.setTypes("WF_QK");
				literature.setDownloadCount(0);
				literature.setClickCount(1);
				literature.setCreateTime(new Date());
				literature.setIsCollection(0);
				literature.setIsDownload(0);
				// 判断是否有全文，如果有全文保存到本地服务器
				Integer hasOriginalDoc = (Integer) resultMap
						.get("HasOriginalDoc");
				literature.setHasOriginalDoc(hasOriginalDoc.byteValue());
				mv.addObject("literature", literature);
			} else {
				mv.addObject("literature", null);
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return mv;
	}
	
	@RequestMapping(value = "literatureDownload")
	public ModelAndView literatureDownload(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/manage/literature/literature-down");
		String fileUrl = null;
		String articleId = request.getParameter("articleId");

		try {			
			Literature literature = literatureService.getByArticleId(articleId);
			
			if (literature == null) {
				
				String url = Const.WF_DETAIL + "&articleId=" + articleId
						+ "&type=WF_QK";
				String result = webClient.doGet(url);
				
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
					literature.setConference((String) resultMap.get("Conference"));
					literature.setConferenceDate((String) resultMap
							.get("ConferenceDate"));
					literature.setConferenceLocus((String) resultMap
							.get("ConferenceLocus"));
					literature.setConvener((String) resultMap.get("Convener"));
					literature.setAuthorSubject((String) resultMap
							.get("AuthorSubject"));
					literature.setDegree((String) resultMap.get("Degree"));
					literature
							.setTeacherName((String) resultMap.get("TeacherName"));
					literature.setTypes((String) resultMap
							.get("DBID"));// Modified by JIANG He at 20181109
					literature.setDownloadCount(0);
					literature.setClickCount(1);
					literature.setCreateTime(new Date());
					literature.setIsCollection(0);
					literature.setIsDownload(0);
					// 判断是否有全文，如果有全文保存到本地服务器
					Integer hasOriginalDoc = (Integer) resultMap
							.get("HasOriginalDoc");
					literature.setHasOriginalDoc(hasOriginalDoc.byteValue());
				}
				
				url = Const.WF_DOWNLOAD + "&articleId=" + articleId + "&type=" + literature.getTypes() + "&sign=" + Tools.getSign();//Modified by JIANG He at 20181109
				//fileUrl = Tools.downloadFile(url, request);
				fileUrl = Tools.downloadFile(url, literature.getTitle(), request);// Modified by JIANG He at 20181114
				literature.setDownloadUrl(fileUrl);
				literature.setSpare1(fileUrl);
				
				literatureMapper.insertSelective(literature);
			}
			
			mv.addObject("literature", literature);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		
		return mv;
	}

}
