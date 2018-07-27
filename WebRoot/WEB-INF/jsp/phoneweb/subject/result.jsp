<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta http-equiv="Content-Type" content="text/html">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Expires" content="-1">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Pragma" content="no-cache">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="1 days" name="revisit-after" />
<meta content="" name="keywords" />
<meta content="" name="description" />
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<meta content="telephone=no" name="format-detection" />
<meta content="false" id="twcClient" name="twcClient" />
    <title>测试结果</title>
    <link rel="stylesheet" type="text/css" href="/phoneweb/css/Ymobilezoom.css"/>
    <link rel="stylesheet" type="text/css" href="/phoneweb/css/f_base.css"/>
    <script type="text/javascript" src="/phoneweb/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="/phoneweb/js/Ymobilezoom.js"></script>
    <%@ include file="../css.jsp" %>
    <script>
	    $(function(){
	        $(".f_naniu input").click(function(){
	        	var subjectId = '${subjectId}';
	           	location.href = "/phoneweb/subject/detail.html?id=" + subjectId;
	        })
	    })
	</script>
</head>
<body>
<header>
<div class="top2 fixedHeader">
    <div class="f_head_lf"><a href="/phoneweb/subject/detail.html?id=${subjectId}"><img src="/phoneweb/images/f_head01.png" /></a></div>
    <div class="f_head_ct">测试结果</div>
    <div class="f_head_rt"><a href="javascript:;" class="Inper_titSha"><img src="/phoneweb/images/f_head02.png" /></a></div>
    <%--
    <div class="f_head_rt"><a href="javascript:;"><img src="/phoneweb/images/f_head02.png" /></a></div>
	 --%>
     </div>
</header>
<div class="f_cscg">
	<div class="f_jg" style="font-size: 33px;">
    	${subjectResult.name }
    </div>
    <div class="f_jg_word" style="font-size: 30px;">
    	${subjectResult.instruction }
	</div>
    <div class="f_jianyi">
    	<div class="f_jy">建议</div>
       	<div class="f_jianyi_word">
       		${subjectResult.suggestion }
		</div>
    </div>
    <div class="f_sapma"><img src="/phoneweb/images/f_cscg.png" /></div>
    <div class="f_naniu"><input type="button" name="" value="再测一次"/></div>
</div>

<%@ include file="../bottom2.jsp" %>
<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
<div class="fenxiangcontent fixedBox nosize">
	<div class="fenxiangcontentA">
        <b>分享到</b>
		<div class="f_g">
        <a class="jiathis_button_tsina"><img src="/phoneweb/images/fenxiangIco_01.png">新浪微博</a>
        <a class="jiathis_button_tqq"><img src="/phoneweb/images/fenxiangIco_02.png">腾讯微博</a>
        <a class="jiathis_button_qzone"><img src="/phoneweb/images/fenxiangIco_03.png">QQ空间</a>
<!--         <a  class="jiathis_button_cqq"><img src="/phoneweb/images/f_qq.png">QQ好友</a> -->
        <a class="jiathis_button_weixin"><img src="/phoneweb/images/f_weixin.png">微信</a>
		</div>


        <i>取消</i>
    </div>
</div>
<style>
body{ font-size:10px;}
.fenxiangcontent{position:fixed; width:100%; height:100%; background:#fff; z-index:99999; top:-500%; transition:all 0.4s cubic-bezier(0.5,1,0.5,1);-webkit-transition:all 0.4s cubic-bezier(0.5,1,0.5,1);}
.fenxiangcontent.curhuscsd{top:0;}
.fenxiangcontent b{display:block; height:60px; line-height:60px; font-size:25px; text-align:center; border-bottom:1px solid #eee; font-weight:normal; background:#f6f6f6; color:#333;}
.fenxiangcontent a{display:block; height:55px; line-height:55px; font-size:20px; padding:0px 25px; border-bottom:1px solid #eee; text-decoration:none; color:#444;}
.fenxiangcontent a img{width:32px; height:32px; margin-bottom:-8px; margin-right:15px;}
.fenxiangcontent i{display:block; height:55px; border-top:1px solid #eee; position:absolute; left:0px; bottom:0px; width:100%; text-align:center; line-height:55px; font-size:20px; font-style:normal; background:#f9f9f9; cursor:pointer;}

</style>
<script>
$(function(){
	$(".f_head_rt").click(function(){
		$(".fenxiangcontent").addClass("curhuscsd");
	})
	$(".fenxiangcontent i").click(function(){
		$(".fenxiangcontent").removeClass("curhuscsd");
	})
})
</script>

</body>
</html>
