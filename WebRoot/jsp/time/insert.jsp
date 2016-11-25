<%@ page contentType="text/html; charset=UTF-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<html>
	<head>
    <title>Insert A Time</title>
    <script src="../js/jquery.min.js"></script>
    <sb:head/>
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
	  <s:form action="submit" theme="bootstrap" cssClass="well form-vertical" label="時間段"  style="max-width:300px;margin:0 auto;">
	 	<s:hidden name="id" />
	  	<s:textfield key="time" />
	  	<s:textfield key="description" />
	  	<s:textfield key="count" />
	  	<s:submit key="submit" cssClass="btn btn-block btn-success" />
	  </s:form>
	</body>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$("input").css("height", "40px");
		})	
	</script>
</html>
