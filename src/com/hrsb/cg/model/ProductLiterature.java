package com.hrsb.cg.model;

import java.util.Date;
import com.hrsb.cg.util.Tools;

public class ProductLiterature {
    private Long id;

    private Long productId;

    private String title;

    private String image;

    private String author;

    private String downloadName;

    private String downloadUrl;
    
    private Integer readCount;//added by JIANG at 20170814

    private Integer managerId;

    private Date operateTime;

    private String spare1;

    private String spare2;

    private String spare3;

    private Byte state;
    
    private String issue;// 期刊号
    
    private Date publishTime;// 发布日期

    private String summary;
    
    private int isDownload;// 是否下载  1是0否
    
    private int isCollection;// 是否收藏 1是0否
    
    private String operateTimeStr;//临时字段 用户手机站异步刷新

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getDownloadName() {
        return downloadName;
    }

    public void setDownloadName(String downloadName) {
        this.downloadName = downloadName == null ? null : downloadName.trim();
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl == null ? null : downloadUrl.trim();
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

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

	public int getIsDownload() {
		return isDownload;
	}

	public void setIsDownload(int isDownload) {
		this.isDownload = isDownload;
	}

	public int getIsCollection() {
		return isCollection;
	}

	public void setIsCollection(int isCollection) {
		this.isCollection = isCollection;
	}

	public String getOperateTimeStr() {
		if(this.operateTime!=null){
			operateTimeStr=Tools.date2Str(this.operateTime, "yyyy-MM-dd");
			
		}
		return operateTimeStr;
	}

	public void setOperateTimeStr(String operateTimeStr) {
		this.operateTimeStr = operateTimeStr;
	}

	public Integer getReadCount() {
		return readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}
	
	
    
}