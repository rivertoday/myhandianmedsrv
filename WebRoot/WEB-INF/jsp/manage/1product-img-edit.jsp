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
function dimg1_upload_success(file, data) {
    var json = $.parseJSON(data);
    
    $(this).bjuiajax('ajaxDone', json);
    if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
        $('#dimg1').val(json.navTabId).trigger('validate');
        $('#dimg_span1').html('<img src="'+ json.navTabId +'" width="100" />');
    }
}
function dimg2_upload_success(file, data) {
    var json = $.parseJSON(data);
    
    $(this).bjuiajax('ajaxDone', json);
    if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
        $('#dimg2').val(json.navTabId).trigger('validate');
        $('#dimg_span2').html('<img src="'+ json.navTabId +'" width="100" />');
    }
}

//单击事件
function S_NodeClick(event, treeId, treeNode) {
    $("#module").val(treeNode.name);
    $("#moduleid").val(treeNode.id);
    return false;
}
</script>
<script src="/manage/js/grade.js" type="text/javascript" ></script>
<div class="bjui-pageContent">
    <form action="/productm/edit.im" id="productForm" class="pageForm" method="post" data-toggle="validate">
        <input type="hidden" name="id" value="${product.id }">
<%--        <input type="hidden" name="createtime" value="${manager.createtime }">--%>
        <div class="pageFormContent" data-layout-h="0">
			<table class="table table-condensed table-hover" width="100%">
				<tbody>
					<tr>
						<td colspan="2">
                            <label class="control-label x85">渲染图：</label>
                            <div style="display: inline-block; vertical-align: middle;">
                                <div id="headimg2" data-toggle="upload" data-uploader="/upload/file.im?sessionid=?" 
                                    data-file-size-limit="1024000000"
                                    data-file-type-exts="*.jpg;*.png;*.gif;*.mpg;*.gif"
                                    data-multi="true"
                                    data-on-upload-success="pic_upload_success"
                                    data-icon="cloud-upload"></div>
                                <input type="hidden" name="logoimg" value="${empty product.logoimg?'/client/images/defaultimg.jpg':product.logoimg }" id="headimg" data-rule="required" data-target="#headimg .bjui-upload-select-file">
                                <span id="headimg_span"><img src="${empty product.logoimg?'/client/images/defaultimg.jpg':product.logoimg}" width="100"/></span>
                            </div>
                        </td>
					</tr>
					
					
					<tr>
						<td colspan="2">
							<label class="control-label x85">详情页图1：</label>
                            <div style="display: inline-block; vertical-align: middle;">
                                <div id="pdimg1" data-toggle="upload" data-uploader="/upload/file.im?sessionid=?" 
                                    data-file-size-limit="1024000000"
                                    data-file-type-exts="*.jpg;*.png;*.gif;*.mpg;*.gif"
                                    data-multi="false"
                                    data-on-upload-success="dimg1_upload_success"
                                    data-icon="cloud-upload"></div>
                                <input type="hidden" name="productImages[0].id" value="${empty product.productImages[0]?0:product.productImages[0].id }">
                                <input type="hidden" name="productImages[0].image" value="${empty product.productImages[0]?'/client/images/defaultimg.jpg':product.productImages[0].image }" id="dimg1" data-rule="required" data-target="#dimg1 .bjui-upload-select-file">
                                <span id="dimg_span1"><img src="${empty product.productImages[0]?'/client/images/defaultimg.jpg':product.productImages[0].image }" width="100"/></span>
                            </div>
                        </td>
						<td colspan="2">
							<label class="control-label x85">详情页图2：</label>
                            <div style="display: inline-block; vertical-align: middle;">
                                <div id="pdimg2" data-toggle="upload" data-uploader="/upload/file.im?sessionid=?" 
                                    data-file-size-limit="1024000000"
                                    data-file-type-exts="*.jpg;*.png;*.gif;*.mpg;*.gif"
                                    data-multi="true"
                                    data-on-upload-success="dimg2_upload_success"
                                    data-icon="cloud-upload"></div>
                                <input type="hidden" name="productImages[1].id" value="${empty product.productImages[0]?0:product.productImages[1].id }">
                                <input type="hidden" name="productImages[1].image" value="${empty product.productImages[1]?'/client/images/defaultimg.jpg':product.productImages[1].image }" id="dimg2" data-rule="required" data-target="#dimg2 .bjui-upload-select-file">
                                <span id="dimg_span2"><img src="${empty product.productImages[1]?'/client/images/defaultimg.jpg':product.productImages[1].image }" width="100"/></span>
                            </div>
                        </td>
<!-- 						<td colspan="2"> -->
<!-- 							<label class="control-label x85">详情页图3：</label> -->
<!--                             <div style="display: inline-block; vertical-align: middle;"> -->
<!--                                 <div id="headimg2" data-toggle="upload" data-uploader="/upload/file.im?sessionid=?"  -->
<!--                                     data-file-size-limit="1024000000" -->
<!--                                     data-file-type-exts="*.jpg;*.png;*.gif;*.mpg;*.gif" -->
<!--                                     data-multi="true" -->
<!--                                     data-on-upload-success="pic_upload_success" -->
<!--                                     data-icon="cloud-upload"></div> -->
<%--                                 <input type="hidden" name="logoimg" value="${empty product.logoimg?'/client/images/defaultimg.jpg':product.logoimg }" id="headimg" data-rule="required" data-target="#headimg .bjui-upload-select-file"> --%>
<%--                                 <span id="headimg_span"><img src="${empty product.logoimg?'/client/images/defaultimg.jpg':product.logoimg}" width="100"/></span> --%>
<!--                             </div> -->
<!--                         </td> -->
<!--                         <td colspan="2"> -->
<!-- 							<label class="control-label x85">详情页图4：</label> -->
<!--                             <div style="display: inline-block; vertical-align: middle;"> -->
<!--                                 <div id="headimg2" data-toggle="upload" data-uploader="/upload/file.im?sessionid=?"  -->
<!--                                     data-file-size-limit="1024000000" -->
<!--                                     data-file-type-exts="*.jpg;*.png;*.gif;*.mpg;*.gif" -->
<!--                                     data-multi="true" -->
<!--                                     data-on-upload-success="pic_upload_success" -->
<!--                                     data-icon="cloud-upload"></div> -->
<%--                                 <input type="hidden" name="logoimg" value="${empty product.logoimg?'/client/images/defaultimg.jpg':product.logoimg }" id="headimg" data-rule="required" data-target="#headimg .bjui-upload-select-file"> --%>
<%--                                 <span id="headimg_span"><img src="${empty product.logoimg?'/client/images/defaultimg.jpg':product.logoimg}" width="100"/></span> --%>
<!--                             </div> -->
<!--                         </td> -->
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

