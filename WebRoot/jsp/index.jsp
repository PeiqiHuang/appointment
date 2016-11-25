<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<html>

	<head>
	    <title>Index</title>
	    <sb:head/>
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   	</head>
   	
	<style>
		body {
			padding : 10px
		}
		div {
			margin: 50px;
		}
		
		
	</style>
	
	<body>
		<h3 class="text-center">登陆成功！欢迎您，<s:property value="username" />. <a href="logout">退出</a></h3>
		<!-- <ul>
			<li></li>
			<li></li>
		</ul>	 -->
		<div><a href="time/show"  class="btn btn-success btn-large btn-block">管理时间段</a></div>
		<div><a href="person/manage/show"  class="btn btn-success btn-large btn-block">管理预约</a></div>
	  
	</body>
	
</html>
