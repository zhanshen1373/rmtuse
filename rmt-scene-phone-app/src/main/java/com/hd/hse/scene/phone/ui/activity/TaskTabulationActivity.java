package com.hd.hse.scene.phone.ui.activity;

import com.hd.hse.common.module.phone.ui.module.activity.BaseTaskListBusActivity;

public class TaskTabulationActivity extends BaseTaskListBusActivity {

	@Override
	public String getTitileName() {
		// TODO Auto-generated method stub
		return "措施确认";
	}

	@Override
	public String getNavCurrentKey() {
		// TODO Auto-generated method stub
		return "hse-scene-phone-app";
	}

	@Override
	public Class<?> getToActivityClass() {
		// TODO Auto-generated method stub
		return SceneTaskActivity.class;
	}


}
