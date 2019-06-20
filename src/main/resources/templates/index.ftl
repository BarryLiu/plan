<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 

"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<head>
<!--meta-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--css-->
<link rel="stylesheet" type="text/css" href="public/home/css/common.css" />
<link rel="stylesheet" type="text/css" href="public/home/css/index.css" />
<link rel="stylesheet" type="text/css" href="public/home/css/order_style.css" />
<link rel="stylesheet" type="text/css" href="public/home/css/slider.css" >
<!--javascript-->
<script type="text/javascript" src="/./Application/Runtime/Cache/config.js"></script>
<script type="text/javascript" src="public/home/js/base.js"></script>
<script type="text/javascript" src="public/home/js/jquery.js"></script>
<script type="text/javascript" src="public/home/js/common.js"></script>
<script type="text/javascript" src="public/home/js/index.js"></script>
<script type="text/javascript" src="public/home/js/jquery.lazyload.js"></script>
<script type="text/javascript" src="public/home/js/jquery.slider.pack.js"></script>
<script type="text/javascript" src="public/home/js/jquery.easing.js"></script>
<script type="text/javascript">
$(function() {
	$('#cycle-prev, #cycle-next').css({opacity: '0'});
	$('.cycleslider-wrap').hover(function(){
		$('#cycle-prev',this).stop().animate({left: '-31', opacity: 

'1'},200,'easeOutCubic');
		$('#cycle-next',this).stop().animate({right: '-31', opacity: 

'1'},200,'easeOutCubic');	 
	}, function() {
		$('#cycle-prev',this).stop().animate({left: '-50', opacity: 

'0'},400,'easeInCubic');
		$('#cycle-next',this).stop().animate({right: '-50', opacity: 

'0'},400,'easeInCubic');		
	});
	
	$(".cycleslider-wrap").fadeIn(1000);
	$(".slider-wrap .loader").css({display: "none"});
	$("#slider").cycle({
		fx:  "scrollLeft",
		speed:  "800", 
		timeout: "5000",
		easing:  "linear",
		next:  "#cycle-next",
		prev:  "#cycle-prev",
		pager:  "#cycle-nav"
	});

});
</script>
<script  type="text/javascript">
	$(function(){
	$("img.lazy").lazyload({
		placeholder : "public/home/images/images/grey.png",
		effect : "fadeIn",
		threshold : 600
  });
});
</script>
<script type="text/javascript" src="public/home/js/test.js"></script>

<script src="public/home/js//order.js" type="text/javascript"></script>
<script src="public/home/js//utils.js" type="text/javascript"></script>
<link href="/favicon.ico" type="image/x-icon" rel="shortcut icon" />
</head>


<body>
<div id="header" align="center">
<img src="style/logo.jpg">
</div>

<!--广告图片-->
<div class="banner" align="center">
<img src="style/banner.jpg">
</div>

<div class="0" align="center">
		<img src="style/0.jpg">
</div>

<!--广告图片2-->
<div class="1" align="center">
		<img src="style/1.jpg">
</div>
<div class="1-1" align="center">
		<img src="style/1-1.jpg">
</div>

<!--广告图片2-->
<div class="2" align="center">
		<img src="style/2.jpg">
</div>
<div class="2-1" align="center">
		<img src="style/2-1.jpg">
</div>

<div class="2-7" align="center">
		<video controls="controls" poster="style/014.jpg" src="http://brainempire.com/014.mp4" width="1000"></video><br><br>
</div>

<div class="2-2" align="center">
		<img src="style/2-2.jpg">
</div>
<div class="2-3" align="center">
		<img src="style/2-3.jpg">
</div>
<div class="2-4" align="center">
		<img src="style/2-4.jpg">
</div>
<div class="2-5" align="center">
		<img src="style/2-5.jpg">
</div>
<div class="2-6" align="center">
		<img src="style/2-6.jpg">
</div>







<!--广告图片2-->
<div class="3" align="center">
		<img src="style/3.jpg">
</div>
<div class="3-1" align="center">
		<img src="style/3-1.jpg">
</div>



<!--广告图片2-->
<div class="4" align="center">
		<img src="style/4.jpg">
</div>
<div class="4-1" align="center">
		<img src="style/4-1.jpg">
</div>


<!--广告图片2-->
<div class="5" align="center">
		<img src="style/5.jpg">
</div>


	<div class="conten2">
		<div id="order" class="center">
			
			<div id="order_form" class="on_order">
				 <h4><span>进货数量：</span></h4>
<form action="order/order.asp" method="post" name="theForm" id="theForm"  target="_blank" >

<!--隐藏参数-->
<input type="hidden" value="201" name="sp_id"></input>
<input type="hidden"  name="prurl"></input>
<input type="hidden" name="spm"></input>
<!--所选套餐-->
<input id="jg" type="hidden" value="" name="jg"></input>
<input id="fc" type="hidden" value="" name="fc"></input>
<input id="suk" type="hidden" value="" name="suk"></input>
<!--推荐人-->
<input type="hidden" value="" name="tjr"></input>
<input type="hidden" value="" name="shangjia"></input>
<input type="hidden" value="201-" name="urlid"></input>
<!--产品列表-->
<div class="chpr_list">
    <dl class="clearfix">
        <dd class="sku_dd">
        <a  data-jg="490" data-suk="零售客户 490元（1盒装）" data-fc="490" >
                <input class="i_check" type="checkbox">
				<em class="f_cur" style=""><img alt="" src="public/home/images/cur_pic03.gif"></em>
				<em class="d_cur" style=""><img alt="" src="public/home/images/cur_pic02.gif"></em>
                <span><img src="style/13.jpg"  height="60" width="60"></span>
                <span>
                    <em style="font-weight:bold;font-size:14px;">零售客户 490元（1盒装）</em><br />
                    S1特级大燕盏，一盏一盒，5.8~10克/盒<br />
					印尼014厂进口，每盏单独防伪溯源码<br />
                    <em style="color:#039221;font-size:14px;font-weight:bold;padding:0 10px;">490元</em>
                    <br>
                </span>
				
                <div class="layer_r">
                    <div><img src="public/home/images/layer_pic01.png" alt=""></div>
                    <div class="layer_r_box">
                        <img  original="style/13.jpg" alt="" height="240" width="240"><br /><br /><br />
                        <h5>零售客户 490元（1盒装）</h5><br /><br />
                       
                        <div><strong>￥490</strong></div>
						 <!--<h6>包含：1套</h6>-->
						
                    </div>
                    <div style="margin-left:11px;"><img src="public/home/images/layer_pic03.png" alt=""></div>
                </div>
            </a>
			
        <a  data-jg="680" data-suk="天使代理 680元（2盒装）" data-fc="680" >
                <input class="i_check" type="checkbox">
				<em class="f_cur" style=""><img alt="" src="public/home/images/cur_pic03.gif"></em>
				<em class="d_cur" style=""><img alt="" src="public/home/images/cur_pic02.gif"></em>
                <span><img src="style/13.jpg"  height="60" width="60"></span>
                <span>
                    <em style="font-weight:bold;font-size:14px;">天使代理 680元（2盒装）</em><br />
					S1特级大燕盏，一盏一盒，5.8~10克/盒<br />
					印尼014厂进口，每盏单独防伪溯源码<br />
                    <em style="color:#039221;font-size:14px;font-weight:bold;padding:0 10px;">680元</em>
                    <br>
                </span>
				
                <div class="layer_r">
                    <div><img src="public/home/images/layer_pic01.png" alt=""></div>
                    <div class="layer_r_box">
                        <img  original="style/13.jpg" alt="" height="240" width="240"><br /><br /><br />
                        <h5>天使代理 680元（2盒装）</h5><br /><br />
                       
                        <div><strong>￥680</strong></div>
						 <!--<h6>包含：5套</h6>-->
						
                    </div>
                    <div style="margin-left:11px;"><img src="public/home/images/layer_pic03.png" alt=""></div>
                </div>
            </a>

        <a  data-jg="3100" data-suk="特约代理 3100元（10盒装）" data-fc="2900" >
                <input class="i_check" type="checkbox">
				<em class="f_cur" style=""><img alt="" src="public/home/images/cur_pic03.gif"></em>
				<em class="d_cur" style=""><img alt="" src="public/home/images/cur_pic02.gif"></em>
                <span><img src="style/13.jpg"  height="60" width="60"></span>
                <span>
                    <em style="font-weight:bold;font-size:14px;">特约代理 3100元（10盒装）</em><br />
					S1特级大燕盏，一盏一盒，5.8~10克/盒<br />
					印尼014厂进口，每盏单独防伪溯源码<br />
                    <em style="color:#039221;font-size:14px;font-weight:bold;padding:0 10px;">3100元</em>
                    <br>
                </span>
				
                <div class="layer_r">
                    <div><img src="public/home/images/layer_pic01.png" alt=""></div>
                    <div class="layer_r_box">
                        <img  original="style/13.jpg" alt="" height="240" width="240"><br /><br /><br />
                        <h5>特约代理 3100元（10盒装）</h5><br /><br />
                       
                        <div><strong>￥3100</strong></div>
						 <!--<h6>包含：10盒</h6>-->
						
                    </div>
                    <div style="margin-left:11px;"><img src="public/home/images/layer_pic03.png" alt=""></div>
                </div>
            </a>



        </dd>
    </dl>  
    <input id="item_num" type="hidden" value="0" name="num">
	<div id="calculate_price" > <b style="color:#FF0000;">商品总价：</b><span id="item_price">￥0</span>元</div>
</div>
<script type="text/javascript">
	$(".sku_dd a").bind("click",function(){ 
			var o = $(this);
			if(!o.hasClass("cur")){
				$(".cur").removeClass("cur");
				o.addClass("cur");
				/*更新对应的选中尺码的数据*/
				$("#item_price").html("￥"+ o.attr("data-jg"));
				$("#item_num").val(o.attr("data-jg"));
				$("#suk").val(o.attr("data-suk"));
				$("#fc").val(o.attr("data-fc"));
				$("#jg").val(o.attr("data-jg"));
			}
		})
</script>
<div class="car_list"></div>
<!--<h4 id="fav_title"><span>优惠活动：</span></h4>-->
<div class="w924" style="margin-top:15px;">
    <div class="yh_act favourable_list"></div>							

		
    <div class="paykind">
    <div class="paykind">
        <h6>收货信息</h6>
        <div class="paykind_box01" id="checkout_form_box">
            <ul>
                <li>
                    <label>收货姓名：</label>
                    <input class="col_c" name="name" id="name" onblur="consigneeSet(this.value)" size="30">
                    <span id="consignee_notice"></span>
                </li>
            <script type="text/javascript" src="js/bddq.js" charset="gb2312"></script>
                <li>
                <label>收货地区：</label>
				<select name="bdprovince" class="bddqxl"></select>省/直辖市
				
				<li>
                <label></label>
				<select name="bdcity" class="bddqxl"></select>市/市级县
				</li>
						
				<li>
                <label></label>
				<select name="bdarea" class="bddqxl"></select>区/县
				</li>
				<span id="flow_notice"></span>
				<script type="text/javascript" src="js/bdbd.js"></script>
                </li>

                <li>
                    <label>详细地址：</label>
                    <input class="col_c" style="width:310px" name="address" id="xdz" onblur="addressSet(this.value)" size="40" maxlength="120" type="text">
                    <span id="address_notice"></span>
                </li>
                <li>
                    <label>联系电话：</label>
                    <input class="col_c"  name="mob" id="tel" maxlength="13"  size="30">
                    <span id="mobile_notice"></span>
                </li>
                
            </ul>
        </div>
        <h6>支付方式</h6>
        <div class="paykind_box02">
            <!--<label>
                <input onclick="javascript:select_pay(4);" value="4" name="pay_id" 

id="payid_4" checked="checked" type="radio">支付宝支付<img 

src="public/home/images/index/pay_pic01.jpg">
            </label><br>-->
            <!--<label>
            <input type="radio" onclick="javascript:select_pay(17);" value="17" 

name="pay_id" id="payid_17"/>网银支付（折上再享95折）<img 

src="public/home/images/index/pay_pic02.jpg">
            </label><br/>
            <label>
            <input type="radio" onclick="javascript:select_pay(8);" value="8" name="pay_id" 

id="payid_8"/>快钱支付（折上再享95折）<img src="public/home/images/index/pay_pic04.png">
            </label><br/>-->
            <label><input onclick="javascript:select_pay(1);" value="0" name="zhifubao" 

type="radio"  checked="checked">货到付款</label>

            <script type="text/javascript" src="get_prevent_refresh867b.html?

t=1441175749"></script>
            <span id="pay_id_notice"></span>
        </div>
	      </div>


    <div class="payform_btn text_center">
	<br />
	<input onclick="this.submit()" src="public/home/images/click_btn04.jpg" type="image"></div>
</div>
</form>
<script language="JavaScript">
    $(".chpr_list a").hover(function(){
        var thisImg = $(this).children(".layer_r").children(".layer_r_box").find("img");
        thisImg.attr("src",thisImg.attr("original"));
    },function(){
    })
</script>		
			</div>
			
		</div>
	</div>




</div>



<script language="javascript">
	//咨询
    function zixun(){
    	alert(1);
    }

</script>
	
<script type="text/javascript">
    $(function (){
        $("#checkout_form_box input").focus(function(){
            $(this).removeClass("col_c");
            $(this).addClass("col_b");
            $(this).addClass("redBd");
        })
        $("#checkout_form_box input").blur(function(){
            $(this).removeClass("redBd");
            if(!$(this).val() || $(this).val() == $(this).attr('dvalue')){
                $(this).removeClass("col_b");
                $(this).addClass("col_c");
                $(this).val($(this).attr('dvalue'));
            }
        })
	    $("#checkout_form_box input").each(function(){
	        if($(this).val() && $(this).val() != $(this).attr('dvalue')){
	            $(this).removeClass("col_c");
	        }
	    });
    });

</script>

<script type="text/javascript" src="https://js.users.51.la/20127933.js"></script>


</body>
</html>