package com.hrsb.cg.controller.manage;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hrsb.cg.controller.client.BaseController;
import com.hrsb.cg.controller.util.BaseLogsUtil;
import com.hrsb.cg.dao.AboutMapper;
import com.hrsb.cg.dao.BannerMapper;
import com.hrsb.cg.dao.DirtyMapper;
import com.hrsb.cg.dao.GuideMapper;
import com.hrsb.cg.dao.VersionMapper;
import com.hrsb.cg.model.About;
import com.hrsb.cg.model.Banner;
import com.hrsb.cg.model.BaseLog;
import com.hrsb.cg.model.Dirty;
import com.hrsb.cg.model.Guide;
import com.hrsb.cg.model.HerbalMedicine;
import com.hrsb.cg.model.Manager;
import com.hrsb.cg.model.Version;
import com.hrsb.cg.service.BaseLogService;
import com.hrsb.cg.util.AjaxUtil;
import com.hrsb.cg.util.Const;
import com.hrsb.cg.util.Page;
import com.hrsb.cg.util.Tools;

@Controller
@RequestMapping(value="/syn")
public class SynthesizeController extends BaseController {
	
	@Autowired
	AboutMapper aboutMapper;
	
	@Autowired
	VersionMapper versionMapper;
	
	@Autowired
	BaseLogService baseLogService;
	
	@Autowired
	BannerMapper bannerMapper;
	
	@Autowired
	DirtyMapper dirtyMapper;
	
	@Autowired
	GuideMapper guideMapper;
	
	@RequestMapping(value="/about")
	public ModelAndView about(Integer aboutId,HttpServletRequest request){
		ModelAndView mv=new ModelAndView("/manage/synthesize/about");
		About about= aboutMapper.selectByPrimaryKey(aboutId);
		mv.addObject("about", about);
		return mv;
	}

	/**
	 * 保存
	 * @param information
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/save")
	public void save(About about, HttpServletRequest request, HttpServletResponse response) throws IOException{
		Manager manager = (Manager) request.getSession().getAttribute(Const.SESSION_MANAGER);
		about.setManagerId(manager.getId());
		about.setOperateTime(new Date());
		int row = aboutMapper.updateByPrimaryKeySelective(about);
		
		if(row > 0){
			//保存操作日志
			/*BaseLog bs = new BaseLog(manager.getIdkey(), new Date(), "关于我们编辑", manager.getIdkey(), "关于我们编辑", 0, 0);
			baseLogService.insertSelective(bs);*/
			new AjaxUtil(request, response).JsonType("200", "操作成功", "", "aboutlist", "", "", "操作成功", false);
		}else{
			new AjaxUtil(request, response).JsonType("300", "操作失败", "", "aboutlist", "", "", "操作失败", false);
		}
	}
	
	/**
	 * 版本信息
	 * @param aboutId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/versionList")
	public ModelAndView versionList(Integer aboutId,@RequestParam(defaultValue="1")Integer pageCurrent,
			@RequestParam(defaultValue="15")Integer pageSize,HttpServletRequest request){
		ModelAndView mv=new ModelAndView("/manage/synthesize/version-list");
		Page<Version> page=new Page<Version>(pageCurrent, pageSize);
		page.setOrderDirection("desc");
		page.setOrderField("operate_time");
		List<Version> versions=versionMapper.findAllByPage(page);
		page.setResults(versions);
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 版本信息
	 * @param aboutId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/versionEdit")
	public ModelAndView versionEdit(
			@RequestParam(required = false) Integer versionId,
			HttpServletRequest request) {
		ModelAndView mv=new ModelAndView("/manage/synthesize/version-edit");
		Version version = versionMapper.selectByPrimaryKey(versionId);
		mv.addObject("vs",version);
		return mv;
	}
	
	@RequestMapping(value = "/versionSave")
	public void versionSave(@ModelAttribute Version version,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		int row = 0;
		if (version.getId() != null) {
			row = versionMapper.updateByPrimaryKeySelective(version);
		} else {
			row = versionMapper.insertSelective(version);
		}

		if (row == 1) {
			new AjaxUtil(request, response).JsonType("200", "保存成功！", "",
					"version", "", "", "保存成功！", true);
		} else {
			new AjaxUtil(request, response).JsonType("300", "保存失败！", "",
					"version", "", "", "保存失败", true);
		}
	}
	
	
	/**
	 * 保存版本信息
	 * @param information
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	/*
	@SuppressWarnings("static-access")
	@RequestMapping(value="/versionSave")
	public void versionSave(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Manager manager = (Manager) request.getSession().getAttribute(Const.SESSION_MANAGER);
		String spare1 = request.getParameter("spare1");//版本号
		String versionName=request.getParameter("versionName");
		String versionLink=request.getParameter("versionLink");//android
		String spare2=request.getParameter("spare2");//ios
		Version version=new Version();
		if(StringUtils.isNotEmpty(versionLink)){
			version.setLink(versionLink.contains("://")?versionLink:Tools.getBasePath(request)+versionLink);
		}
		version.setName(versionName);
		version.setManagerId(manager.getId());
		version.setOperateTime(new Date());
		version.setSpare1(spare1);
		version.setSpare2(spare2);
		int row = versionMapper.insertSelective(version);
		
		if(row > 0){
			new AjaxUtil(request, response).JsonType("200", "操作成功", "", "versionList", "", "", "操作成功", true);
		}else{
			new AjaxUtil(request, response).JsonType("300", "操作失败", "", "versionList", "", "", "操作失败", true);
		}
	}
	*/
	
	/**
	 * 删除版本信息(无用)
	 * @param information
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/versionDel")
	public void versionDel(Integer versionId,HttpServletRequest request, HttpServletResponse response) throws IOException{
		int row=versionMapper.deleteByPrimaryKey(versionId);
		if(row > 0){
			new AjaxUtil(request, response).JsonType("200", "操作成功", "", "aboutlist", "", "", "操作成功", false);
		}else{
			new AjaxUtil(request, response).JsonType("300", "操作失败", "", "aboutlist", "", "", "操作失败", false);
		}
	}
	
	/**
	 * 万方文献首页banner
	 * @param aboutId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/bannerList")
	public ModelAndView bannerList(@RequestParam(defaultValue="1")Integer pageCurrent,
			@RequestParam(defaultValue="15")Integer pageSize,HttpServletRequest request){
		ModelAndView mv=new ModelAndView("/manage/synthesize/banner-list");
		Page<Banner> page=new Page<Banner>(pageCurrent, pageSize);
		page.setOrderDirection("desc");
		page.setOrderField("operate_time");
		List<Banner> bannerList=bannerMapper.findAllByPage(page);
		page.setResults(bannerList);
		mv.addObject("page", page);
		return mv;
	}
	
	
	
	/**
	 * 万方文献首页banner 编辑、新增
	 * @param aboutId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/bannerSaveUI")
	public ModelAndView bannerSaveUI(Integer bannerId,HttpServletRequest request){
		Banner banner= bannerMapper.selectByPrimaryKey(bannerId);
		ModelAndView mv=new ModelAndView("/manage/synthesize/banner-edit");
		mv.addObject("banner", banner);
		return mv;
	}
	
	/**
	 * 万方文献首页banner 编辑、新增
	 * @param aboutId
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/bannerSave")
	public void bannerSave(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Manager manager = (Manager) request.getSession().getAttribute(Const.SESSION_MANAGER);
		int row=0;
		String image=request.getParameter("image");
		String link=request.getParameter("link");
		String bannerId=request.getParameter("bannerId");
		String sorts=request.getParameter("sorts");
		String types=request.getParameter("types");
		Banner banner=new Banner();
		banner.setImage(image);
		banner.setLink(link);
		if(StringUtils.isNotEmpty(sorts)){
		banner.setSorts(Byte.parseByte(sorts));
		}
		banner.setManagerId(manager.getId());
		banner.setOperateTime(new Date());
		banner.setTypes(Byte.valueOf(types));
		if(StringUtils.isEmpty(bannerId)){
			banner.setStatus((byte)1);
			row=bannerMapper.insertSelective(banner);
		}else{
			banner.setId(Integer.parseInt(bannerId));
			row=bannerMapper.updateByPrimaryKeySelective(banner);
		}
		if(row > 0){
			new AjaxUtil(request, response).JsonType("200", "操作成功", "", "adList", "", "", "操作成功", true);
		}else{
			new AjaxUtil(request, response).JsonType("300", "操作失败", "", "adList", "", "", "操作失败", true);
		}
	}
	
	/**
	 * 万方文献首页banner 编辑、新增
	 * @param aboutId
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/bannerDel")
	public void bannerDel(@RequestParam(value="bannerId")Integer bannerId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		int row=bannerMapper.deleteByPrimaryKey(bannerId);
		if(row > 0){
			new AjaxUtil(request, response).JsonType("200", "操作成功", "", "adList", "", "", "操作成功", false);
		}else{
			new AjaxUtil(request, response).JsonType("300", "操作失败", "", "adList", "", "", "操作失败", false);
		}
	}
	
	/**
	 * 万方文献首页banner 编辑、新增
	 * @param aboutId
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/bannerPublish")
	public void bannerPublish(@RequestParam(value="bannerId")Integer bannerId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		Banner banner= bannerMapper.selectByPrimaryKey(bannerId);
		if(banner!=null && banner.getStatus()==1){
			banner.setStatus((byte)0);
		}else if(banner!=null && banner.getStatus()==0){
			banner.setStatus((byte)1);
		}
		int row=bannerMapper.updateByPrimaryKeySelective(banner);
		if(row > 0){
			new AjaxUtil(request, response).JsonType("200", "操作成功", "", "adList", "", "", "操作成功", false);
		}else{
			new AjaxUtil(request, response).JsonType("300", "操作失败", "", "adList", "", "", "操作失败", false);
		}
	}
	
	/**
	 * 脏字库
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/dirty")
	public ModelAndView dirty(HttpServletRequest request){
		ModelAndView mv=new ModelAndView("/manage/synthesize/dirty");
		Dirty dirty = dirtyMapper.selectByPrimaryKey(1);
		mv.addObject("dirty", dirty);
		return mv;
	}

	/**
	 * 脏字库保存
	 * @param information
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/dirty_save")
	public void dirtySave(Dirty dirty, HttpServletRequest request, HttpServletResponse response) throws IOException{
		Manager manager = (Manager) request.getSession().getAttribute(Const.SESSION_MANAGER);
		dirty.setManagerId(manager.getId());
		dirty.setOperateTime(new Date());
		int row = dirtyMapper.updateByPrimaryKeySelective(dirty);
		
		if(row > 0){
			//保存操作日志
			BaseLog bs = new BaseLog(manager.getIdkey(), new Date(), "脏字库编辑", manager.getIdkey(), "脏字库编辑", 0, 0);
			BaseLogsUtil.insertBaseLog(bs);
			new AjaxUtil(request, response).JsonType("200", "操作成功", "", "dirty", "", "", "操作成功", false);
		}else{
			new AjaxUtil(request, response).JsonType("300", "操作失败", "", "dirty", "", "", "操作失败", false);
		}
	}
	
	/**
	 * 引导页列表
	 * @param pageCurrent
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/guide_list")
	public ModelAndView guideList(@RequestParam(defaultValue = "1") int types,
			@RequestParam(required = false)Integer pageCurrent,
			@RequestParam(required = false)Integer pageSize, HttpServletRequest request){
		ModelAndView mv=new ModelAndView("/manage/synthesize/guide_list");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("types", types);
		Page<Guide> page=new Page<Guide>(pageCurrent, pageSize).setParams(params);
		page.setOrderDirection("asc");
		page.setOrderField("sorts");
		List<Guide> guides = guideMapper.selectByPage(page);
		page.setResults(guides);
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 添加/编辑引导页
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/guide_edit")
	public ModelAndView guideEdit(Integer id, HttpServletRequest request){
		ModelAndView mv=new ModelAndView("/manage/synthesize/guide_edit");
		
		if (null != id) {
			Guide guide = guideMapper.selectByPrimaryKey(id);
			mv.addObject("guide", guide);
		}
		
		return mv;
	}
	
	/**
	 * 保存引导页
	 * @param guide
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/guide_save")
	public void guideSave(Guide guide, HttpServletRequest request, HttpServletResponse response) throws IOException{
		Manager manager = (Manager) request.getSession().getAttribute(Const.SESSION_MANAGER);
		guide.setManagerId(manager.getId());
		guide.setOperateTime(new Date());
		int row = 0;
		
		if (null != guide.getId()) {
			row = guideMapper.updateByPrimaryKeySelective(guide);
		} else {
			row = guideMapper.insertSelective(guide);
		}
		
		if(row > 0){
			//保存操作日志
			BaseLog bs = new BaseLog(manager.getIdkey(), new Date(), "引导页编辑", manager.getIdkey(), "引导页编辑", 0, 0);
			BaseLogsUtil.insertBaseLog(bs);
			new AjaxUtil(request, response).JsonType("200", "操作成功", "", "guidelist", "", "", "操作成功", true);
		}else{
			new AjaxUtil(request, response).JsonType("300", "操作失败", "", "guidelist", "", "", "操作失败", true);
		}
	}
	
	/**
	 * 删除引导页
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/guide_del")
	public void guideDel(int id, HttpServletRequest request, HttpServletResponse response) throws IOException{
		Manager manager = (Manager) request.getSession().getAttribute(Const.SESSION_MANAGER);
		int row = guideMapper.deleteByPrimaryKey(id);
		
		if(row > 0){
			//保存操作日志
			BaseLog bs = new BaseLog(manager.getIdkey(), new Date(), "引导页删除", manager.getIdkey(), "引导页删除", 0, 0);
			BaseLogsUtil.insertBaseLog(bs);
			new AjaxUtil(request, response).JsonType("200", "操作成功", "", "guidelist", "", "", "操作成功", false);
		}else{
			new AjaxUtil(request, response).JsonType("300", "操作失败", "", "guidelist", "", "", "操作失败", false);
		}
	}
	
}
