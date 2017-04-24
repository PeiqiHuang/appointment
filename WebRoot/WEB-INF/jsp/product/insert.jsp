<%@ page contentType="text/html; charset=UTF-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<html>
	<head>
    <title>Insert A Product</title>
    <script src="../../js/jquery.min.js"></script>
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
	  <s:form action="submit" theme="bootstrap" cssClass="well form-vertical" label="产品"  style="max-width:300px;margin:0 auto;">
	 	<s:hidden name="product.id" />
	  	<s:textfield key="product.name" />
	  	<s:textfield key="product.alias" />
	  	<s:textfield key="product.price" />
	  	<s:textfield key="product.valid" />
	  	<s:textfield key="product.label" label="分类"/>
	  	<s:textfield key="product.img" />
	  	<s:textfield key="product.link" />
	  	<s:textfield key="product.description" />
	  	<s:submit key="submit" cssClass="btn btn-block btn-success" />
	  </s:form>
	</body>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$("input").css("height", "40px");
		})	
	</script>
</html>
