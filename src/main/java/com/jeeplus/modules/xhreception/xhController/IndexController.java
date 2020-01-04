package com.jeeplus.modules.xhreception.xhController;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeeplus.modules.xhbanner.entity.XhBanner;
import com.jeeplus.modules.xhbanner.service.XhBannerService;
import com.jeeplus.modules.xhfloor.entity.XhFloor;
import com.jeeplus.modules.xhfloor.service.XhFloorService;
import com.jeeplus.modules.xhgoods.service.XhGoodsService;
import com.jeeplus.modules.xhgroups.entity.XhGroups;
import com.jeeplus.modules.xhgroups.service.XhGroupsService;
import com.jeeplus.modules.xhmonthbuy.entity.XhMonthbuy;
import com.jeeplus.modules.xhmonthbuy.service.XhMonthbuyService;

@Controller
public class IndexController {
	
	@Autowired
	private XhBannerService xhBannerService;
	@Autowired
	private XhFloorService xhFloorService;
	@Autowired
	private XhMonthbuyService xhMonthbuyService;
	@Autowired
	private XhGroupsService xhGroupsService;
	@Autowired
	private XhGoodsService xhGoodsService;
	@RequestMapping("index")
	public String indexView(HttpServletRequest request, HttpServletResponse response, Model model,HttpSession session){
		//首页轮播
		XhBanner xhBanner = new XhBanner();
		List<XhBanner> bannerLists=xhBannerService.findList(xhBanner);
		List<XhBanner> bannerList = new ArrayList<>();
		for(XhBanner banner : bannerLists){
			String cid = null;
			if(banner != null && banner.getXhGoods() != null && ! banner.getXhGoods().getId().equals("")){
				cid = banner.getXhGoods().getXhCategory().getId();
			}
			if(cid != null){
				if("8584f30ee88d43eb8ac231237cd509ef".equals(cid)){
					banner.setUrl("groupList");
					bannerList.add(banner);
				}else if("3e42b27014cd4241be6f60c908669484".equals(cid)){
					banner.setUrl("monthbuy");
					bannerList.add(banner);
				}else {
					banner.setUrl("itemDetail?id="+banner.getXhGoods().getId());
					bannerList.add(banner);
				}
			}
		}
		model.addAttribute("bannerList", bannerList);
		//楼层选择
		XhFloor xhFloor = new XhFloor();
		//1楼
		xhFloor.setFloorNum("1");
		XhFloor xhFloor1 = xhFloorService.findFloorByNum(xhFloor);
		model.addAttribute("xhFloor1", xhFloor1);
		//2
		xhFloor.setFloorNum("2");
		XhFloor xhFloor2 = xhFloorService.findFloorByNum(xhFloor);
		model.addAttribute("xhFloor2", xhFloor2);
		//3
		xhFloor.setFloorNum("3");
		XhFloor xhFloor3 = xhFloorService.findFloorByNum(xhFloor);
		model.addAttribute("xhFloor3", xhFloor3);
		//4
		xhFloor.setFloorNum("4");
		XhFloor xhFloor4 = xhFloorService.findFloorByNum(xhFloor);
		model.addAttribute("xhFloor4", xhFloor4);
		//5
		xhFloor.setFloorNum("5");
		XhFloor xhFloor5 = xhFloorService.findFloorByNum(xhFloor);
		model.addAttribute("xhFloor5", xhFloor5);
		//6
		xhFloor.setFloorNum("6");
		XhFloor xhFloor6 = xhFloorService.findFloorByNum(xhFloor);
		model.addAttribute("xhFloor6", xhFloor6);
		//6以后
		List<XhFloor> xhFloors = xhFloorService.findListByNum();
		model.addAttribute("xhFloors", xhFloors);
		//包月商品
		List<XhMonthbuy> xhMonthbuy = xhMonthbuyService.findTwoMonthbuy();
		model.addAttribute("xhMonthbuy", xhMonthbuy);
		//团购商品
		List<XhGroups> xhGroups =xhGroupsService.findTwoGroups();
		model.addAttribute("xhGroups", xhGroups);
		return "modules/xhreception/index";
		
	}
}