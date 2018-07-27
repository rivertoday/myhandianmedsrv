package com.hrsb.cg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.googlecode.ehcache.annotations.When;
import com.hrsb.cg.dao.GroupMapper;
import com.hrsb.cg.model.Group;
import com.hrsb.cg.service.GroupService;
@Service
public class GroupServiceImpl implements GroupService {
	@Autowired
	GroupMapper groupMapper;
	@Override
	@TriggersRemove(cacheName="baseCache",when=When.AFTER_METHOD_INVOCATION,removeAll=true)
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return groupMapper.deleteByPrimaryKey(id);
	}

	@Override
	@TriggersRemove(cacheName="baseCache",when=When.AFTER_METHOD_INVOCATION,removeAll=true)
	public int insert(Group record) {
		// TODO Auto-generated method stub
		return groupMapper.insert(record);
	}

	@Override
	@TriggersRemove(cacheName="baseCache",when=When.AFTER_METHOD_INVOCATION,removeAll=true)
	public int insertSelective(Group record) {
		// TODO Auto-generated method stub
		return groupMapper.insertSelective(record);
	}

	@Override
	@Cacheable(cacheName="baseCache")
	public Group selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return groupMapper.selectByPrimaryKey(id);
	}

	@Override
	@TriggersRemove(cacheName="baseCache",when=When.AFTER_METHOD_INVOCATION,removeAll=true)
	public int updateByPrimaryKeySelective(Group record) {
		// TODO Auto-generated method stub
		return groupMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	@TriggersRemove(cacheName="baseCache",when=When.AFTER_METHOD_INVOCATION,removeAll=true)
	public int updateByPrimaryKey(Group record) {
		// TODO Auto-generated method stub
		return groupMapper.updateByPrimaryKey(record);
	}

	@Override
	@Cacheable(cacheName="baseCache")
	public List<Group> getAll(Group record) {
		// TODO Auto-generated method stub
		return groupMapper.getAll(record);
	}

}
