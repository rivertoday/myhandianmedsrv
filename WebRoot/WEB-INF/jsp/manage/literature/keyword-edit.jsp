<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageContent">
    <form action="/manageLiterature/keywordSave.im" id="memberForm" class="pageForm" method="post" data-toggle="validate">
        <input type="hidden" name="id" value="${userKeyword.id }">
        <div class="pageFormContent" data-layout-h="0">
            <table class="table table-condensed table-hover" width="100%">
                <tbody>
                   <tr>
                        <td>
                            <label for="name" class="control-label x120">名称：</label>
                            <input  name="name" id="name" data-rule="required" value="${userKeyword.name }"/>
                        </td>
                    </tr>
                     <tr>
                        <td >
                            <label for="clickCount" class="control-label x120">搜索次数：</label>
                           <input  name="clickCount" id="clickCount" data-rule="must" data-rule-must="[/^[0-9]+$/,'必须是数字']" value="${userKeyword.clickCount }"/>
                        </td>
                    </tr>
                     <tr>
                        <td >
                            <label for="title" class="control-label x120">是否推荐：</label>
                            <select name="status" id="status" data-toggle="selectpicker">
                            <option value="2" ${userKeyword.status ==2?'selected':'' }>推荐中</option>
                            <option value="1" ${userKeyword.status ==1?'selected':'' }>未推荐</option>
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
