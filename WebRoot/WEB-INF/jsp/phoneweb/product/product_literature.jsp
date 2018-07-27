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
<title>${product.title }</title>
<link rel="stylesheet" type="text/css" href="/phoneweb/css/Ymobilezoom.css"/>
<link rel="stylesheet" type="text/css" href="/phoneweb/css/f_base.css"/>
<script type="text/javascript" src="/phoneweb/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/phoneweb/js/Ymobilezoom.js"></script>
<script type="text/javascript" src="/phoneweb/js/Yeffect16_0118.min.js"></script>
<%@ include file="../css.jsp" %>
<script>
	function collect(id) {
		$("#collect1" + id).hide();
		$("#collect2" + id).show();
		
		$.ajax({
			type:"get",
			url:"/phoneweb/collection.html",
			data:{id:id, types:2},
			success:function(msg) {
				if (msg == "ok") {
					alertS("已添加到收藏，请到个人中心-我的收藏查看详情");
				} else {
					$("#collect2" + id).hide();
					$("#collect1" + id).show();
				}
			}
		});
	}
	
	function downloadrecord(id) {
		$("#download1" + id).hide();
		$("#download2" + id).show();
		
		$.ajax({
			type:"get",
			url:"/phoneweb/download.html",
			data:{id:id, types:2},
			success:function(msg) {
				var ua = navigator.userAgent.toLowerCase();
				
				if (msg == "ok") {
					if (/iphone|ipad|ipod/.test(ua)) {	
						alertS("请到pc站下载");
					} else {
						alertS("已添加到下载，请到个人中心-我的下载查看详情");
					}
				} else if (msg = "already"){
					$("#download2" + id).hide();
					$("#download1" + id).show();
					alertS("您已经下载");
				} else {
					$("#download2" + id).hide();
					$("#download1" + id).show();
					alertS("下载次数达到上限");
				}
			}
		});
	}
</script>
</head>
<body>
<div class="f_div">
	<%@ include file="../header.jsp" %>
    <div class="f_list_all">
    	<c:if test="${!empty productLiteratures }">
	    	<c:forEach items="${productLiteratures }" var="productLiterature">
	    		<div class="f_lt_one">
                	<p class="f_p1"><a style=" color:#333;" href="#2">${productLiterature.title }</a></p>
                    <p class="f_p2"><span style=" display:inline; color:#333333; font-size:22px;">摘要：</span>${productLiterature.summary }</p>
<%--					<div style="text-align:right;" class="f_img clearfix"><img src="<%=basePath %>client/images/f_hd01.png" class="la_tu1" /><img src="<%=basePath %>client/images/f_hd1.png" class="la_tu2"/></div>--%>
					<div class="f_zuozhe3">
                    	<p>作者：${productLiterature.author }</p>
                    	<p>期刊号：${productLiterature.issue }</p>
                        <p>
<%--                        	发布日期：<fmt:formatDate value="${productLiterature.publishTime }" pattern="yyyy-MM"  />--%>
                        <c:if test="${productLiterature.isCollection == 1 }">
                        	<a style=" margin-left:480px; margin-right:24px;" href="javascript:;">
                        		<img src="/phoneweb/images/xingxing011.png" />
	                        </a>
                        </c:if>
                        <c:if test="${productLiterature.isCollection == 0 }">
                        	<a id="collect1${productLiterature.id }" style=" margin-left:480px; margin-right:24px;" href="javascript:;" onclick="collect('${productLiterature.id }');">
                        		<img src="/phoneweb/images/xingxing01.png" />
	                        </a>
                        	<a id="collect2${productLiterature.id }" style=" margin-left:480px; margin-right:24px; display: none;" href="javascript:;">
                        		<img src="/phoneweb/images/xingxing011.png" />
	                        </a>
                        </c:if>
                        <c:if test="${productLiterature.isDownload == 1 }">
                        	<a href="javascript:;">
	                       		<img src="/phoneweb/images/f_wxq022.png" />
	                      	</a>
                        </c:if>
                        <c:if test="${productLiterature.isDownload == 0 }">
                        	<a id="download1${productLiterature.id }" href="javascript:;" onclick="downloadrecord('${productLiterature.id }');">
	                        	<img src="/phoneweb/images/f_wxq02.png" />
	                       	</a>
	                       	<a id="download2${productLiterature.id }" href="javascript:;" style="display: none;">
	                       		<img src="/phoneweb/images/f_wxq022.png" />
	                      	</a>
                        </c:if>
                        </p>
                    </div>
                </div>
	    	</c:forEach>
	    </c:if>
     </div>
     <%--
     <div class="f_dib"><a href="#2"><img src="/client/images/f_db.png" /></a></div>
      --%>
</div>
<%@ include file="../bottom.jsp" %>
<script>
	$(function(){
		var n = true;
			$(".f_img").click(function(){
					
					if(n== true){
						 $(this).parents('.f_lt_one').addClass('tableLa');
						 n = false;
						}else{
						 $(this).parents('.f_lt_one').removeClass('tableLa');
						  n = true;
						}
					
					//$('this').siblings('.f_zjdp_top').css('height','auto')
					//alert(123)
					//$(this).addClass('k')
					
			})
			
			

			
		})

</script>	
<style>

</style>
</body>
</html>