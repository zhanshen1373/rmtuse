/**
 * Project Name:hse-common-module
 * File Name:ReadCardActivity.java
 * Package Name:com.hd.hse.common.module.ui.activity
 * Date:2014年11月6日
 * Copyright (c) 2014, longgang@ushayden.com All Rights Reserved.
 *
 */

package com.hd.hse.common.module.phone.ui.activity;

import org.apache.log4j.Logger;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.hd.hse.common.component.phone.event.IEventType;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.common.module.phone.R;
import com.hd.hse.common.module.phone.ui.utils.SwipingCardUtils;
import com.hd.hse.constant.IConfigEncoding;
import com.hd.hse.entity.workorder.WorkOrder;

/**
 * ClassName:ReadCardActivity (配置读卡activity的超类).<br/>
 * Date: 2014年11月6日 <br/>
 * 
 * @author zhulei
 * @version
 * @see
 */
public class ReadCardExamineActivity<T extends SuperEntity> extends
		ReadCardActivity {
	private static Logger logger = LogUtils
			.getLogger(ReadCardExamineActivity.class);
	public static final String READCADRACTION = "readCardAction";
	// 刷卡区
	private EditText cardEdit;
	// 审批环节描述
	private TextView exmineDesc;
	// 审批环节
	private SuperEntity workApprovalPermission;
	// 作业票
	private WorkOrder workOrder;
	public static final String WORKORDER = "workOrder";
	public static final String WORKAPPROVALPERMISSION = "workApprovalPermission";
	private String[] persondesc;
	private Intent sendIntent;
	// 获取方法
	// private Method setSoftInputShownOnFocus;
	private boolean isfeast = true;
	/**
	 * 是否读卡
	 */
	private boolean hasReadCard = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.hd_common_component_readcard);
			cardnum = null;
			initView();
			initData();
		} catch (Exception e) {
			logger.error(e);
			ToastUtils.toast(this, "系统出错,请联系管理员!");
		}
	}

	private void initView() {
		// TODO Auto-generated method stub
		TextView cancelBT = (TextView) this
				.findViewById(R.id.hd_hse_common_readcard_cancel_tv);
		TextView sureBT = (TextView) this
				.findViewById(R.id.hd_hse_common_readcard_sure_tv);
		exmineDesc = (TextView) findViewById(R.id.hd_hse_common_readcard_tv);
		View view = findViewById(R.id.hd_hse_common_readcard_rl);
		// 压缩读卡界面图片
		setBackground(view, R.drawable.hd_hse_common_readcard_bg);
		cardEdit = (EditText) findViewById(R.id.hd_hse_common_readcard_et);
		cancelBT.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				sendBroadCast();
				ReadCardExamineActivity.this.finish();
			}
		});
		sureBT.setOnClickListener(sureClick);

		if (IConfigEncoding.ISTEST) {
			view.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					cardnum = "22";
					onResume();
				}
			});
		}
	}

	private void initData() {
		Intent intent = getIntent();
		String spfield_desc = "";
		String[] spfildstr;
		Object obj=intent.getSerializableExtra(WORKORDER);
		if(obj==null){
			this.finish();
			logger.error("传入workorder 为null;");
			return;
		}
		workOrder = (WorkOrder)obj;
		obj=(SuperEntity) intent
				.getSerializableExtra(WORKAPPROVALPERMISSION);
		if(obj==null){
			logger.error("传入环节点 为null;");
			//return;
		}
		workApprovalPermission = (SuperEntity)obj;
		spfield_desc = workApprovalPermission.getAttribute("spfield_desc")
				.toString();
		spfildstr = spfield_desc.split("#");
		if (spfildstr.length > 0) {
			spfield_desc = spfildstr[spfildstr.length - 1] + ":";
		} else {
			spfield_desc = "刷卡人:";
		}
		exmineDesc.setText(spfield_desc);
		//判断是否为手动输入
		if((int)workApprovalPermission.getAttribute("isinput")==1){
			cardEdit.setFocusable(true);
			cardEdit.setFocusableInTouchMode(true);
			cardEdit.requestFocus();
		}
		
		// workApprovalPermission.setAttribute("personid", null);spfield_desc
		// workApprovalPermission.setAttribute("persondesc", null);
	}

	private OnClickListener sureClick = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			String cardEditstr = ReadCardExamineActivity.this.cardEdit.getText().toString();
			if ((int)workApprovalPermission.getAttribute("isinput")!=1
					&&!hasReadCard && workApprovalPermission != null
					&& (int) workApprovalPermission.getAttribute("ismust") == 1
					) {
				ToastUtils.imgToast(ReadCardExamineActivity.this,
						R.drawable.hd_hse_common_msg_wrong, "请刷卡！");
				return;
			}else if(ReadCardExamineActivity.this.cardEdit.getText() == null || 
					"".equals(cardEditstr)){
				ToastUtils.imgToast(ReadCardExamineActivity.this,
						R.drawable.hd_hse_common_msg_wrong, "请输入！");
				return;
			}
			// 防止多点击
			if (!isfeast) {
				return;
			}
			if(!hasReadCard){
				if(ReadCardExamineActivity.this.workApprovalPermission.getAttribute("persondesc")!=null
						&& !"".equals(ReadCardExamineActivity.this.workApprovalPermission.getAttribute("persondesc"))){
					cardEditstr =  ReadCardExamineActivity.this.workApprovalPermission.getAttribute("persondesc")+","+cardEditstr;
				}
				ReadCardExamineActivity.this.workApprovalPermission.setAttribute("personid", cardEditstr);
				ReadCardExamineActivity.this.workApprovalPermission.setAttribute("persondesc", cardEditstr);
			}
			isfeast = false;
			setResult(
					IEventType.READCARD_TYPE,
					getIntent()
							.putExtra(
									ReadCardExamineActivity.this.WORKAPPROVALPERMISSION,
									ReadCardExamineActivity.this.workApprovalPermission));

			sendBroadCast();
			hasReadCard = false;
			finish();
		}
	};

	@Override
	protected void onResume() {
		try {
			super.onResume();
			if (cardnum != null && !"".equals(cardnum)) {
				SwipingCardUtils.swipingCard(workOrder, workApprovalPermission,
						cardnum);
				if (cardEdit != null) {
					persondesc = workApprovalPermission
							.getAttribute("persondesc").toString().split(",");
					cardEdit.setText(persondesc[persondesc.length - 1]);
				}
				hasReadCard = true;
				cardnum = null;
			}
		} catch (HDException e) {
			ToastUtils.imgToast(this, R.drawable.hd_hse_common_msg_wrong,
					e.getMessage());
		}
	}

	private void sendBroadCast() {
		// TODO Auto-generated method stub
		if (sendIntent == null) {
			sendIntent = new Intent(); // Itent就是我们要发送的内容
		}
		sendIntent.putExtra(READCADRACTION, 1);
		sendIntent.setAction(READCADRACTION); // 设置你这个广播的action，只有和这个action一样的接受者才能接受者才能接收广播
		sendBroadcast(sendIntent); // 发送广播
	}

	@Override
	public void actionByCardID(String cardid) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		sendBroadCast();
		return super.onKeyDown(keyCode, event);
	}
}
