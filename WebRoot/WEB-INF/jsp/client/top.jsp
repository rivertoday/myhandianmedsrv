<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<div class="top">
    	<div class="left">
        	<a href="/index.html"><img src="/client/images/logo01.png" alt="" /></a>
            <span><img src="/client/images/about02.png" alt="" /></span>
        </div>
        <div style="float:right;">
        	<c:if test="${!empty sessionScope.clientuser }">
        		<div style="float:left;padding-right: 10px;" >${userDetail.nickNameStr },<a style="color: black;" href="/logout.html">退出</a></div>
        		<div style="float:left;padding-right: 10px; padding-top: 29px;" ><a href="/user/detail.html"><img src="/client/images/usercenter.png" /></a></div>
        	</c:if>
        	<div class="right">010-56903361</div>
        </div>
    </div>
