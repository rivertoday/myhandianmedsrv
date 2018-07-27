<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
function pic_upload_success(file, data) {
    var json = $.parseJSON(data)
    $(this).bjuiajax('ajaxDone', json)
    if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
        $('#headimg').val(json.navTabId).trigger('validate')
        $('#headimg_span').html('<img src="'+ json.navTabId +'" width="100" />')
    }
}
function pic_upload_success1(file, data) {
    var json = $.parseJSON(data)
    $(this).bjuiajax('ajaxDone', json)
    if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
        $('#headimg30').val(json.navTabId).trigger('validate')
        $('#headimg30_span').html('<img src="'+ json.navTabId +'" width="100" />')
    }
}
function pic_upload_success2(file, data) {
    var json = $.parseJSON(data)
    $(this).bjuiajax('ajaxDone', json)
    if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
        $('#headimg31').val(json.navTabId).trigger('validate')
        $('#headimg31_span').html('<img src="'+ json.navTabId +'" width="100" />')
    }
}
function pic_upload_success3(file, data) {
    var json = $.parseJSON(data)
    $(this).bjuiajax('ajaxDone', json)
    if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
        $('#headimg32').val(json.navTabId).trigger('validate')
        $('#headimg32_span').html('<img src="'+ json.navTabId +'" width="100" />')
    }
}
function pic_upload_success4(file, data) {
    var json = $.parseJSON(data)
    $(this).bjuiajax('ajaxDone', json)
    if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
        $('#headimg33').val(json.navTabId).trigger('validate')
        $('#headimg33_span').html('<img src="'+ json.navTabId +'" width="100" />')
    }
}
function pic_upload_success5(file, data) {
    var json = $.parseJSON(data)
    $(this).bjuiajax('ajaxDone', json)
    if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
        $('#headimg34').val(json.navTabId).trigger('validate')
        $('#headimg34_span').html('<img src="'+ json.navTabId +'" width="100" />')
    }
}
</script>
<div class="bjui-pageContent">
    <form action="/clientManage/updateClientManage.im" id="managerForm" class="pageForm" method="post" data-toggle="validate">
        <div class="pageFormContent" data-layout-h="0">
        <input type="hidden" name="id" value="${clientManage.id}"/>
        <input type="hidden" name="types" value="${param.type}"/>
            <table class="table table-condensed table-hover" width="100%">
                <tbody>
                	<tr>
                		<td>标题/关键字</td>
                		<td><input type="text" name="title" value=" ${clientManage.title}" size="100"></td>
                	</tr>
                	<tr>
                		<td>超链接</td>
                		<td><input type="text" name="linkurl" value=" ${clientManage.linkurl}" size="100"></td>
                	</tr>
     				<tr>
                        <td colspan="2">
                           <label class="control-label x85">
           					<c:choose>
           						<c:when test="${param.types==1}">
           							首页上方广告条：
           						</c:when>
           						<c:when test="${param.types==5}">
           							首页下方广告条：
           						</c:when>
           						<c:when test="${param.types==3}">
           							banner形象图：
           						</c:when>
           					</c:choose>                
                           </label>
                           <c:if test="${param.type==1 || param.type==5}">
                            <div style="display: inline-block; vertical-align: middle;">
                                <div id="_headimgDiv"  data-toggle="upload" data-uploader="/upload/file.im?sessionid=?" 
                                    data-file-size-limit="1024000000"
                                    data-file-type-exts="*.jpg;*.png;*.gif;*.mpg;*.gif"
                                    data-multi="true"
                                    data-on-upload-success="pic_upload_success"
                                    data-icon="cloud-upload"></div>
                                <input type="hidden" name="image" value="${empty clientManage.image?'/client/images/defaultimg.jpg':clientManage.image }" id="headimg"  data-rule="required" data-target="#headimg .bjui-upload-select-file">
                                <span id="headimg_span"><img src="${empty clientManage.image?'/client/images/defaultimg.jpg':clientManage.image}" /></span>
                            </div>
                            </c:if>
                           <c:if test="${param.type==3}">
             				 <div style="display: inline-block; vertical-align: middle;">
                                <div id="headimg0" data-toggle="upload" data-uploader="/upload/file.im?sessionid=?" 
                                    data-file-size-limit="1024000000"
                                    data-file-type-exts="*.jpg;*.png;*.gif;*.mpg;*.gif"
                                    data-multi="true"
                                    data-on-upload-success="pic_upload_success1"
                                    data-icon="cloud-upload"></div>
                                <input type="hidden" name="image" value="${empty imgArray[0]?'/client/images/defaultimg.jpg':imgArray[0] }" id="headimg30"  data-rule="required" data-target="#headimg30 .bjui-upload-select-file">
                                <span id="headimg30_span"><img src="${empty imgArray[0]?'/client/images/defaultimg.jpg':imgArray[0]}" /></span>
                                
                                
                                 <div id="headimg1" data-toggle="upload" data-uploader="/upload/file.im?sessionid=?" 
                                    data-file-size-limit="1024000000"
                                    data-file-type-exts="*.jpg;*.png;*.gif;*.mpg;*.gif"
                                    data-multi="true"
                                    data-on-upload-success="pic_upload_success2"
                                    data-icon="cloud-upload"></div>
                                <input type="hidden" name="image" value="${empty imgArray[1]?'/client/images/defaultimg.jpg':imgArray[1] }" id="headimg31" data-rule="required" data-target="#headimg31 .bjui-upload-select-file">
                                <span id="headimg31_span"><img src="${empty imgArray[1]?'/client/images/defaultimg.jpg':imgArray[1]}" /></span>
                                
                                <div id="headimg2" data-toggle="upload" data-uploader="/upload/file.im?sessionid=?" 
                                    data-file-size-limit="1024000000"
                                    data-file-type-exts="*.jpg;*.png;*.gif;*.mpg;*.gif"
                                    data-multi="true"
                                    data-on-upload-success="pic_upload_success3"
                                    data-icon="cloud-upload"></div>
                                <input type="hidden" name="image" value="${empty imgArray[2]?'/client/images/defaultimg.jpg':imgArray[2]}" id="headimg32"  data-rule="required" data-target="#headimg32 .bjui-upload-select-file">
                                <span id="headimg32_span"><img src="${empty imgArray[2]?'/client/images/defaultimg.jpg':imgArray[2]}" /></span>
                                
                                
                                 <div id="headimg3" data-toggle="upload" data-uploader="/upload/file.im?sessionid=?" 
                                    data-file-size-limit="1024000000"
                                    data-file-type-exts="*.jpg;*.png;*.gif;*.mpg;*.gif"
                                    data-multi="true"
                                    data-on-upload-success="pic_upload_success4"
                                    data-icon="cloud-upload"></div>
                                <input type="hidden" name="image" value="${empty imgArray[3]?'/client/images/defaultimg.jpg':imgArray[3] }" id="headimg33"  data-rule="required" data-target="#headimg33 .bjui-upload-select-file">
                                <span id="headimg33_span"><img src="${empty imgArray[3]?'/client/images/defaultimg.jpg':imgArray[3]}" /></span>
                                
                                
                                <div id="headimg4" data-toggle="upload" data-uploader="/upload/file.im?sessionid=?" 
                                    data-file-size-limit="1024000000"
                                    data-file-type-exts="*.jpg;*.png;*.gif;*.mpg;*.gif"
                                    data-multi="true"
                                    data-on-upload-success="pic_upload_success5"
                                    data-icon="cloud-upload"></div>
                                <input type="hidden" name="image" value="${empty imgArray[4]?'/client/images/defaultimg.jpg':imgArray[4] }" id="headimg34" data-rule="required" data-target="#headimg34 .bjui-upload-select-file">
                                <span id="headimg34_span"><img src="${empty imgArray[4]?'/client/images/defaultimg.jpg':imgArray[4]}" /></span>
                            </div>
                           </c:if>
                        </td>
                      </tr>
                </tbody>
            </table>
        </div>
        <div class="bjui-footBar">
            <ul>
                <li><button type="button" class="btn-close" data-icon="close">取消</button></li>
                <li>
                	<c:if test="${!empty sessionManagerRole[206] && param.type==1 }">
                		<button type="submit" class="btn-default" data-icon="save">保存</button>
                	</c:if>
                	<c:if test="${!empty sessionManagerRole[208] && param.type==3 }">
                		<button type="submit" class="btn-default" data-icon="save">保存</button>
                	</c:if>
                	<c:if test="${!empty sessionManagerRole[209] && param.type==5 }">
                		<button type="submit" class="btn-default" data-icon="save">保存</button>
                	</c:if>
                </li>
            </ul>
        </div>
    </form>
</div>