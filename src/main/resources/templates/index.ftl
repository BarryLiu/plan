<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>什么是燕窝？</title>
        <!-- <meta name="viewport"content="width=device-width,initial-scale=1.0,maximum-scale=1,user-scalable=no" />-->
		<link rel="shortcut icon" href="images/favicon.ico">
        <meta name="format-detection" content="telephone=no,email=no"/>
		<!-- <link rel="stylesheet" type="text/css"
				media="screen and (max-device-width:640px)"
				href="css/moblie.css" />
		<link rel="stylesheet" type="text/css"
				media="screen and (min-device-width:640px)"
				href="css/style.css" /> -->
		<link rel="stylesheet" type="text/css" href="css/style.css" />	
				
		
		
		<script type="text/javascript" src="${ctx!}/public/home/js/base.js"></script>
		<script type="text/javascript" src="${ctx!}/public/home/js/jquery.js"></script>
		<script type="text/javascript" src="${ctx!}/public/home/js/common.js"></script>
		<script type="text/javascript" src="${ctx!}/public/home/js/index.js"></script>
		<script type="text/javascript" src="${ctx!}/public/home/js/jquery.lazyload.js"></script>
		<script type="text/javascript" src="${ctx!}/public/home/js/jquery.slider.pack.js"></script>
		<script type="text/javascript" src="${ctx!}/public/home/js/jquery.easing.js"></script>
		
    </head>
    <body>
		<div class="img_div">
			<img src="images/yanwo_01.jpg" border="0"/>
			<img src="images/yanwo_02.png" border="0"/>
		</div>
		<div class="zhuti">
			<img src="images/2img.png" border="0"/>
			<img src="images/3img.png" border="0" style="margin-top:30px;"/>
			<img src="images/4img.png" border="0" style="margin-top:30px;"/>
			<video controls="controls" poster="images/video_img.jpg" src="http://brainempire.com/014.mp4" width="950px"></video>
			<img src="images/5img.png" border="0" style="margin-top:30px;"/>
			<img src="images/6img.png" border="0" style="margin-top:30px;"/>
			<img src="images/7img_title.png" border="0" style="margin-top:60px;"/>
		</div>
		
		<div class="zhuti_bd">
			<div class="biaodan">
				<div class="bd_title">
					<img src="images/bd_title_img.png" width="30px" height="auto"/>
					<span>请填写收货人信息</span>
				</div>
				<form onsubmit="return false" id="theForm">
					<div class="page_1">
						<div class="p1">
							<span class="p1_fh">*</span>
							<span class="p1_name">姓名</span><br />
							<input type="text" name="userName" value="" placeholder="" autocomplete="off">
						</div>
						<div class="p1 p2">
							<span class="p1_fh">*</span>
							<span class="p1_name">联系电话</span><br />
							<input type="text" name="phone" value="" autocomplete="off" placeholder="" style="width:120px;">
						</div>
						<div class="p1 p3">
							<span class="p1_fh">*</span>
							<span class="address1">收货地址（请输入详细地址）</span><br />
							<input type="text" name="address1" value="" placeholder="" autocomplete="off" style="width:580px;">
						</div>
					</div>
					
					<div class="page_2">
						<div class="page_2_left">
							<span class="p2_l_1">*</span>
							<span class="p2_l_2">请选择套餐</span><br />
							<select name="productName">
								<option value ="零售客户 490元（1盒装）">零售客户 490元（1盒装）</option>
								<option value ="天使代理 680元（2盒装）">天使代理 680元（2盒装）</option>
								<option value="天使代理 3100元（10盒装）">天使代理 3100元（10盒装）</option>
							</select>
						</div>
						<div class="page_2_right">
							<span class="p2_r_1">*</span>
							<span class="p2_r_2">请选择付款方式</span><br />
							<div class="p2_r_3">
								<label>
									<input name="zhifu" type="radio" value="" checked autocomplete="off"/>货到付款 
								</label> 
								<!-- <label><input name="Fruit" type="radio" value="" />货到付款 </label> -->
							</div>
							 
						</div>
					</div>
						
					<input type="submit" id="submitBtn" value="提交订单" class="btn">
				</form> 
			</div>
			
		</div>
		
		
		<div class="footer">
			<img src="images/footer_img.png" width="100%" height="auto"/>
		</div>
    </body>
    <script type="text/javascript">
      $(function (){
	     //ajax提交form表单的方式
	        $('#submitBtn').click(function() {
	        	//alert($('#theForm').serialize());
	                $.ajax({
	                    type: "POST",
	                    dataType: "json",
	                    url: "${request.contextPath}/order/addForm",
	                    data: $('#theForm').serialize(),
	                    success: function (data) {
	                        if(data.res){
	                        	location.href="${request.contextPath}/chenggong"
	                        }else{
	                        	alert(data.msg);
	                        }
	                    },
	                    error: function(data) {
	                        alert("error:"+data.responseText);
	                    }
	                });
	            }
	
	        );
        });
     </script>
</html>