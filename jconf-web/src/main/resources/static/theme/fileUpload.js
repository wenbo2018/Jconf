$(window).load(function(){
    $("#myFile").fileinput({
        language : 'zh',
        uploadUrl : "/admins/datas/uploadFile",
        autoReplace : true,
        maxFileCount : 1,
        allowedFileExtensions : [ "jpg", "png", "gif" ],
        browseClass : "btn btn-primary", //按钮样式
        previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
        uploadExtraData: function(previewId, index) {   //额外参数的关键点
            var obj = {};
            obj.mapName = fetchMapName();
            obj.mapDesc=fetchMapDesc();
            console.log(obj);
            return obj;
        }
    }).on("fileuploaded", function(e, data) {
        var res = data.response;

        $("#logo").attr("value", res.success);
    })

    fetchMapName = function() {
        return $("#maps-mapName").val();
    };
    fetchMapDesc = function() {
        return $("#maps-mapDesc").val();
    };

});



