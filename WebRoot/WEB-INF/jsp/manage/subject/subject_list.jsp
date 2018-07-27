<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="/managesubject/subject_list.im" method="post">
        <input type="hidden" name="pageSize" value="${page.pageSize}">
        <input type="hidden" name="pageCurrent" value="${page.pageNo}">
        <input type="hidden" name="orderField" value="${page.orderField}">
        <input type="hidden" name="orderDirection" value="${page.orderDirection}">
        <ul class="bjui-searchBar">
        	<c:if test="${!empty sessionManagerRole[322] }">
        		<li><button type="button" data-url="/managesubject/subject_edit.im" data-toggle="dialog" data-mask="true" data-title="新增" data-width="800" data-height="500" class="btn-default" data-icon="edit">新增</button></li>
        	</c:if>
            <li><label>标题：</label><input type="text" value="${page.params.title }" name="title" class="form-control" size="10" /></li>
            <li>
            	<label>职称：</label>
				<select name="types" id="types" data-toggle="selectpicker">
					<option value="0">请选择</option>
 					<option value="1" <c:if test="${page.params.types == 1 }">selected="selected"</c:if>>原始8分</option>
 					<option value="2" <c:if test="${page.params.types == 2 }">selected="selected"</c:if>>原始7分</option>
 					<option value="3" <c:if test="${page.params.types == 3 }">selected="selected"</c:if>>累积分</option>
 					<option value="4" <c:if test="${page.params.types == 4 }">selected="selected"</c:if>>多选一结果</option>
 					<option value="5" <c:if test="${page.params.types == 5 }">selected="selected"</c:if>>一题一答案</option>
 				</select>
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
                <th>标题</th>
            	<th>类型</th>
                <th>操作时间</th>
                <th width="100">操作</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="rs" items="${page.results }">
	            <tr class="bodytr">
	           		<td>${rs.title}</td>
	            	<td>${rs.typesStr }</td>
	            	<td><fmt:formatDate value="${rs.operateTime}" pattern="yyyy-MM-dd"/></td>
	                <td width="185">
	                	<c:if test="${!empty sessionManagerRole[322] }">
		                	<a href="/managesubject/subject_edit.im?id=${rs.id }" class="btn btn-green" data-toggle="dialog" data-mask="true" data-title="编辑" data-width="800" data-height="500" data-icon="edit">编辑</a>
						</c:if>
	                	<c:if test="${!empty sessionManagerRole[324] }">
		                	<a href="/managesubject/question_list.im?subjectId=${rs.id }&types=${rs.types }" class="btn btn-green" data-toggle="navtab" data-id="questionlist">问题列表</a>
						</c:if>
	                	<c:if test="${!empty sessionManagerRole[330] && rs.types != 5 }">
		                	<a href="/managesubject/result_list.im?subjectId=${rs.id }" class="btn btn-green" data-toggle="navtab" data-id="resultlist">结果判定</a>
						</c:if>
						<c:if test="${!empty sessionManagerRole[323] }">
							<a href="/managesubject/subject_del.im?id=${rs.id }" class="btn btn-red" data-confirm-msg="确定要删除吗" data-toggle="doajax">删除</a>
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
