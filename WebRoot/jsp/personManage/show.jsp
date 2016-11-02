<%@ page contentType="text/html; charset=UTF-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<html>
	<head>
	    <title>预约挂号管理</title>
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

	<body style="padding:10px;" class="well">
		<div class="container ">
			<div class="row ">
		  		<div class="span12">
		  		  <!-- 选择日期 -->
				    <div class="form-search text-center">
			  		  <s:textfield key="historyDate" cssClass="input-medium search-query" />
				      <button type="submit" class="btn btn-info" id="dateSubmit">查询</button>
				    </div>		  		  
		  		  <!-- <button class="btn btn-info" id="dateSubmit">查询</button> -->
				  <table class="table table-striped table-bordered">
			  		<thead>
			  			<tr>
			  				<th><a href="javascript:"><s:text name="id"/></a></th>
			  				<th><a href="javascript:"><s:text name="name"/></a></th>
			  				<th><a href="javascript:"><s:text name="gender"/></a></th>
			  				<th><a href="javascript:"><s:text name="age"/></a></th>
			  				<th><a href="javascript:"><s:text name="phone"/></a></th>
			  				<th><a href="javascript:"><s:text name="time"/></a></th>
			  				<th><a href="javascript:"><s:text name="paid"/></a></th>
			  				<th><a href="javascript:"><s:text name="date"/></a></th>
			  				<th colspan="3"><button class="btn btn-block" name="add"><s:text name="add"/></button></th>
			  			</tr>
					</thead>	
					<tbody id="persons">
						<%-- <s:iterator value="persons" status="L">
							<tr>
								<td><s:property value="#L.index+1" /> </td>
								<td><s:property value="name" /> </td>
								<td>
									<s:if test="gender==true">男</s:if> 
									<s:else>女</s:else>
								</td>
								<td><s:property value="age" /></td>
								<td><s:property value="phone" /> </td>
								<td><s:property value="time.time" /> </td>
								<td>
									<s:if test="paid==-1">过期</s:if> 
									<s:elseif test="paid==0">等待</s:elseif>
									<s:else>成功</s:else>
								</td>
								<!-- date tag can format date output -->
								<td><s:date name="date"  format="HH:mm" /> </td>
								<td><button  class="btn btn-block" name="update" value="<s:property value="id" />"><s:text name="update"/></button></td>
								<td><button  class="btn btn-block" name="del" value="<s:property value="id" />"><s:text name="del"/></button></td>
								
								<s:hidden name="gender" />
								<s:hidden name="time.id" />
								<s:hidden name="paid" />
							</tr>
						</s:iterator> --%>
					</tbody>
				  </table>
			  </div>
		  </div>	
	  </div>	
	  
	  <s:form id="form">
	  	<s:hidden name="id" />
	  	<s:hidden name="name" />
	  	<s:hidden name="gender" />
	  	<s:hidden name="age" />
	  	<s:hidden name="phone" />
	  	<s:hidden name="time.id" />
	  	<s:hidden name="paid" />
	  	<s:hidden name="date" />
	  </s:form>
	</body>
	
	<script src="../../js/jquery.min.js"></script>
	<script src="../../js/jquery.tablesort.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			var today = new Date();
			var DATE = today.getFullYear() + "-" + (today.getMonth()+1) + "-" + today.getDate();
			// init table by today's persons
			updateTableByDate();
			
			/* sort table */
			$('table').tablesort().data('tablesort');
			
			/* add */
			$("button[name='add']").click(function() {
				console.info(DATE)
				window.open("../insert?addDate="+DATE, 'newwindow');
				// window.location.href="../insert";
			});
			/* update */
			$(document).on("click", "button[name='update']", function() {
/* 				$("#form_id").val($(this).val().trim());
				var $tds = $(this).parent().parent().children();
				$("#form_name").val($tds.eq(1).text().trim());
				$("#form_gender").val($tds.eq(2).text().trim());
				$("#form_age").val($tds.eq(3).text().trim());
				$("#form_phone").val($tds.eq(4).text().trim());
				$("#form_time_id").val($tds.eq(6).val().trim());
				$("#form_paid").val($tds.eq(7).text().trim()); */
				
				setForm(this);
				$("#form").attr("action", "../insert");
				$("#form").submit();
			});
			/* delete */
			$(document).on("click", "button[name='del']", function() {
				var con = confirm("确定删除?");
				if (con) {
					setForm(this);
					$("#form").attr("action", "del");
					$("#form").submit();
				}
			});
			
			function setForm(btn) {
				$("#form_id").val($(btn).val().trim());
				var $tds = $(btn).parent().parent().children();
				$("#form_name").val($tds.eq(1).text().trim());
				$("#form_age").val($tds.eq(3).text().trim());
				$("#form_phone").val($tds.eq(4).text().trim());
				
				var $hiddenEle = $tds.filter("input[type='hidden']");
				$("#form_gender").val($hiddenEle.eq(0).val());
				$("#form_time_id").val($hiddenEle.eq(1).val());
				$("#form_paid").val($hiddenEle.eq(2).val());
			}
			
			/* ajax 切换日期 */
			$("#dateSubmit").click(function(){
				DATE = $("#historyDate").val().trim();
				updateTableByDate();
			});
			
			function updateTableByDate() {
				if ("" != DATE) {
					var url = "getPersonsByDate";
					var data = {"date" : DATE};
					$.get(url, data, function(data){
						var persons = eval(data);
						// console.info(persons)
						setTableData(persons);
					}, "json");
				}
			}
			
			function setTableData(persons) {
				$("#persons").empty();
				$.each(persons, function(i){
					var line = "<tr>";
					line += "<td>"+(i+1)+"</td>";
					line += "<td>"+this.name+"</td>";
					var gender = "女";
					if (this.gender) {
						gender = "男";
					}
					line += "<td>"+gender+"</td>";
					line += "<td>"+this.age+"</td>";
					line += "<td>"+this.phone+"</td>";
					line += "<td>"+this.time.time+"</td>";
					var paid = "等待";
					if (this.paid > 0) {
						paid="成功";
					} else if (this.paid < 0) {
						paid="过期";
					}
					line += "<td>"+paid+"</td>";
					line += "<td>"+this.date+"</td>";
					line += "<td><button  class='btn btn-block' name='update' value='"+this.id+"'><s:text name='update'/></button></td>";
					line += "<td><button  class='btn btn-block' name='del' value='"+this.id+"''><s:text name='del'/></button></td>";
					line += "<input type='hidden' name='gender' value='"+this.gender+"'/>";
					line += "<input type='hidden' name='time.id' value='"+this.time.id+"'/>";
					line += "<input type='hidden' name='paid' value='"+this.paid+"'/>";
					/* line += "<s:hidden name='date' />"; */
					line += "</tr>";
					$("#persons").append(line);
				});
			}
		})
	</script>
	
</html>
