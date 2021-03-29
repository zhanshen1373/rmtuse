package rmt.question.phone.app.activity;

import android.content.Intent;
import android.view.KeyEvent;

import com.hd.hse.common.component.phone.constant.IActionBar;
import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.module.phone.ui.module.activity.BaseTaskListBusActivity;

/**
 * created by yangning on 2017/6/30 15:58.
 */

public class TaskTabulationActivity extends BaseTaskListBusActivity {
    public static final int RESULTCODE = 0x1655;
    public static final String RMTWORKSUBTASK = "rmtworksubtask";

    @Override

    public String getTitileName() {
        // TODO Auto-generated method stub
        return "问题登记";
    }

    @Override
    public Class<?> getToActivityClass() {
        // TODO Auto-generated method stub
        return QuestionRegistrationActivity.class;
    }

    @Override
    protected void initView() {
        super.initView();
        // 设置导航栏信息
        setCustomActionBar(false, eventListener, new String[]{IActionBar.TV_BACK, IActionBar.TV_TITLE});
        // 设置导航栏标题
        setActionBartitleContent(getTitileName(), false);
        // 设置左侧模块选择抽屉
        setNavContent(null, null);
    }

    @Override
    protected void startNextActivity(SuperEntity superEntity) {
        Intent i = new Intent();
        i.putExtra(RMTWORKSUBTASK, superEntity);
        setResult(RESULTCODE, i);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
