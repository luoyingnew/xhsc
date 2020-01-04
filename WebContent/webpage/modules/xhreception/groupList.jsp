<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>拼团抢购</title>
		<link rel="stylesheet" type="text/css" href="static/xhreception/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="static/xhreception/css/base/base.css"/>
		<link rel="stylesheet" type="text/css" href="static/xhreception/css/ptitem.css"/>
		<script type="text/javascript" src="static/xhreception/js/jquery.js"></script>
		<script type="text/javascript" src="static/xhreception/js/layer/layer.js"></script>
	</head>
	<body>
		<header>
			<%@ include file="fansbase.jsp" %>
		</header>
		<nav>
			<ul>
				<c:forEach items="${xhFloors}" var="xhFloors">
					<li><a href="${xhFloors.url}">${xhFloors.name}</a></li>
				</c:forEach>
			</ul>
		</nav>
		<section>
			<div class="ptitlist">
				<%--<div class="advtion">
					<img class="img-responsive" src="static/xhreception/images/ptitadv.png"/>
					<div class="advinfo">
						<h1>拼团抢购</h1>
						<p class="kh">限时秒杀 | 精选商品</p>
						<p class="snum">仅剩<span>40</span>件</p>
						<p class="price">￥<span>25</span><s>￥<span>50</span></s></p>
						<p class="tosee"><a href="${xhFloors[0].url}">去看看</a></p>
					</div>
				</div> --%>
				<!--列表-->
				<div class="itemlist">
					<ul>
						<c:forEach items="${xhGroups}" var="xg">
						<li><a href="groupDetail?id=${xg.id}">
							<img src="${xg.xhGoods.uploadFile.relaPath}" class="img-responsive" alt="" />
							<p><span class="tuan">${xg.maxNum}人团</span><span class="pname">${xg.xhGoods.name}</span></p>
							<p class="ppang"><span>${xg.xhGoods.title}</span>#拼团#</p>
							<p class="pprice">￥<span>${xg.xhGoods.price}</span><s>￥${xg.xhGoods.maxPrice}</s></p>
						</a></li>
						</c:forEach>
						<!-- <li><a href="ptorder.html">
							<img src="static/xhreception/images/ptit2.png" class="img-responsive" alt="" />
							<p><span class="tuan">3人团</span><span class="pname">【精选百合】</span></p>
							<p class="ppang"><span>24.9</span>元/束    2束<span>39.9</span>#拼团#</p>
							<p class="pprice">￥<span>24.9</span><s>￥39.9</s></p>
						</a></li>
						<li><a href="ptorder.html">
							<img src="static/xhreception/images/ptit1.png" class="img-responsive" alt="" />
							<p><span class="tuan">3人团</span><span class="pname">【精选百合】</span></p>
							<p class="ppang"><span>24.9</span>元/束    2束<span>39.9</span>#拼团#</p>
							<p class="pprice">￥<span>24.9</span><s>￥39.9</s></p>
						</a></li>
						<li><a href="ptorder.html">
							<img src="static/xhreception/images/ptit2.png" class="img-responsive" alt="" />
							<p><span class="tuan">3人团</span><span class="pname">【精选百合】</span></p>
							<p class="ppang"><span>24.9</span>元/束    2束<span>39.9</span>#拼团#</p>
							<p class="pprice">￥<span>24.9</span><s>￥39.9</s></p>
						</a></li> -->
					</ul>
				</div>
			</div>
		</section>
		
		
		<footer>
			<ul>
				<%@ include file="webBase.jsp" %>
			</ul>
		</footer>
		<script type="text/javascript" src="static/xhreception/js/base.js"></script>
	</body>
</html>
