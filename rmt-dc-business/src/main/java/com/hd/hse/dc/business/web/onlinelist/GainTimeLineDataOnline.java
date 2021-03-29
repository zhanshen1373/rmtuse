package com.hd.hse.dc.business.web.onlinelist;

import java.util.HashMap;

import com.hd.hse.common.exception.HDException;
import com.hd.hse.entity.workorder.ItemWorkTask;
import com.hd.hse.entity.workorder.WorkOrder;
import com.hd.hse.entity.workorder.WorkTask;
import com.hd.hse.padinterface.PadInterfaceContainers;
import com.hd.hse.padinterface.PadInterfaceRequest;

public class GainTimeLineDataOnline extends GainDataInfoBaseOnline {
	String bcid = null;
	String zypid = null;

	@Override
	public Object action(String action, Object... args) throws HDException {
		// 添加任务ID
		// setTaskid(0);
		bcid = "0";
		if (args != null && args.length > 1) {
			bcid = ((ItemWorkTask) args[1]).getUd_zyxk_bcid();
			zypid = ((ItemWorkTask) args[1]).getUd_zyxk_worktaskid();
		}
		return super.action(action, args);
	}

	@Override
	public Object initParam() {
		@SuppressWarnings("unchecked")
		HashMap<String, Object> hashmap = (HashMap<String, Object>) super
				.initParam();
		// 添加分项任务条件
		hashmap.put(PadInterfaceRequest.KEYTABLIEID, bcid);
		hashmap.put(PadInterfaceRequest.KEYZYPSTRID, zypid);
		return hashmap;
	}

	@Override
	public String getMethodType() {
		// TODO Auto-generated method stub
		return PadInterfaceContainers.METHOD_ZYPONLINE_TIMELINEDATA;
	}

	@Override
	public Class<?> getEntityClass() {
		// TODO Auto-generated method stub
		return WorkOrder.class;
	}
}
