// JavaScript Document
function  mreset(tshow){
if(tshow==1){
   document.getElementById("In_Zc_C_R").innerHTML = "　　<span class=\"Red_02\"></span>请输入您的身高与胸围，计算器会根据中国女性完美胸围给出丰胸建议。您也可以咨询在线客服！";
}else{
   document.getElementById("Home_Cp_R_ContC").innerHTML ="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"Red_02\"></span>请输入您的身高与胸围，计算器会根据中国女性完美胸围给出丰胸建议。您也可以咨询在线客服！";
}
}
function  getwieght(tshow,weight){

//年龄
//var age  = weight.elements['age'].value;
//胸围
var bust  = weight.elements['bust'].value;
//身高
var top  = weight.elements['top'].value;



/*if(age=='')
{
    check="请填写您的年龄";
	showjieguo(tshow,"<span class=\"Red_02\" style=\"padding-left:25px;\">小贴士：</span><span style=\"color:#333333;\">"+check+"</span>");
	return false;
}*/

if(bust=='')
{
    check="请填写您的胸围";
	showjieguo(tshow,"<div class=\"rult\"><p style=\"color:red;\">"+check+"</p></div><div class=\"clear\"></div><div class=\"quick_btn\"><b>在线快速咨询美胸导师</b>定制专属自己的全胸美塑方案</div>");
	return false;
}
if(top=='')
{
    check="请填写您的身高";
	showjieguo(tshow,"<div class=\"rult\"><p style=\"color:red;\">"+check+"</p></div><div class=\"clear\"></div><div class=\"quick_btn\"><b>在线快速咨询美胸导师</b>定制专属自己的全胸美塑方案</div>");
	return false;
}

var bstate = document.getElementsByName('bstate');
var bstate_v = new Array();
for (var i = 0; i < bstate.length; i++) {
	if(bstate[i].checked){
		bstate_v.push(bstate[i].value);
	}
};

bstate_v = bstate_v.join('');

var patrn = '';   
var patrn1 = '';
patrn = /[0-9]{2,3}(\.\d{1})?$/;
patrn1 = /[0-9]{2,3}$/;
if(!patrn.exec(bust) && !patrn1.exec(bust))
{
    check="请按照正确的格式输入您的胸围，例如 80.5 ";
	showjieguo(tshow,"<div class=\"ctbg19_l fl\"><p style=\"color:red;\">"+check+"</p></div><div class=\"clear\"></div><div class=\"quick_btn\"><b>在线快速咨询美胸导师</b>定制专属自己的全胸美塑方案</div>");
	return false;
}else{
	if(bust < 65 || bust > 120){
		check="胸围的计算适用范围是：65cm到120cm";
		showjieguo(tshow,"<div class=\"ctbg19_l fl\"><p style=\"color:red;\">"+check+"</p></div><div class=\"clear\"></div><div class=\"quick_btn\"><b>在线快速咨询美胸导师</b>定制专属自己的全胸美塑方案</div>");
		return false;
	}
}
patrn = /[0-9]{3}$/;
if(!patrn.exec(top))
{
    check="请按照正确的格式输入您的身高，例如 168 ";
	showjieguo(tshow,"<div class=\"ctbg19_l fl\"><p style=\"color:red;\">"+check+"</p></div><div class=\"clear\"></div><div class=\"quick_btn\"><b>在线快速咨询美胸导师</b>定制专属自己的全胸美塑方案</div>");
	return false;
}else{
	if(top < 146 || top > 192){
		check="身高的计算适用范围是：146cm到192cm";
		showjieguo(tshow,"<div class=\"ctbg19_l fl\"><p style=\"color:red;\">"+check+"</p></div><div class=\"clear\"></div><div class=\"quick_btn\"><b>在线快速咨询美胸导师</b>定制专属自己的全胸美塑方案</div>");
		return false;
	}
}

check='';

stabust = Math.round(parseFloat(bust/top)*100)/100;

var obaType;
if(stabust <= 0.49){obaType = '瘦小族（A Cup）';}
else if(0.5<= stabust && stabust <= 0.53){obaType = '普通族（B Cup）';}
else if(0.54 <= stabust && stabust <= 0.55){obaType = '丰满族（C Cup）';}
else if(stabust > 0.55){obaType = '劲爆族（D Cup）';}

var recommend = '';

if(bstate_v.indexOf('a') > -1 || stabust <= 0.49){
	recommend = "<p>推荐您使用<a href=\"#Map6\"><span>升杯体验装(1盒)</span></a>胸部明显增大，乳头上翘，罩杯升级。</p>";
}

if(bstate_v.indexOf('b') > -1 || (0.5<= stabust && stabust <= 0.53)){
	recommend = "<p>推荐您使用<a href=\"#Map5\"><span>娇挺少女装(2盒)</span></a>胸围高耸上翘，乳沟深邃，肌肤粉嫩细腻。</p>";
}

if(bstate_v.indexOf('c') > -1 || (0.54 <= stabust && stabust <= 0.55)){
	recommend = "<p>推荐您使用<a href=\"#Map7\"><span>深V诱惑装(3盒)</span></a>胸围增大到最满意的程序，拥有明星版挺翘美胸。</p>";
}

if(bstate_v.indexOf('d') > -1 || bstate_v.length > 5 || stabust > 0.55){
	recommend = "<p>推荐您使用<a href=\"#Map7\"><span>深V诱惑装(3盒)</span></a>双峰浑圆挺拔，乳晕粉嫩，乳头上翘，效果永不反弹。</p>";
}

check="<div class=\"rult\"><p>您的测试结果是：<span>"+stabust+"</span></p><p>胸型属于：<span>"+ obaType +"</span></p></div>"+
	    "<div class=\"jianyi\">"+ recommend +"</div>"; 

if(check==''){
    check="您输入的数值超过正常范围，请重新输入。";
	showjieguo(tshow,"<div class=\"rult\"><p style=\"color:red;\">"+check+"</p></div><div class=\"jianyi\"><a href=\"javascript:;\" onclick=\"zixun();\"><p>在线快速咨询美胸导师定制专属自己的全胸美塑方案</p></a></div>");
	return false;	
}
   
   showjieguo(tshow,check);
   goto_google(top,bust,stabust,age);
return false;	
			
}


/*输出函数*/
function showjieguo(tshow,str){	
	if(tshow==0){
	   document.getElementById("In_Zc_C_R").innerHTML=str;
	}else{
	   document.getElementById("Home_Cp_R_ContC").innerHTML =str;
	}
}


// google 统计获取数据
function goto_google(top,bust,stabust,age)
{ 

	// google 统计参数转换
	
	//胸围的计算适用范围是：65cm到120cm    65	68	71	74	77	80	83	86	89	92	95	98	101	104	107	110	113	116	120
	if(bust>=65 && bust <68){google_bust = 'bust_65-68' ;}
	else if(bust>=68 && bust <71){google_bust = 'bust_68-71' ;}
	else if(bust>=71 && bust <74){google_bust = 'bust_71-74' ;}
	else if(bust>=74 && bust <77){google_bust = 'bust_74-77' ;}
	else if(bust>=77 && bust <80){google_bust = 'bust_77-80' ;}
	else if(bust>=80 && bust <83){google_bust = 'bust_80-83' ;}
	else if(bust>=83 && bust <86){google_bust = 'bust_83-86' ;}
	else if(bust>=86 && bust <89){google_bust = 'bust_86-89' ;}
	else if(bust>=89 && bust <92){google_bust = 'bust_89-92' ;}
	else if(bust>=92 && bust <95){google_bust = 'bust_92-95' ;}
	else if(bust>=95 && bust <98){google_bust = 'bust_95-98' ;}
	else if(bust>=98 && bust <101){google_bust = 'bust_98-101' ;}
	else if(bust>=101 && bust <104){google_bust = 'bust_101-104' ;}
	else if(bust>=104 && bust <107){google_bust = 'bust_104-107' ;}
	else if(bust>=104 && bust <107){google_bust = 'bust_104-107' ;}
	else if(bust>=107 && bust <110){google_bust = 'bust_107-110' ;}
	else if(bust>=110 && bust <113){google_bust = 'bust_110-113' ;}
	else if(bust>=113 && bust <116){google_bust = 'bust_113-116' ;}
	else if(bust>=116 && bust <=120){google_bust = 'bust_116-120' ;}
	else {google_bust = 'no choose';}
	
	
	//身高的计算适用范围是：146cm到192cm    146	149	152	155	158	161	164	167	170	173	176	179	182	185	188	192
	if(top>=146 && top <149){google_top = 'top_146-149' ;}
	else if(top>=149 && top <152){google_top = 'top_149-152' ;}
	else if(top>=152 && top <155){google_top = 'top_152-155' ;}
	else if(top>=155 && top <158){google_top = 'top_155-158' ;}
	else if(top>=158 && top <161){google_top = 'top_158-161' ;}
	else if(top>=161 && top <164){google_top = 'top_161-164' ;}
	else if(top>=164 && top <167){google_top = 'top_164-167' ;}
	else if(top>=167 && top <170){google_top = 'top_167-170' ;}
	else if(top>=170 && top <173){google_top = 'top_170-173' ;}
	else if(top>=173 && top <176){google_top = 'top_173-176' ;}
	else if(top>=176 && top <179){google_top = 'top_176-179' ;}
	else if(top>=179 && top <182){google_top = 'top_179-182' ;}
	else if(top>=182 && top <185){google_top = 'top_182-185' ;}
	else if(top>=185 && top <188){google_top = 'top_185-188' ;}
	else if(top>=188 && top <=190){google_top = 'top_188-190' ;}
	else {google_top = 'no choose';}
	
	//胸围的计算适用范围是：65cm到120cm    65	68	71	74	77	80	83	86	89	92	95	98	101	104	107	110	113	116	120
	if(bust>=65 && bust <68){google_bust = 'bust_65-68' ;}
	else if(bust>=68 && bust <71){google_bust = 'bust_68-71' ;}
	else if(bust>=71 && bust <74){google_bust = 'bust_71-74' ;}
	else if(bust>=74 && bust <77){google_bust = 'bust_74-77' ;}
	else if(bust>=77 && bust <80){google_bust = 'bust_77-80' ;}
	else if(bust>=80 && bust <83){google_bust = 'bust_80-83' ;}
	else if(bust>=83 && bust <86){google_bust = 'bust_83-86' ;}
	else if(bust>=86 && bust <89){google_bust = 'bust_86-89' ;}
	else if(bust>=89 && bust <92){google_bust = 'bust_89-92' ;}
	else if(bust>=92 && bust <95){google_bust = 'bust_92-95' ;}
	else if(bust>=95 && bust <98){google_bust = 'bust_95-98' ;}
	else if(bust>=98 && bust <101){google_bust = 'bust_98-101' ;}
	else if(bust>=101 && bust <104){google_bust = 'bust_101-104' ;}
	else if(bust>=104 && bust <107){google_bust = 'bust_104-107' ;}
	else if(bust>=104 && bust <107){google_bust = 'bust_104-107' ;}
	else if(bust>=107 && bust <110){google_bust = 'bust_107-110' ;}
	else if(bust>=110 && bust <113){google_bust = 'bust_110-113' ;}
	else if(bust>=113 && bust <116){google_bust = 'bust_113-116' ;}
	else if(bust>=116 && bust <=120){google_bust = 'bust_116-120' ;}
	else {google_bust = 'no choose';}
	
	//年龄的计算适用范围是：12岁到69岁

	/*if(age>12 && age <69){google_age = age;}
	else {google_age = 'no choose';}*/
	/*
	1 瘦小族：胸围(cm)÷身高(cm)＜0.49 
	2 普通族：0.5≤胸围(cm)÷身高(cm)≤0.53 
	3 丰满族：0.54≤胸围(cm)÷身高(cm)≤0.55 
	4 劲爆族：胸围(cm)÷身高(cm)＞0.55 
	*/
	if(stabust <=0.49) {google_stabust = '属于瘦小族' ;}
	else if(0.5<= stabust && stabust <= 0.53) {google_stabust = '属于普通族' ;}
	else if(0.54 <= stabust && stabust <= 0.55) {google_stabust = '属于丰满族' ;}
	else {google_stabust = '属于劲爆族' ;}
	
/*
	_gaq.push(['_setCustomVar',1,"bust",google_bust,1],
			  ['_setCustomVar',2,"top",google_top,1],
			  ['_setCustomVar',3,"stabust",google_stabust,1],
			  ['_setCustomVar',4,"age",google_age,1],
			  ['_trackPageview', 'virtual/Test']);
*/	
	return false;
			  	  	
}
