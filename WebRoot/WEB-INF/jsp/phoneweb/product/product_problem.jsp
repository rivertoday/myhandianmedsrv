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
	<%--
   	<div class="f_cjwt">
    	<div class="f_lt_one">
                	<p class="f_p1"><a style=" color:#a40001;" href="#2">1、乙哌立松与根痛平颗粒冶疗神经根型颈椎病的疗效观察</a></p>
                    <p style="padding-left:15px;" class="f_p2">目的 观察运用乙哌立松片与根痛平颗粒对神经根型颈推病的疼痛改善率。方法 将海南医学院附属医院神经内科门诊就诊
的96例神经根型颈推病患者随机分为两组...</p>
        </div>
    </div>
     --%>
    <c:if test="${!empty productQuestions }">
    	<c:forEach items="${productQuestions }" var="productQuestion">
    		<div class="f_lt_one">
                	<p class="f_p1"><a style=" color:#a40001;" href="#2">${productQuestion.question }</a></p>
                    <p style="padding-left:15px;" class="f_p2">${productQuestion.answer }</p>
    		</div>
    	</c:forEach>
    </c:if>
</div>
<%@ include file="../bottom.jsp" %>
</body>
</html>