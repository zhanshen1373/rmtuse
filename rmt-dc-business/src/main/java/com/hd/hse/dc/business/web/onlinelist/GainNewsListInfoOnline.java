package com.hd.hse.dc.business.web.onlinelist;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.dc.business.common.result.BaseWebResult;
import com.hd.hse.dc.business.common.result.EntityListWebResult;
import com.hd.hse.dc.business.common.weblistener.down.DataListListener;
import com.hd.hse.entity.common.PushMessage;
import com.hd.hse.padinterface.PadInterfaceContainers;
import com.hd.hse.padinterface.PadInterfaceRequest;
import com.hd.hse.system.SystemProperty;

/**
 * ClassName: GainNewsListInfoOnline (获取消息列表)<br/>
 * date: 2015年6月3日 <br/>
 * 
 * @author lxf
 * @version
 */
public class GainNewsListInfoOnline extends DataListListener {


	private static Logger logger = LogUtils
			.getLogger(GainNewsListInfoOnline.class);
	private HashMap<String, Object> hashmap = new HashMap<String, Object>();

	@Override
	public BaseWebResult getResultChangeType() {
		// TODO Auto-generated method stub
		try {
			return new EntityListWebResult(getEntityClass(),
					getAnalyzeDataAdapter());
		} catch (HDException e) {
			setWritelog(e.getMessage());
		}
		return null;
	}

	@Override
	public Class<?> getEntityClass() {
		// TODO Auto-generated method stub
		return PushMessage.class;
	}

	@Override
	public String getMethodType() {
		// TODO Auto-generated method stub
		return PadInterfaceContainers.METHOD_ZYPONLINE_NEWSLIST;
	}

	@Override
	public String[] getColumns() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object initParam() throws HDException {
		// TODO Auto-generated method stub
		hashmap.put(PadInterfaceRequest.KEYPERSONID, SystemProperty
				.getSystemProperty().getLoginPerson().getPersonid());
		// 表示查看本地作业票是，浏览PC端票
		hashmap.put(PadInterfaceRequest.KEYFUNCODE, "true");

		return hashmap;
	}

	@Override
	public Logger getLogger() {
		// TODO Auto-generated method stub
		return logger;
	}
}
