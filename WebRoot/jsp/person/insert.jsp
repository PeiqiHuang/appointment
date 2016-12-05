<%@ page contentType="text/html; charset=UTF-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<html>
	<head>
	    <title>谢永年卫生所</title>
	    <script src="../js/jquery.min.js"></script>
	    <sb:head/>
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
		<s:if test='null==board || ""==board || id!=null || addDate!=null'>	        
		  <s:form id="form" action="submit" theme="bootstrap" cssClass="well form-vertical" label="请输入您的预约信息"  style="max-width:600px;margin:0 auto;">
		  	<ul>
		  		<li>本次预约仅限<span class="text-error">明天</span>就诊</li>
		  		<li>预约费<span class="text-error">¥20/位</span></li>
		  		<li>如没有你想要的时间段请酌情选择其他可选时间段，或过段时间来看是否有没支付的号返还</li>
	  		</ul>
		  	<s:hidden name="id" />
		  	<!-- appointmentService is on ValueStack, we can directly use its method and can put some param -->
	  		<s:select label="可选时间段" name="time.id" list="appointmentService.getAvailableTime(id, addDate)" listKey="id" listValue="description" />
		  	<s:textfield key="name" />
		  	<s:radio key="gender" list="#{false:'女',true:'男'}" />
		  	<s:textfield key="age" />
		  	<s:textfield key="phone" />
		  	<s:if test="id!=null"><s:select key="paid" list="#{-1:'过期', 0:'等待', 1:'成功'}"/></s:if>
		  	<input type="hidden" value="<s:date format='yyyy-MM-dd HH:mm:ss' name='date' />" name='date' />
		  	<s:hidden name="date" />
		  	<a href="#" id="submit" class="btn btn-success btn-large btn-block">提交</a>
		  	
		  	<a href="searchForm" style="margin-top:10px;float:right">提交记录</a>
		  </s:form>
		  <s:hidden value="false" id="duplicate" />
		  
		</s:if>
		<s:else>
			<h3 class="well text-info"><s:property value="board"/></h3>
		</s:else>
			  
	</body>
	

	<script type="text/javascript">
		$(document).ready(function(){
			$("input[type='text'],select").css("height", "40px");
			
			$("#submit").click(function(){
				if ($("#duplicate").val() == "false") {
					$("#duplicate").val("true");
					$("#form").submit();
				} else {
					alert("预约信息已提交，请避免重复提交！")
					window.location.href = "tip?phone="+$("#form_phone").val();
				}
			})
		})
	</script>
	
</html>
