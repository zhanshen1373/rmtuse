package com.hd.hse.main.phone.ui.activity.main;

import java.util.List;

import org.apache.log4j.Logger;

import com.hd.hse.common.component.phone.custom.MessageDialog;
import com.hd.hse.common.component.phone.custom.ProgressDialog;
import com.hd.hse.common.component.phone.event.IEventType;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.common.module.phone.custom.NoticeListView;
import com.hd.hse.common.module.phone.custom.NoticeListView.OnRefreshListener;
import com.hd.hse.constant.BusinessActionNum;
import com.hd.hse.entity.common.PushMessage;
import com.hd.hse.main.phone.R;
import com.hd.hse.main.phone.ui.receiver.UdpClient;
import com.hd.hse.service.inter.ICallBack;
import com.hd.hse.service.manager.ManagerInstance;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.RelativeLayout;

/**
 * 通知类消息列表
 * 
 * @author zl
 * 
 */
public class MsgListActivity extends Activity {

	private static Logger logger = LogUtils.getLogger(MsgListActivity.class);
	private NoticeListView msgListView;
	private Resources resources;
	private MessageDialog.Builder msgDialog;

	private RelativeLayout container;
	private List<PushMessage> list = null;

	/**
	 * 回调接口
	 */
	private IEventListener iEventLsn = new IEventListener() {

		@Override
		public void eventProcess(int eventType, Object... objects)
				throws HDException {
			if (IEventType.NOTICE_LIST_CLICK == eventType && objects[0] != null) {
				msgDialog.setMessage("  "+((PushMessage) objects[0]).getXxnr());
				msgDialog.create().show();
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_push_msg_list);
		resources = this.getResources();
		initView();
		
		initData();
		// IntentFilter intentFilter = new IntentFilter();
		// intentFilter.addAction(MainActivity.BROADCASTACTION);
		// this.getApplicationContext().registerReceiver(mBroadcastReceiver,
		// intentFilter);
	}
    @Override
    protected void onNewIntent(Intent intent) {
    	// TODO Auto-generated method stub
    	setIntent(intent);
    	super.onNewIntent(intent);
    }
	@Override
	protected void onResume() {
		super.onResume();
		Intent intent=getIntent();
		if("1".equalsIgnoreCase(intent.getStringExtra("MSG"))){
			readDataInfo();
		}
		// initData();
	}

	@Override
	public void finish() {
		// //数据是使用Intent返回
		// Intent intent = new Intent();
		// //把返回数据存入Intent
		// intent.putExtra("result", "My name is linjiqin");
		// //设置返回数据
		// this.setResult(RESULT_OK, intent);
		super.finish();
		// overridePendingTransition(android.R.anim.slide_out_right,android.R.anim.slide_in_left);
		overridePendingTransition(R.anim.hd_hse_common_in_from_right,
				R.anim.hd_hse_common_out_to_left);
	}

	/**
	 * 初始化界面
	 */
	@SuppressLint("ResourceAsColor")
	private void initView() {
		container = (RelativeLayout) findViewById(R.id.rlyt_msg_list_container);
		msgListView = new NoticeListView(this);
		container.addView(msgListView);
		// 返回按钮
		findViewById(R.id.ibtn_msg_list_back).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View arg0) {
						// 关闭窗体
						finish();
					}
				});
		findViewById(R.id.tv_msg_list_clear).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						if (list != null && list.size() != 0) {
							MessageDialog.Builder builder = new MessageDialog.Builder(
									MsgListActivity.this);
							builder.setTitle("提示");
							builder.setMessage("确定全部清除?");
							builder.setPositiveButton("确定",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,
												int which) {
											try {
												ManagerInstance
														.getInstance()
														.getActionInstance()
														.action(BusinessActionNum.ACTION_NEWS_DELTET,
																new ICallBack() {

																	private ProgressDialog mProgressDialog = new ProgressDialog(
																			MsgListActivity.this,
																			"正在删除...");

																	@Override
																	public void start(
																			String action,
																			int flag,
																			Object... objects) {
																		if (!mProgressDialog
																				.isShowing()) {
																			mProgressDialog
																					.show();
																		}
																	}

																	@Override
																	public void process(
																			String action,
																			int flag,
																			Object... objects) {

																	}

																	@Override
																	public void error(
																			String action,
																			int flag,
																			Object... objects) {
																		if (mProgressDialog
																				.isShowing()) {
																			mProgressDialog
																					.dismiss();
																		}
																		if (objects != null) {
																			ToastUtils
																					.imgToast(
																							MsgListActivity.this,
																							R.drawable.hd_common_message_error,
																							objects[0]
																									.toString());
																		} else {
																			ToastUtils
																					.imgToast(
																							MsgListActivity.this,
																							R.drawable.hd_common_message_error,
																							objects[0]
																									.toString());
																		}
																	}

																	@Override
																	public void end(
																			String action,
																			int flag,
																			Object... objects) {

																		if (mProgressDialog
																				.isShowing()) {
																			mProgressDialog
																					.dismiss();
																		}
																		if (list != null) {
																			list.clear();
																		}
																		refreshNoticeDatas();
																		ToastUtils
																				.toast(MsgListActivity.this,
																						"全部清除成功！");
																	}
																}, list);
											} catch (HDException e) {
												e.printStackTrace();
											}
											dialog.dismiss();
										}
									});
							builder.setNegativeButton(
									"取消",
									new android.content.DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,
												int which) {
											dialog.dismiss();
										}
									});
							builder.createWarm().show();

						}
					}
				});
		int padding = resources.getDimensionPixelSize(R.dimen.padding_large);
		// msgListView.onRefreshComplete("刷新成功!");
		msgListView.setiEventLsn(iEventLsn);
		msgListView.setonRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {
				// 查询通知消息（返回list）
				readDataInfo();
			}
		});

		// 设置屏幕填充显示
		msgListView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		msgListView.setBackgroundColor(resources
				.getColor(R.color.hd_hse_common_white));
		msgListView.setPadding(padding, 0, padding, padding);
		msgListView.noDatasText.setPadding(0,
				resources.getDimensionPixelSize(R.dimen.padding_large), 0, 0);
		msgListView.noDatasText.setGravity(Gravity.CENTER);

		msgDialog = new MessageDialog.Builder(this);
		msgDialog.setTitle("消息详情");
		msgDialog.setNegativeButton("取消", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int arg1) {
				dialog.dismiss();
			}
		});
	}
	/**
	 * TODO 
	 * @see android.app.Activity#onRestart(表示在后台重新进入时执行的代码)
	 */
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		//readDataInfo();
	}
	public void readDataInfo() {
		// 查询通知消息（返回list）
		try {
			ManagerInstance
					.getInstance()
					.getActionInstance()
					.action(BusinessActionNum.QUERY_NOTICE_MESSAGE,
							new ICallBackImpl());
		} catch (HDException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
//		list = UdpClient.msgListDatas;
//		msgListView.setDatas(list);
	}

	/**
	 * 刷新界面
	 */
	protected void refreshNoticeDatas() {
//		list = UdpClient.msgListDatas;
//		msgListView.setDatas(list);
	}

	// /**
	// * 广播接受
	// */
	// private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
	// @Override
	// public void onReceive(Context context, Intent intent) {
	// if (MainActivity.BROADCASTACTION.equals(intent.getAction())) {
	// System.out.println("接收到消息，刷新列表");
	// MsgListActivity.this.refreshNoticeDatas();
	// msgListView.onRefreshComplete("刷新成功！");
	// // 终止广播
	// // abortBroadcast();
	// }
	// }
	//
	// };

	// /**
	// * 销毁广播
	// */
	// public void destroyBroadcast() {
	// if (this.getApplicationContext() != null && mBroadcastReceiver != null) {
	// this.getApplicationContext().unregisterReceiver(mBroadcastReceiver);
	// }
	// }

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// destroyBroadcast();
	}

	/**
	 * 初始化时服务端获取消息信息,将通知分为通知类和消息类；
	 * 
	 * @param noticList
	 */
	private void initMsgList(List<PushMessage> msgList) {
		if (msgList != null && msgList.size() > 0) {
			for (PushMessage message : msgList) {
				// 通知类型消息
				if (UdpClient.MSGTYPE01.equals(message.getXxlx()))
//					removeDouble(message,UdpClient.msgListDatas);
				// 操作类型消息
				if (UdpClient.MSGTYPE02.equals(message.getXxlx())) {
					removeDouble(message,UdpClient.taskListDatas);
				}
			}
		}
		// 刷新消息界面
		refreshNoticeDatas();
	}

	/**
	 * 去除缓存中与服务端重复的消息（若不重复则加入缓存list）
	 * 
	 * @param list
	 */
	private void removeDouble(PushMessage msg, List<PushMessage> list) {
		boolean isdouble = false;
		if (msg != null && list != null) {
			for (PushMessage pMessage : list) {
				if (pMessage.getUd_zyxk_xxjlid()
						.equals(msg.getUd_zyxk_xxjlid())) {
					isdouble = true;
					break;
				}
			}
		}
		if (list != null && !isdouble) {
			list.add(msg);
		}
	}

	@SuppressWarnings("unchecked")
	private class ICallBackImpl implements ICallBack {

		@Override
		public void start(String action, int flag, Object... objects) {
			if (BusinessActionNum.QUERY_NOTICE_MESSAGE.equals(action)) {
				msgListView.onRefreshStart();
			}
		}

		@Override
		public void process(String action, int flag, Object... objects) {
		}

		@Override
		public void end(String action, int flag, Object... objects) {
			// 服务端获取通知数据集
			if (BusinessActionNum.QUERY_NOTICE_MESSAGE.equals(action)) {
				initMsgList((List<PushMessage>) objects[0]);
				msgListView.onRefreshComplete("刷新成功!");
			}
		}

		@Override
		public void error(String action, int flag, Object... objects) {
			if (BusinessActionNum.QUERY_NOTICE_MESSAGE.equals(action)) {
				msgListView.onRefreshComplete("刷新失败!");
			}
		}
	};

}
