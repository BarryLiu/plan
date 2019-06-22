//***************************  表单验证  ***************************
window.onerror = function(){return true;} 
function postcheck(){
	if (document.wfform.name.value==""){
	    alert('请填写姓名！');
		document.wfform.name.focus();
		return false;
	}
	var reg1 = /^[\u4e00-\u9fa5]{2,4}$/;
	if (!reg1.test(document.wfform.name.value)){
	    alert('请填写正确的姓名！');
		document.wfform.name.focus();
		return false;
	}
	if (document.wfform.mob.value==""){
		alert('请填写手机号码！');
		document.wfform.mob.focus();
		return false;
	}	
	var reg2 = /^1[3,4,5,8]\d{9}$/;
	if (!reg2.test(document.wfform.mob.value)){
	    alert('请填写正确的手机号码');
		document.wfform.mob.focus();
		return false;
	}
	if (document.wfform.addess.value==""){
		alert('请填写地址！');
		document.wfform.addess.focus();
		return false;
	}	
	if (document.wfform.code.value == "" || document.wfform.code.value.length < 4){
		alert('请填写验证码！');
		document.wfform.code.focus();
		return false;
	}	
	document.wfform.wfsubmit.disabled=true;
	document.wfform.wfsubmit.value="正在提交，请稍候...";
	return true;	
}

function oprize1(){
	var product = document.wfform.product.value;
	for(var i=0;i<document.wfform.product.length;i++){
		if(document.wfform.product[i].checked==true){
			product = document.wfform.product[i].value;
			break;
		}
	}
	var spri=product.split('|');
	var pri=spri[1];
	if(document.wfform.mun.value=="" || document.wfform.mun.value==0){	
		var tmun=1;
	}
	else{
		var tmun=document.wfform.mun.value;
	}	
	var prit=pri*tmun;
	document.wfform.prize.value=prit;
	document.getElementById("showprit").innerHTML=prit;
	//alert(document.wfform.prize.value);
}
oprize1();

//***************************  新窗口打开  ***************************
function opay(){
	document.getElementById("wfform").target="_self";
}
function opay2(){
    document.getElementById("wfform").target="_blank";
}

//***************************  来源记录  ***************************
var ref = '';
if (document.referrer.length > 0){
	ref = document.referrer;
}
try{
	if (ref.length == 0 && opener.location.href.length > 0){
    ref = opener.location.href;
	}
}
catch (e){}
document.cookie="HTTPREF=" + ref+";path=/";

//sub1.js end