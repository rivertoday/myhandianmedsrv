<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="/client/js/shengShiXian.js"></script>
<script type="text/javascript">
function pic_upload_success(file, data) {
    var json = $.parseJSON(data)
    
    $(this).bjuiajax('ajaxDone', json)
    if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
        $('#headimg').val(json.navTabId).trigger('validate')
        $('#headimg_span').html('<img src="'+ json.navTabId +'" width="100" />')
    }
}

$(".bann").click(function(){
	$("input[name='status']").val($(this).attr("data-status"));
	$("form").submit();
})
</script>
<div class="bjui-pageContent">
	<form action="/manageProduct/productSave.im" id="memberForm"
		class="pageForm" method="post" data-toggle="validate">
		<input type="hidden" name="id" value="${product.id }"> <input
			type="hidden" name="status" value="${product.status }">
		<div class="pageFormContent" data-layout-h="0">
			<table class="table table-condensed table-hover" width="100%">
				<tbody>
					<tr>
						<td><label for="title" class="control-label x85">标题：</label>
							<input type="text" name="title" id="title"
							value="${product.title}" data-rule="required" size="15">
						</td>
					</tr>
					<tr>
						<td><label for="sorts" class="control-label x85">排序：</label>
							<input type="text" name="sorts" id="sorts"
							value="${product.sorts}" data-rule="currency"
							data-rule-currency="[/^\d{1,5}$/,'只能输入数字']" data-rule="required"
							size="15"></td>
					</tr>
					<tr>
						<td><label class="control-label x85">图片：</label>
							<div style="display: inline-block; vertical-align: middle;">
								<div id="headimg2" data-toggle="upload"
									data-uploader="/upload/file.im?sessionid=?"
									data-file-size-limit="1024000000"
									data-file-type-exts="*.jpg;*.png;*.gif;*.mpg;*.gif"
									data-multi="true" data-on-upload-success="pic_upload_success"
									data-icon="cloud-upload"></div>
								<input type="hidden" name="image" value="${product.image}"
									id="headimg" data-target="#headimg .bjui-upload-select-file">
								<span id="headimg_span"><img src="${product.image}"
									width="100" /></span>
							</div></td>
					</tr>
					<tr>
						<td><label for="introduction" class="control-label x85">简单介绍：</label>
							<input type="text" name="introduction" id="introduction"
							value="${product.introduction }" data-rule="required" size="50">
						</td>
					</tr>
					<tr>
						<td><label for="content" class="control-label x85">详情：</label>
							<textarea style="width:760px;height:260px;" name="content"
								data-rule="required" data-toggle="kindeditor"
								data-upload-json="/kindeditor/file_upload.im"
								data-file-manager-json="/kindeditor/file_manager.im"
								data-after-upload="E_afterUpload"
								data-after-select-file="E_afterSelectFile"
								data-after-select="E_afterSelect">${product.content }</textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="bjui-footBar">
			<ul>
				<li><button type="button" class="btn-close" data-icon="close">取消</button></li>
				<li><button type="button" class="btn-default bann"
						data-icon="save" data-status="0">保存</button></li>
				<li><button type="button" class="btn-default bann"
						data-icon="save" data-status="1">上架</button></li>
			</ul>
		</div>
	</form>
</div>
