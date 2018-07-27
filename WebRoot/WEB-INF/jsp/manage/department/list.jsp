<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="/department/list.im" method="post">
        <input type="hidden" name="pageSize" value="${page.pageSize}">
        <input type="hidden" name="pageCurrent" value="${page.pageNo}">
        <ul class="bjui-searchBar">
        	<c:if test="${!empty sessionManagerRole[335] }">
        		<li>
        			<button type="button" data-url="/department/edit.im?parentId=0" data-toggle="dialog" data-mask="true" data-title="添加" data-width="600" data-height="400" class="btn-default" data-icon="edit">添加</button>
        		</li>
        	</c:if>
            <li>
            	<label>名称：</label>
            	<input type="text" value="${page.params.name }" name="name" class="form-control" size="10" />
            </li>
            <li><button type="submit" class="btn-default" data-icon="search">查询</button></li>
            <li><a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a></li>
        </ul>
    </form>
</div>
<div class="bjui-pageContent">
    <table class="table table-bordered table-hover table-striped table-top" data-layout-h="0">
        <thead>
            <tr class="headtr">
            	<th>名称</th>
                <th>上一级</th>
                <th>操作时间</th>
                <th width="100">操作</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="us" items="${page.results }">
	            <tr class="bodytr">
	            	<td>${us.name }</td>
	            	<td>${us.department.name }</td>
	                <td><fmt:formatDate value="${us.operateTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	                <td width="185">
	                    <c:if test="${!empty sessionManagerRole[335] }">
	                    	<a href="/department/edit.im?id=${us.id }" class="btn btn-green" data-toggle="dialog" data-mask="true " data-title="编辑" data-width="600" data-height="400">编辑</a>
	                    </c:if>
	                    <c:if test="${!empty sessionManagerRole[335] && us.parentId == 0 }">
	                    	<a href="/department/edit.im?parentId=${us.id }" class="btn btn-green" data-toggle="dialog" data-mask="true " data-title="添加子级" data-width="600" data-height="400">添加子级</a>
	                    </c:if>
	                	<c:if test="${!empty sessionManagerRole[336] }">
	                		<a href="/department/status.im?id=${us.id }&status=2" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要删除吗？">
	                			删除
	                		</a>
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