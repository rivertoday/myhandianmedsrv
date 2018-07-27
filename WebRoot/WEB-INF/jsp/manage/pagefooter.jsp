<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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