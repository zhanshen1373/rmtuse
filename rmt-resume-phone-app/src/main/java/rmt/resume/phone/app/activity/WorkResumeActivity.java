package rmt.resume.phone.app.activity;

import com.hd.hse.common.module.phone.ui.module.activity.BaseTaskListBusActivity;

public class WorkResumeActivity extends BaseTaskListBusActivity {

    @Override
    public String getTitileName() {
        // TODO Auto-generated method stub
        return "作业恢复";
    }

    @Override
    public String getNavCurrentKey() {
        // TODO Auto-generated method stub
        return "rmt-resume-phone-app";
    }

    @Override
    public Class<?> getToActivityClass() {
        // TODO Auto-generated method stub
        return ResumeActivity.class;
    }
}
