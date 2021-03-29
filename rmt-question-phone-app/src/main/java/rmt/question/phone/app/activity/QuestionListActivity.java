package rmt.question.phone.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;

import com.hd.hse.common.component.phone.constant.IActionBar;
import com.hd.hse.common.module.phone.ui.activity.BaseFrameActivity;
import com.hd.hse.entity.sys.AppModule;
import com.hd.hse.system.SystemProperty;

import java.util.List;

import rmt.question.phone.app.R;
import rmt.question.phone.app.fragment.QuestionListUploadedFrag;

/**
 * created by yangning on 2017/6/1 16:46.
 * 问题列表
 */

public class QuestionListActivity extends BaseFrameActivity {
    private ImageView imgAdd;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rmt_question_list_layout);
        initView();
        imgAdd = (ImageView) findViewById(R.id.rmt_question_list_img_add);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        QuestionListUploadedFrag frag = new QuestionListUploadedFrag();
        ft.add(R.id.rmt_question_list_container, frag);
        ft.commit();
        currentFragment = frag;
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(QuestionListActivity.this, QuestionRegistrationActivity.class);
                startActivityForResult(i, 0);
            }
        });
    }

    protected void initView() {
        // 设置导航栏信息
        setCustomActionBar(true, null, new String[]{
                IActionBar.TV_TITLE});
        // 设置导航栏标题
        setActionBartitleContent("问题登记", false);
        // 设置左侧模块选择抽屉
        setNavContent(getNavContentData(), "rmt-question-phone-app");
    }

    public List<AppModule> getNavContentData() {
        return SystemProperty.getSystemProperty()
                .getMainAppModulelist("ONLINE");
    }

    /**
     * 从服务器获取数据
     */
    private void getDataFromNet() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        currentFragment.onActivityResult(requestCode, resultCode, data);
    }
}
