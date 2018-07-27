<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageContent">
    <div class="bjui-headBar">
        <ul>
            <li class="left"><button type="button" onclick="toAdd();" class="btn-edit">添加</button></li>
            <!-- <li class="left"><button type="button" class="btn-red" data-url="/menu/dm.im" data-group="ids" data-toggle="dodelchecked" data-confirm-msg="确定删除所选项？？">删除</button></li> -->
        </ul>
    </div>
    <table class="table table-bordered table-hover table-striped table-top" data-layout-h="-240">
        <thead>
            <tr class="headtr">
            	<th>标题</th>
                <th>修改时间</th>
                <th>是否显示</th>
                <th>排序</th>
                <th width="26"><input type="checkbox" class="checkboxCtrl" data-group="ids" data-toggle="icheck"></th>
                <th width="100">操作</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="m" items="${menubodys }">
	            <tr class="bodytr">
	                <td>${m.title }</td>
	                <td><fmt:formatDate value="${m.createtime }" pattern="yyyy-MM-dd HH:MM:ss"/></td>
	                <td>${m.isshow eq 0?'是':'否' }</td>
	                <td>${m.sort }</td>
	                <td><input type="checkbox" name="ids" data-toggle="icheck" value="${m.id }"></td>
	                <td>
	                    <a class="btn btn-blue" href="javascript:void(0)" onclick="toedit(${m.id})">编辑</a>
	                </td>
	            </tr>
            </c:forEach>
        </tbody>
    </table>
    </form>
    </div>
</div>
