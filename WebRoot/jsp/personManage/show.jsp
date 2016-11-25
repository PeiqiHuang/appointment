<%@ page contentType="text/html; charset=UTF-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<html>
	<head>
	    <title>预约管理</title>
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

	<body style="margin:0px;">
		<div class="container">
			<div class="row ">
		  		<div class="span12" style="background-color:white;position:fixed;padding-bottom:20px;">
	  			    <ul class="nav nav-tabs">
				      <li class="active">
				        <a href='./show'><strong>预约管理</strong></a>
				      </li>
				      <li>
					      <a href='../../time/show'>时间段管理</a>
		     		  </li>	
				      <li>
					      <a href='../../product/manage/order'>订单管理</a>
		     		  </li>	
				      <li>
					      <a href='../../product/manage/all'>产品管理</a>
		     		  </li>	
				    </ul>
		  		  <!-- 选择日期 -->
				    <div class="form-search text-center">
				      <button type="button" class="btn btn-large" id="prev">←</button>
			  		  <s:textfield key="historyDate" style="height:40px;width:84px;" />
				      <button type="button" class="btn btn-large" id="next">→</button>
				      <button class="btn btn-large" name="add" type="button"><s:text name="add"/></button>
			  		  <%-- <s:textfield key="historyDate" cssClass="input-medium search-query" onClick="WdatePicker({maxDate:'%y-%M-%d'})"/>
				      <button type="submit" class="btn" id="dateSubmit">查询</button> --%>
				      <span class="pull-right btn-group">
				       	<button class="btn btn-large btn-success" id="success-count">0</span>
				       	<button class="btn btn-large btn-info" id="wait-count">0</span>
				       	<button class="btn  btn-large btn-danger" id="fail-count">0</span>
				      </span>
				    </div>		  		  
		  		  <!-- <button class="btn btn-info" id="dateSubmit">查询</button> -->
		  		  </div>		
				  <div class="span12" style="margin-top:110px;">  
				  <table class="table">
			  		<thead>
			  			<tr>
			  				<%-- <th><a href="javascript:"><s:text name="id"/></a></th> --%>
			  				<th><a href="javascript:"><s:text name="name"/></a></th>
			  				<th><a href="javascript:"><s:text name="gender"/></a></th>
			  				<th><a href="javascript:"><s:text name="age"/></a></th>
			  				<th><a href="javascript:"><s:text name="phone"/></a></th>
			  				<th><a href="javascript:"><s:text name="time"/></a></th>
			  				<%-- <th><a href="javascript:"><s:text name="date"/></a></th>
			  				<th><a href="javascript:"><s:text name="paid"/></a></th> --%>
			  				<%-- <th colspan="3"><button class="btn btn-block" name="add"><s:text name="add"/></button></th> --%>
			  				<th colspan="2">操作</th>
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
	<script src="../../js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			// store tab
			localStorage.appointment_show_tab = "person";
			var today = new Date();
			var DATE = dateToString(today);
			if (localStorage.personShowDate!=null) {
				DATE = localStorage.personShowDate;
			}
			$("#historyDate").val(DATE);
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
			
			// 日期选择
			$("#historyDate").click(function(){
				WdatePicker({maxDate:'%y-%M-%d',onpicked:saveDate});
			});
			
			$("#prev").click(function(){
				var d = new Date($("#historyDate").val());
				d.setTime(d.getTime() - 86400000 );
				$("#historyDate").val(dateToString(d));
				saveDate();
			});
			$("#next").click(function(){
				var d = new Date($("#historyDate").val());
				d.setTime(d.getTime() + 86400000 );
				$("#historyDate").val(dateToString(d));
				saveDate();
			});
			
			function setForm(btn) {
				$("#form_id").val($(btn).val().trim());
				var $tds = $(btn).parent().parent().children();
				$("#form_name").val($tds.eq(0).text().trim());
				$("#form_age").val($tds.eq(2).text().trim());
				$("#form_phone").val($tds.eq(3).text().trim());
				
				var $hiddenEle = $tds.filter("input[type='hidden']");
				$("#form_gender").val($hiddenEle.eq(0).val());
				$("#form_time_id").val($hiddenEle.eq(1).val());
				$("#form_paid").val($hiddenEle.eq(2).val());
			}
			
			/* ajax 切换日期 */
			/* $("#dateSubmit").click(function(){
				DATE = $("#historyDate").val().trim();
				updateTableByDate();
			}); */
			
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
					/* line += "<td>"+(i+1)+"</td>"; */
					line += "<td>"+this.name+"</td>";
					var gender = "女";
					if (this.gender) {
						gender = "男";
					}
					line += "<td>"+gender+"</td>";
					line += "<td>"+this.age+"</td>";
					line += "<td>"+this.phone+"</td>";
					line += "<td>"+this.time.time+"</td>";
					/* var paid = "等待";
					if (this.paid > 0) {
						paid="成功";
					} else if (this.paid < 0) {
						paid="过期";
					}
					line += "<td>"+this.date+"</td>";
					line += "<td>"+paid+"</td>"; */
					line += "<td><button  class='btn btn-block' name='update' value='"+this.id+"'><s:text name='update'/></button></td>";
					line += "<td><button  class='btn btn-block' name='del' value='"+this.id+"''><s:text name='del'/></button></td>";
					line += "<input type='hidden' name='gender' value='"+this.gender+"'/>";
					line += "<input type='hidden' name='time.id' value='"+this.time.id+"'/>";
					line += "<input type='hidden' name='paid' value='"+this.paid+"'/>";
					/* line += "<s:hidden name='date' />"; */
					line += "</tr>";
					$("#persons").append(line);
				});
				
				updateLabel();
			}
			
			function saveDate() {
				localStorage.personShowDate = $("#historyDate").val();
				DATE = $("#historyDate").val().trim();
				updateTableByDate();
			}
			
			function dateToString(date){
				return date.getFullYear() + "-" + (date.getMonth()+1) + "-" + date.getDate();
			}
			
			function updateLabel() {
				var success = 0;
				var wait = 0;
				var fail = 0;
				$("#persons").children().each(function(){
					//var info = $(this).children().eq(7).text();
					var paid = parseInt($(this).find("input[name='paid']").first().val());
					console.info("paid = " + paid)
					//if ("成功" == info) {
					if (paid > 0) {
						success++;
						$(this).attr("class", "success");
					//} else if ("等待" == info) {
					} else if (paid == 0) {
						wait++;
						$(this).attr("class", "info");
					} else {
						fail++;
						$(this).attr("class", "error");
					}
				})
				$("#success-count").text(success);
				$("#wait-count").text(wait);
				$("#fail-count").text(fail);
			}
		})
	</script>
	
</html>
