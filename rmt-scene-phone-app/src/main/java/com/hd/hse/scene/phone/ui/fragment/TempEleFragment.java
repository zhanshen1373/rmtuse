package com.hd.hse.scene.phone.ui.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hd.hse.common.component.phone.custom.ExpandListView;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.common.module.branch.ui.model.fragment.BaseFragment;
import com.hd.hse.entity.conf.RmtConfElecEqpt;
import com.hd.hse.entity.conf.RmtConfMIntfc;
import com.hd.hse.entity.workorder.RmtWorkPermit;
import com.hd.hse.scene.phone.ui.activity.TempEleChoiceListActivity;

import org.apache.log4j.Logger;
import org.hse.scene.phone.app.R;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TempEleFragment extends BaseFragment {
    private static Logger logger = LogUtils.getLogger(TempEleFragment.class);
    private static final int REQUEST_CODE = 1;
    private EditText purposeEdit; // 临时用电目的
    private EditText voltageEdit; // 工作电压
    private TextView licenseTxt; // 用火许可证编号
    private EditText pointEdit; // 电源接入点
    private ImageView addBtn; // 设备添加按钮
    private ExpandListView listView; // 用电设备清单列表
    private TextView totalTxt; // 负荷合计
    private TextView shenheBtn; // 审核按钮
    private RmtConfMIntfc mPdaConfig;
    private List<RmtConfElecEqpt> devices;
    private List<RmtConfElecEqpt> choiceDevices;
    private RmtWorkPermit workPermit;
    private MyListAdapter listAdapter;
    private ProgressDialog dialog;

    public TempEleFragment() {
        super(STYLE_NO_MENU);
    }

    @Override
    protected View onCreatePageView(LayoutInflater inflater) {
        View view = inflater.inflate(
                R.layout.hd_hse_scene_phone_tempele_fragment, null);
        findView(view);
        initData();
        initView();
        return view;
    }


    private void findView(View view) {
        purposeEdit = (EditText) view.findViewById(R.id.purpose_edit);
        voltageEdit = (EditText) view.findViewById(R.id.voltage_edit);
        licenseTxt = (TextView) view.findViewById(R.id.license_txt);
        pointEdit = (EditText) view.findViewById(R.id.point_edit);
        addBtn = (ImageView) view.findViewById(R.id.add_btn);
        listView = (ExpandListView) view.findViewById(R.id.listview);
        totalTxt = (TextView) view.findViewById(R.id.total_num);
        shenheBtn = (TextView) view.findViewById(R.id.shenhe_btn);
    }

    private void initData() {
        mPdaConfig = mPDAWorkOrderInfoConfig;
        devices = mPdaConfig.getConfElecEqptVOList();
        List<RmtWorkPermit> workPermits = mPdaConfig.getPermitMVOList();
        for (RmtWorkPermit w : workPermits) {
            if (w.getWork_type().equals("elec")
                    || w.getWorkElecEqptVOList() != null) {
                workPermit = w;
                choiceDevices = w.getWorkElecEqptVOList();
                break;
            }
        }
        if (choiceDevices == null) {
            choiceDevices = new ArrayList<>();
        }
    }

    private void initView() {
        addBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(getActivity(),
                        TempEleChoiceListActivity.class);
                intent.putExtra(TempEleChoiceListActivity.DEVICE,
                        (Serializable) devices);
                intent.putExtra(TempEleChoiceListActivity.CHOICE_DEVICE,
                        (Serializable) choiceDevices);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        purposeEdit.setText((String) workPermit.getElec_purpose() == null ? "" : (String) workPermit
                .getElec_purpose());
        voltageEdit.setText((String) workPermit.getElec_voltage() == null ? "" : (String) workPermit
                .getElec_voltage());
        pointEdit.setText((String) workPermit.getElec_power_access_point() == null ? "" : (String) workPermit
                .getElec_power_access_point());
        // if (choiceDevices!=null && choiceDevices.size()!=0) {
//        (String) workPermit.getElec_total_load()
        totalTxt.setText(String.valueOf(workPermit.getElec_total_load()));
        listAdapter = new MyListAdapter();
        listView.setAdapter(listAdapter);
        // }
        shenheBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String str = purposeEdit.getText().toString();
                if (checkStrIsEmpty(str)) {
                    ToastUtils.imgToast(getActivity(),
                            R.drawable.hd_hse_common_msg_wrong, "用电目的不能为空！");
                    return;
                } else {
                    workPermit.setElec_purpose(str);
                }
                str = voltageEdit.getText().toString();
                if (checkStrIsEmpty(str)) {
                    ToastUtils.imgToast(getActivity(),
                            R.drawable.hd_hse_common_msg_wrong, "工作电压不能为空！");
                    return;
                } else {
                    workPermit.setElec_voltage(str);
                }
                for (RmtConfElecEqpt device : choiceDevices) {
                    if (device.getQuantity() == 0) {
                        ToastUtils.imgToast(getActivity(),
                                R.drawable.hd_hse_common_msg_wrong, "设备数量不能为空或0！");
                        return;
                    }
                }
                str = pointEdit.getText().toString();
                workPermit.setElec_power_access_point(str);
                uploadRecord(mPDAWorkOrderInfoConfig);
            }
        });
    }

    private boolean checkStrIsEmpty(String str) {
        if (str == null || str.trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            choiceDevices.clear();
            choiceDevices = (List<RmtConfElecEqpt>) data
                    .getSerializableExtra(TempEleChoiceListActivity.CHOICE_DEVICE);
            listAdapter.notifyDataSetChanged();
            double heji = getHeji();
            totalTxt.setText(heji + "");
            workPermit.setElec_total_load(heji);
            workPermit.setWorkElecEqptVOList(choiceDevices);
        }
    }


    private double getHeji() {
        double heji = 0.0;
        for (RmtConfElecEqpt device : choiceDevices) {
            heji += Double.valueOf(device.getElec_load()) * device.getQuantity();
        }
        /**
         * 处理double浮点计算时产生的误差
         */
        BigDecimal b = new BigDecimal(heji);
        b = b.setScale(2, BigDecimal.ROUND_HALF_UP);
        double d = b.doubleValue();
        return d;
    }

    private class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return choiceDevices.size();
        }

        @Override
        public Object getItem(int arg0) {
            return choiceDevices.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            return arg0;
        }

        @Override
        public View getView(final int position, View view, ViewGroup viewGroup) {

            view = LayoutInflater.from(getActivity()).inflate(
                    R.layout.hd_hse_scence_tempele_fragment_recycle_item,
                    viewGroup, false);
            TextView deviceNameTxt = (TextView) view
                    .findViewById(R.id.devicename_txt);
            TextView deviceModelTxt = (TextView) view
                    .findViewById(R.id.model_txt);
            TextView powerTxt = (TextView) view.findViewById(R.id.power_txt);
            final EditText countEdit = (EditText) view.findViewById(R.id.count_edit);
            deviceNameTxt.setText(choiceDevices.get(position).getEquipment_name());
            deviceModelTxt.setText(choiceDevices.get(position).getModel());
            powerTxt.setText(choiceDevices.get(position).getElec_load());
            countEdit.setText(choiceDevices.get(position).getQuantity() + "");
            countEdit.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {

                }

                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1,
                                              int arg2, int arg3) {

                }

                @Override
                public void afterTextChanged(Editable arg0) {
                    // ToastUtils.toast(getActivity(), arg0);
                    if (arg0.toString().equals("0")) {
                        countEdit.setText("");
                    }
                    int count = 0;
                    try {
                        count = Integer.valueOf(arg0.toString());
                    } catch (NumberFormatException e) {
                        logger.error(e.getMessage());
                    }
                    choiceDevices.get(position).setQuantity(count);
                    for (RmtConfElecEqpt device : devices) {
                        if (choiceDevices.get(position).getElec_eqpt_id()
                                == device.getElec_eqpt_id()) {
                            device.setQuantity(count);
                            break;
                        }
                    }
                    double heji = getHeji();
                    totalTxt.setText(heji + "");
                    workPermit.setElec_total_load(heji);
                }
            });
            return view;
        }

    }
}
