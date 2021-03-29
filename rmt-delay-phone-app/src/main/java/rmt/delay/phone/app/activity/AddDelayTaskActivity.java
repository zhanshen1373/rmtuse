package rmt.delay.phone.app.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hayden.hap.common.utils.ToastUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.custom.ProgressDialog;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.module.branch.ui.model.activity.FrameworkActivity;
import com.hd.hse.entity.resultdata.RmtDelayTaskHistoryList;
import com.hd.hse.entity.resultdata.RmtDelayTaskMsg;
import com.hd.hse.entity.workorder.RmtWorkSubtask;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import rmt.delay.phone.app.Adapter.ListViewAdapter;
import rmt.delay.phone.app.R;

public class AddDelayTaskActivity extends FrameworkActivity {

    private LinearLayout pzr;
    private LinearLayout zxr;
    private TextView button1;
    private TextView button2;
    private EditText delayTaskYqyy;
    private DatePicker delayTaskNyr;
    private TimePicker delayTaskSfm;
    private TextView delayTaskZxr;
    private TextView delayTaskPzr;
    private TextView delayTaskYqyyTv;
    private TextView delayTaskYqsjtv;
    private LinearLayout delayTaskYqsj;
    private RmtDelayTaskHistoryList.MainvoBean.HeadVOBean.RmtWdrList rmt_work_delay_record_m;
    private RmtDelayTaskHistoryList.MainvoBean mainvoBean;
    private RmtInterface anInterface;
    private ProgressDialog dialog;
    private RmtWorkSubtask worksubtaskhh;
    private LinearLayout delayTaskYq;
    private LinearLayout delayTaskYy;
    private LinearLayout anniu;
    private RmtWorkSubtask rmtWorkSubtask;
    private List<Long> audit_personids;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_delay_task);

        Intent intent = getIntent();
        //消息推送传的值
        if (intent != null) {
            rmtWorkSubtask = (RmtWorkSubtask) intent.getSerializableExtra(FrameworkActivity.INTENT_WORKTASK_KEY);
        }

        initView();
        ShowOrHideView();
        initListener();
        initHttp();


    }

    private void initHttp() {
        anInterface = RetrofitUtil.createInterface(RmtInterface.class);

    }

    private void initListener() {
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button1.getText().equals("提交")) {
                    SaveAndCommitRequest("提交");

                } else if (button1.getText().equals("同意")) {
                    AgreeAndBack("同意");
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button2.getText().equals("保存")) {
                    SaveAndCommitRequest("保存");

                } else if (button2.getText().equals("退回")) {
                    AgreeAndBack("退回");

                }
            }
        });
    }

    private void AgreeAndBack(String buttonName) {
        dialog = new ProgressDialog(
                AddDelayTaskActivity.this, "正在上传...");
        dialog.show();
        Call<ResultData<Object>> savegas = null;

        mainvoBean.getHeadVO().getRMT_WORK_DELAY_RECORD_M().setAudit_perosn(LoginUserRecord.getInstance().getUser().getUserName());
        mainvoBean.getHeadVO().getRMT_WORK_DELAY_RECORD_M().setAudit_perosnid(LoginUserRecord.getInstance().getUser().getUserid() + "");


        if (buttonName.equals("同意")) {
            savegas = anInterface.getAgreeButton(mainvoBean.getHeadVO().getRMT_WORK_DELAY_RECORD_M());
        } else if (buttonName.equals("退回")) {
            savegas = anInterface.getBackButton(mainvoBean.getHeadVO().getRMT_WORK_DELAY_RECORD_M());
        }
        Requset(savegas);

    }

    private void SaveAndCommitRequest(String buttonName) {


        RmtDelayTaskHistoryList.MainvoBean.HeadVOBean.RmtWdrList rmtWdrList;
        if (mainvoBean != null) {
            rmtWdrList = mainvoBean.getHeadVO().getRMT_WORK_DELAY_RECORD_M();
        } else {
            rmtWdrList = new RmtDelayTaskHistoryList.MainvoBean.HeadVOBean.RmtWdrList();

        }
        rmtWdrList.setDelay_reason(delayTaskYqyy.getText().toString());
        int month = delayTaskNyr.getMonth() + 1;
        String getMonth = PlusZero(month);
        rmtWdrList.setDelay_time_str(delayTaskNyr.getYear() + "-" + getMonth + "-" + PlusZero(delayTaskNyr.getDayOfMonth()) + " " +
                delayTaskSfm.getCurrentHour() + ":" + delayTaskSfm.getCurrentMinute() + ":00");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            //延期时间
            Date parse = formatter.parse(rmtWdrList.getDelay_time_str());
            //计划结束时间
            Date parse1 = formatter.parse(worksubtaskhh.getEst_end_time());
            if (parse.getTime() < parse1.getTime()) {
                ToastUtil.toast(AddDelayTaskActivity.this, "延期时间不能早于计划结束时间");
                return;
            }
        } catch (ParseException e) {

        }


        rmtWdrList.setApply_person(LoginUserRecord.getInstance().getUser().getUserName());
        rmtWdrList.setApply_personid(LoginUserRecord.getInstance().getUser().getUserid() + "");
        dialog = new ProgressDialog(
                AddDelayTaskActivity.this, "正在上传...");
        dialog.show();
        Call<ResultData<Object>> savegas = null;

        if (buttonName.equals("保存")) {
            savegas = anInterface.getSaveButton(rmtWdrList, worksubtaskhh.getWork_subtask_id() + "");
        } else if (buttonName.equals("提交")) {
            savegas = anInterface.getCommitButton(rmtWdrList, worksubtaskhh.getWork_subtask_id() + "");
        }
        Requset(savegas);

    }

    private String PlusZero(int a) {
        DecimalFormat df = new DecimalFormat("00");
        return df.format(a);
    }

    private void Requset(Call<ResultData<Object>> savegas) {
        HttpBusiness.action(AddDelayTaskActivity.this, false, savegas, new HttpCallBack<Object>() {
            @Override
            public void success(Object o) {
                dialog.dismiss();

                ToastUtils.imgToast(AddDelayTaskActivity.this,
                        R.drawable.hd_hse_common_msg_right,
                        "上传数据完成");
                finish();
            }

            @Override
            public void warning(String msg) {
                dialog.dismiss();

                ToastUtils.imgToast(AddDelayTaskActivity.this, R.drawable.hd_hse_common_msg_wrong,
                        msg);
            }

            @Override
            public void error(Throwable t) {

                dialog.dismiss();
                ToastUtils.imgToast(AddDelayTaskActivity.this,
                        R.drawable.hd_hse_common_msg_wrong,
                        "上传数据失败！");
            }
        });
    }

    private void ShowOrHideView() {

        if (rmtWorkSubtask != null) {
            RmtInterface anInterface = RetrofitUtil.createInterface(RmtInterface.class);
            Call<ResultData<RmtDelayTaskMsg>> delayTask = anInterface.getDelayTask(LoginUserRecord.getInstance().getUser().getUserid(), rmtWorkSubtask.getDelay_id());
            HttpBusiness.action(AddDelayTaskActivity.this, false, delayTask, new HttpCallBack<RmtDelayTaskMsg>() {
                @Override
                public void success(RmtDelayTaskMsg rmtDelayTaskMsg) {
                    RmtWorkSubtask rmt_work_subtask_m = rmtDelayTaskMsg.getHeadvo().getRMT_WORK_SUBTASK_M();
                    List<RmtDelayTaskMsg.BodyvosBean.RMTWORKDELAYRECORDMBean> rmt_work_delay_record_m = rmtDelayTaskMsg.getBodyvos().getRMT_WORK_DELAY_RECORD_M();

                    worksubtaskhh = rmt_work_subtask_m;
                    mainvoBean = new RmtDelayTaskHistoryList.MainvoBean();
                    RmtDelayTaskHistoryList.MainvoBean.HeadVOBean headVOBean = new RmtDelayTaskHistoryList.MainvoBean.HeadVOBean();
                    headVOBean.setRMT_WORK_DELAY_RECORD_M(rmt_work_delay_record_m.get(0));
                    mainvoBean.setHeadVO(headVOBean);

                    setWorkTask(worksubtaskhh);
                    setActionTitle(null);
                    setMenuModel();
                }

                @Override
                public void warning(String msg) {
                    ToastUtils.imgToast(AddDelayTaskActivity.this, R.drawable.hd_hse_common_msg_wrong,
                            msg);
                }

                @Override
                public void error(Throwable t) {
                    ToastUtils.imgToast(AddDelayTaskActivity.this,
                            R.drawable.hd_hse_common_msg_wrong,
                            "获取数据失败！");
                }
            });

        } else {
            Intent intent = getIntent();
            mainvoBean = (RmtDelayTaskHistoryList.MainvoBean) intent.getSerializableExtra("duixiang");
            worksubtaskhh = (RmtWorkSubtask) intent.getSerializableExtra("worksubtask");

            setWorkTask(worksubtaskhh);
            setActionTitle(null);
            setMenuModel();
        }

        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);

        Call<ResultData<RmtInterface.DelayUser>> delayUser = rmtInterface.getDelayUser(worksubtaskhh.getWork_subtask_id());
        HttpBusiness.action(AddDelayTaskActivity.this, false, delayUser, new HttpCallBack<RmtInterface.DelayUser>() {
            @Override
            public void success(RmtInterface.DelayUser delayUser) {
                audit_personids = delayUser.getAudit_personids();
                showData();
            }

            @Override
            public void warning(String msg) {
                ToastUtil.toast(AddDelayTaskActivity.this, msg);

            }

            @Override
            public void error(Throwable t) {
                ToastUtil.toast(AddDelayTaskActivity.this, "获取审批人数据失败");

            }
        });


    }

    private void showData() {

        if (mainvoBean != null) {

            rmt_work_delay_record_m = mainvoBean.getHeadVO().getRMT_WORK_DELAY_RECORD_M();


            //审核人
            if (audit_personids.contains(LoginUserRecord.getInstance().getUser().getUserid())) {

                pzr.setVisibility(View.VISIBLE);
                if (rmt_work_delay_record_m.getAudit_perosn() != null) {
                    delayTaskPzr.setText(rmt_work_delay_record_m.getAudit_perosn());
                } else {
                    delayTaskPzr.setText(LoginUserRecord.getInstance().getUser().getUserName());
                }
                //送交人
            } else {
                pzr.setVisibility(View.GONE);

            }


        }


        if (mainvoBean == null) {
            delayTaskZxr.setText(LoginUserRecord.getInstance().getUser().getUserName());
            ShowCg();
            anniu.setVisibility(View.VISIBLE);
            button1.setText("提交");
            button2.setText("保存");
        }
        //保存
        else if (mainvoBean.getHeadVO().getRMT_WORK_DELAY_RECORD_M().getAudit_status().equals("DRAFT")) {

            delayTaskZxr.setText(mainvoBean.getHeadVO().getRMT_WORK_DELAY_RECORD_M().getApply_person());
            //审核人
            if (audit_personids.contains(LoginUserRecord.getInstance().getUser().getUserid())) {
                ShowCheckingAndChecked();
                anniu.setVisibility(View.GONE);
                //送交人
            } else {
                ShowCg();
                delayTaskYqyy.setText(mainvoBean.getHeadVO().getRMT_WORK_DELAY_RECORD_M().getDelay_reason());
                String delay_time_str = mainvoBean.getHeadVO().getRMT_WORK_DELAY_RECORD_M().getDelay_time();
                String[] split2 = delay_time_str.split(" ");
                String[] split = split2[0].split("-");

                delayTaskNyr.init(Integer.parseInt(split[0]), Integer.parseInt(split[1]) - 1, Integer.parseInt(split[2]), null);

                anniu.setVisibility(View.VISIBLE);
                button1.setText("提交");
                button2.setText("保存");
            }

            //提交
        } else if (mainvoBean.getHeadVO().getRMT_WORK_DELAY_RECORD_M().getAudit_status().equals("AUDITING")) {

            ShowCheckingAndChecked();
            delayTaskZxr.setText(mainvoBean.getHeadVO().getRMT_WORK_DELAY_RECORD_M().getApply_person());

            //审核人
            if (audit_personids.contains(LoginUserRecord.getInstance().getUser().getUserid())) {
                anniu.setVisibility(View.VISIBLE);
                button1.setText("同意");
                button2.setText("退回");
                //送交人
            } else {
                anniu.setVisibility(View.GONE);

            }

            //批准人同意
        } else if (mainvoBean.getHeadVO().getRMT_WORK_DELAY_RECORD_M().getAudit_status().equals("AUDITED")) {
            pzr.setVisibility(View.VISIBLE);
            delayTaskPzr.setText(rmt_work_delay_record_m.getAudit_perosn());
            delayTaskZxr.setText(mainvoBean.getHeadVO().getRMT_WORK_DELAY_RECORD_M().getApply_person());
            ShowCheckingAndChecked();
            anniu.setVisibility(View.GONE);


        }
    }

    private void initView() {

        pzr = (LinearLayout) findViewById(R.id.pzr);
        zxr = (LinearLayout) findViewById(R.id.zxr);
        anniu = (LinearLayout) findViewById(R.id.anniu);
        button1 = (TextView) findViewById(R.id.button1);
        button2 = (TextView) findViewById(R.id.button2);


        delayTaskYqyy = (EditText) findViewById(R.id.delay_task_yqyy);
        delayTaskNyr = (DatePicker) findViewById(R.id.delay_task_nyr);
        delayTaskSfm = (TimePicker) findViewById(R.id.delay_task_sfm);
        delayTaskSfm.setIs24HourView(true);
        delayTaskZxr = (TextView) findViewById(R.id.delay_task_zxr);
        delayTaskPzr = (TextView) findViewById(R.id.delay_task_pzr);
        delayTaskYqyyTv = (TextView) findViewById(R.id.delay_task_yqyytv);


        delayTaskYqsjtv = (TextView) findViewById(R.id.delay_task_yqsjtv);
        delayTaskYqsj = (LinearLayout) findViewById(R.id.delay_task_yqsj);
        delayTaskYq = (LinearLayout) findViewById(R.id.delay_task_yq);
        delayTaskYy = (LinearLayout) findViewById(R.id.delay_task_yy);


    }


    @Override
    public void queryData() {


    }

    private void ShowCheckingAndChecked() {
        delayTaskYqyy.setVisibility(View.GONE);
        delayTaskYqyyTv.setVisibility(View.VISIBLE);
        delayTaskYqyyTv.setText(rmt_work_delay_record_m.getDelay_reason());

        delayTaskYqsj.setVisibility(View.GONE);
        delayTaskYqsjtv.setVisibility(View.VISIBLE);
        delayTaskYqsjtv.setText(rmt_work_delay_record_m.getDelay_time());

        //延期至
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) delayTaskYq.getLayoutParams();
        int d = dip2px(this, 50);
        layoutParams.height = d;
        delayTaskYq.setLayoutParams(layoutParams);


        //延期原因
        LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) delayTaskYy.getLayoutParams();
        int p = dip2px(this, 50);
        layoutParams1.height = p;
        delayTaskYy.setLayoutParams(layoutParams1);

        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) zxr.getLayoutParams();
        int t = dip2px(this, 50);
        layoutParams2.height = t;
        zxr.setLayoutParams(layoutParams2);

        LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) pzr.getLayoutParams();
        int u = dip2px(this, 50);
        layoutParams3.height = u;
        pzr.setLayoutParams(layoutParams3);

    }

    //控制显隐，调整布局
    private void ShowCg() {

        delayTaskYqyy.setVisibility(View.VISIBLE);
        delayTaskYqyyTv.setVisibility(View.GONE);


        delayTaskYqsj.setVisibility(View.VISIBLE);
        delayTaskYqsjtv.setVisibility(View.GONE);

        pzr.setVisibility(View.GONE);

        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) zxr.getLayoutParams();
        int d = dip2px(this, 30);
        layoutParams2.height = d;
        zxr.setLayoutParams(layoutParams2);

        LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) pzr.getLayoutParams();
        int y = dip2px(this, 30);
        layoutParams3.height = y;
        pzr.setLayoutParams(layoutParams3);

        ViewGroup.LayoutParams layoutParams = delayTaskYq.getLayoutParams();
        int i = dip2px(this, 300);
        layoutParams.height = i;
        delayTaskYq.setLayoutParams(layoutParams);

        ViewGroup.LayoutParams layoutParams1 = delayTaskYy.getLayoutParams();
        int t = dip2px(this, 100);
        layoutParams1.height = t;
        delayTaskYy.setLayoutParams(layoutParams1);

    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
