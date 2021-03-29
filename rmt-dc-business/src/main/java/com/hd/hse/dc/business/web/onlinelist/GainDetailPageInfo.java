package com.hd.hse.dc.business.web.onlinelist;

import java.util.HashMap;
import java.util.Map;

import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.dc.business.common.result.BaseWebResult;
import com.hd.hse.dc.business.common.result.GsonEntityListSpecialDealWebResult;
import com.hd.hse.entity.base.PDAWorkOrderInfoConfig;
import com.hd.hse.padinterface.PadInterfaceContainers;
import com.hd.hse.padinterface.PadInterfaceRequest;

/**
 * ClassName: GainDetailPageInfo (获取详细页面信息)<br/>
 * date: 2015年5月28日 <br/>
 * 
 * @author lxf
 * @version
 */
public class GainDetailPageInfo extends GainDataInfoBaseOnline {

	private String where = "";
	private String newid;
	@Override
	public Object action(String action, Object... args) throws HDException {
		// TODO Auto-generated method stub
		newid=null;
		where="";
		if (args != null && args.length > 1) {
			if (args[1] instanceof SuperEntity) {
				SuperEntity sup = (SuperEntity) args[1];
				where = sup.getAttribute(sup.getPrimaryKey()) == null ? ""
						: sup.getAttribute(sup.getPrimaryKey()).toString();
				// setSearch(where);
			} else if (args[1] instanceof String) {
				where = args[1].toString();
				// setSearch(where);
			} else {
				throw new HDException("请传入条件");
			}
		}
		if(args != null && args.length > 2 && args[2]!=null){
			newid=args[2].toString();
		}
		return super.action(action, args);
	}

	@Override
	public Object initParam() {
		// TODO Auto-generated method stub
		Object obj = super.initParam();
		if (obj instanceof HashMap) {
			HashMap<String, Object> hashmap = (HashMap<String, Object>) obj;
			hashmap.put(PadInterfaceRequest.KEYZYPSTRID, where);
			if(newid!=null){
				hashmap.put(PadInterfaceRequest.KEYNEWSID, newid);
			}
			else{
				hashmap.put(PadInterfaceRequest.KEYNEWSID, "");
			}
			return hashmap;
		}
		return obj;
	}

	@Override
	public BaseWebResult getResultChangeType() {
		// TODO Auto-generated method stub
		try {
			return new GsonEntityListSpecialDealWebResult(getEntityClass(),
					getAnalyzeDataAdapter());
		} catch (HDException e) {
			setWritelog(e.getMessage());
		}
		return null;
	}

	@Override
	public Class<?> getEntityClass() {
		// 此处填写page对象类
		return PDAWorkOrderInfoConfig.class;
	}

	@Override
	public String getMethodType() {
		// TODO Auto-generated method stub
		return PadInterfaceContainers.METHOD_ZYPONLINE_OPPAGE;
	}

}
