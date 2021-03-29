/**
 * Project Name:hse-business-service
 * File Name:SaveNetWorkConfig.java
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
import com.hd.hse.entity.common.SysConfig;
import com.hd.hse.service.abstr.business.AbstractBusiness;

/**
 * ClassName:SaveNetWorkConfig ().<br/>
 * Date: 2015年6月4日 <br/>
 * 
 * @author zhaofeng
 * @version
 * @see
 */
public class SaveNetWorkConfig extends AbstractBusiness {

	/**
	 * logger:TODO(日志).
	 */
	private final static Logger logger = LogUtils
			.getLogger(SaveNetWorkConfig.class);

	@Override
	public Object action(String action, Object... objects) throws HDException {
		// TODO Auto-generated method stub
		if (objects != null && objects.length > 0) {
			if (objects[0] instanceof List<?>) {
				List list = (List) objects[0];
				for (Object obj : list) {
					if (!(obj instanceof SysConfig)) {
						throw new HDException("系统错误，请联系管理员！");
					}
				}
				return saveNetWorkConfig((List<SysConfig>) list);
			}
		}
		return super.action(action, objects);
	}

	/**
	 * saveNetWorkConfig:(保存网络连接配置信息). <br/>
	 * date: 2015年6月4日 <br/>
	 * 
	 * @author zhaofeng
	 * @param sysConfig
	 * @return
	 */
	private Object saveNetWorkConfig(List<SysConfig> list) throws HDException {
		try {
			dao.updateEntities(getConnection(),
					list.toArray(new SysConfig[list.size()]),new String[]{"enable","sysurl"});
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			throw new HDException(e.getMessage(), e);
		} finally {
			commit();
		}
		return null;
	}

}
