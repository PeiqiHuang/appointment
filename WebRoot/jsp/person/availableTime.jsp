<%@ page contentType="text/html; charset=UTF-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
    <title>Availbable Times</title>
	</head>

	<body>
	  <s:form id="form" action="insert">
	  	<s:select key="time.id" list="availableTime" listKey="id" listValue="description" />
	  	<s:submit key="submit" />
	  </s:form>
	</body>
	
	<script src="../js/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
		
		})
	</script>
	
</html>
