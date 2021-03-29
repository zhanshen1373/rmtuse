/**
 * 
 */
package com.hd.hse.common.component.phone.adapter;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import android.content.Context;
import android.graphics.Color;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hd.hse.common.component.phone.R;
import com.hd.hse.common.component.phone.custom.EditableDialogManager;
import com.hd.hse.common.component.phone.custom.ProcessLine;
import com.hd.hse.common.component.phone.event.IEventType;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.component.phone.util.ExamineNodeUtils;
import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;

/**
 * ClassName:ListViewAdapter Date: Jan 6, 2015
 * 
 * @author liyich
 * 
 */
public class SignListViewAdapter extends BaseAdapter {

	private static Logger logger = LogUtils
			.getLogger(SignListViewAdapter.class);

	private Context context;

	private LayoutInflater mLayoutInflater;

	/**
	 * edManager:TODO(弹出框).
	 */
	private EditableDialogManager edManager;

	/**
	 * currentTouchPosition:TODO(当前点击ITEM位置).
	 */
	private int selectedItemPosition = 0;

	/**
	 * superEntitiesInfos:TODO(审批环节list).
	 */
	private List<SuperEntity> superEntitiesInfos;

	/**
	 * sEntity:TODO(审批环节).
	 */
	private static SuperEntity sEntity;

	/**
	 * RINGNODE_DESC:TODO(审批环节点).
	 */
	private String RINGNODE_DESC = "spfield_desc";

	/**
	 * RINGNODE_VALUE:TODO(刷卡人描述).
	 */
	private String RINGNODE_VALUE = "persondesc";

	/**
	 * isEnable:TODO(是否可用).
	 */
	private String isEnableAttr = "isexmaineable";

	/**
	 * BPERMULCARD:TODO(是否多人刷卡).
	 */
	private String BPERMULCARD = "bpermulcard";

	/**
	 * SPTIME:TODO(审批时间).
	 */
	private String SPTIME = "sptime";

	/**
	 * listener:TODO(公共调用接口).
	 */
	private IEventListener listener;

	/**
	 * 组名称
	 */
	private Object groupObj;

	/**
	 * group:TODO(组的属性字段).
	 */
	private String group = "pdapaixu";

	/**
	 * isGreen:TODO(标记环节是否是圆圈).
	 */
	private Boolean isCircle = false;

	/**
	 * isBeforeGeeen:TODO(记录前一组环节颜色).
	 */
	private Boolean isBeforeGeeen = false;

	/**
	 * CURRENTGROUPCOLOR:TODO(会签审批进度标记).
	 */
	private String CURRENTGROUPCOLOR = "currentgroupcolor";
	
	/**
	 * GROUPHEADER:TODO(组头).
	 */
	private String GROUPHEADER="groupheader";

	private int green;

	private int gray;

	public SignListViewAdapter(List<SuperEntity> superEntitiesInfos,
			Context context, Map<String, String> map) {
		// 计算状态
		ExamineNodeUtils.sortExamineNode(superEntitiesInfos);
		// 初始化会签审批进行情况
		initWorkAppColor(superEntitiesInfos);

		this.context = context;
		mLayoutInflater = LayoutInflater.from(context);
		this.superEntitiesInfos = superEntitiesInfos;
		// 设置组头字段值
		setUpGroupHead();
		// 初始化长按弹出框对象
		edManager = new EditableDialogManager();

		green = context.getResources().getColor(
				R.color.hd_hse_common_module_phone_countersign_green);
		gray = context.getResources().getColor(
				R.color.hd_hse_common_module_phone_countersign_gray);
	}

	/**
	 * setOnClickListener:(单击事件). <br/>
	 * 
	 * @param listener
	 */
	public void setOnClickListener(IEventListener listener) {
		this.listener = listener;
	}

	private boolean getIsEnable(SuperEntity msuperEntity) {

		int ret = 0;
		if (msuperEntity != null) {
			ret = Integer
					.parseInt(msuperEntity.getAttribute(isEnableAttr) == null ? "0"
							: msuperEntity.getAttribute(isEnableAttr)
									.toString());
		}
		// 大于0，这个SuperEntity可用。
		return ret > 0 ? true : false;
	}

	@Override
	public int getCount() {
		return superEntitiesInfos.size();
	}

	@Override
	public Object getItem(int position) {
		return superEntitiesInfos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		sEntity = superEntitiesInfos.get(position);

		final SuperEntity bean = (SuperEntity) sEntity;
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mLayoutInflater.inflate(
					R.layout.hd_hse_common_component_phone_signlistview_item,
					parent, false);

			// findViewById
			viewHolder.iv_icon = (ProcessLine) convertView
					.findViewById(R.id.processline);
			viewHolder.item_title = (TextView) convertView
					.findViewById(R.id.item_title);
			viewHolder.item_name = (TextView) convertView
					.findViewById(R.id.item_name);
			viewHolder.onImageView = (ImageView) convertView
					.findViewById(R.id.clickImageView);
			// 设置复用
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		// 长按刷卡人描述弹出全部人员名称
		viewHolder.item_name.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				if (bean.getAttribute(RINGNODE_VALUE) != null) {
					edManager
							.showDialog(context,
									bean.getAttribute(RINGNODE_VALUE)
											.toString(), false);
				}
				return false;
			}
		});
		setView(position, sEntity, viewHolder);

		return convertView;
	}

	/**
	 * 
	 * 设置View状态
	 */
	private void setView(int position, SuperEntity sEntity,
			ViewHolder viewHolder) {
		if (sEntity.getAttribute(RINGNODE_DESC) != null) {
			// 设置审批环节点描述
			viewHolder.item_title.setText(sEntity.getAttribute(RINGNODE_DESC)
					.toString().split("#").length > 1 ? sEntity
					.getAttribute(RINGNODE_DESC).toString().split("#")[1]
					.concat("：") : sEntity.getAttribute(RINGNODE_DESC)
					.toString().concat("："));
		}

		if (sEntity.getAttribute(RINGNODE_VALUE) != null) {
			// 设置刷卡人描述
			viewHolder.item_name.setText(splitWorkName(sEntity.getAttribute(
					RINGNODE_VALUE).toString()));
		} else {
			viewHolder.item_name.setText("");
		}

		// 是否允许多人刷卡
		if (sEntity.getAttribute(BPERMULCARD) != null
				&& sEntity.getAttribute(BPERMULCARD).toString()
						.equalsIgnoreCase("1")) {
			LayoutParams tv_name = viewHolder.item_name.getLayoutParams();
			// 高度变大
			tv_name.height = 85;
			// 可以滑动
			viewHolder.item_name.setMovementMethod(ScrollingMovementMethod
					.getInstance());
			viewHolder.item_name.setLayoutParams(tv_name);
		}
		
		if(sEntity.getAttribute(group) != null && 
				sEntity.getAttribute(GROUPHEADER).equals(true)){
			// 画圆圈
			drawCircleorLine(viewHolder, true);
			// 是圆圈
			isCircle = true;
		}else{
			// 画线条
			drawCircleorLine(viewHolder, false);
			// 是线条
			isCircle = false;
		}
		
		// 设置是否可点击
		if (getIsEnable(SignListViewAdapter.sEntity)) {
			// 新文改
			viewHolder.onImageView.setEnabled(true);

			viewHolder.onImageView
					.setImageResource(R.drawable.hd_hse_common_module_countersign_image_touch_style);

			// 点击事件（传入位置）
			viewHolder.onImageView
					.setOnClickListener(new ItemOnClick(position));

			drawLineColor(position, viewHolder);
		} else {
			// 不可点击
			viewHolder.onImageView.setEnabled(false);
			viewHolder.onImageView
					.setImageResource(R.drawable.hd_hse_main_phone_app_portrait_3);
			drawLineColor(position, viewHolder);
		}

	}

	/**
	 * drawLineColor:(线条颜色). <br/>
	 * date: 2015年1月29日 <br/>
	 * 
	 * @author wenlin
	 * @param swingTime
	 *            当前审批环节刷卡时间
	 * @param viewHolder
	 */
	private void drawLineColor(int position, ViewHolder viewHolder) {
		// 同一所在组已有刷卡环节，则线条为绿色
		if (sEntity.getAttribute(CURRENTGROUPCOLOR) != null
				&& sEntity.getAttribute(CURRENTGROUPCOLOR).equals(true)) {
			// 第一组第一个审批环节
			if (isCircle && position == 0 && superEntitiesInfos.size() != 1) {
				setFirstGreenColor(viewHolder);
			}
			// 一共一个审批环节点，只显示圈圈
			else if (isCircle && position == 0
					&& superEntitiesInfos.size() == 1) {
				setColor(viewHolder, green, Color.WHITE, Color.WHITE);
			}
			// 是最后一组唯一一个审批环节
			else if (isCircle && position == (superEntitiesInfos.size() - 1)) {
				setLastGreenColor(viewHolder);
			} else {
				// 线条是绿色
				setColor(viewHolder, green, green, green);
			}
			isBeforeGeeen = true;
		} else {
			// 第一组第一个审批环节
			if (isCircle && position == 0 && superEntitiesInfos.size() != 1) {
				setFirstGrayColor(viewHolder);
			}
			// 一共一个审批环节点，只显示圈圈
			else if (isCircle && position == 0
					&& superEntitiesInfos.size() == 1) {
				setColor(viewHolder, gray, Color.WHITE, Color.WHITE);
			}
			// 是最后一组唯一一个审批环节
			else if (isCircle && position == (superEntitiesInfos.size() - 1)
					&& isBeforeGeeen) {
				setColor(viewHolder, gray, green, Color.WHITE);
			}
			// 是最后一组唯一一个审批环节
			else if (isCircle && position == (superEntitiesInfos.size() - 1)
					&& !isBeforeGeeen) {
				setColor(viewHolder, gray, gray, Color.WHITE);
			} else if (isCircle && isBeforeGeeen) {
				// 上、圆圈是绿色，下是灰色
				setColor(viewHolder, gray, green, gray);
			} else {
				// 是线条则灰色
				setColor(viewHolder, gray, gray, gray);
			}
			isBeforeGeeen = false;
		}
	}

	/**
	 * TODO 初始化会签审批进行状况 <br/>
	 * initWorkAppColor:(). <br/>
	 * date: 2015年2月2日 <br/>
	 * 
	 * @author wenlin
	 */
	public void initWorkAppColor(List<SuperEntity> superEntitiesInfos) {
		for (int i = 0; i < superEntitiesInfos.size(); i++) {
			// 审批环节审批时间不为空
			if (superEntitiesInfos.get(i).getAttribute(SPTIME) != null
					&& !"".equals(superEntitiesInfos.get(i)
							.getAttribute(SPTIME))) {
				for (int j = 0; j < superEntitiesInfos.size(); j++) {
					if (superEntitiesInfos
							.get(j)
							.getAttribute(group)
							.equals(superEntitiesInfos.get(i).getAttribute(
									group)))
						// 将同组打上进度标识
						superEntitiesInfos.get(j).setAttribute(
								CURRENTGROUPCOLOR, true);
				}
			}
		}
	}

	/**
	 * TODO 超过50个字符用省略号代替 splitWorkName:(). <br/>
	 * date: 2015年3月3日 <br/>
	 * 
	 * @author wenlin
	 * @param src
	 * @return
	 */
	public String splitWorkName(String src) {
		StringBuffer _result = new StringBuffer(50);
		if (src != null && !src.isEmpty()) {
			char[] _charArray = src.toCharArray();

			for (int i = 0; i < _charArray.length; i++) {
				_result.append(_charArray[i]);
				if (_result.toString().getBytes().length > 50) {
					_result.deleteCharAt(_result.length() - 1);
					_result.append("...");
					break;
				}
			}
		}
		return _result.toString();
	}

	/**
	 * TODO 检测已刷卡所在组的颜色。如果当前审批环节不是所在组的第一个，则将之前的审批环节的画线为绿色
	 * checkCurrentGroupColor:(). <br/>
	 * date: 2015年1月29日 <br/>
	 * 
	 * @param 当前位置
	 * @author wenlin
	 */
	// private void checkCurrentGroupColor(int position) {
	// // 所在组默认首位置
	// int firstInGroup = -1;
	// // 遍历缓存中审批环节
	// for (int i = 0; i < mHaspMap.size(); i++) {
	// // 和当前审批环节是同一组且小于当前位置下标
	// if (groupObj.equals(((SuperEntity) mHaspMap.get(i)[0])
	// .getAttribute(group)) && i < position) {
	// if (firstInGroup == -1) {
	// // 画圆
	// drawCircleorLine((ViewHolder) mHaspMap.get(i)[1], true);
	// firstInGroup = i;
	// } else {
	// // 画线
	// drawCircleorLine((ViewHolder) mHaspMap.get(i)[1], false);
	// }
	// }
	// }
	// }

	/**
	 * 
	 * 
	 */
	private void drawCircleorLine(ViewHolder viewHolder, boolean b) {
		viewHolder.iv_icon.setDrawModel(b);
		viewHolder.iv_icon.invalidate();
	}

	/**
	 * TODO 设置画笔的颜色 setColor:(). <br/>
	 * date: 2015年1月30日 <br/>
	 * 
	 * @author wenlin
	 * @param viewHolder
	 *            所在审批环节布局
	 * @param circleColoe
	 *            圆圈
	 * @param upColor
	 *            圆圈上部分线条
	 * @param downColor
	 *            圆圈下部分线条
	 */
	private void setColor(ViewHolder viewHolder, int circleColoe, int upColor,
			int downColor) {
		viewHolder.iv_icon.setColor(circleColoe, upColor, downColor);
	}

	/**
	 * setFirstGreenColor:(会签第一组第一个审批环节（绿色）). <br/>
	 * date: 2015年1月30日 <br/>
	 * 
	 * @author wenlin
	 * @param viewHolder
	 */
	private void setFirstGreenColor(ViewHolder viewHolder) {
		viewHolder.iv_icon.setColor(green, Color.WHITE, green);
	}

	/**
	 * setFirstGreenColor:(会签第一组第一个审批环节（灰色）). <br/>
	 * date: 2015年1月30日 <br/>
	 * 
	 * @author wenlin
	 * @param viewHolder
	 */
	private void setFirstGrayColor(ViewHolder viewHolder) {
		viewHolder.iv_icon.setColor(gray, Color.WHITE, gray);
	}

	/**
	 * setFirstGreenColor:(会签最后一组第一个审批环节（绿色）). <br/>
	 * date: 2015年1月30日 <br/>
	 * 
	 * @author wenlin
	 * @param viewHolder
	 */
	private void setLastGreenColor(ViewHolder viewHolder) {
		viewHolder.iv_icon.setColor(green, green, Color.WHITE);
	}

	/**
	 * setCurrentWAPermission:(设置当前审批环节点). <br/>
	 * date: 2015年1月29日 <br/>
	 * 
	 * @author wenlin
	 * @param currentWAP
	 */
	public void setCurrentWAPermission(SuperEntity currentWAP) {
		// 设置当前审批环节为绿色标志位
		currentWAP.setAttribute(CURRENTGROUPCOLOR, true);
		// 和当前同一组的审批环节点设置绿色标志位
		for (int i = 0; i < superEntitiesInfos.size(); i++) {
			if (currentWAP.getAttribute(group).equals(
					superEntitiesInfos.get(i).getAttribute(group))) {
				superEntitiesInfos.get(i).setAttribute(CURRENTGROUPCOLOR, true);
			}
		}
		// 删除所点击审批环节
		superEntitiesInfos.remove(selectedItemPosition);
		// 装载更新后的审批环节
		superEntitiesInfos.add(selectedItemPosition, currentWAP);
		// 计算状态
		ExamineNodeUtils.sortExamineNode(superEntitiesInfos);

		refresh();
	}
	
	
	/**
	 * setUpGroupHead:(设置组头字段值). <br/>
	 * date: 2015年3月25日 <br/>
	 *
	 * @author wenlin
	 */
	public void setUpGroupHead(){
		// 组编号
		Object groupNum = null;
		for(int i = 0 ; i < superEntitiesInfos.size() ; i++){
			if(groupNum == null){
				superEntitiesInfos.get(i).setAttribute(GROUPHEADER, true);
				groupNum = superEntitiesInfos.get(i).getAttribute(group);
				continue;
			}
			// 当前组和上一组是同一组
			if(groupNum.equals(superEntitiesInfos.get(i).getAttribute(group))){
				superEntitiesInfos.get(i).setAttribute(GROUPHEADER, false);
			}else{
				superEntitiesInfos.get(i).setAttribute(GROUPHEADER, true);
			}
			groupNum = superEntitiesInfos.get(i).getAttribute(group);
		}
	}
	
	/**
	 * refresh:(刷新当前审批环节点). <br/>
	 * date: 2015年1月29日 <br/>
	 * 
	 * @author wenlin
	 */
	public void refresh() {
		this.notifyDataSetChanged();
	}

	static class ViewHolder {
		// 最左边图标
		ProcessLine iv_icon;
		// item_title
		TextView item_title;
		// item_name
		TextView item_name;
		// 最右边图标
		ImageView onImageView;
	}

	/**
	 * ClassName:ItemOnClick Date: Jan 29, 2015 ListView Item 单击事件
	 * 
	 * @author liyich
	 * 
	 */
	class ItemOnClick implements OnClickListener {
		// 当前点击所在位置
		private int position;

		public ItemOnClick(int position) {
			this.position = position;
		}

		/*
		 * 单击事件
		 */
		@Override
		public void onClick(View v) {
			// 记录当前点击的位置
			selectedItemPosition = position;
			try {
				listener.eventProcess(IEventType.SIGN_CLICK,
						superEntitiesInfos.get(position));
			} catch (HDException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

}