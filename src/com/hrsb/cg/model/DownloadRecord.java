package com.hrsb.cg.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 下载记录表
 * @author app001
 *
 */
public class DownloadRecord implements Serializable {
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long userId;// 用户id

    private Long literatureId;// 文献id

    private Byte types;// 类型(1万方文献2汉典文献)
    
    private Byte status;// 状态(1使用2删除)     
    
    private Byte isDownload;// 是否已经下载(1是0否) 

    private Date createTime;// 下载时间

    private String spare1;// 备用字段1

    private String spare2;// 备用字段2

    private String spare3;// 备用字段3
    
    private Literature literature;// 万方文献
    
    private ProductLiterature productLiterature;// 汉典文献

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

    public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Byte getIsDownload() {
		return isDownload;
	}

	public void setIsDownload(Byte isDownload) {
		this.isDownload = isDownload;
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
    
}