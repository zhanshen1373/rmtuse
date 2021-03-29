/**
 * Project Name:hse-business-service
 * File Name:SaveCurrentLoginPerson.java
 * Package Name:com.hd.hse.service.impl.comm.business
 * Date:2015年6月4日
 * Copyright (c) 2015, zhaofeng@ushayden.com All Rights Reserved.
 *
 */

package com.hd.hse.service.impl.comm.business;

import org.apache.log4j.Logger;

import com.hd.hse.common.exception.DaoException;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.dc.business.listener.common.PCCheckUser;
import com.hd.hse.entity.common.PersonCard;
import com.hd.hse.entity.common.RecentlyLoginPerson;
import com.hd.hse.service.abstr.business.AbstractBusiness;
import com.hd.hse.system.SystemProperty;

/**
 * ClassName:SaveCurrentLoginPerson ().<br/>
 * Date: 2015年6月4日 <br/>
 * 
 * @author zhaofeng
 * @version
 * @see
 */
public class SaveCurrentLoginPerson extends AbstractBusiness {

	/**
	 * logger:TODO(日志).
	 */
	private final static Logger logger = LogUtils
			.getLogger(SaveCurrentLoginPerson.class);

	@Override
	public Object action(String action, Object... objects)
			throws HDException {
		// TODO Auto-generated method stub
		if (objects != null && objects.length > 0 && objects[0] != null) {
			if (objects[0] instanceof PersonCard) {
				return saveCurrentLoginPerson(action,(PersonCard) objects[0]);
			}
		}
		return super.action(action,objects);
	}

	/**
	 * saveCurrentLoginPerson:(校验保存登陆信息). <br/>
	 * date: 2015年6月1日 <br/>
	 * 
	 * @author zhaofeng
	 * @param person
	 * @return
	 */
	public Object saveCurrentLoginPerson(String action, PersonCard person)
			throws HDException {
		Integer autoLogin = person.getAutoLogin();
		PCCheckUser pcUser = new PCCheckUser();
		PersonCard loginPsn = (PersonCard) pcUser.action(action, person);
		if(loginPsn == null)
			throw new HDException("无效登录人！");
		if (loginPsn != null) {
			// 验证成功，走保存
			try {
				RecentlyLoginPerson newLoginPsn = new RecentlyLoginPerson();
				//lxf修改 把persionid 换成loginid 原因：宁夏返回的是loginid
				newLoginPsn.setUsercode(loginPsn.getLoginid());
				newLoginPsn.setUsername(loginPsn.getPersonid_desc());
				newLoginPsn.setSysorder(1);
				newLoginPsn.setUuid(loginPsn.getUuid());
				newLoginPsn.setAutologin(autoLogin);
				newLoginPsn.setCreatedate(SystemProperty.getSystemProperty()
						.getSysDateTime());
				newLoginPsn.setPassword(loginPsn.getPassword());
				loginPsn.setAutoLogin(autoLogin);
				// 删除最近五条记录之外的数据
				StringBuilder sbSql = new StringBuilder();
				//sbSql.append("delete from hse_sys_login where usercode = '").append(loginPsn.getPersonid()).append("' ;");
				sbSql.append("delete from hse_sys_login where usercode = '").append(loginPsn.getLoginid()).append("' ;");
				sbSql.append("delete from hse_sys_login where hse_sys_loginid not in (select hse_sys_loginid from hse_sys_login login order by login.createdate desc limit 4 );");
				dao.executeUpdate(getConnection(), sbSql.toString());
				//插入新纪录
				dao.insertEntity(getConnection(), newLoginPsn);
				SystemProperty.getSystemProperty().setLoginPerson(loginPsn);
			} catch (DaoException e) {
				logger.error(e.getMessage(), e);
				throw new HDException(e.getMessage(), e);
			}finally{
				commit();
			}
		}
		return null;
	}
}
