<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的评论</title>
<%@ include file="../css.jsp" %>
<script>
	function del(id) {
		if (id != "" && id != undefined)  {
			confirmS("确定删除吗？", function() {
				location.href = "/user/comment_cancel.html?id=" + id;
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
        	<div class="f_wdpl">
        		<c:if test="${!empty page.results }">
                	<c:forEach items="${page.results }" var="rs">
                		<c:if test="${page.params.types == 1 }">
	                		<div class="f_pl_one">
			                	<div class="f_pl_on">
			                    	<div class="f_on_01 clearfix">${rs.literature.title }<span style="cursor: pointer;"><img src="/client/images/f_xinxi.png" />查看评论</span></div>
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
		    			$("#pageform").attr("action", "/user/comment.html").submit();
					}
					
					function pageSub1() {
						var valPageNo=$("#valPageNo").val();
						var totalPage='${page.totalPage}';
						if(valPageNo!="" && totalPage!=""){
							//alert(Number(valPageNo)<=Number(totalPage))
							if(Number(valPageNo)<=Number(totalPage)){
								$("#pageNo").val(valPageNo);
				    			$("#pageform").attr("action", "/user/comment.html").submit();
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
		$(".f_on_01 span").click(function(){
			/*$(".f_hide").toggle();*/
            $(this).parents(".f_pl_one").children(".f_hide").toggle();
		})
    	
		$(".f_person .on").hover(function(){
			$(".f_dw").show();
			})
		})
    </script>
</body>
</html>