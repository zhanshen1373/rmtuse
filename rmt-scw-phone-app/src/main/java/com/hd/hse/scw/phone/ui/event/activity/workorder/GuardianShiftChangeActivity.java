package com.hd.hse.scw.phone.ui.event.activity.workorder;

import android.app.Fragment;
import android.os.Bundle;

import com.hd.hse.common.component.phone.constant.IActionBar;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.common.module.branch.ui.model.activity.FrameworkActivity;
import com.hd.hse.common.module.branch.ui.model.fragment.BaseFragment;
import com.hd.hse.constant.IConfigEncoding;
import com.hd.hse.entity.conf.RmtConfMIntfc;
import com.hd.hse.scw.phone.ui.fragment.measure.JBFragment;

import org.apache.log4j.Logger;

import java.util.List;

/**
 * TODO 交接班Activity ClassName: ShiftChangeActivity ()<br/>
 * date: 2014年11月6日 <br/>
 * 
 * @author zhulei
 * @version
 */
public class GuardianShiftChangeActivity extends FrameworkActivity {
	private static Logger logger = LogUtils
			.getLogger(GuardianShiftChangeActivity.class);

	private RmtConfMIntfc config;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		special();
		super.onCreate(savedInstanceState);
	}

	/**
	 * special:(个性化设置). <br/>
	 * date: 2015年6月8日 <br/>
	 * 
	 * @author zl
	 */
	private void special() {
		setActionTitle("接班");
		setPopupItemsDesc(new String[] { IActionBar.ITEM_PHOTOGTAPH,
				IActionBar.ITEM_PHOTOMANAGER });
	}

	@Override
	public Fragment configFragmentByPadType(List<RmtConfMIntfc> listConfig, RmtConfMIntfc config) {
		// TODO Auto-generated method stub
		BaseFragment fragment = null;
		this.config = config;
		switch (config.getTab_type()) {
		case IConfigEncoding.PROMISE_TYPE:
			fragment = new JBFragment();
			break;
		default:
			break;
		}
		if (fragment != null) {
			fragment.setPDAWorkOrderInfoConfig(config);
			//lxf设置模块编码
			fragment.setModuleCode(function);
		}
		
		return fragment;
	}

	/*
	 * OnClickListener btnClicklsn = new OnClickListener() {
	 * 
	 * @Override public void onClick(View arg0) { try {
	 * ManagerInstance.getInstance
	 * ().getActionInstance().action(BusinessActionNum
	 * .ACTION_ACCEPT_WORK_AUDIT,new ICallBackImpl(), function,config); } catch
	 * (HDException e) { logger.error(e.getMessage()); } } };
	 */

	/*
	 * private class ICallBackImpl implements ICallBack{
	 * 
	 * @Override public void start(String action, int flag, Object... objects) {
	 * 
	 * }
	 * 
	 * @Override public void process(String action, int flag, Object... objects)
	 * { }
	 * 
	 * @Override public void end(String action, int flag, Object... objects) {
	 * ToastUtils.imgToast(GuardianShiftChangeActivity.this,
	 * R.drawable.hd_hse_common_msg_right, "保存成功！"); }
	 * 
	 * @Override public void error(String action, int flag, Object... objects) {
	 * ToastUtils.imgToast(GuardianShiftChangeActivity.this,
	 * R.drawable.hd_hse_common_msg_wrong, "保存失败！"); } };
	 */

}
