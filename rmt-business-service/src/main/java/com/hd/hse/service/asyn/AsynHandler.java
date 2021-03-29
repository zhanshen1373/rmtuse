/**
 * Project Name:woa-oea-business
 * File Name:AsynCheckHandler.java
 * Package Name:com.hd.woa.oea.business.web
 * Date:2015年4月22日
 * Copyright (c) 2015, zhaofeng@ushayden.com All Rights Reserved.
 *
 */

package com.hd.hse.service.asyn;

import org.apache.log4j.Logger;

import android.os.Handler;
import android.os.Message;

import com.hd.hse.business.task.IMessageWhat;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.service.inter.IBusinessConfigCtrl;
import com.hd.hse.service.inter.ICallBack;

/**
 * ClassName:AsynCheckHandler ().<br/>
 * Date: 2015年4月22日 <br/>
 * 
 * @author zhaofeng
 * @version
 * @see
 */
public class AsynHandler extends Handler {

	/**
	 * logger:TODO(日志).
	 */
	private final Logger logger = LogUtils.getLogger(AsynHandler.class);
	/**
	 * callEventListener:TODO(回掉函数).
	 */
	private ICallBack iCallBack = null;
	/**
	 * thread:TODO(子线程).
	 */
	private AsynThread thread = null;
	/**
	 * action:TODO(业务动作).
	 */
	private String action = "";

	/**
	 * TODO 消息分发
	 * 
	 * @see android.os.Handler#dispatchMessage(android.os.Message)
	 */
	@Override
	public void dispatchMessage(Message msg) {
		// TODO Auto-generated method stub
		int msgWhat = msg.what;
		switch (msgWhat) {
		case IMessageWhat.START:
			logger.info("开始执行。。。");
			iCallBack.start(action,msgWhat, msg.obj);
			break;
		case IMessageWhat.PROCESSING:
			logger.info("执行中。。。");
			iCallBack.process(action,msgWhat, msg.obj);
			break;
		case IMessageWhat.END:
			logger.info("执行完成。。。");
			iCallBack.end(action,msgWhat, msg.obj);
			break;
		case IMessageWhat.ERROR:
			logger.error("执行失败。。。");
			iCallBack.error(action,msgWhat, msg.obj);
			break;
		}
		if (msgWhat == IMessageWhat.END || msgWhat == IMessageWhat.ERROR)
			// 结束，或者出错，都将线程关闭
			interrupt();
		super.handleMessage(msg);
	}

	/**
	 * execute:(执行入口函数). <br/>
	 * date: 2015年4月22日 <br/>
	 * 
	 * @author zhaofeng
	 * @param webCtrl
	 * @param callEventListener
	 */
	public void execute(String action,IBusinessConfigCtrl webCtrl,
			ICallBack iCallBack, Object... objects) {
		this.action = action;
		this.iCallBack = iCallBack;
		thread = new AsynThread(action,this, webCtrl, objects);
		thread.start();
	}

	/**
	 * interrupt:(关闭线程). <br/>
	 * date: 2015年4月22日 <br/>
	 * 
	 * @author zhaofeng
	 */
	private void interrupt() {
		if (thread != null) {
			thread.interrupt();
			thread = null;
		}
	}

}
