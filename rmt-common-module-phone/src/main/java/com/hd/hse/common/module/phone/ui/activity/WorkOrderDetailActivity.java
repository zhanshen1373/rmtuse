/**
 * Project Name:hse-wov-app
 * File Name:WorkTaskDetailActivity.java
 * Package Name:com.hd.hse.wov.ui.activity.worktask
 * Date:2014年11月6日
 * Copyright (c) 2014, fulibo@ushayden.com All Rights Reserved.
 *
 */

package com.hd.hse.common.module.phone.ui.activity;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.hd.hse.business.action.BusinessAction;
import com.hd.hse.business.task.AbstractAsyncCallBack;
import com.hd.hse.business.task.BusinessAsyncTask;
import com.hd.hse.common.component.phone.constant.IActionBar;
import com.hd.hse.common.component.phone.custom.MessageDialog;
import com.hd.hse.common.component.phone.custom.ProgressDialog;
import com.hd.hse.common.component.phone.event.IEventType;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.common.module.phone.R;
import com.hd.hse.constant.BusinessActionNum;
import com.hd.hse.constant.IActionType;
import com.hd.hse.entity.workorder.WorkTask;
import com.hd.hse.service.inter.ICallBack;
import com.hd.hse.service.manager.ManagerInstance;
import com.hd.hse.service.workorder.checkrules.AbstractCheckListener;
import com.hd.hse.service.workorder.checkrules.CheckControllerActionListener;
import com.hd.hse.service.workorder.queryinfo.QueryWorkInfo;

/**
 * ClassName:WorkTaskDetailActivity ().<br/>
 * Date: 2014年11月6日 <br/>
 * 
 * @author flb
 * @version
 * @see 此为作业票详情界面,使用时, 需要传入一个作业票对象
 */
public class WorkOrderDetailActivity extends NaviFrameActivity implements IEventListener, ICallBack {

	private static Logger logger = LogUtils.getLogger(WorkOrderDetailActivity.class);
	public static final String WORK_TASK = "args";
	public static int REQUESTCODE = 1;
	private Context context = null;
	private ProgressDialog progressDialog = null;
	private WebView webView = null;
	/**
	 * actionBarTitleName:TODO(导航栏标题).
	 */
	private String actionBarTitleName;
	private WorkTask workTask = null;
	private String[] actionBar;
	private AbstractCheckListener actLsr = null;
	private BusinessAction action = null;
	private BusinessAsyncTask asyTask = null;
	private Intent intent;
	// 错误提示页面
	private View errView;
	private TextView errMsg;
	// 拼接URL
	String url = null;

	@Override
	public void initActionBar() {
		super.initActionBar();
		if (actionBar == null)
			return;
		// 设置导航栏信息
		setCustomActionBar(iEventListener, actionBar);
		// 设置导航栏标题
		setActionBartitleContent(actionBarTitleName, false);
		setCustomMenuBar(new String[] { IActionBar.ITEM_RETURN });
	}

	IEventListener iEventListener = new IEventListener() {

		public void eventProcess(int arg0, Object... objects)
				throws HDException {
			// PhoneEventType.ZYPLIST_ITEM_CLICK, objects
			if (IEventType.ACTIONBAR_RETURN_CLICK == arg0) {
				// 退回
				sendBackWorkOrder();
			}
		}
	};

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		init();
	}

	private void init() {
		errView = View
				.inflate(context, R.layout.hd_hse_common_error_page, null);
		errMsg = (TextView) errView.findViewById(R.id.hd_hse_common_err_desc);
		errView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				refresh();
			}
		});
		
		intent = getIntent();
		workTask = (WorkTask) intent.getSerializableExtra(WORK_TASK);

		try {
			ManagerInstance.getInstance().getActionInstance().action(BusinessActionNum.QUERY_REPORT_URL, this, workTask);
		} catch (HDException e) {
			logger.error(e.getMessage(), e);
		}
		
//		actionBarTitleName = workTask.getZypclassname();
//		QueryWorkInfo workInfo = new QueryWorkInfo();
//		try {
//			url = workInfo.queryReportFormUrl(workTask);
//		} catch (HDException e) {
//			logger.error(e.getMessage(), e);
//			// Log.e(TAG, "url查询失败!");
//		}
//
//		if (isLocal(workInfo, workOrder)) {
//			actionBar = new String[] { IActionBar.TV_BACK,
//					IActionBar.IBTN_LEVELTWO_MORE, IActionBar.TV_TITLE };
//		} else {
//			actionBar = new String[] { IActionBar.TV_BACK, IActionBar.TV_TITLE };
//		}

//		refresh();
		
		// 业务处理
//		actLsr = new CheckControllerActionListener();
//		action = new BusinessAction(actLsr);
//		asyTask = new BusinessAsyncTask(action, asyCallBack);
	}

	private void refresh() {
		final ProgressDialog proDialog = new ProgressDialog(context, "加载中。。。");
		proDialog.show();
		if (webView != null) {
			webView.removeAllViews();
		}
		webView = new WebView(this);
		// 设置缩放支持
		WebSettings settings = webView.getSettings();
		settings.setSupportZoom(true);
		settings.setBuiltInZoomControls(true);

		settings.setDefaultTextEncodingName("UTF-8");
		settings.setRenderPriority(RenderPriority.HIGH);
		
		WebViewClient wClient = new WebViewClient() {
			// @Override
			// public boolean shouldOverrideUrlLoading(WebView view, String url)
			// {
			// webView.loadUrl(url);
			// return true;
			// }
			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);
				proDialog.dismiss();
			}

			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String flingUrl) {

				proDialog.dismiss();
				webView.addView(errView, LayoutParams.MATCH_PARENT,
						LayoutParams.MATCH_PARENT);
				super.onReceivedError(view, errorCode, description, flingUrl);
			}
		};

		webView.setWebViewClient(wClient);
		webView.loadUrl(url);
		
		setContentView(webView);
	}

//	/**
//	 * 判断是否是本地作业票
//	 * 
//	 * @param workInfo
//	 * @param workOrder
//	 * @return
//	 */
//	private boolean isLocal(QueryWorkInfo workInfo, WorkTask workOrder) {
//		try {
//			if (workInfo.queryWorkInfo(workOrder, null) != null) {
//				return true;
//			}
//		} catch (HDException e) {
//			logger.error(e.getMessage());
//			return false;
//		}
//		return false;
//	}

	private AbstractAsyncCallBack asyCallBack = new AbstractAsyncCallBack() {

		@Override
		public void start(Bundle msgData) {

		}

		@Override
		public void processing(Bundle msgData) {

		}

		@Override
		public void end(Bundle msgData) {
			if (progressDialog != null && progressDialog.isShowing()) {
				progressDialog.dismiss();
			}
			ToastUtils.imgToast(context, R.drawable.hd_hse_common_msg_right,
					msgData.getString(IActionType.ACTION_TYPE_RETURN));
			setResult(REQUESTCODE, intent);
		}

		@Override
		public void error(Bundle msgData) {
			if (progressDialog != null && progressDialog.isShowing()) {
				progressDialog.dismiss();
			}
			ToastUtils.imgToast(context, R.drawable.hd_hse_common_msg_wrong,
					msgData.getString(IActionType.ACTION_TYPE_RETURN));
		}
	};

	@Override
	public void eventProcess(int eventType, Object... objects)
			throws HDException {
		switch (eventType) {
		case IEventType.ACTIONBAR_RETURN_CLICK:
			sendBackWorkOrder();
			break;
		}
	}

	private void sendBackWorkOrder() {
		MessageDialog.Builder builder = new MessageDialog.Builder(this);
		builder.setTitle("提示");
		builder.setMessage("确定退回作业票？");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				Map<String, Object> mapParam = new HashMap<String, Object>();
				mapParam.put(WorkTask.class.getName(), workTask);
				try {
					progressDialog = new ProgressDialog(context, "作业票退回中...");
					progressDialog.show();

					asyTask.execute(IActionType.ACTION_TYPE_RETURN, mapParam);
				} catch (HDException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});

		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.createWarm().show();
	}

	@Override
	public void start(String action, int flag, Object... objects) {
		
	}

	@Override
	public void process(String action, int flag, Object... objects) {
		
	}

	@Override
	public void end(String action, int flag, Object... objects) {
		url = (String) objects[0];
		refresh();
	}

	@Override
	public void error(String action, int flag, Object... objects) {
		ToastUtils.imgToast(this, R.drawable.hd_hse_common_msg_wrong, "查询失败:" + objects[0]);
	}
}
