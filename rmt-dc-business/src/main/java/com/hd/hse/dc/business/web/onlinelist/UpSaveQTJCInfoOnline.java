///**
// * Project Name:hse-dc-business
// * File Name:UpSaveQTJCInfoOnline.java
// * Package Name:com.hd.hse.dc.business.web.onlinelist
// * Date:2016年1月13日
// * Copyright (c) 2016, zhaofeng@ushayden.com All Rights Reserved.
// *
// */
//
//package com.hd.hse.dc.business.web.onlinelist;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.log4j.Logger;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import com.hd.hse.common.exception.HDException;
//import com.hd.hse.common.logger.LogUtils;
//import com.hd.hse.dc.business.common.weblistener.AbsWebListener;
//import com.hd.hse.entity.base.GasDetail;
//import com.hd.hse.entity.base.GasDetection2;
//import com.hd.hse.entity.workorder.WorkTask;
//import com.hd.hse.padinterface.PadInterfaceContainers;
//import com.hd.hse.padinterface.PadInterfaceRequest;
//import com.hd.hse.system.SystemProperty;
//
///**
// * ClassName:UpSaveQTJCInfoOnline (在线:提交气体检测信息).<br/>
// * Date: 2016年1月13日 <br/>
// *
// * @author LiuYnag
// * @version
// * @see
// */
//public class UpSaveQTJCInfoOnline extends AbsWebListener {
//	private static Logger logger = LogUtils
//			.getLogger(UpSaveQTJCInfoOnline.class);
//	private WorkTask workTask;
//	private GasDetection2 gasInfo;
//
//	@Override
//	public Object action(String action, Object... args) throws HDException {
//		super.action(action, args);
//		beforUpDateInfo(args);
//		Object params = initParam();
//		Object obj = null;
//		obj = sClient.action(getMethodType(), params);
//		return obj;
//	}
//
//	private boolean beforUpDateInfo(Object... args) throws HDException {
//		if (args[0] instanceof WorkTask) {
//			workTask = (WorkTask) args[0];
//		} else {
//			throw new HDException("请传入作业任务");
//		}
//		if (args[1] instanceof GasDetection2) {
//			gasInfo = (GasDetection2) args[1];
//		} else {
//			throw new HDException("请传入气体检测数据");
//		}
//		return true;
//	}
//
//	@Override
//	public Object initParam() throws HDException {
//		Map<String, Object> params = new HashMap<>();
//		String jsonStr = formatUpdateInfo(gasInfo);
//		params.put(PadInterfaceRequest.KEYDATA, jsonStr);
//		String personid = SystemProperty.getSystemProperty().getLoginPerson().getPersonid();
//		params.put(PadInterfaceRequest.KEYPERSONID, personid);
//		return params;
//	}
//
//	private String formatUpdateInfo(GasDetection2 gas) {
//		try {
//			JSONObject json = new JSONObject();
//			JSONArray UD_ZYXK_QTJC = new JSONArray();
//			JSONObject gasDetection = new JSONObject();
//			gasDetection.put("jclocation_desc", gas.getAddr() == null ? ""
//					: gas.getAddr());
//			gasDetection.put("jctime", gas.getTime());
//			gasDetection.put("ishg", gas.isOk() ? "1" : "0");
//			gasDetection
//					.put("ud_zyxk_zysqid", workTask.getUd_zyxk_worktaskid());
//			JSONArray UD_ZYXK_QTJCLINE = new JSONArray();
//			List<GasDetail> list = gas.getGasDetails();
//			if (list != null && list.size() != 0) {
//				for (GasDetail d : list) {
//					JSONObject gasDetail = new JSONObject();
//					gasDetail.put("qttype", d.getSelectedGasId());
//					gasDetail.put("qtvalue", d.getSelectedGasValue());
//					gasDetail.put("ud_zyxk_zysqid",
//							workTask.getUd_zyxk_worktaskid());
//					gasDetail.put("pctype", d.getParentGasId());
//					UD_ZYXK_QTJCLINE.put(gasDetail);
//				}
//			}
//			gasDetection.put("UD_ZYXK_QTJCLINE", UD_ZYXK_QTJCLINE);
//			UD_ZYXK_QTJC.put(gasDetection);
//			json.put("UD_ZYXK_QTJC", UD_ZYXK_QTJC);
//			return json.toString();
//		} catch (JSONException e) {
//			logger.error(e);
//			return null;
//		}
//	}
//
//	@Override
//	public Logger getLogger() {
//		return logger;
//	}
//
//	@Override
//	public String getMethodType() {
//		return PadInterfaceContainers.METHOD_ZYPONLINE_QTJC_POST;
//	}
//
//}
