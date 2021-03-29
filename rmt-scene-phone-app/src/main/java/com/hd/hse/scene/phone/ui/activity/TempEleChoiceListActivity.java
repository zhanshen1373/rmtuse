package com.hd.hse.scene.phone.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.hd.hse.common.component.phone.constant.IActionBar;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.module.phone.ui.activity.BaseFrameActivity;
import com.hd.hse.entity.conf.RmtConfElecEqpt;

import org.hse.scene.phone.app.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TempEleChoiceListActivity extends BaseFrameActivity {
	private ListView mListView;
	private TextView saveButton;
	private List<RmtConfElecEqpt> mDevices;
	private List<RmtConfElecEqpt> choiceDevices;
	public static final String DEVICE = "device";
	public static final String CHOICE_DEVICE = "choicedevice";
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hd_hse_scence_tmepele_choice_list_activity);
		setCustomActionBar(false, eventLst, new String[]{IActionBar.TV_BACK, IActionBar.TV_TITLE});
		setActionBartitleContent("用电设备清单", false);
		mListView = (ListView) findViewById(R.id.listview);
		saveButton = (TextView) findViewById(R.id.save_btn);
		mDevices = (List<RmtConfElecEqpt>) getIntent().getSerializableExtra(DEVICE);
		choiceDevices = (List<RmtConfElecEqpt>) getIntent().getSerializableExtra(CHOICE_DEVICE);
		if (mDevices == null) {
			mDevices = new ArrayList<>();
		}
		if (choiceDevices == null) {
			choiceDevices = new ArrayList<>();
		}
		for (int i = 0; i < mDevices.size(); i++) {
			for (int j = 0; j < choiceDevices.size(); j++) {
				if (mDevices.get(i).getElec_eqpt_id()==(choiceDevices.get(j).getElec_eqpt_id())) {
					mDevices.get(i).setChoiced(true);
					mDevices.get(i).setQuantity(choiceDevices.get(j).getQuantity());
					break;
				}
			}
		}
		mListView.setAdapter(new MyListAdapter());
		saveButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				choiceDevices.clear();
				for (RmtConfElecEqpt device : mDevices) {
					if (device.isChoiced()) {
						if (device.getQuantity() == 0) {
							device.setQuantity(1);
						}
						choiceDevices.add(device);
					}
				}
				Intent data = getIntent();
				data.putExtra(CHOICE_DEVICE, (Serializable) choiceDevices);
				setResult(RESULT_OK, data);
				finish();
			}
		});
	}
	
	IEventListener eventLst = new IEventListener() {

		@Override
		public void eventProcess(int eventType, Object... objects)
				throws HDException {
			
		}
		
	};
	
	private class MyListAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return mDevices.size();
		}

		@Override
		public Object getItem(int arg0) {
			return mDevices.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(final int position, View view, ViewGroup viewGroup) {
			ViewHolder holder = null;
			if (view == null) {
				view = LayoutInflater.from(TempEleChoiceListActivity.this).inflate(R.layout.hd_hse_scence_tempele_choice_list_item, viewGroup, false);
				holder = new ViewHolder();
				holder.itemView = view.findViewById(R.id.item_view);
				holder.checkBox = (CheckBox) view.findViewById(R.id.checkbox);
				holder.deviceNameTxt = (TextView) view.findViewById(R.id.devicename_txt);
				holder.modelTxt = (TextView) view.findViewById(R.id.model_txt);
				holder.powerTxt = (TextView) view.findViewById(R.id.power_txt);
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}
			holder.deviceNameTxt.setText(mDevices.get(position).getEquipment_name());
			holder.modelTxt.setText(mDevices.get(position).getModel());
			holder.powerTxt.setText(mDevices.get(position).getElec_load());
//			boolean isChecked = false;
//			for (TempEleDevice device : choiceDevices) {
//				if (device.getVersion().equals(mDevices.get(position).getVersion())) {
//					isChecked = true;
//					break;
//				}
//			}
//			holder.checkBox.setChecked(isChecked);
//			mDevices.get(position).setChoiced(isChecked);
			holder.checkBox.setChecked(mDevices.get(position).isChoiced());
			holder.itemView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					if (mDevices.get(position).isChoiced()) {
						mDevices.get(position).setChoiced(false);
						((CheckBox)arg0.findViewById(R.id.checkbox)).setChecked(false);
					} else {
						mDevices.get(position).setChoiced(true);
						((CheckBox)arg0.findViewById(R.id.checkbox)).setChecked(true);
					}
				}
			});
			return view;
		}
		
		private class ViewHolder {
			View itemView;
			CheckBox checkBox;
			TextView deviceNameTxt;
			TextView modelTxt;
			TextView powerTxt;
		}
		
	}
}
