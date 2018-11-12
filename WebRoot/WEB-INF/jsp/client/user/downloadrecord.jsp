<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的下载</title>
<%@ include file="../css.jsp" %>
<script>
	function del(id, types) {
		if (id != "" && id != undefined)  {
			confirmS("确定删除吗？", function() {
				location.href = "/user/download_cancel.html?id=" + id + "&types=" + types;
			});
		}
	}
	
	function upstatus(id) {
		$("dld"+id).removeAttr("onclick");
		$.ajax({
			type:"get",
			url:"/user/download_status.html",
			data:{id:id},
			success:function(msg) {
				$("#dld"+id).html("<img src=\"/client/images/f_wxq022.png\" />已下载");
			}
		});
	}
</script>
</head>

<body>
<!--头部-->
		<c:import url="/top.html"></c:import>
 <!--***-->
 <!--内容-->
    <div class="topbt clearfix"></div>
    <div style="border-bottom:1px solid #e4e4e4; margin-bottom:24px;" class="content clearfix">
    <!--左边-->
    	<%@ include file="user_left.jsp" %>
        <!--***-->
        <!--右边-->
        <div style="min-height:1111px;" class="right right1 clearfix">
        	<div class="f_mysc clearfix">
            	<ul class="clearfix">
                	<li><a <c:if test="${page.params.types == 1 }">class="f_one"</c:if> href="/user/downloadrecord.html">万方文献</a></li>
                    <li><a <c:if test="${page.params.types == 2 }">class="f_one"</c:if> href="/user/downloadrecord.html?types=2">汉典文献</a></li>
                </ul>
                <c:if test="${!empty page.results }">
                	<c:forEach items="${page.results }" var="rs">
                		<c:if test="${page.params.types == 1 }">
	                		<div class="f_sc clearfix">
			                	<div class="f_sc_list">
			                    	<div class="f_sc_one"><a href="/literature/detail.html?articleId=${rs.literature.articleId }&typ=${rs.literature.types }">${rs.literature.title }</a></div>
			                        <div class="f_sc_zz">期刊：${rs.literature.source }</div>
			                        <div class="f_sc_zz">作者：${rs.literature.creator }</div>
			                        <div class="f_sc_time">
			                        	<span>时间：${rs.literature.year }</span>
			                        	<span style=" float:right;">
			                        		<a id="dld${rs.id }" <c:if test="${rs.isDownload == 0 }">onclick="upstatus(${rs.id });"</c:if> style="margin-right:10px;" target="_blank" href="${rs.literature.spare1 }">
			                        			<img src="/client/images/f_wxq022.png" />
			                        			<c:if test="${rs.isDownload == 1 }">已下载</c:if>
			                        			<c:if test="${rs.isDownload == 0 }">下载</c:if>
			                        		</a>
			                        		<a href="javascript:;" onclick="del('${rs.id}', '${page.params.types }');"><img src="/client/images/f_mynew1.png" />删除</a>
			                        	</span>
			                        </div>
			                    </div>
			                </div>
			            </c:if>
                		<c:if test="${page.params.types == 2 }">
	                		<div class="f_sc clearfix">
			                	<div class="f_sc_list">
			                    	<div class="f_sc_one"><a href="/product/detail.html?id=${rs.productLiterature.productId }&diff=2">${rs.productLiterature.title }</a></div>
			                        <div class="f_sc_zz">作者：${rs.productLiterature.author }</div>
			                        <div class="f_sc_time">
			                        	<span>期刊：${rs.productLiterature.issue }</span>
			                        	<span style=" float:right;">
			                        		<a id="dld${rs.id }" target="_blank" <c:if test="${rs.isDownload == 0 }">onclick="upstatus(${rs.id });"</c:if> style="margin-right:10px;" href="${rs.productLiterature.downloadUrl }">
			                        			<img src="/client/images/f_wxq022.png" />
			                        			<c:if test="${rs.isDownload == 1 }">已下载</c:if>
			                        			<c:if test="${rs.isDownload == 0 }">下载</c:if>
			                        		</a>
			                        		<a href="javascript:;" onclick="del('${rs.id}', '${page.params.types }');"><img src="/client/images/f_mynew1.png" />删除</a>
			                        	</span>
			                        </div>
			                    </div>
			                </div>
			            </c:if>
                	</c:forEach>
                </c:if>
            </div>
            <form id="pageform" action="" method="post">
	    		<input type="hidden" name="pageNo" id="pageNo" value="${page.pageNo }" />
	    		<input type="hidden" name="types" id="types" value="${page.params.types }" />
             	<%@ include file="../page.jsp" %>
             </form>
             <script>
					function pageSub(p) {
						//$(this).css({"color":"#e71627","border-color":"#E71627"});
						$("#pageNo").val(p);
		    			$("#pageform").attr("action", "/user/downloadrecord.html").submit();
					}
					
					function pageSub1() {
						var valPageNo=$("#valPageNo").val();
						var totalPage='${page.totalPage}';
						if(valPageNo!="" && totalPage!=""){
							//alert(Number(valPageNo)<=Number(totalPage))
							if(Number(valPageNo)<=Number(totalPage)){
								$("#pageNo").val(valPageNo);
				    			$("#pageform").attr("action", "/user/downloadrecord.html").submit();
			    			}else{
			    				alert("无此页");
			    			}
		    			}
					}
				</script>
        </div>
        	
        <!--***-->
    </div>
	<!--***-->
    <!--底部-->
    <%@ include file="../bottom.jsp" %>
    <!--***-->
</body>
</html>