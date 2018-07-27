<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="/manageProduct/productList.im" method="post">
        <input type="hidden" name="pageSize" value="${page.pageSize}">
        <input type="hidden" name="pageCurrent" value="${page.pageNo}">
        <input type="hidden" name="orderField" value="${page.orderField}">
        <input type="hidden" name="orderDirection" value="${page.orderDirection}">
        <ul class="bjui-searchBar">
            <li><button type="button" data-url="/manageProduct/productSaveUI.im" data-toggle="dialog" data-mask="true" data-title="新增" data-width="800" data-height="500" class="btn-default" data-icon="edit">新增</button></li>
            <li><label>标题：</label><input type="text" value="${page.params.title }" name="title" class="form-control" size="10" /></li>
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
            	<th>图片</th>
                <th>介绍</th>
                <th>上下架</th>
                <th>访问量</th>
                <th>创建时间</th>
                <!-- <th width="26"><input type="checkbox" class="checkboxCtrl" data-group="ids" data-toggle="icheck"></th> -->
                <th width="100">操作</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="rs" items="${page.results }" varStatus="i">
	            <tr class="bodytr">
	           		 <td>${rs.title}</td>
	            	<td><img alt="" src="${rs.image }" width="50" height="30"></td>
	            	<td>${rs.introduction }</td>
	            	<td>${rs.status eq 1?"上架":"下架" }</td>
	            	<td>${rs.clickCount }</td>
	            	<td><fmt:formatDate value="${rs.createTime}" pattern="yyyy-MM-dd"/></td>
	                <td width="185">
	                <a href="/manageProduct/productSaveUI.im?productId=${rs.id }" class="btn btn-green" data-toggle="dialog" data-mask="true" data-title="编辑" data-width="800" data-height="600" data-icon="edit">编辑</a>
					<a href="/manageProduct/updStatus.im?productId=${rs.id }" class="btn btn-green" data-confirm-msg="确定要【${rs.status eq 1?'下架':'上架' }】吗" data-toggle="doajax">${rs.status eq 1?'下架':'上架' }</a>
	                <a href="/manageProduct/questionList.im?productId=${rs.id }" class="btn btn-green" data-toggle="navtab" data-mask="true" data-title="查看问题" data-id="productQuestion" data-width="800" data-height="500" data-icon="edit">常见问题</a>
	                <a href="/manageProduct/productComment.im?productId=${rs.id }" class="btn btn-green" data-toggle="navtab" data-mask="true" data-title="大师点评" data-id="productComment" data-width="800" data-height="500" data-icon="edit">大师点评</a>
	                <a href="/manageProduct/productLiteratureList.im?productId=${rs.id }" class="btn btn-green" data-toggle="navtab" data-mask="true" data-title="产品文献" data-id="productLiteratureList" data-width="800" data-height="500" data-icon="edit">产品文献</a>
	                <a href="/manageProduct/productImageUI.im?productId=${rs.id }" class="btn btn-green" data-toggle="dialog" data-mask="true" data-title="产品图片" data-width="800" data-height="600" data-icon="edit">产品图片</a>
	                <a href="/manageProduct/del.im?productId=${rs.id }" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要删除吗？">删除</a>
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
