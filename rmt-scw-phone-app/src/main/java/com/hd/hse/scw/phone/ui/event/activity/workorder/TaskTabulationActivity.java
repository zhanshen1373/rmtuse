package com.hd.hse.scw.phone.ui.event.activity.workorder;

import com.hd.hse.common.module.phone.ui.module.activity.BaseTaskListBusActivity;

public class TaskTabulationActivity extends BaseTaskListBusActivity {
	@Override
	public String getTitileName() {
		// TODO Auto-generated method stub
		return "任务接班";
	}

	@Override
	public String getNavCurrentKey() {
		// TODO Auto-generated method stub
		return "hse-scw-phone-app";
	}

	@Override
	public Class<?> getToActivityClass() {
		// TODO Auto-generated method stub
		return GuardianShiftChangeActivity.class;
	}
}
