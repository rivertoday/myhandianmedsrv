<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${subject.title }</title>
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
        <div style="border-right:1px solid #e4e4e4;border-left:1px solid #e4e4e4;" class="right clearfix">
        	<div class="f_xq_top clearfix">
            	<div class="f_xq_lf">${subject.title }</div>
                <div class="f_xq_rt">
                	<a class="jiathis_button_cqq" href="javascript:;"><img src="/client/images/f_xq01.png" />QQ</a>
                    <a class="jiathis_button_qzone" href="javascript:;"><img src="/client/images/f_xq02.png" />空间</a>
                    <%--
                    <a class="jiathis_button_weixin" href="javascript:;"><img src="/client/images/f_xq03.png" />微信</a>
                     --%>
                    <a class="jiathis_button_tsina" href="javascript:;"><img src="/client/images/f_xq04.png" />新浪微博</a>
                    <a class="jiathis_button_weixin" href="javascript:;"><img src="/client/images/f_xq05.png" />微信</a>
                    <a class="jiathis_button_tqq" href="javascript:;"><img src="/client/images/f_xq06.png" />腾讯微博</a>
                    <!-- JiaThis Button BEGIN -->
					<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js?uid=" charset="utf-8"></script>
					<!-- JiaThis Button END -->
                </div>
            </div>
            <div class="f_xq_banner">
            	<div class="f_xq_b1"><img src="${subject.image }" /></div>
                <div class="f_xq_b2">${subject.instruction }</div>
                <div class="f_xq_b3">请根据近一年的体验和感觉，回答以下问题：</div>
            </div>
            <form id="myform" method="get">
				<input type="hidden" name="subjectId" value="${subject.id }" />
	            <c:if test="${subject.types == 1 || subject.types == 2 }">
	            	<div class="f_xq_list">
	            		<c:if test="${!empty subjectQuestions }">
				    		<c:forEach items="${subjectQuestions }" var="subjectQuestion">
				    			<div class="f_xq_ti">
				                	<p class="f_xq_tword">${subjectQuestion.question }</p>
				                    <p class="f_xq_twordf">
				                    	<c:if test="${!empty subjectQuestion.subjectQuestionOptions }">
						            		<c:forEach items="${subjectQuestion.subjectQuestionOptions }" var="subjectQuestionOption" varStatus="status">
							           			<c:if test="${status.first }">
							           				<input type="hidden" name="optionId" value="${subjectQuestionOption.id }" />
							           			</c:if>
							           			<span style="padding-top:14px;" id="${subjectQuestionOption.id }" <c:if test="${status.first }">class="on"</c:if>>${subjectQuestionOption.optionName }</span>
						            		</c:forEach>
						            	</c:if>
				                    </p>
				                </div>
				    		</c:forEach>
				    	</c:if>
		            </div>
	            </c:if>
	            <c:if test="${subject.types == 3 }">	
					<div class="f_xq_list">
						<c:if test="${!empty subjectQuestions }">
				    		<c:forEach items="${subjectQuestions }" var="subjectQuestion">
				    			<div class="f_xq_ti clearfix">
				                	<p class="f_xq_tword">${subjectQuestion.question }</p>
				                    <p class="f_xq_twordf f_tdf clearfix">
				                    	<input type="hidden" name="optionId" value="${subjectQuestion.id }" />
				                    	<span style="padding-top:0;" id="${subjectQuestion.id }" class="on">是</span>
				                        <span style="padding-top:0;" id="0">否</span>
				                    </p>
				                </div>
				    		</c:forEach>
				    	</c:if>
					</div>
				</c:if>
				<c:if test="${subject.types == 4 }">	
					<div class="f_xq_list">
						<c:if test="${!empty subjectQuestions }">
				    		<c:forEach items="${subjectQuestions }" var="subjectQuestion">
				    			<div class="f_xq_ti clearfix">
				                	<p class="f_xq_tword">${subjectQuestion.question }</p>
				                    <p class="f_xq_twordf f_tdf clearfix">
				                    	<input type="hidden" name="optionId" value="1" />
				                    	<span style="padding-top:0;" id="1" class="on">是</span>
				                        <span style="padding-top:0;" id="0">否</span>
				                    </p>
				                </div>
				    		</c:forEach>
				    	</c:if>
					</div>
				</c:if>
				<c:if test="${subject.types == 5 }">	
					<div class="f_xq_list">
						<div style="padding-top:0; border-bottom:none;" class="f_xq_ti  clearfix">
							<p style="padding-left:0;" class="f_xq_twordf f_wd clearfix">
						    	<c:if test="${!empty subjectQuestions }">
						    		<c:forEach items="${subjectQuestions }" var="subjectQuestion" varStatus="status">
						    			<c:if test="${status.first }">
				           					<input type="hidden" name="optionId" value="${subjectQuestion.id }" />
					           			</c:if>
					           			<span style=" padding-top:0;" id="${subjectQuestion.id }" <c:if test="${status.first }">class="on"</c:if>>${subjectQuestion.question }</span>
						    		</c:forEach>
						    	</c:if>
				    		</p>
            			</div>
                    </div>
				</c:if>
            </form>
            
            <div class="f_anniu"><input type="button" name="" value="提交测试" /></div>
            
        </div>
        <!--***-->
    </div>
    <!--***-->
    
    <!--底部-->
    <%@ include file="../bottom.jsp" %>
    <!--***-->
    <script>
    $(function(){
        $(".f_xq_ti .f_xq_twordf span").click(function(){
            $(this).addClass("on").siblings().removeClass("on");
            $(this).parent().find("input").val($(this).attr("id"));
        })
		$(".f_xq_ti .f_xq_twordf span").hover(function(){
            $(this).addClass("span").siblings().removeClass("span");
        })
        
        $(".f_anniu input").click(function() {
        	confirmS("确定提交吗？", function(){
        		$("#myform").attr("action", "/subject/result.html").submit();
        	})
        });
    })
</script>
</body>
</html>