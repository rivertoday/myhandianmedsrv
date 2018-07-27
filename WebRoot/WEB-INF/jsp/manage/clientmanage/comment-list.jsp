<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageHeader">
	<fieldset>
	    <form id="pagerForm" data-toggle="ajaxsearch" action="/clientManage/commentList.im" method="post">
	        <input type="hidden" name="pageSize" value="${page.pageSize}">
	        <input type="hidden" name="pageCurrent" value="${page.pageNo}">
			内容：<input type="text" name="content" value="${page.params.content }" size="15">
			开始时间：<input type="text" name="startDate" value="${page.params.startDate }" id="j_custom_birthday" data-toggle="datepicker" readonly="readonly" size="15">
			结束时间：<input type="text" name="endDate" value="${page.params.endDate }" id="j_custom_birthday" data-toggle="datepicker" readonly="readonly" size="15">
			<button type="submit" class="btn-default">查询</button>
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">重新获取</a>
	    </form>
    </fieldset>
<!--    <button type="button" class="btn-default" data-toggle="dialog" data-url="/enquiry/enquiryEdit.im" data-mask="true" data-title="添加广告" data-width="450"  data-icon="edit" data-height="600">添加</button> -->
</div>
<div class="bjui-pageContent">
    <table class="table table-bordered table-hover table-striped table-top" data-layout-h="0">
        <thead>
            <tr class="headtr">
            	<%--
            	<th>
					<input type="checkbox" class="checkboxCtrl" data-group="ids" data-toggle="icheck">
				</th>
				 --%>
            	<th>评论人</th>
            	<th>评论内容</th>
            	<th width="4%">满意度</th>
            	<th>图片</th>
            	<th>评论时间</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
       
         	<c:forEach var="item" items="${page.results }">
         	  <tr class="bodytr">
<%--         	  	<td align="center"><input type="checkbox" name="ids" data-toggle="icheck" value="${item.id }"></td>--%>
         		<td align="center">${item.user.phone }
         			<c:if test="${!empty item.user.phone and !empty item.user.realname }"> /</c:if>
         			${item.user.realname }
         		</td>
         		<td align="center">${item.content }</td>
         		<td align="center">${item.maiyi }颗星</td>
	            <td align="center">
	            	<c:forTokens items="${item.image }" delims="," var="img">
	            		<img src="${img }" />
	            	</c:forTokens>
	            </td>
	            <td align="center"><fmt:formatDate value="${item.createdate }" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
         		<td align="center">
       				<c:if test="${!empty sessionManagerRole[205] }">
       					<a href="/clientManage/commentDel.im?id=${item.id }" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定删除吗？">删除</a>	
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