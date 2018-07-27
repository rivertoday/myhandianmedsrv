<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<script type="text/javascript">

function pic_upload_success1(file, data) {
    var json = $.parseJSON(data)
    
    $(this).bjuiajax('ajaxDone', json)
    if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
        $('[name="logoimg"]').val(json.navTabId).trigger('validate')
        $('#logoimg_pic').html('<img src="'+ json.navTabId +'" width="100" />')
    }
}
function pic_upload_success2(file, data) {
    var json = $.parseJSON(data)
    
    $(this).bjuiajax('ajaxDone', json)
    if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
        $('[name="logohimg"]').val(json.navTabId).trigger('validate')
        $('#logohimg_pic').html('<img src="'+ json.navTabId +'" width="100" />')
    }
}
</script>
<div class="bjui-pageContent">
    <form action="/menu/em.im" id="em" class="pageForm" data-toggle="validate">
        <input type="hidden" name="id" value="${menu.id }">
        <input type="hidden" name="parentid" value="${parentid }"/>
        <div class="pageFormContent" data-layout-h="-260">
            <table class="table table-condensed table-hover" width="100%">
                <tbody>
                	<tr>
                		<td colspan="4">
                            <label for="title" class="control-label x85">标题：</label>
                            <input type="text" name="title" id="title" placeholder="输入标题" value="${menu.title }" data-rule="required" size="30">
                        </td>
                	</tr>
                	<tr>
                		<td colspan="2">
                			<label class="control-label x85">小图：</label>
                            <div style="display: inline-block; vertical-align: middle;">
                                <div id="logoimg" data-toggle="upload" data-uploader="/upload/file.im?sessionid=?" 
                                    data-file-size-limit="1024000000"
                                    data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                                    data-multi="true"
                                    data-on-upload-success="pic_upload_success1"
                                    data-icon="cloud-upload"></div>
                                <input type="hidden" name="logoimg" value="" data-target="#logoimg .bjui-upload-select-file">
                                <span id="logoimg_pic"><img src="${empty menu.logoimg?'/client/images/noimg.gif':menu.logoimg}" width="100" /></span>
                            </div>
                		</td>
                		<td colspan="2">
                			<label class="control-label x85">大图：</label>
                            <div style="display: inline-block; vertical-align: middle;">
                                <div id="logohimg" data-toggle="upload" data-uploader="/upload/file.im?sessionid=?" 
                                    data-file-size-limit="1024000000"
                                    data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                                    data-multi="true"
                                    data-on-upload-success="pic_upload_success2"
                                    data-icon="cloud-upload"></div>
                                <input type="hidden" name="logohimg" value="" data-target="#logohimg .bjui-upload-select-file">
                                <span id="logohimg_pic"><img src="${empty menu.logohimg?'/client/images/noimg.gif':menu.logohimg }" width="100" /></span>
                            </div>
                		</td>
                	</tr>
                	<tr>
                		<td colspan="2">
                            <label for="linkurl" class="control-label x85">链接：</label>
                            <input type="text" name="linkurl" id="linkurl" placeholder="强制链接" value="${menu.linkurl }" size="30">
                        </td>
                        <td colspan="2">
                            <label for="pagekeys" class="control-label x85">关键字：</label>
                            <input type="text" name="pagekeys" id="pagekeys" value="${menu.pagekeys }" data-toggle="tags" data-url="ajaxTags.jsp" data-width="280" size="15" placeholder="输入关键字，回车提交">
                        </td>
                	</tr>
                	<tr>
                		<td colspan="4">
                            <label for="description" class="control-label x85">介绍：</label>
                            <textarea rows="3" name="description" id="description" cols="60">${menu.description }</textarea>
                        </td>
                	</tr>
                	<tr>
                		<td>
                            <label for="isshow" class="control-label x85">是否显示：</label>
                            <input type="radio" name="isshow" id="isshow1" data-toggle="icheck" value="0" ${menu.isshow eq 1?'':'checked' } data-rule="checked" data-label="是&nbsp;&nbsp;">
                            <input type="radio" name="isshow" id="isshow2" data-toggle="icheck" value="1" ${menu.isshow eq 1?'checked':'' }  data-label="否">
                        </td>
                        <td>
                            <label for="istop" class="control-label x85">是否置顶：</label>
                            <input type="radio" name="istop" id="istop1" data-toggle="icheck" value="0" ${menu.istop eq 0?'checked':'' } data-rule="checked" data-label="是&nbsp;&nbsp;">
                            <input type="radio" name="istop" id="istop2" data-toggle="icheck" value="1" ${menu.istop eq 0?'':'checked' } data-label="否">
                        </td>
                        <td>
                            <label for="isindex" class="control-label x85">是否首页显示：</label>
                            <input type="radio" name="isindex" id="isindex1" data-toggle="icheck" value="0" ${menu.isindex eq 0?'checked':'' } data-rule="checked" data-label="是&nbsp;&nbsp;">
                            <input type="radio" name="isindex" id="isindex2" data-toggle="icheck" value="1" ${menu.isindex eq 0?'':'checked' } data-label="否">
                        </td>
                        <td>
                            <label for="isrecommend" class="control-label x85">是否推荐：</label>
                            <input type="radio" name="isrecommend" id="isrecommend1" data-toggle="icheck" value="0" ${menu.isrecommend eq 0?'checked':'' } data-rule="checked" data-label="是&nbsp;&nbsp;">
                            <input type="radio" name="isrecommend" id="isrecommend2" data-toggle="icheck" ${menu.isrecommend eq 0?'':'checked' } value="1" data-label="否">
                        </td>
                	</tr>
                	<tr>
                		<td colspan="2">
                            <label for="sort" class="control-label x85">排序：</label>
                            <input type="text" name="sort" id="sort" value="${menu.sort }" size="15">
                        </td colspan="2">
                        <td>
                            <label for="imgalt" class="control-label x85">图片标题：</label>
                            <input type="text" name="imgalt" id="imgalt" value="${menu.imgalt }" size="15">
                        </td>
                	</tr>
                    <tr>
                        <td colspan="4">
                            <label for="j_custom_content" class="control-label x85">内容编辑：</label>
                            <div style="display: inline-block; vertical-align: middle;">
                                <textarea name="content" id="content" class="content" style="width: 700px;" data-toggle="kindeditor" data-upload-json="/kindeditor/file_upload.im" data-file-manager-json="/kindeditor/file_manager.im" data-after-upload="E_afterUpload" data-after-select-file="E_afterSelectFile" data-after-select="E_afterSelect" data-minheight="200">
                                    ${menu.content }
                                </textarea>
                            </div>
                        </td>
                    </tr>
                    <tr>
                		<td colspan="4">
                            <label for="standbyfield1" class="control-label x285">备用字段Field1：</label>
                            <input type="text" name="standbyfield1" id="standbyfield1" placeholder="备用字段" value="${menu.standbyfield1 }" size="30">
                        </td>
                	</tr>
                	<tr>
                		<td colspan="4">
                            <label for="standbyfield2" class="control-label x285">备用字段Field2：</label>
                            <input type="text" name="standbyfield2" id="standbyfield2" placeholder="备用字段" value="${menu.standbyfield2 }" size="30">
                        </td>
                	</tr>
                	<tr>
                		<td colspan="4">
                            <label for="standbyfield3" class="control-label x285">备用字段Field3：</label>
                            <input type="text" name="standbyfield3" id="standbyfield3" placeholder="备用字段" value="${menu.standbyfield3 }" size="30">
                        </td>
                	</tr>
                	<tr>
                		<td colspan="4">
                            <label for="standbyfield4" class="control-label x285">备用字段Field4：</label>
                            <input type="text" name="standbyfield4" id="standbyfield4" placeholder="备用字段" value="${menu.standbyfield4 }" size="30">
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