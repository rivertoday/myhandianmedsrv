package com.hrsb.cg.service;

import java.util.List;

import com.hrsb.cg.model.ClientManage;
/**
 * 前端管理服务类
 * @author zhanglx
 *
 */
public interface ClientManageService {
	
	
	public static final String CLIENTMANAGER_TYPE_TOPBANNER = "1"; //首页上方广告图
	public static final String CLIENTMANAGER_TYPE_KEYWORDS = "2"; //搜索关键字
	public static final String CLIENTMANAGER_TYPE_MIDDLEBANNER = "3"; //banner形象图
	public static final String CLIENTMANAGER_TYPE_HONOR = "4"; //底部公司荣誉
	public static final String CLIENTMANAGER_TYPE_DOWNBANNER = "5"; //下方banner广告图
	
    public abstract  ClientManage selectByType(String type);
    public abstract boolean saveOrUpdate(ClientManage record);
    public abstract  List<ClientManage> selectAll();
    /**
     * 主键查询
     * @param id
     * @return
     */
    ClientManage selectByPrimaryKey(Integer id);
    
    /**
     * 修改用户信息
     * @param clientManage
     * @return
     */
    int updateByPrimaryKeySelective(ClientManage clientManage);
}
