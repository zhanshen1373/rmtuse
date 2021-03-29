package rmt.resume.phone.app.activity;

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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import rmt.resume.phone.app.R;

//恢复
public class ResumeActivity extends FrameworkActivity {


    private Spinner resumeyy;
    private EditText resumedetailyy;
    private TextView resumeTaskZxr;
    private LinearLayout pzr;
    private TextView resumeTaskPzr;
    private LinearLayout anniu;
    private TextView button1;
    private TextView button2;
    private TextView resumeyy_tv;
    private RmtInterface anInterface;
    private String ztType = null;
    private RmtPauseResetTask rmtPauseResetTask1;
    private List<String> list;
    private ProgressDialog dialog;
    private TextView resumedetailyy_tv;
    private RmtWorkSubtask rmtWorkSubtask;
    private RmtPauseResetTask.BodyvosBean.RMTWORKSTARTPAUSERECORDMBean rmtworkstartpauserecordmBean;
    private List<RmtPauseResetTask.DictvosBean.RMT_WORK_STARTPAUSE_TYPE_MBean> rmt_work_startpause_type_m;
    private List<Long> audit_personids;
    private LinearLayout resumeAll;
    private List<RmtPauseResetTask.DictvosBean.RMTWORKSTARTMBean> rmt_work_start_m;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_resume);


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
            HttpBusiness.action(ResumeActivity.this, false, delayTask, new HttpCallBack<RmtPauseResetTask>() {
                @Override
                public void success(RmtPauseResetTask rmtPauseResetMsg) {

                    //发消息的应该不用判断
                    showData(rmtPauseResetMsg);

                    RmtPauseResetTask.HeadvoBean.RMTWORKSUBTASKMBean rmt_work_subtask_m = rmtPauseResetMsg.getHeadvo().getRMT_WORK_SUBTASK_M();
                    setWorkTask(rmt_work_subtask_m);
                    setActionTitle(null);
                    setMenuModel();
                }

                @Override
                public void warning(String msg) {
                    ToastUtils.imgToast(ResumeActivity.this, R.drawable.hd_hse_common_msg_wrong,
                            msg);
                }

                @Override
                public void error(Throwable t) {
                    ToastUtils.imgToast(ResumeActivity.this,
                            R.drawable.hd_hse_common_msg_wrong,
                            "获取数据失败！");
                }
            });
        } else {

            anInterface = RetrofitUtil.createInterface(RmtInterface.class);
            Call<ResultData<RmtPauseResetTask>> savegas = anInterface.getPauseResetTask(LoginUserRecord.getInstance().getUser().getUserid()
                    , workTask.getWork_subtask_id(), "reset");
            HttpBusiness.action(ResumeActivity.this, false, savegas, new HttpCallBack<RmtPauseResetTask>() {
                @Override
                public void success(final RmtPauseResetTask rmtPauseResetTask) {

                    final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);

                    Call<ResultData<RmtInterface.DelayUser>> delayUser = rmtInterface.getDelayUser(workTask.getWork_subtask_id());
                    HttpBusiness.action(ResumeActivity.this, false, delayUser, new HttpCallBack<RmtInterface.DelayUser>() {
                        @Override
                        public void success(RmtInterface.DelayUser delayUser) {
                            audit_personids = delayUser.getAudit_personids();
                            //审核人
                            if (audit_personids.contains(LoginUserRecord.getInstance().getUser().getUserid()) &&
                                    rmtPauseResetTask.getBodyvos() == null) {
                                resumeAll.setVisibility(View.GONE);
                                ToastUtil.toast(ResumeActivity.this, "主修人提交后才能看到数据");
                            } else {
                                resumeAll.setVisibility(View.VISIBLE);
                                showData(rmtPauseResetTask);
                            }


                        }

                        @Override
                        public void warning(String msg) {
                            ToastUtil.toast(ResumeActivity.this, msg);

                        }

                        @Override
                        public void error(Throwable t) {
                            ToastUtil.toast(ResumeActivity.this, "获取审批人数据失败");

                        }
                    });

                }

                @Override
                public void warning(String msg) {

                    ToastUtils.imgToast(ResumeActivity.this, R.drawable.hd_hse_common_msg_wrong,
                            msg);
                }

                @Override
                public void error(Throwable t) {
                    ToastUtils.imgToast(ResumeActivity.this,
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

    private void initHttp() {
        anInterface = RetrofitUtil.createInterface(RmtInterface.class);
    }

    private void initView() {

        resumeAll = (LinearLayout) findViewById(R.id.resume_all);
        resumeyy = (Spinner) findViewById(R.id.resumeyy);
        resumedetailyy = (EditText) findViewById(R.id.resumedetailyy);
        resumeTaskZxr = (TextView) findViewById(R.id.resume_task_zxr);
        pzr = (LinearLayout) findViewById(R.id.pzr);
        resumeTaskPzr = (TextView) findViewById(R.id.resume_task_pzr);
        anniu = (LinearLayout) findViewById(R.id.anniu);
        button1 = (TextView) findViewById(R.id.button1);
        button2 = (TextView) findViewById(R.id.button2);
        resumeyy_tv = (TextView) findViewById(R.id.resumeyy_tv);
        resumedetailyy_tv = (TextView) findViewById(R.id.resumedetailyy_tv);


    }

    @Override
    public void queryData() {
        //继承这个类是为了用头部布局
        //重写这个方法只是为了不得到父类的数据

    }

    private void showData(RmtPauseResetTask rmtPauseResetTask) {

        rmtPauseResetTask1 = rmtPauseResetTask;


        rmt_work_start_m = rmtPauseResetTask.getDictvos().getRMT_WORK_START_M();
        list = new ArrayList<>();
        for (int i = 0; i < rmt_work_start_m.size(); i++) {
            list.add(rmt_work_start_m.get(i).getName());
        }
        ArrayAdapter<String> ada = new ArrayAdapter<String>(ResumeActivity.this, android.R.layout.simple_spinner_item, list);
        ada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        resumeyy.setAdapter(ada);

        rmt_work_startpause_type_m = rmtPauseResetTask.getDictvos().getRMT_WORK_STARTPAUSE_TYPE_M();


        if (rmtPauseResetTask.getBodyvos() == null) {

            resumeyy_tv.setVisibility(View.GONE);
            resumeyy.setVisibility(View.VISIBLE);

            resumeTaskZxr.setText(LoginUserRecord.getInstance().getUser().getUserName());
            pzr.setVisibility(View.GONE);

            resumedetailyy_tv.setVisibility(View.GONE);
            resumedetailyy.setVisibility(View.VISIBLE);

            anniu.setVisibility(View.VISIBLE);
            button1.setText("提交");
            button2.setText("保存");


        } else {

            List<RmtPauseResetTask.BodyvosBean.RMTWORKSTARTPAUSERECORDMBean> rmt_work_startpause_record_m = rmtPauseResetTask.getBodyvos().getRMT_WORK_STARTPAUSE_RECORD_M();
            if (rmt_work_startpause_record_m != null && rmt_work_startpause_record_m.size() > 0) {
                rmtworkstartpauserecordmBean = rmt_work_startpause_record_m.get(0);


                resumeyy_tv.setVisibility(View.GONE);
                resumeyy.setVisibility(View.VISIBLE);
                resumedetailyy_tv.setVisibility(View.VISIBLE);
                resumedetailyy.setVisibility(View.GONE);


                resumeTaskZxr.setText(rmtworkstartpauserecordmBean.getApply_person());
                for (int j = 0; j < rmt_work_start_m.size(); j++) {
                    if (rmt_work_start_m.get(j).getCode().equals(rmt_work_startpause_record_m.get(0).getStart_reason())) {
                        resumeyy_tv.setText(rmt_work_start_m.get(j).getName());
                    }
                }
                resumedetailyy_tv.setText(rmt_work_startpause_record_m.get(0).getStart_remark());


                //草稿
                if (rmt_work_startpause_record_m.get(0).getAudit_status().equals("draft")) {


                    if (audit_personids.contains(LoginUserRecord.getInstance().getUser().getUserid())) {
                        resumeyy_tv.setVisibility(View.VISIBLE);
                        resumeyy.setVisibility(View.GONE);
                        resumedetailyy_tv.setVisibility(View.VISIBLE);
                        resumedetailyy.setVisibility(View.GONE);
                        anniu.setVisibility(View.GONE);
                        pzr.setVisibility(View.VISIBLE);
                        resumeTaskPzr.setText(LoginUserRecord.getInstance().getUser().getUserName());

                    } else {
                        resumeyy_tv.setVisibility(View.GONE);
                        resumeyy.setVisibility(View.VISIBLE);
                        resumedetailyy_tv.setVisibility(View.GONE);
                        resumedetailyy.setVisibility(View.VISIBLE);
                        anniu.setVisibility(View.VISIBLE);
                        button1.setText("提交");
                        button2.setText("保存");
                        pzr.setVisibility(View.GONE);

                    }


                    for (int w = 0; w < rmt_work_start_m.size(); w++) {
                        if (rmtworkstartpauserecordmBean.getStart_reason().equals(rmt_work_start_m.get(w).getCode())) {
                            resumeyy.setSelection(w, true);
                            break;
                        }

                    }
                    resumedetailyy.setText(rmtworkstartpauserecordmBean.getStart_remark());


                    //审核中
                } else if (rmt_work_startpause_record_m.get(0).getAudit_status().equals("auditing")) {
                    resumeyy_tv.setVisibility(View.VISIBLE);
                    resumeyy.setVisibility(View.GONE);

                    //审核人
                    if (audit_personids.contains(LoginUserRecord.getInstance().getUser().getUserid())) {
                        anniu.setVisibility(View.VISIBLE);
                        button1.setText("同意");
                        button2.setText("退回");
                        pzr.setVisibility(View.VISIBLE);
                        resumeTaskPzr.setText(LoginUserRecord.getInstance().getUser().getUserName());

                    } else {
                        anniu.setVisibility(View.GONE);
                        pzr.setVisibility(View.GONE);
                    }
                    //已审核
                } else if (rmt_work_startpause_record_m.get(0).getAudit_status().equals("audited")) {
                    resumeyy_tv.setVisibility(View.VISIBLE);
                    resumeyy.setVisibility(View.GONE);
                    anniu.setVisibility(View.GONE);
                    pzr.setVisibility(View.VISIBLE);
                    resumeTaskPzr.setText(rmtworkstartpauserecordmBean.getAudit_perosn());

                }
            }
        }

    }


    private void AgreeAndBack(String buttonName) {
        dialog = new ProgressDialog(
                ResumeActivity.this, "正在上传...");
        dialog.show();
        Call<ResultData<Object>> savegas = null;

        rmtworkstartpauserecordmBean.setAudit_perosn(LoginUserRecord.getInstance().getUser().getUserName());
        rmtworkstartpauserecordmBean.setAudit_perosnid(LoginUserRecord.getInstance().getUser().getUserid() + "");

        if (buttonName.equals("同意")) {
            savegas = anInterface.getPauseAgreeButton(rmtworkstartpauserecordmBean, "reset");
        } else if (buttonName.equals("退回")) {
            savegas = anInterface.getPauseBackButton(rmtworkstartpauserecordmBean, "reset");
        }
        Requset(savegas);

    }

    private void SaveAndCommitRequest(String buttonName) {

        dialog = new ProgressDialog(
                ResumeActivity.this, "正在上传...");
        dialog.show();

        RmtPauseResetTask.DictvosBean.RMTWORKSTARTMBean rmtworkstartmBean = null;
        for (int k = 0; k < list.size(); k++) {
            if ((resumeyy.getSelectedItem()).equals(list.get(k))) {
                rmtworkstartmBean = rmt_work_start_m.get(k);
            }
        }


        if (rmtPauseResetTask1.getBodyvos() == null) {

            rmtworkstartpauserecordmBean = new RmtPauseResetTask.BodyvosBean.RMTWORKSTARTPAUSERECORDMBean();
            rmtworkstartpauserecordmBean.setStartpause_type(rmt_work_startpause_type_m.get(1).getCode());
            rmtworkstartpauserecordmBean.setStart_reason(rmtworkstartmBean.getCode());
            rmtworkstartpauserecordmBean.setStart_remark(resumedetailyy.getText().toString());
            rmtworkstartpauserecordmBean.setApply_person(LoginUserRecord.getInstance().getUser().getUserName());
            rmtworkstartpauserecordmBean.setApply_personid(LoginUserRecord.getInstance().getUser().getUserid());

        } else {

            rmtworkstartpauserecordmBean.setStartpause_type(rmt_work_startpause_type_m.get(1).getCode());
            rmtworkstartpauserecordmBean.setStart_reason(rmtworkstartmBean.getCode());
            rmtworkstartpauserecordmBean.setApply_person(LoginUserRecord.getInstance().getUser().getUserName());
            rmtworkstartpauserecordmBean.setApply_personid(LoginUserRecord.getInstance().getUser().getUserid());
            rmtworkstartpauserecordmBean.setStart_remark(resumedetailyy.getText().toString());


        }


        Call<ResultData<Object>> savegas = null;

        if (buttonName.equals("保存")) {
            savegas = anInterface.getPauseSaveButton(rmtworkstartpauserecordmBean, workTask.getWork_subtask_id() + "", "reset");
        } else if (buttonName.equals("提交")) {
            savegas = anInterface.getPauseCommitButton(rmtworkstartpauserecordmBean, workTask.getWork_subtask_id() + "", "reset");
        }
        Requset(savegas);

    }

    private void Requset(Call<ResultData<Object>> savegas) {
        HttpBusiness.action(ResumeActivity.this, false, savegas, new HttpCallBack<Object>() {
            @Override
            public void success(Object o) {
                dialog.dismiss();
                ToastUtils.imgToast(ResumeActivity.this,
                        R.drawable.hd_hse_common_msg_right,
                        "上传数据完成");
                finish();
            }

            @Override
            public void warning(String msg) {
                dialog.dismiss();
                ToastUtils.imgToast(ResumeActivity.this, R.drawable.hd_hse_common_msg_wrong,
                        msg);
            }

            @Override
            public void error(Throwable t) {

                dialog.dismiss();
                ToastUtils.imgToast(ResumeActivity.this,
                        R.drawable.hd_hse_common_msg_wrong,
                        "上传数据失败！");
            }
        });
    }


}
