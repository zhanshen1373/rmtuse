package com.hd.hse.dc.business.web.onlinelist;

import java.util.HashMap;

import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.padinterface.PadInterfaceContainers;
import com.hd.hse.padinterface.PadInterfaceRequest;

/**
 * ClassName: GainZYXKBrowseInfo (获取作业票浏览信息 报表)<br/>
 * date: 2015年6月2日 <br/>
 * 
 * @author lxf
 * @version
 */
public class GainZYXKBrowseInfo extends GainDataInfoBaseOnline {

	@Override
	public Object action(String action, Object... args) throws HDException {
		// TODO Auto-generated method stub
		if (args != null && args.length > 1) {
			if (args[1] != null) {
				String search = args[1].toString();
				setSearch(search);
			}
		}
		return super.action(action, args);
	}

	@Override
	public String getMethodType() {
		// TODO Auto-generated method stub
		return PadInterfaceContainers.METHOD_ZYPONLINE_LIST;
	}

	private HashMap<String, Object> hashmap = null;

	@Override
	public Object initParam() {
		// TODO Auto-generated method stub
		if (hashmap == null) {
			hashmap = (HashMap<String, Object>) super.initParam();
			hashmap.put(PadInterfaceRequest.KEYFUNCODE, "true");
		}

		return hashmap;
	}
}
