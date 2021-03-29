package com.hd.hse.scene.phone.ui.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.hd.hse.common.component.phone.adapter.CSQRListAdapter;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.common.module.branch.ui.model.fragment.BaseFragment;
import com.hd.hse.entity.resultdata.RmtVirRiskM;
import com.hd.hse.entity.workorder.RmtWorkPermitHarm;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

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
