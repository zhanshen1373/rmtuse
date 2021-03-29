package rmt.assignstaff.phone.app.activity;

import com.hd.hse.common.module.phone.ui.module.activity.BaseTaskListBusActivity;

public class TaskTabulationActivity extends BaseTaskListBusActivity {

	@Override
	public String getTitileName() {
		// TODO Auto-generated method stub
		return "人员指定";
	}

	@Override
	public String getNavCurrentKey() {
		// TODO Auto-generated method stub
		return "rmt-assignstaff-phone-app";
	}

	@Override
	public Class<?> getToActivityClass() {
		// TODO Auto-generated method stub
		return AssignStaffActivity.class;
	}


}
