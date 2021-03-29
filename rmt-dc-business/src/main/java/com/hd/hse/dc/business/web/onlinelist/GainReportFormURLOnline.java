package com.hd.hse.dc.business.web.onlinelist;

import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.dc.business.common.result.BaseWebResult;
import com.hd.hse.padinterface.PadInterfaceContainers;

public class GainReportFormURLOnline extends GainDataInfoBaseOnline {
	private static Logger logger = LogUtils
			.getLogger(GainReportFormURLOnline.class);
	private String workOrderId;

	@Override
	public Object action(String action, Object... args) throws HDException {
		if (args != null && args.length != 0) {
			workOrderId = (String) args[0];
		}
		setTaskid(workOrderId);
//		Object obj = null;
		Map<String, Object> map = (Map<String, Object>) super.action(action, args);
		return map.get("rpturl");
	}

	// {rpturl=http://221.7.54.205:8888/maximo//rqReport/reportJsp/showReport_pad.jsp?raq=NXSH_GENERAL.raq&zysqid=22756,
	// value=GENERAL}
//	private String paser(JSONObject json) throws JSONException {
//		String url = json.getString("rpturl");
//		return url;
//	}

	@Override
	public String getMethodType() {
		return PadInterfaceContainers.METHOD_ZYPONLINE_REPORTFORMURL_GET;
	}

	@Override
	public BaseWebResult getResultChangeType() {
		return null;
	}
}
