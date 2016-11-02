<%@ page contentType="text/html; charset=UTF-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<html>
	<head>
    <title>提交状态</title>
    <sb:head/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	</head>

	<body class="well">
	      <h3 id="leftTime" style="padding:15px"></h3>
		<s:hidden name="leftSecond" />
		<s:hidden name="person.name" />
		<s:hidden name="person.time.description" />
		<s:hidden name="person.phone" />
		<%-- <s:property value="phone" /> --%>
	</body>
	
	<script src="../js/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			var interval = 1000; 
			var leftsecond = $("#leftSecond").val();
			function ShowCountDown() { 
				var day1=Math.floor(leftsecond/(60*60*24)); 
				var hour=Math.floor((leftsecond-day1*24*60*60)/3600); 
				var minute=Math.floor((leftsecond-day1*24*60*60-hour*3600)/60); 
				var second=Math.floor(leftsecond-day1*24*60*60-hour*3600-minute*60); 
				var text = "<div>剩余支付时间: <span class='text-error'>"+hour+"小时"+minute+"分"+second+"秒</span></div>" + 
							"<p class='text-info'>请搜索xieyongnian46或长按下方二维码后点击'识别图中二维码'添加微信号后支付预约费用</p>" + 
							"<img src='../img/erweima.jpg' width='100%' />"
							;
				$("#leftTime").html(text); 
				leftsecond--;
			}
			if (leftsecond > 0) {
				window.setInterval(ShowCountDown, interval); 
			} else if (leftsecond == -1) {
				$("#leftTime").html("没有查询到该号码的提交记录！");
			} else if (leftsecond == -2) {
				var text = "该号码已支付成功，请于明天预约时间段前来就诊！<p class='text-info'>姓名："+$("#person_name").val()+"<br/>预约时间段："+$("#person_time_description").val()+"<br/>电话："+$("#person_phone").val()+"</p>";
				$("#leftTime").html(text);
			}else {
				$("#leftTime").html("本号码没有在规定时间内支付，本次预约已作废！");
			}
		})
	</script>
	
</html>
