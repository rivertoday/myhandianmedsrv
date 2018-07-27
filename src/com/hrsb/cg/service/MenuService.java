package com.hrsb.cg.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hrsb.cg.model.Menu;

public interface MenuService {
	 int deleteByPrimaryKey(Integer id);

	    int insert(Menu record);

	    int insertSelective(Menu record);

	    Menu selectByPrimaryKey(Integer id);

	    int updateByPrimaryKeySelective(Menu record);

	    int updateByPrimaryKey(Menu record);
	    //获取所有栏目
	    List<Menu> findMenus();
	    //根据父级ID获取栏目
	    List<Menu> findMenusByParentid(Integer parentid);
	    //批量删除
	    int deleteMoreMenu(String ids);
	    List<Menu> findMenusByParentidRecur(String ids);
}
