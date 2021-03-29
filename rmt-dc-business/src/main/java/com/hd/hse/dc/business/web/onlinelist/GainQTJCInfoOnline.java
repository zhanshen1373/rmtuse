///**
// * Project Name:hse-dc-business
// * File Name:GainQTJCInfo.java
// * Package Name:com.hd.hse.dc.business.web.onlinelist
// * Date:2016年1月5日
// * Copyright (c) 2016, zhaofeng@ushayden.com All Rights Reserved.
// *
// */
//
//package com.hd.hse.dc.business.web.onlinelist;
//
//import java.util.ArrayList;
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
//import com.hd.hse.dc.business.common.result.BaseWebResult;
//import com.hd.hse.entity.base.Domain;
//import com.hd.hse.entity.base.GasDetail;
//import com.hd.hse.entity.base.GasDetection2;
//import com.hd.hse.padinterface.PadInterfaceContainers;
//
///**
// * ClassName:GainQTJCInfo ().<br/>
// * Date: 2016年1月5日 <br/>
// *
// * @author zhaofeng
// * @version
// * @see
// */
//public class GainQTJCInfoOnline extends GainDataInfoBaseOnline {
//	private static Logger logger = LogUtils.getLogger(GainQTJCInfoOnline.class);
//	private String workTaskId;
//	public static final String GASDETAILLIST = "detailList";
//	public static final String GASDISTORY = "distorys";
//
//	@Override
//	public Object action(String action, Object... args) throws HDException {
//		if (args != null && args.length != 0) {
//			workTaskId = (String) args[1];
//		}
//		setTaskid(workTaskId);
//		Object obj;
//		try {
//			obj = paser(new JSONObject(super.action(action, args).toString()));
//		} catch (JSONException e) {
//			logger.error(e);
//			throw new HDException("JSON解析错误");
//		}
//		return obj;
//	}
//
//	private Map<String, Object> paser(JSONObject json) {
//		try {
//			// 解析气体检测域
//			List<GasDetail> detailList = new ArrayList<>();
//			JSONArray domain = json.getJSONArray("ALNDOMAIN");
//			for (int i = 0; i < domain.length(); i++) {
//				// 气体大类别
//				GasDetail detail = new GasDetail();
//				JSONObject json1 = domain.getJSONObject(i);
//				// 气体小类别
//				JSONArray qtType = json1.getJSONArray("UDQTTYPE");
//				List<Domain> gasList = new ArrayList<>();
//				for (int j = 0; j < qtType.length(); j++) {
//					Domain d = new Domain();
//					String qtTypeId = qtType.getJSONObject(j).getString(
//							"valueid");
//					String qtTypeName = qtType.getJSONObject(j).getString(
//							"description");
//					String domainId = qtType.getJSONObject(j).getString(
//							"domainid");
//					double minValue = 0;
//					if (qtType.getJSONObject(j).has("qtminvalue")) {
//						minValue = qtType.getJSONObject(j).getDouble(
//								"qtminvalue");
//					}
//					double maxValue = 0;
//					if (qtType.getJSONObject(j).has("qtmaxvalue")) {
//						maxValue = qtType.getJSONObject(j).getDouble(
//								"qtmaxvalue");
//					}
//					d.setDomainid(domainId);
//					d.setValue(qtTypeId);
//					d.setDescription(qtTypeName);
//					d.setMinValue(minValue);
//					d.setMaxValue(maxValue);
//					gasList.add(d);
//				}
//				detail.setGasList(gasList);
//				detailList.add(detail);
//			}
//			// 解析气体检测历史记录
//			List<GasDetection2> distorys = new ArrayList<>();
//			if (json.has("UD_ZYXK_QTJC")) {
//				JSONArray UD_ZYXK_QTJC = json.getJSONArray("UD_ZYXK_QTJC");
//				for (int i = 0; i < UD_ZYXK_QTJC.length(); i++) {
//					GasDetection2 gas = new GasDetection2();
//					if (UD_ZYXK_QTJC.getJSONObject(i).has("jctime")) {
//						gas.setTime(UD_ZYXK_QTJC.getJSONObject(i).getString(
//								"jctime"));
//					}
//					gas.setOk(UD_ZYXK_QTJC.getJSONObject(i).getInt("ishg") <= 0 ? false
//							: true);
//					if (UD_ZYXK_QTJC.getJSONObject(i).has("jclocation_desc")) {
//						gas.setAddr(UD_ZYXK_QTJC.getJSONObject(i).getString(
//								"jclocation_desc"));
//					}
//					List<GasDetail> gasDetails = new ArrayList<>();
//					JSONArray UD_ZYXK_QTJCLINE = UD_ZYXK_QTJC.getJSONObject(i)
//							.getJSONArray("UD_ZYXK_QTJCLINE");
//					for (int j = 0; j < UD_ZYXK_QTJCLINE.length(); j++) {
//						GasDetail detail = new GasDetail();
//						if (UD_ZYXK_QTJCLINE.getJSONObject(j).has("qttype")) {
//							detail.setSelectedGasName(UD_ZYXK_QTJCLINE
//									.getJSONObject(j).getString("qttype"));
//						}
//						if (UD_ZYXK_QTJCLINE.getJSONObject(j).has("qtvalue")) {
//							detail.setSelectedGasValue(UD_ZYXK_QTJCLINE
//									.getJSONObject(j).getString("qtvalue"));
//						}
//						gasDetails.add(detail);
//					}
//					gas.setGasDetails(gasDetails);
//					distorys.add(gas);
//				}
//			}
//			Map<String, Object> map = new HashMap<>();
//			map.put(GASDETAILLIST, detailList);
//			map.put(GASDISTORY, distorys);
//			return map;
//		} catch (JSONException e) {
//			logger.error(e);
//			return null;
//		}
//	}
//
//	@Override
//	public String getMethodType() {
//		return PadInterfaceContainers.METHOD_ZYPONLINE_QTJC_GET;
//	}
//
//	@Override
//	public BaseWebResult getResultChangeType() {
//		return null;
//	}
//}
