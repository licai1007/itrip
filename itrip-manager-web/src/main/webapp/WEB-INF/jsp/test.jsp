<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath %>">
<title>easyUI入门</title>
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="css/itrip.css" />
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/common.js"></script>
</head>
<body>
	<table class="easyui-datagrid" id="itemList" title="商品列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/item/list',method:'get',pageSize:10">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true,align:'center'"></th>
        	<th data-options="field:'id',width:120,align:'center'">商品ID</th>
            <th data-options="field:'title',width:250,align:'center'">商品标题</th>
            <th data-options="field:'cid',width:60,align:'center'">叶子类目</th>
            <th data-options="field:'sellpoint',width:180,align:'center'">卖点</th>
            <th data-options="field:'price',width:70,align:'center',formatter:itrip.formatPrice">价格</th>
            <th data-options="field:'num',width:70,align:'center'">库存数量</th>
            <th data-options="field:'barcode',width:60,align:'center'">条形码</th>
            <th data-options="field:'status',width:60,align:'center',formatter:itrip.formatItemStatus">状态</th>
            <th data-options="field:'created',width:130,align:'center',formatter:itrip.formatDateTime">创建日期</th>
            <th data-options="field:'updated',width:130,align:'center',formatter:itrip.formatDateTime">更新日期</th>
        </tr>
    </thead>
</table>
</body>
</html>