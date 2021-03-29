package com.hd.hse.entity.test;
/**
 * 二级级状态实体类
 * @author 三人行技术开发团队
 */
public class TwoStatusEntity extends com.hd.hse.common.entity.SuperEntity {
	/* 状态名称 */
	private String actionName;
	/* 活动时间 */
	private String actionTime;
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getActionTime() {
		return actionTime;
	}
	public void setActionTime(String actionTime) {
		this.actionTime = actionTime;
	}
	
	
	
}
