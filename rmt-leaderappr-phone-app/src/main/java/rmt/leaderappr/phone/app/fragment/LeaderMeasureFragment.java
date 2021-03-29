package rmt.leaderappr.phone.app.fragment;

import android.app.ProgressDialog;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.module.branch.ui.model.fragment.MeasureFragment;
import com.hd.hse.entity.conf.RmtConfMIntfc;

import java.util.Map;

import retrofit2.Call;

/**
 * created by yangning on 2018/3/20 14:41.
 */

public class LeaderMeasureFragment extends MeasureFragment {
    protected void saveApprRecord(RmtConfMIntfc rmtConfMIntfc) {
        final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setMessage("正在保存数据...");
        dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        Call<ResultData<Map<String, Long>>> call = rmtInterface.leaderApproveByTab(rmtConfMIntfc, LoginUserRecord.getInstance().getUser().getUserid());
        HttpBusiness.action(getActivity(), false, call, new HttpCallBack<Map<String, Long>>() {
            @Override
            public void success(Map<String, Long> map) {
                dialog.dismiss();
                mPDAWorkOrderInfoConfig.setApprove(true);
                if (rmtSignRecord == null) {
                    ToastUtils.toast(getActivity(), "保存成功");
                } else {
                    if (map != null && map.containsKey("apprRecordId")) {
                        rmtSignRecord.setApprRecordId(map.get("apprRecordId"));
                        upApprRecordState = true;
                        uploadSignPic(rmtSignRecord);
                    } else {
                        upApprRecordState = false;
                        ToastUtils.toast(getActivity(), "没有获取到apprRecordId，上传手签记录失败");
                    }
                }
            }

            @Override
            public void warning(String msg) {
                dialog.dismiss();
                ToastUtils.toast(getActivity(), msg);
            }

            @Override
            public void error(Throwable t) {
                dialog.dismiss();
                ToastUtils.toast(getActivity(), "保存数据失败");
            }
        });
    }
}
