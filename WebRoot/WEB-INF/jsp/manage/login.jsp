<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统登录</title>
<script src="BJUI/js/jquery-1.7.2.min.js"></script>
<script src="BJUI/js/jquery.cookie.js"></script>
<script src="js/sha256.js"></script>
<link href="BJUI/themes/css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
* {font-family: "Verdana", "Tahoma", "Lucida Grande", "Microsoft YaHei", "Hiragino Sans GB", sans-serif;}
body {
    background: url(images/loginbg_01.jpg) no-repeat center center fixed;
    -webkit-background-size: cover;
    -moz-background-size: cover;
    -o-background-size: cover;
    background-size: cover;
}
a:link {color: #285e8e;}
.main_box {
    position: absolute; top:50%; left:50%; margin-top:-260px; margin-left: -300px; padding: 30px; width:600px; height:480px;
    background: #FAFAFA; background: rgba(255,255,255,0.5); border: 1px #DDD solid;
    border-radius: 5px;
    -webkit-box-shadow: 1px 5px 8px #888888; -moz-box-shadow: 1px 5px 8px #888888; box-shadow: 1px 5px 8px #888888;
}
.main_box .setting {position: absolute; top: 5px; right: 10px; width: 10px; height: 10px;}
.main_box .setting a {color: #FF6600;}
.main_box .setting a:hover {color: #555;}
.login_logo {margin-bottom: 20px; height: 45px; text-align: center;}
.login_logo img {height: 45px;}
.login_msg {text-align: center; font-size: 16px;}
.login_form {padding-top: 20px; font-size: 16px;}
.login_box .form-control {display: inline-block; *display: inline; zoom: 1; width: auto; font-size: 18px;}
.login_box .form-control.x319 {width: 319px;}
.login_box .form-control.x164 {width: 164px;}
.login_box .form-group {margin-bottom: 20px;}
.login_box .form-group label.t {width: 120px; text-align: right; cursor: pointer;}
.login_box .form-group.space {padding-top: 15px; border-top: 1px #FFF dotted;}
.login_box .form-group img {margin-top: 1px; height: 32px; vertical-align: top;}
.login_box .m {cursor: pointer;}
.bottom {text-align: center; font-size: 12px;}
</style>
<script type="text/javascript">
var COOKIE_NAME = 'sys__username';
$(function(){
	//changeCode();
	choose_bg();
	if ($.cookie(COOKIE_NAME)){
	    $("#j_username").val($.cookie(COOKIE_NAME));
	    $("#j_password").focus();
	    $("#j_remember").attr('checked', true);
	} else {
		$("#j_username").focus();
	}
	/*$("#captcha_img").click(function(){
		changeCode();
	});*/
	$("#login_form").submit(function(){
		if($("#j_username").val()==""){
			$("#error").html("请输入用户名");
		}else if($("#j_password").val()==""){
			$("#error").html("请输入密码");
		}else if($("#j_captcha").val()==""){
			$("#error").html("请输入验证码");
		}else{
			var issubmit = true;
			var i_index  = 0;
			$(this).find('.in').each(function(i){
				if ($.trim($(this).val()).length == 0) {
					$(this).css('border', '1px #ff0000 solid');
					issubmit = false;
					if (i_index == 0)
						i_index  = i;
				}
			});
			if (!issubmit) {
				$(this).find('.in').eq(i_index).focus();
				return false;
			}
			var $remember = $("#j_remember");
			if ($remember.attr('checked')) {
				$.cookie(COOKIE_NAME, $("#j_username").val(), { path: '/', expires: 15 });
			} else {
				$.cookie(COOKIE_NAME, null, { path: '/' });  //删除cookie
			}
			$("#login_ok").attr("disabled", true).val('登录中..');
	        return true;
        }
        window.setTimeout(function(){
			$("#error").html("");
	 	}, 3000);
	 	return false;
	});
	 window.setTimeout(function(){
		$("#error").html("");
	 }, 3000);
});
function genTimestamp(){
	var time = new Date();
	return time.getTime();
}
function changeCode(){
	//$("#captcha_img").attr("src", "/captcha.jpeg?t="+genTimestamp());
}
function choose_bg() {
	var bg = Math.floor(Math.random() * 9 + 1);

	document.getElementsByTagName("body")[0].style.backgroundImage = "url(images/loginbg_0"+ bg +".jpg)"; 
/* 	$('body').css('background-image', 'url(images/loginbg_0'+ bg +'.jpg)'); */
}
function changeImage(img){
        img.src=img.src +"?" +new Date().getTime();
}
</script>
</head>
<body>
<!--[if lte IE 6]>
<style type="text/css">
#errorie {position: fixed; top: 0; z-index: 100000; height: 30px; background: #FCF8E3;}
#errorie div {width: 900px; margin: 0 auto; line-height: 30px; color: orange; font-size: 14px; text-align: center;}
#errorie div a {color: #459f79;font-size: 14px;}
#errorie div a:hover {text-decoration: underline;}
</style>
<div id="errorie"><div>您还在使用老掉牙的IE，请升级您的浏览器到 IE8以上版本 <a target="_blank" href="http://windows.microsoft.com/zh-cn/internet-explorer/ie-8-worldwide-languages">点击升级</a>&nbsp;&nbsp;强烈建议您更改换浏览器：<a href="http://down.tech.sina.com.cn/content/40975.html" target="_blank">谷歌 Chrome</a></div></div>
<![endif]-->
<div class="main_box">
    <div class="setting"><a href="javascript:;" onclick="choose_bg();" title="更换背景"><span class="glyphicon glyphicon-th-large"></span></a></div>
	<div class="login_box">
        <div class="login_logo">
<%--            <img src="images/index_08.png" >--%>
        </div>
        <!--
		<c:if test="${!empty message}">
			<div class="login_msg">
	      		<font color="red">${message }</font>
	    	</div>
	    </c:if>
        -->
        <div class="login_form">
            <input type="hidden" value="${randomKey }" id="j_randomKey" />
    		<form action="/manage/login.im" id="login_form" method="post">
                <input type="hidden" name="jfinal_token" value="${jfinal_token }" />
    			<div class="form-group">
    				<label for="j_username" class="t">用户名：</label> <input id="j_username" value="${manager.username }" name="username" type="text" class="form-control x319 in" autocomplete="off">
    			</div>
    			<div class="form-group">
    				<label for="j_password" class="t">密　码：</label> <input id="j_password" value="" name="password" type="password" class="form-control x319 in">
    			</div>
    			<div class="form-group">
    				<label for="j_captcha" class="t">验证码：</label> <input id="j_captcha" name="checkcode" type="text" class="form-control x164 in">
    				<img id="captcha_img" src="/codeimg.code" onclick="changeImage(this)" class="m" alt="换一张" >
    			</div>
    			<div class="form-group">
                    <label class="t"></label>
                    <label for="j_remember" class="m"><input id="j_remember" type="checkbox" value="true">&nbsp;记住登录账号!</label>
    			</div>
    			<div class="form-group space">
    				<p class="m" id="error" style="text-align: center;color: red">${error}</p>
                    <label class="t"></label>　
    				<input type="submit" id="login_ok" value="&nbsp;登&nbsp;录&nbsp;" class="btn btn-primary btn-lg">&nbsp;&nbsp;&nbsp;&nbsp;
    				<input type="reset" value="&nbsp;重&nbsp;置&nbsp;" class="btn btn-default btn-lg">
    			</div>
    		</form>
        </div>
	</div>
<!-- 	<div class="bottom">Copyright &copy; 2014 - 2014 <a href="#">系统登录</a></div> -->
</div>
</body>
</html>