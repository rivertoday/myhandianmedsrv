
    /*加载省下拉选*/  
    $(function () {  
        $.ajax({  
            type: "post",  
            url: "${ctx}/shop/area/getProvince",  
            success: function (data) {  
                for (var i = 0; i < data.length; i++) {  
                    $('#province_code').append("<option value='" + data[i].id + "' >" + data[i].aName + "</option>");  
                }  
            },  
            error: function () {  
                alert("加载省失败");  
            }  
        });  
    });  
    /*加载市下拉选*/  
    function getCity() {  
        var id = $("#province_code").val();  
        $("#city_code").empty();  
        $("#area_code").empty();  
        $.ajax({  
            type: "post",  
            url: "${ctx}/shop/area/getCity",  
            data: {"id": id},  
            success: function (data) {  
                $('#city_code').append("<option value='' selected='selected' >" + '请选择' + "</option>");  
                $('#area_code').append("<option value='' selected='selected' >" + '请选择' + "</option>");  
                for (var i = 0; i < data.length; i++) {  
                    $('#city_code').append("<option value='" + data[i].id + "' >" + data[i].aName + "</option>");  
                }  
            },  
            error: function () {  
                alert("加载市失败");  
            }  
        });  
    }  
    ;  
    /*加载地区下拉选*/  
    function getArea() {  
        var id = $("#city_code").val();  
        $("#area_code").empty();  
        $.ajax({  
            type: "post",  
            url: "${ctx}/shop/area/getArea",  
            data: {"id": id},  
            success: function (data) {  
                $('#area_code').append("<option value='' selected='selected' >" + '请选择' + "</option>");  
                for (var i = 0; i < data.length; i++) {  
                    $('#area_code').append("<option value='" + data[i].id + "' >" + data[i].aName + "</option>");  
                }  
            },  
            error: function () {  
                alert("加载区失败");  
            }  
        });  
    }  