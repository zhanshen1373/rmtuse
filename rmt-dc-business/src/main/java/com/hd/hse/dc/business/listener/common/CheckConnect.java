package com.hd.hse.dc.business.listener.common;

import java.io.File;

import org.apache.log4j.Logger;

import com.hd.hse.business.webservice.WebConfig;
import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.dc.business.common.weblistener.down.GainPCDataListener;
import com.hd.hse.entity.common.SysConfig;
import com.hd.hse.padinterface.PadInterfaceContainers;
import com.hd.hse.system.SystemProperty;

/**
 * ClassName: CheckConnect (检查网络)<br/>
 * date: 2015年3月19日 <br/>
 * 
 * @author lxf
 * @version
 */
public class CheckConnect extends GainPCDataListener {
	private static Logger logger = LogUtils.getLogger(CheckConnect.class);

	private SuperEntity sysconfig = null;

	@Override
	public Object action(String action, Object... args) throws HDException {
		// TODO Auto-generated method stub
		if (args != null) {
			if (args[0] instanceof SuperEntity) {
				sysconfig = (SuperEntity) args[0];
			}
		}
		return super.action(action, args);
	}

	@Override
	public void beforDataInfo(Object... obj) throws HDException {
		// 重载 不要验证
	}

	@Override
	public Object initParam() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Logger getLogger() {
		// TODO Auto-generated method stub
		return logger;
	}

	@Override
	public String getMethodType() {
		// TODO Auto-generated method stub
		return PadInterfaceContainers.METHOD_COMMON_TEST;
	}

	@Override
	public WebConfig getWebConfigParam() {
		// TODO Auto-generated method stub
		SystemProperty.getSystemProperty().clearWebConfig();
		WebConfig config = super.getWebConfigParam();
		if (sysconfig != null) {
			String url = sysconfig.getAttribute("sysurl") != null ? sysconfig
					.getAttribute("sysurl").toString() : "";
			if (url != "")
				url=url+File.separator+config.getUrlpart();
				if (url.contains("http")) {
					config.setUrl(url);
				}
				else{
					config.setUrl("http://"+url);
				}

		}
		return config;
	}

}
