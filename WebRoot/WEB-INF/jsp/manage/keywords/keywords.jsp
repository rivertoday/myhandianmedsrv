<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="bjui-pageHeader">
    <%-- <form id="pagerForm" data-toggle="ajaxsearch" action="/member/g1.im" method="post">
        <input type="hidden" name="pageSize" value="${page.pageSize}">
        <input type="hidden" name="pageCurrent" value="${page.pageNo}">
        <input type="hidden" name="orderField" value="${page.orderField}">
        <input type="hidden" name="orderDirection" value="${page.orderDirection}">
        <ul class="bjui-searchBar">
            <li><label>登录名：</label><input type="text" value="${page.params.loginname }" name="loginname" class="form-control" size="10" /></li>
            <li><label>昵称：</label><input type="text" value="${page.params.nick }" name="nick" class="form-control" size="10" /></li>
            <li><button type="submit" class="btn-default" data-icon="search">查询</button></li>
            <li><a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a></li>
        </ul>
    </form> --%>
</div>
<div class="bjui-pageContent">
    <table class="table table-bordered table-hover table-striped table-top" data-layout-h="0">
        <thead>
            <tr class="headtr">
                <th width="300">关键词(逗号分隔)</th>
                <th width="100">操作人</th>
                <th width="100">操作时间</th>
                <th width="100">操作</th>
            </tr>
        </thead>
        <tbody>
	        <c:if test="${page.results != null }">
	        	<c:forEach var="cm" items="${page.results }">
		            <tr class="bodytr">
		            	<td>${cm.title }</td>
		                <td>${cm.operatorName }</td>
		                <td><fmt:formatDate value="${cm.operatdate }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
		                <td>
							<c:if test="${!empty sessionManagerRole[207] }"><a href="/keywords/g2.im?cmid=${cm.id }" class="btn btn-green" data-toggle="dialog" data-mask="true " data-title="修改关键词信息" data-width="600" data-height="400">编辑</a></c:if>
		                </td>
		            </tr>
		            
	            </c:forEach>
            </c:if>
        </tbody>
    </table>
    <div class="bjui-footBar">
        <div class="pages">
            <span>每页&nbsp;</span>
            <div class="selectPagesize">
                <select data-toggle="selectpicker" data-toggle-change="changepagesize">
                    <option value="${page.pageSize }">${page.pageSize }</option>
                    <option value="30">30</option>
                    <option value="60">60</option>
                    <option value="120">120</option>
                    <option value="150">150</option>
                </select>
            </div>
            <span>&nbsp;条，共${page.totalRecord } 条</span>
        </div>
        <div class="pagination-box" data-toggle="pagination" data-total="${page.totalRecord}" data-page-size="${page.pageSize }" data-page-current="${page.pageNo }">
        </div>
    </div>
</div>
