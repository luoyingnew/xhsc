/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhorder.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.xhorder.dao.XhOrderDao;
import com.jeeplus.modules.xhorder.entity.XhOrder;

/**
 * 订单信息Service
 * @author wujianbing
 * @version 2019-04-09
 */
@Service
@Transactional(readOnly = true)
public class XhOrderService extends CrudService<XhOrderDao, XhOrder> {
	@Autowired
	private XhOrderDao xhOrderDao;
	public XhOrder get(String id) {
		return super.get(id);
	}
	
	public List<XhOrder> findList(XhOrder xhOrder) {
		return super.findList(xhOrder);
	}
	
	public Page<XhOrder> findPage(Page<XhOrder> page, XhOrder xhOrder) {
		return super.findPage(page, xhOrder);
	}
	
	@Transactional(readOnly = false)
	public void save(XhOrder xhOrder) {
		super.save(xhOrder);
	}
	
	@Transactional(readOnly = false)
	public void delete(XhOrder xhOrder) {
		super.delete(xhOrder);
	}
	@Transactional(readOnly = false)
	public int upStatusById(XhOrder xhOrder) {
		
		return xhOrderDao.upStatusById(xhOrder);
	}

	public String findnewOrder() {
		return xhOrderDao.findnewOrder();
		
	}

	public String findOidByOrderNo(String orderNo) {
		// TODO Auto-generated method stub
		return xhOrderDao.findOidByOrderNo(orderNo);
	}
	@Transactional(readOnly = false)
	public void updateCommentById(XhOrder xhOrder) {
		xhOrderDao.updateCommentById(xhOrder);
		
	}
	@Transactional(readOnly = false)
	public int upPayStatusById(XhOrder xhOrder){
		return xhOrderDao.upPayStatusById(xhOrder);
	}
	
		//订单总数
	public String getAllOrderNum(){
			return xhOrderDao.getAllOrderNum();
		}
		//待付款总数 
	public String getDOrderNum(){
			return xhOrderDao.getDOrderNum();
		}
		//已支付订单总数
	public String getEOrderNum(){
			return xhOrderDao.getEOrderNum();
		}
		//销售总额
	public String getOrderAmount(){
			return xhOrderDao.getOrderAmount();
		}
		//每天的销售额
	public List<XhOrder> getEOrderAmount(){
			return xhOrderDao.getEOrderAmount();
		}
	public BigDecimal todayAmount(){
		return xhOrderDao.todayAmount();
	}
	
}