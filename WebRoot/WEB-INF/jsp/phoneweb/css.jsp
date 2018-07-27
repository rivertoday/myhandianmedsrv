<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../client/check.jsp" %>

<script type="text/javascript" src="/client/js/shengShiXian.js"></script>
<script type="text/javascript" src="/client/js/grade.js"></script>
<link href="/client/css/uploadify.css" rel="stylesheet" type="text/css" />
<script src="/client/js/jquery.uploadify.min.js"  type="text/javascript" ></script>
<script type="text/javascript" src="/phoneweb/js/upload.js"></script>
<script type="text/javascript" src="/phoneweb/js/upload.js"></script>
<link rel="stylesheet" type="text/css" href="/client/css/tcal.css" />
<script type="text/javascript" src="/client/js//Yeffect16_0118.min.js"></script>

<style>
	.alertshowMask{background:rgba(0,0,0,0.5);height: 100%;left: 0;position: fixed;top: 0;width: 100%;z-index: 1000;}
	.alertshow{width:80%; height:120px;background:#fff;border-radius:10px; position:fixed; left:10%; top:50%; margin-top:-60px; z-index:1000;overflow: hidden;}
	.alertshow p{margin:0px; padding:0px; width:100%; height:66px; line-height:66px; color:#333; font-size:13px; text-align:center;}
	.alertshow i{display:block; width:50%; left:0px; height:44px;line-height:44px;position:absolute;color:#fff; font-size:14px; background:#ae2b2b; border:none;bottom:0;text-align:center; cursor: pointer;font-style: normal;overflow: hidden;box-sizing: border-box;-webkit-box-sizing: border-box; border-right:1px solid #fff;}
	.alertshow i:last-child{left:auto;right:0px;border:0px;border-left:1px solid #fff;}
	.alertshowD i{width:100%;border:0px !important;}
</style>

<script>
	//////全局方法////////
	function alertS(sty,fun){
		var sty=sty || "";
		var fun=fun || function(){};
		alertTAN(sty,fun,"alertshowD");
	}
	
	function confirmS(sty,fun){
		var sty=sty || "";
		var fun=fun || function(){};
		alertTAN(sty,fun);
	}
	
	function alertTAN(sty,fun,nam){
		if(nam){
			$("body").append("<div class='alertshowMask'><div class='alertshow alertshowD'><p>"+sty+"</p><i>确定</i></div></div>");
		}else{
			$("body").append("<div class='alertshowMask'><div class='alertshow'><p>"+sty+"</p><i>确定</i><i>取消</i></div></div>");
		}
		
		var alertshowMask=$(".alertshowMask");
		var alertshows=alertshowMask.find("i");
		//var alertshowsL=alertshows.length;
		alertshows.click(function(){
			if(alertshows.index(this)==0){
				fun();
			}
			alertshowMask.remove();
		})
	}
	
	//发送验证码到手机
	function getCode(phone) {
		$.ajax({
	    	type:"get",
	    	url:"/code.html",
	    	data:{phone:phone},
	    	async:false,
	    	timeout:"10000",
	    	success:function(msg) {
	    		
	    	}
		});
	}
</script>