package com.hrsb.cg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrsb.cg.dao.MenuMapper;
import com.hrsb.cg.model.Menu;
import com.hrsb.cg.service.MenuService;
@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	MenuMapper menuMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return menuMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Menu record) {
		// TODO Auto-generated method stub
		return menuMapper.insert(record);
	}

	@Override
	public int insertSelective(Menu record) {
		// TODO Auto-generated method stub
		return menuMapper.insertSelective(record);
	}

	@Override
	public Menu selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return menuMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Menu record) {
		// TODO Auto-generated method stub
		return menuMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Menu record) {
		// TODO Auto-generated method stub
		return menuMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Menu> findMenus() {
		// TODO Auto-generated method stub
		return menuMapper.findMenus();
	}

	@Override
	public List<Menu> findMenusByParentid(Integer parentid) {
		// TODO Auto-generated method stub
		return menuMapper.findMenusByParentid(parentid);
	}

	@Override
	public int deleteMoreMenu(String ids) {
		List<Menu> ms = findMenusByParentidRecur(ids);
		String [] idss = new String[ms.size()];
		for (int i = 0; i < ms.size(); i++) {
			idss[i] = ms.get(i).getId()+"";
		}
		return menuMapper.deleteMoreMenu(toString(idss));
	}

	@Override
	public List<Menu> findMenusByParentidRecur(String ids) {
		// TODO Auto-generated method stub
		return menuMapper.findMenusByParentidRecur(ids);
	}
	protected String toString(String [] ids){
		String str = "";
		for (int i = 0; i < ids.length; i++) {
			str+=ids[i]+",";
		}
		return str.substring(0,str.length()-1);
	}
}
