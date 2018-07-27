<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="check.jsp" %>

<link rel="stylesheet" type="text/css" href="/client/css/base.css"/>
<link rel="stylesheet" type="text/css" href="/client/css/about.css"/>
<link rel="stylesheet" type="text/css" href="/client/css/Yform.css"/>
<script type="text/javascript" src="/client/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/client/js/Yform.js"></script>
<script type="text/javascript" src="/client/js/Yeffect16_0118.min.js"></script>
<script type="text/javascript" src="/client/js/TouchSlide.1.1.js"></script>
<script type="text/javascript" src="/client/js/js.js"></script>

<script type="text/javascript" src="/client/js/shengShiXian.js"></script>
<script type="text/javascript" src="/client/js/grade.js"></script>
<link href="/client/css/uploadify.css" rel="stylesheet" type="text/css" />
<script src="/client/js/jquery.uploadify.min.js"  type="text/javascript" ></script>
<link rel="stylesheet" type="text/css" href="/client/css/tcal.css" />
<script type="text/javascript" src="/client/js/tcal.js"></script>

<script>
	$(function() {
		$(".top1 a, .lf a").click(function() {
			var user = '${sessionScope.clientuser.id}';
			
			if (user == "" || user == undefined) {
				alertS("请登录");
			} else {
				var addr = $(this).attr("addr");
				
				if (addr != "" && addr != undefined) {
					location.href = $(this).attr("addr");
				}
			}
		});
	});
</script>