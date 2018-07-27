package com.hrsb.cg.service;

import java.util.List;

import javax.servlet.ServletOutputStream;

import com.hrsb.cg.model.UserDetail;
import com.hrsb.cg.model.UserLogin;
import com.hrsb.cg.model.UserMessage;
import com.hrsb.cg.util.Page;


public interface UserService {

	/**
	 * 后台用户信息分页
	 * @param page
	 * @return
	 */
	List<UserDetail> selectUserDetailByPage(Page<UserDetail> page);

	/**
	 * 查询一条
	 * @param page
	 * @return
	 */
	UserDetail selectById(Long userDetailId);

	/**
	 * 保存
	 * @param userDetail
	 * @param loginName 手机号
	 * @param password 密码
	 * @return
	 */
	int userDetailSave(String loginName, String password, UserDetail userDetail);
	
	int delUser(Long userId);

	/**
	 * 判断登录是否存在
	 * @param loginName
	 * @return
	 */
	boolean isExistLoginName(String loginName);

	int saveUserLogin(UserLogin userLogin);

	/**
	 * 按登录主表的id查询
	 * @param userId
	 * @return
	 */
	UserLogin selectUserLoginById(Long userId);

	/**
	 * 导出查询选中
	 * @param list 
	 * @return
	 */
	List<UserDetail> getAll(List<String> list);

	/**
	 * 导出查询全部
	 * @param list 
	 * @return
	 */
	List<UserDetail> getAll();

	void exportExcel(List<UserDetail> sl, String[] titles,
			ServletOutputStream servletOutputStream);

	/**
	 * 验证密码是否错误
	 * @param phone
	 * @param password
	 * @return
	 */
	public String checkPassword(String phone, String password);

	/**
	 * 用户基本信息
	 * @param id
	 * @return
	 */
	UserDetail getUserDetailByUserId(Long id);
	
	UserDetail getUserDetailByUserPhone(String phone);//Added by JIANG

	/**
	 * 前置新闻
	 * @param id
	 * @return
	 */
	List<UserMessage> getTopMessageByUserId(Long id);

	/**
	 * 设置用户头像
	 * @param userDetail
	 */
	void getHeadImg(UserDetail userDetail);

	/**
	 * 修改用户信息
	 * @param userDetail
	 */
	void modifyUserDetailById(UserDetail userDetail);

	/**
	 * 判断手机号唯一
	 * @param phone
	 * @return
	 */
	UserDetail IsExistPhone(String phone);
	
}
