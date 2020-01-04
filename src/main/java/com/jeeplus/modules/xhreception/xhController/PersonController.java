package com.jeeplus.modules.xhreception.xhController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jeeplus.common.web.BaseController;
import com.jeeplus.modules.pointgoods.entity.PointGoods;
import com.jeeplus.modules.pointgoods.service.PointGoodsService;
import com.jeeplus.modules.pointinfo.entity.PointInfo;
import com.jeeplus.modules.pointinfo.service.PointInfoService;
import com.jeeplus.modules.xhreception.domain.Message;
import com.jeeplus.modules.xhuser.entity.XhUser;
import com.jeeplus.modules.xhuser.service.XhUserService;
import com.sun.xml.internal.ws.resources.AddressingMessages;

@Controller
public class PersonController extends BaseController{
	@Autowired
	private XhUserService xhUserService;
	@Autowired
	private PointGoodsService pointGoodsService;
	@Autowired
	private PointInfoService pointInfoService;
	@RequestMapping("personCenter")
	public String PersonCenter(HttpServletRequest request, HttpServletResponse response, Model model,HttpSession session){
		String OpenId = (String)session.getAttribute("openId");
		XhUser xhUser =xhUserService.findInfoByOpenId(OpenId);
		model.addAttribute("xhUser", xhUser);
		return "modules/xhreception/person";
	}
	/**
	 * 积分商城
	 * @return
	 */
	@RequestMapping("integral")
	public String integral(HttpServletRequest request, HttpServletResponse response, Model model,String message){
		model.addAttribute("message", message);
		PointGoods pointGood = new PointGoods();
		List<PointGoods> pointGoods = pointGoodsService.findList(pointGood);
		model.addAttribute("pointGoods", pointGoods);
		return "modules/xhreception/integral";
	}
	@RequestMapping("addPGoodsOrder")
	@ResponseBody
	public Message addPGoodsOrder(HttpServletRequest request, HttpServletResponse response, Model model,HttpSession session,String id){
		Message message = new Message();
		String openId = (String)session.getAttribute("openId");
		XhUser xhUser =xhUserService.findInfoByOpenId(openId);
		PointGoods pointGood = pointGoodsService.get(id);
		String uIntegral = xhUser.getIntegral();
		String pIntegral = pointGood.getPpoint();
		int num = Integer.parseInt(uIntegral)-Integer.parseInt(pIntegral);
		if(num < 0){
			message.setCode("0");
			message.setMessage("积分不足");
			return message;
		}else{
			PointInfo pointInfo = new PointInfo();
			pointInfo.setXhUser(xhUser);
			pointInfo.setuName(xhUser.getUsername());
			pointInfo.setuPhone(xhUser.getPhone());
			pointInfo.setpName(pointGood.getPname());
			pointInfo.setpPoint(pointGood.getPpoint());
			pointInfoService.save(pointInfo);
			xhUser.setIntegral(String.valueOf(num));
			xhUserService.updateIntegralById(xhUser);
		}
		message.setCode("1");
		message.setMessage("兑换成功");
		return message;
	}
	/**
	 * 积分中心
	 * @return
	 */
	@RequestMapping("IntegralCenter")
	public String IntegralCenter(HttpServletRequest request, HttpServletResponse response, Model model,HttpSession session){
		String OpenId = (String)session.getAttribute("openId");
		XhUser xhUser =xhUserService.findInfoByOpenId(OpenId);
		model.addAttribute("xhUser", xhUser);
		PointInfo pointInfo = new PointInfo();
		pointInfo.setXhUser(xhUser);
		List<PointInfo> pointInfos = pointInfoService.findList(pointInfo);
		model.addAttribute("pointInfos", pointInfos);
		return "modules/xhreception/IntegralCenter";
	}
	
	/**
	 * 个人信息
	 * @return
	 */
	@RequestMapping("personInfo")
	public String personInfo(HttpServletRequest request, HttpServletResponse response, Model model,HttpSession session){
		String OpenId = (String)session.getAttribute("openId");
		XhUser xhUser =xhUserService.findInfoByOpenId(OpenId);
		model.addAttribute("xhUser", xhUser);
		return "modules/xhreception/personinfo";
	}
	
	@RequestMapping("updateUserInfo")
	public String updateUserInfo(HttpServletRequest request, HttpServletResponse response, Model model,HttpSession session){
		String openId = (String)session.getAttribute("openId");
		String username = request.getParameter("username");
		String phone = request.getParameter("phone");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");
		XhUser xhUser = new XhUser();
		xhUser.setUsername(username);
		xhUser.setOpenId(openId);
		xhUser.setSex(sex);
		xhUser.setBirthday(birthday);
		xhUser.setPhone(phone);
		xhUserService.updateUserInfoByOpenId(xhUser);
		return "redirect:personCenter";
	}
	
}
