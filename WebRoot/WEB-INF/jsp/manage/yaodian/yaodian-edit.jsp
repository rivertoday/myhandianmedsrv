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
			$('#headimg_span').html(
					'<img src="'+ json.navTabId +'" width="100" />')
		}
	}

	$(".bann").click(function() {
		$("input[name='status']").val($(this).attr("data-status"));
		$("form").submit();
	})
</script>
<div class="bjui-pageContent">
	<form action="/manageYaodian/yaodianSave.im" id="memberForm"
		class="pageForm" method="post" data-toggle="validate">
		<input type="hidden" name="id" value="${herbal.id }">
		<div class="pageFormContent" data-layout-h="0">
			<table class="table table-condensed table-hover" width="100%">
				<tbody>
					<tr>
						<td><label for="herbmed_name" class="control-label x85">草药名称：</label><input
							type="text" name="herbmed_name" id="herbmed_name"
							value="${herbal.herbmed_name}" data-rule="required" size="15">
						</td>
					</tr>
					<tr>
						<td><label for="category" class="control-label x85">拼音分类：</label><input
							type="text" name="category" id="category"
							value="${herbal.category}" data-rule="required" size="15">
						</td>
					</tr>
					<tr>
						<td><div>
								图片1:<input type="text" name="pic1" id="pic1"
									value="${herbal.pic1}" size="15"> 
								图片2:<input type="text" name="pic2" id="pic2" 
									value="${herbal.pic2}" size="15"> 
								图片3:<input type="text" name="pic3" id="pic3"
									value="${herbal.pic3}" size="15">
							</div></td>
					</tr>
					<tr>
						<td>
							<div>
								<img src="${herbal.pic1}" width="100" />
								<img src="${herbal.pic2}" width="100" />
								<img src="${herbal.pic3}" width="100" />
							</div>
						</td>
					</tr>
					<tr>
						<td><label for="introduction" class="control-label x85">介绍：</label>
							<textarea style="width:760px;height:260px;" name="introduction"
								data-rule="required" data-toggle="kindeditor"
								data-upload-json="/kindeditor/file_upload.im"
								data-file-manager-json="/kindeditor/file_manager.im"
								data-after-upload="E_afterUpload"
								data-after-select-file="E_afterSelectFile"
								data-after-select="E_afterSelect">${herbal.introduction }
								</textarea>
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
			</ul>
		</div>
	</form>
</div>
