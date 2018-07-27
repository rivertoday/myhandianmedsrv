package com.hrsb.cg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.googlecode.ehcache.annotations.When;
import com.hrsb.cg.dao.ManagerMapper;
import com.hrsb.cg.dao.ModuleMapper;
import com.hrsb.cg.model.Module;
import com.hrsb.cg.service.ModuleService;
import com.hrsb.cg.util.Page;
@Service
public class ModuleServiceImpl implements ModuleService {
	@Autowired
	ModuleMapper moduleMapper;
	@Autowired
	ManagerMapper managerMapper;
	@Override
	@TriggersRemove(cacheName="baseCache",when=When.AFTER_METHOD_INVOCATION,removeAll=true)
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return moduleMapper.deleteByPrimaryKey(id);
	}

	@Override
	@TriggersRemove(cacheName="baseCache",when=When.AFTER_METHOD_INVOCATION,removeAll=true)
	public int insert(Module record) {
		// TODO Auto-generated method stub
		return moduleMapper.insert(record);
	}

	@Override
	@TriggersRemove(cacheName="baseCache",when=When.AFTER_METHOD_INVOCATION,removeAll=true)
	public int insertSelective(Module record) {
		// TODO Auto-generated method stub
		return moduleMapper.insertSelective(record);
	}

	@Override
	@Cacheable(cacheName="baseCache")
	public Module selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		Module module = moduleMapper.selectByPrimaryKey(id);
		if(module!=null)
		module.setParentM(moduleMapper.selectByPrimaryKey(module.getParentid()));
		return module;
	}

	@Override
	@TriggersRemove(cacheName="baseCache",when=When.AFTER_METHOD_INVOCATION,removeAll=true)
	public int updateByPrimaryKeySelective(Module record) {
		// TODO Auto-generated method stub
		return moduleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	@TriggersRemove(cacheName="baseCache",when=When.AFTER_METHOD_INVOCATION,removeAll=true)
	public int updateByPrimaryKey(Module record) {
		// TODO Auto-generated method stub
		return moduleMapper.updateByPrimaryKey(record);
	}

	@Override
	@Cacheable(cacheName="baseCache")
	public List<Module> findModuleByGroupId(Integer groupId) {
		// TODO Auto-generated method stub
		return moduleMapper.findModuleByGroupId(groupId);
	}

	@Override
	@Cacheable(cacheName="baseCache")
	public List<Module> findAllModuleByChirld(Integer id) {
		// TODO Auto-generated method stub
		return moduleMapper.findAllModuleByChirld(id);
	}

	@Override
	@Cacheable(cacheName="baseCache")
	public List<Module> findAllModuleByParent(Integer id) {
		// TODO Auto-generated method stub
		return moduleMapper.findAllModuleByParent(id);
	}

	@Override
	public List<Module> getAll() {
		// TODO Auto-generated method stub
		return moduleMapper.getAll();
	}

	@Override
	@Cacheable(cacheName="baseCache")
	public List<Module> getModuleByPage(Page<Module> module) {
		List<Module> ms = moduleMapper.getModuleByPage(module);
		for (int i = 0; i < ms.size(); i++) {
			ms.get(i).setOperatorM(managerMapper.selectByPrimaryKey(ms.get(i).getOperator()));
			ms.get(i).setParentM(moduleMapper.selectByPrimaryKey(ms.get(i).getParentid()));
		}
		return ms;
	}

	@Override
	public int deleteSome(List<String> ids) {
		// TODO Auto-generated method stub
		return moduleMapper.deleteSome(ids);
	}

}
