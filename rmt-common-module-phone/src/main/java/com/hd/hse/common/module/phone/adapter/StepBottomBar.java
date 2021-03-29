package com.hd.hse.common.module.phone.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;

import com.hd.hse.common.module.phone.R;

public class StepBottomBar extends RelativeLayout implements OnCheckedChangeListener{

	private View rootView;
	private boolean flag;    //代码设置结果的标记

	private RadioGroup radioGroup;
	private RadioButton rightRTB;
	private RadioButton errorRTB;
	private RadioButton unsureRTB;

	public StepBottomBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public StepBottomBar(Context context, AttributeSet attrs) {
		this(context, attrs, -1);
	}

	public StepBottomBar(Context context) {
		this(context, null);
	}

	private void init(Context context) {
		LayoutInflater inflater = LayoutInflater.from(context);
		rootView = inflater.inflate(R.layout.hd_hse_common_module_bottom_bar,
				null);
		radioGroup = (RadioGroup) rootView
				.findViewById(R.id.rg_step_bottom_bar);
//		rightRTB = (RadioButton) rootView
//				.findViewById(R.id.rb_step_bottom_bar_right);
//		errorRTB = (RadioButton) rootView
//				.findViewById(R.id.rb_step_bottom_bar_error);
//		unsureRTB = (RadioButton) rootView
//				.findViewById(R.id.rb_step_bottom_bar_unsure);

		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		setLayoutParams(params);

		radioGroup.setOnCheckedChangeListener(this);
		
		addView(rootView);
	}
	
	/**
	 *  0. error
	 *  1. right
	 *  2. unsure
	 */
	public void setHideCheckedButton(int position){
		flag = true;
		if(position == 0){
			errorRTB.setVisibility(View.GONE);
		}else if(position == 1){
			rightRTB.setVisibility(View.GONE);
		}else if(position == 2){
			unsureRTB.setVisibility(View.GONE);
		}
		flag = false;
	}
	
	public void setHideCheckedButtonAll(){
		flag = true;
		rightRTB.setVisibility(View.GONE);
		errorRTB.setVisibility(View.GONE);
		unsureRTB.setVisibility(View.GONE);
		flag = false;
	}
	
	public void setShowCheckedButton(int position){
		flag = true;
		if(position == 0){
			errorRTB.setVisibility(View.VISIBLE);
		}else if(position == 1){
			rightRTB.setVisibility(View.VISIBLE);
		}else if(position == 2){
			unsureRTB.setVisibility(View.VISIBLE);
		}
		flag = false;
	}
	
	public void setShowCheckedButtonAll(){
		flag = true;
//		rightRTB.setVisibility(View.VISIBLE);
//		errorRTB.setVisibility(View.VISIBLE);
//		unsureRTB.setVisibility(View.VISIBLE);
		flag = false;
	}
	
	/**
	 * -1. clear
	 *  0. error
	 *  1. right
	 *  2. unsure
	 */
	public void setChecked(int position){
		flag = true;
		if(position == -1){
			radioGroup.clearCheck();
		}else if(position == 0){
//			errorRTB.setChecked(true);
		}else if(position == 1){
//			rightRTB.setChecked(true);
		}else if(position == 2){
//			unsureRTB.setChecked(true);
		}
		flag = false;
	}
	
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if(checkedId != -1 && !flag){
			if(listener != null){
				int result = -1;
//				if(checkedId == R.id.rb_step_bottom_bar_right){
//					result = 1;
//				}
//				else if(checkedId == R.id.rb_step_bottom_bar_error){
//					result = 0;
//				}else if(checkedId == R.id.rb_step_bottom_bar_unsure){
//					result = 2;
//				}
				
				listener.onCheckedResultChanged(result);
			}
		}
	}

	
	private OnCheckedResultChangedListener listener;
	public interface OnCheckedResultChangedListener{
		public void onCheckedResultChanged(int result);
	}
	
	public void setOnCheckedResultChangedListener(OnCheckedResultChangedListener listener){
		this.listener = listener;
	}
}
