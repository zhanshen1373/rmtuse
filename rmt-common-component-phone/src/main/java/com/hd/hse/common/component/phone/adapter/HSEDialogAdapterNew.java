package com.hd.hse.common.component.phone.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hd.hse.common.component.phone.R;
import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;

import java.util.List;

public class HSEDialogAdapterNew extends BaseAdapter {

	private List<SuperEntity> listSuperEntity;
	private LayoutInflater inflater;
	/**
	 * arrayDesc:TODO(字段描述).
	 */
	public String titleAtr = null;
	/**
	 * arrayNum:TODO(字段编码).
	 */
	public String vauleAtr = null;
	/**
	 * titleTag:TODO(是否字段的标记).
	 */
	public String titleTag = null;
	
	private Context context;

	public HSEDialogAdapterNew() {
		// TODO Auto-generated constructor stub
	}

	public HSEDialogAdapterNew(Context context, List<SuperEntity> superEntity,
			String title, String value, String tag) throws HDException {
		this.listSuperEntity = superEntity;
		//if (StringUtils.isEmpty(title)) {
		if (TextUtils.isEmpty(title)) {
			throw new HDException("请传入获取标题的属性");
		}
		if (TextUtils.isEmpty(value)) {
			throw new HDException("请传入获取值的属性");
		}
		this.titleAtr = title;
		this.titleAtr = value;
		this.inflater = LayoutInflater.from(context);
		this.context = context;
	}

	public HSEDialogAdapterNew(Context context) {
		this.inflater = LayoutInflater.from(context);
		this.context = context;
	}

	@Override
	public int getCount() {
		return listSuperEntity == null ? 0 : listSuperEntity.size();
	}

	@Override
	public Object getItem(int position) {
		return listSuperEntity == null ? null : listSuperEntity.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	private Object temptitle;
	private Object tempvalue;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		boolean isQtjc = false;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(
					R.layout.hd_hse_common_component_dialog_detailinfo, null);
			viewHolder.rowid = (LinearLayout) convertView
					.findViewById(R.id.hd_hse_common_component_dialog_rowid);
			viewHolder.tv01 = (TextView) convertView
					.findViewById(R.id.hd_hse_common_component_dialog_tv01);
			viewHolder.tv02 = (TextView) convertView
					.findViewById(R.id.hd_hse_common_component_dialog_tv02);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		temptitle = listSuperEntity.get(position).getAttribute(titleAtr);
		tempvalue = listSuperEntity.get(position).getAttribute(vauleAtr);
		if (temptitle.equals("是否合格:")) {
			isQtjc = true;
			tempvalue = tempvalue.equals("1") ? "是" : "否";
		}
		Object isTitle = listSuperEntity.get(position).getAttribute(titleTag);
		if (isTitle instanceof Boolean && (Boolean) isTitle == true) {
			viewHolder.rowid
					.setBackgroundResource(R.drawable.hd_component_step_check_item_bg_selected_current);
			// 设置字段描述
			viewHolder.tv01.setText(temptitle == null ? "" : temptitle
					.toString());
			viewHolder.tv02.setText("");

		} else {
			viewHolder.rowid.setBackgroundColor(Color.WHITE);// (R.drawable.hd_component_step_check_item_bg_selected_current);
			// 设置字段描述
			viewHolder.tv01.setText(temptitle == null ? "" : temptitle
					.toString());
			// 设置字段值
			viewHolder.tv02.setText(tempvalue == null ? "(空)" : tempvalue
					.toString());
			if (isQtjc) {
				if (tempvalue.equals("是")) {
					viewHolder.tv02.setTextColor(context.getResources().getColor(R.color.hd_hse_common_green));
				} else {
					viewHolder.tv02.setTextColor(context.getResources().getColor(R.color.hd_hse_common_red));
				}
			} else {
				viewHolder.tv02.setTextColor(context.getResources().getColor(R.color.hd_hse_common_alerttext_black));
			}
		}
		return convertView;
	}

	class ViewHolder {
		LinearLayout rowid;
		TextView tv01;
		TextView tv02;
	}

	/**
	 * setDataList:(表示初始化设置设数据). <br/>
	 * date: 2015年6月11日 <br/>
	 * 
	 * @author lxf
	 * @param data
	 */
	public void setInitDataList(List<SuperEntity> data) {
		listSuperEntity = data;
	}

	public void setCurentSuperEntity(List<SuperEntity> superEntity) {
		listSuperEntity = superEntity;
		this.notifyDataSetChanged();
	}
}
