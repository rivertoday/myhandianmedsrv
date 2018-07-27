<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link href="/manage/pf/css/star-rating.css" media="all" rel="stylesheet"
	type="text/css" />
<script src="/manage/pf/js/star-rating.js" type="text/javascript"></script>
<script type="text/javascript">
	function subFun(){
		var name=$("#name").val();
		
		if(name=="" || name==""){
			alert("信息不能为空");
			return;
		}
		
		$("form").submit();
	}
</script>
		<form id="pageForm" data-toggle="validate" action="/productimpression/save.im" method="post">
		<input type="hidden" name="productid" value="${productid}">
		<input type="hidden" name="id" value="${id}">
<div class="bjui-pageContent">
	<%--<div class="bjui-headBar">
        <ul>
            <li class="left"><button type="button" class="btn-default" onclick="forproject()">项目资料</button></li>
            <li class="left"><button type="button" class="btn-green" onclick="forjoinmark()">投标方案列表</button></li>
            <li class="left"><button type="button" class="btn-red">制作期监管</button></li>
            <li class="left"><button type="button" class="btn-orange">资金管理</button></li>
        </ul>
    </div>--%>
	<div class="pageFormContent" data-layout-h="0">
		<table class="table table-condensed table-hover" width="100%">
			<tbody>
				<tr>
					<td><label class="control-label x85">名称: </label> 
						<span>  
							<input name="name" id="name" value="${productImpression.name }">
						</span>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
 <div class="bjui-footBar">
            <ul>
            	<li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
            		<li><button type="button" class="btn-green" data-toggle="" data-url="" onclick="subFun();">保存</button></li>
            </ul>
        </div>
</form>
