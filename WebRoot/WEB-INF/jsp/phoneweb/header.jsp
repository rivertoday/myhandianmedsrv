<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta http-equiv="Content-Type" content="text/html">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Expires" content="-1">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Pragma" content="no-cache">
<style>
.f_banner{width:100%; position:relative; overflow: hidden;}
.f_banner ul{width:100%; height:100%; position:absolute; left:0; top:0;}
.f_banner ul li{float:left; width:100%;}
.f_banner ul li img{width:386px; display:block; height:289px; margin:0 auto;}
.f_banner_kz{width:100%; position:absolute; left:0; bottom:20px; text-align: center;}
.f_banner_kz a{display:inline-block; width:16px; height:16px; border-radius:100%; background: #ccc; margin:0 5px;}
.f_banner_kz a.f_banner_dqys{background: #f00;}
</style>
	<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
<div class="fenxiangcontent fixedBox nosize">
	<div class="fenxiangcontentA">
        <b>分享到</b>
        <a class="jiathis_button_tsina"><img src="/phoneweb/images/fenxiangIco_01.png">新浪微博</a>
        <a class="jiathis_button_tqq"><img src="/phoneweb/images/fenxiangIco_02.png">腾讯微博</a>
        <a class="jiathis_button_qzone"><img src="/phoneweb/images/fenxiangIco_03.png">QQ空间</a>
<!--         <a class="jiathis_button_cqq"><img src="/phoneweb/images/f_qq.png">QQ好友</a> -->
        <a class="jiathis_button_weixin"><img src="/phoneweb/images/f_weixin.png">微信</a>
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
<script>
$(function(){
	$(".f_head_rt").click(function(){
		$(".fenxiangcontent").addClass("curhuscsd");
	})
	$(".fenxiangcontent i").click(function(){
		$(".fenxiangcontent").removeClass("curhuscsd");
	})
})
</script>

<header>
	<div style="z-index:100;" class="top2 fixedHeader">
        <div class="f_head_lf"><a href="javascript:history.go(-1)"><img src="/phoneweb/images/f_head01.png" /></a></div>
        <div class="f_head_ct">产品详情</div>
        <div class="f_head_rt"><a href="javascript:;" class="Inper_titSha"><img src="/phoneweb/images/f_head02.png" /></a></div>
    </div>
</header>
	<div class="f_banner">
		<ul>
			<c:if test="${!empty productImages }">
				<c:forEach items="${productImages }" var="productImage">
					<li>
						<img src="${productImage.image }" width="386px;" height="289px;" />
					</li>
				</c:forEach>
			</c:if>
		</ul>
		<div class="f_banner_kz">
			<c:forEach begin="1" end="${fn:length(productImages)}" step="1">
				<a href="javascript:void(0)" title=""></a>
			</c:forEach>
		</div>
	</div>
	<script>
		$(document).ready(function(){
			Yeffect.mobileBanner(".f_banner ul",".f_banner ul li",".f_banner_kz a","","","f_banner_dqys",5000,300,"easeOutExpo");
			
		})
	</script>
    <div class="f_nav">
    	<dl>
        	<dt><a href="/phoneweb/product/detail.html?id=${product.id }"><img src="/phoneweb/images/f_nav01.png" /></a></dt>
            <dd <c:if test="${types == 1 }">class="f_color"</c:if> style="padding:0;">
            	<a href="/phoneweb/product/detail.html?id=${product.id }">产品详情</a>
            </dd>
        </dl>
        <dl>
        	<dt><a href="/phoneweb/product/problem.html?id=${product.id }"><img src="/phoneweb/images/f_nav02.png" /></a></dt>
            <dd <c:if test="${types == 2 }">class="f_color"</c:if> style="padding:0;"><a href="/phoneweb/product/problem.html?id=${product.id }">常见问题</a></dd>
        </dl>
        <dl>
        	<dt><a href="/phoneweb/product/comment.html?id=${product.id }"><img src="/phoneweb/images/f_nav03.png" /></a></dt>
            <dd <c:if test="${types == 3 }">class="f_color"</c:if> style="padding:0;"><a href="/phoneweb/product/comment.html?id=${product.id }">大师点评</a></dd>
        </dl>
        <dl>
        	<dt><a href="/phoneweb/product/literature.html?id=${product.id }"><img src="/phoneweb/images/f_nav04.png" /></a></dt>
            <dd <c:if test="${types == 4 }">class="f_color"</c:if>><a href="/phoneweb/product/literature.html?id=${product.id }">产品文献</a></dd>
        </dl>
    </div>