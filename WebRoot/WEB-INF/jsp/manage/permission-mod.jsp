<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="bjui-pageContent">
<form action="/permission/update.im" id="role" class="pageForm" data-toggle="validate">
        <input type="hidden" name="id" value="${permission.id }">
        <div class="pageFormContent" data-layout-h="0">
            <table class="table table-condensed table-hover" width="100%">
                <tbody>
                    <tr>
                        <td>
                            <label for="rolename" class="control-label x120">权限名称：</label>
                            <input type="text" name="title" id="title" value="${permission.title }" data-rule="required" size="15">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="rolename" class="control-label x120">排序：</label>
                            <input type="text" name="sort" id="sort" value="${permission.sort }" data-rule="required,number" size="15">
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