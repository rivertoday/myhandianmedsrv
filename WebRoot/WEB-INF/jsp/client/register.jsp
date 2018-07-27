<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册</title>
<%@ include file="css.jsp" %>
<script>
	$(function() {
		$("#regist").click(function() {
			var phone = $("#phone").val();
			var code = $("#code").val();
			var password = $("#password").val();
			var repassword = $("#repassword").val();
			
			if ($("#agreement").is(":checked") == false) {
				alert("请同意用户注册协议");
				return;
			}
			
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
				url:"/existence.html",
				data:{phone:phone},
				success:function(msg) {
					if (msg == "exist") {
	        			alert("手机号已注册");
	        			return;
	        		} else {
	        			$.ajax({
	        				type:"post",
	        				url:"/code_check.html",
	        				data:{phone:phone, code:code},
	        				success:function(msg) {
	        					if (msg == "fail") {
	        	        			alert("验证码错误");
	        	        			return;
	        	        		} else {
	        	        			$("#myform").attr("action", "/register.html").submit();
	        	        		}
	        				}
	        			});
	        		}
				}
			});
		});
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
				url:"/existence.html",
				data:{phone:phone},
				success:function(msg) {
					if (msg == "exist") {
	        			alert("手机号已注册");
	        			return;
	        		} else {
							getCode(phone);	
					        
					        $(".f_a").val(curTImr + "后重新发送");
					        curTImr = 60;
					        $(this).attr("endab", "false");
					    
					        stinxasss = setInterval(function() {
					            curTImr--;
					            $(".f_a").val(curTImr + "后重新发送");
					            
					            if (curTImr <= 0) {
					                curTImr = 60;
					                $(".f_a").val("发送验证码");
					                clearInterval(stinxasss);
					                $(".f_a").attr("endab", "true");
					            }
					        
					        }, 1000)
					        
					        	}
							}
						});
		        
		        
		    })  

	});
</script>
</head>

<body>
<!--头部-->
	<c:import url="/top.html"></c:import>
 <!--***-->
 <!--关于我们内容-->
    <div class="topbt clearfix"></div>
    <div class="content clearfix">
        <div class="f_denglu clearfix">
            <div class="f_denglu_img"><img src="/client/images/f_delu.png" /></div>
            <div class="f_denglu_input">
                <div class="f_find">注册</div>
                <form id="myform" method="post">
	                <p><span>手机：</span><input style="background:url(/client/images/f_delu01.png) no-repeat 16px 8px;" type="text" id="phone" name="phone" placeholder="输入您的手机号" /></p>
	                <p><span>验证码：</span><input style=" text-indent:14px;width:147px;" type="text" id="code" name="code" placeholder="输入验证码" /><input style="width: 108px;font-size: 13px;margin-left: 26px; margin-top:0;" class="f_a" type="button" value="发送验证码" endab="true" onclick="showtime(30)" /></p>
	                <p><span>密码：</span><input type="password" id="password" name="password" placeholder="请输入您的新密码" /></p>
	                <p style="padding-left:66px;"> <input type="password" id="repassword" name="repassword" placeholder="请再次输入您的新密码" /></p>
	                <div class="f_chek"><input type="checkbox" id="agreement" name="" value="1" />我已阅读并同意遵守<a href="javascript:;">《用户协议》</a></div>
                	<p style="padding-left:66px;"> <input type="button" id="regist" name="" value="注册" /></p>
                </form>
            </div>
         </div>
    </div>
    <!--***-->
    
    <!--底部-->
    <%@ include file="bottom.jsp" %>
    <!--***-->
<style>
	body{ position:relative;}
	
</style>


<!--***-->
    <div class="f_Popup"></div>
	<div class="f_Popup_xieyi">
	
     <div class="right clearfix">
        	<div class="f_tk"><img src="/client/images/f_tk01.png" /></div>
            <div class="f_tk_word">
            	${about.content }
            </div>
            
            <div class="f_tongyi"><input type="button" name="" value="关闭" /></div>
        </div>
    </div>
    
</div>
<style>
	body{ position:relative;}
	/*2016-5-9*/
	body{ position:relative;}
	.f_Popup{ display:none;background:#000; opacity:0.4; position:absolute; width:100%; height:100%; left:0; top:0;}
	.f_Popup_xieyi{display:none; position:fixed; left:50%; top:50%; margin-left:-358px; margin-top:-208px;width:716px; height:416px; background:#fff; border:2px solid #dedede; border-top:2px solid #fd9800;}
	.f_xieyi_nei{ padding-left:20px; }
	.f_Popup_xieyi{ padding-top:16px; padding-left:18px; padding-right:18px; font-size:12px; color:#333; }
	.f_xieyi_nei{line-height:24px;}
	.f_tongyi input{ width:300px; height:40px; background:#fd9800; border:none; border-radius:6px; font-size:14px; color:#fff;}
	.f_tongyi{ width:300px; margin:0 auto; padding-top:21px;}
	.f_tongyi input{ line-height:40px;}
	.f_Popup_xieyi{ overflow-y: scroll;}
	.f_tk img{ width:690px;}
</style>



<script>
//协议弹窗
	$(function(){
		$(".f_denglu_input .f_chek a").click(function(){
			$(".f_Popup,.f_Popup_xieyi").show();
			})
		$(".f_tongyi input").click(function(){
			$(".f_Popup,.f_Popup_xieyi").hide();
			})
		})
</script>
</body>
</html>