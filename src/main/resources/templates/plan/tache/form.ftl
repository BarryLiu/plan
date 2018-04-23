<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> - 表单验证 jQuery Validation</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> 
    <link href="${ctx!}/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx!}/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx!}/assets/css/animate.css" rel="stylesheet">
    <link href="${ctx!}/assets/css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>jQuery Validate 简介</h5>
                    </div>
                    <div class="ibox-content">
                        <p>jquery.validate.js 是一款优秀的jQuery表单验证插件。它具有如下特点：</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>环节编辑</h5>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="frm" method="post" action="${ctx!}/plan/tache/edit">
                        	<input type="hidden" id="id" name="id" value="${tache.id}">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">环节序号：</label>
                                <div class="col-sm-2">
                                    <input id="tacheIndex" name="tacheIndex" readonly="readonly" class="form-control" type="text" value="${tache.tacheIndex}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">环节名称：</label>
                                <div class="col-sm-5">
                                    <input id="name" name="name" class="form-control" readonly="readonly" type="text" value="${tache.name}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">计划时间：</label>
                                <div class="col-sm-2">
                                    <input id="planBeginTime" name="planBeginTime" readonly="readonly" class="laydate-icon form-control layer-date" value="${tache.planBeginTime}">
                                </div>
                                <div class="col-sm-2">
                                    <input id="planEndTime" name="planEndTime" readonly="readonly" class="laydate-icon form-control layer-date" value="${tache.planEndTime}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">实际时间：</label>
                                <div class="col-sm-2">
                                    <input id="realBeginTime" name="realBeginTime" readonly="readonly" class="laydate-icon form-control layer-date" value="${tache.realBeginTime}">
                                </div>
                                <div class="col-sm-2">
                                    <input id="realEndTime" name="realEndTime" readonly="readonly" class="laydate-icon form-control layer-date" value="${tache.realEndTime}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">状态：</label>
                                <div class="col-sm-2">
                                	<select name="status" class="form-control">
                                		<option value="0" <#if tache.status == 0>selected="selected"</#if>>新创建</option>
                                		<option value="1" <#if tache.status == 1>selected="selected"</#if>>新执行中</option>
                                        <option value="2" <#if tache.status == 2>selected="selected"</#if>>测试中</option>
                                        <option value="3" <#if tache.status == 3>selected="selected"</#if>>归档完成</option>
                                	</select>
                                </div>
                            </div>
                            <#--<div class="form-group">
                                <label class="col-sm-3 control-label">创建描述${tache.createComment}：</label>
                                <div class="col-sm-5">
                                    <input id="createComment" name="createComment" class="form-control" value="${tache.createComment}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">修改描述：</label>
                                <div class="col-sm-5">
                                    <input id="updateComment" name="updateComment" class="form-control" value="${tache.updateComment}">
                                </div>
                            </div>-->
                            <div class="form-group">
                                <label class="col-sm-3 control-label">备注：</label>
                                <div class="col-sm-5">
                                    <textarea style="height: 150px;" id="updateComment" name="updateComment" class="form-control">${tache.updateComment}</textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-5 col-sm-offset-3">
                                    <button class="btn btn-primary" type="submit">提交</button>
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
    <script type="text/javascript">
    $(document).ready(function () {
        //外部js调用
        laydate({
            elem: '#planBeginTime', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
        });
        laydate({
            elem: '#planEndTime', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
        });
        laydate({
            elem: '#realBeginTime', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
        });
        laydate({
            elem: '#realEndTime', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
        });
	    $("#frm").validate({
    	    rules: {
    	    	name: {
    	        required: true,
    	        minlength: 4,
    	    	maxlength: 20
    	      },
                status: {
    	        required: true
    	      },
    	      	description: {
    	        required: true,
    	        maxlength: 40
    	      }
    	    },
    	    messages: {},
    	    submitHandler:function(form){
    	    	$.ajax({
   	    		   type: "POST",
   	    		   dataType: "json",
   	    		   url: "${ctx!}/plan/tache/edit",
   	    		   data: $(form).serialize(),
   	    		   success: function(msg){
	   	    			layer.msg(msg.message, {time: 2000},function(){
	   						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	   						parent.layer.close(index); 
	   					});
   	    		   }
   	    		});
            } 
    	});
    });
    </script>

</body>

</html>
