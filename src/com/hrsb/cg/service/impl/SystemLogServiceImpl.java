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

import com.hrsb.cg.dao.ManagerMapper;
import com.hrsb.cg.dao.SystemLogMapper;
import com.hrsb.cg.model.SystemLog;
import com.hrsb.cg.service.SystemLogService;
import com.hrsb.cg.util.DateUtil;
import com.hrsb.cg.util.ExcelUtil;
import com.hrsb.cg.util.Page;
@Service
public class SystemLogServiceImpl implements SystemLogService {
	@Autowired
	SystemLogMapper systemLogMapper; 
	@Autowired
	ManagerMapper managerMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return systemLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SystemLog record) {
		// TODO Auto-generated method stub
		return systemLogMapper.insert(record);
	}

	@Override
	public int insertSelective(SystemLog record) {
		// TODO Auto-generated method stub
		return systemLogMapper.insertSelective(record);
	}

	@Override
	public SystemLog selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return systemLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SystemLog record) {
		// TODO Auto-generated method stub
		return systemLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SystemLog record) {
		// TODO Auto-generated method stub
		return systemLogMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<SystemLog> getAllByPage(Page<SystemLog> page) {
		// TODO Auto-generated method stub
		List<SystemLog> sl = systemLogMapper.getAllByPage(page);
		for (int i = 0; i < sl.size(); i++) {
			sl.get(i).setOperatorM(managerMapper.selectByPrimaryKey(sl.get(i).getOperator()));
		}
		return sl;
	}

	@Override
	public void exportExcel(List<SystemLog> systemlog, String[] titles,
			ServletOutputStream outputStream) {
		 	XSSFWorkbook workBook = new XSSFWorkbook();  
	        // 在workbook中添加一个sheet,对应Excel文件中的sheet  
	        XSSFSheet sheet = workBook.createSheet("系统日志");  
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
	                SystemLog sl = systemlog.get(j);  
	  
	                cell = bodyRow.createCell(0);  
	                cell.setCellStyle(bodyStyle);  
	                cell.setCellValue(sl.getTitle());  
	  
	                cell = bodyRow.createCell(1);  
	                cell.setCellStyle(bodyStyle);  
	                cell.setCellValue(sl.getContent());  
	  
	                cell = bodyRow.createCell(2);  
	                cell.setCellStyle(bodyStyle);  
	                cell.setCellValue(DateUtil.formatDate(sl.getOperatime(), "yyyy-MM-dd HH:mm:ss")); 
	               
	                cell = bodyRow.createCell(3);
	                cell.setCellStyle(bodyStyle);
	                cell.setCellValue(sl.getStatus()==0?"成功":"失败");
	                
	                cell = bodyRow.createCell(4);
	                cell.setCellStyle(bodyStyle);
	                cell.setCellValue(sl.getOperatorM().getUsername());
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
	public List<SystemLog> getAll() {
		List<SystemLog> sl = systemLogMapper.getAlls();
		for (int i = 0; i < sl.size(); i++) {
			sl.get(i).setOperatorM(managerMapper.selectByPrimaryKey(sl.get(i).getOperator()));
		}
		return sl;
	}

	@Override
	public List<SystemLog> getAll(List<String> expids) {
		List<SystemLog> sl = systemLogMapper.getAll(expids);
		for (int i = 0; i < sl.size(); i++) {
			sl.get(i).setOperatorM(managerMapper.selectByPrimaryKey(sl.get(i).getOperator()));
		}
		return sl;
	}

	
	
}
