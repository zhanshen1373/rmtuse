package com.hd.hse.dc.business.web.onlinelist;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.dc.business.common.weblistener.down.GainPCDataListener;
import com.hd.hse.padinterface.PadInterfaceContainers;
import com.hd.hse.padinterface.PadInterfaceRequest;
import com.hd.hse.padinterface.PadInterfaceResponse;

/**
 * ClassName: GainNewsServerUrl (获取消息服务的URL地址)<br/>
 * date: 2015年6月4日 <br/>
 * 
 * @author lxf
 * @version
 */
public class GainNewsServerUrl extends GainPCDataListener {
	private static Logger logger = LogUtils.getLogger(GainNewsServerUrl.class);
	private HashMap<String, Object> hashmap;
	String retStr = null;

	@Override
	public Object action(String action, Object... args) throws HDException {
		// TODO Auto-generated method stub
		retStr = "";
		super.action(action, args);
		return retStr;
	}

	@Override
	public Object initParam() throws HDException {
		hashmap = new HashMap<String, Object>();
		return hashmap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void afterDataInfo(Object pcdata, Object... obj) throws HDException {
		// TODO Auto-generated method stub
		if(pcdata instanceof PadInterfaceResponse){
			PadInterfaceResponse response = (PadInterfaceResponse) pcdata;
			setWritelog("pc报错："+response.getExceptionmsg());
			throw new HDException("pc端获取消息服务URL失败,请联系管理员");
		}
		if (pcdata instanceof Map) {
			hashmap = (HashMap<String, Object>) pcdata;
			if (hashmap.size() == 0) {
				retStr = null;
			} else {
				if (hashmap.containsKey(PadInterfaceRequest.KEYNEWSSERVERURL))
					retStr=hashmap.get(PadInterfaceRequest.KEYNEWSSERVERURL)
							.toString();
			}
		} else {
			retStr = null;
		}
	}

	@Override
	public Logger getLogger() {
		// TODO Auto-generated method stub
		return logger;
	}

	@Override
	public String getMethodType() {
		// TODO Auto-generated method stub
		return PadInterfaceContainers.METHOD_COMMON_GETNEWSSERVERURL;
	}

}
