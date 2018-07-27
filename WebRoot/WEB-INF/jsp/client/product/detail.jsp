<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>汉典产品详情</title>
    <%@ include file="../css.jsp" %>
    <script>
    var user='${sessionScope.clientuser}';
	function collect(id) {
	if(user!=null && user!=""){
			$("#collect1" + id).hide();
			$("#collect2" + id).show();
			$.ajax({
				type:"get",
				url:"/collection.html",
				data:{id:id, types:2},
				success:function(msg) {
					if (msg == "ok") {
						alertS("已添加到收藏，请到个人中心-我的收藏查看详情");
					} else {
						$("#collect2" + id).hide();
						$("#collect1" + id).show();
					}
				}
			});
		}else{
			window.location.href="/index.html";
		}
	}
	
	function downloadrecord(id) {
		if(user!=null && user!=""){
				$("#download1" + id).hide();
				$("#download2" + id).show();
				$.ajax({
					type:"get",
					url:"/download.html",
					data:{id:id, types:2},
					success:function(msg) {
						if (msg == "ok") {
							alertS("已添加到下载，请到个人中心-我的下载查看详情");
						} else {
							$("#download2" + id).hide();
							$("#download1" + id).show();
							alertS("下载次数达到上限");
						}
					}
				});
			}else{
				window.location.href="/index.html";
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
<div style=" margin-bottom:20px;border-left:1px solid #e4e4e4;border-bottom:1px solid #e4e4e4;border-right:1px solid #e4e4e4;" class="content clearfix">
    <!--左边-->
    <c:import url="/left.html?types=2"></c:import>
    <!--***-->
    <!--右边-->
    <div style="min-height:1804px;padding-bottom:50px;border-left:1px solid #e4e4e4; height:auto;" class="right clearfix">
        <div class="f_prod">
            <div id="slideBox" class="slideBox">
                <div class="bd">
                    <ul>
                    	<c:if test="${!empty productImages }">
                    		<c:forEach items="${productImages }" var="productImage">
                    			<li>
		                            <a class="pic" href="javascript:;"><img src="${productImage.image }" /></a>
		                        </li>
                    		</c:forEach>
                    	</c:if>
                    </ul>
                </div>
                <div class="hd">
                    <ul></ul>
                </div>
            </div>
            <script type="text/javascript">
                /*TouchSlide({
                    slideCell:"#slideBox",
                    titCell:".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
                    mainCell:".bd ul",
                    effect:"leftLoop",
                    autoPage:true,//自动分页
                    autoPlay:true, //自动播放
                    delayTime:2000,//切换持续时间
                    interTime:4000//切换时间
                });*/
				
				
				
				
					var la_num = $('.bd').find('li').length;
					$('.bd li').each(function(){
							$('.hd ul').append('<li></li>')
					})
					if(la_num>1){
						
						Yeffect.fullAnimatbanner(".bd ul",".bd ul li",".hd li","","","on",5000,800,"current","easeInSine")
					}else{
						//Yeffect.fullAnimatbanner(".bd ul",".bd ul li",".hd li","","","on",5000,80000,"current","easeInSine")
					}
				
				
				

            </script>
			<style>
				.f_prod .slideBox .bd{ height:298px; }
				.bd ul{ width:100%; height:100%; }
				.bd ul li{ position:absolute;  width: 100%; height: 100%; text-align:center; }
				.bd ul li img{ max-width:100%; max-height:100%; }
				.f_prod .slideBox .hd{  position: absolute; width: 100%; left: 0; text-align: right; }
				.f_prod .slideBox .hd ul{ padding-right:36px; }



			</style>
            <div class="f_pdcom" style="position:relative;">
				<div class="f_xq_rt chaoL" style=" right:-23px; top:52px; " >
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
                <ul class="ul1">
                    <li class="list l1 <c:if test="${diff eq 1 }">on</c:if>">产品详情</li>
                    <li class="list l2">常见问题</li>
                    <li class="list l3">大师点评</li>
                    <li class="list l4 <c:if test="${diff eq 2 }">on</c:if>">产品文献</li>
                </ul>
                <ul class="ul2">
                    <li class="list <c:if test="${diff eq 1 }">on</c:if>">
                    	<div style="margin-left:0;" class="f_word">
    						<div class="f_word_one">
                                ${product.content }
            				</div>
        				</div>
                    </li>
                    <li class="list">
                    	<c:if test="${!empty productQuestions }">
                    		<c:forEach items="${productQuestions }" var="productQuestion">
                    			<div class="f_list">
		                            <div class="f_wen"><span>问</span>${productQuestion.question }</div>
		                            <div class="f_da">${productQuestion.answer }</div>
		                        </div>
                    		</c:forEach>
                    	</c:if>
                    </li>
                    <li class="list">
                    	<%--
                    	<div class="f_list_word">
                        	<p>北京汉典制药生产的根痛平颗粒由“中华骨科第一人”杨克勤教授研发。20世纪60年代初，杨克勤教授带领北医三院专家组开展颈、腰椎病诊断与治疗研究，开发出颈椎II号，更新工艺与剂型后，即为根痛平颗粒。</p>
                            <p>根痛平颗粒对颈椎病，尤其是神经根型颈椎病的治疗有显著疗效。其配伍组方可以修复椎间盘，调节骨质代谢平衡，从根本上治疗脊柱退行性病变，颈、腰椎病同治。</p>
                        </div>
                         --%>
                    	<div class="f_zjdp">
                    		<c:if test="${!empty productComments }">
	                    		<c:forEach items="${productComments }" var="productComment">
	                    			<div class="f_zjdp_list">
		                                <div class="f_zjdp_top">
		                                    <div class="f_lt_img"><a href="javascript:;"><img src="${productComment.headImg }" height="63px;" width="61px;" /></a></div>
		                                    <div class="f_rt_word">
		                                        <p class="f_rt_mei">${productComment.userName }<span style=" margin-left:9px;">${productComment.professional }</span><span style=" float:right;"><fmt:formatDate value="${productComment.createTime }" pattern="yyyy-MM-dd"  /></span></p>
		                                        <p class="f_rt_xihe"><span>${productComment.department }</span></p>
		                                    </div>
		                                </div>
		           					    <div class="f_zjdp_bt">${productComment.content }</div>
		           <!-- <div style="text-align:right;" class="f_img clearfix"><img src="../images/f_hd01.png" class="la_tu1" /><img src="../images/f_hd1.png" class="la_tu2"/></div>-->
		       					 	</div>
	                    		</c:forEach>
	                    	</c:if>
   						 </div>
                    </li>
                    <li class="list <c:if test="${diff eq 2 }">on</c:if>">
                    	<div class="f_list_all">
                    		<c:if test="${!empty productLiteratures }">
	                    		<c:forEach items="${productLiteratures }" var="productLiterature">
	                    			<div class="f_lt_one">
			                            <p class="f_p1"><a class="f_active" href="javascript:;">${productLiterature.title }</a></p>
			                            <p class="f_p2"><span style=" display:inline; color:#333333; font-size:16px;">摘要：</span>
											${productLiterature.summary }
										</p>
			                            <div class="f_zuozhe3">
			                                <p>作者：${productLiterature.author }</p>
			                                <p class="f_shijian">期刊号：${productLiterature.issue }
				                                <span>
				                                	<c:if test="${productLiterature.isCollection == 1 }">
							                        	<a href="javascript:;">
							                        		<img src="/client/images/xingxing011.png" style=" position:relative; top:-4px;"/>收藏
								                        </a>
							                        </c:if>
							                        <c:if test="${productLiterature.isCollection == 0 }">
							                        	<a id="collect1${productLiterature.id }" href="javascript:;" onclick="collect('${productLiterature.id }');">
							                        		<img src="/client/images/xingxing01.png" style=" position:relative; top:-4px;" />收藏
								                        </a>
							                        	<a id="collect2${productLiterature.id }" style="display: none;" href="javascript:;">
							                        		<img src="/client/images/xingxing011.png" style=" position:relative; top:-4px;" />收藏
								                        </a>
							                        </c:if>
							                        <c:if test="${productLiterature.isDownload == 1 }">
							                        	<a href="javascript:;">
								                       		<img src="/client/images/f_wxq022.png" style="width:26px; height:26px; position:relative; top:-3px;" />下载
								                      	</a>
							                        </c:if>
							                        <c:if test="${productLiterature.isDownload == 0 }">
							                        	<a id="download1${productLiterature.id }" href="javascript:;" onclick="downloadrecord('${productLiterature.id }');">
								                        	<img src="/client/images/f_wxq02.png" style="width:26px; height:26px; position:relative; top:-3px;" />下载
								                       	</a>
								                       	<a id="download2${productLiterature.id }" href="javascript:;" style="display: none;">
								                       		<img src="/client/images/f_wxq022.png" style="width:26px; height:26px; position:relative; top:-3px;" />下载
								                      	</a>
							                        </c:if>
				                                </span>
			                                </p>
			                    		</div>
			               			 </div>
	                    		</c:forEach>
	                    	</c:if>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!--***-->
</div>
<!--***-->

<!--底部-->
<%@ include file="../bottom.jsp" %>
<!--***-->
<script type="text/javascript">
    $(function(){
        // 点击切换产品详情四个栏目
        $(".f_prod .f_pdcom .ul1 .list").click(function(){
            $(this).addClass("on").siblings().removeClass("on");
            var thise=$(this).index();
            $(this).parent().siblings(".ul2").find(".list").eq(thise).addClass("on").siblings().removeClass("on");
        })
    })
</script>
<style>
.f_prod{ border-right:none;}
</style>
</body>
</html>