package com.hrsb.cg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import com.hrsb.cg.model.Menu;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
    @Select("select * from menus")
    @ResultType(Menu.class)
    List<Menu> findMenus();
    @Select("select * from menus where parentid = #{parentid,jdbcType=INTEGER} order by createtime desc")
    @ResultMap(value="BaseResultMap")
    List<Menu> findMenusByParentid(@Param("parentid")Integer parentid);
    //批量删除
    @Delete("delete from menus where id in(${ids})")
    int deleteMoreMenu(@Param("ids")String ids);
    @Select("select * from menus where FIND_IN_SET(id,findMenuByParentId(#{ids}))")
    List<Menu> findMenusByParentidRecur(@Param("ids")String ids);
}