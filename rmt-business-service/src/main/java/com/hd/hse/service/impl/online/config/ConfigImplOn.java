/**
 * Project Name:hse-business-service
 * File Name:ConfigImplOn.java
 * Package Name:com.hd.hse.service.impl.online.config
 * Date:2015年6月4日
 * Copyright (c) 2015, zhaofeng@ushayden.com All Rights Reserved.
 *
 */

package com.hd.hse.service.impl.online.config;

import com.hd.hse.common.exception.HDException;
import com.hd.hse.constant.BusinessActionNum;
import com.hd.hse.dc.business.web.file.UpImageFileInfo;
import com.hd.hse.dc.business.web.onlinelist.ApplyDeviateOnline;
import com.hd.hse.dc.business.web.onlinelist.GainDetailPageInfo;
import com.hd.hse.dc.business.web.onlinelist.GainImageInfoOnline;
import com.hd.hse.dc.business.web.onlinelist.GainItemProgressInfoOnline;
import com.hd.hse.dc.business.web.onlinelist.GainReportFormURLOnline;
import com.hd.hse.dc.business.web.onlinelist.GainReportUrl;
import com.hd.hse.dc.business.web.onlinelist.GainTimeLineDataOnline;
import com.hd.hse.dc.business.web.onlinelist.GainZYXKListOnLine;
import com.hd.hse.dc.business.web.onlinelist.PCCheckGasOnline;
import com.hd.hse.dc.business.web.onlinelist.TranferZYXKOnline;
import com.hd.hse.dc.business.web.onlinelist.UpSaveTempEleOnline;
import com.hd.hse.dc.business.web.onlinelist.UpSaveZYXKInfoOnline;
import com.hd.hse.service.impl.comm.config.CommonConfig;

/**
 * ClassName:ConfigImplOn ().<br/>
 * Date: 2015年6月4日 <br/>
 * 
 * @author zhaofeng
 * @version
 * @see
 */
public class ConfigImplOn extends CommonConfig {
	@Override
	public Object asynAction(String action, Object... objects)
			throws HDException {
		// TODO Auto-generated method stub
		return config(action, objects);
	}

	/**
	 * config:(统一配置调用的方法). <br/>
	 * date: 2015年5月28日 <br/>
	 * 
	 * @author zhaofeng
	 * @param action
	 * @param objects
	 * @return
	 * @throws HDException
	 */
	private Object config(String action, Object... objects) throws HDException {
		Object obj = null;
		if (action.equals(BusinessActionNum.QUERY_NOTICE_MESSAGE)) {
			// 通知消息查询 返回list集合，里面包含了通知类和操作类的消息
			/*GainNewsListInfoOnline messageList = new GainNewsListInfoOnline();
			obj = messageList.action(action, objects);*/
		} else if (action.equals(BusinessActionNum.QUERY_WORKTASK_LIST)) {
			// 任务列表数据查询
			GainZYXKListOnLine taskBusiness = new GainZYXKListOnLine();
			// objects[0]功能编码“String”
			obj = taskBusiness.action(action, objects);
		} else if (action.equals(BusinessActionNum.QUERY_WORKTASK_OPERATE_DATA)) {
			// 点击任务进入下一界面，的操作数据查询
			GainDetailPageInfo detailInfo = new GainDetailPageInfo();
			// objects[0]功能编码“String” ; objects[1]任务对象“WorkTask”
			obj = detailInfo.action(action, objects);
		} else if (action.equals(BusinessActionNum.QUERY_REPORT_URL)) {
			// 查询报表URL
			GainReportUrl reportUrl = new GainReportUrl();
			reportUrl.action(action, objects);
		} else if (action.equals(BusinessActionNum.ACTION_HARM_AUDIT)) {
			// 危害审批--点击任务获取的对象（危害的对象缓存数据已经更新）
			UpSaveZYXKInfoOnline saveInfo = new UpSaveZYXKInfoOnline();
			obj = saveInfo.action(action, objects);
		} else if (action.equals(BusinessActionNum.ACTION_MEASURE_AUDIT)) {
			// 措施审批--点击任务获取的对象（措施的对象缓存数据已经更新）
			UpSaveZYXKInfoOnline saveInfo = new UpSaveZYXKInfoOnline();
			saveInfo.setCS(true);
			obj = saveInfo.action(action, objects);
		} else if (action.equals(BusinessActionNum.ACTION_WORK_END_AUDIT)) {
			// 作业结束审批---传递参数为WorkTask
			UpSaveZYXKInfoOnline saveInfo = new UpSaveZYXKInfoOnline();
			obj = saveInfo.action(action, objects);
		} else if (action.equals(BusinessActionNum.UPLOAD_WORKTASK_PHOTO)) {
			// 照片上传---参数：照片的对象
			UpImageFileInfo updateImge = new UpImageFileInfo();
			obj = updateImge.action(action, objects);
		} else if (action.equals(BusinessActionNum.QUERY_WORKTASK_PHOTO)) {
			// 照片管理--参数：任务对象
			GainImageInfoOnline image = new GainImageInfoOnline();
			obj = image.action(action, objects);
		} else if (action.equals(BusinessActionNum.ACTION_ACCEPT_WORK_AUDIT)) {
			// 接班确认
			TranferZYXKOnline accept = new TranferZYXKOnline();
			accept.action(action, objects);
		} else if (action.equals(BusinessActionNum.ACTION_APPLY_DEVIATE_AUDIT)) {
			// 申请偏离操作
			ApplyDeviateOnline apply = new ApplyDeviateOnline();
			obj = apply.action(action, objects);
		} else if (action.equals(BusinessActionNum.CHECK_QTJC_ISHE)) {
			// 气体检测校验是否合格 ---参数：传入对应气体的措施项
			PCCheckGasOnline gas = new PCCheckGasOnline();
			obj = gas.action(action, objects);
		} else if (action.equals(BusinessActionNum.QUERY_ITEMPROGRESS_INFO)) {
			// 查看任务下边分项任务的进度信息
			GainItemProgressInfoOnline item = new GainItemProgressInfoOnline();
			obj = item.action(action, objects);
		} else if (action.equals(BusinessActionNum.QUERY_TIMELINE_DATA)) {
			GainTimeLineDataOnline item = new GainTimeLineDataOnline();
			obj = item.action(action, objects);
		} else if (action.equals(BusinessActionNum.QUERY_QTJC)) {
//			GainQTJCInfoOnline gainQTJCInfo = new GainQTJCInfoOnline();
//			obj= gainQTJCInfo.action(action, objects);
		} else if (action.equals(BusinessActionNum.UPLOAD_QTJC)) {
//			UpSaveQTJCInfoOnline upSaveQTJCInfo = new UpSaveQTJCInfoOnline();
//			obj = upSaveQTJCInfo.action(action, objects);
		} else if (action.equals(BusinessActionNum.QUERY_RPTURL)) {
			GainReportFormURLOnline gainUrlOnline = new GainReportFormURLOnline();
			obj = gainUrlOnline.action(action, objects);
		} else if (action.equals(BusinessActionNum.UPLOAD_ELE)) {
			UpSaveTempEleOnline upSaveTempEleOnline = new UpSaveTempEleOnline();
			obj = upSaveTempEleOnline.action(action, objects);
		} else {
			obj = common(action, objects);
		}
		return obj;
	}
}
