package com.hd.hse.common.component.phone.custom;

import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hd.hse.common.component.phone.R;
import com.hd.hse.common.component.phone.listener.IEventListener;

/**
 * ClassName: CustomRecyclerView (自定义复用控件，注意V7包中含有的控件)<br/>
 * date: 2015年8月5日 <br/>
 * 
 * @author lxf
 * @version
 */
public class CustomRecyclerView extends RecyclerView {

	private Context context;
	private List<String> mdate;
	private List<Integer> imagedate;
	private List<Integer> superScriptDatas;
	private RecyclerAdapter reAdapter;
	public final int rowcount = 6;
	/**
	 * ieventListener:TODO(设置事件).
	 */
	private IEventListener ieventListener;
	
	public CustomRecyclerView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		init();
	}

	public CustomRecyclerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}

	public CustomRecyclerView(Context context) {
		super(context);
		this.context = context;
		init();
	}
	/**
	 * setOnClickListener:(设置事件). <br/>
	 * date: 2015年8月7日 <br/>
	 * 
	 * @author lxf
	 * @param onclick
	 */
//	public void setOnIEventListener(IEventListener onclick) {
//		ieventListener = onclick;
//	}
	/**
	 * init:(初始化参数). <br/>
	 * date: 2015年8月5日 <br/>
	 * 
	 * @author lxf
	 */
	private void init() {
		reAdapter = new RecyclerAdapter();
		// 设置一行显示的内容个数
		setGridLayoutManager(rowcount);
		// 添加分隔线
		// this.addItemDecoration(new DividerGridItemDecoration(context));
		this.setAdapter(reAdapter);
	}

	/**
	 * setLayout:(设置gridvie 布局每行显示的个数). <br/>
	 * date: 2015年8月5日 <br/>
	 * 
	 * @author lxf
	 * @param adapter
	 */
	public void setGridLayoutManager(int spanCount) {
		// GridLayoutManager gm=new GridLayoutManager(context, 4);
		setLayoutManager(new GridLayoutManager(context, spanCount));
	}

	/**
	 * setdate:(设置数据源). <br/>
	 * date: 2015年8月5日 <br/>
	 * 
	 * @author lxf
	 * @param obj
	 */
	public void setdate(List<String> obj, List<Integer> imagedate, List<Integer> superScript) {
		mdate = obj;
		this.imagedate = imagedate;
		this.superScriptDatas = superScript;
		if (reAdapter != null) {
			reAdapter.setData(mdate);
			reAdapter.notifyDataSetChanged();
		}
	}

	/**
	 * refresh:(表示刷新动作). <br/>
	 * date: 2015年8月5日 <br/>
	 * 
	 * @author lxf
	 */
//	public void refresh() {
//		if (reAdapter != null) {
//			reAdapter.notifyDataSetChanged();
//		}
//	}

	/**
	 * ClassName: RecyclerAdapter (自定义适配器)<br/>
	 * date: 2015年8月5日 <br/>
	 * 
	 * @author lxf
	 * @version CustomRecyclerView
	 */
	public class RecyclerAdapter extends
			RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

		private List<String> listdate;

		public RecyclerAdapter(List<String> obj) {
			listdate = obj;
		}

		public RecyclerAdapter() {

		}

		/**
		 * setData:(设置数据源). <br/>
		 * date: 2015年8月6日 <br/>
		 *
		 * @author lxf
		 * @param obj
		 */
		public void setData(List<String> obj) {
			listdate =  obj;
		}
		// 用于创建控件
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parentViewGroup, int i) {
			// 获得列表项控件（LinearLayer对象）
			// item是LinearLayout对象
			View item = LayoutInflater
					.from(parentViewGroup.getContext())
					.inflate(
							R.layout.hd_hse_component_expandablelist_task_item_three,
							parentViewGroup, false);
			return new ViewHolder(item);

		}

		// 为控件设置数据
		@Override
		public void onBindViewHolder(ViewHolder viewHolder, final int position) {
			// 获取当前item中显示的数据
			final String rowData = listdate.get(position);
			// 设置要显示的数据
			if(position<imagedate.size() && imagedate.get(position)!=null){
				Drawable drawabletop=getResources().getDrawable((int) imagedate.get(position));
				drawabletop.setBounds(0, 0, drawabletop.getMinimumWidth(), drawabletop.getMinimumHeight());
//				viewHolder.textViewSample.setCompoundDrawables(null, drawabletop, null, null);
				viewHolder.imageView.setImageDrawable(drawabletop);
				viewHolder.imageView.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						Toast.makeText(context, "click", Toast.LENGTH_SHORT).show();
					}
				});
			}
			// 设置要显示的数据
			if(position<superScriptDatas.size() && superScriptDatas.get(position)!=null){
				Drawable drawabletop=getResources().getDrawable((int) superScriptDatas.get(position));
//				drawabletop.setBounds(0, 0, drawabletop.getMinimumWidth(), drawabletop.getMinimumHeight());
				viewHolder.superScript.setImageDrawable(drawabletop);
			}
//			viewHolder.textViewSample.setText(rowData);
			viewHolder.textView.setText(rowData);
			viewHolder.itemView.setTag(rowData);
		}

		@Override
		public int getItemCount() {
			if (listdate == null) {
				return 0;
			}
			return listdate.size();
		}

//		// 删除指定的Item
//		public void removeData(int position) {
//			listdate.remove(position);
//			// 通知RecyclerView控件某个Item已经被删除
//			notifyItemRemoved(position);
//		}
//
//		// 在指定位置添加一个新的Item
//		public void addItem(int positionToAdd) {
//			listdate.add(positionToAdd, "新的列表项" + new Random().nextInt(10000));
//			// 通知RecyclerView控件插入了某个Item
//			notifyItemInserted(positionToAdd);
//		}

		public class ViewHolder extends RecyclerView.ViewHolder {

//			private final TextView textViewSample;
			private final ImageView imageView;
			private final ImageView superScript;
			private final TextView textView;

			public ViewHolder(View itemView) {
				super(itemView);
//				textViewSample = (TextView) itemView
//						.findViewById(R.id.hd_hse_component_phone_grid_item_texticon_three_layer);
				imageView = (ImageView) itemView.findViewById(R.id.hd_hse_component_phone_grid_item_texticon_img);
				superScript = (ImageView) itemView.findViewById(R.id.hd_hse_component_phone_grid_item_texticon_superscript);
				textView = (TextView) itemView.findViewById(R.id.hd_hse_component_phone_grid_item_texticon_text);

				itemView.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						Toast.makeText(context, "click", Toast.LENGTH_SHORT).show();
					}
				});
			}
		}

	}
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		return true;
	}

}
