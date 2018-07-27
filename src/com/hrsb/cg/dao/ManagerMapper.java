package com.hrsb.cg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.hrsb.cg.dto.PremissionItem;
import com.hrsb.cg.model.BaseLog;
import com.hrsb.cg.model.Manager;
import com.hrsb.cg.util.Page;

public interface ManagerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Manager record);

    int insertSelective(Manager record);

    Manager selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKey(Manager record);
    /**
     * 分页
     * @param page
     * @return
     */
    List<Manager> selectByPage(Page<Manager> page);
    /**
     * 根据条件查询管理员信息
     * @param manager
     * @return
     */
    Manager findManager(Manager manager);
    //批量删除
    int deleteManager(List<String> delids);
    //验证邀请码
    @Select("select count(*) from manager where idkey = #{invitecode,jdbcType=VARCHAR}")
    @ResultType(java.lang.Integer.class)
    int selectCountByIdKey(@Param("invitecode")String invitecode);
    @Select("select * from manager")
    @ResultType(Manager.class)
    List<Manager> getAlls();
    List<Manager> getAll(List<String> expids);

    /**
     * 获取用户的权限
     * @return
     */
    List<PremissionItem> getPremission(String username);

    /**
     * 根据条件查询管理员信息
     * @param manager
     * @return
     */
	Manager findManagerTwo(Manager manager);
}