/**
 * Project Name:hse-business-service
 * File Name:AbstractBusiness.java
 * Package Name:com.hd.hse.service.abstr.business
 * Date:2015年6月4日
 * Copyright (c) 2015, zhaofeng@ushayden.com All Rights Reserved.
 *
 */

package com.hd.hse.service.abstr.business;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.dao.BaseDao;
import com.hd.hse.dao.connection.IConnection;
import com.hd.hse.dao.factory.ConnectionSourceManager;
import com.hd.hse.dao.source.IConnectionSource;

/**
 * ClassName:AbstractBusiness ().<br/>
 * Date: 2015年6月4日 <br/>
 * 
 * @author zhaofeng
 * @version
 * @see
 */
public abstract class AbstractBusiness {
	/**
	 * logger:TODO(日志).
	 */
	private final static Logger logger = LogUtils
			.getLogger(AbstractBusiness.class);

	public Object action(String action, Object... objects) throws HDException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * dao:TODO().
	 */
	public BaseDao dao = new BaseDao();
	//
	/**
	 * connection:TODO().
	 */
	private IConnection connection;
	private IConnectionSource connectionSource;

	/**
	 * getConnection:(获取数据库连接). <br/>
	 * date: 2015年6月1日 <br/>
	 * 
	 * @author zhaofeng
	 * @return
	 * @throws HDException
	 */
	public IConnection getConnection() throws HDException {
		if (connection == null) {
			try {
				connectionSource = ConnectionSourceManager.getInstance()
						.getJdbcPoolConSource();
				connection = connectionSource.getConnection();
				connection.setAutoCommit(false);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage(), e);
				throw new HDException(e.getMessage(), e);
			}
		}
		return connection;
	}

	/**
	 * commit:(提交事务). <br/>
	 * date: 2015年6月1日 <br/>
	 * 
	 * @author zhaofeng
	 * @throws SQLException
	 */
	public void commit() throws HDException {
		if (connectionSource != null) {
			if (connection != null) {
				try {
					connection.commit();
					connectionSource.releaseConnection(connection);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					logger.error(e.getMessage(), e);
					throw new HDException(e.getMessage(), e);
				}
			}
		}
	}
}
