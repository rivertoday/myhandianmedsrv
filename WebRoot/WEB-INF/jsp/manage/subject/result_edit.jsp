<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageContent">
    <form action="/managesubject/result_save.im" id="memberForm" class="pageForm" method="post" data-toggle="validate">
        <input type="hidden" name="id" value="${subjectResult.id }" >
        <input type="hidden" name="subjectId" value="${subjectResult.subjectId }" >
        <input type="hidden" name="questionId" value="${subjectResult.questionId }" >
        <div class="pageFormContent" data-layout-h="0">
            <table class="table table-condensed table-hover" width="100%">
                <tbody>
                	<tr>
                        <td>
                            <label for="name" class="control-label x85">名称：</label>
                            <input type="text" name="name" id="name" value="${subjectResult.name}"  data-rule="required"  size="30">
                        </td>
                    </tr>
                    <c:if test="${subject.types != 4 && subject.types != 5 }">
	                	<tr>
	                        <td>
	                            <label for="scoreSmall" class="control-label x85">低分数：</label>
	                            <input type="text" name="scoreSmall" id="scoreSmall" value="${subjectResult.scoreSmall}"  data-rule="required"  size="15">
	                        </td>
	                    </tr>
	                	<tr>
	                        <td>
	                            <label for="scoreBig" class="control-label x85">高分数：</label>
	                            <input type="text" name="scoreBig" id="scoreBig" value="${subjectResult.scoreBig}"  data-rule="required"  size="15">
	                        </td>
	                    </tr>
	                </c:if>
                    <c:if test="${subject.types == 4 }">
	                	<tr>
	                        <td>
	                            <label for="isCorrect" class="control-label x85">是非项：</label>
	                            <input type="radio" name="isCorrect" <c:if test="${empty subjectResult.isCorrect || subjectResult.isCorrect == 1 }">checked="checked"</c:if> value="1">是
	                            <input type="radio" name="isCorrect" <c:if test="${subjectResult.isCorrect == 0 }">checked="checked"</c:if> value="0">否
	                        </td>
	                    </tr>
	                </c:if>
                	<tr>
                        <td>
                            <label for="instruction" class="control-label x85">说明：</label>
                            <textarea rows="6" cols="66" name="instruction" data-rule="required">${subjectResult.instruction }</textarea>
                        </td>
                    </tr>
                	<tr>
                        <td>
                            <label for="suggestion" class="control-label x85">建议：</label>
                            <textarea rows="6" cols="66" name="suggestion" data-rule="required">${subjectResult.suggestion }</textarea>
                        </td>
                    </tr>
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
