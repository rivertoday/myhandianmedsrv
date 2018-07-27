<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">

//单击事件
function S_NodeClick(event, treeId, treeNode) {
    $("#parents").val(treeNode.name);
    $("#parentid").val(treeNode.id);
    return false
}
</script>
<div class="bjui-pageContent">
    <form action="/role/em.im" id="j_custom_form" class="pageForm" data-toggle="validate">
        <input type="hidden" name="id" value="${module.id }">
        <div class="pageFormContent" data-layout-h="0">
            <table class="table table-condensed table-hover" width="100%">
                <tbody>
                    <tr>
                        <td>
                            <label for="modulename" class="control-label x120">部门名称：</label>
                            <input type="text" name="modulename" id="modulename" value="${module.modulename }" data-rule="required" size="15">
                        </td>
                        <td>
                           <td>
                            <label for="modulecode" class="control-label x120">部门编号：</label>
                            <input type="text" name="modulecode" id="modulecode" value="${module.modulecode }" size="15">
                        </td>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="parentid" class="control-label x120">上级部门：</label>
                            <input type="hidden" name="parentid" id="parentid" VALUE="${module.parentid }"/>
                            <input type="text" name="parents" id="parents" data-toggle="selectztree" value="${module.parentM.modulename }" size="18" data-tree="#parent_tree"  readonly>
                                <ul id="parent_tree" class="ztree hide" data-toggle="ztree" data-expand-all="true" data-on-check="S_NodeCheck" data-on-click="S_NodeClick">
                                    <c:forEach items="${modules }" var="m">
                                    	<li data-id="${m.id }" data-pid="${m.parentid }">${m.modulename }</li>
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