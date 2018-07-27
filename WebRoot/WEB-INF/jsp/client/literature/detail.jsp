<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>文献详情</title>
<%@ include file="../css.jsp" %>
<script>
	var user='${sessionScope.clientuser}';
	function collect(id) {
	if(user!=null && user!=""){
			$("#collect1").hide();
			$("#collect2").show();
			
			$.ajax({
				type:"get",
				url:"/collection.html",
				data:{id:id, types:1},
				success:function(msg) {
					if (msg == "ok") {
						alertS("已添加到收藏，请到个人中心-我的收藏查看详情");
					}else if(msg == "usernull"){
						alertS("请登陆");
					}else {
						$("#collect2").hide();
						$("#collect1").show();
					}
				}
			});
		}else{
			window.location.href="/index.html";
		}
	}
	
	function downloadrecord(id) {
	if(user!=null && user!=""){
			$("#download1").hide();
			$("#download2").show();
			$.ajax({
				type:"get",
				url:"/download.html",
				data:{id:id, types:1},
				success:function(msg) {
					if (msg == "ok") {
						alertS("已添加到下载，请到个人中心-我的下载查看详情");
					} else {
						$("#download2").hide();
						$("#download1").show();
						alertS("下载次数达到上限");
					}
				}
			});
		}else{
			window.location.href="/index.html";
		}
	}
	
	function pinglun() {
		var content=$("textarea[name=content]").val();
		if(content.trim()!=null && content.trim()!="" && content.trim()!=undefined){
		$("#myform").attr("action", "/comments.html").submit();
		}else{
		alert("请填写评论内容");
		return;
		}
	}
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
        <div class="right clearfix">
        	<div class="f_wxxq" style="position:relative;">
					
				<div class="f_xq_rt chaoL">
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
            	<div class="f_wxxq_data"><b style="color: #666; font-size: 16px; font-weight:normal;">${literature.source }</b><span>${literature.year }</span></div>
                <div class="f_wxxq_word">${literature.title }</div>
                <div class="f_wxxq_load">
                	<c:if test="${literature.isCollection == 1 }">
		        		<a href="javascript:;">
							<img src="/client/images/xingxing011.png" style="padding-bottom:5px;" />收藏
						</a>
					</c:if>
					<c:if test="${literature.isCollection == 0 }">
						<a id="collect1" href="javascript:;" onclick="collect('${literature.id }');">
							<img src="/client/images/xingxing01.png" style="padding-bottom:5px;" />收藏
						</a>
						<a id="collect2" style="display: none;" href="javascript:;">
							<img src="/client/images/xingxing011.png" style="padding-bottom:5px;" />收藏
						</a>
					</c:if>
                	<c:if test="${literature.isDownload == 1 }">
		        		<a href="javascript:;">
			        		<img src="/client/images/f_wxq022.png" style="width:26px; height:26px;padding-bottom:5px;" />下载
		        		</a>
					</c:if>
					<c:if test="${literature.isDownload == 0 }">
						<a id="download1" href="javascript:;" onclick="downloadrecord('${literature.id }');">
							<img src="/client/images/f_wxq02.png" style="width:26px; height:26px;padding-bottom:5px;" />下载
						</a>
						<a id="download2" style="display: none;" href="javascript:;">
							<img src="/client/images/f_wxq022.png"  style="width:26px; height:26px; padding-bottom:5px;" />下载
						</a>
					</c:if>
                </div>
                <div class="f_wxxq_zuzhe">作者：<b style="background: #f7bb37 none repeat scroll 0 0;border-radius: 5px;color: #fff;margin-right: 16px;padding: 5px 20px; font-weight: normal; line-height: 40px;">${literature.creator }</b></div>
                <div class="f_wxxq_dress">作者单位：${literature.organization }</div>
            </div>
            <div class="f_jbxx">
            	<div class="f_jbxx_main">
                	<div style="color:#333; font-size:16px;">基本信息</div>
                    <ul>
                        <li style="height: auto; line-height: 30px;"><span>关键词</span><span style="float: none; margin-left: 16px;">${literature.keywords }</span></li>
                    	<%--
                    	<li><span>英文刊名</span><span>RARE METAL MATERIALS</span></li>
                        <li><span>机标分类号</span><span>TG1 TM9</span></li>
                        <li><span>机标关键词</span><span>锡镍合金</span></li>
                        <li><span>DOI</span><span>10.3321/j.issn:1002-185X.2008.05.018</span></li>
                    	 --%>
                    </ul>
                </div>
                <div class="f_word">
                	<p class="f_wp1">摘要</p>
                    <p class="f_wp2">${literature.abstracts }</p>
                </div>
                <div class="f_pinglun">
                	<div class="f_pinglun1">评论</div>
                	<form id="myform" method="post">
	                    <div class="f_pingfen g_wypyzm g_pfgezx">评分
	                                <input type="hidden" name="levels" value="5" />
	                                <a href="javascript:void(0)"></a>
	                                <a href="javascript:void(0)"></a>
	                                <a href="javascript:void(0)"></a>
	                                <a href="javascript:void(0)"></a>
	                                <a href="javascript:void(0)"></a></div>
		            	<input type="hidden" name="literatureId" value="${literature.id }" />
		            	<input type="hidden" name="articleId" value="${literature.articleId }" />
		            	<input type="hidden" name="typ" value="${literature.types }" />
		            	<input type="hidden" name="types" value="1" />            
	                    <div class="f_weben"><textarea name="content" id="content" cols="100" rows="20"></textarea></div>
                    </form>
                    <div class="f_fbpl"><input type="button" name="" style="cursor: pointer;" onclick="pinglun();" value="发表评论" /></div>
                </div>
                <script>
							$(document).ready(function(e) {
								$(".g_pfgezx input").val(5)
								var pfaPd=false;
								$(".g_pfgezx a").addClass("g_pfhsx");
								
								$(".g_pfgezx").mousemove(function(e){
									if($(e.target).is("a")){
										$(this).find("a").addClass("g_pfhsx");
										var hdShu=$(".g_pfgezx a").index(e.target);
										//alert(hdShu)
										pfaPd=true;
										$(".g_pfgezx a").removeClass("g_pfhsx");
										for(var i=0;i<$(".g_pfgezx a").length;i++){
											if(i<=hdShu){
												$(".g_pfgezx a").eq(i).addClass("g_pfhsx");
											}	
										}
									}
									
								})
								
								
                                $(".g_pfgezx").hover(function(e){
									
								},function(){
									if(pfaPd){
										$(".g_pfgezx a").removeClass("g_pfhsx");
										var xtShu=$(".g_pfgezx input").val();
										for(var i=0;i<$(".g_pfgezx a").length;i++){
										if(i<xtShu){
											$(".g_pfgezx a").eq(i).addClass("g_pfhsx");
										}
										}
									}
									})
								$(".g_pfgezx a").click(function(){
									pfaPd=false;
									$(this).addClass("g_pfhsx");
									var xtShu=$(".g_pfgezx a").index(this);
									$(".g_pfgezx input").val(xtShu+1);
									for(var i=0;i<$(".g_pfgezx a").length;i++){
										if(i<xtShu){
											$(".g_pfgezx a").eq(i).addClass("g_pfhsx");
										}
									}
								})
                            });
						</script>
                <div class="f_zjdp">
                	<c:if test="${!empty page.results }">
                		<c:forEach items="${page.results }" var="rs">
                			<div class="f_zjdp_list">
		        				<div class="f_zjdp_top">
		            				<div class="f_lt_img"><a href="javascript:;"><img src="${rs.userDetail.headImg }" width="61px;" height="60px;" /></a></div>
		                		<div class="f_rt_word">
		                			<p style=" color:#a8a8a8;"  class="f_rt_p1">汉典-${rs.userDetail.userName }<span></span><span><fmt:formatDate value="${rs.createTime }" pattern="yyyy-MM-dd" /></span></p>
		                    		<p class="f_rt_p2 g_wypyzm g_pfgezx">
		                       			<c:forEach begin="1" end="${rs.levels }" step="1">
		                       				<img src="/client/images/f_pl04.png" />
		                       			</c:forEach>
		                    		</p>
		              	 		</div>
		            		</div>
		            		<div style="color:#676768;" class="f_zjdp_btt">${rs.content }</div>
		      			 </div>
                		</c:forEach>
                	</c:if>
              </div>
            </div>
            
            
            
        </div>
        <!--***-->
    </div>
    <!--***-->
    
    <!--底部-->
    <%@ include file="../bottom.jsp" %>
    <!--***-->
</body>
</html>