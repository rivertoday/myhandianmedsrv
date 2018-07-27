package com.hrsb.cg.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * 对分页的基本数据进行一个简单的封装 
 */  
public class Page<T> {  
   
    private int pageNo = 1;//页码，默认是第一页  
    private int pageSize = 15;//每页显示的记录数，默认是15  
    private int totalRecord;//总记录数  
    private int totalPage;//总页数  
    private List<? extends T> results;//对应的当前页记录  
    private Map<String, Object> params = new HashMap<String, Object>();//其他的参数我们把它分装成一个Map对象  
    private String orderField;//排序字段
    private String orderDirection;//排序方向
    public String getOrderField() {
		return orderField;
	}

	public Page<T> setOrderField(String orderField) {
		this.orderField = orderField==null||orderField.equals("orderField")?null:orderField;
		return this;
	}

	public String getOrderDirection() {
		return orderDirection;
	}

	public Page<T> setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection==null||orderDirection.equals("orderDirection")?null:orderDirection;
		return this;
	}

	public int getPageNo() {  
       return pageNo;  
    }  
   
    public Page<T> setPageNo(int pageNo) {  
       this.pageNo = pageNo;  
       return this;
    }  
   
    public int getPageSize() {  
       return pageSize;  
    }  
   
    public Page<T> setPageSize(int pageSize) {  
       this.pageSize = pageSize;  
       return this;
    }  
   
    public int getTotalRecord() {  
       return totalRecord;  
    }  
   
    public void setTotalRecord(int totalRecord) {  
       this.totalRecord = totalRecord;  
       //在设置总页数的时候计算出对应的总页数，在下面的三目运算中加法拥有更高的优先级，所以最后可以不加括号。  
       int totalPage = totalRecord%pageSize==0 ? totalRecord/pageSize : totalRecord/pageSize + 1;  
       this.setTotalPage(totalPage);  
    }  
   
    public int getTotalPage() {  
       return totalPage;  
    }  
   
    public void setTotalPage(int totalPage) {  
       this.totalPage = totalPage;  
    }  
   
    public List<? extends T> getResults() {  
       return results;  
    }  
   
    public Page<? extends T> setResults(List<? extends T> results) {  
       this.results = results;  
       return this;
    }  
    
    public Map<String, Object> getParams() {  
       return params;  
    }  
     
    public Page<T> setParams(Map<String, Object> params) {  
       this.params = params;
       return this;
    }  
   
    @Override  
    public String toString() {
       StringBuilder builder = new StringBuilder();  
       builder.append("Page [pageNo=").append(pageNo).append(", pageSize=")  
              .append(pageSize).append(", results=").append(results).append(  
                     ", totalPage=").append(totalPage).append(  
                     ", totalRecord=").append(totalRecord).append("]");  
       return builder.toString();
    }
	public Page(Integer pageNo, Integer pageSize) {
		super();
		if(pageNo!=null ){
			this.pageNo = pageNo;
		}
		if(pageSize!=null){
			this.pageSize = pageSize;
		}
	}

	public Page() {
		super();
	}
}