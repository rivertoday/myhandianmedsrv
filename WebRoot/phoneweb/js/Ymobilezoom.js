var bodyContentA,bodyContentB,controlObjAry=[],initviewWidth,bodyobjct;
$(function(){
	bodyobjct=$("body");
	var fixedHeaderHtml="",fixedFooterHtml="";
	var fixdeObjHtml=[];
	var headerObj=$(".fixedHeader"),footerObj=$(".fixedFooter");
	var fixdeObj=$(".fixedBox");
	if(headerObj.length>0){fixedHeaderHtml=headerObj.eq(0)[0].outerHTML;headerObj.eq(0).remove();}
	if(footerObj.length>0){fixedFooterHtml=footerObj.eq(0)[0].outerHTML;footerObj.eq(0).remove();}
	fixdeObj.each(function(index,element) {
		var thisobj=$(this);
        fixdeObjHtml.push(thisobj[0].outerHTML);
		thisobj.remove();
    });
	var htmlStringtage=bodyobjct.html();
	bodyobjct.html("<div class='bodyContentA'>"+fixedHeaderHtml+"<div class='bodyContentB'></div>"+fixedFooterHtml+"</div>");
	bodyContentA=$(".bodyContentA");
	bodyContentB=$(".bodyContentB");
	var bodyContentBHT=bodyContentB.height();
	var bodyConBht=bodyContentBHT;
      var fixedHeader=$(".fixedHeader");
	  if(fixedHeader.length>0){ controlObjAry.push(fixedHeader);}
	  var fixedFooter=$(".fixedFooter");
	  if(fixedFooter.length>0){controlObjAry.push(fixedFooter);}
	bodyContentB.append(htmlStringtage);
	for(var i=0;i<fixdeObjHtml.length;i++){
		bodyContentA.append(fixdeObjHtml[i]);
	}
	var fixedBoxOBj=$(".fixedBox");
	fixedBoxOBj.each(function(index, element) {
		var thisObj=$(this);
		if(!thisObj.hasClass("nosize")){
			 controlObjAry.push($(this));
		}
    });
	viewUpdate();
	$(window).resize(function(){viewUpdate();	});
	setInterval(function(){
		bodyConBht=bodyContentB.height();
		if(bodyConBht!=bodyContentBHT){
			bodyContentBHT=bodyConBht;
			viewUpdate();
		}
	},500);
})
function viewUpdate(viewWidth){
	var contentWidth=viewWidth || initviewWidth || 640;
	initviewWidth=contentWidth;
	var bodyContentA_W=bodyContentA.width();
	//console.log(bodyContentA_W)
	var docuemtHeight=bodyContentB.height();
	bodyContentB.width(contentWidth);
	var ZoomScale=bodyContentA_W/contentWidth;
	bodyContentA.height(docuemtHeight*ZoomScale);
	bodyContentB.css({"transform":"scale("+ZoomScale+")","-o-transform":"scale("+ZoomScale+")","-webkit-transform":"scale("+ZoomScale+")"});
	for(var i=0;i<controlObjAry.length;i++){
		controlObjAry[i].css({"transform":"scale("+ZoomScale+")","-o-transform":"scale("+ZoomScale+")","-webkit-transform":"scale("+ZoomScale+")"});
	}
	bodyobjct.css("opacity",1);
}
