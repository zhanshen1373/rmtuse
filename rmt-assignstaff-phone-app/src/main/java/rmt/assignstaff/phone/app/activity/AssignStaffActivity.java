package rmt.assignstaff.phone.app.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hayden.hap.common.utils.ToastUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.constant.IActionBar;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.module.phone.ui.activity.BaseFrameActivity;
import com.hd.hse.entity.assign.RmtWorkAssign;
import com.hd.hse.entity.resultdata.RmtWorkAssignResultData;
import com.hd.hse.entity.workorder.RmtWorkSubtask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import rmt.assignstaff.phone.app.adapter.AssignStaffAdapter;
import test.demo.rmt_assignstaff_phone_app.R;

import static com.hd.hse.common.module.branch.ui.model.activity.FrameworkActivity.INTENT_FUNCTION_KEY;
import static com.hd.hse.common.module.branch.ui.model.activity.FrameworkActivity.INTENT_WORKTASK_KEY;

/**
 * created by yangning on 2017/8/15 15:02.
 * 人员指定
 */

public class AssignStaffActivity extends BaseFrameActivity implements View.OnClickListener, IEventListener {
    private LinearLayout liAll;
    private ListView lv;
    private RmtWorkSubtask workSubtask;
    private List<RmtWorkAssign> assignData;
    private AssignStaffAdapter mAdapter;
    private String state;
    private Button btAssign;
    /**
     * 全选或全不选
     */
    private Button btSelectAll;
    /**
     * 设备员确认
     */
    private Button btWfFinish;
    private boolean isSelectAll = false;
    public static final String SELECTDATA = "selectdata";
    public static final String WORKSUBTASK = "worksubtask";
    public static final String COMEWHERE = "comesj";
    private Button btSj;
    private Button remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rmt_assignstaff_activity_layout);
        initView();
        initActionbar();
        initData();
    }

    private void initActionbar() {
        // 初始化ActionBar
        setCustomActionBar(false, this, new String[]{IActionBar.TV_BACK, IActionBar.TV_TITLE});
        // 设置导航栏标题
        setActionBartitleContent("人员指定", false);
    }


    private void initData() {
        Intent intent = getIntent();
        workSubtask = (RmtWorkSubtask) intent.getSerializableExtra(INTENT_WORKTASK_KEY);
        state = intent.getStringExtra(INTENT_FUNCTION_KEY);
        assignData = new ArrayList<>();
        mAdapter = new AssignStaffAdapter(assignData, AssignStaffActivity.this);
        lv.setAdapter(mAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAdapter.setCurrentCheck(position, !mAdapter.getCurrentCheck(position));
                HashMap<Integer, Boolean> map = mAdapter.getCheckMap();
                boolean flag = true;
                for (int i = 0; i < assignData.size(); i++) {
                    if (!map.get(i)) {
                        flag = false;
                        break;
                    }
                }
                isSelectAll = flag;
                setBtSelectAllText();

            }
        });
        if (workSubtask != null)
            getNetData(workSubtask.getWork_task_id(), workSubtask.getWork_subtask_id());
        else
            ToastUtils.toast(getApplicationContext(), "未找到分项任务");

    }

    private void initView() {
        liAll = (LinearLayout) findViewById(R.id.rmt_assignstaff_activity_layout_li_all);
        lv = (ListView) findViewById(R.id.rmt_assignstaff_activity_layout_lv);
        btAssign = (Button) findViewById(R.id.rmt_assignstaff_activity_layout_bt_assign);
        btSelectAll = (Button) findViewById(R.id.rmt_assignstaff_activity_layout_bt_all_select);
        btWfFinish = (Button) findViewById(R.id.rmt_assignstaff_activity_layout_bt_wfFinish);
        remove = (Button) findViewById(R.id.rmt_assignstaff_activity_layout_bt_remove);
//        btSj = (Button) findViewById(R.id.rmt_assignstaff_activity_layout_bt_sj);
        btWfFinish.setOnClickListener(this);
        btAssign.setOnClickListener(this);
        btSelectAll.setOnClickListener(this);
        remove.setOnClickListener(this);
//        btSj.setOnClickListener(this);

    }

    private void setBtSelectAllText() {
        if (isSelectAll)
            btSelectAll.setText("全不选");
        else
            btSelectAll.setText("全选");
    }

    private void getRemoveData(List<RmtWorkAssign> rmtWorkAssignList) {

        final ProgressDialog dialog = new ProgressDialog(AssignStaffActivity.this);
        dialog.setMessage("清除数据中。。。");
        dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        Call<ResultData<Object>> call = rmtInterface.getWorkAssignRemove(rmtWorkAssignList, workSubtask.getWork_subtask_id());
        HttpBusiness.action(AssignStaffActivity.this, false, call, new HttpCallBack<Object>() {


            @Override
            public void success(Object result) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                ToastUtils.toast(AssignStaffActivity.this, "清除人员数据成功");
                if (workSubtask != null)
                    getNetData(workSubtask.getWork_task_id(), workSubtask.getWork_subtask_id());
            }

            @Override
            public void warning(String msg) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                ToastUtils.toast(AssignStaffActivity.this, msg);
            }

            @Override
            public void error(Throwable t) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                ToastUtils.toast(AssignStaffActivity.this, "清除人员数据失败");
            }
        });

    }

    private void getNetData(long taskId, long subtaskId) {
        final ProgressDialog dialog = new ProgressDialog(AssignStaffActivity.this);
        dialog.setMessage("加载数据中。。。");
        dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        Call<ResultData<RmtWorkAssignResultData>> call = rmtInterface.getAssignList(taskId, subtaskId);
        HttpBusiness.action(AssignStaffActivity.this, false, call, new HttpCallBack<RmtWorkAssignResultData>() {


            @Override
            public void success(RmtWorkAssignResultData result) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }

                assignData.clear();
                //数据解析
                if (result != null && result.getMainvo() != null && result.getMainvo().size() > 0) {
                    List<RmtWorkAssignResultData.MainvoBean> mainvoList = result.getMainvo();
                    for (RmtWorkAssignResultData.MainvoBean mainvoBean : mainvoList) {
                        if (mainvoBean != null && mainvoBean.getHeadVO() != null
                                && mainvoBean.getHeadVO().getRMT_WORK_ASSIGN_M() != null)
                            assignData.add(mainvoBean.getHeadVO().getRMT_WORK_ASSIGN_M());
                    }
                }
                //assignData.addAll()
                mAdapter.initCheckMap();
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void warning(String msg) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                ToastUtils.toast(AssignStaffActivity.this, msg);
            }

            @Override
            public void error(Throwable t) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                ToastUtils.toast(AssignStaffActivity.this, "获取人员指定数据失败");
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.rmt_assignstaff_activity_layout_bt_assign) {
            //点击人员指定按钮
            assignStaff();
        } else if (id == R.id.rmt_assignstaff_activity_layout_bt_all_select) {
            if (isSelectAll && mAdapter != null) {
                mAdapter.selectNone();
                isSelectAll = false;
            } else if (!isSelectAll && mAdapter != null) {
                mAdapter.selectAll();
                isSelectAll = true;
            }
            setBtSelectAllText();
        } else if (id == R.id.rmt_assignstaff_activity_layout_bt_wfFinish) {
            //点击设备员确认
            onclickWfFinash();
        } else if (id == R.id.rmt_assignstaff_activity_layout_bt_remove) {
            //清除按钮
            assignRemove();
        }
        /*
        else if (id == R.id.rmt_assignstaff_activity_layout_bt_sj) {
            //根据环节点做操作
            if (workSubtask.getWf_instance_seq() == 0) {
                //暂时没有这种情况
                //弹到类似人员制定
                Intent intent = new Intent(AssignStaffActivity.this, SelectPersionActivity.class);
                intent.putExtra(COMEWHERE, "送交");
                intent.putExtra(WORKSUBTASK, workSubtask);
                startActivity(intent);
            } else {
//             直接发送请求
                postSjData();
            }
        }
        */

    }

    private void postSjData() {

        final ProgressDialog dialog = new ProgressDialog(AssignStaffActivity.this);
        dialog.setMessage("加载数据中。。。");
        dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        Call<ResultData<Object>> call = rmtInterface.sjpostOK(workSubtask.getWork_subtask_id(), LoginUserRecord.getInstance().getUser().getUserid(), null);
        HttpBusiness.action(AssignStaffActivity.this, false, call, new HttpCallBack<Object>() {


            @Override
            public void success(Object result) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }

                ToastUtils.toast(getApplicationContext(), "数据提交成功");
            }

            @Override
            public void warning(String msg) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                ToastUtils.toast(getApplicationContext(), msg);
            }

            @Override
            public void error(Throwable t) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                ToastUtils.toast(getApplicationContext(), "数据提交失败");
            }
        });

    }

    private void onclickWfFinash() {
        final ProgressDialog dialog = new ProgressDialog(AssignStaffActivity.this);
        dialog.setMessage("确认中...");
        dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        Call<ResultData<Object>> call = rmtInterface.wfFinishAfterAssign(workSubtask.getWork_subtask_id(), LoginUserRecord.getInstance().getUser().getUserid());
        HttpBusiness.action(AssignStaffActivity.this, false, call, new HttpCallBack<Object>() {
            @Override
            public void success(Object o) {
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
                ToastUtil.toast(AssignStaffActivity.this, "确认成功");
            }

            @Override
            public void warning(String msg) {
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
                ToastUtil.toast(AssignStaffActivity.this, msg);
            }

            @Override
            public void error(Throwable t) {
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
                ToastUtil.toast(AssignStaffActivity.this, "确认失败");
            }
        });
    }


    private void assignRemove() {
        HashMap<Integer, Boolean> checkMap = mAdapter.getCheckMap();
        ArrayList<RmtWorkAssign> selectData = new ArrayList<>();
        for (int i = 0; i < assignData.size(); i++) {
            if (checkMap.get(i))
                selectData.add(assignData.get(i));
        }
        if (selectData.size() == 0) {
            ToastUtils.toast(getApplicationContext(), "您还未勾选任何条目");
            return;
        }

        if (selectData != null && selectData.size() > 0) {
            getRemoveData(selectData);
        }


    }


    private void assignStaff() {
        HashMap<Integer, Boolean> checkMap = mAdapter.getCheckMap();
        ArrayList<RmtWorkAssign> selectData = new ArrayList<>();
        for (int i = 0; i < assignData.size(); i++) {
            if (checkMap.get(i))
                selectData.add(assignData.get(i));
        }
        if (selectData.size() == 0) {
            ToastUtils.toast(getApplicationContext(), "您还未勾选任何条目");
            return;
        }
        Intent intent = new Intent(AssignStaffActivity.this, SelectPersionActivity.class);
        intent.putExtra(SELECTDATA, selectData);
        intent.putExtra(WORKSUBTASK, workSubtask);
        startActivityForResult(intent, 0);


    }


    @Override
    public void eventProcess(int eventType, Object... objects) throws HDException {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getNetData(workSubtask.getWork_task_id(), workSubtask.getWork_subtask_id());
        isSelectAll = false;
        setBtSelectAllText();
    }
}
