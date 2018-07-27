$(document).ready(function() {
	$("#qujian1").blur(function(e){
		var isKilogram = $("#isKilogram").val();
		if(isKilogram === undefined){
			isKilogram = $("#isKilogramProduct").prop('checked') == true? "千克" : "克";
		}
		
		$("#qujian1").empty();
		$("#qujian1tip").empty();
		var tips = "小于" + $("#qujian1").val() + isKilogram;
		$("#qujian1tip").append(tips);
	});
	
	$("#qujian2").blur(function(e){
		var isKilogram = $("#isKilogram").val();
		if(isKilogram === undefined){
			isKilogram = $("#isKilogramProduct").prop('checked') == true? "千克" : "克";
		}
		var qujian2 = parseInt($("#qujian2").val());
		var qujian1 = parseInt($("#qujian1").val());
		$("#qujian2tip").empty();
		$("#qujian3tip").empty();
		if(qujian2 <= qujian1){
			$("#qujian2tip").append("须大于" + qujian1);
			$("#qujian2").val('');
			$("#qujian3").val('');
			return;
		}
		if(isNaN(qujian2)){
			return;
		}
		var tips2 = $("#qujian1").val() + "至" + $("#qujian2").val() + isKilogram;
		var tips3 = "大于" + qujian2 + isKilogram;
		$("#qujian3").attr("value", qujian2);
		$("#qujian2tip").append(tips2);
		$("#qujian3tip").append(tips3);
	});

});
