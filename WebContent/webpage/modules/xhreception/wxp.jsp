<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<title>正在为您跳转微信支付,请稍后...</title>
<script type="text/javascript"
	src="${ctxStatic}/xhreception/js/jquery.js"></script>
<script src="http://res2.wx.qq.com/open/js/jweixin-1.4.0.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {

		var oid = "${oid}";
		var url = window.location.href;
		$.ajax({
			url : "/wxpay/prepay",
			data : {
				"oid" : oid,
				"url" : url
			},
			contentType : "application/json; charset=utf-8",
			dataType : "json",
			success : function(data) {
							wx.config({
						    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
						    appId: data.appid, // 必填，公众号的唯一标识
						    timestamp: data.timestamp, // 必填，生成签名的时间戳
						    nonceStr: data.nonce_str, // 必填，生成签名的随机串
						    signature: data.signature,// 必填，签名
						    jsApiList: ["chooseWXPay"] // 必填，需要使用的JS接口列表
						});
				
				
				wx.ready(function(){
				    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
							 wx.chooseWXPay({
										timestamp:data.timestamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
										nonceStr:data.nonce_str, // 支付签名随机串，不长于 32 位
										package:"prepay_id=" + data.prepay_id, // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=\*\*\*）
										signType:"MD5", // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
										paySign:data.paysign, // 支付签名
										success: function (res) {
										// 支付成功后的回调函数
											window.location.href="/payNext?oid="+"${oid}";
										}
									}); 
								});
							 }
						});
					});
</script>
</head>
<body>

</body>
</html>
