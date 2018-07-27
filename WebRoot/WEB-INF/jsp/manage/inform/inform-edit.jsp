<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="/client/js/shengShiXian.js"></script>
<script type="text/javascript">
function pic_upload_success(file, data) {
    var json = $.parseJSON(data)
    
    $(this).bjuiajax('ajaxDone', json)
    if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
        $('#messageImage').val(json.navTabId).trigger('validate')
        $('#image_span').html('<img src="'+ json.navTabId +'" width="100" />')
    }
}


function clilookup(){
			$("#test").attr("data-url","/manageInform/memberList.im");
			$("#test").attr("data-toggle","dialog");
	
	
}

var userName="";
var userId="";
function getDetail(result){
var objArry=result.split("|");
	//alert(objArry.length)
	for(var i=0;i<objArry.length;i++){
		var mapJson="{"+objArry[i]+"}";
		 var obj = eval("("+mapJson+")");
		 /*
		 if(i==objArry.length-1){alert(1);
				 userId+=obj.userId;
				 if(obj.userName!=undefined || obj.userName!=""){
				 userName+=obj.userName;
				 }
		 
		 }else{alert(2);
				 userId+=obj.userId+",";
				 if(obj.userName!=undefined || obj.userName!=""){
			 		userName+=obj.userName+",";
			 	 }
		 }
		 */
		 userId+=obj.userId+",";
		 
		 if(obj.userName!=undefined || obj.userName!=""){
	 		userName+=obj.userName+",";
	 	 }
	}
	
	//alert(userId);alert(userName);
	var userId2 = "";
	var userName2 = "";
	
	if(userId != undefined || userId != ""){
		userId2 = userId.substring(0, userId.length-1);
	}
	
	if(userName != undefined || userName != ""){
		userName2 = userName.substring(0, userName.length-1);
	}
	
	$("#userDetailId").val(userId2);
	$("#userName").val(userName2);
	//alert($("#userDetailId").val());alert($("#userName").val());
}

$("#submitBtn").click(function(){
	var userDetailId=$("#userDetailId").val();
	//alert(!$("#divcreate").is(":hidden"))
	if(!$("#divcreate").is(":hidden")){
		if(userDetailId=="" || userDetailId==null){
			alert("请选择用户");
			return;
		}
	}
	$("form").submit();
})

$(function(){
	
	$("select[name='chooseUser']").change(function(){
		//alert($(this).val());
		if($(this).val()==1){
			$("#divcreate").hide();
		}else{
			$("#divcreate").show();
		}
	});
	
})

</script>
<div class="bjui-pageContent">
    <form action="/manageInform/informSave.im" id="memberForm" class="pageForm" method="post" data-toggle="validate">
        <div class="pageFormContent" data-layout-h="0">
            <table class="table table-condensed table-hover" width="100%">
                <tbody>
                	<tr>
                         <td>
                            <label for="show" class="control-label x100" style="float:left;">选择用户：</label>
                            <select name="chooseUser" style="float:left;">
                            		<option value="1">所有用户</option>
                            		<option value="2">选择用户</option>
                            </select>
                            <div id="divcreate" style="display: none;width:300px; float:left;">
                        	<button type="button"  id="test" onclick="clilookup();"  data-toggle="dialog" data-mask="true" data-title="查找用户" data-width="1000" data-height="700" class="btn-default"  data-id="lookupid">查找用户</button>
                            <input type="hidden" name="userDetailId" id="userDetailId"/>
                        	<input type="text" id="userName" name="userName" readonly="readonly" style="width:150px">
                       		</div>
                        </td>
                    </tr>
                	<tr>
                        <td>
                            <label for="title" class="control-label x85">标题：</label>
                            <input type="text" name="title" id="title" value=""  data-rule="required" data-rule-loginName="[/^1[3-9]\d{9}$/,'手机号不正确']" size="15">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="name" class="control-label x85">类型：</label>
                            <select name="types" id="types" data-toggle="selectpicker" data-width="100">
                            	<option value="1" ${us.types == 1?selected:'' }>系统广播消息</option>
                            	<option value="2" ${us.types == 2?selected:'' }>活动通知消息</option>
                            </select>
                        </td>
                        
                    </tr>
                    <tr>
                        <td>
                           <label class="control-label x85">图片：</label>
	                            <div style="display: inline-block; vertical-align: middle;">
	                                <div id="image" data-toggle="upload" data-uploader="/upload/file.im?sessionid=?" 
	                                    data-file-size-limit="1024000000"
	                                    data-file-type-exts="*.jpg;*.png;*.gif;*.mpg;*.gif"
	                                    data-multi="true"
	                                    data-on-upload-success="pic_upload_success"
	                                    data-icon="cloud-upload"></div>
	                                <input type="hidden" name="image" value="${us.image}" id="messageImage" data-target="#image .bjui-upload-select-file">
	                                <span id="image_span"><img src="${us.image}" width="100"/></span>
	                            </div>
                        </td>
                      </tr>
                   <tr>
                        <td>
                            <label for="content" class="control-label x85">内容：</label>
                        	<textarea name="content" id="content" style="width:360px;height:60px;" data-rule="required">
	                            	${us.content}
                                </textarea>
                        </td>
                    </tr>
                     <tr>
                        <td>
                            <label for="content" class="control-label x85">详情：</label>
                        	<div style="display: inline-block; vertical-align: middle;">
	                        	<textarea name="detail" id="detail" class="detail" style="width:560px;height:460px;" data-toggle="kindeditor" data-upload-json="/kindeditor/file_upload.im" data-file-manager-json="/kindeditor/file_manager.im" data-after-upload="E_afterUpload" data-after-select-file="E_afterSelectFile" data-after-select="E_afterSelect" data-minheight="30">
	                            	${us.detail}
                                </textarea>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="bjui-footBar">
            <ul>
                <li><button type="button" class="btn-close" data-icon="close">取消</button></li>
                <li><button type="button" id="submitBtn" class="btn-default" data-icon="save">发送</button></li>
            </ul>
        </div>
    </form>
</div>
