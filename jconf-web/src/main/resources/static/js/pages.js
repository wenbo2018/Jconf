
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
                url : '/config/configPagses', // 请求地址
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
                        c+="<tr><td>"+data.list[i].id+"</td><td>"+data.list[i].key+"</td><td>"+data.list[i].value+"</td>";
                        c+="<td>"+data.list[i].env+"</td><td>"+data.list[i].projectId+"</td><td>"+data.list[i].userName+"</td>";
                        c+="<td>"+data.list[i].userEmail+"</td>";
                        c+="<td>"+data.list[i].dateTime+"</td>";
                        c+="<td ><button type='button' data-toggle='modal' data-target='#mymodal' class='btn  btn-info'>修改</button>";
                        c+="&nbsp;<a  id='users-delete-user'  name='"+data.list[i].id+"'  data-toggle='modal' class='btn btn-danger'>删除</a></td></tr>";
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

