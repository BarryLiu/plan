var consignee_not_null =  "<img src=\"/public/home/images/cur_pic12.gif\">请填写收货人姓名";
var tel_or_mobile_not_null = "<img src=\"/public/home/images/cur_pic12.gif\">手机和固定电话请至少填写一项";  
var tel_notice = "<img src=\"/public/home/images/cur_pic12.gif\"><img src=\"/public/home/images/cur_pic12.gif\">区号与电话号码须同时填写";
var tel_invaild = "<img src=\"/public/home/images/cur_pic12.gif\">区号与电话号码须使用数字填写";
var mobile_invaild  = "<img src=\"/public/home/images/cur_pic12.gif\">手机或电话不合法";
var country_not_null = "<img src=\"/public/home/images/cur_pic12.gif\">请选择国家";
var province_not_null = "<img src=\"/public/home/images/cur_pic12.gif\">请选择省份/直辖市";
var city_not_null  =  "<img src=\"/public/home/images/cur_pic12.gif\">请选择城市";
var district_not_null = "<img src=\"/public/home/images/cur_pic12.gif\">请选择区域";
var address_not_null = "<img src=\"/public/home/images/cur_pic12.gif\">请填写详细地址";
var pay_not_null = "<img src=\"/public/home/images/cur_pic12.gif\">请选择支付方式";
var bonus_sn_error =  "<img src=\"/public/home/images/cur_pic12.gif\">短信优惠卷号格式不对，请确定后再输入!";  

var shopping_cart = new Array();　



function add_to_car(product_id)
{
    if(!shopping_cart[product_id]){
		shopping_cart[product_id]=1;
	}else{
    	shopping_cart[product_id] = shopping_cart[product_id]+1;
	}
	calculate();
    if ($("#product_"+product_id).is(".cur") == false)
    {
        $("#product_"+product_id).addClass("cur");
    }
    $("#product_"+product_id).removeAttr("onclick").unbind('click').click(function(){del_cart_goods(product_id);}); 
    //$("#product_"+product_id).removeAttr("onclick").click(function(){del_cart_goods(product_id);}); 
}

function del_cart_goods(product_id)
{
	if(shopping_cart[product_id]){
    	shopping_cart[product_id] = shopping_cart[product_id]-1;
    	if(!shopping_cart[product_id]){
	        $("#product_"+product_id).removeClass("cur");
    		$("#product_"+product_id).removeAttr("onclick").unbind('click').click(function(){add_to_car(product_id);}); 
	    }
	}
	calculate();
}

function calculate(){
	var calculate_price=0;
	for (x in shopping_cart)
	{
		calculate_price=calculate_price+($("#product_price_"+x).val()*shopping_cart[x]);
	}
	if(calculate_price==0){
		$('#calculate_price').hide();
	}else{
		$('#calculate_price').show();
	}
	$('#calculate_price span').html(calculate_price);
	return calculate_price;
}

function checkConsignee(frm){
	//前台验证
	var err = false;	
	
	//产品
	if(Utils.isEmpty(frm.elements['jg'].value)){
		alert('请先选择产品。');
		window.location.href="#order"; 
		return false;
	}
	
	//收货人姓名
	if (Utils.isEmpty(frm.elements['name'].value)||frm.elements['name'].value=='请输入收货人姓名')
	{
		document.getElementById('consignee_notice').innerHTML = consignee_not_null;
		err = true;
		return false;
		//    msg.push(consignee_not_null);
	}
	else
	{
		document.getElementById('consignee_notice').innerHTML = "";
	}	

	//地址
	if (document.getElementById('province') && document.getElementById('province').value == -1 && document.getElementById('province').length > 1)
	{
		document.getElementById('flow_notice').innerHTML = province_not_null;
		err = true;
		return false;  
	}

	/*if (frm.elements['city'] && frm.elements['city'].value == -1 && frm.elements['city'].length > 1)
	{
		document.getElementById('flow_notice').innerHTML = city_not_null;
		err = true;
		return false;  
	}
	var district = null;
	if (frm.elements['district'] && frm.elements['district'].value == -1 && frm.elements['district'].length > 1)
	{
		err = true;
		document.getElementById('flow_notice').innerHTML = district_not_null;
		return false;
	}  
	else
	{
		document.getElementById('flow_notice').innerHTML = "";
	}*/
	if (frm.elements['xdz'] && Utils.isEmpty(frm.elements['xdz'].value)||frm.elements['xdz'].value=='请填写详细地址')
	{
		document.getElementById('address_notice').innerHTML = address_not_null;
		err = true;
		return false;  
	}
	else
	{
		document.getElementById('address_notice').innerHTML = "";
	}
	
	//电话联系
	if (frm.elements['tel'].value.length > 0)
	{
	 //var reg = /(^1\d{10}$|^0\d{2,3}(\-)?\d{7,8}$)/;
	var mreg = /^1\d{10}$/;
	var treg = /^0\d{2,3}(\-)\d{7,8}$/;

	if (!mreg.test(frm.elements['tel'].value)&&!treg.test(frm.elements['tel'].value))
	{
	   document.getElementById('mobile_notice').innerHTML = mobile_invaild;
	   err = true;
	   return false;
	}else
	    {
	        document.getElementById('mobile_notice').innerHTML = "";
	    }
	}



	//
	/*var pay_id = $("input[name='pay_id']:checked").val();	
	if (typeof(pay_id) == 'undefined' || pay_id < 1)
	{
	document.getElementById('pay_id_notice').innerHTML = pay_not_null;
	err = true;
	return false;  
	}else{
	  document.getElementById('pay_id_notice').innerHTML = "";
	}*/
	if (err)
	{
		return false;
	}
}