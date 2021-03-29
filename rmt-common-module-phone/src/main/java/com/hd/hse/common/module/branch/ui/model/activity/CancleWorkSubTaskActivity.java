package com.hd.hse.common.module.branch.ui.model.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hayden.hap.common.utils.ToastUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.constant.IActionBar;
import com.hd.hse.common.component.phone.custom.MessageDialog;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.common.module.phone.R;
import com.hd.hse.common.module.phone.ui.activity.BaseFrameActivity;

import org.apache.log4j.Logger;

import retrofit2.Call;

/**
 * created by yangning on 2017/12/22 14:14.
 */

public class CancleWorkSubTaskActivity extends BaseFrameActivity implements IEventListener, View.OnClickListener {
    private static Logger logger = LogUtils
            .getLogger(CancleWorkSubTaskActivity.class);
    private Button btSave;
    private EditText cancleComment;
    public static final String SUBTASKID = "subtaskId";
    private long subtaskId;
    private String stopComment;
    private ProgressDialog dialog;
    public static final int CANCLESUCESS = 45;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rmt_cancle_worksubtask_activity_layout);
        initData();
        initView();
        initActionbar();

    }

    private void initData() {
        subtaskId = getIntent().getLongExtra(SUBTASKID, -1);

    }

    private void initView() {
        if (subtaskId == -1) {
            ToastUtil.toast(this, "传入数据有误，请联系管理员");
            logger.error("未获取到subtaskId");
            return;
        }

        btSave = (Button) findViewById(R.id.rmt_cancle_worksubtask_activity_layout_ensure);
        cancleComment = (EditText) findViewById(R.id.rmt_cancle_worksubtask_activity_layout_ed_stop_comment);
        btSave.setOnClickListener(this);
    }

    private void initActionbar() {
        // 初始化ActionBar
        setCustomActionBar(false, this, new String[]{IActionBar.TV_BACK, IActionBar.TV_TITLE});
        // 设置导航栏标题
        setActionBartitleContent("任务取消", false);
    }

    @Override
    public void eventProcess(int eventType, Object... objects) throws HDException {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.rmt_cancle_worksubtask_activity_layout_ensure) {
            //保存
            stopComment = cancleComment.getText().toString().trim();
            if (TextUtils.isEmpty(stopComment)) {
                ToastUtil.toast(getApplication(), "请先填写取消说明");
                return;
            }
            showCancleDialog();
            return;
        }

    }

    private void showCancleDialog() {
        MessageDialog.Builder builder = new MessageDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定继续?");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                saveCancle();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消",
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.createWarm().show();
    }

    private void saveCancle() {
        if (dialog == null) {
            dialog = new ProgressDialog(this);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setMessage("正在取消任务...");
        }

        dialog.show();

        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);


        Call<ResultData<Object>> call = rmtInterface.subtaskCancel(LoginUserRecord.getInstance().getUser().getUserid(), subtaskId, stopComment);
        HttpBusiness.action(CancleWorkSubTaskActivity.this, false, call, new HttpCallBack<Object>() {
            @Override
            public void success(Object o) {
                if (CancleWorkSubTaskActivity.this != null && dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                    ToastUtil.toast(getApplicationContext(), "任务取消成功");
                    setResult(CANCLESUCESS);
                    finish();
                }
            }

            @Override
            public void warning(String msg) {
                if (CancleWorkSubTaskActivity.this != null && dialog != null && dialog.isShowing())
                    dialog.dismiss();
                ToastUtil.toast(getApplicationContext(), msg);
            }

            @Override
            public void error(Throwable t) {
                if (CancleWorkSubTaskActivity.this != null && dialog != null && dialog.isShowing())
                    dialog.dismiss();
                ToastUtil.toast(getApplicationContext(), "任务取消失败");
            }
        });
    }
}
