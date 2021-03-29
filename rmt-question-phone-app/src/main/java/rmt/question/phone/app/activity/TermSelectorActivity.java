package rmt.question.phone.app.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;

import com.hd.hse.common.component.phone.constant.IActionBar;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.module.phone.ui.activity.BaseFrameActivity;
import com.hd.hse.entity.workorder.BaseItem;

import java.util.List;

import rmt.question.phone.app.R;
import rmt.question.phone.app.adapter.TermSelectorAdapter;

/**
 * created by yangning on 2019/10/31 15:48.
 */
public abstract class TermSelectorActivity extends BaseFrameActivity implements IEventListener {
    protected ListView lv;
    private TermSelectorAdapter adapter;
    protected ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.term_selector_activity_layout);
        initActionbar();
        initView();
        initData();

    }

    protected void initView() {
        adapter = new TermSelectorAdapter(this);
        lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(adapter);
        dialog = new ProgressDialog(this);
    }

    protected void setdata(List<? extends BaseItem> items) {
        adapter.setData(items);
    }

    protected abstract void initData();

    protected abstract String getActivityTitle();

    private void initActionbar() {
        // 初始化ActionBar
        setCustomActionBar(false, this, new String[]{IActionBar.TV_BACK,
                IActionBar.TV_TITLE});
        // 设置导航栏标题
        setActionBartitleContent(getActivityTitle(), false);
        // 设置右侧菜单栏
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        dialog = null;
    }
    @Override
    public void eventProcess(int eventType, Object... objects) throws HDException {

    }
}
