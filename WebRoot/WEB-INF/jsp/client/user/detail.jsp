<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>基本资料</title>
<%@ include file="../css.jsp" %>
<script type="text/javascript" src="/client/js/upload.js"></script>
<script>
	function sub() {
		//var nickName = $("#nickName").val();
		var userName = $("#userName").val();
		var phone = $("#phone").val();
		var hospitalName = $("#hospitalName").val();
		var hospitalGrade = $("#hospitalGrade").val();
		var departmentTwo = $("#departmentTwo").val();
		var cardImg = $("input[name=cardImg]").val();
		var number = $("input[name=number]").val();
		/*
		if (nickName == "" || nickName == undefined) {
			alertS("请输入昵称");
			return;
		}
		*/
		if (userName == "" || userName == undefined) {
			alertS("请输入姓名");
			return;
		}
		
		if (phone == "" || phone == undefined) {
			alertS("请输入手机号");
			return;
		}
		
		if (hospitalName == "" || hospitalName == undefined) {
			alertS("请输入医院");
			return;
		}
		
		if (hospitalGrade == "" || hospitalGrade == undefined || hospitalGrade == 0) {
			alertS("请选择医院等级");
			return;
		}
		
		if (departmentTwo == "" || departmentTwo == undefined || departmentTwo == 0) {
			alertS("请选择科室");
			return;
		}
		if(number!=null && number!="" && number!=undefined){
			if(!/^[0-9a-zA-z]+$/.test(number)){
			alertS("执业医师资格证编号只能输入数字和字母");
			return;
			}
		}
		if (cardImg == "" || cardImg == undefined) {
			alertS("请上传图片");
			return;
		}
		
		confirmS("确定提交吗？", function() {
    		$("#myform").attr("action", "/user/perfection.html").submit();
    	});
	}
	
	$(function(){
		var status='${userDetail.status}';
		if(status!=1){
			$(".right input,.right select,.right yselect").each(function(){
			$(this).attr("disabled","disabled");
			$(this).find('yval').unbind('click');
			})
			
		}
	})
	
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
        <div class="right right1 clearfix" style="padding-bottom:68px;">
        	<div class="f_jbzl">
            	<div class="f_jbzl_top">完善资料即可获取无限下载权</div>
            </div>
            <div class="f_jbzl_bd clearfix">
            	<form id="myform" method="post">
                	<p class="f_jbzl_p"><span>　昵称</span><input style="height:auto; padding:8px 0 8px 17px;" type="text" id="nickName" name="nickName" value="${userDetail.nickName }" /></p>
                    <p class="f_jbzl_p"><span><img src="/client/images/f_xing01.png" />姓名</span><input style="height:auto; padding:8px 0 8px 17px;" type="text" id="userName" name="userName" value="${userDetail.userName }" /></p>
                    <p class="f_jbzl_p"><span><img src="/client/images/f_xing01.png" />手机号</span><input style="height:auto; padding:8px 0 8px 17px;" type="text" id="phone" name="phone" value="${userDetail.phone }" readonly="readonly"/></p>
                   	<p><span><img src="/client/images/f_xing01.png" />性别</span>
                    	<span style="width:44px; float:left;" class="laform"><input style="height:23px; width:23px; margin-bottom:0;"  type="radio" name="sex" value = "男" <c:if test="${empty userDetail.sex || userDetail.sex eq '男' }">checked="checked"</c:if> /><b>男</b></span>
                    	<span style="width:44px; float:left;" class="laform"><input style="height:23px; width:23px; margin-bottom:0;" type="radio" name="sex" value = "女" <c:if test="${userDetail.sex eq '女' }">checked="checked"</c:if> /><b>女</b></span>
                    	<span style="width:52px; float:left;" class="laform"><input style="height:23px; width:23px; margin-bottom:0;" type="radio" name="sex" value = "保密" <c:if test="${userDetail.sex eq '保密' }">checked="checked"</c:if> /><b>保密</b></span>
                    </p>
                    <p class="f_jbzl_p"><span><img src="/client/images/f_xing01.png" />医院</span><input style="height:auto; padding:8px 0 8px 17px;" type="text" id="hospitalName" name="hospitalName" value="${userDetail.hospitalName }" /></p>
                    <p><span><img src="/client/images/f_xing01.png" />医院等级</span>
                    	<select class="yselect xuanxiang1" id="hospitalGrade" name="hospitalGrade">
                        	<option value="0">请选择</option>
                        	<c:if test="${!empty hospitalGrades }">
                        		<c:forEach items="${hospitalGrades }" var="hospitalGrade">
                        			<option <c:if test="${userDetail.hospitalGrade == hospitalGrade.id }">selected="selected"</c:if> value="${hospitalGrade.id }">${hospitalGrade.name }</option>
                        		</c:forEach>
                        	</c:if>
                        </select>
                    </p>
                    <p><span><img src="/client/images/f_xing01.png" />科室</span>
                    	<select class="yselect xuanxiang1" id="departmentOne" name="departmentOne">
                        	<option value="0">请选择</option>
                        	<c:if test="${!empty departments }">
                        		<c:forEach items="${departments }" var="department">
                        			<option <c:if test="${userDetail.departmentOne == department.id }">selected="selected"</c:if> value="${department.id }">${department.name }</option>
                        		</c:forEach>
                        	</c:if>
                        </select>
                        <span style="width:6px; padding-top:3px; color:#cccccc; position: relative; top: -2px;">____</span>
                        <select class="yselect xuanxiang1" id="departmentTwo" name="departmentTwo">
                        	<option value="0">请选择</option>
                        	<c:if test="${!empty departments2 }">
                        		<c:forEach items="${departments2 }" var="department2">
                        			<option <c:if test="${userDetail.departmentTwo == department2.id }">selected="selected"</c:if> value="${department2.id }">${department2.name }</option>
                        		</c:forEach>
                        	</c:if>
                        </select>
                    </p>
                    <p style="line-height:34px; height:113px; position: relative; top:0px;"><span>所在地区</span>
                    	<select style="width:171px;background: #f7f7f7 url(../images/f_wdwx_xiala.png) no-repeat 140px 0;" class="yselect xuanxiang1" id="province" name="province">
                        	<option value="">请选择</option>
                            <c:if test="${!empty areaProvs }">
                        		<c:forEach items="${areaProvs }" var="areaProv">
                        			<option <c:if test="${userDetail.province eq areaProv.provCode }">selected="selected"</c:if> value="${areaProv.provCode }">${areaProv.provName }</option>
                        		</c:forEach>
                        	</c:if>
                        </select>
                        <span style="width:6px; padding-top:3px; color:#cccccc; position: relative; top: -10px; ">____</span>
                        <select class="yselect xuanxiang1" id="city" name="city">
                        	<option value="">请选择</option>
                        	<c:if test="${!empty areaCities }">
                        		<c:forEach items="${areaCities }" var="areaCity">
                        			<option <c:if test="${userDetail.city eq areaCity.cityCode }">selected="selected"</c:if> value="${areaCity.cityCode }">${areaCity.cityName }</option>
                        		</c:forEach>
                        	</c:if>
                        </select>
                       　<!--　　<span style="width:6px; padding-top:3px; color:#cccccc;">____</span>-->
                   　　　<select class="yselect xuanxiang1 zuo" id="country" name="country">
                        	<option value="">请选择</option>
                        	<c:if test="${!empty areaCounties }">
                        		<c:forEach items="${areaCounties }" var="areaCounty">
                        			<option <c:if test="${userDetail.country eq areaCounty.countyCode }">selected="selected"</c:if> value="${areaCounty.countyCode }">${areaCounty.countyName }</option>
                        		</c:forEach>
                        	</c:if>
                        </select>
                    </p>
                    <p class="f_jbzl_p" style="position:relative; top:-4px;"><span>职称</span>
                    	<select class="yselect xuanxiang1" name="professional">
                        	<option value="0">请选择</option>
                        	<c:if test="${!empty grades }">
                        		<c:forEach items="${grades }" var="grade">
                        			<option <c:if test="${userDetail.professional == grade.id }">selected="selected"</c:if> value="${grade.id }">${grade.name }</option>
                        		</c:forEach>
                        	</c:if>
                        </select>
                    </p>
                    <p class="f_jbzl_l"><span>主治特长</span><input type="text" id="forte" name="forte" value="${userDetail.forte }" /></p>
                    <p class="f_jbzl_l"><span>执业医师资格证编号</span><input type="text" id="number" name="number" value="${userDetail.number }" /></p>
	                <p style="font-size:16px; color:#999;"><span><img src="/client/images/f_xing01.png" />身份认证</span>请上传手持医生资格证的照片</p>
	                <style>
	                	.uploadify {
		                	height: 100%;
							width: 100%;
							position: absolute;
							left: 0px;
							top: 0px;
	                	}
	                	.swfupload {
		                	    width: 100%;
							    height: 100%;
							    left: 0px;
							    top: 0px;
							    border-radius:60px;
	                	}
	                </style>
	                <div class="picturelist">
						<ul>
							<li>
								<input type="file" id="file" data-overhide="true"
									data-img="cimg1" class="upload" 
									data-desc="*.jpg;*.png;*.gif" data-size="5MB"
									data-uploader="/upload/cfile.html?JSESSIONID=808C00C59FD895054C5A7DEFECB57A3B" data-auto="true"
									data-buttonClass="uploadbtn" data-buttonText="选择文件"
									data-multi=true data-name="cardImg"/>
								<img id="cimg1" style="position: absolute;left: 0;top: 0;" src="${!empty userDetail.cardImg ? userDetail.cardImg : '/client/images/f_jia.png' }"/>
								<span></span>
							</li>
						</ul>
					</div>
					<script>
						$(document).ready(function(){
							
							$(".uploadify").attr("style","width:100%; height:100%; position:absolute; left:0; top:0;")
						})
					</script>
                </form>
                <div class="f_niu">
                	<c:if test="${userDetail.status == 1 }">
	                	<a href="javascript:;" onclick="sub();">提交</a>
                	</c:if>
                	<c:if test="${userDetail.status == 2 }">
	                	<a href="javascript:;" style="cursor:default;">审核中</a>
                	</c:if>
                	<c:if test="${userDetail.status == 3 }">
	                	<a href="javascript:;" style="cursor:default;">审核通过</a>
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
    <div style="display:none" class="masktan"></div>
    <div style="display:none" class="f_tc">
    	<dl>
        	<dt><img src="/client/images/f_sz01.png" /></dt>
            <dd>您的密码修改成功</dd>
        </dl>
        <div class="f_qd">确定</div>
    </div>
    <script>
	
		$(".f_bd .f_an").click(function(){
			$(".masktan,.f_tc").show();
			})
		$(".f_tc .f_qd").click(function(){
			$(".masktan,.f_tc").hide();
			})	
	
    	$(function(){
			$(".f_person .on").hover(function(){
				$(".f_dw").show();
				})
			
			})
			
			
			
	  $(function(){
			Yeffect.Html5FileReader(".picturelist ul li input",".picturelist ul li img")		
	})
    </script>
</body>
</html>