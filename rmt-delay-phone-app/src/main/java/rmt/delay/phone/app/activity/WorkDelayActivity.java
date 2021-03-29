package rmt.delay.phone.app.activity;

import com.hd.hse.common.module.phone.ui.module.activity.BaseTaskListBusActivity;





public class WorkDelayActivity extends BaseTaskListBusActivity {


    @Override
    public String getTitileName() {
        // TODO Auto-generated method stub
        return "作业延期";
    }

    @Override
    public String getNavCurrentKey() {
        // TODO Auto-generated method stub
        return "rmt-delay-phone-app";
    }

    @Override
    public Class<?> getToActivityClass() {
        // TODO Auto-generated method stub
        return DelayTaskActivity.class;
    }
}
