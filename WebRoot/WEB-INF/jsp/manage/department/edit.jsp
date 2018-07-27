<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="bjui-pageContent">
    <form action="/department/save.im" id="managerForm" class="pageForm" method="post" data-toggle="validate">
        <input type="hidden" name="id" value="${department.id }">
        <input type="hidden" name="parentId" value="${department.parentId }">
        <div class="pageFormContent" data-layout-h="0">
            <table class="table table-condensed table-hover" width="100%">
                <tbody>
                   <tr>
                        <td>
                            <label for="name" class="control-label x85">名称：</label>
                            <input type="text" name="name" id="name" value="${department.name }"  data-rule="required" size="15">
                        </td>
                    </tr>
                   <tr>
                        <td>
                            <label for="sorts" class="control-label x85">排序：</label>
                            <input type="text" name="sorts" id="sorts" value="${department.sorts }"  data-rule="required" size="15">
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