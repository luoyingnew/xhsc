package com.jeeplus.modules.xhreception.quarts;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.jeeplus.modules.xhbuyer.entity.XhBuyer;
import com.jeeplus.modules.xhbuyer.service.XhBuyerService;
import com.jeeplus.modules.xhorder.entity.XhOrder;
import com.jeeplus.modules.xhorder.service.XhOrderService;
import com.jeeplus.modules.xhreception.pay.PayUtils;
import com.jeeplus.modules.xhreception.pay.WeixinConfig;
import com.jeeplus.modules.xhreception.pay.WxRefundData;
import com.jeeplus.modules.xhreception.xhUtils.DateUtils;

@Service
@Lazy(false)
public class GetToken {
	@Autowired
	private XhBuyerService xhBuyerService;
	@Autowired
	private XhOrderService orderService;
	
	@Scheduled(cron = "0 0/30 * * * ?")
	public void getactJob(){
			WeixinConfig.getJsapiTicket();
		}
	/**
	 * 定时更新过期团购订单信息
	 */
	@Scheduled(cron = "0 0/1 * * * ?")
	public void isPastDue(){
		XhBuyer xhBuyer = new XhBuyer();
		xhBuyer.setGbStatus("1");
		List<XhBuyer> xhBuyers = xhBuyerService.findList(xhBuyer);
		for(XhBuyer xhBuyer1:xhBuyers){
			Date endTime=xhBuyer1.getEndTime();
			try {
				Date date1=new Date();
				long second=DateUtils.dateBetween(date1, endTime);
				if(second <= 0){
					xhBuyerService.updateStatusByGroupNo(xhBuyer1.getGroupNo(), "3");
					XhOrder xhOrder = xhBuyer1.getXhOrder();
					xhOrder.setOrderStatus("6");
					orderService.upStatusById(xhOrder);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
	/*@Scheduled(cron = "0/30 * * * * ?")
	public void wxRefund(){
		XhBuyer xhBuyer = new XhBuyer();
		xhBuyer.setGbStatus("3");
		List<XhBuyer> xhBuyers = xhBuyerService.findList(xhBuyer);
		for(XhBuyer xhBuyer1:xhBuyers){
			String out_trade_no = xhBuyer1.getXhOrder().getId();
			int total_fee = xhBuyer1.getXhOrder().getAmount().multiply(new java.math.BigDecimal(100)).intValue(); 
			WxRefundData data = PayUtils.createRefund(out_trade_no, total_fee);
			String resultCode = data.getResult_code();
			if(resultCode.equals("SUCCESS")){
				XhOrder xhOrder = xhBuyer1.getXhOrder();
				xhOrder.setOrderStatus("7");
				orderService.upStatusById(xhOrder);
				xhOrder.setPayStatus("3");
				orderService.upPayStatusById(xhOrder);
				xhBuyerService.updateStatusByGroupNo(xhBuyer1.getGroupNo(), "4");
			}
		}
	}*/
}
