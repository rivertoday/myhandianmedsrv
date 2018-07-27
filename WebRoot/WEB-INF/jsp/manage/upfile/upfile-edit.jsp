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
		/* $("input[name='status']").val($(this).attr("data-status"));
		$("form").submit(); */
	})
</script>
<div class="bjui-pageContent">
	<form action="/manageUpFile/upfileSave.im" id="memberForm"
		class="pageForm" method="post" data-toggle="validate">
		<input type="hidden" name="id" value="${upfile.id }">
		<div class="pageFormContent" data-layout-h="0">
			<table class="table table-condensed table-hover" width="100%">
				<tbody>
					<tr>
						<td><label for="title" class="control-label x85">标题：</label><input
							type="label" name="title" id="title"
							value="${upfile.title}" data-rule="required" size="100">
						</td>
					</tr>
					<tr>
						<td><label for="downloadName" class="control-label x85">文件名：</label><input
							type="label" name="downloadName" id="downloadName"
							value="${upfile.downloadName}" data-rule="required" size="100">
						</td>
					</tr>
					<tr>
						<td><label for="submitter" class="control-label x85">提交者：</label><input
							type="label" name="submitter" id="submitter"
							value="${upfile.submitter}" data-rule="required" size="50">
						</td>
					</tr>
					<tr>
						<td><label for="phone" class="control-label x85">手机：</label><input
							type="label" name="phone" id="phone"
							value="${upfile.phone}" data-rule="required" size="20">
						</td>
					</tr>
					<tr>
						<td><label for="mail" class="control-label x85">邮箱：</label><input
							type="label" name="mail" id="mail"
							value="${upfile.mail}" data-rule="required" size="50">
						</td>
					</tr>					
					<tr>
						<td><label for="operate_time" class="control-label x85">上传时间：</label><input
							type="label" name="operate_time" id="operate_time"
							value="${upfile.operate_time}" data-rule="required" size="50">
						</td>
					</tr>
					<tr>
						<td><label for="downloadUrl" class="control-label x85">下载链接：</label><input
							type="label" name="downloadUrl" id="downloadUrl"
							value="${upfile.downloadUrl}" data-rule="required" size="50">
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="bjui-footBar">
			<ul>
				<li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
				<!-- <li><button type="button" class="btn-default bann"
						data-icon="save" data-status="0">确定</button></li> -->
			</ul>
		</div>
	</form>
</div>
