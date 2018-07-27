<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageContent">
    <form action="/syn/versionSave.im" id="memberForm" class="pageForm" method="post" data-toggle="validate">
        <input type="hidden" name="id" value="${vs.id }" >
        <div class="pageFormContent" data-layout-h="0">
            <table class="table table-condensed table-hover" width="100%">
                <tbody>
                	<tr>
                        <td>
                            <label for="versionId" class="control-label x85">版本号：</label>
                            <input type="text" name="spare1" id="spare1" value="${vs.spare1 }"  data-rule="required" size="15">
                        </td>
                    </tr>
                	<tr>
                        <td>
                            <label for="versionName" class="control-label x85">版本名称：</label>
                            <input type="text" name="name" id="name" value="${vs.name }"  data-rule="required" size="20">
                        </td>
                    </tr>
                  	<tr>
                        <td>
                            <label for="versionLink" class="control-label x85">安卓下载地址：</label>
                            <input type="text" name="link" id="link" value="${vs.link }"  data-rule="required" size="50">
                        </td>                        
                    </tr>
                    <tr>
                    	<td>
                            <label for="versionLink2" class="control-label x85">苹果下载地址：</label>
                            <input type="text" name="spare2" id="spare2" value="${vs.spare2 }"  data-rule="required" size="50">
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
