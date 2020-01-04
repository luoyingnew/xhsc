/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhtime.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.jeeplus.common.utils.DateUtils;
import com.jeeplus.common.utils.MyBeanUtils;
import com.jeeplus.common.config.Global;
import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.common.utils.excel.ImportExcel;
import com.jeeplus.modules.xhgoods.entity.XhGoods;
import com.jeeplus.modules.xhgoods.service.XhGoodsService;
import com.jeeplus.modules.xhtime.entity.XhTime;
import com.jeeplus.modules.xhtime.service.XhTimeService;

/**
 * 收花时间Controller
 * @author wujianbing
 * @version 2019-04-23
 */
@Controller
@RequestMapping(value = "${adminPath}/xhtime/xhTime")
public class XhTimeController extends BaseController {

	@Autowired
	private XhTimeService xhTimeService;
	@Autowired
	private XhGoodsService xhGoodsService;
	@ModelAttribute
	public XhTime get(@RequestParam(required=false) String id) {
		XhTime entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xhTimeService.get(id);
		}
		if (entity == null){
			entity = new XhTime();
		}
		return entity;
	}
	
	/**
	 * 收花时间列表页面
	 */
	@RequiresPermissions("xhtime:xhTime:list")
	@RequestMapping(value = {"list", ""})
	public String list(XhTime xhTime, HttpServletRequest request, HttpServletResponse response, Model model) {
		XhGoods xhGoods = new XhGoods();
		List<XhGoods> xlist=xhGoodsService.findList(xhGoods);
		model.addAttribute("xlist",xlist);
		Page<XhTime> page = xhTimeService.findPage(new Page<XhTime>(request, response), xhTime); 
		model.addAttribute("page", page);
		return "modules/xhtime/xhTimeList";
	}

	/**
	 * 查看，增加，编辑收花时间表单页面
	 */
	@RequiresPermissions(value={"xhtime:xhTime:view","xhtime:xhTime:add","xhtime:xhTime:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(XhTime xhTime, Model model) {
		XhGoods xhGoods = new XhGoods();
		List<XhGoods> xlist=xhGoodsService.findList(xhGoods);
		model.addAttribute("xlist",xlist);
		model.addAttribute("xhTime", xhTime);
		return "modules/xhtime/xhTimeForm";
	}

	/**
	 * 保存收花时间
	 */
	@RequiresPermissions(value={"xhtime:xhTime:add","xhtime:xhTime:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(XhTime xhTime, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, xhTime)){
			return form(xhTime, model);
		}
		if(!xhTime.getIsNewRecord()){//编辑表单保存
			XhTime t = xhTimeService.get(xhTime.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(xhTime, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			xhTimeService.save(t);//保存
		}else{//新增表单保存
			xhTimeService.save(xhTime);//保存
		}
		addMessage(redirectAttributes, "保存收花时间成功");
		return "redirect:"+Global.getAdminPath()+"/xhtime/xhTime/?repage";
	}
	
	/**
	 * 删除收花时间
	 */
	@RequiresPermissions("xhtime:xhTime:del")
	@RequestMapping(value = "delete")
	public String delete(XhTime xhTime, RedirectAttributes redirectAttributes) {
		xhTimeService.delete(xhTime);
		addMessage(redirectAttributes, "删除收花时间成功");
		return "redirect:"+Global.getAdminPath()+"/xhtime/xhTime/?repage";
	}
	
	/**
	 * 批量删除收花时间
	 */
	@RequiresPermissions("xhtime:xhTime:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			xhTimeService.delete(xhTimeService.get(id));
		}
		addMessage(redirectAttributes, "删除收花时间成功");
		return "redirect:"+Global.getAdminPath()+"/xhtime/xhTime/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("xhtime:xhTime:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(XhTime xhTime, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "收花时间"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<XhTime> page = xhTimeService.findPage(new Page<XhTime>(request, response, -1), xhTime);
    		new ExportExcel("收花时间", XhTime.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出收花时间记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/xhtime/xhTime/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("xhtime:xhTime:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<XhTime> list = ei.getDataList(XhTime.class);
			for (XhTime xhTime : list){
				try{
					xhTimeService.save(xhTime);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条收花时间记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条收花时间记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入收花时间失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/xhtime/xhTime/?repage";
    }
	
	/**
	 * 下载导入收花时间数据模板
	 */
	@RequiresPermissions("xhtime:xhTime:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "收花时间数据导入模板.xlsx";
    		List<XhTime> list = Lists.newArrayList(); 
    		new ExportExcel("收花时间数据", XhTime.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/xhtime/xhTime/?repage";
    }
	
	
	

}