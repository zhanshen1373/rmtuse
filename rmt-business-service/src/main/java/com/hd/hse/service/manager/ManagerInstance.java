/**
 * Project Name:hse-entity-service
 * File Name:ManagerInstance.java
 * Package Name:com.hd.hse.service.branches.manager
 * Date:2015年5月27日
 * Copyright (c) 2015, zhaofeng@ushayden.com All Rights Reserved.
 *
 */

package com.hd.hse.service.manager;

import com.hd.hse.service.impl.offline.action.ActionImplOff;
import com.hd.hse.service.impl.online.action.ActionImplOn;
import com.hd.hse.service.inter.IAction;

/**
 * ClassName:ManagerInstance (实体管理类，根据不同环境获取不同的实例对象).<br/>
 * Date: 2015年5月27日 <br/>
 * 
 * @author zhaofeng
 * @version
 * @see
 */
public class ManagerInstance {
	/**
	 * Creates a new instance of ManagerInstance. 单利模式
	 */
	private ManagerInstance() {
	}

	/**
	 * instance:TODO(实例对象).
	 */
	private static ManagerInstance instance;
	/**
	 * isNetWork:TODO(表示是否是在线操作，默认是在线操作).
	 */
	public static boolean isNetWork = true;
	/**
	 * clock:TODO(线程安全锁).
	 */
	private static Object lock = new Object();

	/**
	 * getInstance:(获取当前类的实力对象). <br/>
	 * date: 2015年5月27日 <br/>
	 * 
	 * @author zhaofeng
	 * @return
	 */
	public static ManagerInstance getInstance() {
		synchronized(lock){
			if (instance == null)
				instance = new ManagerInstance();
		}
		return instance;
	}
	
	/**
	 * getActionInstance:(获取组合动作对象). <br/>
	 * date: 2015年5月29日 <br/>
	 *
	 * @author zhaofeng
	 * @return
	 */
	public IAction getActionInstance(){
		if(isNetWork)
			return new ActionImplOn();
		return new ActionImplOff();
	}

}
