<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../manage/import.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册</title>

<script type="text/javascript">
	$(function() {
		BJUI.init({
			pageInfo : {
				pageCurrent : 'pageCurrent',
				pageSize : 'pageSize',
				orderField : 'orderField',
				orderDirection : 'orderDirection'
			}, //[可选]
			keys : {
				statusCode : 'statusCode',
				message : 'message'
			}, //[可选]
			ui : {
				hideMode : 'display'
			}, //[可选]hideMode:navtab组件切换的隐藏方式，支持的值有’display’，’offsets’负数偏移位置的值，默认值为’display’
			debug : false, // [可选]调试模式 [true|false，默认false]
			theme : 'green' // 若有Cookie['bjui_theme'],优先选择Cookie['bjui_theme']。皮肤[五种皮肤:default, orange, purple, blue, green]
		})
		//时钟
	    var today = new Date(), time = today.getTime();
	    $('#bjui-date').html(today.formatDate('yyyy/MM/dd'));
	    setInterval(function() {
	        today = new Date(today.setSeconds(today.getSeconds() + 1));
	        $('#bjui-clock').html(today.formatDate('HH:mm:ss'));
	    }, 1000);
	})

	function file_upload_success(file, data) {
		var json = $.parseJSON(data);
		$(this).bjuiajax('ajaxDone', json);
		if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
			var file = json.navTabId.split(",");
			$('#downloadName_span').html(file[1]);
			$('#downloadName').val(file[1]);
			$('#downloadUrl').val(file[0]);
		}
	}
	
	function myupload() {
		var phone = $("#phone").val();
		var title = $("#title").val();
		var mail = $("#mail").val();
		var submitter = $("#submitter").val();
		var downloadName = $("#downloadName").val();
		var downloadUrl = $("#downloadUrl").val();
		var checkcode = $("#j_captcha").val();
		
		if (!/^1[3-9]\d{9}$/.test(phone)) {
			alert("手机号不正确");
			return;
		}
		
		if (!/^([0-9A-Za-z\-_\.]+)@([0-9a-z]+\.[a-z]{2,}(\.[a-z]{2,})?)$/g.test(mail)) {
			alert("邮箱不正确");
			return;
		}
		
		if (title.replace(/[\u0391-\uFFE5]/g,"aa").length > 100) {
			alert("标题长度不能超过100");
			return;
		}
		
		if (submitter.replace(/[\u0391-\uFFE5]/g,"aa").length > 50) {
			alert("提交人的名称长度不能超过50");
			return;
		}
		
		if (checkcode.length < 1) {
			alert("验证码不能为空");
			return;
		}
		
		
	    var params = {"title":title,"phone":phone,"mail":mail,"submitter":submitter,"downloadName":downloadName,"downloadUrl":downloadUrl, "checkcode":checkcode};
		$.ajax({
			type: "POST",
			url: "/distillSave.html",
			data: JSON.stringify(params),
			dataType : "json",
			contentType: "application/json; charset=utf-8",
			success: function(respData){
				var json = $.parseJSON(respData);
    
			    //$(this).bjuiajax('ajaxDone', json);
			    if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
					alert("上传成功");
					/* $("#title").value = ${upfile.title};
					$("#submitter").value = ${upfile.submitter};
					$("#phone").value = ${upfile.phone};					
					$("#mail").value = ${upfile.mail};
					$("#downloadName").value = ${upfile.downloadName};
					$("#downloadUrl").value = ${upfile.downloadUrl}; */
			    }else {
			    	alert("上传失败,"+json[BJUI.keys.message]);
			    }
			}
		});
	}

function changeImage(img){
        img.src=img.src +"?" +new Date().getTime();
}
</script>
</head>

<body>
	<!--头部-->
	<header class="navbar navbar-default" id="bjui-header"> <nav
		class="collapse navbar-collapse" id="bjui-navbar-collapse">
	<ul class="nav navbar-nav navbar-right">
		<li class="datetime"><div>
				<span id="bjui-date"></span><br> <i class="fa fa-clock-o"></i>
				<span id="bjui-clock"></span>
			</div></li>
	</ul>
	</nav> </header>
	<!--***-->

	<div id="bjui-container">
		<div class="bjui-pageContent">
			<p>数据提交——请注意，您提交的数据将被汉典医学用来进行相关分析，请谨慎提交！</p>
			<form action="/distillSave.html" id="memberForm" class="pageForm"
				method="post">
				<div class="pageFormContent" data-layout-h="0">
					<table class="table table-condensed table-hover" width="100%">
						<tbody>
							<tr>
								<td><label for="title" class="control-label x85">标题：</label>
									<input type="text" name="title" id="title"
									value="${upfile.title }" data-rule="required" size="60"></td>
							</tr>
							<tr>
								<td><label for="submitter" class="control-label x85">提交人：</label>
									<input type="text" name="submitter" id="submitter"
									value="${upfile.submitter }" data-rule="required" size="60"></td>
							</tr>
							<tr>
								<td><label for="phone" class="control-label x85">手机：</label>
									<input type="text" name="phone" id="phone"
									value="${upfile.phone }" data-rule="required" size="60"></td>
							</tr>
							<tr>
								<td><label for="mail" class="control-label x85">邮箱：</label>
									<input type="text" name="mail" id="mail"
									value="${upfile.mail }" data-rule="required" size="60"></td>
							</tr>
							<tr>
								<td><label class="control-label x85">文件：</label>
									<div style="display: inline-block; vertical-align: middle;">
										<div id="downloadName1" data-toggle="upload"
											data-uploader="/upload/file6.im?"
											data-file-size-limit="1024000000"
											data-file-type-exts="*.doc;*.pdf;*.docx" data-multi="true"
											data-auto=true data-on-upload-success="file_upload_success"
											data-icon="cloud-upload"></div>
										<input type="hidden" name="downloadName"
											value="${upfile.downloadName}" id="downloadName"> <input
											type="hidden" name="downloadUrl"
											value="${upfile.downloadUrl}" id="downloadUrl"><span
											id="downloadName_span">${upfile.downloadName}</span>
									</div></td>
							</tr>
							<tr>
							<td>
							<div class="form-group">
			    				<label for="j_captcha" class="t">验证码：</label> <input id="j_captcha" name="checkcode" type="text" class="form-control x164 in">
			    				<img id="captcha_img" src="/codeimg.code" onclick="changeImage(this)" class="m" alt="换一张" >
			    			</div>
							</td>
							</tr>
							<tr>
								<td>
									<div class="bjui-footBar">
										<button type="button" class="btn-default" id="uploadbtn"
											onclick="myupload();" data-icon="save">提交</button>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>

			</form>
		</div>
	</div>
	<!--***-->
	<footer id="bjui-footer"> Copyright &copy; 2018 <a
		href="javascript:void()" target="_blank">汉典医学</a> </footer>
</body>
</html>