/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhmbuyer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.xhmbuyer.dao.XhMbuyerDao;
import com.jeeplus.modules.xhmbuyer.entity.XhMbuyer;

/**
 * 包月信息Service
 * @author wujianbing
 * @version 2019-04-17
 */
@Service
@Transactional(readOnly = true)
public class XhMbuyerService extends CrudService<XhMbuyerDao, XhMbuyer> {
	
	@Autowired
	private XhMbuyerDao xhMbuyerDao;
	public XhMbuyer get(String id) {
		return super.get(id);
	}
	
	public List<XhMbuyer> findList(XhMbuyer xhMbuyer) {
		return super.findList(xhMbuyer);
	}
	
	public Page<XhMbuyer> findPage(Page<XhMbuyer> page, XhMbuyer xhMbuyer) {
		return super.findPage(page, xhMbuyer);
	}
	
	@Transactional(readOnly = false)
	public void save(XhMbuyer xhMbuyer) {
		super.save(xhMbuyer);
	}
	
	@Transactional(readOnly = false)
	public void delete(XhMbuyer xhMbuyer) {
		super.delete(xhMbuyer);
	}
	
	@Transactional(readOnly = false)
	public void updateStatusById(String id,String mStatus) {
		xhMbuyerDao.updateStatusById(id,mStatus);
		
	}
	@Transactional(readOnly = false)
	public void changeTimesById(XhMbuyer xhMbuyer) {
		xhMbuyerDao.changeTimesById(xhMbuyer);
		
	}
	
	
	
	
}