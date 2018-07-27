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
<title>列表</title>
<link rel="stylesheet" type="text/css" href="/phoneweb/css/f_base.css"/>
<link rel="stylesheet" href="/phoneweb/css/Ymobilezoom.css">
<script type="text/javascript" src="/phoneweb/js/js.js"></script>
<script type="text/javascript" src="/phoneweb/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="/phoneweb/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/phoneweb/js/Ymobilezoom.js"></script>
<%@ include file="../css.jsp" %>
<style>
	body{ background:#f2f2f2;}
    .f_wx_top{position:fixed;top:0;left:0;}
	
</style>
<script>
	function sea() {
		$("#myform").attr("action", "/phoneweb/literature/list.html").submit();
	}
</script>    
</head>
<body>
<c:if test="${isexist ne 1 }">
<header style="height:0;">
	
    <div class="clearfix top2 fixedHeader">
    <div style="padding-top:19px;" class="f_head_lf"><a href="/phoneweb/literature/homepage.html"><img src="/phoneweb/images/f_head01.png" /></a></div>
        <div class="clearfix ss">
        	<form id="myform" method="post">
            	<input type="text" name="title" id="title" value="${!empty page.params.title ? page.params.title : '' }" placeholder="请输入文献关键词" class="fl sou"/>
            	<input type="button" value="" onclick="sea();" class="fl suo" />
        	</form>
        </div>
        <a href="/phoneweb/literature/searchUI.html" class="gaoji">高级检索 </a>
    </div>
   
</header>
</c:if>
<div <c:if test="${isexist ne 1 }">style="background:#fff; padding-top:120px;"</c:if>
<c:if test="${isexist eq 1 }">style="background:#fff; padding-top:0px;"</c:if>
 class="f_wxq">
	<c:if test="${isexist ne 1 }">
	<div style="top:24px;  line-height:90px; padding-top:6px; padding-bottom:4px; height:80px" class="f_painshu fixedBox">
		检索到<span>${page.totalRecord }</span>篇相关文献
	</div>
	
		<style>
		.f_painshu{ height:60px; line-height:60px; background:#f2f2f2; color:#666; text-align:center; font-size:24px;}
		.f_painshu span{ color:#F00;}
		</style>
	</c:if>
	<c:if test="${!empty page.results }">
		<c:forEach items="${page.results }" var="rs">
			<div style="padding:52px 0; border-bottom:1px solid #e1e1e1; margin:0 20px;" class="f_neirong_one">
	            <p style="margin:0;">${rs.source }<span>${rs.year }</span></p>
	            <p><a href="/phoneweb/literature/detail.html?articleId=${rs.articleId }&types=${rs.types }" style="color: #333">${rs.title }</a></p>
	            <p style=" color:#666 ;padding:0; margin:0; font-size:22px;"><a style="float:right;" href="javascript:;"><img src="/phoneweb/images/f_p.png" /></a>作者:　${rs.creator }</p>
	            
	        </div>
		</c:forEach>
	</c:if>
 </div>
 <%--
 <div class="f_shuaxin"><img src="/phoneweb/images/f_chanpin02.png" /></div>
  --%>
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
		$(function(){
        //下滑搜索框佳背景色
        $(window).scroll(function(){
            var t = $(document).scrollTop();
            if(t>0){
                $(".top1").css({"background-color":"#f6f6f6","border-bottom":"#e1e1e1"})
            }else{
                $(".top1").css("border-bottom","#e1e1e1")
            }

        })
		})
		
		
</script>

<script>
		var isfasong = true;
		var pageNo = '${page.pageNo }';
		var totalPage = '${page.totalPage }';
		var title='${page.params.title}';
		
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
					url:"/phoneweb/literature/list_more.html",
					data:{
						pageNo:pageNo,
						title:title,
					},
					dataType:"json",
					success:function(data) {
						var content = "";
						
						for (var i=0; i<data.length; i++) {
				            content += "<div style=\"padding:50px 0; border-bottom:1px solid #e1e1e1; margin:0 20px;\" class=\"f_neirong_one\">";
				            content += "<p style=\"margin:0;\">"+data[i].source+"<span>"+data[i].year+"</span></p>";
				            content += "<p><a href=\"/phoneweb/literature/detail.html?articleId="+data[i].articleId+"&types="+data[i].types+"\" style=\"color: #333\">"+data[i].title+"</a></p>";
				            content += "<p style=\" color:#666 ;padding:0; margin:0; font-size:22px;\"><a style=\"float:right;\" href=\"javascript:;\"><img src=\"/phoneweb/images/f_p.png\" /></a>作者:　"+data[i].creator+"</p>";
				            
				        	content += "</div>";
							
						}
						
						$(".f_wxq").append(content);
						isfasong = true;
					}
				}); 
				
			}
			
		});
	</script> 
<style>

</style>
</body>
</html>