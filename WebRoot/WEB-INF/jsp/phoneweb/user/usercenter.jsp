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
<title>个人中心</title>
<link rel="stylesheet" type="text/css" href="/phoneweb/css/f_base.css"/>
<link rel="stylesheet" type="text/css" href="/phoneweb/css/Ymobilezoom.css"/>
<script type="text/javascript" src="/phoneweb/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/phoneweb/js/Ymobilezoom.js"></script>
<script type="text/javascript" src="/phoneweb/js/upload_head.js"></script>
<%@ include file="../css.jsp" %>
    <style>
        body{background:#f2f2f2;}
        .f_mem .top .pic img{width:100%; height:100%; border-radius:100%;}
    </style>
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
			
			//Yeffect.Html5FileReader(".zhe",".pic img");
			Upload_file_add(".pic","img","span",function(){
				if($(".pic").length>2){
					$(".pic").eq(0).remove();
				}
				var srcZhi=$(".pic").eq(0).find("img").attr("src");
				updHeadImg(srcZhi);
			},900,"",100,"jpeg");
			
			
		})
		
		
		
		function updHeadImg(headImg){
		if(headImg!=null && headImg!='' && headImg!=undefined){
			$.ajax({
			type:"post",
			url:"/phoneweb/user/updheadImg.html",
			data:{headImg:headImg},
			success:function(msg){
				if(msg=='fail'){
					alert("修改头像失败");
				}
				},error:function(){
					alert("失败");
				}
			})
		}else{
			alert("头像为空");
		}
		}
		
</script>
<style>
.pic span{
	display: none;
}
.pic.show span{
	display: block;background-color: rgba(0, 0, 0, 0.5);
     color: #fff;
    display: block;
    font-size: 23px;
    height: 25px;
    line-height: 20px;
    position: absolute;
    right: 0;
    text-align: center;
    top: 0;
    width: 25px;
    }
.f_mem .top .pic{overflow: visible;}
.pic.show span{display:none;}
.clearfix .pic:nth-child(2){display:block !important; opacity:0; position:absolute;}

</style>
</head>
<body>
<header>
<div class="top2 fixedHeader">
	<div class="f_head_lf"><a href="javascript:history.go(-1)"><img src="/phoneweb/images/f_head01.png" /></a></div>
    <div class="f_head_ct">个人中心</div>
  </div>  
</header>
<div class="f_mem">
    <div class="clearfix top">
        <div class="pic"><img src="${userDetail.headImg }" /><span >x</span><input type="file" class="zhe" style="display:block;"></a></div>
        <div class="tbox">
            <div class="clearfix text1"><span class="name">${userDetail.nickNameStr }</span><span class="xiul"></span><a href="javascript:void(0)" class="xiu">修改昵称</a><span class="xiur"></span></div>
            <div class="text2">
            	<c:if test="${userDetail.status == 1 }">
                	完善信息获得无限下载权限
               	</c:if>
               	<c:if test="${userDetail.status == 2 }">
                	审核中
               	</c:if>
               	<c:if test="${userDetail.status == 3 }">
                	无限下载权限
               	</c:if>
            </div>
        </div>
    </div>
    <ul class="box">
        <li class="list list1"><div class="text"><a href="/phoneweb/user/detail.html" style="color: #666666">基本信息</a></div></li>
        <li class="list list2"><div class="text"><a href="/phoneweb/user/downloadrecord.html" style="color: #666666">我的下载</a></div></li>
        <li class="list list3"><div class="text"><a href="/phoneweb/user/collection.html" style="color: #666666">我的收藏</a></div></li>
        <li class="list list4"><div class="text"><a href="/phoneweb/user/comment.html" style="color: #666666">我的评论</a></div></li>
        <li class="list list5<c:if test="${messageCount != 0 }"> on</c:if>"><div class="text"><a href="/phoneweb/user/message.html" style="color: #666666">我的消息</a></div></li><!-- 有消息时li添加class on -->
        <li class="list list6"><a href="/redirect.html?path=phoneweb/user/setting" style="color: #666666;display: block;">设置</a></li>
    </ul>
</div>
<%@ include file="../bottom.jsp" %>
<div class="masktan" style=""></div>
        <div style="display:none;" class="nicheng work clearfix">
        	<p>编辑昵称</p>
            <input class="nc" type="text" name="nickName" value="${userDetail.nickNameStr }" />
            <!--<a class="ding" href="#"><img src="img/grxx07.png" alt=""/></a>-->
            <div style=" text-align:center;" class="clearfix  laqueDcentent">
            	<a href="javascript:void(0)" class="f_quxiao">取消</a>
                <input class="f_queding" type="submit" name=""  value="确定"/>
               
            </div>
        </div>
		<style>
			.laqueDcentent .f_quxiao{ display:inline-block; padding-right: 37px; width:100px; height:60px; text-align:center;line-height:60px; color:#000; border-right:1px solid #e4e4e4;}
			.f_queding{ width:135px; height:60px; background:#fff; background:url("../images/m_zhe.png") no-repeat -5000px 0;   border:none; font-size:24px; padding-left:37px; cursor: pointer;}
			.laqueDcentent{ border-top:1px solid #e4e4e4;}
		</style>
        <script>
        	$(function(){
					$(".ding").click(function(){
						$(this).parent().find("input[type=text]").val("");
						})
						$(".text1 a.xiu").click(function(){
							$(".masktan,.nicheng").show();
					})
						$(".f_quxiao").click(function(){
							$(".masktan,.nicheng").hide();
					})
				})
				
				
			$(".nicheng input[type='submit']").click(function(){
			var nickName=$(".nicheng").find("input[name='nickName']").val();
			if(nickName!=null && nickName!="" && nickName.length<=10){
				$.ajax({
					type:"post",
					url:"/phoneweb/user/updNickName.html",
					data:{nickName:nickName},
					success:function(msg){
						if(msg=='nickNamenull'){
						alert("昵称不能为空");
						}else{
						$(".name").text(nickName);
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
		<style>
		body{position:relative;}
		</style>
</body>
</html>