package com.hrsb.cg.util.bean;

public class MapUtil {
	private Integer PageSize=5;
	private Integer PageCount=0;
	private Object obj;
	public Integer getPageSize() {
		return PageSize;
	}
	public void setPageSize(Integer pageSize) {
		PageSize = pageSize;
	}
	public Integer getPageCount() {
		return PageCount;
	}
	public void setPageCount(Integer pageCount) {
		PageCount = pageCount;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public MapUtil(Integer pageSize, Integer pageCount, Object obj) {
		super();
		if(pageSize!=null && !"".equals(pageSize)){
			PageSize = pageSize;	
		}
		if(pageCount!=null && !"".equals(pageCount)){
			PageCount = pageCount;
		}
		this.obj = obj;
	}
}
