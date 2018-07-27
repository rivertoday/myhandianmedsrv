<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="bjui-pageContent">
    <form action="ajaxDone1.html" id="j_form_form" class="pageForm" data-toggle="validate">
        <div class="pageFormContent" data-layout-h="0">
            <div style="margin:15px auto 0; width:800px;">
                <fieldset>
                    <legend>选项卡</legend>
                    <!-- Tabs -->
                    <ul class="nav nav-tabs" role="tablist">
                        <li class="active"><a href="#home" role="tab" data-toggle="tab">Home</a></li>
                        <li><a href="form-input.html" role="tab" data-toggle="ajaxtab" data-target="#profile" data-reload="false">ajax加载</a></li>
                        <li><a href="#messages" role="tab" data-toggle="tab">Messages</a></li>
                        <li><a href="#settings" role="tab" data-toggle="tab">Settings</a></li>
                    </ul>
                    <!-- Tab panes -->
                    <div class="tab-content">
                        <div class="tab-pane fade active in" id="home"><p>选项卡的a标签上添加[data-toggle="ajaxtab"]属性可以实现ajax加载内容。</p><p>[data-reload]属性可以定义点击该选项卡时是否每次都需要重新加载。</p></div>
                        <div class="tab-pane fade" id="profile"><!-- Ajax加载 --></div>
                        <div class="tab-pane fade" id="messages">No3. Messages</div>
                        <div class="tab-pane fade" id="settings">No4. Settings</div>
                    </div>
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