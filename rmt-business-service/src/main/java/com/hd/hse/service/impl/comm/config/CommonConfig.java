/**
 * Project Name:hse-business-service
 * File Name:CommonConfig.java
 * Package Name:com.hd.hse.service.impl.comm.config
 * Date:2015年6月4日
 * Copyright (c) 2015, zhaofeng@ushayden.com All Rights Reserved.
 *
*/

package com.hd.hse.service.impl.comm.config;

import com.hd.hse.common.exception.HDException;
import com.hd.hse.constant.BusinessActionNum;
import com.hd.hse.dao.BaseDao;
import com.hd.hse.dc.business.listener.common.CheckConnect;
import com.hd.hse.dc.business.web.onlinelist.DelNewsInfoOnline;
import com.hd.hse.dc.business.web.onlinelist.GainNewsServerUrl;
import com.hd.hse.dc.business.web.onlinelist.GainZYXKBrowseInfo;
import com.hd.hse.service.abstr.business.AbstractBusiness;
import com.hd.hse.service.impl.comm.business.QueryHistoryLoginPerson;
import com.hd.hse.service.impl.comm.business.QueryNetWorkConfig;
import com.hd.hse.service.impl.comm.business.SaveCurrentLoginPerson;
import com.hd.hse.service.impl.comm.business.SaveNetWorkConfig;
import com.hd.hse.service.inter.IBusinessConfigCtrl;

/**
 * ClassName:CommonConfig ().<br/>
 * Date:     2015年6月4日  <br/>
 * @author   zhaofeng
 * @version  
 * @see 	 
 */
public class CommonConfig implements IBusinessConfigCtrl {

	@Override
	public Object asynAction(String action, Object... objects)
			throws HDException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public BaseDao dao = new BaseDao();
	
	/**
	 * common:(不用区分离线和在线的方法进行统一配置). <br/>
	 * date: 2015年5月29日 <br/>
	 * 
	 * @author zhaofeng
	 * @param action
	 * @param objects
	 * @return
	 * @throws HDException
	 */
	public Object common(String action, Object... objects) throws HDException {
		Object obj = null;
		AbstractBusiness business = null;
		if (action.equals(BusinessActionNum.QUERY_HISTORY_LOGINED_USER)) {
			// 本地历史登陆人记录查询
			business = new QueryHistoryLoginPerson();	
			obj = business.action(action, objects);
		}else if(action.equals(BusinessActionNum.QUERY_NETWORK_CONFIG)){
			//查询网络配置信息
			business = new QueryNetWorkConfig();	
			obj = business.action(action, objects);
		} else if (action.equals(BusinessActionNum.ACTION_USER_LOGIN)) {
			//保存登陆人信息
			business = new SaveCurrentLoginPerson();
			obj = business.action(action, objects);			
		}else if(action.equals(BusinessActionNum.CHECK_NETWORK_CONNECTION)){
			CheckConnect check = new CheckConnect();
			obj = check.action(action, objects);
		}else if(action.equals(BusinessActionNum.UPDATE_NETWORK_CONFIG)){
			//更新网络配置信息
			business = new SaveNetWorkConfig();
			obj = business.action(action, objects);
		}else if(action.equals(BusinessActionNum.QUERY_WORKTASK_BROWSE_LIST)){
			//任务浏览--不需要参数
			GainZYXKBrowseInfo browse = new GainZYXKBrowseInfo();
			obj = browse.action(action, objects);
		}else if(action.equals(BusinessActionNum.QUERY_PUSH_SERVICE_ADDRESS)){
			//获取推送服务器的地址
			GainNewsServerUrl serviceUrl = new GainNewsServerUrl();
			obj =serviceUrl.action(action, objects);
		}
		else if(action.equals(BusinessActionNum.ACTION_NEWS_DELTET)){
			//删除消息记录
			DelNewsInfoOnline delNews = new DelNewsInfoOnline();
			obj=delNews.action(action, objects);
		}
		return obj;
	}

}

