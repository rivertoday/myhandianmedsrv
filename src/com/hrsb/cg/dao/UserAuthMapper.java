package com.hrsb.cg.dao;

import java.util.Date;

import com.hrsb.cg.model.UserAuth;

//Added by JIANG
public interface UserAuthMapper {	
	int deleteByPhone(String phone);

    int insertSelective(UserAuth record);

    UserAuth selectByPhone(String phone);
    
    UserAuth selectByToken(String token);

    int updateEffectByPhone(Date eTime, Date uTime, String token, String phone);

    UserAuth isExistPhone(String phone);

}
