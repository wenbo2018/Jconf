$(function () {
    $('#config_add').click(function () {//ID为test的元素点击时
        configAdd();

    });
    $("#content").on("click", "#config_delete", function () {
        configDelete();
    });
    $("#content").on("click", "#config_update", function () {
        var str = $(this).attr("name");
        var strs = new Array(); //定义一数组
        strs = str.split(","); //字符分割
        var id = strs[0];
        var key = strs[1];
        $("#config_update_key").val(key);
        $("#config_update_key").attr('disabled', true);
        $("#update_config_id").val(id);
    });
    $("#content").on("click", "#look_config", function () {
        var str = $(this).attr("name");
        $("#config_value").text(str);
    });


    $('#config_update_submit').click(function () {//ID为test的元素点击时
        configUpdate();
    });

});


function configAdd() {
    var key = $("#cofing_key").val();
    var value = $("#cofing_value").val();
    var cofing_type = $("#cofing_type").val();
    var projectId = $("#cofing_projectId").val();
    var env = $("#config_env option:selected").val();
    $.ajax({
        //提交数据的类型 POST GET
        type: "POST",
        //提交的网址
        url: "/jconf/admin/config/add",
        //提交的数据
        data: {cofing_type: cofing_type, key: key, value: value, projectId: projectId, env: env},
        //返回数据的格式
        datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
        // //在请求之前调用的函数
        // beforeSend:function(){$("#msg").html("logining");},
        // //成功返回之后调用的函数
        success: function (data) {
            // $("#msg").html(decodeURI(data));
            if (data.code != 200) {
                myAlert(data.message);
            }
            if (data.code == 200) {
                $('#mymodal2').modal('hide');
                myAlert("新增配置成功！");
            }
        },
        // //调用执行后调用的函数
        // complete: function(XMLHttpRequest, textStatus){
        //     alert(XMLHttpRequest.responseText);
        //     alert(textStatus);
        //     //HideLoading();
        // },
        //调用出错执行的函数
        error: function () {
            //请求出错处理
        }
    });
}


function configDelete() {


    var id = $("#config_delete").attr("name");
    $.ajax({
        //提交数据的类型 POST GET
        type: "POST",
        //提交的网址
        url: "/jconf/admin/config/delete",
        //提交的数据
        data: {id: id},
        //返回数据的格式
        datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
        // //在请求之前调用的函数
        // beforeSend:function(){$("#msg").html("logining");},
        // //成功返回之后调用的函数
        success: function (data) {
            //$("#msg").html(decodeURI(data));
            if (data.code != 200) {
                myAlert(data.message);
            }
            if (data.code == 200) {
                myAlert("删除成功!");
                $("#config_delete").parent("td").parent("tr").remove();
            }
        },
        // //调用执行后调用的函数
        // complete: function(XMLHttpRequest, textStatus){
        //     alert(XMLHttpRequest.responseText);
        //     alert(textStatus);
        //     //HideLoading();
        // },
        //调用出错执行的函数
        error: function () {
            //请求出错处理
        }
    });
}


function configUpdate() {

    var id = $("#update_config_id").val();
    var value = $("#update_config_value").val();
    $.ajax({
        //提交数据的类型 POST GET
        type: "POST",
        //提交的网址
        url: "/jconf/admin/config/update",
        //提交的数据
        data: {id: id, value: value},
        //返回数据的格式
        datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
        // //在请求之前调用的函数
        // beforeSend:function(){$("#msg").html("logining");},
        // //成功返回之后调用的函数
        success: function (data) {
            //$("#msg").html(decodeURI(data));
            if (data.code != 200) {
                myAlert(data.message);
            }
            if (data.code == 200) {
                $('#mymodal').modal('hide');
                myAlert("修改成功!");
            }
        },
        // //调用执行后调用的函数
        // complete: function(XMLHttpRequest, textStatus){
        //     alert(XMLHttpRequest.responseText);
        //     alert(textStatus);
        //     //HideLoading();
        // },
        //调用出错执行的函数
        error: function () {
            //请求出错处理
        }
    });
}




function myAlert(info) {
    $("#myAlertContent").text(info);
    $("#myAlert").modal("show");
}
