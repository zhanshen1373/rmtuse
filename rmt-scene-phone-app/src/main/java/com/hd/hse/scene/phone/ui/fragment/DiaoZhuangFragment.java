package com.hd.hse.scene.phone.ui.fragment;

import android.app.ProgressDialog;
import android.support.annotation.IdRes;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.module.branch.ui.model.fragment.BaseFragment;
import com.hd.hse.entity.workorder.RmtWorkPermit;

import org.hse.scene.phone.app.R;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * Created by dubojian on 2017/8/9.
 */

public class DiaoZhuangFragment extends BaseFragment {


    private ListView dzf_listview;
    private ProgressDialog dialog;
    private ArrayList<RmtWorkPermit> list;
    private List<RmtWorkPermit> data;
    private List<RmtWorkPermit> permitMVOList;

    public DiaoZhuangFragment() {
        super(STYLE_SINGLE_BUTTON);
    }

    @Override
    protected View onCreatePageView(LayoutInflater inflater) {

        View inflate = inflater.inflate(R.layout.hd_hse_scene_phone_diaozhuang_fragment, null);
        findView(inflate);
        initData();
        initView();

        setOnBottomButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog == null) {
                    dialog = new ProgressDialog(getActivity());
                    dialog.setMessage("保存中...");
                }
                dialog.show();

                data = new ArrayList<RmtWorkPermit>();
                data.addAll(permitMVOList);
                data.addAll(list);
                mPDAWorkOrderInfoConfig.setPermitMVOList(data);

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

        return inflate;

    }

    private void initData() {
        if (list != null) {
            return;
        }
        list = new ArrayList<>();
        permitMVOList = mPDAWorkOrderInfoConfig.getPermitMVOList();
        if (permitMVOList != null && permitMVOList.size() > 0) {
            for (int i=0;i<permitMVOList.size();i++){
                if ("hst".equals(permitMVOList.get(i).getWork_type())) {
                    list.add(permitMVOList.get(i));
                    permitMVOList.remove(permitMVOList.get(i));
                    i--;
                }
            }
        }
    }

    private void initView() {

        dzf_listview.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return list.size() > 0 ? list.size() : 0;
            }

            @Override
            public Object getItem(int position) {
                return list.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {

                ViewHold vh = null;
                if (convertView == null) {
                    vh = new ViewHold();
                    convertView = View.inflate(getActivity(), R.layout.dzf_listview_item, null);
                    vh.textView_name = (TextView) convertView.findViewById(R.id.dzf_listview_textview_name);
                    vh.editText_name = (EditText) convertView.findViewById(R.id.dzf_listview_edittext_name);

                    vh.textView_volume = (TextView) convertView.findViewById(R.id.dzf_listview_textview_volume);
                    vh.editText_volume = (EditText) convertView.findViewById(R.id.dzf_listview_edittext_volume);

                    vh.textView_object_weight = (TextView) convertView.findViewById(R.id.dzf_listview_textview_object_weight);
                    vh.editText_object_weight = (EditText) convertView.findViewById(R.id.dzf_listview_edittext_object_weight);

                    vh.textView_lift_weight = (TextView) convertView.findViewById(R.id.dzf_listview_textview_lift_weight);
                    vh.editText_lift_weight = (EditText) convertView.findViewById(R.id.dzf_listview_edittext_lift_weight);

                    vh.textView_radius = (TextView) convertView.findViewById(R.id.dzf_listview_textview_radius);
                    vh.editText_radius = (EditText) convertView.findViewById(R.id.dzf_listview_edittext_radius);

                    vh.textView_height = (TextView) convertView.findViewById(R.id.dzf_listview_textview_height);
                    vh.editText_height = (EditText) convertView.findViewById(R.id.dzf_listview_edittext_height);

                    vh.textView_load = (TextView) convertView.findViewById(R.id.dzf_listview_textview_load);
                    vh.editText_load = (EditText) convertView.findViewById(R.id.dzf_listview_edittext_load);

                    vh.textView_angle = (TextView) convertView.findViewById(R.id.dzf_listview_textview_angle);
                    vh.editText_angle = (EditText) convertView.findViewById(R.id.dzf_listview_edittext_angle);


                    vh.textView_ts = (TextView) convertView.findViewById(R.id.dzf_listview_textview_ts);
                    vh.radioButton_ts1 = (RadioButton) convertView.findViewById(R.id.radiobutton1);
                    vh.radioButton_ts2 = (RadioButton) convertView.findViewById(R.id.radiobutton2);
                    vh.radioGroup = (RadioGroup) convertView.findViewById(R.id.radiogroup);

                    convertView.setTag(vh);
                } else {
                    vh = (ViewHold) convertView.getTag();
                }
                //记录的position
                vh.editText_name.setTag(position);

                vh.editText_name.setText(list.get(position).getHst_object_name() != null ? list.get(position)
                        .getHst_object_name() : "");
                vh.editText_name.addTextChangedListener(new MyTextWatcher(vh, "editText_name"));

                vh.editText_volume.setText(list.get(position).getHst_object_volume() != null ?
                        list.get(position).getHst_object_volume() + "" : "");
                vh.editText_volume.addTextChangedListener(new MyTextWatcher(vh, "editText_volume"));

                vh.editText_object_weight.setText(list.get(position).getHst_object_weight() != null ?
                        list.get(position).getHst_object_weight() + "" : "");
                vh.editText_object_weight.addTextChangedListener(new MyTextWatcher(vh, "editText_object_weight"));


                vh.editText_lift_weight.setText(list.get(position).getHst_lift_weight() != null ?
                        list.get(position).getHst_lift_weight() + "" : "");
                vh.editText_lift_weight.addTextChangedListener(new MyTextWatcher(vh, "editText_lift_weight"));


                vh.editText_radius.setText(list.get(position).getHst_radius() != null ?
                        list.get(position).getHst_radius() + "" : "");
                vh.editText_radius.addTextChangedListener(new MyTextWatcher(vh, "editText_radius"));


                vh.editText_height.setText(list.get(position).getHst_height() != null ?
                        list.get(position).getHst_height() + "" : "");
                vh.editText_height.addTextChangedListener(new MyTextWatcher(vh, "editText_height"));


                vh.editText_load.setText(list.get(position).getHst_rated_load() != null ?
                        list.get(position).getHst_rated_load() + "" : "");
                vh.editText_load.addTextChangedListener(new MyTextWatcher(vh, "editText_load"));


                vh.editText_angle.setText(list.get(position).getHst_angle() != null ?
                        list.get(position).getHst_angle() + "" : "");
                vh.editText_angle.addTextChangedListener(new MyTextWatcher(vh, "editText_angle"));

                if (list.get(position).getHst_ts() == 1) {
                    vh.radioGroup.check(R.id.radiobutton1);
                } else {
                    vh.radioGroup.check(R.id.radiobutton2);
                }
                vh.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                        if (checkedId == R.id.radiobutton1) {
                            group.check(R.id.radiobutton1);
                            RadioButton rb = (RadioButton) group.findViewById(R.id.radiobutton1);
                            if (rb.isChecked()) {
                                list.get(position).setHst_ts(1);
                            }
                        } else if (checkedId == R.id.radiobutton2) {
                            group.check(R.id.radiobutton2);
                            RadioButton rb2 = (RadioButton) group.findViewById(R.id.radiobutton2);
                            if (rb2.isChecked()) {
                                list.get(position).setHst_ts(0);

                            }

                        }
                    }
                });


                return convertView;

            }
        });
    }

    private void findView(View inflate) {
        dzf_listview = (ListView) inflate.findViewById(R.id.hd_hse_scene_phone_diaozhuang_fragment_listview);
    }

    static class ViewHold {
        TextView textView_name;
        EditText editText_name;
        TextView textView_volume;
        EditText editText_volume;

        TextView textView_object_weight;
        EditText editText_object_weight;
        TextView textView_lift_weight;
        EditText editText_lift_weight;

        TextView textView_radius;
        EditText editText_radius;
        TextView textView_height;
        EditText editText_height;

        TextView textView_load;
        EditText editText_load;
        TextView textView_angle;
        EditText editText_angle;

        TextView textView_ts;
        RadioGroup radioGroup;
        RadioButton radioButton_ts1;
        RadioButton radioButton_ts2;

    }

    class MyTextWatcher implements TextWatcher {

        private ViewHold mhold;
        private String flag;

        public MyTextWatcher(ViewHold mhold, String flag) {
            this.mhold = mhold;
            this.flag = flag;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            int position = (Integer) mhold.editText_name.getTag();
            switch (flag) {
                case "editText_name":
                    list.get(position).setHst_object_name(s.toString());
                    break;
                case "editText_volume":
                    if (s.toString() != null && (!("".equals(s.toString())))) {
                        list.get(position).setHst_object_volume(s.toString());
                    } else {
                        list.get(position).setHst_object_volume("0");
                    }

                    break;
                case "editText_object_weight":
                    if (s.toString() != null && (!("".equals(s.toString())))) {
                        list.get(position).setHst_object_weight(new BigDecimal(s.toString()));
                    } else {
                        list.get(position).setHst_object_weight(new BigDecimal("0"));
                    }
                    break;
                case "editText_lift_weight":
                    if (s.toString() != null && (!("".equals(s.toString())))) {
                        list.get(position).setHst_lift_weight(new BigDecimal(s.toString()));
                    } else {
                        list.get(position).setHst_lift_weight(new BigDecimal("0"));
                    }
                    break;
                case "editText_radius":
                    if (s.toString() != null && (!("".equals(s.toString())))) {
                        list.get(position).setHst_radius(new BigDecimal(s.toString()));
                    } else {
                        list.get(position).setHst_radius(new BigDecimal("0"));
                    }
                    break;
                case "editText_height":
                    if (s.toString() != null && (!("".equals(s.toString())))) {
                        list.get(position).setHst_height(new BigDecimal(s.toString()));
                    } else {
                        list.get(position).setHst_height(new BigDecimal("0"));
                    }
                    break;
                case "editText_load":
                    if (s.toString() != null && (!("".equals(s.toString())))) {
                        list.get(position).setHst_rated_load(new BigDecimal(s.toString()));
                    } else {
                        list.get(position).setHst_rated_load(new BigDecimal("0"));
                    }
                    break;
                case "editText_angle":
                    if (s.toString() != null && (!("".equals(s.toString())))) {
                        list.get(position).setHst_angle(new BigDecimal(s.toString()));
                    } else {
                        list.get(position).setHst_angle(new BigDecimal("0"));

                    }
                    break;
                default:

                    break;
            }
        }
    }
}
