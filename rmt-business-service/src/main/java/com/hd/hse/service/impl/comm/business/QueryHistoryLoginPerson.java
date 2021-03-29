/**
 * Project Name:hse-business-service
 * File Name:QueryHistoryLoginPerson.java
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
import com.hd.hse.entity.common.RecentlyLoginPerson;
import com.hd.hse.service.abstr.business.AbstractBusiness;

/**
 * ClassName:QueryHistoryLoginPerson ().<br/>
 * Date:     2015年6月4日  <br/>
 * @author   zhaofeng
 * @version  
 * @see 	 
 */
public class QueryHistoryLoginPerson extends AbstractBusiness {
	
	/**
	 * logger:TODO(日志).
	 */
	private final static Logger logger = LogUtils.getLogger(QueryHistoryLoginPerson.class);
	
	@Override
	public Object action(String action, Object... objects)
			throws HDException {
		// TODO Auto-generated method stub
		return getHistoryLoginPerson();
	}
	
	/**
	 * getHistoryLoginPerson:(获取历史登陆人信息). <br/>
	 * date: 2015年6月1日 <br/>
	 *
	 * @author zhaofeng
	 * @return
	 * @throws HDException
	 */
	public List<RecentlyLoginPerson> getHistoryLoginPerson() throws HDException {
		List<RecentlyLoginPerson> list = null;
		StringBuilder sbSql = new StringBuilder();
		try {
			sbSql.append("select * from hse_sys_login login order by login.createdate desc limit 5 ;");
			list = (List<RecentlyLoginPerson>) dao.executeQuery(sbSql.toString(),
					new EntityListResult(RecentlyLoginPerson.class));
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			throw new HDException(e.getMessage(), e);
		}
		return list;
	}
}

