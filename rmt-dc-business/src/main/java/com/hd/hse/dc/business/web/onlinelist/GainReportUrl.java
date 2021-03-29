package com.hd.hse.dc.business.web.onlinelist;

import java.io.File;

import com.hd.hse.business.webservice.WebConfig;
import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.padinterface.PadInterfaceContainers;

/**
 * ClassName: GainReportUrl (获取报表的URL值)<br/>
 * date: 2015年6月4日 <br/>
 * 
 * @author lxf
 * @version
 */
public class GainReportUrl extends GainNewsServerUrl {

	@Override
	public Object action(String action, Object... args) throws HDException {
		// TODO Auto-generated method stub
		String obj = super.action(action, args).toString();
		return getPeportUrl(obj.toString(), args);
	}

	@Override
	public String getMethodType() {
		// TODO Auto-generated method stub
		return PadInterfaceContainers.METHOD_COMMON_GETREPORTURL;
	}

	private String getPeportUrl(String urlpart, Object... args) {
		WebConfig webconfig = getWebConfigParam();
		String reportUrl = webconfig.getUrl().replace(
				File.separator + webconfig.getUrlpart(), "")
				+ urlpart;
		if (args != null) {
			if (args[0] instanceof SuperEntity) {
				SuperEntity superE = (SuperEntity) args[0];
				reportUrl = reportUrl
						+ superE.getAttribute(superE.getPrimaryKey());
			}
		}

		return reportUrl;
	}

}
