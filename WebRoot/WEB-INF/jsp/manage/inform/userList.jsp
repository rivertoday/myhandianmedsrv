<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
function chooseItem(){
var data="";
var checkedLen=$("input[name='ids']:checked").length;
	
	$("input[name='ids']:checked").each(function(i,n){
			if(i==checkedLen-1){
				data+=this.value;
			}else{
			 	data+=this.value+"|";
		     }
	});
	if(data == ""){
		$(this).alertmsg('warn', "请选择一项");
		return;
	}
	//alert(data);
	$.CurrentDialog.dialog('closeCurrent');
	parent.getDetail(data);
	}
</script>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="/manageInform/memberList.im" method="post">
        <input type="hidden" name="pageSize" value="${page.pageSize}">
        <input type="hidden" name="pageCurrent" value="${page.pageNo}">
        <input type="hidden" name="orderField" value="${page.orderField}">
        <input type="hidden" name="orderDirection" value="${page.orderDirection}">
        <ul class="bjui-searchBar" style="height: 100px">
            <li><label>手机号码：</label><input type="text" value="${page.params.phone }" name="phone" class="form-control" size="10" /></li>
            <li><label>医院：</label><input type="text" value="${page.params.hospitalName }" name="hospitalName" class="form-control" size="10" /></li>
            <li><label>所在地区：</label>
            			<select name="province" id="province">
                            	<option value="">请选择</option>
	                            <c:if test="${!empty areaProvs }">
		                        	<c:forEach items="${areaProvs }" var="areaProv">
		                        		<option value="${areaProv.provCode }" ${areaProv.provCode==page.params.province?'selected':'' }>${areaProv.provName }</option>
		                        	</c:forEach>
		                        </c:if>
                            </select>
            </li>
            <li><label>职称：</label>
           					<select name="professional" id="professional" data-toggle="selectpicker">
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
            		<option value="1" ${page.params.source eq '安卓'?'selected':'' }>安卓</option>
            		<option value="2" ${page.params.source eq 'IOS'?'selected':'' }>IOS</option>
            		<option value="3" ${page.params.source eq '手机站'?'selected':'' }>手机站</option>
            		<option value="4" ${page.params.source eq 'PC站'?'selected':'' }>PC站</option>
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
        	<li>
        	<div class="pull-right">
                <button data-icon="check-square-o" data-warn="请至少选择一项" data-lookupid="ids" onclick="chooseItem()"  class="btn btn-blue" type="button">
                	选择选中</button>
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
                <th>头像</th>
                <th>姓名</th>
                <th>性别</th>
               	<th>所在地区</th>
               	<th>职称</th>
                <th>正常状态</th>
                <th>医院</th>
                <th>医院等级</th>
                <th>科室</th>
                <th>注册来源</th>
                <th>是否认证</th>
                <th width="26"><input type="checkbox" class="checkboxCtrl" data-group="ids" data-toggle="icheck"></th>
                
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="us" items="${page.results }" varStatus="i">
	            <tr class="bodytr">
	           		 <td>${i.count}</td>
	            	<td>${us.phone }</td>
	            	<td>${us.nickName }</td>
	                <td><img alt="" src="${us.headImg}" width="50" height="30"></td>
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
	                <td>${us.status eq 1?"未完善":us.status eq 2?"审核中":"已完善" }</td>
	                <td><input type="checkbox" name="ids" data-toggle="icheck" value='"userId":"${us.id }","userName":"${us.userName }"'></td>
	                
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
