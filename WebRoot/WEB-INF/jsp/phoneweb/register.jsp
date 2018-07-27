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
<title>注册</title>
<link rel="stylesheet" type="text/css" href="/phoneweb/css/Ymobilezoom.css"/>
<link rel="stylesheet" type="text/css" href="/phoneweb/css/f_base.css"/>
<script type="text/javascript" src="/phoneweb/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/phoneweb/js/Ymobilezoom.js"></script>
<%@ include file="css.jsp" %>
<script>
	$(function() {
		$("#regist").click(function() {
			var phone = $("#phone").val();
			var code = $("#code").val();
			var password = $("#password").val();
			var repassword = $("#repassword").val();
			
			if (!/^1[3-9]\d{9}$/.test(phone)) {
				alertS("手机号不正确");
				return;
			}
			
			if (!/\d{4}$/.test(code)) {
				alertS("验证码不正确");
				return;
			}
			
			if (password.trim() == "" || password.trim() == undefined) {
				alertS("请输入密码");
				return;
			}
			
			if (password != repassword) {
				alertS("两次密码不一致");
				return;
			}
			
			$.ajax({
				type:"post",
				url:"/phoneweb/existence.html",
				data:{phone:phone},
				success:function(msg) {
					if (msg == "exist") {
						alertS("手机号已注册");
	        			return;
	        		} else {
	        			$.ajax({
	        				type:"post",
	        				url:"/phoneweb/code_check.html",
	        				data:{phone:phone, code:code},
	        				success:function(msg) {
	        					if (msg == "fail") {
	        						alertS("验证码错误");
	        	        			return;
	        	        		} else {
	        	        			$("#myform").attr("action", "/phoneweb/register.html").submit();
	        	        		}
	        				}
	        			});
	        		}
				}
			});
		});
	});
</script>
</head>
<body>

<header>
	<div class="f_head_lf"><a href="/redirect.html?path=phoneweb/login"><img src="/phoneweb/images/f_head01.png" /></a></div>
    <div class="f_head_ct">注册</div>
</header>
<div class="f_denglu">
	
    <div class="f_user">
    	<form id="myform" method="post">
	        <p class="f_user1"><img src="/phoneweb/images/f_ren02.png" /><input type="number" id="phone" name="phone" placeholder="请输入您的手机号码" /></p>
	        <p class="f_user2"><img src="/phoneweb/images/f_kuaijie.png" /><input type="text" id="code" name="code" placeholder="请输入验证码" /><input class="f_a" type="button" value="发送验证码" endab="true" onclick="showtime(30)" />
	        <p class="f_user2"><img src="/phoneweb/images/f_ren03.png" /><input type="password" id="password" name="password" placeholder="请输入密码" /></p>
	        <p class="f_user2"><img src="/phoneweb/images/f_ren03.png" /><input type="password" id="repassword" name="repassword" placeholder="请确认您的密码" /></p>
		</form>
<!-- <div class="f_yue clearfix"><span class="on">阅读并同意使用<a href="#">用户协议</a></span><a href="/redirect.html?path=phoneweb/register"></a></div> -->
        
        <p class="f_anniu"><input type="button" name="" id="regist" value="注册" /></p>
        
        
    </div>
   
</div>

<script>
$(function(){
        $(".f_yue span").click(function(){
            $(this).addClass("on").removeClass("on").toggle();
        })
    })
	
	
	 var stinxasss;
    var curTImr = 60;
    $(".f_a").click(function() {
        if ($(this).attr("endab") == "false") {
            return false;
        }
        
     	// 发送验证码
		var phone = $("#phone").val();
		if(!/^1[3-9]\d{9}$/.test(phone)) {
			alertS("请输入正确的手机号");
			return;
		}
		$.ajax({
				type:"post",
				url:"/phoneweb/existence.html",
				data:{phone:phone},
				success:function(msg) {
					if (msg == "exist") {
						alertS("手机号已注册");
	        			return;
	        		} else {
	        			
							getCode(phone);
					        
					        $(".f_a").val( "重新发送（" + curTImr + "）");
					        curTImr = 60;
					        $(this).attr("endab", "false");
					    
					        stinxasss = setInterval(function() {
					            curTImr--;
					            $(".f_a").val( "重新发送（" + curTImr + "）");
					            
					            if (curTImr <= 0) {
					                curTImr = 60;
					                $(".f_a").val( "发送验证码");
					                clearInterval(stinxasss);
					                $(".f_a").attr("endab", "true");
					            }
					        
					        }, 1000)
        
        			}
				}
			});
        
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