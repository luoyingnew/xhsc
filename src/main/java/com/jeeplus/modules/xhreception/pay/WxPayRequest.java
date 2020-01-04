package com.jeeplus.modules.xhreception.pay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class WxPayRequest {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(WxPayRequest.class);
	
	public String appId;
	public String timeStamp;
	public String nonceStr;
	public String packageString;
	public String signType = "MD5";
	public String paySign;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (appId != null) {
			builder.append("appId=");
			builder.append(appId);
			builder.append("&");
		}
		if (nonceStr != null) {
			builder.append("nonceStr=");
			builder.append(nonceStr);
			builder.append("&");
		}
		if (packageString != null) {
			builder.append("package=");
			builder.append(packageString);
			builder.append("&");
		}
		if (signType != null) {
			builder.append("signType=");
			builder.append(signType);
			builder.append("&");
		}
		if (timeStamp != null) {
			builder.append("timeStamp=");
			builder.append(timeStamp);
			builder.append("");
		}
		if (paySign != null) {
			builder.append("paySign=");
			builder.append(paySign);
		}
		return builder.toString();
	}
	
	public void sign(){
		nonce_str(32);
		String stringSignTemp = toString()+"&key="+PayUtils.KEY;
		LOGGER.info("stringSignTemp={}",stringSignTemp);
		String signValue = BaseUtil.md5(stringSignTemp).toUpperCase();
		LOGGER.info("signValue={}",signValue);
		paySign = signValue;
	}
	
	private void nonce_str(int length){
		/*StringBuilder builder = new StringBuilder(length);  
		for (int i = 0; i < length; i++) {
			builder.append(RandomStringUtils.randomNumeric(1));
		}
		nonceStr = builder.toString();*/
		nonceStr = "123456";
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getPackageString() {
		return packageString;
	}

	public void setPackageString(String packageString) {
		this.packageString = packageString;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getPaySign() {
		return paySign;
	}

	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}
	
	
}
