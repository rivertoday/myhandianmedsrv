<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="/baselog/gs2.im" method="post">
        <input type="hidden" name="pageSize" value="${page.pageSize}">
        <input type="hidden" name="pageCurrent" value="${page.pageNo}">
        <input type="hidden" name="orderField" value="${page.orderField}">
        <input type="hidden" name="orderDirection" value="${page.orderDirection}">
        <ul class="bjui-searchBar">
        	<li><label>操作时间：</label><input type="text" name="starttime" id="starttime" value="${page.params.starttime }" data-toggle="datepicker" size="15">　＿</li>
        	<li><input type="text" name="endtime" id="endtime" value="${page.params.endtime }" data-toggle="datepicker" size="15"></li>
            <li><a href="javascript:;" class="showMoreSearch" data-toggle="moresearch" data-name="custom" title="更多选项"><i class="fa fa-angle-double-down"></i></a></li>
            <li><button type="submit" class="btn-default" data-icon="search">查询</button></li>
            <li><a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a></li>
            <li class="pull-right">
                <div class="btn-group">
                    <button type="button" class="btn-default dropdown-toggle" data-toggle="dropdown" data-icon="copy">批量操作<span class="caret"></span></button>
                    <ul class="dropdown-menu right" role="menu">
                        <li><a href="/baselog/ep2.im" data-toggle="doexport" data-confirm-msg="确定要导出信息吗？">导出<span style="color: green;">全部</span></a></li>
                        <li><a href="/baselog/ep2.im" data-toggle="doexportchecked" data-confirm-msg="确定要导出选中项吗？" data-idname="expids" data-group="ids">导出<span style="color: red;">选中</span></a></li>
                    </ul>
                </div>
            </li>
        </ul>
        <div class="bjui-moreSearch">
        	<label>操作人编号：</label><input type="text" value="${page.params.operator }" name="operator" class="form-control" size="10" />
        	<label>目标编号：</label><input type="text" value="${page.params.target }" name="target" class="form-control" size="10" />
        	<label>操作行为：</label><input type="text" value="${page.params.operatype }" name="operatype" class="form-control" size="10" />
    	</div>
    </form>
</div>
<div class="bjui-pageContent">
    <table class="table table-bordered table-hover table-striped table-top" data-layout-h="0">
        <thead>
            <tr class="headtr">
            	<th>操作人编号</th>
                <th>操作时间</th>
                <th>目标编号</th>
                <th>操作状态</th>
                <th>操作行为</th>
                <th>操作内容</th>
                <th width="26"><input type="checkbox" class="checkboxCtrl" data-group="ids" data-toggle="icheck"></th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="sls" items="${page.results }">
	            <tr class="bodytr">
	            	<td>${sls.operator }</td>
	                <td><fmt:formatDate value="${sls.operatime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                <td>${sls.target }</td>
	                <td>${sls.status==0?'成功':'失败' }</td>
	                <td>${sls.opreatype}</td>
	                <td>${sls.content}</td>
	                <td><input type="checkbox" name="ids" data-toggle="icheck" value="${sls.id }"></td>
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
