<%@ page contentType="text/html; charset=UTF-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<html>
	<head>
    	<title>产品管理</title>
    	<sb:head/>
	    <style>
			a {
				color:black;
			}
			a:link{
				text-decoration:none;
			}
			table {
				margin-top: 10px
			}
	    </style>
	</head>

	<body style="margin:0px">
		<div class="container ">
			<div class="row ">
		  		<div class="span12"  style="background-color:white;position:fixed;">
		  			<ul class="nav nav-tabs">
				      <li>
					      <a href='../../person/manage/show'>挂号管理</a>
		     		  </li>	
				      <li>
				        <a href='../../time/show'>时间段管理</a>
				      </li>
				      <li>
					      <a href='./order'>订单管理</a>
		     		  </li>	
				      <li class="active">
					      <a href='./all'><strong>产品管理</strong></a>
		     		  </li>	
				    </ul>
				    <div style="margin-top:-10px;margin-bottom:10px;" class="form-search text-center">
			  		  <s:textfield key="searchPattern" cssClass="input-medium search-query" style="height:30px;" />
				      <button type="button" class="btn" id="searchBtn">查询</button>
				    </div>		  		  
			      </div>
			      <div class="span12"  style="margin-top:80px;">
				  <table class="table table-striped table-bordered">
			  		<thead>
			  			<tr>
			  				<th>名字</th>
			  				<th>简称</th>
			  				<th>价格</th>
			  				<th>有效</th>
			  				<th>分类</th>
			  				<th>图片</th>
			  				<th>链接</th>
			  				<th>描述</th>
			  				<th colspan="2"><button   class="btn btn-block" name="add"><s:text name="add"/></button></th>
			  			</tr>
					</thead>	
					<tbody>
						<s:iterator value="page.objectList">
							<tr>
								<td><s:property value="name" /> </td>
								<td><s:property value="alias" /> </td>
								<td><s:property value="price" /> </td>
								<td><s:property value="valid" /> </td>
								<td><s:property value="label" /> </td>
								<td><s:property value="%{img.equals(\"\")? '×' : '√'}" /> </td>
								<td><s:property value="%{link.equals(\"\")? '×' : '√'}" /> </td>
								<td><s:property value="%{description.equals(\"\")? '×' : '√'}" /> </td>
								<td><button   class="btn btn-block" name="update" value="<s:property value="id" />"><s:text name="update"/></button></td>
								<td><button   class="btn btn-block" name="del" value="<s:property value="id" />"><s:text name="del"/></button></td>
							</tr>
						</s:iterator>
					</tbody>
				  </table>
			  </div>
		  </div>
	  </div>
	  
	  <s:form id="form">
	  	<s:hidden name="productId" />
	  </s:form>
	  
	  <jsp:include page="../util/page.jsp"/>
	</body>
	
	<script src="../../js/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			// store tab
			localStorage.appointment_show_tab = "product";
			/* add */
			$("button[name='add']").click(function() {
				/* window.open("insert", 'newwindow', 'height=200, width=300, modal=yes,status=no'); */
				window.location.href="insert";
			});
			/* update */
			$("button[name='update']").click(function() {
				$("#form_productId").val($(this).val().trim());
				/* var $tds = $(this).parent().parent().children();
				$("#form_name").val($tds.first().text().trim());
				$("#form_alias").val($tds.eq(1).text().trim());
				$("#form_price").val($tds.eq(2).text().trim());
				$("#form_valid").val($tds.eq(3).text().trim());
				$("#form_link").val($tds.eq(4).text().trim()); */
				$("#form").attr("action", "insert");
				$("#form").submit();
			});
			/* delete */
			$("button[name='del']").click(function() {
				var con = confirm("确定删除?");
				if (con) {
					$("#form_productId").val($(this).val().trim());
					$("#form").attr("action", "pdel");
					$("#form").submit();
				}
			});
			
			$("#searchBtn").click(function(){
				var text = $("#searchPattern").val().trim();
				if ("" != text) {
					var url = window.location.href;
	    			var pattern = "?searchPattern=";
	    			var index = url.indexOf(pattern);
	    			if (index > 0) {
	    				url = url.substring(0, index);
	    			}
	    			url += pattern + text;
	    			window.location.href = url;
				}
			})
			
		})
	</script>
	
</html>
