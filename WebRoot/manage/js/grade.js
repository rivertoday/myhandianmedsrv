$(document).ready(function() {
	$("#oneGrade").change(function(e){
		$("#twoGrade").empty();
		$("#twoGrade").next().html("请选择");
		$("#threeGrade").empty();
		$("#threeGrade").next().html("请选择");
		$.ajax({
			cache:false,
			type:"post",
			data:{gid:$("#oneGrade").val()},
			url:"/productm/selectChildGrade.im",
			error:function(request){
				alert("connect error");
			},
			success:function(msg){
				var data=eval("("+msg+")");
				if(null!=data){
					$("#twoGrade").append("<option value=\"999999\">请选择</option>");
					$("#threeGrade").append("<option value=\"999999\">请选择</option>");
					$.each(data,function(i, item){
						$("#twoGrade").append("<option value=\""+item.id+"\">"+item.name+"</option>")	;
					});
				}
			}
		});
	});

	$("#twoGrade").change(function(){
		$("#threeGrade").empty();
		$("#threeGrade").next().html("请选择");
		$.ajax({
			cache:false,
			type:"post",
			data:{gid:$("#twoGrade").val()},
			url:"/productm/selectChildGrade.im",
			error:function(request){
				alert("connect error");
			},
			success:function(msg){
				var data=eval("("+msg+")");
				if(null!=data){
					$("#threeGrade").append("<option value=\"999999\">请选择</option>");
					$.each(data,function(i, item){
						$("#threeGrade").append("<option value=\""+item.id+"\">"+item.name+"</option>")	;
					});
				}
			}
		});
	});

});
