<%@ page contentType="text/html; charset=UTF-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<html>
	<head>
	    <title>订单管理</title>
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
			#buyers tr {
				/* color:grey; */
			}
	    </style>
	</head>

	<body style="margin:0px">
		<div class="container">
			<div class="row ">
		  		<div class="span12" style="background-color:white;position:fixed;padding-bottom:10px;border-bottom: 1px solid lightgray">
	  			    <ul class="nav nav-tabs" style="margin-bottom: 10px;">
				      <li>
				        <a href='../../person/manage/show'>挂号管理</a>
				      </li>
				      <li>
					      <a href='../../time/show'>时间段管理</a>
		     		  </li>	
				      <li class="active">
				        <a href='./order'><strong>订单管理</strong></a>
				      </li>
				      <li>
				        <a href='./all'>产品管理</a>
				      </li>
				      <li style="padding:2px;" class="pull-right">
				        <s:textfield key="searchPattern" cssClass="input-medium search-query" style="height:25px;" placeholder="名字/电话" />
				        <button type="button" class="btn" id="searchBtn">查询</button>
				      </li>
				      <li style="padding:2px 40px 2px;" class="pull-right">
					      <button type="button" class="btn" id="prev">←</button>
				  		  <s:textfield key="historyDate"  cssClass="input-medium search-query" style="height:25px;width:105px;" />
					      <button type="button" class="btn" id="next">→</button>
				      </li>
				    </ul>
		  		  <!-- 选择日期 -->
				    <div class="form-search">
					      <!-- <button type="submit" class="btn" id="dateSubmit">查询</button> -->
				      <span class="pull-left">
					      <button class="btn btn-large" name="add" type="button"><s:text name="add"/></button>
					      <button class="btn btn-large" name="paid" type="button" url="changeSelectPaid">支付</button>
					      <button class="btn btn-large" name="sent" type="button" url="changeSelectSent">发货</button>
					      <button class="btn btn-large" type="button" id="showAll">全部</button>
				      </span>
				      <span class="btn-group pull-right">
					       	<button class="btn btn-large btn-info" id="sent2-count">0</span>
					       	<button class="btn btn-large btn-success" id="sent1-count">0</span>
					       	<button class="btn btn-large btn-warning" id="unsent-count">0</span>
					       	<button class="btn btn-large btn-danger" id="unpaid-count">0</span>
				      </span>
				    </div>		
				  </div>		
				  <div class="span12" style="margin-top:110px;">    		  
				  <table class="table "> <!--  table-striped table-bordered -->
			  		<thead>
			  			<tr>
			  				<th><a href="javascript:">序号</a></th>
			  				<th><a href="javascript:">邮寄</a></th>
			  				<th><a href="javascript:">产品</a></th>
			  				<!-- <th><a href="javascript:">电话</a></th>
			  				<th><a href="javascript:">地址</a></th>
			  				<th><a href="javascript:">货费</a></th>
			  				<th><a href="javascript:">运费</a></th>
			  				<th><a href="javascript:">总共</a></th> -->
			  				<th><a href="javascript:">价格</a></th>
			  				<!-- <th><a href="javascript:">支付</a></th>
			  				<th><a href="javascript:">发货</a></th> -->
			  				<!-- <th><a href="javascript:">时间</a></th> -->
			  				<th>操作</th>
			  			</tr>
					</thead>	
					<tbody id="buyers">
					</tbody>
				  </table>
			  </div>
		  </div>	
	  </div>	
	  
	  <s:form id="form">
	  	<s:hidden name="buyerid" value="" />
	  	<s:hidden name="productValid" value="0" />
	  </s:form>
	</body>
	
	<script src="../../js/jquery.min.js"></script>
	<script src="../../js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			// store tab
			localStorage.appointment_show_tab = "order";
			var today = new Date();
			var DATE = dateToString(today);
			if (localStorage.productShowDate!=null) {
				DATE = localStorage.productShowDate;
			}
			$("#historyDate").val(DATE);
			// init table by today's persons
			updateTableByDate();
			
			// init
			$("#form_buyerid").val("");
			
			/* sort table */
			/* $('table').tablesort().data('tablesort'); */
			
			/* add */
			$("button[name='add']").click(function() {
				/* window.open("../buy", 'newwindow'); */
				$("#form").attr("action", "../buy");
				$("#form").submit();
			});
			/* update */
			$(document).on("click", "button[name='update']", function() {
				$("#form_buyerid").val($(this).val());
				$("#form").attr("action", "../buy");
				$("#form").submit();
			});
			/* delete */
			$(document).on("click", "button[name='del']", function() {
				var con = confirm("确定删除?");
				if (con) {
					$("#form_buyerid").val($(this).val());
					$("#form").attr("action", "del");
					$("#form").submit();
				}
			});
			
			/* ajax 切换日期 */
			/* $("#dateSubmit").click(function(){
				DATE = $("#historyDate").val().trim();
				updateTableByDate();
			}); */
			
			// 多选
			$(document).on("click", "#buyers tr", function(){
				var $td = $(this).children().first();
				if ($td.attr("select") != "true") {
					$td.attr("select", "true");
					$(this).css("color", "red");
				} else {
					$td.attr("select", "false");
					$(this)	.css("color", "black");
				}
			})
			
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
			
			// 多项修改支付
			$("button[name='paid'],button[name='sent']").click(function(){
				var url = $(this).attr("url");
				var name = $(this).attr("name");
				var ids = getSelected();
				if (ids.length < 1 ) {
					alert("请至少选择一个买家");
					return;
				}
				var valid = confirm("修改方向？") ? 1 : 0;
				if (name == "sent" && valid > 0) {
					valid = confirm("诊所发？") ? 1 : 2;
				}
				var data = $.param({"ids" : getSelected(), "valid" : valid}, true);
				$.post(url, data, function(info){
					// alert(info);
					window.location.href = window.location.href; 
				})
			});
			
			$("#sent1-count").click(function(){
				filterTable("success");
			})
			$("#sent2-count").click(function(){
				filterTable("info");
			})
			$("#unsent-count").click(function(){
				filterTable("warning");
			})
			$("#unpaid-count").click(function(){
				filterTable("error");
			})
			$("#showAll").click(function(){
				recoverTable();
			})
			
			$("#searchBtn").click(function(){
				var text = $("#searchPattern").val().trim();
				updateTableBySearch(text);
			})
			
			//-------------------------------function--------------------------------
			function getSelected(){
				var ids = [];
				$("#buyers tr").each(function(){
					var $td = $(this).children().first();
					if ($td.attr("select") == "true") {
						ids.push($(this).attr("name"));
					}
				});
				return ids;
			}
			
			function updateTableByDate() {
				if ("" != DATE) {
					var url = "getBuyersByDate";
					var data = {"date" : DATE};
					$.get(url, data, function(data){
						var buyers = eval(data);
						// console.info(buyers)
						setTableData(buyers);
					}, "json");
				}
			}
			
			function updateTableBySearch(text) {
				if ("" != text) {
					var url = "getBuyersBySearch";
					var data = {"pattern" : text};
					$.get(url, data, function(data){
						var buyers = eval(data);
						// console.info(buyers)
						setTableData(buyers);
					}, "json");
				}
			}
			
			function setTableData(buyers) {
				$("#buyers").empty();
				$.each(buyers, function(i){
					var line = "<tr name='"+this.id+"' price='"+this.price+"' paid='"+this.paid+"' sent='"+this.sent+"'>";
					line += "<td rowspan=1><p>"+(i+1)+"</p><p>"+this.date+"</p></td>";
					
					line += "<td rowspan=1>";
					line += "<p>"+this.name+"</p><p>"+this.phone+"</p><p>"+this.address+"</p>";
					if(this.remark!= "")
						line += "<p><small style='color:red'>( 备注: "+this.remark+" )</small><p/>";
					line += "</td>";
					
					line += "<td rowspan=1>";
					$.each(this.buies, function(){
						line += "<p>"+this.product.alias+" "+this.number+"</p>";
					});
					line += "</td>";
					
					/* line += "<td>"+this.name+"</td>";
					line += "<td>"+this.phone+"</td>";
					line += "<td>"+this.address+"</td>";
					line += "<td>"+this.price+"</td>";
					line += "<td>"+this.freight+"</td>";
					line += "<td>"+this.tprice+"</td>"; */
					line += "<td rowspan=1>";
					line += "<p>"+this.price+"</p><p>"+this.freight+"</p><p><strong>"+this.tprice+"</strong></p>";
					line += "</td>";
					
					/* var paid = "×";
					if (this.paid > 0) {
						paid="√";
					}
					line += "<td rowspan=1>"+paid+"</td>";
					var sent = "×";
					if (this.sent == 1) {
						sent="√";
					} else if (this.sent == 2) {
						sent="☆";
					}
					line += "<td rowspan=1>"+sent+"</td>"; */
					/* line += "<td rowspan=1>"+this.date+"</td>"; */
					line += "<td><button  class='btn btn-block' name='update' value='"+this.id+"'>更新</button>"
						+"<button  class='btn btn-block' name='del' value='"+this.id+"'><s:text name='del'/></button></td>";
					line += "</tr>";
					/* line += "<tr><td><button  class='btn btn-block' name='update' value='"+this.id+"'>产品</button></td></tr>"; */
					/* line += "<tr><td><button  class='btn btn-block' name='del' value='"+this.id+"''><s:text name='del'/></button></td></tr>"; */
					/* line += "<input type='hidden' name='paid' value='"+this.paid+"'/>";
					line += "<input type='hidden' name='sent' value='"+this.sent+"'/>"; */
					/* line += "<s:hidden name='date' />"; */
					$("#buyers").append(line);
				});
				updateLabel();
			}
			
			function saveDate() {
				localStorage.productShowDate = $("#historyDate").val();
				DATE = $("#historyDate").val().trim();
				updateTableByDate();
			}
			
			function dateToString(date){
				return date.getFullYear() + "-" + (date.getMonth()+1) + "-" + date.getDate();
			}
			
			function updateLabel() {
				var unpaid = 0;
				var unsent = 0;
				var sent1 = 0;
				var sent2 = 0;
				var unpaid_price = 0;
				var unsent_price = 0;
				var sent1_price = 0;
				var sent2_price = 0;
				$("#buyers").children().each(function(){
					/* $(this).attr("class", "success"); */
					var price = parseInt($(this).attr("price"));
					var paid = parseInt($(this).attr("paid"));
					var sent = parseInt($(this).attr("sent"));
					/* var p = $(this).children().eq(4).text();
					var s = $(this).children().eq(5).text();
					if ("×" == p) { */
					if (paid < 1) {
						unpaid++;
						unpaid_price += price;
						$(this).attr("class", "error");
					}else if (sent == 0) {
						unsent++;
						unsent_price += price;
						$(this).attr("class", "warning");
					} else if (sent == 1){
						sent1++;
						sent1_price += price;
						$(this).attr("class", "success");
					} else if (sent == 2){
						sent2++;
						sent2_price += price;
						$(this).attr("class", "info");
					}
				})
				//console.info($("#buyers").children().length + " " + unpaid + " " + unsent)
				$("#sent1-count").text(sent1 + " / ¥" + sent1_price);
				$("#sent2-count").text(sent2 + " / ¥" + sent2_price);
				$("#unpaid-count").text(unpaid + " / ¥" + unpaid_price);
				$("#unsent-count").text(unsent + " / ¥" + unsent_price);
			}
			
			function filterTable(bgColor) {
				$("#buyers").children().each(function(){
					if ($(this).hasClass(bgColor)) {
						$(this).css("display", "");
					} else {
						$(this).css("display", "none");
					}
				})
			}
			
			function recoverTable() {
				$("#buyers").children().each(function(){
					$(this).css("display", "");
				})
			}
			
		})
	</script>
	
</html>
