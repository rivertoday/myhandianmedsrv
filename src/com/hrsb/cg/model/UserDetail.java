package com.hrsb.cg.model;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class UserDetail {
    private Long id;

    private Long userId;

    private Byte way;

    private String phone;

    private String headImg;

    private String nickName;
    
    private String nickNameStr;

    private String userName;

    private String sex;

    private String province;

    private String city;

    private String country;

    private String hospitalName;

    private Integer hospitalGrade;

    private Integer departmentOne;

    private Integer departmentTwo;

    private Integer professional;

    private String forte;

    private String number;

    private String cardImg;

    private Byte status;//1未完善2审核中3已完善

    private Date createTime;

    private Byte isLoginFirst;

    private String source;
    
    private Byte downloadTypes;// 下载类型(1默认2人工) 
    
    private Integer downloadCount;// 下载总数

    private Integer managerId;

    private Date operateTime;

    private String spare1;

    private String spare2;

    private String spare3;
    
    private Byte frozen;
    
	private String hospitalGradeStr;//
	    
	private String professionalStr;
	    
	private String departmentOneStr;
	
	private String departmentTwoStr;
	
	private String district;//地区 临时字段
	
	private Integer downloadTotal;//下载总数

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Byte getWay() {
        return way;
    }

    public void setWay(Byte way) {
        this.way = way;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg == null ? null : headImg.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getNickNameStr() {
    	this.nickNameStr = StringUtils.isEmpty(nickName) ? "hd" + StringUtils.substring(phone, 0, 3) + "****" + StringUtils.substring(phone, 7) : nickName;
		return nickNameStr;
	}

	public void setNickNameStr(String nickNameStr) {
		this.nickNameStr = nickNameStr;
	}

	public String getUserName() {
        return !StringUtils.isEmpty(this.userName) ? this.userName : "";
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName == null ? null : hospitalName.trim();
    }

    public Integer getHospitalGrade() {
        return hospitalGrade;
    }

    public void setHospitalGrade(Integer hospitalGrade) {
        this.hospitalGrade = hospitalGrade;
    }

    public Integer getDepartmentOne() {
        return departmentOne;
    }

    public void setDepartmentOne(Integer departmentOne) {
        this.departmentOne = departmentOne;
    }

    public Integer getDepartmentTwo() {
        return departmentTwo;
    }

    public void setDepartmentTwo(Integer departmentTwo) {
        this.departmentTwo = departmentTwo;
    }

    public Integer getProfessional() {
        return professional;
    }

    public void setProfessional(Integer professional) {
        this.professional = professional;
    }

    public String getForte() {
        return forte;
    }

    public void setForte(String forte) {
        this.forte = forte == null ? null : forte.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getCardImg() {
        return cardImg;
    }

    public void setCardImg(String cardImg) {
        this.cardImg = cardImg == null ? null : cardImg.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Byte getIsLoginFirst() {
        return isLoginFirst;
    }

    public void setIsLoginFirst(Byte isLoginFirst) {
        this.isLoginFirst = isLoginFirst;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public Byte getDownloadTypes() {
		return downloadTypes;
	}

	public void setDownloadTypes(Byte downloadTypes) {
		this.downloadTypes = downloadTypes;
	}

	public Integer getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(Integer downloadCount) {
		this.downloadCount = downloadCount;
	}

	public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getSpare1() {
        return spare1;
    }

    public void setSpare1(String spare1) {
        this.spare1 = spare1 == null ? null : spare1.trim();
    }

    public String getSpare2() {
        return spare2;
    }

    public void setSpare2(String spare2) {
        this.spare2 = spare2 == null ? null : spare2.trim();
    }

    public String getSpare3() {
        return spare3;
    }

    public void setSpare3(String spare3) {
        this.spare3 = spare3 == null ? null : spare3.trim();
    }

	public Byte getFrozen() {
		return frozen;
	}

	public void setFrozen(Byte frozen) {
		this.frozen = frozen;
	}

	public String getHospitalGradeStr() {
		return hospitalGradeStr;
	}

	public void setHospitalGradeStr(String hospitalGradeStr) {
		this.hospitalGradeStr = hospitalGradeStr;
	}

	public String getProfessionalStr() {
		return professionalStr;
	}

	public void setProfessionalStr(String professionalStr) {
		this.professionalStr = professionalStr;
	}

	public String getDepartmentOneStr() {
		return departmentOneStr;
	}

	public void setDepartmentOneStr(String departmentOneStr) {
		this.departmentOneStr = departmentOneStr;
	}

	public String getDepartmentTwoStr() {
		return departmentTwoStr;
	}

	public void setDepartmentTwoStr(String departmentTwoStr) {
		this.departmentTwoStr = departmentTwoStr;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Integer getDownloadTotal() {
		return downloadTotal;
	}

	public void setDownloadTotal(Integer downloadTotal) {
		this.downloadTotal = downloadTotal;
	}
    
    
}