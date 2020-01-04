/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhmbuyer.dao;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.xhmbuyer.entity.XhMbuyer;

/**
 * 包月信息DAO接口
 * @author wujianbing
 * @version 2019-04-17
 */
@MyBatisDao
public interface XhMbuyerDao extends CrudDao<XhMbuyer> {

	void updateStatusById(String id,String mStatus);

	void changeTimesById(XhMbuyer xhMbuyer);

}