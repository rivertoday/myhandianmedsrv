<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="bjui-pageContent">
    <form action="ajaxDone1.html" id="j_form_form" class="pageForm" data-toggle="validate">
        <div class="pageFormContent" data-layout-h="0">
            <div style="margin:15px auto 0; width:800px;">
                <fieldset>
                    <legend>下拉选择框</legend>
                    <table class="table table-condensed table-hover">
                        <thead>
                            <tr>
                                <th width="80"></th>
                                <th>样例</th>
                                <th>属性</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td align="right"><label class="label-control">自动宽高：</label></td>
                                <td>
                                    <select data-toggle="selectpicker">
                                        <option value="">全部</option>
                                        <option value="1">下拉选项1</option>
                                        <option value="2" selected="">下拉选项2</option>
                                        <option value="3">下拉选项3</option>
                                        <option value="4">下拉选项4</option>
                                    </select>
                                </td>
                                <td>data-toggle="selectpicker"</td>
                            </tr>
                            <tr>
                                <td align="right"><label class="label-control">固定宽度：</label></td>
                                <td>
                                    <select data-toggle="selectpicker" data-width="200">
                                        <option value="">全部</option>
                                        <option value="1">下拉选项1</option>
                                        <option value="2" selected="">下拉选项2</option>
                                        <option value="3">下拉选项3</option>
                                        <option value="4">下拉选项4</option>
                                    </select>
                                </td>
                                <td>data-toggle="selectpicker" data-width="200"</td>
                            </tr>
                            <tr>
                                <td align="right"><label class="label-control">多选：</label></td>
                                <td>
                                    <select data-toggle="selectpicker" data-width="200" multiple>
                                        <option value="">全部</option>
                                        <option value="崇明">崇明</option><option value="黄浦">黄浦</option><option value="卢湾">卢湾</option><option value="徐汇">徐汇</option><option value="长宁">长宁</option><option value="静安">静安</option><option value="普陀">普陀</option><option value="闸北">闸北</option><option value="虹口">虹口</option><option value="杨浦">杨浦</option><option value="闵行">闵行</option><option value="宝山">宝山</option><option value="嘉定">嘉定</option><option value="浦东">浦东</option><option value="金山">金山</option><option value="松江">松江</option><option value="青浦">青浦</option><option value="南汇">南汇</option><option value="奉贤">奉贤</option><option value="朱家角">朱家角</option>
                                    </select>
                                </td>
                                <td>data-toggle="selectpicker" multiple</td>
                            </tr>
                            <tr>
                                <td align="right"><label class="label-control">联动：</label></td>
                                <td>
                                    <select name="province" data-toggle="selectpicker" data-nextselect="#j_form_city" data-refurl="ajax/city_{value}.html">
                                        <option value="all">--省市--</option>
                                        <option value="bj" selected>北京</option>
                                        <option value="sh">上海</option>
                                    </select>
                                    <select name="city" id="j_form_city" data-toggle="selectpicker" data-nextselect="#j_form_area" data-refurl="ajax/area_{value}.html" data-emptytxt="--城市--">
                                        <option value="all">--城市--</option>
                                    </select>
                                    <select name="area" id="j_form_area" data-toggle="selectpicker" data-emptytxt="--区县--">
                                        <option value="all">--区县--</option>
                                    </select>
                                </td>
                                <td></td>
                            </tr>
                            <tr>
                                <td align="right"><label class="label-control">禁用：</label></td>
                                <td>
                                    <select data-toggle="selectpicker" data-width="200" disabled>
                                        <option value="">全部</option>
                                        <option value="1">下拉选项1</option>
                                        <option value="2" selected="">下拉选项2</option>
                                        <option value="3">下拉选项3</option>
                                        <option value="4">下拉选项4</option>
                                    </select>
                                </td>
                                <td>disabled</td>
                            </tr>
                            <tr>
                                <td colspan="3">
                                    <div class="alert alert-warning form-inline"><i class="fa fa-warning"></i> <strong>更多参数：</strong>详见<a href="http://silviomoreto.github.io/bootstrap-select/" target="_blank">http://silviomoreto.github.io/bootstrap-select/</a>。</div>
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