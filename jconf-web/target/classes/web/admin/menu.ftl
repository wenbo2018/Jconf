<style type="text/css">
    .table th, .table td {
        text-align: center;
        height: 38px;
    }
</style>
<div class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="" href="index.ftl"><span class="navbar-brand"><span class="fa fa-paper-plane"></span>Jconf配置管理平台</span></a>
    </div>

    <div class="navbar-collapse collapse" style="height: 1px;">
        <ul id="main-menu" class="nav navbar-nav navbar-right">
            <li class="dropdown hidden-xs">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <span class="glyphicon glyphicon-user padding-right-small" style="position:relative;top: 3px;"></span>shenwenbo<i class="fa fa-caret-down"></i>
                </a>

                <ul class="dropdown-menu">
                    <li><a href="../">My Account</a></li>
                    <li class="divider"></li>
                    <li class="dropdown-header">Admin Panel</li>
                    <li><a href="../">Users</a></li>
                    <li><a href="../">Security</a></li>
                    <li><a tabindex="-1" href="../">Payments</a></li>
                    <li class="divider"></li>
                    <li><a tabindex="-1" href="/sso/logout">Logout</a></li>
                </ul>
            </li>
        </ul>

    </div>
</div>
</div>


<div class="sidebar-nav">
    <ul>
        <li>
            <a href="#" data-target=".dashboard-menu" class="nav-header" data-toggle="collapse">
                <i class="fa fa-fw fa-dashboard"></i>配置管理<i class="fa fa-collapse"></i>
            </a>
        </li>
        <li>
            <ul class="dashboard-menu nav nav-list collapse in">
                <li><a href="index"><span class="fa fa-caret-right"></span>Beat环境</a></li>
                <li><a href="users"><span class="fa fa-caret-right"></span>QA环境</a></li>
                <li><a href="datas"><span class="fa fa-caret-right"></span>Production环境</a></li>
            </ul>
        </li>

        <li>
            <a href="#" data-target=".dashboard-menu" class="nav-header" data-toggle="collapse">
                <i class="fa fa-fw fa-dashboard"></i>系统管理<i class="fa fa-collapse"></i>
            </a>
        </li>
        <li>
            <ul class="dashboard-menu nav nav-list collapse ">
                <li><a href="accounts.ftl"><span class="fa fa-caret-right"></span>账号服务</a></li>
                <li><a href="apis"><span class="fa fa-caret-right"></span>API管理</a></li>
            </ul>
        </li>
</div>