// JavaScript Document
$(document).ready(function(e) {
	var h = $('.content').height();
	
	$('.content .right').height(h+70);
	// 首页banner滑动
	//fullAnimatbanner("列表项容器","列表项","控制小点","左按钮","右按钮","小点上的当前样式",自动切换的间隔时间,缓动时间选填默认500,缓动函数名选填);
	Yeffect.fullAnimatbanner(".y_index_banner ul",".y_index_banner ul li",".y_bankz a","","","y_bankzys",5000,300,"easeInQuart");//首页banner
	//一级导航隐藏层弹出js
	var ishover=false;
	var thisliobj;
	$('.y_index_nav_box ul li').hover(function(){
		ishover=true;
		if($(this).find("a").length>1){
				$('.y_navYC').show()
				var h = $(this).find('.y_show_nav').outerHeight();
				thisliobj=$(this);
				thisliobj.addClass("hover");
				$('.y_navYC').height(h)
			}
	},function(){
		ishover=false;
		ishoverHiswe();
	})
	$('.y_navYC').hover(function(){
		ishover=true;
	},function(){
		ishover=false;
		ishoverHiswe();
	})

	function ishoverHiswe(){
	setTimeout(function(){
		if(!ishover){
			$('.y_navYC').hide();
			thisliobj.removeClass("hover");
		}
	},10)

	}
	//二级导航选项卡
	Yeffect.hover_hxkWcf(".y_show_nav_top a",".y_show_nav_cen","y_show_nav_topmr"); 

	//首页板块A——华岳优势
	Yeffect.click_hxkWcf(".y_indexA_LC a",".y_indexA_LB","y_indexA_LCmr"); 







});



//////全局方法////////
function alertS(sty,fun){
	var sty=sty || "";
	var fun=fun || function(){};
	alertTAN(sty,fun,"alertshowD");
}

function confirmS(sty,fun){
	var sty=sty || "";
	var fun=fun || function(){};
	alertTAN(sty,fun);
}

function alertTAN(sty,fun,nam){
	if(nam){
		$("body").append("<div class='alertshowMask'><div class='labj'><div><div class='alertshow alertshowD'><p>"+sty+"</p><i style='width:100%'>确定</i></div></div>");
	}else{
		$("body").append("<div class='alertshowMask'><div class='labj'><div><div class='alertshow'><p>"+sty+"</p><i>确定</i><i class='mx'>取消</i></div></div>");
	}
	
	var alertshowMask=$(".alertshowMask");
	var alertshows=alertshowMask.find("i");
	//var alertshowsL=alertshows.length;
	alertshows.click(function(){
		if(alertshows.index(this)==0){
			fun();
		}
		alertshowMask.remove();
	})
}




//发送验证码到手机
function getCode(phone) {
	$.ajax({
    	type:"get",
    	url:"/code.html",
    	data:{phone:phone},
    	async:false,
    	timeout:"10000",
    	success:function(msg) {
    		
    	}
	});
}