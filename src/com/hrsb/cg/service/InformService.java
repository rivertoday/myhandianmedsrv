package com.hrsb.cg.service;

import java.util.List;

import com.hrsb.cg.model.UserMessageWithBLOBs;
import com.hrsb.cg.util.Page;

public interface InformService {

	List<UserMessageWithBLOBs> selectByPage(Page<UserMessageWithBLOBs> page);

	int Save(UserMessageWithBLOBs userMessage);

}
