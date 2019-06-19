//js U方法 待完善
//url='',vars='',suffix=1
function U() {
	var paramsLen = arguments.length;
	var url = '';
	var urlArray = $.trim(arguments[0]).split('index.html');
	switch(url_mod){
		case 0://普通模式
		{
			if(urlArray.length == 1) {
			    url = php_file+'?'+var_module+'='+urlArray[0];
			} else if(urlArray.length == 2) {
			    url = php_file+'?'+var_module+'='+urlArray[0]+'&'+var_controller+'='+urlArray[1];
			} else if(urlArray.length == 3) {
			    url = php_file+'?'+var_module+'='+urlArray[0]+'&'+var_controller+'='+urlArray[1]+'&'+var_action+'='+urlArray[2];
			} else {
			    alert('传入URL路径出错！');
			    return false;
			}
			if(paramsLen >= 2) {
				if(typeof arguments[1]=='string'){
					var tmpArray = $.trim(arguments[1]).split('index.html');
					if(tmpArray.length == 1){
						url += '&' + arguments[1];
					}else{
						for(i = 0; i < tmpArray.length; i++){
							if(tmpArray[i]){
								url += '&' + tmpArray[i];
								i++;
								if(tmpArray[i]){
									url += '=' + tmpArray[i];
								}else{
									url += '=';
								}
							}
						}
					}
				}else{
		            $.each(arguments[1],function(k,v){
		                url += '&'+k+'='+v;
		            });
				}
        	}
		}
			break;
		case 1://PATHINFO模式
		{
			url = php_file + '/' + arguments[0];
			if(paramsLen >= 2) {
				if(typeof arguments[1]=='string'){
					url += '/' + arguments[1]
				}else{
		            $.each(arguments[1],function(k,v){
		                url += '/'+k+'/'+v;
		            });
				}
        	}
        	if(paramsLen < 3 || (paramsLen >= 3 && arguments[2])){
				url +=  '.'+ url_html_suffix;
			}
		}
			break;	
		case 2://REWRITE模式
		{
    		app=php_file.substring(0,php_file.lastIndexOf('index.html')); 
			url = app + '/' + arguments[0];
			if(paramsLen >= 2) {
				if(typeof arguments[1]=='string'){
					url += '/' + arguments[1]
				}else{
		            $.each(arguments[1],function(k,v){
		                url += '/'+k+'/'+v;
		            });
				}
        	}
        	if(paramsLen < 3 || (paramsLen >= 3 && arguments[2])){
				url +=  '.'+ url_html_suffix;
			}
		}
			break;
		default:
			break;
	}
   /* var paramsLen = arguments.length,url='';

    if(paramsLen == 1 && typeof(arguments[0])=='object') {
        url = _ACTION_;
        $.each(arguments[0],function(k,v){
            url += '&'+k+'='+v;
        });
    } else {
        var urlArray = $.trim(arguments[0]).split('/');
        if(urlArray.length == 1) {
            url = _URL_+'&'+var_action+'='+urlArray[0];
        } else if(urlArray.length == 2) {
            url = _GROUP_+'&'+var_module+'='+urlArray[0]+'&'+var_action+'='+urlArray[1];
        } else if(urlArray.length == 3) {
            url = _APP_+'?'+_VAR_GROUP_+'='+urlArray[0]+'&'+var_module+'='+urlArray[1]+'&'+var_action+'='+urlArray[2];
        } else {
            alert('传入URL路径出错！');
            return false;
        }
        if(paramsLen == 2) {
            $.each(arguments[1],function(k,v){
                url += '&'+k+'='+v;
            });
        }
    }*/
    if(!url){
		alert('url路径解析出错。');
	}
    return url;
}