/**
 * Project Name:hse-scene-phone-app
 * File Name:GasDetectApp.java
 * Package Name:com.hd.hse.scene.phone.ui.event.homepage
 * Date:2015年12月8日
 * Copyright (c) 2015, zhaofeng@ushayden.com All Rights Reserved.
 *
*/

package com.hd.hse.scene.phone.ui.event.homepage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.module.branch.ui.model.activity.FrameworkActivity;
import com.hd.hse.common.module.phone.ui.activity.LocationSwCard;
import com.hd.hse.common.module.phone.ui.event.homepage.AbstractAppModule;
import com.hd.hse.constant.IRelativeEncoding;
import com.hd.hse.entity.sys.AppModule;
import com.hd.hse.scene.phone.ui.activity.GasDetectListActivity;
import com.hd.hse.system.SystemProperty;

/**
 * ClassName:GasDetectApp (气体检测APP).<br/>
 * Date:     2015年12月8日  <br/>
 * @author   LiuYang
 * @version  
 * @see 	 
 */
public class GasDetectApp extends AbstractAppModule {
	public GasDetectApp(Context context) {
		super(context);
	}
	
	@Override
	public void appModuleOnClick(AppModule aModule) throws HDException {
		super.appModuleOnClick(aModule);
		if (aModule.getIsswcard()==0 || (isRelation(IRelativeEncoding.REUSINGLOCATIONCARD)
				&& SystemProperty.getSystemProperty().getPositionCard() != null)) {
			Activity parent = (Activity) getParent();
			Intent intent = new Intent(parent, GasDetectListActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra(FrameworkActivity.INTENT_FUNCTION_KEY,aModule.getModelnum());
			parent.startActivity(intent);
		} else {
			// 位置卡刷卡
			Activity parent = (Activity) getParent();
			Intent intent = new Intent(parent, LocationSwCard.class);
			Bundle mBundle = new Bundle();
			// 传递目标跳转activity类
			mBundle.putSerializable(LocationSwCard.SER_KEY_TARGETCLASS,
					GasDetectListActivity.class);
			mBundle.putString(FrameworkActivity.INTENT_FUNCTION_KEY,aModule.getModelnum());
			intent.putExtras(mBundle);
			parent.startActivity(intent);
		}
	}
}

