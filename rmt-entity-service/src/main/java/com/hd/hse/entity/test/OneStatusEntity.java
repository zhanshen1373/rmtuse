package com.hd.hse.entity.test;

import java.util.List;

/**
 * 一级状态实体类
 * @author 三人行技术开发团队
 */
public class OneStatusEntity extends com.hd.hse.common.entity.SuperEntity {
	/* 状态名称 */
	private String statusName;
	/* 二级状态list */
	private List<TwoStatusEntity> twoList;
	
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public List<TwoStatusEntity> getTwoList() {
		return twoList;
	}
	public void setTwoList(List<TwoStatusEntity> twoList) {
		this.twoList = twoList;
	}
	
	
}
