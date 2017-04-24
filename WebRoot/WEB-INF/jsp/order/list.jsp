<%@ page contentType="text/html; charset=UTF-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<html>
	<head>
	    <title>订单</title>
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
			.selected {
				/* border : green dashed 2px; */
				/* border-top: solid 3px green; */
				/* background:#D8D8D8; */
				color:red;
			}
			.primary {
				background-color : 0099cc;
				/* color : white; */
			}
	    </style>
	</head>

	<body style="margin:0px">
		<div class="container">
			<div class="row ">
		  		<div class="span12" style="background-color:white;position:fixed;padding-bottom:10px;border-bottom: 1px solid lightgray;z-index: 99;">
		  		  <!-- 选择日期 -->
				    <div class="form-search">
				      <span class="pull-right" style="margin-left:20px;">
					      <s:textfield key="searchPattern" cssClass="input-medium search-query" style="height:25px;" placeholder="名字/电话" />
				          <button type="button" class="btn" id="searchBtn">查询</button>
				      </span>
				      <span class="pull-right">
						  <button type="button" class="btn" id="prev">←</button>
				  		  <s:textfield key="historyDate"  cssClass="input-medium search-query" style="height:25px;width:105px;" />
					      <button type="button" class="btn" id="next">→</button>				      
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
	  </s:form>
	</body>
	
	<script src="../js/jquery.min.js"></script>
	<script src="../js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
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
			
			// 多选
			$(document).on("click", "#buyers tr", function(){
				var $td = $(this).children().first();
				var price = parseInt($(this).attr("price"));
				if ($td.attr("select") != "true") {
					$td.attr("select", "true");
					//$(this).css("color", "red");
					$(this).addClass("selected");
					addSelectedPrice(price);
				} else {
					$td.attr("select", "false");
					// $(this).css("color", "black");
					$(this).removeClass("selected");
					addSelectedPrice(-price);
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
					if (valid == 1) {
						valid = confirm("落公？") ? 1 : 3;
					}
				}
				var data = $.param({"ids" : getSelected(), "valid" : valid}, true);
				$.post(url, data, function(info){
					// alert(info);
					window.location.href = window.location.href; 
				})
			});
			
			
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
					var url = "listAjax";
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
			
			function updateTableByUnsent() {
				var url = "getUnsentBuyers";
				$.get(url, function(data){
					var buyers = eval(data);
					// console.info(buyers)
					setTableData(buyers);
				}, "json");
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
					
					line += "</tr>";
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
				var sent3 = 0;
				var unpaid_price = 0;
				var unsent_price = 0;
				var sent1_price = 0;
				var sent2_price = 0;
				var sent3_price = 0;
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
					} else if (sent == 3){
						sent3++;
						sent3_price += price;
						$(this).attr("class", "primary");
						//$(this).css("background-color", "rgb(0,85,204)");
					}
				})
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
			
			function addSelectedPrice(price) {
				var selected = parseInt($("#selected_price").text());
				$("#selected_price").text(selected + price);
			}
			
		})
	</script>
	
</html>
