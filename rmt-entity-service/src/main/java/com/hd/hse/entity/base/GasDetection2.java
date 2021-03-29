/**
 * Project Name:hse-entity-service
 * File Name:GasDetection2.java
 * Package Name:com.hd.hse.entity.base
 * Date:2015年12月10日
 * Copyright (c) 2015, zhaofeng@ushayden.com All Rights Reserved.
 */

package com.hd.hse.entity.base;

import com.hd.hse.entity.workorder.RmtWorkGasDetect;

import java.util.List;

/**
 * ClassName:GasDetection2 ().<br/>
 * Date: 2015年12月10日 <br/>
 *
 * @author zhaofeng
 * @version
 * @see
 */
public class GasDetection2 {
	private String addr; // 检测位置
	private String time; // 检测时间
	//  private List<GasDetail> gasDetails; // 气体浓度\
	private List<RmtWorkGasDetect.DictvosBean.RMTGASTYPETREEMBean> gasDetails;
	//    private boolean isOk;
	private Integer isOk; // 是否合格





	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}


	public List<RmtWorkGasDetect.DictvosBean.RMTGASTYPETREEMBean> getGasDetails() {
		return gasDetails;
	}

	public void setGasDetails(List<RmtWorkGasDetect.DictvosBean.RMTGASTYPETREEMBean> gasDetails) {
		this.gasDetails = gasDetails;
	}

	public Integer getIsOk() {
		return isOk;
	}

	public void setIsOk(Integer isOk) {
		this.isOk = isOk;
	}

}
