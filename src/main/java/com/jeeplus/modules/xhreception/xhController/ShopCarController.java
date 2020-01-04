package com.jeeplus.modules.xhreception.xhController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jeeplus.common.web.BaseController;
import com.jeeplus.modules.monthtime.entity.MonthTime;
import com.jeeplus.modules.monthtime.service.MonthTimeService;
import com.jeeplus.modules.recaddress.entity.RecAddr;
import com.jeeplus.modules.recaddress.service.RecAddrService;
import com.jeeplus.modules.xhcolor.entity.XhColor;
import com.jeeplus.modules.xhcolor.service.XhColorService;
import com.jeeplus.modules.xhgoods.entity.XhGoods;
import com.jeeplus.modules.xhgoods.service.XhGoodsService;
import com.jeeplus.modules.xhreception.domain.Message;
import com.jeeplus.modules.xhreception.domain.ShopDomain;
import com.jeeplus.modules.xhrule.entity.XhRule;
import com.jeeplus.modules.xhrule.service.XhRuleService;
import com.jeeplus.modules.xhshoper.entity.XhShopcar;
import com.jeeplus.modules.xhshoper.service.XhShopcarService;
import com.jeeplus.modules.xhtime.entity.XhTime;
import com.jeeplus.modules.xhtime.service.XhTimeService;
import com.jeeplus.modules.xhuser.entity.XhUser;
import com.jeeplus.modules.xhuser.service.XhUserService;

/**
 * 购物车
 * 
 * @author Administrator
 *
 */
@Controller
public class ShopCarController extends BaseController {
	@Autowired
	private XhGoodsService xhGoodsService;
	@Autowired
	private XhRuleService xhRuleService;

	@Autowired
	private XhTimeService xhTimeService;
	@Autowired
	private XhColorService xhColorService;
	@Autowired
	private XhShopcarService xhShopcarService;
	@Autowired
	private MonthTimeService monthTimeService;
	@Autowired
	private RecAddrService recAddrService;
	@Autowired
	private XhUserService xhUserService;
	/**
	 * 购物车列表
	 * @param request
	 * @param response
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("shopCar")
	public String shopCar(HttpServletRequest request, HttpServletResponse response, Model model,HttpSession session) {
		session.removeAttribute("xhShopcars");
		session.removeAttribute("amount");
		// 从session中获取用户信息
		String openId = (String)session.getAttribute("openId");
		String uid =xhUserService.findIdByOpenId(openId);
		XhUser xhUser = new XhUser();
		xhUser.setId(uid);
		XhShopcar xhShopcar = new XhShopcar();
		xhShopcar.setXhUser(xhUser);
		List<XhShopcar> xhShopcars = xhShopcarService.findList(xhShopcar);
		model.addAttribute("xhShopcars", xhShopcars);
		return "modules/xhreception/shopcar";
	}

	/**
	 * 加入购物车
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("addcar")
	public String addCar(HttpServletRequest request, HttpServletResponse response, Model model,
			RedirectAttributes redirectAttributes,HttpSession session) {
		// TODO 用户id传递
		String gid = request.getParameter("gid");
		String num = request.getParameter("num");
		String rid = request.getParameter("rid");
		String sid = request.getParameter("sid");
		String tid = request.getParameter("tid");
		String mid = request.getParameter("mid");
		String remarks = request.getParameter("remarks");
		String openId = (String)session.getAttribute("openId");
		String uid =xhUserService.findIdByOpenId(openId);
		/*
		 * 解决购物车出现同一件商品时数量的调整，方法还不完全，由于单件商品规格的不同，所以需要验证其相关规格参数。
		 * XhUser xhUser1 = new XhUser();
		xhUser1.setId(uid);
		XhShopcar xhShopcar1 = new XhShopcar();
		xhShopcar1.setXhUser(xhUser1);
		List<XhShopcar> xhShopcars = xhShopcarService.findList(xhShopcar1);
		if(xhShopcars.size() != 0){
			for(XhShopcar xhShopcar2 : xhShopcars){
				if(xhShopcar2.getXhGoods().getId().equals(gid)){
					String nums = xhShopcar2.getNum();
					nums = String.valueOf(Integer.parseInt(nums)+Integer.parseInt(num));
					xhShopcarService.updateNumById(nums,gid);
				}
			}
			return "redirect:itemDetail?id=" + gid;
		}*/
		if (gid.isEmpty() || gid == null) {
			return "redirect:index";
		}
		XhShopcar xhShopcar = new XhShopcar();
		if (mid != null && !mid.isEmpty()) {
			MonthTime monthTime = monthTimeService.get(mid);
			BigDecimal price = monthTime.getmPrice();
			xhShopcar.setPrice(price);
			xhShopcar.setMonthTime(monthTime);
			xhShopcar.setgStatus("2");
		}else{
			xhShopcar.setgStatus("1");
		}

		XhGoods xhGoods = new XhGoods();
		XhRule xhRule = xhRuleService.get(rid);
		if (xhRule != null && xhRule.getUnitPrice() != null) {
			BigDecimal price = xhRule.getUnitPrice();
			xhShopcar.setPrice(price);
		}
		XhColor xhColor = new XhColor();
		XhTime xhTime = new XhTime();
		xhGoods.setId(gid);

		xhColor.setId(sid);
		xhTime.setId(tid);
		XhUser xhUser = new XhUser();
		xhUser.setId(uid);
		xhShopcar.setXhUser(xhUser);
		xhShopcar.setXhGoods(xhGoods);
		xhShopcar.setXhRule(xhRule);
		xhShopcar.setXhColor(xhColor);
		xhShopcar.setXhTime(xhTime);
		xhShopcar.setNum(num);
		xhShopcar.setRemarks(remarks);
		xhShopcarService.save(xhShopcar);
		if (mid != null && !mid.isEmpty()) {
			return "redirect:monthDetail?id=" + mid + "&gid=" + gid;
		}
		return "redirect:itemDetail?id=" + gid;
	}

	@RequestMapping("shopNext")
	@ResponseBody
	public Message shopNext(@RequestBody List<ShopDomain> ShopDomains, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session) {
		List<XhShopcar> xhShopcars = new ArrayList<XhShopcar>();
		BigDecimal amount = new BigDecimal(0);
		List<XhShopcar> xhShopcars1 = (ArrayList<XhShopcar>) session.getAttribute("xhShopcars");
		BigDecimal amount1 = (BigDecimal) session.getAttribute("amount");
		if (xhShopcars1 != null && amount1 != null) {
			xhShopcars = xhShopcars1;
			amount = amount1;
			String openId = (String)session.getAttribute("openId");
			String uid =xhUserService.findIdByOpenId(openId);
			String recid = request.getParameter("recid");
			RecAddr recAddress = new RecAddr();
			XhUser xhUser = new XhUser();
			xhUser.setId(uid);
			recAddress.setXhUser(xhUser);
			recAddress.setId(recid);
			RecAddr recAddr = recAddrService.findAddrFirst(recAddress);
			model.addAttribute("recAddr", recAddr);
		} else {
			for (ShopDomain shop : ShopDomains) {
				String carId = shop.getCarId();
				String num = shop.getNum();
				XhShopcar xhShopcar = xhShopcarService.get(carId);
				if (Integer.parseInt(num) != 0) {
					xhShopcar.setNum(num);
				}
				xhShopcars.add(xhShopcar);
			}

			for (XhShopcar xhShopcar : xhShopcars) {
				String num = xhShopcar.getNum();
				BigDecimal price = xhShopcar.getPrice();
				BigDecimal xPrice = price.multiply(new BigDecimal(num));
				amount = amount.add(xPrice);
			}
			session.setAttribute("xhShopcars", xhShopcars);
			session.setAttribute("amount", amount);
		}
		model.addAttribute("amount", amount);
		model.addAttribute("xhShopcars", xhShopcars);
		Message message = new Message();
		message.setCode("1");
		return message;
	}
	@RequestMapping("shopcarNextLoad")
	public String shopcarNextLoad(HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session){
		RecAddr recAddress = new RecAddr();
		XhUser xhUser = new XhUser();
		String openId = (String)session.getAttribute("openId");
		String uid =xhUserService.findIdByOpenId(openId);
		xhUser.setId(uid);
		recAddress.setXhUser(xhUser);
		RecAddr recAddr = recAddrService.findAddrFirst(recAddress);
		model.addAttribute("recAddr", recAddr);
		return "modules/xhreception/shopcarnextload";
	}
	
	@RequestMapping("delShopcar")
	@ResponseBody
	public Message delShopcar(@RequestBody List<ShopDomain> ShopDomains, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session){
		XhShopcar xhShopcar = new XhShopcar();
		Message message = new Message();
		for (ShopDomain shop : ShopDomains) {
			String carId = shop.getCarId();
			xhShopcar.setId(carId);
			xhShopcarService.delete(xhShopcar);
		}
		message.setCode("1");
		message.setMessage("刪除成功");
		return message;
	}
}
