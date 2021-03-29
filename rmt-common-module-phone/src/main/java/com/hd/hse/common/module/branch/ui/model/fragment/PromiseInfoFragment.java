package com.hd.hse.common.module.branch.ui.model.fragment;

import android.app.ProgressDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.entity.conf.RmtConfApprAuth;

import java.util.Map;

import retrofit2.Call;

public class PromiseInfoFragment<T extends SuperEntity> extends TextFragment
		implements OnClickListener {

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
		return "暂无承诺信息";
	}

	@Override
	public void onClick(View v) {
		if (dialog == null) {
			dialog = new ProgressDialog(getActivity());
			dialog.setMessage("保存中...");
		}
		dialog.show();
		final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
		Call<ResultData<Map<String, Long>>> subEle = rmtInterface.approveByTab(mPDAWorkOrderInfoConfig, LoginUserRecord.getInstance().getUser().getUserid());
		HttpBusiness.action(getActivity(), false, subEle, new HttpCallBack<Map<String, Long>>() {
			@Override
			public void success(Map<String, Long> map) {
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
