/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.orderdetail.dao;

import org.apache.ibatis.annotations.Param;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.orderdetail.entity.OrderDetail;

/**
 * 订单详情DAO接口
 * @author wujianbing
 * @version 2019-04-29
 */
@MyBatisDao
public interface OrderDetailDao extends CrudDao<OrderDetail> {

	int getCountByGroupNo(String groupNo);

	String findNewGroupNo();

	void changeTimesByOid(int times,String oid);

	
}