/**
 * Project Name:woa-oea-business
 * File Name:IWebActiveCtrl.java
 * Package Name:com.hd.woa.oea.business.web
 * Date:2015年4月22日
 * Copyright (c) 2015, zhaofeng@ushayden.com All Rights Reserved.
 *
*/

package com.hd.hse.service.inter;

import com.hd.hse.common.exception.HDException;


/**
 * ClassName:IBusinessActiveCtrl (业务处理方法本地化).<br/>
 * Date:     2015年4月22日  <br/>
 * @author   zhaofeng
 * @version  
 * @see 	 
 * 
 * 异步处理中，所有操作的入口
 * 实现该接口，
 * 
 * 
 */
public interface IBusinessConfigCtrl {
	
	/**
	 * asynAction:(PC端交互本地化接口). <br/>
	 * date: 2015年5月27日 <br/>
	 *
	 * @author zhaofeng
	 * @param action
	 * @param objects
	 * @return
	 * @throws HDException
	 */
	public Object asynAction(String action, Object... objects) throws HDException;
	
}

