<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageContent">
	<a class="btn btn-green" data-height="400" data-width="600" data-title="添加" data-mask="true " data-toggle="dialog" href="/permission/add.im?parentid=${parentid }">添加</a>
	<form action="/permission/list.im" id="j_custom_form" class="pageForm" data-toggle="validate">
    <input type="hidden" name="mid" value="${mid }">
    <div class="pageFormContent" style="width: 100%; overflow: auto; height: 450px;">
    <table class="table table-bordered table-hover table-striped table-top" data-layout-h="0">
        <thead>
            <tr class="headtr">
            	<th>操作</th>
            	<th>权限ID</th>
            	<th>权限名称</th>
                <th>创建时间</th>
                <th>状态</th>
                <th>排序</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="ps" items="${menubodys }">
	            <tr class="bodytr">
	            	<td align="center">
	            		<a href="/permission/mod.im?id=${ps.id }" class="btn btn-green" data-toggle="dialog" data-mask="true " data-title="修改权限信息" data-width="500" data-height="400">编辑</a>
	            		<a href="/permission/del.im?id=${ps.id }" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要操作该数据吗？" data-mask="true " data-width="500" data-height="400">删除</a> 
	            	</td>
	            	<td align="center">${ps.id }</td>
	            	<td align="center">${ps.title }</td>
	            	<td align="center"><fmt:formatDate value="${ps.operatime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	            	<td align="center">${ps.status }</td>
	            	<td align="center">${ps.sort }</td>
	            </tr>
            </c:forEach>
        </tbody>
    </table>
    </div>
    </form>
</div>
