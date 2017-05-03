<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta content='IE=edge,chrome=1' http-equiv='X-UA-Compatible'>
<#--<link rel="stylesheet" type="text/css" href="/theme/lib/bootstrap/css/bootstrap.css">-->
    <link rel="stylesheet" href="/theme/lib/font-awesome/css/font-awesome.css">

    <script src="/theme/lib/jquery-1.11.1.min.js" type="text/javascript"></script>

    <link rel="stylesheet" type="text/css" href="/theme/stylesheets/theme.css">
    <link rel="stylesheet" type="text/css" href="/theme/stylesheets/premium.css">

    <link rel="stylesheet" type="text/css" href="/theme/jquery.pagination.css">
    <script src="/theme/jquery-2.1.4.min.js"></script>
    <script src="/theme/jquery.pagination-1.2.7.min.js"></script>
    <script src="/js/pages.js"></script>
    <script src="/js/jconf.js"></script>
<#--css-->
    <link rel="stylesheet" type="text/css" href="/bs/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="/bs/css/bootstrap-theme.css">

<#--<script src="/theme/admins.js"></script>-->
</head>
<body class=" theme-blue">

<!-- Demo page code -->
<script type="text/javascript">
    $(window).load(function () {
        configPage();
    });


</script>
<style type="text/css">
    #line-chart {
        height: 300px;
        width: 800px;
        margin: 0px auto;
        margin-top: 1em;
    }

    .navbar-default .navbar-brand, .navbar-default .navbar-brand:hover {
        color: #fff;
    }

    /*.modal-dialog {*/
    /*position: absolute;*/
    /*left: 50%;*/
    /*top: 50%;*/
    /*transform:translateX(-50%) translateY(-50%);*/
    /*}*/

</style>

<script type="text/javascript">
    $(function () {
        var uls = $('.sidebar-nav > ul > *').clone();
        uls.addClass('visible-xs');
        $('#main-menu').append(uls.clone());
    });
</script>



<#include "/admin/menu.ftl"/>





<div class="content">

    <div class="main-content">


        <div class="btn-toolbar list-toolbar">
            <button type='button' data-toggle='modal' data-target='#mymodal2' class='btn  btn-info'>新增配置</button>
        </div>
        <div class="row">
            <div class="col-md-12">
                <br>
                <div id="myTabContent" class="tab-content">
                <#--第一版-->
                    <div class="tab-pane active in" id="home">
                        <div class="panel panel-default">
                            <div class="panel-heading no-collapse">配置数据列表<span class="label label-warning"></span></div>
                            <table class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th class="center">#</th>
                                    <th class="center">Key</th>
                                    <th class="center">Value</th>
                                    <th class="center">环境</th>
                                    <th class="center">项目ID</th>
                                    <th class="center">负责人</th>
                                    <th class="center">邮箱</th>
                                    <th class="center">修改时间</th>
                                    <th class="center">操作</th>
                                </tr>
                                </thead>
                                <tbody id="content">

                                </tbody>
                            </table>
                        </div>
                        <div id="page">
                            <!-- pagination所需容器 -->
                            <div class="pagination"></div>
                        </div>
                    </div>



                </div>
            </div>
        </div>


    </div>
</div>


<script src="/theme/lib/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
    $("[rel=tooltip]").tooltip();
    $(function () {
        $('.demo-cancel-click').click(function () {
            return false;
        });
    });
</script>

<!-- 触发模态弹出窗的元素 -->
<!-- 模态弹出窗 -->
<div class="modal fade" id="mymodal2">
    <div class="modal-dialog " style="width:1000px;">
        <div class="modal-content">
            <div style="width: 100%;padding: 10px;">
                <div class="container-fluid">
                    <div class="row-fluid">
                        <form class="form-horizontal" role="form">
                            <fieldset>
                                <legend class="text-center ">新增配置</legend>
                                <div class="form-group">
                                    <label class="col-sm-1 control-label" >Key:</label>
                                    <div class="col-sm-5">
                                        <input class="form-control"  name="cofing_key" id="cofing_key" type="text"
                                               placeholder="192.168.1.161"/>
                                    </div>
                                    <label class="col-sm-1 control-label" >Value:</label>
                                    <div class="col-sm-5">
                                        <input class="form-control" name="cofing_value" id="cofing_value" type="text" placeholder="msh"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-1 control-label" for="ds_username">项目ID:</label>
                                    <div class="col-sm-5">
                                        <input class="form-control" name="cofing_projectId" id="cofing_projectId" type="text" placeholder="root"/>
                                    </div>
                                    <label class="col-sm-1 control-label" for="ds_password">负责人:</label>
                                    <div class="col-sm-5">
                                        <input class="form-control" name="cofing_userName" id="cofing_userName"
                                               placeholder="123456"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-1 control-label" for="ds_username">邮箱:</label>
                                    <div class="col-sm-5">
                                        <input class="form-control" name="cofing_userMail" id="cofing_userMail" type="text" placeholder="root"/>
                                    </div>
                                    <label class="col-sm-1 control-label" for="ds_password">环境</label>
                                    <div class="col-sm-5">
                                        <select id="config_env" name="confg_env" class="form-control">
                                            <option value="1">测试环境</option>
                                            <option value="2">线上环境</option>
                                        </select>
                                    </div>
                                </div>
                            </fieldset>
                            <div class="form-group text-center">
                                <button class="btn btn-success btn-large" type="button" id="config_add">保存</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 模态弹出窗 -->
<div class="modal fade" id="mymodal">
    <div class="modal-dialog " style="width:800px;">
        <div class="modal-content">
            <div style="width: 100%;padding: 30px;">
                <div class="container-fluid">
                    <div class="row-fluid">
                        <form class="form-horizontal" id="2" role="form">
                            <fieldset>
                                <legend class="text-center ">修改配置</legend>
                                <div class="form-group">
                                    <label class="col-sm-1 control-label" for="ds_host">Key:</label>
                                    <div class="col-sm-5">
                                        <input class="form-control" id="config_id" type="hidden" value="" placeholder="msn"/>
                                        <input class="form-control" id="config_update_key" type="text" value=""/>
                                    </div>
                                    <label class="col-sm-1 control-label" for="ds_name">Value:</label>
                                    <div class="col-sm-5">
                                        <input class="form-control" id="config_update_value" type="text" placeholder="msh"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="form-group text-center">
                                        <button class="btn btn-success btn-large" type="button" type="config_update">保存</button>
                                    </div>
                                </div>
                            </fieldset>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<#--信息提示框-->
<div class="modal fade " id="myAlert" style="z-index:2000; ">
    <div class="modal-backdrop in" style="z-index:1900"></div>
    <div class="modal-dialog" style="z-index:1901; margin-top:10%; ">
        <div class="modal-content">
            <div class="modal-header" >
                警告！
                <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
            </div>
            <div class="modal-body text-center" id="myAlertContent">

            </div>
        </div>
    </div>
</div>


</body>
</html>
