package com.hd.hse.common.component.phone.custom;

import com.hd.hse.common.component.phone.R;
import com.hd.hse.common.component.phone.adapter.HSEDialogAdapter;
import com.hd.hse.common.component.phone.adapter.TaskDetailAdapter;
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
public class TaskDetailDialog extends AlertDialog implements OnClickListener{
	
	public ListView dytable;
	
	public Context context;
	
	public TaskDetailAdapter mDialogAdapter;
	
	public TaskDetailDialog(Context context, TaskDetailAdapter adapter) {
		super(context);
		mDialogAdapter = adapter;
		this.context = context;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.hd_hse_common_component_actionbar_titletouch_popwindow);
	    dytable = (ListView) this.findViewById(R.id.hd_hse_common_component_dialog_lv);
	    dytable.setAdapter(mDialogAdapter);
		// 关闭按钮
	    ImageButton ib = (ImageButton) this.findViewById(R.id.hd_hse_common_component_phone_actionbar_ib_close);
		ib.setOnClickListener(this);
//		dytable.initTabRow(superEntity);
	}
	
	@Override
	public void onClick(final View v) {
		dismiss();
	}
}
