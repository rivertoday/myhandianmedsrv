<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="/mm/memberList.im" method="post">
        <input type="hidden" name="pageSize" value="${page.pageSize}">
        <input type="hidden" name="pageCurrent" value="${page.pageNo}">
        <input type="hidden" name="orderField" value="${page.orderField}">
        <input type="hidden" name="orderDirection" value="${page.orderDirection}">
        <ul class="bjui-searchBar" ><!-- style="width:100%;height:80px;" -->
            <li><button type="button" data-url="/mm/memberDetail.im" data-toggle="dialog" data-mask="true" data-title="新增" data-width="800" data-height="500" class="btn-default" data-icon="edit">新增</button></li>
            <li><label>手机：</label><input type="text" value="${page.params.phone }" name="phone" class="form-control" size="5" /></li>
            <li><label>医院：</label><input type="text" value="${page.params.hospitalName }" name="hospitalName" class="form-control" size="5" /></li>
            <li><label>地区：</label>
            			<select name="province" id="province11111" size="5" data-toggle="selectpicker" data-width="60px">
                            	<option value="">请选择</option>
	                            <c:if test="${!empty areaProvs }">
		                        	<c:forEach items="${areaProvs }" var="areaProv">
		                        		<option value="${areaProv.provCode }" ${areaProv.provCode==page.params.province?'selected':'' }>${areaProv.provName }</option>
		                        	</c:forEach>
		                        </c:if>
                            </select>
            </li>
            <li><label>职称：</label>
           					<select name="professional" id="professional" data-toggle="selectpicker" data-width="60px">
                            	<option value="">请选择</option>
                            	<c:if test="${!empty hospitalProfessionalList}">
	                            	<c:forEach items="${hospitalProfessionalList }" var="professional">
	                            		<option value="${professional.id }" ${professional.id==page.params.professional?'selected':'' }>${professional.name }</option>
	                            	</c:forEach>
                            	</c:if>
                            </select>
            </li>
            <li><label>注册来源：</label>
            	<select name="source" id="source" data-toggle="selectpicker">
            		<option value="">全部</option>
            		<option value="安卓" ${page.params.source eq '安卓'?'selected':'' }>安卓</option>
            		<option value="IOS" ${page.params.source eq 'IOS'?'selected':'' }>IOS</option>
            		<option value="手机站" ${page.params.source eq '手机站'?'selected':'' }>手机站</option>
            		<option value="PC站" ${page.params.source eq 'PC站'?'selected':'' }>PC站</option>
            	</select>
            </li>
            <li><label>认证：</label>
            	<select name="status" id="status" data-toggle="selectpicker">
            		<option value="">全部</option>
            		<option value="1" ${page.params.status eq 1?'selected':'' }>未完善</option>
            		<option value="2" ${page.params.status eq 2?'selected':'' }>审核中</option>
            		<option value="3" ${page.params.status eq 3?'selected':'' }>已完善</option>
            	</select>
            </li>
            <li><button type="submit" class="btn-default" data-icon="search">查询</button></li>
            <li><a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a></li>
       		 <li class="pull-right" style="margin-right: 50px">
                <div class="btn-group">
                    <button type="button" class="btn-default dropdown-toggle" data-toggle="dropdown" data-icon="copy">导出<span class="caret"></span></button>
                    <ul class="dropdown-menu right" role="menu">
                    	 <li><a href="/mm/export.im" data-toggle="doexport" data-confirm-msg="确定要导出信息吗？">导出<span style="color: green;">全部</span></a></li>
                        <li><a href="/mm/export.im" data-toggle="doexportchecked" data-confirm-msg="确定要导出选中项吗？" data-idname="expids" data-group="ids">导出<span style="color: red;">选中</span></a></li>
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
                <th>序号</th>
            	<th>手机号码</th>
                <th>昵称</th>
                <th>下载量</th>
                <th>姓名</th>
                <th>性别</th>
               	<th>所在地区</th>
               	<th>职称</th>
                <th>正常状态</th>
                <th>医院</th>
                <th>医院等级</th>
                <th>科室</th>
                <th>注册来源</th>
                <th>万方全文阅读</th>
                <th>是否认证</th>
                <th width="26"><input type="checkbox" class="checkboxCtrl" data-group="ids" data-toggle="icheck"></th>
                <th width="100">操作</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="us" items="${page.results }" varStatus="i">
	            <tr class="bodytr">
	           		<td>${i.count}</td>
	            	<td>${us.phone }</td>
	            	<td>${us.nickName }</td>
	                <td>${us.downloadTotal }</td>
	                <td>${us.userName }</td>
	                <td>${us.sex }</td>
	                <td>
                   	<c:forEach items="${areaProvs }" var="areaProv">
                   		${areaProv.provCode==us.province?areaProv.provName:'' }
                   	</c:forEach>
                    </td>
                    <td>
                    <c:forEach items="${hospitalProfessionalList }" var="professional">
	                       ${professional.id==us.professional?professional.name:'' }
	                 </c:forEach>
                    </td>
	                <td>${us.frozen==1?"冻结":"解冻" }</td>
	                <td >${us.hospitalName}</td>
	                <td>${us.hospitalGradeStr }</td>
	                <td>${us.departmentOneStr }</td>
	                <td>${us.source }</td>
	                <td>
	                <c:choose>
						<c:when test="${us.spare1 eq 'yes' }">是
						</c:when>   
						<c:otherwise>否
						</c:otherwise>  
					</c:choose>
					</td>
	                <td>${us.status eq 1?"未完善":us.status eq 2?"审核中":"已完善" }</td>
	                <td><input type="checkbox" name="ids" data-toggle="icheck" value="${us.id }"></td>
	                <td width="185">
	                <a href="/mm/memberDetail.im?userDetailId=${us.id }" class="btn btn-green" data-toggle="dialog" data-mask="true" data-title="修改" data-width="800" data-height="500" data-icon="edit">详细</a>					
	                <a href="/mm/frozen.im?userId=${us.userId }" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要${us.frozen eq 1?'解冻':'冻结' }此会员么？">${us.frozen eq 1?'解冻':'冻结' }</a>
	                <c:if test="${us.status eq 2 }">
						<a href="/mm/audit.im?userDetailId=${us.id }&status=3" class="btn btn-green" data-confirm-msg="确定要审核【通过】吗" data-toggle="doajax">审核通过</a>
		                <a href="/mm/audit.im?userDetailId=${us.id }&status=1" class="btn btn-red" data-confirm-msg="确定要审核【不通过】吗" data-toggle="doajax">审核不通过</a>
	                </c:if>
	                <a href="/mm/memberDelete.im?userDetailId=${us.id }" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要删除吗？">删除</a>
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
            <span>&nbsp;条，共${page.totalRecord }条</span>
        </div>
        <div class="pagination-box" data-toggle="pagination" data-total="${page.totalRecord}" data-page-size="${page.pageSize }" data-page-current="${page.pageNo }">
        </div>
    </div>
</div>
