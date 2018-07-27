package com.hrsb.cg.service.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.googlecode.ehcache.annotations.When;
import com.hrsb.cg.dao.ManagerMapper;
import com.hrsb.cg.dao.ModuleMapper;
import com.hrsb.cg.dto.PremissionItem;
import com.hrsb.cg.model.BaseLog;
import com.hrsb.cg.model.Manager;
import com.hrsb.cg.service.ManagerService;
import com.hrsb.cg.util.DateUtil;
import com.hrsb.cg.util.ExcelUtil;
import com.hrsb.cg.util.Page;
@Service
public class ManagerServiceImpl implements ManagerService {
	@Autowired
	ManagerMapper managerMapper;
	@Autowired
	ModuleMapper moduleMapper;
	@TriggersRemove(cacheName="baseCache",when=When.AFTER_METHOD_INVOCATION,removeAll=true)
	public int insertSelective(Manager record) {
		// TODO Auto-generated method stub
		return managerMapper.insertSelective(record);
	}

	@Override
	@TriggersRemove(cacheName="baseCache",when=When.AFTER_METHOD_INVOCATION,removeAll=true)
	public int updateByPrimaryKeySelective(Manager record) {
		// TODO Auto-generated method stub
		return managerMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	@Cacheable(cacheName="baseCache")
	public List<Manager> selectPage(Page<Manager> page) {
		List<Manager> man =  managerMapper.selectByPage(page);
		for (int i = 0; i < man.size(); i++) {
			man.get(i).setOperatorM(managerMapper.selectByPrimaryKey(man.get(i).getOperator()));
		}
		return man;
	}

	@Override
	@Cacheable(cacheName="baseCache")
	public Manager findManager(Manager manager) {
		// TODO Auto-generated method stub
		Manager m = managerMapper.findManager(manager);
		return m;
	}
	
	@Override
	@Cacheable(cacheName="baseCache")
	public Manager findManagerTwo(Manager manager) {
		// TODO Auto-generated method stub
		Manager m = managerMapper.findManagerTwo(manager);
		return m;
	}

	@Override
	public int deleteManager(List<String> ids) {
		// TODO Auto-generated method stub
		return managerMapper.deleteManager(ids);
	}

	@Override
	public Manager selectByPrimaryKey(Integer id) {
		Manager manager =  managerMapper.selectByPrimaryKey(id);
		return manager;
	}

	@Override
	public int selectCountByIdKey(String idkey) {
		// TODO Auto-generated method stub
		return managerMapper.selectCountByIdKey(idkey);
	}

	@Override
	public void exportExcel(List<Manager> managers, String[] titles,
			ServletOutputStream outputStream) {
		XSSFWorkbook workBook = new XSSFWorkbook();  
        // 在workbook中添加一个sheet,对应Excel文件中的sheet  
        XSSFSheet sheet = workBook.createSheet("操作人员信息记录");  
        ExcelUtil exportUtil = new ExcelUtil(workBook, sheet);  
        XSSFCellStyle headStyle = exportUtil.getHeadStyle();  
        XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();  
        // 构建表头  
        XSSFRow headRow = sheet.createRow(0);  
        XSSFCell cell = null;  
        for (int i = 0; i < titles.length; i++)  
        {  
            cell = headRow.createCell(i);  
            cell.setCellStyle(headStyle);  
            cell.setCellValue(titles[i]);  
        }  
        // 构建表体数据  
        if (managers != null && managers.size() > 0)  
        {  
            for (int j = 0; j < managers.size(); j++)  
            {  
                XSSFRow bodyRow = sheet.createRow(j + 1);  
                Manager m = managers.get(j);  
  
                cell = bodyRow.createCell(0);  
                cell.setCellStyle(bodyStyle);  
                cell.setCellValue(m.getIdkey());  
  
                cell = bodyRow.createCell(1);  
                cell.setCellStyle(bodyStyle);  
                cell.setCellValue(m.getUsername());  
  
                cell = bodyRow.createCell(2);  
                cell.setCellStyle(bodyStyle);  
                cell.setCellValue(m.getName()); 
               
                cell = bodyRow.createCell(3);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(m.getPhone());
                
                cell = bodyRow.createCell(4);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(DateUtil.formatDate(m.getCreatetime(), "yyyy-MM-dd HH:mm:ss"));
                
                cell = bodyRow.createCell(5);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(DateUtil.formatDate(m.getOperatime(), "yyyy-MM-dd HH:mm:ss"));
                
                cell = bodyRow.createCell(6);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(m.getStatus().equals("0")?"启用":"禁用");
                
            }  
        }  
        try  
        {  
            workBook.write(outputStream);  
            outputStream.flush();  
            outputStream.close();  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
        finally  
        {  
            try  
            {  
                outputStream.close();  
            }  
            catch (IOException e)  
            {  
                e.printStackTrace();  
            }  
        }
	}

	@Override
	public List<Manager> getAll() {
		// TODO Auto-generated method stub
		return managerMapper.getAlls();
	}

	@Override
	public List<Manager> getAll(List expids) {
		// TODO Auto-generated method stub
		return managerMapper.getAll(expids);
	}

	@Override
	public List<PremissionItem> getPremission(String username) {
		return managerMapper.getPremission(username);
	}
	
	

}
