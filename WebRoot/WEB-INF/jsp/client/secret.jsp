<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>隐私及服务条款</title>
<%@ include file="css.jsp" %>
</head>

<body>
<!--头部-->
	<c:import url="/top.html"></c:import>
 <!--***-->
 <!--关于我们内容-->
    <div class="topbt clearfix"></div>
    <div class="content clearfix">
    <!--左边-->
    	<c:import url="/left.html?types=1"></c:import>
        <!--***-->
        <!--右边-->
        <div class="right clearfix">
            <div class="f_tk"><img src="/client/images/f_tk02.png" /></div>
            <div class="f_tk_word2">
            	${about.content }
            </div>
            
        </div>
        <!--***-->
    </div>
    <!--***-->
    
    <!--底部-->
    <%@ include file="bottom.jsp" %>
    <!--***-->
</body>
</html>