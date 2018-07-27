<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
   <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta http-equiv="Content-Type" content="text/html">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Expires" content="-1">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Pragma" content="no-cache">
<title>健康自测</title>
<link rel="stylesheet" type="text/css" href="/phoneweb/css/f_base.css"/>
<link rel="stylesheet" href="/phoneweb/css/Ymobilezoom.css">
<script type="text/javascript" src="/phoneweb/js/js.js"></script>
<script type="text/javascript" src="/phoneweb/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="/phoneweb/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/phoneweb/js/Ymobilezoom.js"></script>
<%@ include file="../css.jsp" %>
<script>
		var isfasong = true;
		var pageNo = '${page.pageNo }';
		var totalPage = '${page.totalPage }';
		$(window).scroll(function(){ 
			if(!isfasong){
				return;	
			}
			
			var docueHt=$(document).height();
			var windhw=$(window).height();
			var sctop=$(window).scrollTop();
			
			if(sctop+windhw>docueHt-2 && pageNo < totalPage) {
				isfasong = false;
				pageNo = Number(pageNo) + 1;
				$.ajax({
					type:"post",
					url:"/phoneweb/subject/list_more.html",
					data:{
						pageNo:pageNo,
					},
					dataType:"json",
					success:function(data) {
						var content = "";
						
						for (var i=0; i<data.length; i++) {
				            	content+="<div class=\"f_zi\">";
					            content+="	<img src=\""+data[i].image+"\" />";
					            content+="	<div class=\"f_zice\">";
					            content+="		<a href=\"/phoneweb/subject/detail.html?id="+data[i].id+"\">"+data[i].title+"</a>";
					            content+="	</div>";
				            	content+="</div>";

							
						}
						$(".f_all").append(content);
						isfasong = true;
					}
				}); 
				
			}
			
		});
	</script> 
</head>
<body>
<header>
<div class="top2 fixedHeader">
	<div class="f_head_lf"><a href="javascript:history.go(-1)"><img src="/phoneweb/images/f_head01.png" /></a></div>
    <div class="f_head_ct">健康自测</div>
  </div> 
</header>

<div class="f_all">
	<c:if test="${!empty page.results }">
		<c:forEach items="${page.results }" var="rs">
		    <div class="f_zi"><img src="${rs.image }" /><div class="f_zice"><a href="/phoneweb/subject/detail.html?id=${rs.id }">${rs.title }</a></div></div>
		</c:forEach>
	</c:if>
</div>
<%--
<div class="f_shuaxin"><img src="../images/f_chanpin02.png" /></div>
 --%>
<%@ include file="../bottom.jsp" %>
<div class="masktan"></div>
<div class="tuichu clearfix">
    <p>确定取消收藏</p>
    
    <ul class="clearfix anniu">
        <li><a href="#">取消</a></li>
        <li><input type="submit" name="" value="确定"></li>
    </ul>
</div>
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
</script>
</body>
</html>