<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>

	<head>
    <title>Index</title>
   	</head>

	<body>
		<h4>登陆成功！欢迎您，<s:property value="username" />. <a href="logout">退出</a></h4>
		<ul>
			<li><a href="time/show">管理时间段</a></li>
			<li><a href="person/manage/show">管理预约</a></li>
		</ul>	
	  
	</body>
	
</html>
