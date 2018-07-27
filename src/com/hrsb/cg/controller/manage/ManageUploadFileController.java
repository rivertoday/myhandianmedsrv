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
import com.hrsb.cg.model.UploadFile;
import com.hrsb.cg.service.UploadService;
import com.hrsb.cg.util.AjaxUtil;
import com.hrsb.cg.util.Page;

@Controller
@RequestMapping(value = "/manageUpFile")
public class ManageUploadFileController extends BaseController {

	@Autowired
	UploadService upfileService;

	@RequestMapping(value = "upfileList")
	public ModelAndView upfileList(
			@RequestParam(defaultValue = "1", required = false) Integer pageCurrent,
			@RequestParam(defaultValue = "15", required = false) Integer pageSize,
			@RequestParam(required = false) String title,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/manage/upfile/upfile-list");
		Page<UploadFile> page = new Page<UploadFile>(pageCurrent,
				pageSize);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", title);
		page.setParams(params);
		page.setOrderDirection("asc");
		List<UploadFile> mingciList = upfileService
				.selectByPage(page);
		page.setResults(mingciList);
		mv.addObject("page", page);
		return mv;
	}

	@RequestMapping(value = "upfileSaveUI")
	public ModelAndView upfileSaveUI(
			@RequestParam(required = false) Long upfileId,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/manage/upfile/upfile-edit");
		UploadFile upfile = upfileService.getDetails(upfileId);
		mv.addObject("upfile", upfile);
		return mv;
	}

	@RequestMapping(value = "/upfileSave")
	public void upfileSave(@ModelAttribute UploadFile upfile,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		int i = upfileService.saveUploadFile(upfile);
		if (i == 1) {
			new AjaxUtil(request, response).JsonType("200", "保存成功！", "",
					"upfile", "", "", "保存成功！", true);
		} else {
			new AjaxUtil(request, response).JsonType("300", "保存失败！", "",
					"upfile", "", "", "保存失败", true);
		}
	}

	@RequestMapping(value = "del")
	public void upfileDel(Integer upfileId, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int i = 0;
		i = upfileService.delLogic(upfileId);
		if (i == 1) {
			new AjaxUtil(request, response).JsonType("200", "保存成功！", "",
					"upfile", "", "", "保存成功！", false);
		} else {
			new AjaxUtil(request, response).JsonType("300", "保存失败！", "",
					"upfile", "", "", "保存失败", false);
		}
	}

}
