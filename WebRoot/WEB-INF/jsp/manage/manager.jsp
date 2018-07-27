<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
	function gr(id){
		$("#cursub").unbind("click");
		$.ajax({
			type:"post",
			url:"/role/fmr.im",
			async:false,
			success:function(json){
				json = tojson(json);
				var rs = $("input[name='roles']");
				$("input[name='roles']").iCheck('uncheck');
				$.each(json,function(j,o){
					$.each(rs,function(){
						if(this.value==o.roleid){
							$(this).iCheck('check');
						}
					});
				});
				$("#cursub").click(function(){
					$('#roleModal').modal('hide');
					$(this).bjuiajax('doAjax',{url:"/m2/sr.im",type:"post",data:{"mid":id},group:"roles",idname:"rids"});
				});
				$('#roleModal').modal('show');
			},
			data:{mid:id}
		});
	}
	function tototal(id){
		$(window).navtab({id:"manager"+id,title:"操作员详细",url:""});
	}
</script>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="/m2/g1.im" method="post">
        <input type="hidden" name="pageSize" value="${page.pageSize}">
        <input type="hidden" name="pageCurrent" value="${page.pageNo}">
        <input type="hidden" name="orderField" value="${page.orderField}">
        <input type="hidden" name="orderDirection" value="${page.orderDirection}">
        <ul class="bjui-searchBar">
        	<c:if test="${!empty sessionManagerRole[15] }"><li><button type="button" data-url="/m2/g2.im" data-toggle="dialog" data-mask="true" data-title="创建操作员" data-width="600" data-height="400" class="btn-default" data-icon="edit">创建操作员</button></li></c:if>
            <li><label>用户名：</label><input type="text" value="${page.params.username }" name="username" class="form-control" size="10" /></li>
            <li><button type="submit" class="btn-default" data-icon="search">查询</button></li>
            <li><a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a></li>
            <li class="pull-right">
                <div class="btn-group">
                    <button type="button" class="btn-default dropdown-toggle" data-toggle="dropdown" data-icon="copy">批量操作<span class="caret"></span></button>
                    <ul class="dropdown-menu right" role="menu">
                    	 <li><a href="/m2/ep.im" data-toggle="doexport" data-confirm-msg="确定要导出信息吗？">导出<span style="color: green;">全部</span></a></li>
                        <li><a href="/m2/ep.im" data-toggle="doexportchecked" data-confirm-msg="确定要导出选中项吗？" data-idname="expids" data-group="ids">导出<span style="color: red;">选中</span></a></li>
<!--                        <li class="divider"></li> -->
<!--                         <li><a href="/m2/d1.im" data-toggle="dodelchecked" data-confirm-msg="确定要删除选中项吗？" data-idname="ids" data-group="ids">删除选中</a></li> -->
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
                <th>用户编号</th>
            	<th>用户名</th>
                <th>姓名</th>
                <th>联系电话</th>
                <th>创建时间</th>
                <th>修改时间</th>
                <th>状态</th>
                <th width="26"><input type="checkbox" class="checkboxCtrl" data-group="ids" data-toggle="icheck"></th>
                <th width="100">操作</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="us" items="${page.results }">
	            <tr class="bodytr">
	            	<td>${us.idkey }</td>
	            	<td>${us.username }</td>
	                <td>${us.name}</td>
	                <td>${us.phone }</td>
	                <td><fmt:formatDate value="${us.createtime }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
	                <td><fmt:formatDate value="${us.operatime }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
	                <td width="50">${us.status==0?"启用":us.status==1?"冻结":""}</td>
	                <td><input type="checkbox" name="ids" data-toggle="icheck" value="${us.id }"></td>
	                <td width="185">
	                	<c:if test="${!empty sessionManagerRole[14] }"><a href="/redirect.im?path=manage/changepwd2&mid=${us.id }" data-toggle="dialog" class="btn btn-green" data-id="changepwd_page${us.id }" data-mask="true" data-width="400" data-height="260">修改密码</a></c:if>
						<c:if test="${!empty sessionManagerRole[11] }"><a href="/m2/g2.im?mid=${us.id }" class="btn btn-green" data-toggle="dialog" data-mask="true " data-title="修改管理员信息" data-width="600" data-height="400">编辑</a></c:if>
	                    <c:if test="${!empty sessionManagerRole[12] }"><a href="/m2/d2.im?mid=${us.id }" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要冻结该管理员吗？">${us.status eq 1?'启用':'冻结' }</a></c:if>
	                	<c:if test="${!empty sessionManagerRole[13] }"><button type="button" class="btn-primary" onclick="gr(${us.id});" data-target="#myModal">分配角色</button></c:if>
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

<!-- Modal -->
<div class="modal fade" id="roleModal" tabindex="1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">分配角色</h4>
      </div>
      <div class="modal-body">
        <table class="table table-condensed table-hover" width="100%">
                <tbody>
                		<tr>
                             <td><input type="checkbox" class="checkboxCtrl"  value="" data-group="roles" data-toggle="icheck" data-label="全选"></td>
                             <td></td>
                        </tr>
                    <c:forEach items="${roles }" var="role">
                    	<tr>
                             <td><input type="checkbox" name="roles" value="${role.id }" data-toggle="icheck"></td>
                             <td>${role.rolename }</td>
                        </tr>
                    </c:forEach> 
                </tbody>
            </table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" id="cursub" class="btn btn-primary">确定</button>
      </div>
    </div>
  </div>
</div>