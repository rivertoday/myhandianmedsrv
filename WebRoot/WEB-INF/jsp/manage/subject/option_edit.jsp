<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageContent">
    <form action="/managesubject/option_save.im" id="memberForm" class="pageForm" method="post" data-toggle="validate">
        <input type="hidden" name="id" value="${subjectQuestionOption.id }" >
        <input type="hidden" name="subjectId" value="${subjectQuestionOption.subjectId }" >
        <input type="hidden" name="questionId" value="${subjectQuestionOption.questionId }" >
        <div class="pageFormContent" data-layout-h="0">
            <table class="table table-condensed table-hover" width="100%">
                <tbody>
                	<tr>
                        <td>
                            <label for="optionName" class="control-label x85">名称：</label>
                            <input type="text" name="optionName" id="optionName" value="${subjectQuestionOption.optionName}"  data-rule="required"  size="30">
                        </td>
                    </tr>
                	<tr>
                        <td>
                            <label for="optionScore" class="control-label x85">分数：</label>
                            <input type="text" name="optionScore" id="optionScore" value="${subjectQuestionOption.optionScore}"  data-rule="required"  size="15">
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
