<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="/managesubject/option_list.im" method="post">
        <input type="hidden" name="pageSize" value="${page.pageSize}">
        <input type="hidden" name="pageCurrent" value="${page.pageNo}">
        <input type="hidden" name="orderField" value="${page.orderField}">
        <input type="hidden" name="orderDirection" value="${page.orderDirection}">
        <input type="hidden" name="subjectId" value="${subjectId }">
        <input type="hidden" name="questionId" value="${questionId }">
        <ul class="bjui-searchBar">
        	<c:if test="${!empty sessionManagerRole[328] }">
        		<li><button type="button" data-url="/managesubject/option_edit.im?subjectId=${subjectId }&questionId=${questionId }" data-toggle="dialog" data-mask="true" data-title="新增" data-width="800" data-height="500" class="btn-default" data-icon="edit">新增</button></li>
        	</c:if>
        </ul>
    </form>
</div>
<div class="bjui-pageContent">
    <table class="table table-bordered table-hover table-striped table-top" data-layout-h="0">
        <thead>
            <tr class="headtr">
                <th>名称</th>
            	<th>分数</th>
                <th>操作时间</th>
                <th width="100">操作</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="rs" items="${page.results }">
	            <tr class="bodytr">
	           		<td>${rs.optionName}</td>
	            	<td>${rs.optionScore }</td>
	            	<td><fmt:formatDate value="${rs.operateTime}" pattern="yyyy-MM-dd"/></td>
	                <td width="185">
	                	<c:if test="${!empty sessionManagerRole[328] }">
		                	<a href="/managesubject/option_edit.im?id=${rs.id }" class="btn btn-green" data-toggle="dialog" data-mask="true" data-title="编辑" data-width="800" data-height="500" data-icon="edit">编辑</a>
						</c:if>
						<c:if test="${!empty sessionManagerRole[329] }">
							<a href="/managesubject/option_del.im?id=${rs.id }" class="btn btn-red" data-confirm-msg="确定要删除吗" data-toggle="doajax">删除</a>
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
