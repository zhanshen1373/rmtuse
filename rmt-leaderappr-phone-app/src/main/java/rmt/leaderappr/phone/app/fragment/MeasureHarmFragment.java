package rmt.leaderappr.phone.app.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.adapter.CSQRListAdapter;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.common.module.branch.ui.model.fragment.BaseFragment;
import com.hd.hse.entity.conf.RmtConfMIntfc;
import com.hd.hse.entity.resultdata.RmtVirRiskM;
import com.hd.hse.entity.workorder.RmtWorkPermitHarm;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.hayden.hap.hd_push.sdk.HDPush.context;

/**
 * ClassName: MeasureHarmFragment ()<br/>
 * date: 2015年7月7日  <br/>
 * 用危害
 *
 * @author lxf
 */
public class MeasureHarmFragment extends BaseFragment {

    private static Logger logger = LogUtils.getLogger(MeasureHarmFragment.class);

    private BaseAdapter mAdapter;

    private ProgressDialog mDialog;

    public MeasureHarmFragment() {
        super(STYLE_SINGLE_BUTTON);

    }

    @Override
    protected View onCreatePageView(LayoutInflater arg0) {
        ListView _returnView = new ListView(getActivity());

        mAdapter = new CSQRListAdapter(_returnView, getListHazard(),
                "harm_description", "isselect", "confirm");

        setBottomButtonText("审核");

        setOnBottomButtonClickListener(new OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                // 危害审核
                //confirm为null时，设置默认值为0
                List<RmtWorkPermitHarm> rmtWorkPermitHarmList = getListHazard();
                if (rmtWorkPermitHarmList != null) {
                    for (RmtWorkPermitHarm rmtWorkPermitHarm : rmtWorkPermitHarmList) {
                        if (rmtWorkPermitHarm.getConfirm() == null)
                            rmtWorkPermitHarm.setConfirm(0);
                    }
                }

                uploadRecord(mPDAWorkOrderInfoConfig);


            }
        });

        return _returnView;
    }
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
                ToastUtils.toast(context, msg);
            }

            @Override
            public void error(Throwable t) {
                dialog.dismiss();
                ToastUtils.toast(context, "保存数据失败");
            }
        });
    }

    private List<RmtWorkPermitHarm> getListHazard() {
        if (mPDAWorkOrderInfoConfig == null ||
                mPDAWorkOrderInfoConfig.getVirRiskMVOList() == null ||
                mPDAWorkOrderInfoConfig.getVirRiskMVOList().size() == 0) {
            return null;
        }
        List<RmtVirRiskM> rmtVirRiskMList = mPDAWorkOrderInfoConfig.getVirRiskMVOList();
        List<RmtWorkPermitHarm> rmtWorkPermitHarmList = new ArrayList<>();

        for (RmtVirRiskM rmtVirRiskM : rmtVirRiskMList) {
            rmtWorkPermitHarmList.addAll(rmtVirRiskM.getHarmVOList());
        }
        return rmtWorkPermitHarmList.size() > 0 ? rmtWorkPermitHarmList : null;
    }

}
