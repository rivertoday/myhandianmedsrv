package com.hrsb.cg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrsb.cg.dao.UserMessageMapper;
import com.hrsb.cg.model.UserMessage;
import com.hrsb.cg.model.UserMessageWithBLOBs;
import com.hrsb.cg.service.InformService;
import com.hrsb.cg.util.Page;
@Service
public class InformServiceImpl implements InformService{
	
	@Autowired
	UserMessageMapper userMessageMapper;

	@Override
	public List<UserMessageWithBLOBs> selectByPage(Page<UserMessageWithBLOBs> page) {
		
		return userMessageMapper.selectByPage(page);
	}

	@Override
	public int Save(UserMessageWithBLOBs userMessage) {
		return userMessageMapper.insertSelective(userMessage);
	}

}
