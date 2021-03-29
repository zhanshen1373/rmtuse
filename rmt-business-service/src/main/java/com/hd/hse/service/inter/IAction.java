/**
 * Project Name:hse-entity-service
 * File Name:IAction.java
 * Package Name:com.hd.hse.service.branches.inter
 * Date:2015年5月27日
 * Copyright (c) 2015, zhaofeng@ushayden.com All Rights Reserved.
 *
*/

package com.hd.hse.service.inter;

import com.hd.hse.common.exception.HDException;

/**
 * ClassName:IAction ().<br/>
 * Date:     2015年5月27日  <br/>
 * @author   zhaofeng
 * @version  
 * @see 	 
 */
public interface IAction {
	/**
	 * action:(). <br/>
	 * date: 2015年5月27日 <br/>
	 *
	 * @author zhaofeng
	 * @param action
	 * @param callback
	 * @param objects
	 * @return
	 */
	public Object action(String action, ICallBack callback, Object... objects) throws HDException;
}

