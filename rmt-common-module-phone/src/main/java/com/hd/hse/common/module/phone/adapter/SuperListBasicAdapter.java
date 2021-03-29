/**
 * Project Name:hse-common-component-phone
 * File Name:SuperListBasicAdapter.java
 * Package Name:com.hd.hse.common.component.phone.adapter
 * Date:2015年6月1日
 * Copyright (c) 2015, fulibo@ushayden.com All Rights Reserved.
 *
 */

package com.hd.hse.common.module.phone.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.entity.SuperEntity;

/**
 * ClassName:SuperListBasicAdapter ().<br/>
 * Date:     2015年6月1日  <br/>
 * @author   flb
 * @version 
 * @see     
 */
public abstract class SuperListBasicAdapter<T extends SuperEntity> extends BaseAdapter{

	protected Context context;
	protected List<T> sourceData;
	protected LayoutInflater layoutInflater;
	protected IEventListener listener;
	
	public SuperListBasicAdapter(Context context, List<T> data){
		this.context = context;
		this.sourceData = data;
		this.layoutInflater = LayoutInflater.from(this.context);
	}
	
	@Override
	public int getCount() {
		return sourceData != null ? sourceData.size() : 0;
	}

	@Override
	public T getItem(int position) {
		return sourceData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		SuperViewHolder holder = null;
		if(convertView == null){
			convertView = layoutInflater.inflate(getItemResource(), parent, false);
			holder = new SuperViewHolder(convertView);
		}else{
			holder = (SuperViewHolder) convertView.getTag();
		}
		handleViews(position, getItem(position), holder);
		return convertView;
	}
	
	public abstract int getItemResource();
	public abstract void handleViews(int position, T t,SuperViewHolder holder);
	
	public void setOnIEventListener(IEventListener listener){
		this.listener = listener;
	}
}

