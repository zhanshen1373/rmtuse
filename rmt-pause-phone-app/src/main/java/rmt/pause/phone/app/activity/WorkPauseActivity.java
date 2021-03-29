package rmt.pause.phone.app.activity;

import com.hd.hse.common.module.phone.ui.module.activity.BaseTaskListBusActivity;


public class WorkPauseActivity extends BaseTaskListBusActivity {

    @Override
    public String getTitileName() {
        // TODO Auto-generated method stub
        return "作业暂停";
    }

    @Override
    public String getNavCurrentKey() {
        // TODO Auto-generated method stub
        return "rmt-pause-phone-app";
    }

    @Override
    public Class<?> getToActivityClass() {
        // TODO Auto-generated method stub
        return PauseActivity.class;
    }

}
