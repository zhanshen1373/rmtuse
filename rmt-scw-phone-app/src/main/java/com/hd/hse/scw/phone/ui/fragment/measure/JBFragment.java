package com.hd.hse.scw.phone.ui.fragment.measure;

import android.app.ProgressDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.module.branch.ui.model.fragment.TextFragment;
import com.hd.hse.entity.conf.RmtConfApprAuth;

import retrofit2.Call;

/**
 * created by yangning on 2017/6/26 17:52.
 */

public class JBFragment<T extends SuperEntity> extends TextFragment
        implements View.OnClickListener {

    private RmtConfApprAuth data;
    private ProgressDialog dialog;

    @Override
    protected View onCreatePageView(LayoutInflater inflater) {
        setBottomButtonText("保存");
        setOnBottomButtonClickListener(this);

        return super.onCreatePageView(inflater);
    }

    @Override
    protected String getText() {
        if (mPDAWorkOrderInfoConfig != null
                && mPDAWorkOrderInfoConfig.getApprAuthVOList() != null) {
            data = mPDAWorkOrderInfoConfig.getApprAuthVOList().get(0);
            return data.getCmt_info();
        }
        return "暂无接班信息";
    }

    @Override
    public void onClick(View v) {
        if (dialog == null) {
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("保存中...");
        }
        dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        Call<ResultData<Object>> handover = rmtInterface.handover(mPDAWorkOrderInfoConfig, LoginUserRecord.getInstance().getUser().getUserid());
        HttpBusiness.action(getActivity(), false, handover, new HttpCallBack<Object>() {
            @Override
            public void success(Object o) {
                dialog.dismiss();
                ToastUtils.toast(getActivity(), "保存成功");
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