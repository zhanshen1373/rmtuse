package com.hd.hse.dc.business.web.onlinelist;

import java.util.HashMap;

import com.hd.hse.common.exception.HDException;
import com.hd.hse.entity.workorder.ItemWorkTask;
import com.hd.hse.entity.workorder.WorkApprovalPersonRecord;
import com.hd.hse.entity.workorder.WorkTask;
import com.hd.hse.padinterface.PadInterfaceContainers;
import com.hd.hse.padinterface.PadInterfaceRequest;

/**
 * ClassName: GainItemProgressInfoOnline (获取分项任务信息)<br/>
 * date: 2015年7月29日 <br/>
 * 
 * @author lxf
 * @version
 */
public class GainItemProgressInfoOnline extends GainDataInfoBaseOnline {

	String bcid=null;
	@Override
	public Object action(String action, Object... args) throws HDException {
		// TODO Auto-generated method stub
		//添加任务ID
		//setTaskid(0);
		bcid="0";
		if(args!=null && args.length>1){
			bcid=((ItemWorkTask) args[1]).getUd_zyxk_bcid();
		}
		return super.action(action, args);
	}

	@Override
	public Object initParam() {
		@SuppressWarnings("unchecked")
		HashMap<String, Object> hashmap = (HashMap<String, Object>) super
				.initParam();
		// 添加分项任务条件
		hashmap.put(PadInterfaceRequest.KEYTABLIEID, bcid);
		return hashmap;
	}

	@Override
	public String getMethodType() {
		// TODO Auto-generated method stub
		return PadInterfaceContainers.METHOD_ZYPONLINE_ITEMPROGRESS;
	}
	@Override
	public Class<?> getEntityClass() {
		// TODO Auto-generated method stub
		return WorkApprovalPersonRecord.class;
	}
}
