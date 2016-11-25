<%@ page contentType="text/html; charset=UTF-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<html>
	<head>
	    <title>查询订单</title>
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
        
		  <s:form id="form" action="searchResult" theme="bootstrap" cssClass="well form-vertical" label="查询订单">
		  	<s:textfield key="searchPattern"  label="请输入订单姓名 / 电话查询" />
		  	<s:submit value="查询" key="submit" cssClass="btn btn-success btn-block" />
		  </s:form>
	</body>
	<script src="../js/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$("input").css("height", "40px");
		})
	</script>
</html>
