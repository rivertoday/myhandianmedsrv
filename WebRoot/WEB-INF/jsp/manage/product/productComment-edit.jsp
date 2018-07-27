<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="/client/js/shengShiXian.js"></script>
<script type="text/javascript">
function pic_upload_success(file, data) {
    var json = $.parseJSON(data);
    
    $(this).bjuiajax('ajaxDone', json);
    if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
        $('#headImg').val(json.navTabId).trigger('validate')
        $('#headimg_span').html('<img src="'+ json.navTabId +'" width="100" />')
    }
}

$(".bann").click(function(){
	var regex=/^[\u4e00-\u9fa5]{2,20}$/;
	var professional=$("#professional").val();
	var department=$("#department").val();
	
	if(!regex.test(department)){
	alert("科室只能输入汉字2-20位");
	return;
	}
	
	if(!regex.test(professional)){
	alert("职称只能输入汉字2-20位");
	return;
	}
	
	$("input[name='state']").val($(this).attr("data-state"));
	$("form").submit();
})
</script>
<div class="bjui-pageContent">
    <form action="/manageProduct/productCommentSave.im" id="memberForm" class="pageForm" method="post" data-toggle="validate">
        <input type="hidden" name="id" value="${productComment.id }" >
        <input type="hidden" name="productId" value="${!empty productId?productId:productComment.productId}" >
        <input type="hidden" name="state" value="${productComment.state}" >
        <div class="pageFormContent" data-layout-h="0">
            <table class="table table-condensed table-hover" width="100%">
                <tbody>
                	<tr>
                        <td>
                            <label for="username" class="control-label x85">医师姓名：</label>
                            <input type="text" name="userName" id="userName" value="${productComment.userName }"  data-rule="required"  size="15">
                        </td>
                    </tr>
                    <tr>
                    	<td>
                           <label class="control-label x85">头像：</label>
	                            <div style="display: inline-block; vertical-align: middle;">
	                                <div id="headimg2" data-toggle="upload" data-uploader="/upload/file.im?sessionid=?" 
	                                    data-file-size-limit="1024000000"
	                                    data-file-type-exts="*.jpg;*.png;*.gif;*.mpg;*.gif"
	                                    data-multi="true"
	                                    data-on-upload-success="pic_upload_success"
	                                    data-icon="cloud-upload"></div>
	                                <input type="hidden" name="headImg" value="${productComment.headImg}" id="headImg" data-target="#headImg .bjui-upload-select-file">
	                                <span id="headimg_span"><img src="${productComment.headImg}" width="100"/></span>
	                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="username" class="control-label x85">医院：</label>
                            <input type="text" name="hospitalName" id="hospitalName" value="${productComment.hospitalName}"  data-rule="required" size="15">
                        </td>
                    </tr>
                    <tr>
                    	<td>
                            <label for="department" class="control-label x85">科室：</label>
                            <input  type="text" name="department" id="department" value="${productComment.department }" data-rule="required">
                        </td>
                    </tr>
                    <tr>
                        
                        <td>
                            <label for="professional" class="control-label x85">职称：</label>
                           <input  type="text" name="professional" id="professional" value="${productComment.professional }" data-rule="required">
                           
                        </td>
                    </tr>
                     <tr>
                        <td>
                            <label for="content" class="control-label x85">评论内容</label>
                        	<textarea rows="10" cols="30" name="content" id="content" data-rule="required">${productComment.content}</textarea>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="bjui-footBar">
            <ul>
                <li><button type="button" class="btn-close" data-icon="close">取消</button></li>
                <li><button type="button" class="btn-default bann" data-icon="save" data-state="2">保存</button></li>
                <li><button type="button" class="btn-default bann" data-icon="save" data-state="1">发布</button></li>
            </ul>
        </div>
    </form>
</div>