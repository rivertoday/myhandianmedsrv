<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

		<div class="left clearfix">
        	<div class="f_dlcg">
            	<div class="f_person1">
                	<a class="on" href="javascript:;"><img  width="120px;" height="120px;" style="border-radius:60px;"  src="${userDetail.headImg }" /></a>
                	<div style="display:none;" class="f_dw"><img class="f_img_01" src="/client/images/f_person02.png" /><a href="#"><img src="/client/images/f_person01.png" /></a></div>
                </div>
                <div class="f_person_word1">
                	${userDetail.nickNameStr }<br />
                	<c:if test="${userDetail.status == 1 }">
	                	完善信息获得无限下载权限
                	</c:if>
                	<c:if test="${userDetail.status == 2 }">
	                	审核中
                	</c:if>
                	<c:if test="${userDetail.status == 3 }">
	                	无限下载权限
                	</c:if>
                </div>
               	<ul class="f_xxl">
                	<li <c:if test="${type == 1 }">class="f_on"</c:if>><img src="/client/images/f_person04.png" /><a href="/user/detail.html">基本信息</a></li>
                    <li <c:if test="${type == 2 }">class="f_on"</c:if>><img src="/client/images/f_person05.png" /><a href="/user/downloadrecord.html">我的下载</a></li>
                    <li <c:if test="${type == 3 }">class="f_on"</c:if>><img src="/client/images/f_person06.png" /><a href="/user/collection.html">我的收藏</a></li>
                    <li <c:if test="${type == 4 }">class="f_on"</c:if>><img src="/client/images/f_person07.png" /><a href="/user/comment.html">我的评论</a></li>
                    <li <c:if test="${type == 5 }">class="f_on"</c:if>><img src="/client/images/f_person08.png" /><a href="/user/message.html">我的消息</a></li>
                    <li <c:if test="${type == 6 }">class="f_on"</c:if>><img src="/client/images/f_person09.png" /><a href="/user/settingUI.html">修改密码</a></li>
                    <li><img width="21px" height="20px" src="/client/images/f_person0909.png" /><a href="/logout.html">安全退出</a></li>
                    <li><img width="21px" height="20px" src="/client/images/home.png" /><a href="/index.html">返回首页</a></li>
                </ul>
            </div>
            <div class="xuxian"></div>
            <p class="erweima"  style="text-align:center;">
            	<img src="/client/images/about07.png" alt="" />
            	<span style="font-weight:bold;">汉典医学公众号</span>
            </p>
            <p class="erweima" style="text-align:center;">
            	<img src="/client/images/about_android.png" width="88px" height="90px" alt="" />
            	<span style="font-weight:bold; display: block;font-weight: bold; padding-bottom: 13px;width: 97px;padding-top:0;">扫描汉典医学APP</span>
            </p>
            <div class="xuxian line"></div>
        </div>