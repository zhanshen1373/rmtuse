package hd.hse.end.phone.ui.activity;

import com.hd.hse.common.module.phone.ui.module.activity.BaseTaskListBusActivity;

public class TaskTabulationActivity extends BaseTaskListBusActivity {

	@Override
	public String getTitileName() {
		// TODO Auto-generated method stub
		return "任务结束";
	}

	@Override
	public String getNavCurrentKey() {
		// TODO Auto-generated method stub
		return "hse-end-phone-app";
	}

	@Override
	public Class<?> getToActivityClass() {
		// TODO Auto-generated method stub
		return EndTaskActivity.class;
	}


}
