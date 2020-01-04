<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>包月信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var validateForm;
		function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		  if(validateForm.form()){
			  $("#inputForm").submit();
			  return true;
		  }
	
		  return false;
		}
		$(document).ready(function() {
			validateForm = $("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			
		});
	</script>
</head>
<body class="hideScroll">
		<form:form id="inputForm" modelAttribute="xhMbuyer" action="${ctx}/xhmbuyer/xhMbuyer/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					
					<td class="width-15 active"><label class="pull-right">包月用户：</label></td>
					<td class="width-35">
						<form:input path="xhUser.username" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">包月商品：</label></td>
					<td class="width-35">
						<form:input path="xhGoods.name" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					
					<td class="width-15 active"><label class="pull-right">包月订单：</label></td>
					<td class="width-35">
						<form:input path="xhOrder.id" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">包月周期：</label></td>
					<td class="width-35">
						<form:input path="mTime" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					
					<td class="width-15 active"><label class="pull-right">开始时间：</label></td>
					<td class="width-35">
						<form:input path="beginTime" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">结束时间：</label></td>
					<td class="width-35">
						<form:input path="endTime" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					
					<td class="width-15 active"><label class="pull-right">包月状态：</label></td>
					<td class="width-35">
						<form:select path="mStatus" class="form-control ">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('m_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
					<td class="width-15 active"><label class="pull-right">备注信息：</label></td>
					<td class="width-35">
						<form:textarea path="remarks" htmlEscape="false" rows="4"    class="form-control "/>
					</td>
				</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>