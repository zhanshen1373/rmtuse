package com.hd.hse.common.module.phone.ui.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.module.phone.R;
import com.hd.hse.common.module.phone.ui.utils.BitmapUtils;

import java.text.SimpleDateFormat;
import java.util.Date;


public class BaseActivity extends AppCompatActivity {

	private int titleResId = R.string.hd_hse_main_app_main_title;

	public int getTitleResId() {
		return titleResId;
	}

	public void setTitleResId(int titleResId) {
		this.titleResId = titleResId;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		SystemApplication.getInstance().pushActivity(this);

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		initActionBar();

		// changeFont((ViewGroup)this.getWindow().getDecorView());
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		SystemApplication.getInstance().removePopActivty(this);
		super.finish();
	}

	/**
	 * initActionBar:(设置状态栏). <br/>
	 * date: 2014年10月28日 <br/>
	 *
	 * @author lxf
	 */
	@SuppressLint("NewApi")
	public void initActionBar() {
		ActionBar actionBar = getActionBar();
		if (actionBar != null) {
			actionBar.setTitle(titleResId);
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setDisplayShowHomeEnabled(true);
			actionBar.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.hd_hse_common_component_actionbar_bg));
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			this.finish();
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * popActivity:(销毁当前界面). <br/>
	 * date: 2014年10月11日 <br/>
	 *
	 * @author lxf
	 */
	public void popActivity() {
		SystemApplication.getInstance().popActivity();
	}

	private static long lastClickTime;

	/**
	 * isFastDoubleClick:(防止多次快速点击). <br/>
	 * date: 2014年9月15日 <br/>
	 *
	 * @author lxf
	 * @return
	 */
	public boolean isFastDoubleClick() {
		long time = System.currentTimeMillis();
		long timeD = time - lastClickTime;
		if (0 < timeD && timeD < 1500) {
			String msg = getResources().getString(
					R.string.hd_hse_main_app_Multipleclicks_title);
			ToastUtils.toast(BaseActivity.this, msg);
			return true;
		}
		lastClickTime = time;
		return false;
	}

	/**
	 * setBackground:(设置背景). <br/>
	 * date: 2015年1月26日 <br/>
	 *
	 * @author zhulei
	 * @param view
	 * @param resId
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public void setBackground(View view, int resId) {
		if (view == null) {
			return;
		}
		int sdk = android.os.Build.VERSION.SDK_INT;
		if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
			view.setBackgroundDrawable(BitmapUtils.decodeBitmap(getResources(),
					resId));
		} else {
			view.setBackground(BitmapUtils.decodeBitmap(getResources(), resId));
		}
	}

	/**
	 * hideInputKeyboard:(隐藏输入法键盘). <br/>
	 * date: 2014年10月18日 <br/>
	 *
	 * @author lxf
	 * @param v
	 */
	public void hideInputKeyboard(View v) {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			popActivity();
		}
		return false;
	}

	/**
	 * changeFont:(修改文字显示格式). <br/>
	 * date: 2015年2月9日 <br/>
	 *
	 * @author zhaofeng
	 * @param root
	 */
	protected void changeFont(ViewGroup root) {
		Typeface tf = Typeface.createFromAsset(getAssets(),
				"fonts/STHEITI-MEDIUM.TTC");
		for (int i = 0; i < root.getChildCount(); i++) {
			View v = root.getChildAt(i);
			if (v instanceof TextView) {
				((TextView) v).setTypeface(tf);
				// ((TextView)v).setTextSize(15);
				// ((TextView)v).setTextColor(Color.GRAY);
			} else if (v instanceof Button) {
				((Button) v).setTypeface(tf);
				// ((Button)v).setTextSize(15);
				// ((Button)v).setTextColor(Color.GRAY);
			} else if (v instanceof EditText) {
				((EditText) v).setTypeface(tf);
				// ((EditText)v).setTextSize(15);
				// ((EditText)v).setTextColor(Color.GRAY);
			} else if (v instanceof ViewGroup) {
				changeFont((ViewGroup) v);
			}
		}

	}
	public String getNowTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String nowTime =df.format(new Date());
		return  nowTime;
	}


}
