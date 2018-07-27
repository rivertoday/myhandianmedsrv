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
<title>我的下载</title>
<link rel="stylesheet" type="text/css" href="/phoneweb/css/f_base.css"/>
<link rel="stylesheet" type="text/css" href="/phoneweb/css/Ymobilezoom.css"/>
<script type="text/javascript" src="/phoneweb/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/phoneweb/js/js.js"></script>
<script type="text/javascript" src="/phoneweb/js/Ymobilezoom.js"></script>
<%@ include file="../css.jsp" %>
<script>
$(function(){
	
		$(".f_on_01 span").click(function(){
			
            $(this).parents(".f_pl_one").children(".f_hide").toggle();
		})
    // 文献tab切换
    $(".f_ul li").click(function(){
        var Index=$(this).index();
        $(this).find("a").addClass("f_one");
        $(this).siblings("li").find("a").removeClass("f_one");
        $(".f_all").find(".f_mysc").eq(Index).show().siblings(".f_mysc").hide();
    })
})

function del(id, types) {
	if (id != "" && id != undefined)  {
		confirmS("确定删除吗？", function() {
			location.href = "/phoneweb/user/download_cancel.html?id=" + id + "&types=" + types;
		});
	}
}


function upstatus(id) {
		$("dld"+id).removeAttr("onclick");
		$.ajax({
			type:"get",
			url:"/phoneweb/user/download_status.html",
			data:{id:id},
			success:function(msg) {
				$("#dld"+id).html("<img src=\"/client/images/f_wxq022.png\" />已下载");
			}
		});
	}
</script>
</head>
<body>
<header>
<div class="top2 fixedHeader">
	<div class="f_head_lf"><a href="/phoneweb/user/usercenter.html"><img src="/phoneweb/images/f_head01.png" /></a></div>
    <div class="f_head_ct">我的下载</div>
   </div>
</header>
<ul class="f_ul clearfix">
  <li><a <c:if test="${page.params.types == 1 }">class="f_one"</c:if> href="/phoneweb/user/downloadrecord.html">万方文献</a></li>
  <li><a <c:if test="${page.params.types == 2 }">class="f_one"</c:if> href="/phoneweb/user/downloadrecord.html?types=2">汉典文献</a></li>
</ul>
<div class="f_all">
    <div class="f_mysc clearfix">
    	<c:if test="${!empty page.results }">
			<c:forEach items="${page.results }" var="rs">
				<c:if test="${page.params.types == 1 }">
               		<div class="f_sc clearfix">
	                	<div class="f_sc_list">
	                    	<div class="f_sc_one"><a href="/phoneweb/literature/detail.html?articleId=${rs.literature.articleId }&types=${rs.literature.types }">${rs.literature.title }</a></div>
	                        <div class="f_sc_zz">作者：${rs.literature.creator }</div>
	                        <div class="f_sc_time">
	                        	<span>时间：${rs.literature.year }</span>
	                        	<span style=" float:right;">
	                        		<a style="margin-right:20px;" href="javascript:;" onclick="del('${rs.id}', '${page.params.types }');"><img src="/client/images/f_mynew1.png" />删除</a>
	                        		<a id="dld${rs.id }" <c:if test="${rs.isDownload == 0 }">onclick="upstatus(${rs.id });"</c:if> style="margin-right:25px;" target="_blank" href="${rs.literature.Spare1 }">
	                        		<img src="/client/images/f_wxq022.png" />
	                        					<c:if test="${rs.isDownload == 1 }">已下载</c:if>
			                        			<c:if test="${rs.isDownload == 0 }">下载</c:if>
	                        		</a>
	                        	</span>
	                        </div>
	                    </div>
	                </div>
	            </c:if>
              	<c:if test="${page.params.types == 2 }">
               		<div class="f_sc clearfix">
	                	<div class="f_sc_list">
	                    	<div class="f_sc_one"><a href="/phoneweb/product/literature.html?id=${rs.productLiterature.productId }">${rs.productLiterature.title }</a></div>
	                        <div class="f_sc_zz">作者：${rs.productLiterature.author }</div>
	                        <div class="f_sc_time">
	                        	<span>时间：<fmt:formatDate value="${rs.productLiterature.operateTime }" pattern="yyyy-MM-dd" /></span>
	                        	<span style=" float:right;">
	                        		<a href="javascript:;" onclick="del('${rs.id}', '${page.params.types }');"><img src="/client/images/f_mynew1.png" />删除</a>
	                        		<a id="dld${rs.id }" <c:if test="${rs.isDownload == 0 }">onclick="upstatus(${rs.id });"</c:if> style="margin-right:10px;" target="_blank" href="${rs.productLiterature.downloadUrl }">
	                        		<img src="/client/images/f_wxq022.png" />
	                        					<c:if test="${rs.isDownload == 1 }">已下载</c:if>
			                        			<c:if test="${rs.isDownload == 0 }">下载</c:if>
	                        		</a>
	                        	</span>
	                        </div>
	                    </div>
	                </div>
	            </c:if>
			</c:forEach>
		</c:if>                	
    </div>
</div>
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
					url:"/phoneweb/user/downloadrecord_more.html",
					data:{
						pageNo:pageNo,
						types:types
					},
					dataType:"json",
					success:function(data) {
					
						var content = "";
						if(types==1){
							for (var i=0; i<data.length; i++) {
							      content+=" <div class=\"f_sc clearfix\"> ";
				                  content+=" 	<div class=\"f_sc_list\"> ";
				                  content+="    	<div class=\"f_sc_one\"><a href=\"/phoneweb/literature/detail.html?articleId="+data[i].literature.articleId+"&types="+data[i].literature.types+"\">"+data[i].literature.title+"</a></div> ";
				                  content+="       <div class=\"f_sc_zz\">作者："+data[i].literature.creator+"</div> ";
				                  content+="       <div class=\"f_sc_time\"> ";
				                  content+="       	<span>时间："+data[i].literature.year+"</span> ";
				                  content+="       	<span style=\" float:right;\"> ";
				                  content+="       		<a style=\"margin-right:20px;\" href=\"javascript:;\" onclick=\"del('"+data[i].id+"', '"+data[i].types+"');\"><img src=\"/client/images/f_mynew1.png\" />删除</a> ";
				                  if(data[i].isDownload == 1){
				                  		content+="       		<a id=\"dld"+data[i].id+"\" style=\"margin-right:25px;\" target=\"_blank\" href=\""+data[i].literature.Spare1+"\"> ";
				                  }else{
				                  		content+="       		<a id=\"dld"+data[i].id+"\" onclick=\"upstatus("+data[i].id+");\" style=\"margin-right:25px;\" target=\"_blank\" href=\""+data[i].literature.Spare1+"\"> ";
				                  
				                  }
				                  content+="       		<img src=\"/client/images/f_wxq022.png\" /> ";
				                  if(data[i].isDownload == 1){
				                  		content+="已下载";
				                  }else{
				                  		content+="下载";
				                  }
				                  content+="       		</a> ";
					              content+="           	</span> ";
					              content+="           </div> ";
					              content+="       </div> ";
					              content+="   </div> ";
								
							}
						}else{
							for (var i=0; i<data.length; i++) {
								      content+=" <div class=\"f_sc clearfix\"> ";
					                  content+=" 	<div class=\"f_sc_list\"> ";
					                  content+="    	<div class=\"f_sc_one\"><a href=\"/phoneweb/product/literature.html?id="+data[i].productLiterature.productId+"\">"+data[i].productLiterature.title+"</a></div> ";
					                  content+="       <div class=\"f_sc_zz\">作者："+data[i].productLiterature.author+"</div> ";
					                  content+="       <div class=\"f_sc_time\"> ";
					                  content+="       	<span>时间："+data[i].productLiterature.operateTimeStr+"</span> ";
					                  content+="       	<span style=\" float:right;\"> ";
					                  content+="       		<a style=\"margin-right:20px;\" href=\"javascript:;\" onclick=\"del('"+data[i].id+"', '"+data[i].types+"');\"><img src=\"/client/images/f_mynew1.png\" />删除</a> ";
					                  if(data[i].isDownload == 1){
					                  		content+="       		<a id=\"dld"+data[i].id+"\" style=\"margin-right:25px;\" target=\"_blank\" href=\""+data[i].productLiterature.downloadUrl+"\"> ";
					                  }else{
					                  		content+="       		<a id=\"dld"+data[i].id+"\" onclick=\"upstatus("+data[i].id+");\" style=\"margin-right:25px;\" target=\"_blank\" href=\""+data[i].productLiterature.downloadUrl+"\"> ";
					                  
					                  }
					                  content+="       		<img src=\"/client/images/f_wxq022.png\" /> ";
					                  if(data[i].isDownload == 1){
					                  		content+="已下载";
					                  }else{
					                  		content+="下载";
					                  }
					                  content+="       		</a> ";
						              content+="           	</span> ";
						              content+="           </div> ";
						              content+="       </div> ";
						              content+="   </div> ";
									
								}
						
						}
						$(".f_mysc").append(content);
						isfasong = true;
					}
				}); 
				
			}
			
		});
	</script> 
<%@ include file="../bottom.jsp" %>
</body>
</html>