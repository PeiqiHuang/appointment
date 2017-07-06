<%@ page contentType="text/html; charset=UTF-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<html>
	<head>
	    <title>医生排班</title>
	    <script src="../js/jquery.min.js"></script>
	    <sb:head/>
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	</head>
	
	<style>
		body {
			padding : 10px
		}
		input, select {
			width:100%;
		}
		.control-label {
			font-weight:bold
		}
		
	</style>

	<body>
		<div class="well text-error text-center">
			<h4>今日坐诊</h4>
			<s:property value="doctors"/>
		</div>
		<div class="well text-info text-center">
			<h4>日常排班</h4>
			<h5>逢周一休息</h5>
			<h5>谢永年</h5>
			周二至周四，周六至周日 <br>8:00-12:00，14:00-17:30
			<h5>谢朴可</h5>
			周二至周四，周六至周日  <br>8:30-12:00，14:30-17:30
			<h5>张美行</h5>
			周五  <br>8:00-12:00，14:00-17:30
		</div>
			  
	</body>
	
	
</html>
