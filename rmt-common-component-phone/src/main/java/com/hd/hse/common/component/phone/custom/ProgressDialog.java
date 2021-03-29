/**
 * Project Name:hse-cbs-app
 * File Name:ProgressDialog.java
 * Package Name:com.hd.hse.cbs.ui.common.custom
 * Date:2014年9月11日
 * Copyright (c) 2014, longgang@ushayden.com All Rights Reserved.
 *
 */

package com.hd.hse.common.component.phone.custom;





import com.hd.hse.common.component.phone.R;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;


/**
 * ClassName:ProgressDialog (耗时等待对话框).<br/>
 * Date: 2014年9月11日 <br/>
 * 
 * @author lg
 * @version
 * @see
 */
public class ProgressDialog extends Dialog {

	private Context context;
	
	private TextView txtMsg;

	public ProgressDialog(Context context, String msg) {
		this(context, R.style.ProgressDialog, msg);
	}

	public ProgressDialog(Context context, int theme, String msg) {
		super(context, theme);
		this.context = context;
		setContentView(R.layout.hd_hse_main_app_common_progress);
		this.getWindow().getAttributes().gravity = Gravity.CENTER;
		// 不退出
		setCancelable(false);
		txtMsg = (TextView) this.findViewById(R.id.hd_cbs_common_progress_txt);
		if (txtMsg != null) {
			txtMsg.setText(msg);
		}
	}
	
	/**
	 * showMsg:(提示信息). <br/>
	 * date: 2014年9月11日 <br/>
	 *
	 * @author lg
	 * @param msg
	 */
	public void showMsg(String msg){
		txtMsg.setText(msg);
	}
	
	@Override
	public void setCancelable(boolean flag) {
		// TODO Auto-generated method stub
		super.setCancelable(flag);
	}

}
