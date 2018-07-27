<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册</title>
<%@ include file="css.jsp" %>
</head>

<body>
<!--头部-->
	<c:import url="/top.html"></c:import>
 <!--***-->
 <!--关于我们内容-->
    <div class="topbt clearfix"></div>
    <div class="content clearfix">
        <div class="f_denglu clearfix">
            <div class="f_denglu_img"><img src="/client/images/f_delu.png" /></div>
            <div class="f_denglu_input">
                <div class="f_find">注册</div>
                <p><span>手机：</span><input style="background:url(../images/f_delu01.png) no-repeat 16px 8px;" type="text" name="phone" placeholder="输入您的手机号" /></p>
                <p><span>验证码：</span><input style=" text-indent:14px;width:147px;" type="text" name="code" placeholder="输入验证码" /><a href="javascript:;">重新发送（89s）</a></p>
                <p><span>密码：</span><input type="password" name="password" placeholder="请输入您的新密码" /></p>
                <p style="padding-left:66px;"> <input type="password" name="repassword" placeholder="请再次输入您的新密码" /></p>
                <div class="f_chek"><input type="checkbox" name="" value="" />我已阅读并同意遵守<a href="/agreement.html" target="_blank">《用户协议》</a></div>
                <p style="padding-left:66px;"> <input type="button" name="" value="注册" /></p>
            </div>
         </div>
    </div>
    <!--***-->
    
    <!--底部-->
    <%@ include file="bottom.jsp" %>
    <!--***-->
</body>
</html>