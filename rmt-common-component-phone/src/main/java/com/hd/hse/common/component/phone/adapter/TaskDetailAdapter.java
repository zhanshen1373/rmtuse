package com.hd.hse.common.component.phone.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hd.hse.common.component.phone.R;
import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.entity.other.TaskDetailItem;
import com.hd.hse.entity.other.WorkTaskDetailInfo;

/**
 * 作业票详情 Adapter
 * ClassName: TaskDetailAdapter ()<br/>
 * date: 2015年6月11日  <br/>
 *
 * @author xuxinwen
 * @version
 */
public class TaskDetailAdapter extends BaseAdapter {

	private List<SuperEntity> mDetail;
	
	private Context mContext;
	
	public TaskDetailAdapter(Context context, List<SuperEntity> detail){
		mDetail = detail;
		mContext = context;
	}
	
	@Override
	public int getCount() {
		
		return mDetail.size();
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
		ViewHolder viewHolder = null;
		SuperEntity _item = mDetail.get(position);
		
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = View.inflate(mContext, R.layout.hd_hse_common_component_dialog_detailinfo, null);
			viewHolder.tv01 = (TextView) convertView
					.findViewById(R.id.hd_hse_common_component_dialog_tv01);
			viewHolder.tv02 = (TextView) convertView
					.findViewById(R.id.hd_hse_common_component_dialog_tv02);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		if((boolean) _item.getAttribute("istitle")){
			viewHolder.tv01.setText((CharSequence) _item.getAttribute("descrption"));
		}else{
			viewHolder.tv01.setText((CharSequence) _item.getAttribute("descrption"));
			viewHolder.tv02.setText((CharSequence) _item.getAttribute("desValue"));
		}
		
		return convertView;
	}
	
	class ViewHolder {
		TextView tv01;
		TextView tv02;
	}

}
