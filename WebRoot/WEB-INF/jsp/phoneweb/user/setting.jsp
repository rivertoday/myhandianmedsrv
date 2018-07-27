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
<title>设置</title>
<link rel="stylesheet" type="text/css" href="/phoneweb/css/Ymobilezoom.css"/>
<link rel="stylesheet" type="text/css" href="/phoneweb/css/f_base.css"/>
<script type="text/javascript" src="/phoneweb/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/phoneweb/js/Ymobilezoom.js"></script>
<%@ include file="../css.jsp" %>
<script>
	$(function() {
		$("#logout").click(function() {
			confirmS("确定退出吗?", function() {
				location.href = "/phoneweb/logout.html";
			});
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
</head>
<body>
<style>
.f_shezhi{ padding-bottom:0;}
</style>
<div class="f_bg">
  <header>
  <div class="top2 fixedHeader">
      <div class="f_head_lf"><a href="/phoneweb/user/usercenter.html"><img src="/phoneweb/images/f_head01.png" /></a></div>
      <div class="f_head_ct">设置</div>
      </div>
  </header>
  <div class="f_shezhi">
      <ul style="margin-top:0;" class="clearfix">
          <li><div><a href="/phoneweb/about.html"><img class="f_img_02" src="/phoneweb/images/f_5.png" /><span class="f_sp_word">关于我们</span></a></div></li>
          <li><div><a href="/phoneweb/agreement.html"><img class="f_img_02" src="/phoneweb/images/f_5.png" /><span class="f_sp_word">用户协议</span></a></div></li>
          <li><div><a id="logout" href="javascript:;"><img class="f_img_02" src="/phoneweb/images/f_5.png" /><span class="f_sp_word">安全退出</span></a></div></li>
      </ul>    
      <div class="f_co"></div>
      <ul class="f_mima">    
          <li><div><a href="/phoneweb/user/findPwdUI.html"><img class="f_img_02" src="/phoneweb/images/f_5.png" /><span class="f_sp_word">修改/设置密码</span></a></div></li>
      </ul>
  </div>
  <footer class="fposf fixedFooter">
      <nav class="ducternav">
              <ul>
                  <li><a href="/phoneweb/literature/homepage.html"><img src="/phoneweb/images/f_footer1.png"><img src="/phoneweb/images/f_footer01.png">万典文献</a></li>
                  <li><a href="/phoneweb/product/list.html"><img src="/phoneweb/images/f_footer4.png"><img src="/phoneweb/images/f_footer04.png">汉典产品</a></li>
                  <li><a href="/phoneweb/subject/list.html"><img src="/phoneweb/images/f_footer2.png"><img src="/phoneweb/images/f_footer02.png">健康自测</a></li>
                  <li><a href="/phoneweb/user/usercenter.html" class="current_nava"><img src="/phoneweb/images/f_footer3.png"><img src="/phoneweb/images/f_footer03.png">个人中心</a></li>
              </ul>
      </nav>
  </footer>
</div>

</body>
</html>