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
<title>消息详情</title>
<link rel="stylesheet" type="text/css" href="/phoneweb/css/f_base.css"/>
<link rel="stylesheet" type="text/css" href="/phoneweb/css/Ymobilezoom.css"/>
<script type="text/javascript" src="/phoneweb/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/phoneweb/js/Ymobilezoom.js"></script>
<script type="text/javascript" src="/phoneweb/js/js.js"></script>
<%@ include file="../css.jsp" %>
<script>
$(function(){
	
		$(".f_on_01 span").click(function(){
			
            $(this).parents(".f_pl_one").children(".f_hide").toggle();
		})

			})


</script>

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
</head>
<body>
<header>
<div class="top2 fixedHeader">
	<div class="f_head_lf"><a href="/phoneweb/user/usercenter.html"><img src="/phoneweb/images/f_head01.png" /></a></div>
    <div class="f_head_ct">消息详情</div>
  </div> 
</header>
<div class="f_xiangqing1">
	<div class="f_xq">${userMessage.title }</div>
    <div class="f_data"><fmt:formatDate value="${userMessage.operateTime }" pattern="yyyy-MM-dd HH:mm:ss" /></div>
    <c:if test="${!empty userMessage.image }">
		<img src="${userMessage.image }" />
	</c:if>
    <p>${userMessage.content }</p>
    <p>
							<c:if test="${!empty nextUserMessage }">
                    			<a href="/phoneweb/user/message_detail.html?id=${nextUserMessage.id }">下一消息：${nextUserMessage.title }</a>
                    		</c:if>
                    		<c:if test="${empty nextUserMessage }">
                    			<a href="javascript:;">下一消息：没有了</a>
                    		</c:if>    	
    </p>	
   
</div>
<%@ include file="../bottom.jsp" %>

</body>
</html>