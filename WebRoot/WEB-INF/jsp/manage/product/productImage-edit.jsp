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
<div class="bjui-pageContent">
    <form action="/manageProduct/productImageSave.im" id="productForm" class="pageForm" method="post" data-toggle="validate">
        <input type="hidden" name="productId" value="${productId }" />
        <div class="pageFormContent" data-layout-h="0">
			<table class="table table-condensed table-hover" width="100%">
				<tbody>
					<tr>
						<td >
							<label class="control-label x85">详情页图1：</label>
                            <div style="display: inline-block; vertical-align: middle;">
                                <div id="pdimg1" data-toggle="upload" data-uploader="/upload/file.im?sessionid=?" 
                                    data-file-size-limit="1024000000"
                                    data-file-type-exts="*.jpg;*.png;*.gif;*.mpg;*.gif"
                                    data-multi="false"
                                    data-on-upload-success="dimg1_upload_success"
                                    data-icon="cloud-upload"></div>
                                <input type="hidden" name="id" value="${empty productImages[0]?0:productImages[0].id }">
                                <input type="hidden" name="image" value="${productImages[0].image }" id="dimg1"  data-target="#dimg1 .bjui-upload-select-file">
                                <span id="dimg_span1"><img src="${empty productImages[0]?'/client/images/defaultimg.jpg':productImages[0].image }" width="100"/></span>
                            	<br/>
                            	<button type="button" class="btn btn-red delImg" id="delImg">删除</button>
                            </div>
                        </td>
						<td >
							<label class="control-label x85">详情页图2：</label>
                            <div style="display: inline-block; vertical-align: middle;">
                                <div id="pdimg2" data-toggle="upload" data-uploader="/upload/file.im?sessionid=?" 
                                    data-file-size-limit="1024000000"
                                    data-file-type-exts="*.jpg;*.png;*.gif;*.mpg;*.gif"
                                    data-multi="true"
                                    data-on-upload-success="dimg1_upload_success"
                                    data-icon="cloud-upload"></div>
                                <input type="hidden" name="id" value="${empty productImages[1]?0:productImages[1].id }">
                                <input type="hidden" name="image" value="${productImages[1].image }" id="dimg2"  data-target="#dimg2 .bjui-upload-select-file">
                                <span id="dimg_span2"><img src="${empty productImages[1]?'/client/images/defaultimg.jpg':productImages[1].image }" width="100"/></span>
                            	<br/>
                            	<button type="button" class="btn btn-red delImg" id="delImg">删除</button>
                            </div>
                        </td>
                        <td >
                            <label class="control-label x85">详情页图3：</label>
                            <div style="display: inline-block; vertical-align: middle;">
                                <div id="pdimg3" data-toggle="upload" data-uploader="/upload/file.im?sessionid=?" 
                                    data-file-size-limit="1024000000"
                                    data-file-type-exts="*.jpg;*.png;*.gif;*.mpg;*.gif"
                                    data-multi="true"
                                    data-on-upload-success="dimg1_upload_success"
                                    data-icon="cloud-upload"></div>
                                <input type="hidden" name="id" value="${empty productImages[2]?0:productImages[2].id }">
                                <input type="hidden" name="image" value="${productImages[2].image }" id="dimg3" data-target="#dimg3 .bjui-upload-select-file">
                                <span id="dimg_span3"><img src="${empty productImages[2]?'/client/images/defaultimg.jpg':productImages[2].image }" width="100"/></span>
                            	<br/>
                            	<button type="button" class="btn btn-red delImg" id="delImg">删除</button>
                            </div>
                        </td>
                        <td >
                            <label class="control-label x85">详情页图4：</label>
                            <div style="display: inline-block; vertical-align: middle;">
                                <div id="pdimg4" data-toggle="upload" data-uploader="/upload/file.im?sessionid=?" 
                                    data-file-size-limit="1024000000"
                                    data-file-type-exts="*.jpg;*.png;*.gif;*.mpg;*.gif"
                                    data-multi="true"
                                    data-on-upload-success="dimg1_upload_success"
                                    data-icon="cloud-upload"></div>
                                <input type="hidden" name="id" value="${empty productImages[3]?0:productImages[3].id }">
                                <input type="hidden" name="image" value="${productImages[3].image }" id="dimg4" data-target="#dimg4 .bjui-upload-select-file">
                                <span id="dimg_span4"><img src="${empty productImages[3]?'/client/images/defaultimg.jpg':productImages[3].image }" width="100"/></span>
                           		<br/>
                            	<button type="button" class="btn btn-red delImg" id="delImg">删除</button>
                            </div>
                        </td>

                      </tr>
					<tr>
						<td >
							<label class="control-label x85">详情页图5：</label>
                            <div style="display: inline-block; vertical-align: middle;">
                                <div id="pdimg1" data-toggle="upload" data-uploader="/upload/file.im?sessionid=?" 
                                    data-file-size-limit="1024000000"
                                    data-file-type-exts="*.jpg;*.png;*.gif;*.mpg;*.gif"
                                    data-multi="false"
                                    data-on-upload-success="dimg1_upload_success"
                                    data-icon="cloud-upload"></div>
                                <input type="hidden" name="id" value="${empty productImages[4]?0:productImages[4].id }">
                                <input type="hidden" name="image" value="${productImages[4].image }" id="dimg1"  data-target="#dimg1 .bjui-upload-select-file">
                                <span id="dimg_span1"><img src="${empty productImages[4]?'/client/images/defaultimg.jpg':productImages[4].image }" width="100"/></span>
                            	<br/>
                            	<button type="button" class="btn btn-red delImg" id="delImg">删除</button>
                            </div>
                        </td>
						<td >
							<label class="control-label x85">详情页图6：</label>
                            <div style="display: inline-block; vertical-align: middle;">
                                <div id="pdimg2" data-toggle="upload" data-uploader="/upload/file.im?sessionid=?" 
                                    data-file-size-limit="1024000000"
                                    data-file-type-exts="*.jpg;*.png;*.gif;*.mpg;*.gif"
                                    data-multi="true"
                                    data-on-upload-success="dimg1_upload_success"
                                    data-icon="cloud-upload"></div>
                                <input type="hidden" name="id" value="${empty productImages[5]?0:productImages[5].id }">
                                <input type="hidden" name="image" value="${productImages[5].image }" id="dimg2"  data-target="#dimg2 .bjui-upload-select-file">
                                <span id="dimg_span2"><img src="${empty productImages[5]?'/client/images/defaultimg.jpg':productImages[5].image }" width="100"/></span>
                            	<br/>
                            	<button type="button" class="btn btn-red delImg" id="delImg">删除</button>
                            </div>
                        </td>
                        <td >
                            <label class="control-label x85">详情页图7：</label>
                            <div style="display: inline-block; vertical-align: middle;">
                                <div id="pdimg3" data-toggle="upload" data-uploader="/upload/file.im?sessionid=?" 
                                    data-file-size-limit="1024000000"
                                    data-file-type-exts="*.jpg;*.png;*.gif;*.mpg;*.gif"
                                    data-multi="true"
                                    data-on-upload-success="dimg1_upload_success"
                                    data-icon="cloud-upload"></div>
                                <input type="hidden" name="id" value="${empty productImages[6]?0:productImages[6].id }">
                                <input type="hidden" name="image" value="${productImages[6].image }" id="dimg3" data-target="#dimg3 .bjui-upload-select-file">
                                <span id="dimg_span3"><img src="${empty productImages[6]?'/client/images/defaultimg.jpg':productImages[6].image }" width="100"/></span>
                            	<br/>
                            	<button type="button" class="btn btn-red delImg" id="delImg">删除</button>
                            </div>
                        </td>
                        <td >
                            <label class="control-label x85">详情页图8：</label>
                            <div style="display: inline-block; vertical-align: middle;">
                                <div id="pdimg4" data-toggle="upload" data-uploader="/upload/file.im?sessionid=?" 
                                    data-file-size-limit="1024000000"
                                    data-file-type-exts="*.jpg;*.png;*.gif;*.mpg;*.gif"
                                    data-multi="true"
                                    data-on-upload-success="dimg1_upload_success"
                                    data-icon="cloud-upload"></div>
                                <input type="hidden" name="id" value="${empty productImages[7]?0:productImages[7].id }">
                                <input type="hidden" name="image" value="${productImages[7].image }" id="dimg4" data-target="#dimg4 .bjui-upload-select-file">
                                <span id="dimg_span4"><img src="${empty productImages[7]?'/client/images/defaultimg.jpg':productImages[7].image }" width="100"/></span>
                           		<br/>
                            	<button type="button" class="btn btn-red delImg" id="delImg">删除</button>
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