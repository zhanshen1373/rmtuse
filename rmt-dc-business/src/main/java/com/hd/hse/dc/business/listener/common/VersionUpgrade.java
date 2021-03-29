package com.hd.hse.dc.business.listener.common;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hd.hse.business.webservice.WebConfig;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.dc.business.common.weblistener.down.DownFilelistener;
import com.hd.hse.padinterface.PadInterfaceRequest;
import com.hd.hse.system.SystemProperty;

public class VersionUpgrade extends DownFilelistener {

	private static Logger logger= LogUtils.getLogger(VersionUpgrade.class);

	private String downUrl;
	
	@Override
	public Object action(String action, Object... args) throws HDException {
		// TODO Auto-generated method stub
		downUrl=args[0].toString();
		return super.action(action, args);
	}
	@Override
	public String getDownUrl() {
		return downUrl;
		//return "http://192.168.6.11:7001/maximo/upload/hse-cbs-app.apk";
	}

	@Override
	public String getSavePath() {
		
		return SystemProperty.getSystemProperty().getRootVersionPath();
	}

	@Override
	public WebConfig getWebConfigParam() {
		return SystemProperty.getSystemProperty().getWebDataConfig();
	}

	@Override
	public Object initParam() {
		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		hashmap.put(PadInterfaceRequest.KEYPERSONID, SystemProperty.getSystemProperty().getLoginPerson().getPersonid());
		//hashmap.put(PadInterfaceRequest.KEYDEPTNUM, SystemProperty.getSystemProperty().getLoginPerson().getDepartment());
		String dept=SystemProperty.getSystemProperty().getLoginPerson().getDepartment();
		if(!StringUtils.isEmpty(dept))
		{
			hashmap.put(PadInterfaceRequest.KEYDEPTNUM,dept);
		}
		return hashmap;
	}

	@Override
	public Logger getLogger() {
		return logger;
	}
	@Override
	public String getMethodType() {
		// TODO Auto-generated method stub
		return null;
	}

}
