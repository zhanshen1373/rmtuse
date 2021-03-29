package com.hd.hse.common.component.phone.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hd.hse.common.component.phone.R;
import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.entity.workorder.WorkApprovalPersonRecord;

/**
 * ClassName: TimeLineExpanableListAdapter (ExplanableList 时间轴的Adapter)<br/>
 * date: 2015年7月30日 <br/>
 * 
 * @author lxf
 * @version
 */
public class TimeLineExpanableListAdapter extends BaseExpandableListAdapter {

	private LayoutInflater inflater = null;
	/**
	 * oneListData:TODO(数据源).
	 */
	private List<SuperEntity> oneListData;
	private Context context;
	/**
	 * childKey:TODO(取子表数据的KEY).
	 */
	private String childKey = WorkApprovalPersonRecord.class.getName();
	/**
	 * title:TODO(标题).
	 */
	private String title = "value";
	/**
	 * showName:TODO(子项，第一列).
	 */
	private String showName = "person_name";
	/**
	 * showTime:TODO(子项，第二列).
	 */
	private String showTime = "sptime";

	public TimeLineExpanableListAdapter(Context context) {
		// oneListData = oneList;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;
	}

	public TimeLineExpanableListAdapter(Context context, String childKey,
			String titile, String showname, String showTime) {
		// oneListData = oneList;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;
		this.childKey = childKey;
		this.title = titile;
		this.showName = showname;
		this.showTime = showTime;
	}

	/**
	 * setDataInfo:(设置数据). <br/>
	 * date: 2015年8月3日 <br/>
	 * 
	 * @author lxf
	 * @param oneList
	 */
	public void setDataInfo(List<SuperEntity> oneList) {
		if (oneListData == null) {
			oneListData = oneList;
		} else {
			oneListData.clear();
			oneListData.addAll(oneList);
		}
	}

	/**
	 * TODO 获取子表数据信息
	 * 
	 * @see android.widget.ExpandableListAdapter#getChild(int, int)
	 */
	@Override
	public Object getChild(int arg0, int arg1) {
		// TODO Auto-generated method stub
		if (oneListData == null) {
			return null;
		}
		return oneListData.get(arg0).getChild(childKey).get(arg1);
		// return
		// ((OneStatusEntity)oneListData.get(arg0)).getTwoList().get(arg1);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		ChildViewHolder viewHolder = null;
		SuperEntity entity = (SuperEntity) getChild(groupPosition,
				childPosition);
		if (convertView != null) {
			viewHolder = (ChildViewHolder) convertView.getTag();
		} else {
			viewHolder = new ChildViewHolder();
			convertView = inflater.inflate(
					R.layout.hd_hse_common_component_timeline_itemp_two, null);
			viewHolder.actionName = (TextView) convertView
					.findViewById(R.id.action_name);
			viewHolder.actionTime = (TextView) convertView
					.findViewById(R.id.action_time);
			convertView.setTag(viewHolder);
			viewHolder.child_line = (LinearLayout) convertView
					.findViewById(R.id.hd_hse_component_timeline_itemlIneid);
		}
		// 设置第二级时间和事件名称  表示取#后边的内容
		viewHolder.actionName.setText(entity.getAttribute(showName) == null ? "" : entity
				.getAttribute(showName).toString());
		viewHolder.actionTime
				.setText(entity.getAttribute(showTime) == null ? "" : entity
						.getAttribute(showTime).toString());
		// 注册点击事件
		viewHolder.child_line.setOnClickListener(new lineOnClickListener(
				entity, groupPosition, childPosition));

		return convertView;

	}

	@Override
	public int getChildrenCount(int groupPosition) {
		if (oneListData == null) {
			return 0;
		}
		if (oneListData.size() <= (groupPosition)) {
			return 0;
		}
		if (oneListData.get(groupPosition).getChild(childKey) == null) {
			return 0;
		} else {
			return oneListData.get(groupPosition).getChild(childKey).size();
		}
	}

	@Override
	public Object getGroup(int arg0) {
		if (oneListData == null) {
			return null;
		}
		// TODO Auto-generated method stub
		return oneListData.get(arg0);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		if (oneListData == null) {
			return 0;
		}
		return oneListData.size();
	}

	@Override
	public long getGroupId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		GroupViewHolder holder;

		if (convertView == null) {
			holder = new GroupViewHolder();
			convertView = inflater.inflate(
					R.layout.hd_hse_common_component_timeline_itemp_one, null);
			// 设置第一级月份
			holder.groupTitle = (TextView) convertView
					.findViewById(R.id.hd_hse_component_timeline_title);
			holder.group_tiao = (TextView) convertView
					.findViewById(R.id.hd_hse_component_timeline_line_top);
			holder.groupImageView = (ImageView) convertView
					.findViewById(R.id.hd_hse_component_timeline_icon);
			convertView.setTag(holder);
		} else {
			holder = (GroupViewHolder) convertView.getTag();
		}

		String tempTitile = oneListData.get(groupPosition)
				.getAttribute(title).toString();
		String str[]=tempTitile.split("#");
		holder.groupTitle.setText(str.length>1?str[1]:tempTitile);
		if (groupPosition == 0) {
			holder.group_tiao.setVisibility(View.INVISIBLE);
			holder.groupImageView
					.setImageResource(R.drawable.hd_hse_common_component_timeline_ing);
		} else {
			if (holder.group_tiao.getVisibility() == View.INVISIBLE) {
				holder.group_tiao.setVisibility(View.VISIBLE);
				holder.groupImageView
						.setImageResource(R.drawable.hd_hse_common_component_timeline_ed);
			}
		}
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		// 表示启用子项的点击事件
		return true;
	}

	public class lineOnClickListener implements OnClickListener {
		private Object obj;
		private int groupPosition;
		private int childPosition;

		public lineOnClickListener(Object obj, int groupPosition,
				int childPosition) {
			this.groupPosition = groupPosition;
			this.childPosition = childPosition;
			this.obj = obj;
		}

		@SuppressLint("ResourceAsColor")
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			// Log.e("lxf", "1");
			// if (oldview != null) {
			// oldview.setBackgroundColor(R.color.yellow);
			// }
			// //arg0.setBackgroundColor(R.color.grey);
			// oldview = arg0;
			//Toast.makeText(context, "mingzi", Toast.LENGTH_LONG).show();
		}

	}

	private class GroupViewHolder {
		TextView groupTitle;
		ImageView groupImageView;
		public TextView group_tiao;
	}

	private class ChildViewHolder {
		public TextView actionName;
		public TextView actionTime;
		public LinearLayout child_line;
	}

}
