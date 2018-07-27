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
<title>汉典产品</title>
<link rel="stylesheet" type="text/css" href="/phoneweb/css/f_base.css"/>
<link rel="stylesheet" href="/phoneweb/css/Ymobilezoom.css">
<script type="text/javascript" src="/phoneweb/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/phoneweb/js/Ymobilezoom.js"></script>
<script type="text/javascript" src="/phoneweb/js/js.js"></script>
<%@ include file="../css.jsp" %>
    <style>
        body{background:#f2f2f2;}
    </style>
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
					url:"/phoneweb/product/list_more.html",
					data:{
						pageNo:pageNo,
					},
					dataType:"json",
					success:function(data) {
						var content = "";
						
						for (var i=0; i<data.length; i++) {
				            content += " <div class=\"f_chanpin clearfix\">";
						    content += "	<div class=\"f_chanpin_img\"><a href=\"/phoneweb/product/detail.html?id="+data[i].id+"\"><img width=\"139px\" height=\"104px\" src=\""+data[i].image+"\" /></a></div>";
						    content += "    <div class=\"f_chanpin_word\">";
						    content += "    	<div class=\"f_name\"><a href=\"/phoneweb/product/detail.html?id="+data[i].id+"\" style=\"color: #000;\">"+data[i].title+"</a></div>";
						    content += "        <div class=\"f_name_word\"><a href=\"/phoneweb/product/detail.html?id="+data[i].id+"\" style=\"color: #000;\">"+data[i].introduction+"</a></div>";
						    content += "    </div>";
		    				content += "</div>";
							
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
    <div  class="f_head_ct">汉典产品</div>
    </div>
   
</header>

<div style="border-bottom:1px solid #dddddd;" class="f_all">
	<c:if test="${!empty page.results }">
		<c:forEach items="${page.results }" var="rs">
			<div class="f_chanpin clearfix">
		    	<div class="f_chanpin_img"><a href="/phoneweb/product/detail.html?id=${rs.id }"><img width="139px" height="104px" src="${rs.image }" /></a></div>
		        <div class="f_chanpin_word">
		        	<div class="f_name"><a href="/phoneweb/product/detail.html?id=${rs.id }" style="color: #000;">${rs.title }</a></div>
		            <div class="f_name_word"><a href="/phoneweb/product/detail.html?id=${rs.id }" style="color: #000;">${rs.introduction }</a></div>
		            <%--
		            <div class="f_name_anniu"><a href="#2"><img src="../images/f_chanpin01.png" /></a></div>
		             --%>
		        </div>
		             
		        
		        <%--
		        <div class="f_dianji">
		        	<div class="f_dianji_word clearfix"><img src="../images/f_chengfen.png" /><span>炙黄芪、党参、炙甘草、当归、炒白术、升麻、柴胡、陈皮、生姜、大枣。</span></div>
		            <div class="f_dianji_word"><img src="../images/f_xingzhuang.png" /><span>本品为棕色的颗粒；味甜、微苦、辛。</span></div>
		        </div>
		         --%>
		    </div>
		</c:forEach>
	</c:if>
</div>
<%--<div class="f_shuaxin"><img src="../images/f_chanpin02.png" /></div>--%>
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
</body>
</html>