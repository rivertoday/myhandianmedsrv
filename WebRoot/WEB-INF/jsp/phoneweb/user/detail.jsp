<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
	<meta http-equiv="Content-Type" content="text/html"/>
	<meta name="apple-mobile-web-app-capable" content="yes"/>
	<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
	<meta name="format-detection" content="telephone=no"/>
	<meta http-equiv="Expires" content="-1"/>
	<meta http-equiv="Cache-Control" content="no-cache"/>
	<meta http-equiv="Pragma" content="no-cache"/>
    <title>基本信息</title>
    <link rel="stylesheet" type="text/css" href="/phoneweb/css/f_base.css"/>
    <link rel="stylesheet" type="text/css" href="/phoneweb/css/Ymobilezoom.css"/>
	<script type="text/javascript" src="/phoneweb/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="/phoneweb/js/Ymobilezoom.js"></script>
    <script type="text/javascript" src="/phoneweb/js/upload_head.js"></script>
    <%@ include file="../css.jsp" %>
    <script>
		// 根据高度隐藏底部导航
		$(function(){
			$(window).resize(LAfn);
		})
		function LAfn(){
			var windowH = $(window).height();
			if(windowH < 500){
				
				$('.fixedFooter').hide();
			}else{
				$('.fixedFooter').show();
			}
		}
	</script>

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
	    		$("#myform").attr("action", "/phoneweb/user/perfection.html").submit();
	    	});
		}
		
		$(function(){
		var status='${userDetail.status}';
		if(status!=1){
			$(".box input,.box select").each(function(){
			$(this).attr("disabled","disabled");
			
			})
			
				$(".f_jbxx .box .list3 .sl").unbind('click');
			
			
		}
	})
	
	</script>
    <script>
    $(function(){
    var status='${userDetail.status}';
		if(status==1){
        // 选择性别
        $(".f_jbxx .box .list3 .sl").click(function(){
            $(this).addClass("on").siblings().removeClass("on");
            $("#sex").val($(this).text());
            //alert($("#sex").val());
        })
        }
        var n = true;
        $(".f_img").click(function(){

            if(n== true){
                $(this).parents('.f_zjdp_list').addClass('tableLa');
                n = false;
            }else{
                $(this).parents('.f_zjdp_list').removeClass('tableLa');
                n = true;
            }

            //$('this').siblings('.f_zjdp_top').css('height','auto')
            //alert(123)
            //$(this).addClass('k')

        })

		Upload_file_add(".picturelist li","img","span",function(){
				var srcZhi=$(".picturelist img").attr("src");
				$(".picturelist img").attr("src","");
				$(".picturelist img").attr("src",srcZhi);
				$("#cardImg").val("");
				$("#cardImg").val(srcZhi);
			},105,"",1,"jpeg");
    })
	/*$(function(){
		Yeffect.Html5FileReader(".picturelist input",".picturelist img");
		$(".picturelist input").change(function(){
			//var srcZhi;
			setTimeout(function(){
			
				var srcZhi=$(".picturelist img").attr("src");
				$("#cardImg").val("");
				$("#cardImg").val(srcZhi);
				//alert($("#cardImg").val());
			
			},300);
		
		
	})
				
				
		})*/
</script>
</head>
<body>
<header>
<div class="top2 fixedHeader">
    <div class="f_head_lf"><a href="/phoneweb/user/usercenter.html"><img src="/phoneweb/images/f_head01.png" /></a></div>
    <div class="f_head_ct">基本信息</div>
    </div>
</header>
<div class="f_jbxx">
    <div class="clearfix top">
        <div class="pic"><img src="${userDetail.headImg }" /><a href="javascript:void(0)" class="zhe" style="display:none;"></a></div>
        <div class="tbox">
            <div class="clearfix text1"><span class="name">${userDetail.nickName }</span></div>
            <div class="text2">
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
        </div>
    </div>
    <div class="bt">完善一下资料获取无限下载权</div>
    <form id="myform" method="post">
   		<input type="hidden" id="cardImg" name="cardImg" value="" />
    	<input type="hidden" id="sex" name="sex" value="男" />
	    <ul class="box">
	        <li class="clearfix list list1">
	            <span class="text">姓名</span>
	            <input type="text" id="userName" name="userName" value="${userDetail.userName }" class="inp" />
	        </li>
	        <li class="clearfix list list2">
	            <span class="text">手机号</span>
	            <input type="text" id="phone" name="phone" value="${userDetail.phone }" readonly="readonly" class="inp"  />
	        </li>
	        <li class="clearfix list list3">
	            <span class="text">性别</span>
	            <span class="sl<c:if test="${empty userDetail.sex || userDetail.sex eq '男' }"> on</c:if>">男</span>
	            <span class="sl<c:if test="${userDetail.sex eq '女' }"> on</c:if>">女</span>
	            <span class="sl<c:if test="${userDetail.sex eq '保密' }"> on</c:if>">保密</span>
	        </li>
	        <li class="clearfix list list4">
	            <span class="text">医院</span>
	            <input type="text" id="hospitalName" name="hospitalName" value="${userDetail.hospitalName }" class="inp" />
	        </li>
	        <li class="clearfix list list5">
	            <span class="text">医院等级</span>
	            <select class="inp" id="hospitalGrade" name="hospitalGrade">
	                <option value="0">请选择</option>
                   	<c:if test="${!empty hospitalGrades }">
                   		<c:forEach items="${hospitalGrades }" var="hospitalGrade">
                   			<option <c:if test="${userDetail.hospitalGrade == hospitalGrade.id }">selected="selected"</c:if> value="${hospitalGrade.id }">${hospitalGrade.name }</option>
                   		</c:forEach>
                   	</c:if>
	            </select>
	        </li>
	        <li class="clearfix list list6">
	            <span class="text">科室</span>
	            <select class="inp inp1" id="departmentOne" name="departmentOne">
	                <option value="0">请选择</option>
                   	<c:if test="${!empty departments }">
                   		<c:forEach items="${departments }" var="department">
                   			<option <c:if test="${userDetail.departmentOne == department.id }">selected="selected"</c:if> value="${department.id }">${department.name }</option>
                   		</c:forEach>
                   	</c:if>
	            </select><span class="hg">-</span>
	            <select class="inp inp2" id="departmentTwo" name="departmentTwo">
	                <option value="0">请选择</option>
                   	<c:if test="${!empty departments2 }">
                   		<c:forEach items="${departments2 }" var="department2">
                   			<option <c:if test="${userDetail.departmentTwo == department2.id }">selected="selected"</c:if> value="${department2.id }">${department2.name }</option>
                   		</c:forEach>
                   	</c:if>
	            </select>
	        </li>
	        <li style="height:202px;" class="clearfix list list7">
	            <span class="text">所在地区</span>
	            <select style="margin-bottom:10px;"  class="inp inp1" id="province" name="province">
	                <option value="">请选择</option>
                    <c:if test="${!empty areaProvs }">
                		<c:forEach items="${areaProvs }" var="areaProv">
                			<option <c:if test="${userDetail.province eq areaProv.provCode }">selected="selected"</c:if> value="${areaProv.provCode }">${areaProv.provName }</option>
                		</c:forEach>
                	</c:if>
	            </select>
	            <select style="margin-bottom:10px; margin-left:138px;" class="inp inp2" id="city" name="city">
	                <option value="">请选择</option>
                   	<c:if test="${!empty areaCities }">
                   		<c:forEach items="${areaCities }" var="areaCity">
                   			<option <c:if test="${userDetail.city eq areaCity.cityCode }">selected="selected"</c:if> value="${areaCity.cityCode }">${areaCity.cityName }</option>
                   		</c:forEach>
                   	</c:if>
	            </select>
	            <select style=" margin-left:138px;" class="inp inp3" id="country" name="country">
	                <option value="">请选择</option>
                   	<c:if test="${!empty areaCounties }">
                   		<c:forEach items="${areaCounties }" var="areaCounty">
                   			<option <c:if test="${userDetail.country eq areaCounty.countyCode }">selected="selected"</c:if> value="${areaCounty.countyCode }">${areaCounty.countyName }</option>
                   		</c:forEach>
                   	</c:if>
	            </select>
	        </li>
	        <li class="clearfix list list5">
	            <span class="text">职称</span>
	            <select  class="inp" name="professional">
	                <option value="0">请选择</option>
                   	<c:if test="${!empty grades }">
                   		<c:forEach items="${grades }" var="grade">
                   			<option <c:if test="${userDetail.professional == grade.id }">selected="selected"</c:if> value="${grade.id }">${grade.name }</option>
                   		</c:forEach>
                   	</c:if>
	            </select>
	        </li>
	        <li class="clearfix list list9">
	            <span class="text">主治特长</span>
	            <input type="text" id="forte" name="forte" value="${userDetail.forte }" class="inp laone" />
	        </li>
	        <li class="clearfix list list10">
	            <div class="text">执业医师资格证编号</div>
	            <input type="text" id="number"  name="number" value="${userDetail.number }" class="inp laone" />
	        </li>
	        <li class="clearfix list list11">
	            <div class="text">身份认证</div>
	            <div class="ti">请上传手持执业医师资格证的照片</div>
<!-- 	            <a href="javascript:void(0)" class="intu"></a> -->
	            <!-- <input type="file" id="file" data-overhide="true"
					data-img="cimg1" class="upload" 
					data-desc="*.jpg;*.png;*.gif" data-size="5MB"
					data-uploader="/upload/cfile.html?JSESSIONID=808C00C59FD895054C5A7DEFECB57A3B" data-auto="true"
					data-buttonClass="uploadbtn" data-buttonText="选择文件"
					data-multi=true data-name="cardImg"/> -->
					<div style="position:relative;" class="picturelist">
					<ul>
						<li><img src="${!empty userDetail.cardImg?userDetail.cardImg:'/phoneweb/images/f_jb_tb03.gif' }"/>
						 <c:if test="${userDetail.status eq 1 }">
							<input style="position:absolute; left:56px;top:34px; width:115px; height:113px; opacity:0; " type="file"/>
						</c:if> 
						<span>x</span>
						</li>
					</ul>
				</div>
	        </li>
	        <li class="list list12">
	        	<c:if test="${userDetail.status == 1 }">
                	<a href="javascript:void(0)" onclick="sub();" class="tj">提交</a>
               	</c:if>
               	<c:if test="${userDetail.status == 2 }">
                	<a href="javascript:void(0)" class="tj">审核中</a>
               	</c:if>
               	<c:if test="${userDetail.status == 3 }">
                	<a href="javascript:void(0)" class="tj">审核通过</a>
               	</c:if>
	        </li>
	    </ul>
	 </form>
</div>
<script>
$(function(){
	
	$('.laone').each(function(){
		$(this).focus(function(){

			var Tonum = $(this).offset().top - 200;
			//alert(Tonum)
			 $('body,html').animate({ scrollTop:Tonum },600);
		})
	})
	
})
</script>
<style>
.picturelist ul li.show span{display: block;background-color: rgba(0, 0, 0, 0.5);
    color: #fff;
    font-size: 23px;
    line-height: 20px; top:0px; right:0px;}

</style>
<%@ include file="../bottom.jsp" %>

</body>
</html>