<%@ page contentType="text/html; charset=UTF-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<title>快递单管理</title>
	<link rel="stylesheet" type="text/css" href="../js/themes/metro/easyui.css">
	<link rel="stylesheet" type="text/css" href="../js/themes/mobile.css">
	<link rel="stylesheet" type="text/css" href="../js/themes/icon.css">
	<script type="text/javascript" src="../js/jquery.min.js"></script>
	<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../js/jquery.easyui.mobile.js"></script>
	<script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
</head>
<body style="max-width:960px;">
	<table id="dg" data-options="
				header:'#hh',
				iconCls: 'icon-edit',
				singleSelect: true,
				fit:true,
				fitColumns:true,
				border: true,
				scrollbarSize: 0,
				onClickRow: onClickRow
			">
		<thead>
			<tr>
				<th data-options="field:'id',width:30">序号</th>
				<th data-options="field:'name',width:50,editor:'textbox'">名字</th>
				<th data-options="field:'phone',width:75,editor:'numberbox'">电话</th>
				<th data-options="field:'number',width:90,editor:'numberbox'">单号</th>
				<th data-options="field:'company',width:50,editor:{'type':'combobox', options:{editable:false,  
						data:  
						[  
						{'text':'中通'},  
						{'text':'顺丰'},  
						{'text':'圆通'},  
						{'text':'韵达'},  
						{'text':'邮政'},  
						{'text':'EMS'},  
						{'text':'天天'},  
						{'text':'世纪'},  
						{'text':'全峰'}
						],  
						valueField:'text',  
						textField:'text'}}">公司</th>
			</tr>
		</thead>
	</table>
	<div id="hh">
		<div class="m-toolbar">
			<div class="m-left">
			  <button type="button" class="btn" id="prev">←</button>
	  		  <s:textfield key="historyDate" style="height:25px;width:105px;" />
		      <button type="button" class="btn" id="next">→</button>				      
			</div>
			<div class="m-right">
	  		  <s:textfield key="pattern"  style="height:25px;width:105px;" placeholder="名字/电话" />
		      <button type="button" class="btn" id="search" onclick="search()">搜索</button>				      
			</div>
		</div>
		<div class="m-toolbar">
			<div class="m-title">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="add()">新增</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">保存</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject()">撤销</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="track()">物流</a>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		var today = new Date();
		var DATE = dateToString(today);
		$(function(){
			// store tab
			if (localStorage.expressShowDate!=null) {
				DATE = localStorage.expressShowDate;
			}
			$("#historyDate").val(DATE);
			
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
			
			refreshTable();
		});
		
		function saveDate() {
			localStorage.expressShowDate = $("#historyDate").val();
			DATE = $("#historyDate").val().trim();
			refreshTable();
		}
		
		function refreshTable(date) {
			$('#dg').datagrid({
				url: './listAjax?date='+DATE
			});
		}
		
		function dateToString(date){
			return date.getFullYear() + "-" + (date.getMonth()+1) + "-" + date.getDate();
		}
		
		var editIndex = undefined;
		function endEditing(){
			if (editIndex == undefined){return true}
			if ($('#dg').datagrid('validateRow', editIndex)){
				$('#dg').datagrid('endEdit', editIndex);
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
		}
		function onClickRow(index){
			if (editIndex != index){
				if (endEditing()){
					$('#dg').datagrid('selectRow', index)
							.datagrid('beginEdit', index);
					editIndex = index;
				} else {
					$('#dg').datagrid('selectRow', editIndex);
				}
			}
		}
		function removeit(){
			if (editIndex == undefined){return}
			$('#dg').datagrid('cancelEdit', editIndex)
					.datagrid('deleteRow', editIndex);
			editIndex = undefined;
		}
		function add(){
			if (endEditing()){
				var lastNumber = $("td[field='number']:last div").html();
				$('#dg').datagrid('appendRow', {'number': lastNumber, 'company':'中通'});
			}
		}
		function search(){
			$('#dg').datagrid({
				url: './searchAjax?pattern='+$("#pattern").val()
			});
		}
		function track() {
			var row = $('#dg').datagrid('getData').rows[editIndex];
			//$.messager.alert("物流信息", "<ul><li>asfdasd</li><li>asfsdf</li><li>fdsfa</li></ul>");
			if (row) {
				$.get("./trackQuery?id=" + row.id, function(data){
					data = eval("(" + data + ")")
					console.info(data)
					console.info(data.Success)
					if (data.Success) {
						var text = "<ul>";
						$.each(data.Traces, function(){
							text += "<li>" + this.AcceptTime + "<br>" + this.AcceptStation + "</li><br>";
						})
						text +="</ul>";
						$.messager.alert("物流信息", text);
					} else {
						$.messager.alert("查询失败", "单号不正确或物流公司不匹配！");
					}
				});
			}
		}
		function accept(){
			if (endEditing()){
				if ($('#dg').datagrid('getChanges').length) {
				    var inserted = $('#dg').datagrid('getChanges', "inserted");
				    var deleted = $('#dg').datagrid('getChanges', "deleted");
				    var updated = $('#dg').datagrid('getChanges', "updated");
				      
				    var effectRow = new Object();
				    if (inserted.length) {
				        transf(effectRow, inserted, "inserted");
				    }
				    if (deleted.length) {
				        transf(effectRow, deleted, "deleted");
				    }
				    if (updated.length) {
				        transf(effectRow, updated, "updated");
				    }
				    $.post("./commit", effectRow, function(msg) {
				        if(msg.status){
				            $.messager.alert("提示", "提交成功！");
				            $('#dg').datagrid('acceptChanges');
				        } else {
					        $.messager.alert("提示", msg.info);
				        }
				    }, "JSON").error(function() {
				        $.messager.alert("提示", "提交错误了！");
				    });
				} 
			}
		}
		function reject(){
			$('#dg').datagrid('rejectChanges');
			editIndex = undefined;
		}
		
		function transf(param, obj, name) {
			for(var i=0;i<obj.length;i++){
				if (obj[i].id)
					param[name+"[" + i + "].id"] = obj[i].id;
				if (obj[i].name)
					param[name+"[" + i + "].name"] = obj[i].name;
				if (obj[i].phone)
					param[name+"[" + i + "].phone"] = obj[i].phone;
				if (obj[i].number)
					param[name+"[" + i + "].number"] = obj[i].number;
				if (obj[i].company)
					param[name+"[" + i + "].company"] = obj[i].company;
					
				param[name+"[" + i + "].date"] = DATE + " 00:00:00";
			}
		}
	</script>
</body>
</html>
