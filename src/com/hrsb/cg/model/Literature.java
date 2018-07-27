package com.hrsb.cg.model;

import java.util.Date;

public class Literature {
    private Long id;

    private String articleId;

    private String image;

    private String title;

    private String abstracts;

    private String creator;

    private String organization;

    private String source;

    private Integer year;

    private String volume;

    private String issue;

    private String keywords;

    private String page;

    private Byte hasOriginalDoc;

    private String conference;

    private String conferenceDate;

    private String conferenceLocus;

    private String convener;

    private String authorSubject;

    private String degree;

    private String teacherName;

    private String types;

    private String downloadUrl;

    private Integer downloadCount;
    
    private Integer clickCount;

    private Date createTime;

    private Integer managerId;

    private Date operateTime;

    private String spare1;

    private String spare2;

    private String spare3;

    private int isDownload;// 是否下载  1是0否
    
    private int isCollection;// 是否收藏 1是0否

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId == null ? null : articleId.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts == null ? null : abstracts.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization == null ? null : organization.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume == null ? null : volume.trim();
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue == null ? null : issue.trim();
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page == null ? null : page.trim();
    }

    public Byte getHasOriginalDoc() {
        return hasOriginalDoc;
    }

    public void setHasOriginalDoc(Byte hasOriginalDoc) {
        this.hasOriginalDoc = hasOriginalDoc;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference == null ? null : conference.trim();
    }

    public String getConferenceDate() {
        return conferenceDate;
    }

    public void setConferenceDate(String conferenceDate) {
        this.conferenceDate = conferenceDate == null ? null : conferenceDate.trim();
    }

    public String getConferenceLocus() {
        return conferenceLocus;
    }

    public void setConferenceLocus(String conferenceLocus) {
        this.conferenceLocus = conferenceLocus == null ? null : conferenceLocus.trim();
    }

    public String getConvener() {
        return convener;
    }

    public void setConvener(String convener) {
        this.convener = convener == null ? null : convener.trim();
    }

    public String getAuthorSubject() {
        return authorSubject;
    }

    public void setAuthorSubject(String authorSubject) {
        this.authorSubject = authorSubject == null ? null : authorSubject.trim();
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree == null ? null : degree.trim();
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName == null ? null : teacherName.trim();
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types == null ? null : types.trim();
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl == null ? null : downloadUrl.trim();
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

	public Integer getClickCount() {
		return clickCount;
	}

	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
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
	
}