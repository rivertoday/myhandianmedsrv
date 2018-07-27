// JavaScript Document
$(document).ready(function(e) {
    
	
fullAnimatbanner(".g_yhqban ul",".g_yhqban ul li","",".g_gdkzzuo",".g_gdkzyou","",5000)

function fullAnimatbanner(obj1,obj2,num,btnL,btnR,css,time){
	$(obj1).css("width","100%");
	$(obj2).css({position:"absolute",top:0,left:"100%",width:"100%"});
	var liliength=$(obj2).length;
	var curptins=0;
	var setIntervalsAAA;
	$(obj2).eq(curptins).css({left:0});
	$(num).removeClass(css).eq(curptins).addClass(css);
	
	$(num).click(function(){
			var curindex=$(num).index(this);
			if(curindex!=curptins){
				animatgundxz(curindex,true);
			}

		})
	
	$(btnL).click(function(){
		if(curptins-1<0){
			animatgundxz(liliength-1,false);
		}else{
			animatgundxz(curptins-1,false);
		}
		
	})
	
	$(btnR).click(function(){
		if(curptins+1>liliength-1){
			animatgundxz(0,true);
		}else{
			animatgundxz(curptins+1,true);
		}
		
	})
	
	setIntervalsAAA=setInterval(functionTimegd,time);
	function functionTimegd(){
			if(curptins+1>liliength-1){
				animatgundxz(0,true);
			}else{
				animatgundxz(curptins+1,true);
			}
			
		
		}
	
	function animatgundxz(cuet,drect){
			//alert(cuet)
			clearInterval(setIntervalsAAA);
			setIntervalsAAA=setInterval(functionTimegd,time);
			$(obj2).stop().css({left:"100%"});
		if(drect){
			$(obj2).eq(cuet).stop().css({left:"100%"});
			$(obj2).eq(cuet).animate({left:0},500)
			$(obj2).eq(curptins).stop().css({left:"0%"})
			$(obj2).eq(curptins).animate({left:"-100%"},500);
		}else{
			$(obj2).eq(cuet).css({left:"-100%"}).animate({left:0},500);
			$(obj2).eq(curptins).css({left:"0%"}).animate({left:"100%"},500);
		}
		curptins=cuet;
		
		$(num).removeClass(css).eq(curptins).addClass(css);
	
	}
	
	$(window).resize(function(){
			$(obj2).stop().css({left:"100%"});
			$(obj2).eq(curptins).stop().css({left:0});
		})
	
	
	
}

///////////////////////全屏左右滚动banner****/////////////////////////
	
	
	
	
/////////////////点击左右滚动///////////////////////


QIEhuannf(".g_mkqgdrq ul",".g_mkqgdrq ul li",".g_djkzzuo",".g_djkzyou",5,32,300)	
	
function QIEhuannf(obj1,obj2,btnL,btnR,Num,Yuz,time){
	var Objlen=$(obj2).length;
	var objWd=$(obj2).width()+Yuz;
	var intds=0;
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
	}

}
	
	
	
	
});