package com.hrsb.cg.controller.manage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hrsb.cg.controller.client.BaseController;
import com.hrsb.cg.model.MingCi;
import com.hrsb.cg.service.MingCiService;
import com.hrsb.cg.util.AjaxUtil;
import com.hrsb.cg.util.Page;

@Controller
@RequestMapping(value = "/manageMingci")
public class ManageMingciController extends BaseController {

	@Autowired
	MingCiService mingciService;

	@RequestMapping(value = "mingciList")
	public ModelAndView mingciList(
			@RequestParam(defaultValue = "1", required = false) Integer pageCurrent,
			@RequestParam(defaultValue = "15", required = false) Integer pageSize,
			@RequestParam(required = false) String title,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/manage/mingci/mingci-list");
		Page<MingCi> page = new Page<MingCi>(pageCurrent,
				pageSize);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", title);
		page.setParams(params);
		page.setOrderDirection("asc");
		List<MingCi> mingciList = mingciService
				.selectByPage(page);
		page.setResults(mingciList);
		mv.addObject("page", page);
		return mv;
	}

	@RequestMapping(value = "mingciSaveUI")
	public ModelAndView mingciSaveUI(
			@RequestParam(required = false) Long mingciId,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/manage/mingci/mingci-edit");
		MingCi mingci = mingciService.getDetails(mingciId);
		mv.addObject("mingci", mingci);
		return mv;
	}

	@RequestMapping(value = "/mingciSave")
	public void mingciSave(@ModelAttribute MingCi mingci,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		int i = mingciService.saveMingCi(mingci);
		if (i == 1) {
			new AjaxUtil(request, response).JsonType("200", "保存成功！", "",
					"mingci", "", "", "保存成功！", true);
		} else {
			new AjaxUtil(request, response).JsonType("300", "保存失败！", "",
					"mingci", "", "", "保存失败", true);
		}
	}

	@RequestMapping(value = "del")
	public void yaodianDel(Integer mingciId, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int i = 0;
		i = mingciService.delLogic(mingciId);
		if (i == 1) {
			new AjaxUtil(request, response).JsonType("200", "保存成功！", "",
					"mingci", "", "", "保存成功！", false);
		} else {
			new AjaxUtil(request, response).JsonType("300", "保存失败！", "",
					"mingci", "", "", "保存失败", false);
		}
	}

}
