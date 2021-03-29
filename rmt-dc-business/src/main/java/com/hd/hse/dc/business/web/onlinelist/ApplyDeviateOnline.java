package com.hd.hse.dc.business.web.onlinelist;

import com.hd.hse.padinterface.PadInterfaceContainers;

/**
 * ClassName: ApplyDeviateOnline (申请偏离)<br/>
 * date: 2015年6月2日  <br/>
 *
 * @author lxf
 * @version 
 */
public class ApplyDeviateOnline extends UpSaveZYXKInfoOnline {
	
	
	/**
	 * TODO  设置调用申请偏离的方法 
	 * @see UpSaveZYXKInfoOnline#getMethodType()
	 */
	@Override
	public String getMethodType() {
		// TODO Auto-generated method stub
		return PadInterfaceContainers.METHOD_ZYPONLINE_ZYPAPPLYDEVIATE;
	}

}
