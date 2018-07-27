<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

	<div class="bottom clearfix" style=" background:#fbc300; color:#fff;">
			<div class="laFOOTE">
	        	<a id="f_jing" style=" color:#fff;" href="javascript:void(0)">友情链接</a>
	            <div class="f_liangjie">
	            	<ul>
	                	<li><a href="http://www.handian.com/main.asp"  target="_blank" >汉典集团</a></li>
	                    <li><a href="http://www.hantien.com.cn/"  target="_blank" >汉典制药</a></li>
	                </ul>
	            </div>
            </div>
            <style>
				.bottom{ position:relative;}
            	.f_liangjie{ display:none; background:#fbc300; width:92px; position:absolute; left:50%; bottom:48px; margin-left:-242px;}
				.f_liangjie ul{}
				.f_liangjie ul li{}
				.f_liangjie ul li a{ color:#fff;}
				.laFOOTE{ display:inline-block; }
            </style>
            <script>
            	$(function(){
					$(".laFOOTE").hover(function(){
						$(".f_liangjie").show();
					},function(){
						$(".f_liangjie").hide();	
					})
				})
            </script>
            <a style=" color:#fff;" href="/about.html">关于我们</a>
            <a style=" color:#fff;" href="/secret.html">隐私及服务条款</a>
            <a style=" color:#fff;" href="http://www.risenb.com" target="_blank">技术支持：鸿睿思博</a>
    </div>
