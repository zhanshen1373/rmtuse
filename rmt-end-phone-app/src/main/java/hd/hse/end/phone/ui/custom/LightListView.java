/**
 * Project Name:incubator
 * File Name:NoReuseListView.java
 * Package Name:com.example.incubator
 * Date:2015��6��10��
 * Copyright (c) 2015, longgang@ushayden.com All Rights Reserved.
 *
*/
package hd.hse.end.phone.ui.custom;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * ClassName:NoReuseListView ().<br/>
 * Date:     2015年6月10日  <br/>
 * @author   wen
 * @version  
 * @see 	 
 */
@SuppressLint("NewApi")
public class LightListView extends ScrollView {

	/*
	 * 因为在往 LinearLayout中添加View 的时候还会给View 之间添加分割线，
	 * 所以在LinearLayout中的顺序与View本身的 Position出现偏差，为方便的
	 * 记录每个View的Position，添加这个集合，来记录View的position。
	 */
	private List<View> mChilds = new ArrayList<View>();
	
	public LightListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public LightListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public LightListView(Context context) {
		super(context);
		init();
	}
	
	private LinearLayout mContainer;
	private void init(){
		setBackgroundColor(0xffffffff);
		mContainer = new LinearLayout(getContext());
		mContainer.setOrientation(LinearLayout.VERTICAL);
		this.addView(mContainer);
	}
	
	private BaseAdapter mAdapter;
	
	public void setAdapter(BaseAdapter adapter){
		mAdapter = adapter;
		refreshList();
	}
	
	/**
	 * 得到逻辑位置上的 Child
	 * TODO 
	 * @see android.view.ViewGroup#getChildAt(int)
	 */
	public View getLightViewChildAt(int location){
		return mChilds.get(location);
	}
	
	private void refreshList(){
		LinearLayout _container = mContainer;
		_container.removeAllViews();
		BaseAdapter _adapter = mAdapter;
		if(_adapter == null){
			return;
		}
		int _count = _adapter.getCount();
		List<View> _childs = mChilds;
		for(int i=0; i<_count; i++){
			View _item = _adapter.getView(i, null, _container);
			LinearLayout.LayoutParams _lp = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT, 
					LinearLayout.LayoutParams.WRAP_CONTENT);
			_item.setLayoutParams(_lp);
			_childs.add(_item);
			_container.addView(_item);
//			if(i != (_count-1)){
				// 如果不是最后一个，添加分割线
				View _divLine = new View(getContext());
				_divLine.setBackgroundColor(0xffdedede);
				LinearLayout.LayoutParams _lp1 = new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.MATCH_PARENT, 3);
				_divLine.setLayoutParams(_lp1);
				_container.addView(_divLine);
//			}
			
		}
	}

}

