<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>选择收货地址</title>
		<link rel="stylesheet" type="text/css" href="static/xhreception/css/shouhuodzselt.css"/>
		
		<link rel="stylesheet" type="text/css" href="static/xhreception/css/shouhuodzadd.css"/>
		
	</head>
	<body>
		<div class="box">
			<form action="">
				<ul>
					<c:forEach items="${recAddres}" var="recAddres">
					<li>
						<c:choose>
						<c:when test="${nextLoad.mid !=null and nextLoad.mid != '' }">
							<a href="monthNextLoad?recid=${recAddres.id}">
						</c:when>
						<c:otherwise>
						<c:choose>
							<c:when test="${nextLoad==null or nextLoad == ''}">
							<a href="shopcarNextLoad?recid=${recAddres.id}">
							</c:when>
							<c:otherwise>
							<c:choose>
							<c:when test="${nextLoad.status == 3}">
							<a href="groupNextLoad?recid=${recAddres.id}">
							</c:when>
							<c:otherwise>
							<a href="nextLoad?recid=${recAddres.id}">
							</c:otherwise>
							</c:choose>
							</c:otherwise>
							</c:choose>
						</c:otherwise></c:choose>
						<span>${recAddres.recUser}</span><span>${recAddres.recPhone}</span><br />
						<span class="addres">${recAddres.province}${recAddres.city}${recAddres.area}${recAddres.address}</span></a>
						 <button class="delAddr" aid="${recAddres.id}"><img src="static/xhreception/images/del.png"></button> 
					</li>
					</c:forEach>
					
					<!-- <li>
						<a href="qrorder.html"><span>田花花</span>，<span>18569523541</span><br />
						<span class="addres">宁夏银川市金风区IBI育成中心1号楼1403</span></a>
						<a href="#"><img src="static/xhreception/images/edit.png"/></a>
					</li>
					<li>
						<a href="qrorder.html"><input type="radio" name="addres" id="" value="" /><span>田花花</span>，<span>18569523541</span><br />
						<span class="addres">宁夏银川市金风区IBI育成中心1号楼1403</span></a>
						<a href="#"><img src="static/xhreception/images/edit.png"/></a>
					</li>
					<li>
						<a href="qrorder.html"><input type="radio" name="addres" id="" value="" /><span>田花花</span>，<span>18569523541</span><br />
						<span class="addres">宁夏银川市金风区IBI育成中心1号楼1403</span></a>
						<a href="#"><img src="static/xhreception/images/edit.png"/></a>
					</li> -->
					
				</ul>
				<input type="button" class="add" value="新增地址" />
			</form>
		</div>
		
		<div class="box2">
				
			<form action="addAddr" method="get" id="add">
				<ul>
					<li><h4>收货地址管理</h4>
				<div class="close">&times;</div></li>
					<li><input hidden="hidden" name="recid"></li>
					<li>姓名<input id="username" type="text" name="recUser"  placeholder="收货人姓名"/></li>
					<li>电话<input id="tel" type="text" name="recPhone"  placeholder="收货人电话"/></li>
					<li>地区<input type="text" name="upAddr" id="picker" placeholder="选择省/市/区"/></li>
					<li>详细地址<input id="address" type="text" name="address"  placeholder="街道门牌、楼层房间号"/></li>
				</ul>
				<input type="button" value="保存并使用" onclick="add()" /> 
			</form>
			</div>
			<script src="static/xhreception/js/picker.min.js"></script>
			<script type="text/javascript" src="static/xhreception/js/city.js"></script>
			<script type="text/javascript" src="static/xhreception/js/addres.js"></script>
		
		<script type="text/javascript" src="static/xhreception/js/jquery.js"></script>
		<script type="text/javascript">
			/* 删除 */
			$(".delAddr").click(function(){
				debugger;
				 var result = confirm("真的删除吗？");
				 var id =$(this).attr("aid");
				 if(result){
					$.post("delAddr",{
						"id":id
					},function(res){
						if(res.code === '1'){
							window.location.href="addressList";
						}
					}); 
				 }
			});
				/*添加  */
				$(".add").click(function(){
				debugger;
					$(".box2").show();
				})
				$(".box").find("li").find("img").click(function(){
					$(".box2").show();
				})
				$(".close").click(function(){
					$(".box2").hide();
			});
			function add(){
				$("#add").submit();
			}
		</script>
		<script type="text/javascript">
			$("form input#username").mouseout(function(){
					if($.trim(this.value) === "" || $.trim(this.value).length < 1 ){
						alert("收货人姓名不正确");
					}
				})
				/*验证手机号*/
				$("form input#tel").mouseout(function(){
					var tel = $(this).val();
					if(!(/^1(3|4|5|7|8)\d{9}$/.test(tel))){ 
		        	alert("手机号码有误，请重填");  
					}
				})
				/*详细地址*/
				$("form input#address").mouseout(function(){
					if($.trim(this.value) == ""){
						alert("详细地址不能为空");
					}
				})
		</script>
		
 	</body>
</html>
