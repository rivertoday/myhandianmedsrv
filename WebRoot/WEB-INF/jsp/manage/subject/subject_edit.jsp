<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
function pic_upload_success(file, data) {
    var json = $.parseJSON(data)
    
    $(this).bjuiajax('ajaxDone', json)
    if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
        $('#headimg').val(json.navTabId).trigger('validate')
        $('#headimg_span').html('<img src="'+ json.navTabId +'" width="100" />')
    }
}
</script>
<div class="bjui-pageContent">
    <form action="/managesubject/subject_save.im" id="memberForm" class="pageForm" method="post" data-toggle="validate">
        <input type="hidden" name="id" value="${subject.id }" >
        <div class="pageFormContent" data-layout-h="0">
            <table class="table table-condensed table-hover" width="100%">
                <tbody>
                	<tr>
                        <td>
                            <label for="title" class="control-label x85">标题：</label>
                            <input type="text" name="title" id="title" value="${subject.title}"  data-rule="required"  size="30">
                        </td>
                    </tr>
                	<tr>
                        <td>
                            <label for="instruction" class="control-label x85">说明：</label>
                            <input type="text" name="instruction" id="instruction" value="${subject.instruction}"  data-rule="required"  size="30">
                        </td>
                    </tr>
                	<tr>
                        <td>
                            <label for="title" class="control-label x85">类别：</label>
                            <select name="types" id="types" data-toggle="selectpicker">
			 					<option value="1" >原始8分</option>
			 					<option value="2" <c:if test="${subject.types == 2 }">selected="selected"</c:if>>原始7分</option>
			 					<option value="3" <c:if test="${subject.types == 3 }">selected="selected"</c:if>>累积分</option>
			 					<option value="4" <c:if test="${subject.types == 4 }">selected="selected"</c:if>>多选一结果</option>
			 					<option value="5" <c:if test="${subject.types == 5 }">selected="selected"</c:if>>一题一答案</option>
			 				</select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                           <label class="control-label x85">图片：</label>
	                            <div style="display: inline-block; vertical-align: middle;">
	                                <div id="headimg2" data-toggle="upload" data-uploader="/upload/file.im?sessionid=?" 
	                                    data-file-size-limit="1024000000"
	                                    data-file-type-exts="*.jpg;*.png;*.gif;*.mpg;*.gif"
	                                    data-multi="true"
	                                    data-on-upload-success="pic_upload_success"
	                                    data-icon="cloud-upload"></div>
	                                <input type="hidden" name="image" value="${subject.image}" id="headimg" data-target="#headimg .bjui-upload-select-file">
	                                <span id="headimg_span"><img src="${subject.image}" width="100"/></span>
	                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="bjui-footBar">
            <ul>
                <li><button type="button" class="btn-close" data-icon="close">取消</button></li>
                <li><button type="submit" class="btn-default"  data-icon="save" data-status="2">保存</button></li>
            </ul>
        </div>
    </form>
</div>
