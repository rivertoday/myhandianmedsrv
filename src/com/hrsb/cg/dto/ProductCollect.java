package com.hrsb.cg.dto;


public class ProductCollect {
	 private Long id;

	 private String title;// 标题
	 
	 private String logoimg;// 渲染图

	 private Double price;// 价格
	 
	 private int commentNum;
	 
	 private int goodNum;
	 
	 private int goodRate;
	 
	 private Long collectId;

	public Long getCollectId() {
		return collectId;
	}

	public void setCollectId(Long collectId) {
		this.collectId = collectId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLogoimg() {
		return logoimg;
	}

	public void setLogoimg(String logoimg) {
		this.logoimg = logoimg;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public int getGoodNum() {
		return goodNum;
	}

	public void setGoodNum(int goodNum) {
		this.goodNum = goodNum;
	}

	public int getGoodRate() {
		if(this.getCommentNum()==0){
			return 0;
		}
		else {
			return this.getGoodNum() * 100 / this.getCommentNum();
		}
	}

	public void setGoodRate(int goodRate) {
		this.goodRate = goodRate;
	}

}
