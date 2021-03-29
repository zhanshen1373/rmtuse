/**
 * Project Name:hse-entity-service
 * File Name:ICallBack.java
 * Package Name:com.hd.hse.service.branches.inter
 * Date:2015年5月27日
 * Copyright (c) 2015, zhaofeng@ushayden.com All Rights Reserved.
 *
*/

package com.hd.hse.service.inter;
/**
 * ClassName:ICallBack (异步处理中的回调函数).<br/>
 * Date:     2015年5月27日  <br/>
 * @author   zhaofeng
 * @version  
 * @see 	 
 */
public interface ICallBack {
	
	
	/**
	 * start:(开始执行). <br/>
	 * date: 2015年5月27日 <br/>
	 *
	 * @author zhaofeng
	 * @param action 业务动作
	 * @param flag   该次业务动作的流程标志
	 * @param objects业务数据
	 */
	public void start(String action, int flag, Object... objects);
	/**
	 * process:(正在执行). <br/>
	 * date: 2015年5月27日 <br/>
	 *
	 * @author zhaofeng
	 * @param action 业务动作
	 * @param flag   该次业务动作的流程标志
	 * @param objects业务数据
	 */
	public void process(String action, int flag, Object... objects);
	/**
	 * end:(执行完成). <br/>
	 * date: 2015年5月27日 <br/>
	 *
	 * @author zhaofeng
	 * @param action 业务动作
	 * @param flag   该次业务动作的流程标志
	 * @param objects业务数据
	 */
	public void end(String action, int flag, Object... objects);
	/**
	 * error:(执行出错). <br/>
	 * date: 2015年5月27日 <br/>
	 *
	 * @author zhaofeng
	 * @param action 业务动作
	 * @param flag   该次业务动作的流程标志
	 * @param objects业务数据
	 */
	public void error(String action, int flag, Object... objects);
	
}

