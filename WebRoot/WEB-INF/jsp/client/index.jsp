<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>汉典医学</title>
<%@ include file="css.jsp"%>
<script type="text/javascript" src="/client/js/upload_headImg.js"></script>
<script>
	$(function() {
		$(document).keyup(function(event){
			if(event.keyCode == 13){
				$("#myform").attr("action", "/login.html").submit();
			}
		});
		
		$(".zc").click(function() {
// 			location.href = "/registerUI.html";
			alertS("抱歉，目前暂不接受注册");
			return;
		});
		
		$(".dl").click(function() {
			var phone = $("#phone").val();
			var password = $("#password").val();
			if(phone!=null && phone!="" && phone!=undefined){
				if (!/^1[3-9]\d{9}$/.test(phone)) {
					alertS("手机号不正确");
					return;
				}
			}else{
					alertS("手机号不能为空");
					return;
			}
			
			//if (password.trim() == "" || password.trim() == undefined) {
			if (password == "" || password == undefined) {
				alertS("请输入密码");
				return;
			}
			
			$.ajax({
				type:"post",
				url:"/password.html",
				data:{phone:phone, password:password},
				success:function(msg) {
					if (msg == "fail") {
						alertS("登录名或密码错误");
	        			return;
	        		} else if (msg == "frozen") {
	        			alertS("用户被冻结");
	        			return;
	        		} else {
	        			$("#myform").attr("action", "/login.html").submit();
	        		}
				}
			});
		});
	});
	
</script>
</head>
<style>
.swfupload {
	width: 120px;
	height: 120px;
	position: absolute;
	left: 76px;
	top: -120px;
}

.uploadify-button {
	opacity: 0;
	position: absolute;
}

#file {
	position: absolute;
}
</style>
<body>

	<!--头部-->
	<c:import url="/top.html"></c:import>
	<!--***-->
	<!--内容-->
	<div class="topbt clearfix"></div>
	<div class="f_zong">
		<div class="content clearfix">
			<!--左边-->
			<div class="left clearfix">
				<c:if test="${empty clientuser }">
					<div class="user clearfix">
						<p>用户登录</p>
						<form id="myform" method="post">
							<input class="txt" type="text" id="phone" name="phone" value="" />
							<input class="pss" type="password" id="password" name="password"
								value="" />
						</form>
						<div class="p1">
							<a href="/passwordUI.html">忘记密码？</a>
							<%--	                	<span><img src="/client/images/dl1.png" alt="" />无效的用户名或密码！</span>--%>
						</div>

						<div class="anniu">
							<input style="background:#ff9900;" class="dl" type="button"
								name="" value="登录" />
							<!-- <input style="background:#a40001;" class="zc" type="button" name="" value="注册" /> -->
						</div>
						<div class="p1">
							本站不提供自主注册，请联系管理员后台创建用户</a>
							<%--	                	<span><img src="/client/images/dl1.png" alt="" />无效的用户名或密码！</span>--%>
						</div>
						<!-- <div class="f_fangfa">
	                	<div class="f_fs">其他登录方式</div>
	                    <div class="f_fs_img"><a href="#2"><img src="/client/images/f_xq01.png" /></a><a href="#2"><img src="/client/images/f_xq03.png" /></a><a href="#2"><img src="/client/images/f_xq04.png" /></a></div>
	                </div> -->
					</div>
				</c:if>
				<c:if test="${!empty clientuser }">
					<div class="f_dlcg">
						<div class="f_person">
							<a class="on" href="javascript:;"> <img width="120px;"
								height="120px;" style="border-radius:60px;"
								src="${!empty userDetail.headImg?userDetail.headImg:'/client/images/message1.png' }" /></a>
							<!-- <input type="file" class="laSC"/> -->
							<input type="file" id="file" data-overhide="true"
								data-img="cimg1" class="upload" data-desc="*.jpg;*.png;*.gif"
								data-size="5MB"
								data-uploader="/upload/cfile.html?JSESSIONID=808C00C59FD895054C5A7DEFECB57A3B"
								data-auto="true" data-buttonClass="uploadbtn"
								data-buttonText="选择文件" data-multi=true data-name="headImg" />
							<div style="display:none;" class="f_dw">
								<img class="f_img_01" src="/client/images/f_person02.png" /><a
									href="#"><img src="/client/images/f_person01.png" /></a>
							</div>
						</div>
						<div class="f_person_word" style="padding-top: 12px;">
							<b class="sdflsdfsdf">${userDetail.nickNameStr }</b><br />
							<a class="updnickname"
								style="color:#999; text-decoration: underline;" href="#">修改昵称</a><br />
							<%-- <c:if test="${userDetail.status == 1 }">
		                	完善信息获得无限下载权限
	                	</c:if>
	                	<c:if test="${userDetail.status == 2 }">
		                	审核中
	                	</c:if>
	                	<c:if test="${userDetail.status == 3 }">
		                	无限下载权限
	                	</c:if> --%>
							<div class="f-tuichu">
								<a style="margin-right: 10px; font-size: 16px;"
									href="/user/detail.html">个人中心</a><a style="font-size: 16px;"
									href="/logout.html">安全退出</a>
							</div>
						</div>
						<div class="f_person_btn">
							<a href="/user/detail.html">${userDetail.status eq 1?'完善信息':userDetail.status eq 2?'审核中':'审核通过' }</a>
						</div>
					</div>
				</c:if>
				<div class="xuxian"></div>
				<p class="erweima">
					<img src="/client/images/about07.jpg" height="100" width="100" />
					<span style="font-weight:bold;">汉典国药健康公众号</span>
				</p>
				<div style="margin-bottom:32px;" class="xuxian line"></div>
			</div>
			<!--***-->
			<!--右边-->
			<div class="right right1 clearfix" style="min-height: 632px;">
				<!--头部-->
				<div class="top1 clearfix">
					<div class="dlS" style="background:#f4a836;">
						<a class="a" href="javascript:;" addr="/literature/list.html">
							<div class="dt">
								<img src="/client/images/about05.png" alt="" />
							</div>
							<div class="dd">万方文献</div>
						</a>
					</div>
					<div class="lf">
						<img src="/client/images/about09.png" alt="" />
					</div>
					<div class="dlS" style="background:#c92f2f;">
						<a class="a" href="javascript:;" addr="/product/list.html">
							<div class="dt">
								<img src="/client/images/about04.png" alt="" />
							</div>
							<div class="dd">汉典产品</div>
						</a>
					</div>
					<div class="lf">
						<img src="/client/images/about09.png" alt="" />
					</div>
					<div class="dlS" style="background:#56b4e5;">
						<a class="a" href="javascript:;" addr="/subject/list.html">
							<div class="dt">
								<img src="/client/images/about06.png" alt="" />
							</div>
							<div class="dd">健康自测</div>
						</a>
					</div>
				</div>
				<!--***-->
				<!--内容-->
				<div class="xia clearfix">
					<div class="saomiao">扫描二维码下载安装汉典医学APP也可使用相关文献服务。<br />
					苹果手机请自行通过“设置-设备描述”进行信任操作<br /></div>
					<br />
					<div class="saomiao"><h3>请注意：</h3>
					App用户系统属于<a href="https://www.handianmedicine.com/">“汉典云课堂”</a> <br/>
					与本网站用户系统非同一系统，需要另行注册登录，谢谢理解！<br />
					另外App下载托管服务有期限，下面二维码有效期至2019-07-18，届时会进一步更新。
					</div>
					<div class="xl">
						<div class="clearfix xlp">
							<table border="0">
								<tr>
									<td><img src="/client/images/qd02.png" alt="" /></td>
									<td><img src="/client/images/qd01.png" alt="" /></td>
								</tr>
								<tr>
									<td><img src="/client/images/about_android.png"
										height="150" width="150" /></td>
									<td><img src="/client/images/about_ios.png" height="180"
										width="180" /></td>
								</tr>
							</table>

						</div>

					</div>
					<div class="f_phone">
						<div class="xr clearfix">
							<ul>
								<li><img src="/client/images/01.jpg" /></li>
								<li><img src="/client/images/02.jpg" /></li>
								<li><img src="/client/images/03.jpg" /></li>
								<li><img src="/client/images/04.jpg" /></li>
								<li><img src="/client/images/05.png" /></li>
							</ul>
						</div>
					</div>
				</div>
				<!--***-->
			</div>
			<!--***-->
		</div>
	</div>
	<style>
.f_re {
	width: 192px;
	height: 130px;
	float: left;
}

.xr {
	width: 218px;
	height: 400px;
	position: relative;
	overflow: hidden;
}

.xr ul {
	position: absolute;
	width: 218px;
	height: 315px;
	left: 0;
	bottom: 0px;
}

.xr ul li {
	float: left;
}

.xr ul li img {
	width: 218px;
	height: 314px;
	padding: 0 0px;
}

.f_phone {
	background: url(/client/images/qd03.png) no-repeat 88px 19px;
	width: 400px;
	float: right;
}

.pcr span {
	cursor: pointer;
}

/*2016-5-7*/
.masktan {
	display: none;
	position: fixed;
	width: 100%;
	height: 100%;
	left: 0px;
	top: 0px;
	z-index: 100;
	opacity: 0.5;
	background: #000;
	filter: alpha(opacity = 50) \9;
}

.nicheng {
	display: none;
	width: 350px;
	background: white;;
	font-size: 24px;
	position: fixed;
	left: 50%;
	top: 34%;
	border-radius: 5px;
	z-index: 1000;
	margin-left: -175px;
}

ul.anniu li.f_02 input {
	border: none;
	background: white;
	width: 152px;
	height: 50px;
	line-height: 57px;
	color: #ff9567;
	font-size: 24px;
	padding-right: 53px;
	font-size: 16px;
	color: #333;
}

.nc {
	font-size: 24px;
	line-height: 60px;
	padding-left: 8px;
	width: 298px;
}

.work {
	width: 350px;
	background: white;;
	font-size: 24px;
	position: fixed;
	left: 50%;
	top: 34%;
	border-radius: 5px;
	z-index: 1000;
	margin-left: -175px;
}

.work p {
	padding: 24px 0px 18px 0px;
	font-size: 26px;
	text-align: center;
	color: #333333;
}
/*.work .text{ border:1px solid #dcdcdc; color:#333333; border-radius:5px; background:#f3f3f3; width:306px; height:60px; line-height:60px; text-indent:15px; margin:0 22px 25px 22px; }*/
.work .text img {
	float: right;
	margin: 17px 11px 17px 0;
}

.work ul {
	width: 306px;
	margin: 0 21px 0 21px;
	border-top: 1px solid #dcdcdc;
}

.work ul li {
	float: left;
}

.work ul li.f_01 {
	border-right: 1px solid #dcdcdc;
	width: 153px;
	height: 57px;
	line-height: 57px;
	text-align: center;
}

.work ul li.f_01 a {
	color: #333333;
}

.work ul li.f_02 {
	float: left;
	width: 152px;
	height: 57px;
	line-height: 57px;
	text-align: center;
}

.work ul li.f_02 a {
	color: #ff6501;
}

.work ul li a:hover {
	color: #ff6501;
}

input {
	border: 1px solid #dcdcdc;
	color: #333333;
	border-radius: 5px;
	background: #f3f3f3;
	width: 306px;
	height: 60px;
	line-height: 60px; /*text-indent:15px;*/
	margin: 0 22px 25px 22px;
}

.ding {
	position: absolute;
	right: 27px;
	bottom: 102px;
}
</style>
	<script>Yeffect.animat_banner(".xr ul",".xr ul li","retekr","dj","dj","",2000,0,true,"current");</script>
	<!--***-->
	<!--底部-->
	<%@ include file="bottom.jsp"%>
	<!--***-->
	<div class="masktan"></div>
	<div class="nicheng work clearfix">
		<p>编辑昵称</p>
		<input class="nc" type="text" name="nickName" id="nickName"
			value="${userDetail.nickName }" /> <a class="ding" href="#"><img
			src="/client/images/grxx07.png" alt="" /></a>
		<ul class="clearfix  anniu">
			<li class="f_01"><a href="#">取消</a></li>
			<li class="f_02"><input type="submit" name="" value="确定" /></li>

		</ul>
	</div>
	<script>
    $(function(){
			$('.pcr span').first().addClass('se');
			$('.f_re>div').not(':first').hide();	
			$('.pcr span').click(function(){
				$(this).addClass('se').siblings().removeClass('se');
				$('.f_re>div').eq($(this).index()).show().siblings().hide();	
	});
		})
		
	//弹窗
		$(function(){
					$(".updnickname").click(function(){
						$(".masktan,.nicheng").show();
						})
					$(".ding").click(function(){
						$(this).parent().find("input[type=text]").val("");
						});
						$(".anniu a").click(function(){
							$(".masktan,.nicheng").hide();
					});
					
					
				});	
				
		$(".nicheng input[type='submit']").click(function(){
			var nickName=$(".nicheng").find("input[name='nickName']").val();
			if(nickName!=null && nickName!="" && nickName.length<=10){
			
				$.ajax({
					type:"post",
					url:"/user/updNickName.html",
					data:{nickName:nickName},
					success:function(msg){
						if(msg=='nickNamenull'){
						alert("昵称不能为空");
						}else{
						$(".sdflsdfsdf").text(nickName);
						$(".masktan,.nicheng").hide();
						}
					},
					error:function(){
					alert("失败");
					}
				})
			}else{
				alert("昵称不能为空且长度不能超过10位");
			}
		});	
				
	</script>
</body>
</html>