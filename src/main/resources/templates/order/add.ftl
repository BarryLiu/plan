<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> - 表单验证 jQuery Validation</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="/favicon.ico">
    <link href="${ctx!}/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx!}/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx!}/assets/css/animate.css" rel="stylesheet">
    <link href="${ctx!}/assets/css/style.css?v=4.1.0" rel="stylesheet">

    <#--多选下拉框-->
    <link rel="stylesheet" href="/bootstrap_select/dist/css/bootstrap-select.css">

    <script src="${ctx!}/css/plan.css"></script>
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5><!--订单编辑--></h5>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="frm" method="post" action="${ctx!}/order/edit">
                        	<input type="hidden" id="id" name="id" value="${order.id}">

                            <div class="form-group">
                                <label class="col-sm-4 control-label"><h2>用户信息</h2></label>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">姓名： </label>
                                <div class="col-sm-4">
                                    <input id="userName" name="userName" class="form-control" type="text" value="${order.userName}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">手机： </label>
                                <div class="col-sm-4">
                                    <input id="phone" name="phone" class="form-control" type="text" value="${order.phone}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">付款方式： </label>
                                <div class="col-sm-4">
                                    <input id="payStatus" name="payStatus" checked  type="radio" value="0"> 货到付款
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label" >收货地址：</label>
                                <div class="col-sm-4">
                                    <input id="address" name="address" class="form-control" type="text" >
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-4 control-label"><h2>订单信息</h2></label>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">订购商品： </label>
                                <div class="col-sm-4">
                                	<select id="productName" name="productName" class="form-control">
                                		<option value="" >请选择</option>
                                		<option value="零售客户 490元（1盒装）" >零售客户 490元（1盒装）</option>
                                		<option value="天使代理 680元（2盒装）" >天使代理 680元（2盒装）</option>
                                        <option value="特约代理 3100元（10盒装）">特约代理 3100元（10盒装）</option>
                                	</select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">价格：</label>
                                <div class="col-sm-3">
                                	<input id="price" placeholder="单位:元" name="price" class="form-control" type="number" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">备注说明: </label>
                                <div class="col-sm-4">
                                	<input id="createComment" name="createComment" class="form-control" type="text" >
                                </div>
                            </div>
                            
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">下单时间：</label>
                                <div class="col-sm-2">
                                    <input id="createTime" name="createTime" class="laydate-icon form-control layer-date" value="${order.createTime}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">快递公司：</label>
                                <div class="col-sm-4">
                                    <input id="expressCompany" name="expressCompany" class="form-control" type="text" value="${order.expressCompany}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">快递运单号：</label>
                                <div class="col-sm-4">
                                    <input id="expressNumber" name="expressNumber" class="form-control" type="text" value="${order.expressNumber}">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">状态：</label>
                                <div class="col-sm-2">
                                	<select name="status" class="form-control">
                                        <option value="0" <#if order.status == 0>selected="selected"</#if>>待处理</option>
                                		<option value="1" <#if order.status == 1>selected="selected"</#if>>已处理</option>
                                	</select>
                                </div>
                            </div>
                            <div class="form-group">
                                    <div class="col-sm-8 col-sm-offset-3">
                                        <@shiro.hasPermission name="order:add">
                                            <button class="btn btn-primary" type="submit" >提交</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        </@shiro.hasPermission>
                                        <button class="btn btn-primary" type="reset">重置</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                                        <button class="btn btn-primary" type="button" onclick="closeWindow();">取消</button>
                                    </div>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div>
   

    <!-- 全局js -->
    <script src="${ctx!}/assets/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx!}/assets/js/bootstrap.min.js?v=3.3.6"></script>

    <!-- 自定义js -->
    <script src="${ctx!}/assets/js/content.js?v=1.0.0"></script>

    <!-- jQuery Validation plugin javascript-->
    <script src="${ctx!}/assets/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${ctx!}/assets/js/plugins/validate/messages_zh.min.js"></script>
    <script src="${ctx!}/assets/js/plugins/layer/layer.min.js"></script>
    <script src="${ctx!}/assets/js/plugins/layer/laydate/laydate.js"></script>

    <#--多选下拉框-->
    <script src="${ctx!}/bootstrap_select/dist/js/bootstrap-select.js"></script>

    <script src="${ctx!}/js/plan.js"></script>
    <script type="text/javascript">
    $(document).ready(function () {
        //外部js调用

	    $("#frm").validate({
    	    rules: {
    	    	userName: {
    	        required: true,
    	        minlength: 2,
    	    	maxlength: 50
    	      },
    	      phone: {
                required: true,
                minlength: 6,
                maxlength: 13
              },
              address: {
                required: true,
                minlength: 6,
                maxlength: 13
              },
    	    	productName: {
    	        required: true
    	      },
                status: {
    	        required: true
    	      },
                price: {
    	        required: true
    	      },
    	    	expressCompany: {
    	        required: false,
    	        minlength: 2,
    	    	maxlength: 50
    	      },
    	      expressNumber: {
                required: false,
                minlength: 6,
                maxlength: 500
              }
    	    },
    	    messages: {},
    	    submitHandler:function(form){
    	    	$.ajax({
   	    		   type: "POST",
   	    		   dataType: "json",
   	    		   url: "${ctx!}/order/edit",
   	    		   data: $(form).serialize(),
   	    		   success: function(msg){
	   	    			layer.msg(msg.message, {time: 2000},function(){
                            closeWindow();
	   					});
   	    		   }
   	    		});
            } 
    	});
    	
    	 
    });
  
    
    
    
    var closeWindow = function(){
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index);
    }
    </script>

</body>

</html>
