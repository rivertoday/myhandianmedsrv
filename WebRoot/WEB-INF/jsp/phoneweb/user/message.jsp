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
<title>消息详情</title>
<link rel="stylesheet" type="text/css" href="/phoneweb/css/f_base.css"/>
<link rel="stylesheet" type="text/css" href="/phoneweb/css/Ymobilezoom.css"/>
<script type="text/javascript" src="/phoneweb/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/phoneweb/js/Ymobilezoom.js"></script>
<script type="text/javascript" src="/phoneweb/js/js.js"></script>

<%@ include file="../css.jsp" %>
<script>
$(function(){
	
		$(".f_on_01 span").click(function(){
			
            $(this).parents(".f_pl_one").children(".f_hide").toggle();
		})

})

function del(id) {
	if (id != "" && id != undefined)  {
		confirmS("确定删除吗？", function() {
			location.href = "/phoneweb/user/message_cancel.html?id=" + id;
		});
	}
}
</script>
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
					url:"/phoneweb/user/message_more.html",
					data:{
						pageNo:pageNo,
					},
					dataType:"json",
					success:function(data) {
						var content = "";
						
						for (var i=0; i<data.length; i++) {
				            content+="<li>";
				            if(data[i].image!=null){
					        	content+="	<img class=\"f_img1\" src=\""+data[i].image+"\" />";
					        }else{
					        	content+="	<img class=\"f_img1\" src=\"/client/images/message.png\" />";
					        }
					        content+="    <div class=\"f_new_nr\">";
					        content+="    	<p class=\"f_new_p1\"><span>"+data[i].operateTimeStr+"</span><a href=\"/phoneweb/user/message_detail.html?id="+data[i].id+"\">"+data[i].title+"</a></p>";
					       	if(data[i].content!=null){
					        	content+="        <p class=\"f_new_p2\">"+data[i].content+"</p>";
					        }else{
					        	content+="        <p class=\"f_new_p2\"></p>";
					        }
					        content+="        <p class=\"f_new_p3\"><a href=\"javascript:;\" onclick=\"del('"+data[i].id+"');\"><img src=\"/phoneweb/images/f_mynew1.png\" /></a></p>";
					        content+="    </div>";
				        	content+="</li>";
							
						}
						
						$(".clearfix").append(content);
						isfasong = true;
					}
				}); 
				
			}
			
		});
	</script> 
</head>
<body>
<header>
<div class="top2 fixedHeader">
	<div class="f_head_lf"><a href="/phoneweb/user/usercenter.html"><img src="/phoneweb/images/f_head01.png" /></a></div>
    <div class="f_head_ct">消息详情</div>
   </div>
</header>
<div class="f_new1">
	<ul class="clearfix">
				<c:if test="${!empty page.results }">
                	<c:forEach items="${page.results }" var="rs">
                		<li>
				        	<img class="f_img1" src="${!empty rs.image?rs.image:'/client/images/message.png' }" />
				            <div class="f_new_nr">
				            	<p class="f_new_p1"><span><fmt:formatDate value="${rs.operateTime }" pattern="HH:mm" /></span><a href="/phoneweb/user/message_detail.html?id=${rs.id }">${rs.title }</a></p>
				                <p class="f_new_p2">${rs.content }</p>
				                <p class="f_new_p3"><a href="javascript:;" onclick="del('${rs.id }');"><img src="/phoneweb/images/f_mynew1.png" /></a></p>
				            </div>
				        </li>
                	</c:forEach>
                </c:if>
    </ul>
</div>
<%@ include file="../bottom.jsp" %>

<script>
	$(function(){
			$(".f_sc_time a").click(function(){
				$(".masktan").show();
				$(".tuichu").show();
				})
			$(".tuichu a").click(function(){
				$(".masktan").hide();
				$(".tuichu").hide();
				})
		})
</script>
</body>
</html>