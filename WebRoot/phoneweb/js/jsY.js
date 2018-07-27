// JavaScript Document
$(document).ready(function(e) {
    //////////////////点击左右滚动///////////////////////
function QIEhuannf(obj1,obj2,btnL,btnR,Num,Yuz,time,classa){
	var Objlen=$(obj2).length;
	var objWd=$(obj2).width()+Yuz;
	var intds=0;
	$(btnL).removeClass(classa);
	$(btnR).removeClass(classa);
	$(btnL).addClass(classa);
	$(obj1).width(Objlen*objWd).css("left",0);
	$(btnL).click(function(){
	if(intds>0){
		intds--;
		animaTlis(intds);
	}
	})
	$(btnR).click(function(){
		if(intds<Objlen-Num){
			intds++;
			animaTlis(intds);
		}
	})
	function animaTlis(ints){
		$(obj1).animate({left:-ints*objWd},time)
		$(btnL).removeClass(classa);
		$(btnR).removeClass(classa);
		if(ints==0){
			$(btnL).addClass(classa);
		}
		if(ints==Objlen-Num){
			$(btnR).addClass(classa);
		}
	}

}

QIEhuannf(".y_chanpinZtbkL_bC ul",".y_chanpinZtbkL_bC ul li",".y_chanpinZtbkL_b .L",".y_chanpinZtbkL_b .R",5,4,300,"cloasdpocs");
QIEhuannf(".y_tanchuang_GWGdC_ul",".y_tanchuang_GWGdC_ul .y_mainPorCZh_li",".y_tanchuang_GWGd .L",".y_tanchuang_GWGd .R",3,14,300,"cloasdpocs");

//////////////////点击左右滚动***///////////////////////
//////产品数量修改//////
$(".y_NumberCotrl font").click(function(){
	var inpuzhi=parseInt($(this).parent().find("input").val());
	if(inpuzhi>1){
		inpuzhi--;
		$(this).parent().find("input").val(inpuzhi);
	}
	
})
$(".y_NumberCotrl span").click(function(){
	var inpuzhi=parseInt($(this).parent().find("input").val());
		inpuzhi++;
		$(this).parent().find("input").val(inpuzhi);

})
//////产品数量修改***//////



//////3个参数////////////////

/*方法*/

function click_hxk(obj1,obj2,css){
	$(obj1).eq(0).addClass(css)
	$(obj2).hide();
	$(obj2).eq(0).show();
	$(obj1).click(function(){
		$(this).parent().find("."+css).removeClass(css);
		$(this).addClass(css);
		$(obj2).hide();
		$(obj2).eq($(obj1).index(this)).show();
		})
	
}

/*调用*/
click_hxk(".y_mainPorCa_Plfl .a",".y_PingLnXq","curt");

//////3个参数***////





});




function asyncloadingList(container,Item,defaultNum,loadNum,div,timers){
	if(!jQuery.fn.outerHTML){
		jQuery.fn.outerHTML = function(s) {
	  		return (s) ? this.before(s).remove() : $("<Hill_man>").append(this.eq(0).clone()).html();
	 	}
	 }
	 var htmlArry=[];
	 $(Item).each(function(index, element) {
        htmlArry.push($(this).outerHTML());
   	 });
	 $(container).html("");
	 for(var i=0;i<defaultNum;i++){
		 $(container).append(htmlArry[i]); 
	 }
	 htmlArry.splice(0,defaultNum);
	 var loadBol=true;
	 var setTimeouts;
	 clearInterval(setTimeouts);
	 $(window).scroll(function(){
		 if(loadBol){
			var divtop=$(container)	.offset().top;
			var heightz=$(container).height();
			var windoht=$(window).height();
			var windowSCtp=$(window).scrollTop();
			if(divtop+heightz<=windoht+windowSCtp){
				if(htmlArry.length>0){
					$(div).show();
					loadBol=false;
					clearInterval(setTimeouts);
					setTimeouts=setInterval(setIntersdd,timers);
				}
			}
		 }
		  
	})
	function setIntersdd(){
		$(div).hide();
		for(var f=0;f<loadNum && f<htmlArry.length;f++){
			$(container).append(htmlArry[f]); 
		}
		if(loadNum>htmlArry.length){
			htmlArry.splice(0,htmlArry.length);
		}else{
			htmlArry.splice(0,loadNum);
		}
		loadBol=true;
		clearInterval(setTimeouts);
	}

}





















