<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageHeader">
	<fieldset>
		<c:if test="${!empty sessionManagerRole[199] }">
			<a href="/productimpression/edit.im?productid=${productid }" class="btn btn-green" data-toggle="dialog" data-mask="true " data-title="添加印象" data-width="800" data-height="600">添加印象</a>
    	</c:if>
    </fieldset>
</div>
<div class="bjui-pageContent">
    <table class="table table-bordered table-hover table-striped table-top" data-layout-h="0">
        <thead>
            <tr class="headtr">
            	<th>名称</th>
            	<th>操作人</th>
                <th>操作时间</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
         	<c:forEach var="item" items="${page.results }">
         	  <tr class="bodytr">
         		<td align="center">${item.name }</td>
         		<td align="center">${item.manager.name }</td>
         		<td align="center"><fmt:formatDate value="${item.operatdate }" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
         		<td align="center">
         			<c:if test="${!empty sessionManagerRole[199] }">
         				<a href="/productimpression/edit.im?id=${item.id }&productid=${productid }" class="btn btn-green" data-toggle="dialog" data-mask="true " data-title="修改" data-width="800" data-height="600">编辑</a>
	       			</c:if>
	       			<c:if test="${!empty sessionManagerRole[200] }">
	       				<a href="/productimpression/delete.im?id=${item.id }" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定删除吗？">删除</a>
         			</c:if>
         		</td>
	          </tr>
         	</c:forEach>
        </tbody>
    </table>
</div>

<div class="bjui-footBar">
			<div class="pages">
				<span>每页&nbsp;</span>
				<div class="selectPagesize">
					<select data-toggle="selectpicker"
						data-toggle-change="changepagesize">
						<option value="${page.pageSize }">${page.pageSize }</option>
						<option value="30">30</option>
						<option value="60">60</option>
						<option value="120">120</option>
						<option value="150">150</option>
					</select>
				</div>
				<span>&nbsp;条，共${page.totalRecord } 条</span>
			</div>
			<div class="pagination-box" data-toggle="pagination"
				data-total="${page.totalRecord}" data-page-size="${page.pageSize }"
				data-page-current="${page.pageNo }"></div>
</div>