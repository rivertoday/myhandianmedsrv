<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageContent">
    <form action="/managesubject/question_save.im" id="memberForm" class="pageForm" method="post" data-toggle="validate">
        <input type="hidden" name="id" value="${subjectQuestion.id }" >
        <input type="hidden" name="subjectId" value="${subjectQuestion.subjectId }" >
        <input type="hidden" name="types" value="${subjectQuestion.types }" >
        <div class="pageFormContent" data-layout-h="0">
            <table class="table table-condensed table-hover" width="100%">
                <tbody>
                	<tr>
                        <td>
                            <label for="question" class="control-label x85">问题：</label>
                            <input type="text" name="question" id="question" value="${subjectQuestion.question}"  data-rule="required"  size="30">
                        </td>
                    </tr>
                    <c:if test="${subjectQuestion.types == 3 }">
	                	<tr>
	                        <td>
	                            <label for="score" class="control-label x85">分数：</label>
	                            <input type="text" name="score" id="score" value="${subjectQuestion.score}"  data-rule="required"  size="15">
	                        </td>
	                    </tr>
                	</c:if>
                </tbody>
            </table>
        </div>
        <div class="bjui-footBar">
            <ul>
                <li><button type="button" class="btn-close" data-icon="close">取消</button></li>
                <li><button type="submit" class="btn-default"  data-icon="save" data-status="2">保存</button></li>
            </ul>
        </div>
    </form>
</div>
