<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
function pic_upload_success(file, data) {
    var json = $.parseJSON(data);
    
    $(this).bjuiajax('ajaxDone', json);
    if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
        $('#headimg').val(json.navTabId).trigger('validate');
        $('#headimg_span').html('<img src="'+ json.navTabId +'" width="100" />');
    }
}
function dimg1_upload_success(file, data, element) {
	var imgInput = element.next().next();
	var imgSpan = imgInput.next();
    var json = $.parseJSON(data);
    
    $(this).bjuiajax('ajaxDone', json);
    if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
    	imgInput.val(json.navTabId).trigger('validate');
    	imgSpan.html('<img src="'+ json.navTabId +'" width="100" />');
    }
}
$(document).ready(function() {
	
	$(".delImg").bind("click", function(){
        var imgSpan = $(this).prev().prev();
        var imgInput = imgSpan.prev();
    	imgSpan.html('<img src="" width="100" />');
    	imgInput.val("");
	}); 
});

//单击事件
function S_NodeClick(event, treeId, treeNode) {
    $("#module").val(treeNode.name);
    $("#moduleid").val(treeNode.id);
    return false;
}
</script>
<script src="/manage/js/grade.js" type="text/javascript" ></script>
<div class="bjui-pageContent">
    <form action="/clientManage/save.im" id="productForm" class="pageForm" method="post" data-toggle="validate">
        <input type="hidden" name="id" value="${clientManage.id }">
<%--        <input type="hidden" name="createtime" value="${manager.createtime }">--%>
        <div class="pageFormContent" data-layout-h="0">
			<table class="table table-condensed table-hover" width="100%">
				<tbody>
					<tr>
						<td colspan="2">
                            <label class="control-label x85">二维码图：</label>
                            <div style="display: inline-block; vertical-align: middle;">
                                <div id="headimg2" data-toggle="upload" data-uploader="/upload/file.im?sessionid=?" 
                                    data-file-size-limit="1024000000"
                                    data-file-type-exts="*.jpg;*.png;*.gif;*.mpg;*.gif"
                                    data-multi="true"
                                    data-on-upload-success="pic_upload_success"
                                    data-icon="cloud-upload"></div>
                                <input type="hidden" name="image" value="${empty clientManage.image?'/client/images/defaultimg.jpg':clientManage.image }" id="headimg" data-rule="required" data-target="#headimg .bjui-upload-select-file">
                                <span id="headimg_span"><img src="${empty clientManage.image?'/client/images/defaultimg.jpg':clientManage.image}" width="100"/></span>
                            </div>
                        </td>
					</tr>
                </tbody>
            </table>
        </div>
        <div class="bjui-footBar">
            <ul>
                <li><button type="button" class="btn-close" data-icon="close">取消</button></li>
                <li><button type="submit"  id="subBtn" class="btn-default" data-icon="save">保存</button></li>
            </ul>
        </div>
    </form>
</div>

<script type="text/javascript">
$('#subBtn').click(function() {
    $(this).alertmsg('confirm', '确定要保存修改吗', {okCall: function() {$("#productForm").submit();}}, {displayMode:'slide', displayPosition:'bottomcenter', okName:'Yes', cancelName:'no', title:'是否保存'});
    return false;
});
</script>