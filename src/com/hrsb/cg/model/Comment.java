package com.hrsb.cg.model;

import java.util.Date;

public class Comment {
    private Long id;

    private Long userId;

    private Long literatureId;

    private Byte types;

    private Byte levels;// 类型(1万方文献2汉典文献)
    
    private Date createTime;

    private String spare1;

    private String spare2;

    private String spare3;

    private String content;
    
    private String nickName;//昵称
    
    private String phone;//用户手机号码
    
    private String title;//文献标题
    
    private Literature literature;// 万方文献
    
    private ProductLiterature productLiterature;// 汉典文献
    
    private UserDetail userDetail;// 评论人信息
    
    private String createTimeStr;//用户手机端 临时字段

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

    public Long getLiteratureId() {
        return literatureId;
    }

    public void setLiteratureId(Long literatureId) {
        this.literatureId = literatureId;
    }

    public Byte getTypes() {
        return types;
    }

    public void setTypes(Byte types) {
        this.types = types;
    }

    public Byte getLevels() {
		return levels;
	}

	public void setLevels(Byte levels) {
		this.levels = levels;
	}

	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Literature getLiterature() {
		return literature;
	}

	public void setLiterature(Literature literature) {
		this.literature = literature;
	}

	public ProductLiterature getProductLiterature() {
		return productLiterature;
	}

	public void setProductLiterature(ProductLiterature productLiterature) {
		this.productLiterature = productLiterature;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
    
}