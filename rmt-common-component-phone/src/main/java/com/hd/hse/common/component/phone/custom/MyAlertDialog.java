package com.hd.hse.common.component.phone.custom;

import com.hd.hse.common.component.phone.R;
import com.hd.hse.common.component.phone.adapter.HSEDialogAdapter;
import com.hd.hse.common.component.phone.custom.HSEActionBar.OnClickLstner;
import com.hd.hse.common.entity.SuperEntity;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

/**
 * TODO 弹出框
 * ClassName: MyAlertDialog ()<br/>
 * date: 2015年2月12日  <br/>
 *
 * @author wenlin
 * @version 
 */
public class MyAlertDialog extends AlertDialog implements OnClickListener{

	/**
	 * superEntity:TODO(作业票实体).
	 */
	public SuperEntity superEntity;
	
	public ListView dytable;
	
	public Context context;
	
	public Boolean isDjLx;
	
	public HSEDialogAdapter mDialogAdapter;
	public MyAlertDialog(Context context) {
		super(context);
	}

	public MyAlertDialog(Context context, int theme  , SuperEntity superEntity , Boolean isDjLx) {
		super(context, theme);
		this.context = context;
		this.superEntity = superEntity;
		this.isDjLx = isDjLx;
	}
	
	public MyAlertDialog(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.hd_hse_common_component_actionbar_titletouch_popwindow);
	    dytable = (ListView) this.findViewById(R.id.hd_hse_common_component_dialog_lv);
	    mDialogAdapter = new HSEDialogAdapter(context, superEntity ,isDjLx);
	    dytable.setAdapter(mDialogAdapter);
		// 关闭按钮
	    ImageButton ib = (ImageButton) this.findViewById(R.id.hd_hse_common_component_phone_actionbar_ib_close);
		ib.setOnClickListener(this);
//		dytable.initTabRow(superEntity);
	}
	public void setTabRow(SuperEntity superEntity){
		mDialogAdapter.setCurentSuperEntity(superEntity);
	}
	
	@Override
	public void onClick(final View v) {
		dismiss();
	}
}
