<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="/role/fr.im" method="post">
        <ul class="bjui-searchBar">
        	<c:if test="${!empty sessionManagerRole[50] }"><li><button type="button" data-url="/role/gr.im" data-toggle="dialog" data-mask="true " data-title="添加角色信息" data-width="500" data-height="300" class="btn-default" data-icon="edit">添加</button></li></c:if>
            <li><label>角色名称：</label><input type="text" value="${rolename }" name="rolename" class="form-control" size="10" /></li>
            <li><button type="submit" class="btn-default" data-icon="search">查询</button></li>
            <li><a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a></li>
        	<!-- <li class="pull-right">
                <div class="btn-group">
                    <button type="button" class="btn-default dropdown-toggle" data-toggle="dropdown" data-icon="copy">批量操作<span class="caret"></span></button>
                    <ul class="dropdown-menu right" role="menu">
                        <li class="divider"></li>
                        <li><a href="/role/delr.im" data-toggle="dodelchecked" data-confirm-msg="确定要删除选中项吗？" data-idname="ids" data-group="ids">删除选中</a></li>
                    
                     </ul>
                </div>
            </li>
             -->
        </ul>
    </form>
</div>
<div class="bjui-pageContent">
    <table class="table table-bordered table-hover table-striped table-top" data-layout-h="0">
        <thead>
            <tr class="headtr">
            	<th>角色名称</th>
                <!-- <th>所有权限</th> -->
                <th>状态</th>
                <th>操作人</th>
                <th>操作日期</th>
                <th width="26"><input type="checkbox" class="checkboxCtrl" data-group="ids" data-toggle="icheck"></th>
                <th width="100">操作</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="rs" items="${roles }">
	            <tr class="bodytr">
	            	<td>${rs.rolename }</td>
	                <!-- <td><button onclick="$(this).parents('tr').next('tr').toggle()" type="button" class="btn btn-blue"  value="查看权限">查看权限</button></td> -->
	                <td>${rs.status==0?"启用":"禁用" }</td>
	                <td>${rs.operatorM.name }</td>
	                <td><fmt:formatDate value="${rs.operatime }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
	                <td><input type="checkbox" name="ids" data-toggle="icheck" value="${rs.id }"></td>
	                <td width="120">
	                    <c:if test="${!empty sessionManagerRole[50] }"><a href="/role/gr.im?rid=${rs.id }" class="btn btn-green" data-toggle="dialog" data-mask="true " data-title="修改角色信息" data-width="500" data-height="700">编辑</a></c:if>
	                    <c:if test="${!empty sessionManagerRole[51] }"><a href="/role/dr.im?rid=${rs.id }" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要禁用该角色吗？">${rs.status eq 1?"启用":"禁用" }</a></c:if>
	                </td>
	            </tr>
	            <%-- <tr style="display:none;">
	            	<td colspan="8">
	            		<c:forEach items="${rs.pr }" var="p">
	            			<span>${p.permission.title }</span>,
	            		</c:forEach>
	            	</td>
	            </tr> --%>
            </c:forEach>
        </tbody>
    </table>
</div>
