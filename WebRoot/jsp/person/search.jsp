<%@ page contentType="text/html; charset=UTF-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<html>
	<head>
	    <title>请输入预约电话查询</title>
	    <sb:head/>
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	</head>
	
	<style>
		body {
			padding : 10px
		}
		input, select {
			width:100%
		}
		
	</style>

	<body>
	 	<s:actionerror theme="bootstrap"/>
        <s:actionmessage theme="bootstrap"/>
        <s:fielderror theme="bootstrap"/>
        
		  <s:form id="form" action="tip" theme="bootstrap" cssClass="well form-vertical" label="请输入预约电话查询">
		  	<s:textfield key="phone" />
		  	<s:submit key="submit" cssClass="btn btn-success btn-block" />
		  </s:form>
		  
		  
			  
	</body>
	
</html>
