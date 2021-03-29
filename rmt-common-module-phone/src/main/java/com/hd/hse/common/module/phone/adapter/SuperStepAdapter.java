/**
 * Project Name:hse-common-component-phone
 * File Name:SuperListServiceAdapter.java
 * Package Name:com.hd.hse.common.component.phone.adapter
 * Date:2015年6月1日
 * Copyright (c) 2015, fulibo@ushayden.com All Rights Reserved.
 *
 */

package com.hd.hse.common.module.phone.adapter;

import android.content.Context;
import android.view.View;
import android.view.View.OnLongClickListener;

import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.module.phone.R;
import com.hd.hse.entity.workorder.RmtWorkPermitMeas;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:SuperListServiceAdapter ().<br/>
 * Date:     2015年6月1日  <br/>
 * @author   flb
 * @version 
 * @see     
 */
public abstract class SuperStepAdapter<T extends SuperEntity> extends SuperListBasicAdapter<T> implements OnLongClickListener, IEventListener{

	/**
	 * 属性列表顺序
	 * 0. 条目左侧显示文本
	 * 1. 条目中间显示文本
	 * 2. 条目右侧显示状态
	 * 3. 条目左侧PC端选中状态
	 * 4. 条目是否被选中的属性
	 * 5. 气体检测措施项的特殊属性
	 */
	protected List<String> attrs;
	protected int[] iconResLight = new int[]{R.drawable.hd_cbs_icon_step_check_no_one, R.drawable.hd_cbs_icon_step_check_yes_one, R.drawable.hd_cbs_icon_step_check_not_sure_one};
	
	public SuperStepAdapter(Context context, List<T> data, List<String> attrs) {
		super(context, data);
		this.attrs = attrs;
	}
	
	public List<T> getSourceValues(){
		return sourceData;
	}
	
	public synchronized void setSourceValues(List<T> data){
		this.sourceData = data;
		this.notifyDataSetChanged();
	}
	
	public List<T> getSelectedValues(){
		List<T> sels = new ArrayList<T>();
		for (int i = 0; i < sourceData.size(); i++) {
			T t = sourceData.get(i);
			boolean isSel = ((RmtWorkPermitMeas)t).getIsselectitem();
			if(t.isModified() || isSel){
				sels.add(t);
			}
		}
		
		if(sels.size() > 0){
			return sels;
		}else{
			return null;
		}
	}
	
	public List<T> getCurrentSelectedValues(){
		List<T> sels = new ArrayList<T>();
		for (int i = 0; i < sourceData.size(); i++) {
			T t = sourceData.get(i);
			boolean isSel = ((RmtWorkPermitMeas)t).getIsselectitem();
			if(isSel){
				sels.add(t);
			}
		}
		
		if(sels.size() > 0){
			return sels;
		}else{
			return null;
		}
	}
	
	public void modifyValues(List<T> values){
		for (int j = 0; j < values.size(); j++) {
			T temp = values.get(j);
			for (int i = 0; i < sourceData.size(); i++) {
				T entity = sourceData.get(i);
				if (entity.getAttribute(entity.getPrimaryKey()).equals(temp.getAttribute(temp.getPrimaryKey()))) {
					temp.setModified(false);
					((RmtWorkPermitMeas)temp).setIsselectitem(false);
					sourceData.set(i, temp);
				}
			}
		}
		this.notifyDataSetChanged();
	}
	
	public void modifyCurrentValues(List<T> values){
		for (int j = 0; j < values.size(); j++) {
			T temp = values.get(j);
			for (int i = 0; i < sourceData.size(); i++) {
				T entity = sourceData.get(i);
				if (entity.equals(temp)) {
//					((RmtWorkPermitMeas)temp).setIsselectitem(false);
					sourceData.set(i, temp);
				}
			}
		}
		this.notifyDataSetChanged();
	}
	
	@Override
	public boolean onLongClick(View v) {
		Integer position = (Integer) v.getTag();
		List<String> attributes = new ArrayList<String>();
		attributes.add("meas_description");
		attributes.add("confirm");
		final StepDialogManager dialogManager = StepDialogManager.getInstance();
		
		dialogManager.showDialog(context, sourceData, position, attributes);
		dialogManager.setOnIEventListener(this);
		
		return true;
	}
	
	
}
