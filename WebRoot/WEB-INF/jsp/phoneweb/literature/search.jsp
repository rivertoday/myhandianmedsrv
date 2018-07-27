<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="1 days" name="revisit-after" />
<meta content="" name="keywords" />
<meta content="" name="description" />
<meta name="viewport" content="initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no, width=device-width" />
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<meta content="telephone=no" name="format-detection" />
<meta content="false" id="twcClient" name="twcClient" />
<title>高级检索</title>
<link rel="stylesheet" type="text/css" href="/phoneweb/css/f_base.css"/>
<link rel="stylesheet" type="text/css" href="/phoneweb/css/Ymobilezoom.css"/>
<script type="text/javascript" src="/phoneweb/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/phoneweb/js/js.js"></script>
<script type="text/javascript" src="/phoneweb/js/Ymobilezoom.js"></script>
<%@ include file="../css.jsp" %>
</head>
<script>
	// 根据高度隐藏底部导航
	$(function(){
		$(window).resize(LAfn);
	})
	function LAfn(){
		var windowH = $(window).height();
		if(windowH < 500){
			
			$('.fixedFooter').hide();
		}else{
			$('.fixedFooter').show();
		}
	}
</script>
<body>
<header>
<div class="top2 fixedHeader">
	<div class="f_head_lf"><a href="javascript:history.go(-1)"><img src="/phoneweb/images/f_head01.png" /></a></div>
    <div class="f_head_ct">高级检索</div>
   </div>
</header>

<div class="f_gaojjs clearfix">
    <form id="myform" method="post" action="/phoneweb/literature/list_search.html">
        <p style="border-bottom:1px solid #e6e6e6;padding:20px 0; margin:0;"><span>标题中包含</span><input type="text" name="title" id="title" value="" /></p>
        <p style="border-bottom:1px solid #e6e6e6;padding:20px 0; margin:0;"><span>作者中包含</span><input style=" margin-right:0px;" type="text" name="creator" value="" /></p>
        <p style="border-bottom:1px solid #e6e6e6;padding:20px 0; margin:0;"><span>关键词中包含</span><input type="text" name="keywords" value="" /></p>
        <p style="border-bottom:1px solid #e6e6e6;padding:20px 0; margin:0;"><span>摘要中包含</span><input style=" margin-right:0px;" type="text" name="abstracts" value="" /></p>
<!--         <p style="padding:20px 0; margin:0; height:60px;"><span style="width:140px;">出版日期</span></p> -->
        <div>
       <%--  <input style="width: 130px;" type="text" name="yearSmall" id="yearSmall" class="tcal" value="${page.params.yearSmall }" /> --%>
        <p style="padding:20px 0; margin:0; height:60px;"><span style="width:140px;">出版日期</span>
                	<c:set value="${year }" var="y"></c:set>
                <select class="yselect xuanxiang" name="yearSmall" id="yearSmall" style="border-right:1px solid #ccc">
                	
                	<c:forEach items="${year2 }" var="y2">
                	<option>${y2 }</option>
                	</c:forEach>
                </select>
                <select class="yselect xuanxiang f_select" name="monthSmall" id="monthSmall" style="border-right:1px solid #ccc">>
                    <c:forEach items="${month }" var="m">
                	<option>${m }</option>
                	</c:forEach>
                </select>
        
        <span style="width:20px; padding-top:3px; color:#cccccc; margin-top:-11px;">____</span>
        <%-- <input style="width: 130px;" type="text" name="yearBig" id="yearBig" class="tcal" value="${page.params.yearBig }" /> --%>
        <select class="yselect xuanxiang" name="yearBig" id="yearBig" style="border-right:1px solid #ccc">>
                    <c:forEach items="${year }" var="y">
                	<option>${y }</option>
                	</c:forEach>
                </select>
                <select class="yselect xuanxiang f_select" name="monthBig" id="monthBig" style="border-right:1px solid #ccc">>
                    <c:forEach items="${month }" var="m">
                	<option>${m }</option>
                	</c:forEach>
                </select>
                </p>
        </div>
        <div style="font-size:20px; color:#f7bb37;">*此日期以在线出版日期为准</div>
        <div class="f_jiansuo"><input type="submit" name="" value="开始检索" /></div>
    </form>
</div>

<%@ include file="../bottom.jsp" %>

<script>
	$(function(){
			$(".f_sc_time a").click(function(){
				$(".masktan").show();
				$(".tuichu").show();
				})
			$(".tuichu a").click(function(){
				$(".masktan").hide();
				$(".tuichu").hide();
				})
		})
	$(function(){
			$(".f_name_anniu").click(function(){
				$(this).parents(".f_chanpin").find(".f_dianji").show();
				
				
				})
			
			
		})
		$(function(){
			$(".f_dianji").click(function(){
				$(this).parents(".f_chanpin").find(".f_dianji").hide()
				
				})
			
			
		})
</script>
<style>
	body{ padding-bottom:140px;}
	select{ font-size:22px;}
</style>
</body>
</html>