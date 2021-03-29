/**
 * Project Name:hse-business-service
 * File Name:ActionOn.java
 * Package Name:com.hd.hse.service.impl.online.action
 * Date:2015年5月29日
 * Copyright (c) 2015, zhaofeng@ushayden.com All Rights Reserved.
 *
*/

package com.hd.hse.service.impl.online.action;

import com.hd.hse.common.exception.HDException;
import com.hd.hse.service.abstr.action.AbstractAction;
import com.hd.hse.service.impl.asyn.BusinessAsyn;
import com.hd.hse.service.impl.online.config.ConfigImplOn;
import com.hd.hse.service.inter.ICallBack;

/**
 * ClassName:ActionOn (组合业务动作实现类).<br/>
 * Date:     2015年5月29日  <br/>
 * @author   zhaofeng
 * @version  
 * @see 	 
 */
public class ActionImplOn extends AbstractAction {
	@Override
	public Object action(String action, ICallBack callback, Object... objects) throws HDException {
		// TODO Auto-generated method stub
		//System.out.print("在线测试");
		BusinessAsyn.getInstance().exeAsyn(action, new ConfigImplOn(), callback, objects);
		return super.action(action, callback, objects);
	}
}

