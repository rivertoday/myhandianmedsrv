package com.hrsb.cg.dao;

import java.util.List;

import com.hrsb.cg.model.Literature;
import com.hrsb.cg.util.Page;

public interface LiteratureMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(Literature record);
    
    int updateByPrimaryKeySelective(Literature record);//added by JIANG at 20180522

    Literature selectByPrimaryKey(Long id);

	List<Literature> selectByPage(Page<Literature> page);
	
	List<Literature> selectAdvancedByPage(Page<Literature> page);//added by JIANG at 20190603

	void updateDownloadCount(Literature literature2);

	Literature selectByArticleId(String articleId);

	void updateClickCount(Literature literature2);
	
}