/**
 * Project Name:hse-common-module-phone
 * File Name:SelectableZYPExpandableListView.java
 * Package Name:com.hd.hse.common.module.custom
 * Date:2015年1月4日
 * Copyright (c) 2015, xuxinwen@ushayden.com All Rights Reserved.
 *
 */

package com.hd.hse.common.module.phone.custom;

import org.apache.log4j.Logger;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.common.module.phone.PhoneEventType;
import com.hd.hse.entity.workorder.WorkOrder;

/**
 * ClassName:SelectableZYPExpandableListView ().<br/>
 * Date: 2015年1月4日 <br/>
 * 
 * @author xuxinwen
 * @version
 * @see
 */
public class NormalZYPExpandableListView extends BaseZYPExpandableListView  {

	private final String TAG = "NormalZYPExpandableListView";
	private final boolean DEBUG = true;

	private static Logger logger = LogUtils.getLogger(NormalZYPExpandableListView.class);
	
	public NormalZYPExpandableListView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	public NormalZYPExpandableListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NormalZYPExpandableListView(Context context) {
		super(context);
	}
	
	@Override
	protected void onCreateGroupView(GroupHolder viewHolder, int groupPosition) {
		viewHolder.icon.setClickable(false);
	}
	
	@Override
	protected BaseZYPListRow onCreateChildView() {
		
		return new NormalZYPListRow(getContext());
	}
	
	@Override
	protected void onItemClick(Object... args) {
		if(mZYPItemClickListener != null){
			
			mZYPItemClickListener.onClick((WorkOrder) args[0], (View) args[1], (int) args[2]);
		}
		
		if(mEventListener != null){
			try {
				mEventListener.eventProcess(PhoneEventType.ZYPLIST_ITEM_CLICK, 
						args[0],	// 对应的 WorkOrder
						args[3]);	// 该WorkOrder 在其所属组中的位置.
			} catch (HDException e) {
				logger.error(e);
			}
		}
	}
 
	private OnZYPItemClickListener mZYPItemClickListener;
	
	public void setOnZYPItemClickListener(OnZYPItemClickListener listener){
		mZYPItemClickListener = listener;
	}
	
	/**
	 * 作业票 被点击后的事件监听。
	 * 
	 * ClassName: OnZYPItemClickListener ()<br/>
	 * date: 2015年1月15日  <br/>
	 *
	 * @author xuxinwen
	 * @version PopWindowZYPExpandableListView1
	 */
	public interface OnZYPItemClickListener{
		public void onClick(WorkOrder workOrder, View anchorView,
				int pointerHorizontalPosition);
	}
	
}
