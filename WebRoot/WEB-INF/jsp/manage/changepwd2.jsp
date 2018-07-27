<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="bjui-pageContent">
	<form id="j_pwschange_form" action="/m2/cp2.im" data-toggle="validate" method="POST">
		<div class="pageFormContent" data-layout-h="0">
            <hr>
            <input type="hidden" name="mid" value="${mid}"/>
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label for="newpwd" class="control-label x85">新密码：</label>
                <input type="password" data-rule="新密码:required" name="newpwd" id="newpwd" value="" placeholder="新密码" size="20">
            </div>
            <div class="form-group">
                <label for="repwd" class="control-label x85">确认密码：</label>
                <input type="password" data-rule="required;match(newpwd)" name="repwd" id="repwd" value="" placeholder="确认新密码" size="20">
            </div>
		</div>
		<div class="bjui-footBar">
            <ul>
                <li><button type="button" class="btn-close">取消</button></li>
                <li><button type="submit" class="btn-default">保存</button></li>
            </ul>
		</div>
	</form>
</div>