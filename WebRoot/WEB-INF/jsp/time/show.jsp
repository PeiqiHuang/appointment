<%@ page contentType="text/html; charset=UTF-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<html>
	<head>
    	<title>时间段管理</title>
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
		  		<div class="span12">
		  			<ul class="nav nav-tabs">
				      <li>
					      <a href='../person/manage/show'>挂号管理</a>
		     		  </li>	
				      <li class="active">
				        <a href='./show'><strong>时间段管理</strong></a>
				      </li>
				      <li>
					      <a href='../product/manage/order'>订单管理</a>
		     		  </li>	
				      <li>
					      <a href='../product/manage/all'>产品管理</a>
		     		  </li>	
				    </ul>
				  <table class="table table-striped table-bordered">
			  		<thead>
			  			<tr>
			  				<th><s:text name="time"/></th>
			  				<th><s:text name="description"/></th>
			  				<th><s:text name="count"/></th>
			  				<th colspan="2"><button   class="btn btn-block" name="add"><s:text name="add"/></button></th>
			  			</tr>
					</thead>	
					<tbody>
						<s:iterator value="times">
							<tr>
								<td><s:property value="time" /> </td>
								<td><s:property value="description" /> </td>
								<td><s:property value="count" /> </td>
								<td><button   class="btn btn-block" name="update" value="<s:property value="id" />"><s:text name="update"/></button></td>
								<td><button   class="btn btn-block" name="del" value="<s:property value="id" />"><s:text name="del"/></button></td>
							</tr>
						</s:iterator>
					</tbody>
				  </table>
			  	<s:form id="form_board" action="../board/set" >
				  	<s:textarea cssStyle="width:100%" key="board" />
				  	<ul id="tips_board" style="font-size:17px">
				  		<li>18点之后开放明天的预约，请耐心等候！</li>
				  		<li>19点之后开放明天的预约，请耐心等候！</li>
				  		<li>明天休息，预约暂停！</li>
				  		<li>明天的预约已满</li>
				  		<li>明天的预约已满，请明晚18点之后预约后天的号！</li>
				  	</ul>
				  	<button class="btn btn-large" id="clear_board">清空</button>
				  	<s:submit cssClass="btn btn-large" value="发布公告" />
			  	</s:form>
			  	<s:form id="form_doctor" action="../board/doctors" >
				  	<s:textarea cssStyle="width:100%" key="doctors" />
				  	<ul id="tips_doctors" style="font-size:17px">
				  		<li>谢永年</li>
				  		<li>谢朴可</li>
				  		<li>张美行</li>
				  		<li>谢永年，谢朴可</li>
				  		<li>谢永年(上午)，谢朴可(下午)</li>
				  		<li>谢永年，谢朴可</li>
				  		<li>谢永年，张美行</li>
				  		<li>张美行，谢朴可</li>
				  	</ul>
				  	<button class="btn btn-large" id="clear_doctors">清空</button>
				  	<s:submit cssClass="btn btn-large" value="发布医生" />
			  	</s:form>
			  </div>
		  </div>
	  </div>
	  
	  <s:form id="form">
	  	<s:hidden name="id" />
	  	<s:hidden name="time" />
	  	<s:hidden name="description" />
	  	<s:hidden name="count" />
	  </s:form>
	</body>
	
	<script src="../js/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			// store tab
			localStorage.appointment_show_tab = "time";
			/* add */
			$("button[name='add']").click(function() {
				/* window.open("insert", 'newwindow', 'height=200, width=300, modal=yes,status=no'); */
				window.location.href="insert";
			});
			/* update */
			$("button[name='update']").click(function() {
				$("#form_id").val($(this).val().trim());
				var $tds = $(this).parent().parent().children();
				$("#form_time").val($tds.first().text().trim());
				$("#form_description").val($tds.eq(1).text().trim());
				$("#form_count").val($tds.eq(2).text().trim());
				$("#form").attr("action", "insert");
				$("#form").submit();
			});
			/* delete */
			$("button[name='del']").click(function() {
				var con = confirm("确定删除?");
				if (con) {
					$("#form_id").val($(this).val().trim());
					$("#form").attr("action", "del");
					$("#form").submit();
				}
			});
			
			// board
			$("#clear_board").click(function(){
				$("#form_board_board").val("");
			});
			
			$("#tips_board li").click(function(){
				$("#form_board_board").val($(this).text());
			});
			
			// doctors
			$("#clear_doctors").click(function(){
				$("#form_doctor_doctors").val("");
			});
			
			$("#tips_doctors li").click(function(){
				$("#form_doctor_doctors").val($(this).text());
			});
		})
	</script>
	
</html>
