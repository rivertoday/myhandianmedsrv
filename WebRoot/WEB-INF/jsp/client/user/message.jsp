<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的消息</title>
<%@ include file="../css.jsp" %>
<script>
	function del(id) {
		if (id != "" && id != undefined)  {
			confirmS("确定删除吗？", function() {
				location.href = "/user/message_cancel.html?id=" + id;
			});
		}
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
        <div class="right right1 clearfix">
        	<div class="f_mynew_list">
        		<c:if test="${!empty page.results }">
                	<c:forEach items="${page.results }" var="rs">
                		<div class="f_mynew_one">
		                	<div class="f_one_img"><img src="${!empty rs.image?rs.image:'/client/images/message1.png' }" /></div>
		                    <div class="f_one_word">
		                    	<div class="f_one_top clearfix">
		                        	<div class="f_one_lf"><a <c:if test="${rs.isNew == 1 }">class="f_one_active"</c:if> href="/user/message_detail.html?id=${rs.id }">${rs.title }</a></div>
		                            <div class="f_one_rt"><fmt:formatDate value="${rs.operateTime }" pattern="yyyy-MM-dd" /><span></span><a href="javascript:;" onclick="del('${rs.id }');"><img src="/client/images/f_mynew1.png" /></a></div>
		                        </div>
		                        <div class="f_one_xm">${rs.content }</div>
		                    </div>
		                </div>
                	</c:forEach>
                </c:if>
            </div>
             <form id="pageform" action="" method="post">
	    		<input type="hidden" name="pageNo" id="pageNo" value="${page.pageNo }" />
             	<%@ include file="../page.jsp" %>
             </form>
             <script>
					function pageSub(p) {
						//$(this).css({"color":"#e71627","border-color":"#E71627"});
						$("#pageNo").val(p);
		    			$("#pageform").attr("action", "/user/message.html").submit();
					}
					
					function pageSub1() {
						var valPageNo=$("#valPageNo").val();
						var totalPage='${page.totalPage}';
						if(valPageNo!="" && totalPage!=""){
							//alert(Number(valPageNo)<=Number(totalPage))
							if(Number(valPageNo)<=Number(totalPage)){
								$("#pageNo").val(valPageNo);
				    			$("#pageform").attr("action", "/user/message.html").submit();
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
    <script>
    	$(function(){
			$(".f_person .on").hover(function(){
				$(".f_dw").show();
				})
			
			})
    </script>
    <style>
    	.la_fyUl{ width:100%; float:left; text-align:right; }
    </style>
</body>
</html>