<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript">

//单击事件
function S_NodeClick(event, treeId, treeNode) {
    $("#modules").val(treeNode.name);
    $("#moduleid").val(treeNode.id);
    return false
}

//选择事件
function ss(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj(treeId),
        nodes = zTree.getCheckedNodes(true)
    var ids = '', names = ''
    for (var i = 0; i < nodes.length; i++) {
        ids   += ','+ nodes[i].id
        names += ','+ nodes[i].name
    }
    if (ids.length > 0) {
        ids = ids.substr(1), names = names.substr(1)
    }
    $("#pids").val(ids);
    var $from = $('#'+ treeId).data('fromObj')
    
    if ($from && $from.length) $from.val(names)
}
//单击事件
function cc(event, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj(treeId)
    
    zTree.checkNode(treeNode, !treeNode.checked, false, true)
    return false
}
$(function(){
	setTimeout(function(){
		 var zTree = $.fn.zTree.getZTreeObj("ps_tree");
		 var nodes = zTree.transformToArray(zTree.getNodes());
		 var pids = $("#pids").val().split(",");
		 for(var i=0;i<nodes.length;i++){
		 	$.each(pids,function(a,b){
		 		if(nodes[i].id==b){
		 			zTree.checkNode(nodes[i], !nodes[i].checked, false, true)
		 		}
		 	});
		 }
	}, 1000);
});
</script>
    <form action="/role/er.im" id="role" class="pageForm" data-toggle="validate" onmousedown="">
        <input type="hidden" name="id" value="${role.id }">
        <div class="pageFormContent" data-layout-h="0">
            <table class="table table-condensed table-hover" width="100%">
                <tbody>
                    <tr>
                        <td>
                            <label for="rolename" class="control-label x120">角色名称：</label>
                            <input type="text" name="rolename" id="rolename" value="${role.rolename }" data-rule="required" size="15">
                        </td>
                        <td>
                            <%-- <label for="moduleid" class="control-label x120">所属部门：</label>
                            <input type="hidden" name="moduleid" id="moduleid" VALUE="${role.moduleM.id }"/>
                            <input type="text" name="modules" id="modules" data-toggle="selectztree" value="${role.moduleM.modulename }" size="18" data-tree="#module_tree"  readonly>
                                <ul id="module_tree" class="ztree hide" data-toggle="ztree" data-expand-all="true" data-on-check="S_NodeCheck" data-on-click="S_NodeClick">
                                    <c:forEach items="${modules }" var="m">
                                    	<li data-id="${m.id }" data-pid="${m.parentid }">${m.modulename }</li>
                                    </c:forEach>
                                </ul> --%>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="pids" class="control-label x120">所有权限：</label>
                            <input type="hidden" name="pids" id="pids" VALUE="${psid}"/>
                            <input type="text" name="ps" id="ps" data-toggle="selectztree" data-height="540" data-width="300"  value="${psname }" size="auto" data-tree="#ps_tree"  readonly>
                                <ul id="ps_tree" class="ztree hide" data-toggle="ztree" data-chkbox-Type="{'Y':'p','N':'s' }"  data-check-enable="true" data-chk-style="checkbox" data-expand-all="true" data-on-check="ss" data-on-click="cc">
                                    <c:forEach items="${ps }" var="p">
                                    	<li data-id="${p.id }" data-pid="${p.parentid }">${p.title }</li>
                                    </c:forEach>
                                </ul>
                        </td>
                        <td>
                            
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="bjui-footBar">
            <ul>
                <li><button type="button" class="btn-close" data-icon="close">取消</button></li>
                <li><button type="submit" class="btn-default" data-icon="save">保存</button></li>
            </ul>
        </div>
    </form>
</div>