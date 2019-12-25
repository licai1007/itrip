<html>
	<head>
		<title>测试freemarker</title>
	</head>
	<body>
		<table>
			<tr>
				<td>学号</td>
				<td>姓名</td>
				<td>年龄</td>
			</tr>
			<#list mylist as stu>
				<tr>
					<td>${stu.stuid}</td>
					<td>${stu.name}</td>
					<td>${stu.age}</td>
				</tr>
			</#list>
		</table>
		${mydate?string("yyyy年MM月dd日")}
		<br/>
		${hello!"默认值"}
		<br/>
		<#if hello??>
				hello是有值的
			<#else>
				hello是null
		</#if>
		<br/>
		<#include "hello.ftl">
	</body>
</html>