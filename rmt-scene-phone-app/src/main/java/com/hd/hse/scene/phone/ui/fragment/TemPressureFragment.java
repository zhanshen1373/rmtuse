package com.hd.hse.scene.phone.ui.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.module.branch.ui.model.fragment.BaseFragment;
import com.hd.hse.entity.resultdata.EqptType;
import com.hd.hse.entity.resultdata.WorkSite;
import com.hd.hse.scene.phone.ui.activity.SceneTaskActivity;
import com.hd.hse.scene.phone.ui.dialog.PersionSelectDialog;

import org.hse.scene.phone.app.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * Created by dubojian on 2018/3/15.
 */

public class TemPressureFragment extends BaseFragment {


    private EditText pretemperatureEdit;
    private EditText prepressureEdit;
    private EditText prejzEdit;
    private EditText posttemperatureEdit;
    private EditText postpressureEdit;
    private EditText postjzEdit;
    private EditText deviceEdit;
    private Button work_site_nameBt;
    private TextView work_site_nameTv;
    private EditText equipment_nameEdit;
    private TextView equipment_typeTv;
    private Button equipment_typeBt;
    private ProgressDialog dialog;
    private HashMap<String, String> equTemAndPreeMap;
    private PersionSelectDialog persionSelectDialog;
    private long position_id;
    private String dict_data_code;
    private List<TextView> etlist;
    private SceneTaskActivity.MyTouchListener myTouchListener;

    public TemPressureFragment() {
        super(STYLE_SINGLE_BUTTON);
    }

    @Override
    protected View onCreatePageView(LayoutInflater inflater) {

        View inflate = inflater.inflate(R.layout.hd_hse_scene_phone_temperaturepressure_fragment, null);

        pretemperatureEdit = (EditText) inflate.findViewById(R.id.pretemperature_edit);
        prepressureEdit = (EditText) inflate.findViewById(R.id.prepressure_edit);
        prejzEdit = (EditText) inflate.findViewById(R.id.prejz_edit);
        posttemperatureEdit = (EditText) inflate.findViewById(R.id.posttemperature_edit);
        postpressureEdit = (EditText) inflate.findViewById(R.id.postpressure_edit);
        postjzEdit = (EditText) inflate.findViewById(R.id.postjz_edit);
        deviceEdit = (EditText) inflate.findViewById(R.id.device_edit);
        work_site_nameBt = (Button) inflate.findViewById(R.id.work_site_name_button);
        work_site_nameTv = (TextView) inflate.findViewById(R.id.work_site_name_textview);
        equipment_nameEdit = (EditText) inflate.findViewById(R.id.equipment_name_edit);
        equipment_typeTv = (TextView) inflate.findViewById(R.id.equipment_type_textview);
        equipment_typeBt = (Button) inflate.findViewById(R.id.equipment_type_button);

        etlist = new ArrayList<>();
        LinearLayout linearLayout = (LinearLayout) inflate;
        getAllNeedView(linearLayout);
        etlist.add(work_site_nameTv);
        etlist.add(equipment_typeTv);


        work_site_nameBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNetData(work_site_nameTv);
                hideInputKeyboard(v);
            }
        });

        equipment_typeBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getETypeData(equipment_typeTv);
                hideInputKeyboard(v);
            }
        });

        deviceEdit.setText(mPDAWorkOrderInfoConfig.getDevice());
        work_site_nameTv.setText(mPDAWorkOrderInfoConfig.getWork_site_name());
        equipment_nameEdit.setText(mPDAWorkOrderInfoConfig.getEquipment_name());
        equipment_typeTv.setText(mPDAWorkOrderInfoConfig.getEquipment_type_name());

        pretemperatureEdit.setText(mPDAWorkOrderInfoConfig.getTemperature());
        prepressureEdit.setText(mPDAWorkOrderInfoConfig.getPressure());
        prejzEdit.setText(mPDAWorkOrderInfoConfig.getMedium());


        posttemperatureEdit.setText(mPDAWorkOrderInfoConfig.getTemperature_later());
        postpressureEdit.setText(mPDAWorkOrderInfoConfig.getPressure_later());
        postjzEdit.setText(mPDAWorkOrderInfoConfig.getMedium_later());

        equTemAndPreeMap = new HashMap<>();

        setOnBottomButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean empty = isEmpty(etlist);
                if (empty) {
                    ToastUtils.toast(getActivity(), "必填项不能为空");
                    return;
                }

                if (dialog == null) {
                    dialog = new ProgressDialog(getActivity());
                    dialog.setMessage("保存中...");
                }
                dialog.show();

                equTemAndPreeMap.put("temperature", pretemperatureEdit.getText().toString());
                equTemAndPreeMap.put("pressure", prepressureEdit.getText().toString());
                equTemAndPreeMap.put("medium", prejzEdit.getText().toString());
                equTemAndPreeMap.put("temperature_later", posttemperatureEdit.getText().toString());
                equTemAndPreeMap.put("pressure_later", postpressureEdit.getText().toString());
                equTemAndPreeMap.put("medium_later", postjzEdit.getText().toString());
                equTemAndPreeMap.put("device", deviceEdit.getText().toString());
                equTemAndPreeMap.put("work_site_name", work_site_nameTv.getText().toString());
                equTemAndPreeMap.put("equipment_name", equipment_nameEdit.getText().toString());
                equTemAndPreeMap.put("work_site_id", position_id + "");
                equTemAndPreeMap.put("equipment_type_name", equipment_typeTv.getText().toString());

                if (dict_data_code != null) {
                    equTemAndPreeMap.put("equipment_type", dict_data_code);
                } else {
                    equTemAndPreeMap.put("equipment_type", mPDAWorkOrderInfoConfig.getEquipment_type());

                }

                mPDAWorkOrderInfoConfig.setEquTemAndPreeMap(equTemAndPreeMap);
                mPDAWorkOrderInfoConfig.setEquipment_type_name(equipment_typeTv.getText().toString());
                mPDAWorkOrderInfoConfig.setWork_site_name(work_site_nameTv.getText().toString());

                RmtInterface anInterface = RetrofitUtil.createInterface(RmtInterface.class);
                Call<ResultData<Map<String, Long>>> resultDataCall = anInterface.approveByTab(mPDAWorkOrderInfoConfig, LoginUserRecord.getInstance().getUser().getUserid());
                HttpBusiness.action(getActivity(), false, resultDataCall, new HttpCallBack<Map<String, Long>>() {
                    @Override
                    public void success(Map<String, Long> map) {
                        dialog.dismiss();
                        ToastUtils.toast(getActivity(), "保存成功");
                    }

                    @Override
                    public void warning(String msg) {
                        dialog.dismiss();
                        ToastUtils.toast(getActivity(), msg);
                    }

                    @Override
                    public void error(Throwable t) {
                        dialog.dismiss();
                        ToastUtils.toast(getActivity(), "保存数据失败");
                    }
                });
            }
        });

        myTouchListener = new SceneTaskActivity.MyTouchListener() {

            @Override
            public void onTouchEvent(MotionEvent event, View view) {
                hideInputKeyboard(view);
            }
        };

        ((SceneTaskActivity) this.getActivity()).registerMyTouchListener(myTouchListener);
        return inflate;
    }


    public void hideInputKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    private void getAllNeedView(LinearLayout linearLayout) {
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View view = linearLayout.getChildAt(i);
            if (view instanceof LinearLayout) {
                getAllNeedView((LinearLayout) view);
            }
            if (view instanceof EditText) {
                etlist.add((EditText) view);
            }
        }

    }

    private boolean isEmpty(List<TextView> etlist) {
        boolean f = false;
        for (TextView textView : etlist) {
            if (TextUtils.isEmpty(textView.getText().toString().trim())) {
                return !f;
            }
        }
        return f ? true : false;
    }


    private void getETypeData(final TextView equipment_typeTv) {

        if (dialog != null) {
            dialog.setMessage("数据加载中...");
            dialog.show();
        }
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);

        Call<ResultData<EqptType>> call = rmtInterface.geteqptTypeList();
        HttpBusiness.action(getActivity(), false, call, new HttpCallBack<EqptType>() {
            List<EqptType.MainvoBean.HeadVOBean.SYDICTDATABean> datas = new ArrayList<>();

            @Override
            public void success(EqptType eqptType) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }

                if (eqptType != null) {
                    List<EqptType.MainvoBean> mainvo = eqptType.getMainvo();
                    if (mainvo != null && mainvo.size() > 0) {
                        for (EqptType.MainvoBean mainvoBean : mainvo) {
                            EqptType.MainvoBean.HeadVOBean headVO = mainvoBean.getHeadVO();
                            if (headVO != null) {
                                EqptType.MainvoBean.HeadVOBean.SYDICTDATABean sy_dict_data = headVO.getSY_DICT_DATA();
                                if (sy_dict_data != null) {
                                    datas.add(sy_dict_data);
                                }
                            }
                        }
                    }
                } else {
                    ToastUtils.toast(getActivity(), "未获取到设备类型");
                }

                if (datas != null && datas.size() > 0) {
                    showOwnerDialog(datas, equipment_typeTv);
                } else {
                    ToastUtils.toast(getActivity(), "未获取到设备类型");
                }

            }

            @Override
            public void warning(String msg) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                ToastUtils.toast(getActivity(), msg);
            }

            @Override
            public void error(Throwable t) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                ToastUtils.toast(getActivity(), "获取数据失败");
            }
        });
    }

    private void getNetData(final TextView v) {

        if (dialog != null) {
            dialog.setMessage("数据加载中...");
            dialog.show();
        }
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);

        long orgid = LoginUserRecord.getInstance().getUser().getOrgid();
        Call<ResultData<WorkSite>> call = rmtInterface.getworkSiteList(orgid);
        HttpBusiness.action(getActivity(), false, call, new HttpCallBack<WorkSite>() {
            List<WorkSite.MainvoBean.HeadVOBean.EAMPOSITIONMBean> datas = new ArrayList<>();

            @Override
            public void success(WorkSite workSite) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                if (workSite != null) {
                    List<WorkSite.MainvoBean> mainvo = workSite.getMainvo();
                    if (mainvo != null && mainvo.size() > 0) {
                        for (WorkSite.MainvoBean mainvoBean : mainvo) {
                            WorkSite.MainvoBean.HeadVOBean headVO = mainvoBean.getHeadVO();
                            if (headVO != null) {
                                WorkSite.MainvoBean.HeadVOBean.EAMPOSITIONMBean eam_position_m = headVO.getEAM_POSITION_M();
                                if (eam_position_m != null) {
                                    datas.add(eam_position_m);
                                }
                            }
                        }
                    }
                } else {
                    ToastUtils.toast(getActivity(), "未获取到作业区域");
                }
                if (datas != null && datas.size() > 0) {
                    showOwnerDialog(datas, v);
                } else {
                    ToastUtils.toast(getActivity(), "未获取到作业区域");
                }

            }

            @Override
            public void warning(String msg) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                ToastUtils.toast(getActivity(), msg);
            }

            @Override
            public void error(Throwable t) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                ToastUtils.toast(getActivity(), "获取数据失败");
            }
        });
    }

    /**
     * 显示选择现场负责人对话框
     *
     * @param rmtSysUsers
     */
    private <T> void showOwnerDialog(final List<T> rmtSysUsers, final TextView tv) {
        showPersionSelectDialog(rmtSysUsers);
        persionSelectDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                persionSelectDialog.dismiss();
                if (rmtSysUsers.get(position) instanceof WorkSite.MainvoBean.HeadVOBean.EAMPOSITIONMBean) {

                    tv.setText(((WorkSite.MainvoBean.HeadVOBean.EAMPOSITIONMBean) rmtSysUsers.get(position)).getPosition_name());
                    position_id = ((WorkSite.MainvoBean.HeadVOBean.EAMPOSITIONMBean) rmtSysUsers.get(position)).getPositionid();
                } else if (rmtSysUsers.get(position) instanceof EqptType.MainvoBean.HeadVOBean.SYDICTDATABean) {

                    tv.setText(((EqptType.MainvoBean.HeadVOBean.SYDICTDATABean) rmtSysUsers.get(position)).getDict_data_name());
                    dict_data_code = ((EqptType.MainvoBean.HeadVOBean.SYDICTDATABean) rmtSysUsers.get(position)).getDict_data_code();
                }

            }
        });
    }

    /**
     * 显示人员选择对话框
     *
     * @param rmtSysUsers
     */
    private <T> void showPersionSelectDialog(List<T> rmtSysUsers) {
        if (persionSelectDialog == null) {
            persionSelectDialog = new PersionSelectDialog(rmtSysUsers, getActivity());
        } else {
            persionSelectDialog.setRmtSysUsers(rmtSysUsers);
            persionSelectDialog.show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((SceneTaskActivity) this.getActivity()).unRegisterMyTouchListener(myTouchListener);
    }
}
