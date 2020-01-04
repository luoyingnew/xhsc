<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>积分商城管理</title>
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
		<form:form id="inputForm" modelAttribute="pointGoods" action="${ctx}/pointgoods/pointGoods/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">商品名称：</label></td>
					<td class="width-35">
						<form:input path="pname" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">所需积分：</label></td>
					<td class="width-35">
						<form:input path="ppoint" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">商品图：</label></td>
					<td class="width-35" colspan="3">
						<span id="td3">
							<img id="oldImg" src="${pointGoods.uploadFile.relaPath}" width="150px" height="150px" />
							<input id="reInput" type="file" name="imgNews" />
						</span>
					</td>
		  		</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>