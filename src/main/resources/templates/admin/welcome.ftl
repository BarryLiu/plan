<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> 订单后台管理 </title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> 
    <link href="${ctx!}/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx!}/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx!}/assets/css/animate.css" rel="stylesheet">
    <link href="${ctx!}/assets/css/style.css?v=4.1.0" rel="stylesheet">

        <!-- 全局js -->
    <script src="${ctx!}/assets/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx!}/assets/js/bootstrap.min.js?v=3.3.6"></script>


	<!-- Bootstrap table -->
    <script src="${ctx!}/assets/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${ctx!}/assets/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="${ctx!}/assets/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

    <!-- Peity -->
    <script src="${ctx!}/assets/js/plugins/peity/jquery.peity.min.js"></script>

    <script src="${ctx!}/assets/js/plugins/layer/layer.min.js"></script>

    <!-- 自定义js -->
    <script src="${ctx!}/assets/js/content.js?v=1.0.0"></script>
</head>

<body class="gray-bg">
     
        <div class="row">
            <div class="col-sm-12">
                <div class="tabs-container">
                    <ul class="nav nav-tabs">
                        <li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true"> 管理员管理</a>
                        </li>
                        <li class=""><a data-toggle="tab" href="#tab-2" aria-expanded="false">订单管理</a>
                        </li>
                        <li class=""><a data-toggle="tab" href="#tab-3" aria-expanded="false" onclick="window.open('https://www.51.la/report/main?comId=20048555');">流量统计</a>
                        </li>
                        <li class=""><a data-toggle="tab" href="#tab-4" aria-expanded="false" onclick="location.href='${ctx!}/admin/logout';">退出</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div id="tab-1" class="tab-pane active >
 							<#include "${ctx!}/admin/user/index.ftl">
                        </div>
                        <div id="tab-2" class="tab-pane">
                            <#include "${ctx!}/order/index.ftl">
                        </div>
                        <div id="tab-3" class="tab-pane">
                        </div>
                        <div id="tab-4" class="tab-pane">
                            <div class="panel-body">
                                <strong>退出登录</strong>
                            </div>
                        </div>
                    </div>


                </div>
            </div>

             


    <!-- 全局js -->
    <script src="${ctx!}/assets/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx!}/assets/js/bootstrap.min.js?v=3.3.6"></script>

    <script src="${ctx!}/assets/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${ctx!}/assets/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>

    <!-- 自定义js -->
    <script src="${ctx!}/assets/js/content.js?v=1.0.0"></script>
	<script >
    var rootUrl = "${ctx!}/plan/module";

    
</script>
</body>

</html>
