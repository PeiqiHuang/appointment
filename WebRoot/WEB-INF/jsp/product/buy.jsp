<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<html>

	<head>
    	<title>请确认产品和数量</title>
    	<script src="../js/jquery.min.js"></script>
    	<script src="../js/localstorage.js"></script>
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
		img {
			margin-bottom:5px;
		}
	</style>
	
	<body>
		<s:form id="form" action="submit" theme="bootstrap" cssClass="well form-vertical" label="购买清单"  style="max-width:600px;margin:0 auto;">
			
			<s:if test="ids==null && productValid==0">
    			<s:select label="请选择产品" id="products" list="products" listKey="id" listValue="%{alias+' ¥'+price}" emptyOption="true"/>
				<%-- <s:if test="buyerid!=null && buyerid!='' || productValid==0">
	    			<s:select label="请选择产品(可多次选择)" id="products" list="products" listKey="id" listValue="%{alias+' ¥'+price}" emptyOption="true"/>
	   			</s:if>
	   			<s:else>
	    			<s:select label="请选择产品(可多次选择)" id="products" list="products" listKey="id" listValue="%{name+' ¥'+price}"  emptyOption="true"/>
	   			</s:else>  --%>
   			</s:if>
   			
    		
    		<table class="table table-striped table-bordered ">
    			<thead>
    				<tr>
    					<th colspan=1>产品</th>
    					<th>数量</th>
    					<th>价格</th>
    					<th></th>
    				</tr>
    			</thead>
    			<tbody id="items">
    				<s:iterator value="buies" status="L">
    					<tr product=<s:property value='product.id' /> number=<s:property value='number' /> >
    						<%-- <td>
    							<img class="img-rounded" width="130px" src="<s:property value='product.link' />" />
    							<p><s:property value='product.alias' /></p>
    						</td> --%>
    						<td>
    							<s:if test="buyerid!=null && buyerid!=''">
    								<s:property value='product.alias' />
					   			</s:if>
					   			<s:else>
    								<s:property value='product.name' />
   								</s:else>
    						</td>
    						<td price=<s:property value='product.price' />></td>
    						<td><s:property value='product.price' /></td>
    						<td><button class="btn btn-block" id="itemDelBtn" type="button">删除</button></td>
   						</tr>
    				</s:iterator>
    			</tbody>
    			<%-- <tfoot><tr><td colspan=3 style="text-align:right">总价：¥ <span id="sum">0</span></span></td><tr></tfoot> --%>
    		</table>
    		<p class="text-right">产品总价：¥ <span id="sum" name="price">
	    		<s:property value="%{buyer==null || buyer.price==null? 0 : buyer.price}" /></span>
    		<%-- <s:if test="buyerid!=null && buyerid!=''">
	    		<s:property value="buyer.price" /></span>
   			</s:if>
   			<s:else>
	    		0
   			</s:else> --%>
    		</p>
    		
   			<s:if test="buyerid!=null && buyerid!=''  || productValid==0">
   				<s:textfield label="邮费" key="buyer.freight" />
   				<s:select label="支付" id="paidSelect" name="buyer.paid" list="#{0:'否', 1:'是'}"/>
   				<s:select label="发货" id="sentSelect" name="buyer.sent" list="#{0:'未发货', 1:'诊所发公', 2:'私人发', 3:'诊所发私'}"/>
   			</s:if>
   			<s:else>
	    		<s:select label="请选择邮寄地址" id="addSelect" list="#{1:'东莞市内', 2:'广东省内', 3:'广东省外'}"/>
	    		<p class="text-right">邮费：¥ 
	    			<span id="freight" name="freight">8</span>
		    		<s:hidden name="buyer.freight" value="8"/>
	    		</p>
	    		<p class="text-right">（邮费超重等原因付款时可能会变动）</p>
   			</s:else>
    		
    		
    		<hr>
    		<h4>请填写邮寄信息</h4>
    		<s:hidden name="productValid" />
    		<s:hidden name="buyer.id" />
    		<s:textfield key="buyer.name" label="收件人" />
    		<s:textfield key="buyer.phone"  label="联系电话" />
    		<s:textfield key="buyer.address" label="详细地址" />
    		<s:textfield key="buyer.remark" label="备注" />
    		<s:if test="buyerid!=null && buyerid!=''  || productValid==0">
    			<button type="button" id="go" class="btn btn-block btn-large btn-success">确认订单</button>
    		</s:if>
		</s:form>	
	  	
	  	<s:if test="(buyerid==null || buyerid=='') && productValid!=0">
		 <div class="btn-group btn-block" style="position:fixed;bottom:0;left:0;z-index:90;" id="btn-group">
	       	<button class="btn btn-large btn-info" id="back" onclick="JavaScript:history.back(-1);"><i class="icon-shopping-cart icon-white"></i> 返回购物车</span>
	       	<button class="btn btn-large btn-success" id="go"><i class="icon-hand-up icon-white"></i> 确认订单</button>
         </div>
   		</s:if>
        
        <s:hidden id="duplicate" value="false" />
	</body>
	
	
	<script type="text/javascript">
		var TOTAL = 0;
		
		$(document).ready(function(){
			$("input").css("height", "40px");
			
			//init
			if ($("#form_productValid").val() == "") {
				$("#form_productValid").val(1);
			}
			
			if ($("#form_buyer_id").val() == "" && $("#form_productValid").val()!=0) {
				// 居中
				var tab_width = $("#btn-group").width()/2-38;
				$("#back").width(tab_width);
				$("#go").width(tab_width);
				$("#back").css("border-top-left-radius", 0);
				$("#back").css("border-bottom-left-radius", 0);
				$("#go").css("border-top-right-radius", 0);
				$("#go").css("border-bottom-right-radius", 0);
				// 读取缓存邮寄信息
				var POST_INFO = getPost();
				$("#form_buyer_name").val(POST_INFO.name);
				$("#form_buyer_phone").val(POST_INFO.phone);
				$("#form_buyer_address").val(POST_INFO.address);
			}
			
			//初始化产品数量
			$("#items").find("tr").each(function(){
				var number = parseInt($(this).attr("number"));
				var price = parseInt($(this).children().eq(1).attr("price"));
				$(this).children().eq(1).html(getNumberSelect(number));
				$(this).children().eq(2).text(number*price);
				TOTAL += number*price;
			});
			
			// ----------- event -----------
			
			$(document).on("change", "#products", function(){
				addProduct($(this).val(), $(this).find("option:selected").text());
			});
			
			$(document).on("change", "#numberSelect", function(){
				// if ($(this).attr("id") == "products") return;
				var price = parseInt($(this).parent().attr("price"));
				var number = parseInt($(this).val());
				$(this).parent().parent().attr("number", number);
				price*= number;
				var thisPrice = parseInt($(this).parent().next().text());
				$(this).parent().next().text(price);
				TOTAL += price - thisPrice;
				changeTotal();
			});
			
			$(document).on("change", "#addSelect", function(){
				var freight = 0;
				if ($(this).val() == 1) {
					freight = 8;
				} else if ($(this).val() == 2) {
					freight = 10;
				} else {
					freight = 12;
				}
				$("#freight").text(freight);
				$("#form_buyer_freight").val(freight);
			});
			
			$("#go").click(function(){
				if ($("#items").children().length < 1) {
					if ("" == $("#form_buyer_remark").val()) {
						alert("请至少购买一样产品或在备注中填上你想要的产品");
						return;
					}
				}
				if ($("#form_buyer_name").val().trim() == "") {
					alert("请填上邮寄姓名");
					return;
				}
				if ($("#form_buyer_phone").val().trim() == "") {
					alert("请填上邮寄电话");
					return;
				}
				if ($("#form_buyer_address").val().trim() == "") {
					alert("请填上邮寄地址");
					return;
				}
				
				if ($("#form_buyer_id").val() == "") {
					// 清空本地ids缓存
					clearLocal();
					// 保存邮寄信息到缓存
					savePost({"name":$("#form_buyer_name").val(), "phone":$("#form_buyer_phone").val(), "address":$("#form_buyer_address").val()});
				}
				
				$("#items").children().each(function(i){
					var product = $(this).attr("product");
					var number = $(this).attr("number");
					$("#form").append("<input type='hidden' name='buies["+i+"].product.id' value='"+product+"' />");
					$("#form").append("<input type='hidden' name='buies["+i+"].number' value='"+number+"' />");
				});
				$("#form").append("<input type='hidden' name='buyer.price' value='"+TOTAL+"' />");
				/* $("#form").append("<input type='hidden' name='buyer.freight' value='"+$("#freight").text()+"' />"); */
				$("#form").append("<input type='hidden' name='buyer.tprice' value='"+(TOTAL+parseInt($("#form_buyer_freight").val()))+"' />");
				if ($("#duplicate").val() == "false") {
					$("#duplicate").val("true");
					$("#form").submit();
				} else {
					alert("您已经下单了，请加客服微信号完成支付");
					window.location.href="searchResult?searchPattern="+$("#form_buyer_phone").val();
				}
			});
			
			$(document).on("click", "#itemDelBtn", function(){
				TOTAL -= parseInt($(this).parent().prev().text());
				$("#sum").text(TOTAL);
				$(this).parent().parent().remove();
				delLocal(parseInt($(this).parent().parent().attr("product")));
			});
			
			/* $(document).on("click", "tr td a", function(){
				$("#page").attr("src", $(this).attr("url"));
				$("#modal").modal();
			}); */
		})
		
		function addProduct(id, name) {
			var nameArr = name.split(" ");
			name = nameArr[0];
			var price = parseInt(nameArr[1].substring(1));
			var item = "<tr product='"+id+"' number='1'><td>"+name+"</td><td price='"+price+"'>"+getNumberSelect()+"</td><td>"+price+"</td><td><button class='btn btn-block' id='itemDelBtn'>删除</button></td></tr>";
			$("#items").append(item);
			TOTAL += price;
			changeTotal();
		}
		
		function getNumberSelect(selected){
			var select = "<select id='numberSelect'>";
			for (var i = 1; i<11; i++) {
				if (i == parseInt(selected)){
					select += "<option selected value='"+i+"'>"+i+"</option>";
				} else {
					select += "<option value='"+i+"'>"+i+"</option>";
				}
			}
			select += "</select>";
			return select;
		}
		
		function changeTotal() {
			$("#sum").text(TOTAL);
		}
	</script>
</html>
