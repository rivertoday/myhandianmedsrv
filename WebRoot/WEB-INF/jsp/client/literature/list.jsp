<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>万方文献</title>
<%@ include file="../css.jsp" %>
<script>
	$(function() {
		$(".f_right_top dl a").click(function(){
			var title = $(this).parents("dl").find("dt a").html();
			$("#title").val(title);
			$("#myform").attr("action", "/literature/list.html").submit();
		});
	});
	
	function sea() {
		$("#myform").attr("action", "/literature/list.html").submit();
	}
</script>
</head>

<body>
<!--头部-->
	<c:import url="/top.html"></c:import>
	
 <!--***-->
 <!--关于我们内容-->
    <div class="topbt clearfix"></div>
    <div class="content clearfix">
    <!--左边-->
    	<c:import url="/left.html?types=2"></c:import>
        <!--***-->
        <!--右边-->
        <div class="right clearfix">
        	<div class="f_right_top">
            	<dl <c:if test="${page.params.title eq '外科学' }">class="f_active"</c:if>>
                	<dd><a href="javascript:;"><img src="/client/images/f_wdwx03.png" /></a></dd>
                    <dt><a href="javascript:;">外科学</a></dt>
                </dl>
                <dl <c:if test="${page.params.title eq '临床医学' }">class="f_active"</c:if>>
                	<dd><a href="javascript:;"><img src="/client/images/f_wdwx04.png" /></a></dd>
                    <dt><a href="javascript:;">临床医学</a></dt>
                </dl>
                <dl <c:if test="${page.params.title eq '基础医学' }">class="f_active"</c:if>>
                	<dd><a href="javascript:;"><img src="/client/images/f_wdwx05.png" /></a></dd>
                    <dt><a href="javascript:;">基础医学</a></dt>
                </dl>
                <dl <c:if test="${page.params.title eq '预防医学' }">class="f_active"</c:if>>
                	<dd><a href="javascript:;"><img src="/client/images/f_wdwx06.png" /></a></dd>
                    <dt><a href="javascript:;">预防医学</a></dt>
                </dl>
                <dl <c:if test="${page.params.title eq '卫生学' }">class="f_active"</c:if>>
                	<dd><a href="javascript:;"><img src="/client/images/f_wdwx07.png" /></a></dd>
                    <dt><a href="javascript:;">卫生学</a></dt>
                </dl>
                <dl <c:if test="${page.params.title eq '药学' }">class="f_active"</c:if>>
                	<dd><a href="javascript:;"><img src="/client/images/f_wdwx08.png" /></a></dd>
                    <dt><a href="javascript:;">药学</a></dt>
                </dl>
                <dl <c:if test="${page.params.title eq '中国医学' }">class="f_active"</c:if>>
                	<dd><a href="javascript:;"><img src="/client/images/f_wdwx09.png" /></a></dd>
                    <dt><a href="javascript:;">中国医学</a></dt>
                </dl>
                <dl <c:if test="${page.params.title eq '特种医学' }">class="f_active"</c:if>>
                	<dd><a href="javascript:;"><img src="/client/images/f_wdwx10.png" /></a></dd>
                    <dt><a href="javascript:;">特种医学</a></dt>
                </dl>
            </div>
            <div class="f_sousuo">
            	<div class="f_jiansuo">
            	<span style="float:left; padding-left:30px;">检索到${page.totalRecord }篇相关文献</span>
            	<form style="position: relative;"id="myform" method="post">
	            	<input style="${!empty page.params.title ? 'color: black;' : '' }" type="text" name="title" id="title" placeholder="请输入文献关键词" value="${!empty page.params.title ? page.params.title : '' }" />
            		<a style="display: inline-block;position: absolute;right: 102px; top: 4px;" href="javascript:;" onclick="sea();"><img src="/client/images/f_wdwx11.png"/></a>
            		<a href="/literature/searchUI.html">高级检索&gt;</a>
            	</form>
            	</div>
                
            </div>
            <div class="f_list_all">
            	<c:if test="${!empty page.results }">
                	<c:forEach items="${page.results }" var="rs">
                		<div class="f_lt_one">
		                	<p class="f_p1"><a href="/literature/detail.html?articleId=${rs.articleId }&typ=${rs.types }" style="font-weight: 600;">${rs.title }</a></p>
		                    <p class="f_p2"><span style=" display:inline; color:#333333; font-size:16px;">期刊：</span>${rs.source }&nbsp;&nbsp;${rs.year }</p>
							<div class="f_zuozhe3">
		                    	<p>作者：${rs.creator }</p>
		                    	<%--
		                        <p>作者单位：${rs.organization }<span style=" display:inline; float:right; color:#666;">时间：${rs.year }</span></p>
		                    	 --%>
		                    </div>
		                </div>
                	</c:forEach>
                </c:if>
            </div>
            <%--
            <div class="f_yellow"><img src="/client/images/f_wdwx12.png" /></div>
             --%>
             <form id="pageform" action="" method="post">
	    		<input type="hidden" name="pageNo" id="pageNo" value="${page.pageNo }" />
	    		<input type="hidden" name="title" id="title" value="${page.params.title }" />
	             <script>
					function pageSub(p) {
						//$(this).css({"color":"#e71627","border-color":"#E71627"});
						$("#pageNo").val(p);
		    			$("#pageform").attr("action", "/literature/list.html").submit();
					}
					
					function pageSub1() {
						var valPageNo=$("#valPageNo").val();
						var totalPage='${page.totalPage}';
						if(valPageNo!="" && totalPage!=""){
							//alert(Number(valPageNo)<=Number(totalPage))
							if(Number(valPageNo)<=Number(totalPage)){
								$("#pageNo").val(valPageNo);
				    			$("#pageform").attr("action", "/literature/list.html").submit();
			    			}else{
			    				alert("无此页");
			    			}
		    			}
					}
				</script>
             	<%@ include file="../page.jsp" %>
             </form>
        </div>
        <!--***-->
    </div>
    <!--***-->
    
    <!--底部-->
    <%@ include file="../bottom.jsp" %>
    <!--***-->
</body>
</html>