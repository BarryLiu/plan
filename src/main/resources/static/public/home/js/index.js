/* Fucus*/
$(function() {
	 $("#star li").hover(function(){
        var sid = this.id;
        $("#star li").each(function(){
            $(this).removeClass("on");
            if(this.id <= sid){
                $(this).addClass("on");
            }
        })
       $("#star_show").html(sid + " 星评分");  
       $("#star_value").val(sid);
    },function(){});
});

//评论
function remark(frm){
	if (Utils.isEmpty(frm.elements['content'].value))
	{
		alert('请输入评论内容。');
		return false;
	}
	
	if (Utils.isEmpty(frm.elements['username'].value))
	{
		frm.elements['username'].value = "匿名";
	}
	
	
	
	$.ajax({
    url:U('Home/Index/remark'),
    data:{
    	"info":{
    	"typeid":1,
    	 "status":"1",
    	 "catid":"8",
    	 "title":"评论-"+frm.elements['content'].value.substr(0,16),
    	"content": frm.elements['content'].value,
    	"description": frm.elements['star_value'].value + "星",
    	"nickname": frm.elements['username'].value,
    	},
    	"modelid":20,
    	},
    type:'post',
    success:function(res){
    	if (!res)
    	{			
    		res.error = 1;
    		alert('连接失败，请联系客服！');
    		return false;
    	}
    		
    	if (res.message)
    	{
    	    alert(res.message);
    	}
    	if (res.error == 1)
    	{
    		return false;
    	}
    	if(res.status) 
    	{	   
    		frm.elements['content'].value = "";
    		frm.elements['star_value'].value = 5;
    		frm.elements['username'].value = "";
    		alert("提交成功。");
    		//$("#order_form").html(res.content);
        	/*$("#order_form").html(res.content);//更新购物车列表
            GA_Submit_order(res.order_id, res.order_amount);*/
    	}
    	else
    	{ alert(res); }	
    }
  });
}


//疑问
function ask(frm){
	if (Utils.isEmpty(frm.elements['content'].value))
	{
		alert('请输入问题。');
		return false;
	}
	
	if (Utils.isEmpty(frm.elements['contact'].value))
	{
		alert('请输入联系电话。');
		return false;
	}
	
	
	if (Utils.isEmpty(frm.elements['username'].value))
	{
		frm.elements['username'].value = "匿名";
	}
	$.ajax({
    url:U('Home/Index/ask'),
    data:{
    	"info":{
    	"typeid":1,
    	 "status":"1",
    	 "catid":"9",
    	 "title":"问题-"+frm.elements['content'].value.substr(0,16),
    	"content": frm.elements['content'].value,
    	"contact": frm.elements['contact'].value,
    	"nickname": frm.elements['username'].value,
    	},
    	"modelid":20,
    	},
    type:'post',
    success:function(res){
    	if (!res)
    	{			
    		res.error = 1;
    		alert('连接失败，请联系客服！');
    		return false;
    	}
    		
    	if (res.message)
    	{
    	    alert(res.message);
    	}
    	if (res.error == 1)
    	{
    		return false;
    	}
    	if(res.status) 
    	{	   
    		frm.elements['content'].value = "";
    		frm.elements['contact'].value = "";
    		frm.elements['username'].value = "";
    		alert("提交成功。");
    		//$("#order_form").html(res.content);
        	/*$("#order_form").html(res.content);//更新购物车列表
            GA_Submit_order(res.order_id, res.order_amount);*/
    	}
    	else
    	{ alert(res); }	
    }
  });
}

function getremarks(page){
	 $.get(U('Home/Index/getremarks','p/'+page), function(res){$('#ECS_COMMENT').html(res);});
}

function getasks(page){
	 $.get(U('Home/Index/getasks','p/'+page), function(res){$('#ASK').html(res);});
}