// JavaScript Document
$(document).ready(function(e) {
	



///////////正常比例展示图片//////////////////
Gaokuanbia("#g_dtbl img",true) 
Gaokuanbia("#g_xtlb img",true) 


function Gaokuanbia(obj,bder){
	$(obj).each(function(i){
		var img = $(this);
		var realWidth;//真实的宽度
		var realHeight;//真实的高度
		var objwidh=img.width();
		var objhet=img.height();
		$("<img/>").attr("src",img.attr("src")).load(function(){
				realWidth = this.width;
				realHeight =this.height;
				if(objwidh/objwidh>realWidth/realHeight){
					var widthhou=objhet*realWidth/realHeight;
					img.width(widthhou);
					if(bder){
						img.css({paddingLeft:(objwidh-widthhou)/2,paddingRight:(objwidh-widthhou)/2});
					}
				}else{
					var heithou=objwidh*realHeight/realWidth;
					img.height(heithou);
					if(bder){
						img.css({paddingTop:(objhet-heithou)/2,paddingBottom:(objhet-heithou)/2});
					}
				}
			});
	});
		
}
///////////正常比例展示图片//////////////////	
	
	
	
	
/////////////////////点击上下滚动//////////////////////////
QIEhuannfsx(".g_sxgd ul",".g_sxgd ul li",".g_sgbtn",".g_xgbtn",5,11,300)

	
function QIEhuannfsx(obj1,obj2,btnL,btnR,Num,Yuz,time){
	var Objlen=$(obj2).length;
	var objWd=$(obj2).height()+Yuz;
	var intds=0;
	$(obj1).height(Objlen*objWd).css("top",0);
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
		$(obj1).animate({top:-ints*objWd},time)
	}

}	
	
/////////////////////点击上下滚动//////////////////////////	
	
	
$(".g_sxgd ul li").click(function(){
	$(".g_sxgd ul li").removeClass("g_sxqhdqys");
	$(".g_datq img").attr("src",$(this).find("img").attr("src"));
	$(this).addClass("g_sxqhdqys");
})


	
	
	
	
	
	
	
	


//////////////////////列表异步加载///////////////////////
//asyncloadingList("列表容器","列表项目",初始加载条数,每次加载条数,"加载过程中需要显示的内容如load图片",加载时间);
$(".zykj_li").addClass("g_suibian");
asyncloadingList(".g_qzpbl",".zykj_li",6,6,".y_loadDiv",1000);

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
			var divtop=$(container).offset().top;
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
		$(".zykj_li img").load(function(){
			   pubiliu();
			$(this).parents(".zykj_li").removeClass("g_suibian");
		})
	}

}

//////////////////////列表异步加载**********///////////////////////














	
	
	
	
 
fullAnimatbanner(".g_ysgdrq ul",".g_ysgdrq ul li",".g_ysgdsb a","","","g_ysgddqys",5000)
//fullAnimatbanner(".g_yhqban ul",".g_yhqban ul li","",".g_gdkzzuo",".g_gdkzyou","",5000)

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

///////////////////////全屏左右滚动banner****//////////////////////////
	
	
///////////////////////////-------------点击选项卡--------------------/////////////////////////////

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
	
//click_hxk(".g_djhxk",".y_mainPorCa_Cotnt","curhLI")	

click_hxk(".g_qhzlist ul li",".g_qhnrdx","g_syqhdq")


	
//////////////--------左右滚动----------//////////////////

/*方法*/
function animat_banner(obj1,obj,num,btnl,btnr,css,timer){
	var objL=$(obj).length;
	var objW=$(obj).width();
	if(parseInt($(obj).css("margin-left"))>0){
		objW+=parseInt($(obj).css("margin-left"));
	}
	
	if(parseInt($(obj).css("margin-right"))>0){
		objW+=parseInt($(obj).css("margin-right"))
		
	}
	
	
	
	var objHtml=$(obj1).html();
	var dq_ind=objL;
	var settimesdw;
	$(obj1).html(objHtml+objHtml+objHtml)
	$(obj1).width(objL*objW*3)
	$(obj1).css("left",dq_ind*objW*-1)
	$(num).eq(0).addClass(css)
	settimesdw=setInterval(settimesdw_hs,timer)
	function settimesdw_hs(){
			
			if(dq_ind>objL*2-1){
				dq_ind=objL
				$(obj1).css("left",objL*objW*-1)
			}
			dq_ind++;
			tweener(dq_ind)
		
		}
	function tweener(int){
		$(obj1).stop().animate({left:int*objW*-1},300)
		$(num).removeClass(css)
		$(num).eq(int%objL).addClass(css)	
		}
	$(btnl).click(function(){
		clearInterval(settimesdw)
		settimesdw=setInterval(settimesdw_hs,timer)
		if(dq_ind<objL){
			dq_ind=objL*2-1
			$(obj1).css("left",dq_ind*objW*-1)
		}
		dq_ind--;
		tweener(dq_ind)
		
		})
	$(btnr).click(function(){
		clearInterval(settimesdw)
		settimesdw=setInterval(settimesdw_hs,timer)
		if(dq_ind>objL*2-1){
				dq_ind=objL
				$(obj1).css("left",objL*objW*-1)
			}
			dq_ind++;
			tweener(dq_ind)
		})
	$(num).click(function(){
		clearInterval(settimesdw)
		settimesdw=setInterval(settimesdw_hs,timer)
		dq_ind=$(num).index(this)
		tweener(dq_ind)		
		})
}

/*调用*/
animat_banner(".g_dkhgdrq ul",".g_dkhgdrq ul li",".g_dkhgdsb a","","","g_szbfdqys",5000)
	




	
//////////////---------渐隐切换----------//////////////////

/*方法*/
function fade_banner(obj,num,btnl,btnr,css,timer){
	var objl=$(obj).length;
	var dq_ind=0;
	var setshijinasdw;
	for(var i=0;i<objl;i++){	
		$(obj).eq(i).css("z-index",50-i)
	}
	$(num).eq(0).addClass(css)
	function fadesetInrt(int){
		if($(obj).eq(int).css("z-index")!=100){
			for(var i=0;i<objl;i++){	
			$(obj).eq(i).css("z-index",$(obj).eq(i).css("z-index")-1)
			}
			$(obj).eq(int).css("z-index",100)
			$(obj).eq(int).hide();
			$(obj).eq(int).fadeIn(300)
			$(num).removeClass(css)
			$(num).eq(int).addClass(css)
		}
		
	 }

	setshijinasdw=setInterval(setshijinasdw_hs,timer)
	
	function setshijinasdw_hs(){
		dq_ind++;
		if(dq_ind>objl-1){
			dq_ind=0;
		}
		fadesetInrt(dq_ind)
	 }
  
	$(btnl).click(function(){
		clearInterval(setshijinasdw)
		setshijinasdw=setInterval(setshijinasdw_hs,timer)
			dq_ind--;
		if(dq_ind<0){
			dq_ind=objl-1;
		}
		fadesetInrt(dq_ind)
	})
	$(btnr).click(function(){
		clearInterval(setshijinasdw)
		setshijinasdw=setInterval(setshijinasdw_hs,timer)
			dq_ind++;
			if(dq_ind>objl-1){
				dq_ind=0;
			}
			fadesetInrt(dq_ind)
	})

	$(num).hover(function(){
		clearInterval(setshijinasdw)
		setshijinasdw=setInterval(setshijinasdw_hs,timer)
			dq_ind=$(num).index(this)
			fadesetInrt(dq_ind)
	})
	
	
}


/*调用*/
//	
fade_banner(".g_banqhrq ul li",".g_bansbn a","","","g_sbndqys",5000) 	
	
$(".g_djxzan,.g_djxyan").hover(function(){
	$(this).animate({opacity:1},500);		
},function(){
	$(this).animate({opacity:0.3},500);
	})
	
$(".g_szbf").css("margin-left",-($(".g_szbf").width()/2));





/////////////////点击左右滚动////////////////////////
QIEhuannf(".g_djgdq ul",".g_djgdq ul li",".g_zcdjan",".g_ycdjan",4,1,300)
QIEhuannf(".g_tjlist ul",".g_tjlist ul li",".g_zcandjd",".g_ycandjd",4,32,300)
QIEhuannf(".g_cnxhrq ul",".g_cnxhrq ul li",".g_andjzcd",".g_andjycd",5,40,300)
//QIEhuannf(".g_mkqgdrq ul",".g_mkqgdrq ul li",".g_djkzzuo",".g_djkzyou",5,32,300)

$(".w_yinshiqx").each(function(index, element) {
   QIEhuannf($(this).find("ul"),$(this).find("ul li"),$(this).parents(".w_xunhuan").find(".w_zuoceanBtn"),$(this).parents(".w_xunhuan").find(".w_yuoceanBtn"),5,7,300) 
});



$(".g_zcdjan,.g_ycdjan").hover(function(){
	$(this).animate({opacity:1},500);		
},function(){
	$(this).animate({opacity:0.3},500);
	})


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

///////////////////////////-----------------滑动选项卡--------------------/////////////////////////////


/*方法*/

function huadong_hxk(obj1,obj2,css){
	$(obj1).eq(0).addClass(css)
	$(obj2).hide();
	$(obj2).eq(0).show();
	$(obj1).hover(function(){
		$(this).parent().find("."+css).removeClass(css);
		$(this).addClass(css);
		$(obj2).hide();
		
		$(obj2).eq($(obj1).index(this)).show();
		},function(){})
	
}

/*调用*/
huadong_hxk(".g_qhtou a",".g_qhshen","g_hdxdqys")
huadong_hxk(".g_zjqhtou a",".g_zjqhnr","ddfasdasd")
huadong_hxk(".g_tjqrq ul li",".g_tjqlist","g_tjtdqys")


$(".g_ylnr").each(function(index, element) {
    huadong_hxk($(this).find(".g_flqhdjdx a"),$(this).find(".g_flqhshen"),"g_flqhdqys")
});

$(".g_yxspq").each(function(index, element) {
    huadong_hxk($(this).find(".g_yxqhtou a"),$(this).find(".g_yxqhshen"),"g_yxqhdqys")
});

//huadong_hxk(".g_jfqhtou a",".g_jfqhshen","g_jfdqys")

$(".g_lbmknr").each(function(index, element) {
    huadong_hxk($(this).find(".g_lbtlist ul li"),$(this).find(".g_lbqhsnr"),"g_lbtdqys")
});




///////////导航条固定且到相应板块时自动切换当前样式/////////////




msgdmd(".g_xuanfu ul li",".g_ylnr","g_xfdqys",500);
function msgdmd(dhli,bkli,dqys,time){
	
    var shuzu=[];
		
	$(bkli).each(function(index, element) {
        shuzu.push($(this).offset().top-50);
    });
	
	function box(top){
		for(var i=0;i<shuzu.length;i++){
			if(shuzu[i]>top){
				i=shuzu[0]-top>200?100:i;
				return i;
			}
		}
	};
	
	$(window).scroll(function(){
		
		var gdttop=$(window).scrollTop();
		
		var num=box(gdttop);
		if(!num&&gdttop>1428){
			num=shuzu.length;
		};
		if(!num&&gdttop<1428){
			num=0;	
		};
		$(dhli).removeClass(dqys).eq(Math.max(num-1,0)).addClass(dqys);	
	});
	
	$(dhli).click(function(){		
		var aasdasd=$(dhli).index(this);
		$("html,body").animate({scrollTop:$(bkli).eq(aasdasd).offset().top-20},time,"easeInOutCubic");
	});	
}	







$(".g_hddban").click(function(){
	$("html,body").stop().animate({scrollTop:0},300);	
})




var houvebol=true;
var setInteeTim;
function setHnashu(){
	houvebol=true;
	clearInterval(setInteeTim);	
}
$(".g_ejymdh .g_zcsxq").hover(function(){
	if(houvebol){
		$(this).find("ul").slideDown();
		houvebol=false;
		clearInterval(setInteeTim);	
		setInteeTim=setInterval(setHnashu,1000);
	}

},function(){
	$(this).find("ul").slideUp();	
})





$(".g_atjcx select").change(function () {
        var htmlzhi = $(".g_atjcx select option:checked").html();
        $(this).parents(".g_atjcx").find("span").html(htmlzhi);
   })


$(".g_tjxdz").click(function(){
	$(".g_tjtanchu").show();	
})
$(".g_gbaniu").click(function(){
	$(".g_tjtanchu").hide();		
})

$(".g_xgyldz").click(function(){
	$(".g_xgtanchu").show();	
})
$(".g_gbaniuxg").click(function(){
	$(".g_xgtanchu").hide();	
})





$(".g_dpzt").click(function(){
	if(!$(this).hasClass("g_dpdqysa")){
		$(this).parents("li").find(".g_dpxxnr").show();
		$(this).addClass("g_dpdqysa");
	}else{
		$(this).parents("li").find(".g_dpxxnr").hide();
		$(this).removeClass("g_dpdqysa");
		}
		
})





$(".g_zjbq").click(function(){
	$(this).parents("li").find(".g_jbqtc").show();		
})
$(".g_bqtcgb").click(function(){
	$(this).parents(".g_jbqtc").hide();	
})








for (var i = 0; i < $(".g_slkz").length; i++) {
        jiajians($(".g_slkz").eq(i).find(".g_jian"), $(".g_slkz").eq(i).find(".g_jia"), $(".g_slkz").eq(i).find(".g_slsrk"))
}
    function jiajians(btnl, btnr, inpt) {
     
        $(btnl).click(function () {
            if (Number($(inpt).val()) > 0) {
                $(inpt).val(Number($(inpt).val()) - 1)
            }
       })
       $(btnr).click(function () {
           $(inpt).val(Number($(inpt).val()) + 1)
        })
    }


/*for (var i = 0; i < $(".g_gwcslkz").length; i++) {
        jiajians($(".g_gwcslkz").eq(i).find(".g_gwcjian"), $(".g_gwcslkz").eq(i).find(".g_gwcjia"), $(".g_gwcslkz").eq(i).find(".g_slxsk"))
}
    function jiajians(btnl, btnr, inpt) {
     
        $(btnl).click(function () {
            if (Number($(inpt).val()) > 0) {
                $(inpt).val(Number($(inpt).val()) - 1)
            }
       })
       $(btnr).click(function () {
           $(inpt).val(Number($(inpt).val()) + 1)
        })
    }*/


var straa=$(".g_hfan a").html();
$(".g_hfan a").click(function(){
	if(!$(this).hasClass("asdasd")){
		$(this).parents("li").find(".g_wyfbtck").show();
		$(this).addClass("asdasd");
		$(this).html("收起");
	}else{
		$(this).parents("li").find(".g_wyfbtck").hide();
		$(this).removeClass("asdasd");
		$(this).html(straa);
		}
	
		
})



$(".g_fhdb").click(function(){
	$("html,body").stop().animate({scrollTop:0},300);	
})




var slz=parseInt($(".g_gwcxfc ul li").eq(0).find("span").html());
$(".g_gwcgdlan").click(function(){
	$(".g_yctp").css({left:$(this).parents("li").find(".g_dljdtp img").offset().left,top:$(this).parents("li").find(".g_dljdtp img").offset().top})
	$(".g_yctp").attr("src",$(this).parents("li").find(".g_dljdtp img").attr("src"));	
	$(".g_yctp").show();
	$(".g_yctp").stop().animate({left:$(".g_gwcxfc ul li").eq(0).offset().left,top:$(".g_gwcxfc ul li").eq(0).offset().top},700,function(){
			$(".g_yctp").hide();
	});
	slz++;
	$(".g_gwcxfc ul li").eq(0).find("span").html(slz);
})



$(".g_zffsxzq ul li").click(function(){
	$(".g_zffsxzq ul li").removeClass("g_fszdqys");
	$(this).addClass("g_fszdqys");
})



$(".g_zfptdq a,.g_zfptyh a").click(function(){
	$(this).find(".g_zfptdx").trigger("click");
})
$(".g_zfptdq a input,.g_zfptyh a input").click(function(e){
		e.stopPropagation();
})





$(".g_hsth,.g_hrtck").hover(function(){
	$(".g_hrtck").show();	
},function(){
	$(".g_hrtck").hide();
	})







$(".g_xlxkxg select").change(function(){
	$(this).parents(".g_xlxkxg").find("font").html($(this).val());
})


$(".g_dinzd2").hover(function(){
	$(this).find(".g_yhxxyc").show();	
},function(){
	$(this).find(".g_yhxxyc").hide();
	})

$(".g_yhxxyc").hover(function(){
	$(this).show();
},function(){
	$(this).hide();
	})



$(".g_lmrq select").change(function(){
	$(this).parents(".g_lmrq").find("font").html($(this).val())
	
})

///////////////////////////////////////////
	
 /****************************2015-2-6李岩增开始****************************************/
    $("#r_user").click(function () {
        document.getElementById("a_go").innerHTML = "";
        document.getElementById("is_user").innerHTML = "";
    })

    $("#r_ep").click(function () {
        document.getElementById("a_go").innerHTML = "";
        document.getElementById("a_isphone").innerHTML = "";
        document.getElementById("a_ep").innerHTML = "";
    })

    $("#r_ep").click(function () {
        document.getElementById("a_isemail").innerHTML = "";
    })

    $("#r_vc").click(function () {
        document.getElementById("a_go").innerHTML = "";
        document.getElementById("a_is").innerHTML = "";
        document.getElementById("a_vc").innerHTML = "";
    })

    $("#r_pass2").click(function () {
        document.getElementById("a_go").innerHTML = "";
        document.getElementById("a_is").innerHTML = "";
        document.getElementById("a_pwd").innerHTML = "";
    })

    $("#r_pass").click(function () {
        document.getElementById("a_go").innerHTML = "";
        document.getElementById("a_is").innerHTML = "";
        document.getElementById("a_pwd").innerHTML = "";
    })

    /****************************2015-2-6李岩增结束****************************************/	
	
	
	
	
	
	
	
	$(".g_djzkctz").click(function(){
		if(!$(this).hasClass("g_ydhdjdqys")){
			$(this).parents("li").find(".g_cjrdydhnr").hide();
			$(this).addClass("g_ydhdjdqys");
		}else{
			$(this).parents("li").find(".g_cjrdydhnr").show();
			$(this).removeClass("g_ydhdjdqys");
		}
		
	})
	
	
	
	
	
//////自由空间瀑布流////
   pubiliu();
   function pubiliu(){
	var height_lj0=0;
	var height_lj1=0;
	var height_lj2=0;
	var height_lj3=0;
	var height_ljary=[0,0,0,0]
	   for(var i=0;i<$(".zykj_li").length;i++){
		   var sZhi=i%4;
		   var pbBoxa=pbBox();
		   sZhi=height_ljary[pbBoxa];
		   $(".zykj_li").eq(i).css("left",(pbBoxa)*247+0)
		   if(pbBoxa==0){
			   $(".zykj_li").eq(i).css("top",height_ljary[0])
			   height_ljary[0]+=$(".zykj_li").eq(i).height()+12
			  }else if(pbBoxa==1){
				  $(".zykj_li").eq(i).css("top",height_ljary[1])
			   height_ljary[1]+=$(".zykj_li").eq(i).height()+12
			  }else if(pbBoxa==2){
				  $(".zykj_li").eq(i).css("top",height_ljary[2])
			   height_ljary[2]+=$(".zykj_li").eq(i).height()+12 
			  }
			  else if(pbBoxa==3){
				  $(".zykj_li").eq(i).css("top",height_ljary[3])
			   height_ljary[3]+=$(".zykj_li").eq(i).height()+12 
			  }  
	   }
	   var shuszhus=Math.max(height_ljary[0],height_ljary[1]);
	   shuszhus=Math.max(shuszhus,height_ljary[2]);
	   shuszhus=Math.max(shuszhus,height_ljary[3])
	   $(".g_qzpbl").height(shuszhus+20)
	   
	   
	   function pbBox(){
			   var pbzhi=Math.min(height_ljary[0],height_ljary[1]);
			   pbzhi=Math.min(pbzhi,height_ljary[2]);
			   pbzhi=Math.min(pbzhi,height_ljary[3]);
			   if(pbzhi==height_ljary[0]){
				   return 0;
			   }else if(pbzhi==height_ljary[1]){
				   return 1;
				   }else if(pbzhi==height_ljary[2]){
					   	return 2;
					   }else{
						   return 3;
					}
		}
	   
	   
	   
	   
	   if($(".g_qzpbl").height()<height_lj0){
		   $(".g_qzpbl").height(height_lj0)
	   }
	    if($(".g_qzpbl").height()<height_lj1){
		   $(".g_qzpbl").height(height_lj1)
	   }
	    if($(".g_qzpbl").height()<height_lj2){
		   $(".g_qzpbl").height(height_lj2)
	   }
	    if($(".g_qzpbl").height()<height_lj3){
		   $(".g_qzpbl").height(height_lj3)
	   }
	   
	   
  }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
});