package com.hd.hse.common.module.ui.model.fragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hd.hse.business.action.BusinessAction;
import com.hd.hse.business.task.AbstractAsyncCallBack;
import com.hd.hse.business.task.BusinessAsyncTask;
import com.hd.hse.common.component.phone.custom.ProgressDialog;
import com.hd.hse.common.component.phone.event.IEventType;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.common.module.phone.R;
import com.hd.hse.common.module.phone.ui.activity.ReadCardExamineActivity;
import com.hd.hse.common.module.phone.ui.custom.ExamineDialog;
import com.hd.hse.constant.IActionType;
import com.hd.hse.entity.base.HazardNotify;
import com.hd.hse.entity.base.PDAWorkOrderInfoConfig;
import com.hd.hse.entity.workorder.WorkApprovalPermission;
import com.hd.hse.entity.workorder.WorkOrder;
import com.hd.hse.service.workorder.checkrules.CheckControllerActionListener;

public abstract class NaviFrameFragmentExmaine extends NaviFrameFragment {

	private static Logger logger = LogUtils
			.getLogger(NaviFrameFragmentExmaine.class);

	private ExamineDialog<WorkApprovalPermission> exmaineDialog = null;
	public ProgressDialog mDialog = null;
	private List<WorkApprovalPermission> examineList = null;

	/**
	 * workOrder:TODO(作业票实体对象).
	 */
	private WorkOrder workOrder;

	/**
	 * busAction:TODO(后台业务处理对象).
	 */
	private BusinessAction busAction;

	/**
	 * asyTask:TODO(后台异步任务).
	 */
	private BusinessAsyncTask asyTask;

	/**
	 * currentTabPDAConfigInfo:TODO(表示当前PAD选中的).
	 */
	private SuperEntity currentTabPADConfigInfo;

	/**
	 * curApproveNode:TODO(显示当前审批环节点).
	 */
	private WorkApprovalPermission curApproveNode;

	public boolean issave = false;

	/**
	 * setTabPADConfigInfo:(设置当前PAD选中的). <br/>
	 * date: 2015年2月2日 <br/>
	 * 
	 * @author lxf
	 * @param currentPADConfig
	 */
	public void setTabPADConfigInfo(SuperEntity currentPADConfig) {
		currentTabPADConfigInfo = currentPADConfig;
	}

	/**
	 * setWorkEntity:(设置作业票对象). <br/>
	 * date: 2015年2月2日 <br/>
	 * 
	 * @author Administrator
	 * @param sup
	 */
	public void setWorkEntity(WorkOrder sup) {
		workOrder = sup;
	}

	/**
	 * getCurApproveNode:(获取当前环节点). <br/>
	 * date: 2015年2月12日 <br/>
	 * 
	 * @author lxf
	 * @return
	 */
	public WorkApprovalPermission getCurApproveNode() {
		return curApproveNode;
	}

	/**
	 * getWorkEntity:(设置作业票对象). <br/>
	 * date: 2015年2月2日 <br/>
	 * 
	 * @author Administrator
	 * @return
	 */
	public WorkOrder getWorkEntity() {
		return workOrder;
	}

	/**
	 * setExamineList:(设置环节点的数据). <br/>
	 * date: 2015年2月2日 <br/>
	 * 
	 * @author lxf
	 * @param list
	 */
	public void setExamineList(List list) {
		examineList = list;
		if (null != exmaineDialog) {
			exmaineDialog.setExamineList(examineList);
		}
	}

	/**
	 * getExamineList:(获取环节点数据集合). <br/>
	 * date: 2015年2月3日 <br/>
	 * 
	 * @author lxf
	 * @return
	 */
	public List<WorkApprovalPermission> getExamineList() {
		return examineList;
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		exmaineDialog = new ExamineDialog<WorkApprovalPermission>(
				getActivity(), 0);
		exmaineDialog.setWorkOrder(workOrder);
		exmaineDialog.setExamineList(examineList);
		exmaineDialog.setiEventLsn(getDialogIEventLsn());
		exmaineDialog.setFragment(this);
		busAction = new BusinessAction(new CheckControllerActionListener());
		asyTask = new BusinessAsyncTask(busAction, appCallBack);
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (exmaineDialog != null) {
			exmaineDialog.setIsClick(true);
		}
	}

	/**
	 * ShowDialogMessage:(显示dialog). <br/>
	 * date: 2015年2月2日 <br/>
	 * 
	 * @author lxf
	 * @param msg
	 *            显示内容
	 */
	public void ShowDialogMessage(String msg) {
		if (mDialog == null) {
			mDialog = new ProgressDialog(getActivity(), msg);
			mDialog.show();
		} else {
			mDialog.showMsg(msg);
			mDialog.show();
		}
	}

	/**
	 * ShowExmaineDialog:(显示环节点dialog). <br/>
	 * date: 2015年2月2日 <br/>
	 * 
	 * @author lxf
	 */
	public void ShowExmaineDialog() {
		issave = false;
		if (exmaineDialog != null && !exmaineDialog.isShowing()) {
			exmaineDialog.reFresh();
			exmaineDialog.show();
		}
	}
	
	/**
	 * reFresh:(刷新环节点). <br/>
	 * date: 2015年2月28日 <br/>
	 *
	 * @author lxf
	 */
	public void reFresh()
	{
		if(exmaineDialog!=null){
			exmaineDialog.reFresh();
		}
	}

	/**
	 * DismissDialog:(是否dialog). <br/>
	 * date: 2015年2月2日 <br/>
	 * 
	 * @author lxf
	 */
	public void DismissDialog() {
		if (mDialog != null) {
			mDialog.dismiss();
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == IEventType.READCARD_TYPE) {
			curApproveNode = (WorkApprovalPermission) data
					.getSerializableExtra(ReadCardExamineActivity.WORKAPPROVALPERMISSION);
			appSaveClick(IEventType.EXAMINE_EXAMINE_ClICK, curApproveNode);
		}
	}

	/**
	 * exmaineSave:(环节点保存动作). <br/>
	 * date: 2015年2月2日 <br/>
	 * 
	 * @author lxf
	 */
	public void exmaineSave() {
		curApproveNode = null;
		appSaveClick(0);
	};

	/**
	 * AppSaveClick:(表示审核保存方法). <br/>
	 * date: 2014年10月25日 <br/>
	 * 
	 * @author lxf
	 * @param eventType
	 * @param objects
	 * @throws HDException
	 */
	private void appSaveClick(int eventType, Object... objects) {
		Map<String, Object> mapParam = new HashMap<String, Object>();
		// 1.作业票
		mapParam.put(WorkOrder.class.getName(), workOrder);
		// 2.pda配置信息
		if (currentTabPADConfigInfo == null) {
			ToastUtils.toast(getActivity(), "请传入当前页面配置信息对象！");
			return;
		}
		mapParam.put(PDAWorkOrderInfoConfig.class.getName(),
				currentTabPADConfigInfo);
		// 3.审批环节点
		if (IEventType.EXAMINE_EXAMINE_ClICK == eventType
				&& null != curApproveNode) {
			// 审批环节点
			mapParam.put(WorkApprovalPermission.class.getName(), curApproveNode);
		}
		// 措施信息
		String key = getMeasureClassName();
		if (!StringUtils.isEmpty(key)) {
			List<SuperEntity> curChangeData = getSaveDatalist();
			if (isCheckSaveData()) {
				if (curChangeData == null || curChangeData.size() == 0) {
					ToastUtils.toast(getActivity(), "请选中要保存的数据");
					return;
				}
				mapParam.put(key, curChangeData);
			} else {
				if (curChangeData != null && curChangeData.size() > 0) {
					mapParam.put(key, curChangeData);
				}
			}
		} else {
			ToastUtils.toast(getActivity(), "请传入要保存业务数据的KEY");
			return;
		}
		//
		Map<String, Object> mapTemp = getMapParam();
		if (null != mapTemp && mapParam.size() > 0) {
			mapParam.putAll(mapTemp);
		}
		// 异步保存数据
		ShowDialogMessage("保存信息。。。");
		try {
			asyTask.execute(getIAtionType(), mapParam);
		} catch (HDException e) {
			logger.error(e);
			ToastUtils.toast(getActivity(), "保存数据失败!请联系管理员.");
		}
	}

	/**
	 * auditCallBack:TODO(审批环节的回调).
	 */
	private AbstractAsyncCallBack appCallBack = new AbstractAsyncCallBack() {

		@Override
		public void start(Bundle msgData) {
			// TODO Auto-generated method stub

		}

		@Override
		public void processing(Bundle msgData) {
			// TODO Auto-generated method stub
			String actiontype = getIAtionType();
			if (msgData.containsKey(actiontype)) {
				ShowDialogMessage(msgData.getString(actiontype));
			}
		}

		@Override
		public void error(Bundle msgData) {
			// TODO Auto-generated method stub
			String actiontype = getIAtionType();
			if (msgData.containsKey(actiontype)) {
				DismissDialog();
				ToastUtils.imgToast(getActivity(),
						R.drawable.hd_hse_common_msg_wrong,
						msgData.getString(actiontype));
			}
		}

		@Override
		public void end(Bundle msgData) {
			// TODO Auto-generated method stub
			DismissDialog();
			// refresh();
			// if (iEventListener != null) {
			// try {
			// iEventListener.eventProcess(eventTypep, objectsp);
			// } catch (HDException e) {
			// logger.error(e.getMessage());
			// ToastUtils.imgToast(getActivity(),
			// R.drawable.hd_hse_common_msg_wrong, "程序报错，请联系管理员");
			// }
			// }

			refreshViewControl(curApproveNode);
			if (curApproveNode != null) {
				issave = true;
				exmaineDialog.setCurrentEntity(curApproveNode);
			}
		}
	};

	/**
	 * TODO 转换成标准实体类型 changeToSuperEntity:(). date: 2014年10月22日
	 * 
	 * @author wenlin
	 * @param list
	 * @return
	 */
	public List<SuperEntity> changeToSuperEntity(List list) {
		List<SuperEntity> listTemp = list;
		return listTemp;
	}

	/**
	 * isCheckSaveData:(是否检查要保存的数据). <br/>
	 * date: 2015年2月28日 <br/>
	 * 
	 * @author lxf
	 * @return
	 */
	public boolean isCheckSaveData() {
		return true;
	}

	/**
	 * getIAtionType:(获取动作类型). <br/>
	 * date: 2014年11月4日 <br/>
	 * 
	 * @author lxf
	 * @return
	 */
	public abstract String getIAtionType();

	/**
	 * getMeasureClassName:(获取业务对象的包名URL). <br/>
	 * date: 2015年2月2日 <br/>
	 * 
	 * @author lxf
	 * @return
	 */
	public abstract String getMeasureClassName();

	/**
	 * getSaveDatalist:(获取措施保存数据集合). <br/>
	 * date: 2015年2月2日 <br/>
	 * 
	 * @author lxf
	 * @return
	 */
	public abstract List<SuperEntity> getSaveDatalist();

	/**
	 * getMapParam:(获取保存时的参数对象). <br/>
	 * date: 2014年11月12日 <br/>
	 * 
	 * @author lxf
	 * @return
	 */
	public abstract Map<String, Object> getMapParam();

	/**
	 * getIEventLsn:(). <br/>
	 * date: 2015年2月11日 <br/>
	 * 
	 * @author lxf
	 * @return
	 */
	public IEventListener getDialogIEventLsn() {
		return null;
	}

	/**
	 * refreshViewPage:(刷新ViewControl控件). <br/>
	 * date: 2015年2月3日 <br/>
	 * 
	 * @author lxf
	 */
	public abstract void refreshViewControl(Object... objects);

}
