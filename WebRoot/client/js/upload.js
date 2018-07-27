$(function(){
	var $upload = $(".upload");
	$upload.each(function(){
		var obj = this;
		var parent = $(obj).parent();
		var img = $("#"+$(obj).attr("data-img"));
		var overhide = $(obj).attr("data-overhide");
		$(this).uploadify({
			'uploader':$(obj).attr("data-uploader"),
			'auto':$(obj).attr("data-auto"),
			'buttonClass':$(obj).attr("data-buttonClass"),
			'buttonCursor':'hand',
			'buttonText':$(obj).attr("data-buttonText"),
			'checkExisting':false,
			'debug':false,
			'fileObjName':'file',
			'fileSizeLimit':$(obj).attr("data-size"),//设置大小
			'fileTypeDesc':"请选择"+$(obj).attr("data-desc")+"文件",
			'fileTypeExts':$(obj).attr("desc"),
			'method':'POST',
			'multi':$(obj).attr("data-multi")=="false"?false:true,
			'queueID':false,
			'queueSizeLimit':'999',
			'removeCompleted':true,
			'removeTimeout':1,
			'requeueErrors':true,
			'successTimeout':30,
			'swf':'/client/uploadify.swf',
			'uploadLimit':999,
			'onUploadSuccess':function(file, data, response){
				setTimeout(function(){
					if(img){
						img.attr("src","/client/images/c_70.png");
					}
					if($("input[name='"+$(obj).attr("data-name")+"']").length>0){
						$("input[name='"+$(obj).attr("data-name")+"']").val(data);
						$("#cimg1").attr("src", data);
						//$(parent).parent().find("img").attr("src", data);
					}else{
						$(parent).append("<input type='hidden' name='"+$(obj).attr("data-name")+"' value='"+data+"'/>");
						$("#cimg1").attr("src", data);
						//$(parent).parent().append("<img class='la_tus' style='margin-left: 8px;' width='100px;' height='60px;' src='" + data + "'>");
					}
					
					if(overhide=="true"){
//						$(parent).hide();
						$(parent).attr("src","/client/images/c_69.png")
					}
		            mousOuss=true;
		            $(".c_bkyd").attr("src",data)
		            if($(".AoverImgd img").attr("src")){
		            	$(".AoverImgd img").attr("src",data);
		            	$(".BoverImgd img").attr("src",data);
		            }
				}, 1000);
			},
			'onFallback':function(){
				alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
			},
			'onUploadStart':function(){
				$(parent).show();
			}
		});
	});
});