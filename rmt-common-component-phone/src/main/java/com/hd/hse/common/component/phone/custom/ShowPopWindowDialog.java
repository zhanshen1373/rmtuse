package com.hd.hse.common.component.phone.custom;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

import com.hd.hse.common.component.phone.R;
import com.hd.hse.common.component.phone.adapter.HSEDialogAdapterNew;
import com.hd.hse.common.entity.SuperEntity;

/**
 * ClassName: ShowPopWindowDialog (显示详细信息页面)<br/>
 * date: 2015年6月11日 <br/>
 * 
 * @author lxf
 * @version
 */
public class ShowPopWindowDialog extends AlertDialog implements OnClickListener {
	/**
	 * superEntity:TODO(作业票实体).
	 */
	public List<SuperEntity> listData;

	public ListView dytable;
	public Context context;
	public HSEDialogAdapterNew mDialogAdapter;

	/**
	 * arrayDesc:TODO(字段描述).
	 */
	public String titleAtr = "descrption";
	/**
	 * arrayNum:TODO(字段编码).
	 */
	public String vauleAtr = "desValue";
	
	/**
	 * titleTag:TODO(是否标题).
	 */
	public String titleTag="istitle";

	public ShowPopWindowDialog(Context context, int theme) {
		super(context, theme);
		this.context = context;
	}

	public ShowPopWindowDialog(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hd_hse_common_component_actionbar_titletouch_popwindow);
		dytable = (ListView) this
				.findViewById(R.id.hd_hse_common_component_dialog_lv);
		// 关闭按钮
		ImageButton ib = (ImageButton) this
				.findViewById(R.id.hd_hse_common_component_phone_actionbar_ib_close);
		ib.setOnClickListener(this);

		mDialogAdapter = new HSEDialogAdapterNew(context);
		mDialogAdapter.titleAtr = titleAtr;
		mDialogAdapter.vauleAtr = vauleAtr;
		mDialogAdapter.titleTag="istitle";
		if (listData != null) {
			mDialogAdapter.setInitDataList(listData);
		}
		dytable.setAdapter(mDialogAdapter);
	}

	public void setDataList(List<SuperEntity> superEntity) {
		this.listData=superEntity;
		if (mDialogAdapter != null) {
			mDialogAdapter.setCurentSuperEntity(superEntity);
		}
	}

	@Override
	public void onClick(final View v) {
		dismiss();
	}
}
