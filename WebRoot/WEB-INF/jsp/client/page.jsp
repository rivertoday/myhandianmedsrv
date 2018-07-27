<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <%-- <div class="f_fenye_bu">
	<a style="margin-right:20px; color:#999;" href="javascript:;" <c:if test="${page.pageNo > 1 }">onclick="pageSub('${page.pageNo - 1}');"</c:if>>上一页</a>
	<a style="color:#999;" href="javascript:;" <c:if test="${page.pageNo < page.totalPage }">onclick="pageSub(${page.pageNo + 1});"</c:if>>下一页</a>
</div>  --%>

 <%-- <c:if test="${page.pageNo ge 1 }">
		            <a title="#" href="javascript:void(0);" onclick="funPage(1)">首页</a>
		            <c:if test="${page.pageNo gt 1 }">
		            <a title="#" href="javascript:void(0);" onclick="funPage('${page.pageNo - 1}')">上一页</a>
		            </c:if>
		            <c:choose>
		            <c:when test="${page.totalPage gt  3 and page.totalPage ge page.pageNo + 4}">
		            <a title="#" href="javascript:void(0);" class="curent" onclick="funPage('${page.pageNo}')">${page.pageNo}</a>
		            <a title="#" href="javascript:void(0);" onclick="funPage('${page.pageNo +  1 }')">${page.pageNo +  1 }</a>
		            <a title="#" href="javascript:void(0);" onclick="funPage('${page.pageNo +  2 }')">${page.pageNo +  2 }</a>
		            <a title="#" href="javascript:void(0);" class="dian">……</a>
		            </c:when>
		            <c:when test="${page.totalPage gt 2 and page.totalPage ge page.pageNo + 3}">
		            <a title="#" href="javascript:void(0);" class="curent" onclick="funPage('${page.pageNo}')">${page.pageNo}</a>
		            <a title="#" href="javascript:void(0);" onclick="funPage('${page.pageNo +  1 }')">${page.pageNo +  1 }</a>
		            <a title="#" href="javascript:void(0);" onclick="funPage('${page.pageNo +  2 }')">${page.pageNo +  2 }</a>
		            </c:when>
		            
		            <c:when test="${page.totalPage gt 1 and page.totalPage ge page.pageNo + 2}">
		            <a title="#" href="javascript:void(0);" class="curent" onclick="funPage('${page.pageNo}')">${page.pageNo}</a>
		            <a title="#" href="javascript:void(0);" onclick="funPage('${page.pageNo +  1 }')">${page.pageNo +  1 }</a>
		            </c:when>
		            
		            <c:when test="${page.totalPage gt  0 and page.totalPage ge page.pageNo + 1}">
		            <a title="#" href="javascript:void(0);" class="curent" onclick="funPage('${page.pageNo}')">${page.pageNo}</a>
		            </c:when>
		            
		            </c:choose>
		            <c:if test="${page.totalPage gt 0}">
		            <a title="#" href="javascript:void(0);" onclick="funPage('${page.totalPage}')">${page.totalPage }</a>
		            </c:if>
		            <c:if test="${page.totalPage gt page.pageNo}">
		            <a title="#" href="javascript:void(0);" onclick="funPage('${page.pageNo + 1}')">下一页</a>
		            </c:if>
		            <c:if test="${page.totalPage gt 0}">
		            <a title="#" href="javascript:void(0);" onclick="funPage('${page.totalPage}')">末页</a>
		            </c:if>
		            <c:if test="${page.totalPage eq 0}">
		            <a title="#" href="javascript:void(0);" onclick="funPage(1)">末页</a>
		            </c:if>
	            </c:if> --%>
	
					<c:if test="${page.totalPage ge 1 }">
						<div class="la_fyUl la_w">
							<a href="javascript:;"  <c:if test="${page.pageNo > 1 }">onclick="pageSub('${page.pageNo - 1}');"</c:if>>上一页</a>
							<c:choose>
					            <c:when test="${page.totalPage gt  3 and page.totalPage ge page.pageNo + 4}">
					            <a href="javascript:void(0);" class="curent" onclick="pageSub('${page.pageNo}')">${page.pageNo}</a>
					            <a href="javascript:void(0);" onclick="pageSub('${page.pageNo +  1 }')">${page.pageNo +  1 }</a>
					            <a href="javascript:void(0);" onclick="pageSub('${page.pageNo +  2 }')">${page.pageNo +  2 }</a>
					            <span>...</span>
					            </c:when>
					            <c:when test="${page.totalPage gt 2 and page.totalPage ge page.pageNo + 3}">
					            <a href="javascript:void(0);" class="curent" onclick="pageSub('${page.pageNo}')">${page.pageNo}</a>
					            <a href="javascript:void(0);" onclick="pageSub('${page.pageNo +  1 }')">${page.pageNo +  1 }</a>
					            <a href="javascript:void(0);" onclick="pageSub('${page.pageNo +  2 }')">${page.pageNo +  2 }</a>
					            </c:when>
					            
					            <c:when test="${page.totalPage gt 1 and page.totalPage ge page.pageNo + 2}">
					            <a  href="javascript:void(0);" class="curent" onclick="pageSub('${page.pageNo}')">${page.pageNo}</a>
					            <a  href="javascript:void(0);" onclick="pageSub('${page.pageNo +  1 }')">${page.pageNo +  1 }</a>
					            </c:when>
					            
					            <c:when test="${page.totalPage gt  0 and page.totalPage ge page.pageNo + 1}">
					            <a  href="javascript:void(0);" class="curent" onclick="pageSub('${page.pageNo}')">${page.pageNo}</a>
					            </c:when>
				            
				            </c:choose>
							<a href="javascript:;" onclick="pageSub('${page.totalPage}')">${page.totalPage }</a>
							<a href="javascript:;" <c:if test="${page.pageNo < page.totalPage }">onclick="pageSub(${page.pageNo + 1});"</c:if>>下一页</a>
							<span>共${page.totalPage }页</span>
							<span class="la_fySpan">到第</span>
							<input type="text" id="valPageNo" value="${page.pageNo }"  placeholder="1" class="la_fyTxt" />
							<span>页</span>
							<a href="javascript:;" onclick="pageSub1()">确定</a>
						</div>	
						
						</c:if>

<style>
.la_fyUl{padding:34px 0 34px 0; overflow:hidden; font-size:14px; color:#646464; text-align:right;}
.la_fyUl a{ padding:7px 14px; border:1px solid #ccc; display:inline-block; color:#666; margin:0 3px;}
.la_fyUl a span{display:inline-block; margin:0 3px;}
.la_fyTxt{ height:36px; text-align:center; font-size:14px; color:#666; background:none; border:1px solid #ccc;}
.la_fySpan{margin-left:20px;}
.la_fyUl a:hover{color:#e71627; border-color:#E71627;}
</style>
<script type="text/javascript">
$(function(){
	var valPageNo=$("#valPageNo").val();
	$(".la_fyUl a").each(function(){
		if($(this).text()==valPageNo){
			$(this).css({"color":"#e71627","border-color":"#E71627"});
		}
	})
})
</script>
	
	
	
	