<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>B-JUI 客户端框架</title>
<meta name="Keywords" content="B-JUI,Bootstrap,DWZ,jquery,ui,前端,框架,开源,OSC,开源框架,knaan"/>
<meta name="Description" content="B-JUI, Bootstrap for DWZ富客户端框架，基于DWZ富客户端框架修改。主要针对皮肤，编辑器，表单验证等方面进行了大量修改，引入了Bootstrap，Font Awesome，KindEditor，jquery.validationEngine，iCheck等众多开源项目。交流QQ群：232781006。"/> 
<!-- bootstrap - css -->
<link href="BJUI/themes/css/bootstrap.min.css" rel="stylesheet">
<!-- core - css -->
<link href="BJUI/themes/css/style.css" rel="stylesheet">
<link href="BJUI/themes/purple/core.css" id="bjui-link-theme" rel="stylesheet">
<!-- plug - css -->
<link href="BJUI/plugins/kindeditor_4.1.10/themes/default/default.css" rel="stylesheet">
<link href="BJUI/plugins/colorpicker/css/bootstrap-colorpicker.min.css" rel="stylesheet">
<link href="BJUI/plugins/niceValidator/jquery.validator.css" rel="stylesheet">
<link href="BJUI/plugins/bootstrapSelect/bootstrap-select.css" rel="stylesheet">
<link href="BJUI/themes/css/FA/css/font-awesome.min.css" rel="stylesheet">
<!--[if lte IE 7]>
<link href="BJUI/themes/css/ie7.css" rel="stylesheet">
<![endif]-->
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lte IE 9]>
    <script src="BJUI/other/html5shiv.min.js"></script>
    <script src="BJUI/other/respond.min.js"></script>
<![endif]-->
<!-- jquery -->
<script src="BJUI/js/jquery-1.7.2.min.js"></script>
<script src="BJUI/js/jquery.cookie.js"></script>
<!--[if lte IE 9]>
<script src="BJUI/other/jquery.iframe-transport.js"></script>    
<![endif]-->
<!-- BJUI.min 分模块压缩版 -->
<!--  
<script src="BJUI/js/b-jui.all.js"></script>
-->
<!-- 以下是B-JUI的分模块未压缩版，建议开发调试阶段使用下面的版本 -->
<script src="BJUI/js/bjui-core.js"></script>
<script src="BJUI/js/bjui-regional.zh-CN.js"></script>
<script src="BJUI/js/bjui-frag.js"></script>
<script src="BJUI/js/bjui-extends.js"></script>
<script src="BJUI/js/bjui-basedrag.js"></script>
<script src="BJUI/js/bjui-slidebar.js"></script>
<script src="BJUI/js/bjui-contextmenu.js"></script>
<script src="BJUI/js/bjui-navtab.js"></script>
<script src="BJUI/js/bjui-dialog.js"></script>
<script src="BJUI/js/bjui-taskbar.js"></script>
<script src="BJUI/js/bjui-tablefixed.js"></script>
<script src="BJUI/js/bjui-alertmsg.js"></script>
<script src="BJUI/js/bjui-pagination.js"></script>
<script src="BJUI/js/bjui-util.date.js"></script>
<script src="BJUI/js/bjui-datepicker.js"></script>
<script src="BJUI/js/bjui-spinner.js"></script>
<script src="BJUI/js/bjui-theme.js"></script>
<script src="BJUI/js/bjui-ajaxtab.js"></script>
<script src="BJUI/js/bjui-tabledit.js"></script>
<script src="BJUI/js/bjui-lookup.js"></script>
<script src="BJUI/js/bjui-tags.js"></script>
<script src="BJUI/js/bjui-ajax.js"></script>
<script src="BJUI/js/bjui-initui.js"></script>
<script src="BJUI/js/bjui-upload.js"></script>
<script src="BJUI/js/bjui-plugins.js"></script>
<!-- plugins -->
<!-- swfupload for uploadify && kindeditor -->
<script src="BJUI/plugins/swfupload/swfupload.js"></script>
<!-- kindeditor -->
<script src="BJUI/plugins/kindeditor_4.1.10/kindeditor-all.min.js"></script>
<script src="BJUI/plugins/kindeditor_4.1.10/lang/zh_CN.js"></script>
<!-- colorpicker -->
<script src="BJUI/plugins/colorpicker/js/bootstrap-colorpicker.min.js"></script>
<!-- ztree -->
<script src="BJUI/plugins/ztree/jquery.ztree.all-3.5.js"></script>
<!-- nice validate -->
<script src="BJUI/plugins/niceValidator/jquery.validator.js"></script>
<script src="BJUI/plugins/niceValidator/jquery.validator.themes.js"></script>
<!-- bootstrap plugins -->
<script src="BJUI/plugins/bootstrap.min.js"></script>
<script src="BJUI/plugins/bootstrapSelect/bootstrap-select.min.js"></script>
<!-- icheck -->
<script src="BJUI/plugins/icheck/icheck.min.js"></script>
<!-- dragsort -->
<script src="BJUI/plugins/dragsort/jquery.dragsort-0.5.1.min.js"></script>
<!-- other plugins -->
<script src="BJUI/plugins/other/jquery.autosize.js"></script>
<link href="BJUI/plugins/uploadify/css/uploadify.css" rel="stylesheet">
<script src="BJUI/plugins/uploadify/scripts/jquery.uploadify.min.js"></script>
<!-- init -->
<script type="text/javascript">
$(function() {
    BJUI.init({
        loginInfo  : {url:'logintimeout.html', title:'登录', width:300, height:200}, // 弹出登录对话框
        statusCode : {ok:200, error:300, timeout:301}, //[可选]
        pageInfo   : {pageCurrent:'pageCurrent', pageSize:'pageSize', orderField:'orderField', orderDirection:'orderDirection'}, //[可选]
        keys       : {statusCode:'statusCode', message:'message'}, //[可选]
        ui         : {hideMode:'display'}, //[可选]hideMode:navtab组件切换的隐藏方式，支持的值有’display’，’offsets’负数偏移位置的值，默认值为’display’
        debug      : true,    // [可选]调试模式 [true|false，默认false]
        theme      : 'purple' // 若有Cookie['bjui_theme'],优先选择Cookie['bjui_theme']。皮肤[五种皮肤:default, orange, purple, blue, green]
    })
    //时钟
    var today = new Date(), time = today.getTime()
    $('#bjui-date').html(today.formatDate('yyyy/MM/dd'))
    setInterval(function() {
        today = new Date(today.setSeconds(today.getSeconds() + 1))
        $('#bjui-clock').html(today.formatDate('HH:mm:ss'))
    }, 1000)
})

//console.log('IE:'+ (!$.support.leadingWhitespace))
//菜单-事件
function MainMenuClick(event, treeId, treeNode) {
    if (treeNode.isParent) {
        var zTree = $.fn.zTree.getZTreeObj(treeId)
        
        zTree.expandNode(treeNode)
        return
    }
    
    if (treeNode.target && treeNode.target == 'dialog')
        $(event.target).dialog({id:treeNode.tabid, url:treeNode.url, title:treeNode.name})
    else
        $(event.target).navtab({id:treeNode.tabid, url:treeNode.url, title:treeNode.name, fresh:treeNode.fresh})
}
</script>
</head>
<body>
    <!--[if lte IE 7]>
        <div id="errorie"><div>您还在使用老掉牙的IE，正常使用系统前请升级您的浏览器到 IE8以上版本 <a target="_blank" href="http://windows.microsoft.com/zh-cn/internet-explorer/ie-8-worldwide-languages">点击升级</a>&nbsp;&nbsp;强烈建议您更改换浏览器：<a href="http://down.tech.sina.com.cn/content/40975.html" target="_blank">谷歌 Chrome</a></div></div>
    <![endif]-->
    <header class="navbar navbar-default" id="bjui-header">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bjui-navbar-collapse">
                <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"><img src="images/logo.png"></a>
        </div>
        <nav class="collapse navbar-collapse" id="bjui-navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li class="datetime"><div><span id="bjui-date"></span><br><i class="fa fa-clock-o"></i> <span id="bjui-clock"></span></div></li>
                <li><a href="http://b-jui.com/doc/" target="_blank">B-JUI在线文档</a></li>
                <li><a href="http://www.bootcss.com/" target="_blank">Bootstrap中文网</a></li>
                <li><a href="http://www.j-ui.com/" target="_blank">DWZ(j-ui)官网</a></li>
                <li><a href="#">消息 <span class="badge">4</span></a></li>
                <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">我的账户 <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="changepwd.html" data-toggle="dialog" data-id="changepwd_page" data-mask="true" data-width="400" data-height="260">&nbsp;<span class="glyphicon glyphicon-lock"></span> 修改密码&nbsp;</a></li>
                        <li><a href="#">&nbsp;<span class="glyphicon glyphicon-user"></span> 我的资料</a></li>
                        <li class="divider"></li>
                        <li><a href="login.html" class="red">&nbsp;<span class="glyphicon glyphicon-off"></span> 注销登陆</a></li>
                    </ul>
                </li>
                <li class="dropdown"><a href="#" class="dropdown-toggle theme purple" data-toggle="dropdown"><i class="fa fa-tree"></i></a>
                    <ul class="dropdown-menu" role="menu" id="bjui-themes">
                        <li><a href="javascript:;" class="theme_default" data-toggle="theme" data-theme="default">&nbsp;<i class="fa fa-tree"></i> 黑白分明&nbsp;&nbsp;</a></li>
                        <li><a href="javascript:;" class="theme_orange" data-toggle="theme" data-theme="orange">&nbsp;<i class="fa fa-tree"></i> 橘子红了</a></li>
                        <li class="active"><a href="javascript:;" class="theme_purple" data-toggle="theme" data-theme="purple">&nbsp;<i class="fa fa-tree"></i> 紫罗兰</a></li>
                        <li><a href="javascript:;" class="theme_blue" data-toggle="theme" data-theme="blue">&nbsp;<i class="fa fa-tree"></i> 青出于蓝</a></li>
                        <li><a href="javascript:;" class="theme_green" data-toggle="theme" data-theme="green">&nbsp;<i class="fa fa-tree"></i> 绿草如茵</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
    </header>
    
    <style>
        #bjui-hnav{position:absolute; top:50px; z-index:5; margin-bottom:5px; width:100%;}
        #bjui-hnav > .navbar{margin-bottom:0; min-height:36px; background-color:#eae7ef; border-bottom:1px #CCC solid;}
        #bjui-hnav > .navbar .navbar-brand{padding:0 20px; height:36px; line-height:34px; color:#563d7c;}
        #bjui-hnav > .navbar .navbar-nav > li > a{margin-right:1px; padding:0 15px; height:36px; font-size:14px; line-height:36px;}
        #bjui-hnav > .navbar .navbar-nav > li > a:hover,
        #bjui-hnav > .navbar .navbar-nav >.active > a,
        #bjui-hnav > .navbar .navbar-nav >.active > a:hover,
        #bjui-hnav > .navbar .navbar-nav >.active > a:focus{color:#FFF; background-color:#927ab5;}
        #bjui-hnav > .navbar .navbar-form{margin:0; padding:0; height:36px; line-height:34px;}
        .panel-group{margin-bottom:0;}
    </style>
    <div class="bjui-hnav" id="bjui-hnav">
        <nav class="navbar navbar-default" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bjui-h-navbar">
                        <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#"><i class="fa fa-bars"></i> 主菜单 <i class="fa fa-angle-right"></i></a>
                </div>
                <div class="collapse navbar-collapse" id="bjui-h-navbar">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#leftBar-main" data-toggle="leftbar"><i class="fa fa-user"></i> 我的业务</a></li>
                        <li><a href="#leftBar-line" data-toggle="leftbar"><i class="fa fa-plane"></i> 线路规划</a></li>
                        <li><a href="#leftBar-group" data-toggle="leftbar"><i class="fa fa-group"></i> 组团信息</a></li>
                        <li><a href="#leftBar-scenic" data-toggle="leftbar"><i class="fa fa-image"></i> 景点模块</a></li>
                        <li><a href="#leftBar-dining" data-toggle="leftbar"><i class="fa fa-coffee"></i> 餐饮模块</a></li>
                        <li><a href="#leftBar-hotel" data-toggle="leftbar"><i class="fa fa-home"></i> 酒店模块</a></li>
                        <li><a href="#leftBar-finance" data-toggle="leftbar"><i class="fa fa-dollar"></i> 财务结算</a></li>
                        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-cog"></i> 系统相关 <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="#leftBar-scenic">部门管理</a></li>
                                <li><a href="#">员工管理</a></li>
                                <li><a href="#">导游管理</a></li>
                                <li class="divider"></li>
                                <li><a href="#">地接社</a></li>
                                <li class="divider"></li>
                                <li><a href="#">友情链接</a></li>
                            </ul>
                        </li>
                    </ul>
                    <form class="navbar-form navbar-right" role="search">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Search">
                        </div>
                        <button type="submit" class="btn btn-default" data-icon="search">Go!</button>
                    </form>
                </div>
            </div>
        </nav>
    </div>
    <div class="bjui-leftside" id="bjui-leftside">
        <div id="bjui-sidebar-s">
            <div class="collapse">
                <div class="toggleCollapse"><div title="展开菜单"><i class="fa fa-angle-double-right"></i></div></div>
            </div>
        </div>
        <div id="bjui-sidebar">
            <div class="toggleCollapse"><h2>导航菜单</h2><div title="收缩菜单"><i class="fa fa-angle-double-left"></i></div></div>
            <div class="panel-group panel-main" data-toggle="accordion" id="leftBar-main" data-heightbox="#bjui-sidebar" data-offsety="26">
                <div class="panel panel-default">
                    <div class="panel-heading panelContent">
                        <h4 class="panel-title"><a data-toggle="collapse" data-parent="#leftBar-main" href="#bjui-collapse-main" class="active"><i class="fa fa-caret-square-o-down"></i>&nbsp;我的业务</a></h4>
                    </div>
                    <div id="bjui-collapse-main" class="panel-collapse panelContent collapse in">
                        <div class="panel-body">
                            <ul id="bjui-tree-main" class="ztree ztree_main" data-toggle="ztree" data-on-click="MainMenuClick" data-expand-all="true">
                                <li data-id="1" data-pid="0">我的业务</li>
                                <li data-id="10" data-pid="1" data-url="form.html" data-tabid="form">我的表单</li>
                                <li data-id="10" data-pid="1" data-url="table.html" data-tabid="table">我的表格</li>
                            </ul>
                        </div>
                    </div>
                    <div class="panelFooter"><div class="panelFooterContent"></div></div>
                </div>
            </div>
            <div class="panel-group panel-main hide" data-toggle="accordion" id="leftBar-line" data-heightbox="#bjui-sidebar" data-offsety="26">
                <div class="panel panel-default">
                    <div class="panel-heading panelContent">
                        <h4 class="panel-title"><a data-toggle="collapse" data-parent="#leftBar-line" href="#bjui-collapse-line" class="active"><i class="fa fa-caret-square-o-down"></i>&nbsp;线路规划</a></h4>
                    </div>
                    <div id="bjui-collapse-line" class="panel-collapse panelContent collapse in">
                        <div class="panel-body">
                            <ul id="bjui-tree-line" class="ztree ztree_main" data-toggle="ztree" data-on-click="MainMenuClick" data-expand-all="true">
                                <li data-id="1" data-pid="0">线路规划</li>
                                <li data-id="10" data-pid="1" data-url="form.html" data-tabid="form">线路表单</li>
                                <li data-id="10" data-pid="1" data-url="table.html" data-tabid="table">线路表格</li>
                            </ul>
                        </div>
                    </div>
                    <div class="panelFooter"><div class="panelFooterContent"></div></div>
                </div>
            </div>
            <div class="panel-group panel-main hide" data-toggle="accordion" id="leftBar-group" data-heightbox="#bjui-sidebar" data-offsety="26">
                <div class="panel panel-default">
                    <div class="panel-heading panelContent">
                        <h4 class="panel-title"><a data-toggle="collapse" data-parent="#leftBar-group" href="#bjui-collapse-group" class="active"><i class="fa fa-caret-square-o-down"></i>&nbsp;组团信息</a></h4>
                    </div>
                    <div id="bjui-collapse-group" class="panel-collapse panelContent collapse in">
                        <div class="panel-body">
                            <ul id="bjui-tree-group" class="ztree ztree_main" data-toggle="ztree" data-on-click="MainMenuClick" data-expand-all="true">
                                <li data-id="1" data-pid="0">组团信息</li>
                                <li data-id="10" data-pid="1" data-url="form.html" data-tabid="form">组团表单</li>
                                <li data-id="10" data-pid="1" data-url="table.html" data-tabid="table">组团表格</li>
                            </ul>
                        </div>
                    </div>
                    <div class="panelFooter"><div class="panelFooterContent"></div></div>
                </div>
            </div>
            <div class="panel-group panel-main hide" data-toggle="accordion" id="leftBar-scenic" data-heightbox="#bjui-sidebar" data-offsety="26">
                <div class="panel panel-default">
                    <div class="panel-heading panelContent">
                        <h4 class="panel-title"><a data-toggle="collapse" data-parent="#leftBar-scenic" href="#bjui-collapse-scenic" class="active"><i class="fa fa-caret-square-o-down"></i>&nbsp;景点模块</a></h4>
                    </div>
                    <div id="bjui-collapse-scenic" class="panel-collapse panelContent collapse in">
                        <div class="panel-body">
                            <ul id="bjui-tree-scenic" class="ztree ztree_main" data-toggle="ztree" data-on-click="MainMenuClick" data-expand-all="true">
                                <li data-id="1" data-pid="0">景点模块</li>
                                <li data-id="10" data-pid="1" data-url="form.html" data-tabid="form">景点表单</li>
                                <li data-id="10" data-pid="1" data-url="table.html" data-tabid="table">景点表格</li>
                            </ul>
                        </div>
                    </div>
                    <div class="panelFooter"><div class="panelFooterContent"></div></div>
                </div>
            </div>
            <div class="panel-group panel-main hide" data-toggle="accordion" id="leftBar-dining" data-heightbox="#bjui-sidebar" data-offsety="26">
                <div class="panel panel-default">
                    <div class="panel-heading panelContent">
                        <h4 class="panel-title"><a data-toggle="collapse" data-parent="#leftBar-dining" href="#bjui-collapse-dining" class="active"><i class="fa fa-caret-square-o-down"></i>&nbsp;餐饮模块</a></h4>
                    </div>
                    <div id="bjui-collapse-dining" class="panel-collapse panelContent collapse in">
                        <div class="panel-body">
                            <ul id="bjui-tree-dining" class="ztree ztree_main" data-toggle="ztree" data-on-click="MainMenuClick" data-expand-all="true">
                                <li data-id="1" data-pid="0">餐饮模块</li>
                                <li data-id="10" data-pid="1" data-url="form.html" data-tabid="form">餐饮表单</li>
                                <li data-id="10" data-pid="1" data-url="table.html" data-tabid="table">餐饮表格</li>
                            </ul>
                        </div>
                    </div>
                    <div class="panelFooter"><div class="panelFooterContent"></div></div>
                </div>
            </div>
            <div class="panel-group panel-main hide" data-toggle="accordion" id="leftBar-hotel" data-heightbox="#bjui-sidebar" data-offsety="26">
                <div class="panel panel-default">
                    <div class="panel-heading panelContent">
                        <h4 class="panel-title"><a data-toggle="collapse" data-parent="#leftBar-hotel" href="#bjui-collapse-hotel" class="active"><i class="fa fa-caret-square-o-down"></i>&nbsp;酒店模块</a></h4>
                    </div>
                    <div id="bjui-collapse-hotel" class="panel-collapse panelContent collapse in">
                        <div class="panel-body">
                            <ul id="bjui-tree-hotel" class="ztree ztree_main" data-toggle="ztree" data-on-click="MainMenuClick" data-expand-all="true">
                                <li data-id="1" data-pid="0">酒店模块</li>
                                <li data-id="10" data-pid="1" data-url="form.html" data-tabid="form">酒店表单</li>
                                <li data-id="10" data-pid="1" data-url="table.html" data-tabid="table">酒店表格</li>
                            </ul>
                        </div>
                    </div>
                    <div class="panelFooter"><div class="panelFooterContent"></div></div>
                </div>
            </div>
            <div class="panel-group panel-main hide" data-toggle="accordion" id="leftBar-finance" data-heightbox="#bjui-sidebar" data-offsety="26">
                <div class="panel panel-default">
                    <div class="panel-heading panelContent">
                        <h4 class="panel-title"><a data-toggle="collapse" data-parent="#leftBar-finance" href="#bjui-collapse-finance" class="active"><i class="fa fa-caret-square-o-down"></i>&nbsp;财务结算</a></h4>
                    </div>
                    <div id="bjui-collapse-finance" class="panel-collapse panelContent collapse in">
                        <div class="panel-body">
                            <ul id="bjui-tree-finance" class="ztree ztree_main" data-toggle="ztree" data-on-click="MainMenuClick" data-expand-all="true">
                                <li data-id="1" data-pid="0">财务结算</li>
                                <li data-id="10" data-pid="1" data-url="form.html" data-tabid="form">财务表单</li>
                                <li data-id="10" data-pid="1" data-url="table.html" data-tabid="table">财务表格</li>
                            </ul>
                        </div>
                    </div>
                    <div class="panelFooter"><div class="panelFooterContent"></div></div>
                </div>
            </div>
        </div>
    </div>
    <div id="bjui-container">
        <div id="bjui-navtab" class="tabsPage">
            <div class="tabsPageHeader">
                <div class="tabsPageHeaderContent">
                    <ul class="navtab-tab nav nav-tabs">
                        <li data-tabid="main" class="main active"><a href="javascript:;"><span><i class="fa fa-home"></i> #maintab#</span></a></li>
                    </ul>
                </div>
                <div class="tabsLeft"><i class="fa fa-angle-double-left"></i></div>
                <div class="tabsRight"><i class="fa fa-angle-double-right"></i></div>
                <div class="tabsMore"><i class="fa fa-angle-double-down"></i></div>
            </div>
            <ul class="tabsMoreList">
                <li><a href="javascript:;">#maintab#</a></li>
            </ul>
            <div class="navtab-panel tabsPageContent layoutBox">
                <div class="page unitBox">
                    <div class="bjui-pageHeader" style="background:#FFF;">
                        <div style="padding: 0 5px; border-bottom: 1px #DDD solid;">
                            <h4>B-JUI 前端框架 　　<a target="_blank" href="http://shang.qq.com/wpa/qunwpa?idkey=0047455f3845597286edd381d54076b1e10a45c0c735115f0ee74961f70880af"><img border="0" src="images/group.png" alt="[B-JUI]Bootstrap4DWZ交流群" title="[B-JUI]Bootstrap4DWZ交流群" style="vertical-align:top;"></a></h4>
                            <hr style="margin: 12px 0 0px;">
                            <div class="row">
                                <div class="col-md-6">
                                    <h5>网址：<a href="http://b-jui.com/" target="_blank">http://b-jui.com/</a></h5>
                                    <h5>项目地址：<a href="http://git.oschina.net/xknaan/B-JUI" target="_blank">GIT</a>　<a href="http://www.oschina.net/p/bootstrap-for-DWZ" target="_blank">OSCHINA</a></h5>
                                    <h5>微博地址：<a href="http://weibo.com/xknaan" target="_blank">http://weibo.com/xknaan</a></h5>                                    
                                </div>
                                <div class="col-md-6">
                                    <h5>DWZ(J-UI)官网：<a href="http://www.j-ui.com/" target="_blank">http://www.j-ui.com/</a></h5>
                                    <h5>Bootstrap中文网：<a href="http://www.bootcss.com/" target="_blank">http://www.bootcss.com/</a></h5>
                                    <h5>QQ交流群：232781006</h5>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="bjui-pageFormContent" data-layout-h="0">
                        <div style="position: absolute;top:10px;right:0;width:300px;">
                            <iframe width="100%" height="550" class="share_self" frameborder="0" scrolling="no" src="http://widget.weibo.com/weiboshow/index.php?language=&width=0&height=550&fansRow=2&ptype=1&speed=0&skin=1&isTitle=1&noborder=1&isWeibo=1&isFans=0&uid=2838273614&verifier=75a3b95b&dpc=1"></iframe>
                        </div>
                        <div style="margin-right:300px; overflow: hidden;">
                            <div class="row" style="padding: 0 8px;">
                                <div class="col-md-12">
                                    <div class="panel panel-default">
                                        <div class="panel-heading"><h3 class="panel-title">历史版本</h3></div>
                                        <div class="panel-body">
                                            <table class="table table-condensed table-striped table-hover" width="100%">
                                                <thead>
                                                    <tr>
                                                        <th>版本号</th>
                                                        <th>发布日期</th>
                                                        <th>在线DEMO</th>
                                                        <th>下载地址</th>
                                                        <th>描述</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>1.0 beta</td>
                                                        <td>2014.10.31</td>
                                                        <td>http://b-jui.com/</td>
                                                        <td>http://git.oschina.net/xknaan/B-JUI</td>
                                                        <td>全新的前端框架B-JUI，全Bootstrap插件式的组合，仅借鉴了DWZ的思想，功能已全部重写。</td>
                                                    </tr>
                                                    <tr>
                                                        <td>0.1 released</td>
                                                        <td>2014.08.01</td>
                                                        <td>http://xknaan.com/</td>
                                                        <td>http://git.oschina.net/xknaan/Bootstrap_for_DWZ</td>
                                                        <td>穿上Bootstrap衣服的DWZ</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="panel panel-default">
                                        <div class="panel-heading"><h3 class="panel-title">更新日志</h3></div>
                                        <div class="panel-body">
                                            <table class="table table-condensed table-striped table-hover" width="100%">
                                                <thead>
                                                    <tr>
                                                        <th>更新日期</th>
                                                        <th>更新版本</th>
                                                        <th>更新内容</th>
                                                        <th>重要程度</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>2014.11.04</td>
                                                        <td>V1.0 内测版本</td>
                                                        <td>修复IE8下的selectpicker</td>
                                                        <td><span class="label label-info">一般</span></td>
                                                    </tr>
                                                    <tr>
                                                        <td>2014.11.03</td>
                                                        <td>V1.0 内测版本</td>
                                                        <td>在线文档（部分）；zTree - select功能完成；ajax回调功能修整；其他细节完善</td>
                                                        <td><span class="label label-danger">重要</span></td>
                                                    </tr>
                                                    <tr>
                                                        <td>2014.10.31</td>
                                                        <td>V1.0 内测版本</td>
                                                        <td>创建</td>
                                                        <td><span class="label label-danger">重要</span></td>
                                                    </tr>
                                                    <tr>
                                                        <td>2014.09.10</td>
                                                        <td>初始版本</td>
                                                        <td>创建</td>
                                                        <td><span class="label label-default">停更</span></td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <footer id="bjui-footer">Copyright &copy; 2013 - 2014 <a href="http://xknaan.com" target="_blank">K'naan</a>(xknaan@163.com)　<a href="http://b-jui.com/" target="_blank">B-JUI 前端框架</a>　
        <script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1252983288'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s23.cnzz.com/stat.php%3Fid%3D1252983288%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script>
    </footer>
</body>
</html>