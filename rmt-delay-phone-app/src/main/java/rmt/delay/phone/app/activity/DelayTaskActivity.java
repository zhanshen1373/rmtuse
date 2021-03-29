package rmt.delay.phone.app.activity;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hayden.hap.common.utils.ToastUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.custom.MessageDialog;
import com.hd.hse.common.module.branch.ui.model.activity.FrameworkActivity;
import com.hd.hse.common.module.phone.ui.module.activity.BaseTaskListBusActivity;
import com.hd.hse.common.module.phone.ui.utils.SwipingCardUtils;
import com.hd.hse.entity.resultdata.RmtDelayTaskHistoryList;
import com.hd.hse.entity.workorder.ResultDataBean;
import com.hd.hse.entity.workorder.RmtWorkSubtask;

import java.util.List;

import retrofit2.Call;
import rmt.delay.phone.app.Adapter.ListViewAdapter;
import rmt.delay.phone.app.R;

public class DelayTaskActivity extends FrameworkActivity {

    private ListView listView;
    private ImageView imageView;
    private ListViewAdapter listViewAdapter;
    private List<RmtDelayTaskHistoryList.MainvoBean> mainvo;
    private RmtWorkSubtask hh;
    private List<RmtDelayTaskHistoryList.DictvosBean.RMTWORKDELAYRECORDAUDITSTATUSMBean> rmt_work_delay_record_audit_status_m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delay_task);
        listView = (ListView) findViewById(R.id.delaytaskactivity_listview);
        imageView = (ImageView) findViewById(R.id.delaytaskactivity_image);
        hh = workTask;
        showFunction();
    }

    List<Long> audit_personids = null;
    boolean isShr = false;

    public void queryData() {
        final ProgressDialog dialog = new ProgressDialog(DelayTaskActivity.this);
        dialog.setMessage("加载数据中。。。");
        dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        // 获取分项任务
        Call<ResultData<RmtDelayTaskHistoryList>> delayTaskHistoryList = rmtInterface.getDelayTaskHistoryList(LoginUserRecord.getInstance().getUser().getUserid(), workTask.getWork_subtask_id());
        HttpBusiness.action(DelayTaskActivity.this, false, delayTaskHistoryList, new HttpCallBack<RmtDelayTaskHistoryList>() {
            @Override
            public void success(final RmtDelayTaskHistoryList rmtDelayTaskHistoryList) {
                dialog.dismiss();

                Call<ResultData<RmtInterface.DelayUser>> delayUser = rmtInterface.getDelayUser(workTask.getWork_subtask_id());
                HttpBusiness.action(DelayTaskActivity.this, false, delayUser, new HttpCallBack<RmtInterface.DelayUser>() {
                    @Override
                    public void success(RmtInterface.DelayUser delayUser) {
                        audit_personids = delayUser.getAudit_personids();

                        if (rmtDelayTaskHistoryList != null) {
                            mainvo = rmtDelayTaskHistoryList.getMainvo();
                            rmt_work_delay_record_audit_status_m = rmtDelayTaskHistoryList.getDictvos().getRMT_WORK_DELAY_RECORD_AUDIT_STATUS_M();
                        }


                        if (audit_personids != null && audit_personids.size() > 0) {
                            //审核人
                            if (audit_personids.contains(LoginUserRecord.getInstance().getUser().getUserid())) {
                                isShr = true;
                                imageView.setVisibility(View.GONE);
                                //送交人
                            } else {
                                imageView.setVisibility(View.VISIBLE);

                            }
                            if (mainvo != null && mainvo.size() > 0) {
                                listViewAdapter = new ListViewAdapter(DelayTaskActivity.this, mainvo, rmt_work_delay_record_audit_status_m, isShr);
                                listView.setAdapter(listViewAdapter);
                            } else {
                                ToastUtil.toast(DelayTaskActivity.this, "暂无数据");
                            }

                        }
//                                if (mainvo.get(0).getHeadVO().getRMT_WORK_DELAY_RECORD_M().getApply_person().equals(LoginUserRecord.getInstance().getUser().getUserName())) {
//                                    imageView.setVisibility(View.VISIBLE);
//                                } else {
//                                    imageView.setVisibility(View.GONE);
//                                }

                    }

                    @Override
                    public void warning(String msg) {
                        ToastUtil.toast(DelayTaskActivity.this, msg);

                    }

                    @Override
                    public void error(Throwable t) {
                        ToastUtil.toast(DelayTaskActivity.this, "获取审批人数据失败");

                    }
                });


            }

            @Override
            public void warning(String msg) {
                dialog.dismiss();
                ToastUtil.toast(DelayTaskActivity.this, msg);
            }

            @Override
            public void error(Throwable t) {
                dialog.dismiss();
                ToastUtil.toast(DelayTaskActivity.this, "获取数据失败");
            }
        });
        setActionTitle(null);
        setMenuModel();

    }


    private void showFunction() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DelayTaskActivity.this, AddDelayTaskActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("duixiang", mainvo.get(position));
                bundle.putSerializable("worksubtask", hh);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {


                String audit_status = mainvo.get(position).getHeadVO().getRMT_WORK_DELAY_RECORD_M().getAudit_status();
                switch (audit_status) {

                    case "DRAFT":

                        MessageDialog.Builder builder = new MessageDialog.Builder(DelayTaskActivity.this);
                        builder.setTitle("提示");
                        builder.setMessage("是否删除本条数据");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                requestInterface(position);

                            }
                        });
                        builder.setNegativeButton("取消",
                                new android.content.DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        builder.createWarm().show();
                        break;

                    default:

                        MessageDialog.Builder builder1 = new MessageDialog.Builder(DelayTaskActivity.this);
                        builder1.setTitle("提示");
                        builder1.setMessage("该状态数据不能删除");
                        builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                                // 设置你的操作事项

                            }
                        });
                        builder1.createWarm().show();

                }


                return true;
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DelayTaskActivity.this, AddDelayTaskActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("worksubtask", hh);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void requestInterface(final int position) {
        long work_delay_id = mainvo.get(position).getHeadVO().getRMT_WORK_DELAY_RECORD_M().getWork_delay_id();
        final ProgressDialog dialog = new ProgressDialog(DelayTaskActivity.this);
        dialog.setMessage("正在查询");
        dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        Call<ResultData<Object>> delayDelButton = rmtInterface.getDelayDelButton(work_delay_id);
        HttpBusiness.action(DelayTaskActivity.this, false, delayDelButton, new HttpCallBack<Object>() {

            @Override
            public void success(Object o) {
                mainvo.remove(position);
                listViewAdapter.notifyDataSetChanged();
                dialog.dismiss();
                ToastUtil.toast(DelayTaskActivity.this, "删除数据成功");
            }

            @Override
            public void warning(String msg) {
                dialog.dismiss();
                ToastUtil.toast(DelayTaskActivity.this, msg);
            }

            @Override
            public void error(Throwable t) {
                dialog.dismiss();
                ToastUtil.toast(DelayTaskActivity.this, "删除数据失败");

            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        queryData();
    }
}
