<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="/manageInform/inform.im" method="post">
        <input type="hidden" name="pageSize" value="${page.pageSize}">
        <input type="hidden" name="pageCurrent" value="${page.pageNo}">
        <input type="hidden" name="orderField" value="${page.orderField}">
        <input type="hidden" name="orderDirection" value="${page.orderDirection}">
        <ul class="bjui-searchBar">
            <li><button type="button" data-url="/manageInform/informEditUI.im" data-toggle="dialog" data-mask="true" data-title="新增" data-width="1000" data-height="600" class="btn-default" data-icon="edit">新增</button></li>
        </ul>
    </form>
</div>
<div class="bjui-pageContent">
    <table class="table table-bordered table-hover table-striped table-top" data-layout-h="0">
        <thead>
            <tr class="headtr">
                <th>标题</th>
            	<th>内容</th>
                <th>图片</th>
                <th>详情</th>
                <th>通知类型</th>
                <th>发布时间</th>
                <!-- <th width="26"><input type="checkbox" class="checkboxCtrl" data-group="ids" data-toggle="icheck"></th> -->
                 <th width="100">操作</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="rs" items="${page.results }" varStatus="i">
	            <tr class="bodytr">
	            	<td>${rs.title }</td>
	            	<td>${rs.content }</td>
	                <td><img width="50" height="30" src="${rs.image }"></td>
	                <td>${rs.detail }</td>
	                <td>${rs.types eq 1?"系统广播消息":"活动通知消息" }</td>
	                <td><fmt:formatDate value="${rs.operateTime }" pattern="yyyy-MM-dd"/> </td>
	                <%-- <td><input type="checkbox" name="ids" data-toggle="icheck" value="${us.id }"></td> --%>
 	                <td width="185">
 	     				<a href="/manageInform/user_list.im?title=${rs.title }" class="btn btn-green" data-toggle="navtab" data-id="infouserlist">发送用户列表</a>           
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
