package com.hd.hse.common.component.phone.custom;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hd.hse.common.component.phone.R;

import java.text.NumberFormat;


/**
 * ClassName: CustomProgressDialog (自定义进度条)<br/>
 * date: 2014年9月9日 <br/>
 * 
 * @author lxf
 * @version
 */
public class MessageProgressDialog  extends AlertDialog {
	private ProgressBar mProgress;
	//private Context mcontext;

	private TextView mProgressPercent;
	private TextView mProgressMessage;
	private TextView mProgressTitle;

	private Handler mViewUpdateHandler;
	private int mMax;
	private CharSequence mMessage;
	private CharSequence mTitle;
	private boolean mHasStarted;
	private int mProgressVal;

	private Button positiveButton;
//	private Button negativeButton;
//	private ImageView imageIcon;
	
	
	private boolean disPositiveButtonText;
	private boolean disNegativeButtonText;
	

	private int times=0;
	private final String ERROR="ERROR";
	private final String SUCCESSFUL="SUCCESSFUL";
	
	private Context context;

	// private String mProgressNumberFormat;
	private NumberFormat mProgressPercentFormat;

	public MessageProgressDialog(Context context) {
		super(context);
		this.context=context;
		initFormats();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.hd_common_progress_dialog_layout);
		// 初始化控件
		initControl();

		onProgressChanged();
		// 禁用返回键
		setCancelable(false);
		// 禁用点击外框区域消失框
		setCanceledOnTouchOutside(false);

		displayPositiveButton();

		displayNegativeButton();

		if(mTitle!=null)
		{
			setTitle(mTitle);
		}
		if (mMessage != null) {
			setMessage(mMessage);
		}
		if (mMax > 0) {
			setMax(mMax);
		}
		if (mProgressVal > 0) {
			setProgress(mProgressVal);
		}
	}

	@SuppressLint("HandlerLeak")
	private Handler myhandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			int progress = mProgress.getProgress();
			int max = mProgress.getMax();
			double dProgress = (double) progress / (double) (1024 * 1024);
			double dMax = (double) max / (double) (1024 * 1024);

			if (mProgressPercentFormat != null) {
				double percent = (double) progress / (double) max;
				SpannableString tmp = new SpannableString(
						mProgressPercentFormat.format(percent));
				tmp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0,
						tmp.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				mProgressPercent.setText(tmp);
			} else {
				mProgressPercent.setText("");
			}
		}
	};

	/**
	 * initControl:(初始化控件). <br/>
	 * date: 2014年9月10日 <br/>
	 * 
	 * @author lxf
	 */
	private void initControl() {
		mProgress = (ProgressBar) findViewById(R.id.hse_sys_progress);
		mProgressPercent = (TextView) findViewById(R.id.hse_sys_progress_percent);
		mProgressMessage = (TextView) findViewById(R.id.hse_sys_dialog_image_message);
		positiveButton = (Button) findViewById(R.id.hd_hse_phone_message_sure_positiveButton);
//		negativeButton = (Button) findViewById(R.id.hd_hse_phone_message_cancel_negativeButton);
//		imageIcon=(ImageView)findViewById(R.id.hse_sys_message_image);
		mProgressTitle=(TextView) findViewById(R.id.hd_sys_progress_title);
		
		positiveButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				dismiss();
			}
		});
//		negativeButton.setOnClickListener(new View.OnClickListener() {
//			public void onClick(View arg0) {
//				cancel();
//			}
//		});

		mViewUpdateHandler = myhandler;

	}

	/**
	 * displayPositiveButton:(是否显示). <br/>
	 * date: 2014年9月10日 <br/>
	 *
	 * @author lxf
	 */
	private void displayPositiveButton() {
		if (null != positiveButton) {
			if (disPositiveButtonText) {
				positiveButton.setVisibility(View.VISIBLE);
			} else {
				positiveButton.setVisibility(View.GONE);
			}
		}
	}

	/**
	 * initButtonLayout:(控制按钮是否显示). <br/>
	 * date: 2014年9月10日 <br/>
	 * 
	 * @author lxf
	 */
	private void displayNegativeButton() {
//		if (null != negativeButton) {
//			if (disNegativeButtonText) {
//				negativeButton.setVisibility(View.VISIBLE);
//			} else {
//				negativeButton.setVisibility(View.GONE);
//			}
//		}
	}

	
	/**
	 * setDisPositiveButton:(设置是否显示确认按钮). <br/>
	 * date: 2014年9月10日 <br/>
	 * 
	 * @author Administrator
	 * @param //positiveButtonText
	 */
	public void setDisPositiveButton(boolean disPositiveButton) {
		this.disNegativeButtonText = disPositiveButton;
		displayPositiveButton();
	}

	/**
	 * setDisNegativeButtonText:(设置是否显示取消按钮). <br/>
	 * date: 2014年9月10日 <br/>
	 * 
	 * @author lxf
	 * @param //negativeButtonText
	 */
	public void setDisNegativeButtonText(boolean disnegativeButtonText) {
		this.disNegativeButtonText= disnegativeButtonText;
		displayNegativeButton();
	}

	/**
	 * initFormats:(初始化参数). <br/>
	 * date: 2014年9月9日 <br/>
	 * 
	 * @author lxf
	 */
	private void initFormats() {
		// mProgressNumberFormat = "%1.2fM/%2.2fM";
		mProgressPercentFormat = NumberFormat.getPercentInstance();
		mProgressPercentFormat.setMaximumFractionDigits(0);
	}

	/**
	 * onProgressChanged:(). <br/>
	 * date: 2014年9月9日 <br/>
	 * 
	 * @author lxf
	 */
	private void onProgressChanged() {
		mViewUpdateHandler.sendEmptyMessage(0);
	}

	/**
	 * setProgressStyle:(设置样式). <br/>
	 * date: 2014年9月9日 <br/>
	 * 
	 * @author lxf
	 * @param style
	 */
	public void setProgressStyle(int style) {
		// mProgressStyle = style;
	}

	/**
	 * getMax:(获取最大值). <br/>
	 * date: 2014年9月9日 <br/>
	 * 
	 * @author lxf
	 * @return
	 */
	public int getMax() {
		if (mProgress != null) {
			return mProgress.getMax();
		}
		return mMax;
	}

	/**
	 * setMax:(设置最大值). <br/>
	 * date: 2014年9月9日 <br/>
	 * 
	 * @author lxf
	 * @param max
	 */
	public void setMax(int max) {
		max = max * 1024 * 1024;
		if (mProgress != null) {
			mProgress.setMax(max);
			onProgressChanged();
		} else {
			mMax = max;
		}
	}

	public void setIndeterminate(boolean indeterminate) {
		if (mProgress != null) {
			mProgress.setIndeterminate(indeterminate);
		}
		// else {
		// mIndeterminate = indeterminate;
		// }
	}

	/**
	 * setProgress:(设置显示进度). <br/>
	 * date: 2014年9月9日 <br/>
	 * 
	 * @author lxf
	 * @param value
	 */
	public void setProgress(int value) {
		value = value * 1024 * 1024;
		if(value<mProgress.getProgress())
		{
			return;
		}
		if (mHasStarted) {
			mProgress.setProgress(value);
			onProgressChanged();
		} else {
			mProgressVal = value;
		}
	}



	/**
	 * setEnableConfirm:(成功是显示的按钮). <br/>
	 * date: 2014年9月10日 <br/>
	 * 
	 * @author lxf
	 */
	public void executSuccessful() {


        if(!TextUtils.isEmpty(mTitle.toString()))
		{
			mProgressMessage.setText(mTitle+","+"执行成功。");
		}
		/*else
		{
			mProgressMessage.setText(mTitle);
		}*/
		excuteType(SUCCESSFUL);
	}
	/**
	 * setEnableConfirm:(成功是显示的按钮). <br/>
	 * date: 2014年9月10日 <br/>
	 * 
	 * @author lxf
	 */
	public void executSuccessful1() {
		//if(!StringUtils.isEmpty(mTitle.toString()))
        if(!TextUtils.isEmpty(mTitle.toString()))
		{
			mProgressMessage.setText(mTitle+"成功。");
		}
		/*else
		{
			mProgressMessage.setText(mTitle);
		}*/
		excuteType(SUCCESSFUL);
	}
	/**
	 * executFailed:(失败是显示信息). <br/>
	 * date: 2014年9月10日 <br/>
	 *
	 * @author lxf
	 */
	public void executFailed() {
		excuteType(ERROR);
	}
	private void excuteType(String type)
	{
		if(times!=0)
			return ;
		times++;
		disPositiveButtonText=true;
		displayPositiveButton();
//		int mpheight=0;
		if(null!=mProgress)
		{
			mProgressMessage.setVisibility(View.VISIBLE);
//			mpheight=mProgress.getHeight();
			mProgress.setVisibility(View.GONE);
			mProgressPercent.setVisibility(View.GONE);
//			mProgressMessage.setHeight(mProgressMessage.getHeight()+mpheight);
			if(type==ERROR)
			{
				Drawable left = context.getResources().getDrawable(R.drawable.hd_hse_phone_message_error);
				left.setBounds(0, 0, left.getMinimumWidth(), left.getMinimumHeight());
				mProgressMessage.setCompoundDrawables(left, null, null, null);
//				imageIcon.setImageResource(R.drawable.hd_hse_phone_message_error);
			}
			else if(type==SUCCESSFUL)
			{
				Drawable left = context.getResources().getDrawable(R.drawable.hd_hse_phone_message_true);
				left.setBounds(0, 0, left.getMinimumWidth(), left.getMinimumHeight());
				mProgressMessage.setCompoundDrawables(left, null, null, null);
//				imageIcon.setImageResource(R.drawable.hd_hse_phone_message_true);
			}
			setCancelable(true);
			setCanceledOnTouchOutside(true);
		}
	}

	/**
	 * TODO 设置 显示 消息
	 * 
	 * @see android.app.AlertDialog#setMessage(java.lang.CharSequence)
	 */
	@Override
	public void setMessage(CharSequence message) {

		if (mProgressMessage != null) {
			mProgressMessage.setText(message);
		} else {
			mMessage = message;
		}
	}
	@Override
	public void setTitle(CharSequence title) {
		if(mProgressTitle!=null)
		{
			mProgressTitle.setText(title);
		}
		else
		{
			mTitle=title;
		}
	}

	/**
	 * TODO 是否禁用返回键
	 * 
	 * @see android.app.Dialog#setCancelable(boolean)
	 */
	@Override
	public void setCancelable(boolean flag) {
		// TODO Auto-generated method stub
		super.setCancelable(flag);
	}

	/**
	 * TODO 是否禁用点击消息框外部区域时，消息框消失
	 * 
	 * @see android.app.Dialog#setCanceledOnTouchOutside(boolean)
	 */
	@Override
	public void setCanceledOnTouchOutside(boolean cancel) {
		// TODO Auto-generated method stub
		super.setCanceledOnTouchOutside(cancel);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		mHasStarted = true;
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		mHasStarted = false;
	}

}
