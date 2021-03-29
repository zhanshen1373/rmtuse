package com.hd.hse.dc.business.web.onlinelist;

import com.hd.hse.padinterface.PadInterfaceContainers;

public class UpSaveTempEleOnline extends UpSaveZYXKInfoOnline {
	@Override
	public String getMethodType() {
		return PadInterfaceContainers.METHOD_ZYPONLINE_TEMPELE_SAVE;
	}
}
