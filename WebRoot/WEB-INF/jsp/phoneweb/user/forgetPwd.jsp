<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<title>找回密码</title>
<link rel="stylesheet" type="text/css" href="/phoneweb/css/f_base.css"/>
<link rel="stylesheet" type="text/css" href="/phoneweb/css/Ymobilezoom.css"/>
<script type="text/javascript" src="/phoneweb/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/phoneweb/js/Ymobilezoom.js"></script>
<%@ include file="../css.jsp" %>
<script>
$(function(){
$(".f_anniu input").click(function() {
			var phone = $("#phone").val();
			var code = $("#code").val();
			var password = $("#password").val();
			var repassword = $("#repassword").val();
			if (!/^1[3-9]\d{9}$/.test(phone)) {
				alert("手机号不正确");
				return;
			}
			if (!/\d{4}$/.test(code)) {
				alert("验证码不正确");
				return;
			}
			
			
			if (password.trim() == "" || password.trim() == undefined) {
				alert("请输入密码");
				return;
			}
			
			if (password != repassword) {
				alert("两次密码不一致");
				return;
			}
			
			$.ajax({
				type:"post",
				url:"/phoneweb/existence.html",
				data:{phone:phone},
				success:function(msg) {
					if (msg == "exist") {
						$.ajax({
	        				type:"post",
	        				url:"/phoneweb/code_check.html",
	        				data:{phone:phone, code:code},
	        				success:function(msg) {
	        					if (msg == "fail") {
	        	        			alert("验证码错误");
	        	        			return;
	        	        		} else {
	        	        			confirmS("确认修改吗？", function() {
	        	        	        				$("#myform").attr("action", "/phoneweb/user/password_forget.html").submit();
	        	        	        			});
	        	        		}
	        				}
	        			});
	        		} else {
	        			alert("手机号未注册");
	        			return;
	        		}
				}
			});
		});

})
</script>
</head>
<body>
<header>
	<div class="f_head_lf"><a href="/phoneweb/user/usercenter.html"><img src="/phoneweb/images/f_head01.png" /></a></div>
    <div style="padding-left:62px;" class="f_head_ct">找回密码</div>
   
</header>
<div class="f_denglu">
	<form action="" id="myform" method="post">
    <div class="f_user">
        <p class="f_user1"><img src="/phoneweb/images/f_ren02.png" /><input type="text" name="phone" id="phone" value="" placeholder="请输入您的手机号码" /></p>
        <p class="f_user2"><img src="/phoneweb/images/f_kuaijie.png" /><input type="text" id="code" name="code" placeholder="请输入验证码"/><input class="f_a" type="button" value="发送验证码" onclick="showtime(30)" />
        <p class="f_user2"><img src="/phoneweb/images/f_ren03.png" /><input type="password" id="password" name="password" placeholder="请输入新密码" /></p>
        <p class="f_user2"><img src="/phoneweb/images/f_ren03.png" /><input type="password" id="repassword" name="repassword" placeholder="请确认您的密码" />   </p>     
        <p class="f_anniu"><input type="button" name="" value="确认修改" /></p>
        
        
    </div>
    </form>
   
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
        $(".f_a").val( "重新发送（" + curTImr + "）");
        curTImr = 60;
        $(this).attr("endab", "false");
    	// 发送验证码
		var phone = $("#phone").val();
		if(!/^1[3-9]\d{9}$/.test(phone)) {
			alertS("请输入正确的手机号");
			return;
		}
		
		getCode(phone);	
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