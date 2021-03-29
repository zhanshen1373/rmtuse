/**
 * Project Name:hse-scene-phone-app
 * File Name:GasDetectActivity.java
 * Package Name:com.hd.hse.scene.phone.ui.activity
 * Date:2015年12月8日
 * Copyright (c) 2015, zhaofeng@ushayden.com All Rights Reserved.
 */

package com.hd.hse.scene.phone.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hayden.hap.common.utils.ToastUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.constant.IActionBar;
import com.hd.hse.common.component.phone.custom.ExpandListView;
import com.hd.hse.common.component.phone.custom.ProgressDialog;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.component.phone.util.DateTimePickDialogUtil;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.common.module.branch.ui.model.activity.FrameworkActivity;
import com.hd.hse.common.module.phone.ui.activity.BaseFrameActivity;
import com.hd.hse.common.module.ui.model.fragment.CombinationUI;
import com.hd.hse.entity.base.CommonVO;
import com.hd.hse.entity.base.GasDetection2;
import com.hd.hse.entity.workorder.RmtWorkGasDetect;
import com.hd.hse.entity.workorder.RmtWorkSubtask;

import org.apache.log4j.Logger;
import org.hse.scene.phone.app.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;

/**
 * ClassName:GasDetectActivity ().<br/>
 * Date: 2015年12月8日 <br/>
 *
 * @author zhaofeng
 * @see
 */
public class GasDetectActivity extends BaseFrameActivity {
    private static Logger logger = LogUtils.getLogger(GasDetectActivity.class);
    private Button addBtn; // 新增按钮
    private View addView; // 新增布局
    private TextView addrEdit; // 检测位置
    private TextView timeTxt; // 检测时间
    // private TextView isOkTxt; // 是否合格
    private ExpandListView concentrationListView; // 检测浓度
    private Button saveBtn; // 审核（保存）按钮

    // private ExpandListView historyTitleListView; // 历史记录标题
    private ViewPager historyDetailViewPager; // 历史记录内容
    private RadioGroup historyDetailIndicator; // 历史记录内容指示器

    /**
     * 此页面中有 ViewPager与Indictor 结合的UI 需要这样一个工具类
     */
    private CombinationUI combinationUI = new CombinationUI();

    private GasDetection2 dataForAdd;
    private List<RmtWorkGasDetect.MainvoBean> historyDatas;

    private RmtWorkSubtask workTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hd_hse_gas_detect_activity_layout);
        initActionbar();
        findView();
        initData();
        // initView();
    }

    private void initActionbar() {
        setCustomActionBar(false, eventLst, new String[]{IActionBar.TV_TITLE,
                IActionBar.TV_BACK});
        setActionBartitleContent("气体检测", false);
    }

    private void findView() {
        addBtn = (Button) findViewById(R.id.add_btn);
        addView = findViewById(R.id.add_view);
        addView.setVisibility(View.GONE);
        addrEdit = (TextView) findViewById(R.id.addr_edit);
        timeTxt = (TextView) findViewById(R.id.time_txt);
        // isOkTxt = (TextView) findViewById(R.id.isok_txt);
        concentrationListView = (ExpandListView) findViewById(R.id.concentration_listview);
        saveBtn = (Button) findViewById(R.id.save_btn);

        // historyTitleListView = (ExpandListView)
        // findViewById(R.id.hd_hse_common_module_table_title);
        historyDetailViewPager = (ViewPager) findViewById(R.id.hd_hse_common_module_table_content);
        historyDetailIndicator = (RadioGroup) findViewById(R.id.hd_hse_common_module_radio_group);
    }

    private void initData() {
        workTask = (RmtWorkSubtask) getIntent().getSerializableExtra(
                FrameworkActivity.INTENT_WORKTASK_KEY);
        setActionBartitleContent(workTask.getDescription(), false);
        final ProgressDialog dialog = new ProgressDialog(
                GasDetectActivity.this, "正在获取历史记录...");
        dialog.show();

        RmtInterface anInterface = RetrofitUtil.createInterface(RmtInterface.class);

        Call<ResultData<RmtWorkGasDetect>> call = anInterface.getRmtWorkGas(workTask.getWork_subtask_id());
        HttpBusiness.action(GasDetectActivity.this, false, call, new HttpCallBack<RmtWorkGasDetect>() {
            @Override
            public void success(RmtWorkGasDetect rmtWorkGasDetect) {

                dialog.dismiss();
                dataForAdd = new GasDetection2();
                List<RmtWorkGasDetect.MainvoBean> mainvo = rmtWorkGasDetect.getMainvo();
                List<RmtWorkGasDetect.DictvosBean.RMTGASTYPETREEMBean> rmt_gas_type_tree_m = rmtWorkGasDetect.getDictvos().getRMT_GAS_TYPE_TREE_M();
                dataForAdd.setGasDetails(rmt_gas_type_tree_m);
                setObject("gasdetail", rmtWorkGasDetect);
                if (mainvo != null) {
//					List<RmtWorkGasDetect.MainvoBean> mainvo = rmtWorkGasDetect.getMainvo();
//					List<RmtWorkGasDetect.DictvosBean.RMTGASTYPETREEMBean> rmt_gas_type_tree_m = rmtWorkGasDetect.getDictvos().getRMT_GAS_TYPE_TREE_M();
//
//					dataForAdd.setGasDetails(rmt_gas_type_tree_m);
                    historyDatas = mainvo;
//					setObject("gasdetail",rmtWorkGasDetect);

                    initView();
                } else {
                    initView();
                }
            }

            @Override
            public void warning(String msg) {
                dialog.dismiss();
                ToastUtil.toast(GasDetectActivity.this, msg + "/t");
            }

            @Override
            public void error(Throwable t) {
                dialog.dismiss();
                ToastUtil.toast(GasDetectActivity.this, "获取数据列表失败");
            }
        });


    }

    public void setObject(String key, Object object) {
        SharedPreferences sp = GasDetectActivity.this.getSharedPreferences("hd", Context.MODE_PRIVATE);

        //创建字节输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //创建字节对象输出流
        ObjectOutputStream out = null;
        try {
            //然后通过将字对象进行64转码，写入key值为key的sp中
            out = new ObjectOutputStream(baos);
            out.writeObject(object);
            String objectVal = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(key, objectVal);
            editor.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void initView() {
        addView.setVisibility(View.GONE);
        //这个不可能为Null     dataForAdd.getGasDetails();
        if (historyDatas == null) {

            addBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    addView.setVisibility(View.VISIBLE);
                    timeTxt.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                            .format(new Date()));
                    initGasDetail();

                    timeTxt.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            String str = ((TextView) v).getText().toString();
                            DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                                    GasDetectActivity.this, str);
                            dateTimePicKDialog.dateTimePicKDialog(((TextView) v));
                        }
                    });

                    saveBtn.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View arg0) {
                            saveGas();
                        }
                    });

                    concentrationListView.setAdapter(new ConcentrationListAdapter(
                            dataForAdd.getGasDetails()));

                }
            });
        } else {

            addBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    addView.setVisibility(View.VISIBLE);
                    timeTxt.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                            .format(new Date()));
                    setAddr();
                    initGasDetail();
                }
            });

            timeTxt.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    String str = ((TextView) v).getText().toString();
                    DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                            GasDetectActivity.this, str);
                    dateTimePicKDialog.dateTimePicKDialog(((TextView) v));
                }
            });

            concentrationListView.setAdapter(new ConcentrationListAdapter(
                    dataForAdd.getGasDetails()));

            saveBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    saveGas();
                }
            });
            historyDetailViewPager.setAdapter(new HistoryViewPagerAdapter(
                    GasDetectActivity.this, historyDatas));
            // 绑定viewpager指示器
            combinationUI.bindIndictor2ViewPager(GasDetectActivity.this,
                    historyDetailIndicator, historyDetailViewPager);
        }
    }

    private void setAddr() {
        if (historyDatas != null && historyDatas.size() != 0) {
            String addr;
            for (RmtWorkGasDetect.MainvoBean h : historyDatas) {
                addr = h.getHeadVO().getRMT_WORK_GAS_DETECT_M().getDetect_site();
                if (addr != null && !addr.equals("")) {
                    addrEdit.setText(addr);
                }
            }
        }
    }

    private void initGasDetail() {
        for (int i = 0; i < concentrationListView.getChildCount(); i++) {
            View view = concentrationListView.getChildAt(i);
            EditText editText = (EditText) view.findViewById(R.id.value);
            editText.setText("");
        }
    }


    /**
     * 保存气体检测数据(向服务器发送气体检测数据). <br/>
     * saveGas:(). <br/>
     * date: 2016年1月11日 <br/>
     *
     * @author LiuYang
     */
    private void saveGas() {
        List<CommonVO> gasdetail = new ArrayList<>();
        String addAddr = addrEdit.getText().toString();
        if (addAddr == null || addAddr.equals("")) {
            ToastUtils.imgToast(GasDetectActivity.this,
                    R.drawable.hd_hse_common_msg_wrong, "请输入检测位置");
            return;
        }
        dataForAdd.setAddr(addAddr);
        String addTime = timeTxt.getText().toString();

        dataForAdd.setTime(addTime);
        boolean hasGasValue = false; // 判断有没有填气体浓度，有一条气体浓度有值就可以
        int ishg = 1; // 判断气体检测结果是否合格
        for (int i = 0; i < concentrationListView.getChildCount(); i++) {
            View view = concentrationListView.getChildAt(i);
            Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
            int position = spinner.getSelectedItemPosition();
            String selectedGasType = dataForAdd.getGasDetails().get(i)
                    .getChildren().get(position).getCode();
            String parentGasType = dataForAdd.getGasDetails().get(i)
                    .getCode();
            EditText editText = (EditText) view.findViewById(R.id.value);
            String value = editText.getText().toString();
            if (value != null && !value.equals("")) {
                hasGasValue = true;

                String lowerlimit = dataForAdd.getGasDetails().get(i).getChildren()
                        .get(position).getLowerlimit();
                double min = 0;
                if (lowerlimit != null) {
                    min = Double.valueOf(lowerlimit).doubleValue();
                }
                String upperlimit = dataForAdd.getGasDetails().get(i).getChildren()
                        .get(position).getUpperlimit();
                double max = 0;
                if (upperlimit != null) {
                    max = Double.valueOf(upperlimit).doubleValue();
                }
                double val = Double.valueOf(value);

                int boundary = dataForAdd.getGasDetails().get(i).getChildren()
                        .get(position).getBoundary();
                if (boundary == 1) {
                    if ((val > max && max != 0) || (val < min && min != 0)) {
                        ishg = 0;
                    }
                } else {
                    if ((val >= max && max != 0) || (val <= min && min != 0)) {
                        ishg = 0;
                    }
                }

            }
            CommonVO cvo = new CommonVO();
            cvo.setCode(parentGasType);
            cvo.setSubcode(selectedGasType);
            cvo.setWork_subtask_id(workTask.getWork_subtask_id());
            cvo.setGasvalue(value);
            gasdetail.add(cvo);
        }
        dataForAdd.setIsOk(ishg);
        if (hasGasValue) {
            final ProgressDialog dialog = new ProgressDialog(
                    GasDetectActivity.this, "正在上传...");
            dialog.show();

            RmtInterface anInterface = RetrofitUtil.createInterface(RmtInterface.class);
            RmtInterface.SaveGasRequestBody saveGasRequestBody = new RmtInterface.SaveGasRequestBody();
            saveGasRequestBody.work_subtask_id = workTask.getWork_subtask_id();
            saveGasRequestBody.detect_site = dataForAdd.getAddr();
            saveGasRequestBody.detect_time = dataForAdd.getTime();
            saveGasRequestBody.qualified = dataForAdd.getIsOk();
            saveGasRequestBody.voList = gasdetail;
            saveGasRequestBody.analyzer_id = LoginUserRecord.getInstance().getUser().getUserid();
            saveGasRequestBody.analyzer_name = LoginUserRecord.getInstance().getUser().getUserName();
//			Call<JsonObject> call = anInterface.savegas(saveGasRequestBody);

            Call<ResultData<Object>> savegas = anInterface.savegas(saveGasRequestBody);
            HttpBusiness.action(GasDetectActivity.this, false, savegas, new HttpCallBack<Object>() {
                @Override
                public void success(Object o) {
                    dialog.hide();
                    ToastUtils.imgToast(GasDetectActivity.this,
                            R.drawable.hd_hse_common_msg_right,
                            "上传数据完成");
                    initData(); // 上传完成后重新获取气体历史记录，刷新界面
                }

                @Override
                public void warning(String msg) {
                    dialog.hide();
                    ToastUtils.imgToast(GasDetectActivity.this, R.drawable.hd_hse_common_msg_wrong,
                            msg);
                }

                @Override
                public void error(Throwable t) {

                    dialog.hide();
                    ToastUtils.imgToast(GasDetectActivity.this,
                            R.drawable.hd_hse_common_msg_wrong,
                            "上传数据失败！");
                }
            });


        } else {
            ToastUtils.imgToast(GasDetectActivity.this,
                    R.drawable.hd_hse_common_msg_wrong, "请输入气体浓度！");
        }
    }

    /**
     * 气体检测浓度列表Adapter ClassName: ConcentrationListAdapter ()<br/>
     * date: 2015年12月15日 <br/>
     *
     * @author LiuYang
     * @version GasDetectActivity
     */
    private class ConcentrationListAdapter extends BaseAdapter {
        private List<RmtWorkGasDetect.DictvosBean.RMTGASTYPETREEMBean> gasDetails;

        public ConcentrationListAdapter(List<RmtWorkGasDetect.DictvosBean.RMTGASTYPETREEMBean> gasDetails) {
            this.gasDetails = gasDetails;
        }

        @Override
        public int getCount() {
            return gasDetails == null ? 0 : this.gasDetails.size();
        }

        @Override
        public Object getItem(int arg0) {
            return gasDetails.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            return arg0;
        }

        @Override
        public View getView(final int position, View view, ViewGroup viewGroup) {
            ViewHolder holder = null;
            if (view == null) {
                view = LayoutInflater.from(GasDetectActivity.this).inflate(
                        R.layout.hd_hse_gas_detect_nongdu_list_item, null);
                holder = new ViewHolder();
                holder.title = (TextView) view.findViewById(R.id.title);
                holder.spinner = (Spinner) view.findViewById(R.id.spinner);
                holder.value = (EditText) view.findViewById(R.id.value);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            if (position == 0) {
                holder.title.setVisibility(View.VISIBLE);
            } else {
                holder.title.setVisibility(View.INVISIBLE);
            }
            List<String> list = new ArrayList<>();
//            for (Domain d : gasDetails.get(position).getGasList()) {
//            }
            if (position == 0) {
                for (int i = 0; i < gasDetails.get(position).getChildren().size(); i++) {
                    list.add(gasDetails.get(position).getChildren().get(i).getName());
                }
            } else if (position == 1) {
                for (int i = 0; i < gasDetails.get(position).getChildren().size(); i++) {
                    list.add(gasDetails.get(position).getChildren().get(i).getName());
                }
            } else if (position == 2) {
                for (int i = 0; i < gasDetails.get(position).getChildren().size(); i++) {
                    list.add(gasDetails.get(position).getChildren().get(i).getName());
                }

            }
//            list.add(gasDetails.get(position).getChildren().get(position).getName());
            holder.spinner.setAdapter(new SpinnerAdapter(list));
            return view;
        }

        private class ViewHolder {
            TextView title;
            Spinner spinner;
            @SuppressWarnings("unused")
            EditText value;
        }

    }

    public IEventListener eventLst = new IEventListener() {

        @Override
        public void eventProcess(int eventType, Object... objects)
                throws HDException {

        }

    };

    /**
     * 历史记录列表ViewPager适配器 ClassName: HistoryViewPagerAdapter ()<br/>
     * date: 2015年12月15日 <br/>
     *
     * @author LiuYang
     * @version GasDetectActivity
     */
    private class HistoryViewPagerAdapter extends PagerAdapter {
        private Context context;
        private List<RmtWorkGasDetect.MainvoBean> datas;


        public HistoryViewPagerAdapter(Context context,
                                       List<RmtWorkGasDetect.MainvoBean> historyDatas) {
            this.context = context;
            this.datas = historyDatas;

        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Log.w(this.getClass().getSimpleName(), "destroyItem position:"
                    + position);
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(context).inflate(
                    R.layout.hd_hse_gas_detect_activity_history_item, null);
            TextView addrTxt = (TextView) view
                    .findViewById(R.id.gas_history_addr);
            TextView timeTxt = (TextView) view
                    .findViewById(R.id.gas_history_time);
            TextView isOkTxt = (TextView) view
                    .findViewById(R.id.gas_history_isok);
            ListView concentrationListView = (ListView) view
                    .findViewById(R.id.gas_history_concentration_listview);
            addrTxt.setText(datas.get(position).getHeadVO().getRMT_WORK_GAS_DETECT_M().getDetect_site());
            timeTxt.setText(datas.get(position).getHeadVO().getRMT_WORK_GAS_DETECT_M().getDetect_time());
            isOkTxt.setText(datas.get(position).getHeadVO().getRMT_WORK_GAS_DETECT_M().getQualified() == 1 ? "合格" : "不合格");
            if (datas.get(position).getHeadVO().getRMT_WORK_GAS_DETECT_M().getQualified() == 1) {
                isOkTxt.setTextColor(getResources().getColor(
                        R.color.hd_hse_common_green));
            } else {
                isOkTxt.setTextColor(getResources().getColor(
                        R.color.hd_hse_common_red));
            }

            concentrationListView.setAdapter(new ConcentrationHistoryAdapter(
                    context, datas.get(position).getBodyVOs().getRMT_WORK_GAS_DETECT_LINE_M()));
            container.addView(view);
            return view;
        }

    }

    private class SpinnerAdapter extends BaseAdapter {
        private List<String> data;

        public SpinnerAdapter(List<String> data) {
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int arg0) {
            return data.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            return arg0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            ViewHolder holder = null;
            if (view == null) {
                view = LayoutInflater.from(GasDetectActivity.this).inflate(
                        R.layout.item_gasdetect_spinner, null);
                holder = new ViewHolder();
                holder.textView = (TextView) view.findViewById(R.id.textview);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            holder.textView.setText((String) getItem(position));
            return view;
        }

        private class ViewHolder {
            TextView textView;
        }

    }

    /**
     * 气体检测历史记录里的气体浓度列表的Adapter ClassName: ConcentrationHistoryAdapter ()<br/>
     * date: 2015年12月15日 <br/>
     *
     * @author LiuYang
     * @version GasDetectActivity
     */
    private class ConcentrationHistoryAdapter extends BaseAdapter {
        private Context context;
        private List<RmtWorkGasDetect.MainvoBean.BodyVOsBean.RMTWORKGASDETECTLINEMBeanX> datas;

        public ConcentrationHistoryAdapter(Context context,
                                           List<RmtWorkGasDetect.MainvoBean.BodyVOsBean.RMTWORKGASDETECTLINEMBeanX> datas) {
            this.context = context;
            this.datas = datas;
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int arg0) {
            return datas.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            return arg0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            ViewHolder holder = null;
            if (view == null) {
                view = LayoutInflater.from(context).inflate(
                        R.layout.hd_hse_gas_detect_history_nd_list_item, null);
                holder = new ViewHolder();
                holder.gasType = (TextView) view.findViewById(R.id.gas_type);
                holder.gasValue = (TextView) view.findViewById(R.id.gas_value);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            holder.gasType.setText(datas.get(position).getHeadVO().getRMT_WORK_GAS_DETECT_LINE_M().getGas_type_sub_name());
            holder.gasValue.setText(String.valueOf(datas.get(position).getHeadVO().getRMT_WORK_GAS_DETECT_LINE_M().getGas_value()));
            return view;
        }

        private class ViewHolder {
            TextView gasType;
            TextView gasValue;
        }

    }

}
