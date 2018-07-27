$(document).ready(function() {
	$("#departmentOne").change(function(e){
		$("#departmentTwo").empty();
		$("#departmentTwo").next().html("请选择");
		
		$.ajax({
			cache:false,
			type:"post",
			data:{id:$("#departmentOne").val()},
			url:"/department.html",
			error:function(request){
				alert("connect error");
			},
			success:function(msg){
				var data=eval("("+msg+")");
				
				if(null!=data){
					$("#departmentTwo").append("<option value=\"0\">请选择</option>");
					
					$.each(data,function(i, item){
						$("#departmentTwo").append("<option value=\""+item.id+"\">"+item.name+"</option>");
					});
				}
			}
		});
	});
});
