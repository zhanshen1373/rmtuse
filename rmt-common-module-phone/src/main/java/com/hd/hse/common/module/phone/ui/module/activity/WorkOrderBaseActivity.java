package com.hd.hse.common.module.phone.ui.module.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import android.content.Intent;
import android.os.Bundle;

import com.hd.hse.business.action.BusinessAction;
import com.hd.hse.business.task.AbstractAsyncCallBack;
import com.hd.hse.business.task.BusinessAsyncTask;
import com.hd.hse.common.component.phone.constant.IActionBar;
import com.hd.hse.common.component.phone.custom.ProgressDialog;
import com.hd.hse.common.component.phone.event.IEventType;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.common.module.phone.R;
import com.hd.hse.common.module.phone.ui.activity.NaviFrameActivity;
import com.hd.hse.common.module.ui.model.fragment.GasesCheckFragment;
import com.hd.hse.common.module.ui.model.fragment.NaviFrameFragment;
import com.hd.hse.constant.IActionType;
import com.hd.hse.constant.IConfigEncoding;
import com.hd.hse.constant.IWorkOrderStatus;
import com.hd.hse.entity.base.HazardNotify;
import com.hd.hse.entity.base.PDAWorkOrderInfoConfig;
import com.hd.hse.entity.workorder.WorkGuardEquipment;
import com.hd.hse.entity.workorder.WorkOrder;
import com.hd.hse.service.workorder.checkrules.AbstractCheckListener;
import com.hd.hse.service.workorder.checkrules.CheckControllerActionListener;
import com.hd.hse.service.workorder.queryinfo.IQueryWorkInfo;
import com.hd.hse.service.workorder.queryinfo.QueryWorkInfo;
import com.hd.hse.utils.ServiceActivityUtils;

/**
 * ClassName: WorkOrderBaseActivity (措施、ppe、会签 显示基类窗体)<br/>
 * date: 2015年2月6日 <br/>
 * 
 * @version
 */
public class WorkOrderBaseActivity extends NaviFrameActivity {

	private static Logger logger = LogUtils
			.getLogger(WorkOrderBaseActivity.class);
	/**
	 * 作业票参数
	 */
	public static final String WORK_ORDER = "workOrder";
	/**
	 * quertWorkInfo:TODO(作业票查询服务).
	 */
	private IQueryWorkInfo quertWorkInfo = new QueryWorkInfo();
	/**
	 * 作业票
	 */
	public WorkOrder workOrder;

	/**
	 * preStatus:TODO(初次加载的票状态).
	 */
	private String preStatus;
	/**
	 * preYqCount:TODO(初始已延期次数).
	 */
	private int preYyqCount;
	/**
	 * listPDAConfigInfo:TODO().
	 */
	public List<SuperEntity> listPDAConfigInfo = null;

	/**
	 * priviousNaviTouchEntity:TODO(左侧导航栏记录触摸前一个实体).
	 */
	public PDAWorkOrderInfoConfig priviousNaviTouchEntity;

	/**
	 * action:TODO(后台业务处理).
	 */
	public BusinessAction action;

	/**
	 * actLsr:TODO(后台业务处理).
	 */
	public AbstractCheckListener actLsr;

	/**
	 * asyTask:TODO(异步任务).
	 */
	public BusinessAsyncTask asyTask;

	/**
	 * prgDialog:TODO(耗时等待).
	 */
	public ProgressDialog prgDialog;

	/**
	 * iEnventListener:TODO(设置事件).
	 */
	public IEventListener getiEnventListener() {
		return null;
	}

	public WorkOrder getWorkOrder() {
		return workOrder;
	}

	public List<SuperEntity> getListPDAConfigInfo() {
		return listPDAConfigInfo;
	}

	/**
	 * title:TODO(详细页面标题).
	 */
	private String title = "";
	/*********** 资源ID **************/
	// 危害识别
	private static final int[] HARM_DIST = new int[] {
			R.drawable.hd_hse_common_nav_harm_dist_normal,
			R.drawable.hd_hse_common_nav_harm_dist_finished,
			R.drawable.hd_hse_common_nav_harm_dist_selected };
	// 措施确认
	private static final int[] STEP_CHECK = new int[] {
			R.drawable.hd_hse_common_nav_step_check_normal,
			R.drawable.hd_hse_common_nav_step_check_finished,
			R.drawable.hd_hse_common_nav_step_check_selected };
	// 个人防护
	private static final int[] NAV_PROTECT = new int[] {
			R.drawable.hd_hse_common_nav_protect_normal,
			R.drawable.hd_hse_common_nav_protect_finished,
			R.drawable.hd_hse_common_nav_protect_selected };
	// 气体检测
	private static final int[] GAS_CHECK = new int[] {
			R.drawable.hd_hse_common_nav_gas_check_normal,
			R.drawable.hd_hse_common_nav_gas_check_finished,
			R.drawable.hd_hse_common_nav_gas_check_selected };
	// 签发
	private static final int[] NAV_SIGN = new int[] {
			R.drawable.hd_hse_common_nav_sign_normal,
			R.drawable.hd_hse_common_nav_sign_finished,
			R.drawable.hd_hse_common_nav_sign_selected };

	// 延期
	private static final int[] NAV_YQ = new int[] {
			R.drawable.hd_hse_common_nav_yq_normal,
			R.drawable.hd_hse_common_nav_yq_finished,
			R.drawable.hd_hse_common_nav_yq_selected };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		try {
			super.onCreate(savedInstanceState);
			initParam();
			initView();
		} catch (Exception e) {
			logger.error(e);
			ToastUtils.toast(this, "加载报错,请联系管理员!");
		}
	}

	@Override
	public void finish() {
		try {
			workOrder = quertWorkInfo.queryWorkInfo(workOrder, null);
			// 校验作业票最新状态
			if (!preStatus.equals(workOrder.getStatus())
					|| (preYyqCount != workOrder.getYyqcount() && workOrder
							.getYyqcount() >= workOrder.getYqcount())) {
				setResult(RESULT_OK);
			} else {
				setResult(RESULT_CANCELED);
			}
		} catch (HDException e) {
			logger.error(e.getMessage(), e);
		}
		super.finish();
	}

	/**
	 * initParam:(初始化对象). <br/>
	 * date: 2015年2月6日 <br/>
	 * 
	 * @author Administrator
	 */
	private void initParam() {
		// TODO Auto-generated method stub
		Intent intent = this.getIntent();
		workOrder = (WorkOrder) intent.getSerializableExtra(WORK_ORDER);
		// 作业票类型
		title = workOrder.getZypclassname();
		// 初次加载的票状态
		preStatus = workOrder.getStatus();
		preYyqCount = workOrder.getYyqcount();
		// 导航栏配置信息
		listPDAConfigInfo = workOrder.getChild(PDAWorkOrderInfoConfig.class
				.getName());
		if (null == listPDAConfigInfo || listPDAConfigInfo.size() == 0) {
			// 表示没有配置显示界面信息.
			ToastUtils.toast(this, "没有配置要操作的界面功能。");
		}
		actLsr = new CheckControllerActionListener();
		action = new BusinessAction(actLsr);
		asyTask = new BusinessAsyncTask(action, asyCallBack);
	}

	/**
	 * initView:(初始化关联布局). <br/>
	 * date: 2015年1月21日 <br/>
	 * 
	 * @author wenlin
	 * @throws Exception
	 */
	private void initView() throws Exception {
		// 初始化导航栏
		setCustomActionBar(eventLst, new String[] { IActionBar.TV_BACK,
				IActionBar.TV_TITLE, IActionBar.IBTN_LEVELTWO_MORE });
		// 设置菜单栏信息
		setCustomMenuBarInfo();
		// 设置导航栏标题
		setActionBartitleContent(title, true);
		// 设置作业票详细信息弹出框
		setPopDetailWorkerOrer(workOrder);

		// 设置当前PDAWorkOrderInfoConfig
		if (listPDAConfigInfo != null && listPDAConfigInfo.size() > 0) {
			setNaviData(loadNaviiCon(listPDAConfigInfo));
		}
		// 设置查询对象
		setQueryWorkInfo(quertWorkInfo);
		// 设置作业票信息
		setWorkInfo(workOrder);

		setNaviItemOnClickListener(eventLst);
		// 初始化加载默认第一个PDA配置页面条目
		getFragmentByPDAConfig((PDAWorkOrderInfoConfig) getNaviCurrentEntity());

		// 执行子类特殊的业务
		distribute();
	}

	/**
	 * distribute:(分发到子类中去). <br/>
	 * date: 2015年3月3日 <br/>
	 * 
	 * @author zhaofeng
	 */
	public void distribute() {

	}

	/**
	 * setCustomMenuBarInfo:(设置自定义菜单来信息). <br/>
	 * date: 2015年2月9日 <br/>
	 * 
	 * @author lxf
	 */
	public void setCustomMenuBarInfo() {
		setCustomMenuBar(new String[] { IActionBar.ITEM_PHOTOGTAPH,
				IActionBar.ITEM_PHOTOMANAGER });
	}

	private IEventListener eventLst = new IEventListener() {

		@Override
		public void eventProcess(int eventType, Object... arg1)
				throws HDException {
			if (getiEnventListener() != null) {
				getiEnventListener().eventProcess(eventType, arg1);
			}
			if (IEventType.NAVIGATION_ITME_SINGLE_CLICK == eventType) {
				// 之前校验
				if ((Boolean) beforeValidate(eventType, workOrder,
						(PDAWorkOrderInfoConfig) arg1[0])) {
					// 切换fragment
					getFragmentByPDAConfig((PDAWorkOrderInfoConfig) arg1[0]);
				} else {
					// priviousNaviTouchEntity = (PDAWorkOrderInfoConfig)
					// arg1[0];
					setNaviCurrentEntity(priviousNaviTouchEntity);
				}
			}
		}
	};

	/**
	 * TODO 加载navi导航图标 loadNaviCon:(). <br/>
	 * date: 2015年1月27日 <br/>
	 * 
	 * @author wenlin
	 * @return
	 * @throws Exception
	 */
	private List<SuperEntity> loadNaviiCon(List<SuperEntity> listPDAConfigInfo)
			throws Exception {

		for (int i = 0; i < listPDAConfigInfo.size(); i++) {
			SuperEntity entity = listPDAConfigInfo.get(i);
			// 页面类型
			Integer contype = (Integer) entity.getAttribute("contype");
			if (contype != null) {
				switch (contype) {
				case IConfigEncoding.HARM_TYPE:
					entity.setAttribute("naviIconId", HARM_DIST);
					break;

				case IConfigEncoding.MEASURE_TYPE:
					entity.setAttribute("naviIconId", STEP_CHECK);
					break;

				case IConfigEncoding.PPE_TYPE:
					entity.setAttribute("naviIconId", NAV_PROTECT);
					break;
				case IConfigEncoding.GAS_TYPE:
					entity.setAttribute("naviIconId", GAS_CHECK);
					break;
				case IConfigEncoding.SIGN_TYPE:
					entity.setAttribute("naviIconId", NAV_SIGN);
					break;
				case IConfigEncoding.YQ_SIGN_TYPE:
					entity.setAttribute("naviIconId", NAV_YQ);
					break;
				default:
					Object obj = entity.getAttribute("contypedesc");
					String msg = "不支持" + obj != null ? obj.toString() : ""
							+ " 配置类型：" + contype.toString();
					throw new Exception(msg);
				}
			}
		}
		return listPDAConfigInfo;

	}

	/**
	 * resultClz:TODO(点击navi目标跳转的fragment类).
	 */
	public Class<? extends NaviFrameFragment> resultClz;
	private HashMap<Object, Object> hasmapTab = new HashMap<Object, Object>();
	@SuppressWarnings("unused")
	private NaviFrameFragment currentFragment = null;

	/**
	 * getFragmentByPDAConfig:( * 根据 PDAWorkOrderInfoConfig 配置信息确定相应的
	 * Fragment字节码， 用于传递给 NaviFrameActivity进行切换。 getFragmentByPDAConfig:(). <br/>
	 * ). <br/>
	 * date: 2015年2月6日 <br/>
	 * 
	 * @author Administrator
	 * @param pdaConfig
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Class<? extends NaviFrameFragment> getFragmentByPDAConfig(
			PDAWorkOrderInfoConfig pdaConfig) {
		Integer contype = pdaConfig.getContype();
		List<Object> dataListTemp = new ArrayList<Object>();
		dataListTemp.add(workOrder);
		if (contype == IConfigEncoding.HARM_TYPE
				|| contype == IConfigEncoding.PPE_TYPE
				|| contype == IConfigEncoding.GAS_TYPE) {
			dataListTemp.add(pdaConfig);
		}
		resultClz = getNavFragmentClass(contype);
		if (resultClz == null) {
			return null;
		}
		try {
			switch (contype) {
			case IConfigEncoding.HARM_TYPE:
				// 切换到危害的Fragment
				List<SuperEntity> getlistSuperHarm = null;
				if (!hasmapTab.containsKey(contype)) {
					getlistSuperHarm = workOrder.getChild(HazardNotify.class
							.getName());
					if (null == getlistSuperHarm) {
						// 取读数据库数据
						getlistSuperHarm = changeToSuperEntity((List) quertWorkInfo
								.queryHarmInfo(workOrder, null));
					}
					hasmapTab.put(contype, getlistSuperHarm);
				} else {
					getlistSuperHarm = (List<SuperEntity>) hasmapTab
							.get(contype);
				}
				dataListTemp.add(getlistSuperHarm);
				dataListTemp.add(getListAppInfo(contype));
				break;
			case IConfigEncoding.ENERGY_TYPE:
				// resultClz = 把对应的 Fragment字节码填上
				break;
			case IConfigEncoding.GAS_TYPE:
				resultClz = GasesCheckFragment.class;
				break;
			case IConfigEncoding.MEASURE_TYPE:
				List<SuperEntity> getlistSuper = null;
				if (!hasmapTab.containsKey(contype)) {
					getlistSuper = pdaConfig
							.getChild(PDAWorkOrderInfoConfig.class.getName());
					hasmapTab.put(contype, getlistSuper);
				} else {
					getlistSuper = (List<SuperEntity>) hasmapTab.get(contype);
				}
				dataListTemp.add(getlistSuper);
				// switchContentView(resultClz, dataListTemp);
				break;
			case IConfigEncoding.PPE_TYPE:
				List<SuperEntity> getlistSuperPPE = null;
				if (!hasmapTab.containsKey(contype)) {
					// 设置个人防
					getlistSuperPPE = workOrder
							.getChild(WorkGuardEquipment.class.getName());
					if (null == getlistSuperPPE) {
						// 取读数据库数据
						getlistSuperPPE = changeToSuperEntity((List) quertWorkInfo
								.queryPpeInfo(workOrder, null));
					}
					// 设置审批环节内容
					hasmapTab.put(contype, getlistSuperPPE);
				} else {
					getlistSuperPPE = (List<SuperEntity>) hasmapTab
							.get(contype);
				}
				dataListTemp.add(getlistSuperPPE);
				dataListTemp.add(getListAppInfo(contype));
				break;
			case IConfigEncoding.SIGN_TYPE:
				// 会签权限校验
				validateAuthority(IActionType.ACTION_TYPE_CANSIGN);
				// canSign(resultClz);
				break;
			case IConfigEncoding.YQ_SIGN_TYPE:
				// resultClz =
				dataListTemp.add(pdaConfig);
				break;
			default:
				validate(contype, workOrder, listPDAConfigInfo);
			}
		} catch (HDException e) {
			logger.error(e.getMessage(), e);
		}
		if (contype != IConfigEncoding.SIGN_TYPE) {
			switchContentView(resultClz, dataListTemp);
		}
		currentFragment = getFragmentByClass(resultClz);
		// 当前点击NAVI实体不是会签
		if (contype != IConfigEncoding.SIGN_TYPE) {
			// 缓存pda实体
			priviousNaviTouchEntity = pdaConfig;
		}
		return resultClz;
	}

	/**
	 * TODO 校验会签权限 validateAuthority:(). <br/>
	 * date: 2015年2月2日 <br/>
	 * 
	 * @author wenlin
	 */
	private void validateAuthority(String actionType) {
		HashMap<String, Object> paras = new HashMap<String, Object>();
		paras.put(WorkOrder.class.getName(), workOrder);
		paras.put(PDAWorkOrderInfoConfig.class.getName(), listPDAConfigInfo);
		actionShowDialog("校验会签权限...", IActionType.ACTION_TYPE_CANSIGN, paras);
	}

	/**
	 * validate:(验证满足条件). <br/>
	 * date: 2015年2月9日 <br/>
	 * 
	 * @author lxf
	 * @param objects
	 */
	public void validate(Object... objects) {
	}

	/**
	 * beforeValidate:(在页签切换前检验). <br/>
	 * date: 2015年3月6日 <br/>
	 * 
	 * @author zhaofeng
	 * @param objects
	 * @return
	 * @throws HDException
	 */
	public Object beforeValidate(Object... objects) throws HDException {
		return Boolean.TRUE;
	}

	/**
	 * setNaviBarIsCompLete:(设置当前导航完成标记). <br/>
	 * date: 2015年1月26日 <br/>
	 * 
	 * @author wenlin
	 */
	public void setNaviBarIsCompLete() {
		try {
			// 设置PDA配置实体中完成标记
			SuperEntity currentNaviTouchEntity = ServiceActivityUtils
					.getRefreshWorkConfigInfo(workOrder,
							(PDAWorkOrderInfoConfig) getNaviCurrentEntity());
			// 设置功能导航状态
			setNaviFinish(currentNaviTouchEntity);
		} catch (HDException e) {
			logger.error(e.getMessage());
			ToastUtils.imgToast(this, R.drawable.hd_hse_common_msg_wrong,
					e.getMessage());
		}
	}

	/**
	 * getNavFragmentClass:(获得导航栏的fragment的类). <br/>
	 * date: 2015年2月6日 <br/>
	 * 
	 * @author lxf
	 * @return
	 */
	public Class<? extends NaviFrameFragment> getNavFragmentClass(
			Integer contype) {
		return null;
	}

	/**
	 * TODO 获取审批环节信息 getListAppInfo:(). <br/>
	 * date: 2015年1月30日 <br/>
	 * 
	 * @author wenlin
	 * @param controlType
	 * @return
	 * @throws HDException
	 */
	public List<SuperEntity> getListAppInfo(int controlType) throws HDException {
		List<SuperEntity> listAppinfo = null;
		SuperEntity sup = null;
		for (int i = 0; i < listPDAConfigInfo.size(); i++) {
			if (getSuperControlType(listPDAConfigInfo.get(i)) == controlType) {
				sup = (SuperEntity) listPDAConfigInfo.get(i)
						.getChild(PDAWorkOrderInfoConfig.class.getName())
						.get(0);
				break;
			}
		}
		if (null != sup) {
			listAppinfo = changeToSuperEntity((List) quertWorkInfo
					.queryApprovalPermission(workOrder, sup, null, null));
		}
		return listAppinfo;
	}

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
	 * TODO 会签fragment初始化 canSign:(). <br/>
	 * date: 2015年1月27日 <br/>
	 * 
	 * @author wenlin
	 * @throws HDException
	 */
	@SuppressWarnings("unchecked")
	public void canSign(Class<? extends NaviFrameFragment> resultClz)
			throws HDException {
		List<SuperEntity> lstAPP = null;
		if (!hasmapTab.containsKey(IConfigEncoding.SIGN_TYPE)) {
			try {
				lstAPP = getListAppInfo(IConfigEncoding.SIGN_TYPE);
			} catch (HDException e) {
				logger.error(e.getMessage(), e);
			}
			hasmapTab.put(IConfigEncoding.SIGN_TYPE, lstAPP);
		} else {
			lstAPP = (List<SuperEntity>) hasmapTab
					.get(IConfigEncoding.SIGN_TYPE);
		}
		List<Object> data = new ArrayList<Object>();
		data.add(lstAPP);
		data.add(workOrder);
		switchContentView(resultClz, data);
	}

	/**
	 * getSuperControlType:(获取控件类型). <br/>
	 * date: 2014年10月23日 <br/>
	 * 
	 * @author lxf
	 * @param sup
	 * @return
	 */
	public int getSuperControlType(SuperEntity sup) {
		int ret = -2;
		if (null != sup) {
			ret = (int) sup.getAttribute("contype");
		}
		return ret;
	}

	public void actionShowDialog(String msg, String actiontype, Object... param) {
		// if(prgDialog==null){
		prgDialog = new ProgressDialog(WorkOrderBaseActivity.this, msg);
		prgDialog.show();
		try {
			asyTask.execute(actiontype, param);
		} catch (HDException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * TODO 异步成功后操作（子类覆写业务） successActionCallback:(). <br/>
	 * date: 2015年3月9日 <br/>
	 * 
	 * @author wenlin
	 * @param msgData
	 */
	public void successActionCallback(Bundle msgData) {

	}

	/**
	 * actionShowDialogDissmiss:(释放进度条). <br/>
	 * date: 2015年2月9日 <br/>
	 * 
	 * @author lxf
	 */
	public void actionShowDialogDissmiss() {
		if (prgDialog != null) {
			prgDialog.dismiss();
		}
	}

	private AbstractAsyncCallBack asyCallBack = new AbstractAsyncCallBack() {

		@Override
		public void start(Bundle msgData) {
			// TODO Auto-generated method stub
		}

		@Override
		public void processing(Bundle msgData) {
			// TODO Auto-generated method stub

		}

		@Override
		public void error(Bundle msgData) {
			prgDialog.dismiss();
			// 退回
			if (msgData.containsKey(IActionType.ACTION_TYPE_RETURN)) {
				ToastUtils.imgToast(WorkOrderBaseActivity.this,
						R.drawable.hd_hse_common_msg_wrong,
						msgData.getString(IActionType.ACTION_TYPE_RETURN));
			}
			if (msgData.containsKey(IActionType.ACTION_TYPE_CANNULLIFY)) {
				ToastUtils.imgToast(WorkOrderBaseActivity.this,
						R.drawable.hd_hse_common_msg_wrong,
						msgData.getString(IActionType.ACTION_TYPE_CANNULLIFY));
			}
			// 会签
			if (msgData.containsKey(IActionType.ACTION_TYPE_CANSIGN)) {
				ToastUtils.imgToast(WorkOrderBaseActivity.this,
						R.drawable.hd_hse_common_msg_wrong,
						msgData.getString(IActionType.ACTION_TYPE_CANSIGN));
				try {
					// NAVI切回之前的页面
					setNaviCurrentEntity(priviousNaviTouchEntity);
					// 切回原fragment
					getFragmentByPDAConfig((PDAWorkOrderInfoConfig) priviousNaviTouchEntity);

				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		}

		@Override
		public void end(Bundle msgData) {
			prgDialog.dismiss();

			successActionCallback(msgData);

			if (msgData.containsKey(IActionType.ACTION_TYPE_RETURN)) {
				ToastUtils.imgToast(WorkOrderBaseActivity.this,
						R.drawable.hd_hse_common_msg_right,
						msgData.getString(IActionType.ACTION_TYPE_RETURN));
			}
			// 会签权限校验完毕
			if (msgData.containsKey(IActionType.ACTION_TYPE_CANSIGN)) {
				try {
					// 切换fragment
					canSign(resultClz);
				} catch (HDException e) {
					logger.error(e.getMessage(), e);
					ToastUtils.imgToast(WorkOrderBaseActivity.this,
							R.drawable.hd_hse_common_msg_wrong,
							msgData.getString(IActionType.ACTION_TYPE_CANSIGN));
				}
			}
		}
	};
	/**
	 * 重写系统返回键监听（屏蔽系统返回键功能）
	 */
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
		return false;
	};

}
