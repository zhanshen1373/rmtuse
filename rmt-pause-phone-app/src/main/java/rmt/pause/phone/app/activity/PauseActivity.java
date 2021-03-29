package rmt.pause.phone.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

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
import com.hd.hse.entity.resultdata.RmtPauseResetTask;
import com.hd.hse.entity.workorder.RmtWorkSubtask;
import com.lidroid.xutils.db.sqlite.DbModelSelector;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import rmt.pause.phone.app.R;

public class PauseActivity extends FrameworkActivity {


    private EditText pausedetailyy;
    private TextView pauseTaskZxr;
    private LinearLayout pzr;
    private TextView pauseTaskPzr;
    private TextView button1;
    private TextView button2;
    private RmtInterface anInterface;
    private Spinner spin;
    private LinearLayout buttonlinear;
    private TextView pauseyy_tv;
    private ProgressDialog dialog;
    private RmtPauseResetTask rmtPauseResetTask1;
    private List<String> list;
    private TextView pausedetailyy_tv;
    private RmtWorkSubtask rmtWorkSubtask;
    private RmtPauseResetTask.BodyvosBean.RMTWORKSTARTPAUSERECORDMBean rmtworkstartpauserecordmBean;
    private List<RmtPauseResetTask.DictvosBean.RMTWORKPAUSEMBean> rmt_work_pause_m;
    private List<RmtPauseResetTask.DictvosBean.RMT_WORK_STARTPAUSE_TYPE_MBean> rmt_work_startpause_type_m;
    private List<Long> audit_personids;
    private LinearLayout pauseAll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pause);


        Intent intent = getIntent();
        //intent是否需要非空判断
        //消息推送传的值
        if (intent != null) {
            rmtWorkSubtask = (RmtWorkSubtask) intent.getSerializableExtra(FrameworkActivity.INTENT_PAUSEORRESET_KEY);
        }


        initView();
        ShowOrHideView();
        initHttp();
        initListener();


    }

    private void ShowOrHideView() {

        if (rmtWorkSubtask != null) {
            RmtInterface anInterface = RetrofitUtil.createInterface(RmtInterface.class);
            Call<ResultData<RmtPauseResetTask>> delayTask = anInterface.getPauseResetMsg(LoginUserRecord.getInstance().getUser().getUserid(), rmtWorkSubtask.getDelay_id(), "pause");
            HttpBusiness.action(PauseActivity.this, false, delayTask, new HttpCallBack<RmtPauseResetTask>() {
                @Override
                public void success(RmtPauseResetTask rmtPauseResetMsg) {
                    showData(rmtPauseResetMsg);

                    RmtPauseResetTask.HeadvoBean.RMTWORKSUBTASKMBean rmt_work_subtask_m = rmtPauseResetMsg.getHeadvo().getRMT_WORK_SUBTASK_M();
                    setWorkTask(rmt_work_subtask_m);
                    setActionTitle(null);
                    setMenuModel();
                }

                @Override
                public void warning(String msg) {
                    ToastUtils.imgToast(PauseActivity.this, R.drawable.hd_hse_common_msg_wrong,
                            msg);
                }

                @Override
                public void error(Throwable t) {
                    ToastUtils.imgToast(PauseActivity.this,
                            R.drawable.hd_hse_common_msg_wrong,
                            "获取数据失败！");
                }
            });
        } else {

            anInterface = RetrofitUtil.createInterface(RmtInterface.class);
            Call<ResultData<RmtPauseResetTask>> savegas = anInterface.getPauseResetTask(LoginUserRecord.getInstance().getUser().getUserid()
                    , workTask.getWork_subtask_id(), "pause");
            HttpBusiness.action(PauseActivity.this, false, savegas, new HttpCallBack<RmtPauseResetTask>() {
                @Override
                public void success(final RmtPauseResetTask rmtPauseResetTask) {


                    final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);

                    Call<ResultData<RmtInterface.DelayUser>> delayUser = rmtInterface.getDelayUser(workTask.getWork_subtask_id());
                    HttpBusiness.action(PauseActivity.this, false, delayUser, new HttpCallBack<RmtInterface.DelayUser>() {
                        @Override
                        public void success(RmtInterface.DelayUser delayUser) {
                            audit_personids = delayUser.getAudit_personids();
                            //审核人
                            showData(rmtPauseResetTask);

//                            if (audit_personids.contains(LoginUserRecord.getInstance().getUser().getUserid()) &&
//                                    rmtPauseResetTask.getBodyvos() == null) {
//                                pauseAll.setVisibility(View.GONE);
//                                ToastUtil.toast(PauseActivity.this, "主修人提交后才能看到数据");
//                            } else {
//                                pauseAll.setVisibility(View.VISIBLE);
//                                showData(rmtPauseResetTask);
//                            }


                        }

                        @Override
                        public void warning(String msg) {
                            ToastUtil.toast(PauseActivity.this, msg);

                        }

                        @Override
                        public void error(Throwable t) {
                            ToastUtil.toast(PauseActivity.this, "获取审批人数据失败");

                        }
                    });


                }

                @Override
                public void warning(String msg) {
                    ToastUtils.imgToast(PauseActivity.this, R.drawable.hd_hse_common_msg_wrong,
                            msg);
                }

                @Override
                public void error(Throwable t) {
                    ToastUtils.imgToast(PauseActivity.this,
                            R.drawable.hd_hse_common_msg_wrong,
                            "获取数据失败！");
                }
            });

            setWorkTask(workTask);
            setActionTitle(null);
            setMenuModel();
        }


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

    private void initView() {

        pauseAll = (LinearLayout) findViewById(R.id.pause_all);
        pausedetailyy = (EditText) findViewById(R.id.pausedetailyy);
        pauseTaskZxr = (TextView) findViewById(R.id.pause_task_zxr);
        pzr = (LinearLayout) findViewById(R.id.pzr);
        pauseTaskPzr = (TextView) findViewById(R.id.pause_task_pzr);
        button1 = (TextView) findViewById(R.id.button1);
        button2 = (TextView) findViewById(R.id.button2);
        buttonlinear = (LinearLayout) findViewById(R.id.anniu);
        pauseyy_tv = (TextView) findViewById(R.id.pauseyy_tv);
        pausedetailyy_tv = (TextView) findViewById(R.id.pausedetailyy_tv);


        spin = (Spinner) findViewById(R.id.pauseyy);
    }


    private void initHttp() {
        anInterface = RetrofitUtil.createInterface(RmtInterface.class);

    }

    @Override
    public void queryData() {

        //继承这个类是为了用头部布局
        //重写这个方法只是为了不得到父类的数据

    }

    private void showData(RmtPauseResetTask rmtPauseResetTask) {

        rmtPauseResetTask1 = rmtPauseResetTask;

        rmt_work_pause_m = rmtPauseResetTask.getDictvos().getRMT_WORK_PAUSE_M();
        list = new ArrayList<>();
        for (int i = 0; i < rmt_work_pause_m.size(); i++) {
            list.add(rmt_work_pause_m.get(i).getName());
        }
        ArrayAdapter<String> ada = new ArrayAdapter<String>(PauseActivity.this, android.R.layout.simple_spinner_item, list);
        ada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(ada);

        rmt_work_startpause_type_m = rmtPauseResetTask.getDictvos().getRMT_WORK_STARTPAUSE_TYPE_M();


        if (rmtPauseResetTask.getBodyvos() == null) {


            pauseyy_tv.setVisibility(View.GONE);
            spin.setVisibility(View.VISIBLE);

            pauseTaskZxr.setText(LoginUserRecord.getInstance().getUser().getUserName());
            pzr.setVisibility(View.GONE);

            pausedetailyy_tv.setVisibility(View.GONE);
            pausedetailyy.setVisibility(View.VISIBLE);

            buttonlinear.setVisibility(View.VISIBLE);
            button1.setText("提交");
            button2.setText("保存");


        } else {

            List<RmtPauseResetTask.BodyvosBean.RMTWORKSTARTPAUSERECORDMBean> rmt_work_startpause_record_m = rmtPauseResetTask.getBodyvos().getRMT_WORK_STARTPAUSE_RECORD_M();
            if (rmt_work_startpause_record_m != null && rmt_work_startpause_record_m.size() > 0) {

                rmtworkstartpauserecordmBean = rmt_work_startpause_record_m.get(0);


                pauseyy_tv.setVisibility(View.GONE);
                spin.setVisibility(View.VISIBLE);
                pausedetailyy_tv.setVisibility(View.VISIBLE);
                pausedetailyy.setVisibility(View.GONE);

                pauseTaskZxr.setText(rmtworkstartpauserecordmBean.getApply_person());
                for (int j = 0; j < rmt_work_pause_m.size(); j++) {
                    if (rmt_work_pause_m.get(j).getCode().equals(rmt_work_startpause_record_m.get(0).getPause_reason())) {
                        pauseyy_tv.setText(rmt_work_pause_m.get(j).getName());
                    }
                }
                pausedetailyy_tv.setText(rmt_work_startpause_record_m.get(0).getPause_remark());


                //草稿
                if (rmt_work_startpause_record_m.get(0).getAudit_status().equals("draft")) {


//                    if (audit_personids.contains(LoginUserRecord.getInstance().getUser().getUserid())) {
//                        pauseyy_tv.setVisibility(View.VISIBLE);
//                        spin.setVisibility(View.GONE);
//                        buttonlinear.setVisibility(View.GONE);
//                        pzr.setVisibility(View.VISIBLE);
//                        pauseTaskPzr.setText(LoginUserRecord.getInstance().getUser().getUserName());
//                        pausedetailyy_tv.setVisibility(View.VISIBLE);
//                        pausedetailyy.setVisibility(View.GONE);
//
//                    } else {
//
//                    }

                    pauseyy_tv.setVisibility(View.GONE);
                    spin.setVisibility(View.VISIBLE);
                    pausedetailyy_tv.setVisibility(View.GONE);
                    pausedetailyy.setVisibility(View.VISIBLE);
                    buttonlinear.setVisibility(View.VISIBLE);
                    button1.setText("提交");
                    button2.setText("保存");
                    pzr.setVisibility(View.GONE);


                    for (int w = 0; w < rmt_work_pause_m.size(); w++) {
                        if (rmtworkstartpauserecordmBean.getPause_reason().equals(rmt_work_pause_m.get(w).getCode())) {
                            spin.setSelection(w, true);
                            break;
                        }

                    }
                    pausedetailyy.setText(rmtworkstartpauserecordmBean.getPause_remark());


                    //审核中
                } else if (rmt_work_startpause_record_m.get(0).getAudit_status().equals("auditing")) {

                    pauseyy_tv.setVisibility(View.VISIBLE);
                    spin.setVisibility(View.GONE);
                    //审核人
                    if (audit_personids.contains(LoginUserRecord.getInstance().getUser().getUserid())) {
                        buttonlinear.setVisibility(View.VISIBLE);
                        button1.setText("同意");
                        button2.setText("退回");
                        pzr.setVisibility(View.VISIBLE);
                        pauseTaskPzr.setText(LoginUserRecord.getInstance().getUser().getUserName());
                    } else {
                        buttonlinear.setVisibility(View.GONE);
                        pzr.setVisibility(View.GONE);
                    }

                    //已审核
                } else if (rmt_work_startpause_record_m.get(0).getAudit_status().equals("audited")) {
                    pauseyy_tv.setVisibility(View.VISIBLE);
                    spin.setVisibility(View.GONE);
                    buttonlinear.setVisibility(View.GONE);
                    pzr.setVisibility(View.VISIBLE);
                    pauseTaskPzr.setText(rmtworkstartpauserecordmBean.getAudit_perosn());

                }
            }
        }


    }


    private void AgreeAndBack(String buttonName) {
        dialog = new ProgressDialog(
                PauseActivity.this, "正在上传...");
        dialog.show();
        Call<ResultData<Object>> savegas = null;

        rmtworkstartpauserecordmBean.setAudit_perosn(LoginUserRecord.getInstance().getUser().getUserName());
        rmtworkstartpauserecordmBean.setAudit_perosnid(LoginUserRecord.getInstance().getUser().getUserid() + "");

        if (buttonName.equals("同意")) {
            savegas = anInterface.getPauseAgreeButton(rmtworkstartpauserecordmBean, "pause");
        } else if (buttonName.equals("退回")) {
            savegas = anInterface.getPauseBackButton(rmtworkstartpauserecordmBean, "pause");
        }
        Requset(savegas);

    }

    private void SaveAndCommitRequest(String buttonName) {

        dialog = new ProgressDialog(
                PauseActivity.this, "正在上传...");
        dialog.show();

        RmtPauseResetTask.DictvosBean.RMTWORKPAUSEMBean rmtworkpausemBean = null;
        for (int k = 0; k < list.size(); k++) {
            if ((spin.getSelectedItem()).equals(list.get(k))) {
                rmtworkpausemBean = rmt_work_pause_m.get(k);
            }
        }
        if (rmtPauseResetTask1.getBodyvos() == null) {

            rmtworkstartpauserecordmBean = new RmtPauseResetTask.BodyvosBean.RMTWORKSTARTPAUSERECORDMBean();
            rmtworkstartpauserecordmBean.setStartpause_type(rmt_work_startpause_type_m.get(0).getCode());
            rmtworkstartpauserecordmBean.setPause_reason(rmtworkpausemBean.getCode());
            rmtworkstartpauserecordmBean.setApply_person(LoginUserRecord.getInstance().getUser().getUserName());
            rmtworkstartpauserecordmBean.setApply_personid(LoginUserRecord.getInstance().getUser().getUserid());
            rmtworkstartpauserecordmBean.setPause_remark(pausedetailyy.getText().toString());

        } else {

            rmtworkstartpauserecordmBean.setStartpause_type(rmt_work_startpause_type_m.get(0).getCode());
            rmtworkstartpauserecordmBean.setPause_reason(rmtworkpausemBean.getCode());
            rmtworkstartpauserecordmBean.setApply_person(LoginUserRecord.getInstance().getUser().getUserName());
            rmtworkstartpauserecordmBean.setApply_personid(LoginUserRecord.getInstance().getUser().getUserid());
            rmtworkstartpauserecordmBean.setPause_remark(pausedetailyy.getText().toString());
        }


        Call<ResultData<Object>> savegas = null;

        if (buttonName.equals("保存")) {
            savegas = anInterface.getPauseSaveButton(rmtworkstartpauserecordmBean, workTask.getWork_subtask_id() + "", "pause");
        } else if (buttonName.equals("提交")) {
            savegas = anInterface.getPauseCommitButton(rmtworkstartpauserecordmBean, workTask.getWork_subtask_id() + "", "pause");
        }
        Requset(savegas);

    }

    private void Requset(Call<ResultData<Object>> savegas) {
        HttpBusiness.action(PauseActivity.this, false, savegas, new HttpCallBack<Object>() {
            @Override
            public void success(Object o) {
                dialog.dismiss();
                ToastUtils.imgToast(PauseActivity.this,
                        R.drawable.hd_hse_common_msg_right,
                        "上传数据完成");
                finish();
            }

            @Override
            public void warning(String msg) {
                dialog.dismiss();
                ToastUtils.imgToast(PauseActivity.this, R.drawable.hd_hse_common_msg_wrong,
                        msg);
            }

            @Override
            public void error(Throwable t) {

                dialog.dismiss();
                ToastUtils.imgToast(PauseActivity.this,
                        R.drawable.hd_hse_common_msg_wrong,
                        "上传数据失败！");
            }
        });
    }


}
