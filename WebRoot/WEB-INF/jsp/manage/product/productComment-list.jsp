<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="/manageProduct/productComment.im" method="post">
        <input type="hidden" name="pageSize" value="${page.pageSize}">
        <input type="hidden" name="pageCurrent" value="${page.pageNo}">
        <input type="hidden" name="orderField" value="${page.orderField}">
        <input type="hidden" name="orderDirection" value="${page.orderDirection}">
        <input type="hidden" name="productId" value="${page.params.productId}">
        <ul class="bjui-searchBar">
            <li><button type="button" data-url="/manageProduct/productCommentSaveUI.im?productId=${page.params.productId }" data-toggle="dialog" data-mask="true" data-title="新增" data-width="800" data-height="500" class="btn-default" data-icon="edit">新增</button></li>
            <li><label>姓名：</label><input type="text" value="${page.params.userName }" name="userName" class="form-control" size="10" /></li>
            <li><button type="submit" class="btn-default" data-icon="search">查询</button></li>
        </ul>
    </form>
</div>
<div class="bjui-pageContent">
    <table class="table table-bordered table-hover table-striped table-top" data-layout-h="0">
        <thead>
            <tr class="headtr">
                <th>医师头像</th>
            	<th>医师姓名</th>
                <th>职称</th>
                <th>医院</th>
                <th>科室</th>
                <th width="580">点评内容</th>
                <th>状态</th>
               	<th>点评时间</th>
                <!-- <th width="26"><input type="checkbox" class="checkboxCtrl" data-group="ids" data-toggle="icheck"></th> -->
                <th width="100">操作</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="rs" items="${page.results }" varStatus="i">
	            <tr class="bodytr">
	           		 <td><img alt="" src="${rs.headImg}" width="50" height="30"> </td>
	            	<td>${rs.userName }</td>
	            	<td>${rs.professional }</td>
	                <td>${rs.hospitalName}</td>
	                <td>${rs.department }</td>
	                <td>${rs.content }</td>
	                <td>${rs.state eq 1 ?"显示":"不显示" }</td>
	                <td><fmt:formatDate value="${rs.createTime }" pattern="yyyy-MM-dd"/> </td>
	                <%-- <td><input type="checkbox" name="ids" data-toggle="icheck" value="${us.id }"></td> --%>
	                <td width="185">
	                <a href="/manageProduct/productCommentSaveUI.im?productCommentId=${rs.id}" class="btn btn-green" data-toggle="dialog" data-mask="true" data-title="新增" data-width="800" data-height="500" data-icon="edit">编辑</a>
					<a href="/manageProduct/updProductCommentState.im?productCommentId=${rs.id}" class="btn btn-green" data-confirm-msg="确定要【${rs.state eq 1 ?'不显示':'显示' }】吗" data-toggle="doajax">${rs.state eq 1 ?'不显示':'显示' }</a>
	                	<c:if test="${!empty sessionManagerRole[342] }">
	                		<a href="/manageProduct/commentDel.im?id=${rs.id }" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要删除吗？">
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
