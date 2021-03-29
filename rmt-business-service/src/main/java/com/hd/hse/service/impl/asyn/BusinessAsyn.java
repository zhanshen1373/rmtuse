/**
 * Project Name:hse-entity-service
 * File Name:BusinessAsyn.java
 * Package Name:com.hd.hse.service.branches.impl.asyn
 * Date:2015年5月27日
 * Copyright (c) 2015, zhaofeng@ushayden.com All Rights Reserved.
 *
*/

package com.hd.hse.service.impl.asyn;

import com.hd.hse.service.asyn.AsynHandler;
import com.hd.hse.service.inter.IBusinessConfigCtrl;
import com.hd.hse.service.inter.ICallBack;

/**
 * ClassName:BusinessAsyn ().<br/>
 * Date:     2015年5月27日  <br/>
 * @author   zhaofeng
 * @version  
 * @see 	 
 */
public class BusinessAsyn {
	/**
	 * handler:TODO(操作类).
	 */
	private AsynHandler handler;
	/**
	 * Creates a new instance of BusinessAsyn.
	 *单利模式
	 */
	private BusinessAsyn(){}
	/**
	 * instance:TODO(当前实体对象).
	 */
	private static BusinessAsyn instance;
	/**
	 * lock:TODO(线程安全锁).
	 */
	private static Object lock = new Object();
	/**
	 * getInstance:(获取实体对象的方法). <br/>
	 * date: 2015年5月27日 <br/>
	 *
	 * @author zhaofeng
	 * @return
	 */
	public static BusinessAsyn getInstance(){
		synchronized (lock) {
			if(instance == null)
				instance = new BusinessAsyn();
		}
		return instance;
	}
	/**
	 * exeAsyn:(执行异步线程). <br/>
	 * date: 2015年5月27日 <br/>
	 *
	 * @author zhaofeng
	 * @param iWeb
	 * @param iCallBack
	 * @param objects
	 */
	public void exeAsyn(String action,IBusinessConfigCtrl iWeb, ICallBack iCallBack,Object...objects ){
//		if(handler == null)
			handler = new AsynHandler();
		handler.execute(action,iWeb, iCallBack, objects);		
	}
}

