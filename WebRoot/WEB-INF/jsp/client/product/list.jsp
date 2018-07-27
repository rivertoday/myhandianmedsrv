<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>汉典产品</title>
<%@ include file="../css.jsp" %>
</head>

<body>
<!--头部-->
		<c:import url="/top.html"></c:import>

 <!--***-->
 <!--关于我们内容-->
    <div class="topbt clearfix"></div>
    <div class="content clearfix">
    <!--左边-->
    	<c:import url="/left.html?types=2"></c:import>
        <!--***-->
        <!--右边-->
        <div style="border-left:1px solid #e4e4e4; min-height:986px;" class="right clearfix">
            <ul class="f_pro">
            	<c:if test="${!empty page.results }">
            		<c:forEach items="${page.results }" var="rs">
            			<li class="f_plist">
		                    <ul>
		                        <li class="pic"><a href="/product/detail.html?id=${rs.id }&diff=1"><img src="${rs.image }"/></a></li>
		                        <li class="com">
		                            <div class="bt"><a href="/product/detail.html?id=${rs.id }&diff=1">${rs.title }</a></div>
		                            <div class="con">${rs.introduction }</div>
		                        </li>
		                    </ul>
		                </li>
            		</c:forEach>
            	</c:if>
                <div class="clear"></div>
            </ul>
            <form id="pageform" action="" method="post">
	    		<input type="hidden" name="pageNo" id="pageNo" value="${page.pageNo }" />
	    		<input type="hidden" name="title" id="title" value="${page.params.title }" />
	             <script>
					function pageSub(p) {
						//$(this).css({"color":"#e71627","border-color":"#E71627"});
						$("#pageNo").val(p);
		    			$("#pageform").attr("action", "/product/list.html").submit();
					}
					
					function pageSub1() {
						var valPageNo=$("#valPageNo").val();
						var totalPage='${page.totalPage}';
						if(valPageNo!="" && totalPage!=""){
							//alert(Number(valPageNo)<=Number(totalPage))
							if(Number(valPageNo)<=Number(totalPage)){
								$("#pageNo").val(valPageNo);
				    			$("#pageform").attr("action", "/product/list.html").submit();
			    			}else{
			    				alert("无此页");
			    			}
		    			}
					}
				</script>
             	<%@ include file="../page.jsp" %>
             </form>
        </div>
        <!--***-->
    </div>
    <!--***-->
    
    <!--底部-->
    <%@ include file="../bottom.jsp" %>
    <!--***-->
</body>
</html>