
    function configPage() {
        /* 先销毁pagination容器 */
        $("#page").html("");
        /* 重新生成容器 */
        $("#page").html("<div class='pagination'></div>");
        $(".pagination").eq(0).page({
            firstBtnText : '首页',
            lastBtnText : '尾页',
            prevBtnText : '上一页',
            nextBtnText : '下一页',
            showInfo : true,
            showJump : true,
            jumpBtnText : '跳转',
            showPageSizes : true,
            infoFormat : '{start} ~ {end}条，共{total}条',
            remote : {
                url : '/jconf/admin/config/configPagses', // 请求地址
                params : {
                    // 'param' : param
                    // 自定义请求参数
                },
                beforeSend : function(XMLHttpRequest) {
                    $("#content").html("");
                },
                success : function(data, pageIndex) {
                    //data为响应返回的json数据
                    //...do somethings
                    var c="";
                    for (var i = 0; i < data.list.length; i++) {
                        c+="<tr><td>"+data.list[i].id+"</td><td>"+data.list[i].key+"</td><td><span class='glyphicon glyphicon-zoom-in' aria-hidden='true' data-toggle='modal' data-target='.look_config'  id='look_config' name='"+data.list[i].value+"'></span></td>";

                        if (data.list[i].configType==1) {
                            c+="<td>String</td>";
                        } else if (data.list[i].configType==2) {
                            c+="<td>Integer</td>";
                        } else if (data.list[i].configType==3) {
                            c += "<td>Boolean</td>";
                        } else if (data.list[i].configType==4) {
                            c += "<td>Double</td>";
                        } else {
                            c += "<td>String</td>";
                        }
                        if (data.list[i].env==1) {
                            c+="<td>测试环境</td>";
                        } else if (data.list[i].env==2){
                            c+="<td>线上环境</td>";
                        } else {
                            c+="<td>测试环境</td>";
                        }
                        c+="<td>"+data.list[i].projectId+"</td><td>"+data.list[i].userName+"</td>";
                        c+="<td>"+data.list[i].userEmail+"</td>";
                        c+="<td>"+data.list[i].dateTime+"</td>";
                        c+="<td ><button type='button' id='config_update' name='"+data.list[i].id+","+data.list[i].key+"'  data-toggle='modal' data-target='#mymodal' class='btn  btn-info'>修改</button>";
                        c+="&nbsp;<a  id='config_delete'  name='"+data.list[i].id+"'  data-toggle='modal' class='btn btn-danger'>删除</a></td></tr>";
                    }
                    $("#content").html(c);
                },
                complete : function(XMLHttpRequest, textStatu) {
                    //...
                },
                pageIndexName : 'pageIndex', //请求参数，当前页数，索引从0开始
                pageSizeName : 'pageSize', //请求参数，每页数量
                totalName : 'total' //指定返回数据的总数据量的字段名
            }
        });
    }

