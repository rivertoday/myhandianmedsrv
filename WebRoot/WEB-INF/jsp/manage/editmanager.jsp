<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
function pic_upload_success(file, data) {
    var json = $.parseJSON(data)
    
    $(this).bjuiajax('ajaxDone', json)
    if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
        $('#headimg').val(json.navTabId).trigger('validate')
        $('#headimg_span').html('<img src="'+ json.navTabId +'" width="100" />')
    }
}
//单击事件
function S_NodeClick(event, treeId, treeNode) {
    $("#module").val(treeNode.name);
    $("#moduleid").val(treeNode.id);
    return false;
}
</script>
<div class="bjui-pageContent">
    <form action="/m2/e1.im" id="managerForm" class="pageForm" method="post" data-toggle="validate">
        <input type="hidden" name="id" value="${manager.id }">
<%--        <input type="hidden" name="createtime" value="${manager.createtime }">--%>
        <input type="hidden" name="idkey" value="${manager.idkey }">
        <div class="pageFormContent" data-layout-h="0">
            <table class="table table-condensed table-hover" width="100%">
                <tbody>
<%--                 <tr>
                        <td>
                            <label for="moduleid" class="control-label x85">所属部门：</label>
                            <input type="hidden" name="moduleId" id="moduleid" VALUE="${manager.moduleM.id}"/>
                            <input type="text" id="module" data-toggle="selectztree" value="${manager.moduleM.modulename }" size="18" data-tree="#module_tree"  readonly>
                                <ul id="module_tree" class="ztree hide" data-toggle="ztree" data-expand-all="true" data-on-click="S_NodeClick">
                                    <c:forEach items="${modules }" var="m">
                                    	<li data-id="${m.id }" data-pid="${m.parentid }">${m.modulename }</li>
                                    </c:forEach>
                                </ul>
                        </td><td></td>
                    </tr> --%>
                   <tr>
                        <td>
                            <label for="username" class="control-label x85">用户名：</label>
                            <input type="text" name="username" id="username" value="${manager.username }"  data-rule="required" size="15">
                        </td>
                        <td>
                            <label for="password" class="control-label x85">密码：</label>
                            <input type="password" name="password" id="password" value="${manager.password }" data-rule="required" size="15">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="name" class="control-label x85">真实姓名：</label>
                            <input type="text" name="name" id="name" value="${manager.name }" data-rule="required" size="15">
                        </td>
                        <td>
                            <label for="phone" class="control-label x85">手机号码：</label>
                           <input type="text" name="phone" id="phone" value="${manager.phone }" data-rule="required;mobile" size="15">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <label class="control-label x85">头像：</label>
                            <div style="display: inline-block; vertical-align: middle;">
                                <div id="headimg2" data-toggle="upload" data-uploader="/upload/file.im?sessionid=?" 
                                    data-file-size-limit="1024000000"
                                    data-file-type-exts="*.jpg;*.png;*.gif;*.mpg;*.gif"
                                    data-multi="true"
                                    data-on-upload-success="pic_upload_success"
                                    data-icon="cloud-upload"></div>
                                <input type="hidden" name="headimg" value="${empty manager.headimg?'/client/images/defaultimg.jpg':manager.headimg }" id="headimg" data-rule="required" data-target="#headimg .bjui-upload-select-file">
                                <span id="headimg_span"><img src="${empty manager.headimg?'/client/images/defaultimg.jpg':manager.headimg}" width="100"/></span>
                            </div>
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