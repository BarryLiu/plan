<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>环节列表</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="/favicon.ico">
    <link href="${ctx!}/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx!}/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${ctx!}/assets/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">

    <link href="${ctx!}/assets/css/animate.css" rel="stylesheet">
    <link href="${ctx!}/assets/css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox ">
                    <div class="ibox-title">
                        <h5>环节管理</h5>
                    </div>
                    <div class="ibox-content">
                        <p>
							<#--环节没有添加功能-->
                        	<#--<@shiro.hasPermission name="system:resource:add">
                        		<button class="btn btn-success " type="button" onclick="add();"><i class="fa fa-plus"></i>&nbsp;添加</button>
                        	</@shiro.hasPermission>-->
                        </p>
                        <hr>
                        <div class="row row-lg">
		                    <div class="col-sm-12">
		                        <!-- Example Card View -->
		                        <div class="example-wrap">
		                            <div class="example">
		                            	<table id="table_list"></table>
		                            </div>
		                        </div>
		                        <!-- End Example Card View -->
		                    </div>
	                    </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

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

    <!-- Page-Level Scripts -->
    <script>
		var rootUrl = "${ctx!}/plan/tache";
        $(document).ready(function () {
			//初始化表格,动态从服务器加载数据  
			$("#table_list").bootstrapTable({
			    //使用get请求到服务器获取数据  
			    method: "POST",
			    //必须设置，不然request.getParameter获取不到请求参数
			    contentType: "application/x-www-form-urlencoded",
			    //获取数据的Servlet地址  
			    url: rootUrl+"/list?searchTypeName=${searchTypeName}",
			    //表格显示条纹  
			    striped: true,
			    //启动分页  
			    pagination: true,
			    //每页显示的记录数  
			    pageSize: 12,
			    //当前第几页  
			    pageNumber: 1,
			    //记录数可选列表  
			    pageList: [12, 24, 36, 48],
			    //是否启用查询  
			    search: true,
			    //是否启用详细信息视图
			    detailView:true,
			    detailFormatter:detailFormatter,
			    //表示服务端请求  
			    sidePagination: "server",
			    //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder  
			    //设置为limit可以获取limit, offset, search, sort, order  
			    queryParamsType: "undefined",
			    //json数据解析
			    responseHandler: function(res) {
			        return {
			            "rows": res.content,
			            "total": res.totalElements
			        };
			    },
			    //数据列
			    columns: [/*{
			        title: "ID",
			        field: "id",
			        sortable: true
			    },*/{
                    title: "项目名称",
                    field: "module.project.name"
                },{
			        title: "功能名称",
			        field: "module.name"
			    },{
                        title: "环节",
                        field: "tacheIndex",
                        sortable: true
				},{
					title: "环节名称",
					field: "name",
                    formatter: function (value, row, index) {
                        var operateHtml= '<a style="white-space: nowrap;"  onclick="recordTacheUser(\''+row.id+'\',1)">'+row.name+'</a>';
                        return operateHtml;
                    }
				},{
                    title: "计划时间",
                    field: "planBeginTime",
                    sortable: true,
                    formatter: function (value, row, index) {
                        var planBeginTime =  row.planBeginTime;
                        var planEndTime = row.planEndTime;
                        planBeginTime=planBeginTime==null?'':planBeginTime;
                        planEndTime=planEndTime==null?'':planEndTime;

						return planBeginTime+" / "+planEndTime;
                    }
                },{
                    title: "实际时间",
                    field: "wishTime",
                    sortable: true,
                    formatter: function (value, row, index) {
						var realBeginTime =  row.realBeginTime;
						var realEndTime = row.realEndTime;
                        realBeginTime=realBeginTime==null?'N':realBeginTime;
                        realEndTime=realEndTime==null?'N':realEndTime;
                        return realBeginTime+" / "+realEndTime;
                    }
                } ,{
                    title: "责任人",
                    field: "user_nickName",
                    formatter: function (value, row, index) {
                        //alert("index: " + index + " row:" + row.user);
						var nickName = ' - ';
						if(row.user!=null && row.user != undefined ){
							nickName = row.user.nickName;
						}
						return nickName;
                    }
                },{
			        title: "状态",
			        sortable: true,
			        field: "statusName",
                    formatter: function (value, row, index) {
						//alert( "index: "+index+" row:"+row.createTime);
                    	/*if(value == 0)
                    		return '<span class="label label-info"><!--新创建-->Open</span>';
                    	else if(value == 1)
                    		return '<span class="label label-info"><!--执行中-->Open</span>';
                        else if(value == 2)
                            return '<span class="label label-info"><!--测试中-->Open</span>';
                        else if(value == 3)
                            return '<span class="label label-danger"><!--归档完成-->Close</span>';
                        else
                            return '<span class="label label-danger">未知</span>';*/
                     return '<span class="label label-info"></span>';
                    }
			    }/*,{
			        title: "创建时间",
			        field: "createTime",
			        sortable: true
			    }*/,{
                    title: "备注",
                    field: "createTime",
                    formatter: function (value, row, index) {
						var operateHtml= '<a  style="white-space: nowrap;" onclick="record(\''+row.id+'\',1)">&nbsp;（'+row.openates.length+'）条</a>';
						return operateHtml;
                    }
                },{
			        title: "最后更新时间",
			        field: "updateTime",
			        sortable: true
			    }/*,{
                    title: "归档时间",
                    field: "archiveTime",
                    sortable: true
                }*/,{
			        title: "操作",
			        field: "empty",
                    formatter: function (value, row, index) {
                    	var operateHtml = '<@shiro.hasPermission name="plan:tache:add"><button class="btn btn-primary btn-xs" type="button" onclick="edit(\''+row.id+'\')">修改</button> &nbsp;</@shiro.hasPermission>';
                        operateHtml = operateHtml + '<button class="btn btn-info btn-xs" type="button" onclick="record(\''+row.id+'\',0)">&nbsp;记录</button>&nbsp;';
                    	operateHtml = operateHtml + '<@shiro.hasPermission name="plan:tache:deleteBatch"><button class="btn btn-danger btn-xs" type="button" onclick="del(\''+row.id+'\')">删除</button></@shiro.hasPermission>';

                        return operateHtml;
                    }
			    }]
			});
        });
        
        function edit(id){
        	layer.open({
        	      type: 2,
        	      title: '环节修改',
        	      shadeClose: true,
        	      shade: false,
//        	      area: ['1093px', '800px'],
                  area: ['90%', '80%'],
        	      content: rootUrl+'/edit/' + id,
        	      end: function(index){
        	    	  $('#table_list').bootstrapTable("refresh");
       	    	  }
        	    });
        }
        function record(id,type){
            layer.open({
                type: 2,
                title: '环节修改',
                shadeClose: true,
                shade: false,
//                area: ['1293px', '800px'],
                area: ['70%', '80%'],
                content: rootUrl+'/recordlist?tacheId=' + id+'&type='+type,
                end: function(index){
                    $('#table_list').bootstrapTable("refresh");
                }
            });
        }
        function recordTacheUser(id,type){
            layer.open({
                type: 2,
                title: '环节操作记录',
                shadeClose: true,
                shade: false,
//                area: ['1293px', '800px'],
                area: ['70%', '80%'],
                content: rootUrl+'/recordTacheUserlist?tacheId=' + id+'&type='+type,
                end: function(index){
                    $('#table_list').bootstrapTable("refresh");
                }
            });
        }
        function add(){
        	layer.open({
        	      type: 2,
        	      title: '环节添加(由自动添加)',
        	      shadeClose: true,
        	      shade: false,
        	      //area: ['893px', '600px'],
                  area: ['70%', '80%'],
        	      content: rootUrl+'/add',
        	      end: function(index){
        	    	  $('#table_list').bootstrapTable("refresh");
       	    	  }
        	    });
        }
        function del(id){
        	layer.confirm('确定删除吗?', {icon: 3, title:'提示'}, function(index){
        		$.ajax({
    	    		   type: "POST",
    	    		   dataType: "json",
    	    		   url: rootUrl+"/delete/" + id,
    	    		   success: function(msg){
	 	   	    			layer.msg(msg.message, {time: 2000},function(){
	 	   	    				$('#table_list').bootstrapTable("refresh");
	 	   	    				layer.close(index);
	 	   					});
    	    		   }
    	    	});
       		});
        }
        
        function detailFormatter(index, row) {
	        var html = [];
	        html.push('<p><b>描述:</b> ' + '这个环节很坑的啊,小心点做,不然打你哦!' + '</p>');
	        return html.join('');
	    }
    </script>

    
    

</body>

</html>
