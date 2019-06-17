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
                        <h5>修改订单</h5>
                    </div>
                    <div class="ibox-content">
                        <p></p>

                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>功能编辑</h5>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="frm" method="post" action="${ctx!}/plan/module/edit">
                        	<input type="hidden" id="id" name="id" value="${module.id}">

                            <div class="form-group">
                                <label class="col-sm-3 control-label">用户信息：</label>

                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">姓名：</label>
                                <div class="col-sm-4">
                                    ${order.userName}
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label" >手机号码：</label>
                                <div class="col-sm-4">
                                    <input id="name" name="name" class="form-control" type="text" value="${module.name}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">启动时间：</label>
                                <div class="col-sm-8">
                                    <input id="startTime" name="startTime" readonly="readonly" class="laydate-icon form-control layer-date" value="${module.startTime}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">期望上线时间：</label>
                                <div class="col-sm-8">
                                    <input id="wishTime" name="wishTime" readonly="readonly" class="laydate-icon form-control layer-date" value="${module.wishTime}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">状态：</label>
                                <div class="col-sm-2">
                                	<select name="status" class="form-control">
                                		<#--<option value="0" <#if module.status == 0>selected="selected"</#if>>正常</option>-->
                                		<#--<option value="1" <#if module.status == 1>selected="selected"</#if>>异常</option>-->
                                        <#--<option value="2" <#if module.status == 2>selected="selected"</#if>>已上线</option>-->
                                        <option value="0" <#if module.status == 0>selected="selected"</#if>>待启动</option>
                                		<option value="1" <#if module.status == 1>selected="selected"</#if>>进行中</option>
                                        <option value="2" <#if module.status == 2>selected="selected"</#if>>暂停</option>
                                        <option value="3" <#if module.status == 3>selected="selected"</#if>>待上线</option>
                                        <option value="4" <#if module.status == 4>selected="selected"</#if>>已上线</option>
                                	</select>
                                </div>
                            </div>
                            <#if !module.id?exists ><#--moduleId不存在, -->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">拥有环节：</label>
                                    <div class="col-sm-4">

                                        <select class="selectpicker" name="haveTacheIds" id = "haveTacheIds" multiple data-live-search="true" data-live-search-placeholder="Search" data-actions-box="true">
                                        <#--<optgroup label="filter1">-->
                                            <#list projectTacheList as u >
                                                <option value="${u.id }"  >${u.name }&nbsp;</option>
                                            </#list>
                                        <#--</optgroup>
                                        <optgroup label="filter2">
                                            <option>option1</option>
                                            <option>option2</option>
                                            <option>option3</option>
                                            <option>option4</option>
                                        </optgroup>
                                        -->
                                        </select>
                                    </div>
                                </div>
                            </#if>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">创建描述：</label>
                                <div class="col-sm-8">
                                    <textarea style="height: 80px;" id="createComment" name="createComment" class="form-control">${module.createComment}</textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">修改描述：</label>
                                <div class="col-sm-8">
                                    <textarea style="height: 80px;" id="updateComment" name="updateComment" class="form-control">${module.updateComment}</textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                    <div class="col-sm-8 col-sm-offset-3">
                                        <@shiro.hasPermission name="plan:module:edit">
                                            <button class="btn btn-primary" type="submit">提交</button>
                                        </@shiro.hasPermission>
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
        laydate({
            elem: '#startTime', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
        });
        laydate({
            elem: '#wishTime', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
        });

	    $("#frm").validate({
    	    rules: {
    	    	name: {
    	        required: true,
    	        minlength: 4,
    	    	maxlength: 20
    	      },
                startTime: {
                required: true,
                minlength: 4,
                maxlength: 20
              },
                wishTime: {
                required: true,
                minlength: 4,
                maxlength: 20
              },
                status: {
    	        required: true
    	      },
                createComment: {
                required: false,
                maxlength: 2000
              },
                updateComment: {
                required: false,
                maxlength: 2000
              }
    	    },
    	    messages: {},
    	    submitHandler:function(form){

                <#if !module.id?exists ><#--moduleId不存在, -->
                    var haveTacheIds = $("#haveTacheIds").val();
                    var flag = (haveTacheIds==null&&);
                    if(flag){
                        layer.msg("必须选择一个以上拥有的环节", {time: 4000},function(){
                            $("#haveStatus").focus();
                        });
                        return;
                    }
                </#if>

    	    	$.ajax({
   	    		   type: "POST",
   	    		   dataType: "json",
   	    		   url: "${ctx!}/plan/module/edit",
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
