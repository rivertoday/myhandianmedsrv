package com.hrsb.cg.dao;

import java.util.List;

import com.hrsb.cg.model.DownloadRecord;
import com.hrsb.cg.util.Page;

public interface DownloadRecordMapper {

    int insertSelective(DownloadRecord record);

    DownloadRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DownloadRecord record);

	int selectCountByUserId(Long userId);

	List<DownloadRecord> selectByPage(Page<DownloadRecord> page);

	DownloadRecord selectByLiteratureIdAndUserId(Long literatureId, long userId, byte types);

	void deleteByIdAndUserId(long id, long userId);

	void updateByIdAndUserId(long id, long userId, byte status);
	
}