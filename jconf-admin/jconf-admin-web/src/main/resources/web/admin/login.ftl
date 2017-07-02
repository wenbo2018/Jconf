<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta content='IE=edge,chrome=1' http-equiv='X-UA-Compatible'>

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

</head>
<script type="text/javascript">
    $(document).ready(function() {
        $("#login_button").click(function () {
            if ($("#username").val() == "" || $("#password").val() == "") {
                alert("用户名或密码不能为空");
            } else {
                $.ajax({
                    url: "/jconf/user/login/checkUser",
                    type: "POST",
                    dataType: "text",
                    data: {"userName": $("#username").val(), "passWord": $("#password").val()},
                    success: function (data) {
                        var obj=jQuery.parseJSON(data);
                        if (obj.code ==200) {
                            location.href = "/jconf/admin/config/index";
                        } else {
                            $("#error").text("用户名或密码错误");
                        }
                    },
                    error: function (data) {

                    }
                });
            }
        });

    });


</script>

<body class=" theme-blue">

<!-- Demo page code -->

<script type="text/javascript">
    $(function () {
        var match = document.cookie.match(new RegExp('color=([^;]+)'));
        if (match) var color = match[1];
        if (color) {
            $('body').removeClass(function (index, css) {
                return (css.match(/\btheme-\S+/g) || []).join(' ')
            })
            $('body').addClass('theme-' + color);
        }

        $('[data-popover="true"]').popover({html: true});

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
</style>

<script type="text/javascript">
    $(function () {
        var uls = $('.sidebar-nav > ul > *').clone();
        uls.addClass('visible-xs');
        $('#main-menu').append(uls.clone());
    });
</script>

<div class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <a class="" href="index.html"><span class="navbar-brand"><span class="fa fa-paper-plane"></span> Jconf</span></a>
    </div>
    <div class="navbar-collapse collapse" style="height: 1px;">
    </div>
</div>
</div>
<div class="dialog">
    <div class="panel panel-default">
        <p class="panel-heading no-collapse" style="text-align: center;">Jconf</p>
        <div class="panel-body">
            <form>
                <div class="form-group">
                    <label>Username</label>
                    <input type="text" class="form-control span12" id="username">
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input type="password" id="password" class="form-controlspan12 form-control">
                </div>

                <div class="form-group">
                    <label id="error" style="text-color:#cc0101;"></label>
                </div>

                <a id="login_button" class="btn btn-primary pull-right">登录</a>
                <label class="remember-me"><input type="checkbox">记住密码</label>
                <label class="remember-me"><a href="sginup">注册</a></label>
                <div class="clearfix"></div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
