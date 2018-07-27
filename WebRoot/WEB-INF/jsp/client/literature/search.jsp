<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>检索</title>
<%@ include file="../css.jsp" %>
<script>
	$(function() {
		$(".f_right_top dl a").click(function(){
			var title = $(this).parents("dl").find("dt a").html();
			$("#title").val(title);
			$("#myform").attr("action", "/literature/list_search.html").submit();
		});
		
		$("#tij").click(function() {
			$("#myform").attr("action", "/literature/list_search.html").submit();
		});
		
		
	});
	
	
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
        <div class="right clearfix laH" style="padding-bottom:68px" >
        	<div class="f_right_top" >
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
                <dl <c:if test="${page.params.title eq '中医' }">class="f_active"</c:if>>
                	<dd><a href="javascript:;"><img src="/client/images/f_wdwx09.png" /></a></dd>
                    <dt><a href="javascript:;">中医</a></dt>
                </dl>
                <dl <c:if test="${page.params.title eq '特种医学' }">class="f_active"</c:if>>
                	<dd><a href="javascript:;"><img src="/client/images/f_wdwx10.png" /></a></dd>
                    <dt><a href="javascript:;">特种医学</a></dt>
                </dl>
            </div>
            <%--
            <div class="f_sousuo">
            	<input type="text" name="" value="请输入文献关键词" />
                <a href="#2">高级检索&gt;</a>
            </div>
             --%>
            <div class="f_gaojjs clearfix">
            	<form id="myform" method="post">
                	<span>标题中包含：</span><input type="text" name="title" id="title" value="${page.params.title }" />
                    <span>作者中包含：</span><input style=" margin-right:0px;" type="text" name="creator" value="${page.params.creator }" />
                    <span>关键词中包含：</span><input type="text" name="keywords" value="${page.params.keywords }" />
                    <span>摘要中包含：</span><input style=" margin-right:0px;" type="text" name="abstracts" value="${page.params.abstracts }" />
                    <span style="margin-top:0;">出版日期：</span>
                    <div>
					<select name="yearSmall" id="yearSmall" style="width: 86px;"  class="yselect xuanxiang1" >
                        	<c:forEach items="${year2 }" var="y2">
		                		<option <c:if test="${page.params.yearSmall1 eq y2 }">selected</c:if>>${y2 }</option>
		                	</c:forEach>
                        </select>
						<select name="monthSmall" id="monthSmall" style="width: 86px;"  class="yselect xuanxiang1" >
                        	<c:forEach items="${month }" var="m">
		                		<option <c:if test="${page.params.monthSmall eq m }">selected</c:if>>${m }</option>
		                	</c:forEach>
                        </select>
					<!--<input style="width: 130px;" type="text" name="yearSmall" id="yearSmall" class="tcal" value="${page.params.yearSmall }" />--></div>
                    <span style="width:20px; padding-top:3px; color:#cccccc;  padding: 0 2px; left: -4px; position: relative; top: -4px;">__</span>
                    <div>
					<select name="yearBig" id="yearBig" style="width: 86px;"  class="yselect xuanxiang1" >
                        	<c:forEach items="${year }" var="y">
		                		<option <c:if test="${page.params.yearBig1 eq y}">selected</c:if>>${y }</option>
		                	</c:forEach>
                        </select>
						<select name="monthBig" id="monthBig" style="width: 86px;"  class="yselect xuanxiang1" >
                        	<c:forEach items="${month }" var="m">
		                		<option <c:if test="${page.params.monthBig eq m }">selected</c:if>>${m }</option>
		                	</c:forEach>
                        </select>
					<!--<input style="width: 130px;" type="text" name="yearBig" id="yearBig" class="tcal" value="${page.params.yearBig }" />--></div>
                    <span style="font-size:12px; width:164px; color:#f7bb37; padding-top:8px;">*此日期以在线出版日期为准</span>
                    <input type="button" name="" id="tij" style="cursor: pointer;" value="开始检索" />
                </form>
            </div>
           		<c:if test="${!empty page.pageNo }">
           			<div class="la_js"> 检索到<span>${page.totalRecord }</span>篇相关文献</div> 
           		</c:if>
           
            
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
	    		<input type="hidden" name="creator" id="creator" value="${page.params.creator }" />
	    		<input type="hidden" name="keywords" id="keywords" value="${page.params.keywords }" />
	    		<input type="hidden" name="abstracts" id="abstracts" value="${page.params.abstracts }" />
	    		<input type="hidden" name="yearSmall" id="yearSmall" value="${page.params.yearSmall }" />
	    		<input type="hidden" name="yearBig" id="yearBig" value="${page.params.yearBig }" />
	             <script>
					function pageSub(p) {
						$("#pageNo").val(p);
		    			$("#pageform").attr("action", "/literature/list_search.html").submit();
					}
					function pageSub1() {
						var valPageNo=$("#valPageNo").val();
						var totalPage='${page.totalPage}';
						if(valPageNo!="" && totalPage!=""){
							//alert(Number(valPageNo)<=Number(totalPage))
							if(Number(valPageNo)<=Number(totalPage)){
								$("#pageNo").val(valPageNo);
				    			$("#pageform").attr("action", "/literature/list_search.html").submit();
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
	<style>
	.f_gaojjs .xuanxiang1{ width:86px; background: #f7f7f7 url(/client/images/f_wdwx_xiala.png) no-repeat 51px 0;  }
	.xuanxiang1 yval { width: 72px; }
    

	</style>
</body>
</html>