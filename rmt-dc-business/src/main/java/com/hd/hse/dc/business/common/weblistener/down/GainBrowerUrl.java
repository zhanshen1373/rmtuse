package com.hd.hse.dc.business.common.weblistener.down;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.dc.business.common.weblistener.EachListener;
import com.hd.hse.padinterface.PadInterfaceContainers;
import com.hd.hse.padinterface.PadInterfaceRequest;
import com.hd.hse.system.SystemProperty;

/**
 * ClassName: GainBrowerUrl (最终审核人在线审核)<br/>
 * date: 2015年5月25日  <br/>
 *
 * @author lxf
 * @version 
 */
public class GainBrowerUrl extends EachListener {

	private HashMap<String, Object> hashmap=new HashMap<String, Object>(); 
	private static Logger logger = LogUtils.getLogger(GainBrowerUrl.class);
	@Override
	public Object initParam() throws HDException {
		
		hashmap.put(PadInterfaceRequest.KEYPERSONID, SystemProperty
				.getSystemProperty().getLoginPerson().getPersonid());
		String dept = SystemProperty.getSystemProperty().getLoginPerson()
				.getDepartment();
			hashmap.put(PadInterfaceRequest.KEYDEPTNUM, dept);
		return hashmap;
	}

	@Override
	public Logger getLogger() {
		// TODO Auto-generated method stub
		return logger;
	}

	@Override
	public String getMethodType() {
		// TODO Auto-generated method stub
		return PadInterfaceContainers.METHOD_ZYP_ONLINENULLIFY;
	}

}
