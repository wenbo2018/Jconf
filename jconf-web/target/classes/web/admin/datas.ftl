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


    <#--<ul class="nav nav-tabs">-->
    <#--<li class="active"><a href="#home" data-toggle="tab">IMU数据</a></li>-->
    <#--<li><a href="#profile"   data-toggle="tab">WIFI数据</a></li>-->
    <#--<li><a href="#recoder"   data-toggle="tab">数据采集记录</a></li>-->
    <#--<li><a href="#ap"   data-toggle="tab">AP数据</a></li>-->
    <#--</ul>-->

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

                <#--&lt;#&ndash;第二版&ndash;&gt;-->
                <#--<div class="tab-pane fade" id="profile">-->
                <#--<div class="panel panel-default">-->
                <#--<div class="panel-heading no-collapse">WIFI数据<span class="label label-warning">+10</span></div>-->
                <#--<table class="table table-bordered table-striped">-->
                <#--<thead>-->
                <#--<tr>-->
                <#--<th class="center">Id</th>-->
                <#--<th class="center">AP1</th>-->
                <#--<th class="center">AP2</th>-->
                <#--<th class="center">AP3</th>-->
                <#--<th class="center">AP4</th>-->
                <#--<th class="center">AP5</th>-->
                <#--<th class="center">AP6</th>-->
                <#--<th class="center">AP7</th>-->
                <#--<th class="center">AP8</th>-->
                <#--<th class="center">AP9</th>-->
                <#--<th class="center">AP10</th>-->
                <#--<th class="center">Position</th>-->
                <#--<th class="center">操作</th>-->
                <#--</tr>-->
                <#--</thead>-->
                <#--<tbody id="contentw">-->

                <#--</tbody>-->
                <#--</table>-->
                <#--</div>-->
                <#--<div id="pagew">-->
                <#--<!-- pagination所需容器 &ndash;&gt;-->
                <#--<div class="w-pagination"></div>-->
                <#--</div>-->
                <#--</div>-->

                <#--&lt;#&ndash;第三版&ndash;&gt;-->
                <#--<div class="tab-pane fade" id="recoder">-->
                <#--<div class="panel panel-default">-->
                <#--<div class="panel-heading no-collapse">数据采集记录<span class="label label-warning">+10</span></div>-->
                <#--<table class="table table-bordered table-striped">-->
                <#--<thead>-->
                <#--<tr>-->
                <#--<th class="center">Id</th>-->
                <#--<th class="center">Position</th>-->
                <#--<th class="center">手机型号</th>-->
                <#--<th class="center">数据量</th>-->
                <#--<th class="center">类型</th>-->
                <#--<th class="center">采集时间</th>-->
                <#--<th class="center">操作</th>-->
                <#--</tr>-->
                <#--</thead>-->
                <#--<tbody id="contentr">-->

                <#--</tbody>-->
                <#--</table>-->
                <#--</div>-->
                <#--<div id="pager">-->
                <#--<!-- pagination所需容器 &ndash;&gt;-->
                <#--<div class="r-pagination"></div>-->
                <#--</div>-->
                <#--</div>-->

                <#--&lt;#&ndash;第四版&ndash;&gt;-->
                <#--<div class="tab-pane fade" id="ap">-->
                <#--<div class="panel panel-default">-->
                <#--<div class="panel-heading no-collapse">AP数据<span class="label label-warning">+10</span></div>-->
                <#--<table class="table table-bordered table-striped">-->
                <#--<thead>-->
                <#--<tr>-->
                <#--<th class="center">Id</th>-->
                <#--<th class="center">MAC地址</th>-->
                <#--<th class="center">操作</th>-->
                <#--</tr>-->
                <#--</thead>-->
                <#--<tbody id="contenta">-->

                <#--</tbody>-->
                <#--</table>-->
                <#--</div>-->
                <#--<div id="pagea">-->
                <#--<!-- pagination所需容器 &ndash;&gt;-->
                <#--<div class="a-pagination"></div>-->
                <#--</div>-->
                <#--</div>-->

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
<div class="modal fade" id="mymodal">
    <div class="modal-dialog" style="width:800px;">
        <div class="modal-content">
            <div style="width: 100%;padding: 40px;">
                <div class="container-fluid">
                    <div class="row-fluid">
                        <form class="form-horizontal" role="form">
                            <fieldset>
                                <legend class="text-center ">配置数据源</legend>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label" for="ds_host">Key:</label>
                                    <div class="col-sm-4">
                                        <input class="form-control" id="ds_host" type="text" placeholder="192.168.1.161"/>
                                    </div>
                                    <label class="col-sm-2 control-label" for="ds_name">Value:</label>
                                    <div class="col-sm-4">
                                        <input class="form-control" id="ds_name" type="text" placeholder="msh"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label" for="ds_username">项目ID:</label>
                                    <div class="col-sm-4">
                                        <input class="form-control" id="ds_username" type="text" placeholder="root"/>
                                    </div>
                                    <label class="col-sm-2 control-label" for="ds_password">负责人:</label>
                                    <div class="col-sm-4">
                                        <input class="form-control" id="ds_password" type="password" placeholder="123456"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label" for="ds_username">邮箱:</label>
                                    <div class="col-sm-4">
                                        <input class="form-control" id="ds_username" type="text" placeholder="root"/>
                                    </div>
                                    <label class="col-sm-2 control-label" for="ds_password">环境选择</label>
                                    <div class="col-sm-4">
                                        <select id="disabledSelect" class="form-control">
                                            <option>Beat环境</option>
                                            <option>QA环境</option>
                                            <option>Production环境</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group text-center">
                                    <button class="btn btn-success btn-large" type="button">保存</button>
                                </div>
                            </fieldset>
                </div>
            </div>
        </div>

</body>
</html>
