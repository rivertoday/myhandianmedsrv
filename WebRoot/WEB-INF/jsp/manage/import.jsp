<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- bootstrap - css -->
<link href="/manage/BJUI/themes/css/bootstrap.min.css" rel="stylesheet">
<!-- core - css -->
<link href="/manage/BJUI/themes/css/style.css" rel="stylesheet">
<link href="/manage/BJUI/themes/purple/core.css" id="bjui-link-theme" rel="stylesheet">
<!-- plug - css -->
<link href="/manage/BJUI/plugins/kindeditor_4.1.10/themes/default/default.css" rel="stylesheet">
<link href="/manage/BJUI/plugins/colorpicker/css/bootstrap-colorpicker.min.css" rel="stylesheet">
<link href="/manage/BJUI/plugins/niceValidator/jquery.validator.css" rel="stylesheet">
<link href="/manage/BJUI/plugins/bootstrapSelect/bootstrap-select.css" rel="stylesheet">
<link href="/manage/BJUI/themes/css/FA/css/font-awesome.min.css" rel="stylesheet">
<!--[if lte IE 7]>
<link href="BJUI/themes/css/ie7.css" rel="stylesheet">
<![endif]-->
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lte IE 9]>
    <script src="BJUI/other/html5shiv.min.js"></script>
    <script src="BJUI/other/respond.min.js"></script>
<![endif]-->
<!-- jquery -->
<script src="/manage/BJUI/js/jquery-1.7.2.min.js"></script>
<script src="/manage/BJUI/js/jquery.cookie.js"></script>
<!--[if lte IE 9]>
<script src="BJUI/other/jquery.iframe-transport.js"></script>    
<![endif]-->
<!-- BJUI.min 分模块压缩版 -->
<!--  
<script src="BJUI/js/b-jui.all.js"></script>
-->
<!-- 以下是B-JUI的分模块未压缩版，建议开发调试阶段使用下面的版本 -->
<script src="/manage/BJUI/js/bjui-core.js"></script>
<script src="/manage/BJUI/js/bjui-regional.zh-CN.js"></script>
<script src="/manage/BJUI/js/bjui-frag.js"></script>
<script src="/manage/BJUI/js/bjui-extends.js"></script>
<script src="/manage/BJUI/js/bjui-basedrag.js"></script>
<script src="/manage/BJUI/js/bjui-slidebar.js"></script>
<script src="/manage/BJUI/js/bjui-contextmenu.js"></script>
<script src="/manage/BJUI/js/bjui-navtab.js"></script>
<script src="/manage/BJUI/js/bjui-dialog.js"></script>
<script src="/manage/BJUI/js/bjui-taskbar.js"></script>
<script src="/manage/BJUI/js/bjui-tablefixed.js"></script>
<script src="/manage/BJUI/js/bjui-alertmsg.js"></script>
<script src="/manage/BJUI/js/bjui-pagination.js"></script>
<script src="/manage/BJUI/js/bjui-util.date.js"></script>
<script src="/manage/BJUI/js/bjui-datepicker.js"></script>
<script src="/manage/BJUI/js/bjui-spinner.js"></script>
<script src="/manage/BJUI/js/bjui-theme.js"></script>
<script src="/manage/BJUI/js/bjui-ajaxtab.js"></script>
<script src="/manage/BJUI/js/bjui-tabledit.js"></script>
<script src="/manage/BJUI/js/bjui-lookup.js"></script>
<script src="/manage/BJUI/js/bjui-tags.js"></script>
<script src="/manage/BJUI/js/bjui-ajax.js"></script>
<script src="/manage/BJUI/js/bjui-initui.js"></script>
<script src="/manage/BJUI/js/bjui-upload.js"></script>
<script src="/manage/BJUI/js/bjui-plugins.js"></script>
<!-- plugins -->
<!-- swfupload for uploadify && kindeditor -->
<script src="/manage/BJUI/plugins/swfupload/swfupload.js"></script>
<!-- kindeditor -->
<script src="/manage/BJUI/plugins/kindeditor_4.1.10/kindeditor-all.min.js"></script>
<script src="/manage/BJUI/plugins/kindeditor_4.1.10/lang/zh_CN.js"></script>
<!-- colorpicker -->
<script src="/manage/BJUI/plugins/colorpicker/js/bootstrap-colorpicker.min.js"></script>
<!-- ztree -->
<script src="/manage/BJUI/plugins/ztree/jquery.ztree.all-3.5.js"></script>
<!-- nice validate -->
<script src="/manage/BJUI/plugins/niceValidator/jquery.validator.js"></script>
<script src="/manage/BJUI/plugins/niceValidator/jquery.validator.themes.js"></script>
<!-- bootstrap plugins -->
<script src="/manage/BJUI/plugins/bootstrap.min.js"></script>
<script src="/manage/BJUI/plugins/bootstrapSelect/bootstrap-select.min.js"></script>
<!-- icheck -->
<script src="/manage/BJUI/plugins/icheck/icheck.min.js"></script>
<!-- dragsort -->
<script src="/manage/BJUI/plugins/dragsort/jquery.dragsort-0.5.1.min.js"></script>
<!-- other plugins -->
<script src="/manage/BJUI/plugins/other/jquery.autosize.js"></script>
<link href="/manage/BJUI/plugins/uploadify/css/uploadify.css" rel="stylesheet">
<script src="/manage/BJUI/plugins/uploadify/scripts/jquery.uploadify.min.js"></script>
<script src="/manage/xiangce/layer/layer.min.js"></script>
<script src="/manage/xiangce/extend/layer.ext.js"></script>
