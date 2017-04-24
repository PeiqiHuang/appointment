<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<html>

	<head>
    	<title>Login</title>
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
		<!-- <h4>请登陆</h4>  -->
		<s:form action="login" theme="bootstrap" cssClass="well form-vertical" label="请登陆" style="max-width:600px;margin:0 auto;">
    		<s:textfield key="username"/>
     		<s:password key="password"/>
    		<s:submit key="submit"  cssClass="btn btn-success btn-large btn-block" />
		</s:form>	
	  
		
	</body>
	
	<script src="./js/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$("input").css("height", "40px");
		})
	</script>
</html>
