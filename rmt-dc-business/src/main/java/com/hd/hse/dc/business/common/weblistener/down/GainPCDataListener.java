package com.hd.hse.dc.business.common.weblistener.down;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import com.hd.hse.common.exception.HDException;
import com.hd.hse.dc.business.common.result.BaseWebResult;
import com.hd.hse.dc.business.common.result.IProcessResultSet;
import com.hd.hse.dc.business.common.weblistener.AbsWebListener;
import com.hd.hse.padinterface.PadInterfaceResponse;

/**
 * ClassName: GainPCDataListener (获取PC端数据)<br/>
 * date: 2015年3月20日 <br/>
 * 
 * @author lxf
 * @version
 */
public abstract class GainPCDataListener extends AbsWebListener {

	@Override
	public Object action(String action, Object... args) throws HDException {
		Object obj;
		try {
			super.action(action, args);
			beforDataInfo(args);
			obj = getDataInfo(args);
			afterDataInfo(obj, args);
		} catch (HDException e) {
			setWritelog(e.getMessage());
			throw e;
		}
		return obj;
	}

	/**
	 * beforDown:(下载前动作). <br/>
	 * date: 2015年3月19日 <br/>
	 * 
	 * @author lxf
	 * @param obj
	 * @throws HDException
	 */
	public void beforDataInfo(Object... obj) throws HDException {
		if (!(initParam() instanceof HashMap)) {
			throw new HDException("initParam()必须设置为HashMap对象;");
		}
	}

	/**
	 * down:(下载动作). <br/>
	 * date: 2015年3月19日 <br/>
	 * 
	 * @author Administrator
	 * @param obj
	 * @throws HDException
	 */
	@SuppressWarnings("unchecked")
	public Object getDataInfo(Object... obj) throws HDException {
		String methodtype = getMethodType();
		if (StringUtils.isEmpty(methodtype)) {
			throw new HDException("请注册要调用方法的");
		}
		Object retobj = sClient.action(methodtype,
				(HashMap<String, Object>) initParam());
		if (retobj instanceof PadInterfaceResponse) {
			PadInterfaceResponse response = (PadInterfaceResponse) retobj;
			setWritelog("PC错误：" + response.getExceptionmsg());
			throw new HDException("加载失败,请联系管理员");
		}
		if(retobj==null){
			retobj="[]";
		}
		setWriteDubuglog(retobj.toString());
		BaseWebResult type = getResultChangeType();
		if (null == type) {
			return retobj;
		} else {
			return type.processResultSet(retobj);
		}
	}

	/**
	 * afterdown:(下载后的动作). <br/>
	 * date: 2015年3月19日 <br/>
	 * 
	 * @author lxf
	 * @param obj
	 */
	public void afterDataInfo(Object pcdata, Object... obj) throws HDException {

	}

	/**
	 * 设置返回查询结结果对象
	 * */
	public BaseWebResult getResultChangeType() {
		return null;
	}
}
