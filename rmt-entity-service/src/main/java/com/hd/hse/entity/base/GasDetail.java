/**
 * Project Name:hse-entity-service
 * File Name:GasDetail.java
 * Package Name:com.hd.hse.entity.base
 * Date:2015年12月10日
 * Copyright (c) 2015, zhaofeng@ushayden.com All Rights Reserved.
 *
 */

package com.hd.hse.entity.base;

import java.util.List;
import java.util.Map;

/**
 * ClassName:GasDetail ().<br/>
 * Date: 2015年12月10日 <br/>
 * 
 * @author LiuYang
 * @version
 * @see
 */
public class GasDetail {

//	private Map<String, String> gasMap; // 气体列表<编码/中文名>
	private List<Domain> gasList; // 气体列表
	private String selectedGasId; // 选中的气体编码
	private String selectedGasName; // 选中的气体中文名
	private String selectedGasValue; // 气体浓度值

	private String parentGasId; // 气体大类的编码

	public String getParentGasId() {
		return parentGasId;
	}

	public void setParentGasId(String parentGasId) {
		this.parentGasId = parentGasId;
	}

	public List<Domain> getGasList() {
		return gasList;
	}

	public void setGasList(List<Domain> gasList) {
		this.gasList = gasList;
	}

//	public Map<String, String> getGasMap() {
//		return gasMap;
//	}
//
//	public void setGasMap(Map<String, String> gasMap) {
//		this.gasMap = gasMap;
//	}

	public String getSelectedGasId() {
		return selectedGasId;
	}

	public void setSelectedGasId(String selectedGasId) {
		this.selectedGasId = selectedGasId;
	}

	public String getSelectedGasName() {
		return selectedGasName;
	}

	public void setSelectedGasName(String selectedGasName) {
		this.selectedGasName = selectedGasName;
	}

	public String getSelectedGasValue() {
		return selectedGasValue;
	}

	public void setSelectedGasValue(String selectedGasValue) {
		this.selectedGasValue = selectedGasValue;
	}
}
