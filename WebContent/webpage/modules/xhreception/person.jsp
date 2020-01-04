<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>个人中心</title>
		<link rel="stylesheet" type="text/css" href="static/xhreception/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="static/xhreception/css/base/base.css"/>
		<link rel="stylesheet" href="static/xhreception/css/personcet.css" />
	</head>
	<body>
		<div class="top">
			<img src="${xhUser.pic}"/>
			<p>${xhUser.username}</p>
		</div>
		<!--我的订单-->
		<div class="myord">
			<div class="tpline"><span>我的订单</span><a href="personOrder">查看全部订单</a></div>
			<div class="ord">
				<ul>
					<li><a href="personOrder">
						<img src="static/xhreception/images/pd1.png"/>
						<span>待付款</span>
					</a></li>
					<li><a href="personOrder">
						<img src="static/xhreception/images/pd2.png"/>
						<span>待发货</span>
					</a></li>
					<li><a href="personOrder">
						<img src="static/xhreception/images/pd3.png"/>
						<span>待收货</span>
					</a></li>
					<li><a href="personOrder">
						<img src="static/xhreception/images/pd4.png"/>
						<span>待评价</span>
					</a></li>
				</ul>
			</div>
		</div>
		
		<div class="orp">
				<ul>
					<li><a href="personOrder">
						<img src="static/xhreception/images/pd5.png" alt="" />
						<span>我的订单</span>
					</a></li>
					<!-- <li><a href="#">
						<img src="static/xhreception/images/pd6.png" alt="" />
						<span>账号设置</span>
					</a></li> -->
					<li><a href="personInfo">
						<img src="static/xhreception/images/pd7.png" alt="" />
						<span>个人信息</span>
					</a></li>
					<li><a href="addressList">
						<img src="static/xhreception/images/pd8.png" alt="" />
						<span>收货地址</span>
					</a></li>
					<li><a href="IntegralCenter">
						<img src="static/xhreception/images/pd9.png" alt="" />
						<span>积分管理</span>
					</a></li>
					<li><a href="integral">
						<img src="static/xhreception/images/pd10.png" alt="" />
						<span>积分商城</span>
					</a></li>
				</ul>
		</div>
		<footer>
			<ul>
				<%@ include file="webBase.jsp" %>
			</ul>
		</footer>
	</body>
</html>
