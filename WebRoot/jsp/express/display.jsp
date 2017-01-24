<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<html>

	<head>
    	<title>物流信息</title>
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
		
		table {
			margin-top: 10px;
		}
		h4 {
			margin-top: 25px;
		}
	</style>
	
	<body>
		<!-- <h3 class="text-center">物流信息</h3> -->
   		<table class="table table-striped table-bordered ">
   			<thead>
   				<tr>
   					<th colspan=2>物流信息 - <s:property value="pattern" /></th>
   				</tr>
   			</thead>
   			<tbody>
   				<tr>
   					<td colspan=2><s:property value="express.company" /> <s:property value="express.number" /></td>
   				</tr>
   			</tbody>
   			<tbody>
   				<s:iterator value="trackList" status="L">
   					<tr>
   						<td><s:property value="time" /></td>
   						<td><s:property value="station" /></td>
					</tr>
   				</s:iterator>
   			</tbody>
		</table>
	</body>
	
</html>
