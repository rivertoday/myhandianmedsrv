function pic_upload_success(file, data) {
    var json = $.parseJSON(data);
    
    $(this).bjuiajax('ajaxDone', json);
    if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
        $('#headimg').val(json.navTabId).trigger('validate');
        $('#headimg_span').html('<img src="'+ json.navTabId +'" width="100" />');
    }
}
function dimg1_upload_success(file, data) {
    var json = $.parseJSON(data);
    
    $(this).bjuiajax('ajaxDone', json);
    if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
        $('#dimg1').val(json.navTabId).trigger('validate');
        $('#dimg_span1').append('<img src="'+ json.navTabId +'" width="100" />');
        $('#dimg_span1').append('<input type="hidden" name="image" value='+ json.navTabId +'/>');
        $('#dimg_span1').append('<input type="hidden" name="id" value="0"/>');
    }
}
function dimg2_upload_success(file, data) {
    var json = $.parseJSON(data);
    
    $(this).bjuiajax('ajaxDone', json);
    if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
        $('#dimg2').val(json.navTabId).trigger('validate');
        $('#dimg_span2').html('<img src="'+ json.navTabId +'" width="100" />');
    }
}


//将表单序列化成json格式的数据(但不适用于含有控件的表单，例如复选框、多选的select)
 (function($){
    $.fn.serializeJson = function(){
        var jsonData1 = {};
        var serializeArray = this.serializeArray();
        // 先转换成{"id": ["12","14"], "name": ["aaa","bbb"], "pwd":["pwd1","pwd2"]}这种形式
        $(serializeArray).each(function () {
            if (jsonData1[this.name]) {
                if ($.isArray(jsonData1[this.name])) {
                    jsonData1[this.name].push(this.value);
                } else {
                    jsonData1[this.name] = [jsonData1[this.name], this.value];
                }
            } else {
                jsonData1[this.name] = this.value;
            }
        });
        // 再转成[{"id": "12", "name": "aaa", "pwd":"pwd1"},{"id": "14", "name": "bb", "pwd":"pwd2"}]的形式
        var vCount = 0;
        // 计算json内部的数组最大长度
        for(var item in jsonData1){
            var tmp = $.isArray(jsonData1[item]) ? jsonData1[item].length : 1;
            vCount = (tmp > vCount) ? tmp : vCount;
        }

        if(vCount > 1) {
            var jsonData2 = new Array();
            for(var i = 0; i < vCount; i++){
                var jsonObj = {};
                for(var item in jsonData1) {
                    jsonObj[item] = jsonData1[item][i];
                }
                jsonData2.push(jsonObj);
            }
            return JSON.stringify(jsonData2);
        }else{
            return "[" + JSON.stringify(jsonData1) + "]";
        }
    };
})(jQuery);

function submitUserList_4() {
    var jsonStr = $("#productForm").serializeJson();
    console.log("jsonStr:\r\n" + jsonStr);
    //alert(jsonStr);
    $.ajax({
        url: "/productm/edit2.im",
        type: "POST",
        contentType : 'application/json;charset=utf-8', //设置请求头信息
        dataType:"json",
        data: jsonStr,
        success: function(data){
            alert(data);
        },
        error: function(res){
            alert(res.responseText);
        }
    });
} 

//单击事件
function S_NodeClick(event, treeId, treeNode) {
    $("#module").val(treeNode.name);
    $("#moduleid").val(treeNode.id);
    return false;
}