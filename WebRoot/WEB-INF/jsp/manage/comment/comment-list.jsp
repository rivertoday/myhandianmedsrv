<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="/comment/commentList.im" method="post">
        <input type="hidden" name="pageSize" value="${page.pageSize}">
        <input type="hidden" name="pageCurrent" value="${page.pageNo}">
        <input type="hidden" name="orderField" value="${page.orderField}">
        <input type="hidden" name="orderDirection" value="${page.orderDirection}">
    </form>
</div>
<div class="bjui-pageContent">
    <table class="table table-bordered table-hover table-striped table-top"  data-layout-h="0">
        <thead>
            <tr class="headtr">
                <th width="10px">序号</th>
            	<th width="60px">手机号码</th>
                <th width="60px">昵称</th>
                <th width="180px">评论内容</th>
                <th width="60px">评论时间</th>
                <th width="60px">操作</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="comm" items="${page.results }" varStatus="i">
	            <tr class="bodytr">
	           		<td>${i.count}</td>
	            	<td>${comm.phone }</td>
	            	<td>${comm.nickName }</td>
	                <td>${comm.content}</td>
	                <td><fmt:formatDate value="${comm.createTime }" pattern="yyyy-MM-dd"/></td>
	                <td>
	                <c:if test="${!empty sessionManagerRole[309] }">
	                <a href="/comment/commentDel.im?commentId=${comm.id }" class="btn btn-red" data-confirm-msg="确定要【删除】吗？" data-toggle="doajax">删除</a>
	                </c:if>
	                </td>
	            </tr>
	            
            </c:forEach>
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
