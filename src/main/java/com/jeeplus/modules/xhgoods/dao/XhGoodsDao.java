/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhgoods.dao;

import com.jeeplus.modules.xhcategory.entity.XhCategory;
import java.util.List;
import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.xhgoods.entity.XhGoods;

/**
 * 商品管理DAO接口
 * @author wujianbing
 * @version 2019-04-04
 */
@MyBatisDao
public interface XhGoodsDao extends CrudDao<XhGoods> {

	public List<XhCategory> findListByxhCategory(XhCategory xhCategory);

	public String getNum();
	
}