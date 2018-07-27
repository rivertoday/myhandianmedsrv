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
<title>我的评论</title>
<link rel="stylesheet" type="text/css" href="/phoneweb/css/f_base.css"/>
<link rel="stylesheet" type="text/css" href="/phoneweb/css/Ymobilezoom.css"/>
<script type="text/javascript" src="/phoneweb/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/phoneweb/js/js.js"></script>
<script type="text/javascript" src="/phoneweb/js/Ymobilezoom.js"></script>
<%@ include file="../css.jsp" %>
<script>
$(function(){
	
		addEventSP();

})

function addEventSP(){
	$(".f_on_01 span").each(function(){
		if($(this).hasClass("addEvt")) return;
		$(this).addClass("addEvt").click(function(){
            $(this).parents(".f_pl_one").children(".f_hide").toggle();
		})
	
	})
}



	function del(id) {
		if (id != "" && id != undefined)  {
			confirmS("确定删除吗？", function() {
				location.href = "/phoneweb/user/comment_cancel.html?id=" + id;
			});
		}
	}
</script>
<script>
		var isfasong = true;
		var pageNo = '${page.pageNo }';
		var totalPage = '${page.totalPage }';
		var types='${page.params.types}';
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
					url:"/phoneweb/user/comment_more.html",
					data:{
						pageNo:pageNo,
						types:types
					},
					dataType:"json",
					success:function(data) {
						var content = "";
						if(types==1){
							for (var i=0; i<data.length; i++) {
					         content+="   <div class=\"f_pl_one\">";
				             content+="   	<div class=\"f_pl_on\">";
				             content+="       	<div class=\"f_on_01 clearfix\">"+data[i].literature.title+"<span><img src=\"/phoneweb/images/f_xinxi0 (2).png\" />查看评论</span></div>";
				             content+="           <div class=\"f_on_02\">时间："+data[i].createTimeStr+" </div>";
				             content+="       </div>";
				             content+="       <div class=\"f_hide clearfix\">";
				             content+="       	<div class=\"f_hide_img\"><img width=\"61px;\" height=\"60px;\" src=\""+data[i].userDetail.headImg+"\" /></div>";
				             content+="           <div class=\"f_hide_word\">";
				             content+="           	<div class=\"f_hide_xing\">汉典-"+data[i].userDetail.userName+" ";
				             content+="           		<span>";
				             for(var j=0; j<data[i].levels.length; j++){
				             	content+="           				<img src=\"/client/images/f_pl04.png\" />";
				             }
				             content+="           		</span>";
				             content+="          	</div>";
				             content+="              <div class=\"f_hide_lu clearfix\">"+data[i].content+"<span><img onclick=\"del('"+data[i].id+"');\" src=\"/client/images/f_mynew1.png\" /></span></div>";
				             content+="           </div>";
				             content+="       </div>";
				             content+="   </div>";
								
							}
							
						}
						$(".f_wdpl").append(content);
						addEventSP();
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
    <div class="f_head_ct">我的评论</div>
   </div>
</header>
<div class="f_shezhi">
    			<c:if test="${!empty page.results }">
    <div class="f_wdpl">
                	<c:forEach items="${page.results }" var="rs">
                		<c:if test="${page.params.types == 1 }">
	                		<div class="f_pl_one">
			                	<div class="f_pl_on">
			                    	<div class="f_on_01 clearfix">${rs.literature.title }<span><img src="/phoneweb/images/f_xinxi0 (2).png" />查看评论</span></div>
			                        <div class="f_on_02">时间：<fmt:formatDate value="${rs.createTime }" pattern="yyyy-MM-dd" /></div>
			                    </div>
			                    <div class="f_hide clearfix">
			                    	<div class="f_hide_img"><img width="61px;" height="60px;" src="${userDetail.headImg }" /></div>
			                        <div class="f_hide_word">
			                        	<div class="f_hide_xing">汉典-${userDetail.userName }
			                        		<span>
			                        			<c:forEach begin="1" end="${rs.levels }" step="1">
			                        				<img src="/client/images/f_pl04.png" />
			                        			</c:forEach>
			                        		</span>
			                        	</div>
			                            <div class="f_hide_lu clearfix">${rs.content }<span><img onclick="del('${rs.id}');" src="/client/images/f_mynew1.png" /></span></div>
			                        </div>
			                    </div>
			                </div>
			            </c:if>
                	</c:forEach>
    </div>
                </c:if>
</div>
<%@ include file="../bottom.jsp" %>	
</body>
</html>