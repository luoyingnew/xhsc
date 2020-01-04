/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhshoper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.xhshoper.entity.XhShopcar;
import com.jeeplus.modules.xhshoper.dao.XhShopcarDao;

/**
 * 购物车Service
 * @author wujianbing
 * @version 2019-04-28
 */
@Service
@Transactional(readOnly = true)
public class XhShopcarService extends CrudService<XhShopcarDao, XhShopcar> {
	@Autowired
	private XhShopcarDao xhShopcarDao;
	public XhShopcar get(String id) {
		return super.get(id);
	}
	
	public List<XhShopcar> findList(XhShopcar xhShopcar) {
		return super.findList(xhShopcar);
	}
	
	public Page<XhShopcar> findPage(Page<XhShopcar> page, XhShopcar xhShopcar) {
		return super.findPage(page, xhShopcar);
	}
	
	@Transactional(readOnly = false)
	public void save(XhShopcar xhShopcar) {
		super.save(xhShopcar);
	}
	
	@Transactional(readOnly = false)
	public void delete(XhShopcar xhShopcar) {
		super.delete(xhShopcar);
	}
	@Transactional(readOnly = false)
	public void updateNumById(String num,String gid) {
		xhShopcarDao.updateNumById(num,gid);
		
	}
	
	
	
	
}