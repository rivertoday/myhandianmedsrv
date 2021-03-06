<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="table-edit-lookup.html" method="post">
        <input type="hidden" name="pageCurrent" value="1">
        <input type="hidden" name="pageSize" value="${model.pageSize}">
        <input type="hidden" name="orderField" value="${param.orderField}">
        <ul class="bjui-searchBar">
            <li><label>名称：</label><input type="text" value="" name="code" size="10" /></li>
            <li><button type="submit" class="btn-default" data-icon="search">查询</button></li>
            <li><a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a></li>
            <li class="pull-right">
                <button type="button" class="btn-blue" data-toggle="lookupback" data-lookupid="ids" data-warn="请至少选择一项职业" data-icon="check-square-o">选择选中</button>
            </li>
        </ul>
    </form>
</div>
<div class="bjui-pageContent">
    <table data-toggle="tablefixed" data-width="100%" data-layout-h="0">
        <thead>
            <tr>
                <th data-order-field="id">No.</th>
                <th data-order-field="name">名称</th>
                <th data-order-field="note">描述</th>
                <th class="orderby asc" data-order-field="date">创建日期</th>
                <th width="28"><input type="checkbox" class="checkboxCtrl" data-group="ids" data-toggle="icheck"></th>
                <th width="74">操作</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>1</td>
                <td>自由职业</td>
                <td>Free Man</td>
                <td>2010-01-01</td>
                <td><input type="checkbox" name="ids" data-toggle="icheck" value="{pid:'1', profession:'自由职业'}"></td>
                <td>
                    <a href="javascript:;" data-toggle="lookupback" data-args="{pid:'1', profession:'自由职业'}" class="btn btn-blue" title="选择本项" data-icon="check">选择</a>
                </td>
            </tr>
            <tr>
                <td>2</td>
                <td>工程师</td>
                <td>干大事的</td>
                <td>2010-01-02</td>
                <td><input type="checkbox" name="ids" data-toggle="icheck" value="{pid:'2', profession:'工程师'}"></td>
                <td>
                    <a href="javascript:;" data-toggle="lookupback" data-args="{pid:'2', profession:'工程师'}" class="btn btn-blue" title="选择本项" data-icon="check">选择</a>
                </td>
            </tr>
            <tr>
                <td>3</td>
                <td>教师</td>
                <td>传道授业解惑</td>
                <td>2010-01-03</td>
                <td><input type="checkbox" name="ids" data-toggle="icheck" value="{pid:'3', profession:'教师'}"></td>
                <td>
                    <a href="javascript:;" data-toggle="lookupback" data-args="{pid:'3', profession:'教师'}" class="btn btn-blue" title="选择本项" data-icon="check">选择</a>
                </td>
            </tr>
            <tr>
                <td>4</td>
                <td>医生</td>
                <td>救死扶伤</td>
                <td>2010-01-04</td>
                <td><input type="checkbox" name="ids" data-toggle="icheck" value="{pid:'4', profession:'医生'}"></td>
                <td>
                    <a href="javascript:;" data-toggle="lookupback" data-args="{pid:'4', profession:'医生'}" class="btn btn-blue" title="选择本项" data-icon="check">选择</a>
                </td>
            </tr>
            <tr>
                <td>5</td>
                <td>程序猿</td>
                <td>码农</td>
                <td>2010-01-05</td>
                <td><input type="checkbox" name="ids" data-toggle="icheck" value="{pid:'5', profession:'程序猿'}"></td>
                <td>
                    <a href="javascript:;" data-toggle="lookupback" data-args="{pid:'5', profession:'程序猿'}" class="btn btn-blue" title="选择本项" data-icon="check">选择</a>
                </td>
            </tr>
            <tr>
                <td>6</td>
                <td>当官的</td>
                <td>吃人不吐骨头</td>
                <td>2010-01-06</td>
                <td><input type="checkbox" name="ids" data-toggle="icheck" value="{pid:'6', profession:'当官的'}"></td>
                <td>
                    <a href="javascript:;" data-toggle="lookupback" data-args="{pid:'6', profession:'当官的'}" class="btn btn-blue" title="选择本项" data-icon="check">选择</a>
                </td>
            </tr>
            <tr>
                <td>7</td>
                <td>路人甲</td>
                <td>打酱油路过</td>
                <td>2010-01-07</td>
                <td><input type="checkbox" name="ids" data-toggle="icheck" value="{pid:'7', profession:'路人甲'}"></td>
                <td>
                    <a href="javascript:;" data-toggle="lookupback" data-args="{pid:'7', profession:'路人甲'}" class="btn btn-blue" title="选择本项" data-icon="check">选择</a>
                </td>
            </tr>
        </tbody>
    </table>
    <div class="bjui-footBar">
        <div class="pages">
            <span>每页&nbsp;</span>
            <div class="selectPagesize">
                <select data-toggle="selectpicker" data-toggle-change="changepagesize">
                    <option value="30">30</option>
                    <option value="60">60</option>
                    <option value="120">120</option>
                    <option value="150">150</option>
                </select>
            </div>
            <span>&nbsp;条，共 7 条</span>
        </div>
        <div class="pagination-box" data-toggle="pagination" data-total="7" data-page-size="30" data-page-current="1">
        </div>
    </div>
</div>