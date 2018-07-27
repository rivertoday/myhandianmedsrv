package com.hrsb.cg.dao;

import java.util.List;

import com.hrsb.cg.model.UserMessage;
import com.hrsb.cg.model.UserMessageWithBLOBs;
import com.hrsb.cg.util.Page;

public interface UserMessageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserMessageWithBLOBs record);

    int insertSelective(UserMessageWithBLOBs record);

    UserMessageWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserMessageWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(UserMessageWithBLOBs record);

    int updateByPrimaryKey(UserMessage record);

	List<UserMessageWithBLOBs> selectByPage(Page<UserMessageWithBLOBs> page);

	List<UserMessage> selectTopByUserId(Long userId);

	List<UserMessageWithBLOBs> getByPage(Page<UserMessageWithBLOBs> page);

	void deleteByIdAndUserId(long id, Long id2);

	void updateIsNew(long id);

	UserMessage selectPre(Long id, Long userId);

	UserMessage selectNext(Long id, Long userId);

	int selectCountByIsNewAndUserId(byte isNew, Long userId);
	
}