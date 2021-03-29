package com.hd.hse.common.module.phone.ui.module.frament;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hd.hse.business.action.BusinessAction;
import com.hd.hse.business.task.AbstractAsyncCallBack;
import com.hd.hse.business.task.BusinessAsyncTask;
import com.hd.hse.common.component.phone.adapter.SignListViewAdapter;
import com.hd.hse.common.component.phone.custom.ProgressDialog;
import com.hd.hse.common.component.phone.event.IEventType;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.common.module.phone.R;
import com.hd.hse.common.module.phone.ui.activity.NaviFrameActivity;
import com.hd.hse.common.module.phone.ui.activity.ReadCardExamineActivity;
import com.hd.hse.common.module.phone.ui.utils.SwipingCardUtils;
import com.hd.hse.common.module.ui.model.fragment.NaviFrameFragment;
import com.hd.hse.constant.IActionType;
import com.hd.hse.entity.base.PDAWorkOrderInfoConfig;
import com.hd.hse.entity.workorder.WorkApprovalPermission;
import com.hd.hse.entity.workorder.WorkOrder;
import com.hd.hse.service.workorder.checkrules.CheckControllerActionListener;

/**
 * TODO 会签审批Fragment ClassName: CounterSignFramment ()<br/>
 * date: 2015年1月26日 <br/>
 * 
 * @author wenlin
 * @version
 */
public class CounterSignFramment extends NaviFrameFragment {

	private static Logger logger = LogUtils
			.getLogger(CounterSignFramment.class);
	/**
	 * CounterSignView:TODO(布局).
	 */
	private View counterSignView;

	/**
	 * sighLst:TODO(会签List).
	 */
	private ListView sighLst;

	/**
	 * signLsvAdapter:TODO(适配器).
	 */
	private SignListViewAdapter signLsvAdapter;

	/**
	 * workAPLst:TODO(审批环节点).
	 */
	private List<SuperEntity> workAPLst;

	/**
	 * mWorkOrder:TODO(作业票信息).
	 */
	private WorkOrder mWorkOrder;

	/**
	 * currentApp:TODO(当前审批环节).
	 */
	private SuperEntity currentAppPsm;

	/**
	 * prgDialog:TODO(异步任务提示框).
	 */
	private ProgressDialog prgDialog;

	/**
	 * busAction:TODO(业务处理).
	 */
	private BusinessAction busAction;

	/**
	 * busAsyTask:TODO(异步任务).
	 */
	private BusinessAsyncTask busAsyTask;
	/**
	 * 是否可以点击刷卡按钮
	 */
	private boolean isClick = true;

	/**
	 * mRequestCode:TODO(请求码).
	 */
	private int mRequestCode = 69;

	public CounterSignFramment() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initData(List<Object> data) {
		if (null != data && data.size() > 1) {
			// 获取数据
			this.workAPLst = (List<SuperEntity>) data.get(0);
			this.mWorkOrder = (WorkOrder) data.get(1);
		} else {
			this.mWorkOrder = (WorkOrder) data.get(0);
			workAPLst = new ArrayList<SuperEntity>();
		}
	}

	@Override
	protected View initView(LayoutInflater inflater, ViewGroup container) {
		counterSignView = inflater.inflate(
				R.layout.hd_hse_common_module_phone_countersignfragment,
				container, false);
		sighLst = (ListView) counterSignView
				.findViewById(R.id.hd_hse_common_module_phone_countersign_lv);

		return counterSignView;
	}

	@Override
	protected void init() {
		// 会签条目适配器
		signLsvAdapter = new SignListViewAdapter(workAPLst, getActivity(), null);
		sighLst.setAdapter(signLsvAdapter);
		// 设置监听
		signLsvAdapter.setOnClickListener(eventLst);

		busAction = new BusinessAction(new CheckControllerActionListener());
		busAsyTask = new BusinessAsyncTask(busAction, appCallBack);
	}

	private IEventListener eventLst = new IEventListener() {

		@Override
		public void eventProcess(int eventType, Object... arg1)
				throws HDException {
			if (!isClick) {
				return;
			}
			isClick = false;
			if (IEventType.SIGN_CLICK == eventType) {
				// 当前点击所在环节点
				SuperEntity currentWPP = (SuperEntity) arg1[0];
				// 开启刷卡
				Intent intent = new Intent(getActivity(),
						ReadCardExamineActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable(
						ReadCardExamineActivity.WORKAPPROVALPERMISSION,
						currentWPP);
				bundle.putSerializable(ReadCardExamineActivity.WORKORDER,
						mWorkOrder);
				intent.putExtras(bundle);
				startActivityForResult(intent, mRequestCode);
			}
		}
	};

	/**
	 * TODO 获取返回值
	 * 
	 * @see android.app.Fragment#onActivityResult(int, int,
	 *      android.content.Intent)
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		//isClick = true;
		if (requestCode == mRequestCode
				&& resultCode == IEventType.READCARD_TYPE) {
			// 获取刷卡后的审批环节
			currentAppPsm = (SuperEntity) data.getExtras().get(
					ReadCardExamineActivity.WORKAPPROVALPERMISSION);
			try {
				// 校验当前审批环节刷卡人权限
				SwipingCardUtils.swipingCard(mWorkOrder, currentAppPsm);
				// 启用异步保存刷卡人信息
				saveCurrentAPInfo();
			} catch (HDException e) {
				logger.error(e.getMessage(), e);
			} finally {
				isClick = true;
			}
		}
		isClick = true;
	}

	/**
	 * TODO 保存当前审批环节信息 saveCurrentAPInfo:(). <br/>
	 * date: 2015年1月30日 <br/>
	 * 
	 * @author wenlin
	 * @throws HDException
	 */
	public void saveCurrentAPInfo() throws HDException {

		prgDialog = new ProgressDialog(getActivity(), "保存信息...");
		prgDialog.show();

		Map<String, Object> mapParam = new HashMap<String, Object>();
		// 作业票信息
		if (mWorkOrder != null) {
			mapParam.put(WorkOrder.class.getName(), mWorkOrder);
		}
		// pda配置信息
		mapParam.put(PDAWorkOrderInfoConfig.class.getName(),
				getNaviCurrentEntity());
		// 审批环节信息
		mapParam.put(WorkApprovalPermission.class.getName(), currentAppPsm);
		// 启动异步保存
		busAsyTask.execute(IActionType.ACTION_TYPE_SIGN, mapParam);
	}

	@Override
	public void refreshData() {
		// TODO Auto-generated method stub

	}

	private AbstractAsyncCallBack appCallBack = new AbstractAsyncCallBack() {

		@Override
		public void start(Bundle msgData) {
			// TODO Auto-generated method stub

		}

		@Override
		public void processing(Bundle msgData) {
			if (msgData.containsKey(IActionType.ACTION_TYPE_SIGN)) {
				prgDialog.showMsg(msgData
						.getString(IActionType.ACTION_TYPE_SIGN));
			}
		}

		@Override
		public void error(Bundle msgData) {
			if (msgData.containsKey(IActionType.ACTION_TYPE_SIGN)) {
				ToastUtils.imgToast(getActivity(),
						R.drawable.hd_hse_common_msg_wrong,
						msgData.getString(IActionType.ACTION_TYPE_SIGN));
				prgDialog.dismiss();
			}
		}

		@Override
		public void end(Bundle msgData) {

			prgDialog.dismiss();

			if (null != sighLst) {
				// 更新适配器数据源数据并界面刷新
				signLsvAdapter.setCurrentWAPermission(currentAppPsm);
			}
			// 刷新导航栏完成标识
			if (null != sighLst && currentAppPsm != null
					&& currentAppPsm.getAttribute("isend").equals(1)) {
				// 设置当前导航标记
				((PDAWorkOrderInfoConfig) getNaviCurrentEntity())
						.setIsComplete(1);
				// 设置导航已完成已完成标记
				((NaviFrameActivity) getActivity())
						.setNaviFinish(getNaviCurrentEntity());
			}
			ToastUtils.imgToast(getActivity(),
					R.drawable.hd_hse_phone_message_true, "保存成功！");
		}
	};
}
