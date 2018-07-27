<%@page import="com.hrsb.cg.model.Subject"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
/*
Long idS = (Long) request.getAttribute("idS");
String titleS = (String) request.getAttribute("titleS");
String imageS = (String) request.getAttribute("imageS");
String url = basePath + "subject/detail.html?id=" + idS + "&desc=" + titleS + 
"&summary=" + titleS + "&title=" + titleS + "&site=jiathis&pics=" + imageS;
*/
//url = URLEncoder.encode(url);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>健康自测-测试结果</title>
<%@ include file="../css.jsp" %>
	<script>
	    $(function(){
	        $(".f_anniu input").click(function(){
	        	var subjectId = '${subjectId}';
	           	location.href = "/subject/detail.html?id=" + subjectId;
	        })
	    })
	</script>
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
                	<%-- <a target="_blank" href="http://connect.qq.com/widget/shareqq/index.html?url=<%=basePath %>subject/detail.html?id=${subject.id }&showcount=0&desc=${subjectResult.name }&summary=${subjectResult.name }&title=${subjectResult.name }&site=jiathis&pics=${subject.image }"><img src="/client/images/f_xq01.png" />QQ</a>
                    <a target="_blank" href="http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url=<%=basePath %>subject/detail.html?id=${subject.id }&title=${subjectResult.name }&pics=${subject.image }&summary="><img src="/client/images/f_xq02.png" />空间</a>
                    
                    <a class="jiathis_button_weixin" href="javascript:;"><img src="/client/images/f_xq03.png" />微信</a>
                    
                    <a target="_blank" href="http://service.weibo.com/share/share.php?title=${subjectResult.name }&url=<%=basePath %>subject/detail.html?id=${subject.id }&source=bookmark&appkey=2992571369&pic=${subject.image }&ralateUid=#_loginLayer_1460703199304"><img src="/client/images/f_xq04.png" />新浪微博</a>
                    <a target="_blank" href="http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?to=pengyou&url=<%=basePath %>subject/detail.html?id=${subject.id }"><img src="/client/images/f_xq05.png" />朋友圈</a>
                    <a target="_blank" href="http://s.jiathis.com/?webid=tqq&url=<%=basePath %>subject/detail.html?id=${subject.id }&title=${subjectResult.name }&uid=1626433&pic=${subject.image }&su=1"><img src="/client/images/f_xq06.png" />腾讯微博</a>
                     --%>
                    <a class="jiathis_button_cqq"><img src="/client/images/f_xq01.png" />QQ</a>
                    <a class="jiathis_button_qzone" ><img src="/client/images/f_xq02.png" />空间</a>
                    <a class="jiathis_button_tsina"><img src="/client/images/f_xq04.png" />新浪微博</a>
                    <a class="jiathis_button_weixin"><img src="/client/images/f_xq05.png"  />微信</a>
                    <a class="jiathis_button_tqq"><img src="/client/images/f_xq06.png" />腾讯微博</a>
                    
                    <!-- JiaThis Button BEGIN -->
						<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
					<!-- JiaThis Button END -->
                </div>
            </div>
            <div class="f_xq_banner">
            	<div class="f_xq_b1"><img src="${subject.image }" /></div>
            </div>
            <div class="f_jieguo">
            	<p>您的测试结果：</p>
                 <div class="f_padd">判定结果：${subjectResult.instruction }</div>
                <div class="f_jieguo_nr">
                	${subjectResult.suggestion }
	            </div>
            </div>
            <div class="f_anniu"><input style="margin-bottom:40px;" type="button" name="" value="再测一次" /> <div class="f_jieguo_img"><img src="/client/images/f_jieguo01.png" /></div></div>
           
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
        })
		$(".f_xq_ti .f_xq_twordf span").hover(function(){
            $(this).addClass("span").siblings().removeClass("span");
        })
    })
</script>
</body>
</html>
