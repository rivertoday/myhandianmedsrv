<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的消息-详细页</title>
<%@ include file="../css.jsp" %>
</head>

<body>
<!--头部-->
		<c:import url="/top.html"></c:import>
 <!--***-->
 <!--内容-->
    <div class="topbt clearfix"></div>
    <div style="border-bottom:1px solid #e4e4e4; margin-bottom:24px;"class="content clearfix">
    <!--左边-->
    	<%@ include file="user_left.jsp" %>
        <!--***-->
        <!--右边-->
        <div class="right right1 clearfix">
        	<div class="f_mynew">
            	<div class="f_mynew_tp">
                	<p class="f_pq">${userMessage.title }</p>
                    <p class="f_pq1"><fmt:formatDate value="${userMessage.operateTime }" pattern="yyyy-MM-dd" /></p>
                </div>
                <div class="f_mynew_img">
                	<c:if test="${!empty userMessage.image }">
                		<div class="f_img"><img src="${userMessage.image }" /></div>
               		</c:if>
                    <div class="f_img_word">
                    	<p>${userMessage.content }</p>
                        <p>${userMessage.detail }</p>
                    </div>
                    <div class="f_new_lj">
                    	<p>
                    		<c:if test="${!empty preUserMessage }">
                    			<a class="f_new_avtive" href="/user/message_detail.html?id=${preUserMessage.id }">上一消息：${preUserMessage.title }</a>
                    		</c:if>
                    		<c:if test="${empty preUserMessage }">
                    			<a class="f_new_avtive" href="javascript:;">上一消息：没有了</a>
                    		</c:if>
                    	</p>
                        <p>
                        	<c:if test="${!empty nextUserMessage }">
                    			<a class="f_new_avtive" href="/user/message_detail.html?id=${nextUserMessage.id }">下一消息：${nextUserMessage.title }</a>
                    		</c:if>
                    		<c:if test="${empty nextUserMessage }">
                    			<a class="f_new_avtive" href="javascript:;">下一消息：没有了</a>
                    		</c:if>
                        </p>
                    </div>
                </div>
            </div>
        </div>
        <!--***-->
    </div>
	<!--***-->
    <!--底部-->
    <%@ include file="../bottom.jsp" %>
    <!--***-->
    <script>
    	$(function(){
			$(".f_person .on").hover(function(){
				$(".f_dw").show();
				})
			
			})
    </script>
</body>
</html>