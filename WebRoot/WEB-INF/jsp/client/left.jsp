<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<c:if test="${types == 1 }">
		<div style="height:700px;" class="left left1 clearfix">
	</c:if>
	<c:if test="${types == 2 }">
		<div style=" border-bottom:0px solid #e4e4e4;" class="left left1 clearfix">
	</c:if>	
        	<ul class="f_ul">
        		<li style="background:#cf89d7;">
        		<a href="/index.html"><img src="/client/images/f_shouye.png" alt="" />首　　页</a>
        		</li>
            	<li style="background:#f4a836;">
            	<a href="/literature/list.html" <c:if test="${type == 3 }">class="on"</c:if>>
            	<img src="/client/images/about05.png" alt="" />万方文献</a>
            	</li>
                <li style="background:#c92f2f;"><a href="/product/list.html" <c:if test="${type == 2 }">class="on"</c:if>><img src="/client/images/about04.png" alt="" />汉典产品</a></li>
                <li style="background:#56b4e5;"><a href="/subject/list.html" <c:if test="${type == 4 }">class="on"</c:if>><img src="/client/images/about06.png" alt="" />健康自测</a></li>
            </ul>
            <div class="xuxian"></div>
            <%--
		<c:if test="${types == 2 }">
            <div class="f_new">
                <div class="f_mynew">
                    <div class="f_new"><img src="/client/images/f_wdwx01.png" />我的消息</div>
                </div>
                <ul>
                	<c:if test="${!empty userMessages }">
                		<c:forEach items="${userMessages }" var="userMessage">
                			<li>
                				<img src="/client/images/f_wdwx02.png" />
                				<a class="f_act" href="/user/message_detail.html?id=${userMessage.id }">${userMessage.title }</a>
                			</li>
                		</c:forEach>
                	</c:if>
                </ul>
            </div>
        </c:if>	
         --%>    
            <p class="erweima"><img src="/client/images/about07.png" alt="" />
            	<span>汉典医学公众号</span>
            </p>
            <p class="erweima"><img src="/client/images/about_android.png" width="88px" height="90px" alt="" />
            	<span>扫描汉典医学APP</span>
            </p>
            <div class="xuxian line1"></div>
        </div>