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
	<form action="/manageMingci/mingciSave.im" id="memberForm"
		class="pageForm" method="post" data-toggle="validate">
		<input type="hidden" name="id" value="${mingci.id }">
		<div class="pageFormContent" data-layout-h="0">
			<table class="table table-condensed table-hover" width="100%">
				<tbody>
					<tr>
						<td><label for="term" class="control-label x85">名词：</label><input
							type="text" name="term" id="term"
							value="${mingci.term}" data-rule="required" size="15">
						</td>
					</tr>
					<tr>
						<td><label for="category" class="control-label x85">拼音分类：</label><input
							type="text" name="category" id="category"
							value="${mingci.category}" data-rule="required" size="15">
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
								data-after-select="E_afterSelect">${mingci.introduction }
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
