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
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<meta content="telephone=no" name="format-detection" />
<meta content="false" id="twcClient" name="twcClient" />
<title>${product.title }</title>
<link rel="stylesheet" type="text/css" href="/phoneweb/css/Ymobilezoom.css"/>
<link rel="stylesheet" type="text/css" href="/phoneweb/css/f_base.css"/>
<script type="text/javascript" src="/phoneweb/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/phoneweb/js/Ymobilezoom.js"></script>
<script type="text/javascript" src="/phoneweb/js/Yeffect16_0118.min.js"></script>
<%@ include file="../css.jsp" %>
</head>
<body>
<div class="f_div">
	<%@ include file="../header.jsp" %>
   	<div class="f_zjdp">
    	<c:if test="${!empty productComments }">
	    	<c:forEach items="${productComments }" var="productComment">
	    		<div class="f_zjdp_list">
		        	<div class="f_zjdp_top">
		            	<div class="f_lt_img"><a href="javascript:void(0);"><img src="${productComment.headImg }" width="61px;" height="63px;" /></a></div>
		                <div class="f_rt_word">
		                	<p class="f_rt_p1">${productComment.userName }<span style="color:#a8a8a8; margin-left:9px; font-size:">${productComment.professional }</span><span><fmt:formatDate value="${productComment.createTime }" pattern="yyyy-MM-dd"  /></span></p>
		                    <p class="f_rt_p2"><span>${productComment.department }</span></p>
		                </div>
		            </div>
		            <div class="f_zjdp_btt">${productComment.content }</div>
<%--		            <div style="text-align:right;" class="f_img clearfix"><img src="/phoneweb/images/f_hd01.png" class="la_tu1" /><img src="/phoneweb/images/f_hd1.png" class="la_tu2"/></div>--%>
		        </div>
	    	</c:forEach>
	    </c:if>
    </div>
</div>
<%@ include file="../bottom.jsp" %>
<script>
	$(function(){
		var n = true;
			$(".f_img").click(function(){
					
					if(n== true){
						 $(this).parents('.f_zjdp_list').addClass('tableLa');
						 n = false;
						}else{
						 $(this).parents('.f_zjdp_list').removeClass('tableLa');
						  n = true;
						}
					
					//$('this').siblings('.f_zjdp_top').css('height','auto')
					//alert(123)
					//$(this).addClass('k')
					
			})
			
			
		})
</script>		
</body>
</html>