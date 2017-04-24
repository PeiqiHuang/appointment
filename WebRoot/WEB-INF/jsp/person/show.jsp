<%@ page contentType="text/html; charset=UTF-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<html>
	<head>
	    <title>查看预约</title>
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
		  		<div class="span12" style="background-color:white;position:fixed;">
		  		  	<!-- 选择日期 -->
				    <div class="form-search text-center">
				      <button type="button" class="btn btn-large" id="prev">←</button>
			  		  <s:textfield key="historyDate" style="height:40px;width:88px;" />
				      <button type="button" class="btn btn-large" id="next">→</button>
				    </div>		  		  
		  		  </div>		
				  <div class="span12" style="margin-top:50px;">  
				  <table class="table">
			  		<thead>
			  			<tr>
			  				<%-- <th><a href="javascript:"><s:text name="id"/></a></th> --%>
			  				<th><a href="javascript:"><s:text name="name"/></a></th>
			  				<th><a href="javascript:"><s:text name="gender"/></a></th>
			  				<th><a href="javascript:"><s:text name="age"/></a></th>
			  				<th><a href="javascript:"><s:text name="phone"/></a></th>
			  				<th><a href="javascript:"><s:text name="time"/></a></th>
			  			</tr>
					</thead>	
					<tbody id="persons">
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
	
	<script src="../js/jquery.min.js"></script>
	<script src="../js/jquery.tablesort.min.js"></script>
	<script src="../js/My97DatePicker/WdatePicker.js"></script>
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
					var postData = {"date" : DATE};
					$.get(url, postData, function(data){
						var persons = eval(data);
						// console.info(persons)
						setTableData(persons);
						// 设置月度总数
						$.get("getMonthCount", postData, function(data){
							$("#monthCount").text(data + " / ¥" + (parseInt(data) * 20));
						})
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
					line += "<input type='hidden' name='gender' value='"+this.gender+"'/>";
					line += "<input type='hidden' name='time.id' value='"+this.time.id+"'/>";
					line += "<input type='hidden' name='paid' value='"+this.paid+"'/>";
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
			}
		})
	</script>
	
</html>
