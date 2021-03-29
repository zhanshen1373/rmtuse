/**
 * Project Name:hse-common-component-phone
 * File Name:SuperViewHolder.java
 * Package Name:com.hd.hse.common.component.phone.adapter
 * Date:2015年6月1日
 * Copyright (c) 2015, fulibo@ushayden.com All Rights Reserved.
 *
 */

package com.hd.hse.common.module.phone.adapter;

import android.util.SparseArray;
import android.view.View;

/**
 * ClassName:SuperViewHolder ().<br/>
 * Date: 2015年6月1日 <br/>
 * 
 * @author flb
 * @version
 * @see
 */
@SuppressWarnings("all")
public class SuperViewHolder {

	private View convertView;
	private SparseArray<View> holder;

	public SuperViewHolder(View convertView) {
		this.convertView = convertView;
		this.holder = new SparseArray<View>();
		this.convertView.setTag(this);
	}

	public <T extends View> T getView(int id) {
		View childView = holder.get(id);
		if (childView == null) {
			childView = convertView.findViewById(id);
			holder.put(id, childView);
		}
		return (T) childView;
	}
}
