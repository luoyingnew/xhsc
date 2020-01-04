package com.jeeplus.modules.xhreception.pay;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeeplus.modules.xhorder.entity.XhOrder;
import com.jeeplus.modules.xhorder.service.XhOrderService;
import com.jeeplus.modules.xhuser.entity.XhUser;
import com.jeeplus.modules.xhuser.service.XhUserService;
import com.vdurmont.emoji.EmojiParser;

import net.sf.json.JSONObject;
@Controller
@RequestMapping(value="wxpay")
public class WxPayController {
	
	public static final Logger logger = LoggerFactory.getLogger(WxPayController.class);
	public static final String SUCCESS = "SUCCESS";
	
	
	@Autowired
	private XhUserService xhUserService;
	
	@Autowired
	private XhOrderService xhOrderService;
	
	/**
	 * 获取code
	 */
	@RequestMapping("getCode")
	public String getCode(Model model){
	String appid = PayUtils.appid;
	model.addAttribute("appid", appid);
		return "modules/xhreception/getCode";
	}
	
	/**
	 * 获取oppenid
	 * @param code
	 * @param state
	 * @param session
	 * @return
	 */
	@RequestMapping("oppid")
	public String redirectFromWeixin(String code,String state,HttpSession session){
		logger.debug("code={}",code);
		JSONObject authResult = WeixinUtil.getAccessToken(PayUtils.appid, PayUtils.secret, code);
		String openid = authResult.getString("openid");
		logger.debug("openid={}",openid);
		String access_token=authResult.getString("access_token");
		logger.debug("access_token={}",access_token);
		//获取用户信息
		String wxurl ="https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
		String url = wxurl.replace("ACCESS_TOKEN", access_token).replace("OPENID", openid);
		logger.debug("url={}",url);
		String result = HttpKit.get(url);
		logger.debug("result={}",result);
		JSONObject userResult = JSONObject.fromObject(result); 
		logger.debug("userResult={}",userResult);
		String username = userResult.getString("nickname");
		username = EmojiParser.removeAllEmojis(username);
		String sex = userResult.getString("sex");
		String pic = userResult.getString("headimgurl");
		XhUser user = new XhUser();
		String uid = null;
		if(openid!=null){
			uid =xhUserService.findIdByOpenId(openid);
		}
		if(uid!=null){
			String beforePath = (String)session.getAttribute("beforePath");
			logger.info("befoPath={}",beforePath);
			session.setAttribute("openId", openid);
			return "redirect:"+beforePath;
		}else{
			String beforePath = (String)session.getAttribute("beforePath");
			logger.info("befoPath={}",beforePath);
			user.setUsername(username);
			user.setSex(sex);
			user.setPic(pic);
			user.setOpenId(openid);
			user.setIntegral("5");
			xhUserService.save(user);
			session.setAttribute("openId", openid);
			return "redirect:"+beforePath;
		}
		
	}
	
	/**
	 * 跳转到微信支付页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "wxpay")
	public String wxpay(HttpServletRequest request, HttpServletResponse response , Model model) {
		String oid = request.getParameter("oid");
		model.addAttribute("oid", oid);
		return "modules/xhreception/wxp";
	}
	/**
	 * 统一下单api
	 * @param oid
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("prepay")
	@ResponseBody
	public Map<String,String> prepay(String oid,String url,HttpSession session,HttpServletRequest request,Model model){
		
		if(WeixinConfig.jsapi_ticket ==null || WeixinConfig.jsapi_ticket == ""){
			WeixinConfig.getJsapiTicket();
		}
		ReturnData rd = new ReturnData();
		rd.setSuccess(true);
		rd.setMsg("下单成功");
	//	Order_info info = order_infoService.findByPK(oid);
		//获取订单号
		XhOrder info = xhOrderService.get(oid);
		//获取设备ip
		String spbillCreateIp = request.getRemoteAddr();
		/*String spbillCreateIp = "192.168.1.1";*/
	//	String spbillCreateIp = "8.8.8.8";
		//获取openid openId1
		String openId = (String) session.getAttribute("openId");
		int amount = 1;
		System.out.println("info="+info);
		if(info != null){
			//获取总价
			amount = info.getAmount().multiply(new BigDecimal(100)).intValue();
			logger.info("amount:{}",amount);
		}
		
			// 判断prepay_id 是否过期
			
				//生成prid
				WxReturnData wxReturnData = PayUtils.createOrder(oid,amount, spbillCreateIp, null,  PayUtils.TRADE_TYPE_JSAPI, null, openId );
				//已经存在微信下单信息
				Map<String,String> data = new HashMap<String,String>();
				WxPayRequest pr =new WxPayRequest();
				pr.setAppId(PayUtils.appid);
				pr.setPackageString("prepay_id="+wxReturnData.getPrepay_id());
				pr.setTimeStamp(String.valueOf(System.currentTimeMillis()/1000));
				pr.setSignType("MD5");
				pr.sign();
				if(wxReturnData.getReturn_code().equals(SUCCESS)){
					try {
						rd.setSuccess(true);
						//保存微信下单信息，避免外部单据号码重复问题
						data.put("prepay_id", wxReturnData.getPrepay_id());
						data.put("appid", PayUtils.appid);
						data.put("timestamp", pr.getTimeStamp());
						data.put("nonce_str", pr.getNonceStr());
						String signatures = "jsapi_ticket="+WeixinConfig.jsapi_ticket+"&noncestr="+"123456"+"&timestamp="+pr.getTimeStamp()+"&url="+url;
						logger.info("signatures={}",signatures);
						String signature = DigestUtils.sha1Hex(signatures);
						logger.info("signature="+signature);
						data.put("signature", signature);
						data.put("paysign", pr.getPaySign());
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					rd.setSuccess(false);
				}
			model.addAttribute("oid",oid);
			return data;
	}
	/**
	 * 异步通知地址，可以调整为你需要处理的业务逻辑
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("successful")
	public void successful(HttpServletRequest request,HttpServletResponse response) throws Exception{
		/*System.out.println("已经支付了");
		DefaultProfile profile = DefaultProfile.getProfile("default", "LTAIslyfIYYvZklg", "vGtRPsKadWM3KWiRXptDkgNBLnruwe");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "default");
        request.putQueryParameter("PhoneNumbers", "13615693261");
        request.putQueryParameter("SignName", "宁夏芊艺农业科技有限公司");
        request.putQueryParameter("TemplateCode", "SMS_171745311");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println("发送短信了吗？"+response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        try {
			responses.getWriter().write("<xml><return_code><![CDATA[SUCCESS]]></return_code></xml>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		InputStream in = request.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in,"UTF_8"));
		StringBuffer sb = new StringBuffer();
		String line;
		while((line=reader.readLine()) != null){
			sb.append(line);
		}
		in.close();
		reader.close();
		
		response.setContentType("text/xml");
		response.getWriter().println("success");
    }
}
