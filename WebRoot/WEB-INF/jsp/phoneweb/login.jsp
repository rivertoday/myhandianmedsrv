<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="1 days" name="revisit-after" />
<meta content="" name="keywords" />
<meta content="" name="description" />
<meta name="viewport" content="initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no, width=device-width" />
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<meta content="telephone=no" name="format-detection" />
<meta content="false" id="twcClient" name="twcClient" />
<title>登录</title>
<link rel="stylesheet" type="text/css" href="/phoneweb/css/Ymobilezoom.css"/>
<link rel="stylesheet" type="text/css" href="/phoneweb/css/f_base.css"/>
<script type="text/javascript" src="/phoneweb/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/phoneweb/js/Ymobilezoom.js"></script>
<%@ include file="css.jsp" %>
<script>
	$(function() {
		$(".f_anniu input").click(function() {
			var agreement = $(".f_yue span").attr("class");
			var phone = $("#phone").val();
			var password = $("#password").val();
			
			 if (agreement.trim() != "on") {
				alertS("请同意用户协议");
				return;
			} 
			
			if (!/^1[3-9]\d{9}$/.test(phone)) {
				alertS("手机号不正确");
				return;
			}
			
			if (password.trim() == "" || password.trim() == undefined) {
				alertS("请输入密码");
				return;
			}
			
			$.ajax({
				type:"post",
				url:"/phoneweb/password.html",
				data:{phone:phone, password:password},
				success:function(msg) {
					if (msg == "fail") {
	        			alertS("登录名或密码错误");
	        			return;
	        		} else if (msg == "frozen") {
	        			alertS("用户被冻结");
	        			return;
	        		} else {
	        			$("#myform").attr("action", "/phoneweb/login.html").submit();
	        		}
				}
			});
		});
	});
</script>
</head>
<body>
<header>
	<div class="f_head_lf"></div>
    <div class="f_head_ct">登录</div>
</header>
<div class="f_denglu">
	<div class="f_logo"><a href="javascript:;"><img src="/phoneweb/images/f_ren01.png" /></a></div>
    <div class="f_user">
    	<form id="myform" method="post">
	        <p class="f_user1"><img src="/phoneweb/images/f_ren02.png" /><input type="number" id="phone" name="phone" placeholder="请输入您的手机号码" /></p>
	        <p class="f_user2"><img src="/phoneweb/images/f_ren03.png" /><input type="password" id="password" name="password" placeholder="请输入密码" /></p>
        </form>
        <div class="f_yue clearfix">
        <span class="on">阅读并同意使用<a style="color:red;" href="/phoneweb/agreement2.html"><<用户协议>></a></span> 
        <a href="/redirect.html?path=phoneweb/register">立即注册</a></div>
        <p class="f_anniu"><input type="button" name="" value="登录" /></p>
		<p class="f_wangji"><a href="/phoneweb/user/forgetPwdUI.html">忘记密码?</a></p>
        <div><img src="/phoneweb/images/f_ren04.png" /></div>
        <div class="f_qq clearfix">
        	<dl>
            	<dt><a href="javascript:;"><img src="/phoneweb/images/f_ren05.png" /></a></dt>
                <dd>QQ</dd>
            </dl>
            <dl>
            	<dt><a href="javascript:;"><img src="/phoneweb/images/f_ren06.png" /></a></dt>
                <dd>新浪微博</dd>
            </dl>
            <dl>
            	<dt><a href="javascript:;"><img src="/phoneweb/images/f_ren07.png" /></a></dt>
                <dd>微信</dd>
            </dl>
            <dl style="margin-right:0;">
            	<dt><a href="javascript:;"><img src="/phoneweb/images/f_ren08.png" /></a></dt>
                <dd>快捷登录</dd>
            </dl>
        </div>
    </div>
   
</div>

<script>
$(function(){
	     var laCkus = true;
        $(".f_yue span").click(function(){
			if(laCkus){
				$(this).addClass("on");
				laCkus = false;
			}else{
				$(this).removeClass("on");
				laCkus = true;
			}
            
			
        })
    })

</script>

<script>
	$(function(){
		var n = true;
			$(".f_img").click(function(){
					
					if(n== true){
						 $(this).parents('.f_zjdp_list').addClass('tableLa');
						 n = false;
						}else{
						 $(this).parents('.f_zjdp_list').removeClass('tableLa');
						  n = true;
						}
					
					//$('this').siblings('.f_zjdp_top').css('height','auto')
					//alert(123)
					//$(this).addClass('k')
					
			})
			
			
		})
</script>		
</body>
</html>