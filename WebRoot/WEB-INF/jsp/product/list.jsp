<%@ page contentType="text/html; charset=UTF-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<html>
	<head>
	    <title>谢永年卫生所</title>
	    <script src="../js/jquery.min.js"></script>
	    <script src="../js/localstorage.js"></script>
	    <sb:head/>
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	</head>
	
	<style>
		body {
			margin : 5px
		}
		a {
			color:black;
		}
		a:link{
			text-decoration:none;
		}
		img {
			width:"100%" 
		}
		.selected {
			border-color: #54a954;
			/* border-width: 2px; */
		}
		.modal-div {
			display:none;
			position: fixed;
			top: 0;
			left: 0;
			z-index:98;
			margin: 0;
			padding:10px;
			width:100%;
			height:100%;
			/* opacity:0.95; */
			background-color:white;
			overflow-y: scroll;
		}
		.modal-ul {
			font-size: 18px;
			z-index:99;
			/* margin: 10px 50px; */
			/* width:200px; */
			/* height:400px; */
		}
		.modal-ul li {
			height: 25px;
			padding: 2.5px;
		}
	</style>
	
	<body class="well">
		<div class="row-fluid">
			<div class="span12">
				<div class="page-header">
		            <h3 class="text-center">护理产品目录</h3>
		        </div>
	            <!-- <div class="media well" style="margin-top:-20px;">
			      <div class="media-body">
		            <h5 class="text-center" style="text-decoration:underline;">提示</h5>
					<p>如下方没有你想要的产品，可直接点击下单，在备注中写下你要的产品，加付款微信后确认。</p>
					<p>如下方没有你想要的产品，可长按下方二维码添加客服微信购买。</p>
					<img width="30%" src='../img/erweima.jpg' style="padding: 0 35%;"/>
			      </div>
			    </div> -->
			    <div class="media well" id="tip"  style="margin-top:-20px;">
			      <div class="pull-left">
			        <img class="media-object" width="100px" src="../img/erweima.jpg">
			      </div>
			      <div class="media-body">
			        <h4 class="media-heading text-center">提示</h4>
					<p>如下方没有你想要的产品，可长按左方二维码添加客服微信购买。</p>
			      </div>
			    </div>
		        		
				<!-- Modal -->
			    <div class="modal-div" id="modal">
			    	<div class="modal-ul">
					    <ul class="nav nav-list">
					      <li class="nav-header">分类</li>
					      <li class="active"><a href="#">全部</a></li>
					      <s:iterator value="labels" id="l">
						      <li><a href="#"><s:property value="l" /></a></li>
					      </s:iterator>
					    </ul>
				    </div>
			    </div>
		        <%-- <s:select label="产品分类" id="labels" list="labels"/> --%>
				<!-- <h4 class="text-center"><i class="icon-heart"></i> 保湿乳霜</h4> -->
		        <ul class="thumbnails" id="products">
		          <s:iterator value="products" status="L">
		          	<s:if test="valid > 0">
				      <li class="span12" style="margin-left:0" name="<s:property value='label' />">
					        <div class="thumbnail">
					      	  <a href="<s:property value='link' />">
					          	  <img src="../img/<s:property value='img' />">
						          <h4>
						          	<s:property value="name" /> 
						          	<span class="badge badge-inverse">¥ <s:property value="price" /></span>
						          	<%-- <small>¥<s:property value="price" /></small> --%>
						          	<%-- <span class="pull-right" style="font-size:12px;"> <i class="icon-arrow-right"></i> 产品详情</span> --%>
					          	  </h4>
						          <p><s:property value="description" /></p>
						      	  <p class="text-error">产品详情</p>
						      </a>
					          <hr>
					          <%-- <a href="javascript:void(0)"  name="<s:property value="id" />" id="shopping"/> --%>
					          <button class="btn btn-block btn-large" name="<s:property value="id" />" id="shopping"/>
					          	<!-- <h4 class="text-center"> --><i class="icon-shopping-cart"></i> 加入购物车<!-- </h4> -->
					          <!-- </a> -->
					          </button>
					        </div>
				      </li>
		          	</s:if>
			      </s:iterator>
			    </ul>
			    
			    <div class="btn-group btn-block" style="position:fixed;bottom:0;left:0;z-index:90;" id="btn-group">
			       	<button class="btn btn-large btn-danger" id="clear"><i class="icon-remove-sign icon-white"></i> 清空</span>
			       	<button class="btn btn-large btn-warning" id="search" onclick="location.href='search';"><i class="icon-search icon-white"></i> 查询</span>
			       	<button class="btn btn-large btn-info" id="category"><i class="icon-filter icon-white"></i> 分类</span>
			       	<button class="btn btn-large btn-success" id="go"><!-- <i class="icon-hand-up icon-white"></i>  -->下单 <span class="badge badge-success" id="sum">0</span></button>
		        </div>
			</div>
		</div>
		
	    <s:form id="form" action="./buy">
	    </s:form>
	</body>
	
	
	<script type="text/javascript">
		$(document).ready(function(){
			// 居中
			var tab_width = $("#btn-group").width()/4-38;
			$("#clear").width(tab_width);
			$("#search").width(tab_width);
			$("#category").width(tab_width);
			$("#go").width(tab_width);
			$("#clear").css("border-top-left-radius", 0);
			$("#clear").css("border-bottom-left-radius", 0);
			$("#go").css("border-top-right-radius", 0);
			$("#go").css("border-bottom-right-radius", 0);
			
			/* $("#buy").click(function(){
				if ($("#tip").css("display") == "none")
					$("#tip").css("display", "block");
				else
					$("#tip").css("display", "none");
			}); */
			
			// <br>直接显示问题
			$("p").each(function(){
				$(this).html($(this).text());
			});
			
			$("#clear").click(function(){
				clearSelected();
			});
			
			$(document).on("click", "#shopping", function(){
				if ($(this).parent().hasClass("selected")) {
					// unselected operation
					unselectedItem($(this));
				} else {
					// selected operation
					selectItem($(this));
				}
				shopping(parseInt($(this).attr("name")));
			});
			
			$("#category").click(function(){
				// $("#category-modal-body")
				$("#modal").css("display","block");
			})
			
			$("#go").click(function(){
				//放假不给下单
				//alert("1月23日-2月4日休息，暂停下单！");
				//return;
				var SELECTED_ITEMS = getLocal();
				// console.info(SELECTED_ITEMS)
				if (SELECTED_ITEMS.length < 1) {
					//alert("购物车空空如也~");
					//return;
				} else {
					/* var data = $.param({"ids" : SELECTED_ITEMS}, true);
					$.post("./buy", data, function); */
					$.each(SELECTED_ITEMS, function(i){
						var node = "<input type=hidden name=ids["+i+"] value="+this+" />";
						$("#form").append(node);
					})
				}
				$("#form").submit();
			})
			
			
			
			$(document).on("click", ".modal-ul li", function(){
				$(this).parent().children().each(function(){
					if ($(this).hasClass("active")) {
						$(this).removeClass("active");
						return;
					}
				})
				$(this).addClass("active");
				var label = $(this).text();
				$("#category").text(label);
				$("#modal").css("display", "none");
				filterByLabel(label);
			})
			function filterByLabel(label) {
				$("#products").children().each(function(){
					if ($(this).attr("name").indexOf(label) > -1 || label == "全部") {
						$(this).css("display", "block");
					} else {
						$(this).css("display", "none");
					}
				});
			}
			
			function selectItem($node) {
				$node.parent().addClass("selected");
				$node.addClass("btn-success");
				$node.html("<i class='icon-ok-sign icon-white'></i> 已添加");
				changeSum(1);
			}
			
			function unselectedItem($node) {
				$node.parent().removeClass("selected");
				$node.removeClass("btn-success");
				$node.html("<i class='icon-shopping-cart'></i> 加入购物车");
				changeSum(-1);
			}
			
			function selectItemById(id) {
				$("button").each(function(){
					if (parseInt($(this).attr("name")) == id) {
						selectItem($(this));
						return;
					}
				});
			}
			
			function unselectItemById(id) {
				$("button").each(function(){
					if (parseInt($(this).attr("name")) == id) {
						unselectedItem($(this));
						return;
					}
				});
			}
			
			function clearSelected() {
				var SELECTED_ITEMS = getLocal();
				$.each(SELECTED_ITEMS, function(){
					unselectItemById(this);
				});
				// localStorage.appointment_selected_products = "[]";
				clearLocal();
			}
			
			function initSelected() {
				var SELECTED_ITEMS = getLocal();
				$.each(SELECTED_ITEMS, function(){
					selectItemById(this);
				});
			}
			initSelected();
			
			function shopping(pid) {
				var SELECTED_ITEMS = getLocal();
				var deleteFlag = false;
				$.each(SELECTED_ITEMS, function(i){
					if (this == pid) {
						SELECTED_ITEMS.splice(i, 1);
						deleteFlag = true;
						return;
					}
				});
				if (!deleteFlag) {
					SELECTED_ITEMS.push(pid);
				}
				// console.info(SELECTED_ITEMS)
				saveLocal(SELECTED_ITEMS);
			}
			
			function changeSum(number) {
				$("#sum").text(parseInt($("#sum").text())+number);
			}
			
		})
	</script>
	
</html>