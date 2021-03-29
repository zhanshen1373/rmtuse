package rmt.authorize.phone.app.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hayden.hap.common.utils.ToastUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.module.branch.ui.model.activity.FrameworkActivity;
import com.hd.hse.entity.resultdata.RmtAuthorizeRefuseReceiveBean;
import com.hd.hse.entity.resultdata.RmtAuthorizeTsDetailMessage;
import com.hd.hse.entity.workorder.RmtWorkSubtask;

import java.util.List;

import retrofit2.Call;
import rmt.authorize.phone.app.R;

public class WorkAuthorizeMessageActivity extends BarActivity implements View.OnClickListener {


    private TextView author;
    private TextView starttime;
    private TextView endtime;
    private Button refuse;
    private Button receive;
    private TextView time;
    private RmtAuthorizeTsDetailMessage.MainvoBean.HeadVOBean.RMTWORKGRANTITEMMBean rmt_work_grant_item_m;
    private RmtWorkSubtask workTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_work_authorize_message);

        Intent intent = getIntent();
        workTask = (RmtWorkSubtask) intent.getSerializableExtra(FrameworkActivity.INTENT_WORKTASK_KEY);


        View inflate = View.inflate(this, R.layout.activity_work_authorize_message, null);
        frameLayout.addView(inflate);

        author = (TextView) findViewById(R.id.author);
        starttime = (TextView) findViewById(R.id.starttime);
        endtime = (TextView) findViewById(R.id.endtime);
        refuse = (Button) findViewById(R.id.refuse);
        receive = (Button) findViewById(R.id.receive);
        time = (TextView) findViewById(R.id.time);

        initData();

        initEvent();
    }

    private void initEvent() {

        refuse.setOnClickListener(this);
        receive.setOnClickListener(this);

    }

    private void initData() {

        final ProgressDialog dialog = new ProgressDialog(WorkAuthorizeMessageActivity.this);
        dialog.setMessage("正在查询");
        dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);

        Call<ResultData<RmtAuthorizeTsDetailMessage>> call = rmtInterface.getTsWorkAuthorizeMessage(workTask.getWork_subtask_id(), LoginUserRecord.getInstance().getUser().getUserid());
        HttpBusiness.action(WorkAuthorizeMessageActivity.this, false, call, new HttpCallBack<RmtAuthorizeTsDetailMessage>() {
            @Override
            public void success(RmtAuthorizeTsDetailMessage resultDataBean) {
                dialog.dismiss();
                if (resultDataBean != null) {
                    List<RmtAuthorizeTsDetailMessage.MainvoBean> mainvo = resultDataBean.getMainvo();
                    if (mainvo != null && mainvo.size() > 0) {
                        RmtAuthorizeTsDetailMessage.MainvoBean.HeadVOBean headVO = mainvo.get(0).getHeadVO();
                        if (headVO != null) {
                            rmt_work_grant_item_m = headVO.getRMT_WORK_GRANT_ITEM_M();
                            if (rmt_work_grant_item_m != null) {
                                if (!rmt_work_grant_item_m.getStatus().equals("appr")){
                                    refuse.setVisibility(View.INVISIBLE);
                                    receive.setVisibility(View.INVISIBLE);
                                }
                                tv.setText(rmt_work_grant_item_m.getBe_authed() + "的授权");
                                RmtAuthorizeTsDetailMessage.MainvoBean.HeadVOBean.RMTWORKGRANTITEMMBean.GrantvoBean grantvo = rmt_work_grant_item_m.getGrantvo();
                                if (grantvo != null) {
                                    starttime.setText("开始时间: " + grantvo.getStarttime());
                                    endtime.setText("结束时间: " + grantvo.getEndtime());
                                    author.setText(grantvo.getAuth() + "于");
                                    time.setText(grantvo.getCreated_dt());
                                }
                            }
                        }
                    }

                }
            }

            @Override
            public void warning(String msg) {
                dialog.dismiss();
                ToastUtil.toast(WorkAuthorizeMessageActivity.this, msg);
            }

            @Override
            public void error(Throwable t) {
                dialog.dismiss();
                ToastUtil.toast(WorkAuthorizeMessageActivity.this, "获取数据失败");
            }
        });


    }

    @Override
    public int Menu() {
        return 0;
    }

    @Override
    protected void onResume() {
        super.onResume();
        toolbar.setTitle("");
        tv.setText("作业授权");
    }


    @Override
    public void onClick(View v) {
        RmtAuthorizeRefuseReceiveBean rmtAuthorizeRefuseReceiveBean = new RmtAuthorizeRefuseReceiveBean();
        rmtAuthorizeRefuseReceiveBean.setBe_authed(rmt_work_grant_item_m.getBe_authed());
        rmtAuthorizeRefuseReceiveBean.setBe_authedid(rmt_work_grant_item_m.getBe_authedid());
        rmtAuthorizeRefuseReceiveBean.setCreated_dt(rmt_work_grant_item_m.getCreated_dt());
        rmtAuthorizeRefuseReceiveBean.setGrt_id(rmt_work_grant_item_m.getGrt_id());
        rmtAuthorizeRefuseReceiveBean.setGrt_item_id(rmt_work_grant_item_m.getGrt_item_id());
        rmtAuthorizeRefuseReceiveBean.setGrt_item_time(rmt_work_grant_item_m.getGrt_item_time());
        rmtAuthorizeRefuseReceiveBean.setRec_time(rmt_work_grant_item_m.getRec_time());
        rmtAuthorizeRefuseReceiveBean.setStatus(rmt_work_grant_item_m.getStatus());
        //拒绝
        if (v.getId() == R.id.refuse) {

            refuse(rmtAuthorizeRefuseReceiveBean);

        }
        //接收
        else if (v.getId() == R.id.receive) {

            receive(rmtAuthorizeRefuseReceiveBean);

        }
    }

    private void receive(RmtAuthorizeRefuseReceiveBean rmtAuthorizeRefuseReceiveBean) {

        final ProgressDialog dialog = new ProgressDialog(WorkAuthorizeMessageActivity.this);
        dialog.setMessage("正在保存数据...");
        dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        Call<ResultData<Object>> call = rmtInterface.getTsWorkAuthorizeReceive(rmtAuthorizeRefuseReceiveBean, LoginUserRecord.getInstance().getUser().getUserid());
        HttpBusiness.action(WorkAuthorizeMessageActivity.this, false, call, new HttpCallBack<Object>() {
            @Override
            public void success(Object map) {
                dialog.dismiss();
                ToastUtil.toast(WorkAuthorizeMessageActivity.this, "操作成功");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finish();
            }

            @Override
            public void warning(String msg) {
                dialog.dismiss();
                ToastUtils.toast(WorkAuthorizeMessageActivity.this, msg);
            }

            @Override
            public void error(Throwable t) {
                dialog.dismiss();
                ToastUtils.toast(WorkAuthorizeMessageActivity.this, "失败");
            }
        });

    }

    private void refuse(RmtAuthorizeRefuseReceiveBean rmtAuthorizeRefuseReceiveBean) {

        final ProgressDialog dialog = new ProgressDialog(WorkAuthorizeMessageActivity.this);
        dialog.setMessage("正在保存数据...");
        dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        Call<ResultData<Object>> call = rmtInterface.getTsWorkAuthorizeRefuse(rmtAuthorizeRefuseReceiveBean, LoginUserRecord.getInstance().getUser().getUserid());
        HttpBusiness.action(WorkAuthorizeMessageActivity.this, false, call, new HttpCallBack<Object>() {
            @Override
            public void success(Object map) {
                dialog.dismiss();
                ToastUtil.toast(WorkAuthorizeMessageActivity.this, "操作成功");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finish();
            }

            @Override
            public void warning(String msg) {
                dialog.dismiss();
                ToastUtils.toast(WorkAuthorizeMessageActivity.this, msg);
            }

            @Override
            public void error(Throwable t) {
                dialog.dismiss();
                ToastUtils.toast(WorkAuthorizeMessageActivity.this, "失败");
            }
        });


    }
}
