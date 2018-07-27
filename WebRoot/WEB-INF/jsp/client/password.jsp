<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>找回密码</title>
<%@ include file="css.jsp" %>
<script>
	$(function() {
		$("#modify").click(function() {
			var phone = $("#phone").val();
			var code = $("#code").val();
			var password = $("#password").val();
			var repassword = $("#repassword").val();
			
			if (!/^1[3-9]\d{9}$/.test(phone)) {
				alert("请输入手机号码");
				return;
			}
			
			if (!/\d{4}$/.test(code)) {
				alert("请输入验证码");
				return;
			}
			
			if (password == "" || password == undefined) {
				alert("请输入密码");
				return;
			}
			
			if (repassword == "" || repassword == undefined) {
				alert("请输入确认密码");
				return;
			}
			
			if (password != repassword) {
				alert("两次输入的密码不一致");
				return;
			}
			
			$.ajax({
				type:"post",
				url:"/existence.html",
				data:{phone:phone},
				success:function(msg) {
					if (msg == "exist") {
	        			$("#myform").attr("action", "/password_forget.html").submit();
	        		} else {
	        			alert("手机号未注册");
	        			return;
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
                <div class="f_find">找回密码</div>
                <form id="myform" method="post">
	                <p><span>手机：</span><input style="background:url(/client/images/f_delu01.png) no-repeat 16px 8px;" type="text" id="phone" name="phone" placeholder="输入您的手机号" /></p>
	                <p><span>验证码：</span><input style=" text-indent:14px;width:147px;" type="text" id="code" name="code" placeholder="输入验证码" /><input style="width: 108px;font-size: 13px;margin-left: 26px; margin-top:0;" class="f_a" type="button" value="发送验证码" endab="true" onclick="showtime(30)" /></p>
	                <p><span>密码：</span><input type="password" id="password" name="password" placeholder="请输入您的新密码" /></p>
	                <p style="padding-left:66px;"> <input type="password" id="repassword" name="repassword" placeholder="请再次输入您的新密码" /></p>
                	<p style="padding-left:66px;"> <input type="button" id="modify" name="" value="确认修改" /></p>
            	</form>
            </div>
         </div>
    </div>
    <!--***-->
    
    <!--底部-->
    <%@ include file="bottom.jsp" %>
    <!--***-->
</body>
</html>