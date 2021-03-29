/**
 * Project Name:hse-common-module-phone
 * File Name:DomainListDialog.java
 * Package Name:com.hd.hse.common.module.phone.ui.custom
 * Date:2015年6月11日
 * Copyright (c) 2015, longgang@ushayden.com All Rights Reserved.
 *
*/

package com.hd.hse.common.module.phone.ui.custom;

import java.util.List;

import org.apache.log4j.Logger;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.hd.hse.common.component.phone.event.IEventType;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.common.module.phone.R;
import com.hd.hse.common.module.phone.ui.custom.AlertDialogListView.ViewHolder;
import com.hd.hse.entity.base.Domain;

/**
 * ClassName:DomainListDialog ().<br/>
 * Date:     2015年6月11日  <br/>
 * @author   wen
 * @version  
 * @see 	 
 */
public class DomainListDialog extends Dialog {

	private final Logger logger = LogUtils.getLogger(DomainListDialog.class);
	
	public DomainListDialog(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
	}

	public DomainListDialog(Context context, int theme) {
		super(context, theme);
	}

	public DomainListDialog(Context context) {
		super(context);
	}
	
	// Views
	private TextView tvTitle;
	private ListView lvList;
	
	// Data 
	private List<Domain> mDomains;
	private BaseAdapter mAdapter;
	
	public void setDomains(List<Domain> domains){
		mDomains = domains;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//设置窗口样式.
		Window window = this.getWindow();
		window.setGravity(Gravity.BOTTOM);
		window.setWindowAnimations(R.style.dialogWindowAnim);
		
		WindowManager.LayoutParams params = this.getWindow()
				.getAttributes();
		params.height = LayoutParams.WRAP_CONTENT;
		this.getWindow().setAttributes(params);
		this.getWindow().setBackgroundDrawable(new ColorDrawable(0x00ffffff));
		
		setContentView(R.layout.hd_hse_common_domain_dialog);
		
		tvTitle = (TextView)findViewById(R.id.hd_hse_common_pager_title_tab_dialog_title);
		lvList = (ListView)findViewById(R.id.hd_hse_common_pager_title_tab_dialog_lv);
		
		mAdapter = new MyAdapter();
		lvList.setAdapter(mAdapter);
		lvList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		lvList.setOnItemClickListener(listener);
	}
	
	@Override
	public void show() {
		super.show();
		
		
	}
	
	private class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			if(mDomains != null){
				return mDomains.size();
			}
			return 0;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = View.inflate(getContext(), R.layout.hd_hse_common_module_alertdialog_singleselect_style, null);
				holder.checkView = (CheckedTextView) convertView
						.findViewById(R.id.hd_hse_common_alertdialog_singleselect_cv);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.checkView.setText(mDomains.get(position).getDescription());
			holder.checkView.setHint(mDomains.get(position).getValue());
			if (mDomains.get(position).getIsselected() == 1) {
				holder.checkView.setBackgroundColor(getContext().getResources()
						.getColor(R.color.hd_hse_common_item_down_bg));
				holder.checkView.setChecked(true);
				holder.checkView.setTextColor(getContext().getResources().getColor(R.color.hd_hse_common_white));
				holder.checkView
						.setCheckMarkDrawable(getContext().getResources()
								.getDrawable(R.drawable.hd_hse_common_component_phone_whfhzb_list_icon_checked));
			} else {
				holder.checkView.setChecked(false);
				holder.checkView.setCheckMarkDrawable(null);
				holder.checkView.setTextColor(getContext().getResources().getColor(R.color.hd_hse_common_alerttext_black));
				holder.checkView.setBackgroundColor(getContext().getResources()
						.getColor(R.color.hd_hse_common_item_bg));
			}
			return convertView;
		}
		
	}
	class ViewHolder {
		CheckedTextView checkView;
	}
	
	private IEventListener mIEventListner;
	
	private OnItemClickListener listener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			CheckedTextView checkTV = (CheckedTextView) view
					.findViewById(R.id.hd_hse_common_alertdialog_singleselect_cv);
			Log.i(AlertDialogListView.class.getName(), String.valueOf(position));
			if (mIEventListner != null) {
				
					try {
						for (int i = 0; i < mDomains.size(); i++) {
							mDomains.get(i).setIsselected(0);
						}
						view.setBackgroundColor(getContext().getResources()
								.getColor(R.color.hd_hse_common_item_down_bg));
						mDomains.get(position).setIsselected(1);
						checkTV.toggle();
						checkTV.setCheckMarkDrawable(getContext().getResources()
								.getDrawable(R.drawable.hd_hse_common_component_phone_whfhzb_list_icon_checked));
						checkTV.setTextColor(getContext().getResources().getColor(R.color.hd_hse_common_white));
						mIEventListner.eventProcess(
								IEventType.POPUPWINDOW_CHOICE_MULTIPLEORSINGLE,
								mDomains.get(position));
//						mAlertDialogAdapter.notifyDataSetChanged();
						DomainListDialog.this.dismiss();
					} catch (HDException e) {
						logger.error(e.getMessage());
					}
				
			}
		}
	};

}

