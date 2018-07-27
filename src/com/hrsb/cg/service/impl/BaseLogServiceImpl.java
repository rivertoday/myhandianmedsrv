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
import com.hrsb.cg.dao.BaseLogMapper;
import com.hrsb.cg.dao.ManagerMapper;
import com.hrsb.cg.model.BaseLog;
import com.hrsb.cg.model.SystemLog;
import com.hrsb.cg.service.BaseLogService;
import com.hrsb.cg.util.DateUtil;
import com.hrsb.cg.util.ExcelUtil;
import com.hrsb.cg.util.Page;
@Service("baseLogService")
public class BaseLogServiceImpl implements BaseLogService {
	@Autowired
	BaseLogMapper baseLogMapper;
	@Autowired
	ManagerMapper managerMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return baseLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(BaseLog record) {
		// TODO Auto-generated method stub
		return baseLogMapper.insert(record);
	}

	@Override
	public int insertSelective(BaseLog record) {
		// TODO Auto-generated method stub
		return baseLogMapper.insertSelective(record);
	}

	@Override
	public BaseLog selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return baseLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(BaseLog record) {
		// TODO Auto-generated method stub
		return baseLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(BaseLog record) {
		// TODO Auto-generated method stub
		return baseLogMapper.updateByPrimaryKey(record);
	}

	@Override
	@Cacheable(cacheName="baseCache")
	public List<BaseLog> selectLogsByPage(Page<BaseLog> page) {
		// TODO Auto-generated method stub
		List<BaseLog> ss = baseLogMapper.selectLogsByPage(page);
		for (int i = 0; i < ss.size(); i++) {
			ss.get(i).setOperatorM(managerMapper.selectByPrimaryKey(ss.get(i).getId()));
		}
		return ss;
	}

	@Override
	public List<BaseLog> getAll(Integer type) {
		List<BaseLog> ls = baseLogMapper.getAlls(type);
		return ls;
	}

	@Override
	public List<BaseLog> getAll(List expids) {
		List<BaseLog> ls = baseLogMapper.getAll(expids);
		return ls;
	}

	@Override
	public void exportExcel(List<BaseLog> systemlog, String[] titles,
			ServletOutputStream outputStream) {
		XSSFWorkbook workBook = new XSSFWorkbook();  
        // 在workbook中添加一个sheet,对应Excel文件中的sheet  
        XSSFSheet sheet = workBook.createSheet("操作记录");  
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
        if (systemlog != null && systemlog.size() > 0)  
        {  
            for (int j = 0; j < systemlog.size(); j++)  
            {  
                XSSFRow bodyRow = sheet.createRow(j + 1);  
                BaseLog sl = systemlog.get(j);  
  
                cell = bodyRow.createCell(0);  
                cell.setCellStyle(bodyStyle);  
                cell.setCellValue(sl.getOperator());  
  
                cell = bodyRow.createCell(1);  
                cell.setCellStyle(bodyStyle);  
                cell.setCellValue(DateUtil.formatDate(sl.getOperatime(), "yyyy-MM-dd HH:mm:ss"));  
  
                cell = bodyRow.createCell(2);  
                cell.setCellStyle(bodyStyle);  
                cell.setCellValue(sl.getTarget()); 
               
                cell = bodyRow.createCell(3);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(sl.getStatus()==0?"成功":"失败");
                
                cell = bodyRow.createCell(4);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(sl.getOpreatype());
                
                cell = bodyRow.createCell(5);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(sl.getContent());
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

}
