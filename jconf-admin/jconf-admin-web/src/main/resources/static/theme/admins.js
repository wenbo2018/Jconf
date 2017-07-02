$(document).ready(function () {

    //User删除Ajax
    $("#content").on("click","#users-delete-user",function(){
        $.ajax({
            url: "/admins/datas/deleteUser",
            type: "POST",
            dataType: "text",
            data: {"userId": $("#users-delete-user").attr("name")},
            success: function (data) {
                var obj = jQuery.parseJSON(data);
                if (obj.code == 200) {
                    alert("删除成功");
                    $("#users-delete-user").parent("td").parent("tr").remove();
                } else {
                    alert("删除失败"+obj.meaasge);
                }
            },
            error: function (data) {
                alert("删除失败"+data);
            }
        });
    });

    //IMU删除Ajax
    $("#contenti").on("click","#datas-delete-imu",function(){
        $.ajax({
            url: "/admins/datas/deleteImu",
            type: "POST",
            dataType: "text",
            data: {"imuId": $("#datas-delete-imu").attr("name")},
            success: function (data) {
                var obj = jQuery.parseJSON(data);
                if (obj.code == 200) {
                    alert("删除成功");
                    $("#datas-delete-imu").parent("td").parent("tr").remove();
                } else {
                    alert("删除失败"+obj.meaasge);
                }
            },
            error: function (data) {
                alert("删除失败"+data);
            }
        });
    });

    //IMU删除Ajax
    $("#contentw").on("click","#datas-delete-wifi",function(){
        $.ajax({
            url: "/admins/datas/deleteWifi",
            type: "POST",
            dataType: "text",
            data: {"wifiId": $("#datas-delete-wifi").attr("name")},
            success: function (data) {
                var obj = jQuery.parseJSON(data);
                if (obj.code == 200) {
                    alert("删除成功");
                    $("#datas-delete-wifi").parent("td").parent("tr").remove();
                } else {
                    alert("删除失败"+obj.meaasge);
                }
            },
            error: function (data) {
                alert("删除失败"+data);
            }
        });
    });

    //AP删除Ajax
    $("#contenta").on("click","#datas-delete-ap",function(){
        $.ajax({
            url: "/admins/datas/deleteAp",
            type: "POST",
            dataType: "text",
            data: {"apId": $("#datas-delete-ap").attr("name")},
            success: function (data) {
                var obj = jQuery.parseJSON(data);
                if (obj.code == 200) {
                    alert("删除成功");
                    $("#datas-delete-ap").parent("td").parent("tr").remove();
                } else {
                    alert("删除失败"+obj.meaasge);
                }
            },
            error: function (data) {
                alert("删除失败"+data);
            }
        });
    });


    //AP删除Ajax
    $("#contentr").on("click","#datas-delete-recoder",function(){
        $.ajax({
            url: "/admins/datas/deleteRecoder",
            type: "POST",
            dataType: "text",
            data: {"recoderId": $("#datas-delete-recoder").attr("name")},
            success: function (data) {
                var obj = jQuery.parseJSON(data);
                if (obj.code == 200) {
                    alert("删除成功");
                    $("#datas-delete-recoder").parent("td").parent("tr").remove();
                } else {
                    alert("删除失败"+obj.meaasge);
                }
            },
            error: function (data) {
                alert("删除失败"+data);
            }
        });
    });


    //数据下载Ajax
    $("#contentr").on("click","#datas-download",function(){
        $.ajax({
            url: "/admins/datas/downEcxel",
            type: "GET",
            data: {"position": $("#datas-download").attr("name"),"type":$("#datas-download").attr("type")},
        });
    });

    //AP删除Ajax
    $("#contentmap").on("click","#maps-delete-map",function(){
        $.ajax({
            url: "/admins/datas/deleteMap",
            type: "POST",
            dataType: "text",
            data: {"mapId": $("#maps-delete-map").attr("name")},
            success: function (data) {
                var obj = jQuery.parseJSON(data);
                if (obj.code == 200) {
                    alert("删除成功");
                    $("#maps-delete-map").parent("td").parent("tr").remove();
                } else {
                    alert("删除失败"+obj.meaasge);
                }
            },
            error: function (data) {
                alert("删除失败"+data);
            }
        });
    });


    $("#contentmap").on("mouseover mouseout",".preview",function(){
        if(event.type == "mouseover"){
            var xOffset = 10;
            var yOffset = 20;
            var w = $(window).width();
            if(/.png$|.gif$|.jpg$|.bmp$/.test($(this).attr("href"))){
                $("body").append("<div id='preview'><div><img src='"+$(this).attr('href')+"' /><p>"+$(this).attr('title')+"</p></div></div>");
            }else{
                $("body").append("<div id='preview'><div><p>"+$(this).attr('title')+"</p></div></div>");
            }
            $("#preview").css({
                position:"absolute",
                padding:"4px",
                border:"1px solid #f3f3f3",
                backgroundColor:"#eeeeee",
                top:(e.pageY - yOffset) + "px",
                zIndex:1000
            });
            $("#preview > div").css({
                padding:"5px",
                backgroundColor:"white",
                border:"1px solid #cccccc"
            });
            $("#preview > div > p").css({
                textAlign:"center",
                fontSize:"12px",
                padding:"8px 0 3px",
                margin:"0"
            });
            if(e.pageX < w/2){
                $("#preview").css({
                    left: e.pageX + xOffset + "px",
                    right: "auto"
                }).fadeIn("fast");
            }else{
                $("#preview").css("right",(w - e.pageX + yOffset) + "px").css("left", "auto").fadeIn("fast");
            }
        }else if(event.type == "mouseout"){
            $("#preview").remove();
        }
    });


    //AP删除Ajax
    $("#contentmap").on("click","#imges",function(){
        var tb=$(this).attr("name");
        $('#'+tb).viewer({
            url: 'data-original',
        });
    });



    //APIdata删除Ajax
    $("#contentapi").on("click","#apiss-delete-apidata",function(){
        $.ajax({
            url: "/admins/apis/deleteApiData",
            type: "POST",
            dataType: "text",
            data: {"apiId": $("#apiss-delete-apidata").attr("name")},
            success: function (data) {
                var obj = jQuery.parseJSON(data);
                if (obj.code == 200) {
                    alert("删除成功");
                    $("#apiss-delete-apidata").parent("td").parent("tr").remove();
                } else {
                    alert("删除失败"+obj.meaasge);
                }
            },
            error: function (data) {
                alert("删除失败"+data);
            }
        });
    });
});