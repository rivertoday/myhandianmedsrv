package com.hrsb.cg.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class DataImportTest {
	/*static ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{ "spring.xml","spring-mybatis.xml" });
	//static ProductService productService = (ProductService) ac.getBean("productService");
	public static void main(String[] args) {
		POIFSFileSystem fs = null;
		FileInputStream is = null;
		try {
			is = new FileInputStream("d:/new.xls");
			fs = new POIFSFileSystem(is);

			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);

			int count = sheet.getLastRowNum();
			for (int i = 1; i <= count; i++) {
				HSSFRow row = sheet.getRow(i);
				
				String yijifenlei = getCellStringValue(row, 0); // 一级分类
				String erjifenlei = getCellStringValue(row, 1); // 二级分类
				String sanjifenlei = getCellStringValue(row, 2); // 三级分类
				
				String title = getCellStringValue(row, 3); // 产品标题
				String fenzi = getCellStringValue(row, 4); // 分子式
				String chengfeng = getCellStringValue(row, 5); // 成分
				String guige = getCellStringValue(row, 6); // 规格
				Double jinhuojiage = getCellNumberValue(row, 7); // 进货价格
				Double qidingliang = getCellNumberValue(row, 8); // 起订量
				Double dijinliang = getCellNumberValue(row, 9);//递进单位
				Double dazongbiaozhun = getCellNumberValue(row, 10);//大宗标准
				Double xiaoshojiage1 = getCellNumberValue(row, 11);//销售价格1
				Double jiedian1 = getCellNumberValue(row, 12);//节点1（千克）
				Double xiaoshoujiage2 = getCellNumberValue(row, 13);//销售价格2
				Double jiedian2 = getCellNumberValue(row, 14);//节点2（千克）
				Double xiaoshoujiage3 = getCellNumberValue(row, 15);//销售价格3
				
				//ProductWithBLOBs productWithBLOBs = productService.selectProductByTitle(title);
				if(productWithBLOBs == null){
					Product product = new Product();
					product.setTitle(title);//标题
					product.setPrice(jinhuojiage);
					product.setGoumai(qidingliang.doubleValue());
					product.setDazong(dazongbiaozhun.doubleValue());
					product.setSuggestIncrement(dijinliang.doubleValue());
					product.setOneGrade(2);
					product.setOperatdate(new Date());
					product.setOperator(1);
					product.setQujian1(jiedian1.toString());
					product.setQujianPrice1(xiaoshojiage1);
					product.setQujian2(jiedian2.toString());
					product.setQujianPrice2(xiaoshoujiage2);
					product.setQujian3(jiedian2.toString());
					product.setQujianPrice3(xiaoshoujiage3);
					product.setIsKilogram(1);//单位千克
					productService.insertSelective(product);
					System.out.println("添加第"+i+"条成功");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String getCellStringValue(HSSFRow row, int i) {
		HSSFCell cell = row.getCell(i);
		return cell.getRichStringCellValue().getString();
	}

	public static double getCellNumberValue(HSSFRow row, int i) {
		HSSFCell cell = row.getCell(i);
		if(cell == null){
			return 0;
		}else{
			return cell.getNumericCellValue();
		}
	}*/
}
