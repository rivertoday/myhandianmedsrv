<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="/client/js/shengShiXian.js"></script>
<script type="text/javascript">
function pic_upload_success(file, data) {
    var json = $.parseJSON(data)
    
    $(this).bjuiajax('ajaxDone', json)
    if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
        $('#image').val(json.navTabId).trigger('validate')
        $('#image_span').html('<img src="'+ json.navTabId +'" width="100" />')
    }
}

</script>
<div class="bjui-pageContent">
    <form action="/syn/bannerSave.im" id="memberForm" class="pageForm" method="post" data-toggle="validate">
        <input type="hidden" name="bannerId" value="${banner.id }" >
        <div class="pageFormContent" data-layout-h="0">
            <table class="table table-condensed table-hover" width="100%">
                <tbody>
                    <tr>
                        <td>
                            <label for="link" class="control-label x85">链接：</label>
                            <input type="text" name="link" id="link" value="${banner.link}"  data-rule="required" size="15">
                        </td>
                    </tr>
                     <tr>
                        <td>
                            <label for="sorts" class="control-label x85">排序：</label>
                            <input type="text" name="sorts" id="sorts" value="${banner.sorts}"  data-rule="sorts"  data-rule-sorts="[/^[0-9]{1,2}$/,'必须是1到2位数字']" size="15">
                        </td>
                    </tr>
                     <tr>
                        <td>
                            <label for="types" class="control-label x85">类型：</label>
                            <input type="radio" name="types" <c:if test="${empty banner.types || banner.types == 1 }">checked="checked"</c:if> value="1" />万方首页
                            <input type="radio" name="types" <c:if test="${banner.types == 2 }">checked="checked"</c:if> value="2" />新首页
                        </td>
                    </tr>
                    <tr>
                        <td>
                           <label class="control-label x85">头像：</label>
	                            <div style="display: inline-block; vertical-align: middle;">
	                                <div id="image2" data-toggle="upload" data-uploader="/upload/file.im?sessionid=?" 
	                                    data-file-size-limit="1024000000"
	                                    data-file-type-exts="*.jpg;*.png;*.gif;*.mpg;*.gif"
	                                    data-multi="true"
	                                    data-on-upload-success="pic_upload_success"
	                                    data-icon="cloud-upload"></div>
	                                <input type="hidden" name="image" value="${banner.image}" id="image" data-target="#image .bjui-upload-select-file">
	                                <span id="image_span"><img src="${banner.image}" width="100"/></span>
	                            </div>
                        </td>
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