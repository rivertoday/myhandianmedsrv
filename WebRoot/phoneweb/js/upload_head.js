function Upload_file_add(obj,img,X,fn,maxC,alerta,maxLeng,geshi){
			var divobj=$("<div style='width:0px; height:0px;position:relative; overflow:hidden;'>");
			var OBjctent=$(obj).eq(0);
			var bodyObj=$("body");
			var InputObj=OBjctent.find("input");
			var maxC=maxC || 5000;
			var fn=fn || function(){};
			var IMglength=0;
			var imgcur=0;
			var alerta=alerta || "你选择的不是图片！";
			var maxLeng=maxLeng || 100;
			var curLeng=[];
			divobj.append(InputObj);
			var thisHtml=OBjctent[0].outerHTML;
			OBjctent.append(InputObj);
			bodyObj.append(divobj);

			InputObj.change(function(e){
				var thisfil=$(this)[0].files;
				/*如果是图片*/
					var inyy=true;
//					IMglength=0;
//					imgcur=0;
					for(var i=0;i<thisfil.length;i++){
						if(thisfil[i].type.indexOf("image")>-1){
							var Imgreader=new FileReader();
							IMglength++;
							Imgreader.readAsDataURL(thisfil[i]);
							Imgreader.onload=function(e){
								var datasrl=e.target.result;
									Imgreader=null;
									var tupian=$("<img style='max-width:"+maxC+"px;max-width:"+maxC+"px;' src='"+datasrl+"'>");
									//newImg.find(img).attr("src",datasrl);
									//$(".f_mem .top .pic img").attr("src",datasrl)
									//alert(datasrl)
									tupian.load(function(){
										var thisoBj=$(this);
										//setTimeout(function(){
											divobj.append(thisoBj);
											dowCanvas(thisoBj)
										//},300)

										
									})
							}
							inyy=false;
						}
					}
					if(inyy){
						alert(alerta);
					};
			});
			function dowCanvas(objimg){
				var imgW=objimg.width();
				var imgH=objimg.height();
				var canvas=$("<canvas>");
				canvas[0].width=imgW;
				canvas[0].height=imgH;
				divobj.append(canvas);
				var canvastxt=canvas[0].getContext('2d');
				canvastxt.drawImage(objimg[0],0,0,imgW,imgH);
				setTimeout(function(){
					if(curLeng.length>=maxLeng){
						return;
					}
					var imgdaul;
					if(geshi){
						imgdaul=canvas[0].toDataURL("image/"+geshi);
					}else{
						imgdaul=canvas[0].toDataURL();
					}

					var newImg=$(thisHtml);
					newImg.addClass("show");
					newImg.find(img).attr("src",imgdaul);
					newImg.find(X).click(function(){
						var thispart=$(this).parent();
						thispart.addClass("remove");
						thispart.remove();
						curLeng=removeAry(curLeng);
						//alert(curLeng.length)
						OBjctent.show();
						fn();
					});

					OBjctent.before(newImg);
					curLeng.push(newImg);
					canvas.remove();
					canvas=null;
					canvastxt=null;
					objimg.unbind("load");
					objimg.remove();
					imgcur++;
					if(curLeng.length>=maxLeng){
						OBjctent.hide();
					}else{
						OBjctent.show();
					}
					if(imgcur>=IMglength){
						fn();
					}
				},50);
			}
			function removeAry(ayr){
				var newAy=[];
				for(var i=0;i<ayr.length;i++){
					if(!ayr[i].hasClass("remove")){
						newAy.push(ayr[i]);
					}
				};
				return newAy;
			}

		}
