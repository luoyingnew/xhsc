<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>商品规格管理</title>
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
		<form:form id="inputForm" modelAttribute="xhRule" action="${ctx}/xhrule/xhRule/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">商品信息：</label></td>
					<td class="width-35">
						<form:select path="xhGoods" class="form-control ">
						<form:option value="" label="">---请选择商品---</form:option>
						<c:forEach items="${xlist}" var="xlist">
							<form:option value="${xlist.id}" label="">${xlist.name}</form:option>
							<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</c:forEach>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">商品规格：</label></td>
					<td class="width-35">
						<form:input path="rule" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">商品定价：</label></td>
					<td class="width-35">
						<form:input path="unitPrice" htmlEscape="false"   class="form-control " />
					</td>
				</tr>
		 	</tbody>
		</table>
		     <center>
			     <span style="color:red;font-size: 14px;">注：包月商品的价格请在[商品包月-包月时间设定]中添加!</span>
		     </center>
	</form:form>
</body>
</html>