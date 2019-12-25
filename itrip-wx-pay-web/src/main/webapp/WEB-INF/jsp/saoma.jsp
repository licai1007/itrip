<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath %>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>爱旅行支付中心</title>
</head>
<body bgcolor="#BFEAC5">
<div style="border:1px solid #aaa;position:absolute;left:50%;top:50%;width:350px;height:390px;margin-left:-175px;margin-top:-195px;">
	<div style="padding:5px;border-bottom:1px solid #aaa;">
		订单号：${ oid }<br/>
		总金额：<font color="red">${totalprice}元</font>
	</div>
	<div id="qrcode" style="margin:5 8 8;"></div>
</div>
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<!-- 该js是微信官方所提供的一个用来生成二维码的一个js文件 -->
<script type="text/javascript" src="js/qrcode.js"></script>
<script type="text/javascript">
	new QRCode('qrcode', {
		text: '${code_url}',//就是微信支付系统返回的一个二维码生成的url
		width: 333,
		height: 333
	});
	
	// 递归发送ajax请求，查询订单的状态是否修改，后台需要根据订单id查询数据库中订单状态，这种方式称为“ajax轮询”
	(function polling() {
		$.ajax({
			url: "wx/checkStatus?oid=${oid}",
			cache: false, // 禁用缓存
			success: function(data) {
				//alert("data的值：" + data);
				if ( data == "true" ) {
					// 替换地址栏，不产生后退历史
					location.replace("http://localhost:8088/wx/paysuccess");
				}
				// 后台超时
				else {
					polling(); // 递归调用
				}
			},
			// 发生错误的时候执行的方法
			error: function() {
				polling(); // 递归调用
			}
		});
	})();
</script>
</body>
</html>