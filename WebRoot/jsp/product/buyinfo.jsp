<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<html>

	<head>
    	<title>购买信息</title>
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
		
		table {
			margin-top: 10px;
		}
		h4 {
			margin-top: 25px;
		}
	</style>
	
	<body>
		<h3 class="text-center">购买信息</h3>
   		<table class="table table-striped table-bordered ">
   			<thead>
   				<tr>
   					<th>产品</th>
   					<th>单价</th>
   					<th>数量</th>
   					<th>价格</th>
   				</tr>
   			</thead>
   			<tbody>
   				<s:iterator value="buies" status="L">
   					<tr>
   						<td><s:property value="product.name" /></td>
   						<td><s:property value="product.price" /></td>
   						<td><s:property value="number" /></td>
   						<td><s:property value="product.price * number" /></td>
					</tr>
   				</s:iterator>
   				<tfoot><tr><td colspan=4 style="text-align: center;">产品总价 ¥ <s:property value="buyer.price" /></td></tr></tfoot>
   			</tbody>
   		</table>
   		<table class="table table-bordered ">
   			<thead>
   				<tr>
   					<th colspan=2>邮寄信息</th>
   				</tr>
   			</thead>
   			<tbody>
				<tr>
					<td>姓名</td>
					<td><s:property value="buyer.name" /></td>
				</tr>
				<tr>
					<td>电话</td>
					<td><s:property value="buyer.phone" /></td>
				</tr>
				<tr>
					<td>地址</td>
					<td><s:property value="buyer.address" /></td>
				</tr>
				<%-- <tr>
					<td>邮费<br><small>(根据实际情况可能有所调整)</small></td>
					<td>¥ <s:property value="buyer.freight" /></td>
				</tr> --%>
   			</tbody>
   			<tfoot>
   				<tr>
	   				<td style="text-align: center;" colspan=2>
	   					邮费 ¥ <s:property value="buyer.freight" />
	   					<br>
	   					<s:if test="buyer.paid < 1">
	   						<small>(根据实际情况可能有所调整)</small>
	   					</s:if>
	   				</td>
				</tr>
			</tfoot>
   		</table>	  
   		
   		<s:if test="buyer.paid < 1">
	   		<table class="table table-bordered ">
	   			<thead>
	   				<tr>
	   					<th style="text-align: center;">请添加以下客服微信号完成付款</th>
	   				</tr>
	   			</thead>
	   			<tbody>
					<tr>
						<td style="text-align: center;">
							<img width="75%" src='../img/erweima.jpg'/>
							<br>
							<small>(长按二维码→识别图中二维码)</small>
						</td>
					</tr>
	   			</tbody>
	   			<tfoot style='color:red'><tr><td style="text-align: center;">共需支付 ¥ <s:property value="buyer.tprice" /></td></tr></tfoot>
	   		</table>	
	   		<p class="text-center"><small style="color:grey">关闭本页面前可截图留下记录<br>也可以在公众号【查询订单】功能中查询</small></p>
   		</s:if>
   		<s:elseif test="buyer.sent < 1">
	   		<p class="text-center"><strong style="color:red">已完成支付,我们正在快马加鞭打包中...</strong></p>
   		</s:elseif>
   		<s:else>
	   		<p class="text-center"><strong style="color:red">产品已交付快递，请耐心等候<br>如长时间收不到请联系客服</strong></p>
   		</s:else>
   		<p class="text-center"><a href="./list">再下一单</a></p>
	</body>
	
</html>
