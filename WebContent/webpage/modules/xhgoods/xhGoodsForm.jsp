<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<title>商品管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/ueditor/themes/default/css/ueditor.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" charset="utf-8" src="${ctxStatic}/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${ctxStatic}/ueditor/ueditor.all.js"></script>
	<script type="text/javascript" charset="utf-8" src="${ctxStatic}/pingying/pinying.js"></script>
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
			debugger;
			 var src = $("#oldImg").attr("src");
			 var src1 = $("#oldImg1").attr("src");
			 var src2 = $("#oldImg2").attr("src");
			 var src3 = $("#oldImg3").attr("src");
			 var src4 = $("#oldImg4").attr("src");
			 var src5 = $("#oldImg5").attr("src");
			if(src ===""){
				$("#btnR").hide();
				$("#oldImg").hide();
				$("#btnC").show();
				$("#reInput").show();
			}else{
				$("#btnC").hide();
				$("#reInput").hide();
				$("#btnR").show();
				$("#oldImg").show();
			}
			if(src1 ===""){
				$("#btnR1").hide();
				$("#oldImg1").hide();
				$("#btnC1").show();
				$("#reInput1").show();
			}else{
				$("#btnC1").hide();
				$("#reInput1").hide();
				$("#btnR1").show();
				$("#oldImg1").show();
			}
			if(src2 ===""){
				$("#btnR2").hide();
				$("#oldImg2").hide();
				$("#btnC2").show();
				$("#reInput2").show();
			}else{
				$("#btnC2").hide();
				$("#reInput2").hide();
				$("#btnR2").show();
				$("#oldImg2").show();
			}
			if(src3 ===""){
				$("#btnR3").hide();
				$("#oldImg3").hide();
				$("#btnC3").show();
				$("#reInput3").show();
			}else{
				$("#btnC3").hide();
				$("#reInput3").hide();
				$("#btnR3").show();
				$("#oldImg3").show();
			}
			if(src4 ===""){
				$("#btnR4").hide();
				$("#oldImg4").hide();
				$("#btnC4").show();
				$("#reInput4").show();
			}else{
				$("#btnC4").hide();
				$("#reInput4").hide();
				$("#btnR4").show();
				$("#oldImg4").show();
			}
			if(src5 ===""){
				$("#btnR5").hide();
				$("#oldImg5").hide();
				$("#btnC5").show();
				$("#reInput5").show();
			}else{
				$("#btnC5").hide();
				$("#reInput5").hide();
				$("#btnR5").show();
				$("#oldImg5").show();
			}
			
			$("#btnR").click(function(){
				$("#btnR").hide();
				$("#oldImg").hide();
				$("#btnC").show();
				$("#reInput").show();
			});
			
			$("#btnC").click(function(){
				$("#btnC").hide();
				$("#reInput").hide();
				$("#btnR").show();
				$("#oldImg").show();
			});
			$("#btnR1").click(function(){
				$("#btnR1").hide();
				$("#oldImg1").hide();
				$("#btnC1").show();
				$("#reInput1").show();
			});
			
			$("#btnC1").click(function(){
				$("#btnC1").hide();
				$("#reInput1").hide();
				$("#btnR1").show();
				$("#oldImg1").show();
			});
			$("#btnR2").click(function(){
				$("#btnR2").hide();
				$("#oldImg2").hide();
				$("#btnC2").show();
				$("#reInput2").show();
			});
			
			$("#btnC2").click(function(){
				$("#btnC2").hide();
				$("#reInput2").hide();
				$("#btnR2").show();
				$("#oldImg2").show();
			});
			$("#btnR3").click(function(){
				$("#btnR3").hide();
				$("#oldImg3").hide();
				$("#btnC3").show();
				$("#reInput3").show();
			});
			
			$("#btnC3").click(function(){
				$("#btnC3").hide();
				$("#reInput3").hide();
				$("#btnR3").show();
				$("#oldImg3").show();
			});
			$("#btnR4").click(function(){
				$("#btnR4").hide();
				$("#oldImg4").hide();
				$("#btnC4").show();
				$("#reInput4").show();
			});
			
			$("#btnC4").click(function(){
				$("#btnC4").hide();
				$("#reInput4").hide();
				$("#btnR4").show();
				$("#oldImg4").show();
			});
			$("#btnR5").click(function(){
				$("#btnR5").hide();
				$("#oldImg5").hide();
				$("#btnC5").show();
				$("#reInput5").show();
			});
			
			$("#btnC5").click(function(){
				$("#btnC5").hide();
				$("#reInput5").hide();
				$("#btnR5").show();
				$("#oldImg5").show();
			});
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
			var ue = UE.getEditor('xhUE');
		});
	</script>
	
	

</head>
<body class="hideScroll">
		<form:form id="inputForm" modelAttribute="xhGoods" action="${ctx}/xhgoods/xhGoods/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<%-- <td class="width-15 active"><label class="pull-right">商品种类：</label></td>
					<td class="width-35">
						<sys:gridselect url="${ctx}/xhgoods/xhGoods/selectxhCategory" id="xhCategory" name="xhCategory.id"  value="${xhGoods.xhCategory.id}"  title="选择商品种类" labelName="xhCategory.name" 
						 labelValue="${xhGoods.xhCategory.name}" cssClass="form-control required" fieldLabels="name" fieldKeys="name" searchLabel="name" searchKey="name" ></sys:gridselect>
					</td> --%>
					
					<td class="width-15 active"><label class="pull-right">商品种类：</label></td>	
					<td class="width-35" ><sys:treeselect id="xhCategory" name="xhCategory.id" value="${xhGoods.xhCategory.id}" labelName="xhCategory.name" labelValue="${xhGoods.xhCategory.name}"
					title="商品类别" url="/xhcategory/xhCategory/treeData" cssClass="form-control required"/></td>
					
					
					
					<td class="width-15 active"><label class="pull-right">商品名称：</label></td>
					<td class="width-35">
						<form:input path="name" htmlEscape="false" id="txtChinese" class="form-control " onKeyUp="query()"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">英文缩写：</label></td>
					<td class="width-35">
						<form:select path="initial" id="select11"  htmlEscape="false" class="form-control " >
						</form:select>
					</td>
					<td class="width-15 active"><label class="pull-right">鲜花物语：</label></td>
					<td class="width-35">
						<form:input path="title" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">价格单位：</label></td>
					<td class="width-35">
						<form:select path="unit" class="form-control ">
							<form:options items="${fns:getDictList('unit')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
					<td class="width-15 active"><label class="pull-right">商品单位：</label></td>
					<td class="width-35">
						<form:select path="bigUnit" class="form-control ">
							<form:options items="${fns:getDictList('bigunit')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">最低定价：</label></td>
					<td class="width-35">
						<form:input path="price" htmlEscape="false"    class="form-control "  />
					</td>
					<td class="width-15 active"><label class="pull-right">最高定价：</label></td>
					<td class="width-35">
						<form:input path="maxPrice" htmlEscape="false"    class="form-control "  />
					</td>
					
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">商品上下架：</label></td>
					<td class="width-35">
						<form:radiobuttons path="status" items="${fns:getDictList('xh_status')}"  itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</td>
					<td class="width-15 active"><label class="pull-right">商品积分：</label></td>
					<td class="width-35">
						<form:input path="integral" htmlEscape="false"    class="form-control "  onkeyup="this.value=this.value.replace(/\D/g, '')"/>
					</td>
					<%-- <td class="width-15 active"><label class="pull-right">商品库存：</label></td>
					<td class="width-35">
						<form:input path="xhInventory" htmlEscape="false"    class="form-control "  onkeyup="this.value=this.value.replace(/\D/g, '')"/>
					</td> --%>
				</tr>
				<tr>
					<td class="width-15 active"><label id="td4" class="pull-right">信息图片1：</label></td>
							<td class="width-35" colspan="3">
								<span id="td3">
									<img id="oldImg" src="${xhGoods.uploadFile.relaPath }" width="150px" height="150px" />
									<input id="reInput" type="file" name="imgNews1" />
									<input type="button" id="btnR"  value="重新上传" />
									<input type="button" id="btnC"  value="取消上传" />
								</span>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label id="td4" class="pull-right">信息图片2：</label></td>
							<td class="width-35" colspan="3">
								<span id="td3">
									<img id="oldImg1" src="${xhGoods.uploadFile1.relaPath }" width="150px" height="150px" />
									<input id="reInput1" type="file" name="imgNews2" />
									<input type="button" id="btnR1"  value="重新上传" />
									<input type="button" id="btnC1"  value="取消上传" />
								</span>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label id="td4" class="pull-right">信息图片3：</label></td>
							<td class="width-35" colspan="3">
								<span id="td3">
									<img id="oldImg2" src="${xhGoods.uploadFile2.relaPath }" width="150px" height="150px" />
									<input id="reInput2" type="file" name="imgNews3" />
									<input type="button" id="btnR2"  value="重新上传" />
									<input type="button" id="btnC2"  value="取消上传" />
								</span>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label id="td4" class="pull-right">信息图片4：</label></td>
							<td class="width-35" colspan="3">
								<span id="td3">
									<img id="oldImg3" src="${xhGoods.uploadFile3.relaPath }" width="150px" height="150px" />
									<input id="reInput3" type="file" name="imgNews4" />
									<input type="button" id="btnR3"  value="重新上传" />
									<input type="button" id="btnC3"  value="取消上传" />
								</span>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label id="td4" class="pull-right">信息图片5：</label></td>
							<td class="width-35" colspan="3">
								<span id="td3">
									<img id="oldImg4" src="${xhGoods.uploadFile4.relaPath }" width="150px" height="150px" />
									<input id="reInput4" type="file" name="imgNews5" />
									<input type="button" id="btnR4"  value="重新上传" />
									<input type="button" id="btnC4"  value="取消上传" />
								</span>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label id="td4" class="pull-right">信息图片6：</label></td>
							<td class="width-35" colspan="3">
								<span id="td3">
									<img id="oldImg5" src="${xhGoods.uploadFile5.relaPath }" width="150px" height="150px" />
									<input id="reInput5" type="file" name="imgNews6" />
									<input type="button" id="btnR5"  value="重新上传" />
									<input type="button" id="btnC5"  value="取消上传" />
								</span>
					</td>
				</tr>
				
				<tr >
					<td class="width-15 active"><label class="pull-right">商品介绍：</label></td>
					<td  class="width-35" colspan="3">
						<div>
							<form:textarea path="description"  id="xhUE" />
                        </div>
					</td>
		  		</tr>
		 	</tbody>
		</table>
	</form:form>
	
		<script type="text/javascript">
			debugger;
			//根据文本框输入的汉字自动获取汉字拼音首字母到下拉列表中，支持多音字，需引入库pinying.js
			function query(){
		    var str = document.getElementById("txtChinese").value.trim();
		    if(str == "") return;
		    var arrRslt = makePy(str);
		    //循环将值到下拉框
		    var option = null;
		    document.getElementById("select11").innerHTML="";//清空下拉框
		    var first = document.getElementById("select11");
		    for(var j=0;j<arrRslt.length;j++){
				var obj = document.getElementById("select11");
				obj.add(new Option(arrRslt[j],arrRslt[j]));
	 	   }
		}
 	</script>
  </body>
</html>