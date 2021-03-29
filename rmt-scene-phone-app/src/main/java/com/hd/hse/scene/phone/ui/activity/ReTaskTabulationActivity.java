package com.hd.hse.scene.phone.ui.activity;

import com.hd.hse.common.module.phone.ui.module.activity.BaseTaskListBusActivity;

/**
 * 措施复查
 * created by yangning on 2017/4/26 16:03.
 */

public class ReTaskTabulationActivity extends BaseTaskListBusActivity{
    @Override
    public String getTitileName() {
        // TODO Auto-generated method stub
        return "措施复查";
    }

    @Override
    public String getNavCurrentKey() {
        // TODO Auto-generated method stub
        return "hse-resc-phone-app";
    }

    @Override
    public Class<?> getToActivityClass() {
        // TODO Auto-generated method stub
        return SceneTaskActivity.class;
    }

}
