package com.hrsb.cg.controller.manage;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hrsb.cg.model.Menu;
import com.hrsb.cg.service.MenuService;
import com.hrsb.cg.util.AjaxUtil;

@Controller
@RequestMapping(value="/menu")
public class MenuController {
	@Autowired
	MenuService menuService;
	/**
	 * 查询所有
	 * @return
	 */
	@RequestMapping("/fa")
	public ModelAndView findMenus(){
		List<Menu> ms = menuService.findMenus();
		return  new ModelAndView("/manage/menus").addObject("menus", ms);
	}
	@RequestMapping("/fp")
	public ModelAndView findMenusByParentId(Integer parentid){
		System.out.println(parentid);
		List<Menu> ms = menuService.findMenus();
		List<Menu> ms2 = menuService.findMenusByParentid(parentid);
		return new ModelAndView("/manage/menus").addObject("menus", ms).addObject("menubodys", ms2).addObject("parentid", parentid);
	}
	@RequestMapping("/fi")
	public ModelAndView findMenuById(Integer id,Integer parentid){
		List<Menu> ms = menuService.findMenus();
		Menu menu = new Menu();
		if(id!=null){
			menu = menuService.selectByPrimaryKey(id);
		}else{
			menu.setParentid(parentid);
		}
		return new ModelAndView("/manage/menus").addObject("menus", ms).addObject("menu", menu).addObject("parentid", parentid);
	}
	@RequestMapping("/em")
	public void editMenu(@RequestBody@ModelAttribute  Menu menu,HttpServletRequest request,HttpServletResponse response) throws IOException{
		int row = 0;
		menu.setCreatetime(new Date());
		if(menu.getId()==null){
			row = menuService.insertSelective(menu);
		}else{
			row = menuService.updateByPrimaryKey(menu);
		}
		if(row>0){
			new AjaxUtil(request, response).JsonType("200", "操作完成", "/menu/fp.im?parentid="+menu.getParentid(), "", "", "", "");
		}else{
			new AjaxUtil(request, response).JsonType("300", "操作失败", "", "", "", "", "");
		}
	}
	@RequestMapping("/dm")
	public void deleteMenu(String ids,HttpServletRequest request,HttpServletResponse response) throws IOException{
		int row = menuService.deleteMoreMenu(ids);
		if(row>0){
			new AjaxUtil(request, response).JsonType("200", "操作完成，本次删除"+row+"条记录", "", "menu", "", "", "");
		}else{
			new AjaxUtil(request, response).JsonType("300", "操作失败", "", "menu", "", "", "");
		}
	}
}
