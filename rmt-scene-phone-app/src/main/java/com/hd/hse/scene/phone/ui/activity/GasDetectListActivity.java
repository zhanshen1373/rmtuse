/**
 * Project Name:hse-scene-phone-app
 * File Name:GasDetectListActivity.java
 * Package Name:com.hd.hse.scene.phone.ui.activity
 * Date:2015年12月8日
 * Copyright (c) 2015, zhaofeng@ushayden.com All Rights Reserved.
 *
*/

package com.hd.hse.scene.phone.ui.activity;

import com.hd.hse.common.module.phone.ui.module.activity.BaseTaskListBusActivity;

/**
 * ClassName:GasDetectListActivity ().<br/>
 * Date:     2015年12月8日  <br/>
 * @author   LiuYang
 * @version  
 * @see 	 
 */
public class GasDetectListActivity extends BaseTaskListBusActivity {
	
	@Override
	public String getTitileName() {
		return "气体检测";
	}
	
	@Override
	public String getNavCurrentKey() {
		return "hse-gas-phone-app";
	}
	
	@Override
	public Class<?> getToActivityClass() {
		return GasDetectActivity.class;
	}

}

