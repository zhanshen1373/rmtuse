package com.hd.hse.dc.business.listener.common;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hd.hse.business.task.IMessageWhat;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.dc.business.common.weblistener.down.GainPCDataListener;
import com.hd.hse.padinterface.PadInterfaceContainers;
import com.hd.hse.padinterface.PadInterfaceRequest;
import com.hd.hse.padinterface.PadInterfaceResponse;
import com.hd.hse.system.SystemProperty;

/**
 * ClassName: GetVersionInfo (获取服务器版本信息)<br/>
 * date: 2015年3月19日 <br/>
 * 
 * @author lxf
 * @version
 */
public class GetVersionInfo extends GainPCDataListener {

	private static Logger logger = LogUtils.getLogger(GetVersionInfo.class);
	private static String KEYVALUEVERSION = "ZYXKVERSION";

	/**
	 * setKeyValueVersion:(添加设置取动态域的主键). <br/>
	 * date: 2015年5月4日 <br/>
	 *
	 * @author lxf
	 * @param keyValue
	 */
	public void setKeyValueVersion(String keyValue) {
		KEYVALUEVERSION = keyValue;
	}

	@Override
	public Object action(String action, Object... args) throws HDException {
		Object obj = null;
		try {
			obj = super.action(action, args);
			this.sendMessage(IMessageWhat.END, 100, 100, "", obj);
		} catch (HDException e) {
			getLogger().error(e.getMessage(), e);
			this.sendMessage(IMessageWhat.ERROR, 9, 9, e.getMessage());
		}
		return obj;
	}

	@Override
	public void afterDataInfo(Object pcdata, Object... obj) throws HDException {
		// TODO Auto-generated method stub
		super.afterDataInfo(pcdata, obj);
		if (pcdata instanceof PadInterfaceResponse) {
			PadInterfaceResponse response = (PadInterfaceResponse) pcdata;
			setWritelog("pc报错：" + response.getExceptionmsg());
			throw new HDException("下载版本信息报错,请联系管理员");
		}
	}

	@Override
	public Object initParam() {
		// TODO Auto-generated method stub
		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		hashmap.put(PadInterfaceRequest.KEYPERSONID, SystemProperty
				.getSystemProperty().getLoginPerson().getPersonid());
		String dept = SystemProperty.getSystemProperty().getLoginPerson()
				.getDepartment();
		if (!StringUtils.isEmpty(dept)) {
			hashmap.put(PadInterfaceRequest.KEYDEPTNUM, dept);
		}
		// value 表示请求承包商的url
		hashmap.put(PadInterfaceRequest.KEYPADAPKURL, KEYVALUEVERSION);
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
		return PadInterfaceContainers.METHOD_COMMON_VERSIONIFNO;
	}

}
