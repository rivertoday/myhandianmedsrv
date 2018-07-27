$(document).ready(function() {
	$("#province").change(function(e){
	//	alert($("#prov").val());
		//alert($(this).parents("yselect").find("yval").text())
		$("#city").empty();
		$("#city").next().html("市");
		$("#country").empty();
		$("#country").next().html("县");
		$.ajax({
			cache:false, 
			type:"post",
			data:{province:$("#province").val()},
			url:"/city.html",
			error:function(request){
				alert("connect error");
			},
			success:function(msg){
				var data=eval("("+msg+")");
				//alert(data);
				if(null!=data){
					//$("#city").append("<option value=\"0\">市</option>");
					$.each(data,function(i, item){
						$("#city").append("<option value=\""+item.cityCode+"\">"+item.cityName+"</option>")	;
					});
				}
			}
			
		});
		
	});

	$("#city").change(function(){
	//	alert($("#city").val());
		$("#country").empty();
		$("#country").next().html("县");
		$.ajax({
			cache:false,
			type:"post",
			data:{city:$("#city").val()},
			url:"/county.html",
			error:function(request){
				alert("connect error");
			},
			success:function(msg){
				var data=eval("("+msg+")");
				//alert(data);
				if(null!=data){
					//$("#county").append("<option value=\"0\">县</option>");
					$.each(data,function(i, item){
						$("#country").append("<option value=\""+item.countyCode+"\">"+item.countyName+"</option>")	;
					});
				}
			}
			
		});
		
	});
});
