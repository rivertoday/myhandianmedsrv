// JavaScript Document
$(document).ready(function(e) {
    $(".yywrapMright").height($(".yywrapMleft").height()+50);
	$(window).scroll(function(){
			
		setToppisfun();
		
		});
	setToppisfun();
	function setToppisfun(){
		var sctop=$(window).scrollTop();
		var ofstTOP=$(".yywrapMright").offset().top;
		var windWd=$(window).height();
		var bottom=$(".yybottom").offset().top;
		$(".yywrapright01").removeClass("yywrapright01Top").removeClass("yywrapright01Bottom").removeAttr("style");	
		if(ofstTOP>sctop+windWd-$(".yywrapright01").height()){
			$(".yywrapright01").addClass("yywrapright01Top").css("top",ofstTOP);
		}
		if(bottom<sctop+windWd){
			$(".yywrapright01").addClass("yywrapright01Bottom").css("top",$(".yywrapMright").height()-$(".yywrapright01").height()+ofstTOP);
		}
		
		
	}
	
	
	////////////倒计时////////////////
	var timrArry=[];
	$(".g_time").each(function(index, element) {
        var timer=parseInt($(this).attr("time"));
		timrArry.push(timer);
		var tim=retTimes(timer);
		$(this).html("剩余"+tim[0]+"天"+tim[1]+"小时 "+tim[2]+"分 "+tim[3]+"秒");
    });
	
	setInterval(function(){
		$(".g_time").each(function(index, element) {
			if(timrArry[index]>0){
				timrArry[index]--;
			}
			var tim=retTimes(timrArry[index]);
			$(this).html("剩余"+tim[0]+"天"+tim[1]+"小时 "+tim[2]+"分 "+tim[3]+"秒");
        });
	},1000)
	
	
	function retTimes(ints){
		var T=Math.floor(ints/3600/24);
		var tmiea=ints-T*3600*24;
		var H=Math.floor(tmiea/3600);
		var tmieb=tmiea-H*3600;
		var M=Math.floor(tmieb/60);
		var tmiec=tmieb-M*60;
		var S=tmiec;
		return [T,H,M,S]
	}
	
	
	
	////////////倒计时****////////////////
	
	
	
});