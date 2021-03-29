/**
 * Project Name:hse-common-module-phone
 * File Name:NormalZYPListRow.java
 * Package Name:com.hd.hse.common.module.phone.custom
 * Date:2015年1月15日
 * Copyright (c) 2015, xuxinwen@ushayden.com All Rights Reserved.
 *
*/

package com.hd.hse.common.module.phone.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.module.phone.PhoneEventType;

/**
 * ClassName:NormalZYPListRow ().<br/>
 * Date:     2015年1月15日  <br/>
 * @author   xuxinwen
 * @version  
 * @see 	 
 */
public class NormalZYPListRow extends BaseZYPListRow {
	
	private final String TAG = "NormalZYPListRow";
	private final boolean DEBUG = true;
	
	public NormalZYPListRow(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
	}

	public NormalZYPListRow(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}

	public NormalZYPListRow(Context context) {
		super(context);
		
	}

	@Override
	public void onClick(View v) {
		/*
		 * 此处点击后 NormalZYPListRow 不知道要干什么，具体的操作要留给
		 * 使用 NormalZYPListRow的类来实现。不能用虚函数来实现，应该用接口
		 * 的方法来实现。
		 */
		if (mEventListener != null) {
			try {
				int _offSet = v.getLeft() + v.getWidth() / 2;
				int _index = (int) v.getTag();

				if (DEBUG) //TODO
					Log.w(TAG, "NormalZYPListRow 里的item被点击后，抛出去的 NormalZYPListRow"
							+ "实体是："+NormalZYPListRow.this);
				
				mEventListener.eventProcess(PhoneEventType.ZYPLIST_ITEM_CLICK,
						mData.get(_index), // 对应的实体。
						NormalZYPListRow.this, // 要依附的 View
						_offSet,	// 指针的位置。
						mGroupPosition
						); 

			} catch (HDException e) {
				// TODO
			}
		}
	}

}

