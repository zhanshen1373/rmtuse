package com.hd.hse.dc.business.web.onlinelist;

import org.apache.log4j.Logger;

import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.padinterface.PadInterfaceContainers;

/**
 * ClassName: DelNewsInfoOnline (删除消息信息)<br/>
 * date: 2015年7月10日  <br/>
 *
 * @author lxf
 * @version 
 */
public class DelNewsInfoOnline extends UpSaveZYXKInfoOnline {
	private static Logger logger = LogUtils.getLogger(DelNewsInfoOnline.class);
	/**
	 * TODO 
	 * @see UpSaveZYXKInfoOnline#beforUpDataInfo(Object[])
	 * 数据上传前的处理
	 */
	@Override
	public boolean beforUpDataInfo(Object... args) throws HDException {
		// TODO Auto-generated method stub
		if (args != null) {
			return true;
		} else {
			throw new HDException("请传掺入要操作的信息");
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
		return PadInterfaceContainers.METHOD_ZYPONLINE_DELNEWS;
	}
}
