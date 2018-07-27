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

function file_upload_success(file, data) {
    var json = $.parseJSON(data);
    $(this).bjuiajax('ajaxDone', json);
    if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
    	var file=json.navTabId.split(",");
    	//alert(file[1])
       $('#downloadUrl').val(file[0]).trigger('validate');
       $('#downloadName').val(file[1]).trigger('validate');
       $('#downloadName_span').html(file[1]);
    }
}


$(".bann").click(function(){
	$("input[name='state']").val($(this).attr("data-state"));
	$("form").submit();
})
</script>
<div class="bjui-pageContent">
    <form action="/manageProduct/productLiteratureSave.im" id="memberForm" class="pageForm" method="post" data-toggle="validate">
        <input type="hidden" name="id" value="${productLiterature.id }" >
        <input type="hidden" name="productId" value="${!empty productId?productId:productLiterature.productId}" >
        <input type="hidden" name="state" value="${productLiterature.state}" >
        <div class="pageFormContent" data-layout-h="0">
            <table class="table table-condensed table-hover" width="100%">
                <tbody>
                	<tr>
                        <td>
                            <label for="title" class="control-label x85">标题：</label>
                            <input type="text" name="title" id="title" value="${productLiterature.title }"  data-rule="required"  size="15">
                        </td>
                    </tr>
                	<tr>
                        <td>
                            <label for="issue" class="control-label x85">期刊号：</label>
                            <input type="text" name="issue" id="issue" value="${productLiterature.issue }"  data-rule="required"  size="15">
                        </td>
                    </tr>
                    <%--
                	<tr>
                        <td>
                            <label for="publishTime" class="control-label x85">发布日期：</label>
                        	<input type="text" name="publishTime" id="publishTime" value="<fmt:formatDate value='${productLiterature.publishTime }' pattern='yyyy-MM' />" data-pattern="yyyy-MM" data-toggle="datepicker" size="15">
                        </td>
                    </tr>
                     --%>
                    <tr>
                    	<td>
                           <label class="control-label x85">图片：</label>
	                            <div style="display: inline-block; vertical-align: middle;">
	                                <div id="headimg2" data-toggle="upload" data-uploader="/upload/file.im?sessionid=?" 
	                                    data-file-size-limit="1024000000"
	                                    data-file-type-exts="*.jpg;*.png;*.gif;*.mpg;*.gif"
	                                    data-multi="true"
	                                    data-auto=true
	                                    data-on-upload-success="pic_upload_success"
	                                    data-icon="cloud-upload"></div>
	                                <input type="hidden" name="image" value="${productLiterature.image}" id="headImg" data-target="#headImg .bjui-upload-select-file">
	                                <span id="headimg_span"><img src="${productLiterature.image}" width="100"/></span>
	                            </div>
                        </td>
                    </tr>
                    <tr>
                    	<td>
                           <label class="control-label x85">文件：</label>
	                            <div style="display: inline-block; vertical-align: middle;">
	                                <div id="downloadName1" data-toggle="upload" data-uploader="/upload/file6.im?sessionid=?" 
	                                    data-file-size-limit="1024000000"
	                                    data-file-type-exts="*.doc;*.pdf;*.docx"
	                                    data-multi="true"
	                                    data-auto=true
	                                    data-on-upload-success="file_upload_success"
	                                    data-icon="cloud-upload"></div>
	                                <input type="hidden" name="downloadName" value="${productLiterature.downloadName}" id="downloadName" >
	                                <input type="hidden" name="downloadUrl" value="${productLiterature.downloadUrl}" id="downloadUrl" >
	                                <span id="downloadName_span">${productLiterature.downloadName}</span>
	                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="author" class="control-label x85">作者：</label>
                            <input type="text" name="author" id="author" value="${productLiterature.author}"  data-rule="required" size="15">
                        </td>
                    </tr>
                     <tr>
                        <td>
                            <label for="summary" class="control-label x85">摘要</label>
                        	<textarea rows="10" cols="30" name="summary" id="summary" data-rule="required">${productLiterature.summary}</textarea>
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