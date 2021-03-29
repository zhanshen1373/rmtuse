/**
 * Project Name:hse-business-service
 * File Name:AbstractAction.java
 * Package Name:com.hd.hse.service.abstr.action
 * Date:2015年5月29日
 * Copyright (c) 2015, zhaofeng@ushayden.com All Rights Reserved.
 *
*/

package com.hd.hse.service.abstr.action;

import com.hd.hse.common.exception.HDException;
import com.hd.hse.service.inter.IAction;
import com.hd.hse.service.inter.ICallBack;

/**
 * ClassName:AbstractAction (组合动作抽象层).<br/>
 * Date:     2015年5月29日  <br/>
 * @author   zhaofeng
 * @version  
 * @see 	 
 */
public abstract class AbstractAction implements IAction {

	@Override
	public Object action(String action, ICallBack callback, Object... objects)
			throws HDException {
		// TODO Auto-generated method stub
		return null;
	}

}

