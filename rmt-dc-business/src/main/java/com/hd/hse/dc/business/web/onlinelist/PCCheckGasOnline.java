package com.hd.hse.dc.business.web.onlinelist;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.dc.business.common.weblistener.down.GainPCDataListener;
import com.hd.hse.dc.business.listener.common.PCCheckUser;
import com.hd.hse.entity.workorder.WorkApplyMeasure;
import com.hd.hse.padinterface.PadInterfaceContainers;
import com.hd.hse.padinterface.PadInterfaceResponse;

/**
 * ClassName: PCCheckGasOnline (在线验证措施气体检测是否合格---暂时不用)<br/>
 * date: 2015年6月3日  <br/>
 *
 * @author lxf
 * @version 
 */
public class PCCheckGasOnline extends GainPCDataListener {
	private static Logger logger = LogUtils.getLogger(PCCheckUser.class);
	private HashMap<String, Object> hashmap;
	WorkApplyMeasure measure = null;
	@Override
	public Object action(String action, Object... args) throws HDException {
		Object obj = args[0];
		measure = (WorkApplyMeasure) obj;
		if(measure==null){
			throw new HDException("请传入验证的用户对象!");
		}
		super.action(action, args);
		return measure;
	}
	@Override
	public void beforDataInfo(Object... args) throws HDException {
//		hashmap = new HashMap<String, Object>();
//		String str = pcard.getPcardnum();
//		if (StringUtils.isEmpty(str)) {
//			str = pcard.getPersonid();
//		}
//		if (StringUtils.isEmpty(str)) {
//			throw new HDException("请刷卡或输入账号!");
//		}
//		String pw = pcard.getPassword();
//		hashmap.put(PadInterfaceRequest.KEYPERSONID, str);
//		hashmap.put(PadInterfaceRequest.KEYPASSWORD, pw);
	}
	@SuppressWarnings("unchecked")
	@Override
	public void afterDataInfo(Object pcdata, Object... obj) throws HDException {
		if(pcdata instanceof PadInterfaceResponse){
			PadInterfaceResponse response = (PadInterfaceResponse) pcdata;
			setWritelog("pc报错："+response.getExceptionmsg());
			throw new HDException("pc端验证失败,请联系管理员");
		}
		if (pcdata instanceof Map) {
			hashmap = (HashMap<String, Object>) pcdata;
			if (hashmap.size() == 0) {
				measure = null;
			} else {
//				if (hashmap.containsKey(PadInterfaceRequest.KEYPERSONID))
//					pcard.setPersonid(hashmap.get(PadInterfaceRequest.KEYPERSONID)
//							.toString());
//				if (hashmap.containsKey(PadInterfaceRequest.KEYPERSONDESC))
//					pcard.setPersonid_desc(hashmap.get(PadInterfaceRequest.KEYPERSONDESC)
//							.toString());
//				if (hashmap.containsKey(PadInterfaceRequest.KEYDEPTNUM))
//					pcard.setDepartment(hashmap.get(PadInterfaceRequest.KEYDEPTNUM)
//							.toString());
//				if (hashmap.containsKey(PadInterfaceRequest.KEYDEPTDESC))
//					pcard.setDepartment_desc(hashmap.get(PadInterfaceRequest.KEYDEPTDESC)
//							.toString());
			}
		} else {
			measure = null;
		}
	}
	@Override
	public Object initParam() {
		// TODO Auto-generated method stub
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
		return PadInterfaceContainers.METHOD_ZYPONLINE_ZYPCHECKGAS;
	}

}
