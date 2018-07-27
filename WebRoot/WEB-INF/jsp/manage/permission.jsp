<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
//单击事件
function ZtreeClick(event, treeId, treeNode) {
	$(this).navtab({id:"permission",title:"权限管理",url:"/permission/fp.im?parentid="+treeNode.id+"&_="+new Date().getTime(),type:"post"});
	event.preventDefault();
}
</script>
<div class="bjui-pageContent">
    <div class="pageFormContent" data-layout-h="30">
        <div style="float:left; margin:5px 5px 0; width:220px;">
            <fieldset>
                <legend>权限管理</legend>
                <div class="clearfix">
                    <div style="float:left; width:220px; min-height:800px;">
                        <ul id="menuztree" class="ztree" data-toggle="ztree"  data-expand-all="true" data-on-click="ZtreeClick">
                            <c:forEach items="${permissions }" var="permissions">
                            	<li data-id="${permissions.id }"   data-pid="${permissions.parentid }">${permissions.title }</li>
                            </c:forEach>
                        </ul>
                    </div>
                   </div> 
            </fieldset>
        </div>
        <div style="margin-right:10px; margin-top:5px;margin-left:250px;min-width:800px;min-height:800px; ">
            <%--<c:if test="${!empty permissions }">
	            <fieldset id="menuBody">
	                <legend>菜单管理</legend>                
	            	<jsp:include page="editmenu.jsp"></jsp:include>
	            </fieldset>
            </c:if>
            
            	--%><c:if test="${!empty mid or !empty parentid}">
	            <fieldset id="menuList">
	                <legend>菜单列表</legend>                
	            	<jsp:include page="permission-list.jsp"></jsp:include>
	            </fieldset>
	            </c:if>
        </div>
    </div>
    <div class="bjui-footBar">
        <ul>
            <li><button type="button" class="btn btn-close" data-icon="close">关闭</button></li>
        </ul>
    </div>
</div>