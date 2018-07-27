<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageContent">
    <form action="/hd/hospitalGradeEdit.im" id="memberForm" class="pageForm" method="post" data-toggle="validate">
        <input type="hidden" name="id" value="${hospitalGrade.id }">
        <div class="pageFormContent" data-layout-h="0">
            <table class="table table-condensed table-hover" width="100%">
                <tbody>
                   <tr>
                        <td>
                            <label for="name" class="control-label x120">名称：</label>
                            <input  name="name" id="name" data-rule="required" value="${hospitalGrade.name }"/>
                        </td>
                    </tr>
                   <tr>
                        <td>
                            <label for="sorts" class="control-label x120">排序：</label>
                            <input  name="sorts" id="sorts" data-rule="required" value="${hospitalGrade.sorts }"/>
                        </td>
                    </tr>
                     <tr>
                        <td >
                            <label for="types" class="control-label x120">类型：</label>
                            <select name="types" id="types" data-toggle="selectpicker">
                            <option value="2" ${hospitalGrade.types ==2?'selected':'' }>职称</option>
                            <option value="1" ${hospitalGrade.types ==1?'selected':'' }>医院等级</option>
                            </select>
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
