package com.hrsb.cg.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrsb.cg.dao.AreaCityMapper;
import com.hrsb.cg.dao.AreaCountyMapper;
import com.hrsb.cg.dao.AreaProvMapper;
import com.hrsb.cg.dao.DepartmentMapper;
import com.hrsb.cg.dao.DownloadRecordMapper;
import com.hrsb.cg.dao.HospitalGradeMapper;
import com.hrsb.cg.dao.UserDetailMapper;
import com.hrsb.cg.dao.UserLoginMapper;
import com.hrsb.cg.dao.UserMessageMapper;
import com.hrsb.cg.model.AreaCity;
import com.hrsb.cg.model.AreaCounty;
import com.hrsb.cg.model.AreaProv;
import com.hrsb.cg.model.Department;
import com.hrsb.cg.model.HospitalGrade;
import com.hrsb.cg.model.UserDetail;
import com.hrsb.cg.model.UserLogin;
import com.hrsb.cg.model.UserMessage;
import com.hrsb.cg.service.UserService;
import com.hrsb.cg.util.Const;
import com.hrsb.cg.util.ExcelUtil;
import com.hrsb.cg.util.MD5;
import com.hrsb.cg.util.Page;
@Service(value="userService")
public class UserServiceImpl implements UserService{
	@Autowired
	UserDetailMapper userDetailMapper;
	@Autowired
	AreaProvMapper areaProvMapper;
	@Autowired
	AreaCityMapper areaCityMapper;
	@Autowired
	AreaCountyMapper areaCountyMapper;
	@Autowired
	HospitalGradeMapper hospitalGradeMapper;
	@Autowired
	DepartmentMapper departmentMapper;
	@Autowired
	UserLoginMapper userLoginMapper;
	@Autowired
	UserMessageMapper userMessageMapper;
	@Autowired
	DownloadRecordMapper downloadRecordMapper;

	/**
	 * 获取最终名称
	 * @param userDetail
	 */
	public void finalName(UserDetail userDetail){
		HospitalGrade hospitalGrade=hospitalGradeMapper.selectByPrimaryKey(userDetail.getHospitalGrade());
		HospitalGrade professional=hospitalGradeMapper.selectByPrimaryKey(userDetail.getProfessional());
		if(hospitalGrade!=null){
		userDetail.setHospitalGradeStr(hospitalGrade.getName());
		}
		if(professional!=null){
			userDetail.setProfessionalStr(professional.getName());
		}
		
		Department departmentOne= departmentMapper.selectByPrimaryKey(userDetail.getDepartmentOne());
		if(departmentOne!=null){
			userDetail.setDepartmentOneStr(departmentOne.getName());
		}
		
		Department departmentTwo= departmentMapper.selectByPrimaryKey(userDetail.getDepartmentTwo());
		if(departmentTwo!=null){
			userDetail.setDepartmentTwoStr(departmentOne.getName());
		}
		
		if(StringUtils.isNotEmpty(userDetail.getCity())){
			/*AreaCity areaCity=areaCityMapper.getCityByCityCode(userDetail.getCity());
			userDetail.setCity(areaCity.getCityName());*/
		}
		if(StringUtils.isNotEmpty(userDetail.getCountry())){
			/*AreaCounty areaCounty= areaCountyMapper.getCountyByCountyCode(userDetail.getCountry());
			userDetail.setCountry(areaCounty.getCountyName());*/
		}
		
		if(StringUtils.isNotEmpty(userDetail.getProvince())){
			/*AreaProv areaProv=areaProvMapper.getByProvCode(userDetail.getProvince());
			userDetail.setProvince(areaProv.getProvName());*/
		}
	}
	
	@Override
	public List<UserDetail> selectUserDetailByPage(Page<UserDetail> page) {
		List<UserDetail> userDetails=userDetailMapper.selectUserDetailByPage(page);
		if(userDetails!=null && userDetails.size()>0){
			for(UserDetail userDetail:userDetails){
				userDetail.setDownloadTotal(downloadRecordMapper.selectCountByUserId(userDetail.getUserId()));
				finalName(userDetail);
			}
		}
		return userDetails;
	}

	@Override
	public UserDetail selectById(Long userDetailId) {
		UserDetail userDetail=userDetailMapper.selectByPrimaryKey(userDetailId);
		if(userDetail!=null){
			finalName(userDetail);
			
		}
		return userDetail;
	}

	@Override
	public int userDetailSave(String loginName, String password, UserDetail userDetail) {
		int i=0;
		if(userDetail.getId()==null){
			//新增用户
			UserLogin userLogin=new UserLogin();
			userLogin.setPhone(loginName);
			userLogin.setPassword(MD5.convertToMD5(password));//added by JIANG
			userLoginMapper.insertSelective(userLogin);
			userDetail.setUserId(userLogin.getId());
			userDetail.setSource("PC站");
			userDetail.setStatus((byte)1);
			userDetail.setCreateTime(new Date());
			i=userDetailMapper.insertSelective(userDetail);
		}else{
			//修改用户
			//added by JIANG				
			if ((password != null) && (password != "")) {
				UserLogin userLogin=new UserLogin();		
				userLogin.setPhone(loginName);
				userLogin.setPassword(MD5.convertToMD5(password));
				userLoginMapper.updateByPhoneSelective(userLogin);
			}
			
			//修改界面前端将省的代码转换成了名称，所以这里保存的时候需要重新将名称转换成代码
			AreaProv areaProv=areaProvMapper.getByProvName(userDetail.getProvince());
			userDetail.setProvince(areaProv.getProvCode());		
			
			//根据资料完善程度来修改是否支持无限下载
			if (userDetail.getStatus() == 3) {
				userDetail.setDownloadTypes(new Byte("1"));//无限下载
			}else {
				userDetail.setDownloadTypes(new Byte("0"));//无限下载
			}
			
			i=userDetailMapper.updateByPrimaryKeySelective(userDetail);
		}
		return i;
	}
	
	@Override
	public int delUser(Long userId) {
		int i = userDetailMapper.deleteByPrimaryKey(userId);
		int j = userLoginMapper.deleteByPrimaryKey(userId);
		return i&j;
	}

	/*@Override
	public boolean isExistLoginName(String loginName) {
		UserLogin userLogin=userLoginMapper.isExistLoginName(loginName);
		if(userLogin==null){
			return true;
		}
		return false;
	}*/
	//Fixed by JIANG
	public boolean isExistLoginName(String loginName) {
		UserLogin userLogin=userLoginMapper.isExistLoginName(loginName);
		if(userLogin==null){
			return false;
		}
		return true;
	}
	//Fixed end

	@Override
	public int saveUserLogin(UserLogin userLogin) {
		return userLoginMapper.insertSelective(userLogin);
	}

	@Override
	public UserLogin selectUserLoginById(Long userId) {
		
		return userLoginMapper.selectByPrimaryKey(userId);
	}

	@Override
	public List<UserDetail> getAll(List<String> list) {
		List<UserDetail> userDetails=userDetailMapper.selectByChoose(list);
		if(userDetails!=null && userDetails.size()>0){
			for(UserDetail userDetail:userDetails){
				finalName(userDetail);
			}
		}
		return userDetails;
	}

	@Override
	public List<UserDetail> getAll() {
		List<UserDetail> userDetails=userDetailMapper.selectAll();
		if(userDetails!=null && userDetails.size()>0){
			for(UserDetail userDetail:userDetails){
				finalName(userDetail);
			}
		}
		return userDetails;
	}

	@Override
	public void exportExcel(List<UserDetail> sl, String[] titles,
			ServletOutputStream servletOutputStream) {
		XSSFWorkbook xssfWorkbook=new XSSFWorkbook();
		XSSFSheet xssfSheet=xssfWorkbook.createSheet("会员列表");
		ExcelUtil excelUtil=new ExcelUtil(xssfWorkbook, xssfSheet);
		XSSFCellStyle headStyle=excelUtil.getHeadStyle();
		XSSFCellStyle bodyStyle= excelUtil.getBodyStyle();
		XSSFRow xssfRow= xssfSheet.createRow(0);
		XSSFCell xssfCell=null;
		for (int i = 0; i < titles.length; i++) {
			xssfCell=xssfRow.createCell(i);
			xssfCell.setCellStyle(headStyle);
			xssfCell.setCellValue(titles[i]);
		}
		
		if(sl!=null && sl.size()>0){
			for (int j = 0; j < sl.size(); j++) {
				UserDetail userDetail=sl.get(j);
				XSSFRow bodyRow=xssfSheet.createRow(j+1);
				xssfCell=bodyRow.createCell(0);
				xssfCell.setCellStyle(bodyStyle);
				xssfCell.setCellValue(userDetail.getPhone());
				
				xssfCell=bodyRow.createCell(1);
				xssfCell.setCellStyle(bodyStyle);
				xssfCell.setCellValue(userDetail.getNickName());
				
				xssfCell=bodyRow.createCell(2);
				xssfCell.setCellStyle(bodyStyle);
				xssfCell.setCellValue(userDetail.getUserName());
				
				
				xssfCell=bodyRow.createCell(3);
				xssfCell.setCellStyle(bodyStyle);
				xssfCell.setCellValue(userDetail.getHospitalName());
				
				xssfCell=bodyRow.createCell(4);
				xssfCell.setCellStyle(bodyStyle);
				xssfCell.setCellValue(userDetail.getProvince());
				
				xssfCell=bodyRow.createCell(5);
				xssfCell.setCellStyle(bodyStyle);
				if(userDetail.getStatus()!=null){
					if(userDetail.getStatus()==1){
						xssfCell.setCellValue("未完善");
					}else if(userDetail.getStatus()==2){
						xssfCell.setCellValue("审核中");
					}else{
						xssfCell.setCellValue("已完善");
					}
				}else{
					xssfCell.setCellValue("");
				}
				xssfCell=bodyRow.createCell(6);
				xssfCell.setCellStyle(bodyStyle);
				HospitalGrade hospitalGrade= hospitalGradeMapper.selectByPrimaryKey(userDetail.getProfessional());
				if(hospitalGrade!=null){
				xssfCell.setCellValue(hospitalGrade.getName());
				}else{
					xssfCell.setCellValue("");
				}
				xssfCell=bodyRow.createCell(7);
				xssfCell.setCellStyle(bodyStyle);
				xssfCell.setCellValue(userDetail.getSource());
				
				HospitalGrade hospitalGrade2= hospitalGradeMapper.selectByPrimaryKey(userDetail.getHospitalGrade());

				xssfCell=bodyRow.createCell(8);
				xssfCell.setCellStyle(bodyStyle);
				if(hospitalGrade2!=null){
				xssfCell.setCellValue(hospitalGrade2.getName());
				}else{
					xssfCell.setCellValue("");	
				}
				
				int row=downloadRecordMapper.selectCountByUserId(userDetail.getUserId());
				xssfCell=bodyRow.createCell(9);
				xssfCell.setCellStyle(bodyStyle);
				xssfCell.setCellValue(row);
			}
		}
		
		
		try {
			xssfWorkbook.write(servletOutputStream);
			servletOutputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				servletOutputStream.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	@Override
	public String checkPassword(String phone, String password) {
		String msg = "fail";
		UserLogin user = userLoginMapper.selectByPhoneAndPassword(phone, MD5.convertToMD5(password));
		
		if (null != user) {
			if (user.getFrozen() == 0) {
				msg = "ok";
			} else {
				msg = "frozen";
			}
		}
		
		return msg;
	}

	@Override
	public UserDetail getUserDetailByUserId(Long userId) {
		return userDetailMapper.selectByUserId(userId);
	}
	
	@Override
	public UserDetail getUserDetailByUserPhone(String phone) {
		return userDetailMapper.selectByPhone(phone);
	}

	@Override
	public List<UserMessage> getTopMessageByUserId(Long id) {
		return userMessageMapper.selectTopByUserId(id);
	}

	@Override
	public void getHeadImg(UserDetail userDetail) {
		// 判断用户头像是否为空，如果为空，判断用户是否审核通过，通过之后判断性别，否则就是未知
		if (StringUtils.isEmpty(userDetail.getHeadImg())) {
			if (userDetail.getStatus() == 3) {
				if ("男".equals(userDetail.getSex())) {
					userDetail.setHeadImg(Const.DEFAULT_M);
				} else if ("女".equals(userDetail.getSex())) {
					userDetail.setHeadImg(Const.DEFAULT_W);
				} else {
					userDetail.setHeadImg(Const.DEFAULT_N);
				}
			} else {
				userDetail.setHeadImg(Const.DEFAULT_N);
			}
		}
	}

	@Override
	public void modifyUserDetailById(UserDetail userDetail) {
		userDetailMapper.updateByPrimaryKeySelective(userDetail);
	}

	@Override
	public UserDetail IsExistPhone(String phone) {
		UserDetail userDetail= userDetailMapper.getPhone(phone);
		return userDetail;
	}
	
}
