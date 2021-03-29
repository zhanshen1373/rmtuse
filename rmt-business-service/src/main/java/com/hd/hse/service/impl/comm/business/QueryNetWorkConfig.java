/**
 * Project Name:hse-business-service
 * File Name:QueryNetWorkConfig.java
 * Package Name:com.hd.hse.service.impl.comm.business
 * Date:2015年6月4日
 * Copyright (c) 2015, zhaofeng@ushayden.com All Rights Reserved.
 *
*/

package com.hd.hse.service.impl.comm.business;

import java.util.List;

import org.apache.log4j.Logger;

import com.hd.hse.common.exception.DaoException;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.dao.result.EntityListResult;
import com.hd.hse.entity.common.SysConfig;
import com.hd.hse.service.abstr.business.AbstractBusiness;

/**
 * ClassName:QueryNetWorkConfig ().<br/>
 * Date:     2015年6月4日  <br/>
 * @author   zhaofeng
 * @version  
 * @see 	 
 */
public class QueryNetWorkConfig extends AbstractBusiness {
	/**
	 * logger:TODO(日志).
	 */
	private final static Logger logger = LogUtils.getLogger(QueryNetWorkConfig.class);
	
	@Override
	public Object action(String action, Object... objects) throws HDException {
		// TODO Auto-generated method stub
		return queryNetWorkConfig();
	}
	
	private List<SysConfig> queryNetWorkConfig() throws HDException{
		List<SysConfig> list = null;
		StringBuilder sbSql = new StringBuilder();
		sbSql.append("select * from hse_sys_config where syscode in ('inurl','outurl');");
		try{
			list = (List<SysConfig>)dao.executeQuery(sbSql.toString(), new EntityListResult(SysConfig.class));
		}catch(DaoException e){
			logger.error(e.getMessage(), e);
			throw new HDException(e.getMessage(), e);
		}
		return list;
	}
}

