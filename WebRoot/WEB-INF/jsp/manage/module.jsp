<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="/role/modules.im" method="post">
        <input type="hidden" name="pageSize" value="${page.pageSize}">
        <input type="hidden" name="pageNo" value="${page.pageNo}">
        <input type="hidden" name="orderField" value="${page.orderField}">
        <input type="hidden" name="orderDirection" value="${page.orderDirection}">
        <ul class="bjui-searchBar">
        	<li><button type="button" data-url="/role/gm.im" data-toggle="dialog" data-mask="true " data-title="添加部门信息" data-width="500" data-height="300" class="btn-default" data-icon="edit">添加</button></li>
            <li><label>部门名称：</label><input type="text" value="${page.params.modulename }" name="modulename" class="form-control" size="10" /></li>
            <li><button type="submit" class="btn-default" data-icon="search">查询</button></li>
            <li><a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a></li>
        	<li class="pull-right">
                <div class="btn-group">
                    <button type="button" class="btn-default dropdown-toggle" data-toggle="dropdown" data-icon="copy">批量操作<span class="caret"></span></button>
                    <ul class="dropdown-menu right" role="menu">
                        <li class="divider"></li>
                        <li><a href="/role/dm.im" data-toggle="dodelchecked" data-confirm-msg="确定要删除选中项吗？" data-idname="delids" data-group="ids">删除选中</a></li>
                    </ul>
                </div>
            </li>
        </ul>
    </form>
</div>
<div class="bjui-pageContent">
    <table class="table table-bordered table-hover table-striped table-top" data-layout-h="0">
        <thead>
            <tr class="headtr">
            	<th>部门编号</th>
                <th>部门名称</th>
                <th>上级部门</th>
                <th>状态</th>
                <th>操作人</th>
                <th>操作日期</th>
                <th width="26"><input type="checkbox" class="checkboxCtrl" data-group="ids" data-toggle="icheck"></th>
                <th width="100">操作</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="ms" items="${page.results }">
	            <tr class="bodytr">
	            	<td>${ms.modulecode }</td>
	                <td>${ms.modulename}</td>
	                <td>${ms.parentM.modulename }</td>
	                <td>${ms.status==0?"启用":"禁用" }</td>
	                <td>${ms.operatorM.name }</td>
	                <td><fmt:formatDate value="${ms.operatime }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
	                <td><input type="checkbox" name="ids" data-toggle="icheck" value="${ms.id }"></td>
	                <td width="120">
	                    <a href="/role/gm.im?mid=${ms.id }" class="btn btn-green" data-toggle="dialog" data-mask="true " data-title="修改部门信息" data-width="500" data-height="300">编辑</a>
	                    <a href="/role/dj.im?mid=${ms.id }" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要禁用该部门吗？">${ms.status eq 1?"启用":"禁用" }</a>
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
                    <option value="15">15</option>
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
