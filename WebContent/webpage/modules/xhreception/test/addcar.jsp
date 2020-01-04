<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>加入购物车</title>
		<link rel="stylesheet" type="text/css" href="static/xhreception/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="static/xhreception/css/arguments.css"/>
		<script type="text/javascript" src="static/xhreception/js/jquery.js"></script>
		<script type="text/javascript" src="static/xhreception/js/layer/layer.js"></script>
		<script type="text/javascript" src="static/xhreception/js/arguments.js"></script>
	</head>
	<body>
		<div class="gdinfo">
			<img src="${xhGoods.uploadFile.relaPath}"/>
			<div class="gdname">
				<h5>${xhGoods.name}</h5>
				<p class="iteminfo_price"><m>￥</m><span class="sys_item_price">${xhGoods.price}-${xhGoods.maxPrice}</span></p>
			</div>
		</div>
		<div class="iteminfo_buying">
		<!--规格属性-->
			<div class="sys_item_spec">
				<c:if test="${!empty xhRules}">
				<dl class="clearfix iteminfo_parameter sys_item_specpara">
					<dt>规格：</dt>
					<dd>
						<ul class="sys_spec_text">
							<c:forEach items="${xhRules}" var="xhRules">
							<li data-aid="3" name="${xhRules.unitPrice}"><a href="javascript:;" title="9枝盒装玫瑰花束" >${xhRules.rule}</a></li>
							<!-- <li data-aid="4"><a href="javascript:;" title="11枝盒装玫瑰花束">11枝盒装玫瑰花束</a></li>
							<li data-aid="5"><a href="javascript:;" title="19枝盒装玫瑰花束">19枝盒装玫瑰花束</a></li> -->
							</c:forEach>
						</ul>
					</dd>
				</dl>
				</c:if>
				<c:if test="${!empty xhColors}">
				<dl class="clearfix iteminfo_parameter sys_item_specpara">
					<dt>颜色：</dt>
					<dd>
						<ul class="sys_spec_text">
							<c:forEach items="${xhColors}" var="xhColors">
							<li data-aid="6" name="0"><a href="javascript:;" title="红色">${xhColors.color}</a></li>
							<!-- <li data-aid="7"><a href="javascript:;" title="香槟色">香槟色</a></li>
							<li data-aid="8"><a href="javascript:;" title="粉色">粉色</a></li> -->
							</c:forEach>
						</ul>
					</dd>
				</dl>
				</c:if>
				<%-- <c:if test="${xhRims != null}">
				<dl class="clearfix iteminfo_parameter sys_item_specpara">
					<dt>周边配件：</dt>
					<dd>
						<ul class="sys_spec_text">
							<c:forEach items="${xhRims}" var="xhRims">
							<li data-aid="9" name="${xhRims.rimPrice}"><a href="javascript:;" title="保鲜剂4包">${xhRims.rimName}</a></li>
							</c:forEach>
							<li data-aid="10"><a href="javascript:;" title="不需要">不需要</a></li>
						</ul>
					</dd>
				</dl>
				</c:if> --%>
				<c:if test="${!empty xhTimes}">
				<dl class="clearfix iteminfo_parameter sys_item_specpara">
					<dt>收花时间：</dt>
					<dd>
						<ul class="sys_spec_text">
							<c:forEach items="${xhTimes}" var="xhTimes">
							<li data-aid="11" name="0"><a href="javascript:;" title="">${xhTimes.xhTime}</a></li>
							</c:forEach>
						</ul>
					</dd>
				</dl>
				</c:if>
				<dl class="clearfix iteminfo_parameter sys_item_specpara">
					<dt class="gnum">购买数量：</dt>
					<dd class="gopnm">
						<div class="optnum">
						<div class="optnumdec left">-</div>
						<input type="text" value="0" class="shang"/>
						<div class="optnumadd left">+</div>
					</div>
					</dd>
				</dl>
				<dl class="clearfix iteminfo_parameter sys_item_specpara mb-50">
					<dt class="bzhu ">卡片留言：</dt>
					<dd class="bzhucotn">
						<input type="text" />
					</dd>
				</dl>
			</div>						
	<!--规格属性-->
	</div>
	<!--加入购物车-->
	<div class="btoopt">
		<button id="addcar" class="adc" style="background:#ff557b ; color: #fff;">加入购物车</button>
	</div>
		<script type="text/javascript">
		$("#addcar").click(function(){
			var gid ="${xhGoods.id}";
			var rmarks = $(".selected").text();
			var price = $(".sys_item_price").text();
			var num = $("#num").val();
			var amount = price * num ;
			$.post("addcar","gid="+gid+"&rmarks="+rmarks+"&num="+num+"&price="+price+"&amount="+amount);
		});
	</body>
</html>
