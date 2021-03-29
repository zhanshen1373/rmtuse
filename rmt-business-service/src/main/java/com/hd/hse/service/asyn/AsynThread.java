/**
 * Project Name:hse-entity-service
 * File Name:AsynThread.java
 * Package Name:com.hd.hse.service.branches.asyn
 * Date:2015年5月27日
 * Copyright (c) 2015, zhaofeng@ushayden.com All Rights Reserved.
 *
*/

package com.hd.hse.service.asyn;

import org.apache.log4j.Logger;

import android.os.Handler;
import android.os.Message;

import com.hd.hse.business.task.IMessageWhat;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.service.inter.IBusinessConfigCtrl;

/**
 * ClassName:AsynThread (创建内部类线程).<br/>
 * Date:     2015年4月22日  <br/>
 * @author   zhaofeng
 * @version  
 * @see 	 
 */
public class AsynThread extends Thread {
	/**
	 * logger:TODO(日志).
	 */
	private final Logger logger = LogUtils.getLogger(AsynThread.class);
	/**
	 * webCtrl:TODO(接口执行方法).
	 */
	private IBusinessConfigCtrl webCtrl = null;
	/**
	 * handler:TODO(UI消息发送机制).
	 */
	private Handler handler = null;
	/**
	 * objects:TODO(业务数据集合).
	 */
	private Object[] objects = null;
	/**
	 * action:TODO(业务动作).
	 */
	private String action = "";
	/**
	 * Creates a new instance of AsynThread.
	 *
	 * @param handler
	 * @param webCtrl
	 */
	public AsynThread(String action,Handler handler,IBusinessConfigCtrl webCtrl,Object...objects){
		this.action = action;
		this.handler = handler;
		this.webCtrl = webCtrl;
		this.objects = objects;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		if(handler == null || webCtrl == null)
			return;
		//开始执行...
		Message start = handler.obtainMessage();
		start.what = IMessageWhat.START;
		handler.sendMessage(start);
		//
		try {
			//执行中...
			Message process = handler.obtainMessage();
			process.what = IMessageWhat.PROCESSING;
			handler.sendMessage(process);
			//执行方法
			Object obj = webCtrl.asynAction(action,objects);
			//执行完成...
			Message end = handler.obtainMessage();
			end.what = IMessageWhat.END;
			end.obj = obj;
			handler.sendMessage(end);
		} catch (HDException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
			//出错...
			Message error = handler.obtainMessage();
			error.what = IMessageWhat.ERROR;
			error.obj = e.getMessage();
			handler.sendMessage(error);			
		}
		
	}

}

