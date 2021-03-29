/**
 * Project Name:hse-dao
 * File Name:BaseResult.java
 * Package Name:com.hd.hse.dao.result
 * Date:2014年8月10日
 * Copyright (c) 2014, longgang@ushayden.com All Rights Reserved.
 *
 */

package com.hd.hse.dao.result;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.hd.hse.common.exception.DaoException;

/**
 * ClassName:BaseResult (结果集处理基类).<br/>
 * Date: 2014年8月10日 <br/>
 * 
 * @author lg
 * @version
 * @see
 */
public abstract class BaseResult implements IProcessResultSet {

	/**
	 * TODO
	 * 
	 * @see com.hd.hse.dao.result.IProcessResultSet#processResultSet(ResultSet)
	 */
	public Object processResultSet(ResultSet rs) throws DaoException {
		// TODO Auto-generated method stub
		if (rs == null) {
			throw new DaoException("结果集不允许为空！");
		}
		try {
			return changeResultType(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DaoException(e.getMessage(), e);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block

				}
		}
	}

	/**
	 * changeResultType:(结果集类型转换). <br/>
	 * date: 2014年8月10日 <br/>
	 * 
	 * @author lg
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public abstract Object changeResultType(ResultSet rs) throws SQLException;
}
