<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
	<meta http-equiv="Content-Type" content="text/html"/>
	<meta name="apple-mobile-web-app-capable" content="yes"/>
	<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
	<meta name="format-detection" content="telephone=no"/>
	<meta http-equiv="Expires" content="-1"/>
	<meta http-equiv="Cache-Control" content="no-cache"/>
	<meta http-equiv="Pragma" content="no-cache"/>
    <title>万方文献</title>
    <link rel="stylesheet" type="text/css" href="/phoneweb/css/f_base.css"/>
    <link rel="stylesheet" href="/phoneweb/css/Ymobilezoom.css"/>
    <script type="text/javascript" src="/phoneweb/js/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="/phoneweb/js/TouchSlide.1.1.js"></script>
    <script type="text/javascript" src="/phoneweb/js/Ymobilezoom.js"></script>
    <link rel="stylesheet" href="/phoneweb/css/swiper.min.css"/>
    <%@ include file="../css.jsp" %>
	<script>
		// 根据高度隐藏底部导航
		$(function(){
			$(window).resize(LAfn);
		})
		function LAfn(){
			var windowH = $(window).height();
			if(windowH < 500){
				
				$('.fixedFooter').hide();
			}else{
				$('.fixedFooter').show();
			}
		}
	</script>
    <script>
		function sea() {
			$("#myform").attr("action", "/phoneweb/literature/list.html").submit();
		}
		
		$(function() {
			$(".swiper-slide a").click(function() {
				$("#title").val($(this).attr("title"));
				$("#myform").attr("action", "/phoneweb/literature/list.html").submit();
			});
		})
	</script> 
    <script>
    $(function(){
		
		
        //下滑搜索框佳背景色
        $(window).scroll(scrollFUNcss)
		scrollFUNcss();
		function scrollFUNcss(){
            var t = $(document).scrollTop();
            if(t>0){
                $(".top1hte").css("background","#f6f6f6")
            }else{
                $(".top1hte").css("background","")
            }

        }
		
		
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
.top1hte{ padding-top:0; height:61px;}
</style>
<div class="f_intop">
    <div style=" padding-top:14px;" class="clearfix top1 top1hte fixedHeader">
        <div style="position:relative;" class="clearfix ss">
        	<form  id="myform" method="post">
            	<input type="text" name="title" id="title" value="${!empty page.params.title ? page.params.title : '' }" placeholder="搜索" class="fl sou"/>
            	<input type="button" value="" onclick="sea();" class="fl suo" />
        	</form>
        </div>
        <a href="/phoneweb/literature/searchUI.html" class="gaoji">高级检索</a>
    </div>
    <div id="slideBox" class="slideBox">
        <div class="bd">
            <ul>
            	<c:if test="${!empty banners }">
            		<c:forEach items="${banners }" var="banner">
                		<li><a href="javascript:void(0)" target="_blank"><img src="${banner.image }" ></a></li>
            		</c:forEach>
            	</c:if>
            </ul>
        </div>
        <div class="hd">
            <ul></ul>
        </div>
    </div>
   <script type="text/javascript">
        TouchSlide({
            slideCell:"",
            titCell:"", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
            mainCell:"",
            effect:"leftLoop",
            autoPage:true,//自动分页
            autoPlay:true, //自动播放
            delayTime:400,//切换效果持续时间
            interTime:500000//自动运行间隔
        });
    </script>
    <div class="swiper-container" id="fi">
        <div class="swiper-wrapper">
            <div class="swiper-slide"><a style=" display: inline-block;" title="外科学" href="javascript:;"><img src="/phoneweb/images/f_int1.jpg" class="pic"/><div class="text">外科学</div></a></div>
            <div class="swiper-slide"><a style=" display: inline-block;" title="临床医学" href="javascript:;"><img src="/phoneweb/images/f_int2.jpg" class="pic"/><div class="text">临床医学</div></a></div>
            <div class="swiper-slide"><a style=" display: inline-block;" title="基础医学" href="javascript:;"><img src="/phoneweb/images/f_int3.jpg" class="pic"/><div class="text">基础医学</div></a></div>
            <div class="swiper-slide"><a style=" display: inline-block;" title="预防医学" href="javascript:;"><img src="/phoneweb/images/f_int4.jpg" class="pic"/><div class="text">预防医学</div></a></div>
            <div class="swiper-slide"><a style=" display: inline-block;" title="卫生学" href="javascript:;"><img src="/client/images/f_wdwx07.png" width="80px" height="80px" class="pic"/><div class="text">卫生学</div></a></div>
            <div class="swiper-slide"><a style=" display: inline-block;" title="药学" href="javascript:;"><img src="/client/images/f_wdwx08.png" width="80px" height="80px" class="pic"/><div class="text">药学</div></a></div>
            <div class="swiper-slide"><a style=" display: inline-block;" title="中医" href="javascript:;"><img src="/client/images/f_wdwx09.png" width="80px" height="80px" class="pic"/><div class="text">中国医学</div></a></div>
            <div class="swiper-slide"><a style=" display: inline-block;" title="特种医学" href="javascript:;"><img src="/client/images/f_wdwx10.png" width="80px" height="80px" class="pic"/><div class="text">特种医学</div></a></div>
        </div>
        <!-- Add Pagination -->
        <div class="swiper-pagination"></div>
    </div>
    <script src="/phoneweb/js/swiper.min.js"></script>
    <script>
        var swiper = new Swiper('.swiper-container', {
            pagination: '.swiper-pagination',
            slidesPerView: 4,
            paginationClickable: true,
            spaceBetween: 0,
            nextButton: '.swiper-button-next',
            prevButton: '.swiper-button-prev',
            loop: false,
            /*centeredSlides: false,
            autoplay: 3000,
            autoplayDisableOnInteraction: false*/  /* 自动滚动就显示 */
        });
    </script>
</div>
<div class="f_inbox">
    <div class="clearfix box1"><span class="text1">文献中心</span><a href="/phoneweb/literature/list.html?isexist=1" class="text2">更多</a></div>
    <ul class="clearfix box2">
    	<c:if test="${!empty page.results }">
    		<c:forEach items="${page.results }" var="rs">
    			<li class="list">
		            <a href="/phoneweb/literature/detail.html?articleId=${rs.articleId }&types=${rs.types }">
		                <div class="text1">${rs.title }</div>
		                <div class="clearfix text2"><span class="tm">${rs.source }</span><span class="time">${rs.year }</span></div>
		                <div class="text3">作者：<span class="zuo">${rs.creator }</span></div>
		                <div class="text4">作者单位：<span class="zuo">${rs.organization }</span></div>
		            </a>
		        </li>
    		</c:forEach>
    		
    	</c:if>
    </ul>
<%--    <div class="shaxin"><img src="/phoneweb/images/f_shaxin.png" /></div>--%>
</div>
 <script>
		var isfasong = true;
		var pageNo = '${page.pageNo }';
		var totalPage = '${page.totalPage }';
		
		$(window).scroll(function(){
			if(!isfasong){
				return;	
			}
			
			var docueHt=$(document).height();
			var windhw=$(window).height();
			var sctop=$(window).scrollTop();
			
			if(sctop+windhw>docueHt-2 && pageNo < totalPage) {
				isfasong = false;
				pageNo = Number(pageNo) + 1;
				$.ajax({
					type:"post",
					url:"/phoneweb/literature/home_more.html",
					data:{
						pageNo:pageNo,
					},
					dataType:"json",
					success:function(data) {
						var content = "";
						
						for (var i=0; i<data.length; i++) {
							content += "<li class=\"list\">";
				            content += "<a href=\"/phoneweb/literature/detail.html?articleId="+data[i].articleId+"&types="+data[i].types+"\">";
				            content += "    <div class=\"text1\">"+data[i].title+"</div>";
				            content += "    <div class=\"clearfix text2\"><span class=\"tm\">"+data[i].source+"</span><span class=\"time\">"+data[i].year+"</span></div>";
				            if(data[i].creator!=undefined){
				            	content += "    <div class=\"text3\">作者：<span class=\"zuo\">"+data[i].creator+"</span></div>";
				            }else{
				            	content += "    <div class=\"text3\">作者：<span class=\"zuo\"></span></div>";
				            }
				            if(data[i].organization!=undefined){
				            	content += "    <div class=\"text4\">作者单位：<span class=\"zuo\">" +data[i].organization+ "</span></div>";
				            }else{
				            	content += "    <div class=\"text4\">作者单位：<span class=\"zuo\"></span></div>";
				            
				            }
				            content += "</a>";
				        	content += "</li>";
							
						}
						
						$(".f_inbox .box2").append(content);
						isfasong = true;
					}
				}); 
				
			}
			
		});
	</script> 
<%@ include file="../bottom.jsp" %>
<script type="application/JavaScript" src="http://istyles.blog.163.com/blog/js/iscroll.js"></script>
<script type="text/javascript">
var myScroll;
function loaded() {
    myScroll = new iScroll('fi');
}
document.addEventListener('DOMContentLoaded', loaded, false);
window.onload = function() { 
    setTimeout(function(){ new iScroll(document.getElementById('scroller')) }, 100) 
};
</script>

</body>
</html>