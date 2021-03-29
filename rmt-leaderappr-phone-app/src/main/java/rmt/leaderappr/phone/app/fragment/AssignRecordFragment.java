package rmt.leaderappr.phone.app.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hayden.hap.common.utils.ToastUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.module.branch.ui.model.fragment.BaseFragment;
import com.hd.hse.entity.assign.RmtAssignRecord;
import com.hd.hse.entity.workorder.RmtWorkSubtask;

import java.util.List;

import retrofit2.Call;
import rmt.leaderappr.phone.app.R;

/**
 * created by yangning on 2017/11/2 14:06.
 */

public class AssignRecordFragment extends BaseFragment {
    private RmtWorkSubtask workTask;
    private Context mContext;
    private List<RmtAssignRecord> rmtAssignRecords;
    private ListView listView;
    private View view;
    private AssignRecordAdapter assignRecordAdapter;
    /**
     * mProgressDialog:TODO(dialog弹出框).
     */
    private ProgressDialog mProgressDialog;

    public AssignRecordFragment() {
        super(BaseFragment.STYLE_NO_MENU);
    }

    @Override
    protected View onCreatePageView(LayoutInflater inflater) {
        mContext = getActivity();

        view = inflater.inflate(R.layout.assign_record_fragment_layout, null);
        listView = (ListView) view.findViewById(R.id.assign_record_fragment_layout_lv);
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setMessage("正在加载交接班记录...");
        }
        mProgressDialog.show();

        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        Call<ResultData<List<RmtAssignRecord>>> call = rmtInterface.getAssignRecord(workTask.getWork_subtask_id(), LoginUserRecord.getInstance().getUser().getUserid());
        HttpBusiness.action(mContext, false, call, new HttpCallBack<List<RmtAssignRecord>>() {
            @Override
            public void success(List<RmtAssignRecord> rmtAssignRecordList) {
                mProgressDialog.dismiss();
                rmtAssignRecords = rmtAssignRecordList;
                if (rmtAssignRecords == null || rmtAssignRecords.size() <= 0) {
                    ToastUtil.toast(mContext, "暂时还没有交接班记录");
                    return;
                }
                assignRecordAdapter = new AssignRecordAdapter(rmtAssignRecords, mContext);
                listView.setAdapter(assignRecordAdapter);
            }

            @Override
            public void warning(String msg) {
                mProgressDialog.dismiss();
                ToastUtil.toast(mContext, msg);
            }

            @Override
            public void error(Throwable t) {
                mProgressDialog.dismiss();
                ToastUtil.toast(mContext, "获取数据失败");
            }
        });
        return view;
    }

    public void setWorkTask(RmtWorkSubtask workTask) {
        this.workTask = workTask;
    }
}
