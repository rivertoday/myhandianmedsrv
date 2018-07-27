package com.hrsb.cg.service;

import java.util.List;

import javax.servlet.ServletOutputStream;

import com.hrsb.cg.dto.PremissionItem;
import com.hrsb.cg.model.Manager;
import com.hrsb.cg.util.Page;

/**
 * 管理者
 * @author jing
 *
 */
public interface ManagerService {
	/**
	 * 添加管理（为空不插入）
	 * @param record
	 * @return
	 */
	int insertSelective(Manager record);
	/**
	 * 更新管理信息（为空更新）
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(Manager record);
	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	List<Manager> selectPage(Page<Manager> page);
	/**
	 * 获取唯一对象
	 * @param manager
	 * @return
	 */
	Manager findManager(Manager manager);
	
	/**
	 * 获取唯一对象
	 * @param manager
	 * @return
	 */
	Manager findManagerTwo(Manager manager);
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	int deleteManager(List<String> ids);
	Manager selectByPrimaryKey(Integer id);
	//验证邀请码
    int selectCountByIdKey(String idkey);
  //导出
    public void exportExcel(List<Manager> systemlog,String [] titles,ServletOutputStream outputStream);
  //获取全部
    public List<Manager> getAll();
    //根据id获取全部
    public List<Manager> getAll(List<String> expids);
    
    /**
     * 获取用户的权限
     * @return
     */
    List<PremissionItem> getPremission(String username);
}
