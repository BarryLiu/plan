
$(document).ready(function () {

   	$(".chpr_list a").hover(function(){
		$(this).css("z-index","9999")
		$(this).children(".layer_r").show();
		},function(){
		$(this).css("z-index","1")
		$(this).children(".layer_r").hide();
	})

	//评价打星
	var starLi = $("#star_c li");
	starLi.mouseover(function() {
		var t= $(this);
		t.addClass("on");
		t.prevAll().addClass("on");
		t.nextAll().removeClass("on");
	});
	

	
	//促销优惠列表
	$(".yhact_box li").hover(function() {        
			$(this).addClass("cur");
			},function(){
			$(this).removeClass("cur");
	});
	$(".yh_act_list").each(function() {
		var i_Btn = $(this).find(".yhact_tit i");
		var i_box = $(this).find(".yhact_box");
			i_Btn.bind("click",function(){
				if(i_box.is(":visible")){
					$(this).empty().html("+")
					i_box.addClass("dn");
				}else{
					i_box.removeClass("dn");
					$(this).empty().html("-")
				}
			})
			if(i_box.find("li").length<6){
				i_box.children(".pn_btn").hide();
			}
	});	
 

   $(".paykind_box01 input").each(function() {
		$(this).focusin(function(){	
			var t = $(this);
            if(t.val()=='请输入收货人姓名'||t.val()=='请填写详细地址'||t.val()=='手机号或区号-固话')
            {                
				t.val('');
            }
            $this.removeClass("col_c");
            })           
            
		})
	
	$("#footPhone").focusin(function() {
		var t=$(this);
		var temp= t.val();
		if(t.val()=="输入您的电话号码"){
			t.val("");
		}else(t.val(temp))
	}).blur(function(){
		if($(this).val()==""){
			$(this).val("输入您的电话号码");
		}
	});


});

