<%@ page contentType="text/html; charset=UTF-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
    <title>Insert A Time</title>
	</head>

	<body>
	  <s:form action="submit">
	 	<s:hidden name="id" />
	  	<s:textfield key="time" />
	  	<s:textfield key="description" />
	  	<s:textfield key="count" />
	  	<s:submit key="submit" />
	  </s:form>
	</body>
	
	
</html>
