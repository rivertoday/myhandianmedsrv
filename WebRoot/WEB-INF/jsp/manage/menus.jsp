<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
//单击事件
function ZtreeClick(event, treeId, treeNode) {
	$(this).navtab({id:"menu",title:"菜单管理",url:"/menu/fp.im?parentid="+treeNode.id+"&_="+new Date().getTime(),type:"post"});
    event.preventDefault();
}
function toAdd(){
	var treeObj = $.fn.zTree.getZTreeObj("menuztree");
	var nodes = treeObj.getSelectedNodes();
	var id = 1
	if(nodes.length>0){
		id = nodes[0].id;
	}
	$(this).navtab({id:"menu",title:"菜单管理",url:"/menu/fi.im?parentid="+id,type:"post",data:{parentid:id}});
}
function toedit(mid){
	var treeObj = $.fn.zTree.getZTreeObj("menuztree");
	var nodes = treeObj.getSelectedNodes();
	$(this).navtab({id:"menu",title:"菜单管理",url:"/menu/fi.im?parentid="+nodes[0].id+"&id="+mid,type:"post",data:{parentid:nodes[0].id,id:mid}});
}
$(function(){
	setTimeout(function(){
		 var zTree = $.fn.zTree.getZTreeObj("menuztree");
		 var nodes = zTree.transformToArray(zTree.getNodes());
		 $.each(nodes,function(a,b){
		 		var c = "<%=request.getAttribute("parentid")%>";
		 		if(c==b.id){
		 			zTree.selectNode(b);
		 		}
		 });
	}, 200);
});
</script>
<div class="bjui-pageContent">
    <div class="pageFormContent" data-layout-h="30">
        <div style="float:left; margin:5px 5px 0; width:220px;">
            <fieldset>
                <legend>菜单管理</legend>
                <div class="clearfix">
                    <div style="float:left; width:220px; min-height:800px;">
                        <ul id="menuztree" class="ztree" data-toggle="ztree"  data-expand-all="true" data-on-click="ZtreeClick">
                            <c:forEach items="${menus }" var="m">
                            	<li data-id="${m.id }"  data-pid="${m.parentid }">${m.title }</li>
                            </c:forEach>
                        </ul>
                    </div>
                   </div> 
            </fieldset>
        </div>
        <div style="margin-right:10px; margin-top:5px;margin-left:250px;min-width:800px;min-height:800px; ">
            <c:if test="${!empty menu }">
            <fieldset id="menuBody">
                <legend>菜单管理</legend>                
            	<jsp:include page="editmenu.jsp"></jsp:include>
            </fieldset>
            </c:if>
            <c:if test="${empty menu}">
            <fieldset id="menuList">
                <legend>菜单列表</legend>                
            	<jsp:include page="menulist.jsp"></jsp:include>
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