var itrip = ITRIP = {
	checkLogin : function(){
		//1.从cookie中获取到值
		var _ticket = $.cookie("ITRIP_COOKIE");
		if(!_ticket){
			return ;
		}
		$.ajax({
			url : "http://localhost:8084/user/token/" + _ticket,
			dataType : "jsonp",
			type : "GET",
			success : function(data){
				//alert(JSON.stringify(data));
				if(data.status == 200){
					var username = data.data.username;
					var html = username + "，欢迎来到爱旅行！<a href=\"http://localhost:8084/user/logout/"+_ticket+"\" class=\"link-logout\">[退出]</a>";
					$("#loginbar").html(html);
				}
			}
		});
	}
}

$(function(){
	// 查看是否已经登录，如果已经登录查询登录信息
	itrip.checkLogin();
});