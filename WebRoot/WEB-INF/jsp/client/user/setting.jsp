<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>设置</title>
<%@ include file="../css.jsp" %>
<script>
	$(function() {
		$(".f_an input").click(function() {
			var phone = $("#phone").val();
			var code = $("#code").val();
			var oldpwd = $("#oldpwd").val();
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
			
			if (oldpwd.trim() == "" || oldpwd.trim() == undefined) {
				alert("请输入旧密码");
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
						$.ajax({
	        				type:"post",
	        				url:"/code_check.html",
	        				data:{phone:phone, code:code},
	        				success:function(msg) {
	        					if (msg == "fail") {
	        	        			alert("验证码错误");
	        	        			return;
	        	        		} else {
	        	        			$.ajax({
	        	        				type:"post",
	        	        				url:"/password_check.html",
	        	        				data:{phone:phone, oldpwd:oldpwd},
	        	        				success:function(msg) {
	        	        					if (msg == "fail") {
	        	        	        			alert("旧密码错误");
	        	        	        			return;
	        	        	        		} else {
	        	        	        			confirmS("确认修改吗？", function() {
	        	        	        				$("#myform").attr("action", "/user/setting.html").submit();
	        	        	        			});
	        	        	        		}
	        	        				}
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
	});
</script>
</head>

<body>
<!--头部-->
		<c:import url="/top.html"></c:import>
 <!--***-->
 <!--内容-->
    <div class="topbt clearfix"></div>
    <div class="content clearfix">
    <!--左边-->
    	<%@ include file="user_left.jsp" %>
        <!--***-->
        <!--右边-->
        <div class="right right1 clearfix">
        	<div class="f_xiu">
            	<div class="f_xg_pass">修改密码</div>
                <div class="f_bd">
                	<form id="myform" method="post">
                    	<p><span>请输入您的手机号</span><input type="text" id="phone" name="phone" value="${userLogin.phone }" readonly="readonly"/></p>
                        <p class="f_ym"><span>输入验证码</span><input type="text" id="code" name="code" value="" /><input class="f_a" type="button" endab="true" value="发送验证码" onclick="showtime(30)" />
</p>
                        <p><span>请输入您的旧密码</span><input type="password" id="oldpwd" name="oldpwd" value="" /></p>
                        <p><span>请输入您的新密码</span><input type="password" id="password" name="password" value="" /></p>
                        <p><span>再次输入新密码</span><input type="password" id="repassword" name="repassword" value="" /></p>
                        <p class="f_an"><input type="button" name="" style="margin-left: 192px;" value="确认修改" /></p>
                    </form>
                </div>
                
            </div>
        </div>
        <!--***-->
    </div>
	<!--***-->
    <!--底部-->
    <%@ include file="../bottom.jsp" %>
    <!--***-->
    <div style="display:none" class="masktan"></div>
    <div style="display:none" class="f_tc">
    	<dl>
        	<dt><img src="/client/images/f_sz01.png" /></dt>
            <dd>您的密码修改成功</dd>
        </dl>
        <div class="f_qd">确定</div>
    </div>
    <script>
		var ms = '${ms}';
		<%  
			session.setAttribute("ms", null);
		%>
		if (ms != "" && ms != undefined) {
			$(".masktan,.f_tc").show();
		}
		/*
		$(".f_bd .f_an").click(function(){
			$(".masktan,.f_tc").show();
			})
		*/
		$(".f_tc .f_qd").click(function(){
			$(".masktan,.f_tc").hide();
			})	
		
    	$(function(){
			$(".f_person .on").hover(function(){
				$(".f_dw").show();
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
    

    </script>
</body>
</html>