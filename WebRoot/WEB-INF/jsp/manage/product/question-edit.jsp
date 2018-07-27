<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="/client/js/shengShiXian.js"></script>
<script type="text/javascript">
function pic_upload_success(file, data) {
    var json = $.parseJSON(data)
    
    $(this).bjuiajax('ajaxDone', json)
    if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
        $('#headimg').val(json.navTabId).trigger('validate')
        $('#headimg_span').html('<img src="'+ json.navTabId +'" width="100" />')
    }
}

$(".bann").click(function(){
	$("input[name='state']").val($(this).attr("data-state"));
	$("form").submit();
})
</script>
<div class="bjui-pageContent">
    <form action="/manageProduct/questionSave.im" id="memberForm" class="pageForm" method="post" data-toggle="validate">
        <input type="hidden" name="questionId" value="${productQuestion.id }" >
        <input type="hidden" name="state" value="${productQuestion.state }" >
        <input type="hidden" name="productId" value="${productId}" >
        <div class="pageFormContent" data-layout-h="0">
            <table class="table table-condensed table-hover" width="100%">
                <tbody>
                	<tr>
                        <td>
                            <label for="question" class="control-label x85">问题：</label>
                            <input type="text" name="question" id="question" value="${productQuestion.question}"  data-rule="required"  size="40">
                        </td>
                    </tr>
                    
                      <tr>
                        <td>
                            <label for="answer" class="control-label x85">答案：</label>
                            <textarea rows="10" cols="40" name="answer" id="answer" data-rule="required">${productQuestion.answer }</textarea>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="bjui-footBar">
            <ul>
                <li><button type="button" class="btn-close" data-icon="close">取消</button></li>
                <li><button type="button" class="btn-default bann"  data-icon="save" data-state="2">保存</button></li>
                <li><button type="button" class="btn-default bann"  data-icon="save" data-state="1">上架</button></li>
            </ul>
        </div>
    </form>
</div>
