<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta http-equiv="Content-Type" content="text/html">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Expires" content="-1">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Pragma" content="no-cache">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="1 days" name="revisit-after" />
<meta content="" name="keywords" />
<meta content="" name="description" />
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<meta content="telephone=no" name="format-detection" />
<meta content="false" id="twcClient" name="twcClient" />
<title>${product.title }</title>
<link rel="stylesheet" type="text/css" href="/phoneweb/css/Ymobilezoom.css"/>
<link rel="stylesheet" type="text/css" href="/phoneweb/css/f_base.css"/>
<script type="text/javascript" src="/phoneweb/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/phoneweb/js/Ymobilezoom.js"></script>
<script type="text/javascript" src="/phoneweb/js/jquery.easing.min.js"></script>
<script type="text/javascript" src="/phoneweb/js/js.js"></script>
<script type="text/javascript" src="/phoneweb/js/tcal.js"></script>
<%@ include file="../css.jsp" %>
<script>
	function collect(id) {
		$("#collect1").hide();
		$("#collect2").show();
		
		$.ajax({
			type:"get",
			url:"/phoneweb/collection.html",
			data:{id:id, types:1},
			success:function(msg) {
				if (msg == "ok") {
					alertS("已添加到收藏，请到个人中心-我的收藏查看详情");
				} else {
					$("#collect2").hide();
					$("#collect1").show();
				}
			}
		});
	}
	
	function downloadrecord(id) {
		$("#download1").hide();
		$("#download2").show();
		
		$.ajax({
			type:"get",
			url:"/phoneweb/download.html",
			data:{id:id, types:1},
			success:function(msg) {
				var ua = navigator.userAgent.toLowerCase();
				
				if (msg == "ok") {
					if (/iphone|ipad|ipod/.test(ua)) {	
						alertS("请到pc站下载");
					} else {
						alertS("已添加到下载，请到个人中心-我的下载查看详情");
					}
				} else {
					$("#download2").hide();
					$("#download1").show();
					alertS("下载次数达到上限");
				}
			}
		});
	}
	
	function getComment(id) {
		if (id != "" && id != undefined && id != null) {
			var pageNo = $("#pageNo").val();
			
			if (id == "" || id == undefined) {
				pageNo = 1;
			} 
			
			$.ajax({
				type:"post",
				url:"/phoneweb/literature/comment.html",
				data:{id:id, pageNo:pageNo},
				success:function(data) {
					var msg = eval("(" + data + ")");
					$("#plzs").text("评论(" + msg.success + ")");
					var result = msg.data;
					var con = "";
					
					if (result != "" && result != undefined) {
						for (var i=0; i<result.length; i++) {
							con += '<div class="f_zjdp_list" style="padding-bottom: 15px; padding-top: 15px;">';
							con += '<div class="f_zjdp_top">';
							con += '<div class="f_lt_img"><a href="javascript:;"><img src="' + result[i].headImg + '" width="60px;" height="60px;" style="border-radius: 30px;" /></a></div>';
							con += '<div class="f_rt_word">';
							con += '<p style=" color:#a8a8a8;" class="f_rt_p1">' + result[i].userName + '<span></span><span>' + result[i].createTimeStr + '</span></p>';
							con += '<p class="f_rt_p2 g_wypyzm g_pfgezx">';
							
							for (var j=1; j<=result[i].levels; j++) {
								con += '<img src="/client/images/f_pl04.png" />';
							} 
							
							con += '</p>';
							con += '</div></div>';
							con += '<div style=" color:#676768;" class="f_zjdp_btt">' + result[i].content + '</div>';
							
							if (i == 5) {
								con += '<div class="f_duo clearfix"><a href="javascript:;" onclick="getComment(' + id + ');">查看更多</a></div>';
							}
							
							con += '</div>';
						}
						
						if (pageNo != 1) {
							$(".f_duo").remove();
						}
						
						//alert(con);
						pageNo = Number(pageNo) + Number(1);
						$("#pageNo").val(pageNo);
						$(".f_zjdp").append(con);
						viewUpdate();
					} else {
						$(".f_duo").remove();
					}
				}
			});
		}
	}
	
	$(function() {
		getComment('${literature.id}');		
	});
	
	function pinglun() {
		var content = $("#content").val();
		
		 if (content == undefined || content.trim() == "") {
			alert("请填写评论内容");
			$(".masktan,.f_plun").show();
			return;
		} 
		
		$("#myform").attr("action", "/phoneweb/comment.html").submit();
	}
</script>
<script>
$(function(){
	$(".Inper_titSha").click(function(){
		$(".fenxiangcontent").addClass("curhuscsd");
	})
	$(".fenxiangcontent i").click(function(){
		$(".fenxiangcontent").removeClass("curhuscsd");
	})
})
</script>

<script>
							$(document).ready(function(e) {
								$(".g_pfgezx input").val(5)
								var pfaPd=false;
								$(".g_pfgezx a").addClass("g_pfhsx");
								
								$(".g_pfgezx").mousemove(function(e){
									if($(e.target).is("a")){
										$(this).find("a").addClass("g_pfhsx");
										var hdShu=$(".g_pfgezx a").index(e.target);
										//alert(hdShu)
										pfaPd=true;
										$(".g_pfgezx a").removeClass("g_pfhsx");
										for(var i=0;i<$(".g_pfgezx a").length;i++){
											if(i<=hdShu){
												$(".g_pfgezx a").eq(i).addClass("g_pfhsx");
											}	
										}
									}
									
								})
								
								
                                $(".g_pfgezx").hover(function(e){
									
								},function(){
									if(pfaPd){
										$(".g_pfgezx a").removeClass("g_pfhsx");
										var xtShu=$(".g_pfgezx input").val();
										for(var i=0;i<$(".g_pfgezx a").length;i++){
										if(i<xtShu){
											$(".g_pfgezx a").eq(i).addClass("g_pfhsx");
										}
										}
									}
									})
								$(".g_pfgezx a").click(function(){
									pfaPd=false;
									$(this).addClass("g_pfhsx");
									var xtShu=$(".g_pfgezx a").index(this);
									$(".g_pfgezx input").val(xtShu+1);
									for(var i=0;i<$(".g_pfgezx a").length;i++){
										if(i<xtShu){
											$(".g_pfgezx a").eq(i).addClass("g_pfhsx");
										}
									}
								})
                            });
						</script>
 <script>
 $(function(){
 
 	$(".f_pltop a").click(function(){
 		var phoneuser = '${sessionScope.phoneuser.id}';
 		
 		if (phoneuser != "" && phoneuser != undefined) {
			$(".masktan,.f_plun").show();
 		} else {
 			location.href = "/redirect.html?path=phoneweb/login";
 		}
	})
	$(".anniu li a").click(function(){
		$(".masktan,.f_plun").hide();
		})
	$(".f_plun input").click(function(){
		var content = $("#content").val();
		
		if (content == undefined || content.trim() == "") {
			return;
		}
		$(".masktan,.f_plun").hide();
		})


    var n = true;
        $(".f_rt_p2 img.f_huise").click(function(){
			if(n== true){ 
			$(this).parents('.f_rt_p2').addClass('f_lala');
			n=false;
			}else{
				$(this).parents('.f_rt_p2').removeClass('f_lala');
				}
          
		   
		   
		   
		  
        })
    
		$(".f_cc").click(function(){
			$(".f_plun,.masktan").hide();
			})
			
		$(".f_plun .f_in").click(function(){
			$(".f_queren").show();
		})
		
		$(".f_queren p input").click(function(){
			$(".f_queren").hide();
			$(".masktan,.f_plun").show();
		})
		})
 </script>

</head>
<body>

<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
<div class="fenxiangcontent fixedBox nosize">
	<div class="fenxiangcontentA">
        <b>分享到</b>
		<div class="f_fenxiang">
        <a class="jiathis_button_tsina"><img src="/phoneweb/images/fenxiangIco_01.png">新浪微博</a>
        <a class="jiathis_button_tqq"><img src="/phoneweb/images/fenxiangIco_02.png">腾讯微博</a>
        <a class="jiathis_button_qzone"><img src="/phoneweb/images/fenxiangIco_03.png">QQ空间</a>
<!--         <a class="jiathis_button_cqq"><img src="/phoneweb/images/f_qq.png">QQ好友</a> -->
        <a class="jiathis_button_weixin"><img src="/phoneweb/images/f_weixin.png">微信</a>
		</div>
        <i>取消</i>
    </div>
</div>
<style>
body{ font-size:10px;}
.fenxiangcontent{position:fixed; width:100%; height:100%; background:#fff; z-index:99999; top:-500%; transition:all 0.4s cubic-bezier(0.5,1,0.5,1);-webkit-transition:all 0.4s cubic-bezier(0.5,1,0.5,1);}
.fenxiangcontent.curhuscsd{top:0;}
.fenxiangcontent b{display:block; height:60px; line-height:60px; font-size:25px; text-align:center; border-bottom:1px solid #eee; font-weight:normal; background:#f6f6f6; color:#333;}
.fenxiangcontent a{display:block; height:55px; line-height:55px; font-size:20px; padding:0px 25px; border-bottom:1px solid #eee; text-decoration:none; color:#444;}
.fenxiangcontent a img{width:32px; height:32px; margin-bottom:-8px; margin-right:15px;}
.fenxiangcontent i{display:block; height:55px; border-top:1px solid #eee; position:absolute; left:0px; bottom:0px; width:100%; text-align:center; line-height:55px; font-size:20px; font-style:normal; background:#f9f9f9; cursor:pointer;}
</style>

<header>
	<div style="z-index:100;" class="top2 fixedHeader">
        <div class="f_head_lf"><a href="javascript:history.go(-1)"><img src="/phoneweb/images/f_head01.png" /></a></div>
        <div class="f_head_ct">文献详情</div>
        <div class="f_head_rt"><a href="javascript:;" class="Inper_titSha"><img src="/phoneweb/images/f_head02.png" /></a></div>
    </div>
</header>
<div class="f_wxq">
	<div class="f_neirong_one">
    	<p>${literature.source }<span>${literature.year }</span></p>
        <p>${literature.title }
        	<%--
        	<a href="#2"><img src="/client/images/f_hd01.png" /></a>
        	 --%>
        </p>
        <p>
        	<c:if test="${literature.isCollection == 1 }">
        		<a href="javascript:;">
					<img src="/phoneweb/images/xingxing011.png" />
				</a>
			</c:if>
			<c:if test="${literature.isCollection == 0 }">
				<c:if test="${!empty sessionScope.phoneuser }">
					<a id="collect1" href="javascript:;" onclick="collect('${literature.id }');">
						<img src="/phoneweb/images/xingxing01.png" />
					</a>
					<a id="collect2" style="display: none;" href="javascript:;">
						<img src="/phoneweb/images/xingxing011.png" />
					</a>
				</c:if>
				<c:if test="${empty sessionScope.phoneuser }">
					<a href="/redirect.html?path=phoneweb/login">
						<img src="/phoneweb/images/xingxing01.png" />
					</a>
				</c:if>
			</c:if>
        	<c:if test="${literature.isDownload == 1 }">
        		<a href="javascript:;">
	        		<img src="/phoneweb/images/f_wxq022.png" />
        		</a>
			</c:if>
			<c:if test="${literature.isDownload == 0 }">
				<c:if test="${!empty sessionScope.phoneuser }">
					<a id="download1" href="javascript:;" onclick="downloadrecord('${literature.id }');">
						<img src="/phoneweb/images/f_wxq02.png" />
					</a>
					<a id="download2" style="display: none;" href="javascript:;">
						<img src="/phoneweb/images/f_wxq022.png" />
					</a>
				</c:if>
				<c:if test="${empty sessionScope.phoneuser }">
					<a href="/redirect.html?path=phoneweb/login">
						<img src="/phoneweb/images/f_wxq02.png" />
					</a>
				</c:if>
			</c:if>
        </p>
        <p>作者：<a style="line-height: 50px;" href="javascript:;">${literature.creator }</a>
        	<%--
        	<c:forEach items="${fn:split(literature.creator, ';') }" var="creator">
        		<a href="javascript:;">${creator }</a>
        	</c:forEach>
        	 --%>
        </p>
        <p>作者单位：${literature.organization }</p>
    </div>
    <div class="f_neirong_two">
    	<div style="color:#959595; padding-left:19px; font-size:20px;">基本信息</div>
        <ul style="padding-left:0;">
            <li><span style="padding-left:2px; font-size:20px;">关　键　词</span><span>${literature.keywords }</span></li>
            <%--
        	<li><span>英文刊名</span><span>RARE METAL MATERIALS</span></li>
            <li><span>机标分类号</span><span>TG1 TM9</span></li>
            <li><span>机标关键词</span><span>锡镍合金</span></li>
            <li><span>DOI</span><span>10.3321/j.issn:1002-185X.2008.05.018</span></li>
             --%>
        </ul>
    </div>
    <div class="f_neirong_three">
    	<div class="f_zhaiyao">摘要</div>
        <div class="f_zhaiyao_word">
        	${literature.abstracts }
		</div>
    </div>
    <div class="f_hui"></div>
    <div class="f_pltop clearfix"><span id="plzs"></span><a href="javascript:;">我要评论</a></div>
    <input type="hidden" id="pageNo" value="1" /> 
    <div class="f_zjdp">
	</div>
<%@ include file="../bottom2.jsp" %>
<div style="display:none; height:1167px;" class="masktan fixedBox nosize"></div>
 <div style="display:none; height:575px; top:-50px;"  class="f_plun clearfix fixedBox">
        	<p>我要评论</p>
            <form id="myform" method="post">
            <p style="margin:0;" class="f_rt_p2 g_wypyzm g_pfgezx">评分：
				<input type="hidden" name="levels" value="5" />
				<a href="javascript:void(0)"></a>
				<a href="javascript:void(0)"></a>
				<a href="javascript:void(0)"></a>
				<a href="javascript:void(0)"></a>
				<a href="javascript:void(0)"></a>
			</p>
            <p>评价描述：</p>
            	<input type="hidden" name="literatureId" value="${literature.id }" />
            	<input type="hidden" name="articleId" value="${articleId }" />
            	<input type="hidden" name="type" value="${literature.types }" />
            	<input type="hidden" name="types" value="1" />
            	<p><textarea style="font-size:22px;width: 536px;" name="content" id="content" cols="47" rows="5"></textarea></p>
            </form>
            <p style="padding-left: 0px;"><input type="button" name="" value="确认提交" onclick="pinglun();" /></p>
            <div class="f_cc"><img src="/phoneweb/images/grxx06.png" /></div>
</div>
<div style="display:none;" class="f_queren fixedBox">
	<p>请填写评论内容</p>
    <p><input type="button" name="" value="确认" /></p>
</div>
</div>
 <style>
	.f_neirong_one p a img{position:relative; bottom:0;}
	.f_neirong_two ul li{ height:auto;}
 </style>
</body>
</html>
