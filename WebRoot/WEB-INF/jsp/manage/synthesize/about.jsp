<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="bjui-pageContent">
    <form action="/syn/save.im" id="managerForm" class="pageForm" method="post" data-toggle="validate">
        <input type="hidden" name="id" value="${about.id }">
        <div class="pageFormContent" data-layout-h="0">
            <table class="table table-condensed table-hover" width="100%">
                <tbody>
                    <tr>
                        <td>
                            <label for="content" class="control-label x85">内容：</label>
                           	<div style="display: inline-block; vertical-align: middle;">
	                        	<textarea name="content" id="content" class="content" style="width:760px;height:660px;" data-toggle="kindeditor" data-upload-json="/kindeditor/file_upload.im" data-file-manager-json="/kindeditor/file_manager.im" data-after-upload="E_afterUpload" data-after-select-file="E_afterSelectFile" data-after-select="E_afterSelect" data-minheight="30">
	                            	${about.content}
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
                <li><button type="submit" class="btn-default" data-icon="save">保存</button></li>
            </ul>
        </div>
    </form>
</div>