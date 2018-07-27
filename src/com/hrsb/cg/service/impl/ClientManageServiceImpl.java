package com.hrsb.cg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrsb.cg.dao.ClientManageMapper;
import com.hrsb.cg.dao.ManagerMapper;
import com.hrsb.cg.model.ClientManage;
import com.hrsb.cg.service.ClientManageService;
@Service
public class ClientManageServiceImpl implements ClientManageService {
	@Autowired
	private ClientManageMapper clientManageDao;
	
	@Autowired
	private ManagerMapper managerMapper;
	
	public ClientManage selectByType(String type) {
		ClientManage clientManage = clientManageDao.selectByType(type);
		if(null != clientManage.getOperator()){
			if(null != managerMapper.selectByPrimaryKey(clientManage.getOperator())){
				clientManage.setOperatorName(managerMapper.selectByPrimaryKey(clientManage.getOperator()).getName());
			}
		}
		return clientManage;
	}
	public boolean saveOrUpdate(ClientManage record) {
		boolean result = false;
		if(null == record.getId() || record.getId() <=0){
			result = clientManageDao.insertSelective(record) > 0;
		}else{
			result = clientManageDao.updateByPrimaryKeySelective(record) > 0;
		}
		return result;
	}
	public List<ClientManage> selectAll() {
		return clientManageDao.selectAll();
	}
	
	@Override
	public ClientManage selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return clientManageDao.selectByPrimaryKey(id);
	}
	@Override
	public int updateByPrimaryKeySelective(ClientManage clientManage) {
		// TODO Auto-generated method stub
		return clientManageDao.updateByPrimaryKeySelective(clientManage);
	}


}
