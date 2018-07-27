package com.hrsb.cg.controller.manage;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.ehcache.util.ProductInfo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hrsb.cg.controller.client.BaseController;
import com.hrsb.cg.model.AreaProv;
import com.hrsb.cg.model.Department;
import com.hrsb.cg.model.HospitalGrade;
import com.hrsb.cg.model.Manager;
import com.hrsb.cg.model.UserDetail;
import com.hrsb.cg.model.HerbalMedicine;
import com.hrsb.cg.service.HerbalMedicineService;
import com.hrsb.cg.util.AjaxUtil;
import com.hrsb.cg.util.Const;
import com.hrsb.cg.util.Page;
import com.hrsb.cg.util.Tools;

@Controller
@RequestMapping(value = "/manageYaodian")
public class ManageYaodianController extends BaseController {

	@Autowired
	HerbalMedicineService herbalMedicineService;

	@RequestMapping(value = "yaodianList")
	public ModelAndView yaodianList(
			@RequestParam(defaultValue = "1", required = false) Integer pageCurrent,
			@RequestParam(defaultValue = "15", required = false) Integer pageSize,
			@RequestParam(required = false) String title,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/manage/yaodian/yaodian-list");
		Page<HerbalMedicine> page = new Page<HerbalMedicine>(pageCurrent,
				pageSize);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", title);
		page.setParams(params);
		page.setOrderDirection("asc");
		List<HerbalMedicine> herbalList = herbalMedicineService
				.selectByPage(page);
		page.setResults(herbalList);
		mv.addObject("page", page);
		return mv;
	}

	@RequestMapping(value = "yaodianSaveUI")
	public ModelAndView yaodianSaveUI(
			@RequestParam(required = false) Long herbalId,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/manage/yaodian/yaodian-edit");
		HerbalMedicine herbal = herbalMedicineService.getDetails(herbalId);
		mv.addObject("herbal", herbal);
		return mv;
	}

	@RequestMapping(value = "/yaodianSave")
	public void yaodianSave(@ModelAttribute HerbalMedicine herbal,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		int i = herbalMedicineService.saveHerbal(herbal);
		if (i == 1) {
			new AjaxUtil(request, response).JsonType("200", "保存成功！", "",
					"herbal", "", "", "保存成功！", true);
		} else {
			new AjaxUtil(request, response).JsonType("300", "保存失败！", "",
					"herbal", "", "", "保存失败", true);
		}
	}

	@RequestMapping(value = "del")
	public void yaodianDel(Integer herbalId, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int i = 0;
		i = herbalMedicineService.delLogic(herbalId);
		if (i == 1) {
			new AjaxUtil(request, response).JsonType("200", "保存成功！", "",
					"herbal", "", "", "保存成功！", false);
		} else {
			new AjaxUtil(request, response).JsonType("300", "保存失败！", "",
					"herbal", "", "", "保存失败", false);
		}
	}

}
