$(document).ready(function() {
	$("#province").change(function(e){
		$("#city").empty();
		$("#city").next().html("请选择");
		$("#county").empty();
		$("#county").next().html("请选择");
		$.ajax({
			cache:false,
			type:"post",
			data:{provCode:$("#province").val()},
			url:"/common/selectAllCity.html",
			error:function(request){
				alert("connect error");
			},
			success:function(msg){
				var data=eval("("+msg+")");
				if(null!=data){
					$("#city").append("<option value=\"0\">请选择</option>");
					$.each(data,function(i, item){
						$("#city").append("<option value=\""+item.cityCode+"\">"+item.cityName+"</option>")	;
					});
				}
			}
		});
	});

	$("#city").change(function(){
		$("#county").empty();
		$("#county").next().html("请选择");
		$.ajax({
			cache:false,
			type:"post",
			data:{cityCode:$("#city").val()},
			url:"/common/selectAllCounty.html",
			error:function(request){
				alert("connect error");
			},
			success:function(msg){
				var data=eval("("+msg+")");
				if(null!=data){
					$("#county").append("<option value=\"0\">请选择</option>");
					$.each(data,function(i, item){
						$("#county").append("<option value=\""+item.countyCode+"\">"+item.countyName+"</option>")	;
					});
				}
			}
		});
	});

});
