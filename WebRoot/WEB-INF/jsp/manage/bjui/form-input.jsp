<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="bjui-pageContent">
    <form action="ajaxDone1.html" id="j_form_form" class="pageForm" data-toggle="validate">
        <div class="pageFormContent" data-layout-h="0">
            <div style="margin:15px auto 0; width:800px;">
                <fieldset>
                    <legend>文本框</legend>
                    <table class="table table-condensed table-hover">
                        <thead>
                            <tr>
                                <th>样例</th>
                                <th>Class</th>
                                <th>属性</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><input type="text" value="普通文本框"></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td><input type="text" class="input-sm" value="小尺寸文本框"></td>
                                <td>input-sm</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td><input type="text" class="input-nm" value="稍大尺寸文本框"></td>
                                <td>input-nm</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td><input type="text" class="input-lg" value="较大尺寸文本框"></td>
                                <td>input-lg</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td><input type="text" value="固定尺寸的普通文本框" size="30"></td>
                                <td></td>
                                <td>size="30"</td>
                            </tr>
                            <tr>
                                <td><input type="text" value="只读文本框" size="30" readonly></td>
                                <td></td>
                                <td>size="30" readonly</td>
                            </tr>
                            <tr>
                                <td><input type="text" value="已禁用的文本框" size="30" disabled></td>
                                <td></td>
                                <td>size="30" disabled</td>
                            </tr>
                            <tr>
                                <td><textarea>普通多行文本框</textarea></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td><textarea cols="30">固定尺寸的普通多行文本框</textarea></td>
                                <td></td>
                                <td>cols="30"</td>
                            </tr>
                            <tr>
                                <td><textarea cols="30" rows="1" data-toggle="autoheight">自动调整高度的多行文本框</textarea></td>
                                <td></td>
                                <td>cols="30" rows="1" data-toggle="autoheight"</td>
                            </tr>
                            <tr>
                                <td colspan="3">
                                    <div class="alert alert-warning form-inline"><i class="fa fa-warning"></i> <strong>Class说明：</strong>JS会为text或textarea自动加上Class[form-control]。</div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </fieldset>
            </div>
        </div>
    </form>
    <div class="bjui-footBar">
        <ul>
            <li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
        </ul>
    </div>
</div>