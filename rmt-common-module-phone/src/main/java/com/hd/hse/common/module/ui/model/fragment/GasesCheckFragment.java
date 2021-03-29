/**
 * Project Name:hse-common-module-phone
 * File Name:GasesCheckFragment.java
 * Package Name:com.hd.hse.common.module.ui.model.fragment
 * Date:2015年1月19日
 * Copyright (c) 2015, xuxinwen@ushayden.com All Rights Reserved.
 *
 */

package com.hd.hse.common.module.ui.model.fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TimeUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.hd.hse.business.action.BusinessAction;
import com.hd.hse.business.task.AbstractAsyncCallBack;
import com.hd.hse.business.task.BusinessAsyncTask;
import com.hd.hse.business.task.IMessageWhat;
import com.hd.hse.common.component.phone.adapter.TableContentPagerAdapter;
import com.hd.hse.common.component.phone.adapter.TableTitleAdapter;
import com.hd.hse.common.component.phone.custom.ProgressDialog;
import com.hd.hse.common.component.phone.event.IEventType;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.component.phone.util.DateTimePickDialogUtil;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.component.phone.util.ViewMeasureUtils;
import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.common.module.phone.R;
import com.hd.hse.common.module.phone.custom.ExamineListView;
import com.hd.hse.common.module.phone.ui.activity.NaviFrameActivity;
import com.hd.hse.common.module.phone.ui.activity.ReadCardExamineActivity;
import com.hd.hse.constant.IActionType;
import com.hd.hse.constant.IConfigEncoding;
import com.hd.hse.constant.IRelativeEncoding;
import com.hd.hse.constant.ITableName;
import com.hd.hse.constant.IWorkOrderStatus;
import com.hd.hse.entity.base.Domain;
import com.hd.hse.entity.base.GasDetectSub;
import com.hd.hse.entity.base.GasDetection;
import com.hd.hse.entity.base.PDAWorkOrderInfoConfig;
import com.hd.hse.entity.base.RelationTableName;
import com.hd.hse.entity.workorder.WorkApprovalPermission;
import com.hd.hse.entity.workorder.WorkApprovalPersonRecord;
import com.hd.hse.entity.workorder.WorkOrder;
import com.hd.hse.service.config.IQueryRelativeConfig;
import com.hd.hse.service.config.QueryRelativeConfig;
import com.hd.hse.service.workorder.checkrules.CheckControllerActionListener;
import com.hd.hse.service.workorder.queryinfo.IQueryCallEventListener;
import com.hd.hse.service.workorder.queryinfo.IQueryWorkInfo;
import com.hd.hse.service.workorder.queryinfo.QueryWorkInfo;
import com.hd.hse.system.SystemProperty;

/**
 * ClassName:GasesCheckFragment ().<br/>
 * Date: 2015年1月19日 <br/>
 * 
 * @author xuxinwen
 * @version
 * @see
 */
public class GasesCheckFragment extends NaviFrameFragment {

	private static Logger logger = LogUtils.getLogger(GasesCheckFragment.class);

	private final String TAG = this.getClass().getSimpleName();
	private final boolean DEBUG = true;

	// 时间的格式
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final int QUERY_APPROVAL_TYPE_ADD = 0;
	private static final int QUERY_APPROVAL_TYPE_UNDONE = 1;
	
	boolean isCheckTaskRunning = false;

//	private long mLastTime = 0;
	
	private SimpleDateFormat mDataFormat = new SimpleDateFormat(DATE_FORMAT,
			Locale.CHINA);
	
	private long convertStringTime2Long(String time){
		try {
			return mDataFormat.parse(time).getTime();
		} catch (ParseException e) {
			logger.error(e);
			return 0;
		}
	}
	
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				ArrayList<Map<String, String>> _data = (ArrayList<Map<String, String>>) msg.obj;
				
				long _lastTime = 0;
			
				// 找出作业票中时间最晚的
				for (Map<String, String> item : _data) {
					String _time = item.get("检测时间");

					long _currTime = convertStringTime2Long(_time);
					if (_currTime > _lastTime) {
						_lastTime = _currTime;
					}

				}
				
//				mLastTime = _lastTime;
				
				break;

			default:
				break;
			}
		};
	};
	
	
	// 存储 气体浓度类型和值的 控件
	private List<GaseViewPairs> gaseDetectRowHolder = new ArrayList<GaseViewPairs>();
	
	// 气体检测信息。
	private GasDetection mDataToSave;

	// 新增要用的气体检测信息
	private GasDetection mDataToAdd;

	// 气体检测信息。
	private GasDetection mDataToDisplay;
	
	// 历史记录表头。
	private ArrayList<String> mDataHistoryHeaders = new ArrayList<String>();
	
	
	// 检测方式
	private List<SuperEntity> dataDetectMethodList;
	
	// 气体浓度的 信息
	private List<List<SuperEntity>> dataGasesListList;
	
	@Override
	public void refreshData() {
		// TODO 查询历史记录。
		queryHistory();
	}

	@Override
	protected View initView(LayoutInflater inflater, ViewGroup container) {
		if (DEBUG) Log.w(TAG, "initView");
		
		View _view = inflater.inflate(
				R.layout.hd_hse_common_module_phone_frag_gases_check, null);

		 getActivity().getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
		
		findViewById(_view);

		btn_check_time.setOnClickListener(new ButtonsOnClickListener());
		btn_add.setOnClickListener(new ButtonsOnClickListener());

		return _view;
	}
	
	/**
	 * 通用的 初始化 方法 init:(). <br/>
	 * date: 2014年10月27日 <br/>
	 * 
	 * @author xuxinwen
	 */
	protected void init() {
		if (DEBUG) Log.w(TAG, "init");
		
		
		instantiation();

		queryUndone();

		ctrlSyncBtnVisiblity();
	}

	// 新增数据
	private TextView btn_add;

	private TextView btn_sync;

	// 用于控制显示隐藏。
	private LinearLayout ll_control_visible;
	// 用于动态增加气体检测条目
	private TableLayout tl_modify_gases_item;
	// 设置检测位置
	private TextView tv_check_location;
	// 设置检测时间
	private Button btn_check_time;
	// 设置检测方式
	private Spinner spinner_check_pattern;
	// 设置检测单位
	private Button btn_check_company;
	// 气体检查
	private ExamineListView examinelv_examine;
	// 检测浓度第一行，
	private Spinner spinner_row1_gase_type;
	private EditText et_row1_gase_value;

	// 历史记录表头
	private ListView gHistoryTitle;
	
	// 历史记录内容
	private ViewPager gHistoryContent;
	
	// 历史记录 Indictor
	private RadioGroup gHistoryRadioGroup;
	
	private TextView tv_check_isok;

	private DisplayMetrics mDisplayMetrics = new DisplayMetrics();
	
	private void findViewById(View view) {
		
		
		btn_sync = (TextView) view
				.findViewById(R.id.hd_hse_common_module_gases_check_sync_btn);

		ll_control_visible = (LinearLayout) view
				.findViewById(R.id.hd_hse_common_module_gases_check_typein_ll);
		tl_modify_gases_item = (TableLayout) view
				.findViewById(R.id.hd_hse_common_module_gases_check_table_tl);
		tv_check_location = (TextView) view
				.findViewById(R.id.hd_hse_common_module_gases_check_location_tv);
		btn_check_time = (Button) view
				.findViewById(R.id.hd_hse_common_module_gases_check_time_btn);
		spinner_check_pattern = (Spinner) view
				.findViewById(R.id.hd_hse_common_module_gases_check_pattern_spinner);
		btn_check_company = (Button) view
				.findViewById(R.id.hd_hse_common_module_gases_check_company_btn);
		examinelv_examine = (ExamineListView) view
				.findViewById(R.id.hd_hse_common_module_gases_check_examine_examinelv);
		btn_add = (TextView) view
				.findViewById(R.id.hd_hse_common_module_gases_check_add_btn);
		spinner_row1_gase_type = (Spinner) view
				.findViewById(R.id.hd_hse_common_module_gases_check_concentration_row1_spinner);

		et_row1_gase_value = (EditText) view
				.findViewById(R.id.hd_hse_common_module_gases_check_concentration_row1_value_et);

		tv_check_isok = (TextView) view
				.findViewById(R.id.hd_hse_common_module_gases_check_isok_tv);
		
		gHistoryContent = (ViewPager) view.findViewById(R.id.hd_hse_common_module_table_content);
		
		gHistoryTitle = (ListView) view.findViewById(R.id.hd_hse_common_module_table_title);
		gHistoryTitle.setEnabled(false);
		gHistoryTitle.setDivider(getResources().getDrawable(R.drawable.divider_10));
		
		// 设置 历史记录的 Title 为屏幕尺寸的 1/3
		gHistoryTitle.getLayoutParams().width = mDisplayMetrics.widthPixels/3;
		
		gHistoryRadioGroup = (RadioGroup) view.findViewById(R.id.hd_hse_common_module_radio_group);
		
	}

	class ButtonsOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			int id = v.getId();
			if (id == R.id.hd_hse_common_module_gases_check_time_btn) {
				// 检测时间
				btnTimeOnClick(v);
			} else if (id == R.id.hd_hse_common_module_gases_check_add_btn) {
				// 增加气体检测 按钮的点击事件
				btnAddNewOnClick();
			} else if (id == R.id.hd_hse_common_module_gases_check_sync_btn) {
				btnSyncOnClick();
			}
		}

	}

	
	/**
	 * 设置 气体检测控件 是否可用。
	 * 
	 * setViewsEnabled:(). <br/>
	 * date: 2014年10月21日 <br/>
	 * 
	 * @author xuxinwen
	 * @param enabled
	 */
	private void setTopUIEnabled(boolean enabled) {

		if (et_row1_gase_value.isEnabled() == enabled) {
			return;
		}

		btn_check_time.setEnabled(enabled);
		spinner_check_pattern.setEnabled(enabled);

		spinner_row1_gase_type.setEnabled(enabled);
		et_row1_gase_value.setEnabled(enabled);

		for (GaseViewPairs item : gaseDetectRowHolder) {
			item.type.setEnabled(enabled);
			item.value.setEnabled(enabled);
		}
	}
	
	/**
	 * 重置气体检测界面的数据。 resetGaseDetect:(). <br/>
	 * date: 2014年10月21日 <br/>
	 * 
	 * @author xuxinwen
	 */
	private void resetGaseDetect() {
		setTopUIEnabled(true);

		// 将时间设置为当前时间。
		btn_check_time.setText(SystemProperty.getSystemProperty()
				.getSysDateTime());

		// 检测单位
		btn_check_company.setText("");

		et_row1_gase_value.setText("");

		tv_check_isok.setText("");

		for (GaseViewPairs item : gaseDetectRowHolder) {
			item.value.setText("");
		}
	}
	
	// 查询数据的 工具类。
	private IQueryWorkInfo queryWorkInfo;

	private void btnAddNewOnClick() {
		// 用来保存新增的录入数据，而不是直接在 查询出来的数据集中更改。

		boolean _isRun = true;

		// 判断部分代码
		String _psCode = mPDAWorkOrderInfoConfig.getPscode();
		if (IConfigEncoding.SP.equals(_psCode)) {
			// 现场核查 部分

			try {
				WorkOrder _workOrder = queryWorkInfo.queryWorkInfo(mWorkOrder,
						null);

				if (IWorkOrderStatus.INPRG.equals(_workOrder.getStatus())
						|| IWorkOrderStatus.APPAUDITED.equals(_workOrder.getStatus())) {
					_isRun = true;
				} else {
					_isRun = false;
				}
			} catch (HDException e) {
				logger.error(e.getMessage(), e);
				ToastUtils.imgToast(getActivity(),
						R.drawable.hd_common_message_error, "查询作业票失败，请联系管理员！");

				return;
			}

		} else if (IConfigEncoding.FC.equals(_psCode)) {
			// 复查部分
			_isRun = true;
		} else {
			// 不知道是哪个部分，直接返回。
			return;
		}

		// 执行代码。
		if (_isRun) {
			mDataToSave = new GasDetection();

			if (mDataToAdd != null) {
				/*
				 * 已经获取过新增的显示数据。
				 */
				resetGaseDetect();
				queryApproval(QUERY_APPROVAL_TYPE_ADD);
				// setTopUIData(QUERY_APPROVAL_TYPE_ADD, mDataToAdd);
			} else {
				/*
				 * 还没获得过新增的数据。
				 */

				// 1、调用查询类查询气体检测部分信息。
				try {
					queryWorkInfo
							.queryGasInfo(
									mWorkOrder,
									new IQueryCallEventListenerImpl(
											IQueryCallEventListenerImpl.TYPE_GASE_INFO));
				} catch (HDException e) {
					logger.error(e.getMessage(), e);
				}
			}

		} else {
			ToastUtils.toast(getActivity(), "非审批中，作业不允许气体检测！！！");
		}

	}

	@Override
	public void onResume() {
		super.onResume();
		/*try {
			if (super_eventType != null && super_objects != null) {
				swipingCard(super_eventType, super_objects);

			}

		} catch (HDException e) {
			ToastUtils.imgToast(this.getActivity(),
					R.drawable.hd_hse_common_msg_wrong, e.getMessage());
		}*/

		restoreValues();

	}
	
	@Override
	public void onPause() {
		super.onPause();
		cacheValues();
	}
	
	private void cacheValues() {
		if (gaseDetectRowHolder == null) {
			return;
		}

		int _size = gaseDetectRowHolder.size();
		GaseViewPairs _gaseViewPairs = null;

		for (int i = 0; i < _size; i++) {
			_gaseViewPairs = gaseDetectRowHolder.get(i);
			_gaseViewPairs.selectPosition = _gaseViewPairs.type
					.getSelectedItemPosition();
			_gaseViewPairs.textValue = _gaseViewPairs.value.getText()
					.toString();
		}
	}

	
	private void restoreValues() {
		if (gaseDetectRowHolder == null) {
			return;
		}

		int _size = gaseDetectRowHolder.size();
		GaseViewPairs _gaseViewPairs = null;

		for (int i = 0; i < _size; i++) {
			_gaseViewPairs = gaseDetectRowHolder.get(i);
			_gaseViewPairs.type.setSelection(_gaseViewPairs.selectPosition);
			_gaseViewPairs.value.setText(_gaseViewPairs.textValue);
		}
	}
	
	/**
	 * 查询 审批环节点。 queryApproval:(). <br/>
	 * date: 2014年10月24日 <br/>
	 * 
	 * @author xuxinwen
	 */
	private void queryApproval(int type) {
		try {
			/*
			 * 此处应该根据当前获得的检测信息，得到相应的环节点信息。
			 */
			WorkApprovalPersonRecord _queryPersonCard = new WorkApprovalPersonRecord();

			switch (type) {
			case QUERY_APPROVAL_TYPE_ADD:
				// 新增

				break;
			case QUERY_APPROVAL_TYPE_UNDONE:
				// 未完成。
				_queryPersonCard.setTablename(ITableName.UD_ZYXK_QTJC);
				_queryPersonCard.setTableid(mDataToSave.getUd_zyxk_qtjcid());
				break;

			default:
				break;
			}

			queryWorkInfo.queryApprovalPermission(mWorkOrder,
					mPDAWorkOrderInfoConfig, _queryPersonCard,
					new IQueryCallEventListenerImpl(
							IQueryCallEventListenerImpl.TYPE_APPROVAL));
			
		} catch (HDException e) {
			logger.error(e.getMessage(), e);

		}
	}
	
	
	private IEventListener mSyncPCData;

	public void setSyncPCDataCallBack(IEventListener cb) {
		mSyncPCData = cb;
	}

	public void btnSyncOnClick() {
		// 与 PC 段同步的 代码。
		try {
			if (null != mSyncPCData) {
				mSyncPCData.eventProcess(IEventType.ACTION_QTJC_SYNCHRONOUS,
						mWorkOrder, GasesCheckFragment.this);
			}
		} catch (HDException e) {
			logger.error(e);
		}
	}

	/**
	 * 设置时间的 Button 点击事件。 btnTimeOnClick:(). <br/>
	 * date: 2014年10月21日 <br/>
	 * 
	 * @author xuxinwen
	 * @param v
	 */
	private void btnTimeOnClick(View v) {
		String str = ((TextView)v).getText().toString();
		
		DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
				getActivity(), str);
		dateTimePicKDialog.dateTimePicKDialog(((TextView)v));
		// 得到现在的时间
//		String dateStr = ((Button) v).getText().toString();
//		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
//		Date date = null;
//		try {
//			date = dateFormat.parse(dateStr);
//			v.setTag(date);
//		} catch (ParseException e) {
//			// TODO
//		}
//
//		TimePicker picker = new TimePicker(getActivity());
//		picker.setIs24HourView(true);
//		picker.setCurrentHour(date.getHours());
//		picker.setCurrentMinute(date.getMinutes());
//
//		AlertDialog dialog = new AlertDialog.Builder(getActivity())
//				.setView(picker)
//				.setPositiveButton("确定", new DialogOnClickListener(picker))
//				.setNegativeButton("取消", null).create();
//
//		dialog.show();

	}

	private WorkOrder mWorkOrder;

	private PDAWorkOrderInfoConfig mPDAWorkOrderInfoConfig;

	@Override
	public void initData(List<Object> data) {
		/*
		 * 这里在赋值的时候要进行 null 判断，因为这里不允许传空后 覆盖之前的值。在这个 Fragment中传递的值的协议是 WorkOrder
		 * 和 PDAWorkOrderInfoConfig
		 */
		if (data.size() < 2) {
			return;
		}

		mWorkOrder = (WorkOrder) data.get(0);

		
			mPDAWorkOrderInfoConfig = (PDAWorkOrderInfoConfig) data.get(1);
		

	}

	private WorkApprovalPermission curApproveNode;
	private ProgressDialog prgDialog;
	private BusinessAction busAction;
	private AbstractAsyncCallBack auditCallBack;
	private BusinessAsyncTask asyTask;
	private WorkApprovalPersonRecord personCard;

	private void instantiation() {
		if (DEBUG) Log.w(TAG, "instantiation");
		
		personCard = new WorkApprovalPersonRecord();
		personCard.setTablename(ITableName.UD_ZYXK_QTJC);
		busAction = new BusinessAction(new CheckControllerActionListener());
		// 异步保存数据
		prgDialog = new ProgressDialog(getActivity(), "作废信息保存");

		asyTask = new BusinessAsyncTask(busAction, new AbstractAsyncCallBack() {

			@Override
			public void start(Bundle msgData) {

			}

			@Override
			public void processing(Bundle msgData) {
				String actionType = getActionType();
				if (msgData.containsKey(actionType)) {
					prgDialog.showMsg(msgData.getString(actionType));
				}
			}

			@Override
			public void error(Bundle msgData) {
				if (DEBUG)
					Log.w(TAG, "BusinessAsyncTask:error");
				
				isCheckTaskRunning = false;
				String actionType = getActionType();
				if (msgData.containsKey(actionType)) {
					prgDialog.dismiss();
					ToastUtils.imgToast(getActivity(),
							R.drawable.hd_hse_common_msg_wrong,
							msgData.getString(actionType));
				}

				mDataToSave.clearChild(GasDetectSub.class.getName());
			}

			@Override
			public void end(Bundle msgData) {
				if (DEBUG)
					Log.w(TAG, "BusinessAsyncTask:end");
				
				isCheckTaskRunning = false;
				prgDialog.dismiss();

				ToastUtils.imgToast(getActivity(),
						R.drawable.hd_hse_common_msg_right,
						msgData.getString(getActionType()));

				// 更新 是否合格状态
				if (mDataToSave.getIshg() == null) {
					tv_check_isok.setText("");
				} else if (mDataToSave.getIshg().equalsIgnoreCase("0")) {
					tv_check_isok.setTextColor(Color.RED);
					tv_check_isok.setText("否");
				} else if (mDataToSave.getIshg().equalsIgnoreCase("1")) {
					tv_check_isok.setTextColor(Color.BLACK);
					tv_check_isok.setText("是");
				}

				setTopUIEnabled(false);
				queryHistory();

				examinelv_examine.setCurrentEntity(curApproveNode);
				if (curApproveNode == null || curApproveNode.getIsend() == 1) {
					// 刷新导航栏状态。

					PDAWorkOrderInfoConfig naviCurrentEntity = (PDAWorkOrderInfoConfig) ((NaviFrameActivity) getActivity())
							.getNaviCurrentEntity();
					if (null != naviCurrentEntity) {
						naviCurrentEntity.setIsComplete(1);

						setNaviCurrentEntity(naviCurrentEntity);
					}
				}

			}

		});

		// 设置环节点查询编码。
		queryWorkInfo = new QueryWorkInfo();
	}

	private String getActionType() {
		String _psCode = mPDAWorkOrderInfoConfig.getPscode();
		String _actionType = null;

		if (IConfigEncoding.SP.equals(_psCode)) {
			_actionType = IActionType.ACTION_TYPE_GAS;
		} else if (IConfigEncoding.FC.equals(_psCode)) {
			_actionType = IActionType.ACTION_TYPE_RECHECKGAS;
		}

		return _actionType;
	}
	
	/**
	 * 气体检测的时候， Spinner 和 EditText 的对数是不确定的 而且在完成的时候还要保存其中的数据。所以要提供方便再次获取到
	 * 这两个控件对的方法，
	 * 
	 * 这里采用的方法是：每次动态创建一对控件的时候，都把他们的引用存到 本类的一个实例中。然后把这个 ViewHolder 存到 List 集合中。
	 * 因为不仅要找到控件对，还要能方便的获取某个控件对对应的实体。以便保存相应的 数据。 实际情况是每个 控件对 对应一个 实体的 List ，List
	 * 中的每个实体 中对应 Spinner 中的一个条目和对应 EditText 要显示的值。
	 * 
	 * ClassName: GaseViewPairs ()<br/>
	 * date: 2014年10月21日 <br/>
	 * 
	 * @author xuxinwen
	 * @version GasesCheckFragment
	 */
	class GaseViewPairs {
		Spinner type;
		int selectPosition;
		EditText value;
		String textValue;
	}
	
	
	/**
	 * 时间选择器对话框的 监听 ClassName: DialogOnClickListener ()<br/>
	 * date: 2014年10月21日 <br/>
	 * 
	 * @author xuxinwen
	 * @version GasesCheckFragment
	 */
	class DialogOnClickListener implements DialogInterface.OnClickListener {

		private TimePicker timePicker;

		public DialogOnClickListener(TimePicker timePicker) {
			this.timePicker = timePicker;
		}

		@Override
		public void onClick(DialogInterface dialog, int which) {
			Date date = (Date) btn_check_time.getTag();

			date.setHours(timePicker.getCurrentHour());
			date.setMinutes(timePicker.getCurrentMinute());
			date.setSeconds(0);

			btn_check_time.setText(new SimpleDateFormat(DATE_FORMAT)
					.format(date));
		}

	}
	
	/**
	 * 刷新历史记录页面。异步查询，完成的回调在 IQueryCallEventListenerImpl 类中
	 * 
	 * refreshHistory:(). <br/>
	 * date: 2014年10月28日 <br/>
	 * 
	 * @author xuxinwen
	 */
	public void queryHistory() {
		try {
			if (mWorkOrder != null) {

				queryWorkInfo.queryHistoryGasInfo(mWorkOrder,
						new IQueryCallEventListenerImpl(
								IQueryCallEventListenerImpl.TYPE_HISTORY));
			}
		} catch (HDException e) {
			logger.error(e.getMessage(), e);
		}
	}

	
	/**
	 * 设置气体检测的相关数据集，此方法不区分 是 新增还是 未完成。 setGaseInfo:(). <br/>
	 * date: 2014年10月28日 <br/>
	 * 
	 * @author xuxinwen
	 * @param objects
	 */
	private void filterDataOfTopUI(int queryApprovalType, Object... objects) {

		if (objects[0] == null) {
			Toast.makeText(getActivity(), "查询数据失败！请联系管理员", 0).show();
			return;
		}

		mDataToDisplay = (GasDetection) objects[0];

		/*
		 * 这里获得的值可能为 null ， 当该数据来自未完成的订单。
		 */
		dataDetectMethodList = mDataToDisplay.getChild(Domain.class.getName());

		dataGasesListList = mDataToDisplay.getListChild(GasDetectSub.class
				.getName());

		// 处理审批环节点。
		queryApproval(queryApprovalType);
		
		// 初始化 界面
		d2u_setTopData();
	}

	/**
	 * 控制上半部分 界面显示 与否， setTopUIShow:(). <br/>
	 * date: 2014年10月25日 <br/>
	 * 
	 * @author xuxinwen
	 * @param isShow
	 */
	private void ui_setTopUIShow(boolean isShow) {
		if (isShow) {
			ll_control_visible.setVisibility(View.VISIBLE);
		} else {
			ll_control_visible.setVisibility(View.GONE);
		}
	}
	
	/**
	 * 这是一个业务方法，
	 * isNewlyAddAction:(). <br/>
	 * date: 2015年1月20日 <br/>
	 *
	 * @author xuxinwen
	 * @return
	 */
	private boolean isNewlyAddAction(){
		if (mDataToDisplay.getUd_zyxk_qtjcid() == null) {
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 针对 SuperEntity 子类通用的 单一显示效果的 Adapter ClassName: SuperEntityAdapter ()<br/>
	 * date: 2014年10月20日 <br/>
	 * 
	 * @author xuxinwen
	 * @version GasesCheckFragment
	 */
	class SuperEntityAdapter extends BaseAdapter {

		public SuperEntityAdapter(List<SuperEntity> list, String textKey) {
			this.data = list;
			this.key = textKey;
		}

		// 要显示 数据对应的 key
		private String key;

		// 数据
		private List<SuperEntity> data;

		/**
		 * data.
		 * 
		 * @return the data
		 */
		public List<SuperEntity> getData() {
			return this.data;
		}

		/**
		 * data.
		 * 
		 * @param data
		 *            the data to set
		 */
		public void setData(List<SuperEntity> data, String textKey) {
			this.data = data;
			this.key = textKey;
		}

		@Override
		public int getCount() {
			if (this.data != null) {
				return this.data.size();
			}
			return 0;
		}

		@Override
		public Object getItem(int position) {
			if (this.data != null) {
				this.data.get(position);
			}
			return null;
		}

		@Override
		public long getItemId(int position) {

			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;
			SuperEntity item = (SuperEntity) this.data.get(position);

			if (convertView == null) {
				convertView = View.inflate(getActivity(),
						R.layout.hd_hse_common_module_simple_list_item1, null);

				holder = new ViewHolder();
				holder.tv = (TextView) convertView
						.findViewById(android.R.id.text1);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			CharSequence text = (CharSequence) item.getAttribute(key);

			holder.tv.setText(text != null ? text : " ");

			return convertView;
		}

		class ViewHolder {
			TextView tv;
		}

	}
	
	/**
	 * 根据实体中的数据 刷新 气体检测的界面显示。
	 * 
	 * setDataOfTopUI:(). <br/>
	 * date: 2014年10月21日 <br/>
	 * 
	 * @author xuxinwen
	 */
	private void d2u_setTopData() {

		if (mDataToDisplay != null) {
			ui_setTopUIShow(true);

			/*
			 * 这里区分了 是 新增数据，还是未完成数据的查询结果，并对相应的情况作不同的界面处理。
			 */
			if (isNewlyAddAction()) {
				// 新增数据
				
				// 获得系统当前时间，
				btn_check_time.setText(SystemProperty.getSystemProperty()
						.getSysDateTime());
				
				// 设置检测方式，
				spinner_check_pattern.setAdapter(new SuperEntityAdapter(
						dataDetectMethodList, "value"));

				// 更新检测位置
				tv_check_location.setText(mWorkOrder.getJclocation());

			} else {
				// 未完成数据
				btn_check_time.setText(mDataToDisplay.getJctime());
				spinner_check_pattern.setAdapter(new ArrayAdapter<>(
						getActivity(),
						R.layout.hd_hse_common_module_simple_list_item1,
						android.R.id.text1, new String[] { mDataToDisplay
								.getJcmethod() }));

				// 更新检测位置
				tv_check_location.setText(mDataToDisplay.getJclocation_desc());
			}

			// 更新检测位置
			tv_check_location.setText(mWorkOrder.getJclocation_desc());

			// 更新检测单位
			btn_check_company.setText(mDataToDisplay.getJcdept());

			d2u_IsUpToStandard();

			d2u_setGaseDetectTable();

			if (isNewlyAddAction()) {
				// 就是新增,清空界面数据
				resetGaseDetect();
			}

			setTopUIEnabled(isNewlyAddAction());

		}
	}
	

	private void d2u_setGaseDetectTable() {
		if (dataGasesListList != null && dataGasesListList.size() > 0) {
			// 设置第一行。
			spinner_row1_gase_type.setAdapter(new SuperEntityAdapter(
					dataGasesListList.get(0), "description"));
			Float qtvalue2 = ((GasDetectSub) dataGasesListList.get(0)
					.get(0)).getQtvalue();

			if (qtvalue2 != null) {
				et_row1_gase_value.setText(qtvalue2.toString());
			}
			
			ui_dynamicAddRowToTable();
			d2u_setGasesInfo();
		} else {
			Log.e(TAG, "没有气体检测信息！");
		}
	}
	
	/**
	 * 气体检测动态添加TableRow部分中，TableLayout 中最原始 TableRow个数。
	 */
	private final int CONF_TABLE_MINIMUM_ROW = 2;
	
	/**
	 * 去掉动态添加过的 TableRow
	 * ui_clearTableRow:(). <br/>
	 * date: 2015年1月20日 <br/>
	 *
	 * @author xuxinwen
	 */
	private void ui_clearTableRow() {
		/*
		 * 清空之前动态添加的 行,布局文件
		 * 根据 布局文件的不同会对应不同的算法。
		 */
		while (tl_modify_gases_item.getChildCount() > CONF_TABLE_MINIMUM_ROW) {
			tl_modify_gases_item.removeViewAt(tl_modify_gases_item
					.getChildCount() - 2);
		}

		// 控件对的引用。
		gaseDetectRowHolder.clear();
	}
	
	/**
	 * 根据数据动态添加行，
	 * ui_dynamicAddRowToTable:(). <br/>
	 * date: 2015年1月20日 <br/>
	 *
	 * @author xuxinwen
	 */
	private void ui_dynamicAddRowToTable() {
		ui_clearTableRow();
		
		/*
		 * 在这里根据数据量动态创建 Spinner 和 EditText 的控件对。 其中 每个 “控件对” 对应的是 一个“实体的
		 * List” 。 每个实体中 存储的是 Spinner 中的一个条目以及该条目对应的 Value 也就是 应该显示在
		 * EditText 中的数据。
		 */
		
		// 两级 List ， 外层的 List 的 数量决定了要创建 几对 控件，里层的 List 决定了
		// 某个 Spinner 中的 item的数量
		int len = dataGasesListList.size();
		View v = null;
		GaseViewPairs holder = null;

		for (int i = 1; i < len; i++) {
			v = View.inflate(
					getActivity(),
					R.layout.hd_hse_common_module_phone_frag_gases_check_inner_row,
					null);
			
			TableLayout.LayoutParams _lp = new TableLayout.LayoutParams(
					TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
			_lp.topMargin = 5;
			_lp.bottomMargin = 5;
			v.setLayoutParams(_lp);
			
			holder = new GaseViewPairs();
			holder.type = (Spinner) v
					.findViewById(R.id.hd_hse_common_module_gases_check_concentration_row_spinner);
			holder.value = (EditText) v
					.findViewById(R.id.hd_hse_common_module_gases_check_concentration_value_row_et);
			holder.type.setTag(holder.value);
			
			// 添加到 ViewHolder 集合中，
			gaseDetectRowHolder.add(holder);
			
			// 往 TableLayout 末端添加 TableRow
			tl_modify_gases_item.addView(v,
					tl_modify_gases_item.getChildCount() - 1);
		}
	}
	
	private void d2u_setGasesInfo() {
		if (DEBUG) Log.w(TAG, "d2u_setGasesInfo");
		
		int _len = gaseDetectRowHolder.size();
		GaseViewPairs _holder = null;
		
		List<SuperEntity> _list = null;
		
		for (int i = 0; i < _len; i++) {
			
			_holder = gaseDetectRowHolder.get(i);
			
			_list = dataGasesListList.get(i+1);
			
			// 设置 Spinner 的值，
			_holder.type.setAdapter(new SuperEntityAdapter(_list,
					"description"));
	
			// 设置气体浓度数值，
			Float qtvalue = ((GasDetectSub) _list.get(0))
					.getQtvalue();
			_holder.value.setText(qtvalue != null ? qtvalue.toString() : "");
		}
	}

	/**
	 * 数据与 UI 绑定的方法，是否合格。
	 * d2UIsUpToStandard:(). <br/>
	 * date: 2015年1月20日 <br/>
	 *
	 * @author xuxinwen
	 */
	private void d2u_IsUpToStandard() {
		// 设置是否合格。
		if (mDataToDisplay.getIshg() == null) {
			tv_check_isok.setText("");
		} else if (mDataToDisplay.getIshg().equalsIgnoreCase("0")) {
			tv_check_isok.setText("否");
		} else if (mDataToDisplay.getIshg().equalsIgnoreCase("1")) {
			tv_check_isok.setText("是");
		}
	}
	
	/**
	 * 查询结果的处理函数，用于查询结果与界面显示的绑定。
	 * ClassName: IQueryCallEventListenerImpl ()<br/>
	 * date: 2015年1月20日  <br/>
	 *
	 * @author xuxinwen
	 * @version GasesCheckFragment_1
	 */
	class IQueryCallEventListenerImpl implements IQueryCallEventListener {

		/*
		 * 这里为什么会出现自己定义的四个查询类型，
		 * 因为四个查询方法使用了同样的接口，我们可以写四个实现类来分别处理，
		 * 这样就不会出现自己定义的四个查询类型。
		 */
		
		// 气体检测信息
		public static final int TYPE_GASE_INFO = 0;

		// 历史记录查询
		public static final int TYPE_HISTORY = 1;

		// 为完成查询
		public static final int TYPE_UNDONE = 2;

		// 查询审批环节点。
		public static final int TYPE_APPROVAL = 3;

		private int mQueryType;

		public IQueryCallEventListenerImpl(int queryType) {
			mQueryType = queryType;
		}

		@Override
		public void callBackProcess(int type, Object... objects)
				throws HDException {
			if (type == IMessageWhat.END) {
				// 查询完成。

				switch (mQueryType) {
				case TYPE_GASE_INFO:
					// 气体检测查询。
					// 备份一下。
					mDataToAdd = (GasDetection) objects[0];
					filterDataOfTopUI(QUERY_APPROVAL_TYPE_ADD, objects);
					break;
				case TYPE_UNDONE:

					setUndone(objects);
					break;
				case TYPE_APPROVAL:

					d2u_setDataOfApproval(objects);
					// TODO 关闭等待 dialog
					break;
				case TYPE_HISTORY:
					setDataOfHistory(objects);

					break;
				default:
					break;
				}
			} else {
				Toast.makeText(getActivity(), "正在查询，请稍后", 0).show();
				// 正在查询
				switch (mQueryType) {
				case TYPE_GASE_INFO:
					// 气体检测查询。
					// TODO 开启等待 dialog

					break;
				case TYPE_UNDONE:
					// TODO 开启等待 dialog

					break;
				case TYPE_APPROVAL:

					break;

				default:
					break;
				}
			}
		}

	}

	private void setUndone(Object[] objects) {

		if ((GasDetection) objects[0] != null) {
			// 有未完成的数据。

			/*
			 * 要保存的数据，因为是未完成的订单，不用再new 需要保存的 对象了。
			 */
			mDataToSave = (GasDetection) objects[0];

			filterDataOfTopUI(QUERY_APPROVAL_TYPE_UNDONE, objects);
		}

	}
	
	private void d2u_setDataOfApproval(Object[] objects) {
		if (DEBUG) Log.w(TAG, "d2u_setDataOfApproval");
		List<WorkApprovalPermission> queryApprovalPermission = (List<WorkApprovalPermission>) objects[0];
		examinelv_examine.clearDatas();
		examinelv_examine.setData(queryApprovalPermission);
		examinelv_examine.setWorkOrder(mWorkOrder);
		examinelv_examine.setIEventListener(new IEventListenerImpl());
	}
	
	/**
	 * 判断是不是所有气体浓度都录入了数值。 isAllGaseDensityHasValue:(). <br/>
	 * date: 2014年11月7日 <br/>
	 * 
	 * @author xuxinwen
	 * @return
	 */
	private boolean ui_isGaseDensityHasValue() {
		boolean _result = false;

		if (TextUtils.isEmpty(et_row1_gase_value.getText())) {
		} else {
			_result = true;
		}

		for (GaseViewPairs item : gaseDetectRowHolder) {
			if (TextUtils.isEmpty(item.value.getText())) {
			} else {
				_result = true;
				break;
			}
		}

		return _result;
	}
	
	/**
	 * 判断气体检测所录入数据是否合法，
	 * ui_isGaseDensityValueLegal:(). <br/>
	 * date: 2015年3月26日 <br/>
	 *
	 * @author xuxinwen
	 * @return
	 */
	private boolean ui_isGaseDensityValueLegal() {

		String _txt = et_row1_gase_value.getText().toString();
		
		if ((!TextUtils.isEmpty(_txt))	// 文本框有值
				&& (_txt.length() == 1)		// 只有一个字符
				&& (_txt.charAt(0) == '.')	// 是小数点
				) {
			return false;
		} 

		for (GaseViewPairs item : gaseDetectRowHolder) {
			_txt = item.value.getText().toString();
			if ((!TextUtils.isEmpty(_txt))	// 文本框有值
					&& (_txt.length() == 1)		// 只有一个字符
					&& (_txt.charAt(0) == '.')	// 是小数点
					) {
				return false;
			} 
		}

		return true;
	}
	
	/**
	 * 通用监听处理。 ClassName: IEventListenerImpl ()<br/>
	 * date: 2014年10月23日 <br/>
	 * 
	 * @author xuxinwen
	 * @version GasesCheckFragment
	 */
	class IEventListenerImpl implements IEventListener {

		@Override
		public void eventProcess(int eventType, Object... objects)
				throws HDException {

			if (DEBUG) Log.w(TAG, "eventProcess");
			
			if(IEventType.EXAMINE_TOEXAMINE_ClICK == eventType){
				if (DEBUG) Log.w(TAG, "EXAMINE_TOEXAMINE_ClICK");
				
				if (!ui_isGaseDensityHasValue()) {
					Toast.makeText(getActivity(), "数据录入不完全", 0).show();
					throw new HDException("数据录入不完全");
				}
				
				if(!ui_isGaseDensityValueLegal()){
					Toast.makeText(getActivity(), "数据录入不合法，不能只输入小数点", 0).show();
					throw new HDException("数据录入不合法，不能只输入小数点");
				}
				
				examinelv_examine.setFragment(GasesCheckFragment.this);
				
			}
			
			
		}

	}
	
	//TODO
	private void saveGaseData(WorkApprovalPermission wap) {
		if (isCheckTaskRunning) {
			return;
		}

		if (DEBUG) Log.w(TAG, "saveGaseData");
		
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put(WorkOrder.class.getName(), mWorkOrder);

		// 审批环节点
		curApproveNode = wap;
		mapParam.put(WorkApprovalPermission.class.getName(), curApproveNode);
		mapParam.put(PDAWorkOrderInfoConfig.class.getName(),
				mPDAWorkOrderInfoConfig);

		if (mDataToDisplay.getUd_zyxk_qtjcid() == null) {
			u2d_saveGaseDetectState();
		} else {

		}
		mapParam.put(GasDetection.class.getName(), mDataToSave);
		personCard.setTableid(mDataToSave.getUd_zyxk_qtjcid());
		personCard.setDycode(mPDAWorkOrderInfoConfig.getDycode());
		mapParam.put(WorkApprovalPersonRecord.class.getName(), personCard);

		try { 
			isCheckTaskRunning = true;
			asyTask.execute(getActionType(), mapParam);
		} catch (HDException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 控制 顶部同步按钮的 可见性。由数据库查询自动决定结果。 ctrlSyncBtnVisiblity:(). <br/>
	 * date: 2014年12月1日 <br/>
	 * 
	 * @author xuxinwen
	 */
	private void ctrlSyncBtnVisiblity() {
		if (DEBUG) Log.w(TAG, "ctrlSyncBtnVisiblity");
		
		new AsyncTask<Void, Void, Boolean>() {

			@Override
			protected Boolean doInBackground(Void... params) {
				IQueryRelativeConfig _iQueryRelativeConfig = new QueryRelativeConfig();
				RelationTableName _tableName = new RelationTableName();
				_tableName.setSys_type(IRelativeEncoding.ISASYSCPCQT);
				return _iQueryRelativeConfig.isHadRelative(_tableName);
			}

			@Override
			protected void onPostExecute(Boolean result) {
				if (result) {
					btn_sync.setVisibility(View.VISIBLE);
					btn_sync.setOnClickListener(new ButtonsOnClickListener());
				} else {
					btn_sync.setVisibility(View.INVISIBLE);
					btn_sync.setOnClickListener(null);
				}
			}

		}.execute();
	}
	
	private String SP_LAST_CHECK_TIME = "lastCheckTime";
	
	/**
	 * 保存 当前气体检测 界面的数据。 saveGaseDetectState:(). <br/>
	 * date: 2014年10月21日 <br/>
	 * 
	 * @author xuxinwen
	 */
	private void u2d_saveGaseDetectState() {
		mDataToSave.setJclocation_desc(tv_check_location.getText().toString());
		// 保存时间
		mDataToSave.setJctime(btn_check_time.getText().toString());
		
		// 保存检测方式
		String value = ((Domain) dataDetectMethodList.get(spinner_check_pattern
				.getSelectedItemPosition())).getValue();
		mDataToSave.setJcmethod(value);

		// 保存检测单位
		mDataToSave.setJcdept(btn_check_company.getText().toString());

		// 保存各个气体的检测浓度。
		List<SuperEntity> saveData = new ArrayList<SuperEntity>();

		for (int i = dataGasesListList.size(); i > 0; i--) {
			if (i == 1) {
				// 第一个 得到数据源中对应的 实体
				List<SuperEntity> gasesInfo = dataGasesListList.get(i - 1);

				// 得到选中实体
				int index = spinner_row1_gase_type.getSelectedItemPosition();
				GasDetectSub source = (GasDetectSub) gasesInfo.get(index);

				// 创建新的实体， 设置相应信息。
				GasDetectSub des = new GasDetectSub();
				des.setQttype(source.getQttype());

				String qtValue = et_row1_gase_value.getText().toString();
				if (!TextUtils.isEmpty(qtValue) ) {
					try{
						des.setQtvalue(Float.valueOf(qtValue));
					}catch(NumberFormatException e){
						// 不能转换成数字的情况是只有小数点的时候
						
						logger.error("气体检测录入数据格式有问题："+qtValue+" 系统将值设为0");
						des.setQtvalue(0f);
					}
				}

				des.setMaxvalue(source.getMaxvalue());
				des.setMinvalue(source.getMinvalue());
				des.setIsbj(source.getIsbj());

				saveData.add(des);

				continue;
			}

			GaseViewPairs viewPairs = gaseDetectRowHolder.get(i - 2);
			List<SuperEntity> gasesInfo = dataGasesListList.get(i - 1);

			// 得到数据源中对应的 实体
			int index = viewPairs.type.getSelectedItemPosition();
			GasDetectSub source = (GasDetectSub) gasesInfo.get(index);

			// 创建新的实体， 设置相应信息。
			GasDetectSub des = new GasDetectSub();
			des.setQttype(source.getQttype());
			if (!TextUtils.isEmpty(viewPairs.value.getText().toString())) {
				des.setQtvalue(Float.valueOf(viewPairs.value.getText()
						.toString()));
			}

			des.setMaxvalue(source.getMaxvalue());
			des.setMinvalue(source.getMinvalue());
			des.setIsbj(source.getIsbj());

			saveData.add(des);
		}

		mDataToSave.setChild(GasDetectSub.class.getName(), saveData);

	}
	
	private void setDataOfHistory(Object[] objects) {
		if (objects[0] == null) {
			return;
		}

		ArrayList<Map<String, String>> _data = (ArrayList<Map<String, String>>) objects[0];
		//TODO
		/*
		 * 这里要处理数据的分离，与用来显示历史记录的控件结合。因为该界面显示历史记录
		 * 所使用的控件与横版不同，数据分离要在控件外部完成，
		 */
		mDataHistoryHeaders.clear();
		
		if (_data.size() > 0) {
			 
			
			// 取得第一组数据的 key，
			Set<String> _keySet = new LinkedHashSet<String>();
			Map<String, String> _item = null;
			
			
			int _size = _data.size();
			
			for(int i=0; i<_size; i++){
				_item = _data.get(i);
				
				// 将该 item 里的 key 全部添加到 keySet 中，
				_keySet.addAll(_item.keySet());
			}
			
			
			Iterator<String> iterator = _keySet.iterator();

			while (iterator.hasNext()) {
				String _next = iterator.next();
				
				/*if(_next.contains("#")){
					_next = _next.substring(_next.indexOf('#')+1);
				}*/
				
				mDataHistoryHeaders.add(_next);
			}
			
		}
		
		TableTitleAdapter _tableTitleAdapter = new TableTitleAdapter(getActivity(), mDataHistoryHeaders);
		
		//测量标题ListView 中每个 item 的宽度找出最宽的 item 作为标题的宽度
//		int _count = _tableTitleAdapter.getCount();
//		int _maxWidth = 0;
//		
//		for(int i=0; i<_count; i++){
//			int _width = ViewMeasureUtils.getMeasuredWidth(_tableTitleAdapter.getView(i, null, null));
//			if(_width > _maxWidth){
//				_maxWidth = _width;
//			}
//		}
//
//		gHistoryTitle.getLayoutParams().width = _maxWidth;
		
		if (DEBUG)
			Log.w(TAG, _data.toString());
		
		gHistoryTitle.setAdapter(_tableTitleAdapter);
		
		// 设置 Title ListView 的Divider 实现 item 之间的间距。
		gHistoryTitle.setDivider(new ColorDrawable(0x00000000));
		gHistoryTitle.setDividerHeight((int) getActivity().getResources().getDimensionPixelOffset(R.dimen.hd_hse_common_module_phone_gases_check_history_list_dividerHeight));
		
		gHistoryContent.setAdapter(new TableContentPagerAdapter(getActivity(), mDataHistoryHeaders, _data, gHistoryTitle));

		
		combinationUI.bindIndictor2ViewPager(getActivity(), gHistoryRadioGroup, gHistoryContent);
		
		//因为遍历数据中最晚时间与当前更新UI 的操作无关, 等到下一个消息的时候处理
//		Message _message = Message.obtain();
//		_message.what = 0;
//		_message.obj = _data;
//		mHandler.sendMessage(_message);
		
		
	}
	
	/**
	 * 此页面中有 ViewPager与Indictor 结合的UI 需要这样一个工具类
	 */
	private CombinationUI combinationUI = new CombinationUI();
	
	/**
	 * 查询未完成的订单， 异步查询， queryUndone:(). <br/>
	 * date: 2014年10月27日 <br/>
	 * TODO
	 * @author xuxinwen
	 */
	private void queryUndone() {
		// 查询是否有未完成的订单。
		if (DEBUG) Log.w(TAG, "queryUndone");
		
		if (mWorkOrder == null) {
			return;
		}

		try {
			// 历史记录部分。
			// 查询未完成订单。

			queryWorkInfo.queryUndoneGasInfo(mWorkOrder,
					new IQueryCallEventListenerImpl(
							IQueryCallEventListenerImpl.TYPE_UNDONE));
		} catch (HDException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (DEBUG) Log.w(TAG, "onActivityResult");
		
		if(data == null){
			return;
		}
		
		WorkApprovalPermission _curr = (WorkApprovalPermission) data.getSerializableExtra(ReadCardExamineActivity.WORKAPPROVALPERMISSION);
		btn_check_company.setText(_curr.getDepartmentdesc());
		saveGaseData(_curr);
//		examinelv_examine.setCurrentEntity(_curr);
		
	}
	
}
